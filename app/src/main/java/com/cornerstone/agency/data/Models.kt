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
    /** Last activity (REST /api/sessions rows carry it; session.list rows do not). */
    @SerialName("last_active") val lastActive: Double = 0.0,
) {
    /** Best known "last touched" time. */
    val activeAt: Double get() = if (lastActive > 0) lastActive else startedAt

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
    val request: PendingRequest? = null,
    /** Durable `messages.id` once the row is persisted — edit/regenerate/react/branch need it. */
    val rowId: Long? = null,
    /** Images / files that ride with this message (local picks or `@image:` refs on the PC). */
    val attachments: List<ChatAttachment> = emptyList(),
    /** Emoji reactions on this message (Tapback style, one per author). */
    val reactions: List<Reaction> = emptyList(),
    /** Unix seconds; 0 when unknown (live rows before they persist). */
    val timestamp: Double = 0.0,
)

@kotlinx.serialization.Serializable
data class ChatAttachment(
    val name: String,
    /** "image" or "file". */
    val kind: String = "image",
    val mime: String = "",
    /** Phone-side content:// or file:// URI for a local pick; empty for PC-side refs. */
    val localUri: String = "",
    /** Path on the PC (from an `@image:` ref) — fetched through /api/media for display. */
    val remotePath: String = "",
    val sizeBytes: Long = 0,
)

@kotlinx.serialization.Serializable
data class Reaction(val emoji: String, val author: String = "user")

// ---- M3: bot settings / model options -------------------------------------

/** GET /api/profiles/{name}/soul — verified: {"content": "...", "exists": true} */
@Serializable
data class SoulResponse(
    val content: String = "",
    val exists: Boolean = false,
)

/** Generic {"ok": true} mutation acknowledgement. */
@Serializable
data class OkResponse(
    val ok: Boolean = false,
    val name: String = "",
    val path: String = "",
    @SerialName("model_set") val modelSet: Boolean = false,
    @SerialName("model_error") val modelError: String = "",
)

/** One provider bucket inside GET /api/model/options. */
@Serializable
data class ModelProvider(
    val slug: String = "",
    val name: String = "",
    @SerialName("is_current") val isCurrent: Boolean = false,
    @SerialName("is_user_defined") val isUserDefined: Boolean = false,
    @SerialName("total_models") val totalModels: Int = 0,
    val models: List<String> = emptyList(),
)

@Serializable
data class ModelOptionsResponse(
    val providers: List<ModelProvider> = emptyList(),
)

// ---- M4: cron jobs / skills / system --------------------------------------

/** GET /api/cron/jobs entry — shape verified against hermes serve 0.21.3. */
@Serializable
data class CronSchedule(
    val kind: String = "",
    val minutes: Int = 0,
    val expr: String = "",
    val display: String = "",
)

@Serializable
data class CronRepeat(
    val times: Int? = null,
    val completed: Int = 0,
)

@Serializable
data class CronJob(
    val id: String = "",
    val name: String = "",
    val prompt: String = "",
    val script: String? = null,
    val skill: String? = null,
    val model: String? = null,
    val provider: String? = null,
    @SerialName("no_agent") val noAgent: Boolean = false,
    val schedule: CronSchedule = CronSchedule(),
    @SerialName("schedule_display") val scheduleDisplay: String = "",
    val repeat: CronRepeat = CronRepeat(),
    val enabled: Boolean = true,
    val state: String = "",
    @SerialName("paused_at") val pausedAt: String? = null,
    @SerialName("paused_reason") val pausedReason: String? = null,
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("last_run_at") val lastRunAt: String? = null,
    @SerialName("next_run_at") val nextRunAt: String? = null,
    @SerialName("last_status") val lastStatus: String = "",
    @SerialName("last_error") val lastError: String? = null,
    @SerialName("failure_streak") val failureStreak: Int = 0,
    val deliver: String = "",
    val workdir: String? = null,
) {
    /** "[bot:kage] watch" → profile "kage"; everything else runs under default. */
    val botProfile: String
        get() = BOT_PREFIX.find(name)?.groupValues?.get(1) ?: "default"

    /** Job name with the [bot:x] routing prefix stripped. */
    val displayName: String
        get() = name.replace(BOT_PREFIX, "").trim().ifBlank { name }

    val paused: Boolean
        get() = pausedAt != null || state == "paused" || !enabled

    /** Server-rendered schedule ("every 5m") with a cron-expr fallback. */
    val scheduleText: String
        get() = scheduleDisplay.ifBlank { schedule.display }.ifBlank {
            cronToText(schedule.expr, schedule.minutes)
        }

    companion object {
        private val BOT_PREFIX = Regex("^\\[bot:([a-z0-9-]+)\\]")
    }
}

