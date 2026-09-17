package ai.nous.aurora.presentation.assist;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.core.location.LocationRequestCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AssistOrb.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001\u001aQ\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001aE\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u000eH\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001aC\u0010\u0017\u001a\u00020\b*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H\u0002¢\u0006\u0004\b#\u0010$\u001a3\u0010%\u001a\u00020\b*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u001cH\u0002¢\u0006\u0004\b(\u0010)\u001a;\u0010*\u001a\u00020\b*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u001cH\u0002¢\u0006\u0004\b,\u0010-\u001aY\u0010.\u001a\u00020\b*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020\u001c2\f\u00100\u001a\b\u0012\u0004\u0012\u000202012\u0006\u0010'\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c2\u0006\u00103\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00104\u001a\u00020\u001fH\u0002¢\u0006\u0004\b5\u00106¨\u00067²\u0006\n\u0010+\u001a\u00020\u001cX\u008a\u0084\u0002²\u0006\n\u00108\u001a\u00020\u001cX\u008a\u0084\u0002²\u0006\n\u00109\u001a\u00020\u001cX\u008a\u0084\u0002"}, d2 = {"assistOrbPhaseOf", "Lai/nous/aurora/presentation/assist/AssistOrbPhase;", "phase", "", "assistOrbPalette", "Lai/nous/aurora/presentation/assist/AssistOrbPalette;", "style", "AssistOrb", "", "modifier", "Landroidx/compose/ui/Modifier;", "size", "Landroidx/compose/ui/unit/Dp;", "onClick", "Lkotlin/Function0;", "contentDescription", "AssistOrb-jIwJxvA", "(Lai/nous/aurora/presentation/assist/AssistOrbPhase;Ljava/lang/String;Landroidx/compose/ui/Modifier;FLkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)V", "AssistOrbPreview", "selected", "", "AssistOrbPreview-hGBTI10", "(Ljava/lang/String;ZLandroidx/compose/ui/Modifier;FLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "drawPulseRings", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "center", "Landroidx/compose/ui/geometry/Offset;", "baseRadius", "", "maxRadius", "color", "Landroidx/compose/ui/graphics/Color;", "t", "rings", "", "drawPulseRings-xjpq4WA", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFFJFI)V", "drawWorkingSweeps", "radius", "spinDeg", "drawWorkingSweeps-dmr55fk", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFJF)V", "drawListeningArcs", "breath", "drawListeningArcs-IfIb9nM", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFJFF)V", "drawDust", "bodyR", "dust", "", "Lai/nous/aurora/presentation/assist/DustSpec;", "energy", "secondary", "drawDust-7fTDFas", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFLjava/util/List;FFFJJ)V", "app_debug", "spin", "pulse"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AssistOrbKt {

    /* JADX INFO: compiled from: AssistOrb.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AssistOrbPhase.values().length];
            try {
                iArr[AssistOrbPhase.Listening.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AssistOrbPhase.Working.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AssistOrbPhase.Done.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AssistOrbPhase.Error.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[AssistOrbPhase.NeedMic.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[AssistOrbPhase.Idle.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AssistOrbPreview_hGBTI10$lambda$17(String str, boolean z, Modifier modifier, float f, Function0 function0, int i, int i2, Composer composer, int i3) {
        m86AssistOrbPreviewhGBTI10(str, z, modifier, f, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AssistOrb_jIwJxvA$lambda$16(AssistOrbPhase assistOrbPhase, String str, Modifier modifier, float f, Function0 function0, String str2, int i, int i2, Composer composer, int i3) {
        m85AssistOrbjIwJxvA(assistOrbPhase, str, modifier, f, function0, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final AssistOrbPhase assistOrbPhaseOf(String phase) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        switch (phase.hashCode()) {
            case 3089282:
                if (phase.equals("done")) {
                    return AssistOrbPhase.Done;
                }
                break;
            case 96784904:
                if (phase.equals("error")) {
                    return AssistOrbPhase.Error;
                }
                break;
            case 866001694:
                if (phase.equals("need_mic")) {
                    return AssistOrbPhase.NeedMic;
                }
                break;
            case 993558001:
                if (phase.equals("recording")) {
                    return AssistOrbPhase.Listening;
                }
                break;
            case 1525164849:
                if (phase.equals("working")) {
                    return AssistOrbPhase.Working;
                }
                break;
        }
        return AssistOrbPhase.Idle;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x0052  */
    /* JADX WARN: Code duplicated, block: B:41:0x0199  */
    public static final AssistOrbPalette assistOrbPalette(String style, AssistOrbPhase phase) {
        long base;
        long work;
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(phase, "phase");
        switch (style.hashCode()) {
            case -1406381216:
                if (!style.equals("aurora")) {
                    base = ColorKt.Color(4293441645L);
                } else {
                    base = ColorKt.Color(4291077492L);
                }
                break;
            case -1355154317:
                if (!style.equals("cobalt")) {
                    base = ColorKt.Color(4293441645L);
                } else {
                    base = ColorKt.Color(4285442303L);
                }
                break;
            case -902467928:
                if (!style.equals("signal")) {
                    base = ColorKt.Color(4293441645L);
                } else {
                    base = ColorKt.Color(4284336805L);
                }
                break;
            default:
                base = ColorKt.Color(4293441645L);
                break;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[phase.ordinal()]) {
            case 1:
                long j = base;
                return new AssistOrbPalette(base, Color.m4532copywmQWz5c(j, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j) : 0.55f, (14 & 2) != 0 ? Color.m4540getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j) : 0.0f), ColorKt.Color(4294963408L), Color.m4532copywmQWz5c(j, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j) : 0.35f, (14 & 2) != 0 ? Color.m4540getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j) : 0.0f), null);
            case 2:
                switch (style) {
                    case "aurora":
                        work = ColorKt.Color(4292130966L);
                        break;
                    case "cobalt":
                        work = ColorKt.Color(4287545599L);
                        break;
                    case "signal":
                        work = ColorKt.Color(4286239928L);
                        break;
                    default:
                        work = ColorKt.Color(4293970040L);
                        break;
                }
                long j2 = work;
                return new AssistOrbPalette(work, Color.m4532copywmQWz5c(work, (14 & 1) != 0 ? Color.m4536getAlphaimpl(work) : 0.5f, (14 & 2) != 0 ? Color.m4540getRedimpl(work) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(work) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(work) : 0.0f), ColorKt.Color(4293456127L), Color.m4532copywmQWz5c(j2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j2) : 0.3f, (14 & 2) != 0 ? Color.m4540getRedimpl(j2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j2) : 0.0f), null);
            case 3:
                long jColor = ColorKt.Color(4284336805L);
                long jColor2 = ColorKt.Color(4284336805L);
                long jM4532copywmQWz5c = Color.m4532copywmQWz5c(jColor2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jColor2) : 0.45f, (14 & 2) != 0 ? Color.m4540getRedimpl(jColor2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jColor2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jColor2) : 0.0f);
                long jColor3 = ColorKt.Color(4292411376L);
                long jColor4 = ColorKt.Color(4280970069L);
                return new AssistOrbPalette(jColor, jM4532copywmQWz5c, jColor3, Color.m4532copywmQWz5c(jColor4, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jColor4) : 0.4f, (14 & 2) != 0 ? Color.m4540getRedimpl(jColor4) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jColor4) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jColor4) : 0.0f), null);
            case 4:
                long jColor5 = ColorKt.Color(4294929259L);
                long jColor6 = ColorKt.Color(4294929259L);
                long jM4532copywmQWz5c2 = Color.m4532copywmQWz5c(jColor6, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jColor6) : 0.5f, (14 & 2) != 0 ? Color.m4540getRedimpl(jColor6) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jColor6) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jColor6) : 0.0f);
                long jColor7 = ColorKt.Color(4294955216L);
                long jColor8 = ColorKt.Color(4287310378L);
                return new AssistOrbPalette(jColor5, jM4532copywmQWz5c2, jColor7, Color.m4532copywmQWz5c(jColor8, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jColor8) : 0.45f, (14 & 2) != 0 ? Color.m4540getRedimpl(jColor8) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jColor8) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jColor8) : 0.0f), null);
            case 5:
                long j3 = base;
                return new AssistOrbPalette(Color.m4532copywmQWz5c(j3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j3) : 0.45f, (14 & 2) != 0 ? Color.m4540getRedimpl(j3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j3) : 0.0f), Color.m4532copywmQWz5c(j3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j3) : 0.15f, (14 & 2) != 0 ? Color.m4540getRedimpl(j3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j3) : 0.0f), Color.m4532copywmQWz5c(j3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j3) : 0.35f, (14 & 2) != 0 ? Color.m4540getRedimpl(j3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j3) : 0.0f), Color.m4532copywmQWz5c(j3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j3) : 0.12f, (14 & 2) != 0 ? Color.m4540getRedimpl(j3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j3) : 0.0f), null);
            case 6:
                long j4 = base;
                return new AssistOrbPalette(base, Color.m4532copywmQWz5c(base, (14 & 1) != 0 ? Color.m4536getAlphaimpl(base) : 0.4f, (14 & 2) != 0 ? Color.m4540getRedimpl(base) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(base) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(base) : 0.0f), ColorKt.Color(4294964968L), Color.m4532copywmQWz5c(j4, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j4) : 0.28f, (14 & 2) != 0 ? Color.m4540getRedimpl(j4) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j4) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j4) : 0.0f), null);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Code duplicated, block: B:187:0x05d6  */
    /* JADX WARN: Code duplicated, block: B:188:0x05d9  */
    /* JADX WARN: Code duplicated, block: B:199:0x068b  */
    /* JADX INFO: renamed from: AssistOrb-jIwJxvA, reason: not valid java name */
    public static final void m85AssistOrbjIwJxvA(final AssistOrbPhase phase, String style, Modifier modifier, float size, Function0<Unit> function0, String contentDescription, Composer $composer, final int $changed, final int i) {
        String str;
        Modifier modifier2;
        float size2;
        final Function0<Unit> function1;
        final String contentDescription2;
        int i2;
        float size3;
        int i3;
        int i4;
        Continuation continuation;
        Object value$iv;
        AssistOrbKt$AssistOrb$1$1 value$iv2;
        Object value$iv3;
        AssistOrbKt$AssistOrb$2$1 value$iv4;
        ArrayList value$iv5;
        Object value$iv6;
        Object value$iv7;
        String style2;
        Modifier modifier3;
        CompositionLocalMap localMap$iv$iv;
        boolean z;
        boolean invalid$iv;
        Object value$iv8;
        Intrinsics.checkNotNullParameter(phase, "phase");
        Composer $composer2 = $composer.startRestartGroup(-1841466142);
        ComposerKt.sourceInformation($composer2, "C(AssistOrb)P(3,5,1,4:c#ui.unit.Dp,2)153@5232L48,155@5309L590,174@5926L488,191@6442L484,209@6987L30,210@7044L178,210@7022L200,219@7294L27,220@7348L251,220@7326L273,230@7656L468,244@8148L39,260@8561L132,257@8481L6989:AssistOrb.kt#rc2ijs");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(phase) ? 4 : 2;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty |= 48;
            str = style;
        } else if (($changed & 48) == 0) {
            str = style;
            $dirty |= $composer2.changed(str) ? 32 : 16;
        } else {
            str = style;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty |= 3072;
            size2 = size;
        } else if (($changed & 3072) == 0) {
            size2 = size;
            $dirty |= $composer2.changed(size2) ? 2048 : 1024;
        } else {
            size2 = size;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty |= 24576;
            function1 = function0;
        } else if (($changed & 24576) == 0) {
            function1 = function0;
            $dirty |= $composer2.changedInstance(function1) ? 16384 : 8192;
        } else {
            function1 = function0;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            contentDescription2 = contentDescription;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            contentDescription2 = contentDescription;
            $dirty |= $composer2.changed(contentDescription2) ? 131072 : 65536;
        } else {
            contentDescription2 = contentDescription;
        }
        if (($dirty & 74899) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            style2 = str;
            modifier3 = modifier2;
            size3 = size2;
        } else {
            String style3 = i5 != 0 ? "amber" : str;
            Modifier modifier4 = i6 != 0 ? Modifier.INSTANCE : modifier2;
            if (i7 != 0) {
                size2 = Dp.m7016constructorimpl(200);
            }
            if (i8 != 0) {
                function1 = null;
            }
            if (i9 != 0) {
                contentDescription2 = "Assist orb";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1841466142, $dirty, -1, "ai.nous.aurora.presentation.assist.AssistOrb (AssistOrb.kt:151)");
            }
            final AssistOrbPalette palette = assistOrbPalette(style3, phase);
            InfiniteTransition infinite = InfiniteTransitionKt.rememberInfiniteTransition("assist_orb", $composer2, 6, 0);
            switch (WhenMappings.$EnumSwitchMapping$0[phase.ordinal()]) {
                case 1:
                    i2 = 1400;
                    break;
                case 2:
                    i2 = 2200;
                    break;
                case 3:
                    i2 = 2800;
                    break;
                case 4:
                    i2 = 900;
                    break;
                default:
                    i2 = 3200;
                    break;
            }
            int $dirty2 = $dirty;
            size3 = size2;
            final State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(infinite, 0.0f, 1.0f, AnimationSpecKt.m492infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(i2, 0, EasingKt.getFastOutSlowInEasing(), 2, null), RepeatMode.Reverse, 0L, 4, null), "breath", $composer2, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
            switch (WhenMappings.$EnumSwitchMapping$0[phase.ordinal()]) {
                case 1:
                    i3 = 9000;
                    break;
                case 2:
                    i3 = 2800;
                    break;
                default:
                    i3 = 16000;
                    break;
            }
            final State<Float> stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infinite, 0.0f, 360.0f, AnimationSpecKt.m492infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(i3, 0, EasingKt.getLinearEasing(), 2, null), RepeatMode.Restart, 0L, 4, null), "spin", $composer2, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
            switch (WhenMappings.$EnumSwitchMapping$0[phase.ordinal()]) {
                case 1:
                    i4 = 1600;
                    break;
                case 4:
                    i4 = 1100;
                    break;
                default:
                    i4 = 2400;
                    break;
            }
            final State<Float> stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infinite, 0.0f, 1.0f, AnimationSpecKt.m492infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(i4, 0, EasingKt.getLinearEasing(), 2, null), RepeatMode.Restart, 0L, 4, null), "pulse", $composer2, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
            $composer2.startReplaceGroup(623418430);
            ComposerKt.sourceInformation($composer2, "CC(remember):AssistOrb.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                continuation = null;
                value$iv = AnimatableKt.Animatable$default(0.92f, 0.0f, 2, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                continuation = null;
                value$iv = it$iv;
            }
            final Animatable settle = (Animatable) value$iv;
            $composer2.endReplaceGroup();
            $composer2.startReplaceGroup(623420402);
            ComposerKt.sourceInformation($composer2, "CC(remember):AssistOrb.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changedInstance(settle);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new AssistOrbKt$AssistOrb$1$1(settle, continuation);
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            $composer2.endReplaceGroup();
            EffectsKt.LaunchedEffect(phase, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer2, $dirty2 & 14);
            $composer2.startReplaceGroup(623428251);
            ComposerKt.sourceInformation($composer2, "CC(remember):AssistOrb.kt#9igjgp");
            Object it$iv3 = $composer2.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
                $composer2.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            Animatable doneBloomAnim = (Animatable) value$iv3;
            $composer2.endReplaceGroup();
            $composer2.startReplaceGroup(623430203);
            ComposerKt.sourceInformation($composer2, "CC(remember):AssistOrb.kt#9igjgp");
            boolean invalid$iv3 = (($dirty2 & 14) == 4) | $composer2.changedInstance(doneBloomAnim);
            Object it$iv4 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = new AssistOrbKt$AssistOrb$2$1(phase, doneBloomAnim, null);
                $composer2.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv4;
            }
            $composer2.endReplaceGroup();
            EffectsKt.LaunchedEffect(phase, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv4, $composer2, $dirty2 & 14);
            final float doneBloom = ((Number) doneBloomAnim.getValue()).floatValue();
            $composer2.startReplaceGroup(623440276);
            ComposerKt.sourceInformation($composer2, "CC(remember):AssistOrb.kt#9igjgp");
            boolean invalid$iv4 = ($dirty2 & 112) == 32;
            Object it$iv5 = $composer2.rememberedValue();
            if (invalid$iv4 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                Random rng = RandomKt.Random((style3.hashCode() * 31) + 17);
                ArrayList arrayList = new ArrayList(28);
                int i10 = 0;
                for (int $i$f$cache = 28; i10 < $i$f$cache; $i$f$cache = 28) {
                    arrayList.add(new DustSpec(rng.nextFloat() * 360.0f, (rng.nextFloat() * 0.42f) + 0.28f, (rng.nextFloat() * 0.018f) + 0.008f, (rng.nextFloat() * 1.1f) + 0.35f, rng.nextFloat(), (rng.nextFloat() * 0.55f) + 0.25f));
                    i10++;
                }
                value$iv5 = arrayList;
                $composer2.updateRememberedValue(value$iv5);
            } else {
                value$iv5 = it$iv5;
            }
            final List dust = (List) value$iv5;
            $composer2.endReplaceGroup();
            $composer2.startReplaceGroup(623455591);
            ComposerKt.sourceInformation($composer2, "CC(remember):AssistOrb.kt#9igjgp");
            Object it$iv6 = $composer2.rememberedValue();
            if (it$iv6 == Composer.INSTANCE.getEmpty()) {
                value$iv6 = InteractionSourceKt.MutableInteractionSource();
                $composer2.updateRememberedValue(value$iv6);
            } else {
                value$iv6 = it$iv6;
            }
            MutableInteractionSource interaction = (MutableInteractionSource) value$iv6;
            $composer2.endReplaceGroup();
            Modifier clickMod = function1 != null ? ClickableKt.m619clickableO2vRcR0(Modifier.INSTANCE, interaction, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : Role.m6282boximpl(Role.INSTANCE.m6289getButtono7Vup1c()), function1) : Modifier.INSTANCE;
            Modifier modifierM1079size3ABfNKs = SizeKt.m1079size3ABfNKs(modifier4, size3);
            $composer2.startReplaceGroup(623468900);
            ComposerKt.sourceInformation($composer2, "CC(remember):AssistOrb.kt#9igjgp");
            boolean invalid$iv5 = (($dirty2 & 458752) == 131072) | (($dirty2 & 57344) == 16384);
            Object it$iv7 = $composer2.rememberedValue();
            if (invalid$iv5 || it$iv7 == Composer.INSTANCE.getEmpty()) {
                value$iv7 = new Function1() { // from class: ai.nous.aurora.presentation.assist.AssistOrbKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AssistOrbKt.AssistOrb_jIwJxvA$lambda$11$lambda$10(contentDescription2, function1, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv7);
            } else {
                value$iv7 = it$iv7;
            }
            $composer2.endReplaceGroup();
            style2 = style3;
            Modifier modifier$iv = SemanticsModifierKt.semantics$default(modifierM1079size3ABfNKs, false, (Function1) value$iv7, 1, null).then(clickMod);
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            modifier3 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                localMap$iv$iv = localMap$iv$iv2;
            } else {
                localMap$iv$iv = localMap$iv$iv2;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i12 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 779772428, "C267@8826L6638,267@8784L6680:AssistOrb.kt#rc2ijs");
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                $composer2.startReplaceGroup(1964817901);
                ComposerKt.sourceInformation($composer2, "CC(remember):AssistOrb.kt#9igjgp");
                boolean zChangedInstance = $composer2.changedInstance(settle) | $composer2.changed(stateAnimateFloat);
                if (($dirty2 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                invalid$iv = zChangedInstance | z | $composer2.changed(doneBloom) | $composer2.changed(palette) | $composer2.changed(stateAnimateFloat3) | $composer2.changed(stateAnimateFloat2) | $composer2.changedInstance(dust);
                value$iv8 = $composer2.rememberedValue();
                if (!invalid$iv || value$iv8 == Composer.INSTANCE.getEmpty()) {
                    value$iv8 = new Function1() { // from class: ai.nous.aurora.presentation.assist.AssistOrbKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AssistOrbKt.AssistOrb_jIwJxvA$lambda$15$lambda$14$lambda$13(settle, phase, doneBloom, palette, dust, stateAnimateFloat, stateAnimateFloat3, stateAnimateFloat2, (DrawScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv8);
                }
                r4.endReplaceGroup();
                CanvasKt.Canvas(modifierFillMaxSize$default, (Function1) value$iv8, $composer2, 6);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i13 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i14 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 779772428, "C267@8826L6638,267@8784L6680:AssistOrb.kt#rc2ijs");
            Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            $composer2.startReplaceGroup(1964817901);
            ComposerKt.sourceInformation($composer2, "CC(remember):AssistOrb.kt#9igjgp");
            boolean zChangedInstance2 = $composer2.changedInstance(settle) | $composer2.changed(stateAnimateFloat);
            if (($dirty2 & 14) == 4) {
                z = true;
            } else {
                z = false;
            }
            invalid$iv = zChangedInstance2 | z | $composer2.changed(doneBloom) | $composer2.changed(palette) | $composer2.changed(stateAnimateFloat3) | $composer2.changed(stateAnimateFloat2) | $composer2.changedInstance(dust);
            value$iv8 = $composer2.rememberedValue();
            if (!invalid$iv) {
            }
            value$iv8 = new Function1() { // from class: ai.nous.aurora.presentation.assist.AssistOrbKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AssistOrbKt.AssistOrb_jIwJxvA$lambda$15$lambda$14$lambda$13(settle, phase, doneBloom, palette, dust, stateAnimateFloat, stateAnimateFloat3, stateAnimateFloat2, (DrawScope) obj);
                }
            };
            $composer2.updateRememberedValue(value$iv8);
            r4.endReplaceGroup();
            CanvasKt.Canvas(modifierFillMaxSize$default2, (Function1) value$iv8, $composer2, 6);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final String str2 = style2;
            final Modifier modifier5 = modifier3;
            final float f = size3;
            final Function0<Unit> function2 = function1;
            final String str3 = contentDescription2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.assist.AssistOrbKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AssistOrbKt.AssistOrb_jIwJxvA$lambda$16(phase, str2, modifier5, f, function2, str3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float AssistOrb_jIwJxvA$lambda$0(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    private static final float AssistOrb_jIwJxvA$lambda$1(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    private static final float AssistOrb_jIwJxvA$lambda$2(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AssistOrb_jIwJxvA$lambda$11$lambda$10(String $contentDescription, Function0 $onClick, SemanticsPropertyReceiver semantics) {
        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
        SemanticsPropertiesKt.setContentDescription(semantics, $contentDescription);
        if ($onClick != null) {
            SemanticsPropertiesKt.m6308setRolekuIjeqM(semantics, Role.INSTANCE.m6289getButtono7Vup1c());
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AssistOrb_jIwJxvA$lambda$15$lambda$14$lambda$13(Animatable $settle, AssistOrbPhase $phase, float $doneBloom, AssistOrbPalette $palette, List $dust, State $breath$delegate, State $pulse$delegate, State $spin$delegate, DrawScope Canvas) throws Throwable {
        float fAssistOrb_jIwJxvA$lambda$0;
        float energy;
        long center;
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        float minDim = Math.min(Size.m4362getWidthimpl(Canvas.mo5090getSizeNHjbRc()), Size.m4359getHeightimpl(Canvas.mo5090getSizeNHjbRc()));
        float cx = Size.m4362getWidthimpl(Canvas.mo5090getSizeNHjbRc()) / 2.0f;
        float cy = Size.m4359getHeightimpl(Canvas.mo5090getSizeNHjbRc()) / 2.0f;
        long center2 = OffsetKt.Offset(cx, cy);
        float scale = ((Number) $settle.getValue()).floatValue();
        float coreR = minDim * 0.22f * scale;
        float bodyR = minDim * 0.28f * scale;
        float haloR = minDim * 0.48f * ((AssistOrb_jIwJxvA$lambda$0($breath$delegate) * 0.08f) + 0.94f) * scale;
        switch (WhenMappings.$EnumSwitchMapping$0[$phase.ordinal()]) {
            case 1:
                fAssistOrb_jIwJxvA$lambda$0 = (AssistOrb_jIwJxvA$lambda$0($breath$delegate) * 0.12f) + 0.18f;
                break;
            case 2:
                fAssistOrb_jIwJxvA$lambda$0 = 0.14f;
                break;
            case 3:
                fAssistOrb_jIwJxvA$lambda$0 = 0.16f + ($doneBloom * 0.35f);
                break;
            case 4:
                fAssistOrb_jIwJxvA$lambda$0 = (AssistOrb_jIwJxvA$lambda$0($breath$delegate) * 0.1f) + 0.12f;
                break;
            case 5:
                fAssistOrb_jIwJxvA$lambda$0 = 0.05f;
                break;
            case 6:
                fAssistOrb_jIwJxvA$lambda$0 = (AssistOrb_jIwJxvA$lambda$0($breath$delegate) * 0.04f) + 0.1f;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        float bloomBoost = fAssistOrb_jIwJxvA$lambda$0;
        Brush.Companion companion = Brush.INSTANCE;
        long jM100getGlow0d7_KjU = $palette.m100getGlow0d7_KjU();
        long jM98getCore0d7_KjU = $palette.m98getCore0d7_KjU();
        DrawScope.m5070drawCircleV9BoPsw$default(Canvas, companion.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(jM100getGlow0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM100getGlow0d7_KjU) : bloomBoost, (14 & 2) != 0 ? Color.m4540getRedimpl(jM100getGlow0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM100getGlow0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM100getGlow0d7_KjU) : 0.0f)), Color.m4524boximpl(Color.m4532copywmQWz5c(jM98getCore0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM98getCore0d7_KjU) : bloomBoost * 0.35f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM98getCore0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM98getCore0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM98getCore0d7_KjU) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : center2, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : minDim * 0.5f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), minDim * 0.5f, center2, 0.0f, null, null, 0, 120, null);
        if ($phase == AssistOrbPhase.Listening || $phase == AssistOrbPhase.Error) {
            float f = minDim * 0.48f;
            AssistOrbPhase assistOrbPhase = AssistOrbPhase.Error;
            m89drawPulseRingsxjpq4WA(Canvas, center2, bodyR, f, $palette.m98getCore0d7_KjU(), AssistOrb_jIwJxvA$lambda$2($pulse$delegate), $phase == AssistOrbPhase.Listening ? 3 : 2);
        }
        if ($phase == AssistOrbPhase.Working) {
            m90drawWorkingSweepsdmr55fk(Canvas, center2, bodyR * 1.55f, $palette.m98getCore0d7_KjU(), AssistOrb_jIwJxvA$lambda$1($spin$delegate));
        }
        Brush.Companion companion2 = Brush.INSTANCE;
        long jM98getCore0d7_KjU2 = $palette.m98getCore0d7_KjU();
        long jM99getDeep0d7_KjU = $palette.m99getDeep0d7_KjU();
        DrawScope.m5070drawCircleV9BoPsw$default(Canvas, companion2.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(jM98getCore0d7_KjU2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM98getCore0d7_KjU2) : (AssistOrb_jIwJxvA$lambda$0($breath$delegate) * 0.06f) + 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM98getCore0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM98getCore0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM98getCore0d7_KjU2) : 0.0f)), Color.m4524boximpl(Color.m4532copywmQWz5c(jM99getDeep0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM99getDeep0d7_KjU) : 0.08f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM99getDeep0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM99getDeep0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM99getDeep0d7_KjU) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : center2, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : haloR, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), haloR, center2, 0.0f, null, null, 0, 120, null);
        switch (WhenMappings.$EnumSwitchMapping$0[$phase.ordinal()]) {
            case 1:
                energy = 1.35f;
                break;
            case 2:
                energy = 1.1f;
                break;
            case 3:
                energy = (0.6f * $doneBloom) + 0.55f;
                break;
            case 4:
                energy = 1.2f;
                break;
            case 5:
                energy = 0.25f;
                break;
            case 6:
                energy = 0.65f;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        m87drawDust7fTDFas(Canvas, center2, bodyR, $dust, AssistOrb_jIwJxvA$lambda$1($spin$delegate), AssistOrb_jIwJxvA$lambda$0($breath$delegate), energy, $palette.m97getAccent0d7_KjU(), $palette.m98getCore0d7_KjU());
        Brush.Companion companion3 = Brush.INSTANCE;
        Float fValueOf = Float.valueOf(0.0f);
        long jM97getAccent0d7_KjU = $palette.m97getAccent0d7_KjU();
        Float fValueOf2 = Float.valueOf(0.18f);
        long jM98getCore0d7_KjU3 = $palette.m98getCore0d7_KjU();
        Float fValueOf3 = Float.valueOf(0.55f);
        long jM98getCore0d7_KjU4 = $palette.m98getCore0d7_KjU();
        Float fValueOf4 = Float.valueOf(0.82f);
        long jM99getDeep0d7_KjU2 = $palette.m99getDeep0d7_KjU();
        Float fValueOf5 = Float.valueOf(1.0f);
        long jM98getCore0d7_KjU5 = $palette.m98getCore0d7_KjU();
        DrawScope.m5070drawCircleV9BoPsw$default(Canvas, companion3.m4498radialGradientP_VxKs((Pair<Float, Color>[]) new Pair[]{TuplesKt.to(fValueOf, Color.m4524boximpl(Color.m4532copywmQWz5c(jM97getAccent0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM97getAccent0d7_KjU) : 0.95f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM97getAccent0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM97getAccent0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM97getAccent0d7_KjU) : 0.0f))), TuplesKt.to(fValueOf2, Color.m4524boximpl(Color.m4532copywmQWz5c(jM98getCore0d7_KjU3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM98getCore0d7_KjU3) : 1.0f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM98getCore0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM98getCore0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM98getCore0d7_KjU3) : 0.0f))), TuplesKt.to(fValueOf3, Color.m4524boximpl(Color.m4532copywmQWz5c(jM98getCore0d7_KjU4, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM98getCore0d7_KjU4) : 0.92f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM98getCore0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM98getCore0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM98getCore0d7_KjU4) : 0.0f))), TuplesKt.to(fValueOf4, Color.m4524boximpl(Color.m4532copywmQWz5c(jM99getDeep0d7_KjU2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM99getDeep0d7_KjU2) : 0.95f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM99getDeep0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM99getDeep0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM99getDeep0d7_KjU2) : 0.0f))), TuplesKt.to(fValueOf5, Color.m4524boximpl(Color.m4532copywmQWz5c(jM98getCore0d7_KjU5, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM98getCore0d7_KjU5) : 0.55f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM98getCore0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM98getCore0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM98getCore0d7_KjU5) : 0.0f)))}, (8 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : OffsetKt.Offset(cx - (bodyR * 0.22f), cy - (bodyR * 0.28f)), (8 & 4) != 0 ? Float.POSITIVE_INFINITY : bodyR * 1.35f, (8 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), bodyR, center2, 0.0f, null, null, 0, 120, null);
        Brush.Companion companion4 = Brush.INSTANCE;
        long jM4571getWhite0d7_KjU = Color.INSTANCE.m4571getWhite0d7_KjU();
        long jM97getAccent0d7_KjU2 = $palette.m97getAccent0d7_KjU();
        long jM98getCore0d7_KjU6 = $palette.m98getCore0d7_KjU();
        DrawScope.m5070drawCircleV9BoPsw$default(Canvas, companion4.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(jM4571getWhite0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM4571getWhite0d7_KjU) : (AssistOrb_jIwJxvA$lambda$0($breath$delegate) * 0.15f) + 0.55f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM4571getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM4571getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM4571getWhite0d7_KjU) : 0.0f)), Color.m4524boximpl(Color.m4532copywmQWz5c(jM97getAccent0d7_KjU2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM97getAccent0d7_KjU2) : 0.65f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM97getAccent0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM97getAccent0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM97getAccent0d7_KjU2) : 0.0f)), Color.m4524boximpl(Color.m4532copywmQWz5c(jM98getCore0d7_KjU6, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM98getCore0d7_KjU6) : 0.0f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM98getCore0d7_KjU6) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM98getCore0d7_KjU6) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM98getCore0d7_KjU6) : 0.0f))}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : OffsetKt.Offset(cx - (0.15f * coreR), cy - (0.2f * coreR)), (14 & 4) != 0 ? Float.POSITIVE_INFINITY : coreR * 1.2f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), coreR, center2, 0.0f, null, null, 0, 120, null);
        long specCenter = OffsetKt.Offset(cx - (0.28f * bodyR), cy - (0.32f * bodyR));
        Brush.Companion companion5 = Brush.INSTANCE;
        long jM4571getWhite0d7_KjU2 = Color.INSTANCE.m4571getWhite0d7_KjU();
        long jM4571getWhite0d7_KjU3 = Color.INSTANCE.m4571getWhite0d7_KjU();
        DrawScope.m5070drawCircleV9BoPsw$default(Canvas, companion5.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(jM4571getWhite0d7_KjU2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM4571getWhite0d7_KjU2) : 0.55f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM4571getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM4571getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM4571getWhite0d7_KjU2) : 0.0f)), Color.m4524boximpl(Color.m4532copywmQWz5c(jM4571getWhite0d7_KjU3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM4571getWhite0d7_KjU3) : 0.12f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM4571getWhite0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM4571getWhite0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM4571getWhite0d7_KjU3) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : specCenter, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : bodyR * 0.42f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), bodyR * 0.42f, specCenter, 0.0f, null, null, 0, 120, null);
        long jM97getAccent0d7_KjU3 = $palette.m97getAccent0d7_KjU();
        DrawScope.m5071drawCircleVaOC9Bg$default(Canvas, Color.m4532copywmQWz5c(jM97getAccent0d7_KjU3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM97getAccent0d7_KjU3) : (AssistOrb_jIwJxvA$lambda$0($breath$delegate) * 0.08f) + 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM97getAccent0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM97getAccent0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM97getAccent0d7_KjU3) : 0.0f), bodyR, center2, 0.0f, new Stroke(minDim * 0.008f, 0.0f, 0, 0, null, 30, null), null, 0, LocationRequestCompat.QUALITY_LOW_POWER, null);
        if ($phase == AssistOrbPhase.Idle || $phase == AssistOrbPhase.Done) {
            float degrees$iv = AssistOrb_jIwJxvA$lambda$1($spin$delegate);
            DrawContext $this$withTransform_u24lambda_u246$iv$iv = Canvas.getDrawContext();
            long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo5011getSizeNHjbRc();
            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
            try {
                DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
                center = center2;
                try {
                    $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo5017rotateUv8p0NA(degrees$iv, center);
                    long jM98getCore0d7_KjU7 = $palette.m98getCore0d7_KjU();
                    DrawScope.m5069drawArcyD3GUKo$default(Canvas, Color.m4532copywmQWz5c(jM98getCore0d7_KjU7, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM98getCore0d7_KjU7) : 0.35f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM98getCore0d7_KjU7) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM98getCore0d7_KjU7) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM98getCore0d7_KjU7) : 0.0f), -20.0f, 70.0f, false, OffsetKt.Offset(cx - (bodyR * 1.25f), cy - (1.25f * bodyR)), androidx.compose.ui.geometry.SizeKt.Size(bodyR * 2.5f, 2.5f * bodyR), 0.0f, new Stroke(minDim * 0.006f, 0.0f, StrokeCap.INSTANCE.m4888getRoundKaPHkGw(), 0, null, 26, null), null, 0, 832, null);
                    $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u246$iv$iv.mo5012setSizeuvyYCjk(previousSize$iv$iv);
                } catch (Throwable th) {
                    th = th;
                    $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u246$iv$iv.mo5012setSizeuvyYCjk(previousSize$iv$iv);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            center = center2;
        }
        if ($phase == AssistOrbPhase.Listening) {
            m88drawListeningArcsIfIb9nM(Canvas, center, bodyR * 1.35f, $palette.m98getCore0d7_KjU(), AssistOrb_jIwJxvA$lambda$0($breath$delegate), AssistOrb_jIwJxvA$lambda$1($spin$delegate));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: AssistOrbPreview-hGBTI10, reason: not valid java name */
    public static final void m86AssistOrbPreviewhGBTI10(final String style, final boolean selected, Modifier modifier, float size, Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        Function0<Unit> function1;
        Modifier modifier3;
        float size2;
        Function0<Unit> function2;
        Intrinsics.checkNotNullParameter(style, "style");
        Composer $composer2 = $composer.startRestartGroup(-2119168428);
        ComposerKt.sourceInformation($composer2, "C(AssistOrbPreview)P(4,2!1,3:c#ui.unit.Dp)451@15724L244:AssistOrb.kt#rc2ijs");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(style) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(selected) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            f = size;
        } else if (($changed & 3072) == 0) {
            f = size;
            $dirty |= $composer2.changed(f) ? 2048 : 1024;
        } else {
            f = size;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            function1 = function0;
        } else if (($changed & 24576) == 0) {
            function1 = function0;
            $dirty |= $composer2.changedInstance(function1) ? 16384 : 8192;
        } else {
            function1 = function0;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            size2 = f;
            function2 = function1;
        } else {
            modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            size2 = i3 != 0 ? Dp.m7016constructorimpl(40) : f;
            function2 = i4 != 0 ? null : function1;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2119168428, $dirty2, -1, "ai.nous.aurora.presentation.assist.AssistOrbPreview (AssistOrb.kt:450)");
            }
            m85AssistOrbjIwJxvA(selected ? AssistOrbPhase.Idle : AssistOrbPhase.NeedMic, style, modifier3, size2, function2, "Orb style " + style, $composer2, (($dirty2 << 3) & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            final float f2 = size2;
            final Function0<Unit> function3 = function2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.assist.AssistOrbKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AssistOrbKt.AssistOrbPreview_hGBTI10$lambda$17(style, selected, modifier4, f2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: drawPulseRings-xjpq4WA, reason: not valid java name */
    private static final void m89drawPulseRingsxjpq4WA(DrawScope $this$drawPulseRings_u2dxjpq4WA, long center, float baseRadius, float maxRadius, long color, float t, int rings) {
        for (int i = 0; i < rings; i++) {
            float local = (t + (i / rings)) % 1.0f;
            float r = baseRadius + ((maxRadius - baseRadius) * local);
            float alpha = (1.0f - local) * 0.45f;
            DrawScope.m5071drawCircleVaOC9Bg$default($this$drawPulseRings_u2dxjpq4WA, Color.m4532copywmQWz5c(color, (14 & 1) != 0 ? Color.m4536getAlphaimpl(color) : alpha, (14 & 2) != 0 ? Color.m4540getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(color) : 0.0f), r, center, 0.0f, new Stroke(Size.m4361getMinDimensionimpl($this$drawPulseRings_u2dxjpq4WA.mo5090getSizeNHjbRc()) * 0.01f * (1.0f - (0.6f * local)), 0.0f, 0, 0, null, 30, null), null, 0, LocationRequestCompat.QUALITY_LOW_POWER, null);
        }
    }

    /* JADX INFO: renamed from: drawWorkingSweeps-dmr55fk, reason: not valid java name */
    private static final void m90drawWorkingSweepsdmr55fk(DrawScope $this$drawWorkingSweeps_u2ddmr55fk, long center, float radius, long color, float spinDeg) throws Throwable {
        long previousSize$iv$iv;
        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
        long previousSize$iv$iv2;
        DrawContext $this$withTransform_u24lambda_u246$iv$iv2;
        float stroke = 0.014f * Size.m4361getMinDimensionimpl($this$drawWorkingSweeps_u2ddmr55fk.mo5090getSizeNHjbRc());
        DrawContext $this$withTransform_u24lambda_u246$iv$iv3 = $this$drawWorkingSweeps_u2ddmr55fk.getDrawContext();
        long previousSize$iv$iv3 = $this$withTransform_u24lambda_u246$iv$iv3.mo5011getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv3.getCanvas().save();
        try {
            DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv3.getTransform();
            $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo5017rotateUv8p0NA(spinDeg, center);
            try {
                DrawScope.m5068drawArcillE91I$default($this$drawWorkingSweeps_u2ddmr55fk, Brush.INSTANCE.m4499sweepGradientUv8p0NA(CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU()), Color.m4524boximpl(Color.m4532copywmQWz5c(color, (14 & 1) != 0 ? Color.m4536getAlphaimpl(color) : 0.15f, (14 & 2) != 0 ? Color.m4540getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(color) : 0.0f)), Color.m4524boximpl(Color.m4532copywmQWz5c(color, (14 & 1) != 0 ? Color.m4536getAlphaimpl(color) : 0.85f, (14 & 2) != 0 ? Color.m4540getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(color) : 0.0f)), Color.m4524boximpl(Color.m4532copywmQWz5c(color, (14 & 1) != 0 ? Color.m4536getAlphaimpl(color) : 0.15f, (14 & 2) != 0 ? Color.m4540getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(color) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), center), 0.0f, 360.0f, false, OffsetKt.Offset(Offset.m4293getXimpl(center) - radius, Offset.m4294getYimpl(center) - radius), androidx.compose.ui.geometry.SizeKt.Size(radius * 2.0f, radius * 2.0f), 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m4888getRoundKaPHkGw(), 0, null, 26, null), null, 0, 832, null);
                $this$withTransform_u24lambda_u246$iv$iv3.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv3.mo5012setSizeuvyYCjk(previousSize$iv$iv3);
                float degrees$iv = (-spinDeg) * 1.4f;
                DrawContext $this$withTransform_u24lambda_u246$iv$iv4 = $this$drawWorkingSweeps_u2ddmr55fk.getDrawContext();
                long previousSize$iv$iv4 = $this$withTransform_u24lambda_u246$iv$iv4.mo5011getSizeNHjbRc();
                $this$withTransform_u24lambda_u246$iv$iv4.getCanvas().save();
                try {
                    DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv2 = $this$withTransform_u24lambda_u246$iv$iv4.getTransform();
                    $this$rotate_Rg1IO4c_u24lambda_u240$iv2.mo5017rotateUv8p0NA(degrees$iv, center);
                    float r2 = radius * 0.78f;
                    previousSize$iv$iv2 = previousSize$iv$iv4;
                    try {
                        DrawScope.m5069drawArcyD3GUKo$default($this$drawWorkingSweeps_u2ddmr55fk, Color.m4532copywmQWz5c(color, (14 & 1) != 0 ? Color.m4536getAlphaimpl(color) : 0.55f, (14 & 2) != 0 ? Color.m4540getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(color) : 0.0f), 40.0f, 100.0f, false, OffsetKt.Offset(Offset.m4293getXimpl(center) - r2, Offset.m4294getYimpl(center) - r2), androidx.compose.ui.geometry.SizeKt.Size(r2 * 2.0f, r2 * 2.0f), 0.0f, new Stroke(stroke * 0.7f, 0.0f, StrokeCap.INSTANCE.m4888getRoundKaPHkGw(), 0, null, 26, null), null, 0, 832, null);
                        $this$withTransform_u24lambda_u246$iv$iv4.getCanvas().restore();
                        $this$withTransform_u24lambda_u246$iv$iv4.mo5012setSizeuvyYCjk(previousSize$iv$iv2);
                        for (int i = 0; i < 3; i++) {
                            double ang = Math.toRadians((1.2f * spinDeg) + (i * 120.0f));
                            float beadR = 0.92f * radius;
                            long p = OffsetKt.Offset(Offset.m4293getXimpl(center) + ((float) (Math.cos(ang) * ((double) beadR))), Offset.m4294getYimpl(center) + ((float) (Math.sin(ang) * ((double) beadR))));
                            DrawScope.m5071drawCircleVaOC9Bg$default($this$drawWorkingSweeps_u2ddmr55fk, Color.m4532copywmQWz5c(color, (14 & 1) != 0 ? Color.m4536getAlphaimpl(color) : 0.7f, (14 & 2) != 0 ? Color.m4540getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(color) : 0.0f), 0.012f * Size.m4361getMinDimensionimpl($this$drawWorkingSweeps_u2ddmr55fk.mo5090getSizeNHjbRc()), p, 0.0f, null, null, 0, 120, null);
                        }
                    } catch (Throwable th) {
                        th = th;
                        $this$withTransform_u24lambda_u246$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv4;
                        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().restore();
                        $this$withTransform_u24lambda_u246$iv$iv2.mo5012setSizeuvyYCjk(previousSize$iv$iv2);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    previousSize$iv$iv2 = previousSize$iv$iv4;
                    $this$withTransform_u24lambda_u246$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv4;
                }
            } catch (Throwable th3) {
                th = th3;
                previousSize$iv$iv = previousSize$iv$iv3;
                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv3;
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv.mo5012setSizeuvyYCjk(previousSize$iv$iv);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            previousSize$iv$iv = previousSize$iv$iv3;
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv3;
        }
    }

    /* JADX INFO: renamed from: drawListeningArcs-IfIb9nM, reason: not valid java name */
    private static final void m88drawListeningArcsIfIb9nM(DrawScope $this$drawListeningArcs_u2dIfIb9nM, long center, float radius, long color, float breath, float spinDeg) throws Throwable {
        long previousSize$iv$iv;
        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
        float stroke = 0.011f * Size.m4361getMinDimensionimpl($this$drawListeningArcs_u2dIfIb9nM.mo5090getSizeNHjbRc());
        float degrees$iv = 0.4f * spinDeg;
        DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = $this$drawListeningArcs_u2dIfIb9nM.getDrawContext();
        long previousSize$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv2.mo5011getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
        try {
            DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
            $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo5017rotateUv8p0NA(degrees$iv, center);
            int i = 0;
            while (i < 4) {
                float sweep = (22.0f * breath) + 28.0f + (i * 6.0f);
                float start = (i * 90.0f) + (18.0f * breath);
                float r = radius * ((i * 0.06f) + 0.92f);
                try {
                    long previousSize$iv$iv3 = previousSize$iv$iv2;
                    try {
                        $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                        int i2 = i;
                        try {
                            DrawScope.m5069drawArcyD3GUKo$default($this$drawListeningArcs_u2dIfIb9nM, Color.m4532copywmQWz5c(color, (14 & 1) != 0 ? Color.m4536getAlphaimpl(color) : 0.35f - (i * 0.05f), (14 & 2) != 0 ? Color.m4540getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(color) : 0.0f), start, sweep, false, OffsetKt.Offset(Offset.m4293getXimpl(center) - r, Offset.m4294getYimpl(center) - r), androidx.compose.ui.geometry.SizeKt.Size(r * 2.0f, 2.0f * r), 0.0f, new Stroke(stroke, 0.0f, StrokeCap.INSTANCE.m4888getRoundKaPHkGw(), 0, null, 26, null), null, 0, 832, null);
                            i = i2 + 1;
                            $this$withTransform_u24lambda_u246$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv;
                            previousSize$iv$iv2 = previousSize$iv$iv3;
                        } catch (Throwable th) {
                            th = th;
                            previousSize$iv$iv = previousSize$iv$iv3;
                            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                            $this$withTransform_u24lambda_u246$iv$iv.mo5012setSizeuvyYCjk(previousSize$iv$iv);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                        previousSize$iv$iv = previousSize$iv$iv3;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                    previousSize$iv$iv = previousSize$iv$iv2;
                }
            }
            DrawContext $this$withTransform_u24lambda_u246$iv$iv3 = $this$withTransform_u24lambda_u246$iv$iv2;
            $this$withTransform_u24lambda_u246$iv$iv3.getCanvas().restore();
            $this$withTransform_u24lambda_u246$iv$iv3.mo5012setSizeuvyYCjk(previousSize$iv$iv2);
        } catch (Throwable th4) {
            th = th4;
            previousSize$iv$iv = previousSize$iv$iv2;
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
        }
    }

    /* JADX INFO: renamed from: drawDust-7fTDFas, reason: not valid java name */
    private static final void m87drawDust7fTDFas(DrawScope $this$drawDust_u2d7fTDFas, long center, float bodyR, List<DustSpec> list, float spinDeg, float breath, float energy, long color, long secondary) {
        float twoPi = 6.2831855f;
        List<DustSpec> $this$forEachIndexed$iv = list;
        int $i$f$forEachIndexed = 0;
        int index = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv = index + 1;
            if (index < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            DustSpec d = (DustSpec) item$iv;
            double ang = Math.toRadians(d.getAngle0() + (d.getSpeed() * spinDeg * energy) + (12.0f * breath * d.getSpeed()));
            float wobble = (((float) Math.sin((breath + d.getPhase()) * twoPi)) * 0.06f) + 1.0f;
            float dist = d.getRadiusFrac() * bodyR * wobble * ((0.08f * energy) + 0.95f);
            float twoPi2 = twoPi;
            Iterable $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
            int $i$f$forEachIndexed2 = $i$f$forEachIndexed;
            long p = OffsetKt.Offset(Offset.m4293getXimpl(center) + ((float) (Math.cos(ang) * ((double) dist))), Offset.m4294getYimpl(center) + ((float) (Math.sin(ang) * ((double) dist))));
            float pr = Size.m4361getMinDimensionimpl($this$drawDust_u2d7fTDFas.mo5090getSizeNHjbRc()) * d.getSizeFrac() * ((0.2f * energy) + 0.85f);
            float a = RangesKt.coerceIn(d.getAlpha() * energy * ((Math.abs((float) Math.sin(((double) (breath + d.getPhase())) * 3.141592653589793d)) * 0.45f) + 0.55f), 0.0f, 0.9f);
            long c = index % 3 == 0 ? color : secondary;
            DrawScope.m5071drawCircleVaOC9Bg$default($this$drawDust_u2d7fTDFas, Color.m4532copywmQWz5c(c, (14 & 1) != 0 ? Color.m4536getAlphaimpl(c) : a, (14 & 2) != 0 ? Color.m4540getRedimpl(c) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(c) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(c) : 0.0f), pr, p, 0.0f, null, null, 0, 120, null);
            if (a > 0.45f) {
                long j = c;
                DrawScope.m5071drawCircleVaOC9Bg$default($this$drawDust_u2d7fTDFas, Color.m4532copywmQWz5c(j, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j) : a * 0.25f, (14 & 2) != 0 ? Color.m4540getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j) : 0.0f), pr * 2.4f, p, 0.0f, null, null, 0, 120, null);
            }
            index = index$iv;
            twoPi = twoPi2;
            $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
            $i$f$forEachIndexed = $i$f$forEachIndexed2;
        }
    }
}
