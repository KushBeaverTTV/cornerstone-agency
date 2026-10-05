package com.cornerstone.agency.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.util.LruCache
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cornerstone.agency.data.ChatAttachment
import com.cornerstone.agency.data.GatewayHub
import com.cornerstone.agency.data.OutgoingAttachment
import com.cornerstone.agency.theme.AgencyTokens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

/** Picks larger than this are refused (the gateway caps uploads at 25 MB). */
internal const val MAX_ATTACHMENT_BYTES = 20L * 1024 * 1024

/** Longest image edge sent to the model; phone photos are downscaled to this. */
private const val MAX_IMAGE_EDGE = 2048

/** An attachment staged in the composer: preview data + the bytes to upload. */
internal class PendingAttachment(val upload: OutgoingAttachment, val preview: ImageBitmap?) {
    val chip: ChatAttachment
        get() = ChatAttachment(
            upload.name, if (upload.isImage) "image" else "file", upload.mime, upload.localUri,
            sizeBytes = upload.bytes.size.toLong(),
        )
}

private fun displayName(ctx: Context, uri: Uri): String {
    ctx.contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)?.use { c ->
        val col = c.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (col >= 0 && c.moveToFirst()) c.getString(col)?.let { return it }
    }
    return uri.lastPathSegment?.substringAfterLast('/') ?: "attachment"
}

private fun decodeUpright(ctx: Context, uri: Uri, maxEdge: Int): Bitmap? = try {
    if (Build.VERSION.SDK_INT >= 28) {
        // ImageDecoder applies EXIF rotation and samples down while decoding.
        ImageDecoder.decodeBitmap(ImageDecoder.createSource(ctx.contentResolver, uri)) { dec, info, _ ->
            val w = info.size.width
            val h = info.size.height
            val scale = maxEdge.toFloat() / maxOf(w, h)
            if (scale < 1f) dec.setTargetSize((w * scale).toInt().coerceAtLeast(1), (h * scale).toInt().coerceAtLeast(1))
            dec.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
        }
    } else {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        ctx.contentResolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it, null, bounds) }
        var sample = 1
        while (maxOf(bounds.outWidth, bounds.outHeight) / (sample * 2) >= maxEdge) sample *= 2
        ctx.contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, BitmapFactory.Options().apply { inSampleSize = sample })
        }
    }
} catch (_: Exception) {
    null
}

/**
 * Read a picked URI into an upload. Images are re-encoded upright as JPEG with the long edge
 * capped at [MAX_IMAGE_EDGE] (a 12 MP photo becomes ~400 KB); GIF/PNG under 4 MB pass through so
 * transparency and animation survive. Returns null + a reason when the file can't be sent.
 */
internal suspend fun readAttachment(ctx: Context, uri: Uri): Pair<PendingAttachment?, String?> =
    withContext(Dispatchers.IO) {
        val name = displayName(ctx, uri)
        val mime = ctx.contentResolver.getType(uri) ?: android.webkit.MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(name.substringAfterLast('.', "").lowercase()) ?: "application/octet-stream"
        val raw = try {
            ctx.contentResolver.openInputStream(uri)?.use { input ->
                val out = ByteArrayOutputStream()
                val buf = ByteArray(64 * 1024)
                var total = 0L
                while (true) {
                    val n = input.read(buf)
                    if (n < 0) break
                    total += n
                    if (total > MAX_ATTACHMENT_BYTES) return@withContext null to "$name is over 20 MB"
                    out.write(buf, 0, n)
                }
                out.toByteArray()
            }
        } catch (e: Exception) {
            null
        } ?: return@withContext null to "Couldn't read $name"
        if (!mime.startsWith("image/")) {
            return@withContext PendingAttachment(OutgoingAttachment(name, mime, raw, uri.toString()), null) to null
        }
        val bmp = decodeUpright(ctx, uri, MAX_IMAGE_EDGE)
            ?: return@withContext null to "Couldn't open the image $name"
        val keepOriginal = (mime == "image/gif" || mime == "image/png") && raw.size < 4 * 1024 * 1024
        val bytes = if (keepOriginal) raw else ByteArrayOutputStream().also {
            bmp.compress(Bitmap.CompressFormat.JPEG, 85, it)
        }.toByteArray()
        val outName = if (keepOriginal) name else name.substringBeforeLast('.') + ".jpg"
        val outMime = if (keepOriginal) mime else "image/jpeg"
        val thumb = Bitmap.createScaledBitmap(
            bmp, (bmp.width * 240 / maxOf(bmp.width, bmp.height)).coerceAtLeast(1),
            (bmp.height * 240 / maxOf(bmp.width, bmp.height)).coerceAtLeast(1), true,
        )
        PendingAttachment(OutgoingAttachment(outName, outMime, bytes, uri.toString()), thumb.asImageBitmap()) to null
    }

/** Thumbnails for messages: phone-local URIs or PC-side paths fetched through /api/media. */
internal object MediaLoader {
    private val cache = LruCache<String, ImageBitmap>(48)