@Serializable
data class CronRunsResponse(
    val runs: List<SessionSummary> = emptyList(),
    val limit: Int = 0,
)

/** GET /api/skills entry (bare array). */
@Serializable
data class Skill(
    val name: String = "",
    val description: String? = null,
    val category: String? = null,
    val enabled: Boolean = true,
    val usage: Int = 0,
    val provenance: String = "",
)

/** GET /api/skills/hub/search result entry. */
@Serializable
data class HubSkill(
    val name: String = "",
    val description: String? = null,
    val source: String = "",
    val identifier: String = "",
    @SerialName("trust_level") val trustLevel: String = "",
)

@Serializable
data class HubSearchResponse(
    val results: List<HubSkill> = emptyList(),
)

/** GET /api/system/stats — nested blocks kept loose on purpose. */
@Serializable
data class ByteBlock(
    val total: Long = 0,
    val used: Long = 0,
    val free: Long = 0,
    val available: Long = 0,
    val percent: Double = 0.0,
)

@Serializable
data class ProcessStats(
    val pid: Int = 0,
    val rss: Long = 0,
    @SerialName("num_threads") val numThreads: Int = 0,
)

@Serializable
data class SystemStats(
    val os: String = "",
    val platform: String = "",
    val arch: String = "",
    val hostname: String = "",
    @SerialName("python_version") val pythonVersion: String = "",
    @SerialName("hermes_version") val hermesVersion: String = "",
    @SerialName("cpu_count") val cpuCount: Int = 0,
    @SerialName("cpu_percent") val cpuPercent: Double = 0.0,
    val memory: ByteBlock = ByteBlock(),
    val disk: ByteBlock = ByteBlock(),
    @SerialName("load_avg") val loadAvg: List<Double> = emptyList(),
    @SerialName("uptime_seconds") val uptimeSeconds: Double = 0.0,
    val process: ProcessStats = ProcessStats(),
)

/** GET /api/analytics/usage — only the pieces we render. */
@Serializable
data class DailyUsage(
    val day: String = "",
    @SerialName("input_tokens") val inputTokens: Long = 0,
    @SerialName("output_tokens") val outputTokens: Long = 0,
    @SerialName("cache_read_tokens") val cacheReadTokens: Long = 0,
    val sessions: Int = 0,
    @SerialName("api_calls") val apiCalls: Int = 0,
    @SerialName("estimated_cost") val estimatedCost: Double = 0.0,
)

@Serializable
data class AnalyticsUsage(
    val daily: List<DailyUsage> = emptyList(),
)

/** WS plugins.list / tools.list results. */
@Serializable
data class PluginInfo(
    val name: String = "",
    val version: String = "",
    val enabled: Boolean = false,
)

@Serializable
data class PluginsResult(
    val plugins: List<PluginInfo> = emptyList(),
)

@Serializable
data class ToolsetInfo(
    val name: String = "",
    val description: String = "",
    @SerialName("tool_count") val toolCount: Int = 0,
    val enabled: Boolean = false,
)

@Serializable
data class ToolsetsResult(
    val toolsets: List<ToolsetInfo> = emptyList(),
)

/** Fallback cron→text when the server doesn't ship schedule_display. */
fun cronToText(expr: String, intervalMinutes: Int): String {
    if (expr.isNotBlank()) {
        val f = expr.split(Regex("\\s+"))
        if (f.size == 5) {
            val (min, hour, dom, mon, dow) = f
            if (min.startsWith("*/") && hour == "*" && dom == "*" && mon == "*" && dow == "*") {
                return "Every ${min.removePrefix("*/")} min"
            }
            if (hour != "*" && dom == "*" && mon == "*" && dow == "*") {
                val h = hour.toIntOrNull()
                val m = min.toIntOrNull() ?: 0
                if (h != null) {
                    val ampm = if (h < 12) "am" else "pm"
                    val hh = if (h % 12 == 0) 12 else h % 12
                    return "Every day at $hh:${"%02d".format(m)}$ampm"
                }
            }
            if (min == "*" && hour == "*" && dom == "*" && mon == "*" && dow == "*") {
                return "Every minute"
            }
        }
        return expr
    }
    if (intervalMinutes > 0) {
        return if (intervalMinutes % 60 == 0) {
            "Every ${intervalMinutes / 60}h"
        } else {
            "Every ${intervalMinutes}m"
        }
    }
    return "Unscheduled"
}

/** Response bodies sometimes nest under a key, sometimes come bare — keep a tolerant decode path. */
@Serializable
data class ApiErrorBody(
    val detail: String? = null,
    val error: String? = null,
    val extras: Map<String, JsonElement> = emptyMap(),
)
