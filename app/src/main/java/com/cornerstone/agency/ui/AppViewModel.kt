package com.cornerstone.agency.ui

import android.app.Application
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.viewModelScope
import com.cornerstone.agency.BuildConfig
import com.cornerstone.agency.data.ApiException
import com.cornerstone.agency.data.AutoConnect
import com.cornerstone.agency.data.KnownPc
import com.cornerstone.agency.data.GatewayState
import com.cornerstone.agency.data.BotProfile
import com.cornerstone.agency.data.CompanionRepository
import com.cornerstone.agency.data.ConnectionRepository
import com.cornerstone.agency.data.GatewayHub
import com.cornerstone.agency.data.GatewaySocket
import com.cornerstone.agency.data.Prefs
import com.cornerstone.agency.data.SecureStore
import com.cornerstone.agency.data.SessionInfo
import com.cornerstone.agency.data.StatusPayload
import com.cornerstone.agency.service.ConnectionService
import com.cornerstone.agency.service.NotificationCenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

data class UiState(
    val booting: Boolean = true,
    val connected: Boolean = false,
    val baseUrl: String = "",
    val sessionUser: String = "",
    // connect flow
    val connectBusy: Boolean = false,
    val connectError: String = "",
    val needsCredentials: Boolean = false,
    // shell
    val reachable: Boolean = true,
    val profiles: List<BotProfile> = emptyList(),
    val profilesLoading: Boolean = false,
    val profilesError: String = "",
    val status: StatusPayload? = null,
    // auto-connect
    /** What auto-connect is doing right now ("Looking for KUSH-PC…"); empty when idle. */
    val autoStatus: String = "",
    /** Name of the PC this build / address book knows, or empty. */
    val knownPcName: String = "",
)

/** A notification/intent asking the nav host to open a specific chat. */
data class ChatDeepLink(val profile: String, val session: String?)

class AppViewModel(private val app: Application) : AndroidViewModel(app) {

    private val store = SecureStore(app)
    internal val repo = ConnectionRepository(store)
    private val prefs = Prefs(app)

    /** The PC baked into this build (Cornerstone_Secrets/agency-pairing.env), if any. */
    private val bakedPc: KnownPc? = KnownPc.fromBaked(
        BuildConfig.AGENCY_PC_NAME, BuildConfig.AGENCY_PC_HOSTS, BuildConfig.AGENCY_BRIDGE_TOKEN,
        BuildConfig.AGENCY_VOICE_PORT, BuildConfig.AGENCY_VOICE_TOKEN,
    )
    // Declared before `init`: the cold-start sweep launched there touches both.
    private val autoConnectLock = kotlinx.coroutines.sync.Mutex()
    private var lastAutoConnectAt = 0L

    /** Local-first store for the Feed / Goals / Artifacts tabs (process singleton). */
    val companion = CompanionRepository.get(app)

    /** Keep the socket + process alive in background via ConnectionService. */
    val stayConnected: StateFlow<Boolean> =
        prefs.stayConnected.stateIn(
            viewModelScope,
            kotlinx.coroutines.flow.SharingStarted.Eagerly,
            false,
        )

    fun setStayConnected(stay: Boolean) {
        viewModelScope.launch {
            prefs.setStayConnected(stay)
            syncService()
        }
    }

    /** Shared dashboard socket — lives in GatewayHub so it survives the Activity. */
    val gateway: GatewaySocket = GatewayHub.attach(repo)

    /** Headless Feed writer (process singleton; a run outlives this ViewModel). */
    val feedWriter = com.cornerstone.agency.data.FeedWriter.get(app)

    fun writeFeed() {
        if (feedWriter.busy) return
        rememberFeedBot()
        feedWriter.start()
    }

    /** Persist which bot writes the Feed so scheduled runs in a cold process know too. */
    private fun rememberFeedBot() {
        val p = _ui.value.profiles.firstOrNull { it.isDefault || it.name == "default" } ?: return
        companion.setFeedBot(p.name, p.title)
    }

