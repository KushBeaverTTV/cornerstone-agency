package ai.nous.aurora.domain.model;

import android.content.res.Configuration;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowLayoutInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RazrLayoutClassifier.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¨\u0006\n"}, d2 = {"Lai/nous/aurora/domain/model/RazrLayoutClassifier;", "", "<init>", "()V", "classify", "Lai/nous/aurora/domain/model/AuroraDisplayMode;", "configuration", "Landroid/content/res/Configuration;", "layoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RazrLayoutClassifier {
    public static final int $stable = 0;
    public static final RazrLayoutClassifier INSTANCE = new RazrLayoutClassifier();

    private RazrLayoutClassifier() {
    }

    public static /* synthetic */ AuroraDisplayMode classify$default(RazrLayoutClassifier razrLayoutClassifier, Configuration configuration, WindowLayoutInfo windowLayoutInfo, int i, Object obj) {
        if ((i & 2) != 0) {
            windowLayoutInfo = null;
        }
        return razrLayoutClassifier.classify(configuration, windowLayoutInfo);
    }

    public final AuroraDisplayMode classify(Configuration configuration, WindowLayoutInfo layoutInfo) {
        FoldingFeature folding;
        Iterable displayFeatures;
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        if (layoutInfo == null || (displayFeatures = layoutInfo.getDisplayFeatures()) == null) {
            folding = null;
        } else {
            Iterable $this$filterIsInstance$iv = displayFeatures;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filterIsInstance$iv) {
                if (element$iv$iv instanceof FoldingFeature) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            folding = (FoldingFeature) CollectionsKt.firstOrNull((List) destination$iv$iv);
        }
        int shortest = Math.min(configuration.screenWidthDp, configuration.screenHeightDp);
        int longest = Math.max(configuration.screenWidthDp, configuration.screenHeightDp);
        if (folding != null && Intrinsics.areEqual(folding.getState(), FoldingFeature.State.HALF_OPENED) && shortest <= 280) {
            return AuroraDisplayMode.RAZR_COVER;
        }
        if (shortest <= 280 || longest <= 400) {
            return AuroraDisplayMode.RAZR_COVER;
        }
        return shortest < 600 ? AuroraDisplayMode.COMPACT : AuroraDisplayMode.FULL;
    }
}
