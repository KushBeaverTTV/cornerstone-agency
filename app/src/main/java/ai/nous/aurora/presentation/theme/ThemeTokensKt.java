package ai.nous.aurora.presentation.theme;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ThemeTokens.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000e\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000b\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u000f"}, d2 = {"ConstellationThemeTokens", "Lai/nous/aurora/presentation/theme/ThemeTokens;", "getConstellationThemeTokens", "()Lai/nous/aurora/presentation/theme/ThemeTokens;", "EmberThemeTokens", "getEmberThemeTokens", "LocalThemeTokens", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalThemeTokens", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalAuroraSkin", "Lai/nous/aurora/presentation/theme/AuroraSkin;", "getLocalAuroraSkin", "themeTokensFor", "skin", "app_debug"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ThemeTokensKt {
    private static final ThemeTokens ConstellationThemeTokens = new ThemeTokens(ColorKt.Color(4278453260L), ColorKt.Color(4278716438L), ColorKt.Color(4278650642L), ColorKt.Color(4278519056L), ColorKt.Color(4279374378L), ColorKt.Color(4279769142L), ColorKt.Color(4280558664L), ColorKt.Color(4281084000L), ColorKt.Color(4282006130L), ColorKt.Color(4293717759L), ColorKt.Color(4292928506L), ColorKt.Color(4291876592L), ColorKt.Color(4282731118L), ColorKt.Color(4288384255L), ColorKt.Color(4284239288L), ColorKt.Color(4291081727L), ColorKt.Color(868529663), ColorKt.Color(4286278911L), ColorKt.Color(4289771744L), ColorKt.Color(4286278911L), ColorKt.Color(863726847), ColorKt.Color(4283056272L), ColorKt.Color(4284277222L), ColorKt.Color(4278716436L), ColorKt.Color(4284277222L), ColorKt.Color(4280982176L), ColorKt.Color(861725158), ColorKt.Color(4281611370L), ColorKt.Color(4283058824L), ColorKt.Color(4294929290L), ColorKt.Color(4294953047L), ColorKt.Color(4284284848L), ColorKt.Color(4285249791L), ColorKt.Color(4278321672L), ColorKt.Color(4279896629L), ColorKt.Color(4287323382L), ColorKt.Color(4294112511L), ColorKt.Color(4279505966L), ColorKt.Color(4284108960L), ColorKt.Color(4288595455L), ColorKt.Color(4291337215L), ColorKt.Color(4284277222L), ColorKt.Color(571083296), true, null);
    private static final ThemeTokens EmberThemeTokens = new ThemeTokens(ColorKt.Color(4278848010L), ColorKt.Color(4278913546L), ColorKt.Color(4278913289L), ColorKt.Color(4278848010L), ColorKt.Color(4279702030L), ColorKt.Color(4280293393L), ColorKt.Color(4280950550L), ColorKt.Color(4281541912L), ColorKt.Color(4283054376L), ColorKt.Color(4294241245L), ColorKt.Color(4290357912L), ColorKt.Color(4287923829L), ColorKt.Color(4284107838L), ColorKt.Color(4291339882L), ColorKt.Color(4287262533L), ColorKt.Color(4294766090L), ColorKt.Color(868787818), ColorKt.Color(4288314186L), ColorKt.Color(4290357912L), ColorKt.Color(4294766090L), ColorKt.Color(868787818), ColorKt.Color(4287262533L), ColorKt.Color(4294766090L), ColorKt.Color(4278848010L), ColorKt.Color(4291339882L), ColorKt.Color(4286211136L), ColorKt.Color(868787818), ColorKt.Color(4283054376L), ColorKt.Color(4285026614L), ColorKt.Color(4291983984L), ColorKt.Color(4291136082L), ColorKt.Color(4287014527L), ColorKt.Color(4286159273L), ColorKt.Color(4279241994L), ColorKt.Color(4279702030L), ColorKt.Color(4279702030L), ColorKt.Color(4294241245L), ColorKt.Color(4280293393L), ColorKt.Color(4283054376L), ColorKt.Color(4292722052L), ColorKt.Color(4292984954L), ColorKt.Color(4289235279L), ColorKt.Color(4278979080L), false, null);
    private static final ProvidableCompositionLocal<ThemeTokens> LocalThemeTokens = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: ai.nous.aurora.presentation.theme.ThemeTokensKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return ThemeTokensKt.EmberThemeTokens;
        }
    });
    private static final ProvidableCompositionLocal<AuroraSkin> LocalAuroraSkin = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: ai.nous.aurora.presentation.theme.ThemeTokensKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AuroraSkin.Ember;
        }
    });

    /* JADX INFO: compiled from: ThemeTokens.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AuroraSkin.values().length];
            try {
                iArr[AuroraSkin.Ember.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AuroraSkin.Constellation.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final ThemeTokens getConstellationThemeTokens() {
        return ConstellationThemeTokens;
    }

    public static final ThemeTokens getEmberThemeTokens() {
        return EmberThemeTokens;
    }

    public static final ProvidableCompositionLocal<ThemeTokens> getLocalThemeTokens() {
        return LocalThemeTokens;
    }

    public static final ProvidableCompositionLocal<AuroraSkin> getLocalAuroraSkin() {
        return LocalAuroraSkin;
    }

    public static final ThemeTokens themeTokensFor(AuroraSkin skin) {
        Intrinsics.checkNotNullParameter(skin, "skin");
        switch (WhenMappings.$EnumSwitchMapping$0[skin.ordinal()]) {
            case 1:
                return EmberThemeTokens;
            case 2:
                return ConstellationThemeTokens;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
