package ai.nous.aurora.device;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AssistDeviceActionParser.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0005\u0002\u0003\u0004\u0005\u0006\u0082\u0001\u0005\u0007\b\t\n\u000b¨\u0006\f"}, d2 = {"Lai/nous/aurora/device/AssistDeviceAction;", "", "SetAlarm", "SetTimer", "CreateCalendarEvent", "OpenDialer", "ComposeSms", "Lai/nous/aurora/device/AssistDeviceAction$ComposeSms;", "Lai/nous/aurora/device/AssistDeviceAction$CreateCalendarEvent;", "Lai/nous/aurora/device/AssistDeviceAction$OpenDialer;", "Lai/nous/aurora/device/AssistDeviceAction$SetAlarm;", "Lai/nous/aurora/device/AssistDeviceAction$SetTimer;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface AssistDeviceAction {

    /* JADX INFO: compiled from: AssistDeviceActionParser.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lai/nous/aurora/device/AssistDeviceAction$SetAlarm;", "Lai/nous/aurora/device/AssistDeviceAction;", "hour", "", "minute", "label", "", "<init>", "(IILjava/lang/String;)V", "getHour", "()I", "getMinute", "getLabel", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class SetAlarm implements AssistDeviceAction {
        public static final int $stable = 0;
        private final int hour;
        private final String label;
        private final int minute;

        public static /* synthetic */ SetAlarm copy$default(SetAlarm setAlarm, int i, int i2, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = setAlarm.hour;
            }
            if ((i3 & 2) != 0) {
                i2 = setAlarm.minute;
            }
            if ((i3 & 4) != 0) {
                str = setAlarm.label;
            }
            return setAlarm.copy(i, i2, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getHour() {
            return this.hour;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getMinute() {
            return this.minute;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        public final SetAlarm copy(int hour, int minute, String label) {
            Intrinsics.checkNotNullParameter(label, "label");
            return new SetAlarm(hour, minute, label);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SetAlarm)) {
                return false;
            }
            SetAlarm setAlarm = (SetAlarm) other;
            return this.hour == setAlarm.hour && this.minute == setAlarm.minute && Intrinsics.areEqual(this.label, setAlarm.label);
        }

        public int hashCode() {
            return (((Integer.hashCode(this.hour) * 31) + Integer.hashCode(this.minute)) * 31) + this.label.hashCode();
        }

        public String toString() {
            return "SetAlarm(hour=" + this.hour + ", minute=" + this.minute + ", label=" + this.label + ")";
        }

        public SetAlarm(int hour, int minute, String label) {
            Intrinsics.checkNotNullParameter(label, "label");
            this.hour = hour;
            this.minute = minute;
            this.label = label;
        }

        public /* synthetic */ SetAlarm(int i, int i2, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, (i3 & 4) != 0 ? "Cornerstone" : str);
        }


    }

    /* JADX INFO: compiled from: AssistDeviceActionParser.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lai/nous/aurora/device/AssistDeviceAction$SetTimer;", "Lai/nous/aurora/device/AssistDeviceAction;", "seconds", "", "label", "", "<init>", "(ILjava/lang/String;)V", "getSeconds", "()I", "getLabel", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class SetTimer implements AssistDeviceAction {
        public static final int $stable = 0;
        private final String label;
        private final int seconds;

        public static /* synthetic */ SetTimer copy$default(SetTimer setTimer, int i, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = setTimer.seconds;
            }
            if ((i2 & 2) != 0) {
                str = setTimer.label;
            }
            return setTimer.copy(i, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getSeconds() {
            return this.seconds;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        public final SetTimer copy(int seconds, String label) {
            Intrinsics.checkNotNullParameter(label, "label");
            return new SetTimer(seconds, label);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SetTimer)) {
                return false;
            }
            SetTimer setTimer = (SetTimer) other;
            return this.seconds == setTimer.seconds && Intrinsics.areEqual(this.label, setTimer.label);
        }

        public int hashCode() {
            return (Integer.hashCode(this.seconds) * 31) + this.label.hashCode();
        }

        public String toString() {
            return "SetTimer(seconds=" + this.seconds + ", label=" + this.label + ")";
        }

        public SetTimer(int seconds, String label) {
            Intrinsics.checkNotNullParameter(label, "label");
            this.seconds = seconds;
            this.label = label;
        }

        public /* synthetic */ SetTimer(int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, (i2 & 2) != 0 ? "Aurora timer" : str);
        }



    }

    /* JADX INFO: compiled from: AssistDeviceActionParser.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ0\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\f¨\u0006\u001b"}, d2 = {"Lai/nous/aurora/device/AssistDeviceAction$CreateCalendarEvent;", "Lai/nous/aurora/device/AssistDeviceAction;", "title", "", "beginMillis", "", "endMillis", "<init>", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)V", "getTitle", "()Ljava/lang/String;", "getBeginMillis", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getEndMillis", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)Lai/nous/aurora/device/AssistDeviceAction$CreateCalendarEvent;", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class CreateCalendarEvent implements AssistDeviceAction {
        public static final int $stable = 0;
        private final Long beginMillis;
        private final Long endMillis;
        private final String title;

        public static /* synthetic */ CreateCalendarEvent copy$default(CreateCalendarEvent createCalendarEvent, String str, Long l, Long l2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = createCalendarEvent.title;
            }
            if ((i & 2) != 0) {
                l = createCalendarEvent.beginMillis;
            }
            if ((i & 4) != 0) {
                l2 = createCalendarEvent.endMillis;
            }
            return createCalendarEvent.copy(str, l, l2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Long getBeginMillis() {
            return this.beginMillis;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Long getEndMillis() {
            return this.endMillis;
        }

        public final CreateCalendarEvent copy(String title, Long beginMillis, Long endMillis) {
            Intrinsics.checkNotNullParameter(title, "title");
            return new CreateCalendarEvent(title, beginMillis, endMillis);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CreateCalendarEvent)) {
                return false;
            }
            CreateCalendarEvent createCalendarEvent = (CreateCalendarEvent) other;
            return Intrinsics.areEqual(this.title, createCalendarEvent.title) && Intrinsics.areEqual(this.beginMillis, createCalendarEvent.beginMillis) && Intrinsics.areEqual(this.endMillis, createCalendarEvent.endMillis);
        }

        public int hashCode() {
            return (((this.title.hashCode() * 31) + (this.beginMillis == null ? 0 : this.beginMillis.hashCode())) * 31) + (this.endMillis != null ? this.endMillis.hashCode() : 0);
        }

        public String toString() {
            return "CreateCalendarEvent(title=" + this.title + ", beginMillis=" + this.beginMillis + ", endMillis=" + this.endMillis + ")";
        }

        public CreateCalendarEvent(String title, Long beginMillis, Long endMillis) {
            Intrinsics.checkNotNullParameter(title, "title");
            this.title = title;
            this.beginMillis = beginMillis;
            this.endMillis = endMillis;
        }

        public /* synthetic */ CreateCalendarEvent(String str, Long l, Long l2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : l, (i & 4) != 0 ? null : l2);
        }




    }

    /* JADX INFO: compiled from: AssistDeviceActionParser.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lai/nous/aurora/device/AssistDeviceAction$OpenDialer;", "Lai/nous/aurora/device/AssistDeviceAction;", "number", "", "<init>", "(Ljava/lang/String;)V", "getNumber", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class OpenDialer implements AssistDeviceAction {
        public static final int $stable = 0;
        private final String number;

        public static /* synthetic */ OpenDialer copy$default(OpenDialer openDialer, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = openDialer.number;
            }
            return openDialer.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getNumber() {
            return this.number;
        }

        public final OpenDialer copy(String number) {
            Intrinsics.checkNotNullParameter(number, "number");
            return new OpenDialer(number);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof OpenDialer) && Intrinsics.areEqual(this.number, ((OpenDialer) other).number);
        }

        public int hashCode() {
            return this.number.hashCode();
        }

        public String toString() {
            return "OpenDialer(number=" + this.number + ")";
        }

        public OpenDialer(String number) {
            Intrinsics.checkNotNullParameter(number, "number");
            this.number = number;
        }


    }

    /* JADX INFO: compiled from: AssistDeviceActionParser.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lai/nous/aurora/device/AssistDeviceAction$ComposeSms;", "Lai/nous/aurora/device/AssistDeviceAction;", "number", "", "message", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getNumber", "()Ljava/lang/String;", "getMessage", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class ComposeSms implements AssistDeviceAction {
        public static final int $stable = 0;
        private final String message;
        private final String number;

        public static /* synthetic */ ComposeSms copy$default(ComposeSms composeSms, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = composeSms.number;
            }
            if ((i & 2) != 0) {
                str2 = composeSms.message;
            }
            return composeSms.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getNumber() {
            return this.number;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final ComposeSms copy(String number, String message) {
            Intrinsics.checkNotNullParameter(number, "number");
            Intrinsics.checkNotNullParameter(message, "message");
            return new ComposeSms(number, message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ComposeSms)) {
                return false;
            }
            ComposeSms composeSms = (ComposeSms) other;
            return Intrinsics.areEqual(this.number, composeSms.number) && Intrinsics.areEqual(this.message, composeSms.message);
        }

        public int hashCode() {
            return (this.number.hashCode() * 31) + this.message.hashCode();
        }

        public String toString() {
            return "ComposeSms(number=" + this.number + ", message=" + this.message + ")";
        }

        public ComposeSms(String number, String message) {
            Intrinsics.checkNotNullParameter(number, "number");
            Intrinsics.checkNotNullParameter(message, "message");
            this.number = number;
            this.message = message;
        }

        public /* synthetic */ ComposeSms(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? "" : str2);
        }



    }
}