    fun setFeedSchedule(schedule: com.cornerstone.agency.data.FeedSchedule) {
        rememberFeedBot()
        companion.setFeedSchedule(schedule)
        com.cornerstone.agency.service.FeedScheduler.sync(app)
    }

    /** Notification tap asking for a shell tab ("feed"); AppShell consumes it. */
    private val _pendingTab = MutableStateFlow<String?>(null)
    val pendingTab: StateFlow<String?> = _pendingTab.asStateFlow()
    fun requestTab(tab: String) { _pendingTab.value = tab }
    fun consumeTab() { _pendingTab.value = null }

    // -- M6: desktop bridge (Prima + Laya) ----------------------------------

    /** REST client for the local bridge (docs/BRIDGE.md). Host/token in SecureStore. */
    val bridge = com.cornerstone.agency.data.BridgeRepository(store)

    /** One WS fanning out all Prima session events. Created on VM init. */
    val primaSocket = com.cornerstone.agency.data.PrimaSocket(bridge, viewModelScope)

    /** Last /health result; null = untested/unreachable. */
    private val _bridgeHealth = MutableStateFlow<com.cornerstone.agency.data.BridgeHealth?>(null)
    val bridgeHealth: StateFlow<com.cornerstone.agency.data.BridgeHealth?> = _bridgeHealth.asStateFlow()

    /** Probe /health; on success (re)connect the Prima event socket. */
    fun refreshBridge() {
        viewModelScope.launch {
            if (!bridge.configured) {
                _bridgeHealth.value = null
                return@launch
            }
            try {
                _bridgeHealth.value = bridge.health()
                primaSocket.connect()
            } catch (_: Exception) {
                _bridgeHealth.value = null
            }
        }
    }

    fun setBridgeHost(host: String) {
        bridge.host = host.trim()
        bridge.save()
        refreshBridge()
    }

    fun setBridgeToken(token: String) {
        bridge.token = token.trim()
        bridge.save()
        refreshBridge()
    }

    /** Deep-link requested by a notification/share intent; consumed by AppNav. */
    private val _pendingDeepLink = MutableStateFlow<ChatDeepLink?>(null)
    val pendingDeepLink: StateFlow<ChatDeepLink?> = _pendingDeepLink.asStateFlow()

    fun requestDeepLink(profile: String, session: String?) {
        _pendingDeepLink.value = ChatDeepLink(profile, session)
    }

    private var voiceRequestedAt = 0L
    fun requestVoice() { voiceRequestedAt = System.currentTimeMillis() }

    /** True once per widget tap, and only if the tap is fresh (never leaks into a later chat). */
    fun consumeVoiceRequest(): Boolean {
        val fresh = voiceRequestedAt != 0L && System.currentTimeMillis() - voiceRequestedAt < 20_000
        voiceRequestedAt = 0L
        return fresh
    }

    fun consumeDeepLink() {
        _pendingDeepLink.value = null
    }

    /** ACTION_SEND payload waiting for the share-target picker. */
    data class ShareContent(val text: String, val imageNames: List<String>)

    private val _pendingShare = MutableStateFlow<ShareContent?>(null)
    val pendingShare: StateFlow<ShareContent?> = _pendingShare.asStateFlow()

    fun requestShare(text: String, imageNames: List<String>) {
        _pendingShare.value = ShareContent(text, imageNames)
    }

    fun consumeShare() {
        _pendingShare.value = null
    }

    /** Composer prefill handed from ShareTargetScreen to ChatScreen. */
    private val _shareDraft = MutableStateFlow<String?>(null)
    val shareDraft: StateFlow<String?> = _shareDraft.asStateFlow()

    fun setShareDraft(text: String) {
        _shareDraft.value = text
    }

    fun consumeShareDraft(): String? {
        val d = _shareDraft.value
        _shareDraft.value = null
        return d
    }

    /** Dispatch composer handoff: text to auto-send once the target chat opens. */
    private val _dispatchText = MutableStateFlow<String?>(null)
    val dispatchText: StateFlow<String?> = _dispatchText.asStateFlow()

