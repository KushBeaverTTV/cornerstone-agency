package ai.nous.aurora.domain.model;

import java.net.URI;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AssistLockPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\"B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tJ\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\tJ\u0016\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\tJ\u000e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012J\u0016\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\tJ\u0016\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0012J\u000e\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0005J\u000e\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\tJ\u000e\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\tJ\u0006\u0010 \u001a\u00020\tJ\u0006\u0010!\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lai/nous/aurora/domain/model/AssistLockPolicy;", "", "<init>", "()V", "MAX_RECORDING_MILLIS", "", "LEAVE_CANCEL_DEBOUNCE_MILLIS", "MIN_CAPTURE_BYTES", "STATUS_HOLD_POWER_AGAIN", "", "STATUS_READY", "STATUS_WORKING", "STATUS_NEED_MIC", "STATUS_TOO_SHORT", "powerGestureAction", "Lai/nous/aurora/domain/model/AssistLockPolicy$PowerGesture;", "phase", "shouldAutoStartListen", "", "autoListen", "shouldBlockAutoListen", "deviceLocked", "shouldBlockRecording", "canBeginRecording", "canFinishRecording", "hasRecorder", "isUsableCapture", "byteLength", "isTrustedBridgeAudioUrl", "url", "shouldCancelListenOnLeave", "STATUS_CANCELLED", "autoListenBlockedStatus", "recordingBlockedStatus", "PowerGesture", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AssistLockPolicy {
    public static final int $stable = 0;
    public static final AssistLockPolicy INSTANCE = new AssistLockPolicy();
    public static final long LEAVE_CANCEL_DEBOUNCE_MILLIS = 350;
    public static final long MAX_RECORDING_MILLIS = 45000;
    public static final long MIN_CAPTURE_BYTES = 800;
    public static final String STATUS_CANCELLED = "Stopped — left Assist";
    public static final String STATUS_HOLD_POWER_AGAIN = "Listening… short-hold power again when done";
    public static final String STATUS_NEED_MIC = "Microphone permission required";
    public static final String STATUS_READY = "Short-hold power to talk · or tap the orb";
    public static final String STATUS_TOO_SHORT = "Too short — talk, then short-hold power";
    public static final String STATUS_WORKING = "Sending to Hermes…";

    private AssistLockPolicy() {
    }

    /* JADX INFO: compiled from: AssistLockPolicy.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/domain/model/AssistLockPolicy$PowerGesture;", "", "<init>", "(Ljava/lang/String;I)V", "StartListen", "FinishSend", "Ignore", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum PowerGesture {
        StartListen,
        FinishSend,
        Ignore;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<PowerGesture> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002b, code lost:
    
        if (r2.equals("need_mic") == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0034, code lost:
    
        if (r2.equals("error") == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003d, code lost:
    
        if (r2.equals("idle") == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0046, code lost:
    
        if (r2.equals("done") == false) goto L26;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final ai.nous.aurora.domain.model.AssistLockPolicy.PowerGesture powerGestureAction(java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "phase"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            int r0 = r2.hashCode()
            switch(r0) {
                case 3089282: goto L40;
                case 3227604: goto L37;
                case 96784904: goto L2e;
                case 866001694: goto L25;
                case 993558001: goto L19;
                case 1525164849: goto Ld;
                default: goto Lc;
            }
        Lc:
            goto L4c
        Ld:
            java.lang.String r0 = "working"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L16
            goto Lc
        L16:
            ai.nous.aurora.domain.model.AssistLockPolicy$PowerGesture r0 = ai.nous.aurora.domain.model.AssistLockPolicy.PowerGesture.Ignore
            goto L4e
        L19:
            java.lang.String r0 = "recording"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L22
            goto Lc
        L22:
            ai.nous.aurora.domain.model.AssistLockPolicy$PowerGesture r0 = ai.nous.aurora.domain.model.AssistLockPolicy.PowerGesture.FinishSend
            goto L4e
        L25:
            java.lang.String r0 = "need_mic"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L49
            goto Lc
        L2e:
            java.lang.String r0 = "error"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L49
            goto Lc
        L37:
            java.lang.String r0 = "idle"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L49
            goto Lc
        L40:
            java.lang.String r0 = "done"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L49
            goto Lc
        L49:
            ai.nous.aurora.domain.model.AssistLockPolicy$PowerGesture r0 = ai.nous.aurora.domain.model.AssistLockPolicy.PowerGesture.StartListen
            goto L4e
        L4c:
            ai.nous.aurora.domain.model.AssistLockPolicy$PowerGesture r0 = ai.nous.aurora.domain.model.AssistLockPolicy.PowerGesture.StartListen
        L4e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ai.nous.aurora.domain.model.AssistLockPolicy.powerGestureAction(java.lang.String):ai.nous.aurora.domain.model.AssistLockPolicy$PowerGesture");
    }

    public final boolean shouldAutoStartListen(boolean autoListen, String phase) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        return SetsKt.setOf((Object[]) new String[]{"idle", "done", "error", "need_mic"}).contains(phase);
    }

    public final boolean shouldBlockAutoListen(boolean deviceLocked, String phase) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        return false;
    }

    public final boolean shouldBlockRecording(boolean deviceLocked) {
        return false;
    }

    public final boolean canBeginRecording(boolean deviceLocked, String phase) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        return SetsKt.setOf((Object[]) new String[]{"idle", "done", "error"}).contains(phase);
    }

    public final boolean canFinishRecording(String phase, boolean hasRecorder) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        return Intrinsics.areEqual(phase, "recording") && hasRecorder;
    }

    public final boolean isUsableCapture(long byteLength) {
        return byteLength >= 800;
    }

    public final boolean isTrustedBridgeAudioUrl(String url) {
        String scheme;
        Intrinsics.checkNotNullParameter(url, "url");
        String u = StringsKt.trim((CharSequence) url).toString();
        if ((u.length() == 0) || StringsKt.contains$default((CharSequence) u, (CharSequence) "@", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) u, (CharSequence) "..", false, 2, (Object) null)) {
            return false;
        }
        try {
            URI uri = new URI(u);
            String scheme2 = uri.getScheme();
            if (scheme2 != null) {
                scheme = scheme2.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(scheme, "toLowerCase(...)");
            } else {
                scheme = null;
            }
            if (!Intrinsics.areEqual(scheme, "http") && !Intrinsics.areEqual(scheme, "https")) {
                return false;
            }
            String host = uri.getHost();
            if (host == null || StringsKt.isBlank(host)) {
                return false;
            }
            String path = uri.getPath();
            if (path == null) {
                path = "";
            }
            return StringsKt.startsWith$default(path, "/v1/voice/audio/", false, 2, (Object) null) && path.length() > 16;
        } catch (Exception e) {
            return false;
        }
    }

    public final boolean shouldCancelListenOnLeave(String phase) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        return Intrinsics.areEqual(phase, "recording");
    }

    public final String autoListenBlockedStatus() {
        return STATUS_READY;
    }

    public final String recordingBlockedStatus() {
        return STATUS_READY;
    }
}
