package ai.nous.aurora.domain.model;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CoverOpenPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u0005J\"\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0018\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000f0\u000e0\r¨\u0006\u0011"}, d2 = {"Lai/nous/aurora/domain/model/CoverOpenPolicy;", "", "<init>", "()V", "displayAfterOpenIntent", "Lai/nous/aurora/domain/model/CoverOpenPolicy$DisplayMode;", "current", "openRequested", "", "shouldDeferFullChromeUntilUnfold", "pickLatestSessionId", "", "sessions", "", "Lkotlin/Pair;", "", "DisplayMode", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CoverOpenPolicy {
    public static final int $stable = 0;
    public static final CoverOpenPolicy INSTANCE = new CoverOpenPolicy();

    /* JADX INFO: compiled from: CoverOpenPolicy.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DisplayMode.values().length];
            try {
                iArr[DisplayMode.Cover.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[DisplayMode.Compact.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[DisplayMode.Full.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: compiled from: CoverOpenPolicy.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/domain/model/CoverOpenPolicy$DisplayMode;", "", "<init>", "(Ljava/lang/String;I)V", "Cover", "Compact", "Full", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum DisplayMode {
        Cover,
        Compact,
        Full;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<DisplayMode> getEntries() {
            return $ENTRIES;
        }
    }

    private CoverOpenPolicy() {
    }

    public final DisplayMode displayAfterOpenIntent(DisplayMode current, boolean openRequested) {
        Intrinsics.checkNotNullParameter(current, "current");
        if (!openRequested) {
            return current;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[current.ordinal()]) {
            case 1:
                return DisplayMode.Cover;
            case 2:
            case 3:
                return DisplayMode.Full;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean shouldDeferFullChromeUntilUnfold(DisplayMode current) {
        Intrinsics.checkNotNullParameter(current, "current");
        return current == DisplayMode.Cover;
    }

    public final String pickLatestSessionId(List<Pair<String, Long>> sessions) {
        Object maxElem$iv;
        Intrinsics.checkNotNullParameter(sessions, "sessions");
        List<Pair<String, Long>> $this$maxByOrNull$iv = sessions;
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            maxElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                Pair it = (Pair) maxElem$iv;
                long maxValue$iv = ((Number) it.getSecond()).longValue();
                do {
                    Object e$iv = iterator$iv.next();
                    Pair it2 = (Pair) e$iv;
                    long v$iv = ((Number) it2.getSecond()).longValue();
                    if (maxValue$iv < v$iv) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            maxElem$iv = null;
        }
        Pair pair = (Pair) maxElem$iv;
        if (pair != null) {
            return (String) pair.getFirst();
        }
        return null;
    }
}
