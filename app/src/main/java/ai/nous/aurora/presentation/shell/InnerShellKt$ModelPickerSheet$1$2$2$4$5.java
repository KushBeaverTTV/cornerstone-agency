package ai.nous.aurora.presentation.shell;

import ai.nous.aurora.MainViewModel;
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
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InnerShell.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class InnerShellKt$ModelPickerSheet$1$2$2$4$5 implements Function3<ColumnScope, Composer, Integer, Unit> {
    final /* synthetic */ MutableState<String> $activeProvider$delegate;
    final /* synthetic */ List<MainViewModel.ModelChoice> $deduped;
    final /* synthetic */ MutableState<Boolean> $providerMenuOpen$delegate;
    final /* synthetic */ List<String> $providerOrder;

    InnerShellKt$ModelPickerSheet$1$2$2$4$5(List<String> list, List<MainViewModel.ModelChoice> list2, MutableState<String> mutableState, MutableState<Boolean> mutableState2) {
        this.$providerOrder = list;
        this.$deduped = list2;
        this.$activeProvider$delegate = mutableState;
        this.$providerMenuOpen$delegate = mutableState2;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
        invoke(columnScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(ColumnScope DropdownMenu, Composer $composer, int $changed) {
        Object element$iv;
        String providerName;
        Object value$iv;
        MainViewModel.ModelChoice it;
        Intrinsics.checkNotNullParameter(DropdownMenu, "$this$DropdownMenu");
        ComposerKt.sourceInformation($composer, "C*1661@75492L19,1662@75559L170,1660@75431L333:InnerShell.kt#m1syn");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1146712311, $changed, -1, "ai.nous.aurora.presentation.shell.ModelPickerSheet.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (InnerShell.kt:1655)");
        }
        Iterable $this$forEach$iv = this.$providerOrder;
        Iterable iterable = this.$deduped;
        final MutableState<String> mutableState = this.$activeProvider$delegate;
        final MutableState<Boolean> mutableState2 = this.$providerMenuOpen$delegate;
        for (Object element$iv2 : $this$forEach$iv) {
            final String provider = (String) element$iv2;
            Iterable $this$firstOrNull$iv = iterable;
            Iterator it2 = $this$firstOrNull$iv.iterator();
            do {
                if (!it2.hasNext()) {
                    element$iv = null;
                    break;
                } else {
                    element$iv = it2.next();
                    it = (MainViewModel.ModelChoice) element$iv;
                }
            } while (!Intrinsics.areEqual(it.getProvider(), provider));
            MainViewModel.ModelChoice modelChoice = (MainViewModel.ModelChoice) element$iv;
            if (modelChoice == null || (providerName = modelChoice.getProviderName()) == null) {
                providerName = provider;
            }
            final String fullLabel = providerName;
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-308054647, true, new Function2<Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$2$4$5$1$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "C1661@75494L15:InnerShell.kt#m1syn");
                    if (($changed2 & 3) == 2 && $composer2.getSkipping()) {
                        $composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-308054647, $changed2, -1, "ai.nous.aurora.presentation.shell.ModelPickerSheet.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (InnerShell.kt:1661)");
                    }
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(fullLabel, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, 0, 0, 131070);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer, 54);
            $composer.startReplaceGroup(1433617817);
            ComposerKt.sourceInformation($composer, "CC(remember):InnerShell.kt#9igjgp");
            boolean invalid$iv = $composer.changed(mutableState) | $composer.changed(provider);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.Companion.getEmpty()) {
                value$iv = new Function0() { // from class: ai.nous.aurora.presentation.shell.InnerShellKt$ModelPickerSheet$1$2$2$4$5$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return InnerShellKt$ModelPickerSheet$1$2$2$4$5.invoke$lambda$3$lambda$2$lambda$1(provider, mutableState, mutableState2);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer.endReplaceGroup();
            AndroidMenu_androidKt.DropdownMenuItem((Function2) composableLambdaRememberComposableLambda, (Function0) value$iv, null, null, null, false, null, null, null, $composer, 6, 508);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$3$lambda$2$lambda$1(String $provider, MutableState $activeProvider$delegate, MutableState $providerMenuOpen$delegate) {
        $activeProvider$delegate.setValue($provider);
        InnerShellKt.ModelPickerSheet$lambda$88($providerMenuOpen$delegate, false);
        return Unit.INSTANCE;
    }
}