    suspend fun load(ctx: Context, att: ChatAttachment, edge: Int = 720): ImageBitmap? {
        val key = (att.localUri.ifEmpty { att.remotePath }) + "@" + edge
        cache.get(key)?.let { return it }
        val bmp = withContext(Dispatchers.IO) {
            when {
                att.localUri.isNotEmpty() -> decodeUpright(ctx, Uri.parse(att.localUri), edge)
                att.remotePath.isNotEmpty() -> runCatching {
                    val bytes = GatewayHub.repo?.media(att.remotePath) ?: return@runCatching null
                    val opts = BitmapFactory.Options().apply { inJustDecodeBounds = true }
                    BitmapFactory.decodeByteArray(bytes, 0, bytes.size, opts)
                    var sample = 1
                    while (maxOf(opts.outWidth, opts.outHeight) / (sample * 2) >= edge) sample *= 2
                    BitmapFactory.decodeByteArray(bytes, 0, bytes.size, BitmapFactory.Options().apply { inSampleSize = sample })
                }.getOrNull()
                else -> null
            }
        } ?: return null
        return bmp.asImageBitmap().also { cache.put(key, it) }
    }
}

/** Staged attachments above the composer field; each has a remove button. */
@Composable
internal fun PendingAttachmentStrip(items: List<PendingAttachment>, onRemove: (PendingAttachment) -> Unit) {
    val colors = AgencyTokens.colors
    Row(
        Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items.forEach { a ->
            Box {
                if (a.preview != null) {
                    Image(
                        a.preview, contentDescription = a.upload.name, contentScale = ContentScale.Crop,
                        modifier = Modifier.size(64.dp).clip(RoundedCornerShape(12.dp)),
                    )
                } else {
                    FileChip(a.upload.name, Modifier.heightIn(min = 64.dp).widthIn(max = 180.dp))
                }
                Box(
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(2.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(colors.chrome)
                        .clickable(role = Role.Button, onClickLabel = "Remove ${a.upload.name}") { onRemove(a) },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Filled.Close, "Remove ${a.upload.name}", tint = colors.foreground, modifier = Modifier.size(14.dp))
                }
            }
        }
    }
}

@Composable
internal fun FileChip(name: String, modifier: Modifier = Modifier) {
    val colors = AgencyTokens.colors
    Row(
        modifier
            .clip(RoundedCornerShape(12.dp))
            .background(colors.card)
            .border(1.dp, colors.stroke, RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("📄", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.width(8.dp))
        Text(name, style = MaterialTheme.typography.labelLarge, color = colors.foreground, maxLines = 2,
            overflow = TextOverflow.Ellipsis)
    }
}

/** Images (tap to view full screen) and file chips that ride with a message. */
@Composable
internal fun MessageAttachments(atts: List<ChatAttachment>, alignEnd: Boolean) {
    if (atts.isEmpty()) return
    var viewing by remember { mutableStateOf<ChatAttachment?>(null) }
    Column(
        Modifier.fillMaxWidth().padding(bottom = 6.dp),
        horizontalAlignment = if (alignEnd) Alignment.End else Alignment.Start,
    ) {
        Row(
            Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            atts.filter { it.kind == "image" }.forEach { a ->
                AttachmentThumb(a, Modifier.size(132.dp)) { viewing = a }
            }
        }
        atts.filter { it.kind != "image" }.forEach { a ->
            Spacer(Modifier.size(4.dp))
            FileChip(a.name, Modifier.widthIn(max = 260.dp))
        }
    }
    viewing?.let { a ->
        Dialog(onDismissRequest = { viewing = null }, properties = DialogProperties(usePlatformDefaultWidth = false)) {
            Box(
                Modifier.fillMaxSize().background(AgencyTokens.colors.chrome).clickable { viewing = null },
                contentAlignment = Alignment.Center,
            ) {
                AttachmentThumb(a, Modifier.fillMaxWidth(), edge = 2048, crop = false) { viewing = null }
            }
        }
    }
}

@Composable
private fun AttachmentThumb(
    a: ChatAttachment,
    modifier: Modifier,
    edge: Int = 480,
    crop: Boolean = true,
    onClick: () -> Unit,
) {
    val ctx = LocalContext.current
    var img by remember(a, edge) { mutableStateOf<ImageBitmap?>(null) }
    var failed by remember(a, edge) { mutableStateOf(false) }
    LaunchedEffect(a, edge) {
        img = MediaLoader.load(ctx, a, edge)
        failed = img == null
    }
    val colors = AgencyTokens.colors
    Box(
        modifier
            .clip(RoundedCornerShape(14.dp))
            .background(colors.card)
            .clickable(role = Role.Image, onClickLabel = "View image") { onClick() }
            .semantics { contentDescription = "Image ${a.name}" },
        contentAlignment = Alignment.Center,
    ) {
        val bmp = img
        when {
            bmp != null -> Image(
                bmp, contentDescription = null,
                contentScale = if (crop) ContentScale.Crop else ContentScale.Fit,
                modifier = if (crop) Modifier.fillMaxSize() else Modifier.fillMaxWidth(),
            )
            failed -> Text("🖼 ${a.name}", style = MaterialTheme.typography.labelSmall, color = colors.tertiary,
                modifier = Modifier.padding(8.dp), maxLines = 3, overflow = TextOverflow.Ellipsis)
            else -> Text("…", color = colors.tertiary)
        }
    }
}
