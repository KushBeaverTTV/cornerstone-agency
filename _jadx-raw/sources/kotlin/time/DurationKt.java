package kotlin.time;

import androidx.core.view.MotionEventCompat;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Duration.kt */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b*\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001d\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010&\u001a\u0015\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0010\u001a\u0015\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0010\u001a\u0015\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0010\u001a\u0015\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0010\u001a\u0010\u0010/\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u0001H\u0002\u001a\u0010\u00100\u001a\u00020\u00012\u0006\u0010.\u001a\u00020\u0001H\u0002\u001a\u001d\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002¢\u0006\u0002\u00106\u001a\u0010\u00107\u001a\u00020\u00012\u0006\u00102\u001a\u000203H\u0002\u001a)\u00108\u001a\u00020\u0005*\u0002032\u0006\u00109\u001a\u00020\u00052\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u0002050;H\u0082\b\u001a)\u0010=\u001a\u000203*\u0002032\u0006\u00109\u001a\u00020\u00052\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u0002050;H\u0082\b\u001a\u001c\u0010>\u001a\u00020\u0007*\u00020\b2\u0006\u0010?\u001a\u00020\u0007H\u0087\n¢\u0006\u0004\b@\u0010A\u001a\u001c\u0010>\u001a\u00020\u0007*\u00020\u00052\u0006\u0010?\u001a\u00020\u0007H\u0087\n¢\u0006\u0004\bB\u0010C\u001a\u0019\u0010D\u001a\u00020\u0007*\u00020\b2\u0006\u0010E\u001a\u00020FH\u0007¢\u0006\u0002\u0010G\u001a\u0019\u0010D\u001a\u00020\u0007*\u00020\u00052\u0006\u0010E\u001a\u00020FH\u0007¢\u0006\u0002\u0010H\u001a\u0019\u0010D\u001a\u00020\u0007*\u00020\u00012\u0006\u0010E\u001a\u00020FH\u0007¢\u0006\u0002\u0010I\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u001e\u0010\u0006\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u001e\u0010\u0006\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\r\u001a\u0004\b\u000b\u0010\u000e\"\u001e\u0010\u0006\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u000f\u001a\u0004\b\u000b\u0010\u0010\"\u001e\u0010\u0011\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\n\u001a\u0004\b\u0013\u0010\f\"\u001e\u0010\u0011\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000e\"\u001e\u0010\u0011\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0010\"\u001e\u0010\u0014\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\f\"\u001e\u0010\u0014\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\r\u001a\u0004\b\u0016\u0010\u000e\"\u001e\u0010\u0014\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0010\"\u001e\u0010\u0017\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0018\u0010\n\u001a\u0004\b\u0019\u0010\f\"\u001e\u0010\u0017\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0018\u0010\r\u001a\u0004\b\u0019\u0010\u000e\"\u001e\u0010\u0017\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0018\u0010\u000f\u001a\u0004\b\u0019\u0010\u0010\"\u001e\u0010\u001a\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\n\u001a\u0004\b\u001c\u0010\f\"\u001e\u0010\u001a\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\r\u001a\u0004\b\u001c\u0010\u000e\"\u001e\u0010\u001a\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\u000f\u001a\u0004\b\u001c\u0010\u0010\"\u001e\u0010\u001d\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001e\u0010\n\u001a\u0004\b\u001f\u0010\f\"\u001e\u0010\u001d\u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001e\u0010\r\u001a\u0004\b\u001f\u0010\u000e\"\u001e\u0010\u001d\u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001e\u0010\u000f\u001a\u0004\b\u001f\u0010\u0010\"\u001e\u0010 \u001a\u00020\u0007*\u00020\b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b!\u0010\n\u001a\u0004\b\"\u0010\f\"\u001e\u0010 \u001a\u00020\u0007*\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b!\u0010\r\u001a\u0004\b\"\u0010\u000e\"\u001e\u0010 \u001a\u00020\u0007*\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b!\u0010\u000f\u001a\u0004\b\"\u0010\u0010¨\u0006J"}, d2 = {"MAX_MILLIS", "", "MAX_NANOS", "MAX_NANOS_IN_MILLIS", "NANOS_IN_MILLIS", "", "days", "Lkotlin/time/Duration;", "", "getDays$annotations", "(D)V", "getDays", "(D)J", "(I)V", "(I)J", "(J)V", "(J)J", "hours", "getHours$annotations", "getHours", "microseconds", "getMicroseconds$annotations", "getMicroseconds", "milliseconds", "getMilliseconds$annotations", "getMilliseconds", "minutes", "getMinutes$annotations", "getMinutes", "nanoseconds", "getNanoseconds$annotations", "getNanoseconds", "seconds", "getSeconds$annotations", "getSeconds", "durationOf", "normalValue", "unitDiscriminator", "(JI)J", "durationOfMillis", "normalMillis", "durationOfMillisNormalized", "millis", "durationOfNanos", "normalNanos", "durationOfNanosNormalized", "nanos", "millisToNanos", "nanosToMillis", "parseDuration", "value", "", "strictIso", "", "(Ljava/lang/String;Z)J", "parseOverLongIsoComponent", "skipWhile", "startIndex", "predicate", "Lkotlin/Function1;", "", "substringWhile", "times", "duration", "times-kIfJnKk", "(DJ)J", "times-mvk6XK0", "(IJ)J", "toDuration", "unit", "Lkotlin/time/DurationUnit;", "(DLkotlin/time/DurationUnit;)J", "(ILkotlin/time/DurationUnit;)J", "(JLkotlin/time/DurationUnit;)J", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class DurationKt {
    public static final long MAX_MILLIS = 4611686018427387903L;
    public static final long MAX_NANOS = 4611686018426999999L;
    private static final long MAX_NANOS_IN_MILLIS = 4611686018426L;
    public static final int NANOS_IN_MILLIS = 1000000;

    @Deprecated(message = "Use 'Double.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.days", imports = {"kotlin.time.Duration.Companion.days"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getDays$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.days", imports = {"kotlin.time.Duration.Companion.days"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getDays$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.days", imports = {"kotlin.time.Duration.Companion.days"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getDays$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getHours$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getHours$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getHours$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getMicroseconds$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getMicroseconds$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getMicroseconds$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getMilliseconds$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getMilliseconds$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getMilliseconds$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getMinutes$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getMinutes$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getMinutes$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getNanoseconds$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getNanoseconds$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getNanoseconds$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getSeconds$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getSeconds$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static /* synthetic */ void getSeconds$annotations(long j) {
    }

    public static final long toDuration(int $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (unit.compareTo(DurationUnit.SECONDS) <= 0) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow($this$toDuration, unit, DurationUnit.NANOSECONDS));
        }
        return toDuration($this$toDuration, unit);
    }

    public static final long toDuration(long $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        long maxNsInUnit = DurationUnitKt.convertDurationUnitOverflow(MAX_NANOS, DurationUnit.NANOSECONDS, unit);
        boolean z = false;
        if ((-maxNsInUnit) <= $this$toDuration && $this$toDuration <= maxNsInUnit) {
            z = true;
        }
        if (z) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow($this$toDuration, unit, DurationUnit.NANOSECONDS));
        }
        long millis = DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.MILLISECONDS);
        return durationOfMillis(RangesKt.coerceIn(millis, -4611686018427387903L, MAX_MILLIS));
    }

    public static final long toDuration(double $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        double valueInNs = DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.NANOSECONDS);
        if (Double.isNaN(valueInNs)) {
            throw new IllegalArgumentException("Duration value cannot be NaN.".toString());
        }
        long nanos = MathKt.roundToLong(valueInNs);
        boolean z = false;
        if (-4611686018426999999L <= nanos && nanos < 4611686018427000000L) {
            z = true;
        }
        if (z) {
            long millis = durationOfNanos(nanos);
            return millis;
        }
        long millis2 = MathKt.roundToLong(DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.MILLISECONDS));
        return durationOfMillisNormalized(millis2);
    }

    /* JADX INFO: renamed from: times-mvk6XK0, reason: not valid java name */
    private static final long m8841timesmvk6XK0(int $this$times_u2dmvk6XK0, long duration) {
        return Duration.m8752timesUwyO8pc(duration, $this$times_u2dmvk6XK0);
    }

    /* JADX INFO: renamed from: times-kIfJnKk, reason: not valid java name */
    private static final long m8840timeskIfJnKk(double $this$times_u2dkIfJnKk, long duration) {
        return Duration.m8751timesUwyO8pc(duration, $this$times_u2dkIfJnKk);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:166:0x0332  */
    /* JADX WARN: Code duplicated, block: B:199:0x00d7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:202:0x0342 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:203:0x033a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:205:0x0303 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:51:0x00bc A[LOOP:1: B:35:0x007b->B:51:0x00bc, LOOP_END] */
    public static final long parseDuration(String value, boolean strictIso) {
        int index;
        boolean isNegative;
        DurationUnit unit;
        int dotIndex;
        String nonDigitSymbols;
        String infinityString;
        boolean hasSign;
        boolean isNegative2;
        boolean z;
        int length = value.length();
        if (length == 0) {
            throw new IllegalArgumentException("The string is empty");
        }
        long result = Duration.INSTANCE.m8817getZEROUwyO8pc();
        String infinityString2 = "Infinity";
        switch (value.charAt(0)) {
            case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
            case MotionEventCompat.AXIS_GENERIC_14 /* 45 */:
                int index2 = 0 + 1;
                index = index2;
                break;
            case MotionEventCompat.AXIS_GENERIC_13 /* 44 */:
            default:
                index = 0;
                break;
        }
        boolean hasSign2 = index > 0;
        boolean isNegative3 = hasSign2 && StringsKt.startsWith$default((CharSequence) value, '-', false, 2, (Object) null);
        if (length <= index) {
            throw new IllegalArgumentException("No components");
        }
        if (value.charAt(index) == 'P') {
            int index3 = index + 1;
            if (index3 == length) {
                throw new IllegalArgumentException();
            }
            String nonDigitSymbols2 = "+-.";
            boolean isTimeComponent = false;
            DurationUnit prevUnit = null;
            while (index3 < length) {
                if (value.charAt(index3) == 'T') {
                    if (isTimeComponent || (index3 = index3 + 1) == length) {
                        throw new IllegalArgumentException();
                    }
                    isTimeComponent = true;
                } else {
                    int $i$f$substringWhile = 0;
                    String $this$skipWhile$iv$iv = value;
                    int i$iv$iv = index3;
                    while (true) {
                        int $i$f$substringWhile2 = $i$f$substringWhile;
                        int $i$f$substringWhile3 = $this$skipWhile$iv$iv.length();
                        if (i$iv$iv < $i$f$substringWhile3) {
                            isNegative2 = isNegative3;
                            String $this$skipWhile$iv$iv2 = $this$skipWhile$iv$iv;
                            char it = $this$skipWhile$iv$iv2.charAt(i$iv$iv);
                            if ('0' <= it && it < ':') {
                                nonDigitSymbols = nonDigitSymbols2;
                                infinityString = infinityString2;
                                hasSign = hasSign2;
                            } else {
                                nonDigitSymbols = nonDigitSymbols2;
                                infinityString = infinityString2;
                                hasSign = hasSign2;
                                if (!StringsKt.contains$default((CharSequence) nonDigitSymbols2, it, false, 2, (Object) null)) {
                                    z = false;
                                }
                                if (z) {
                                    i$iv$iv++;
                                    hasSign2 = hasSign;
                                    infinityString2 = infinityString;
                                    $i$f$substringWhile = $i$f$substringWhile2;
                                    isNegative3 = isNegative2;
                                    $this$skipWhile$iv$iv = $this$skipWhile$iv$iv2;
                                    nonDigitSymbols2 = nonDigitSymbols;
                                }
                            }
                            z = true;
                            if (z) {
                                i$iv$iv++;
                                hasSign2 = hasSign;
                                infinityString2 = infinityString;
                                $i$f$substringWhile = $i$f$substringWhile2;
                                isNegative3 = isNegative2;
                                $this$skipWhile$iv$iv = $this$skipWhile$iv$iv2;
                                nonDigitSymbols2 = nonDigitSymbols;
                            }
                        } else {
                            nonDigitSymbols = nonDigitSymbols2;
                            infinityString = infinityString2;
                            hasSign = hasSign2;
                            isNegative2 = isNegative3;
                        }
                    }
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.String");
                    String component = value.substring(index3, i$iv$iv);
                    Intrinsics.checkNotNullExpressionValue(component, "substring(...)");
                    if (component.length() == 0) {
                        throw new IllegalArgumentException();
                    }
                    int index4 = index3 + component.length();
                    String str = value;
                    if (!(index4 >= 0 && index4 < str.length())) {
                        throw new IllegalArgumentException("Missing unit for value " + component);
                    }
                    char unitChar = str.charAt(index4);
                    index3 = index4 + 1;
                    DurationUnit unit2 = DurationUnitKt.durationUnitByIsoChar(unitChar, isTimeComponent);
                    DurationUnit prevUnit2 = prevUnit;
                    if (prevUnit2 != null && prevUnit2.compareTo(unit2) <= 0) {
                        throw new IllegalArgumentException("Unexpected order of duration components");
                    }
                    prevUnit = unit2;
                    int dotIndex2 = StringsKt.indexOf$default((CharSequence) component, '.', 0, false, 6, (Object) null);
                    if (unit2 == DurationUnit.SECONDS && dotIndex2 > 0) {
                        Intrinsics.checkNotNull(component, "null cannot be cast to non-null type java.lang.String");
                        String whole = component.substring(0, dotIndex2);
                        Intrinsics.checkNotNullExpressionValue(whole, "substring(...)");
                        long result2 = Duration.m8750plusLRDsOJo(result, toDuration(parseOverLongIsoComponent(whole), unit2));
                        Intrinsics.checkNotNull(component, "null cannot be cast to non-null type java.lang.String");
                        String strSubstring = component.substring(dotIndex2);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                        result = Duration.m8750plusLRDsOJo(result2, toDuration(Double.parseDouble(strSubstring), unit2));
                        hasSign2 = hasSign;
                        infinityString2 = infinityString;
                        isNegative3 = isNegative2;
                        nonDigitSymbols2 = nonDigitSymbols;
                    } else {
                        result = Duration.m8750plusLRDsOJo(result, toDuration(parseOverLongIsoComponent(component), unit2));
                        hasSign2 = hasSign;
                        infinityString2 = infinityString;
                        isNegative3 = isNegative2;
                        nonDigitSymbols2 = nonDigitSymbols;
                    }
                }
            }
            isNegative = isNegative3;
        } else {
            isNegative = isNegative3;
            if (!strictIso) {
                if (StringsKt.regionMatches(value, index, "Infinity", 0, Math.max(length - index, "Infinity".length()), true)) {
                    result = Duration.INSTANCE.m8815getINFINITEUwyO8pc();
                } else {
                    DurationUnit prevUnit3 = null;
                    boolean afterFirst = false;
                    boolean allowSpaces = !hasSign2;
                    if (hasSign2 && value.charAt(index) == '(' && StringsKt.last(value) == ')') {
                        allowSpaces = true;
                        index++;
                        length--;
                        if (index == length) {
                            throw new IllegalArgumentException("No components");
                        }
                    }
                    while (index < length) {
                        if (afterFirst && allowSpaces) {
                            int i$iv = index;
                            while (i$iv < value.length()) {
                                boolean afterFirst2 = afterFirst;
                                if ((value.charAt(i$iv) == ' ' ? (char) 1 : (char) 0) != 0) {
                                    i$iv++;
                                    afterFirst = afterFirst2;
                                } else {
                                    index = i$iv;
                                }
                            }
                            index = i$iv;
                        }
                        boolean afterFirst3 = true;
                        int i$iv$iv2 = index;
                        while (true) {
                            afterFirst = afterFirst3;
                            if (i$iv$iv2 < value.length()) {
                                char it2 = value.charAt(i$iv$iv2);
                                allowSpaces = allowSpaces;
                                if (((('0' <= it2 && it2 < ':') || it2 == '.') ? (char) 1 : (char) 0) != 0) {
                                    i$iv$iv2++;
                                    afterFirst3 = afterFirst;
                                    allowSpaces = allowSpaces;
                                }
                            } else {
                                allowSpaces = allowSpaces;
                            }
                        }
                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.String");
                        String component2 = value.substring(index, i$iv$iv2);
                        Intrinsics.checkNotNullExpressionValue(component2, "substring(...)");
                        if (component2.length() == 0) {
                            throw new IllegalArgumentException();
                        }
                        int index5 = index + component2.length();
                        int $i$f$substringWhile4 = 0;
                        int i$iv$iv3 = index5;
                        while (i$iv$iv3 < value.length()) {
                            char it3 = value.charAt(i$iv$iv3);
                            int $i$f$substringWhile5 = $i$f$substringWhile4;
                            if ('a' <= it3 && it3 < '{') {
                                i$iv$iv3++;
                                $i$f$substringWhile4 = $i$f$substringWhile5;
                            } else {
                                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.String");
                                String unitName = value.substring(index5, i$iv$iv3);
                                Intrinsics.checkNotNullExpressionValue(unitName, "substring(...)");
                                index = index5 + unitName.length();
                                unit = DurationUnitKt.durationUnitByShortName(unitName);
                                if (prevUnit3 == null && prevUnit3.compareTo(unit) <= 0) {
                                    throw new IllegalArgumentException("Unexpected order of duration components");
                                }
                                prevUnit3 = unit;
                                dotIndex = StringsKt.indexOf$default((CharSequence) component2, '.', 0, false, 6, (Object) null);
                                if (dotIndex > 0) {
                                    Intrinsics.checkNotNull(component2, "null cannot be cast to non-null type java.lang.String");
                                    String whole2 = component2.substring(0, dotIndex);
                                    Intrinsics.checkNotNullExpressionValue(whole2, "substring(...)");
                                    long result3 = Duration.m8750plusLRDsOJo(result, toDuration(Long.parseLong(whole2), unit));
                                    Intrinsics.checkNotNull(component2, "null cannot be cast to non-null type java.lang.String");
                                    String strSubstring2 = component2.substring(dotIndex);
                                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                                    result = Duration.m8750plusLRDsOJo(result3, toDuration(Double.parseDouble(strSubstring2), unit));
                                    if (index >= length) {
                                        throw new IllegalArgumentException("Fractional component must be last");
                                    }
                                } else {
                                    result = Duration.m8750plusLRDsOJo(result, toDuration(Long.parseLong(component2), unit));
                                }
                            }
                        }
                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.String");
                        String unitName2 = value.substring(index5, i$iv$iv3);
                        Intrinsics.checkNotNullExpressionValue(unitName2, "substring(...)");
                        index = index5 + unitName2.length();
                        unit = DurationUnitKt.durationUnitByShortName(unitName2);
                        if (prevUnit3 == null) {
                        }
                        prevUnit3 = unit;
                        dotIndex = StringsKt.indexOf$default((CharSequence) component2, '.', 0, false, 6, (Object) null);
                        if (dotIndex > 0) {
                            Intrinsics.checkNotNull(component2, "null cannot be cast to non-null type java.lang.String");
                            String whole3 = component2.substring(0, dotIndex);
                            Intrinsics.checkNotNullExpressionValue(whole3, "substring(...)");
                            long result4 = Duration.m8750plusLRDsOJo(result, toDuration(Long.parseLong(whole3), unit));
                            Intrinsics.checkNotNull(component2, "null cannot be cast to non-null type java.lang.String");
                            String strSubstring3 = component2.substring(dotIndex);
                            Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                            result = Duration.m8750plusLRDsOJo(result4, toDuration(Double.parseDouble(strSubstring3), unit));
                            if (index >= length) {
                                throw new IllegalArgumentException("Fractional component must be last");
                            }
                        } else {
                            result = Duration.m8750plusLRDsOJo(result, toDuration(Long.parseLong(component2), unit));
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        return isNegative ? Duration.m8767unaryMinusUwyO8pc(result) : result;
    }

    private static final long parseOverLongIsoComponent(String value) {
        boolean z;
        int length = value.length();
        int startIndex = 0;
        if (length > 0 && StringsKt.contains$default((CharSequence) "+-", value.charAt(0), false, 2, (Object) null)) {
            startIndex = 0 + 1;
        }
        if (length - startIndex > 16) {
            Iterable $this$all$iv = new IntRange(startIndex, StringsKt.getLastIndex(value));
            if (!($this$all$iv instanceof Collection) || !((Collection) $this$all$iv).isEmpty()) {
                Iterator it = $this$all$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        int element$iv = ((IntIterator) it).nextInt();
                        char cCharAt = value.charAt(element$iv);
                        int it2 = ('0' > cCharAt || cCharAt >= ':') ? 0 : 1;
                        if (it2 == 0) {
                            z = false;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (z) {
                return value.charAt(0) == '-' ? Long.MIN_VALUE : Long.MAX_VALUE;
            }
        }
        return StringsKt.startsWith$default(value, "+", false, 2, (Object) null) ? Long.parseLong(StringsKt.drop(value, 1)) : Long.parseLong(value);
    }

    private static final String substringWhile(String $this$substringWhile, int startIndex, Function1<? super Character, Boolean> function1) {
        int i$iv = startIndex;
        while (i$iv < $this$substringWhile.length() && function1.invoke(Character.valueOf($this$substringWhile.charAt(i$iv))).booleanValue()) {
            i$iv++;
        }
        Intrinsics.checkNotNull($this$substringWhile, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = $this$substringWhile.substring(startIndex, i$iv);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    private static final int skipWhile(String $this$skipWhile, int startIndex, Function1<? super Character, Boolean> function1) {
        int i = startIndex;
        while (i < $this$skipWhile.length() && function1.invoke(Character.valueOf($this$skipWhile.charAt(i))).booleanValue()) {
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long nanosToMillis(long nanos) {
        return nanos / ((long) NANOS_IN_MILLIS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long millisToNanos(long millis) {
        return ((long) NANOS_IN_MILLIS) * millis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfNanos(long normalNanos) {
        return Duration.m8714constructorimpl(normalNanos << 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfMillis(long normalMillis) {
        return Duration.m8714constructorimpl((normalMillis << 1) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOf(long normalValue, int unitDiscriminator) {
        return Duration.m8714constructorimpl((normalValue << 1) + ((long) unitDiscriminator));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfNanosNormalized(long nanos) {
        boolean z = false;
        if (-4611686018426999999L <= nanos && nanos < 4611686018427000000L) {
            z = true;
        }
        if (z) {
            return durationOfNanos(nanos);
        }
        return durationOfMillis(nanosToMillis(nanos));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfMillisNormalized(long millis) {
        boolean z = false;
        if (-4611686018426L <= millis && millis < 4611686018427L) {
            z = true;
        }
        if (z) {
            return durationOfNanos(millisToNanos(millis));
        }
        return durationOfMillis(RangesKt.coerceIn(millis, -4611686018427387903L, MAX_MILLIS));
    }
}
