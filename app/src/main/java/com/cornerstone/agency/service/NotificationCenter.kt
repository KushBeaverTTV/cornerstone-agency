package com.cornerstone.agency.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.cornerstone.agency.MainActivity
import com.cornerstone.agency.R
import com.cornerstone.agency.data.GatewayHub
import com.cornerstone.agency.data.GatewaySocket
import com.cornerstone.agency.data.PendingRequests
import com.cornerstone.agency.data.PendingRequest
import com.cornerstone.agency.data.RequestKind
import com.cornerstone.agency.data.pendingRequestFrom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import java.util.concurrent.ConcurrentHashMap

/**
 * System-notification surface for the dashboard socket. Owns the three
 * channels, the persistent "connected" notification for ConnectionService,
 * and a global collector that turns server→client request frames into
 * actionable heads-up notifications even when no chat screen is open.
 *
 * Bound once per socket via [bind]; collectors live on NotificationCenter's
 * own process-level scope so they keep running while backgrounded.
 */
object NotificationCenter {

    const val CH_CONNECTION = "connection"
    const val CH_REQUESTS = "requests"
    const val CH_MESSAGES = "messages"
    const val CH_FEED = "feed"
    const val CONN_NOTIFICATION_ID = 100
    const val FEED_NOTIFICATION_ID = 101

    const val EXTRA_PROFILE = "com.cornerstone.agency.extra.PROFILE"
    const val EXTRA_SESSION = "com.cornerstone.agency.extra.SESSION"
    /** Open the shell on a tab ("feed"). */
    const val EXTRA_TAB = "com.cornerstone.agency.extra.TAB"

    private const val MESSAGE_NOTIF_BASE = 20000
    private const val MESSAGE_NOTIF_SPAN = 9000
    private const val REQUEST_NOTIF_BASE = 30000
    private const val REQUEST_NOTIF_SPAN = 9000

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    // Stable, collision-free notification id ranges: requests keyed by request
    // id (30000+), messages by session key (20000+). A message heads-up must
    // never overwrite a pending request card notification.
    private fun requestNotifId(key: String): Int =
        REQUEST_NOTIF_BASE + (key.hashCode() and 0x7FFF) % REQUEST_NOTIF_SPAN

    private fun messageNotifId(sessionKey: String): Int =
        MESSAGE_NOTIF_BASE + (sessionKey.hashCode() and 0x7FFF) % MESSAGE_NOTIF_SPAN

    /** Request keys resolved outside a chat card (notification action, TTL,
     * server event). ChatRepository collects this to collapse the card too —
     * the server does not emit a *.resolved event for srq-frame answers. */
    val resolvedKeys = kotlinx.coroutines.flow.MutableSharedFlow<String>(
        extraBufferCapacity = 64,
    )

    private class Tracked(
        val notificationId: Int,
        val sessionKey: String,
        val profile: String,
        val key: String = "",
        val srq: String = "",
        val req: PendingRequest? = null,
    )

    /** One open request, answerable from the Chats list without opening the chat. */
    data class InboxItem(
        val key: String,
        val srq: String,
        val req: PendingRequest,
        val profile: String,
        val sessionKey: String,
    )

    private val _inbox = kotlinx.coroutines.flow.MutableStateFlow<List<InboxItem>>(emptyList())
    val inbox: kotlinx.coroutines.flow.StateFlow<List<InboxItem>> = _inbox

    /** Debug builds only: fake requests so the inbox UI can be verified without a live approval. */
    fun debugInject() {
        val a = PendingRequest(
            kind = RequestKind.Approval, requestId = "dbg-1", toolName = "terminal",
            command = "rm -rf ./build && ./gradlew assembleRelease",
        )
        val c = PendingRequest(
            kind = RequestKind.Clarify, requestId = "dbg-2",
            questions = listOf(com.cornerstone.agency.data.ClarifyQuestion("q0", "Which color do you prefer?", listOf("red", "blue"))),
        )
        _inbox.value = listOf(
            InboxItem("dbg-1", "dbg-1", a, "default", "dbg-s1"),
            InboxItem("dbg-2", "dbg-2", c, "aurora", "dbg-s2"),
        )
    }

    private fun dropDebug(item: InboxItem): Boolean {
        if (!item.key.startsWith("dbg-")) return false
        _inbox.value = _inbox.value.filterNot { it.key == item.key }
        return true
    }

