package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.domain.model.MoaEditorPresentationPolicy;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MoaEditorScreen.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class MoaEditorScreenKt$MoaEditorScreen$5$3$1$9 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ MutableState<Boolean> $runBehaviorExpanded$delegate;

    MoaEditorScreenKt$MoaEditorScreen$5$3$1$9(MutableState<Boolean> mutableState) {
        this.$runBehaviorExpanded$delegate = mutableState;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
        Object value$iv;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C550@24849L46,546@24501L450:MoaEditorScreen.kt#syfnu6");
        if (($changed & 17) != 16 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1877285787, $changed, -1, "ai.nous.aurora.presentation.settings.MoaEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MoaEditorScreen.kt:546)");
            }
            String strSectionLabel = MoaEditorPresentationPolicy.INSTANCE.sectionLabel(MoaEditorPresentationPolicy.Section.RunBehavior);
            String strSectionSubtitle = MoaEditorPresentationPolicy.INSTANCE.sectionSubtitle(MoaEditorPresentationPolicy.Section.RunBehavior);
            Boolean boolValueOf = Boolean.valueOf(MoaEditorScreenKt.MoaEditorScreen$lambda$48(this.$runBehaviorExpanded$delegate));
            $composer.startReplaceGroup(482388646);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            final MutableState<Boolean> mutableState = this.$runBehaviorExpanded$delegate;
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.Companion.getEmpty()) {
                value$iv = new Function0() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$9$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MoaEditorScreenKt$MoaEditorScreen$5$3$1$9.invoke$lambda$1$lambda$0(mutableState);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer.endReplaceGroup();
            MoaEditorScreenKt.SectionHeader(strSectionLabel, strSectionSubtitle, boolValueOf, (Function0) value$iv, true, $composer, 27648, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        $composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$1$lambda$0(MutableState $runBehaviorExpanded$delegate) {
        MoaEditorScreenKt.MoaEditorScreen$lambda$49($runBehaviorExpanded$delegate, !MoaEditorScreenKt.MoaEditorScreen$lambda$48($runBehaviorExpanded$delegate));
        return Unit.INSTANCE;
    }
}
