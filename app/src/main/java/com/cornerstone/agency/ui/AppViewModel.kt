package com.cornerstone.agency.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.viewModelScope
import com.cornerstone.agency.data.ApiException
import com.cornerstone.agency.data.BotProfile
import com.cornerstone.agency.data.ConnectionRepository
import com.cornerstone.agency.data.GatewaySocket
import com.cornerstone.agency.data.SecureStore
import com.cornerstone.agency.data.SessionInfo
import com.cornerstone.agency.data.StatusPayload
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

class AppViewModel(app: Application) : AndroidViewModel(app) {

    private val store = SecureStore(app)
    internal val repo = ConnectionRepository(store)
    private val prefs = com.cornerstone.agency.data.Prefs(app)

    /** Chats tab: hide cron-job session chatter (DataStore-persisted). */
    val hideCronChatter: StateFlow<Boolean> =
        prefs.hideCronChatter.stateIn(
            viewModelScope,
            kotlinx.coroutines.flow.SharingStarted.Eagerly,
            false,
        )

    fun setHideCronChatter(hide: Boolean) {
        viewModelScope.launch { prefs.setHideCronChatter(hide) }
    }

    /** Shared dashboard socket — chats subscribe to its events/state. */
    val gateway = GatewaySocket(repo, viewModelScope)

    private val _ui = MutableStateFlow(UiState())
    val ui: StateFlow<UiState> = _ui.asStateFlow()

    private var healthJob: Job? = null

    @Volatile
    private var foregrounded = true

    init {
        // Health polling and the gateway socket only run while the app is foregrounded.
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                foregrounded = true
                syncBackgroundWork()
            }

            override fun onStop(owner: LifecycleOwner) {
                foregrounded = false
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
        gateway.disconnect()
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
        refreshProfiles()
        refreshStatus()
        syncBackgroundWork()
    }

    /** Polling + socket exist only while connected AND foregrounded. */
    private fun syncBackgroundWork() {
        if (foregrounded && _ui.value.connected) {
            startHealthPolling()
            gateway.connect()
        } else {
            healthJob?.cancel()
            healthJob = null
            gateway.disconnect()
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
}