    /** Answer a single-question clarify with one of its choices (mirrors ChatRepository.answerClarify). */
    fun answerClarify(item: InboxItem, answer: String) {
        if (dropDebug(item)) return
        val q = item.req.questions.firstOrNull() ?: return
        val batch = item.req.questions.any { it.qid.isNotEmpty() }
        val result = if (batch) {
            buildJsonObject { put("answers", buildJsonObject { put(q.qid.ifEmpty { "q0" }, answer) }) }
        } else {
            buildJsonObject { put("answer", answer) }
        }
        GatewayHub.socket?.respond(JsonPrimitive(item.srq), result)
        resolve(item.key)
    }

    /** Answer an approval from the inbox: same srq-frame path as the notification action. */
    fun answerApproval(item: InboxItem, choice: String) {
        if (dropDebug(item)) return
        GatewayHub.socket?.respond(
            JsonPrimitive(item.srq),
            buildJsonObject { put("choice", choice) },
        )
        resolve(item.key)
    }

    /** request_id / srq id -> posted notification + badge routing. */
    private val tracked = ConcurrentHashMap<String, Tracked>()
    private var bound: GatewaySocket? = null
    private var cronNames: Set<String>? = null

    @Volatile
    private var appContext: Context? = null

    fun ensureChannels(context: Context) {
        val nm = context.getSystemService(NotificationManager::class.java) ?: return
        nm.createNotificationChannels(
            listOf(
                NotificationChannel(
                    CH_CONNECTION, "Connection", NotificationManager.IMPORTANCE_MIN,
                ).apply { description = "Persistent connection state" },
                NotificationChannel(
                    CH_REQUESTS, "Requests", NotificationManager.IMPORTANCE_HIGH,
                ).apply { description = "Approvals and questions from your agents" },
                NotificationChannel(
                    CH_MESSAGES, "Messages", NotificationManager.IMPORTANCE_DEFAULT,
                ).apply { description = "Agent replies while the app is closed" },
                NotificationChannel(
                    CH_FEED, "Feed", NotificationManager.IMPORTANCE_LOW,
                ).apply { description = "A new issue of your Feed is ready" },
            ),
        )
    }

