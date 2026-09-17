package ai.nous.aurora.presentation.assist;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AssistPresenceOrb.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aA\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"AssistPresenceOrb", "", "phase", "", "style", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "size", "Landroidx/compose/ui/unit/Dp;", "AssistPresenceOrb-FJfuzF0", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;FLandroidx/compose/runtime/Composer;II)V", "app_debug"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AssistPresenceOrbKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AssistPresenceOrb_FJfuzF0$lambda$0(String str, String str2, Function0 function0, Modifier modifier, float f, int i, int i2, Composer composer, int i3) {
        m102AssistPresenceOrbFJfuzF0(str, str2, function0, modifier, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:92:0x012a  */
    /* JADX INFO: renamed from: AssistPresenceOrb-FJfuzF0, reason: not valid java name */
    public static final void m102AssistPresenceOrbFJfuzF0(final String phase, final String style, final Function0<Unit> onClick, Modifier modifier, float size, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float size2;
        String str;
        Modifier modifier3;
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer2 = $composer.startRestartGroup(-1597143925);
        ComposerKt.sourceInformation($composer2, "C(AssistPresenceOrb)P(2,4,1!,3:c#ui.unit.Dp)19@500L477:AssistPresenceOrb.kt#rc2ijs");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(phase) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(style) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(onClick) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty |= 24576;
            size2 = size;
        } else if (($changed & 24576) == 0) {
            size2 = size;
            $dirty |= $composer2.changed(size2) ? 16384 : 8192;
        } else {
            size2 = size;
        }
        if (($dirty & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            float size3 = i3 != 0 ? Dp.m7016constructorimpl(188) : size2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1597143925, $dirty, -1, "ai.nous.aurora.presentation.assist.AssistPresenceOrb (AssistPresenceOrb.kt:18)");
            }
            AssistOrbPhase assistOrbPhaseAssistOrbPhaseOf = AssistOrbKt.assistOrbPhaseOf(phase);
            switch (phase) {
                case "done":
                    str = "Done — tap to talk again";
                    break;
                case "error":
                    str = "Error — tap to retry";
                    break;
                case "need_mic":
                    str = "Microphone required";
                    break;
                case "recording":
                    str = "Listening — tap to send";
                    break;
                case "working":
                    str = "Working";
                    break;
                default:
                    str = "Tap to talk";
                    break;
            }
            AssistOrbKt.m85AssistOrbjIwJxvA(assistOrbPhaseAssistOrbPhaseOf, style, modifier4, size3, onClick, str, $composer2, ($dirty & 112) | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (57344 & ($dirty << 6)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            size2 = size3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final float f = size2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.assist.AssistPresenceOrbKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AssistPresenceOrbKt.AssistPresenceOrb_FJfuzF0$lambda$0(phase, style, onClick, modifier5, f, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