    fun setDispatchText(text: String) {
        _dispatchText.value = text
    }

    fun consumeDispatch(): String? {
        val d = _dispatchText.value
        _dispatchText.value = null
        return d
    }

    val lastShareProfile: StateFlow<String> =
        prefs.lastShareProfile.stateIn(
            viewModelScope,
            kotlinx.coroutines.flow.SharingStarted.Eagerly,
            "",
        )

    fun setLastShareProfile(profile: String) {
        viewModelScope.launch { prefs.setLastShareProfile(profile) }
    }

    val voiceBridge: StateFlow<String> =
        prefs.voiceBridge.stateIn(
            viewModelScope,
            kotlinx.coroutines.flow.SharingStarted.Eagerly,
            "127.0.0.1:8766",
        )

    fun setVoiceBridge(addr: String) {
        viewModelScope.launch { prefs.setVoiceBridge(addr) }
    }

    val voiceToken: StateFlow<String> =
        prefs.voiceToken.stateIn(
            viewModelScope,
            kotlinx.coroutines.flow.SharingStarted.Eagerly,
            "",
        )

    fun setVoiceToken(token: String) {
        viewModelScope.launch { prefs.setVoiceToken(token) }
    }

    private val _ui = MutableStateFlow(UiState())
    val ui: StateFlow<UiState> = _ui.asStateFlow()

    private var healthJob: Job? = null
    private var graceJob: Job? = null

    @Volatile
    private var foregrounded = true

    init {
        // Re-arm the daily issue (idempotent) once the saved schedule is actually loaded.
        viewModelScope.launch {
            companion.awaitLoaded()
            com.cornerstone.agency.service.FeedScheduler.sync(app)
        }
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                foregrounded = true
                GatewayHub.foregrounded = true
                syncBackgroundWork()
                // Coming back to the app: if the stored PC is gone, find it on another path.
                if (!_ui.value.booting) autoConnectAsync(AutoConnect.Reason.FOREGROUND)
            }