    /** "Kaori wrote today's issue · 7 new posts" — quiet, tap opens the Feed tab. */
    fun postFeedReady(context: Context, botTitle: String, added: Int) {
        val app = context.applicationContext
        ensureChannels(app)
        val nm = app.getSystemService(NotificationManager::class.java) ?: return
        val tap = PendingIntent.getActivity(
            app, FEED_NOTIFICATION_ID,
            Intent(app, MainActivity::class.java).apply {
                putExtra(EXTRA_TAB, "feed")
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )
        val posts = if (added == 1) "1 new post" else "$added new posts"
        nm.notify(
            FEED_NOTIFICATION_ID,
            NotificationCompat.Builder(app, CH_FEED)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("$botTitle wrote today's issue")
                .setContentText(posts)
                .setAutoCancel(true)
                .setContentIntent(tap)
                .build(),
        )
    }

    /** Persistent low-priority notification used by the foreground service. */
    fun connectionNotification(context: Context): Notification {
        ensureChannels(context)
        val open = PendingIntent.getActivity(
            context, 0,
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )
        val disconnect = PendingIntent.getService(
            context, 1,
            ConnectionService.intent(context, ConnectionService.ACTION_DISCONNECT),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )
        return NotificationCompat.Builder(context, CH_CONNECTION)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Cornerstone Agency")
            .setContentText("Connected to Hermes")
            .setOngoing(true)
            .setContentIntent(open)
            .addAction(0, "Disconnect", disconnect)
            .build()
    }

    /** Start collecting this socket's request frames + events (idempotent). */
    fun bind(context: Context, socket: GatewaySocket) {
        appContext = context.applicationContext
        if (bound === socket) return
        bound = socket
        val app = context.applicationContext
        scope.launch { socket.requests.collect { onRequest(app, socket, it) } }
        scope.launch { socket.events.collect { onEvent(app, it) } }
    }

    fun unbind() {
        bound = null
    }

    fun cancelAll(context: Context) {
        val nm = context.getSystemService(NotificationManager::class.java) ?: return
        tracked.values.forEach { nm.cancel(it.notificationId) }
        tracked.clear()
        reportCounts()
    }

    /** A request resolved anywhere (card tap, notification action, timeout,
     * another client) — drop its notification and badge count. */
    fun resolve(context: Context, key: String?) {
        if (key == null) return
        val t = tracked.remove(key) ?: return
        context.getSystemService(NotificationManager::class.java)
            ?.cancel(t.notificationId)
        reportCounts()
        resolvedKeys.tryEmit(key)
    }

    /** Context-free resolve for callers without a Context (e.g. ChatRepository). */
    fun resolve(key: String?) {
        val ctx = appContext ?: return
        resolve(ctx, key)
    }

    private fun reportCounts() = synchronized(tracked) {
        if (_inbox.value.any { it.key.startsWith("dbg-") } && tracked.isEmpty()) return@synchronized
        _inbox.value = tracked.values.mapNotNull { t ->
            t.req?.let { InboxItem(t.key, t.srq, it, t.profile, t.sessionKey) }
        }.sortedBy { it.req.createdAt }
        PendingRequests.reportExternal(
            tracked.values.groupingBy { it.sessionKey }.eachCount(),
            tracked.values.groupingBy { it.profile }.eachCount(),
        )
    }

    // ---- collectors -----------------------------------------------------

    private suspend fun onRequest(
        app: Context,
        socket: GatewaySocket,
        frame: com.cornerstone.agency.data.GatewayRequest,
    ) {
        val req = pendingRequestFrom(frame.method, frame.params, frame.id)
        val srq = frame.idString
        val key = req.requestId ?: srq
        if (tracked.containsKey(key)) return

        val runtimeSid = req.sessionId ?: ""
        var ref = GatewayHub.sessions[runtimeSid]
        if (ref == null && runtimeSid.isNotEmpty()) {
            ref = lookupSession(socket, runtimeSid)
        }
        val profile = ref?.profile ?: "default"
        val sessionKey = ref?.storedId?.takeIf { it.isNotEmpty() } ?: runtimeSid

        val notifId = requestNotifId(key)
        tracked[key] = Tracked(notifId, sessionKey, profile, key, srq, req)
        reportCounts()

        // When the user is literally looking at that chat, skip the heads-up —
        // the in-transcript card is already there. Backgrounded always notifies.
        val chatVisible = GatewayHub.foregrounded && runtimeSid in GatewayHub.openChats
        if (!chatVisible) {
            postRequestNotification(app, req, notifId, key, srq, profile, sessionKey)
        }

        // Local expiry — mirrors the card TTL so stale notifications clear.
        scope.launch {
            delay(PendingRequest.REQUEST_TTL_MS)
            resolve(app, key)
        }
    }

    private fun onEvent(app: Context, ev: com.cornerstone.agency.data.GatewayEvent) {
        when {
            ev.type == "request.cancel" ->
                resolve(app, ev.payload?.str("id"))

            ev.type.endsWith(".resolved") || ev.type.endsWith(".expired") ||
                ev.type.endsWith(".answered") || ev.type.endsWith(".cancelled") ||
                ev.type.endsWith(".canceled") || ev.type.endsWith(".timeout") ->
                resolve(
                    app,
                    ev.payload?.str("request_id") ?: ev.payload?.str("requestId"),
                )

            ev.type == "message.complete" && !GatewayHub.foregrounded -> {
                val sid = ev.sessionId ?: return
                val text = ev.payload?.str("text")?.takeIf { it.isNotBlank() } ?: return
                val ref = GatewayHub.sessions[sid]
                if (isCronChatter(ref?.title ?: "")) return
                postMessageNotification(app, ref, text, sid)
            }
        }
    }

    /** session.status returns a text blob: "Session ID: <stored>" and
     * "Path: <profile home>" — parse both to route deep links and badges. */
    private suspend fun lookupSession(
        socket: GatewaySocket,
        runtimeId: String,
    ): GatewayHub.SessionRef? {
        return try {
            val res = socket.call(
                "session.status",
                buildJsonObject { put("session_id", runtimeId) },
            )
            val output = (res as? JsonObject)?.str("output")
                ?: (res as? JsonPrimitive)?.contentOrNull ?: ""
            val stored = Regex("Session ID:\\s*(\\S+)")
                .find(output)?.groupValues?.get(1) ?: ""
            val prof = Regex("""profiles[\\/]([\w-]+)""")
                .find(output)?.groupValues?.get(1) ?: "default"
            val ref = GatewayHub.SessionRef(stored, prof)
            GatewayHub.sessions[runtimeId] = ref
            if (stored.isNotEmpty()) GatewayHub.sessions[stored] = ref
            ref
        } catch (_: Exception) {
            null
        }
    }

    private fun isCronChatter(title: String): Boolean {
        if (title.contains("scheduled cron job", ignoreCase = true)) return true
        val names = cronNames ?: return false
        return names.any { title == it || title.startsWith("$it ·") }
    }

    /** Lazily cache cron job names for the chatter filter (REST, once). */
    suspend fun warmCronFilter() {
        if (cronNames != null) return
        val repo = GatewayHub.repo ?: return
        cronNames = runCatching {
            repo.cronJobs().map { it.displayName }.toSet()
        }.getOrDefault(emptySet())
    }

    // ---- posting ----------------------------------------------------------

    private fun postRequestNotification(
        app: Context,
        req: PendingRequest,
        notifId: Int,
        key: String,
        srq: String,
        profile: String,
        sessionKey: String,
    ) {
        ensureChannels(app)
        val nm = app.getSystemService(NotificationManager::class.java) ?: return
        val bot = profileTitle(profile)
        val (title, body) = when (req.kind) {
            RequestKind.Approval ->
                "$bot needs approval" to
                    (req.command.ifBlank { req.description }.ifBlank { "Approval requested" })
            RequestKind.Clarify ->
                "$bot has a question" to
                    (req.questions.firstOrNull()?.question ?: req.prompt.ifBlank { "Question" })
            RequestKind.Secret -> "$bot needs a secret" to req.prompt.ifBlank { "Secret required" }
            RequestKind.Sudo -> "$bot needs sudo" to req.prompt.ifBlank { "Password required" }
            RequestKind.McpSetup -> "$bot · MCP setup" to req.title
            RequestKind.Value -> "$bot needs input" to
                req.prompt.ifBlank { req.title.ifBlank { "Request" } }
        }

        val tap = PendingIntent.getActivity(
            app, notifId,
            chatIntent(app, profile, sessionKey),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )
        val builder = NotificationCompat.Builder(app, CH_REQUESTS)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)
            .setContentIntent(tap)

        if (req.kind == RequestKind.Approval) {
            builder.addAction(
                0, "Allow once",
                actionIntent(app, notifId, key, srq, "once", profile, sessionKey),
            )
            builder.addAction(
                0, "Deny",
                actionIntent(app, notifId, key, srq, "deny", profile, sessionKey),
            )
        }
        nm.notify(notifId, builder.build())
    }

