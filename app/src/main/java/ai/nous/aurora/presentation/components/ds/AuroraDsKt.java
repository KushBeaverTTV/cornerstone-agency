package ai.nous.aurora.presentation.components.ds;

import ai.nous.aurora.domain.model.AstralReadabilityPolicy;
import ai.nous.aurora.domain.model.ComposerChipPolicy;
import ai.nous.aurora.domain.model.InboxActionLabelPolicy;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import ai.nous.aurora.presentation.theme.AuroraShapeKt;
import ai.nous.aurora.presentation.theme.AuroraSkyKt;
import ai.nous.aurora.presentation.theme.AuroraSpacing;
import ai.nous.aurora.presentation.theme.ThemeTokens;
import ai.nous.aurora.presentation.theme.ThemeTokensKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRightKt;
import androidx.compose.material.icons.filled.ArrowDropDownKt;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.SwitchDefaults;
import androidx.compose.material3.SwitchKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.GenericFontFamily;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: AuroraDs.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a7\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\n\u001a\u001f\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\r\u001a«\u0001\u0010\u000e\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00122\u0015\b\u0002\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00122\b\b\u0002\u0010\u0014\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\t2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\u001b\u001a\u0019\u0010\u001c\u001a\u00020\u00012\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0007¢\u0006\u0004\b\u001f\u0010 \u001aS\u0010!\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\b\b\u0002\u0010$\u001a\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010%\u001ab\u0010&\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\t2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00162\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00122\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010)\u001a\u00020\tH\u0007¢\u0006\u0002\u0010*\u001a5\u0010+\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\t2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010,\u001aQ\u0010-\u001a\u00020\u00012\u0006\u0010.\u001a\u00020\u00032\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001002\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u00101\u001a\u00020\u00032\b\b\u0002\u00102\u001a\u00020\t2\b\b\u0002\u00103\u001a\u00020\tH\u0007¢\u0006\u0002\u00104\u001aG\u00105\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00032\u0006\u00106\u001a\u00020\t2\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0001002\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u00102\u001a\u00020\tH\u0007¢\u0006\u0002\u00108\u001a+\u00109\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00032\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010;\u001a-\u0010<\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00032\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010>\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010?\u001as\u0010@\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010.\u001a\u00020\u00032\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00030B2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001002\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u00102\u001a\u00020\t2\b\b\u0002\u0010D\u001a\u00020\t2\b\b\u0002\u0010E\u001a\u00020\u00032\b\b\u0002\u0010F\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010G\u001aC\u0010H\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010.\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\n\b\u0002\u0010I\u001a\u0004\u0018\u00010J2\b\b\u0002\u0010K\u001a\u00020\u0016H\u0007¢\u0006\u0004\bL\u0010M¨\u0006N²\u0006\n\u0010O\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010P\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010Q\u001a\u00020\u0003X\u008a\u008e\u0002"}, d2 = {"AuroraPillAction", "", "label", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "emphasize", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/runtime/Composer;II)V", "AuroraSectionLabel", "title", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "AuroraListRow", "subtitle", "meta", "leading", "Landroidx/compose/runtime/Composable;", "trailing", "showChevron", "subtitleMaxLines", "", "elevated", "titleWeight", "Landroidx/compose/ui/text/font/FontWeight;", "highlighted", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZIZLandroidx/compose/ui/text/font/FontWeight;ZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;III)V", "AuroraRowDivider", "startInset", "Landroidx/compose/ui/unit/Dp;", "AuroraRowDivider-kHDZbjc", "(FLandroidx/compose/runtime/Composer;II)V", "AuroraContextBar", "actionLabel", "onAction", "actionEnabled", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;ZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "AuroraFilterChip", "selected", "count", "dense", "(Ljava/lang/String;ZLkotlin/jvm/functions/Function0;Ljava/lang/Integer;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLandroidx/compose/runtime/Composer;II)V", "AuroraChoiceChip", "(Ljava/lang/String;ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "AuroraTextFieldRow", "value", "onValueChange", "Lkotlin/Function1;", "placeholder", "enabled", "singleLine", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Ljava/lang/String;ZZLandroidx/compose/runtime/Composer;II)V", "AuroraSwitchRow", "checked", "onCheckedChange", "(Ljava/lang/String;ZLkotlin/jvm/functions/Function1;Ljava/lang/String;ZLandroidx/compose/runtime/Composer;II)V", "AuroraEmptyState", "body", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "AuroraSheetHeader", "onDone", "doneLabel", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)V", "AuroraDropdownRow", "options", "", "onSelect", "allowCustom", "customLabel", "blankLabel", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)V", "AuroraCompactSettingRow", "valueColor", "Landroidx/compose/ui/graphics/Color;", "valueMaxLines", "AuroraCompactSettingRow-Sj8uqqQ", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/graphics/Color;ILandroidx/compose/runtime/Composer;II)V", "app_debug", "expanded", "customMode", "customDraft"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AuroraDsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraChoiceChip$lambda$15(String str, boolean z, Function0 function0, Modifier modifier, int i, int i2, Composer composer, int i3) {
        AuroraChoiceChip(str, z, function0, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraCompactSettingRow_Sj8uqqQ$lambda$53(String str, String str2, Function0 function0, Color color, int i, int i2, int i3, Composer composer, int i4) {
        m127AuroraCompactSettingRowSj8uqqQ(str, str2, function0, color, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraContextBar$lambda$12(String str, String str2, String str3, Function0 function0, boolean z, Modifier modifier, int i, int i2, Composer composer, int i3) {
        AuroraContextBar(str, str2, str3, function0, z, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraDropdownRow$lambda$51(String str, String str2, List list, Function1 function1, String str3, boolean z, boolean z2, String str4, String str5, int i, int i2, Composer composer, int i3) {
        AuroraDropdownRow(str, str2, list, function1, str3, z, z2, str4, str5, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraEmptyState$lambda$21(String str, String str2, Modifier modifier, int i, int i2, Composer composer, int i3) {
        AuroraEmptyState(str, str2, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraFilterChip$lambda$14(String str, boolean z, Function0 function0, Integer num, Function2 function2, Modifier modifier, boolean z2, int i, int i2, Composer composer, int i3) {
        AuroraFilterChip(str, z, function0, num, function2, modifier, z2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraListRow$lambda$6(String str, String str2, String str3, Function0 function0, Function2 function2, Function2 function3, boolean z, int i, boolean z2, FontWeight fontWeight, boolean z3, Modifier modifier, int i2, int i3, int i4, Composer composer, int i5) {
        AuroraListRow(str, str2, str3, function0, function2, function3, z, i, z2, fontWeight, z3, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraPillAction$lambda$0(String str, Function0 function0, Modifier modifier, boolean z, int i, int i2, Composer composer, int i3) {
        AuroraPillAction(str, function0, modifier, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraRowDivider_kHDZbjc$lambda$7(float f, int i, int i2, Composer composer, int i3) {
        m128AuroraRowDividerkHDZbjc(f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraSectionLabel$lambda$2(String str, Modifier modifier, int i, int i2, Composer composer, int i3) {
        AuroraSectionLabel(str, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraSheetHeader$lambda$23(String str, Function0 function0, String str2, int i, int i2, Composer composer, int i3) {
        AuroraSheetHeader(str, function0, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraSwitchRow$lambda$19(String str, boolean z, Function1 function1, String str2, boolean z2, int i, int i2, Composer composer, int i3) {
        AuroraSwitchRow(str, z, function1, str2, z2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraTextFieldRow$lambda$16(String str, Function1 function1, Modifier modifier, String str2, boolean z, boolean z2, int i, int i2, Composer composer, int i3) {
        AuroraTextFieldRow(str, function1, modifier, str2, z, z2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void AuroraPillAction(final String label, final Function0<Unit> onClick, Modifier modifier, boolean emphasize, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        boolean emphasize2;
        long auroraAmber;
        Modifier modifierM589backgroundbw27NRU$default;
        Composer $composer2;
        Modifier modifier3;
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer3 = $composer.startRestartGroup(1487464680);
        ComposerKt.sourceInformation($composer3, "C(AuroraPillAction)P(1,3,2)83@3879L18,98@4491L11,84@3902L757:AuroraDs.kt#rimizs");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(label) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onClick) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            z = emphasize;
        } else if (($changed & 3072) == 0) {
            z = emphasize;
            $dirty |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = emphasize;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            emphasize2 = z;
            $composer2 = $composer3;
        } else {
            Modifier modifier4 = i2 != 0 ? Modifier.Companion : modifier2;
            emphasize2 = i3 != 0 ? false : z;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1487464680, $dirty2, -1, "ai.nous.aurora.presentation.components.ds.AuroraPillAction (AuroraDs.kt:80)");
            }
            RoundedCornerShape shape = AuroraShapeKt.getAuroraShapePill();
            String painted = InboxActionLabelPolicy.INSTANCE.paintLabel(label);
            Brush emphasizeBg = AuroraSkyKt.getAuroraGoldGradient($composer3, 0);
            int iM6883getCentere0LSkKk = ai.nous.aurora.compat.Compat.m6883getCentere0LSkKk(TextAlign.Companion);
            if (emphasize2) {
                $composer3.startReplaceGroup(-1531210189);
                ComposerKt.sourceInformation($composer3, "87@4001L14");
                auroraAmber = AuroraColorSchemeKt.getAuroraOnAccent($composer3, 0);
            } else {
                $composer3.startReplaceGroup(-1531209552);
                ComposerKt.sourceInformation($composer3, "87@4021L11");
                auroraAmber = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
            }
            $composer3.endReplaceGroup();
            long j = auroraAmber;
            GenericFontFamily monospace = FontFamily.Companion.getMonospace();
            long sp = TextUnitKt.getSp(12);
            FontWeight semiBold = FontWeight.Companion.getSemiBold();
            Modifier modifierClip = ClipKt.clip(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(modifier4, ai.nous.aurora.compat.Compat.m7016constructorimpl(48), 0.0f, 2, null), shape);
            $composer3.startReplaceGroup(-1531199618);
            ComposerKt.sourceInformation($composer3, "96@4417L11");
            if (emphasize2) {
                modifierM589backgroundbw27NRU$default = ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(Modifier.Companion, emphasizeBg, null, 0.0f, 6, null);
            } else {
                Modifier companion = Modifier.Companion;
                long auroraAmber2 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                modifierM589backgroundbw27NRU$default = ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(companion, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAmber2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAmber2) : 0.07f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAmber2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAmber2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAmber2) : 0.0f), null, 2, null);
            }
            $composer3.endReplaceGroup();
            Modifier modifierThen = modifierClip.then(modifierM589backgroundbw27NRU$default);
            float fM7016constructorimpl = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
            long auroraAmber3 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
            Modifier modifier5 = modifier4;
            $composer2 = $composer3;
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(painted, ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierThen, fM7016constructorimpl, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAmber3, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAmber3) : emphasize2 ? 0.0f : 0.7f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAmber3) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAmber3) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAmber3) : 0.0f), shape), false, null, null, onClick, 7, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(8)), j, sp, (FontStyle) null, semiBold, (FontFamily) monospace, 0L, (TextDecoration) null, ai.nous.aurora.compat.Compat.m6876boximpl(iM6883getCentere0LSkKk), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, 199680, 0, 130448);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier3;
            final boolean z2 = emphasize2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraDsKt.AuroraPillAction$lambda$0(label, onClick, modifier6, z2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void AuroraSectionLabel(final String title, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Intrinsics.checkNotNullParameter(title, "title");
        Composer $composer2 = $composer.startRestartGroup(522373208);
        ComposerKt.sourceInformation($composer2, "C(AuroraSectionLabel)P(1)111@4895L727:AuroraDs.kt#rimizs");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(title) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            Modifier modifier3 = i2 != 0 ? Modifier.Companion : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(522373208, $dirty, -1, "ai.nous.aurora.presentation.components.ds.AuroraSectionLabel (AuroraDs.kt:110)");
            }
            Modifier modifierM1037paddingqDBjuR0 = ai.nous.aurora.compat.Compat.m1037paddingqDBjuR0(modifier3, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), AuroraSpacing.INSTANCE.m237getLgD9Ej5fM(), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), AuroraSpacing.INSTANCE.m242getXsD9Ej5fM());
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM914spacedBy0680j_4 = ai.nous.aurora.compat.Compat.m914spacedBy0680j_4(Arrangement.INSTANCE, ai.nous.aurora.compat.Compat.m7016constructorimpl(6));
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalOrVerticalM914spacedBy0680j_4, centerVertically, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifierM1037paddingqDBjuR0);
            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            Modifier modifier4 = modifier3;
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
            Composer $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer2);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i4 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 39678596, "C123@5287L11,121@5237L121,128@5431L15,126@5367L249:AuroraDs.kt#rimizs");
            long auroraAmber = AuroraColorSchemeKt.getAuroraAmber($composer2, 0);
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("✦", (Modifier) null, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAmber, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAmber) : 0.75f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAmber) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAmber) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAmber) : 0.0f), TextUnitKt.getSp(9), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, 3078, 0, 131058);
            String upperCase = title.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(upperCase, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer2, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) FontFamily.Companion.getMonospace(), TextUnitKt.getSp(1.2d), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, 12782592, 0, 130834);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier2$c386 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraDsKt.AuroraSectionLabel$lambda$2(title, modifier2$c386, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:238:0x059c  */
    /* JADX WARN: Code duplicated, block: B:239:0x05ea  */
    /* JADX WARN: Code duplicated, block: B:242:0x05fc  */
    /* JADX WARN: Code duplicated, block: B:245:0x069f  */
    /* JADX WARN: Code duplicated, block: B:248:0x06ab  */
    /* JADX WARN: Code duplicated, block: B:249:0x06b1  */
    /* JADX WARN: Code duplicated, block: B:252:0x06e2  */
    /* JADX WARN: Code duplicated, block: B:255:0x06f5  */
    /* JADX WARN: Code duplicated, block: B:256:0x06f8  */
    /* JADX WARN: Code duplicated, block: B:260:0x0749  */
    /* JADX WARN: Code duplicated, block: B:261:0x0752  */
    /* JADX WARN: Code duplicated, block: B:264:0x07e1  */
    /* JADX WARN: Code duplicated, block: B:268:0x07ea  */
    /* JADX WARN: Code duplicated, block: B:270:0x07ed  */
    /* JADX WARN: Code duplicated, block: B:271:0x0849  */
    /* JADX WARN: Code duplicated, block: B:274:0x0862  */
    /* JADX WARN: Code duplicated, block: B:278:0x086b  */
    /* JADX WARN: Code duplicated, block: B:280:0x086e  */
    /* JADX WARN: Code duplicated, block: B:283:0x08e8  */
    /* JADX WARN: Code duplicated, block: B:284:0x0915  */
    /* JADX WARN: Code duplicated, block: B:286:0x0919  */
    /* JADX WARN: Code duplicated, block: B:287:0x0951  */
    /* JADX WARN: Code duplicated, block: B:290:0x0977  */
    public static final void AuroraListRow(String title, String subtitle, String meta, Function0<Unit> function0, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, boolean showChevron, int subtitleMaxLines, boolean elevated, FontWeight titleWeight, boolean highlighted, Modifier modifier, Composer $composer, final int $changed, final int $changed1, final int i) {
        String str;
        boolean showChevron2;
        Modifier modifier2;
        String subtitle2;
        boolean elevated2;
        FontWeight titleWeight2;
        boolean highlighted2;
        String meta2;
        Function0<Unit> function1;
        Function2<? super Composer, ? super Integer, Unit> function4;
        boolean showChevron3;
        int subtitleMaxLines2;
        int $dirty;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier companionM600borderxT4_qwU;
        MeasurePolicy measurePolicy$iv;
        MeasurePolicy measurePolicy$iv2;
        int compositeKeyHash$iv$iv;
        CompositionLocalMap localMap$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function6;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        CompositionLocalMap localMap$iv$iv2;
        MeasurePolicy measurePolicy$iv3;
        FontWeight semiBold;
        Modifier modifier3;
        int $dirty2;
        Composer $composer2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        String str2;
        boolean z;
        int subtitleMaxLines3;
        int i2;
        int i3;
        int i4;
        String str3;
        int i5;
        Composer $composer3;
        Function2<? super Composer, ? super Integer, Unit> function9;
        int subtitleMaxLines4;
        long jM73elevatedListRowBordervNxB06k;
        Intrinsics.checkNotNullParameter(title, "title");
        Composer $composer4 = $composer.startRestartGroup(-343992235);
        ComposerKt.sourceInformation($composer4, "C(AuroraListRow)P(9,7,3,5,2,11,6,8!1,10)163@6769L7,191@7905L2349:AuroraDs.kt#rimizs");
        int $dirty3 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer4.changed(title) ? 4 : 2;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer4.changed(subtitle) ? 32 : 16;
        }
        int i7 = i & 4;
        if (i7 != 0) {
            $dirty3 |= 384;
            str = meta;
        } else if (($changed & 384) == 0) {
            str = meta;
            $dirty3 |= $composer4.changed(str) ? 256 : 128;
        } else {
            str = meta;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty3 |= $composer4.changedInstance(function0) ? 2048 : 1024;
        }
        int i9 = i & 16;
        if (i9 != 0) {
            $dirty3 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty3 |= $composer4.changedInstance(function2) ? 16384 : 8192;
        }
        int i10 = i & 32;
        if (i10 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer4.changedInstance(function3) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty3 |= ((i & 64) == 0 && $composer4.changed(showChevron)) ? 1048576 : 524288;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer4.changed(subtitleMaxLines) ? 8388608 : 4194304;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer4.changed(elevated) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty3 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty3 |= $composer4.changed(titleWeight) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int i14 = i & 1024;
        if (i14 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer4.changed(highlighted) ? 4 : 2;
        }
        int i15 = i & 2048;
        if (i15 != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer4.changed(modifier) ? 32 : 16;
        }
        if (($dirty3 & 306783379) == 306783378 && ($dirty1 & 19) == 18 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            subtitle2 = subtitle;
            function1 = function0;
            function8 = function2;
            function9 = function3;
            showChevron3 = showChevron;
            subtitleMaxLines4 = subtitleMaxLines;
            elevated2 = elevated;
            titleWeight2 = titleWeight;
            highlighted2 = highlighted;
            modifier3 = modifier;
            $composer2 = $composer4;
            meta2 = str;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                String subtitle3 = i6 != 0 ? null : subtitle;
                String meta3 = i7 != 0 ? null : meta;
                Function0<Unit> function10 = i8 != 0 ? null : function0;
                Function2<? super Composer, ? super Integer, Unit> function11 = i9 != 0 ? null : function2;
                Function2<? super Composer, ? super Integer, Unit> function12 = i10 != 0 ? null : function3;
                if ((i & 64) != 0) {
                    showChevron2 = function10 != null;
                    $dirty3 &= -3670017;
                } else {
                    showChevron2 = showChevron;
                }
                int subtitleMaxLines5 = i11 != 0 ? 2 : subtitleMaxLines;
                boolean elevated3 = i12 != 0 ? false : elevated;
                FontWeight titleWeight3 = i13 != 0 ? null : titleWeight;
                boolean highlighted3 = i14 != 0 ? false : highlighted;
                if (i15 != 0) {
                    modifier2 = Modifier.Companion;
                    subtitle2 = subtitle3;
                    elevated2 = elevated3;
                    titleWeight2 = titleWeight3;
                    highlighted2 = highlighted3;
                    meta2 = meta3;
                    function1 = function10;
                    function4 = function11;
                    showChevron3 = showChevron2;
                    subtitleMaxLines2 = subtitleMaxLines5;
                    $dirty = $dirty3;
                    function5 = function12;
                } else {
                    modifier2 = modifier;
                    subtitle2 = subtitle3;
                    elevated2 = elevated3;
                    titleWeight2 = titleWeight3;
                    highlighted2 = highlighted3;
                    meta2 = meta3;
                    function1 = function10;
                    function4 = function11;
                    showChevron3 = showChevron2;
                    subtitleMaxLines2 = subtitleMaxLines5;
                    $dirty = $dirty3;
                    function5 = function12;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                }
                subtitle2 = subtitle;
                meta2 = meta;
                function1 = function0;
                function4 = function2;
                function5 = function3;
                showChevron3 = showChevron;
                subtitleMaxLines2 = subtitleMaxLines;
                elevated2 = elevated;
                titleWeight2 = titleWeight;
                highlighted2 = highlighted;
                modifier2 = modifier;
                $dirty = $dirty3;
            }
            $composer4.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-343992235, $dirty, $dirty1, "ai.nous.aurora.presentation.components.ds.AuroraListRow (AuroraDs.kt:161)");
            }
            RoundedCornerShape cardShape = ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(18));
            ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer4.consume(localThemeTokens);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ThemeTokens tokens = (ThemeTokens) objConsume;
            $composer4.startReplaceGroup(-526259784);
            ComposerKt.sourceInformation($composer4, "171@7109L12,184@7715L12");
            if (elevated2) {
                List stops = AstralReadabilityPolicy.INSTANCE.elevatedListRowStops(tokens);
                Modifier modifierM1035paddingVpY3zN4 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(6));
                float fM7016constructorimpl = tokens.getAstral() ? ai.nous.aurora.compat.Compat.m7016constructorimpl(10) : ai.nous.aurora.compat.Compat.m7016constructorimpl(7);
                RoundedCornerShape roundedCornerShape = cardShape;
                long auroraAccent = AuroraColorSchemeKt.getAuroraAccent($composer4, 0);
                long jM4532copywmQWz5c = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent) : tokens.getAstral() ? 0.18f : 0.1f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent) : 0.0f);
                long jM4560getBlack0d7_KjU = ai.nous.aurora.compat.Compat.m4560getBlack0d7_KjU(Color.Companion);
                Modifier modifierClip = ClipKt.clip(ai.nous.aurora.compat.Compat.m4198shadows4CzXII$default(modifierM1035paddingVpY3zN4, fM7016constructorimpl, roundedCornerShape, false, jM4532copywmQWz5c, ai.nous.aurora.compat.Compat.m4532copywmQWz5c(jM4560getBlack0d7_KjU, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(jM4560getBlack0d7_KjU) : 0.45f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(jM4560getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(jM4560getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(jM4560getBlack0d7_KjU) : 0.0f), 4, null), cardShape);
                Brush.Companion companion = Brush.Companion;
                List $this$map$iv = stops;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    AstralReadabilityPolicy.GradientStop it = (AstralReadabilityPolicy.GradientStop) item$iv$iv;
                    destination$iv$iv.add(TuplesKt.to(Float.valueOf(it.getOffset()), ai.nous.aurora.compat.Compat.m4524boximpl(it.m81getColor0d7_KjU())));
                }
                Collection $this$toTypedArray$iv = (List) destination$iv$iv;
                Pair[] pairArr = (Pair[]) $this$toTypedArray$iv.toArray(new Pair[0]);
                Modifier modifierBackground$default = ai.nous.aurora.compat.Compat.d_BackgroundKt_background_6(modifierClip, ai.nous.aurora.compat.Compat.m4492verticalGradient8A3gB4$default(companion, (Pair[]) Arrays.copyOf(pairArr, pairArr.length), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null);
                float fM7016constructorimpl2 = ai.nous.aurora.compat.Compat.m7016constructorimpl(1);
                if (highlighted2) {
                    long auroraAccent2 = AuroraColorSchemeKt.getAuroraAccent($composer4, 0);
                    jM73elevatedListRowBordervNxB06k = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAccent2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAccent2) : 0.82f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAccent2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAccent2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAccent2) : 0.0f);
                } else {
                    jM73elevatedListRowBordervNxB06k = AstralReadabilityPolicy.INSTANCE.m73elevatedListRowBordervNxB06k(tokens);
                }
                companionM600borderxT4_qwU = ai.nous.aurora.compat.Compat.m600borderxT4_qwU(modifierBackground$default, fM7016constructorimpl2, jM73elevatedListRowBordervNxB06k, cardShape);
            } else {
                companionM600borderxT4_qwU = Modifier.Companion;
            }
            $composer4.endReplaceGroup();
            Modifier chrome = companionM600borderxT4_qwU;
            Modifier modifierM1067heightInVpY3zN4$default = ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(modifier2, 0.0f, 1, null).then(chrome), elevated2 ? ai.nous.aurora.compat.Compat.m7016constructorimpl(68) : ai.nous.aurora.compat.Compat.m7016constructorimpl(56), 0.0f, 2, null);
            Modifier companionM622clickableXHw0xAI$default = Modifier.Companion;
            if (function1 != null) {
                companionM622clickableXHw0xAI$default = ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(companionM622clickableXHw0xAI$default, false, null, null, function1, 7, null);
            }
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(modifierM1067heightInVpY3zN4$default.then(companionM622clickableXHw0xAI$default), elevated2 ? ai.nous.aurora.compat.Compat.m7016constructorimpl(14) : AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(12));
            Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer4, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv4 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer4, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
            CompositionLocalMap localMap$iv$iv3 = $composer4.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.Companion.getConstructor();
            int subtitleMaxLines6 = subtitleMaxLines2;
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer4.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer4.startReusableNode();
            if ($composer4.getInserting()) {
                $composer4.createNode(constructor2);
            } else {
                $composer4.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer4);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv4, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                measurePolicy$iv = measurePolicy$iv4;
            } else {
                measurePolicy$iv = measurePolicy$iv4;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                int i16 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer4, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i17 = ((384 >> 6) & 112) | 6;
                RowScope $this$AuroraListRow_u24lambda_u245 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer4, -1487121823, "C217@8779L1091:AuroraDs.kt#rimizs");
                $composer4.startReplaceGroup(1891689384);
                ComposerKt.sourceInformation($composer4, "209@8574L12,204@8394L208,211@8615L29");
                if (highlighted2) {
                    BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(3)), ai.nous.aurora.compat.Compat.m7016constructorimpl(38)), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraAccent($composer4, 0), null, 2, null), $composer4, 0);
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), $composer4, 6);
                }
                $composer4.endReplaceGroup();
                $composer4.startReplaceGroup(1891698800);
                ComposerKt.sourceInformation($composer4, "214@8698L9,215@8720L40");
                if (function4 != null) {
                    function4.invoke($composer4, Integer.valueOf(($dirty >> 12) & 14));
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, AuroraSpacing.INSTANCE.m238getMdD9Ej5fM()), $composer4, 6);
                }
                $composer4.endReplaceGroup();
                Modifier modifier$iv2 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraListRow_u24lambda_u245, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer4, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getStart();
                measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv2 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer4, modifier$iv2);
                constructor = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer4.startReusableNode();
                if ($composer4.getInserting()) {
                    function6 = constructor;
                    $composer4.createNode(function6);
                } else {
                    function6 = constructor;
                    $composer4.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer4);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    localMap$iv$iv2 = localMap$iv$iv;
                    measurePolicy$iv3 = measurePolicy$iv2;
                } else {
                    localMap$iv$iv2 = localMap$iv$iv;
                    measurePolicy$iv3 = measurePolicy$iv2;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
                    int i18 = ($changed$iv$iv$iv2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i19 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, 940286299, "C220@8885L10,218@8832L257:AuroraDs.kt#rimizs");
                    long auroraText = AuroraColorSchemeKt.getAuroraText($composer4, 0);
                    if (titleWeight2 == null) {
                        semiBold = FontWeight.Companion.getSemiBold();
                    } else {
                        semiBold = titleWeight2;
                    }
                    modifier3 = modifier2;
                    $dirty2 = $dirty;
                    $composer2 = $composer4;
                    function7 = function5;
                    function8 = function4;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, auroraText, TextUnitKt.getSp(16), (FontStyle) null, semiBold, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, ($dirty & 14) | 3072, 3120, 120786);
                    $composer4.startReplaceGroup(30339793);
                    ComposerKt.sourceInformation($composer4, "227@9151L29,230@9261L15,228@9197L256");
                    str2 = subtitle2;
                    if (str2 != null || StringsKt.isBlank(str2)) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        subtitleMaxLines3 = subtitleMaxLines6;
                        i2 = 0;
                        i3 = 6;
                        i4 = 1;
                    } else {
                        i3 = 6;
                        SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(3)), $composer4, 6);
                        i2 = 0;
                        subtitleMaxLines3 = subtitleMaxLines6;
                        i4 = 1;
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer4, 0), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, RangesKt.coerceAtLeast(subtitleMaxLines3, 1), 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, (($dirty2 >> 3) & 14) | 3072, 48, 120818);
                    }
                    $composer4.endReplaceGroup();
                    $composer4.startReplaceGroup(30351904);
                    ComposerKt.sourceInformation($composer4, "237@9525L29,240@9631L15,238@9571L275");
                    str3 = meta2;
                    if (str3 != null || StringsKt.isBlank(str3)) {
                        i5 = i4;
                    } else {
                        i5 = i2;
                    }
                    if (i5 == 0) {
                        SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(2)), $composer4, i3);
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(meta2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer4, i2), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, (($dirty2 >> 6) & 14) | 3072, 3120, 120754);
                    }
                    $composer4.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    if (function7 != null) {
                        $composer3 = $composer4;
                        $composer3.startReplaceGroup(-1485651866);
                        ComposerKt.sourceInformation($composer3, "249@9915L40,250@9968L10");
                        SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, AuroraSpacing.INSTANCE.m240getSmD9Ej5fM()), $composer3, i3);
                        function7.invoke($composer3, Integer.valueOf(($dirty2 >> 15) & 14));
                        $composer3.endReplaceGroup();
                    } else {
                        $composer3 = $composer4;
                        if (showChevron3) {
                            $composer3.startReplaceGroup(-1485538096);
                            ComposerKt.sourceInformation($composer3, "255@10159L15,252@10025L213");
                            ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(KeyboardArrowRightKt.getKeyboardArrowRight(Icons.AutoMirrored.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(18)), AuroraColorSchemeKt.getAuroraTextFaint($composer3, i2), $composer3, 432, 0);
                            $composer3.endReplaceGroup();
                        } else {
                            $composer3.startReplaceGroup(-1485310339);
                            $composer3.endReplaceGroup();
                        }
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function9 = function7;
                    subtitleMaxLines4 = subtitleMaxLines3;
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
                int i110 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer4, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i111 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer4, 940286299, "C220@8885L10,218@8832L257:AuroraDs.kt#rimizs");
                long auroraText2 = AuroraColorSchemeKt.getAuroraText($composer4, 0);
                if (titleWeight2 == null) {
                    semiBold = FontWeight.Companion.getSemiBold();
                } else {
                    semiBold = titleWeight2;
                }
                modifier3 = modifier2;
                $dirty2 = $dirty;
                $composer2 = $composer4;
                function7 = function5;
                function8 = function4;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, auroraText2, TextUnitKt.getSp(16), (FontStyle) null, semiBold, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, ($dirty & 14) | 3072, 3120, 120786);
                $composer4.startReplaceGroup(30339793);
                ComposerKt.sourceInformation($composer4, "227@9151L29,230@9261L15,228@9197L256");
                str2 = subtitle2;
                if (str2 != null) {
                    z = true;
                } else {
                    z = true;
                }
                if (z) {
                    i3 = 6;
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(3)), $composer4, 6);
                    i2 = 0;
                    subtitleMaxLines3 = subtitleMaxLines6;
                    i4 = 1;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer4, 0), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, RangesKt.coerceAtLeast(subtitleMaxLines3, 1), 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, (($dirty2 >> 3) & 14) | 3072, 48, 120818);
                } else {
                    subtitleMaxLines3 = subtitleMaxLines6;
                    i2 = 0;
                    i3 = 6;
                    i4 = 1;
                }
                $composer4.endReplaceGroup();
                $composer4.startReplaceGroup(30351904);
                ComposerKt.sourceInformation($composer4, "237@9525L29,240@9631L15,238@9571L275");
                str3 = meta2;
                if (str3 != null) {
                    i5 = i4;
                } else {
                    i5 = i4;
                }
                if (i5 == 0) {
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(2)), $composer4, i3);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(meta2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer4, i2), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, (($dirty2 >> 6) & 14) | 3072, 3120, 120754);
                }
                $composer4.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                if (function7 != null) {
                    $composer3 = $composer4;
                    $composer3.startReplaceGroup(-1485651866);
                    ComposerKt.sourceInformation($composer3, "249@9915L40,250@9968L10");
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, AuroraSpacing.INSTANCE.m240getSmD9Ej5fM()), $composer3, i3);
                    function7.invoke($composer3, Integer.valueOf(($dirty2 >> 15) & 14));
                    $composer3.endReplaceGroup();
                } else {
                    $composer3 = $composer4;
                    if (showChevron3) {
                        $composer3.startReplaceGroup(-1485538096);
                        ComposerKt.sourceInformation($composer3, "255@10159L15,252@10025L213");
                        ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(KeyboardArrowRightKt.getKeyboardArrowRight(Icons.AutoMirrored.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(18)), AuroraColorSchemeKt.getAuroraTextFaint($composer3, i2), $composer3, 432, 0);
                        $composer3.endReplaceGroup();
                    } else {
                        $composer3.startReplaceGroup(-1485310339);
                        $composer3.endReplaceGroup();
                    }
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function9 = function7;
                subtitleMaxLines4 = subtitleMaxLines3;
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i112 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer4, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i113 = ((384 >> 6) & 112) | 6;
            RowScope $this$AuroraListRow_u24lambda_u246 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer4, -1487121823, "C217@8779L1091:AuroraDs.kt#rimizs");
            $composer4.startReplaceGroup(1891689384);
            ComposerKt.sourceInformation($composer4, "209@8574L12,204@8394L208,211@8615L29");
            if (highlighted2) {
                BoxKt.Box(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(3)), ai.nous.aurora.compat.Compat.m7016constructorimpl(38)), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraAccent($composer4, 0), null, 2, null), $composer4, 0);
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), $composer4, 6);
            }
            $composer4.endReplaceGroup();
            $composer4.startReplaceGroup(1891698800);
            ComposerKt.sourceInformation($composer4, "214@8698L9,215@8720L40");
            if (function4 != null) {
                function4.invoke($composer4, Integer.valueOf(($dirty >> 12) & 14));
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, AuroraSpacing.INSTANCE.m238getMdD9Ej5fM()), $composer4, 6);
            }
            $composer4.endReplaceGroup();
            Modifier modifier$iv3 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraListRow_u24lambda_u246, Modifier.Companion, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer4, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.Companion.getStart();
            measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv3 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
            localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer4, modifier$iv3);
            constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer4.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer4.startReusableNode();
            if ($composer4.getInserting()) {
                function6 = constructor;
                $composer4.createNode(function6);
            } else {
                function6 = constructor;
                $composer4.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer4);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                localMap$iv$iv2 = localMap$iv$iv;
                measurePolicy$iv3 = measurePolicy$iv2;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
                int i114 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer4, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                int i115 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer4, 940286299, "C220@8885L10,218@8832L257:AuroraDs.kt#rimizs");
                long auroraText3 = AuroraColorSchemeKt.getAuroraText($composer4, 0);
                if (titleWeight2 == null) {
                    semiBold = FontWeight.Companion.getSemiBold();
                } else {
                    semiBold = titleWeight2;
                }
                modifier3 = modifier2;
                $dirty2 = $dirty;
                $composer2 = $composer4;
                function7 = function5;
                function8 = function4;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, auroraText3, TextUnitKt.getSp(16), (FontStyle) null, semiBold, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, ($dirty & 14) | 3072, 3120, 120786);
                $composer4.startReplaceGroup(30339793);
                ComposerKt.sourceInformation($composer4, "227@9151L29,230@9261L15,228@9197L256");
                str2 = subtitle2;
                if (str2 != null) {
                    z = true;
                } else {
                    z = true;
                }
                if (z) {
                    i3 = 6;
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(3)), $composer4, 6);
                    i2 = 0;
                    subtitleMaxLines3 = subtitleMaxLines6;
                    i4 = 1;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer4, 0), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, RangesKt.coerceAtLeast(subtitleMaxLines3, 1), 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, (($dirty2 >> 3) & 14) | 3072, 48, 120818);
                } else {
                    subtitleMaxLines3 = subtitleMaxLines6;
                    i2 = 0;
                    i3 = 6;
                    i4 = 1;
                }
                $composer4.endReplaceGroup();
                $composer4.startReplaceGroup(30351904);
                ComposerKt.sourceInformation($composer4, "237@9525L29,240@9631L15,238@9571L275");
                str3 = meta2;
                if (str3 != null) {
                    i5 = i4;
                } else {
                    i5 = i4;
                }
                if (i5 == 0) {
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(2)), $composer4, i3);
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(meta2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer4, i2), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, (($dirty2 >> 6) & 14) | 3072, 3120, 120754);
                }
                $composer4.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                if (function7 != null) {
                    $composer3 = $composer4;
                    $composer3.startReplaceGroup(-1485651866);
                    ComposerKt.sourceInformation($composer3, "249@9915L40,250@9968L10");
                    SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, AuroraSpacing.INSTANCE.m240getSmD9Ej5fM()), $composer3, i3);
                    function7.invoke($composer3, Integer.valueOf(($dirty2 >> 15) & 14));
                    $composer3.endReplaceGroup();
                } else {
                    $composer3 = $composer4;
                    if (showChevron3) {
                        $composer3.startReplaceGroup(-1485538096);
                        ComposerKt.sourceInformation($composer3, "255@10159L15,252@10025L213");
                        ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(KeyboardArrowRightKt.getKeyboardArrowRight(Icons.AutoMirrored.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(18)), AuroraColorSchemeKt.getAuroraTextFaint($composer3, i2), $composer3, 432, 0);
                        $composer3.endReplaceGroup();
                    } else {
                        $composer3.startReplaceGroup(-1485310339);
                        $composer3.endReplaceGroup();
                    }
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function9 = function7;
                subtitleMaxLines4 = subtitleMaxLines3;
            } else {
                localMap$iv$iv2 = localMap$iv$iv;
                measurePolicy$iv3 = measurePolicy$iv2;
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
            int i116 = ($changed$iv$iv$iv3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer4, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
            int i117 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer4, 940286299, "C220@8885L10,218@8832L257:AuroraDs.kt#rimizs");
            long auroraText4 = AuroraColorSchemeKt.getAuroraText($composer4, 0);
            if (titleWeight2 == null) {
                semiBold = FontWeight.Companion.getSemiBold();
            } else {
                semiBold = titleWeight2;
            }
            modifier3 = modifier2;
            $dirty2 = $dirty;
            $composer2 = $composer4;
            function7 = function5;
            function8 = function4;
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, auroraText4, TextUnitKt.getSp(16), (FontStyle) null, semiBold, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, ($dirty & 14) | 3072, 3120, 120786);
            $composer4.startReplaceGroup(30339793);
            ComposerKt.sourceInformation($composer4, "227@9151L29,230@9261L15,228@9197L256");
            str2 = subtitle2;
            if (str2 != null) {
                z = true;
            } else {
                z = true;
            }
            if (z) {
                i3 = 6;
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(3)), $composer4, 6);
                i2 = 0;
                subtitleMaxLines3 = subtitleMaxLines6;
                i4 = 1;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer4, 0), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, RangesKt.coerceAtLeast(subtitleMaxLines3, 1), 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, (($dirty2 >> 3) & 14) | 3072, 48, 120818);
            } else {
                subtitleMaxLines3 = subtitleMaxLines6;
                i2 = 0;
                i3 = 6;
                i4 = 1;
            }
            $composer4.endReplaceGroup();
            $composer4.startReplaceGroup(30351904);
            ComposerKt.sourceInformation($composer4, "237@9525L29,240@9631L15,238@9571L275");
            str3 = meta2;
            if (str3 != null) {
                i5 = i4;
            } else {
                i5 = i4;
            }
            if (i5 == 0) {
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(2)), $composer4, i3);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(meta2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer4, i2), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, (($dirty2 >> 6) & 14) | 3072, 3120, 120754);
            }
            $composer4.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            $composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            if (function7 != null) {
                $composer3 = $composer4;
                $composer3.startReplaceGroup(-1485651866);
                ComposerKt.sourceInformation($composer3, "249@9915L40,250@9968L10");
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, AuroraSpacing.INSTANCE.m240getSmD9Ej5fM()), $composer3, i3);
                function7.invoke($composer3, Integer.valueOf(($dirty2 >> 15) & 14));
                $composer3.endReplaceGroup();
            } else {
                $composer3 = $composer4;
                if (showChevron3) {
                    $composer3.startReplaceGroup(-1485538096);
                    ComposerKt.sourceInformation($composer3, "255@10159L15,252@10025L213");
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(KeyboardArrowRightKt.getKeyboardArrowRight(Icons.AutoMirrored.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(18)), AuroraColorSchemeKt.getAuroraTextFaint($composer3, i2), $composer3, 432, 0);
                    $composer3.endReplaceGroup();
                } else {
                    $composer3.startReplaceGroup(-1485310339);
                    $composer3.endReplaceGroup();
                }
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function9 = function7;
            subtitleMaxLines4 = subtitleMaxLines3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final String str4 = subtitle2;
            final String str5 = meta2;
            final Function0<Unit> function13 = function1;
            final Function2<? super Composer, ? super Integer, Unit> function14 = function8;
            final Function2<? super Composer, ? super Integer, Unit> function15 = function9;
            final boolean z2 = showChevron3;
            final int i20 = subtitleMaxLines4;
            final boolean z3 = elevated2;
            final FontWeight fontWeight = titleWeight2;
            final boolean z4 = highlighted2;
            final Modifier modifier4 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraDsKt.AuroraListRow$lambda$6(title, str4, str5, function13, function14, function15, z2, i20, z3, fontWeight, z4, modifier4, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: AuroraRowDivider-kHDZbjc, reason: not valid java name */
    public static final void m128AuroraRowDividerkHDZbjc(float startInset, Composer $composer, final int $changed, final int i) {
        Composer $composer2 = $composer.startRestartGroup(210950488);
        ComposerKt.sourceInformation($composer2, "C(AuroraRowDivider)P(0:c#ui.unit.Dp)264@10362L14,264@10336L90:AuroraDs.kt#rimizs");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(startInset) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 3) != 2 || !$composer2.getSkipping()) {
            if (i2 != 0) {
                startInset = AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(210950488, $dirty2, -1, "ai.nous.aurora.presentation.components.ds.AuroraRowDivider (AuroraDs.kt:263)");
            }
            ai.nous.aurora.compat.Compat.m2446HorizontalDivider9IZ8Weo(ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(Modifier.Companion, startInset, 0.0f, 0.0f, 0.0f, 14, null), 0.0f, AuroraColorSchemeKt.getAuroraHairline($composer2, 0), $composer2, 0, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final float startInset$c1225 = startInset;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraDsKt.AuroraRowDivider_kHDZbjc$lambda$7(startInset$c1225, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:106:0x02b0  */
    /* JADX WARN: Code duplicated, block: B:109:0x02bc  */
    /* JADX WARN: Code duplicated, block: B:110:0x02c0  */
    /* JADX WARN: Code duplicated, block: B:113:0x02ee  */
    /* JADX WARN: Code duplicated, block: B:116:0x0301  */
    /* JADX WARN: Code duplicated, block: B:117:0x0304  */
    /* JADX WARN: Code duplicated, block: B:121:0x03dd  */
    /* JADX WARN: Code duplicated, block: B:125:0x03e6  */
    /* JADX WARN: Code duplicated, block: B:127:0x03ea  */
    /* JADX WARN: Code duplicated, block: B:128:0x0438  */
    /* JADX WARN: Code duplicated, block: B:131:0x0464  */
    /* JADX WARN: Code duplicated, block: B:133:0x0468  */
    /* JADX WARN: Code duplicated, block: B:135:0x047b  */
    /* JADX WARN: Code duplicated, block: B:136:0x047e  */
    /* JADX WARN: Code duplicated, block: B:140:0x0486  */
    /* JADX WARN: Code duplicated, block: B:143:0x0493  */
    /* JADX WARN: Code duplicated, block: B:147:0x04a0  */
    /* JADX WARN: Code duplicated, block: B:151:0x04bf  */
    /* JADX WARN: Code duplicated, block: B:153:0x04d9  */
    /* JADX WARN: Code duplicated, block: B:154:0x04dc  */
    /* JADX WARN: Code duplicated, block: B:157:0x050e  */
    public static final void AuroraContextBar(final String title, String subtitle, String actionLabel, Function0<Unit> function0, boolean actionEnabled, Modifier modifier, Composer $composer, final int $changed, final int i) {
        String str;
        String str2;
        Function0<Unit> function1;
        boolean actionEnabled2;
        Modifier modifier2;
        String subtitle2;
        String actionLabel2;
        MeasurePolicy measurePolicy$iv;
        int $dirty;
        MeasurePolicy measurePolicy$iv2;
        int compositeKeyHash$iv$iv;
        CompositionLocalMap localMap$iv$iv;
        Function0<ComposeUiNode> constructor;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        CompositionLocalMap localMap$iv$iv2;
        MeasurePolicy measurePolicy$iv3;
        boolean actionEnabled3;
        Modifier modifier3;
        Function0<Unit> function2;
        String str3;
        boolean z;
        int $dirty2;
        int i2;
        boolean actionEnabled4;
        Function0<Unit> function3;
        Composer $composer2;
        boolean actionEnabled5;
        Function0<Unit> function4;
        int i3;
        int i4;
        Object value$iv;
        Modifier companionAlpha;
        Intrinsics.checkNotNullParameter(title, "title");
        Composer $composer3 = $composer.startRestartGroup(919212261);
        ComposerKt.sourceInformation($composer3, "C(AuroraContextBar)P(5,4,1,3)276@10660L1151,306@11842L14,306@11816L41:AuroraDs.kt#rimizs");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed(title) ? 4 : 2;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty3 |= 48;
            str = subtitle;
        } else if (($changed & 48) == 0) {
            str = subtitle;
            $dirty3 |= $composer3.changed(str) ? 32 : 16;
        } else {
            str = subtitle;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty3 |= 384;
            str2 = actionLabel;
        } else if (($changed & 384) == 0) {
            str2 = actionLabel;
            $dirty3 |= $composer3.changed(str2) ? 256 : 128;
        } else {
            str2 = actionLabel;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty3 |= 3072;
            function1 = function0;
        } else if (($changed & 3072) == 0) {
            function1 = function0;
            $dirty3 |= $composer3.changedInstance(function1) ? 2048 : 1024;
        } else {
            function1 = function0;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty3 |= 24576;
            actionEnabled2 = actionEnabled;
        } else if (($changed & 24576) == 0) {
            actionEnabled2 = actionEnabled;
            $dirty3 |= $composer3.changed(actionEnabled2) ? 16384 : 8192;
        } else {
            actionEnabled2 = actionEnabled;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        if (($dirty3 & 74899) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            subtitle2 = str;
            actionLabel2 = str2;
            modifier3 = modifier2;
            $composer2 = $composer3;
            actionEnabled5 = actionEnabled2;
            function4 = function1;
        } else {
            subtitle2 = i5 != 0 ? null : str;
            actionLabel2 = i6 != 0 ? null : str2;
            Function0<Unit> function5 = i7 != 0 ? null : function1;
            if (i8 != 0) {
                actionEnabled2 = true;
            }
            if (i9 != 0) {
                modifier2 = Modifier.Companion;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(919212261, $dirty3, -1, "ai.nous.aurora.presentation.components.ds.AuroraContextBar (AuroraDs.kt:275)");
            }
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(modifier2, 0.0f, 1, null), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(12));
            Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv4 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor2);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv4, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                measurePolicy$iv = measurePolicy$iv4;
            } else {
                measurePolicy$iv = measurePolicy$iv4;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                int i10 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i11 = ((384 >> 6) & 112) | 6;
                RowScope $this$AuroraContextBar_u24lambda_u2411 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1788218559, "C282@10868L501:AuroraDs.kt#rimizs");
                Modifier modifier$iv2 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraContextBar_u24lambda_u2411, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getStart();
                $dirty = $dirty3;
                measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv2 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                constructor = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor);
                } else {
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    localMap$iv$iv2 = localMap$iv$iv;
                    measurePolicy$iv3 = measurePolicy$iv2;
                } else {
                    localMap$iv$iv2 = localMap$iv$iv;
                    measurePolicy$iv3 = measurePolicy$iv2;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
                    int i12 = ($changed$iv$iv$iv2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i13 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 327176085, "C283@10941L10,283@10921L83:AuroraDs.kt#rimizs");
                    actionEnabled3 = actionEnabled2;
                    modifier3 = modifier2;
                    function2 = function5;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty & 14) | 199680, 0, 131026);
                    $composer3.startReplaceGroup(-1236368946);
                    ComposerKt.sourceInformation($composer3, "287@11130L15,285@11066L279");
                    str3 = subtitle2;
                    if (str3 != null || StringsKt.isBlank(str3)) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        $dirty2 = $dirty;
                        i2 = 0;
                    } else {
                        i2 = 0;
                        $dirty2 = $dirty;
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 3) & 14) | 3072, 3120, 120754);
                    }
                    $composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.startReplaceGroup(-334763324);
                    ComposerKt.sourceInformation($composer3, "299@11611L33,297@11530L265");
                    if (actionLabel2 != null) {
                        function3 = function2;
                        if (function3 != null) {
                            $composer3.startReplaceGroup(-334756262);
                            ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                            if ((57344 & $dirty2) == 16384) {
                                i3 = 1;
                            } else {
                                i3 = i2;
                            }
                            i4 = i3 | (($dirty2 & 7168) != 2048 ? i2 : 1);
                            Object it$iv = $composer3.rememberedValue();
                            if (i4 == 0 || it$iv == Composer.Companion.getEmpty()) {
                                actionEnabled4 = actionEnabled3;
                                final boolean actionEnabled4$c1490 = actionEnabled4;
                                final Function0<Unit> function3$c1490 = function3;
                                value$iv = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda11
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return AuroraDsKt.AuroraContextBar$lambda$11$lambda$10$lambda$9(actionEnabled4$c1490, function3$c1490);
                                    }
                                };
                                $composer3.updateRememberedValue(value$iv);
                            } else {
                                value$iv = it$iv;
                                actionEnabled4 = actionEnabled3;
                            }
                            Function0 function6 = (Function0) value$iv;
                            $composer3.endReplaceGroup();
                            Modifier companion = Modifier.Companion;
                            companionAlpha = Modifier.Companion;
                            if (!actionEnabled4) {
                                companionAlpha = AlphaKt.alpha(companionAlpha, 0.5f);
                            }
                            AuroraPillAction(actionLabel2, function6, companion.then(companionAlpha), false, $composer3, ($dirty2 >> 6) & 14, 8);
                        } else {
                            actionEnabled4 = actionEnabled3;
                        }
                    } else {
                        actionEnabled4 = actionEnabled3;
                        function3 = function2;
                    }
                    $composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2 = $composer3;
                    ai.nous.aurora.compat.Compat.m2446HorizontalDivider9IZ8Weo(null, 0.0f, AuroraColorSchemeKt.getAuroraHairline($composer2, i2), $composer2, 0, 3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    actionEnabled5 = actionEnabled4;
                    function4 = function3;
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
                int i14 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i15 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 327176085, "C283@10941L10,283@10921L83:AuroraDs.kt#rimizs");
                actionEnabled3 = actionEnabled2;
                modifier3 = modifier2;
                function2 = function5;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty & 14) | 199680, 0, 131026);
                $composer3.startReplaceGroup(-1236368946);
                ComposerKt.sourceInformation($composer3, "287@11130L15,285@11066L279");
                str3 = subtitle2;
                if (str3 != null) {
                    z = true;
                } else {
                    z = true;
                }
                if (z) {
                    i2 = 0;
                    $dirty2 = $dirty;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 3) & 14) | 3072, 3120, 120754);
                } else {
                    $dirty2 = $dirty;
                    i2 = 0;
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.startReplaceGroup(-334763324);
                ComposerKt.sourceInformation($composer3, "299@11611L33,297@11530L265");
                if (actionLabel2 != null) {
                    function3 = function2;
                    if (function3 != null) {
                        $composer3.startReplaceGroup(-334756262);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                        if ((57344 & $dirty2) == 16384) {
                            i3 = 1;
                        } else {
                            i3 = i2;
                        }
                        i4 = i3 | (($dirty2 & 7168) != 2048 ? i2 : 1);
                        Object it$iv2 = $composer3.rememberedValue();
                        if (i4 == 0) {
                            actionEnabled4 = actionEnabled3;
                            final boolean actionEnabled4$c1582 = actionEnabled4;
                            final Function0<Unit> function3$c1582 = function3;
                            value$iv = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda11
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return AuroraDsKt.AuroraContextBar$lambda$11$lambda$10$lambda$9(actionEnabled4$c1582, function3$c1582);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv);
                        } else {
                            actionEnabled4 = actionEnabled3;
                            final boolean actionEnabled4$c1591 = actionEnabled4;
                            final Function0<Unit> function3$c1591 = function3;
                            value$iv = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda11
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return AuroraDsKt.AuroraContextBar$lambda$11$lambda$10$lambda$9(actionEnabled4$c1591, function3$c1591);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv);
                        }
                        Function0 function7 = (Function0) value$iv;
                        $composer3.endReplaceGroup();
                        Modifier companion2 = Modifier.Companion;
                        companionAlpha = Modifier.Companion;
                        if (!actionEnabled4) {
                            companionAlpha = AlphaKt.alpha(companionAlpha, 0.5f);
                        }
                        AuroraPillAction(actionLabel2, function7, companion2.then(companionAlpha), false, $composer3, ($dirty2 >> 6) & 14, 8);
                    } else {
                        actionEnabled4 = actionEnabled3;
                    }
                } else {
                    actionEnabled4 = actionEnabled3;
                    function3 = function2;
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2 = $composer3;
                ai.nous.aurora.compat.Compat.m2446HorizontalDivider9IZ8Weo(null, 0.0f, AuroraColorSchemeKt.getAuroraHairline($composer2, i2), $composer2, 0, 3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                actionEnabled5 = actionEnabled4;
                function4 = function3;
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i16 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i17 = ((384 >> 6) & 112) | 6;
            RowScope $this$AuroraContextBar_u24lambda_u2412 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1788218559, "C282@10868L501:AuroraDs.kt#rimizs");
            Modifier modifier$iv3 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraContextBar_u24lambda_u2412, Modifier.Companion, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.Companion.getStart();
            $dirty = $dirty3;
            measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv3 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
            constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                localMap$iv$iv2 = localMap$iv$iv;
                measurePolicy$iv3 = measurePolicy$iv2;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
                int i18 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                int i19 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 327176085, "C283@10941L10,283@10921L83:AuroraDs.kt#rimizs");
                actionEnabled3 = actionEnabled2;
                modifier3 = modifier2;
                function2 = function5;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty & 14) | 199680, 0, 131026);
                $composer3.startReplaceGroup(-1236368946);
                ComposerKt.sourceInformation($composer3, "287@11130L15,285@11066L279");
                str3 = subtitle2;
                if (str3 != null) {
                    z = true;
                } else {
                    z = true;
                }
                if (z) {
                    i2 = 0;
                    $dirty2 = $dirty;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 3) & 14) | 3072, 3120, 120754);
                } else {
                    $dirty2 = $dirty;
                    i2 = 0;
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.startReplaceGroup(-334763324);
                ComposerKt.sourceInformation($composer3, "299@11611L33,297@11530L265");
                if (actionLabel2 != null) {
                    function3 = function2;
                    if (function3 != null) {
                        $composer3.startReplaceGroup(-334756262);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                        if ((57344 & $dirty2) == 16384) {
                            i3 = 1;
                        } else {
                            i3 = i2;
                        }
                        i4 = i3 | (($dirty2 & 7168) != 2048 ? i2 : 1);
                        Object it$iv3 = $composer3.rememberedValue();
                        if (i4 == 0) {
                            actionEnabled4 = actionEnabled3;
                            final boolean actionEnabled4$c1718 = actionEnabled4;
                            final Function0<Unit> function3$c1718 = function3;
                            value$iv = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda11
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return AuroraDsKt.AuroraContextBar$lambda$11$lambda$10$lambda$9(actionEnabled4$c1718, function3$c1718);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv);
                        } else {
                            actionEnabled4 = actionEnabled3;
                            final boolean actionEnabled4$c1727 = actionEnabled4;
                            final Function0<Unit> function3$c1727 = function3;
                            value$iv = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda11
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return AuroraDsKt.AuroraContextBar$lambda$11$lambda$10$lambda$9(actionEnabled4$c1727, function3$c1727);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv);
                        }
                        Function0 function8 = (Function0) value$iv;
                        $composer3.endReplaceGroup();
                        Modifier companion3 = Modifier.Companion;
                        companionAlpha = Modifier.Companion;
                        if (!actionEnabled4) {
                            companionAlpha = AlphaKt.alpha(companionAlpha, 0.5f);
                        }
                        AuroraPillAction(actionLabel2, function8, companion3.then(companionAlpha), false, $composer3, ($dirty2 >> 6) & 14, 8);
                    } else {
                        actionEnabled4 = actionEnabled3;
                    }
                } else {
                    actionEnabled4 = actionEnabled3;
                    function3 = function2;
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2 = $composer3;
                ai.nous.aurora.compat.Compat.m2446HorizontalDivider9IZ8Weo(null, 0.0f, AuroraColorSchemeKt.getAuroraHairline($composer2, i2), $composer2, 0, 3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                actionEnabled5 = actionEnabled4;
                function4 = function3;
            } else {
                localMap$iv$iv2 = localMap$iv$iv;
                measurePolicy$iv3 = measurePolicy$iv2;
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
            int i110 = ($changed$iv$iv$iv3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
            int i111 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 327176085, "C283@10941L10,283@10921L83:AuroraDs.kt#rimizs");
            actionEnabled3 = actionEnabled2;
            modifier3 = modifier2;
            function2 = function5;
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty & 14) | 199680, 0, 131026);
            $composer3.startReplaceGroup(-1236368946);
            ComposerKt.sourceInformation($composer3, "287@11130L15,285@11066L279");
            str3 = subtitle2;
            if (str3 != null) {
                z = true;
            } else {
                z = true;
            }
            if (z) {
                i2 = 0;
                $dirty2 = $dirty;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 3) & 14) | 3072, 3120, 120754);
            } else {
                $dirty2 = $dirty;
                i2 = 0;
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.startReplaceGroup(-334763324);
            ComposerKt.sourceInformation($composer3, "299@11611L33,297@11530L265");
            if (actionLabel2 != null) {
                function3 = function2;
                if (function3 != null) {
                    $composer3.startReplaceGroup(-334756262);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                    if ((57344 & $dirty2) == 16384) {
                        i3 = 1;
                    } else {
                        i3 = i2;
                    }
                    i4 = i3 | (($dirty2 & 7168) != 2048 ? i2 : 1);
                    Object it$iv4 = $composer3.rememberedValue();
                    if (i4 == 0) {
                        actionEnabled4 = actionEnabled3;
                        final boolean actionEnabled4$c1819 = actionEnabled4;
                        final Function0<Unit> function3$c1819 = function3;
                        value$iv = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda11
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AuroraDsKt.AuroraContextBar$lambda$11$lambda$10$lambda$9(actionEnabled4$c1819, function3$c1819);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        actionEnabled4 = actionEnabled3;
                        final boolean actionEnabled4$c1828 = actionEnabled4;
                        final Function0<Unit> function3$c1828 = function3;
                        value$iv = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda11
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AuroraDsKt.AuroraContextBar$lambda$11$lambda$10$lambda$9(actionEnabled4$c1828, function3$c1828);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                    }
                    Function0 function9 = (Function0) value$iv;
                    $composer3.endReplaceGroup();
                    Modifier companion4 = Modifier.Companion;
                    companionAlpha = Modifier.Companion;
                    if (!actionEnabled4) {
                        companionAlpha = AlphaKt.alpha(companionAlpha, 0.5f);
                    }
                    AuroraPillAction(actionLabel2, function9, companion4.then(companionAlpha), false, $composer3, ($dirty2 >> 6) & 14, 8);
                } else {
                    actionEnabled4 = actionEnabled3;
                }
            } else {
                actionEnabled4 = actionEnabled3;
                function3 = function2;
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            ai.nous.aurora.compat.Compat.m2446HorizontalDivider9IZ8Weo(null, 0.0f, AuroraColorSchemeKt.getAuroraHairline($composer2, i2), $composer2, 0, 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            actionEnabled5 = actionEnabled4;
            function4 = function3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final String str4 = subtitle2;
            final String str5 = actionLabel2;
            final Function0<Unit> function10 = function4;
            final boolean z2 = actionEnabled5;
            final Modifier modifier4 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraDsKt.AuroraContextBar$lambda$12(title, str4, str5, function10, z2, modifier4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraContextBar$lambda$11$lambda$10$lambda$9(boolean $actionEnabled, Function0 $onAction) {
        if ($actionEnabled) {
            $onAction.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:148:0x03b7  */
    /* JADX WARN: Code duplicated, block: B:151:0x03ce  */
    /* JADX WARN: Code duplicated, block: B:152:0x03d3  */
    /* JADX WARN: Code duplicated, block: B:155:0x0429  */
    /* JADX WARN: Code duplicated, block: B:157:0x042f  */
    /* JADX WARN: Code duplicated, block: B:158:0x044d  */
    /* JADX WARN: Code duplicated, block: B:162:0x04ba  */
    public static final void AuroraFilterChip(final String label, final boolean selected, final Function0<Unit> onClick, Integer count, Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean dense, Composer $composer, final int $changed, final int i) {
        Integer num;
        Function2<? super Composer, ? super Integer, Unit> function3;
        Modifier modifier2;
        boolean z;
        Integer count2;
        boolean dense2;
        long auroraHairline;
        int i2;
        Object obj;
        float f;
        long jM74filterChipUnselectedBackgroundvNxB06k;
        long auroraTextMuted;
        Function0<ComposeUiNode> function0;
        CompositionLocalMap localMap$iv$iv;
        FontWeight.Companion companion;
        FontWeight medium;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Composer $composer2;
        long auroraTextFaint;
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer3 = $composer.startRestartGroup(-2012013057);
        ComposerKt.sourceInformation($composer3, "C(AuroraFilterChip)P(2,6,5!1,3,4)331@12869L967:AuroraDs.kt#rimizs");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(label) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(selected) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(onClick) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            num = count;
        } else if (($changed & 3072) == 0) {
            num = count;
            $dirty |= $composer3.changed(num) ? 2048 : 1024;
        } else {
            num = count;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            function3 = function2;
        } else if (($changed & 24576) == 0) {
            function3 = function2;
            $dirty |= $composer3.changedInstance(function3) ? 16384 : 8192;
        } else {
            function3 = function2;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
        } else if ((196608 & $changed) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty |= 1572864;
            z = dense;
        } else if ((1572864 & $changed) == 0) {
            z = dense;
            $dirty |= $composer3.changed(z) ? 1048576 : 524288;
        } else {
            z = dense;
        }
        if (($dirty & 599187) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            count2 = num;
            function4 = function3;
            dense2 = z;
            $composer2 = $composer3;
        } else {
            count2 = i3 != 0 ? null : num;
            Function2<? super Composer, ? super Integer, Unit> function5 = i4 != 0 ? null : function3;
            if (i5 != 0) {
                modifier2 = Modifier.Companion;
            }
            dense2 = i6 != 0 ? false : z;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2012013057, $dirty, -1, "ai.nous.aurora.presentation.components.ds.AuroraFilterChip (AuroraDs.kt:319)");
            }
            RoundedCornerShape shape = AuroraShapeKt.getAuroraShapePill();
            if (selected) {
                $composer3.startReplaceGroup(1533817229);
                ComposerKt.sourceInformation($composer3, "322@12321L11");
                auroraHairline = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                i2 = 14;
                obj = null;
                f = 0.85f;
            } else {
                $composer3.startReplaceGroup(1533818508);
                ComposerKt.sourceInformation($composer3, "322@12358L14");
                auroraHairline = AuroraColorSchemeKt.getAuroraHairline($composer3, 0);
                i2 = 14;
                obj = null;
                f = 0.8f;
            }
            long border = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraHairline, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraHairline) : f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraHairline) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraHairline) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraHairline) : 0.0f);
            $composer3.endReplaceGroup();
            if (selected) {
                $composer3.startReplaceGroup(1533820365);
                ComposerKt.sourceInformation($composer3, "323@12419L11");
                long auroraAmber = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                jM74filterChipUnselectedBackgroundvNxB06k = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAmber, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAmber) : 0.18f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAmber) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAmber) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAmber) : 0.0f);
            } else {
                $composer3.startReplaceGroup(1533821970);
                ComposerKt.sourceInformation($composer3, "323@12528L7");
                AstralReadabilityPolicy astralReadabilityPolicy = AstralReadabilityPolicy.INSTANCE;
                ProvidableCompositionLocal<ThemeTokens> localThemeTokens = ThemeTokensKt.getLocalThemeTokens();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localThemeTokens);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                jM74filterChipUnselectedBackgroundvNxB06k = astralReadabilityPolicy.m74filterChipUnselectedBackgroundvNxB06k((ThemeTokens) objConsume);
            }
            $composer3.endReplaceGroup();
            long bg = jM74filterChipUnselectedBackgroundvNxB06k;
            if (selected) {
                $composer3.startReplaceGroup(1533824933);
                ComposerKt.sourceInformation($composer3, "324@12574L11");
                auroraTextMuted = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
            } else {
                $composer3.startReplaceGroup(1533825481);
                ComposerKt.sourceInformation($composer3, "324@12591L15");
                auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0);
            }
            $composer3.endReplaceGroup();
            int $dirty2 = $dirty;
            long contentColor = auroraTextMuted;
            float hPad = dense2 ? ai.nous.aurora.compat.Compat.m7016constructorimpl(9) : ai.nous.aurora.compat.Compat.m7016constructorimpl(12);
            float vPad = dense2 ? ai.nous.aurora.compat.Compat.m7016constructorimpl(4) : ai.nous.aurora.compat.Compat.m7016constructorimpl(6);
            float minH = dense2 ? ai.nous.aurora.compat.Compat.m7016constructorimpl(30) : ai.nous.aurora.compat.Compat.m7016constructorimpl(34);
            long labelSp = dense2 ? TextUnitKt.getSp(11) : TextUnitKt.getSp(12);
            long countSp = TextUnitKt.getSp(dense2 ? 10 : 11);
            float gap = dense2 ? ai.nous.aurora.compat.Compat.m7016constructorimpl(4) : ai.nous.aurora.compat.Compat.m7016constructorimpl(6);
            Modifier modifier3 = modifier2;
            Modifier modifierM1035paddingVpY3zN4 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.m1067heightInVpY3zN4$default(modifier2, minH, 0.0f, 2, null), shape), bg, null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), border, shape), false, null, null, onClick, 7, null), hPad, vPad);
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM914spacedBy0680j_4 = ai.nous.aurora.compat.Compat.m914spacedBy0680j_4(Arrangement.INSTANCE, ai.nous.aurora.compat.Compat.m7016constructorimpl(6));
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalOrVerticalM914spacedBy0680j_4, centerVertically, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifierM1035paddingVpY3zN4);
            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                function0 = constructor;
                localMap$iv$iv = localMap$iv$iv2;
            } else {
                function0 = constructor;
                localMap$iv$iv = localMap$iv$iv2;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                int i7 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i8 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -904043631, "C343@13287L209:AuroraDs.kt#rimizs");
                $composer3.startReplaceGroup(-167710303);
                ComposerKt.sourceInformation($composer3, "342@13270L8");
                if (function5 != null) {
                    function5.invoke($composer3, Integer.valueOf(($dirty2 >> 12) & 14));
                    Unit unit = Unit.INSTANCE;
                }
                $composer3.endReplaceGroup();
                companion = FontWeight.Companion;
                if (selected) {
                    medium = companion.getSemiBold();
                } else {
                    medium = companion.getMedium();
                }
                function4 = function5;
                $composer2 = $composer3;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(label, (Modifier) null, contentColor, labelSp, (FontStyle) null, medium, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, $dirty2 & 14, 3072, 122834);
                $composer3.startReplaceGroup(-167702466);
                ComposerKt.sourceInformation($composer3, "351@13538L282");
                if (count2 != null) {
                    String string = count2.toString();
                    if (selected) {
                        $composer3.startReplaceGroup(-167698836);
                        ComposerKt.sourceInformation($composer3, "353@13616L11");
                        long auroraAmber2 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                        auroraTextFaint = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAmber2, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAmber2) : 0.85f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAmber2) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAmber2) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAmber2) : 0.0f);
                    } else {
                        $composer3.startReplaceGroup(-167698040);
                        ComposerKt.sourceInformation($composer3, "353@13653L15");
                        auroraTextFaint = AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0);
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(string, (Modifier) null, auroraTextFaint, countSp, (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 130962);
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i9 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            int i10 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -904043631, "C343@13287L209:AuroraDs.kt#rimizs");
            $composer3.startReplaceGroup(-167710303);
            ComposerKt.sourceInformation($composer3, "342@13270L8");
            if (function5 != null) {
                function5.invoke($composer3, Integer.valueOf(($dirty2 >> 12) & 14));
                Unit unit2 = Unit.INSTANCE;
            }
            $composer3.endReplaceGroup();
            companion = FontWeight.Companion;
            if (selected) {
                medium = companion.getSemiBold();
            } else {
                medium = companion.getMedium();
            }
            function4 = function5;
            $composer2 = $composer3;
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(label, (Modifier) null, contentColor, labelSp, (FontStyle) null, medium, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, $dirty2 & 14, 3072, 122834);
            $composer3.startReplaceGroup(-167702466);
            ComposerKt.sourceInformation($composer3, "351@13538L282");
            if (count2 != null) {
                String string2 = count2.toString();
                if (selected) {
                    $composer3.startReplaceGroup(-167698836);
                    ComposerKt.sourceInformation($composer3, "353@13616L11");
                    long auroraAmber3 = AuroraColorSchemeKt.getAuroraAmber($composer3, 0);
                    auroraTextFaint = ai.nous.aurora.compat.Compat.m4532copywmQWz5c(auroraAmber3, (14 & 1) != 0 ? ai.nous.aurora.compat.Compat.m4536getAlphaimpl(auroraAmber3) : 0.85f, (14 & 2) != 0 ? ai.nous.aurora.compat.Compat.m4540getRedimpl(auroraAmber3) : 0.0f, (14 & 4) != 0 ? ai.nous.aurora.compat.Compat.m4539getGreenimpl(auroraAmber3) : 0.0f, (14 & 8) != 0 ? ai.nous.aurora.compat.Compat.m4537getBlueimpl(auroraAmber3) : 0.0f);
                } else {
                    $composer3.startReplaceGroup(-167698040);
                    ComposerKt.sourceInformation($composer3, "353@13653L15");
                    auroraTextFaint = AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0);
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(string2, (Modifier) null, auroraTextFaint, countSp, (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 130962);
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Integer num2 = count2;
            final Function2<? super Composer, ? super Integer, Unit> function6 = function4;
            final Modifier modifier4 = modifier2;
            final boolean z2 = dense2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return AuroraDsKt.AuroraFilterChip$lambda$14(label, selected, onClick, num2, function6, modifier4, z2, $changed, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    public static final void AuroraChoiceChip(final String label, final boolean selected, final Function0<Unit> onClick, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer2 = $composer.startRestartGroup(1758413806);
        ComposerKt.sourceInformation($composer2, "C(AuroraChoiceChip)P(!1,3,2)369@13984L92:AuroraDs.kt#rimizs");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(label) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(selected) ? 32 : 16;
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
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            modifier3 = i2 != 0 ? Modifier.Companion : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1758413806, $dirty2, -1, "ai.nous.aurora.presentation.components.ds.AuroraChoiceChip (AuroraDs.kt:368)");
            }
            AuroraFilterChip(label, selected, onClick, null, null, modifier3, false, $composer2, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | (458752 & ($dirty2 << 6)), 88);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraDsKt.AuroraChoiceChip$lambda$15(label, selected, onClick, modifier4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void AuroraTextFieldRow(final String value, final Function1<? super String, Unit> onValueChange, Modifier modifier, String placeholder, boolean enabled, boolean singleLine, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        String placeholder2;
        boolean enabled2;
        boolean singleLine2;
        Modifier modifier3;
        String placeholder3;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(onValueChange, "onValueChange");
        Composer $composer3 = $composer.startRestartGroup(-1394512946);
        ComposerKt.sourceInformation($composer3, "C(AuroraTextFieldRow)P(5,2,1,3)387@14524L10,388@14588L12,392@14708L13,393@14749L14,395@14856L194,382@14348L709:AuroraDs.kt#rimizs");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onValueChange) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            placeholder2 = placeholder;
        } else if (($changed & 3072) == 0) {
            placeholder2 = placeholder;
            $dirty |= $composer3.changed(placeholder2) ? 2048 : 1024;
        } else {
            placeholder2 = placeholder;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            enabled2 = enabled;
        } else if (($changed & 24576) == 0) {
            enabled2 = enabled;
            $dirty |= $composer3.changed(enabled2) ? 16384 : 8192;
        } else {
            enabled2 = enabled;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            singleLine2 = singleLine;
        } else if ((196608 & $changed) == 0) {
            singleLine2 = singleLine;
            $dirty |= $composer3.changed(singleLine2) ? 131072 : 65536;
        } else {
            singleLine2 = singleLine;
        }
        int $dirty2 = $dirty;
        if ((74899 & $dirty2) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            placeholder3 = placeholder2;
            $composer2 = $composer3;
        } else {
            Modifier modifier4 = i2 != 0 ? Modifier.Companion : modifier2;
            if (i3 != 0) {
                placeholder2 = "";
            }
            boolean enabled3 = i4 != 0 ? true : enabled2;
            boolean singleLine3 = i5 != 0 ? true : singleLine2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1394512946, $dirty2, -1, "ai.nous.aurora.presentation.components.ds.AuroraTextFieldRow (AuroraDs.kt:380)");
            }
            RoundedCornerShape shape = ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(12));
            modifier3 = modifier4;
            placeholder3 = placeholder2;
            $composer2 = $composer3;
            final String placeholder2$c2363 = placeholder2;
            final String placeholder2$c2362 = placeholder2;
            BasicTextFieldKt.BasicTextField(value, onValueChange, ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(modifier4, 0.0f, 1, null), shape), AuroraColorSchemeKt.getAuroraSurface($composer3, 0), null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer3, 0), shape), ai.nous.aurora.compat.Compat.m7016constructorimpl(14), ai.nous.aurora.compat.Compat.m7016constructorimpl(12)), enabled3, false, ai.nous.aurora.compat.Compat.newTextStyle(AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(15), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777212), (KeyboardOptions) null, (KeyboardActions) null, singleLine3, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, (MutableInteractionSource) null, (Brush) ai.nous.aurora.compat.Compat.solidColorBrush(AuroraColorSchemeKt.getAuroraAccent($composer3, 0)), (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) (Function3) ComposableLambdaKt.rememberComposableLambda(829694987, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt.AuroraTextFieldRow.1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function2, Composer composer, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function2, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code duplicated, block: B:29:0x00a5  */
                public final void invoke(Function2<? super Composer, ? super Integer, Unit> inner, Composer $composer4, int $changed2) {
                    Intrinsics.checkNotNullParameter(inner, "inner");
                    ComposerKt.sourceInformation($composer4, "C399@15033L7:AuroraDs.kt#rimizs");
                    int $dirty3 = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty3 |= $composer4.changedInstance(inner) ? 4 : 2;
                    }
                    int $dirty4 = $dirty3;
                    if (($dirty4 & 19) != 18 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(829694987, $dirty4, -1, "ai.nous.aurora.presentation.components.ds.AuroraTextFieldRow.<anonymous> (AuroraDs.kt:396)");
                        }
                        $composer4.startReplaceGroup(-1764891409);
                        ComposerKt.sourceInformation($composer4, "397@14972L15,397@14946L60");
                        if (value.length() == 0) {
                            if (placeholder2$c2363.length() > 0) {
                                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(placeholder2$c2363, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer4, 0), TextUnitKt.getSp(15), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 3072, 0, 131058);
                            }
                        }
                        $composer4.endReplaceGroup();
                        inner.invoke($composer4, Integer.valueOf($dirty4 & 14));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer2, ($dirty2 & 14) | ($dirty2 & 112) | (($dirty2 >> 3) & 7168) | (($dirty2 << 9) & 234881024), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 16080);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            enabled2 = enabled3;
            singleLine2 = singleLine3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final String str = placeholder3;
            final boolean z = enabled2;
            final boolean z2 = singleLine2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraDsKt.AuroraTextFieldRow$lambda$16(value, onValueChange, modifier5, str, z, z2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0399  */
    /* JADX WARN: Code duplicated, block: B:105:0x03a2  */
    /* JADX WARN: Code duplicated, block: B:107:0x03a6  */
    /* JADX WARN: Code duplicated, block: B:108:0x03e1  */
    /* JADX WARN: Code duplicated, block: B:111:0x03fe  */
    /* JADX WARN: Code duplicated, block: B:112:0x0401  */
    /* JADX WARN: Code duplicated, block: B:115:0x04d6  */
    /* JADX WARN: Code duplicated, block: B:86:0x026f  */
    /* JADX WARN: Code duplicated, block: B:89:0x027b  */
    /* JADX WARN: Code duplicated, block: B:90:0x0281  */
    /* JADX WARN: Code duplicated, block: B:93:0x02b2  */
    /* JADX WARN: Code duplicated, block: B:96:0x02c5  */
    /* JADX WARN: Code duplicated, block: B:97:0x02c8  */
    public static final void AuroraSwitchRow(final String title, final boolean checked, final Function1<? super Boolean, Unit> onCheckedChange, String subtitle, boolean enabled, Composer $composer, final int $changed, final int i) {
        String str;
        boolean z;
        String subtitle2;
        boolean enabled2;
        MeasurePolicy measurePolicy$iv;
        int compositeKeyHash$iv$iv;
        CompositionLocalMap localMap$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        CompositionLocalMap localMap$iv$iv2;
        Function0<ComposeUiNode> function1;
        Composer $composer2;
        String str2;
        boolean z2;
        int i2;
        String str3;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(onCheckedChange, "onCheckedChange");
        Composer $composer3 = $composer.startRestartGroup(-892835776);
        ComposerKt.sourceInformation($composer3, "C(AuroraSwitchRow)P(4!1,2,3)412@15242L1187:AuroraDs.kt#rimizs");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(title) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(checked) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(onCheckedChange) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            str = subtitle;
        } else if (($changed & 3072) == 0) {
            str = subtitle;
            $dirty |= $composer3.changed(str) ? 2048 : 1024;
        } else {
            str = subtitle;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            z = enabled;
        } else if (($changed & 24576) == 0) {
            z = enabled;
            $dirty |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = enabled;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            subtitle2 = str;
            enabled2 = z;
            $composer2 = $composer3;
        } else {
            subtitle2 = i3 != 0 ? null : str;
            enabled2 = i4 != 0 ? true : z;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-892835776, $dirty2, -1, "ai.nous.aurora.presentation.components.ds.AuroraSwitchRow (AuroraDs.kt:411)");
            }
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
            Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            int $changed$iv$iv = (390 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor2);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                measurePolicy$iv = measurePolicy$iv2;
            } else {
                measurePolicy$iv = measurePolicy$iv2;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                int i5 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i6 = ((390 >> 6) & 112) | 6;
                RowScope $this$AuroraSwitchRow_u24lambda_u2418 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 380989863, "C418@15450L279,426@15806L15,424@15738L224,437@16217L11,438@16268L15,439@16323L19,440@16383L14,435@16124L288,431@15971L452:AuroraDs.kt#rimizs");
                Modifier modifier$iv2 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraSwitchRow_u24lambda_u2418, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getStart();
                MeasurePolicy measurePolicy$iv3 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv2 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                constructor = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function0 = constructor;
                    $composer3.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    localMap$iv$iv2 = localMap$iv$iv;
                    function1 = function0;
                } else {
                    localMap$iv$iv2 = localMap$iv$iv;
                    function1 = function0;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
                    int i7 = ($changed$iv$iv$iv2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i8 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -906899523, "C419@15523L10,419@15503L83:AuroraDs.kt#rimizs");
                    $composer2 = $composer3;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty2 & 14) | 199680, 0, 131026);
                    $composer3.startReplaceGroup(1494768806);
                    ComposerKt.sourceInformation($composer3, "421@15671L15,421@15648L57");
                    str2 = subtitle2;
                    if (str2 != null || StringsKt.isBlank(str2)) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        i2 = 0;
                    } else {
                        i2 = 0;
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 9) & 14) | 3072, 0, 131058);
                    }
                    $composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (checked) {
                        str3 = DebugKt.DEBUG_PROPERTY_VALUE_ON;
                    } else {
                        str3 = DebugKt.DEBUG_PROPERTY_VALUE_OFF;
                    }
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str3, ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(8), 0.0f, 11, null), AuroraColorSchemeKt.getAuroraTextFaint($composer3, i2), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3120, 0, 130992);
                    SwitchKt.Switch(checked, onCheckedChange, null, null, enabled2, ai.nous.aurora.compat.Compat.m2949colorsV1nXRL4(SwitchDefaults.INSTANCE, ai.nous.aurora.compat.Compat.m4571getWhite0d7_KjU(Color.Companion), AuroraColorSchemeKt.getAuroraAmber($composer3, i2), 0L, 0L, AuroraColorSchemeKt.getAuroraTextFaint($composer3, i2), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer3, i2), AuroraColorSchemeKt.getAuroraHairline($composer3, i2), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 6, SwitchDefaults.$stable << 18, 65420), null, $composer3, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112) | ($dirty2 & 57344), 76);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
                int i9 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i10 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -906899523, "C419@15523L10,419@15503L83:AuroraDs.kt#rimizs");
                $composer2 = $composer3;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty2 & 14) | 199680, 0, 131026);
                $composer3.startReplaceGroup(1494768806);
                ComposerKt.sourceInformation($composer3, "421@15671L15,421@15648L57");
                str2 = subtitle2;
                if (str2 != null) {
                    z2 = true;
                } else {
                    z2 = true;
                }
                if (z2) {
                    i2 = 0;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 9) & 14) | 3072, 0, 131058);
                } else {
                    i2 = 0;
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (checked) {
                    str3 = DebugKt.DEBUG_PROPERTY_VALUE_ON;
                } else {
                    str3 = DebugKt.DEBUG_PROPERTY_VALUE_OFF;
                }
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str3, ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(8), 0.0f, 11, null), AuroraColorSchemeKt.getAuroraTextFaint($composer3, i2), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3120, 0, 130992);
                SwitchKt.Switch(checked, onCheckedChange, null, null, enabled2, ai.nous.aurora.compat.Compat.m2949colorsV1nXRL4(SwitchDefaults.INSTANCE, ai.nous.aurora.compat.Compat.m4571getWhite0d7_KjU(Color.Companion), AuroraColorSchemeKt.getAuroraAmber($composer3, i2), 0L, 0L, AuroraColorSchemeKt.getAuroraTextFaint($composer3, i2), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer3, i2), AuroraColorSchemeKt.getAuroraHairline($composer3, i2), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 6, SwitchDefaults.$stable << 18, 65420), null, $composer3, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112) | ($dirty2 & 57344), 76);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i11 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i12 = ((390 >> 6) & 112) | 6;
            RowScope $this$AuroraSwitchRow_u24lambda_u2419 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 380989863, "C418@15450L279,426@15806L15,424@15738L224,437@16217L11,438@16268L15,439@16323L19,440@16383L14,435@16124L288,431@15971L452:AuroraDs.kt#rimizs");
            Modifier modifier$iv3 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraSwitchRow_u24lambda_u2419, Modifier.Companion, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.Companion.getStart();
            MeasurePolicy measurePolicy$iv4 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv3 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
            constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv4, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                localMap$iv$iv2 = localMap$iv$iv;
                function1 = function0;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
                int i13 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                int i14 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -906899523, "C419@15523L10,419@15503L83:AuroraDs.kt#rimizs");
                $composer2 = $composer3;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty2 & 14) | 199680, 0, 131026);
                $composer3.startReplaceGroup(1494768806);
                ComposerKt.sourceInformation($composer3, "421@15671L15,421@15648L57");
                str2 = subtitle2;
                if (str2 != null) {
                    z2 = true;
                } else {
                    z2 = true;
                }
                if (z2) {
                    i2 = 0;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 9) & 14) | 3072, 0, 131058);
                } else {
                    i2 = 0;
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (checked) {
                    str3 = DebugKt.DEBUG_PROPERTY_VALUE_ON;
                } else {
                    str3 = DebugKt.DEBUG_PROPERTY_VALUE_OFF;
                }
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str3, ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(8), 0.0f, 11, null), AuroraColorSchemeKt.getAuroraTextFaint($composer3, i2), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3120, 0, 130992);
                SwitchKt.Switch(checked, onCheckedChange, null, null, enabled2, ai.nous.aurora.compat.Compat.m2949colorsV1nXRL4(SwitchDefaults.INSTANCE, ai.nous.aurora.compat.Compat.m4571getWhite0d7_KjU(Color.Companion), AuroraColorSchemeKt.getAuroraAmber($composer3, i2), 0L, 0L, AuroraColorSchemeKt.getAuroraTextFaint($composer3, i2), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer3, i2), AuroraColorSchemeKt.getAuroraHairline($composer3, i2), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 6, SwitchDefaults.$stable << 18, 65420), null, $composer3, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112) | ($dirty2 & 57344), 76);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                localMap$iv$iv2 = localMap$iv$iv;
                function1 = function0;
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
            int i15 = ($changed$iv$iv$iv3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
            int i16 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -906899523, "C419@15523L10,419@15503L83:AuroraDs.kt#rimizs");
            $composer2 = $composer3;
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty2 & 14) | 199680, 0, 131026);
            $composer3.startReplaceGroup(1494768806);
            ComposerKt.sourceInformation($composer3, "421@15671L15,421@15648L57");
            str2 = subtitle2;
            if (str2 != null) {
                z2 = true;
            } else {
                z2 = true;
            }
            if (z2) {
                i2 = 0;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 9) & 14) | 3072, 0, 131058);
            } else {
                i2 = 0;
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (checked) {
                str3 = DebugKt.DEBUG_PROPERTY_VALUE_ON;
            } else {
                str3 = DebugKt.DEBUG_PROPERTY_VALUE_OFF;
            }
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(str3, ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(8), 0.0f, 11, null), AuroraColorSchemeKt.getAuroraTextFaint($composer3, i2), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.Companion.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 3120, 0, 130992);
            SwitchKt.Switch(checked, onCheckedChange, null, null, enabled2, ai.nous.aurora.compat.Compat.m2949colorsV1nXRL4(SwitchDefaults.INSTANCE, ai.nous.aurora.compat.Compat.m4571getWhite0d7_KjU(Color.Companion), AuroraColorSchemeKt.getAuroraAmber($composer3, i2), 0L, 0L, AuroraColorSchemeKt.getAuroraTextFaint($composer3, i2), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer3, i2), AuroraColorSchemeKt.getAuroraHairline($composer3, i2), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 6, SwitchDefaults.$stable << 18, 65420), null, $composer3, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112) | ($dirty2 & 57344), 76);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
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
            final String str4 = subtitle2;
            final boolean z3 = enabled2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraDsKt.AuroraSwitchRow$lambda$19(title, checked, onCheckedChange, str4, z3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void AuroraEmptyState(final String title, String body, Modifier modifier, Composer $composer, final int $changed, final int i) {
        String str;
        Modifier modifier2;
        String body2;
        Modifier modifier3;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(title, "title");
        Composer $composer3 = $composer.startRestartGroup(492921790);
        ComposerKt.sourceInformation($composer3, "C(AuroraEmptyState)P(2)452@16555L439:AuroraDs.kt#rimizs");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(title) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            str = body;
        } else if (($changed & 48) == 0) {
            str = body;
            $dirty |= $composer3.changed(str) ? 32 : 16;
        } else {
            str = body;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            body2 = str;
            modifier3 = modifier2;
            $composer2 = $composer3;
        } else {
            body2 = i2 != 0 ? null : str;
            Modifier modifier4 = i3 != 0 ? Modifier.Companion : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(492921790, $dirty2, -1, "ai.nous.aurora.presentation.components.ds.AuroraEmptyState (AuroraDs.kt:451)");
            }
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(modifier4, 0.0f, 1, null), AuroraSpacing.INSTANCE.m241getXlD9Ej5fM());
            Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i4 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 300935085, "C458@16755L15,458@16735L86:AuroraDs.kt#rimizs");
            modifier3 = modifier4;
            $composer2 = $composer3;
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty2 & 14) | 199680, 0, 131026);
            $composer3.startReplaceGroup(-1375762793);
            ComposerKt.sourceInformation($composer3, "460@16871L41,461@16944L15,461@16925L53");
            String str2 = body2;
            if (!(str2 == null || StringsKt.isBlank(str2))) {
                SpacerKt.Spacer(ai.nous.aurora.compat.Compat.m1065height3ABfNKs(Modifier.Companion, AuroraSpacing.INSTANCE.m240getSmD9Ej5fM()), $composer3, 6);
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(body2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 3) & 14) | 3072, 0, 131058);
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
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
            final String str3 = body2;
            final Modifier modifier5 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraDsKt.AuroraEmptyState$lambda$21(title, str3, modifier5, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void AuroraSheetHeader(final String title, final Function0<Unit> onDone, String doneLabel, Composer $composer, final int $changed, final int i) {
        String str;
        String doneLabel2;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(onDone, "onDone");
        Composer $composer3 = $composer.startRestartGroup(-1527655769);
        ComposerKt.sourceInformation($composer3, "C(AuroraSheetHeader)P(2,1)472@17116L588,492@17735L14,492@17709L41:AuroraDs.kt#rimizs");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(title) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onDone) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            str = doneLabel;
        } else if (($changed & 384) == 0) {
            str = doneLabel;
            $dirty |= $composer3.changed(str) ? 256 : 128;
        } else {
            str = doneLabel;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            doneLabel2 = str;
            $composer2 = $composer3;
        } else {
            doneLabel2 = i2 != 0 ? "Done" : str;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1527655769, $dirty2, -1, "ai.nous.aurora.presentation.components.ds.AuroraSheetHeader (AuroraDs.kt:471)");
            }
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(14));
            Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            int $changed$iv$iv = (390 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i4 = ((390 >> 6) & 112) | 6;
            RowScope $this$AuroraSheetHeader_u24lambda_u2422 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1725619752, "C480@17369L10,478@17324L186,487@17568L12,485@17519L179:AuroraDs.kt#rimizs");
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraSheetHeader_u24lambda_u2422, Modifier.Companion, 1.0f, false, 2, null), AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty2 & 14) | 199680, 0, 131024);
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(doneLabel2, ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(Modifier.Companion, false, null, null, onDone, 7, null), AuroraColorSchemeKt.getAuroraAccent($composer3, 0), 0L, (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 131032);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            ai.nous.aurora.compat.Compat.m2446HorizontalDivider9IZ8Weo(null, 0.0f, AuroraColorSchemeKt.getAuroraHairline($composer2, 0), $composer2, 0, 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final String str2 = doneLabel2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraDsKt.AuroraSheetHeader$lambda$23(title, onDone, str2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:200:0x045f  */
    /* JADX WARN: Code duplicated, block: B:201:0x046d  */
    /* JADX WARN: Code duplicated, block: B:204:0x0506  */
    /* JADX WARN: Code duplicated, block: B:207:0x0512  */
    /* JADX WARN: Code duplicated, block: B:208:0x0518  */
    /* JADX WARN: Code duplicated, block: B:211:0x054b  */
    /* JADX WARN: Code duplicated, block: B:214:0x055e  */
    /* JADX WARN: Code duplicated, block: B:215:0x0561  */
    /* JADX WARN: Code duplicated, block: B:219:0x062a  */
    /* JADX WARN: Code duplicated, block: B:222:0x0636  */
    /* JADX WARN: Code duplicated, block: B:223:0x063a  */
    /* JADX WARN: Code duplicated, block: B:226:0x0669  */
    /* JADX WARN: Code duplicated, block: B:229:0x067c  */
    /* JADX WARN: Code duplicated, block: B:230:0x067f  */
    /* JADX WARN: Code duplicated, block: B:234:0x077a  */
    /* JADX WARN: Code duplicated, block: B:238:0x0783  */
    /* JADX WARN: Code duplicated, block: B:240:0x0786  */
    /* JADX WARN: Code duplicated, block: B:241:0x07c8  */
    /* JADX WARN: Code duplicated, block: B:244:0x083d  */
    /* JADX WARN: Code duplicated, block: B:247:0x0849  */
    /* JADX WARN: Code duplicated, block: B:248:0x084f  */
    /* JADX WARN: Code duplicated, block: B:251:0x0882  */
    /* JADX WARN: Code duplicated, block: B:255:0x0898 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:259:0x09b3  */
    /* JADX WARN: Code duplicated, block: B:262:0x09bf  */
    /* JADX WARN: Code duplicated, block: B:263:0x09c5  */
    /* JADX WARN: Code duplicated, block: B:266:0x09f8  */
    /* JADX WARN: Code duplicated, block: B:270:0x0a0e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:274:0x0a68  */
    /* JADX WARN: Code duplicated, block: B:275:0x0a79  */
    /* JADX WARN: Code duplicated, block: B:278:0x0b43  */
    /* JADX WARN: Code duplicated, block: B:279:0x0b53  */
    /* JADX WARN: Code duplicated, block: B:282:0x0bf5 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:325:0x0e94  */
    /* JADX WARN: Code duplicated, block: B:328:0x0ebe  */
    public static final void AuroraDropdownRow(final String title, final String value, final List<String> options, final Function1<? super String, Unit> onSelect, String subtitle, boolean enabled, boolean allowCustom, String customLabel, String blankLabel, Composer $composer, final int $changed, final int i) {
        boolean z;
        boolean z2;
        String subtitle2;
        boolean enabled2;
        boolean allowCustom2;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        boolean z3;
        Function0<ComposeUiNode> function0;
        MeasurePolicy measurePolicy$iv;
        CompositionLocalMap localMap$iv$iv;
        Object it$iv;
        Object value$iv4;
        MeasurePolicy measurePolicy$iv2;
        int compositeKeyHash$iv$iv;
        CompositionLocalMap localMap$iv$iv2;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function1;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        CompositionLocalMap localMap$iv$iv3;
        MeasurePolicy measurePolicy$iv3;
        MeasurePolicy measurePolicy$iv4;
        int compositeKeyHash$iv$iv2;
        CompositionLocalMap localMap$iv$iv4;
        Function0<ComposeUiNode> constructor2;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        CompositionLocalMap localMap$iv$iv5;
        MeasurePolicy measurePolicy$iv5;
        int $dirty;
        String customLabel2;
        String blankLabel2;
        String str;
        boolean z4;
        int $dirty2;
        int i2;
        int compositeKeyHash$iv$iv3;
        Function0<ComposeUiNode> constructor3;
        Function0<ComposeUiNode> function2;
        Composer $this$Layout_u24lambda_u240$iv$iv3;
        int compositeKeyHash$iv$iv4;
        Function0<ComposeUiNode> constructor4;
        Function0<ComposeUiNode> function3;
        Composer $this$Layout_u24lambda_u240$iv$iv4;
        long auroraText;
        Object it$iv2;
        MutableState expanded$delegate;
        Object value$iv5;
        Composer $composer2;
        Function0<ComposeUiNode> function4;
        Object value$iv6;
        Object value$iv7;
        final MutableState customMode$delegate;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(onSelect, "onSelect");
        Composer $composer3 = $composer.startRestartGroup(-1600014194);
        ComposerKt.sourceInformation($composer3, "C(AuroraDropdownRow)P(7,8,5,4,6,3!1,2)508@18075L34,509@18132L34,510@18190L41,520@18726L4238:AuroraDs.kt#rimizs");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed(title) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(value) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty3 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty3 |= $composer3.changedInstance(options) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty3 |= $composer3.changedInstance(onSelect) ? 2048 : 1024;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty3 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty3 |= $composer3.changed(subtitle) ? 16384 : 8192;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z = enabled;
        } else if ((196608 & $changed) == 0) {
            z = enabled;
            $dirty3 |= $composer3.changed(z) ? 131072 : 65536;
        } else {
            z = enabled;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty3 |= 1572864;
            z2 = allowCustom;
        } else if ((1572864 & $changed) == 0) {
            z2 = allowCustom;
            $dirty3 |= $composer3.changed(z2) ? 1048576 : 524288;
        } else {
            z2 = allowCustom;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changed(customLabel) ? 8388608 : 4194304;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changed(blankLabel) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($dirty3 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            subtitle2 = subtitle;
            customLabel2 = customLabel;
            blankLabel2 = blankLabel;
            enabled2 = z;
            $composer2 = $composer3;
            allowCustom2 = z2;
        } else {
            subtitle2 = i3 != 0 ? null : subtitle;
            enabled2 = i4 != 0 ? true : z;
            allowCustom2 = i5 != 0 ? false : z2;
            String customLabel3 = i6 != 0 ? "Custom…" : customLabel;
            String blankLabel3 = i7 != 0 ? "Unset" : blankLabel;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1600014194, $dirty3, -1, "ai.nous.aurora.presentation.components.ds.AuroraDropdownRow (AuroraDs.kt:507)");
            }
            $composer3.startReplaceGroup(1660915477);
            ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
            Object it$iv3 = $composer3.rememberedValue();
            if (it$iv3 == Composer.Companion.getEmpty()) {
                value$iv = ai.nous.aurora.compat.Compat.d_SnapshotStateKt_mutableStateOf_4(false, null, 2, null);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv3;
            }
            final MutableState expanded$delegate2 = (MutableState) value$iv;
            $composer3.endReplaceGroup();
            $composer3.startReplaceGroup(1660917301);
            ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
            Object it$iv4 = $composer3.rememberedValue();
            if (it$iv4 == Composer.Companion.getEmpty()) {
                value$iv2 = ai.nous.aurora.compat.Compat.d_SnapshotStateKt_mutableStateOf_4(false, null, 2, null);
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv4;
            }
            MutableState customMode$delegate2 = (MutableState) value$iv2;
            $composer3.endReplaceGroup();
            $composer3.startReplaceGroup(1660919164);
            ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
            boolean invalid$iv = ($dirty3 & 112) == 32;
            Object it$iv5 = $composer3.rememberedValue();
            if (invalid$iv || it$iv5 == Composer.Companion.getEmpty()) {
                value$iv3 = ai.nous.aurora.compat.Compat.d_SnapshotStateKt_mutableStateOf_4(value, null, 2, null);
                $composer3.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv5;
            }
            final MutableState customDraft$delegate = (MutableState) value$iv3;
            $composer3.endReplaceGroup();
            String str2 = value;
            if (StringsKt.isBlank(str2)) {
                str2 = blankLabel3;
            }
            String display = str2;
            List $this$AuroraDropdownRow_u24lambda_u2436 = CollectionsKt.createListBuilder();
            List<String> $this$any$iv = options;
            if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z3 = false;
                        break;
                    }
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (it2.length() == 0) {
                        z3 = true;
                        break;
                    }
                }
            } else {
                z3 = false;
            }
            boolean hasBlankOption = z3 || StringsKt.isBlank(value);
            if (hasBlankOption) {
                $this$AuroraDropdownRow_u24lambda_u2436.add(blankLabel3);
            }
            if (!StringsKt.isBlank(value) && !options.contains(value) && !Intrinsics.areEqual(value, blankLabel3)) {
                $this$AuroraDropdownRow_u24lambda_u2436.add(value);
            }
            List<String> $this$filter$iv = options;
            String blankLabel4 = blankLabel3;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                boolean hasBlankOption2 = hasBlankOption;
                String it3 = (String) element$iv$iv;
                if (!StringsKt.isBlank(it3)) {
                    destination$iv$iv.add(element$iv$iv);
                }
                hasBlankOption = hasBlankOption2;
            }
            $this$AuroraDropdownRow_u24lambda_u2436.addAll(CollectionsKt.distinct((List) destination$iv$iv));
            if (allowCustom2) {
                $this$AuroraDropdownRow_u24lambda_u2436.add(customLabel3);
            }
            List opts = CollectionsKt.distinct(CollectionsKt.build($this$AuroraDropdownRow_u24lambda_u2436));
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.Companion.getStart();
            MeasurePolicy measurePolicy$iv6 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            int $changed$iv$iv = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv6 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor5;
                $composer3.createNode(function0);
            } else {
                function0 = constructor5;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv5 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv6, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv6, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv5.getInserting()) {
                measurePolicy$iv = measurePolicy$iv6;
                localMap$iv$iv = localMap$iv$iv6;
            } else {
                measurePolicy$iv = measurePolicy$iv6;
                localMap$iv$iv = localMap$iv$iv6;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                int i8 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i9 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1446435874, "C524@18894L19,521@18779L3000:AuroraDs.kt#rimizs");
                Modifier modifierFillMaxWidth$default = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null);
                $composer3.startReplaceGroup(-1293585691);
                ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.Companion.getEmpty()) {
                    value$iv4 = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda17
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AuroraDsKt.AuroraDropdownRow$lambda$50$lambda$38$lambda$37(expanded$delegate2);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv;
                }
                $composer3.endReplaceGroup();
                Modifier modifier$iv2 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierFillMaxWidth$default, enabled2, null, null, (Function0) value$iv4, 6, null), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
                Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                int $changed$iv$iv2 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                constructor = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    localMap$iv$iv3 = localMap$iv$iv2;
                    measurePolicy$iv3 = measurePolicy$iv2;
                } else {
                    localMap$iv$iv3 = localMap$iv$iv2;
                    measurePolicy$iv3 = measurePolicy$iv2;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
                    int i10 = ($changed$iv$iv$iv2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i11 = ((384 >> 6) & 112) | 6;
                    RowScope $this$AuroraDropdownRow_u24lambda_u2450_u24lambda_u2444 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1853987694, "C528@19077L347,534@19437L2332:AuroraDs.kt#rimizs");
                    Modifier modifier$iv3 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraDropdownRow_u24lambda_u2450_u24lambda_u2444, Modifier.Companion, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv2 = Alignment.Companion.getStart();
                    measurePolicy$iv4 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv3 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                    constructor2 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        $composer3.createNode(constructor2);
                    } else {
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv4, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                        localMap$iv$iv5 = localMap$iv$iv4;
                        measurePolicy$iv5 = measurePolicy$iv4;
                    } else {
                        localMap$iv$iv5 = localMap$iv$iv4;
                        measurePolicy$iv5 = measurePolicy$iv4;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        }
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
                        int i12 = ($changed$iv$iv$iv3 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                        int i13 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1654829855, "C529@19154L10,529@19134L83:AuroraDs.kt#rimizs");
                        $dirty = $dirty3;
                        customLabel2 = customLabel3;
                        blankLabel2 = blankLabel4;
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty3 & 14) | 199680, 0, 131026);
                        $composer3.startReplaceGroup(-85162624);
                        ComposerKt.sourceInformation($composer3, "531@19310L15,531@19287L105");
                        str = subtitle2;
                        if (str != null || StringsKt.isBlank(str)) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (z4) {
                            $dirty2 = $dirty;
                            i2 = 0;
                        } else {
                            i2 = 0;
                            $dirty2 = $dirty;
                            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 12) & 14) | 3072, 3120, 120818);
                        }
                        $composer3.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        int $changed$iv = i2;
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Modifier modifier$iv4 = Modifier.Companion;
                        Alignment contentAlignment$iv = Alignment.Companion.getTopStart();
                        MeasurePolicy measurePolicy$iv7 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv4 = ($changed$iv << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, i2);
                        CompositionLocalMap localMap$iv$iv7 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer3, modifier$iv4);
                        constructor3 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function2 = constructor3;
                            $composer3.createNode(function2);
                        } else {
                            function2 = constructor3;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv7, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv7, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash4);
                        }
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv4, ComposeUiNode.Companion.getSetModifier());
                        int i14 = ($changed$iv$iv$iv4 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i15 = (($changed$iv >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1655214875, "C538@19597L13,539@19650L14,535@19459L954,556@20524L20,557@20597L19,558@20637L1118,554@20430L1325:AuroraDs.kt#rimizs");
                        Modifier modifier$iv5 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), AuroraColorSchemeKt.getAuroraSurface($composer3, 0), null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer3, 0), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), ai.nous.aurora.compat.Compat.m7016constructorimpl(10), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
                        Alignment.Vertical verticalAlignment$iv2 = Alignment.Companion.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getStart();
                        int $i$f$Row = ((384 >> 3) & 14) | ((384 >> 3) & 112);
                        MeasurePolicy measurePolicy$iv8 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer3, $i$f$Row);
                        int $changed$iv$iv5 = (384 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv8 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer3, modifier$iv5);
                        constructor4 = ComposeUiNode.Companion.getConstructor();
                        int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function3 = constructor4;
                            $composer3.createNode(function3);
                        } else {
                            function3 = constructor4;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv8, ComposeUiNode.Companion.getSetMeasurePolicy());
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv8, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash5);
                        }
                        ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv5, ComposeUiNode.Companion.getSetModifier());
                        int i16 = ($changed$iv$iv$iv5 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        int i17 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 744379049, "C543@19872L407,552@20346L15,552@20300L95:AuroraDs.kt#rimizs");
                        if (StringsKt.isBlank(value)) {
                            $composer3.startReplaceGroup(-1361458658);
                            ComposerKt.sourceInformation($composer3, "545@19964L15");
                            auroraText = AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0);
                        } else {
                            $composer3.startReplaceGroup(-1361457991);
                            ComposerKt.sourceInformation($composer3, "545@19985L10");
                            auroraText = AuroraColorSchemeKt.getAuroraText($composer3, 0);
                        }
                        $composer3.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(display, ai.nous.aurora.compat.Compat.m1086widthInVpY3zN4$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(160), 1, null), auroraText, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199728, 3120, 120784);
                        ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(ArrowDropDownKt.getArrowDropDown(Icons.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), $composer3, 432, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        boolean zAuroraDropdownRow$lambda$25 = AuroraDropdownRow$lambda$25(expanded$delegate2);
                        $composer3.startReplaceGroup(-85121500);
                        ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                        it$iv2 = $composer3.rememberedValue();
                        if (it$iv2 == Composer.Companion.getEmpty()) {
                            expanded$delegate = expanded$delegate2;
                            final MutableState expanded$delegate$c3541 = expanded$delegate;
                            value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return AuroraDsKt.AuroraDropdownRow$lambda$50$lambda$44$lambda$43$lambda$42$lambda$41(expanded$delegate$c3541);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv5);
                        } else {
                            expanded$delegate = expanded$delegate2;
                            value$iv5 = it$iv2;
                        }
                        $composer3.endReplaceGroup();
                        ai.nous.aurora.compat.Compat.m2138DropdownMenuIlH_yew(zAuroraDropdownRow$lambda$25, (Function0) value$iv5, ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(Modifier.Companion, AuroraColorSchemeKt.getAuroraSurfaceRaised($composer3, 0), null, 2, null), 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(693504979, true, new AuroraDsKt$AuroraDropdownRow$1$2$2$3(opts, blankLabel2, value, allowCustom2, customLabel2, onSelect, expanded$delegate, customMode$delegate2), $composer3, 54), $composer3, 48, 48, 2040);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.startReplaceGroup(-1293491932);
                        ComposerKt.sourceInformation($composer3, "587@21833L1115");
                        if (AuroraDropdownRow$lambda$28(customMode$delegate2) || !allowCustom2) {
                            $composer2 = $composer3;
                        } else {
                            Modifier modifier$iv6 = ai.nous.aurora.compat.Compat.m1038paddingqDBjuR0$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), 0.0f, AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(10), 2, null);
                            Alignment.Vertical verticalAlignment$iv3 = Alignment.Companion.getCenterVertically();
                            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                            Arrangement.Horizontal horizontalArrangement$iv3 = Arrangement.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv9 = RowKt.rowMeasurePolicy(horizontalArrangement$iv3, verticalAlignment$iv3, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                            int $changed$iv$iv6 = (390 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv9 = $composer3.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer3, modifier$iv6);
                            Function0<ComposeUiNode> constructor6 = ComposeUiNode.Companion.getConstructor();
                            int $changed$iv$iv$iv6 = (($changed$iv$iv6 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                                function4 = constructor6;
                                $composer3.createNode(function4);
                            } else {
                                function4 = constructor6;
                                $composer3.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv6 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv9, ComposeUiNode.Companion.getSetMeasurePolicy());
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv9, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv6.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv6.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv6))) {
                                $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                                $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash6);
                            }
                            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv6, ComposeUiNode.Companion.getSetModifier());
                            int i18 = ($changed$iv$iv$iv6 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                            int i19 = ((390 >> 6) & 112) | 6;
                            RowScope $this$AuroraDropdownRow_u24lambda_u2450_u24lambda_u2449 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1856938522, "C595@22207L20,593@22110L276,602@22465L12,607@22744L131,600@22403L531:AuroraDs.kt#rimizs");
                            String strAuroraDropdownRow$lambda$31 = AuroraDropdownRow$lambda$31(customDraft$delegate);
                            Modifier modifierWeight$default = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraDropdownRow_u24lambda_u2450_u24lambda_u2449, Modifier.Companion, 1.0f, false, 2, null);
                            $composer3.startReplaceGroup(-1602664443);
                            ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                            boolean invalid$iv2 = $composer3.changed(customDraft$delegate);
                            Object it$iv6 = $composer3.rememberedValue();
                            if (invalid$iv2 || it$iv6 == Composer.Companion.getEmpty()) {
                                value$iv6 = new Function1() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda2
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return AuroraDsKt.AuroraDropdownRow$lambda$50$lambda$49$lambda$46$lambda$45(customDraft$delegate, (String) obj);
                                    }
                                };
                                $composer3.updateRememberedValue(value$iv6);
                            } else {
                                value$iv6 = it$iv6;
                            }
                            $composer3.endReplaceGroup();
                            AuroraTextFieldRow(strAuroraDropdownRow$lambda$31, (Function1) value$iv6, modifierWeight$default, "Enter value", enabled2, false, $composer3, (57344 & ($dirty2 >> 3)) | 3072, 32);
                            long auroraAccent = AuroraColorSchemeKt.getAuroraAccent($composer3, 0);
                            GenericFontFamily monospace = FontFamily.Companion.getMonospace();
                            long sp = TextUnitKt.getSp(11);
                            FontWeight bold = FontWeight.Companion.getBold();
                            Modifier companion = Modifier.Companion;
                            boolean z5 = enabled2 && !StringsKt.isBlank(AuroraDropdownRow$lambda$31(customDraft$delegate));
                            $composer3.startReplaceGroup(-1602647148);
                            ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                            $composer2 = $composer3;
                            boolean invalid$iv3 = (($dirty2 & 7168) == 2048) | $composer2.changed(customDraft$delegate);
                            Object it$iv7 = $composer3.rememberedValue();
                            if (invalid$iv3 || it$iv7 == Composer.Companion.getEmpty()) {
                                customMode$delegate = customMode$delegate2;
                                value$iv7 = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda3
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return AuroraDsKt.AuroraDropdownRow$lambda$50$lambda$49$lambda$48$lambda$47(onSelect, customDraft$delegate, customMode$delegate);
                                    }
                                };
                                $composer3.updateRememberedValue(value$iv7);
                            } else {
                                value$iv7 = it$iv7;
                                customMode$delegate = customMode$delegate2;
                            }
                            $composer3.endReplaceGroup();
                            ai.nous.aurora.compat.Compat.m3067Text4IGK_g("SAVE", ai.nous.aurora.compat.Compat.m1034padding3ABfNKs(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(companion, z5, null, null, (Function0) value$iv7, 6, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(10)), auroraAccent, sp, (FontStyle) null, bold, (FontFamily) monospace, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199686, 0, 130960);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                        }
                        $composer3.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.Companion.getSetModifier());
                    int i110 = ($changed$iv$iv$iv3 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                    int i111 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1654829855, "C529@19154L10,529@19134L83:AuroraDs.kt#rimizs");
                    $dirty = $dirty3;
                    customLabel2 = customLabel3;
                    blankLabel2 = blankLabel4;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty3 & 14) | 199680, 0, 131026);
                    $composer3.startReplaceGroup(-85162624);
                    ComposerKt.sourceInformation($composer3, "531@19310L15,531@19287L105");
                    str = subtitle2;
                    if (str != null) {
                        z4 = true;
                    } else {
                        z4 = true;
                    }
                    if (z4) {
                        i2 = 0;
                        $dirty2 = $dirty;
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 12) & 14) | 3072, 3120, 120818);
                    } else {
                        $dirty2 = $dirty;
                        i2 = 0;
                    }
                    $composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    int $changed$iv2 = i2;
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Modifier modifier$iv7 = Modifier.Companion;
                    Alignment contentAlignment$iv2 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv10 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                    int $changed$iv$iv7 = ($changed$iv2 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, i2);
                    CompositionLocalMap localMap$iv$iv10 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv7 = ComposedModifierKt.materializeModifier($composer3, modifier$iv7);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv7 = (($changed$iv$iv7 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor3;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv10, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv10, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash7);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv7, ComposeUiNode.Companion.getSetModifier());
                    int i112 = ($changed$iv$iv$iv7 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i113 = (($changed$iv2 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1655214875, "C538@19597L13,539@19650L14,535@19459L954,556@20524L20,557@20597L19,558@20637L1118,554@20430L1325:AuroraDs.kt#rimizs");
                    Modifier modifier$iv8 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), AuroraColorSchemeKt.getAuroraSurface($composer3, 0), null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer3, 0), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), ai.nous.aurora.compat.Compat.m7016constructorimpl(10), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
                    Alignment.Vertical verticalAlignment$iv4 = Alignment.Companion.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv4 = Arrangement.INSTANCE.getStart();
                    int $i$f$Row2 = ((384 >> 3) & 14) | ((384 >> 3) & 112);
                    MeasurePolicy measurePolicy$iv11 = RowKt.rowMeasurePolicy(horizontalArrangement$iv4, verticalAlignment$iv4, $composer3, $i$f$Row2);
                    int $changed$iv$iv8 = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv11 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv8 = ComposedModifierKt.materializeModifier($composer3, modifier$iv8);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv8 = (($changed$iv$iv8 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function3 = constructor4;
                        $composer3.createNode(function3);
                    } else {
                        function3 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv11, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv11, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash8);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv8, ComposeUiNode.Companion.getSetModifier());
                    int i114 = ($changed$iv$iv$iv8 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                    int i115 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 744379049, "C543@19872L407,552@20346L15,552@20300L95:AuroraDs.kt#rimizs");
                    if (StringsKt.isBlank(value)) {
                        $composer3.startReplaceGroup(-1361458658);
                        ComposerKt.sourceInformation($composer3, "545@19964L15");
                        auroraText = AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0);
                    } else {
                        $composer3.startReplaceGroup(-1361457991);
                        ComposerKt.sourceInformation($composer3, "545@19985L10");
                        auroraText = AuroraColorSchemeKt.getAuroraText($composer3, 0);
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(display, ai.nous.aurora.compat.Compat.m1086widthInVpY3zN4$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(160), 1, null), auroraText, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199728, 3120, 120784);
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(ArrowDropDownKt.getArrowDropDown(Icons.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), $composer3, 432, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    boolean zAuroraDropdownRow$lambda$26 = AuroraDropdownRow$lambda$25(expanded$delegate2);
                    $composer3.startReplaceGroup(-85121500);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                    it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.Companion.getEmpty()) {
                        expanded$delegate = expanded$delegate2;
                        final MutableState expanded$delegate$c3808 = expanded$delegate;
                        value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AuroraDsKt.AuroraDropdownRow$lambda$50$lambda$44$lambda$43$lambda$42$lambda$41(expanded$delegate$c3808);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv5);
                    } else {
                        expanded$delegate = expanded$delegate2;
                        value$iv5 = it$iv2;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2138DropdownMenuIlH_yew(zAuroraDropdownRow$lambda$26, (Function0) value$iv5, ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(Modifier.Companion, AuroraColorSchemeKt.getAuroraSurfaceRaised($composer3, 0), null, 2, null), 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(693504979, true, new AuroraDsKt$AuroraDropdownRow$1$2$2$3(opts, blankLabel2, value, allowCustom2, customLabel2, onSelect, expanded$delegate, customMode$delegate2), $composer3, 54), $composer3, 48, 48, 2040);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.startReplaceGroup(-1293491932);
                    ComposerKt.sourceInformation($composer3, "587@21833L1115");
                    if (AuroraDropdownRow$lambda$28(customMode$delegate2)) {
                        $composer2 = $composer3;
                    } else {
                        $composer2 = $composer3;
                    }
                    $composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.Companion.getSetModifier());
                int i116 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i117 = ((384 >> 6) & 112) | 6;
                RowScope $this$AuroraDropdownRow_u24lambda_u2450_u24lambda_u2445 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1853987694, "C528@19077L347,534@19437L2332:AuroraDs.kt#rimizs");
                Modifier modifier$iv9 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraDropdownRow_u24lambda_u2450_u24lambda_u2445, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv3 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv3 = Alignment.Companion.getStart();
                measurePolicy$iv4 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv3, horizontalAlignment$iv3, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv9 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv9 = ComposedModifierKt.materializeModifier($composer3, modifier$iv9);
                constructor2 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv9 = (($changed$iv$iv9 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor2);
                } else {
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv4, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash9 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    localMap$iv$iv5 = localMap$iv$iv4;
                    measurePolicy$iv5 = measurePolicy$iv4;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv9, ComposeUiNode.Companion.getSetModifier());
                    int i118 = ($changed$iv$iv$iv9 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                    int i119 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1654829855, "C529@19154L10,529@19134L83:AuroraDs.kt#rimizs");
                    $dirty = $dirty3;
                    customLabel2 = customLabel3;
                    blankLabel2 = blankLabel4;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty3 & 14) | 199680, 0, 131026);
                    $composer3.startReplaceGroup(-85162624);
                    ComposerKt.sourceInformation($composer3, "531@19310L15,531@19287L105");
                    str = subtitle2;
                    if (str != null) {
                        z4 = true;
                    } else {
                        z4 = true;
                    }
                    if (z4) {
                        i2 = 0;
                        $dirty2 = $dirty;
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 12) & 14) | 3072, 3120, 120818);
                    } else {
                        $dirty2 = $dirty;
                        i2 = 0;
                    }
                    $composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    int $changed$iv3 = i2;
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Modifier modifier$iv10 = Modifier.Companion;
                    Alignment contentAlignment$iv3 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv12 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                    int $changed$iv$iv10 = ($changed$iv3 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, i2);
                    CompositionLocalMap localMap$iv$iv12 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv10 = ComposedModifierKt.materializeModifier($composer3, modifier$iv10);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv10 = (($changed$iv$iv10 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor3;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv12, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv12, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash10 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash10);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv10, ComposeUiNode.Companion.getSetModifier());
                    int i1110 = ($changed$iv$iv$iv10 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    int i1111 = (($changed$iv3 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1655214875, "C538@19597L13,539@19650L14,535@19459L954,556@20524L20,557@20597L19,558@20637L1118,554@20430L1325:AuroraDs.kt#rimizs");
                    Modifier modifier$iv11 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), AuroraColorSchemeKt.getAuroraSurface($composer3, 0), null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer3, 0), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), ai.nous.aurora.compat.Compat.m7016constructorimpl(10), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
                    Alignment.Vertical verticalAlignment$iv5 = Alignment.Companion.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv5 = Arrangement.INSTANCE.getStart();
                    int $i$f$Row3 = ((384 >> 3) & 14) | ((384 >> 3) & 112);
                    MeasurePolicy measurePolicy$iv13 = RowKt.rowMeasurePolicy(horizontalArrangement$iv5, verticalAlignment$iv5, $composer3, $i$f$Row3);
                    int $changed$iv$iv11 = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv13 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv11 = ComposedModifierKt.materializeModifier($composer3, modifier$iv11);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv11 = (($changed$iv$iv11 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function3 = constructor4;
                        $composer3.createNode(function3);
                    } else {
                        function3 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv13, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv13, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash11);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv11, ComposeUiNode.Companion.getSetModifier());
                    int i1112 = ($changed$iv$iv$iv11 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                    int i1113 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 744379049, "C543@19872L407,552@20346L15,552@20300L95:AuroraDs.kt#rimizs");
                    if (StringsKt.isBlank(value)) {
                        $composer3.startReplaceGroup(-1361458658);
                        ComposerKt.sourceInformation($composer3, "545@19964L15");
                        auroraText = AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0);
                    } else {
                        $composer3.startReplaceGroup(-1361457991);
                        ComposerKt.sourceInformation($composer3, "545@19985L10");
                        auroraText = AuroraColorSchemeKt.getAuroraText($composer3, 0);
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(display, ai.nous.aurora.compat.Compat.m1086widthInVpY3zN4$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(160), 1, null), auroraText, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199728, 3120, 120784);
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(ArrowDropDownKt.getArrowDropDown(Icons.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), $composer3, 432, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    boolean zAuroraDropdownRow$lambda$27 = AuroraDropdownRow$lambda$25(expanded$delegate2);
                    $composer3.startReplaceGroup(-85121500);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                    it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.Companion.getEmpty()) {
                        expanded$delegate = expanded$delegate2;
                        final MutableState expanded$delegate$c4024 = expanded$delegate;
                        value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AuroraDsKt.AuroraDropdownRow$lambda$50$lambda$44$lambda$43$lambda$42$lambda$41(expanded$delegate$c4024);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv5);
                    } else {
                        expanded$delegate = expanded$delegate2;
                        value$iv5 = it$iv2;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2138DropdownMenuIlH_yew(zAuroraDropdownRow$lambda$27, (Function0) value$iv5, ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(Modifier.Companion, AuroraColorSchemeKt.getAuroraSurfaceRaised($composer3, 0), null, 2, null), 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(693504979, true, new AuroraDsKt$AuroraDropdownRow$1$2$2$3(opts, blankLabel2, value, allowCustom2, customLabel2, onSelect, expanded$delegate, customMode$delegate2), $composer3, 54), $composer3, 48, 48, 2040);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.startReplaceGroup(-1293491932);
                    ComposerKt.sourceInformation($composer3, "587@21833L1115");
                    if (AuroraDropdownRow$lambda$28(customMode$delegate2)) {
                        $composer2 = $composer3;
                    } else {
                        $composer2 = $composer3;
                    }
                    $composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    localMap$iv$iv5 = localMap$iv$iv4;
                    measurePolicy$iv5 = measurePolicy$iv4;
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash9);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv9, ComposeUiNode.Companion.getSetModifier());
                int i1114 = ($changed$iv$iv$iv9 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                int i1115 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1654829855, "C529@19154L10,529@19134L83:AuroraDs.kt#rimizs");
                $dirty = $dirty3;
                customLabel2 = customLabel3;
                blankLabel2 = blankLabel4;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty3 & 14) | 199680, 0, 131026);
                $composer3.startReplaceGroup(-85162624);
                ComposerKt.sourceInformation($composer3, "531@19310L15,531@19287L105");
                str = subtitle2;
                if (str != null) {
                    z4 = true;
                } else {
                    z4 = true;
                }
                if (z4) {
                    i2 = 0;
                    $dirty2 = $dirty;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 12) & 14) | 3072, 3120, 120818);
                } else {
                    $dirty2 = $dirty;
                    i2 = 0;
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                int $changed$iv4 = i2;
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Modifier modifier$iv12 = Modifier.Companion;
                Alignment contentAlignment$iv4 = Alignment.Companion.getTopStart();
                MeasurePolicy measurePolicy$iv14 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
                int $changed$iv$iv12 = ($changed$iv4 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, i2);
                CompositionLocalMap localMap$iv$iv14 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv12 = ComposedModifierKt.materializeModifier($composer3, modifier$iv12);
                constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv12 = (($changed$iv$iv12 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor3;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv14, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv14, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash12 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash12);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv12, ComposeUiNode.Companion.getSetModifier());
                int i1116 = ($changed$iv$iv$iv12 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                int i1117 = (($changed$iv4 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1655214875, "C538@19597L13,539@19650L14,535@19459L954,556@20524L20,557@20597L19,558@20637L1118,554@20430L1325:AuroraDs.kt#rimizs");
                Modifier modifier$iv13 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), AuroraColorSchemeKt.getAuroraSurface($composer3, 0), null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer3, 0), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), ai.nous.aurora.compat.Compat.m7016constructorimpl(10), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
                Alignment.Vertical verticalAlignment$iv6 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv6 = Arrangement.INSTANCE.getStart();
                int $i$f$Row4 = ((384 >> 3) & 14) | ((384 >> 3) & 112);
                MeasurePolicy measurePolicy$iv15 = RowKt.rowMeasurePolicy(horizontalArrangement$iv6, verticalAlignment$iv6, $composer3, $i$f$Row4);
                int $changed$iv$iv13 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv15 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv13 = ComposedModifierKt.materializeModifier($composer3, modifier$iv13);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv13 = (($changed$iv$iv13 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function3 = constructor4;
                    $composer3.createNode(function3);
                } else {
                    function3 = constructor4;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv15, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv15, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash13 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash13);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv13, ComposeUiNode.Companion.getSetModifier());
                int i1118 = ($changed$iv$iv$iv13 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                int i1119 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 744379049, "C543@19872L407,552@20346L15,552@20300L95:AuroraDs.kt#rimizs");
                if (StringsKt.isBlank(value)) {
                    $composer3.startReplaceGroup(-1361458658);
                    ComposerKt.sourceInformation($composer3, "545@19964L15");
                    auroraText = AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0);
                } else {
                    $composer3.startReplaceGroup(-1361457991);
                    ComposerKt.sourceInformation($composer3, "545@19985L10");
                    auroraText = AuroraColorSchemeKt.getAuroraText($composer3, 0);
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(display, ai.nous.aurora.compat.Compat.m1086widthInVpY3zN4$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(160), 1, null), auroraText, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199728, 3120, 120784);
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(ArrowDropDownKt.getArrowDropDown(Icons.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), $composer3, 432, 0);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                boolean zAuroraDropdownRow$lambda$28 = AuroraDropdownRow$lambda$25(expanded$delegate2);
                $composer3.startReplaceGroup(-85121500);
                ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.Companion.getEmpty()) {
                    expanded$delegate = expanded$delegate2;
                    final MutableState expanded$delegate$c4206 = expanded$delegate;
                    value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AuroraDsKt.AuroraDropdownRow$lambda$50$lambda$44$lambda$43$lambda$42$lambda$41(expanded$delegate$c4206);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv5);
                } else {
                    expanded$delegate = expanded$delegate2;
                    value$iv5 = it$iv2;
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m2138DropdownMenuIlH_yew(zAuroraDropdownRow$lambda$28, (Function0) value$iv5, ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(Modifier.Companion, AuroraColorSchemeKt.getAuroraSurfaceRaised($composer3, 0), null, 2, null), 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(693504979, true, new AuroraDsKt$AuroraDropdownRow$1$2$2$3(opts, blankLabel2, value, allowCustom2, customLabel2, onSelect, expanded$delegate, customMode$delegate2), $composer3, 54), $composer3, 48, 48, 2040);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.startReplaceGroup(-1293491932);
                ComposerKt.sourceInformation($composer3, "587@21833L1115");
                if (AuroraDropdownRow$lambda$28(customMode$delegate2)) {
                    $composer2 = $composer3;
                } else {
                    $composer2 = $composer3;
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
            $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i20 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
            int i21 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1446435874, "C524@18894L19,521@18779L3000:AuroraDs.kt#rimizs");
            Modifier modifierFillMaxWidth$default2 = ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null);
            $composer3.startReplaceGroup(-1293585691);
            ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
            it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.Companion.getEmpty()) {
                value$iv4 = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AuroraDsKt.AuroraDropdownRow$lambda$50$lambda$38$lambda$37(expanded$delegate2);
                    }
                };
                $composer3.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv;
            }
            $composer3.endReplaceGroup();
            Modifier modifier$iv14 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(modifierFillMaxWidth$default2, enabled2, null, null, (Function0) value$iv4, 6, null), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(10));
            Alignment.Vertical verticalAlignment$iv7 = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv7 = Arrangement.INSTANCE.getStart();
            measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv7, verticalAlignment$iv7, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv14 = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv14 = ComposedModifierKt.materializeModifier($composer3, modifier$iv14);
            constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv14 = (($changed$iv$iv14 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor;
                $composer3.createNode(function1);
            } else {
                function1 = constructor;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash14 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                localMap$iv$iv3 = localMap$iv$iv2;
                measurePolicy$iv3 = measurePolicy$iv2;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv14, ComposeUiNode.Companion.getSetModifier());
                int i1120 = ($changed$iv$iv$iv14 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i1121 = ((384 >> 6) & 112) | 6;
                RowScope $this$AuroraDropdownRow_u24lambda_u2450_u24lambda_u2446 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1853987694, "C528@19077L347,534@19437L2332:AuroraDs.kt#rimizs");
                Modifier modifier$iv15 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraDropdownRow_u24lambda_u2450_u24lambda_u2446, Modifier.Companion, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv4 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv4 = Alignment.Companion.getStart();
                measurePolicy$iv4 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv4, horizontalAlignment$iv4, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv15 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv15 = ComposedModifierKt.materializeModifier($composer3, modifier$iv15);
                constructor2 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv15 = (($changed$iv$iv15 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor2);
                } else {
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv4, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash15 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    localMap$iv$iv5 = localMap$iv$iv4;
                    measurePolicy$iv5 = measurePolicy$iv4;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv15, ComposeUiNode.Companion.getSetModifier());
                    int i11110 = ($changed$iv$iv$iv15 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance7 = ColumnScopeInstance.INSTANCE;
                    int i11111 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1654829855, "C529@19154L10,529@19134L83:AuroraDs.kt#rimizs");
                    $dirty = $dirty3;
                    customLabel2 = customLabel3;
                    blankLabel2 = blankLabel4;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty3 & 14) | 199680, 0, 131026);
                    $composer3.startReplaceGroup(-85162624);
                    ComposerKt.sourceInformation($composer3, "531@19310L15,531@19287L105");
                    str = subtitle2;
                    if (str != null) {
                        z4 = true;
                    } else {
                        z4 = true;
                    }
                    if (z4) {
                        i2 = 0;
                        $dirty2 = $dirty;
                        ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 12) & 14) | 3072, 3120, 120818);
                    } else {
                        $dirty2 = $dirty;
                        i2 = 0;
                    }
                    $composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    int $changed$iv5 = i2;
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Modifier modifier$iv16 = Modifier.Companion;
                    Alignment contentAlignment$iv5 = Alignment.Companion.getTopStart();
                    MeasurePolicy measurePolicy$iv16 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv5, false);
                    int $changed$iv$iv16 = ($changed$iv5 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, i2);
                    CompositionLocalMap localMap$iv$iv16 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv16 = ComposedModifierKt.materializeModifier($composer3, modifier$iv16);
                    constructor3 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv16 = (($changed$iv$iv16 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor3;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv16, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv16, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash16 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash16);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv16, ComposeUiNode.Companion.getSetModifier());
                    int i11112 = ($changed$iv$iv$iv16 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                    int i11113 = (($changed$iv5 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1655214875, "C538@19597L13,539@19650L14,535@19459L954,556@20524L20,557@20597L19,558@20637L1118,554@20430L1325:AuroraDs.kt#rimizs");
                    Modifier modifier$iv17 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), AuroraColorSchemeKt.getAuroraSurface($composer3, 0), null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer3, 0), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), ai.nous.aurora.compat.Compat.m7016constructorimpl(10), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
                    Alignment.Vertical verticalAlignment$iv8 = Alignment.Companion.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv8 = Arrangement.INSTANCE.getStart();
                    int $i$f$Row5 = ((384 >> 3) & 14) | ((384 >> 3) & 112);
                    MeasurePolicy measurePolicy$iv17 = RowKt.rowMeasurePolicy(horizontalArrangement$iv8, verticalAlignment$iv8, $composer3, $i$f$Row5);
                    int $changed$iv$iv17 = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv17 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv17 = ComposedModifierKt.materializeModifier($composer3, modifier$iv17);
                    constructor4 = ComposeUiNode.Companion.getConstructor();
                    int $changed$iv$iv$iv17 = (($changed$iv$iv17 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function3 = constructor4;
                        $composer3.createNode(function3);
                    } else {
                        function3 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv17, ComposeUiNode.Companion.getSetMeasurePolicy());
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv17, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash17 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash17);
                    ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv17, ComposeUiNode.Companion.getSetModifier());
                    int i11114 = ($changed$iv$iv$iv17 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                    int i11115 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 744379049, "C543@19872L407,552@20346L15,552@20300L95:AuroraDs.kt#rimizs");
                    if (StringsKt.isBlank(value)) {
                        $composer3.startReplaceGroup(-1361458658);
                        ComposerKt.sourceInformation($composer3, "545@19964L15");
                        auroraText = AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0);
                    } else {
                        $composer3.startReplaceGroup(-1361457991);
                        ComposerKt.sourceInformation($composer3, "545@19985L10");
                        auroraText = AuroraColorSchemeKt.getAuroraText($composer3, 0);
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(display, ai.nous.aurora.compat.Compat.m1086widthInVpY3zN4$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(160), 1, null), auroraText, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199728, 3120, 120784);
                    ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(ArrowDropDownKt.getArrowDropDown(Icons.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), $composer3, 432, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    boolean zAuroraDropdownRow$lambda$29 = AuroraDropdownRow$lambda$25(expanded$delegate2);
                    $composer3.startReplaceGroup(-85121500);
                    ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                    it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.Companion.getEmpty()) {
                        expanded$delegate = expanded$delegate2;
                        final MutableState expanded$delegate$c4477 = expanded$delegate;
                        value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AuroraDsKt.AuroraDropdownRow$lambda$50$lambda$44$lambda$43$lambda$42$lambda$41(expanded$delegate$c4477);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv5);
                    } else {
                        expanded$delegate = expanded$delegate2;
                        value$iv5 = it$iv2;
                    }
                    $composer3.endReplaceGroup();
                    ai.nous.aurora.compat.Compat.m2138DropdownMenuIlH_yew(zAuroraDropdownRow$lambda$29, (Function0) value$iv5, ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(Modifier.Companion, AuroraColorSchemeKt.getAuroraSurfaceRaised($composer3, 0), null, 2, null), 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(693504979, true, new AuroraDsKt$AuroraDropdownRow$1$2$2$3(opts, blankLabel2, value, allowCustom2, customLabel2, onSelect, expanded$delegate, customMode$delegate2), $composer3, 54), $composer3, 48, 48, 2040);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.startReplaceGroup(-1293491932);
                    ComposerKt.sourceInformation($composer3, "587@21833L1115");
                    if (AuroraDropdownRow$lambda$28(customMode$delegate2)) {
                        $composer2 = $composer3;
                    } else {
                        $composer2 = $composer3;
                    }
                    $composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    localMap$iv$iv5 = localMap$iv$iv4;
                    measurePolicy$iv5 = measurePolicy$iv4;
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash15);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv15, ComposeUiNode.Companion.getSetModifier());
                int i11116 = ($changed$iv$iv$iv15 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance8 = ColumnScopeInstance.INSTANCE;
                int i11117 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1654829855, "C529@19154L10,529@19134L83:AuroraDs.kt#rimizs");
                $dirty = $dirty3;
                customLabel2 = customLabel3;
                blankLabel2 = blankLabel4;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty3 & 14) | 199680, 0, 131026);
                $composer3.startReplaceGroup(-85162624);
                ComposerKt.sourceInformation($composer3, "531@19310L15,531@19287L105");
                str = subtitle2;
                if (str != null) {
                    z4 = true;
                } else {
                    z4 = true;
                }
                if (z4) {
                    i2 = 0;
                    $dirty2 = $dirty;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 12) & 14) | 3072, 3120, 120818);
                } else {
                    $dirty2 = $dirty;
                    i2 = 0;
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                int $changed$iv6 = i2;
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Modifier modifier$iv18 = Modifier.Companion;
                Alignment contentAlignment$iv6 = Alignment.Companion.getTopStart();
                MeasurePolicy measurePolicy$iv18 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv6, false);
                int $changed$iv$iv18 = ($changed$iv6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, i2);
                CompositionLocalMap localMap$iv$iv18 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv18 = ComposedModifierKt.materializeModifier($composer3, modifier$iv18);
                constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv18 = (($changed$iv$iv18 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor3;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv18, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv18, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash18 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash18);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv18, ComposeUiNode.Companion.getSetModifier());
                int i11118 = ($changed$iv$iv$iv18 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                int i11119 = (($changed$iv6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1655214875, "C538@19597L13,539@19650L14,535@19459L954,556@20524L20,557@20597L19,558@20637L1118,554@20430L1325:AuroraDs.kt#rimizs");
                Modifier modifier$iv19 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), AuroraColorSchemeKt.getAuroraSurface($composer3, 0), null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer3, 0), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), ai.nous.aurora.compat.Compat.m7016constructorimpl(10), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
                Alignment.Vertical verticalAlignment$iv9 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv9 = Arrangement.INSTANCE.getStart();
                int $i$f$Row6 = ((384 >> 3) & 14) | ((384 >> 3) & 112);
                MeasurePolicy measurePolicy$iv19 = RowKt.rowMeasurePolicy(horizontalArrangement$iv9, verticalAlignment$iv9, $composer3, $i$f$Row6);
                int $changed$iv$iv19 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv19 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv19 = ComposedModifierKt.materializeModifier($composer3, modifier$iv19);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv19 = (($changed$iv$iv19 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function3 = constructor4;
                    $composer3.createNode(function3);
                } else {
                    function3 = constructor4;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv19, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv19, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash19 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash19);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv19, ComposeUiNode.Companion.getSetModifier());
                int i111110 = ($changed$iv$iv$iv19 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance6 = RowScopeInstance.INSTANCE;
                int i111111 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 744379049, "C543@19872L407,552@20346L15,552@20300L95:AuroraDs.kt#rimizs");
                if (StringsKt.isBlank(value)) {
                    $composer3.startReplaceGroup(-1361458658);
                    ComposerKt.sourceInformation($composer3, "545@19964L15");
                    auroraText = AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0);
                } else {
                    $composer3.startReplaceGroup(-1361457991);
                    ComposerKt.sourceInformation($composer3, "545@19985L10");
                    auroraText = AuroraColorSchemeKt.getAuroraText($composer3, 0);
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(display, ai.nous.aurora.compat.Compat.m1086widthInVpY3zN4$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(160), 1, null), auroraText, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199728, 3120, 120784);
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(ArrowDropDownKt.getArrowDropDown(Icons.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), $composer3, 432, 0);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                boolean zAuroraDropdownRow$lambda$210 = AuroraDropdownRow$lambda$25(expanded$delegate2);
                $composer3.startReplaceGroup(-85121500);
                ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.Companion.getEmpty()) {
                    expanded$delegate = expanded$delegate2;
                    final MutableState expanded$delegate$c4659 = expanded$delegate;
                    value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AuroraDsKt.AuroraDropdownRow$lambda$50$lambda$44$lambda$43$lambda$42$lambda$41(expanded$delegate$c4659);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv5);
                } else {
                    expanded$delegate = expanded$delegate2;
                    value$iv5 = it$iv2;
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m2138DropdownMenuIlH_yew(zAuroraDropdownRow$lambda$210, (Function0) value$iv5, ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(Modifier.Companion, AuroraColorSchemeKt.getAuroraSurfaceRaised($composer3, 0), null, 2, null), 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(693504979, true, new AuroraDsKt$AuroraDropdownRow$1$2$2$3(opts, blankLabel2, value, allowCustom2, customLabel2, onSelect, expanded$delegate, customMode$delegate2), $composer3, 54), $composer3, 48, 48, 2040);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.startReplaceGroup(-1293491932);
                ComposerKt.sourceInformation($composer3, "587@21833L1115");
                if (AuroraDropdownRow$lambda$28(customMode$delegate2)) {
                    $composer2 = $composer3;
                } else {
                    $composer2 = $composer3;
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                localMap$iv$iv3 = localMap$iv$iv2;
                measurePolicy$iv3 = measurePolicy$iv2;
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash14);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv14, ComposeUiNode.Companion.getSetModifier());
            int i1122 = ($changed$iv$iv$iv14 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i1123 = ((384 >> 6) & 112) | 6;
            RowScope $this$AuroraDropdownRow_u24lambda_u2450_u24lambda_u2447 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 1853987694, "C528@19077L347,534@19437L2332:AuroraDs.kt#rimizs");
            Modifier modifier$iv110 = ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraDropdownRow_u24lambda_u2450_u24lambda_u2447, Modifier.Companion, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv5 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv5 = Alignment.Companion.getStart();
            measurePolicy$iv4 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv5, horizontalAlignment$iv5, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv110 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv110 = ComposedModifierKt.materializeModifier($composer3, modifier$iv110);
            constructor2 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv110 = (($changed$iv$iv110 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor2);
            } else {
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv4, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash110 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                localMap$iv$iv5 = localMap$iv$iv4;
                measurePolicy$iv5 = measurePolicy$iv4;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv110, ComposeUiNode.Companion.getSetModifier());
                int i111112 = ($changed$iv$iv$iv110 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance9 = ColumnScopeInstance.INSTANCE;
                int i111113 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1654829855, "C529@19154L10,529@19134L83:AuroraDs.kt#rimizs");
                $dirty = $dirty3;
                customLabel2 = customLabel3;
                blankLabel2 = blankLabel4;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty3 & 14) | 199680, 0, 131026);
                $composer3.startReplaceGroup(-85162624);
                ComposerKt.sourceInformation($composer3, "531@19310L15,531@19287L105");
                str = subtitle2;
                if (str != null) {
                    z4 = true;
                } else {
                    z4 = true;
                }
                if (z4) {
                    i2 = 0;
                    $dirty2 = $dirty;
                    ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 12) & 14) | 3072, 3120, 120818);
                } else {
                    $dirty2 = $dirty;
                    i2 = 0;
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                int $changed$iv7 = i2;
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Modifier modifier$iv111 = Modifier.Companion;
                Alignment contentAlignment$iv7 = Alignment.Companion.getTopStart();
                MeasurePolicy measurePolicy$iv110 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv7, false);
                int $changed$iv$iv111 = ($changed$iv7 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, i2);
                CompositionLocalMap localMap$iv$iv110 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv111 = ComposedModifierKt.materializeModifier($composer3, modifier$iv111);
                constructor3 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv111 = (($changed$iv$iv111 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor3;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv110, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv110, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash111);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv111, ComposeUiNode.Companion.getSetModifier());
                int i111114 = ($changed$iv$iv$iv111 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                int i111115 = (($changed$iv7 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1655214875, "C538@19597L13,539@19650L14,535@19459L954,556@20524L20,557@20597L19,558@20637L1118,554@20430L1325:AuroraDs.kt#rimizs");
                Modifier modifier$iv112 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), AuroraColorSchemeKt.getAuroraSurface($composer3, 0), null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer3, 0), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), ai.nous.aurora.compat.Compat.m7016constructorimpl(10), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
                Alignment.Vertical verticalAlignment$iv10 = Alignment.Companion.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv10 = Arrangement.INSTANCE.getStart();
                int $i$f$Row7 = ((384 >> 3) & 14) | ((384 >> 3) & 112);
                MeasurePolicy measurePolicy$iv111 = RowKt.rowMeasurePolicy(horizontalArrangement$iv10, verticalAlignment$iv10, $composer3, $i$f$Row7);
                int $changed$iv$iv112 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv111 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv112 = ComposedModifierKt.materializeModifier($composer3, modifier$iv112);
                constructor4 = ComposeUiNode.Companion.getConstructor();
                int $changed$iv$iv$iv112 = (($changed$iv$iv112 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function3 = constructor4;
                    $composer3.createNode(function3);
                } else {
                    function3 = constructor4;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv111, ComposeUiNode.Companion.getSetMeasurePolicy());
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv111, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash112 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash112);
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv112, ComposeUiNode.Companion.getSetModifier());
                int i111116 = ($changed$iv$iv$iv112 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance7 = RowScopeInstance.INSTANCE;
                int i111117 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 744379049, "C543@19872L407,552@20346L15,552@20300L95:AuroraDs.kt#rimizs");
                if (StringsKt.isBlank(value)) {
                    $composer3.startReplaceGroup(-1361458658);
                    ComposerKt.sourceInformation($composer3, "545@19964L15");
                    auroraText = AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0);
                } else {
                    $composer3.startReplaceGroup(-1361457991);
                    ComposerKt.sourceInformation($composer3, "545@19985L10");
                    auroraText = AuroraColorSchemeKt.getAuroraText($composer3, 0);
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(display, ai.nous.aurora.compat.Compat.m1086widthInVpY3zN4$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(160), 1, null), auroraText, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199728, 3120, 120784);
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(ArrowDropDownKt.getArrowDropDown(Icons.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), $composer3, 432, 0);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                boolean zAuroraDropdownRow$lambda$211 = AuroraDropdownRow$lambda$25(expanded$delegate2);
                $composer3.startReplaceGroup(-85121500);
                ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
                it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.Companion.getEmpty()) {
                    expanded$delegate = expanded$delegate2;
                    final MutableState expanded$delegate$c4878 = expanded$delegate;
                    value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AuroraDsKt.AuroraDropdownRow$lambda$50$lambda$44$lambda$43$lambda$42$lambda$41(expanded$delegate$c4878);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv5);
                } else {
                    expanded$delegate = expanded$delegate2;
                    value$iv5 = it$iv2;
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m2138DropdownMenuIlH_yew(zAuroraDropdownRow$lambda$211, (Function0) value$iv5, ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(Modifier.Companion, AuroraColorSchemeKt.getAuroraSurfaceRaised($composer3, 0), null, 2, null), 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(693504979, true, new AuroraDsKt$AuroraDropdownRow$1$2$2$3(opts, blankLabel2, value, allowCustom2, customLabel2, onSelect, expanded$delegate, customMode$delegate2), $composer3, 54), $composer3, 48, 48, 2040);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.startReplaceGroup(-1293491932);
                ComposerKt.sourceInformation($composer3, "587@21833L1115");
                if (AuroraDropdownRow$lambda$28(customMode$delegate2)) {
                    $composer2 = $composer3;
                } else {
                    $composer2 = $composer3;
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                localMap$iv$iv5 = localMap$iv$iv4;
                measurePolicy$iv5 = measurePolicy$iv4;
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash110);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv110, ComposeUiNode.Companion.getSetModifier());
            int i111118 = ($changed$iv$iv$iv110 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance10 = ColumnScopeInstance.INSTANCE;
            int i111119 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1654829855, "C529@19154L10,529@19134L83:AuroraDs.kt#rimizs");
            $dirty = $dirty3;
            customLabel2 = customLabel3;
            blankLabel2 = blankLabel4;
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(title, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer3, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty3 & 14) | 199680, 0, 131026);
            $composer3.startReplaceGroup(-85162624);
            ComposerKt.sourceInformation($composer3, "531@19310L15,531@19287L105");
            str = subtitle2;
            if (str != null) {
                z4 = true;
            } else {
                z4 = true;
            }
            if (z4) {
                i2 = 0;
                $dirty2 = $dirty;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(subtitle2, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 12) & 14) | 3072, 3120, 120818);
            } else {
                $dirty2 = $dirty;
                i2 = 0;
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            int $changed$iv8 = i2;
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Modifier modifier$iv113 = Modifier.Companion;
            Alignment contentAlignment$iv8 = Alignment.Companion.getTopStart();
            MeasurePolicy measurePolicy$iv112 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv8, false);
            int $changed$iv$iv113 = ($changed$iv8 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, i2);
            CompositionLocalMap localMap$iv$iv112 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv113 = ComposedModifierKt.materializeModifier($composer3, modifier$iv113);
            constructor3 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv113 = (($changed$iv$iv113 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function2 = constructor3;
                $composer3.createNode(function2);
            } else {
                function2 = constructor3;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv3 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv112, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv112, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash113 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash113);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv113, ComposeUiNode.Companion.getSetModifier());
            int i1111110 = ($changed$iv$iv$iv113 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
            int i1111111 = (($changed$iv8 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1655214875, "C538@19597L13,539@19650L14,535@19459L954,556@20524L20,557@20597L19,558@20637L1118,554@20430L1325:AuroraDs.kt#rimizs");
            Modifier modifier$iv114 = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m600borderxT4_qwU(ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(ClipKt.clip(Modifier.Companion, ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), AuroraColorSchemeKt.getAuroraSurface($composer3, 0), null, 2, null), ai.nous.aurora.compat.Compat.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer3, 0), ai.nous.aurora.compat.Compat.m1317RoundedCornerShape0680j_4(ai.nous.aurora.compat.Compat.m7016constructorimpl(10))), ai.nous.aurora.compat.Compat.m7016constructorimpl(10), ai.nous.aurora.compat.Compat.m7016constructorimpl(8));
            Alignment.Vertical verticalAlignment$iv11 = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv11 = Arrangement.INSTANCE.getStart();
            int $i$f$Row8 = ((384 >> 3) & 14) | ((384 >> 3) & 112);
            MeasurePolicy measurePolicy$iv113 = RowKt.rowMeasurePolicy(horizontalArrangement$iv11, verticalAlignment$iv11, $composer3, $i$f$Row8);
            int $changed$iv$iv114 = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv113 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv114 = ComposedModifierKt.materializeModifier($composer3, modifier$iv114);
            constructor4 = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv114 = (($changed$iv$iv114 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function3 = constructor4;
                $composer3.createNode(function3);
            } else {
                function3 = constructor4;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv4 = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv113, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv113, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash114 = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash114);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv114, ComposeUiNode.Companion.getSetModifier());
            int i1111112 = ($changed$iv$iv$iv114 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance8 = RowScopeInstance.INSTANCE;
            int i1111113 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 744379049, "C543@19872L407,552@20346L15,552@20300L95:AuroraDs.kt#rimizs");
            if (StringsKt.isBlank(value)) {
                $composer3.startReplaceGroup(-1361458658);
                ComposerKt.sourceInformation($composer3, "545@19964L15");
                auroraText = AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0);
            } else {
                $composer3.startReplaceGroup(-1361457991);
                ComposerKt.sourceInformation($composer3, "545@19985L10");
                auroraText = AuroraColorSchemeKt.getAuroraText($composer3, 0);
            }
            $composer3.endReplaceGroup();
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(display, ai.nous.aurora.compat.Compat.m1086widthInVpY3zN4$default(Modifier.Companion, 0.0f, ai.nous.aurora.compat.Compat.m7016constructorimpl(160), 1, null), auroraText, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 199728, 3120, 120784);
            ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(ArrowDropDownKt.getArrowDropDown(Icons.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(20)), AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), $composer3, 432, 0);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            boolean zAuroraDropdownRow$lambda$212 = AuroraDropdownRow$lambda$25(expanded$delegate2);
            $composer3.startReplaceGroup(-85121500);
            ComposerKt.sourceInformation($composer3, "CC(remember):AuroraDs.kt#9igjgp");
            it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.Companion.getEmpty()) {
                expanded$delegate = expanded$delegate2;
                final MutableState expanded$delegate$c5060 = expanded$delegate;
                value$iv5 = new Function0() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AuroraDsKt.AuroraDropdownRow$lambda$50$lambda$44$lambda$43$lambda$42$lambda$41(expanded$delegate$c5060);
                    }
                };
                $composer3.updateRememberedValue(value$iv5);
            } else {
                expanded$delegate = expanded$delegate2;
                value$iv5 = it$iv2;
            }
            $composer3.endReplaceGroup();
            ai.nous.aurora.compat.Compat.m2138DropdownMenuIlH_yew(zAuroraDropdownRow$lambda$212, (Function0) value$iv5, ai.nous.aurora.compat.Compat.m589backgroundbw27NRU$default(Modifier.Companion, AuroraColorSchemeKt.getAuroraSurfaceRaised($composer3, 0), null, 2, null), 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(693504979, true, new AuroraDsKt$AuroraDropdownRow$1$2$2$3(opts, blankLabel2, value, allowCustom2, customLabel2, onSelect, expanded$delegate, customMode$delegate2), $composer3, 54), $composer3, 48, 48, 2040);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.startReplaceGroup(-1293491932);
            ComposerKt.sourceInformation($composer3, "587@21833L1115");
            if (AuroraDropdownRow$lambda$28(customMode$delegate2)) {
                $composer2 = $composer3;
            } else {
                $composer2 = $composer3;
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
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
            final String str3 = subtitle2;
            final boolean z6 = enabled2;
            final boolean z7 = allowCustom2;
            final String str4 = customLabel2;
            final String str5 = blankLabel2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraDsKt.AuroraDropdownRow$lambda$51(title, value, options, onSelect, str3, z6, z7, str4, str5, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final boolean AuroraDropdownRow$lambda$25(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AuroraDropdownRow$lambda$26(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean AuroraDropdownRow$lambda$28(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AuroraDropdownRow$lambda$29(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String AuroraDropdownRow$lambda$31(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraDropdownRow$lambda$50$lambda$38$lambda$37(MutableState $expanded$delegate) {
        AuroraDropdownRow$lambda$26($expanded$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraDropdownRow$lambda$50$lambda$44$lambda$43$lambda$42$lambda$41(MutableState $expanded$delegate) {
        AuroraDropdownRow$lambda$26($expanded$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraDropdownRow$lambda$50$lambda$49$lambda$46$lambda$45(MutableState $customDraft$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $customDraft$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AuroraDropdownRow$lambda$50$lambda$49$lambda$48$lambda$47(Function1 $onSelect, MutableState $customDraft$delegate, MutableState $customMode$delegate) {
        $onSelect.invoke(StringsKt.trim((CharSequence) AuroraDropdownRow$lambda$31($customDraft$delegate)).toString());
        AuroraDropdownRow$lambda$29($customMode$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:86:0x0280  */
    /* JADX WARN: Code duplicated, block: B:87:0x0286  */
    /* JADX WARN: Code duplicated, block: B:90:0x032f  */
    /* JADX INFO: renamed from: AuroraCompactSettingRow-Sj8uqqQ, reason: not valid java name */
    public static final void m127AuroraCompactSettingRowSj8uqqQ(final String label, final String value, final Function0<Unit> onClick, Color valueColor, int valueMaxLines, Composer $composer, final int $changed, final int i) {
        Color color;
        int i2;
        Color valueColor2;
        int valueMaxLines2;
        CompositionLocalMap localMap$iv$iv;
        Composer $composer2;
        long jM4544unboximpl;
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer3 = $composer.startRestartGroup(884817818);
        ComposerKt.sourceInformation($composer3, "C(AuroraCompactSettingRow)P(!1,2!1,3:c#ui.graphics.Color)626@23139L817:AuroraDs.kt#rimizs");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(label) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(value) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(onClick) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            color = valueColor;
        } else if (($changed & 3072) == 0) {
            color = valueColor;
            $dirty |= $composer3.changed(color) ? 2048 : 1024;
        } else {
            color = valueColor;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            i2 = valueMaxLines;
        } else if (($changed & 24576) == 0) {
            i2 = valueMaxLines;
            $dirty |= $composer3.changed(i2) ? 16384 : 8192;
        } else {
            i2 = valueMaxLines;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            valueColor2 = color;
            valueMaxLines2 = i2;
            $composer2 = $composer3;
        } else {
            valueColor2 = i3 != 0 ? null : color;
            valueMaxLines2 = i4 != 0 ? 1 : i2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(884817818, $dirty2, -1, "ai.nous.aurora.presentation.components.ds.AuroraCompactSettingRow (AuroraDs.kt:625)");
            }
            Modifier modifier$iv = ai.nous.aurora.compat.Compat.m1035paddingVpY3zN4(ai.nous.aurora.compat.Compat.m622clickableXHw0xAI$default(ai.nous.aurora.compat.Compat.d_SizeKt_fillMaxWidth_4(Modifier.Companion, 0.0f, 1, null), false, null, null, onClick, 7, null), AuroraSpacing.INSTANCE.m239getScreenD9Ej5fM(), ai.nous.aurora.compat.Compat.m7016constructorimpl(11));
            Alignment.Vertical verticalAlignment$iv = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = ai.nous.aurora.compat.Compat.m4027constructorimpl($composer3);
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                localMap$iv$iv = localMap$iv$iv2;
            } else {
                localMap$iv$iv = localMap$iv$iv2;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
                int i5 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i6 = ((384 >> 6) & 112) | 6;
                RowScope $this$AuroraCompactSettingRow_Sj8uqqQ_u24lambda_u2452 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 2127642524, "C633@23409L15,633@23389L89,634@23487L282,646@23879L15,643@23778L172:AuroraDs.kt#rimizs");
                $composer2 = $composer3;
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(label, ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(ComposerChipPolicy.CHIP_MAX_WIDTH_DP)), AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty2 & 14) | 3120, 0, 131056);
                $composer3.startReplaceGroup(1454110990);
                ComposerKt.sourceInformation($composer3, "636@23546L10");
                if (valueColor2 == null) {
                    jM4544unboximpl = AuroraColorSchemeKt.getAuroraText($composer3, 0);
                } else {
                    jM4544unboximpl = ai.nous.aurora.compat.Compat.m4544unboximpl(valueColor);
                }
                $composer3.endReplaceGroup();
                ai.nous.aurora.compat.Compat.m3067Text4IGK_g(value, ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraCompactSettingRow_Sj8uqqQ_u24lambda_u2452, Modifier.Companion, 1.0f, false, 2, null), jM4544unboximpl, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, valueMaxLines2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 3) & 14) | 199680, (($dirty2 >> 3) & 7168) | 48, 120784);
                ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(KeyboardArrowRightKt.getKeyboardArrowRight(Icons.AutoMirrored.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(18)), AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), $composer3, 432, 0);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
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
            ai.nous.aurora.compat.Compat.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.Companion.getSetModifier());
            int i7 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int i8 = ((384 >> 6) & 112) | 6;
            RowScope $this$AuroraCompactSettingRow_Sj8uqqQ_u24lambda_u2453 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 2127642524, "C633@23409L15,633@23389L89,634@23487L282,646@23879L15,643@23778L172:AuroraDs.kt#rimizs");
            $composer2 = $composer3;
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(label, ai.nous.aurora.compat.Compat.m1084width3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(ComposerChipPolicy.CHIP_MAX_WIDTH_DP)), AuroraColorSchemeKt.getAuroraTextMuted($composer3, 0), TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, ($dirty2 & 14) | 3120, 0, 131056);
            $composer3.startReplaceGroup(1454110990);
            ComposerKt.sourceInformation($composer3, "636@23546L10");
            if (valueColor2 == null) {
                jM4544unboximpl = AuroraColorSchemeKt.getAuroraText($composer3, 0);
            } else {
                jM4544unboximpl = ai.nous.aurora.compat.Compat.m4544unboximpl(valueColor);
            }
            $composer3.endReplaceGroup();
            ai.nous.aurora.compat.Compat.m3067Text4IGK_g(value, ai.nous.aurora.compat.Compat.d_RowScope_weight_6($this$AuroraCompactSettingRow_Sj8uqqQ_u24lambda_u2453, Modifier.Companion, 1.0f, false, 2, null), jM4544unboximpl, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, ai.nous.aurora.compat.Compat.m6933getEllipsisgIe3tQ8(TextOverflow.Companion), false, valueMaxLines2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, (($dirty2 >> 3) & 14) | 199680, (($dirty2 >> 3) & 7168) | 48, 120784);
            ai.nous.aurora.compat.Compat.m2524Iconww6aTOc(KeyboardArrowRightKt.getKeyboardArrowRight(Icons.AutoMirrored.Filled.INSTANCE), (String) null, ai.nous.aurora.compat.Compat.m1079size3ABfNKs(Modifier.Companion, ai.nous.aurora.compat.Compat.m7016constructorimpl(18)), AuroraColorSchemeKt.getAuroraTextFaint($composer3, 0), $composer3, 432, 0);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
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
            final Color color2 = valueColor2;
            final int i9 = valueMaxLines2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.components.ds.AuroraDsKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AuroraDsKt.AuroraCompactSettingRow_Sj8uqqQ$lambda$53(label, value, onClick, color2, i9, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
