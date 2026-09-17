package ai.nous.aurora.presentation.components.ds;

import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.TextUnitKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AuroraDs.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class AuroraDsKt$AuroraDropdownRow$1$2$2$3 implements Function3<ColumnScope, Composer, Integer, Unit> {
    final /* synthetic */ boolean $allowCustom;
    final /* synthetic */ String $blankLabel;
    final /* synthetic */ String $customLabel;
    final /* synthetic */ MutableState<Boolean> $customMode$delegate;
    final /* synthetic */ MutableState<Boolean> $expanded$delegate;
    final /* synthetic */ Function1<String, Unit> $onSelect;
    final /* synthetic */ List<String> $opts;
    final /* synthetic */ String $value;

    /* JADX WARN: Multi-variable type inference failed */
    AuroraDsKt$AuroraDropdownRow$1$2$2$3(List<String> list, String str, String str2, boolean z, String str3, Function1<? super String, Unit> function1, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2) {
        this.$opts = list;
        this.$blankLabel = str;
        this.$value = str2;
        this.$allowCustom = z;
        this.$customLabel = str3;
        this.$onSelect = function1;
        this.$expanded$delegate = mutableState;
        this.$customMode$delegate = mutableState2;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
        invoke(columnScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(ColumnScope DropdownMenu, Composer $composer, int $changed) {
        final MutableState<Boolean> mutableState;
        Intrinsics.checkNotNullParameter(DropdownMenu, "$this$DropdownMenu");
        ComposerKt.sourceInformation($composer, "C*565@20944L286,572@21270L418,564@20891L824:AuroraDs.kt#rimizs");
        if (($changed & 17) != 16 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(693504979, $changed, -1, "ai.nous.aurora.presentation.components.ds.AuroraDropdownRow.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AuroraDs.kt:559)");
            }
            Iterable $this$forEach$iv = this.$opts;
            String str = this.$blankLabel;
            String str2 = this.$value;
            boolean z = this.$allowCustom;
            String str3 = this.$customLabel;
            Function1<String, Unit> function1 = this.$onSelect;
            final MutableState<Boolean> mutableState2 = this.$expanded$delegate;
            MutableState<Boolean> mutableState3 = this.$customMode$delegate;
            for (Object element$iv : $this$forEach$iv) {
                final String opt = (String) element$iv;
                final boolean selected = Intrinsics.areEqual(opt, str) ? StringsKt.isBlank(str2) : Intrinsics.areEqual(opt, str2);
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-135187117, true, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$AuroraDropdownRow$1$2$2$3$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer2, int $changed2) {
                        long auroraText;
                        ComposerKt.sourceInformation($composer2, "C566@20978L222:AuroraDs.kt#rimizs");
                        if (($changed2 & 3) != 2 || !$composer2.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-135187117, $changed2, -1, "ai.nous.aurora.presentation.components.ds.AuroraDropdownRow.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AuroraDs.kt:566)");
                            }
                            String str4 = opt;
                            if (selected) {
                                $composer2.startReplaceGroup(139712121);
                                ComposerKt.sourceInformation($composer2, "568@21083L12");
                                auroraText = AuroraColorSchemeKt.getAuroraAccent($composer2, 0);
                            } else {
                                $composer2.startReplaceGroup(139712695);
                                ComposerKt.sourceInformation($composer2, "568@21101L10");
                                auroraText = AuroraColorSchemeKt.getAuroraText($composer2, 0);
                            }
                            $composer2.endReplaceGroup();
                            TextKt.m3067Text4IGK_g(str4, (Modifier) null, auroraText, TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, 3072, 0, 131058);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer2.skipToGroupEnd();
                    }
                }, $composer, 54);
                $composer.startReplaceGroup(-1238019472);
                ComposerKt.sourceInformation($composer, "CC(remember):AuroraDs.kt#9igjgp");
                boolean invalid$iv = $composer.changed(z) | $composer.changed(opt) | $composer.changed(str3) | $composer.changed(function1) | $composer.changed(str);
                Object value$iv = $composer.rememberedValue();
                if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                    final boolean z2 = z;
                    final String str4 = str3;
                    final Function1<String, Unit> function2 = function1;
                    mutableState = mutableState3;
                    final String str5 = str;
                    value$iv = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$AuroraDropdownRow$1$2$2$3$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AuroraDsKt$AuroraDropdownRow$1$2$2$3.invoke$lambda$2$lambda$1$lambda$0(z2, opt, str4, function2, str5, mutableState2, mutableState);
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    mutableState = mutableState3;
                }
                $composer.endReplaceGroup();
                AndroidMenu_androidKt.DropdownMenuItem(composableLambdaRememberComposableLambda, (Function0) value$iv, null, null, null, false, null, null, null, $composer, 6, 508);
                str3 = str3;
                str = str;
                z = z;
                str2 = str2;
                mutableState3 = mutableState;
                mutableState2 = mutableState2;
                function1 = function1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        $composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$2$lambda$1$lambda$0(boolean $allowCustom, String $opt, String $customLabel, Function1 $onSelect, String $blankLabel, MutableState $expanded$delegate, MutableState $customMode$delegate) {
        AuroraDsKt.AuroraDropdownRow$lambda$26($expanded$delegate, false);
        if (!$allowCustom || !Intrinsics.areEqual($opt, $customLabel)) {
            AuroraDsKt.AuroraDropdownRow$lambda$29($customMode$delegate, false);
            $onSelect.invoke(Intrinsics.areEqual($opt, $blankLabel) ? "" : $opt);
        } else {
            AuroraDsKt.AuroraDropdownRow$lambda$29($customMode$delegate, true);
        }
        return Unit.INSTANCE;
    }
}
