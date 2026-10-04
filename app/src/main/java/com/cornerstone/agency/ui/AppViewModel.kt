package com.cornerstone.agency.ui

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.viewModelScope
import com.cornerstone.agency.data.ApiException
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
)

/** A notification/intent asking the nav host to open a specific chat. */
data class ChatDeepLink(val profile: String, val session: String?)

class AppViewModel(private val app: Application) : AndroidViewModel(app) {

    private val store = SecureStore(app)
    internal val repo = ConnectionRepository(store)
    private val prefs = Prefs(app)

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
            }

            override fun onStop(owner: LifecycleOwner) {
                foregrounded = false
                GatewayHub.foregrounded = false
                syncBackgroundWork()
            }
        })
        viewModelScope.launch {
            if (repo.restore()) {
                try {
                    val me = repo.me()
                    enterShell(me)
                    return@launch
                } catch (_: Exception) {
                    // Stored session is dead — fall through to the connect screen.
                }
            }
            _ui.update { it.copy(booting = false) }
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
        refreshStatus()
        refreshBridge()
        syncBackgroundWork()
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
        private const val BACKGROUND_GRACE_MS = 3 * 60 * 1000L
    }
}
