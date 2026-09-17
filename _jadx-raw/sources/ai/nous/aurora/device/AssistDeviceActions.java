package ai.nous.aurora.device;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import com.termux.terminal.KeyHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: AssistDeviceActions.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\tJ\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0002J(\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\u0002J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¨\u0006\u0018"}, d2 = {"Lai/nous/aurora/device/AssistDeviceActions;", "", "<init>", "()V", "tryHandle", "Lai/nous/aurora/device/AssistDeviceActions$ActionResult;", "context", "Landroid/content/Context;", "transcript", "", "reply", "structuredJson", "execute", "action", "Lai/nous/aurora/device/AssistDeviceAction;", "launch", "intent", "Landroid/content/Intent;", "success", "failure", "durationLabel", "seconds", "", "ActionResult", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AssistDeviceActions {
    public static final int $stable = 0;
    public static final AssistDeviceActions INSTANCE = new AssistDeviceActions();

    /* JADX INFO: compiled from: AssistDeviceActions.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0017"}, d2 = {"Lai/nous/aurora/device/AssistDeviceActions$ActionResult;", "", "handled", "", "message", "", "launched", "<init>", "(ZLjava/lang/String;Z)V", "getHandled", "()Z", "getMessage", "()Ljava/lang/String;", "getLaunched", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class ActionResult {
        public static final int $stable = 0;
        private final boolean handled;
        private final boolean launched;
        private final String message;

        public static /* synthetic */ ActionResult copy$default(ActionResult actionResult, boolean z, String str, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = actionResult.handled;
            }
            if ((i & 2) != 0) {
                str = actionResult.message;
            }
            if ((i & 4) != 0) {
                z2 = actionResult.launched;
            }
            return actionResult.copy(z, str, z2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getHandled() {
            return this.handled;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getLaunched() {
            return this.launched;
        }

        public final ActionResult copy(boolean handled, String message, boolean launched) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new ActionResult(handled, message, launched);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ActionResult)) {
                return false;
            }
            ActionResult actionResult = (ActionResult) other;
            return this.handled == actionResult.handled && Intrinsics.areEqual(this.message, actionResult.message) && this.launched == actionResult.launched;
        }

        public int hashCode() {
            return (((Boolean.hashCode(this.handled) * 31) + this.message.hashCode()) * 31) + Boolean.hashCode(this.launched);
        }

        public String toString() {
            return "ActionResult(handled=" + this.handled + ", message=" + this.message + ", launched=" + this.launched + ")";
        }

        public ActionResult(boolean handled, String message, boolean launched) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.handled = handled;
            this.message = message;
            this.launched = launched;
        }

        public /* synthetic */ ActionResult(boolean z, String str, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, str, (i & 4) != 0 ? false : z2);
        }

        public final boolean getHandled() {
            return this.handled;
        }

        public final String getMessage() {
            return this.message;
        }

        public final boolean getLaunched() {
            return this.launched;
        }
    }

    private AssistDeviceActions() {
    }

    public static /* synthetic */ ActionResult tryHandle$default(AssistDeviceActions assistDeviceActions, Context context, String str, String str2, String str3, int i, Object obj) {
        if ((i & 8) != 0) {
            str3 = null;
        }
        return assistDeviceActions.tryHandle(context, str, str2, str3);
    }

    public final ActionResult tryHandle(Context context, String transcript, String reply, String structuredJson) {
        boolean z;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(transcript, "transcript");
        Intrinsics.checkNotNullParameter(reply, "reply");
        List<AssistDeviceAction> structured = AssistDeviceActionParser.INSTANCE.parseStructured(structuredJson);
        if (structured.isEmpty()) {
            structured = AssistDeviceActionParser.INSTANCE.parseReply(reply);
        }
        List<AssistDeviceAction> transcript2 = structured;
        if (transcript2.isEmpty()) {
            transcript2 = AssistDeviceActionParser.INSTANCE.parseTranscript(transcript);
        }
        List<AssistDeviceAction> list = transcript2;
        if (list.isEmpty()) {
            return new ActionResult(false, "", false, 4, null);
        }
        List<AssistDeviceAction> $this$map$iv = list;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            AssistDeviceAction it = (AssistDeviceAction) item$iv$iv;
            destination$iv$iv.add(INSTANCE.execute(context, it));
        }
        Iterable results = (List) destination$iv$iv;
        String strJoinToString$default = CollectionsKt.joinToString$default(results, " · ", null, null, 0, null, new Function1() { // from class: ai.nous.aurora.device.AssistDeviceActions$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AssistDeviceActions.tryHandle$lambda$3((AssistDeviceActions.ActionResult) obj);
            }
        }, 30, null);
        Iterable $this$all$iv = results;
        if (($this$all$iv instanceof Collection) && ((Collection) $this$all$iv).isEmpty()) {
            z = true;
        } else {
            for (Object element$iv : $this$all$iv) {
                ActionResult it2 = (ActionResult) element$iv;
                if (!it2.getLaunched()) {
                    z = false;
                }
            }
            z = true;
        }
        return new ActionResult(true, strJoinToString$default, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence tryHandle$lambda$3(ActionResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getMessage();
    }

    private final ActionResult execute(Context context, AssistDeviceAction action) {
        if (action instanceof AssistDeviceAction.SetAlarm) {
            Intent intent = new Intent("android.intent.action.SET_ALARM");
            intent.putExtra("android.intent.extra.alarm.HOUR", ((AssistDeviceAction.SetAlarm) action).getHour());
            intent.putExtra("android.intent.extra.alarm.MINUTES", ((AssistDeviceAction.SetAlarm) action).getMinute());
            intent.putExtra("android.intent.extra.alarm.MESSAGE", ((AssistDeviceAction.SetAlarm) action).getLabel());
            intent.putExtra("android.intent.extra.alarm.SKIP_UI", true);
            intent.putExtra("android.intent.extra.alarm.VIBRATE", true);
            intent.addFlags(KeyHandler.KEYMOD_NUM_LOCK);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format(Locale.US, "%d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(((AssistDeviceAction.SetAlarm) action).getHour()), Integer.valueOf(((AssistDeviceAction.SetAlarm) action).getMinute())}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return launch(context, intent, "Alarm set for " + str, "Couldn't open the alarm app");
        }
        if (action instanceof AssistDeviceAction.SetTimer) {
            Intent intent2 = new Intent("android.intent.action.SET_TIMER");
            intent2.putExtra("android.intent.extra.alarm.LENGTH", ((AssistDeviceAction.SetTimer) action).getSeconds());
            intent2.putExtra("android.intent.extra.alarm.MESSAGE", ((AssistDeviceAction.SetTimer) action).getLabel());
            intent2.putExtra("android.intent.extra.alarm.SKIP_UI", true);
            intent2.addFlags(KeyHandler.KEYMOD_NUM_LOCK);
            return launch(context, intent2, "Timer set for " + durationLabel(((AssistDeviceAction.SetTimer) action).getSeconds()), "Couldn't open the timer app");
        }
        if (action instanceof AssistDeviceAction.CreateCalendarEvent) {
            Intent intent3 = new Intent("android.intent.action.INSERT");
            intent3.setData(CalendarContract.Events.CONTENT_URI);
            intent3.putExtra("title", ((AssistDeviceAction.CreateCalendarEvent) action).getTitle());
            Long beginMillis = ((AssistDeviceAction.CreateCalendarEvent) action).getBeginMillis();
            if (beginMillis != null) {
                long it = beginMillis.longValue();
                intent3.putExtra("beginTime", it);
            }
            Long endMillis = ((AssistDeviceAction.CreateCalendarEvent) action).getEndMillis();
            if (endMillis != null) {
                long it2 = endMillis.longValue();
                intent3.putExtra("endTime", it2);
            }
            intent3.addFlags(KeyHandler.KEYMOD_NUM_LOCK);
            return launch(context, intent3, "Opened calendar event", "Couldn't open the calendar");
        }
        if (action instanceof AssistDeviceAction.OpenDialer) {
            Intent intent4 = new Intent("android.intent.action.DIAL", Uri.fromParts("tel", ((AssistDeviceAction.OpenDialer) action).getNumber(), null));
            intent4.addFlags(KeyHandler.KEYMOD_NUM_LOCK);
            return launch(context, intent4, "Opened dialer", "Couldn't open the dialer");
        }
        if (!(action instanceof AssistDeviceAction.ComposeSms)) {
            throw new NoWhenBranchMatchedException();
        }
        Intent intent5 = new Intent("android.intent.action.SENDTO", Uri.fromParts("smsto", ((AssistDeviceAction.ComposeSms) action).getNumber(), null));
        intent5.putExtra("sms_body", ((AssistDeviceAction.ComposeSms) action).getMessage());
        intent5.addFlags(KeyHandler.KEYMOD_NUM_LOCK);
        return launch(context, intent5, "Opened message draft", "Couldn't open messages");
    }

    private final ActionResult launch(Context context, Intent intent, String success, String failure) {
        try {
            context.startActivity(intent);
            return new ActionResult(true, success, true);
        } catch (Exception e) {
            return new ActionResult(true, failure, false);
        }
    }

    private final String durationLabel(int seconds) {
        if (seconds % 3600 == 0) {
            return (seconds / 3600) + "h";
        }
        if (seconds % 60 != 0) {
            return seconds + "s";
        }
        return (seconds / 60) + "m";
    }
}
