package androidx.compose.material3;

import ai.nous.aurora.presentation.theme.AuroraMotion;
import androidx.autofill.HintConstants;
import androidx.collection.IntList;
import androidx.collection.IntListKt;
import androidx.collection.MutableIntList;
import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.internal.AccessibilityServiceStateProvider_androidKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.TimeInputTokens;
import androidx.compose.material3.tokens.TimePickerTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.CharsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: TimePicker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Â\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\u001a7\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00012\u0011\u0010'\u001a\r\u0012\u0004\u0012\u00020#0(¢\u0006\u0002\b)H\u0003ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001a\u001d\u0010,\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/H\u0003¢\u0006\u0002\u00100\u001a%\u00101\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/2\u0006\u00102\u001a\u000203H\u0001¢\u0006\u0002\u00104\u001a-\u00105\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020\u001f2\u0006\u00106\u001a\u00020\u00192\u0006\u00102\u001a\u000203H\u0003¢\u0006\u0002\u00107\u001a\u0015\u00108\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0003¢\u0006\u0002\u00109\u001a\u001d\u0010:\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/H\u0003¢\u0006\u0002\u00100\u001a%\u0010;\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/H\u0003¢\u0006\u0002\u0010<\u001a1\u0010=\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001f2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010.\u001a\u00020/2\u0006\u00102\u001a\u000203H\u0001¢\u0006\u0002\u0010>\u001a=\u0010?\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020CH\u0003¢\u0006\u0002\u0010E\u001a)\u0010F\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001a2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010.\u001a\u00020/H\u0007¢\u0006\u0002\u0010G\u001a%\u0010H\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010.\u001a\u00020/2\u0006\u0010-\u001a\u00020\u001aH\u0003¢\u0006\u0002\u0010I\u001a8\u0010J\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001a2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u0010K\u001a\u00020LH\u0007ø\u0001\u0000¢\u0006\u0004\bM\u0010N\u001a \u0010O\u001a\u00020\u001a2\u0006\u0010P\u001a\u00020\u00192\u0006\u0010Q\u001a\u00020\u00192\u0006\u0010R\u001a\u000203H\u0007\u001ab\u0010S\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u00106\u001a\u00020T2\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020#0V2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010W\u001a\u00020X2\b\b\u0002\u0010Y\u001a\u00020Z2\b\b\u0002\u0010[\u001a\u00020\\2\u0006\u0010.\u001a\u00020/H\u0003ø\u0001\u0000¢\u0006\u0004\b]\u0010^\u001a:\u0010_\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u00106\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010W\u001a\u00020X2\u0006\u0010.\u001a\u00020/H\u0003ø\u0001\u0000¢\u0006\u0004\b`\u0010a\u001aQ\u0010b\u001a\u00020#2\u0006\u0010c\u001a\u0002032\u0006\u0010d\u001a\u00020C2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020#0(2\u0006\u0010.\u001a\u00020/2\u001c\u0010'\u001a\u0018\u0012\u0004\u0012\u00020f\u0012\u0004\u0012\u00020#0V¢\u0006\u0002\b)¢\u0006\u0002\bgH\u0003¢\u0006\u0002\u0010h\u001a\u001d\u0010i\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/H\u0003¢\u0006\u0002\u00100\u001a%\u0010j\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/H\u0003¢\u0006\u0002\u0010<\u001a1\u0010k\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001f2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010.\u001a\u00020/2\u0006\u00102\u001a\u000203H\u0001¢\u0006\u0002\u0010>\u001a\u0018\u0010l\u001a\u00020\b2\u0006\u0010m\u001a\u00020\b2\u0006\u0010n\u001a\u00020\bH\u0002\u001a(\u0010o\u001a\u00020\b2\u0006\u0010p\u001a\u00020\b2\u0006\u0010q\u001a\u00020\b2\u0006\u0010r\u001a\u00020\u00192\u0006\u0010s\u001a\u00020\u0019H\u0002\u001a*\u0010t\u001a\u00020u2\u0006\u0010W\u001a\u00020X2\u0006\u0010R\u001a\u0002032\u0006\u0010v\u001a\u00020\u0019H\u0001ø\u0001\u0000¢\u0006\u0004\bw\u0010x\u001a+\u0010y\u001a\u00020\u001a2\b\b\u0002\u0010P\u001a\u00020\u00192\b\b\u0002\u0010Q\u001a\u00020\u00192\b\b\u0002\u0010R\u001a\u000203H\u0007¢\u0006\u0002\u0010z\u001a`\u0010{\u001a\u00020#2\u0006\u0010W\u001a\u00020X2\u0006\u0010-\u001a\u00020\u001a2\u0006\u00106\u001a\u00020T2\u0006\u0010|\u001a\u00020T2\u0006\u0010}\u001a\u00020\u00192\"\u0010~\u001a\u001e\u0012\u0014\u0012\u00120T¢\u0006\r\b\u007f\u0012\t\b\u0080\u0001\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020#0VH\u0002ø\u0001\u0000¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001\u001a\u001d\u0010\u0083\u0001\u001a\u00020%*\u00020%2\u0006\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/H\u0002\u001a<\u0010\u0084\u0001\u001a\u00020#*\u00020\u001a2\u0006\u0010n\u001a\u00020\b2\u0006\u0010m\u001a\u00020\b2\u0007\u0010\u0085\u0001\u001a\u00020\b2\b\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0002ø\u0001\u0000¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001\u001aE\u0010\u008a\u0001\u001a\u00020#*\u00020\u001f2\u0006\u0010n\u001a\u00020\b2\u0006\u0010m\u001a\u00020\b2\u0007\u0010\u0085\u0001\u001a\u00020\b2\u0006\u00102\u001a\u0002032\b\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0082@ø\u0001\u0000¢\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001\u001a\u0016\u0010\u008d\u0001\u001a\u00020%*\u00020%2\u0007\u0010\u008d\u0001\u001a\u000203H\u0003\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u000b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\f\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\r\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u000f\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0010\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0016\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0017\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0018\u0010\u0018\u001a\u00020\u0019*\u00020\u001a8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0018\u0010\u001d\u001a\u00020\u001e*\u00020\u001f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b \u0010!\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u008e\u0001²\u0006\u000b\u0010\u008f\u0001\u001a\u000203X\u008a\u0084\u0002²\u0006\u000b\u0010\u0090\u0001\u001a\u00020TX\u008a\u008e\u0002²\u0006\u000b\u0010\u0091\u0001\u001a\u00020TX\u008a\u008e\u0002²\u0006\f\u0010\u0086\u0001\u001a\u00030\u0092\u0001X\u008a\u008e\u0002²\u0006\f\u0010\u0093\u0001\u001a\u00030\u0087\u0001X\u008a\u008e\u0002"}, d2 = {"ClockDisplayBottomMargin", "Landroidx/compose/ui/unit/Dp;", "F", "ClockFaceBottomMargin", "DisplaySeparatorWidth", "ExtraHours", "Landroidx/collection/IntList;", "FullCircle", "", "HalfCircle", "Hours", "InnerCircleRadius", "MaxDistance", "MinimumInteractiveSize", "Minutes", "OuterCircleSizeRadius", "PeriodToggleMargin", "QuarterCircle", "", "RadiansPerHour", "RadiansPerMinute", "SeparatorZIndex", "SupportLabelTop", "TimeInputBottomPadding", "hourForDisplay", "", "Landroidx/compose/material3/TimePickerState;", "getHourForDisplay", "(Landroidx/compose/material3/TimePickerState;)I", "selectorPos", "Landroidx/compose/ui/unit/DpOffset;", "Landroidx/compose/material3/AnalogTimePickerState;", "getSelectorPos", "(Landroidx/compose/material3/AnalogTimePickerState;)J", "CircularLayout", "", "modifier", "Landroidx/compose/ui/Modifier;", "radius", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "CircularLayout-uFdPcIQ", "(Landroidx/compose/ui/Modifier;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ClockDisplayNumbers", "state", "colors", "Landroidx/compose/material3/TimePickerColors;", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "ClockFace", "autoSwitchToMinute", "", "(Landroidx/compose/material3/AnalogTimePickerState;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;I)V", "ClockText", "value", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/AnalogTimePickerState;IZLandroidx/compose/runtime/Composer;I)V", "DisplaySeparator", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "HorizontalClockDisplay", "HorizontalPeriodToggle", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "HorizontalTimePicker", "(Landroidx/compose/material3/AnalogTimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;II)V", "PeriodToggleImpl", "measurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "startShape", "Landroidx/compose/ui/graphics/Shape;", "endShape", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/ui/layout/MeasurePolicy;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "TimeInput", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "TimeInputImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/material3/TimePickerState;Landroidx/compose/runtime/Composer;I)V", "TimePicker", "layoutType", "Landroidx/compose/material3/TimePickerLayoutType;", "TimePicker-mT9BvqQ", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ILandroidx/compose/runtime/Composer;II)V", "TimePickerState", "initialHour", "initialMinute", "is24Hour", "TimePickerTextField", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "selection", "Landroidx/compose/material3/TimePickerSelectionMode;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "TimePickerTextField-1vLObsk", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/TimePickerState;ILandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "TimeSelector", "TimeSelector-SAnMeKU", "(Landroidx/compose/ui/Modifier;ILandroidx/compose/material3/TimePickerState;ILandroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "ToggleItem", "checked", "shape", "onClick", "Landroidx/compose/foundation/layout/RowScope;", "Lkotlin/ExtensionFunctionType;", "(ZLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/TimePickerColors;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "VerticalClockDisplay", "VerticalPeriodToggle", "VerticalTimePicker", "atan", "y", "x", "dist", "x1", "y1", "x2", "y2", "numberContentDescription", "", "number", "numberContentDescription-dSwYdS4", "(IZILandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "rememberTimePickerState", "(IIZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TimePickerState;", "timeInputOnChange", "prevValue", "max", "onNewValue", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "timeInputOnChange-z7XvuPQ", "(ILandroidx/compose/material3/TimePickerState;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/TextFieldValue;ILkotlin/jvm/functions/Function1;)V", "drawSelector", "moveSelector", "maxDist", "center", "Landroidx/compose/ui/unit/IntOffset;", "moveSelector-d3b8Pxo", "(Landroidx/compose/material3/TimePickerState;FFFJ)V", "onTap", "onTap-rOwcSBo", "(Landroidx/compose/material3/AnalogTimePickerState;FFFZJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "visible", "material3_release", "a11yServicesEnabled", "hourValue", "minuteValue", "Landroidx/compose/ui/geometry/Offset;", "parentCenter"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TimePickerKt {
    private static final IntList ExtraHours;
    private static final float FullCircle = 6.2831855f;
    private static final float HalfCircle = 3.1415927f;
    private static final float PeriodToggleMargin;
    private static final double QuarterCircle = 1.5707963267948966d;
    private static final float RadiansPerHour = 0.5235988f;
    private static final float RadiansPerMinute = 0.10471976f;
    private static final float SeparatorZIndex = 2.0f;
    private static final float OuterCircleSizeRadius = Dp.m7016constructorimpl(101);
    private static final float InnerCircleRadius = Dp.m7016constructorimpl(69);
    private static final float ClockDisplayBottomMargin = Dp.m7016constructorimpl(36);
    private static final float ClockFaceBottomMargin = Dp.m7016constructorimpl(24);
    private static final float DisplaySeparatorWidth = Dp.m7016constructorimpl(24);
    private static final float SupportLabelTop = Dp.m7016constructorimpl(7);
    private static final float TimeInputBottomPadding = Dp.m7016constructorimpl(24);
    private static final float MaxDistance = Dp.m7016constructorimpl(74);
    private static final float MinimumInteractiveSize = Dp.m7016constructorimpl(48);
    private static final IntList Minutes = IntListKt.intListOf(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55);
    private static final IntList Hours = IntListKt.intListOf(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

    /* JADX INFO: renamed from: TimePicker-mT9BvqQ, reason: not valid java name */
    public static final void m3094TimePickermT9BvqQ(final TimePickerState state, Modifier modifier, TimePickerColors colors, int layoutType, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TimePickerColors timePickerColors;
        int i2;
        Modifier.Companion modifier3;
        TimePickerColors colors2;
        int layoutType2;
        Object value$iv;
        Modifier modifier4;
        TimePickerColors colors3;
        int layoutType3;
        Composer $composer2 = $composer.startRestartGroup(-619286452);
        ComposerKt.sourceInformation($composer2, "C(TimePicker)P(3,2!,1:c#material3.TimePickerLayoutType)218@11441L8,219@11509L12,221@11558L35,222@11616L48:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                timePickerColors = colors;
                int i4 = $composer2.changed(timePickerColors) ? 256 : 128;
                $dirty |= i4;
            } else {
                timePickerColors = colors;
            }
            $dirty |= i4;
        } else {
            timePickerColors = colors;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                i2 = layoutType;
                int i5 = $composer2.changed(i2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                i2 = layoutType;
            }
            $dirty |= i5;
        } else {
            i2 = layoutType;
        }
        if (($dirty & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            colors3 = timePickerColors;
            layoutType3 = i2;
            modifier4 = modifier2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    colors2 = TimePickerDefaults.INSTANCE.colors($composer2, 6);
                    $dirty &= -897;
                } else {
                    colors2 = timePickerColors;
                }
                if ((i & 8) != 0) {
                    layoutType2 = TimePickerDefaults.INSTANCE.m3092layoutTypesDNSZnc($composer2, 6);
                    $dirty &= -7169;
                } else {
                    layoutType2 = i2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                modifier3 = modifier2;
                colors2 = timePickerColors;
                layoutType2 = i2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-619286452, $dirty, -1, "androidx.compose.material3.TimePicker (TimePicker.kt:220)");
            }
            boolean invalid$iv = false;
            State<Boolean> stateRememberAccessibilityServiceState = AccessibilityServiceStateProvider_androidKt.rememberAccessibilityServiceState(false, false, $composer2, 0, 3);
            ComposerKt.sourceInformationMarkerStart($composer2, -2089091954, "CC(remember):TimePicker.kt#9igjgp");
            if (($dirty & 14) == 4 || (($dirty & 8) != 0 && $composer2.changed(state))) {
                invalid$iv = true;
            }
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new AnalogTimePickerState(state);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            AnalogTimePickerState analogState = (AnalogTimePickerState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (TimePickerLayoutType.m3112equalsimpl0(layoutType2, TimePickerLayoutType.INSTANCE.m3117getVerticalQJTpgSE())) {
                $composer2.startReplaceGroup(-337235422);
                ComposerKt.sourceInformation($composer2, "224@11728L178");
                VerticalTimePicker(analogState, modifier3, colors2, !TimePicker_mT9BvqQ$lambda$0(stateRememberAccessibilityServiceState), $composer2, ($dirty & 112) | ($dirty & 896), 0);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-337036960);
                ComposerKt.sourceInformation($composer2, "231@11928L180");
                HorizontalTimePicker(analogState, modifier3, colors2, !TimePicker_mT9BvqQ$lambda$0(stateRememberAccessibilityServiceState), $composer2, ($dirty & 112) | ($dirty & 896), 0);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            colors3 = colors2;
            layoutType3 = layoutType2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier4;
            final TimePickerColors timePickerColors2 = colors3;
            final int i6 = layoutType3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePicker$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i7) {
                    TimePickerKt.m3094TimePickermT9BvqQ(state, modifier5, timePickerColors2, i6, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    private static final boolean TimePicker_mT9BvqQ$lambda$0(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    public static final void TimeInput(final TimePickerState state, Modifier modifier, TimePickerColors colors, Composer $composer, final int $changed, final int i) {
        Composer $composer2 = $composer.startRestartGroup(-760850373);
        ComposerKt.sourceInformation($composer2, "C(TimeInput)P(2,1)259@12999L8,261@13017L38:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= ((i & 4) == 0 && $composer2.changed(colors)) ? 256 : 128;
        }
        if (($dirty & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if (i2 != 0) {
                    modifier = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    colors = TimePickerDefaults.INSTANCE.colors($composer2, 6);
                    $dirty &= -897;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-760850373, $dirty, -1, "androidx.compose.material3.TimeInput (TimePicker.kt:260)");
            }
            TimeInputImpl(modifier, colors, state, $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty << 6) & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier2 = modifier;
            final TimePickerColors timePickerColors = colors;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.TimeInput.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    TimePickerKt.TimeInput(state, modifier2, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final TimePickerState rememberTimePickerState(int initialHour, int initialMinute, boolean is24Hour, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1237715277, "C(rememberTimePickerState)572@28878L14,575@29014L185,575@28960L239:TimePicker.kt#uh7d8r");
        final int initialHour2 = (i & 1) != 0 ? 0 : initialHour;
        final int initialMinute2 = (i & 2) != 0 ? 0 : initialMinute;
        final boolean is24Hour2 = (i & 4) != 0 ? TimeFormat_androidKt.is24HourFormat($composer, 0) : is24Hour;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1237715277, $changed, -1, "androidx.compose.material3.rememberTimePickerState (TimePicker.kt:573)");
        }
        Object[] objArr = new Object[0];
        Saver<TimePickerStateImpl, ?> Saver = TimePickerStateImpl.INSTANCE.Saver();
        ComposerKt.sourceInformationMarkerStart($composer, -1964549601, "CC(remember):TimePicker.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialHour2)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(initialMinute2)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(is24Hour2)) || ($changed & 384) == 256);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new Function0<TimePickerStateImpl>() { // from class: androidx.compose.material3.TimePickerKt$rememberTimePickerState$state$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final TimePickerStateImpl invoke() {
                    return new TimePickerStateImpl(initialHour2, initialMinute2, is24Hour2);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        TimePickerStateImpl state = (TimePickerStateImpl) RememberSaveableKt.m4120rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return state;
    }

    public static final TimePickerState TimePickerState(int initialHour, int initialMinute, boolean is24Hour) {
        return new TimePickerStateImpl(initialHour, initialMinute, is24Hour);
    }

    public static final int getHourForDisplay(TimePickerState $this$hourForDisplay) {
        if ($this$hourForDisplay.getIs24hour()) {
            return $this$hourForDisplay.getHour() % 24;
        }
        if ($this$hourForDisplay.getHour() % 12 == 0) {
            return 12;
        }
        return $this$hourForDisplay.isAfternoon() ? $this$hourForDisplay.getHour() - 12 : $this$hourForDisplay.getHour();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: moveSelector-d3b8Pxo, reason: not valid java name */
    public static final void m3103moveSelectord3b8Pxo(TimePickerState $this$moveSelector_u2dd3b8Pxo, float x, float y, float maxDist, long center) {
        if (!TimePickerSelectionMode.m3121equalsimpl0($this$moveSelector_u2dd3b8Pxo.mo2133getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI()) || !$this$moveSelector_u2dd3b8Pxo.getIs24hour()) {
            return;
        }
        $this$moveSelector_u2dd3b8Pxo.setAfternoon(dist(x, y, IntOffset.m7144getXimpl(center), IntOffset.m7145getYimpl(center)) < maxDist);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:27:0x00c0 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:30:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: onTap-rOwcSBo, reason: not valid java name */
    public static final Object m3105onTaprOwcSBo(AnalogTimePickerState $this$onTap_u2drOwcSBo, float x, float y, float maxDist, boolean autoSwitchToMinute, long center, Continuation<? super Unit> continuation) {
        TimePickerKt$onTap$1 timePickerKt$onTap$1;
        float angle;
        AnalogTimePickerState $this$onTap_u2drOwcSBo2;
        boolean autoSwitchToMinute2;
        if (continuation instanceof TimePickerKt$onTap$1) {
            timePickerKt$onTap$1 = (TimePickerKt$onTap$1) continuation;
            if ((timePickerKt$onTap$1.label & Integer.MIN_VALUE) != 0) {
                timePickerKt$onTap$1.label -= Integer.MIN_VALUE;
            } else {
                timePickerKt$onTap$1 = new TimePickerKt$onTap$1(continuation);
            }
        } else {
            timePickerKt$onTap$1 = new TimePickerKt$onTap$1(continuation);
        }
        TimePickerKt$onTap$1 timePickerKt$onTap$2 = timePickerKt$onTap$1;
        Object $result = timePickerKt$onTap$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (timePickerKt$onTap$2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                float angle2 = atan(y - IntOffset.m7145getYimpl(center), x - IntOffset.m7144getXimpl(center));
                if (TimePickerSelectionMode.m3121equalsimpl0($this$onTap_u2drOwcSBo.mo2133getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI())) {
                    angle = ((float) Math.rint((angle2 / RadiansPerMinute) / 5.0f)) * 5.0f * RadiansPerMinute;
                } else {
                    angle = ((float) Math.rint(angle2 / RadiansPerHour)) * RadiansPerHour;
                }
                m3103moveSelectord3b8Pxo($this$onTap_u2drOwcSBo, x, y, maxDist, center);
                timePickerKt$onTap$2.L$0 = $this$onTap_u2drOwcSBo;
                timePickerKt$onTap$2.Z$0 = autoSwitchToMinute;
                timePickerKt$onTap$2.label = 1;
                if ($this$onTap_u2drOwcSBo.rotateTo(angle, true, timePickerKt$onTap$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $this$onTap_u2drOwcSBo2 = $this$onTap_u2drOwcSBo;
                autoSwitchToMinute2 = autoSwitchToMinute;
                if (TimePickerSelectionMode.m3121equalsimpl0($this$onTap_u2drOwcSBo2.mo2133getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI()) && autoSwitchToMinute2) {
                    timePickerKt$onTap$2.L$0 = $this$onTap_u2drOwcSBo2;
                    timePickerKt$onTap$2.Z$0 = autoSwitchToMinute2;
                    timePickerKt$onTap$2.label = 2;
                    if (DelayKt.delay(100L, timePickerKt$onTap$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                if (autoSwitchToMinute2) {
                    $this$onTap_u2drOwcSBo2.mo2134setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI());
                }
                return Unit.INSTANCE;
            case 1:
                autoSwitchToMinute2 = timePickerKt$onTap$2.Z$0;
                $this$onTap_u2drOwcSBo2 = (AnalogTimePickerState) timePickerKt$onTap$2.L$0;
                ResultKt.throwOnFailure($result);
                if (TimePickerSelectionMode.m3121equalsimpl0($this$onTap_u2drOwcSBo2.mo2133getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                    timePickerKt$onTap$2.L$0 = $this$onTap_u2drOwcSBo2;
                    timePickerKt$onTap$2.Z$0 = autoSwitchToMinute2;
                    timePickerKt$onTap$2.label = 2;
                    if (DelayKt.delay(100L, timePickerKt$onTap$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                if (autoSwitchToMinute2) {
                    $this$onTap_u2drOwcSBo2.mo2134setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI());
                }
                return Unit.INSTANCE;
            case 2:
                autoSwitchToMinute2 = timePickerKt$onTap$2.Z$0;
                $this$onTap_u2drOwcSBo2 = (AnalogTimePickerState) timePickerKt$onTap$2.L$0;
                ResultKt.throwOnFailure($result);
                if (autoSwitchToMinute2) {
                    $this$onTap_u2drOwcSBo2.mo2134setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI());
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final long getSelectorPos(AnalogTimePickerState $this$selectorPos) {
        float arg0$iv = Dp.m7016constructorimpl(TimePickerTokens.INSTANCE.m3915getClockDialSelectorHandleContainerSizeD9Ej5fM() / 2);
        float length = Dp.m7016constructorimpl(Dp.m7016constructorimpl((($this$selectorPos.getIs24hour() && $this$selectorPos.isAfternoon() && TimePickerSelectionMode.m3121equalsimpl0($this$selectorPos.mo2133getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) ? InnerCircleRadius : OuterCircleSizeRadius) - arg0$iv) + arg0$iv);
        float other$iv = (float) Math.cos($this$selectorPos.getCurrentAngle());
        float arg0$iv2 = Dp.m7016constructorimpl(Dp.m7016constructorimpl(length * other$iv) + Dp.m7016constructorimpl(TimePickerTokens.INSTANCE.m3913getClockDialContainerSizeD9Ej5fM() / 2));
        float other$iv2 = (float) Math.sin($this$selectorPos.getCurrentAngle());
        return DpKt.m7037DpOffsetYgX7TsA(arg0$iv2, Dp.m7016constructorimpl(Dp.m7016constructorimpl(length * other$iv2) + Dp.m7016constructorimpl(TimePickerTokens.INSTANCE.m3913getClockDialContainerSizeD9Ej5fM() / 2)));
    }

    public static final void VerticalTimePicker(final AnalogTimePickerState state, Modifier modifier, TimePickerColors colors, final boolean autoSwitchToMinute, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TimePickerColors colors2;
        Modifier.Companion modifier3;
        Function0<ComposeUiNode> function0;
        Modifier modifier4;
        TimePickerColors colors3;
        Composer $composer2 = $composer.startRestartGroup(1249591487);
        ComposerKt.sourceInformation($composer2, "C(VerticalTimePicker)P(3,2,1)927@40188L8,930@40238L379:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(state) ? 4 : 2;
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
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                colors2 = colors;
                int i3 = $composer2.changed(colors2) ? 256 : 128;
                $dirty |= i3;
            } else {
                colors2 = colors;
            }
            $dirty |= i3;
        } else {
            colors2 = colors;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(autoSwitchToMinute) ? 2048 : 1024;
        }
        if (($dirty & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            colors3 = colors2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    colors2 = TimePickerDefaults.INSTANCE.colors($composer2, 6);
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1249591487, $dirty, -1, "androidx.compose.material3.VerticalTimePicker (TimePicker.kt:929)");
            }
            Modifier modifier$iv = SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt.VerticalTimePicker.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.setTraversalGroup($this$semantics, true);
                }
            }, 1, null);
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier modifier5 = modifier3;
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1628603633, "C934@40388L35,935@40432L60,936@40501L44,937@40554L57:TimePicker.kt#uh7d8r");
            VerticalClockDisplay(state, colors2, $composer2, ($dirty & 14) | (($dirty >> 3) & 112));
            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, ClockDisplayBottomMargin), $composer2, 6);
            ClockFace(state, colors2, autoSwitchToMinute, $composer2, ($dirty & 14) | (($dirty >> 3) & 112) | (($dirty >> 3) & 896));
            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, ClockFaceBottomMargin), $composer2, 6);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier5;
            colors3 = colors2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier4;
            final TimePickerColors timePickerColors = colors3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.VerticalTimePicker.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i6) {
                    TimePickerKt.VerticalTimePicker(state, modifier6, timePickerColors, autoSwitchToMinute, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void HorizontalTimePicker(final AnalogTimePickerState state, Modifier modifier, TimePickerColors colors, final boolean autoSwitchToMinute, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TimePickerColors colors2;
        Modifier.Companion modifier3;
        Function0<ComposeUiNode> function0;
        Modifier modifier4;
        TimePickerColors colors3;
        Composer $composer2 = $composer.startRestartGroup(1432307537);
        ComposerKt.sourceInformation($composer2, "C(HorizontalTimePicker)P(3,2,1)945@40787L8,948@40837L309:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(state) ? 4 : 2;
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
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                colors2 = colors;
                int i3 = $composer2.changed(colors2) ? 256 : 128;
                $dirty |= i3;
            } else {
                colors2 = colors;
            }
            $dirty |= i3;
        } else {
            colors2 = colors;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(autoSwitchToMinute) ? 2048 : 1024;
        }
        if (($dirty & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            colors3 = colors2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    colors2 = TimePickerDefaults.INSTANCE.colors($composer2, 6);
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1432307537, $dirty, -1, "androidx.compose.material3.HorizontalTimePicker (TimePicker.kt:947)");
            }
            Modifier modifier$iv = PaddingKt.m1038paddingqDBjuR0$default(modifier3, 0.0f, 0.0f, 0.0f, ClockFaceBottomMargin, 7, null);
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            Modifier modifier5 = modifier3;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i5 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 157723300, "C952@40982L37,953@41028L59,954@41096L44:TimePicker.kt#uh7d8r");
            HorizontalClockDisplay(state, colors2, $composer2, ($dirty & 14) | (($dirty >> 3) & 112));
            SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, ClockDisplayBottomMargin), $composer2, 6);
            ClockFace(state, colors2, autoSwitchToMinute, $composer2, ($dirty & 14) | (($dirty >> 3) & 112) | (($dirty >> 3) & 896));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier5;
            colors3 = colors2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier4;
            final TimePickerColors timePickerColors = colors3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.HorizontalTimePicker.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i6) {
                    TimePickerKt.HorizontalTimePicker(state, modifier6, timePickerColors, autoSwitchToMinute, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TimeInputImpl(final Modifier modifier, final TimePickerColors colors, final TimePickerState state, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Composer $composer3 = $composer.startRestartGroup(-475657989);
        ComposerKt.sourceInformation($composer3, "C(TimeInputImpl)P(1)965@41356L112,965@41304L164,969@41552L104,969@41500L156,972@41661L4502:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(colors) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= ($changed & 512) == 0 ? $composer3.changed(state) : $composer3.changedInstance(state) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-475657989, $dirty2, -1, "androidx.compose.material3.TimeInputImpl (TimePicker.kt:963)");
            }
            Object[] objArr = new Object[0];
            Saver<TextFieldValue, Object> saver = TextFieldValue.INSTANCE.getSaver();
            ComposerKt.sourceInformationMarkerStart($composer3, 339122986, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = ($dirty2 & 896) == 256 || (($dirty2 & 512) != 0 && $composer3.changedInstance(state));
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function0) new Function0<MutableState<TextFieldValue>>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$hourValue$2$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final MutableState<TextFieldValue> invoke() {
                        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue(CalendarLocale_jvmKt.toLocalString$default(TimePickerKt.getHourForDisplay(state), 2, 0, false, 6, null), 0L, (TextRange) null, 6, (DefaultConstructorMarker) null), null, 2, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final MutableState hourValue$delegate = RememberSaveableKt.rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv, $composer3, 0, 4);
            Object[] objArr2 = new Object[0];
            Saver<TextFieldValue, Object> saver2 = TextFieldValue.INSTANCE.getSaver();
            ComposerKt.sourceInformationMarkerStart($composer3, 339129250, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv2 = ($dirty2 & 896) == 256 || (($dirty2 & 512) != 0 && $composer3.changedInstance(state));
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new Function0<MutableState<TextFieldValue>>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$minuteValue$2$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final MutableState<TextFieldValue> invoke() {
                        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue(CalendarLocale_jvmKt.toLocalString$default(state.getMinute(), 2, 0, false, 6, null), 0L, (TextRange) null, 6, (DefaultConstructorMarker) null), null, 2, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final MutableState minuteValue$delegate = RememberSaveableKt.rememberSaveable(objArr2, (Saver) saver2, (String) null, (Function0) value$iv2, $composer3, 0, 4);
            $composer2 = $composer3;
            Modifier modifier$iv = PaddingKt.m1038paddingqDBjuR0$default(modifier, 0.0f, 0.0f, 0.0f, TimeInputBottomPadding, 7, null);
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1328966913, "C977@41861L5,986@42225L3553,982@42004L3774:TimePicker.kt#uh7d8r");
            TextStyle value = TypographyKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldLabelTextFont(), $composer2, 6);
            TextStyle textStyle = value.m6505copyp1EtxEg((15695871 & 1) != 0 ? value.spanStyle.m6429getColor0d7_KjU() : colors.m3090timeSelectorContentColorvNxB06k$material3_release(true), (15695871 & 2) != 0 ? value.spanStyle.getFontSize() : 0L, (15695871 & 4) != 0 ? value.spanStyle.getFontWeight() : null, (15695871 & 8) != 0 ? value.spanStyle.getFontStyle() : null, (15695871 & 16) != 0 ? value.spanStyle.getFontSynthesis() : null, (15695871 & 32) != 0 ? value.spanStyle.getFontFamily() : null, (15695871 & 64) != 0 ? value.spanStyle.getFontFeatureSettings() : null, (15695871 & 128) != 0 ? value.spanStyle.getLetterSpacing() : 0L, (15695871 & 256) != 0 ? value.spanStyle.getBaselineShift() : null, (15695871 & 512) != 0 ? value.spanStyle.getTextGeometricTransform() : null, (15695871 & 1024) != 0 ? value.spanStyle.getLocaleList() : null, (15695871 & 2048) != 0 ? value.spanStyle.getBackground() : 0L, (15695871 & 4096) != 0 ? value.spanStyle.getTextDecoration() : null, (15695871 & 8192) != 0 ? value.spanStyle.getShadow() : null, (15695871 & 16384) != 0 ? value.spanStyle.getDrawStyle() : null, (15695871 & 32768) != 0 ? value.paragraphStyle.getTextAlign() : TextAlign.INSTANCE.m6883getCentere0LSkKk(), (15695871 & 65536) != 0 ? value.paragraphStyle.getTextDirection() : 0, (15695871 & 131072) != 0 ? value.paragraphStyle.getLineHeight() : 0L, (15695871 & 262144) != 0 ? value.paragraphStyle.getTextIndent() : null, (15695871 & 524288) != 0 ? value.platformStyle : null, (15695871 & 1048576) != 0 ? value.paragraphStyle.getLineHeightStyle() : null, (15695871 & 2097152) != 0 ? value.paragraphStyle.getLineBreak() : 0, (15695871 & 4194304) != 0 ? value.paragraphStyle.getHyphens() : 0, (15695871 & 8388608) != 0 ? value.paragraphStyle.getTextMotion() : null);
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{TextKt.getLocalTextStyle().provides(textStyle), CompositionLocalsKt.getLocalLayoutDirection().provides(LayoutDirection.Ltr)}, ComposableLambdaKt.rememberComposableLambda(1306700887, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    Object value$iv3;
                    Object value$iv4;
                    Object value$iv5;
                    Object value$iv6;
                    Object value$iv7;
                    ComposerKt.sourceInformation($composer4, "C987@42239L3529:TimePicker.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1306700887, $changed2, -1, "androidx.compose.material3.TimeInputImpl.<anonymous>.<anonymous> (TimePicker.kt:987)");
                        }
                        final MutableState<TextFieldValue> mutableState = hourValue$delegate;
                        final TimePickerState timePickerState = state;
                        TimePickerColors timePickerColors = colors;
                        final MutableState<TextFieldValue> mutableState2 = minuteValue$delegate;
                        ComposerKt.sourceInformationMarkerStart($composer4, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                        Modifier modifier$iv2 = Modifier.INSTANCE;
                        Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getStart();
                        Alignment.Vertical verticalAlignment$iv2 = Alignment.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv2 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer4, modifier$iv2);
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
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
                        Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer4);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                        int i4 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -227350393, "C990@42357L529,1004@42963L451,1024@43854L52,988@42261L1727,1028@44005L123,1033@44248L422,1045@44749L429,1065@45620L52,1031@44145L1609:TimePicker.kt#uh7d8r");
                        Modifier.Companion companion = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer4, -284428440, "CC(remember):TimePicker.kt#9igjgp");
                        boolean invalid$iv3 = $composer4.changed(mutableState) | $composer4.changedInstance(timePickerState);
                        Object value$iv8 = $composer4.rememberedValue();
                        if (invalid$iv3 || value$iv8 == Composer.INSTANCE.getEmpty()) {
                            value$iv8 = (Function1) new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$1$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                                    return m3107invokeZmokQxo(keyEvent.m5588unboximpl());
                                }

                                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                                public final Boolean m3107invokeZmokQxo(android.view.KeyEvent event) {
                                    int iM5601getUtf16CodePointZmokQxo = KeyEvent_androidKt.m5601getUtf16CodePointZmokQxo(event);
                                    boolean switchFocus = (48 <= iM5601getUtf16CodePointZmokQxo && iM5601getUtf16CodePointZmokQxo < 58) && TextRange.m6479getStartimpl(TimePickerKt.TimeInputImpl$lambda$6(mutableState).getSelection()) == 2 && TimePickerKt.TimeInputImpl$lambda$6(mutableState).getText().length() == 2;
                                    if (switchFocus) {
                                        timePickerState.mo2134setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI());
                                    }
                                    return false;
                                }
                            };
                            $composer4.updateRememberedValue(value$iv8);
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(companion, (Function1) value$iv8);
                        TextFieldValue textFieldValueTimeInputImpl$lambda$6 = TimePickerKt.TimeInputImpl$lambda$6(mutableState);
                        ComposerKt.sourceInformationMarkerStart($composer4, -284409126, "CC(remember):TimePicker.kt#9igjgp");
                        boolean invalid$iv4 = $composer4.changedInstance(timePickerState) | $composer4.changed(mutableState);
                        Object it$iv3 = $composer4.rememberedValue();
                        if (invalid$iv4 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv3 = (Function1) new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$1$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                    invoke2(textFieldValue);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextFieldValue newValue) {
                                    int iM3125getHouryecRtBI = TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI();
                                    TimePickerState timePickerState2 = timePickerState;
                                    TextFieldValue textFieldValueTimeInputImpl$lambda$7 = TimePickerKt.TimeInputImpl$lambda$6(mutableState);
                                    int i5 = timePickerState.getIs24hour() ? 23 : 12;
                                    final MutableState<TextFieldValue> mutableState3 = mutableState;
                                    TimePickerKt.m3106timeInputOnChangez7XvuPQ(iM3125getHouryecRtBI, timePickerState2, newValue, textFieldValueTimeInputImpl$lambda$7, i5, new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$1$2$1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                            invoke2(textFieldValue);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(TextFieldValue it) {
                                            mutableState3.setValue(it);
                                        }
                                    });
                                }
                            };
                            $composer4.updateRememberedValue(value$iv3);
                        } else {
                            value$iv3 = it$iv3;
                        }
                        Function1 function2 = (Function1) value$iv3;
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        int iM3125getHouryecRtBI = TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI();
                        KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, KeyboardType.INSTANCE.m6714getNumberPjHm6EE(), ImeAction.INSTANCE.m6660getNexteUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 115, (DefaultConstructorMarker) null);
                        ComposerKt.sourceInformationMarkerStart($composer4, -284381013, "CC(remember):TimePicker.kt#9igjgp");
                        boolean invalid$iv5 = $composer4.changedInstance(timePickerState);
                        Object it$iv4 = $composer4.rememberedValue();
                        if (invalid$iv5 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                            value$iv4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$1$3$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                    invoke2(keyboardActionScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyboardActionScope $this$$receiver) {
                                    timePickerState.mo2134setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI());
                                }
                            };
                            $composer4.updateRememberedValue(value$iv4);
                        } else {
                            value$iv4 = it$iv4;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        TimePickerKt.m3095TimePickerTextField1vLObsk(modifierOnKeyEvent, textFieldValueTimeInputImpl$lambda$6, function2, timePickerState, iM3125getHouryecRtBI, keyboardOptions, new KeyboardActions(null, null, (Function1) value$iv4, null, null, null, 59, null), timePickerColors, $composer4, 24576, 0);
                        TimePickerKt.DisplaySeparator(SizeKt.m1081sizeVpY3zN4(Modifier.INSTANCE, TimePickerKt.DisplaySeparatorWidth, TimeInputTokens.INSTANCE.m3907getPeriodSelectorContainerHeightD9Ej5fM()), $composer4, 6);
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer4, -284368035, "CC(remember):TimePicker.kt#9igjgp");
                        boolean invalid$iv6 = $composer4.changed(mutableState2) | $composer4.changedInstance(timePickerState);
                        Object it$iv5 = $composer4.rememberedValue();
                        if (invalid$iv6 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                            value$iv5 = new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$1$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                                    return m3108invokeZmokQxo(keyEvent.m5588unboximpl());
                                }

                                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                                public final Boolean m3108invokeZmokQxo(android.view.KeyEvent event) {
                                    boolean switchFocus = KeyEvent_androidKt.m5601getUtf16CodePointZmokQxo(event) == 0 && TextRange.m6479getStartimpl(TimePickerKt.TimeInputImpl$lambda$9(mutableState2).getSelection()) == 0;
                                    if (switchFocus) {
                                        timePickerState.mo2134setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI());
                                    }
                                    return Boolean.valueOf(switchFocus);
                                }
                            };
                            $composer4.updateRememberedValue(value$iv5);
                        } else {
                            value$iv5 = it$iv5;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Modifier modifierOnPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(companion2, (Function1) value$iv5);
                        TextFieldValue textFieldValueTimeInputImpl$lambda$9 = TimePickerKt.TimeInputImpl$lambda$9(mutableState2);
                        ComposerKt.sourceInformationMarkerStart($composer4, -284351996, "CC(remember):TimePicker.kt#9igjgp");
                        boolean invalid$iv7 = $composer4.changedInstance(timePickerState) | $composer4.changed(mutableState2);
                        Object it$iv6 = $composer4.rememberedValue();
                        if (invalid$iv7 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                            value$iv6 = new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$1$5$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                    invoke2(textFieldValue);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextFieldValue newValue) {
                                    int iM3126getMinuteyecRtBI = TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI();
                                    TimePickerState timePickerState2 = timePickerState;
                                    TextFieldValue textFieldValueTimeInputImpl$lambda$10 = TimePickerKt.TimeInputImpl$lambda$9(mutableState2);
                                    final MutableState<TextFieldValue> mutableState3 = mutableState2;
                                    TimePickerKt.m3106timeInputOnChangez7XvuPQ(iM3126getMinuteyecRtBI, timePickerState2, newValue, textFieldValueTimeInputImpl$lambda$10, 59, new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$1$5$1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                            invoke2(textFieldValue);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(TextFieldValue it) {
                                            mutableState3.setValue(it);
                                        }
                                    });
                                }
                            };
                            $composer4.updateRememberedValue(value$iv6);
                        } else {
                            value$iv6 = it$iv6;
                        }
                        Function1 function3 = (Function1) value$iv6;
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        int iM3126getMinuteyecRtBI = TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI();
                        KeyboardOptions keyboardOptions2 = new KeyboardOptions(0, (Boolean) null, KeyboardType.INSTANCE.m6714getNumberPjHm6EE(), ImeAction.INSTANCE.m6658getDoneeUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 115, (DefaultConstructorMarker) null);
                        ComposerKt.sourceInformationMarkerStart($composer4, -284324501, "CC(remember):TimePicker.kt#9igjgp");
                        boolean invalid$iv8 = $composer4.changedInstance(timePickerState);
                        Object it$iv7 = $composer4.rememberedValue();
                        if (invalid$iv8 || it$iv7 == Composer.INSTANCE.getEmpty()) {
                            value$iv7 = new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$1$6$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                    invoke2(keyboardActionScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyboardActionScope $this$$receiver) {
                                    timePickerState.mo2134setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI());
                                }
                            };
                            $composer4.updateRememberedValue(value$iv7);
                        } else {
                            value$iv7 = it$iv7;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        TimePickerKt.m3095TimePickerTextField1vLObsk(modifierOnPreviewKeyEvent, textFieldValueTimeInputImpl$lambda$9, function3, timePickerState, iM3126getMinuteyecRtBI, keyboardOptions2, new KeyboardActions(null, null, (Function1) value$iv7, null, null, null, 59, null), timePickerColors, $composer4, 24576, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            $composer2.startReplaceGroup(511443242);
            ComposerKt.sourceInformation($composer2, "1073@45823L324");
            if (!state.getIs24hour()) {
                Modifier modifier$iv2 = PaddingKt.m1038paddingqDBjuR0$default(Modifier.INSTANCE, PeriodToggleMargin, 0.0f, 0.0f, 0.0f, 14, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function1 = constructor2;
                    $composer2.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer2.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer2);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -415465671, "C1074@45891L242:TimePicker.kt#uh7d8r");
                VerticalPeriodToggle(SizeKt.m1081sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m3908getPeriodSelectorContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m3907getPeriodSelectorContainerHeightD9Ej5fM()), state, colors, $composer2, (($dirty2 >> 3) & 112) | 6 | (($dirty2 << 3) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
            }
            $composer2.endReplaceGroup();
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.TimeInputImpl.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    TimePickerKt.TimeInputImpl(modifier, colors, state, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$lambda$6(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$lambda$9(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HorizontalClockDisplay(final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Composer $composer2 = $composer.startRestartGroup(755539561);
        ComposerKt.sourceInformation($composer2, "C(HorizontalClockDisplay)P(1)1087@46270L590:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(colors) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(755539561, $dirty, -1, "androidx.compose.material3.HorizontalClockDisplay (TimePicker.kt:1086)");
            }
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1556019140, "C1088@46329L34:TimePicker.kt#uh7d8r");
            ClockDisplayNumbers(state, colors, $composer2, ($dirty & 14) | ($dirty & 112));
            $composer2.startReplaceGroup(919638492);
            ComposerKt.sourceInformation($composer2, "1090@46407L437");
            if (!state.getIs24hour()) {
                Modifier modifier$iv2 = PaddingKt.m1038paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, PeriodToggleMargin, 0.0f, 0.0f, 13, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function1 = constructor2;
                    $composer2.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer2.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer2);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -743649264, "C1091@46484L346:TimePicker.kt#uh7d8r");
                HorizontalPeriodToggle(SizeKt.m1081sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m3919getPeriodSelectorHorizontalContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m3918getPeriodSelectorHorizontalContainerHeightD9Ej5fM()), state, colors, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
            }
            $composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.HorizontalClockDisplay.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    TimePickerKt.HorizontalClockDisplay(state, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerticalClockDisplay(final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Composer $composer2 = $composer.startRestartGroup(2054675515);
        ComposerKt.sourceInformation($composer2, "C(VerticalClockDisplay)P(1)1107@46965L585:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(colors) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2054675515, $dirty, -1, "androidx.compose.material3.VerticalClockDisplay (TimePicker.kt:1106)");
            }
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -519240274, "C1108@47023L34:TimePicker.kt#uh7d8r");
            ClockDisplayNumbers(state, colors, $composer2, ($dirty & 14) | ($dirty & 112));
            $composer2.startReplaceGroup(-709485014);
            ComposerKt.sourceInformation($composer2, "1110@47101L433");
            if (!state.getIs24hour()) {
                Modifier modifier$iv2 = PaddingKt.m1038paddingqDBjuR0$default(Modifier.INSTANCE, PeriodToggleMargin, 0.0f, 0.0f, 0.0f, 14, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function1 = constructor2;
                    $composer2.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer2.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer2);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 204292100, "C1111@47180L340:TimePicker.kt#uh7d8r");
                VerticalPeriodToggle(SizeKt.m1081sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m3922getPeriodSelectorVerticalContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m3921getPeriodSelectorVerticalContainerHeightD9Ej5fM()), state, colors, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
            }
            $composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.VerticalClockDisplay.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    TimePickerKt.VerticalClockDisplay(state, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockDisplayNumbers(final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-934561141);
        ComposerKt.sourceInformation($composer2, "C(ClockDisplayNumbers)P(1)1128@47738L5,1131@47873L775,1127@47654L994:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(colors) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-934561141, $dirty, -1, "androidx.compose.material3.ClockDisplayNumbers (TimePicker.kt:1126)");
            }
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{TextKt.getLocalTextStyle().provides(TypographyKt.getValue(TimePickerTokens.INSTANCE.getTimeSelectorLabelTextFont(), $composer2, 6)), CompositionLocalsKt.getLocalLayoutDirection().provides(LayoutDirection.Ltr)}, ComposableLambdaKt.rememberComposableLambda(-477913269, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockDisplayNumbers.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C1132@47883L759:TimePicker.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-477913269, $changed2, -1, "androidx.compose.material3.ClockDisplayNumbers.<anonymous> (TimePicker.kt:1132)");
                        }
                        TimePickerState timePickerState = state;
                        TimePickerColors timePickerColors = colors;
                        ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer3);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        int i2 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 599195447, "C1133@47901L294,1140@48208L123,1143@48344L288:TimePicker.kt#uh7d8r");
                        TimePickerKt.m3096TimeSelectorSAnMeKU(SizeKt.m1081sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m3925getTimeSelectorContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m3924getTimeSelectorContainerHeightD9Ej5fM()), TimePickerKt.getHourForDisplay(timePickerState), timePickerState, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI(), timePickerColors, $composer3, 3078);
                        TimePickerKt.DisplaySeparator(SizeKt.m1081sizeVpY3zN4(Modifier.INSTANCE, TimePickerKt.DisplaySeparatorWidth, TimePickerTokens.INSTANCE.m3921getPeriodSelectorVerticalContainerHeightD9Ej5fM()), $composer3, 6);
                        TimePickerKt.m3096TimeSelectorSAnMeKU(SizeKt.m1081sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m3925getTimeSelectorContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m3924getTimeSelectorContainerHeightD9Ej5fM()), timePickerState.getMinute(), timePickerState, TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI(), timePickerColors, $composer3, 3078);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockDisplayNumbers.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TimePickerKt.ClockDisplayNumbers(state, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HorizontalPeriodToggle(final Modifier modifier, final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(1261215927);
        ComposerKt.sourceInformation($composer2, "C(HorizontalPeriodToggle)P(1,2)1160@48810L1014,1188@49871L5,1190@49902L206:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1261215927, $dirty2, -1, "androidx.compose.material3.HorizontalPeriodToggle (TimePicker.kt:1159)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 2071625362, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new MeasurePolicy() { // from class: androidx.compose.material3.TimePickerKt$HorizontalPeriodToggle$measurePolicy$1$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo391measure3p2s80s(MeasureScope $this$MeasurePolicy, List<? extends Measurable> list, long constraints) {
                        int size = list.size();
                        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                            Object item$iv$iv = list.get(index$iv$iv);
                            Measurable it = (Measurable) item$iv$iv;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "Spacer")) {
                                Measurable spacer = (Measurable) item$iv$iv;
                                final Placeable spacerPlaceable = spacer.mo5870measureBRTryo0(Constraints.m6959copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m6971getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m6969getMaxWidthimpl(constraints) : $this$MeasurePolicy.mo720roundToPx0680j_4(TimePickerTokens.INSTANCE.m3920getPeriodSelectorOutlineWidthD9Ej5fM()), (11 & 4) != 0 ? Constraints.m6970getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m6968getMaxHeightimpl(constraints) : 0));
                                List target$iv = new ArrayList(list.size());
                                int index$iv$iv2 = 0;
                                int size2 = list.size();
                                while (index$iv$iv2 < size2) {
                                    Measurable measurable = list.get(index$iv$iv2);
                                    Measurable it2 = measurable;
                                    Measurable spacer2 = spacer;
                                    if (!Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "Spacer")) {
                                        target$iv.add(measurable);
                                    }
                                    index$iv$iv2++;
                                    spacer = spacer2;
                                }
                                List $this$fastMap$iv = target$iv;
                                List target$iv2 = new ArrayList($this$fastMap$iv.size());
                                List $this$fastForEach$iv$iv = $this$fastMap$iv;
                                int $i$f$fastForEach = 0;
                                int index$iv$iv3 = 0;
                                int size3 = $this$fastForEach$iv$iv.size();
                                while (index$iv$iv3 < size3) {
                                    Measurable item = (Measurable) $this$fastForEach$iv$iv.get(index$iv$iv3);
                                    target$iv2.add(item.mo5870measureBRTryo0(Constraints.m6959copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m6971getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m6969getMaxWidthimpl(constraints) : Constraints.m6969getMaxWidthimpl(constraints) / 2, (11 & 4) != 0 ? Constraints.m6970getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m6968getMaxHeightimpl(constraints) : 0)));
                                    index$iv$iv3++;
                                    $this$fastForEach$iv$iv = $this$fastForEach$iv$iv;
                                    $i$f$fastForEach = $i$f$fastForEach;
                                }
                                final List items = target$iv2;
                                return MeasureScope.layout$default($this$MeasurePolicy, Constraints.m6969getMaxWidthimpl(constraints), Constraints.m6968getMaxHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$HorizontalPeriodToggle$measurePolicy$1$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                        invoke2(placementScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Placeable.PlacementScope $this$layout) {
                                        Placeable.PlacementScope.place$default($this$layout, items.get(0), 0, 0, 0.0f, 4, null);
                                        Placeable.PlacementScope.place$default($this$layout, items.get(1), items.get(0).getWidth(), 0, 0.0f, 4, null);
                                        Placeable.PlacementScope.place$default($this$layout, spacerPlaceable, items.get(0).getWidth() - (spacerPlaceable.getWidth() / 2), 0, 0.0f, 4, null);
                                    }
                                }, 4, null);
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Shape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), $composer2, 6);
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.foundation.shape.CornerBasedShape");
            CornerBasedShape shape = (CornerBasedShape) value;
            PeriodToggleImpl(modifier, state, colors, measurePolicy, ShapesKt.start(shape), ShapesKt.end(shape), $composer2, ($dirty2 & 14) | 3072 | ($dirty2 & 112) | ($dirty2 & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.HorizontalPeriodToggle.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TimePickerKt.HorizontalPeriodToggle(modifier, state, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerticalPeriodToggle(final Modifier modifier, final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(-1898918107);
        ComposerKt.sourceInformation($composer2, "C(VerticalPeriodToggle)P(1,2)1206@50268L1021,1234@51336L5,1236@51367L207:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1898918107, $dirty2, -1, "androidx.compose.material3.VerticalPeriodToggle (TimePicker.kt:1205)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1491514731, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new MeasurePolicy() { // from class: androidx.compose.material3.TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo391measure3p2s80s(MeasureScope $this$MeasurePolicy, List<? extends Measurable> list, long constraints) {
                        int size = list.size();
                        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                            Object item$iv$iv = list.get(index$iv$iv);
                            Measurable it = (Measurable) item$iv$iv;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "Spacer")) {
                                Measurable spacer = (Measurable) item$iv$iv;
                                final Placeable spacerPlaceable = spacer.mo5870measureBRTryo0(Constraints.m6959copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m6971getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m6969getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m6970getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m6968getMaxHeightimpl(constraints) : $this$MeasurePolicy.mo720roundToPx0680j_4(TimePickerTokens.INSTANCE.m3920getPeriodSelectorOutlineWidthD9Ej5fM())));
                                List target$iv = new ArrayList(list.size());
                                int index$iv$iv2 = 0;
                                int size2 = list.size();
                                while (index$iv$iv2 < size2) {
                                    Measurable measurable = list.get(index$iv$iv2);
                                    Measurable it2 = measurable;
                                    Measurable spacer2 = spacer;
                                    if (!Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "Spacer")) {
                                        target$iv.add(measurable);
                                    }
                                    index$iv$iv2++;
                                    spacer = spacer2;
                                }
                                List $this$fastMap$iv = target$iv;
                                List target$iv2 = new ArrayList($this$fastMap$iv.size());
                                List $this$fastForEach$iv$iv = $this$fastMap$iv;
                                int $i$f$fastForEach = 0;
                                int index$iv$iv3 = 0;
                                int size3 = $this$fastForEach$iv$iv.size();
                                while (index$iv$iv3 < size3) {
                                    Measurable item = (Measurable) $this$fastForEach$iv$iv.get(index$iv$iv3);
                                    target$iv2.add(item.mo5870measureBRTryo0(Constraints.m6959copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m6971getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m6969getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m6970getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m6968getMaxHeightimpl(constraints) : Constraints.m6968getMaxHeightimpl(constraints) / 2)));
                                    index$iv$iv3++;
                                    $this$fastForEach$iv$iv = $this$fastForEach$iv$iv;
                                    $i$f$fastForEach = $i$f$fastForEach;
                                }
                                final List items = target$iv2;
                                return MeasureScope.layout$default($this$MeasurePolicy, Constraints.m6969getMaxWidthimpl(constraints), Constraints.m6968getMaxHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                        invoke2(placementScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Placeable.PlacementScope $this$layout) {
                                        Placeable.PlacementScope.place$default($this$layout, items.get(0), 0, 0, 0.0f, 4, null);
                                        Placeable.PlacementScope.place$default($this$layout, items.get(1), 0, items.get(0).getHeight(), 0.0f, 4, null);
                                        Placeable.PlacementScope.place$default($this$layout, spacerPlaceable, 0, items.get(0).getHeight() - (spacerPlaceable.getHeight() / 2), 0.0f, 4, null);
                                    }
                                }, 4, null);
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Shape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), $composer2, 6);
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.foundation.shape.CornerBasedShape");
            CornerBasedShape shape = (CornerBasedShape) value;
            PeriodToggleImpl(modifier, state, colors, measurePolicy, ShapesKt.top(shape), ShapesKt.bottom(shape), $composer2, ($dirty2 & 14) | 3072 | ($dirty2 & 112) | ($dirty2 & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.VerticalPeriodToggle.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TimePickerKt.VerticalPeriodToggle(modifier, state, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PeriodToggleImpl(final Modifier modifier, final TimePickerState state, final TimePickerColors colors, final MeasurePolicy measurePolicy, final Shape startShape, final Shape endShape, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        Composer $composer2 = $composer.startRestartGroup(1374241901);
        ComposerKt.sourceInformation($composer2, "C(PeriodToggleImpl)P(3,5!1,2,4)1258@51953L5,1259@52008L41,1263@52129L128,1260@52054L1189:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(measurePolicy) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(startShape) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(endShape) ? 131072 : 65536;
        }
        int $dirty2 = $dirty;
        if ((74899 & $dirty2) != 74898 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1374241901, $dirty2, -1, "androidx.compose.material3.PeriodToggleImpl (TimePicker.kt:1254)");
            }
            BorderStroke borderStroke = BorderStrokeKt.m616BorderStrokecXLIe8U(TimePickerTokens.INSTANCE.m3920getPeriodSelectorOutlineWidthD9Ej5fM(), colors.getPeriodSelectorBorderColor());
            Shape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), $composer2, 6);
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.foundation.shape.CornerBasedShape");
            CornerBasedShape shape = (CornerBasedShape) value;
            Strings.Companion companion = Strings.INSTANCE;
            final String contentDescription = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_time_picker_period_toggle_description), $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -2132305224, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(contentDescription);
            Object value$iv3 = $composer2.rememberedValue();
            if (invalid$iv || value$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$PeriodToggleImpl$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setTraversalGroup($this$semantics, true);
                        SemanticsPropertiesKt.setContentDescription($this$semantics, contentDescription);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierBorder = BorderKt.border(SelectableGroupKt.selectableGroup(SemanticsModifierKt.semantics$default(modifier, false, (Function1) value$iv3, 1, null)), borderStroke, shape);
            int $changed$iv = ($dirty2 >> 3) & 896;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifierBorder);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = (($changed$iv << 6) & 896) | 6;
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
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 822309452, "C1274@52547L29,1271@52427L283,1279@52723L219,1288@53072L28,1285@52955L272:TimePicker.kt#uh7d8r");
            boolean z = !state.isAfternoon();
            ComposerKt.sourceInformationMarkerStart($composer2, -250565484, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv2 = ($dirty2 & 112) == 32 || (($dirty2 & 64) != 0 && $composer2.changedInstance(state));
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.TimePickerKt$PeriodToggleImpl$2$1$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        state.setAfternoon(false);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ToggleItem(z, startShape, (Function0) value$iv, colors, ComposableSingletons$TimePickerKt.INSTANCE.m2364getLambda1$material3_release(), $composer2, (($dirty2 >> 9) & 112) | 24576 | (($dirty2 << 3) & 7168));
            SpacerKt.Spacer(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.fillMaxSize$default(ZIndexModifierKt.zIndex(LayoutIdKt.layoutId(Modifier.INSTANCE, "Spacer"), SeparatorZIndex), 0.0f, 1, null), colors.getPeriodSelectorBorderColor(), null, 2, null), $composer2, 0);
            boolean zIsAfternoon = state.isAfternoon();
            ComposerKt.sourceInformationMarkerStart($composer2, -250548685, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv3 = ($dirty2 & 112) == 32 || (($dirty2 & 64) != 0 && $composer2.changedInstance(state));
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new Function0<Unit>() { // from class: androidx.compose.material3.TimePickerKt$PeriodToggleImpl$2$2$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        state.setAfternoon(true);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ToggleItem(zIsAfternoon, endShape, (Function0) value$iv2, colors, ComposableSingletons$TimePickerKt.INSTANCE.m2365getLambda2$material3_release(), $composer2, (($dirty2 >> 12) & 112) | 24576 | (($dirty2 << 3) & 7168));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.PeriodToggleImpl.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i2) {
                    TimePickerKt.PeriodToggleImpl(modifier, state, colors, measurePolicy, startShape, endShape, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ToggleItem(final boolean checked, final Shape shape, final Function0<Unit> function0, final TimePickerColors colors, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(-1937408098);
        ComposerKt.sourceInformation($composer2, "C(ToggleItem)P(!1,4,3)1310@53677L22,1316@53868L124,1308@53569L429:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(checked) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(shape) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(colors) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 16384 : 8192;
        }
        if (($dirty & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1937408098, $dirty, -1, "androidx.compose.material3.ToggleItem (TimePicker.kt:1304)");
            }
            long contentColor = colors.m3088periodSelectorContentColorvNxB06k$material3_release(checked);
            long containerColor = colors.m3087periodSelectorContainerColorvNxB06k$material3_release(checked);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(ZIndexModifierKt.zIndex(Modifier.INSTANCE, checked ? 0.0f : 1.0f), 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -857429536, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ToggleItem$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setSelected($this$semantics, checked);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ButtonKt.TextButton(function0, SemanticsModifierKt.semantics$default(modifierFillMaxSize$default, false, (Function1) value$iv, 1, null), false, shape, ButtonDefaults.INSTANCE.m2194textButtonColorsro_MJ88(containerColor, contentColor, 0L, 0L, $composer2, 24576, 12), null, null, PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0)), null, function3, $composer2, (($dirty >> 6) & 14) | 12582912 | (($dirty << 6) & 7168) | (($dirty << 15) & 1879048192), 356);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ToggleItem.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TimePickerKt.ToggleItem(checked, shape, function0, colors, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DisplaySeparator(final Modifier modifier, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(2100674302);
        ComposerKt.sourceInformation($composer2, "C(DisplaySeparator)1326@54104L7,1335@54375L172:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($dirty & 3) == 2 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2100674302, $dirty, -1, "androidx.compose.material3.DisplaySeparator (TimePicker.kt:1324)");
            }
            ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localTextStyle);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            TextStyle textStyle = (TextStyle) objConsume;
            TextStyle style = textStyle.m6505copyp1EtxEg((15695871 & 1) != 0 ? textStyle.spanStyle.m6429getColor0d7_KjU() : 0L, (15695871 & 2) != 0 ? textStyle.spanStyle.getFontSize() : 0L, (15695871 & 4) != 0 ? textStyle.spanStyle.getFontWeight() : null, (15695871 & 8) != 0 ? textStyle.spanStyle.getFontStyle() : null, (15695871 & 16) != 0 ? textStyle.spanStyle.getFontSynthesis() : null, (15695871 & 32) != 0 ? textStyle.spanStyle.getFontFamily() : null, (15695871 & 64) != 0 ? textStyle.spanStyle.getFontFeatureSettings() : null, (15695871 & 128) != 0 ? textStyle.spanStyle.getLetterSpacing() : 0L, (15695871 & 256) != 0 ? textStyle.spanStyle.getBaselineShift() : null, (15695871 & 512) != 0 ? textStyle.spanStyle.getTextGeometricTransform() : null, (15695871 & 1024) != 0 ? textStyle.spanStyle.getLocaleList() : null, (15695871 & 2048) != 0 ? textStyle.spanStyle.getBackground() : 0L, (15695871 & 4096) != 0 ? textStyle.spanStyle.getTextDecoration() : null, (15695871 & 8192) != 0 ? textStyle.spanStyle.getShadow() : null, (15695871 & 16384) != 0 ? textStyle.spanStyle.getDrawStyle() : null, (15695871 & 32768) != 0 ? textStyle.paragraphStyle.getTextAlign() : TextAlign.INSTANCE.m6883getCentere0LSkKk(), (15695871 & 65536) != 0 ? textStyle.paragraphStyle.getTextDirection() : 0, (15695871 & 131072) != 0 ? textStyle.paragraphStyle.getLineHeight() : 0L, (15695871 & 262144) != 0 ? textStyle.paragraphStyle.getTextIndent() : null, (15695871 & 524288) != 0 ? textStyle.platformStyle : null, (15695871 & 1048576) != 0 ? textStyle.paragraphStyle.getLineHeightStyle() : new LineHeightStyle(LineHeightStyle.Alignment.INSTANCE.m6860getCenterPIaL0Z0(), LineHeightStyle.Trim.INSTANCE.m6872getBothEVpEnUU(), null), (15695871 & 2097152) != 0 ? textStyle.paragraphStyle.getLineBreak() : 0, (15695871 & 4194304) != 0 ? textStyle.paragraphStyle.getHyphens() : 0, (15695871 & 8388608) != 0 ? textStyle.paragraphStyle.getTextMotion() : null);
            Modifier modifier$iv = SemanticsModifierKt.clearAndSetSemantics(modifier, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt.DisplaySeparator.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
                }
            });
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
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
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -789568360, "C1336@54520L5,1336@54471L70:TimePicker.kt#uh7d8r");
            TextKt.m3067Text4IGK_g(":", (Modifier) null, ColorSchemeKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSeparatorColor(), $composer2, 6), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, style, $composer2, 6, 0, 65530);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.DisplaySeparator.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    TimePickerKt.DisplaySeparator(modifier, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: TimeSelector-SAnMeKU, reason: not valid java name */
    public static final void m3096TimeSelectorSAnMeKU(final Modifier modifier, final int value, final TimePickerState state, final int selection, final TimePickerColors colors, Composer $composer, final int $changed) {
        int iM3279constructorimpl;
        Object value$iv;
        Object value$iv2;
        Composer $composer2 = $composer.startRestartGroup(-1148055889);
        ComposerKt.sourceInformation($composer2, "C(TimeSelector)P(1,4,3,2:c#material3.TimePickerSelectionMode)1351@54864L214,1363@55302L124,1373@55637L5,1367@55446L117,1375@55682L497,1361@55218L961:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(value) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= ($changed & 512) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(selection) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(colors) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1148055889, $dirty2, -1, "androidx.compose.material3.TimeSelector (TimePicker.kt:1348)");
            }
            boolean selected = TimePickerSelectionMode.m3121equalsimpl0(state.mo2133getSelectionyecRtBI(), selection);
            if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                Strings.Companion companion = Strings.INSTANCE;
                iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour_selection);
            } else {
                Strings.Companion companion2 = Strings.INSTANCE;
                iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute_selection);
            }
            final String selectorContentDescription = Strings_androidKt.m3349getString2EP1pXo(iM3279constructorimpl, $composer2, 0);
            long containerColor = colors.m3089timeSelectorContainerColorvNxB06k$material3_release(selected);
            final long contentColor = colors.m3090timeSelectorContentColorvNxB06k$material3_release(selected);
            ComposerKt.sourceInformationMarkerStart($composer2, 1840519339, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(selectorContentDescription);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.m6308setRolekuIjeqM($this$semantics, Role.INSTANCE.m6293getRadioButtono7Vup1c());
                        SemanticsPropertiesKt.setContentDescription($this$semantics, selectorContentDescription);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifier, true, (Function1) value$iv);
            Shape value2 = ShapesKt.getValue(TimePickerTokens.INSTANCE.getTimeSelectorContainerShape(), $composer2, 6);
            ComposerKt.sourceInformationMarkerStart($composer2, 1840523940, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv2 = (($dirty2 & 7168) == 2048) | (($dirty2 & 896) == 256 || (($dirty2 & 512) != 0 && $composer2.changedInstance(state)));
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        if (!TimePickerSelectionMode.m3121equalsimpl0(selection, state.mo2133getSelectionyecRtBI())) {
                            state.mo2134setSelection6_8s6DQ(selection);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SurfaceKt.m2918Surfaced85dljk(selected, (Function0<Unit>) value$iv2, modifierSemantics, false, value2, containerColor, 0L, 0.0f, 0.0f, (BorderStroke) null, (MutableInteractionSource) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1477282471, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    Object value$iv3;
                    ComposerKt.sourceInformation($composer3, "C1377@55734L152,1383@55896L277:TimePicker.kt#uh7d8r");
                    if (($changed2 & 3) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1477282471, $changed2, -1, "androidx.compose.material3.TimeSelector.<anonymous> (TimePicker.kt:1376)");
                    }
                    final String valueContentDescription = TimePickerKt.m3104numberContentDescriptiondSwYdS4(selection, state.getIs24hour(), value, $composer3, 0);
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                    int i = value;
                    long j = contentColor;
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer3);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i2 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i3 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 396102018, "C1385@56003L48,1384@55951L212:TimePicker.kt#uh7d8r");
                    Modifier.Companion companion3 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 705515645, "CC(remember):TimePicker.kt#9igjgp");
                    boolean invalid$iv3 = $composer3.changed(valueContentDescription);
                    Object it$iv3 = $composer3.rememberedValue();
                    if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                        value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$3$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                SemanticsPropertiesKt.setContentDescription($this$semantics, valueContentDescription);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = it$iv3;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    TextKt.m3067Text4IGK_g(CalendarLocale_jvmKt.toLocalString$default(i, 2, 0, false, 6, null), SemanticsModifierKt.semantics$default(companion3, false, (Function1) value$iv3, 1, null), j, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 0, 0, 131064);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, 0, 48, 1992);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TimePickerKt.m3096TimeSelectorSAnMeKU(modifier, value, state, selection, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public static final void ClockFace(final AnalogTimePickerState state, final TimePickerColors colors, final boolean autoSwitchToMinute, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1170157036);
        ComposerKt.sourceInformation($composer2, "C(ClockFace)P(2,1)1521@60272L2018,1513@59878L2412:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(colors) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(autoSwitchToMinute) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1170157036, $dirty2, -1, "androidx.compose.material3.ClockFace (TimePicker.kt:1512)");
            }
            CrossfadeKt.Crossfade(state.getClockFaceValues(), drawSelector(SizeKt.m1079size3ABfNKs(BackgroundKt.m588backgroundbw27NRU(Modifier.INSTANCE, colors.getClockDialColor(), RoundedCornerShapeKt.getCircleShape()).then(new ClockDialModifier(state, autoSwitchToMinute, state.mo2133getSelectionyecRtBI(), null)), TimePickerTokens.INSTANCE.m3913getClockDialContainerSizeD9Ej5fM()), state, colors), AnimationSpecKt.tween$default(200, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(-1022006568, true, new Function3<IntList, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(IntList intList, Composer composer, Integer num) {
                    invoke(intList, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(final IntList screen, Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C1525@60456L1828,1522@60292L1992:TimePicker.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1022006568, $changed2, -1, "androidx.compose.material3.ClockFace.<anonymous> (TimePicker.kt:1522)");
                    }
                    Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m3913getClockDialContainerSizeD9Ej5fM()), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.1.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                            SemanticsPropertiesKt.selectableGroup($this$semantics);
                        }
                    }, 1, null);
                    float f = TimePickerKt.OuterCircleSizeRadius;
                    final TimePickerColors timePickerColors = colors;
                    final AnalogTimePickerState analogTimePickerState = state;
                    final boolean z = autoSwitchToMinute;
                    TimePickerKt.m3093CircularLayoutuFdPcIQ(modifierSemantics$default, f, ComposableLambdaKt.rememberComposableLambda(-320307952, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed3) {
                            ComposerKt.sourceInformation($composer4, "C1528@60589L1685,1526@60470L1804:TimePicker.kt#uh7d8r");
                            if (($changed3 & 3) != 2 || !$composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-320307952, $changed3, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous> (TimePicker.kt:1526)");
                                }
                                ProvidedValue<Color> providedValueProvides = ContentColorKt.getLocalContentColor().provides(Color.m4524boximpl(timePickerColors.m3071clockDialContentColorvNxB06k$material3_release(false)));
                                final IntList intList = screen;
                                final AnalogTimePickerState analogTimePickerState2 = analogTimePickerState;
                                final boolean z2 = z;
                                CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(1992872400, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.1.2.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer5, int $changed4) {
                                        int outerValue;
                                        ComposerKt.sourceInformation($composer5, "C1551@61690L552,1545@61343L899:TimePicker.kt#uh7d8r");
                                        if (($changed4 & 3) != 2 || !$composer5.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1992872400, $changed4, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1529)");
                                            }
                                            $composer5.startReplaceGroup(1547046870);
                                            ComposerKt.sourceInformation($composer5, "*1537@61008L36,1536@60943L271");
                                            int size = intList.getSize();
                                            AnalogTimePickerState analogTimePickerState3 = analogTimePickerState2;
                                            IntList intList2 = intList;
                                            boolean z3 = z2;
                                            for (int i = 0; i < size; i++) {
                                                final int index = i;
                                                if (!analogTimePickerState3.getIs24hour() || TimePickerSelectionMode.m3121equalsimpl0(analogTimePickerState3.mo2133getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI())) {
                                                    outerValue = intList2.get(index);
                                                } else {
                                                    outerValue = intList2.get(index) % 12;
                                                }
                                                Modifier.Companion companion = Modifier.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart($composer5, 707420712, "CC(remember):TimePicker.kt#9igjgp");
                                                boolean invalid$iv = $composer5.changed(index);
                                                Object value$iv = $composer5.rememberedValue();
                                                if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                                                    value$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$1$1$1
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(1);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function1
                                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                            invoke2(semanticsPropertyReceiver);
                                                            return Unit.INSTANCE;
                                                        }

                                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                        public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                                            SemanticsPropertiesKt.setTraversalIndex($this$semantics, index);
                                                        }
                                                    };
                                                    $composer5.updateRememberedValue(value$iv);
                                                }
                                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                                TimePickerKt.ClockText(SemanticsModifierKt.semantics$default(companion, false, (Function1) value$iv, 1, null), analogTimePickerState3, outerValue, z3, $composer5, 0);
                                            }
                                            $composer5.endReplaceGroup();
                                            if (TimePickerSelectionMode.m3121equalsimpl0(analogTimePickerState2.mo2133getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI()) && analogTimePickerState2.getIs24hour()) {
                                                Modifier modifierM588backgroundbw27NRU = BackgroundKt.m588backgroundbw27NRU(SizeKt.m1079size3ABfNKs(LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutId.InnerCircle), TimePickerTokens.INSTANCE.m3913getClockDialContainerSizeD9Ej5fM()), Color.INSTANCE.m4569getTransparent0d7_KjU(), RoundedCornerShapeKt.getCircleShape());
                                                float f2 = TimePickerKt.InnerCircleRadius;
                                                final AnalogTimePickerState analogTimePickerState4 = analogTimePickerState2;
                                                final boolean z4 = z2;
                                                TimePickerKt.m3093CircularLayoutuFdPcIQ(modifierM588backgroundbw27NRU, f2, ComposableLambdaKt.rememberComposableLambda(-205464413, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.1.2.1.2
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(2);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                                        invoke(composer, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(Composer $composer6, int $changed5) {
                                                        Object value$iv2;
                                                        ComposerKt.sourceInformation($composer6, "C*1556@61951L41,1554@61842L352:TimePicker.kt#uh7d8r");
                                                        if (($changed5 & 3) != 2 || !$composer6.getSkipping()) {
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(-205464413, $changed5, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1552)");
                                                            }
                                                            int size2 = TimePickerKt.ExtraHours.getSize();
                                                            AnalogTimePickerState analogTimePickerState5 = analogTimePickerState4;
                                                            boolean z5 = z4;
                                                            for (int i2 = 0; i2 < size2; i2++) {
                                                                final int index2 = i2;
                                                                int innerValue = TimePickerKt.ExtraHours.get(index2);
                                                                Modifier.Companion companion2 = Modifier.INSTANCE;
                                                                ComposerKt.sourceInformationMarkerStart($composer6, 1677472780, "CC(remember):TimePicker.kt#9igjgp");
                                                                boolean invalid$iv2 = $composer6.changed(index2);
                                                                Object it$iv = $composer6.rememberedValue();
                                                                if (invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                                                                    value$iv2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$2$1$1$1
                                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                        {
                                                                            super(1);
                                                                        }

                                                                        @Override // kotlin.jvm.functions.Function1
                                                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                                            invoke2(semanticsPropertyReceiver);
                                                                            return Unit.INSTANCE;
                                                                        }

                                                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                        public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                                                            SemanticsPropertiesKt.setTraversalIndex($this$semantics, 12 + index2);
                                                                        }
                                                                    };
                                                                    $composer6.updateRememberedValue(value$iv2);
                                                                } else {
                                                                    value$iv2 = it$iv;
                                                                }
                                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                                TimePickerKt.ClockText(SemanticsModifierKt.semantics$default(companion2, false, (Function1) value$iv2, 1, null), analogTimePickerState5, innerValue, z5, $composer6, 0);
                                                            }
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventEnd();
                                                                return;
                                                            }
                                                            return;
                                                        }
                                                        $composer6.skipToGroupEnd();
                                                    }
                                                }, $composer5, 54), $composer5, 432, 0);
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer5.skipToGroupEnd();
                                    }
                                }, $composer4, 54), $composer4, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer4.skipToGroupEnd();
                        }
                    }, $composer3, 54), $composer3, 432, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, 24960, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TimePickerKt.ClockFace(state, colors, autoSwitchToMinute, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    private static final Modifier drawSelector(Modifier $this$drawSelector, final AnalogTimePickerState state, final TimePickerColors colors) {
        return DrawModifierKt.drawWithContent($this$drawSelector, new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt.drawSelector.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope $this$drawWithContent) {
                long selectorOffsetPx = OffsetKt.Offset($this$drawWithContent.mo726toPx0680j_4(DpOffset.m7077getXD9Ej5fM(TimePickerKt.getSelectorPos(state))), $this$drawWithContent.mo726toPx0680j_4(DpOffset.m7079getYD9Ej5fM(TimePickerKt.getSelectorPos(state))));
                float f = 2;
                float selectorRadius = $this$drawWithContent.mo726toPx0680j_4(TimePickerTokens.INSTANCE.m3915getClockDialSelectorHandleContainerSizeD9Ej5fM()) / f;
                long selectorColor = colors.getSelectorColor();
                DrawScope.m5071drawCircleVaOC9Bg$default($this$drawWithContent, Color.INSTANCE.m4560getBlack0d7_KjU(), selectorRadius, selectorOffsetPx, 0.0f, null, null, BlendMode.INSTANCE.m4449getClear0nO6VwU(), 56, null);
                $this$drawWithContent.drawContent();
                DrawScope.m5071drawCircleVaOC9Bg$default($this$drawWithContent, selectorColor, selectorRadius, selectorOffsetPx, 0.0f, null, null, BlendMode.INSTANCE.m4477getXor0nO6VwU(), 56, null);
                float strokeWidth = $this$drawWithContent.mo726toPx0680j_4(TimePickerTokens.INSTANCE.m3916getClockDialSelectorTrackContainerWidthD9Ej5fM());
                long lineLength = Offset.m4297minusMKHz9U(selectorOffsetPx, OffsetKt.Offset(((float) Math.cos(state.getCurrentAngle())) * selectorRadius, ((float) Math.sin(state.getCurrentAngle())) * selectorRadius));
                DrawScope.m5076drawLineNGM6Ib0$default($this$drawWithContent, selectorColor, androidx.compose.ui.geometry.SizeKt.m4372getCenteruvyYCjk($this$drawWithContent.mo5090getSizeNHjbRc()), lineLength, strokeWidth, 0, null, 0.0f, null, BlendMode.INSTANCE.m4476getSrcOver0nO6VwU(), AuroraMotion.TAB_MS, null);
                DrawScope.m5071drawCircleVaOC9Bg$default($this$drawWithContent, selectorColor, $this$drawWithContent.mo726toPx0680j_4(TimePickerTokens.INSTANCE.m3914getClockDialSelectorCenterContainerSizeD9Ej5fM()) / f, androidx.compose.ui.geometry.SizeKt.m4372getCenteruvyYCjk($this$drawWithContent.mo5090getSizeNHjbRc()), 0.0f, null, null, 0, 120, null);
                DrawScope.m5071drawCircleVaOC9Bg$default($this$drawWithContent, colors.m3071clockDialContentColorvNxB06k$material3_release(true), selectorRadius, selectorOffsetPx, 0.0f, null, null, BlendMode.INSTANCE.m4459getDstOver0nO6VwU(), 56, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockText(final Modifier modifier, final AnalogTimePickerState state, final int value, final boolean autoSwitchToMinute, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        final MutableState parentCenter$delegate;
        int i;
        Composer $composer2 = $composer.startRestartGroup(-206784607);
        ComposerKt.sourceInformation($composer2, "C(ClockText)P(1,2,3)1639@64421L5,*1640@64463L7,1641@64513L40,1642@64578L43,1643@64638L24,1645@64700L142,1665@65318L163,1670@65563L503,1659@65093L1182:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(value) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(autoSwitchToMinute) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-206784607, $dirty2, -1, "androidx.compose.material3.ClockText (TimePicker.kt:1638)");
            }
            TextStyle style = TypographyKt.getValue(TimePickerTokens.INSTANCE.getClockDialLabelTextFont(), $composer2, 6);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$ClockText_u24lambda_u2427 = (Density) objConsume;
            final float maxDist = $this$ClockText_u24lambda_u2427.mo726toPx0680j_4(MaxDistance);
            ComposerKt.sourceInformationMarkerStart($composer2, 297230880, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m4282boximpl(Offset.INSTANCE.m4309getZeroF1C5BW0()), null, 2, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableState center$delegate = (MutableState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 297232963, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntOffset.m7135boximpl(IntOffset.INSTANCE.m7154getZeronOccac()), null, 2, null);
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            MutableState parentCenter$delegate2 = (MutableState) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer2, -954363344, "CC(remember):Effects.kt#9igjgp");
            Object value$iv$iv = $composer2.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2));
                $composer2.updateRememberedValue(value$iv$iv);
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final CoroutineScope scope = wrapper$iv.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final String contentDescription = m3104numberContentDescriptiondSwYdS4(state.mo2133getSelectionyecRtBI(), state.getIs24hour(), value, $composer2, $dirty2 & 896);
            String text = CalendarLocale_jvmKt.toLocalString$default(value, 0, 0, false, 7, null);
            final boolean selected = TimePickerSelectionMode.m3121equalsimpl0(state.mo2133getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI()) ? Intrinsics.areEqual(CalendarLocale_jvmKt.toLocalString$default(state.getMinute(), 0, 0, false, 7, null), text) : Intrinsics.areEqual(CalendarLocale_jvmKt.toLocalString$default(state.getHour(), 0, 0, false, 7, null), text);
            Alignment center = Alignment.INSTANCE.getCenter();
            Modifier modifierM1079size3ABfNKs = SizeKt.m1079size3ABfNKs(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier), MinimumInteractiveSize);
            ComposerKt.sourceInformationMarkerStart($composer2, 297256763, "CC(remember):TimePicker.kt#9igjgp");
            Object value$iv3 = $composer2.rememberedValue();
            if (value$iv3 == Composer.INSTANCE.getEmpty()) {
                parentCenter$delegate = parentCenter$delegate2;
                value$iv3 = (Function1) new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockText$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                        invoke2(layoutCoordinates);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LayoutCoordinates it) {
                        MutableState<IntOffset> mutableState = parentCenter$delegate;
                        LayoutCoordinates parentCoordinates = it.getParentCoordinates();
                        TimePickerKt.ClockText$lambda$33(mutableState, parentCoordinates != null ? IntSizeKt.m7192getCenterozmzZPI(parentCoordinates.mo5878getSizeYbymL2g()) : IntOffset.INSTANCE.m7154getZeronOccac());
                        TimePickerKt.ClockText$lambda$30(center$delegate, LayoutCoordinatesKt.boundsInParent(it).m4323getCenterF1C5BW0());
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
            } else {
                parentCenter$delegate = parentCenter$delegate2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierFocusable$default = FocusableKt.focusable$default(OnGloballyPositionedModifierKt.onGloballyPositioned(modifierM1079size3ABfNKs, (Function1) value$iv3), false, null, 3, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 297264943, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(scope) | $composer2.changedInstance(state) | $composer2.changed(maxDist) | (($dirty2 & 7168) == 2048) | $composer2.changed(selected);
            Object value$iv4 = $composer2.rememberedValue();
            if (invalid$iv || value$iv4 == Composer.INSTANCE.getEmpty()) {
                i = 0;
                final MutableState mutableState = parentCenter$delegate;
                value$iv4 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockText$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        final CoroutineScope coroutineScope = scope;
                        final AnalogTimePickerState analogTimePickerState = state;
                        final float f = maxDist;
                        final boolean z = autoSwitchToMinute;
                        final MutableState<Offset> mutableState2 = center$delegate;
                        final MutableState<IntOffset> mutableState3 = mutableState;
                        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.TimePickerKt$ClockText$2$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockText$2$1$1$1, reason: invalid class name and collision with other inner class name */
                            /* JADX INFO: compiled from: TimePicker.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material3.TimePickerKt$ClockText$2$1$1$1", f = "TimePicker.kt", i = {}, l = {1674}, m = "invokeSuspend", n = {}, s = {})
                            static final class C01321 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ boolean $autoSwitchToMinute;
                                final /* synthetic */ MutableState<Offset> $center$delegate;
                                final /* synthetic */ float $maxDist;
                                final /* synthetic */ MutableState<IntOffset> $parentCenter$delegate;
                                final /* synthetic */ AnalogTimePickerState $state;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                C01321(AnalogTimePickerState analogTimePickerState, float f, boolean z, MutableState<Offset> mutableState, MutableState<IntOffset> mutableState2, Continuation<? super C01321> continuation) {
                                    super(2, continuation);
                                    this.$state = analogTimePickerState;
                                    this.$maxDist = f;
                                    this.$autoSwitchToMinute = z;
                                    this.$center$delegate = mutableState;
                                    this.$parentCenter$delegate = mutableState2;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new C01321(this.$state, this.$maxDist, this.$autoSwitchToMinute, this.$center$delegate, this.$parentCenter$delegate, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((C01321) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object $result) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch (this.label) {
                                        case 0:
                                            ResultKt.throwOnFailure($result);
                                            this.label = 1;
                                            if (TimePickerKt.m3105onTaprOwcSBo(this.$state, Offset.m4293getXimpl(TimePickerKt.ClockText$lambda$29(this.$center$delegate)), Offset.m4294getYimpl(TimePickerKt.ClockText$lambda$29(this.$center$delegate)), this.$maxDist, this.$autoSwitchToMinute, TimePickerKt.ClockText$lambda$32(this.$parentCenter$delegate), this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            break;
                                        case 1:
                                            ResultKt.throwOnFailure($result);
                                            break;
                                        default:
                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    return Unit.INSTANCE;
                                }
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C01321(analogTimePickerState, f, z, mutableState2, mutableState3, null), 3, null);
                                return true;
                            }
                        }, 1, null);
                        SemanticsPropertiesKt.setSelected($this$semantics, selected);
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
            } else {
                i = 0;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifierFocusable$default, true, (Function1) value$iv4);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, i);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifierSemantics);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
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
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1481062706, "C1688@66158L48,1686@66083L186:TimePicker.kt#uh7d8r");
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 879062471, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changed(contentDescription);
            Object value$iv5 = $composer2.rememberedValue();
            if (invalid$iv2 || value$iv5 == Composer.INSTANCE.getEmpty()) {
                value$iv5 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockText$3$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
                        SemanticsPropertiesKt.setContentDescription($this$clearAndSetSemantics, contentDescription);
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            TextKt.m3067Text4IGK_g(text, SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) value$iv5), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, style, $composer2, 0, 0, 65532);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockText.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i4) {
                    TimePickerKt.ClockText(modifier, state, value, autoSwitchToMinute, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long ClockText$lambda$29(MutableState<Offset> mutableState) {
        MutableState<Offset> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().getPackedValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockText$lambda$30(MutableState<Offset> mutableState, long value) {
        mutableState.setValue(Offset.m4282boximpl(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long ClockText$lambda$32(MutableState<IntOffset> mutableState) {
        MutableState<IntOffset> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().getPackedValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockText$lambda$33(MutableState<IntOffset> mutableState, long value) {
        mutableState.setValue(IntOffset.m7135boximpl(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: timeInputOnChange-z7XvuPQ, reason: not valid java name */
    public static final void m3106timeInputOnChangez7XvuPQ(int selection, TimePickerState state, TextFieldValue value, TextFieldValue prevValue, int max, Function1<? super TextFieldValue, Unit> function1) {
        int newValue;
        TextFieldValue textFieldValueM6722copy3r_uNRQ$default;
        if (Intrinsics.areEqual(value.getText(), prevValue.getText())) {
            function1.invoke(value);
            return;
        }
        if (value.getText().length() == 0) {
            if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                state.setHour(0);
            } else {
                state.setMinute(0);
            }
            function1.invoke(TextFieldValue.m6722copy3r_uNRQ$default(value, "", 0L, (TextRange) null, 6, (Object) null));
            return;
        }
        try {
            if (value.getText().length() == 3 && TextRange.m6479getStartimpl(value.getSelection()) == 1) {
                newValue = CharsKt.digitToInt(value.getText().charAt(0));
            } else {
                newValue = Integer.parseInt(value.getText());
            }
            if (newValue <= max) {
                if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                    state.setHour(newValue);
                    if (newValue > 1 && !state.getIs24hour()) {
                        state.mo2134setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI());
                    }
                } else {
                    state.setMinute(newValue);
                }
                if (value.getText().length() <= 2) {
                    textFieldValueM6722copy3r_uNRQ$default = value;
                } else {
                    textFieldValueM6722copy3r_uNRQ$default = TextFieldValue.m6722copy3r_uNRQ$default(value, String.valueOf(value.getText().charAt(0)), 0L, (TextRange) null, 6, (Object) null);
                }
                function1.invoke(textFieldValueM6722copy3r_uNRQ$default);
            }
        } catch (NumberFormatException e) {
        } catch (IllegalArgumentException e2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:102:0x0135  */
    /* JADX WARN: Code duplicated, block: B:105:0x015a  */
    /* JADX WARN: Code duplicated, block: B:106:0x0165  */
    /* JADX WARN: Code duplicated, block: B:109:0x0187  */
    /* JADX WARN: Code duplicated, block: B:110:0x0194  */
    /* JADX WARN: Code duplicated, block: B:113:0x028f  */
    /* JADX WARN: Code duplicated, block: B:116:0x029b  */
    /* JADX WARN: Code duplicated, block: B:117:0x02a1  */
    /* JADX WARN: Code duplicated, block: B:120:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:123:0x02e5  */
    /* JADX WARN: Code duplicated, block: B:124:0x02e8  */
    /* JADX WARN: Code duplicated, block: B:128:0x033e  */
    /* JADX WARN: Code duplicated, block: B:130:0x0360  */
    /* JADX WARN: Code duplicated, block: B:131:0x0365  */
    /* JADX WARN: Code duplicated, block: B:133:0x03ad  */
    /* JADX WARN: Code duplicated, block: B:136:0x03d7  */
    /* JADX WARN: Code duplicated, block: B:137:0x03e1  */
    /* JADX WARN: Code duplicated, block: B:140:0x0456  */
    /* JADX WARN: Code duplicated, block: B:143:0x0462  */
    /* JADX WARN: Code duplicated, block: B:144:0x0468  */
    /* JADX WARN: Code duplicated, block: B:147:0x0499  */
    /* JADX WARN: Code duplicated, block: B:151:0x04af A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:155:0x0532  */
    /* JADX WARN: Code duplicated, block: B:159:0x0543 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:163:0x06c9  */
    /* JADX WARN: Code duplicated, block: B:164:0x06d3  */
    /* JADX WARN: Code duplicated, block: B:167:0x074f  */
    /* JADX WARN: Code duplicated, block: B:173:0x075c  */
    /* JADX WARN: Code duplicated, block: B:177:0x0766  */
    /* JADX WARN: Code duplicated, block: B:180:0x0771  */
    /* JADX WARN: Code duplicated, block: B:184:0x077e  */
    /* JADX WARN: Code duplicated, block: B:187:0x079f  */
    /* JADX WARN: Code duplicated, block: B:191:0x07aa  */
    /* JADX WARN: Code duplicated, block: B:193:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x0115 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:95:0x0117  */
    /* JADX WARN: Code duplicated, block: B:96:0x0120  */
    /* JADX WARN: Code duplicated, block: B:98:0x0124  */
    /* JADX WARN: Code duplicated, block: B:99:0x012d  */
    /* JADX INFO: renamed from: TimePickerTextField-1vLObsk, reason: not valid java name */
    public static final void m3095TimePickerTextField1vLObsk(final Modifier modifier, final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, final TimePickerState state, final int selection, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, final TimePickerColors colors, Composer $composer, final int $changed, final int i) {
        KeyboardOptions keyboardOptions2;
        KeyboardActions keyboardActions2;
        int i2;
        int $dirty;
        KeyboardOptions keyboardOptions3;
        KeyboardActions keyboardActions3;
        Object it$iv;
        Object value$iv;
        Object it$iv2;
        Object value$iv2;
        FocusRequester focusRequester;
        boolean selected;
        MeasurePolicy measurePolicy$iv;
        int compositeKeyHash$iv$iv;
        CompositionLocalMap localMap$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        CompositionLocalMap localMap$iv$iv2;
        MeasurePolicy measurePolicy$iv2;
        FocusRequester focusRequester2;
        Composer $composer2;
        Composer $composer$iv;
        int i3;
        int iM3279constructorimpl;
        Composer $composer3;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function2;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        FocusRequester focusRequester3;
        boolean invalid$iv;
        Object value$iv3;
        int iM3279constructorimpl2;
        Composer $composer4;
        boolean z;
        boolean invalid$iv2;
        Object value$iv4;
        int minute;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer $composer5 = $composer.startRestartGroup(1299172990);
        ComposerKt.sourceInformation($composer5, "C(TimePickerTextField)P(3,7,4,6,5:c#material3.TimePickerSelectionMode,2,1)1762@68219L39,1763@68284L29,1765@68374L243,1771@68670L3410,1857@72118L103,1857@72086L135:TimePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer5.changed(modifier) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer5.changed(value) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer5.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= ($changed & 4096) == 0 ? $composer5.changed(state) : $composer5.changedInstance(state) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer5.changed(selection) ? 16384 : 8192;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            keyboardOptions2 = keyboardOptions;
        } else if ((196608 & $changed) == 0) {
            keyboardOptions2 = keyboardOptions;
            $dirty2 |= $composer5.changed(keyboardOptions2) ? 131072 : 65536;
        } else {
            keyboardOptions2 = keyboardOptions;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty2 |= 1572864;
            keyboardActions2 = keyboardActions;
        } else if ((1572864 & $changed) == 0) {
            keyboardActions2 = keyboardActions;
            $dirty2 |= $composer5.changed(keyboardActions2) ? 1048576 : 524288;
        } else {
            keyboardActions2 = keyboardActions;
        }
        if ((i & 128) == 0) {
            if ((12582912 & $changed) == 0) {
                i2 = $composer5.changed(colors) ? 8388608 : 4194304;
            }
            $dirty = $dirty2;
            if ((4793491 & $dirty) == 4793490 || !$composer5.getSkipping()) {
                if (i4 != 0) {
                    keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
                } else {
                    keyboardOptions3 = keyboardOptions2;
                }
                if (i5 != 0) {
                    keyboardActions3 = KeyboardActions.INSTANCE.getDefault();
                } else {
                    keyboardActions3 = keyboardActions2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1299172990, $dirty, -1, "androidx.compose.material3.TimePickerTextField (TimePicker.kt:1761)");
                }
                ComposerKt.sourceInformationMarkerStart($composer5, 882970966, "CC(remember):TimePicker.kt#9igjgp");
                it$iv = $composer5.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer5.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                final MutableInteractionSource interactionSource = (MutableInteractionSource) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer5);
                ComposerKt.sourceInformationMarkerStart($composer5, 882973036, "CC(remember):TimePicker.kt#9igjgp");
                it$iv2 = $composer5.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new FocusRequester();
                    $composer5.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv2;
                }
                focusRequester = (FocusRequester) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer5);
                final TextFieldColors textFieldColors = OutlinedTextFieldDefaults.INSTANCE.m2717colors0hiis_0(colors.m3090timeSelectorContentColorvNxB06k$material3_release(true), 0L, 0L, 0L, colors.m3089timeSelectorContainerColorvNxB06k$material3_release(true), colors.m3089timeSelectorContainerColorvNxB06k$material3_release(true), 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer5, 0, 0, 0, 0, 3072, 2147483598, 4095);
                selected = TimePickerSelectionMode.m3121equalsimpl0(selection, state.mo2133getSelectionyecRtBI());
                int $changed$iv = $dirty & 14;
                ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                int $changed$iv$iv = ($changed$iv << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer5, modifier);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer5.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer5.startReusableNode();
                if ($composer5.getInserting()) {
                    function0 = constructor;
                    $composer5.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer5.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer5);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    localMap$iv$iv2 = localMap$iv$iv;
                    measurePolicy$iv2 = measurePolicy$iv;
                } else {
                    localMap$iv$iv2 = localMap$iv$iv;
                    measurePolicy$iv2 = measurePolicy$iv;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i6 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i7 = (($changed$iv >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer5, -2071429233, "C1788@69244L240,1796@69494L2047,1845@71677L244,1852@71988L5,1853@72059L5,1842@71551L523:TimePicker.kt#uh7d8r");
                    $composer5.startReplaceGroup(2011386807);
                    ComposerKt.sourceInformation($composer5, "1773@68737L450");
                    if (selected) {
                        focusRequester2 = focusRequester;
                        $composer2 = $composer5;
                        $composer$iv = $composer5;
                        i3 = 0;
                    } else {
                        Modifier modifierM1081sizeVpY3zN4 = SizeKt.m1081sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m3911getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m3910getTimeFieldContainerHeightD9Ej5fM());
                        if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                            minute = getHourForDisplay(state);
                        } else {
                            minute = state.getMinute();
                        }
                        i3 = 0;
                        focusRequester2 = focusRequester;
                        $composer2 = $composer5;
                        $composer$iv = $composer5;
                        m3096TimeSelectorSAnMeKU(modifierM1081sizeVpY3zN4, minute, state, selection, colors, $composer2, (($dirty >> 3) & 896) | 6 | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344));
                    }
                    $composer2.endReplaceGroup();
                    if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI())) {
                        Strings.Companion companion = Strings.INSTANCE;
                        iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute_text_field);
                    } else {
                        Strings.Companion companion2 = Strings.INSTANCE;
                        iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour_text_field);
                    }
                    $composer3 = $composer2;
                    final String contentDescription = Strings_androidKt.m3349getString2EP1pXo(iM3279constructorimpl, $composer3, i3);
                    Modifier modifier$iv = visible(Modifier.INSTANCE, selected);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv2 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function2 = constructor2;
                        $composer3.createNode(function2);
                    } else {
                        function2 = constructor2;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer3);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i8 = ($changed$iv$iv$iv2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i9 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1024044049, "C1803@69837L48,1807@70087L7,1814@70388L11,1815@70456L11,1819@70616L915,1797@69540L1991:TimePicker.kt#uh7d8r");
                    focusRequester3 = focusRequester2;
                    Modifier modifierM1081sizeVpY3zN5 = SizeKt.m1081sizeVpY3zN4(FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester3), TimeInputTokens.INSTANCE.m3911getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m3910getTimeFieldContainerHeightD9Ej5fM());
                    ComposerKt.sourceInformationMarkerStart($composer3, -1002857443, "CC(remember):TimePicker.kt#9igjgp");
                    invalid$iv = $composer3.changed(contentDescription);
                    Object it$iv3 = $composer3.rememberedValue();
                    if (!invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                        value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                SemanticsPropertiesKt.setContentDescription($this$semantics, contentDescription);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = it$iv3;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierM1081sizeVpY3zN5, false, (Function1) value$iv3, 1, null);
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    BasicTextFieldKt.BasicTextField(value, function1, modifierSemantics$default, true, false, (TextStyle) objConsume, keyboardOptions3, keyboardActions3, true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource, Brush.Companion.m4492verticalGradient8A3gB4$default(Brush.INSTANCE, new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m4524boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getPrimary())), TuplesKt.to(Float.valueOf(0.9f), Color.m4524boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getPrimary())), TuplesKt.to(Float.valueOf(0.9f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU()))}, 0.0f, 0.0f, 0, 14, (Object) null), (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(825138052, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function3, Composer composer, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function3, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer6, int $changed2) {
                            ComposerKt.sourceInformation($composer6, "C1829@71096L403,1820@70660L857:TimePicker.kt#uh7d8r");
                            int $dirty3 = $changed2;
                            if (($changed2 & 6) == 0) {
                                $dirty3 |= $composer6.changedInstance(function3) ? 4 : 2;
                            }
                            int $dirty4 = $dirty3;
                            if (($dirty4 & 19) == 18 && $composer6.getSkipping()) {
                                $composer6.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(825138052, $dirty4, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1820)");
                            }
                            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                            String text = value.getText();
                            VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                            PaddingValues paddingValuesM1027PaddingValues0680j_4 = PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0));
                            MutableInteractionSource mutableInteractionSource = interactionSource;
                            TextFieldColors textFieldColors2 = textFieldColors;
                            final MutableInteractionSource mutableInteractionSource2 = interactionSource;
                            final TextFieldColors textFieldColors3 = textFieldColors;
                            outlinedTextFieldDefaults.DecorationBox(text, function3, true, true, none, mutableInteractionSource, false, null, null, null, null, null, null, null, textFieldColors2, paddingValuesM1027PaddingValues0680j_4, ComposableLambdaKt.rememberComposableLambda(-833003881, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                    invoke(composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer $composer7, int $changed3) {
                                    ComposerKt.sourceInformation($composer7, "C1834@71391L5,1830@71148L329:TimePicker.kt#uh7d8r");
                                    if (($changed3 & 3) == 2 && $composer7.getSkipping()) {
                                        $composer7.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-833003881, $changed3, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1830)");
                                    }
                                    OutlinedTextFieldDefaults.INSTANCE.m2715Container4EFweAY(true, false, mutableInteractionSource2, null, textFieldColors3, ShapesKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldContainerShape(), $composer7, 6), 0.0f, 0.0f, $composer7, 100663734, 200);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, $composer6, 54), $composer6, (($dirty4 << 3) & 112) | 224640, 14352384, 16320);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54), $composer3, (($dirty >> 3) & 14) | 100666368 | (($dirty >> 3) & 112) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128), 199680, 7696);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(androidx.compose.foundation.layout.OffsetKt.m995offsetVpY3zN4$default(Modifier.INSTANCE, 0.0f, SupportLabelTop, 1, null), new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$2
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
                        }
                    });
                    if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                        Strings.Companion companion3 = Strings.INSTANCE;
                        iM3279constructorimpl2 = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour);
                    } else {
                        Strings.Companion companion4 = Strings.INSTANCE;
                        iM3279constructorimpl2 = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute);
                    }
                    TextKt.m3067Text4IGK_g(Strings_androidKt.m3349getString2EP1pXo(iM3279constructorimpl2, $composer3, 0), modifierClearAndSetSemantics, ColorSchemeKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextColor(), $composer3, 6), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TypographyKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextFont(), $composer3, 6), $composer3, 0, 0, 65528);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    $composer5.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    TimePickerSelectionMode timePickerSelectionModeM3118boximpl = TimePickerSelectionMode.m3118boximpl(state.mo2133getSelectionyecRtBI());
                    $composer4 = $composer5;
                    ComposerKt.sourceInformationMarkerStart($composer4, 883095798, "CC(remember):TimePicker.kt#9igjgp");
                    if (($dirty & 7168) != 2048 || (($dirty & 4096) != 0 && $composer4.changedInstance(state))) {
                        z = true;
                    } else {
                        z = false;
                    }
                    invalid$iv2 = z | ((57344 & $dirty) == 16384);
                    Object it$iv4 = $composer4.rememberedValue();
                    if (!invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                        value$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester3, null);
                        $composer4.updateRememberedValue(value$iv4);
                    } else {
                        value$iv4 = it$iv4;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    EffectsKt.LaunchedEffect(timePickerSelectionModeM3118boximpl, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv4, $composer4, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    keyboardActions2 = keyboardActions3;
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i10 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i11 = (($changed$iv >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer5, -2071429233, "C1788@69244L240,1796@69494L2047,1845@71677L244,1852@71988L5,1853@72059L5,1842@71551L523:TimePicker.kt#uh7d8r");
                $composer5.startReplaceGroup(2011386807);
                ComposerKt.sourceInformation($composer5, "1773@68737L450");
                if (selected) {
                    Modifier modifierM1081sizeVpY3zN6 = SizeKt.m1081sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m3911getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m3910getTimeFieldContainerHeightD9Ej5fM());
                    if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                        minute = getHourForDisplay(state);
                    } else {
                        minute = state.getMinute();
                    }
                    i3 = 0;
                    focusRequester2 = focusRequester;
                    $composer2 = $composer5;
                    $composer$iv = $composer5;
                    m3096TimeSelectorSAnMeKU(modifierM1081sizeVpY3zN6, minute, state, selection, colors, $composer2, (($dirty >> 3) & 896) | 6 | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344));
                } else {
                    focusRequester2 = focusRequester;
                    $composer2 = $composer5;
                    $composer$iv = $composer5;
                    i3 = 0;
                }
                $composer2.endReplaceGroup();
                if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI())) {
                    Strings.Companion companion5 = Strings.INSTANCE;
                    iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute_text_field);
                } else {
                    Strings.Companion companion6 = Strings.INSTANCE;
                    iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour_text_field);
                }
                $composer3 = $composer2;
                final String contentDescription2 = Strings_androidKt.m3349getString2EP1pXo(iM3279constructorimpl, $composer3, i3);
                Modifier modifier$iv2 = visible(Modifier.INSTANCE, selected);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                int $changed$iv$iv3 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor2;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor2;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer3);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i12 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i13 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1024044049, "C1803@69837L48,1807@70087L7,1814@70388L11,1815@70456L11,1819@70616L915,1797@69540L1991:TimePicker.kt#uh7d8r");
                focusRequester3 = focusRequester2;
                Modifier modifierM1081sizeVpY3zN7 = SizeKt.m1081sizeVpY3zN4(FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester3), TimeInputTokens.INSTANCE.m3911getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m3910getTimeFieldContainerHeightD9Ej5fM());
                ComposerKt.sourceInformationMarkerStart($composer3, -1002857443, "CC(remember):TimePicker.kt#9igjgp");
                invalid$iv = $composer3.changed(contentDescription2);
                Object it$iv5 = $composer3.rememberedValue();
                if (invalid$iv) {
                }
                value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setContentDescription($this$semantics, contentDescription2);
                    }
                };
                $composer3.updateRememberedValue(value$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierSemantics$default2 = SemanticsModifierKt.semantics$default(modifierM1081sizeVpY3zN7, false, (Function1) value$iv3, 1, null);
                ProvidableCompositionLocal<TextStyle> localTextStyle2 = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer3.consume(localTextStyle2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                BasicTextFieldKt.BasicTextField(value, function1, modifierSemantics$default2, true, false, (TextStyle) objConsume2, keyboardOptions3, keyboardActions3, true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource, Brush.Companion.m4492verticalGradient8A3gB4$default(Brush.INSTANCE, new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m4524boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getPrimary())), TuplesKt.to(Float.valueOf(0.9f), Color.m4524boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getPrimary())), TuplesKt.to(Float.valueOf(0.9f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU()))}, 0.0f, 0.0f, 0, 14, (Object) null), (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(825138052, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function3, Composer composer, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function3, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer6, int $changed2) {
                        ComposerKt.sourceInformation($composer6, "C1829@71096L403,1820@70660L857:TimePicker.kt#uh7d8r");
                        int $dirty3 = $changed2;
                        if (($changed2 & 6) == 0) {
                            $dirty3 |= $composer6.changedInstance(function3) ? 4 : 2;
                        }
                        int $dirty4 = $dirty3;
                        if (($dirty4 & 19) == 18 && $composer6.getSkipping()) {
                            $composer6.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(825138052, $dirty4, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1820)");
                        }
                        OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                        String text = value.getText();
                        VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                        PaddingValues paddingValuesM1027PaddingValues0680j_4 = PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0));
                        MutableInteractionSource mutableInteractionSource = interactionSource;
                        TextFieldColors textFieldColors2 = textFieldColors;
                        final MutableInteractionSource mutableInteractionSource2 = interactionSource;
                        final TextFieldColors textFieldColors3 = textFieldColors;
                        outlinedTextFieldDefaults.DecorationBox(text, function3, true, true, none, mutableInteractionSource, false, null, null, null, null, null, null, null, textFieldColors2, paddingValuesM1027PaddingValues0680j_4, ComposableLambdaKt.rememberComposableLambda(-833003881, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer7, int $changed3) {
                                ComposerKt.sourceInformation($composer7, "C1834@71391L5,1830@71148L329:TimePicker.kt#uh7d8r");
                                if (($changed3 & 3) == 2 && $composer7.getSkipping()) {
                                    $composer7.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-833003881, $changed3, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1830)");
                                }
                                OutlinedTextFieldDefaults.INSTANCE.m2715Container4EFweAY(true, false, mutableInteractionSource2, null, textFieldColors3, ShapesKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldContainerShape(), $composer7, 6), 0.0f, 0.0f, $composer7, 100663734, 200);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer6, 54), $composer6, (($dirty4 << 3) & 112) | 224640, 14352384, 16320);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54), $composer3, (($dirty >> 3) & 14) | 100666368 | (($dirty >> 3) & 112) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128), 199680, 7696);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierClearAndSetSemantics2 = SemanticsModifierKt.clearAndSetSemantics(androidx.compose.foundation.layout.OffsetKt.m995offsetVpY3zN4$default(Modifier.INSTANCE, 0.0f, SupportLabelTop, 1, null), new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
                    }
                });
                if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                    Strings.Companion companion7 = Strings.INSTANCE;
                    iM3279constructorimpl2 = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour);
                } else {
                    Strings.Companion companion8 = Strings.INSTANCE;
                    iM3279constructorimpl2 = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute);
                }
                TextKt.m3067Text4IGK_g(Strings_androidKt.m3349getString2EP1pXo(iM3279constructorimpl2, $composer3, 0), modifierClearAndSetSemantics2, ColorSchemeKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextColor(), $composer3, 6), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TypographyKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextFont(), $composer3, 6), $composer3, 0, 0, 65528);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer5.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer5);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                TimePickerSelectionMode timePickerSelectionModeM3118boximpl2 = TimePickerSelectionMode.m3118boximpl(state.mo2133getSelectionyecRtBI());
                $composer4 = $composer5;
                ComposerKt.sourceInformationMarkerStart($composer4, 883095798, "CC(remember):TimePicker.kt#9igjgp");
                if (($dirty & 7168) != 2048) {
                    z = true;
                } else {
                    z = true;
                }
                invalid$iv2 = z | ((57344 & $dirty) == 16384);
                Object it$iv6 = $composer4.rememberedValue();
                if (invalid$iv2) {
                    value$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester3, null);
                    $composer4.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester3, null);
                    $composer4.updateRememberedValue(value$iv4);
                }
                ComposerKt.sourceInformationMarkerEnd($composer4);
                EffectsKt.LaunchedEffect(timePickerSelectionModeM3118boximpl2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv4, $composer4, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                keyboardActions2 = keyboardActions3;
            } else {
                $composer5.skipToGroupEnd();
                keyboardOptions3 = keyboardOptions2;
                $composer4 = $composer5;
            }
            scopeUpdateScopeEndRestartGroup = $composer4.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final KeyboardOptions keyboardOptions4 = keyboardOptions3;
                final KeyboardActions keyboardActions4 = keyboardActions2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer, int i14) {
                        TimePickerKt.m3095TimePickerTextField1vLObsk(modifier, value, function1, state, selection, keyboardOptions4, keyboardActions4, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                    }
                });
            }
        }
        i2 = 12582912;
        $dirty2 |= i2;
        $dirty = $dirty2;
        if ((4793491 & $dirty) == 4793490) {
            if (i4 != 0) {
                keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
            } else {
                keyboardOptions3 = keyboardOptions2;
            }
            if (i5 != 0) {
                keyboardActions3 = KeyboardActions.INSTANCE.getDefault();
            } else {
                keyboardActions3 = keyboardActions2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1299172990, $dirty, -1, "androidx.compose.material3.TimePickerTextField (TimePicker.kt:1761)");
            }
            ComposerKt.sourceInformationMarkerStart($composer5, 882970966, "CC(remember):TimePicker.kt#9igjgp");
            it$iv = $composer5.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = InteractionSourceKt.MutableInteractionSource();
                $composer5.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableInteractionSource interactionSource2 = (MutableInteractionSource) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerStart($composer5, 882973036, "CC(remember):TimePicker.kt#9igjgp");
            it$iv2 = $composer5.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new FocusRequester();
                $composer5.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            focusRequester = (FocusRequester) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer5);
            final TextFieldColors textFieldColors2 = OutlinedTextFieldDefaults.INSTANCE.m2717colors0hiis_0(colors.m3090timeSelectorContentColorvNxB06k$material3_release(true), 0L, 0L, 0L, colors.m3089timeSelectorContainerColorvNxB06k$material3_release(true), colors.m3089timeSelectorContainerColorvNxB06k$material3_release(true), 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer5, 0, 0, 0, 0, 3072, 2147483598, 4095);
            selected = TimePickerSelectionMode.m3121equalsimpl0(selection, state.mo2133getSelectionyecRtBI());
            int $changed$iv2 = $dirty & 14;
            ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
            measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer5, (($changed$iv2 >> 3) & 14) | (($changed$iv2 >> 3) & 112));
            int $changed$iv$iv4 = ($changed$iv2 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
            localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer5, modifier);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer5.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer5.startReusableNode();
            if ($composer5.getInserting()) {
                function0 = constructor;
                $composer5.createNode(function0);
            } else {
                function0 = constructor;
                $composer5.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer5);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                localMap$iv$iv2 = localMap$iv$iv;
                measurePolicy$iv2 = measurePolicy$iv;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                int i14 = ($changed$iv$iv$iv4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                int i15 = (($changed$iv2 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer5, -2071429233, "C1788@69244L240,1796@69494L2047,1845@71677L244,1852@71988L5,1853@72059L5,1842@71551L523:TimePicker.kt#uh7d8r");
                $composer5.startReplaceGroup(2011386807);
                ComposerKt.sourceInformation($composer5, "1773@68737L450");
                if (selected) {
                    Modifier modifierM1081sizeVpY3zN8 = SizeKt.m1081sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m3911getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m3910getTimeFieldContainerHeightD9Ej5fM());
                    if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                        minute = getHourForDisplay(state);
                    } else {
                        minute = state.getMinute();
                    }
                    i3 = 0;
                    focusRequester2 = focusRequester;
                    $composer2 = $composer5;
                    $composer$iv = $composer5;
                    m3096TimeSelectorSAnMeKU(modifierM1081sizeVpY3zN8, minute, state, selection, colors, $composer2, (($dirty >> 3) & 896) | 6 | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344));
                } else {
                    focusRequester2 = focusRequester;
                    $composer2 = $composer5;
                    $composer$iv = $composer5;
                    i3 = 0;
                }
                $composer2.endReplaceGroup();
                if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI())) {
                    Strings.Companion companion9 = Strings.INSTANCE;
                    iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute_text_field);
                } else {
                    Strings.Companion companion10 = Strings.INSTANCE;
                    iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour_text_field);
                }
                $composer3 = $composer2;
                final String contentDescription3 = Strings_androidKt.m3349getString2EP1pXo(iM3279constructorimpl, $composer3, i3);
                Modifier modifier$iv3 = visible(Modifier.INSTANCE, selected);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                int $changed$iv$iv5 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv5 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor2;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor2;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer3);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash5);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                int i16 = ($changed$iv$iv$iv5 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i17 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1024044049, "C1803@69837L48,1807@70087L7,1814@70388L11,1815@70456L11,1819@70616L915,1797@69540L1991:TimePicker.kt#uh7d8r");
                focusRequester3 = focusRequester2;
                Modifier modifierM1081sizeVpY3zN9 = SizeKt.m1081sizeVpY3zN4(FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester3), TimeInputTokens.INSTANCE.m3911getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m3910getTimeFieldContainerHeightD9Ej5fM());
                ComposerKt.sourceInformationMarkerStart($composer3, -1002857443, "CC(remember):TimePicker.kt#9igjgp");
                invalid$iv = $composer3.changed(contentDescription3);
                Object it$iv7 = $composer3.rememberedValue();
                if (invalid$iv) {
                }
                value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setContentDescription($this$semantics, contentDescription3);
                    }
                };
                $composer3.updateRememberedValue(value$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierSemantics$default3 = SemanticsModifierKt.semantics$default(modifierM1081sizeVpY3zN9, false, (Function1) value$iv3, 1, null);
                ProvidableCompositionLocal<TextStyle> localTextStyle3 = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer3.consume(localTextStyle3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                BasicTextFieldKt.BasicTextField(value, function1, modifierSemantics$default3, true, false, (TextStyle) objConsume3, keyboardOptions3, keyboardActions3, true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource2, Brush.Companion.m4492verticalGradient8A3gB4$default(Brush.INSTANCE, new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m4524boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getPrimary())), TuplesKt.to(Float.valueOf(0.9f), Color.m4524boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getPrimary())), TuplesKt.to(Float.valueOf(0.9f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU()))}, 0.0f, 0.0f, 0, 14, (Object) null), (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(825138052, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function3, Composer composer, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function3, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer6, int $changed2) {
                        ComposerKt.sourceInformation($composer6, "C1829@71096L403,1820@70660L857:TimePicker.kt#uh7d8r");
                        int $dirty3 = $changed2;
                        if (($changed2 & 6) == 0) {
                            $dirty3 |= $composer6.changedInstance(function3) ? 4 : 2;
                        }
                        int $dirty4 = $dirty3;
                        if (($dirty4 & 19) == 18 && $composer6.getSkipping()) {
                            $composer6.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(825138052, $dirty4, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1820)");
                        }
                        OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                        String text = value.getText();
                        VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                        PaddingValues paddingValuesM1027PaddingValues0680j_4 = PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0));
                        MutableInteractionSource mutableInteractionSource = interactionSource2;
                        TextFieldColors textFieldColors3 = textFieldColors2;
                        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
                        final TextFieldColors textFieldColors4 = textFieldColors2;
                        outlinedTextFieldDefaults.DecorationBox(text, function3, true, true, none, mutableInteractionSource, false, null, null, null, null, null, null, null, textFieldColors3, paddingValuesM1027PaddingValues0680j_4, ComposableLambdaKt.rememberComposableLambda(-833003881, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer7, int $changed3) {
                                ComposerKt.sourceInformation($composer7, "C1834@71391L5,1830@71148L329:TimePicker.kt#uh7d8r");
                                if (($changed3 & 3) == 2 && $composer7.getSkipping()) {
                                    $composer7.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-833003881, $changed3, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1830)");
                                }
                                OutlinedTextFieldDefaults.INSTANCE.m2715Container4EFweAY(true, false, mutableInteractionSource2, null, textFieldColors4, ShapesKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldContainerShape(), $composer7, 6), 0.0f, 0.0f, $composer7, 100663734, 200);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer6, 54), $composer6, (($dirty4 << 3) & 112) | 224640, 14352384, 16320);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54), $composer3, (($dirty >> 3) & 14) | 100666368 | (($dirty >> 3) & 112) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128), 199680, 7696);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierClearAndSetSemantics3 = SemanticsModifierKt.clearAndSetSemantics(androidx.compose.foundation.layout.OffsetKt.m995offsetVpY3zN4$default(Modifier.INSTANCE, 0.0f, SupportLabelTop, 1, null), new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
                    }
                });
                if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                    Strings.Companion companion11 = Strings.INSTANCE;
                    iM3279constructorimpl2 = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour);
                } else {
                    Strings.Companion companion12 = Strings.INSTANCE;
                    iM3279constructorimpl2 = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute);
                }
                TextKt.m3067Text4IGK_g(Strings_androidKt.m3349getString2EP1pXo(iM3279constructorimpl2, $composer3, 0), modifierClearAndSetSemantics3, ColorSchemeKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextColor(), $composer3, 6), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TypographyKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextFont(), $composer3, 6), $composer3, 0, 0, 65528);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer5.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer5);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                TimePickerSelectionMode timePickerSelectionModeM3118boximpl3 = TimePickerSelectionMode.m3118boximpl(state.mo2133getSelectionyecRtBI());
                $composer4 = $composer5;
                ComposerKt.sourceInformationMarkerStart($composer4, 883095798, "CC(remember):TimePicker.kt#9igjgp");
                if (($dirty & 7168) != 2048) {
                    z = true;
                } else {
                    z = true;
                }
                invalid$iv2 = z | ((57344 & $dirty) == 16384);
                Object it$iv8 = $composer4.rememberedValue();
                if (invalid$iv2) {
                    value$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester3, null);
                    $composer4.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester3, null);
                    $composer4.updateRememberedValue(value$iv4);
                }
                ComposerKt.sourceInformationMarkerEnd($composer4);
                EffectsKt.LaunchedEffect(timePickerSelectionModeM3118boximpl3, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv4, $composer4, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                keyboardActions2 = keyboardActions3;
            } else {
                localMap$iv$iv2 = localMap$iv$iv;
                measurePolicy$iv2 = measurePolicy$iv;
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash4);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
            int i18 = ($changed$iv$iv$iv4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
            int i19 = (($changed$iv2 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer5, -2071429233, "C1788@69244L240,1796@69494L2047,1845@71677L244,1852@71988L5,1853@72059L5,1842@71551L523:TimePicker.kt#uh7d8r");
            $composer5.startReplaceGroup(2011386807);
            ComposerKt.sourceInformation($composer5, "1773@68737L450");
            if (selected) {
                Modifier modifierM1081sizeVpY3zN10 = SizeKt.m1081sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m3911getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m3910getTimeFieldContainerHeightD9Ej5fM());
                if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                    minute = getHourForDisplay(state);
                } else {
                    minute = state.getMinute();
                }
                i3 = 0;
                focusRequester2 = focusRequester;
                $composer2 = $composer5;
                $composer$iv = $composer5;
                m3096TimeSelectorSAnMeKU(modifierM1081sizeVpY3zN10, minute, state, selection, colors, $composer2, (($dirty >> 3) & 896) | 6 | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344));
            } else {
                focusRequester2 = focusRequester;
                $composer2 = $composer5;
                $composer$iv = $composer5;
                i3 = 0;
            }
            $composer2.endReplaceGroup();
            if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI())) {
                Strings.Companion companion13 = Strings.INSTANCE;
                iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute_text_field);
            } else {
                Strings.Companion companion14 = Strings.INSTANCE;
                iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour_text_field);
            }
            $composer3 = $composer2;
            final String contentDescription4 = Strings_androidKt.m3349getString2EP1pXo(iM3279constructorimpl, $composer3, i3);
            Modifier modifier$iv4 = visible(Modifier.INSTANCE, selected);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv6 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
            int $changed$iv$iv6 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv6 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer3, modifier$iv4);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv6 = (($changed$iv$iv6 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function2 = constructor2;
                $composer3.createNode(function2);
            } else {
                function2 = constructor2;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer3);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash6);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv6, ComposeUiNode.INSTANCE.getSetModifier());
            int i110 = ($changed$iv$iv$iv6 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            int i111 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1024044049, "C1803@69837L48,1807@70087L7,1814@70388L11,1815@70456L11,1819@70616L915,1797@69540L1991:TimePicker.kt#uh7d8r");
            focusRequester3 = focusRequester2;
            Modifier modifierM1081sizeVpY3zN11 = SizeKt.m1081sizeVpY3zN4(FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester3), TimeInputTokens.INSTANCE.m3911getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m3910getTimeFieldContainerHeightD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart($composer3, -1002857443, "CC(remember):TimePicker.kt#9igjgp");
            invalid$iv = $composer3.changed(contentDescription4);
            Object it$iv9 = $composer3.rememberedValue();
            if (invalid$iv) {
            }
            value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.setContentDescription($this$semantics, contentDescription4);
                }
            };
            $composer3.updateRememberedValue(value$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics$default4 = SemanticsModifierKt.semantics$default(modifierM1081sizeVpY3zN11, false, (Function1) value$iv3, 1, null);
            ProvidableCompositionLocal<TextStyle> localTextStyle4 = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer3.consume(localTextStyle4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BasicTextFieldKt.BasicTextField(value, function1, modifierSemantics$default4, true, false, (TextStyle) objConsume4, keyboardOptions3, keyboardActions3, true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource2, Brush.Companion.m4492verticalGradient8A3gB4$default(Brush.INSTANCE, new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m4524boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getPrimary())), TuplesKt.to(Float.valueOf(0.9f), Color.m4524boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getPrimary())), TuplesKt.to(Float.valueOf(0.9f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU()))}, 0.0f, 0.0f, 0, 14, (Object) null), (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(825138052, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function3, Composer composer, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function3, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer6, int $changed2) {
                    ComposerKt.sourceInformation($composer6, "C1829@71096L403,1820@70660L857:TimePicker.kt#uh7d8r");
                    int $dirty3 = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty3 |= $composer6.changedInstance(function3) ? 4 : 2;
                    }
                    int $dirty4 = $dirty3;
                    if (($dirty4 & 19) == 18 && $composer6.getSkipping()) {
                        $composer6.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(825138052, $dirty4, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1820)");
                    }
                    OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                    String text = value.getText();
                    VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                    PaddingValues paddingValuesM1027PaddingValues0680j_4 = PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0));
                    MutableInteractionSource mutableInteractionSource = interactionSource2;
                    TextFieldColors textFieldColors3 = textFieldColors2;
                    final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
                    final TextFieldColors textFieldColors4 = textFieldColors2;
                    outlinedTextFieldDefaults.DecorationBox(text, function3, true, true, none, mutableInteractionSource, false, null, null, null, null, null, null, null, textFieldColors3, paddingValuesM1027PaddingValues0680j_4, ComposableLambdaKt.rememberComposableLambda(-833003881, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer7, int $changed3) {
                            ComposerKt.sourceInformation($composer7, "C1834@71391L5,1830@71148L329:TimePicker.kt#uh7d8r");
                            if (($changed3 & 3) == 2 && $composer7.getSkipping()) {
                                $composer7.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-833003881, $changed3, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1830)");
                            }
                            OutlinedTextFieldDefaults.INSTANCE.m2715Container4EFweAY(true, false, mutableInteractionSource2, null, textFieldColors4, ShapesKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldContainerShape(), $composer7, 6), 0.0f, 0.0f, $composer7, 100663734, 200);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer6, 54), $composer6, (($dirty4 << 3) & 112) | 224640, 14352384, 16320);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer3, (($dirty >> 3) & 14) | 100666368 | (($dirty >> 3) & 112) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128), 199680, 7696);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierClearAndSetSemantics4 = SemanticsModifierKt.clearAndSetSemantics(androidx.compose.foundation.layout.OffsetKt.m995offsetVpY3zN4$default(Modifier.INSTANCE, 0.0f, SupportLabelTop, 1, null), new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
                }
            });
            if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                Strings.Companion companion15 = Strings.INSTANCE;
                iM3279constructorimpl2 = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour);
            } else {
                Strings.Companion companion16 = Strings.INSTANCE;
                iM3279constructorimpl2 = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute);
            }
            TextKt.m3067Text4IGK_g(Strings_androidKt.m3349getString2EP1pXo(iM3279constructorimpl2, $composer3, 0), modifierClearAndSetSemantics4, ColorSchemeKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextColor(), $composer3, 6), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TypographyKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextFont(), $composer3, 6), $composer3, 0, 0, 65528);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            $composer5.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            TimePickerSelectionMode timePickerSelectionModeM3118boximpl4 = TimePickerSelectionMode.m3118boximpl(state.mo2133getSelectionyecRtBI());
            $composer4 = $composer5;
            ComposerKt.sourceInformationMarkerStart($composer4, 883095798, "CC(remember):TimePicker.kt#9igjgp");
            if (($dirty & 7168) != 2048) {
                z = true;
            } else {
                z = true;
            }
            invalid$iv2 = z | ((57344 & $dirty) == 16384);
            Object it$iv10 = $composer4.rememberedValue();
            if (invalid$iv2) {
                value$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester3, null);
                $composer4.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester3, null);
                $composer4.updateRememberedValue(value$iv4);
            }
            ComposerKt.sourceInformationMarkerEnd($composer4);
            EffectsKt.LaunchedEffect(timePickerSelectionModeM3118boximpl4, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv4, $composer4, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            keyboardActions2 = keyboardActions3;
        } else {
            if (i4 != 0) {
                keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
            } else {
                keyboardOptions3 = keyboardOptions2;
            }
            if (i5 != 0) {
                keyboardActions3 = KeyboardActions.INSTANCE.getDefault();
            } else {
                keyboardActions3 = keyboardActions2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1299172990, $dirty, -1, "androidx.compose.material3.TimePickerTextField (TimePicker.kt:1761)");
            }
            ComposerKt.sourceInformationMarkerStart($composer5, 882970966, "CC(remember):TimePicker.kt#9igjgp");
            it$iv = $composer5.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = InteractionSourceKt.MutableInteractionSource();
                $composer5.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableInteractionSource interactionSource3 = (MutableInteractionSource) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerStart($composer5, 882973036, "CC(remember):TimePicker.kt#9igjgp");
            it$iv2 = $composer5.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new FocusRequester();
                $composer5.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            focusRequester = (FocusRequester) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer5);
            final TextFieldColors textFieldColors3 = OutlinedTextFieldDefaults.INSTANCE.m2717colors0hiis_0(colors.m3090timeSelectorContentColorvNxB06k$material3_release(true), 0L, 0L, 0L, colors.m3089timeSelectorContainerColorvNxB06k$material3_release(true), colors.m3089timeSelectorContainerColorvNxB06k$material3_release(true), 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer5, 0, 0, 0, 0, 3072, 2147483598, 4095);
            selected = TimePickerSelectionMode.m3121equalsimpl0(selection, state.mo2133getSelectionyecRtBI());
            int $changed$iv3 = $dirty & 14;
            ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv3 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv3 = Alignment.INSTANCE.getStart();
            measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv3, horizontalAlignment$iv3, $composer5, (($changed$iv3 >> 3) & 14) | (($changed$iv3 >> 3) & 112));
            int $changed$iv$iv7 = ($changed$iv3 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
            localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv7 = ComposedModifierKt.materializeModifier($composer5, modifier);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv7 = (($changed$iv$iv7 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer5.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer5.startReusableNode();
            if ($composer5.getInserting()) {
                function0 = constructor;
                $composer5.createNode(function0);
            } else {
                function0 = constructor;
                $composer5.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer5);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                localMap$iv$iv2 = localMap$iv$iv;
                measurePolicy$iv2 = measurePolicy$iv;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv7, ComposeUiNode.INSTANCE.getSetModifier());
                int i112 = ($changed$iv$iv$iv7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                int i113 = (($changed$iv3 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer5, -2071429233, "C1788@69244L240,1796@69494L2047,1845@71677L244,1852@71988L5,1853@72059L5,1842@71551L523:TimePicker.kt#uh7d8r");
                $composer5.startReplaceGroup(2011386807);
                ComposerKt.sourceInformation($composer5, "1773@68737L450");
                if (selected) {
                    Modifier modifierM1081sizeVpY3zN12 = SizeKt.m1081sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m3911getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m3910getTimeFieldContainerHeightD9Ej5fM());
                    if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                        minute = getHourForDisplay(state);
                    } else {
                        minute = state.getMinute();
                    }
                    i3 = 0;
                    focusRequester2 = focusRequester;
                    $composer2 = $composer5;
                    $composer$iv = $composer5;
                    m3096TimeSelectorSAnMeKU(modifierM1081sizeVpY3zN12, minute, state, selection, colors, $composer2, (($dirty >> 3) & 896) | 6 | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344));
                } else {
                    focusRequester2 = focusRequester;
                    $composer2 = $composer5;
                    $composer$iv = $composer5;
                    i3 = 0;
                }
                $composer2.endReplaceGroup();
                if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI())) {
                    Strings.Companion companion17 = Strings.INSTANCE;
                    iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute_text_field);
                } else {
                    Strings.Companion companion18 = Strings.INSTANCE;
                    iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour_text_field);
                }
                $composer3 = $composer2;
                final String contentDescription5 = Strings_androidKt.m3349getString2EP1pXo(iM3279constructorimpl, $composer3, i3);
                Modifier modifier$iv5 = visible(Modifier.INSTANCE, selected);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv5 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv7 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv5, false);
                int $changed$iv$iv8 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv7 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv8 = ComposedModifierKt.materializeModifier($composer3, modifier$iv5);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv8 = (($changed$iv$iv8 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor2;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor2;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer3);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash8);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv8, ComposeUiNode.INSTANCE.getSetModifier());
                int i114 = ($changed$iv$iv$iv8 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                int i115 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1024044049, "C1803@69837L48,1807@70087L7,1814@70388L11,1815@70456L11,1819@70616L915,1797@69540L1991:TimePicker.kt#uh7d8r");
                focusRequester3 = focusRequester2;
                Modifier modifierM1081sizeVpY3zN13 = SizeKt.m1081sizeVpY3zN4(FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester3), TimeInputTokens.INSTANCE.m3911getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m3910getTimeFieldContainerHeightD9Ej5fM());
                ComposerKt.sourceInformationMarkerStart($composer3, -1002857443, "CC(remember):TimePicker.kt#9igjgp");
                invalid$iv = $composer3.changed(contentDescription5);
                Object it$iv11 = $composer3.rememberedValue();
                if (invalid$iv) {
                }
                value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setContentDescription($this$semantics, contentDescription5);
                    }
                };
                $composer3.updateRememberedValue(value$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierSemantics$default5 = SemanticsModifierKt.semantics$default(modifierM1081sizeVpY3zN13, false, (Function1) value$iv3, 1, null);
                ProvidableCompositionLocal<TextStyle> localTextStyle5 = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume5 = $composer3.consume(localTextStyle5);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                BasicTextFieldKt.BasicTextField(value, function1, modifierSemantics$default5, true, false, (TextStyle) objConsume5, keyboardOptions3, keyboardActions3, true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource3, Brush.Companion.m4492verticalGradient8A3gB4$default(Brush.INSTANCE, new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m4524boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getPrimary())), TuplesKt.to(Float.valueOf(0.9f), Color.m4524boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getPrimary())), TuplesKt.to(Float.valueOf(0.9f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU()))}, 0.0f, 0.0f, 0, 14, (Object) null), (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(825138052, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function3, Composer composer, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function3, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer6, int $changed2) {
                        ComposerKt.sourceInformation($composer6, "C1829@71096L403,1820@70660L857:TimePicker.kt#uh7d8r");
                        int $dirty3 = $changed2;
                        if (($changed2 & 6) == 0) {
                            $dirty3 |= $composer6.changedInstance(function3) ? 4 : 2;
                        }
                        int $dirty4 = $dirty3;
                        if (($dirty4 & 19) == 18 && $composer6.getSkipping()) {
                            $composer6.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(825138052, $dirty4, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1820)");
                        }
                        OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                        String text = value.getText();
                        VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                        PaddingValues paddingValuesM1027PaddingValues0680j_4 = PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0));
                        MutableInteractionSource mutableInteractionSource = interactionSource3;
                        TextFieldColors textFieldColors4 = textFieldColors3;
                        final MutableInteractionSource mutableInteractionSource2 = interactionSource3;
                        final TextFieldColors textFieldColors5 = textFieldColors3;
                        outlinedTextFieldDefaults.DecorationBox(text, function3, true, true, none, mutableInteractionSource, false, null, null, null, null, null, null, null, textFieldColors4, paddingValuesM1027PaddingValues0680j_4, ComposableLambdaKt.rememberComposableLambda(-833003881, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer7, int $changed3) {
                                ComposerKt.sourceInformation($composer7, "C1834@71391L5,1830@71148L329:TimePicker.kt#uh7d8r");
                                if (($changed3 & 3) == 2 && $composer7.getSkipping()) {
                                    $composer7.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-833003881, $changed3, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1830)");
                                }
                                OutlinedTextFieldDefaults.INSTANCE.m2715Container4EFweAY(true, false, mutableInteractionSource2, null, textFieldColors5, ShapesKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldContainerShape(), $composer7, 6), 0.0f, 0.0f, $composer7, 100663734, 200);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer6, 54), $composer6, (($dirty4 << 3) & 112) | 224640, 14352384, 16320);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54), $composer3, (($dirty >> 3) & 14) | 100666368 | (($dirty >> 3) & 112) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128), 199680, 7696);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierClearAndSetSemantics5 = SemanticsModifierKt.clearAndSetSemantics(androidx.compose.foundation.layout.OffsetKt.m995offsetVpY3zN4$default(Modifier.INSTANCE, 0.0f, SupportLabelTop, 1, null), new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
                    }
                });
                if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                    Strings.Companion companion19 = Strings.INSTANCE;
                    iM3279constructorimpl2 = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour);
                } else {
                    Strings.Companion companion110 = Strings.INSTANCE;
                    iM3279constructorimpl2 = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute);
                }
                TextKt.m3067Text4IGK_g(Strings_androidKt.m3349getString2EP1pXo(iM3279constructorimpl2, $composer3, 0), modifierClearAndSetSemantics5, ColorSchemeKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextColor(), $composer3, 6), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TypographyKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextFont(), $composer3, 6), $composer3, 0, 0, 65528);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer5.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer5);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                TimePickerSelectionMode timePickerSelectionModeM3118boximpl5 = TimePickerSelectionMode.m3118boximpl(state.mo2133getSelectionyecRtBI());
                $composer4 = $composer5;
                ComposerKt.sourceInformationMarkerStart($composer4, 883095798, "CC(remember):TimePicker.kt#9igjgp");
                if (($dirty & 7168) != 2048) {
                    z = true;
                } else {
                    z = true;
                }
                invalid$iv2 = z | ((57344 & $dirty) == 16384);
                Object it$iv12 = $composer4.rememberedValue();
                if (invalid$iv2) {
                    value$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester3, null);
                    $composer4.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester3, null);
                    $composer4.updateRememberedValue(value$iv4);
                }
                ComposerKt.sourceInformationMarkerEnd($composer4);
                EffectsKt.LaunchedEffect(timePickerSelectionModeM3118boximpl5, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv4, $composer4, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                keyboardActions2 = keyboardActions3;
            } else {
                localMap$iv$iv2 = localMap$iv$iv;
                measurePolicy$iv2 = measurePolicy$iv;
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash7);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv7, ComposeUiNode.INSTANCE.getSetModifier());
            int i116 = ($changed$iv$iv$iv7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
            int i117 = (($changed$iv3 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer5, -2071429233, "C1788@69244L240,1796@69494L2047,1845@71677L244,1852@71988L5,1853@72059L5,1842@71551L523:TimePicker.kt#uh7d8r");
            $composer5.startReplaceGroup(2011386807);
            ComposerKt.sourceInformation($composer5, "1773@68737L450");
            if (selected) {
                Modifier modifierM1081sizeVpY3zN14 = SizeKt.m1081sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m3911getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m3910getTimeFieldContainerHeightD9Ej5fM());
                if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                    minute = getHourForDisplay(state);
                } else {
                    minute = state.getMinute();
                }
                i3 = 0;
                focusRequester2 = focusRequester;
                $composer2 = $composer5;
                $composer$iv = $composer5;
                m3096TimeSelectorSAnMeKU(modifierM1081sizeVpY3zN14, minute, state, selection, colors, $composer2, (($dirty >> 3) & 896) | 6 | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344));
            } else {
                focusRequester2 = focusRequester;
                $composer2 = $composer5;
                $composer$iv = $composer5;
                i3 = 0;
            }
            $composer2.endReplaceGroup();
            if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI())) {
                Strings.Companion companion111 = Strings.INSTANCE;
                iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute_text_field);
            } else {
                Strings.Companion companion112 = Strings.INSTANCE;
                iM3279constructorimpl = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour_text_field);
            }
            $composer3 = $composer2;
            final String contentDescription6 = Strings_androidKt.m3349getString2EP1pXo(iM3279constructorimpl, $composer3, i3);
            Modifier modifier$iv6 = visible(Modifier.INSTANCE, selected);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv6 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv8 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv6, false);
            int $changed$iv$iv9 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv8 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv9 = ComposedModifierKt.materializeModifier($composer3, modifier$iv6);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv9 = (($changed$iv$iv9 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function2 = constructor2;
                $composer3.createNode(function2);
            } else {
                function2 = constructor2;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer3);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash9 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash9);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv9, ComposeUiNode.INSTANCE.getSetModifier());
            int i118 = ($changed$iv$iv$iv9 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
            int i119 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1024044049, "C1803@69837L48,1807@70087L7,1814@70388L11,1815@70456L11,1819@70616L915,1797@69540L1991:TimePicker.kt#uh7d8r");
            focusRequester3 = focusRequester2;
            Modifier modifierM1081sizeVpY3zN15 = SizeKt.m1081sizeVpY3zN4(FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester3), TimeInputTokens.INSTANCE.m3911getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m3910getTimeFieldContainerHeightD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart($composer3, -1002857443, "CC(remember):TimePicker.kt#9igjgp");
            invalid$iv = $composer3.changed(contentDescription6);
            Object it$iv13 = $composer3.rememberedValue();
            if (invalid$iv) {
            }
            value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.setContentDescription($this$semantics, contentDescription6);
                }
            };
            $composer3.updateRememberedValue(value$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics$default6 = SemanticsModifierKt.semantics$default(modifierM1081sizeVpY3zN15, false, (Function1) value$iv3, 1, null);
            ProvidableCompositionLocal<TextStyle> localTextStyle6 = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer3.consume(localTextStyle6);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BasicTextFieldKt.BasicTextField(value, function1, modifierSemantics$default6, true, false, (TextStyle) objConsume6, keyboardOptions3, keyboardActions3, true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource3, Brush.Companion.m4492verticalGradient8A3gB4$default(Brush.INSTANCE, new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m4524boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getPrimary())), TuplesKt.to(Float.valueOf(0.9f), Color.m4524boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getPrimary())), TuplesKt.to(Float.valueOf(0.9f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU()))}, 0.0f, 0.0f, 0, 14, (Object) null), (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(825138052, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function3, Composer composer, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function3, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer6, int $changed2) {
                    ComposerKt.sourceInformation($composer6, "C1829@71096L403,1820@70660L857:TimePicker.kt#uh7d8r");
                    int $dirty3 = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty3 |= $composer6.changedInstance(function3) ? 4 : 2;
                    }
                    int $dirty4 = $dirty3;
                    if (($dirty4 & 19) == 18 && $composer6.getSkipping()) {
                        $composer6.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(825138052, $dirty4, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1820)");
                    }
                    OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                    String text = value.getText();
                    VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                    PaddingValues paddingValuesM1027PaddingValues0680j_4 = PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0));
                    MutableInteractionSource mutableInteractionSource = interactionSource3;
                    TextFieldColors textFieldColors4 = textFieldColors3;
                    final MutableInteractionSource mutableInteractionSource2 = interactionSource3;
                    final TextFieldColors textFieldColors5 = textFieldColors3;
                    outlinedTextFieldDefaults.DecorationBox(text, function3, true, true, none, mutableInteractionSource, false, null, null, null, null, null, null, null, textFieldColors4, paddingValuesM1027PaddingValues0680j_4, ComposableLambdaKt.rememberComposableLambda(-833003881, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer7, int $changed3) {
                            ComposerKt.sourceInformation($composer7, "C1834@71391L5,1830@71148L329:TimePicker.kt#uh7d8r");
                            if (($changed3 & 3) == 2 && $composer7.getSkipping()) {
                                $composer7.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-833003881, $changed3, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1830)");
                            }
                            OutlinedTextFieldDefaults.INSTANCE.m2715Container4EFweAY(true, false, mutableInteractionSource2, null, textFieldColors5, ShapesKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldContainerShape(), $composer7, 6), 0.0f, 0.0f, $composer7, 100663734, 200);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer6, 54), $composer6, (($dirty4 << 3) & 112) | 224640, 14352384, 16320);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer3, (($dirty >> 3) & 14) | 100666368 | (($dirty >> 3) & 112) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128), 199680, 7696);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierClearAndSetSemantics6 = SemanticsModifierKt.clearAndSetSemantics(androidx.compose.foundation.layout.OffsetKt.m995offsetVpY3zN4$default(Modifier.INSTANCE, 0.0f, SupportLabelTop, 1, null), new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
                }
            });
            if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3125getHouryecRtBI())) {
                Strings.Companion companion113 = Strings.INSTANCE;
                iM3279constructorimpl2 = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour);
            } else {
                Strings.Companion companion114 = Strings.INSTANCE;
                iM3279constructorimpl2 = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute);
            }
            TextKt.m3067Text4IGK_g(Strings_androidKt.m3349getString2EP1pXo(iM3279constructorimpl2, $composer3, 0), modifierClearAndSetSemantics6, ColorSchemeKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextColor(), $composer3, 6), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TypographyKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextFont(), $composer3, 6), $composer3, 0, 0, 65528);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            $composer5.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            TimePickerSelectionMode timePickerSelectionModeM3118boximpl6 = TimePickerSelectionMode.m3118boximpl(state.mo2133getSelectionyecRtBI());
            $composer4 = $composer5;
            ComposerKt.sourceInformationMarkerStart($composer4, 883095798, "CC(remember):TimePicker.kt#9igjgp");
            if (($dirty & 7168) != 2048) {
                z = true;
            } else {
                z = true;
            }
            invalid$iv2 = z | ((57344 & $dirty) == 16384);
            Object it$iv14 = $composer4.rememberedValue();
            if (invalid$iv2) {
                value$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester3, null);
                $composer4.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester3, null);
                $composer4.updateRememberedValue(value$iv4);
            }
            ComposerKt.sourceInformationMarkerEnd($composer4);
            EffectsKt.LaunchedEffect(timePickerSelectionModeM3118boximpl6, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv4, $composer4, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            keyboardActions2 = keyboardActions3;
        }
        scopeUpdateScopeEndRestartGroup = $composer4.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final KeyboardOptions keyboardOptions5 = keyboardOptions3;
            final KeyboardActions keyboardActions5 = keyboardActions2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i120) {
                    TimePickerKt.m3095TimePickerTextField1vLObsk(modifier, value, function1, state, selection, keyboardOptions5, keyboardActions5, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: CircularLayout-uFdPcIQ, reason: not valid java name */
    public static final void m3093CircularLayoutuFdPcIQ(Modifier modifier, final float radius, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Object value$iv;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(1548175696);
        ComposerKt.sourceInformation($composer2, "C(CircularLayout)P(1,2:c#ui.unit.Dp)1871@72466L1666,1871@72419L1713:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(radius) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1548175696, $dirty2, -1, "androidx.compose.material3.CircularLayout (TimePicker.kt:1870)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -328610546, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = ($dirty2 & 112) == 32;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new MeasurePolicy() { // from class: androidx.compose.material3.TimePickerKt$CircularLayout$1$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo391measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, final long constraints) {
                        Object it$iv2;
                        Object it$iv3;
                        final float radiusPx = $this$Layout.mo726toPx0680j_4(radius);
                        long itemConstraints = Constraints.m6959copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m6971getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m6969getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m6970getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m6968getMaxHeightimpl(constraints) : 0);
                        List target$iv = new ArrayList(list.size());
                        int size = list.size();
                        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                            Measurable measurable = list.get(index$iv$iv);
                            Measurable it = measurable;
                            if ((LayoutIdKt.getLayoutId(it) == LayoutId.Selector || LayoutIdKt.getLayoutId(it) == LayoutId.InnerCircle) ? false : true) {
                                target$iv.add(measurable);
                            }
                        }
                        List $this$fastMap$iv = target$iv;
                        List target$iv2 = new ArrayList($this$fastMap$iv.size());
                        int size2 = $this$fastMap$iv.size();
                        for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
                            Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv2);
                            Measurable measurable2 = (Measurable) item$iv$iv;
                            target$iv2.add(measurable2.mo5870measureBRTryo0(itemConstraints));
                        }
                        final List placeables = target$iv2;
                        List<? extends Measurable> list2 = list;
                        int index$iv$iv3 = 0;
                        int size3 = list2.size();
                        while (true) {
                            if (index$iv$iv3 < size3) {
                                Object item$iv$iv2 = list2.get(index$iv$iv3);
                                it$iv2 = item$iv$iv2;
                                Measurable it2 = (Measurable) it$iv2;
                                List<? extends Measurable> list3 = list2;
                                if (LayoutIdKt.getLayoutId(it2) == LayoutId.Selector) {
                                    break;
                                }
                                index$iv$iv3++;
                                list2 = list3;
                            } else {
                                it$iv2 = null;
                                break;
                            }
                        }
                        Measurable selectorMeasurable = (Measurable) it$iv2;
                        List<? extends Measurable> list4 = list;
                        int $i$f$fastFirstOrNull = 0;
                        int index$iv$iv4 = 0;
                        int size4 = list4.size();
                        while (true) {
                            if (index$iv$iv4 < size4) {
                                Object item$iv$iv3 = list4.get(index$iv$iv4);
                                it$iv3 = item$iv$iv3;
                                Measurable it3 = (Measurable) it$iv3;
                                List<? extends Measurable> list5 = list4;
                                int $i$f$fastFirstOrNull2 = $i$f$fastFirstOrNull;
                                if (LayoutIdKt.getLayoutId(it3) == LayoutId.InnerCircle) {
                                    break;
                                }
                                index$iv$iv4++;
                                list4 = list5;
                                $i$f$fastFirstOrNull = $i$f$fastFirstOrNull2;
                            } else {
                                it$iv3 = null;
                                break;
                            }
                        }
                        Measurable innerMeasurable = (Measurable) it$iv3;
                        final float theta = 6.2831855f / placeables.size();
                        final Placeable selectorPlaceable = selectorMeasurable != null ? selectorMeasurable.mo5870measureBRTryo0(itemConstraints) : null;
                        final Placeable innerCirclePlaceable = innerMeasurable != null ? innerMeasurable.mo5870measureBRTryo0(itemConstraints) : null;
                        return MeasureScope.layout$default($this$Layout, Constraints.m6971getMinWidthimpl(constraints), Constraints.m6970getMinHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$CircularLayout$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope $this$layout) {
                                Placeable placeable = selectorPlaceable;
                                if (placeable != null) {
                                    Placeable.PlacementScope.place$default($this$layout, placeable, 0, 0, 0.0f, 4, null);
                                }
                                List<Placeable> list6 = placeables;
                                long j = constraints;
                                float f = radiusPx;
                                float f2 = theta;
                                int index$iv = 0;
                                int size5 = list6.size();
                                while (index$iv < size5) {
                                    Object item$iv = list6.get(index$iv);
                                    Placeable it4 = (Placeable) item$iv;
                                    int i3 = index$iv;
                                    int centerOffsetX = (Constraints.m6969getMaxWidthimpl(j) / 2) - (it4.getWidth() / 2);
                                    int centerOffsetY = (Constraints.m6968getMaxHeightimpl(j) / 2) - (it4.getHeight() / 2);
                                    double d = f;
                                    List<Placeable> list7 = list6;
                                    double offsetX = (Math.cos(((double) (i3 * f2)) - 1.5707963267948966d) * d) + ((double) centerOffsetX);
                                    double offsetY = ((double) centerOffsetY) + (d * Math.sin(((double) (i3 * f2)) - 1.5707963267948966d));
                                    Placeable.PlacementScope.place$default($this$layout, it4, MathKt.roundToInt(offsetX), MathKt.roundToInt(offsetY), 0.0f, 4, null);
                                    index$iv++;
                                    list6 = list7;
                                    j = j;
                                    f = f;
                                }
                                Placeable placeable2 = innerCirclePlaceable;
                                if (placeable2 != null) {
                                    Placeable.PlacementScope.place$default($this$layout, placeable2, (Constraints.m6971getMinWidthimpl(constraints) - innerCirclePlaceable.getWidth()) / 2, (Constraints.m6970getMinHeightimpl(constraints) - innerCirclePlaceable.getHeight()) / 2, 0.0f, 4, null);
                                }
                            }
                        }, 4, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $changed$iv = (($dirty2 >> 6) & 14) | (($dirty2 << 3) & 112);
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier4);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = (($changed$iv << 6) & 896) | 6;
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
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4027constructorimpl($composer2);
            Modifier modifier5 = modifier4;
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            function2.invoke($composer2, Integer.valueOf(($changed$iv$iv >> 6) & 14));
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$CircularLayout$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    TimePickerKt.m3093CircularLayoutuFdPcIQ(modifier6, radius, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: numberContentDescription-dSwYdS4, reason: not valid java name */
    public static final String m3104numberContentDescriptiondSwYdS4(int selection, boolean is24Hour, int number, Composer $composer, int $changed) {
        int id;
        ComposerKt.sourceInformationMarkerStart($composer, 194237364, "C(numberContentDescription)P(2:c#material3.TimePickerSelectionMode)1924@74567L21:TimePicker.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(194237364, $changed, -1, "androidx.compose.material3.numberContentDescription (TimePicker.kt:1914)");
        }
        if (TimePickerSelectionMode.m3121equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3126getMinuteyecRtBI())) {
            Strings.Companion companion = Strings.INSTANCE;
            id = Strings.m3279constructorimpl(R.string.m3c_time_picker_minute_suffix);
        } else if (is24Hour) {
            Strings.Companion companion2 = Strings.INSTANCE;
            id = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour_24h_suffix);
        } else {
            Strings.Companion companion3 = Strings.INSTANCE;
            id = Strings.m3279constructorimpl(R.string.m3c_time_picker_hour_suffix);
        }
        String strM3350getStringqBjtwXw = Strings_androidKt.m3350getStringqBjtwXw(id, new Object[]{Integer.valueOf(number)}, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return strM3350getStringqBjtwXw;
    }

    private static final float dist(float x1, float y1, int x2, int y2) {
        float x = x2 - x1;
        float y = y2 - y1;
        return (float) Math.hypot(x, y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float atan(float y, float x) {
        float ret = ((float) Math.atan2(y, x)) - 1.5707964f;
        return ret < 0.0f ? FullCircle + ret : ret;
    }

    static {
        MutableIntList $this$ExtraHours_u24lambda_u2446 = new MutableIntList(Hours.getSize());
        IntList this_$iv = Hours;
        int[] content$iv = this_$iv.content;
        int i = this_$iv._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            int it = content$iv[i$iv];
            $this$ExtraHours_u24lambda_u2446.add((it % 12) + 12);
        }
        ExtraHours = $this$ExtraHours_u24lambda_u2446;
        PeriodToggleMargin = Dp.m7016constructorimpl(12);
    }

    private static final Modifier visible(Modifier $this$visible, final boolean visible) {
        return $this$visible.then(new VisibleModifier(visible, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TimePickerKt$visible$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                $this$null.setName("visible");
                $this$null.getProperties().set("visible", Boolean.valueOf(visible));
            }
        } : InspectableValueKt.getNoInspectorInfo()));
    }
}
