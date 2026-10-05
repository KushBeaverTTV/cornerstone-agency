package com.cornerstone.agency.ui.screens

import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cornerstone.agency.data.ChatItem
import com.cornerstone.agency.data.ChatKind
import com.cornerstone.agency.theme.AgencyTokens
import com.cornerstone.agency.ui.components.AgencyIcons
import java.util.Locale

/** Tapback set, same as the Hermes desktop's quick reactions. */
internal val QUICK_REACTIONS = listOf("👍", "❤️", "😂", "🔥", "👀", "🙏")

/** What the sheet may offer for one message. Computed by the screen from the chat's abilities. */
internal data class MessageActionSet(
    val react: Boolean,
    val edit: Boolean,
    val regenerate: Boolean,
    val branch: Boolean,
    val resend: Boolean,
)

internal fun actionsFor(item: ChatItem, addressable: Boolean, serverActions: Boolean, busy: Boolean) = MessageActionSet(
    react = serverActions && addressable && (item.kind == ChatKind.User || item.kind == ChatKind.Assistant),
    edit = serverActions && addressable && !busy && item.kind == ChatKind.User,
    regenerate = serverActions && addressable && !busy && item.kind == ChatKind.Assistant,
    branch = serverActions && addressable && !busy && item.kind != ChatKind.Request,
    // Without server-side rewind (Prima), "send again" re-asks as a new message.
    resend = !serverActions && !busy && item.kind == ChatKind.User && item.text.isNotBlank(),
)

/** Markdown → something a TTS voice can read. */
internal fun speakable(text: String): String = text
    .replace(Regex("```[\\s\\S]*?```"), " code block. ")
    .replace(Regex("`([^`]*)`"), "$1")
    .replace(Regex("!?\\[([^\\]]*)]\\([^)]*\\)"), "$1")
    .replace(Regex("(?m)^\\s{0,3}(#{1,6}|>|[-*+]|\\d+\\.)\\s+"), "")
    .replace(Regex("[*_~]{1,3}"), "")
    .replace(Regex("\\s+"), " ")
    .trim()

/** One shared TTS engine; lazily created on first "Read aloud". */
internal object Speaker {
    private var tts: TextToSpeech? = null
    private var ready = false
    private var queued: String? = null

    fun speak(ctx: Context, text: String) {
        val t = speakable(text).take(TextToSpeech.getMaxSpeechInputLength() - 1)
        if (t.isEmpty()) return
        val engine = tts
        if (engine != null && ready) {
            engine.speak(t, TextToSpeech.QUEUE_FLUSH, null, "agency-read")
            return
        }
        queued = t
        if (engine == null) {
            tts = TextToSpeech(ctx.applicationContext) { status ->
                ready = status == TextToSpeech.SUCCESS
                if (ready) {
                    tts?.language = Locale.getDefault()
                    queued?.let { tts?.speak(it, TextToSpeech.QUEUE_FLUSH, null, "agency-read") }
                }
                queued = null
            }
        }
    }

    fun stop() {
        tts?.stop()
    }
}

internal fun shareText(ctx: Context, text: String) {
    val send = Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, text)
    ctx.startActivity(Intent.createChooser(send, "Share message").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
}

/**
 * Long-press sheet for a message (Hermes desktop parity and then some): tapbacks, copy, select
 * text, share, read aloud, quote in reply, edit & resend, regenerate, branch into a new chat.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MessageActionSheet(
    item: ChatItem,
    actions: MessageActionSet,
    onDismiss: () -> Unit,
    onReact: (String) -> Unit,
    onSelectText: () -> Unit,
    onQuote: () -> Unit,
    onEdit: () -> Unit,
    onRegenerate: () -> Unit,
    onBranch: () -> Unit,
    onResend: () -> Unit,
) {
    val colors = AgencyTokens.colors
    val ctx = LocalContext.current
    val mine = item.reactions.firstOrNull { it.author == "user" }?.emoji
    ModalBottomSheet(onDismissRequest = onDismiss, containerColor = colors.elevated) {
        Column(Modifier.fillMaxWidth().navigationBarsPadding().padding(bottom = 12.dp)) {
            Text(
                item.text.ifBlank { item.attachments.joinToString { it.name } },
                style = MaterialTheme.typography.bodyMedium,
                color = colors.secondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp),
            )
            if (actions.react) {
                Row(
                    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    QUICK_REACTIONS.forEach { e ->
                        Text(
                            e,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(if (e == mine) colors.card else colors.elevated)
                                .border(1.dp, if (e == mine) colors.gold else colors.elevated, CircleShape)
                                .clickable(role = Role.Button, onClickLabel = if (e == mine) "Remove $e" else "React $e") {
                                    onReact(e)
                                }
                                .padding(top = 8.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        )
                    }
                }
            }
            if (item.text.isNotBlank()) {
                ActionRow(AgencyIcons.Copy, "Copy") {
                    copyToClipboard(ctx, item.text)
                    Toast.makeText(ctx, "Copied", Toast.LENGTH_SHORT).show()
                    onDismiss()
                }
                ActionRow(AgencyIcons.SelectText, "Select text") { onSelectText() }
                ActionRow(AgencyIcons.Share, "Share") { shareText(ctx, item.text); onDismiss() }
                if (item.kind == ChatKind.Assistant) {
                    ActionRow(AgencyIcons.Speak, "Read aloud") { Speaker.speak(ctx, item.text); onDismiss() }
                }
                ActionRow(AgencyIcons.Quote, "Quote in reply") { onQuote() }
            }
            if (actions.edit) ActionRow(AgencyIcons.Edit, "Edit & resend") { onEdit() }
            if (actions.resend) ActionRow(AgencyIcons.Retry, "Send again") { onResend() }
            if (actions.regenerate) ActionRow(AgencyIcons.Retry, "Regenerate") { onRegenerate() }
            if (actions.branch) ActionRow(AgencyIcons.Branch, "Branch into new chat") { onBranch() }
        }
    }
}

@Composable
private fun ActionRow(icon: ImageVector, label: String, onClick: () -> Unit) {
    val colors = AgencyTokens.colors
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(role = Role.Button, onClick = onClick)
            .heightIn(min = 52.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon, contentDescription = null, tint = colors.secondary, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(20.dp))
        Text(label, style = MaterialTheme.typography.bodyLarge, color = colors.foreground)
    }
}

/** Full text, selectable (partial copy). */
@Composable
internal fun SelectTextDialog(text: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select text") },
        text = {
            SelectionContainer(Modifier.heightIn(max = 420.dp).verticalScroll(rememberScrollState())) {
                Text(text, style = MaterialTheme.typography.bodyLarge)
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Done") } },
    )
}