    private fun postMessageNotification(
        app: Context,
        ref: GatewayHub.SessionRef?,
        text: String,
        runtimeSid: String,
    ) {
        ensureChannels(app)
        val nm = app.getSystemService(NotificationManager::class.java) ?: return
        val profile = ref?.profile ?: "default"
        val bot = profileTitle(profile)
        val sessionKey = ref?.storedId?.takeIf { it.isNotEmpty() } ?: runtimeSid
        val notifId = messageNotifId(sessionKey)
        val tap = PendingIntent.getActivity(
            app, notifId,
            chatIntent(app, profile, ref?.storedId ?: ""),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )
        nm.notify(
            notifId,
            NotificationCompat.Builder(app, CH_MESSAGES)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(bot)
                .setContentText(text.take(120))
                .setStyle(NotificationCompat.BigTextStyle().bigText(text.take(300)))
                .setAutoCancel(true)
                .setContentIntent(tap)
                .build(),
        )
    }

    private fun chatIntent(app: Context, profile: String, storedId: String): Intent =
        Intent(app, MainActivity::class.java).apply {
            putExtra(EXTRA_PROFILE, profile)
            if (storedId.isNotEmpty()) putExtra(EXTRA_SESSION, storedId)
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

    private fun actionIntent(
        app: Context,
        notifId: Int,
        key: String,
        srq: String,
        choice: String,
        profile: String,
        sessionKey: String,
    ): PendingIntent = PendingIntent.getBroadcast(
        app, notifId * 31 + choice.hashCode(),
        Intent(app, RequestActionReceiver::class.java).apply {
            action = RequestActionReceiver.ACTION_RESPOND
            putExtra(RequestActionReceiver.EXTRA_NOTIF_ID, notifId)
            putExtra(RequestActionReceiver.EXTRA_KEY, key)
            putExtra(RequestActionReceiver.EXTRA_SRQ, srq)
            putExtra(RequestActionReceiver.EXTRA_CHOICE, choice)
            putExtra(EXTRA_PROFILE, profile)
            putExtra(EXTRA_SESSION, sessionKey)
        },
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
    )

    private fun profileTitle(profile: String): String =
        if (profile == "default") "Kaori" else profile.replaceFirstChar { it.uppercase() }

    private fun JsonObject.str(key: String): String? =
        this[key]?.jsonPrimitive?.contentOrNull
}
