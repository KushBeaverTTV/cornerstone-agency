package com.cornerstone.agency.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

/**
 * Process-level home for the dashboard socket + session routing table.
 *
 * The socket used to live in AppViewModel.viewModelScope, which dies with the
 * Activity — killing pending approvals as soon as the app was swiped away.
 * GatewayHub owns the socket on a standalone scope so it survives Activity
 * teardown; ConnectionService keeps the process itself alive.
 */
object GatewayHub {

    private var hubScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    var socket: GatewaySocket? = null
        private set
    var repo: ConnectionRepository? = null
        private set

    /** Runtime session_id -> (stored session id, profile). Filled by ChatRepository
     * on resume/create; NotificationCenter uses it for deep links and badges. */
    val sessions = java.util.concurrent.ConcurrentHashMap<String, SessionRef>()

    data class SessionRef(val storedId: String, val profile: String, var title: String = "")

    /** Runtime session ids currently open in a visible ChatScreen — no notifications. */
    val openChats = java.util.concurrent.ConcurrentHashMap.newKeySet<String>()

    @Volatile
    var foregrounded = true

    /** Stored ids of the chats currently on screen. */
    fun openChatStoredIds(): Set<String> =
        openChats.mapNotNull { sessions[it]?.storedId?.takeIf { s -> s.isNotEmpty() } }.toSet()

    /** Reuse the live socket across Activity/ViewModel recreation, swapping in
     * the freshest repo so ticket mints use current credentials. */
    fun attach(r: ConnectionRepository): GatewaySocket {
        repo = r
        val existing = socket
        if (existing != null) {
            existing.repo = r
            return existing
        }
        val s = GatewaySocket(r, hubScope)
        socket = s
        return s
    }

    fun detach() {
        socket?.disconnect()
        socket = null
        repo = null
        sessions.clear()
    }

    fun registerSession(runtimeId: String?, storedId: String?, profile: String, title: String = "") {
        if (runtimeId.isNullOrEmpty()) return
        val ref = SessionRef(storedId ?: "", profile, title)
        sessions[runtimeId] = ref
        // A stored id can collide with a runtime id in event filters — register both.
        if (!storedId.isNullOrEmpty() && storedId != runtimeId) {
            sessions[storedId] = ref
        }
    }
}
