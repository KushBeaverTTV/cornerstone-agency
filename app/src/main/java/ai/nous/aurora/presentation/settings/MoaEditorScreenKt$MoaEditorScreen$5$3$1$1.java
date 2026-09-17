package ai.nous.aurora.presentation.settings;

import ai.nous.aurora.data.remote.MoaPresetDto;
import ai.nous.aurora.domain.model.MoaEditorPresentationPolicy;
import ai.nous.aurora.presentation.components.ds.AuroraDsKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MoaEditorScreen.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class MoaEditorScreenKt$MoaEditorScreen$5$3$1$1 implements Function3<LazyItemScope, Composer, Integer, Unit> {
    final /* synthetic */ MutableState<String> $activePreset$delegate;
    final /* synthetic */ boolean $bridgeOnline;
    final /* synthetic */ MutableState<String> $defaultPreset$delegate;
    final /* synthetic */ MutableState<Map<String, MoaPresetDto>> $drafts$delegate;
    final /* synthetic */ boolean $saving;
    final /* synthetic */ MutableState<String> $selectedName$delegate;

    MoaEditorScreenKt$MoaEditorScreen$5$3$1$1(boolean z, boolean z2, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<Map<String, MoaPresetDto>> mutableState4) {
        this.$bridgeOnline = z;
        this.$saving = z2;
        this.$selectedName$delegate = mutableState;
        this.$defaultPreset$delegate = mutableState2;
        this.$activePreset$delegate = mutableState3;
        this.$drafts$delegate = mutableState4;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
        invoke(lazyItemScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
        Object value$iv;
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C298@12401L75,292@12021L474:MoaEditorScreen.kt#syfnu6");
        if (($changed & 17) != 16 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(532939032, $changed, -1, "ai.nous.aurora.presentation.settings.MoaEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MoaEditorScreen.kt:292)");
            }
            String strMoaEditorScreen$lambda$7 = MoaEditorScreenKt.MoaEditorScreen$lambda$7(this.$selectedName$delegate);
            String strPresetSubtitle = MoaEditorPresentationPolicy.INSTANCE.presetSubtitle(MoaEditorScreenKt.MoaEditorScreen$lambda$11(this.$defaultPreset$delegate), MoaEditorScreenKt.MoaEditorScreen$lambda$14(this.$activePreset$delegate), MoaEditorScreenKt.MoaEditorScreen$lambda$7(this.$selectedName$delegate));
            List listSorted = CollectionsKt.sorted(CollectionsKt.toList(MoaEditorScreenKt.MoaEditorScreen$lambda$24(this.$drafts$delegate).keySet()));
            boolean z = this.$bridgeOnline && !this.$saving;
            $composer.startReplaceGroup(481990339);
            ComposerKt.sourceInformation($composer, "CC(remember):MoaEditorScreen.kt#9igjgp");
            boolean invalid$iv = $composer.changed(this.$selectedName$delegate);
            final MutableState<String> mutableState = this.$selectedName$delegate;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.Companion.getEmpty()) {
                value$iv = new Function1() { // from class: ai.nous.aurora.presentation.settings.MoaEditorScreenKt$MoaEditorScreen$5$3$1$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MoaEditorScreenKt$MoaEditorScreen$5$3$1$1.invoke$lambda$1$lambda$0(mutableState, (String) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer.endReplaceGroup();
            AuroraDsKt.AuroraDropdownRow("Selected configuration", strMoaEditorScreen$lambda$7, listSorted, (Function1) value$iv, strPresetSubtitle, z, false, null, null, $composer, 6, 448);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        $composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$1$lambda$0(MutableState $selectedName$delegate, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        $selectedName$delegate.setValue(name);
        return Unit.INSTANCE;
    }
}
