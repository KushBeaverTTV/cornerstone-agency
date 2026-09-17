package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.presentation.components.ds.AuroraDsKt;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import ai.nous.aurora.presentation.theme.AuroraSpacing;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MoaEditorScreen.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class MoaEditorScreenKt$MoaEditorScreen$5$3$1$12 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ boolean $bridgeOnline;
    final /* synthetic */ MutableState<Boolean> $dirty$delegate;
    final /* synthetic */ Function1<Boolean, Unit> $onDirtyChange;
    final /* synthetic */ MutableState<Boolean> $saveTraces$delegate;
    final /* synthetic */ boolean $saving;
    final /* synthetic */ MutableState<String> $traceDir$delegate;

    /* JADX WARN: Multi-variable type inference failed */
    MoaEditorScreenKt$MoaEditorScreen$5$3$1$12(boolean z, boolean z2, MutableState<Boolean> mutableState, Function1<? super Boolean, Unit> function1, MutableState<String> mutableState2, MutableState<Boolean> mutableState3) {
        this.$bridgeOnline = z;
        this.$saving = z2;
        this.$saveTraces$delegate = mutableState;
        this.$onDirtyChange = function1;
        this.$traceDir$delegate = mutableState2;
        this.$dirty$delegate = mutableState3;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
        Object value$iv;
        Object value$iv2;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C627@29386L115,622@29072L452,632@29571L14,632@29545L100,635@29747L10,633@29666L353,642@30142L113,640@30040L482:MoaEditorScreen.kt#syfnu6");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1846276405, $changed, -1, "ai.nous.aurora.presentation.settings.MoaEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MoaEditorScreen.kt:622)");
        }
        boolean zMoaEditorScreen$lambda$17 = MoaEditorScreenKt.MoaEditorScreen$lambda$17(this.$saveTraces$delegate);
        boolean z = this.$bridgeOnline && !this.$saving;
        $composer.startReplaceGroup(482533899);
        ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
        boolean invalid$iv = $composer.changed(this.$saveTraces$delegate) | $composer.changed(this.$onDirtyChange);
        final MutableState<Boolean> mutableState = this.$saveTraces$delegate;
        final Function1<Boolean, Unit> function1 = this.$onDirtyChange;
        final MutableState<Boolean> mutableState2 = this.$dirty$delegate;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$12$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoaEditorScreenKt$MoaEditorScreen$5$3$1$12.invoke$lambda$1$lambda$0(mutableState, function1, mutableState2, ((Boolean) obj).booleanValue());
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        $composer.endReplaceGroup();
        AuroraDsKt.AuroraSwitchRow("Save MoA traces", zMoaEditorScreen$lambda$17, (Function1) value$iv, "Applies to every preset · JSONL reference + aggregator turns", z, $composer, 3078, 0);
        DividerKt.m2446HorizontalDivider9IZ8Weo(PaddingKt.m1038paddingqDBjuR0$default(Modifier.INSTANCE, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), 0.0f, 0.0f, 0.0f, 14, null), 0.0f, AuroraColorSchemeKt.getAuroraHairline($composer, 0), $composer, 6, 2);
        TextKt.m3067Text4IGK_g("Trace directory", PaddingKt.m1037paddingqDBjuR0(Modifier.INSTANCE, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), Dp.m7016constructorimpl(10), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), Dp.m7016constructorimpl(6)), AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131024);
        String strMoaEditorScreen$lambda$20 = MoaEditorScreenKt.MoaEditorScreen$lambda$20(this.$traceDir$delegate);
        boolean z2 = this.$bridgeOnline && !this.$saving;
        Modifier modifierM1038paddingqDBjuR0$default = PaddingKt.m1038paddingqDBjuR0$default(Modifier.INSTANCE, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), 0.0f, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), AuroraSpacing.INSTANCE.m240getSmD9Ej5fM(), 2, null);
        $composer.startReplaceGroup(482558089);
        ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(this.$traceDir$delegate) | $composer.changed(this.$onDirtyChange);
        final MutableState<String> mutableState3 = this.$traceDir$delegate;
        final Function1<Boolean, Unit> function2 = this.$onDirtyChange;
        final MutableState<Boolean> mutableState4 = this.$dirty$delegate;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$12$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoaEditorScreenKt$MoaEditorScreen$5$3$1$12.invoke$lambda$3$lambda$2(mutableState3, function2, mutableState4, (String) obj);
                }
            };
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        $composer.endReplaceGroup();
        AuroraDsKt.AuroraTextFieldRow(strMoaEditorScreen$lambda$20, (Function1) value$iv2, modifierM1038paddingqDBjuR0$default, "Unset", z2, false, $composer, 3456, 32);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$1$lambda$0(MutableState $saveTraces$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, boolean it) {
        MoaEditorScreenKt.MoaEditorScreen$lambda$18($saveTraces$delegate, it);
        MoaEditorScreenKt.MoaEditorScreen$markDirty($onDirtyChange, $dirty$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$3$lambda$2(MutableState $traceDir$delegate, Function1 $onDirtyChange, MutableState $dirty$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $traceDir$delegate.setValue(it);
        MoaEditorScreenKt.MoaEditorScreen$markDirty($onDirtyChange, $dirty$delegate, true);
        return Unit.INSTANCE;
    }
}
