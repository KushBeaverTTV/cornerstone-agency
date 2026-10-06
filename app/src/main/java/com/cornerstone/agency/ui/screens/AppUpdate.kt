package com.cornerstone.agency.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.FileProvider
import java.io.File

/** Where a downloaded release waits for the system installer (shared via FileProvider). */
internal fun updateApkFile(ctx: Context): File = File(File(ctx.cacheDir, "updates"), "cornerstone-agency.apk")

/**
 * Hand the downloaded APK to the system installer. The first time, Android asks the user to
 * allow installs from this app; we open that setting and the user taps Update again.
 * Returns false when the permission screen was shown instead.
 */
internal fun installApk(ctx: Context, apk: File): Boolean {
    if (Build.VERSION.SDK_INT >= 26 && !ctx.packageManager.canRequestPackageInstalls()) {
        ctx.startActivity(
            Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:${ctx.packageName}"))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
        )
        return false
    }
    val uri = FileProvider.getUriForFile(ctx, "${ctx.packageName}.files", apk)
    ctx.startActivity(
        Intent(Intent.ACTION_VIEW)
            .setDataAndType(uri, "application/vnd.android.package-archive")
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK),
    )
    return true
}
