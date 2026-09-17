package ai.nous.aurora.service;

import ai.nous.aurora.MainActivity;
import com.cornerstone.agency.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.window.embedding.SplitRule;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AuroraNotificationManager.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lai/nous/aurora/service/AuroraNotificationManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "notifyMessage", "", "title", "", "body", "id", "", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AuroraNotificationManager {
    public static final String CHANNEL_ID = "aurora_messages";
    private final Context context;
    public static final int $stable = 8;

    public AuroraNotificationManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Hermes messages", 3);
        channel.setDescription("New replies from Hermes while Aurora is in the background");
        NotificationManager nm = (NotificationManager) this.context.getSystemService(NotificationManager.class);
        nm.createNotificationChannel(channel);
    }

    public static /* synthetic */ void notifyMessage$default(AuroraNotificationManager auroraNotificationManager, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 1001;
        }
        auroraNotificationManager.notifyMessage(str, str2, i);
    }

    public final void notifyMessage(String title, String body, int id) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(body, "body");
        if (Build.VERSION.SDK_INT >= 33) {
            boolean granted = ContextCompat.checkSelfPermission(this.context, "android.permission.POST_NOTIFICATIONS") == 0;
            if (!granted) {
                return;
            }
        }
        if (NotificationManagerCompat.from(this.context).areNotificationsEnabled()) {
            Intent launch = new Intent(this.context, (Class<?>) MainActivity.class);
            launch.setFlags(603979776);
            PendingIntent pi = PendingIntent.getActivity(this.context, 0, launch, 201326592);
            Notification notification = new NotificationCompat.Builder(this.context, CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(title).setContentText(StringsKt.take(body, 180)).setStyle(new NotificationCompat.BigTextStyle().bigText(StringsKt.take(body, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT))).setContentIntent(pi).setAutoCancel(true).setPriority(0).build();
            Intrinsics.checkNotNullExpressionValue(notification, "build(...)");
            try {
                NotificationManagerCompat.from(this.context).notify(id, notification);
            } catch (SecurityException e) {
            }
        }
    }
}
