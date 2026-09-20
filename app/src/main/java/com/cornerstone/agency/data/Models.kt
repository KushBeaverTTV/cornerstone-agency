package com.cornerstone.agency.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Health(
    val ok: Boolean = false,
    val version: String = "",
    @SerialName("auth_required") val authRequired: Boolean = false,
)

@Serializable
data class AuthProviderInfo(
    val name: String = "",
    @SerialName("display_name") val displayName: String = "",
    @SerialName("supports_password") val supportsPassword: Boolean = false,
)

@Serializable
data class ProvidersResponse(
    val providers: List<AuthProviderInfo> = emptyList(),
)

@Serializable
data class PasswordLoginBody(
    val provider: String,
    val username: String,
    val password: String,
)

@Serializable
data class SessionInfo(
    @SerialName("user_id") val userId: String = "",
    val email: String = "",
    @SerialName("display_name") val displayName: String = "",
    @SerialName("org_id") val orgId: String = "",
    val provider: String = "",
    @SerialName("expires_at") val expiresAt: Long = 0,
)

/** /api/profiles entry — shape verified against hermes serve 0.21.3. */
@Serializable
data class BotProfile(
    val name: String = "",
    val path: String = "",
    @SerialName("is_default") val isDefault: Boolean = false,
    val model: String = "",
    val provider: String = "",
    @SerialName("has_env") val hasEnv: Boolean = false,
    @SerialName("skill_count") val skillCount: Int = 0,
    @SerialName("gateway_running") val gatewayRunning: Boolean = false,
    val description: String = "",
    @SerialName("description_auto") val descriptionAuto: Boolean = false,
    @SerialName("display_name") val displayName: String = "",
    @SerialName("distribution_name") val distributionName: String? = null,
    @SerialName("distribution_version") val distributionVersion: String? = null,
    @SerialName("distribution_source") val distributionSource: String? = null,
    @SerialName("has_alias") val hasAlias: Boolean = false,
) {
    /** The default profile ships unnamed as "default"; on this fleet it is Kaori. */
    val title: String
        get() = displayName.ifBlank {
            if (isDefault || name == "default") "Kaori" else name.replaceFirstChar { it.uppercase() }
        }

    val subtitle: String
        get() = description.ifBlank { model }
}

@Serializable
data class ProfilesResponse(
    val profiles: List<BotProfile> = emptyList(),
)

@Serializable
data class PlatformStatus(
    val state: String = "",
    @SerialName("error_code") val errorCode: String? = null,
    @SerialName("error_message") val errorMessage: String? = null,
    @SerialName("updated_at") val updatedAt: String = "",
    @SerialName("needs_attention") val needsAttention: Boolean = false,
)

@Serializable
data class StatusPayload(
    val version: String = "",
    val overall: String = "",
    @SerialName("gateway_running") val gatewayRunning: Boolean = false,
    @SerialName("gateway_state") val gatewayState: String = "",
    @SerialName("gateway_platforms") val gatewayPlatforms: Map<String, PlatformStatus> = emptyMap(),
    @SerialName("active_sessions") val activeSessions: Int = 0,
    @SerialName("auth_required") val authRequired: Boolean = false,
)

/** session.list entry — id is the STORED session id (resume/delete target), not the runtime id. */
@Serializable
data class SessionSummary(
    val id: String = "",
    val title: String = "",
    val preview: String = "",
    @SerialName("started_at") val startedAt: Double = 0.0,
    @SerialName("message_count") val messageCount: Int = 0,
    val source: String = "",
    // Some server builds attach routing metadata; this one does not — stay tolerant.
    val profile: String? = null,
    @SerialName("profile_name") val profileName: String? = null,
) {
    val label: String
        get() = title.ifBlank { preview.lineSequence().firstOrNull()?.take(60) ?: "" }
            .ifBlank { "Session ${id.take(8)}" }
}

@Serializable
data class SessionListResult(
    val sessions: List<SessionSummary> = emptyList(),
)

enum class ChatKind { User, Assistant, Thinking, Tool, Status, Error, Request }

/** One rendered row in a chat transcript. Streaming items mutate until finalized. */
data class ChatItem(
    val id: String,
    val kind: ChatKind,
    val text: String,
    val streaming: Boolean = false,
    val done: Boolean = true,
    val meta: String = "",
)

/** Response bodies sometimes nest under a key, sometimes come bare — keep a tolerant decode path. */
@Serializable
data class ApiErrorBody(
    val detail: String? = null,
    val error: String? = null,
    val extras: Map<String, JsonElement> = emptyMap(),
)