            override fun onStop(owner: LifecycleOwner) {
                foregrounded = false
                GatewayHub.foregrounded = false
                syncBackgroundWork()
            }
        })
        _ui.update { it.copy(knownPcName = (store.knownPc ?: bakedPc)?.name.orEmpty()) }
        viewModelScope.launch {
            if (repo.restore()) {
                try {
                    val me = repo.verify()
                    enterShell(me)
                    return@launch
                } catch (_: Exception) {
                    // Stored session is dead — fall through to auto-connect / the connect screen.
                }
            }
            if (!autoConnect(AutoConnect.Reason.COLD_START)) _ui.update { it.copy(booting = false) }
        }
        watchSocket()
    }

    // -- auto-connect ----------------------------------------------------------

    /** True when there is a PC to auto-connect to at all. */
    val hasKnownPc: Boolean get() = store.knownPc != null || bakedPc != null

    /** Me screen "Reconnect now". */
    fun reconnectNow() { autoConnectAsync(AutoConnect.Reason.USER) }

    /** Fire-and-forget variant; the result lands in [UiState]. */
    private fun autoConnectAsync(reason: AutoConnect.Reason) {
        viewModelScope.launch { autoConnect(reason) }
    }

    /**
     * Find the PC on whichever path answers (USB loopback, tailnet) and pair with it through the
     * same code the QR link uses. Returns true when the app ended up connected. Never asks the
     * user for anything; when nothing answers it leaves a one-line status and the existing
     * connection (if any) untouched.
     */
    private suspend fun autoConnect(reason: AutoConnect.Reason): Boolean {
        val candidates = AutoConnect.candidates(store.knownPc, bakedPc)
        if (candidates.isEmpty()) return false
        if (!autoConnectLock.tryLock()) return _ui.value.connected
        try {
            // Foreground/socket triggers can fire in bursts; USER always runs.
            val now = System.currentTimeMillis()
            if (reason != AutoConnect.Reason.USER && reason != AutoConnect.Reason.COLD_START &&
                now - lastAutoConnectAt < AUTO_CONNECT_MIN_INTERVAL_MS
            ) return _ui.value.connected
            lastAutoConnectAt = now
            // Already attached and healthy on a live path: nothing to do.
            if (_ui.value.connected && reason != AutoConnect.Reason.USER && reason != AutoConnect.Reason.SOCKET_DOWN) {
                val alive = runCatching { repo.verify(); true }.getOrDefault(false)
                if (alive) {
                    refreshAddressBook() // the PC may have gained a tailnet address since last time
                    return true
                }
            }
            val pcName = candidates.first().second.name.ifBlank { "your PC" }
            _ui.update { it.copy(autoStatus = "Looking for $pcName…", connectError = "") }
            Log.i(TAG, "auto-connect ($reason): probing ${candidates.map { it.first }}")
            val hit = AutoConnect.pickReachable(candidates) { (host, pc) -> bridge.probe(host, pc.token) }
            if (hit == null) {
                Log.i(TAG, "auto-connect ($reason): no path to $pcName")
                _ui.update { it.copy(autoStatus = "$pcName is not reachable right now") }
                return _ui.value.connected
            }
            val (host, pc) = hit
            val current = _ui.value.baseUrl.removePrefix("http://").substringBefore('/')
            if (_ui.value.connected && current == host && repo.viaRelay) {
                _ui.update { it.copy(autoStatus = "") }
                return true
            }
            _ui.update { it.copy(autoStatus = "Connecting to $pcName over ${AutoConnect.pathLabel(host)}…") }
            val ok = pairSilently(pc.linkFor(host))
            _ui.update { it.copy(autoStatus = if (ok) "" else "$pcName answered but refused the pairing") }
            if (ok) {
                Log.i(TAG, "auto-connect ($reason): paired with $pcName via ${AutoConnect.pathLabel(host)} ($host)")
                if (!stayConnected.value) setStayConnected(true)
            }
            return ok
        } finally {
            autoConnectLock.unlock()
        }
    }

    /** [pair] without the UI error strings; returns success. */
    private suspend fun pairSilently(link: com.cornerstone.agency.data.PairingLink): Boolean = try {
        val me = repo.pairRelay(link.bridge, link.token)
        bridge.host = link.bridge
        bridge.token = link.token
        bridge.save()
        if (link.voice.isNotEmpty()) prefs.setVoiceBridge(link.voice)
        if (link.voiceToken.isNotEmpty()) prefs.setVoiceToken(link.voiceToken)
        refreshBridge()
        if (_ui.value.connected) gateway.disconnect()
        enterShell(me)
        true
    } catch (e: Exception) {
        Log.w(TAG, "auto-connect pairing failed at ${link.bridge}: ${e.message}")
        false
    }

    /** After any relay connection: pull the PC's current address book so the next path is known. */
    private fun refreshAddressBook() {
        if (!repo.viaRelay || !bridge.configured) return
        viewModelScope.launch {
            runCatching { KnownPc.fromPairingResponse(bridge.pairing()) }.getOrNull()?.let { pc ->
                store.knownPc = pc
                _ui.update { it.copy(knownPcName = pc.name) }
                Log.i(TAG, "address book: ${pc.name} ${pc.hosts}")
            }
        }
    }

    /** The socket stuck in RECONNECTING for a while means the current path died: try the others. */
    private fun watchSocket() {
        viewModelScope.launch {
            var downSince = 0L
            while (true) {
                delay(5_000)
                if (!_ui.value.connected || !hasKnownPc) { downSince = 0; continue }
                val state = gateway.state.value
                if (state == GatewayState.OPEN || state == GatewayState.CLOSED) { downSince = 0; continue }
                if (downSince == 0L) downSince = System.currentTimeMillis()
                if (System.currentTimeMillis() - downSince >= SOCKET_DOWN_GRACE_MS) {
                    downSince = 0
                    autoConnectAsync(AutoConnect.Reason.SOCKET_DOWN)
                }
            }
        }
    }

    /** Probe /api/health; if the dashboard wants auth, reveal the credential fields. */
    fun connect(url: String) {
        viewModelScope.launch {
            _ui.update { it.copy(connectBusy = true, connectError = "") }
            try {
                val health = repo.probe(url)
                if (health.authRequired) {
                    _ui.update {
                        it.copy(connectBusy = false, needsCredentials = true, baseUrl = repo.baseUrl)
                    }
                } else {
                    store.save(SecureStore.Connection(repo.baseUrl, "", "", ""))
                    _ui.update {
                        it.copy(connectBusy = false, baseUrl = repo.baseUrl)
                    }
                    enterShell(SessionInfo())
                }
            } catch (e: ApiException) {
                _ui.update { it.copy(connectBusy = false, connectError = e.message ?: "error") }
            } catch (e: IOException) {
                _ui.update {
                    it.copy(connectBusy = false, connectError = "Dashboard unreachable — check the URL")
                }
            } catch (e: IllegalArgumentException) {
                _ui.update { it.copy(connectBusy = false, connectError = e.message ?: "Invalid address") }
            }
        }
    }

    fun login(user: String, pass: String) {
        viewModelScope.launch {
            _ui.update { it.copy(connectBusy = true, connectError = "") }
            try {
                val me = repo.login(user, pass)
                enterShell(me)
            } catch (e: ApiException) {
                val msg = if (e.status == 401 || e.status == 403) "Bad username or password"
                    else e.message ?: "Login failed"
                _ui.update { it.copy(connectBusy = false, connectError = msg) }
            } catch (e: IOException) {
                _ui.update {
                    it.copy(connectBusy = false, connectError = "Dashboard unreachable — check the URL")
                }
            }
        }
    }

    /**
     * Pair from a QR / deep link: Hermes through the bridge relay (live turns shared with the
     * desktop), the bridge itself (Prima, services), and the voice bridge — one scan, no password.
     */
    fun pair(link: com.cornerstone.agency.data.PairingLink) {
        viewModelScope.launch {
            _ui.update { it.copy(connectBusy = true, connectError = "") }
            try {
                val me = repo.pairRelay(link.bridge, link.token)
                bridge.host = link.bridge
                bridge.token = link.token
                bridge.save()
                if (link.voice.isNotEmpty()) prefs.setVoiceBridge(link.voice)
                if (link.voiceToken.isNotEmpty()) prefs.setVoiceToken(link.voiceToken)
                refreshBridge()
                if (_ui.value.connected) {
                    // Re-pairing while signed in: reconnect the socket to the new backend.
                    gateway.disconnect()
                }
                enterShell(me)
            } catch (e: ApiException) {
                _ui.update {
                    it.copy(
                        connectBusy = false,
                        connectError = if (e.status == 401) "Pairing code was rejected — make a new one on the PC"
                            else "Pairing failed: ${e.message}",
                    )
                }
            } catch (e: Exception) {
                _ui.update { it.copy(connectBusy = false, connectError = "Can't reach ${link.bridge} — is the PC on Tailscale?") }
            }
        }
    }

    /** DEBUG-only seed: attach a pre-minted session cookie (no password round-trip). */
    fun seedConnection(url: String, user: String, cookie: String) {
        viewModelScope.launch {
            try {
                repo.seedConnection(url, user, cookie)
                val me = repo.me()
                enterShell(me)
            } catch (_: Exception) {
                _ui.update { it.copy(booting = false) }
            }
        }
    }

    fun refreshProfiles() {
        viewModelScope.launch {
            _ui.update { it.copy(profilesLoading = true, profilesError = "") }
            try {
                val list = repo.profiles().sortedByDescending { it.isDefault || it.name == "default" }
                _ui.update { it.copy(profiles = list, profilesLoading = false) }
            } catch (e: ApiException) {
                _ui.update {
                    it.copy(
                        profilesLoading = false,
                        profilesError = if (e.status == 401) "Session expired" else (e.message ?: "error"),
                    )
                }
            } catch (e: IOException) {
                _ui.update { it.copy(profilesLoading = false, profilesError = "Dashboard unreachable") }
            }
        }
    }

    fun refreshStatus() {
        viewModelScope.launch {
            try {
                _ui.update { it.copy(status = repo.status()) }
            } catch (_: Exception) {
            }
        }
    }

    fun disconnect() {
        graceJob?.cancel()
        // Signed out: the on-device transcripts belong to that sign-in.
        com.cornerstone.agency.data.ChatCache.clearAll()
        com.cornerstone.agency.service.CatchUpWorker.cancel(app)
        app.stopService(ConnectionService.intent(app))
        NotificationCenter.cancelAll(app)
        NotificationCenter.unbind()
        gateway.disconnect()
        GatewayHub.detach()
        repo.disconnect()
        healthJob?.cancel()
        _ui.value = UiState(booting = false)
    }

    private fun enterShell(me: SessionInfo) {
        _ui.update {
            it.copy(
                booting = false,
                connected = true,
                connectBusy = false,
                connectError = "",
                needsCredentials = false,
                baseUrl = repo.baseUrl,
                sessionUser = me.displayName.ifBlank { me.userId },
            )
        }
        NotificationCenter.bind(app, gateway)
        viewModelScope.launch { NotificationCenter.warmCronFilter() }
        refreshProfiles()
        refreshAddressBook()
        refreshStatus()
        refreshBridge()
        syncBackgroundWork()
        com.cornerstone.agency.service.CatchUpWorker.schedule(app)
    }

    /**
     * Foregrounded: socket + health poll live, grace timer cancelled.
     * Backgrounded with stay-connected: socket stays (service holds process).
     * Backgrounded without it: 3-minute grace before disconnect, so a quick
     * app-switch doesn't kill a pending approval.
     */
    private fun syncBackgroundWork() {
        val connected = _ui.value.connected
        if (foregrounded && connected) {
            graceJob?.cancel()
            graceJob = null
            startHealthPolling()
            gateway.connect()
        } else if (connected && !foregrounded && !stayConnected.value) {
            healthJob?.cancel()
            healthJob = null
            graceJob?.cancel()
            graceJob = viewModelScope.launch {
                delay(BACKGROUND_GRACE_MS)
                gateway.disconnect()
            }
        } else if (!connected) {
            graceJob?.cancel()
            healthJob?.cancel()
            healthJob = null
            gateway.disconnect()
        }
        // stayConnected && !foregrounded: socket keeps running, health poll rests.
        if (connected && !foregrounded) {
            healthJob?.cancel()
            healthJob = null
        }
        syncService()
    }

    /** The service tracks the toggle + connection state, started while
     * foreground (background FGS starts are blocked on Android 12+). */
    private fun syncService() {
        val want = _ui.value.connected && stayConnected.value
        if (want) {
            ContextCompat.startForegroundService(app, ConnectionService.intent(app))
        } else {
            app.stopService(ConnectionService.intent(app))
        }
    }

    private fun startHealthPolling() {
        healthJob?.cancel()
        healthJob = viewModelScope.launch {
            while (true) {
                delay(15_000)
                try {
                    repo.health(repo.baseUrl)
                    _ui.update { it.copy(reachable = true) }
                } catch (_: Exception) {
                    _ui.update { it.copy(reachable = false) }
                }
            }
        }
    }

    companion object {
        private const val TAG = "AutoConnect"
        private const val BACKGROUND_GRACE_MS = 3 * 60 * 1000L
        /** Foreground/socket-triggered sweeps are rate-limited to this. */
        private const val AUTO_CONNECT_MIN_INTERVAL_MS = 10_000L
        /** Socket stuck reconnecting this long -> look for the PC on another path. */
        private const val SOCKET_DOWN_GRACE_MS = 20_000L
    }
}
