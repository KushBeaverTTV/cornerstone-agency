package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.gestures.FlingBehavior;
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
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.foundation.lazy.grid.GridCells;
import androidx.compose.foundation.lazy.grid.LazyGridDslKt;
import androidx.compose.foundation.lazy.grid.LazyGridItemInfo;
import androidx.compose.foundation.lazy.grid.LazyGridItemScope;
import androidx.compose.foundation.lazy.grid.LazyGridScope;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.foundation.lazy.grid.LazyGridStateKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ArrowDropDownKt;
import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.CalendarModelKt;
import androidx.compose.material3.internal.CalendarModel_androidKt;
import androidx.compose.material3.internal.CalendarMonth;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.material3.tokens.MotionTokens;
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
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.RotateKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: DatePicker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ì\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a\u0084\u0001\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0018¢\u0006\u0002\b\u00192\u0013\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0018¢\u0006\u0002\b\u00192\u0013\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0018¢\u0006\u0002\b\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00032\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u0019H\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001ak\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010'\u001a\u00020(2\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0018¢\u0006\u0002\b\u00192\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0018¢\u0006\u0002\b\u00192\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0007¢\u0006\u0002\u0010+\u001a\u008d\u0001\u0010,\u001a\u00020\u00142\b\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u00020.2!\u00100\u001a\u001d\u0012\u0013\u0012\u00110.¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020\u0014012!\u00105\u001a\u001d\u0012\u0013\u0012\u00110.¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u0014012\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010'\u001a\u00020(2\u0006\u0010;\u001a\u00020<2\u0006\u0010\u001c\u001a\u00020\u001dH\u0003¢\u0006\u0002\u0010=\u001aZ\u0010>\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0018¢\u0006\u0002\b\u00192\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020@2\u0006\u0010B\u001a\u00020\u00032\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u0019H\u0001ø\u0001\u0000¢\u0006\u0004\bC\u0010D\u001aT\u0010E\u001a\u00020&2\n\u0010F\u001a\u00060Gj\u0002`H2\n\b\u0002\u0010I\u001a\u0004\u0018\u00010.2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010.2\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010K\u001a\u00020L2\b\b\u0002\u0010;\u001a\u00020<H\u0007ø\u0001\u0000¢\u0006\u0004\bM\u0010N\u001an\u0010O\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010P\u001a\u00020*2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00140\u00182\u0006\u0010R\u001a\u00020*2\u0006\u0010S\u001a\u00020*2\u0006\u0010T\u001a\u00020*2\u0006\u0010U\u001a\u00020*2\u0006\u0010V\u001a\u00020W2\u0006\u0010\u001c\u001a\u00020\u001d2\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u0019H\u0003¢\u0006\u0002\u0010X\u001a6\u0010Y\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010Z\u001a\u00020L2\u0012\u0010[\u001a\u000e\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u001401H\u0001ø\u0001\u0000¢\u0006\u0004\b\\\u0010]\u001a\u008d\u0001\u0010^\u001a\u00020\u00142\u0006\u0010_\u001a\u00020`2\b\u0010-\u001a\u0004\u0018\u00010.2!\u00100\u001a\u001d\u0012\u0013\u0012\u00110.¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020\u0014012!\u00105\u001a\u001d\u0012\u0013\u0012\u00110.¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u0014012\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010'\u001a\u00020(2\u0006\u0010;\u001a\u00020<2\u0006\u0010\u001c\u001a\u00020\u001dH\u0003¢\u0006\u0002\u0010a\u001av\u0010b\u001a\u00020\u00142\u0006\u0010c\u001a\u00020d2!\u00100\u001a\u001d\u0012\u0013\u0012\u00110.¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020\u0014012\u0006\u0010e\u001a\u00020.2\b\u0010f\u001a\u0004\u0018\u00010.2\b\u0010g\u001a\u0004\u0018\u00010.2\b\u0010h\u001a\u0004\u0018\u00010i2\u0006\u0010'\u001a\u00020(2\u0006\u0010;\u001a\u00020<2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\u0010j\u001ag\u0010k\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010l\u001a\u00020*2\u0006\u0010m\u001a\u00020*2\u0006\u0010n\u001a\u00020*2\u0006\u0010o\u001a\u00020W2\f\u0010p\u001a\b\u0012\u0004\u0012\u00020\u00140\u00182\f\u0010q\u001a\b\u0012\u0004\u0012\u00020\u00140\u00182\f\u0010r\u001a\b\u0012\u0004\u0012\u00020\u00140\u00182\u0006\u0010\u001c\u001a\u00020\u001dH\u0003¢\u0006\u0002\u0010s\u001a\u009c\u0001\u0010t\u001a\u00020\u00142\b\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u00020.2\u0006\u0010Z\u001a\u00020L2#\u00100\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010.¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020\u0014012!\u00105\u001a\u001d\u0012\u0013\u0012\u00110.¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u0014012\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010'\u001a\u00020(2\u0006\u0010;\u001a\u00020<2\u0006\u0010\u001c\u001a\u00020\u001dH\u0003ø\u0001\u0000¢\u0006\u0004\bu\u0010v\u001a\u001d\u0010w\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u00107\u001a\u000208H\u0001¢\u0006\u0002\u0010x\u001a^\u0010y\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010P\u001a\u00020*2\u0006\u0010z\u001a\u00020*2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00140\u00182\u0006\u0010S\u001a\u00020*2\u0006\u0010V\u001a\u00020W2\u0006\u0010\u001c\u001a\u00020\u001d2\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u0019H\u0003¢\u0006\u0002\u0010{\u001a`\u0010|\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010/\u001a\u00020.2!\u0010}\u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(~\u0012\u0004\u0012\u00020\u0014012\u0006\u0010;\u001a\u00020<2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010\u001c\u001a\u00020\u001dH\u0003¢\u0006\u0002\u0010\u007f\u001aC\u0010\u0080\u0001\u001a\u00020\u00142\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00140\u00182\u0007\u0010\u0081\u0001\u001a\u00020*2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u0019H\u0003¢\u0006\u0003\u0010\u0082\u0001\u001a6\u0010\u0083\u0001\u001a\n\u0012\u0005\u0012\u00030\u0085\u00010\u0084\u00012\u0007\u0010%\u001a\u00030\u0086\u00012\b\u0010\u0087\u0001\u001a\u00030\u0088\u00012\u0007\u0010\u0089\u0001\u001a\u00020W2\u0007\u0010\u008a\u0001\u001a\u00020WH\u0002\u001a>\u0010\u008b\u0001\u001a\u0004\u0018\u00010W2\u0007\u0010\u008c\u0001\u001a\u00020*2\u0007\u0010\u008d\u0001\u001a\u00020*2\u0007\u0010\u008e\u0001\u001a\u00020*2\u0007\u0010\u008f\u0001\u001a\u00020*2\u0007\u0010\u0090\u0001\u001a\u00020*H\u0003¢\u0006\u0003\u0010\u0091\u0001\u001a\u0011\u0010\u0092\u0001\u001a\u00020\f2\u0006\u00109\u001a\u00020:H\u0000\u001aK\u0010\u0093\u0001\u001a\u00020&2\n\b\u0002\u0010I\u001a\u0004\u0018\u00010.2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010.2\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010K\u001a\u00020L2\b\b\u0002\u0010;\u001a\u00020<H\u0007ø\u0001\u0000¢\u0006\u0006\b\u0094\u0001\u0010\u0095\u0001\u001aK\u0010\u0096\u0001\u001a\u00020\u00142\u0006\u0010_\u001a\u00020`2!\u00105\u001a\u001d\u0012\u0013\u0012\u00110.¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u0014012\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0080@¢\u0006\u0003\u0010\u0097\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0007\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"\u000e\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\r\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u000e\u0010\u0005\"\u0016\u0010\u000f\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0010\u0010\u0005\"\u000e\u0010\u0011\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0012\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0098\u0001²\u0006\n\u0010n\u001a\u00020*X\u008a\u008e\u0002"}, d2 = {"DatePickerHeadlinePadding", "Landroidx/compose/foundation/layout/PaddingValues;", "DatePickerHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "getDatePickerHorizontalPadding", "()F", "F", "DatePickerModeTogglePadding", "getDatePickerModeTogglePadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "DatePickerTitlePadding", "MaxCalendarRows", "", "MonthYearHeight", "getMonthYearHeight", "RecommendedSizeForAccessibility", "getRecommendedSizeForAccessibility", "YearsInRow", "YearsVerticalPadding", "DateEntryContainer", "", "modifier", "Landroidx/compose/ui/Modifier;", "title", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "headline", "modeToggleButton", "colors", "Landroidx/compose/material3/DatePickerColors;", "headlineTextStyle", "Landroidx/compose/ui/text/TextStyle;", "headerMinHeight", "content", "DateEntryContainer-au3_HiA", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/text/TextStyle;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "DatePicker", "state", "Landroidx/compose/material3/DatePickerState;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "showModeToggle", "", "(Landroidx/compose/material3/DatePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DatePickerFormatter;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;II)V", "DatePickerContent", "selectedDateMillis", "", "displayedMonthMillis", "onDateSelectionChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "dateInMillis", "onDisplayedMonthChange", "monthInMillis", "calendarModel", "Landroidx/compose/material3/internal/CalendarModel;", "yearRange", "Lkotlin/ranges/IntRange;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "(Ljava/lang/Long;JLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "DatePickerHeader", "titleContentColor", "Landroidx/compose/ui/graphics/Color;", "headlineContentColor", "minHeight", "DatePickerHeader-pc5RIQQ", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;JJFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "DatePickerState", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "initialSelectedDateMillis", "initialDisplayedMonthMillis", "initialDisplayMode", "Landroidx/compose/material3/DisplayMode;", "DatePickerState-sHin3Bw", "(Ljava/util/Locale;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;)Landroidx/compose/material3/DatePickerState;", "Day", "selected", "onClick", "animateChecked", "enabled", "today", "inRange", "description", "", "(Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function0;ZZZZLjava/lang/String;Landroidx/compose/material3/DatePickerColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "DisplayModeToggleButton", "displayMode", "onDisplayModeChange", "DisplayModeToggleButton-tER2X8s", "(Landroidx/compose/ui/Modifier;ILkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "HorizontalMonthsList", "lazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "(Landroidx/compose/foundation/lazy/LazyListState;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "Month", "month", "Landroidx/compose/material3/internal/CalendarMonth;", "todayMillis", "startDateMillis", "endDateMillis", "rangeSelectionInfo", "Landroidx/compose/material3/SelectedRangeInfo;", "(Landroidx/compose/material3/internal/CalendarMonth;Lkotlin/jvm/functions/Function1;JLjava/lang/Long;Ljava/lang/Long;Landroidx/compose/material3/SelectedRangeInfo;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "MonthsNavigation", "nextAvailable", "previousAvailable", "yearPickerVisible", "yearPickerText", "onNextClicked", "onPreviousClicked", "onYearPickerButtonClicked", "(Landroidx/compose/ui/Modifier;ZZZLjava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "SwitchableDateEntryContent", "SwitchableDateEntryContent-d7iavvg", "(Ljava/lang/Long;JILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "WeekDays", "(Landroidx/compose/material3/DatePickerColors;Landroidx/compose/material3/internal/CalendarModel;Landroidx/compose/runtime/Composer;I)V", "Year", "currentYear", "(Landroidx/compose/ui/Modifier;ZZLkotlin/jvm/functions/Function0;ZLjava/lang/String;Landroidx/compose/material3/DatePickerColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "YearPicker", "onYearSelected", "year", "(Landroidx/compose/ui/Modifier;JLkotlin/jvm/functions/Function1;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "YearPickerMenuButton", "expanded", "(Lkotlin/jvm/functions/Function0;ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "customScrollActions", "", "Landroidx/compose/ui/semantics/CustomAccessibilityAction;", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "scrollUpLabel", "scrollDownLabel", "dayContentDescription", "rangeSelectionEnabled", "isToday", "isStartDate", "isEndDate", "isInRange", "(ZZZZZLandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "numberOfMonthsInRange", "rememberDatePickerState", "rememberDatePickerState-EU0dCGE", "(Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DatePickerState;", "updateDisplayedMonth", "(Landroidx/compose/foundation/lazy/LazyListState;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DatePickerKt {
    private static final int MaxCalendarRows = 6;
    private static final int YearsInRow = 3;
    private static final float RecommendedSizeForAccessibility = Dp.m7016constructorimpl(48);
    private static final float MonthYearHeight = Dp.m7016constructorimpl(56);
    private static final float DatePickerHorizontalPadding = Dp.m7016constructorimpl(12);
    private static final PaddingValues DatePickerModeTogglePadding = PaddingKt.m1031PaddingValuesa9UjIt4$default(0.0f, 0.0f, Dp.m7016constructorimpl(12), Dp.m7016constructorimpl(12), 3, null);
    private static final PaddingValues DatePickerTitlePadding = PaddingKt.m1031PaddingValuesa9UjIt4$default(Dp.m7016constructorimpl(24), Dp.m7016constructorimpl(16), Dp.m7016constructorimpl(12), 0.0f, 8, null);
    private static final PaddingValues DatePickerHeadlinePadding = PaddingKt.m1031PaddingValuesa9UjIt4$default(Dp.m7016constructorimpl(24), 0.0f, Dp.m7016constructorimpl(12), Dp.m7016constructorimpl(12), 2, null);
    private static final float YearsVerticalPadding = Dp.m7016constructorimpl(16);

    /* JADX WARN: Code duplicated, block: B:33:0x0063  */
    public static final void DatePicker(final DatePickerState state, Modifier modifier, DatePickerFormatter dateFormatter, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, boolean showModeToggle, DatePickerColors colors, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda2;
        boolean showModeToggle2;
        DatePickerColors datePickerColors;
        final DatePickerFormatter dateFormatter2;
        int i2;
        boolean z;
        int $dirty;
        boolean showModeToggle3;
        final DatePickerColors colors2;
        Object value$iv;
        Object value$iv2;
        DatePickerFormatter dateFormatter3;
        boolean showModeToggle4;
        DatePickerColors colors3;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Function2<? super Composer, ? super Integer, Unit> function5;
        int i3;
        Composer $composer2 = $composer.startRestartGroup(809701813);
        ComposerKt.sourceInformation($composer2, "C(DatePicker)P(5,3,1,6,2,4)171@8197L47,172@8285L170,178@8499L278,187@8865L8,189@8902L15,190@8942L62,207@9637L5,210@9746L655,191@9009L1392:DatePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(state) ? 4 : 2;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) != 0) {
                i3 = 128;
            } else {
                if (($changed & 512) == 0 ? $composer2.changed(dateFormatter) : $composer2.changedInstance(dateFormatter)) {
                    i3 = 256;
                } else {
                    i3 = 128;
                }
            }
            $dirty2 |= i3;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            function2RememberComposableLambda = function2;
        } else if (($changed & 3072) == 0) {
            function2RememberComposableLambda = function2;
            $dirty2 |= $composer2.changedInstance(function2RememberComposableLambda) ? 2048 : 1024;
        } else {
            function2RememberComposableLambda = function2;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty2 |= 24576;
            function2RememberComposableLambda2 = function3;
        } else if (($changed & 24576) == 0) {
            function2RememberComposableLambda2 = function3;
            $dirty2 |= $composer2.changedInstance(function2RememberComposableLambda2) ? 16384 : 8192;
        } else {
            function2RememberComposableLambda2 = function3;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            showModeToggle2 = showModeToggle;
        } else if ((196608 & $changed) == 0) {
            showModeToggle2 = showModeToggle;
            $dirty2 |= $composer2.changed(showModeToggle2) ? 131072 : 65536;
        } else {
            showModeToggle2 = showModeToggle;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                datePickerColors = colors;
                int i8 = $composer2.changed(datePickerColors) ? 1048576 : 524288;
                $dirty2 |= i8;
            } else {
                datePickerColors = colors;
            }
            $dirty2 |= i8;
        } else {
            datePickerColors = colors;
        }
        if ((599187 & $dirty2) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            dateFormatter3 = dateFormatter;
            modifier3 = modifier2;
            showModeToggle4 = showModeToggle2;
            colors3 = datePickerColors;
            function4 = function2RememberComposableLambda;
            function5 = function2RememberComposableLambda2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, 255556878, "CC(remember):DatePicker.kt#9igjgp");
                    Object it$iv = $composer2.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = DatePickerDefaults.dateFormatter$default(DatePickerDefaults.INSTANCE, null, null, null, 7, null);
                        $composer2.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    dateFormatter2 = (DatePickerFormatter) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty2 &= -897;
                } else {
                    dateFormatter2 = dateFormatter;
                }
                if (i5 != 0) {
                    i2 = 54;
                    z = true;
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1504998463, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.2
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer3, int $changed2) {
                            ComposerKt.sourceInformation($composer3, "C173@8314L135:DatePicker.kt#uh7d8r");
                            if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1504998463, $changed2, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:173)");
                                }
                                DatePickerDefaults.INSTANCE.m2396DatePickerTitlehOD91z4(state.mo2411getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), $composer3, 432, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    }, $composer2, 54);
                } else {
                    i2 = 54;
                    z = true;
                }
                if (i6 != 0) {
                    function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-1780043561, z, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.3
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
                            ComposerKt.sourceInformation($composer3, "C179@8528L243:DatePicker.kt#uh7d8r");
                            if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1780043561, $changed2, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:179)");
                                }
                                DatePickerDefaults.INSTANCE.m2395DatePickerHeadline3kbWawI(state.getSelectedDateMillis(), state.mo2411getDisplayModejFl4v0(), dateFormatter2, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), $composer3, 27648, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    }, $composer2, i2);
                }
                if (i7 != 0) {
                    showModeToggle2 = true;
                }
                if ((i & 64) != 0) {
                    modifier2 = modifier4;
                    $dirty = $dirty2 & (-3670017);
                    colors2 = DatePickerDefaults.INSTANCE.colors($composer2, 6);
                    showModeToggle3 = showModeToggle2;
                } else {
                    modifier2 = modifier4;
                    $dirty = $dirty2;
                    showModeToggle3 = showModeToggle2;
                    colors2 = datePickerColors;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 64) != 0) {
                    dateFormatter2 = dateFormatter;
                    $dirty = (-3670017) & $dirty2;
                    showModeToggle3 = showModeToggle2;
                    colors2 = datePickerColors;
                } else {
                    dateFormatter2 = dateFormatter;
                    $dirty = $dirty2;
                    showModeToggle3 = showModeToggle2;
                    colors2 = datePickerColors;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(809701813, $dirty, -1, "androidx.compose.material3.DatePicker (DatePicker.kt:188)");
            }
            Locale defaultLocale = CalendarLocale_androidKt.defaultLocale($composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 255580733, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(defaultLocale);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = CalendarModel_androidKt.createCalendarModel(defaultLocale);
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            final CalendarModel calendarModel = (CalendarModel) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.startReplaceGroup(255587712);
            ComposerKt.sourceInformation($composer2, "197@9187L323");
            ComposableLambda composableLambdaRememberComposableLambda = showModeToggle3 ? ComposableLambdaKt.rememberComposableLambda(1982226759, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.4
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
                    ComposerKt.sourceInformation($composer3, "C201@9419L50,198@9209L283:DatePicker.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1982226759, $changed2, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:198)");
                        }
                        Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
                        int iMo2411getDisplayModejFl4v0 = state.mo2411getDisplayModejFl4v0();
                        ComposerKt.sourceInformationMarkerStart($composer3, -2079723184, "CC(remember):DatePicker.kt#9igjgp");
                        boolean invalid$iv2 = $composer3.changed(state);
                        final DatePickerState datePickerState = state;
                        Object it$iv3 = $composer3.rememberedValue();
                        if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv3 = new Function1<DisplayMode, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePicker$4$1$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode) {
                                    m2408invokevCnGnXg(displayMode.getValue());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
                                public final void m2408invokevCnGnXg(int displayMode) {
                                    datePickerState.mo2412setDisplayModevCnGnXg(displayMode);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv3);
                        } else {
                            value$iv3 = it$iv3;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        DatePickerKt.m2404DisplayModeToggleButtontER2X8s(modifierPadding, iMo2411getDisplayModejFl4v0, (Function1) value$iv3, $composer3, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54) : null;
            $composer2.endReplaceGroup();
            dateFormatter3 = dateFormatter2;
            m2400DateEntryContainerau3_HiA(modifier2, function2RememberComposableLambda, function2RememberComposableLambda2, composableLambdaRememberComposableLambda, colors2, TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getHeaderHeadlineFont(), $composer2, 6), DatePickerModalTokens.INSTANCE.m3521getHeaderContainerHeightD9Ej5fM(), ComposableLambdaKt.rememberComposableLambda(-1840727866, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) throws Throwable {
                    Object value$iv3;
                    Object value$iv4;
                    ComposerKt.sourceInformation($composer3, "C215@9987L59,216@10085L91,211@9756L639:DatePicker.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1840727866, $changed2, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:211)");
                        }
                        Long selectedDateMillis = state.getSelectedDateMillis();
                        long displayedMonthMillis = state.getDisplayedMonthMillis();
                        int iMo2411getDisplayModejFl4v0 = state.mo2411getDisplayModejFl4v0();
                        ComposerKt.sourceInformationMarkerStart($composer3, -2079704999, "CC(remember):DatePicker.kt#9igjgp");
                        boolean invalid$iv2 = $composer3.changed(state);
                        final DatePickerState datePickerState = state;
                        Object it$iv3 = $composer3.rememberedValue();
                        if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv3 = new Function1<Long, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePicker$5$1$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                    invoke2(l);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Long dateInMillis) {
                                    datePickerState.setSelectedDateMillis(dateInMillis);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv3);
                        } else {
                            value$iv3 = it$iv3;
                        }
                        Function1 function1 = (Function1) value$iv3;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerStart($composer3, -2079701831, "CC(remember):DatePicker.kt#9igjgp");
                        boolean invalid$iv3 = $composer3.changed(state);
                        final DatePickerState datePickerState2 = state;
                        Object it$iv4 = $composer3.rememberedValue();
                        if (invalid$iv3 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                            value$iv4 = new Function1<Long, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePicker$5$2$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                    invoke(l.longValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(long monthInMillis) {
                                    datePickerState2.setDisplayedMonthMillis(monthInMillis);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv4);
                        } else {
                            value$iv4 = it$iv4;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        DatePickerKt.m2405SwitchableDateEntryContentd7iavvg(selectedDateMillis, displayedMonthMillis, iMo2411getDisplayModejFl4v0, function1, (Function1) value$iv4, calendarModel, state.getYearRange(), dateFormatter2, state.getSelectableDates(), colors2, $composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, (($dirty >> 3) & 14) | 14155776 | (($dirty >> 6) & 112) | (($dirty >> 6) & 896) | (($dirty >> 6) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            showModeToggle4 = showModeToggle3;
            colors3 = colors2;
            modifier3 = modifier2;
            function4 = function2RememberComposableLambda;
            function5 = function2RememberComposableLambda2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final DatePickerFormatter datePickerFormatter = dateFormatter3;
            final Function2<? super Composer, ? super Integer, Unit> function6 = function4;
            final Function2<? super Composer, ? super Integer, Unit> function7 = function5;
            final boolean z2 = showModeToggle4;
            final DatePickerColors datePickerColors2 = colors3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePicker.6
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

                public final void invoke(Composer composer, int i9) {
                    DatePickerKt.DatePicker(state, modifier5, datePickerFormatter, function6, function7, z2, datePickerColors2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: rememberDatePickerState-EU0dCGE, reason: not valid java name */
    public static final DatePickerState m2407rememberDatePickerStateEU0dCGE(Long initialSelectedDateMillis, Long initialDisplayedMonthMillis, IntRange yearRange, int initialDisplayMode, SelectableDates selectableDates, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 2065763010, "C(rememberDatePickerState)P(2,1,4,0:c#material3.DisplayMode)365@15667L15,*366@15771L383,366@15694L460:DatePicker.kt#uh7d8r");
        Long initialSelectedDateMillis2 = (i & 1) != 0 ? null : initialSelectedDateMillis;
        Long initialDisplayedMonthMillis2 = (i & 2) != 0 ? initialSelectedDateMillis2 : initialDisplayedMonthMillis;
        IntRange yearRange2 = (i & 4) != 0 ? DatePickerDefaults.INSTANCE.getYearRange() : yearRange;
        int initialDisplayMode2 = (i & 8) != 0 ? DisplayMode.INSTANCE.m2443getPickerjFl4v0() : initialDisplayMode;
        SelectableDates selectableDates2 = (i & 16) != 0 ? DatePickerDefaults.INSTANCE.getAllDates() : selectableDates;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2065763010, $changed, -1, "androidx.compose.material3.rememberDatePickerState (DatePicker.kt:364)");
        }
        final Locale locale = CalendarLocale_androidKt.defaultLocale($composer, 0);
        Object[] objArr = new Object[0];
        Saver<DatePickerStateImpl, Object> Saver = DatePickerStateImpl.INSTANCE.Saver(selectableDates2, locale);
        ComposerKt.sourceInformationMarkerStart($composer, -390857660, "CC(remember):DatePicker.kt#9igjgp");
        boolean invalid$iv = ((((57344 & $changed) ^ 24576) > 16384 && $composer.changed(selectableDates2)) || ($changed & 24576) == 16384) | (((($changed & 14) ^ 6) > 4 && $composer.changed(initialSelectedDateMillis2)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(initialDisplayedMonthMillis2)) || ($changed & 48) == 32) | $composer.changedInstance(yearRange2) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(initialDisplayMode2)) || ($changed & 3072) == 2048) | $composer.changedInstance(locale);
        Object value$iv = $composer.rememberedValue();
        if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
            final Long l = initialSelectedDateMillis2;
            final Long l2 = initialDisplayedMonthMillis2;
            final IntRange intRange = yearRange2;
            final int i2 = initialDisplayMode2;
            final SelectableDates selectableDates3 = selectableDates2;
            value$iv = new Function0<DatePickerStateImpl>() { // from class: androidx.compose.material3.DatePickerKt$rememberDatePickerState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final DatePickerStateImpl invoke() {
                    return new DatePickerStateImpl(l, l2, intRange, i2, selectableDates3, locale, null);
                }
            };
            $composer.updateRememberedValue(value$iv);
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Object objM4120rememberSaveable = RememberSaveableKt.m4120rememberSaveable(objArr, Saver, (String) null, (Function0<? extends Object>) value$iv, $composer, 0, 4);
        DatePickerStateImpl $this$rememberDatePickerState_EU0dCGE_u24lambda_u243 = (DatePickerStateImpl) objM4120rememberSaveable;
        $this$rememberDatePickerState_EU0dCGE_u24lambda_u243.setSelectableDates(selectableDates2);
        DatePickerStateImpl datePickerStateImpl = (DatePickerStateImpl) objM4120rememberSaveable;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return datePickerStateImpl;
    }

    /* JADX INFO: renamed from: DatePickerState-sHin3Bw$default, reason: not valid java name */
    public static /* synthetic */ DatePickerState m2403DatePickerStatesHin3Bw$default(Locale locale, Long l, Long l2, IntRange intRange, int i, SelectableDates selectableDates, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            l = null;
        }
        return m2402DatePickerStatesHin3Bw(locale, l, (i2 & 4) != 0 ? l : l2, (i2 & 8) != 0 ? DatePickerDefaults.INSTANCE.getYearRange() : intRange, (i2 & 16) != 0 ? DisplayMode.INSTANCE.m2443getPickerjFl4v0() : i, (i2 & 32) != 0 ? DatePickerDefaults.INSTANCE.getAllDates() : selectableDates);
    }

    /* JADX INFO: renamed from: DatePickerState-sHin3Bw, reason: not valid java name */
    public static final DatePickerState m2402DatePickerStatesHin3Bw(Locale locale, Long initialSelectedDateMillis, Long initialDisplayedMonthMillis, IntRange yearRange, int initialDisplayMode, SelectableDates selectableDates) {
        return new DatePickerStateImpl(initialSelectedDateMillis, initialDisplayedMonthMillis, yearRange, initialDisplayMode, selectableDates, locale, null);
    }

    /* JADX WARN: Code duplicated, block: B:91:0x028b  */
    /* JADX INFO: renamed from: DateEntryContainer-au3_HiA, reason: not valid java name */
    public static final void m2400DateEntryContainerau3_HiA(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final DatePickerColors colors, final TextStyle headlineTextStyle, final float headerMinHeight, final Function2<? super Composer, ? super Integer, Unit> function5, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        CompositionLocalMap localMap$iv$iv;
        Composer $composer2 = $composer.startRestartGroup(1507356255);
        ComposerKt.sourceInformation($composer2, "C(DateEntryContainer)P(6,7,3,5!1,4,2:c#ui.unit.Dp)1309@61230L1775:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(colors) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(headlineTextStyle) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer2.changed(headerMinHeight) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function5) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty) != 4793490 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1507356255, $dirty, -1, "androidx.compose.material3.DateEntryContainer (DatePicker.kt:1308)");
            }
            Modifier modifier$iv = BackgroundKt.m589backgroundbw27NRU$default(SemanticsModifierKt.semantics$default(SizeKt.m1083sizeInqDBjuR0$default(modifier, DatePickerModalTokens.INSTANCE.m3515getContainerWidthD9Ej5fM(), 0.0f, 0.0f, 0.0f, 14, null), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DateEntryContainer$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.setContainer($this$semantics, true);
                }
            }, 1, null), colors.getContainerColor(), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
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
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                function0 = constructor;
                localMap$iv$iv = localMap$iv$iv2;
            } else {
                function0 = constructor;
                localMap$iv$iv = localMap$iv$iv2;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i2 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 133465402, "C1325@61799L1182,1319@61549L1432,1351@62990L9:DatePicker.kt#uh7d8r");
                m2401DatePickerHeaderpc5RIQQ(Modifier.INSTANCE, function2, colors.getTitleContentColor(), colors.getHeadlineContentColor(), headerMinHeight, ComposableLambdaKt.rememberComposableLambda(-229007058, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DateEntryContainer$2$1
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

                    public final void invoke(Composer $composer3, int $changed2) {
                        Function0<ComposeUiNode> function1;
                        Arrangement.HorizontalOrVertical horizontalArrangement;
                        Function0<ComposeUiNode> function6;
                        ComposerKt.sourceInformation($composer3, "C1326@61813L1158:DatePicker.kt#uh7d8r");
                        if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-229007058, $changed2, -1, "androidx.compose.material3.DateEntryContainer.<anonymous>.<anonymous> (DatePicker.kt:1326)");
                            }
                            Modifier modifier$iv2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            final Function2<Composer, Integer, Unit> function7 = function3;
                            Function2<Composer, Integer, Unit> function8 = function4;
                            Function2<Composer, Integer, Unit> function9 = function2;
                            DatePickerColors datePickerColors = colors;
                            TextStyle textStyle = headlineTextStyle;
                            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                            int $changed$iv$iv2 = (6 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                                function1 = constructor2;
                                $composer3.createNode(function1);
                            } else {
                                function1 = constructor2;
                                $composer3.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer3);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                            }
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                            int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                            int i4 = ((6 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1718813547, "C1333@62174L520:DatePicker.kt#uh7d8r");
                            if (function7 == null || function8 == null) {
                                horizontalArrangement = function7 != null ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getEnd();
                            } else {
                                horizontalArrangement = Arrangement.INSTANCE.getSpaceBetween();
                            }
                            Modifier modifier$iv3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement, verticalAlignment$iv, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                            int $changed$iv$iv3 = (390 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                                function6 = constructor3;
                                $composer3.createNode(function6);
                            } else {
                                function6 = constructor3;
                                $composer3.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4027constructorimpl($composer3);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                            }
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                            int i5 = ($changed$iv$iv$iv3 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                            int i6 = ((390 >> 6) & 112) | 6;
                            final RowScope $this$invoke_u24lambda_u241_u24lambda_u240 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1252979940, "C:DatePicker.kt#uh7d8r");
                            $composer3.startReplaceGroup(-1287344744);
                            ComposerKt.sourceInformation($composer3, "1339@62501L106,1339@62457L150");
                            if (function7 != null) {
                                TextKt.ProvideTextStyle(textStyle, ComposableLambdaKt.rememberComposableLambda(-962031352, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DateEntryContainer$2$1$1$1$1
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

                                    public final void invoke(Composer $composer4, int $changed3) {
                                        Function0<ComposeUiNode> function10;
                                        ComposerKt.sourceInformation($composer4, "C1340@62531L50:DatePicker.kt#uh7d8r");
                                        if (($changed3 & 3) == 2 && $composer4.getSkipping()) {
                                            $composer4.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-962031352, $changed3, -1, "androidx.compose.material3.DateEntryContainer.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1340)");
                                        }
                                        Modifier modifier$iv4 = RowScope.weight$default($this$invoke_u24lambda_u241_u24lambda_u240, Modifier.INSTANCE, 1.0f, false, 2, null);
                                        Function2<Composer, Integer, Unit> function11 = function7;
                                        ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                        MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                        int $changed$iv$iv4 = (0 << 3) & 112;
                                        ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                                        CompositionLocalMap localMap$iv$iv5 = $composer4.getCurrentCompositionLocalMap();
                                        Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer4, modifier$iv4);
                                        Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                                        int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!($composer4.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        $composer4.startReusableNode();
                                        if ($composer4.getInserting()) {
                                            function10 = constructor4;
                                            $composer4.createNode(function10);
                                        } else {
                                            function10 = constructor4;
                                            $composer4.useNode();
                                        }
                                        Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer4);
                                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                                            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                                            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash4);
                                        }
                                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                                        int i7 = ($changed$iv$iv$iv4 >> 6) & 14;
                                        ComposerKt.sourceInformationMarkerStart($composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        int i8 = ((0 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart($composer4, 1398677055, "C1340@62569L10:DatePicker.kt#uh7d8r");
                                        function11.invoke($composer4, 0);
                                        ComposerKt.sourceInformationMarkerEnd($composer4);
                                        ComposerKt.sourceInformationMarkerEnd($composer4);
                                        $composer4.endNode();
                                        ComposerKt.sourceInformationMarkerEnd($composer4);
                                        ComposerKt.sourceInformationMarkerEnd($composer4);
                                        ComposerKt.sourceInformationMarkerEnd($composer4);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, $composer3, 54), $composer3, 48);
                            }
                            $composer3.endReplaceGroup();
                            $composer3.startReplaceGroup(-1287336668);
                            ComposerKt.sourceInformation($composer3, "1343@62668L8");
                            if (function8 != null) {
                                function8.invoke($composer3, 0);
                            }
                            $composer3.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.startReplaceGroup(1995137078);
                            ComposerKt.sourceInformation($composer3, "1347@62893L46");
                            if (function9 != null || function7 != null || function8 != null) {
                                DividerKt.m2446HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors.getDividerColor(), $composer3, 0, 3);
                            }
                            $composer3.endReplaceGroup();
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
                }, $composer2, 54), $composer2, ($dirty & 112) | 196614 | (($dirty >> 6) & 57344));
                function5.invoke($composer2, Integer.valueOf(($dirty >> 21) & 14));
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
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i4 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 133465402, "C1325@61799L1182,1319@61549L1432,1351@62990L9:DatePicker.kt#uh7d8r");
            m2401DatePickerHeaderpc5RIQQ(Modifier.INSTANCE, function2, colors.getTitleContentColor(), colors.getHeadlineContentColor(), headerMinHeight, ComposableLambdaKt.rememberComposableLambda(-229007058, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DateEntryContainer$2$1
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

                public final void invoke(Composer $composer3, int $changed2) {
                    Function0<ComposeUiNode> function1;
                    Arrangement.HorizontalOrVertical horizontalArrangement;
                    Function0<ComposeUiNode> function6;
                    ComposerKt.sourceInformation($composer3, "C1326@61813L1158:DatePicker.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-229007058, $changed2, -1, "androidx.compose.material3.DateEntryContainer.<anonymous>.<anonymous> (DatePicker.kt:1326)");
                        }
                        Modifier modifier$iv2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        final Function2<? super Composer, ? super Integer, Unit> function7 = function3;
                        Function2<Composer, Integer, Unit> function8 = function4;
                        Function2<Composer, Integer, Unit> function9 = function2;
                        DatePickerColors datePickerColors = colors;
                        TextStyle textStyle = headlineTextStyle;
                        ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                        int $changed$iv$iv2 = (6 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function1 = constructor2;
                            $composer3.createNode(function1);
                        } else {
                            function1 = constructor2;
                            $composer3.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer3);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                        int i6 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1718813547, "C1333@62174L520:DatePicker.kt#uh7d8r");
                        if (function7 == null || function8 == null) {
                            horizontalArrangement = function7 != null ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getEnd();
                        } else {
                            horizontalArrangement = Arrangement.INSTANCE.getSpaceBetween();
                        }
                        Modifier modifier$iv3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement, verticalAlignment$iv, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                        int $changed$iv$iv3 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                        Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function6 = constructor3;
                            $composer3.createNode(function6);
                        } else {
                            function6 = constructor3;
                            $composer3.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4027constructorimpl($composer3);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                        int i7 = ($changed$iv$iv$iv3 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                        int i8 = ((390 >> 6) & 112) | 6;
                        final RowScope $this$invoke_u24lambda_u241_u24lambda_u240 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1252979940, "C:DatePicker.kt#uh7d8r");
                        $composer3.startReplaceGroup(-1287344744);
                        ComposerKt.sourceInformation($composer3, "1339@62501L106,1339@62457L150");
                        if (function7 != null) {
                            TextKt.ProvideTextStyle(textStyle, ComposableLambdaKt.rememberComposableLambda(-962031352, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DateEntryContainer$2$1$1$1$1
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

                                public final void invoke(Composer $composer4, int $changed3) {
                                    Function0<ComposeUiNode> function10;
                                    ComposerKt.sourceInformation($composer4, "C1340@62531L50:DatePicker.kt#uh7d8r");
                                    if (($changed3 & 3) == 2 && $composer4.getSkipping()) {
                                        $composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-962031352, $changed3, -1, "androidx.compose.material3.DateEntryContainer.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1340)");
                                    }
                                    Modifier modifier$iv4 = RowScope.weight$default($this$invoke_u24lambda_u241_u24lambda_u240, Modifier.INSTANCE, 1.0f, false, 2, null);
                                    Function2<Composer, Integer, Unit> function11 = function7;
                                    ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                    MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                    int $changed$iv$iv4 = (0 << 3) & 112;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                                    CompositionLocalMap localMap$iv$iv5 = $composer4.getCurrentCompositionLocalMap();
                                    Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer4, modifier$iv4);
                                    Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                                    int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                    if (!($composer4.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    $composer4.startReusableNode();
                                    if ($composer4.getInserting()) {
                                        function10 = constructor4;
                                        $composer4.createNode(function10);
                                    } else {
                                        function10 = constructor4;
                                        $composer4.useNode();
                                    }
                                    Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer4);
                                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash4);
                                    }
                                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i9 = ($changed$iv$iv$iv4 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    int i10 = ((0 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer4, 1398677055, "C1340@62569L10:DatePicker.kt#uh7d8r");
                                    function11.invoke($composer4, 0);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    $composer4.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, $composer3, 54), $composer3, 48);
                        }
                        $composer3.endReplaceGroup();
                        $composer3.startReplaceGroup(-1287336668);
                        ComposerKt.sourceInformation($composer3, "1343@62668L8");
                        if (function8 != null) {
                            function8.invoke($composer3, 0);
                        }
                        $composer3.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.startReplaceGroup(1995137078);
                        ComposerKt.sourceInformation($composer3, "1347@62893L46");
                        if (function9 != null || function7 != null || function8 != null) {
                            DividerKt.m2446HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors.getDividerColor(), $composer3, 0, 3);
                        }
                        $composer3.endReplaceGroup();
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
            }, $composer2, 54), $composer2, ($dirty & 112) | 196614 | (($dirty >> 6) & 57344));
            function5.invoke($composer2, Integer.valueOf(($dirty >> 21) & 14));
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DateEntryContainer$3
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

                public final void invoke(Composer composer, int i5) {
                    DatePickerKt.m2400DateEntryContainerau3_HiA(modifier, function2, function3, function4, colors, headlineTextStyle, headerMinHeight, function5, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: renamed from: DisplayModeToggleButton-tER2X8s, reason: not valid java name */
    public static final void m2404DisplayModeToggleButtontER2X8s(final Modifier modifier, final int displayMode, final Function1<? super DisplayMode, Unit> function1, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        Composer $composer2 = $composer.startRestartGroup(1393846115);
        ComposerKt.sourceInformation($composer2, "C(DisplayModeToggleButton)P(1,0:c#material3.DisplayMode):DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(displayMode) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1393846115, $dirty2, -1, "androidx.compose.material3.DisplayModeToggleButton (DatePicker.kt:1361)");
            }
            if (DisplayMode.m2438equalsimpl0(displayMode, DisplayMode.INSTANCE.m2443getPickerjFl4v0())) {
                $composer2.startReplaceGroup(-411219388);
                ComposerKt.sourceInformation($composer2, "1363@63278L42,1363@63257L262");
                ComposerKt.sourceInformationMarkerStart($composer2, -428906382, "CC(remember):DatePicker.kt#9igjgp");
                boolean invalid$iv = ($dirty2 & 896) == 256;
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$DisplayModeToggleButton$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
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
                            function1.invoke(DisplayMode.m2435boximpl(DisplayMode.INSTANCE.m2442getInputjFl4v0()));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                IconButtonKt.IconButton((Function0) value$iv2, modifier, false, null, null, ComposableSingletons$DatePickerKt.INSTANCE.m2335getLambda1$material3_release(), $composer2, (($dirty2 << 3) & 112) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 28);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-410937381);
                ComposerKt.sourceInformation($composer2, "1370@63562L43,1370@63541L271");
                ComposerKt.sourceInformationMarkerStart($composer2, -428897293, "CC(remember):DatePicker.kt#9igjgp");
                boolean invalid$iv2 = ($dirty2 & 896) == 256;
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv = new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$DisplayModeToggleButton$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
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
                            function1.invoke(DisplayMode.m2435boximpl(DisplayMode.INSTANCE.m2443getPickerjFl4v0()));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                IconButtonKt.IconButton((Function0) value$iv, modifier, false, null, null, ComposableSingletons$DatePickerKt.INSTANCE.m2336getLambda2$material3_release(), $composer2, (($dirty2 << 3) & 112) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 28);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DisplayModeToggleButton$3
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
                    DatePickerKt.m2404DisplayModeToggleButtontER2X8s(modifier, displayMode, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: SwitchableDateEntryContent-d7iavvg, reason: not valid java name */
    public static final void m2405SwitchableDateEntryContentd7iavvg(final Long selectedDateMillis, final long displayedMonthMillis, final int displayMode, final Function1<? super Long, Unit> function1, final Function1<? super Long, Unit> function2, final CalendarModel calendarModel, final IntRange yearRange, final DatePickerFormatter dateFormatter, final SelectableDates selectableDates, final DatePickerColors colors, Composer $composer, final int $changed) throws Throwable {
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(-895379221);
        ComposerKt.sourceInformation($composer2, "C(SwitchableDateEntryContent)P(8,4,3:c#material3.DisplayMode,5,6!1,9,2,7)*1399@64608L7,1407@64864L2301,1451@67222L1081,1400@64644L3659:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(selectedDateMillis) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(displayedMonthMillis) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(displayMode) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(calendarModel) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(yearRange) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= (16777216 & $changed) == 0 ? $composer2.changed(dateFormatter) : $composer2.changedInstance(dateFormatter) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty |= $composer2.changed(selectableDates) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & $changed) == 0) {
            $dirty |= $composer2.changed(colors) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int $dirty2 = $dirty;
        if ((306783379 & $dirty2) == 306783378 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-895379221, $dirty2, -1, "androidx.compose.material3.SwitchableDateEntryContent (DatePicker.kt:1396)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$SwitchableDateEntryContent_d7iavvg_u24lambda_u247 = (Density) objConsume;
            final int parallaxTarget = -$this$SwitchableDateEntryContent_d7iavvg_u24lambda_u247.mo720roundToPx0680j_4(Dp.m7016constructorimpl(48));
            DisplayMode displayModeM2435boximpl = DisplayMode.m2435boximpl(displayMode);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.setContainer($this$semantics, true);
                }
            }, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -742566717, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(parallaxTarget);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<AnimatedContentTransitionScope<DisplayMode>, ContentTransform>() { // from class: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final ContentTransform invoke(AnimatedContentTransitionScope<DisplayMode> animatedContentTransitionScope) {
                        ContentTransform contentTransform;
                        if (DisplayMode.m2438equalsimpl0(animatedContentTransitionScope.getTargetState().getValue(), DisplayMode.INSTANCE.m2442getInputjFl4v0())) {
                            EnterTransition enterTransitionPlus = EnterExitTransitionKt.slideInVertically$default(null, new Function1<Integer, Integer>() { // from class: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$2$1.1
                                public final Integer invoke(int height) {
                                    return Integer.valueOf(height);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                                    return invoke(num.intValue());
                                }
                            }, 1, null).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(100, 100, null, 4, null), 0.0f, 2, null));
                            ExitTransition exitTransitionFadeOut$default = EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, 2, null);
                            final int i = parallaxTarget;
                            contentTransform = AnimatedContentKt.togetherWith(enterTransitionPlus, exitTransitionFadeOut$default.plus(EnterExitTransitionKt.slideOutVertically$default(null, new Function1<Integer, Integer>() { // from class: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$2$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                public final Integer invoke(int i2) {
                                    return Integer.valueOf(i);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                                    return invoke(num.intValue());
                                }
                            }, 1, null)));
                        } else {
                            TweenSpec tweenSpecTween$default = AnimationSpecKt.tween$default(0, 50, null, 5, null);
                            final int i2 = parallaxTarget;
                            contentTransform = AnimatedContentKt.togetherWith(EnterExitTransitionKt.slideInVertically(tweenSpecTween$default, new Function1<Integer, Integer>() { // from class: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$2$1.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                                    return invoke(num.intValue());
                                }

                                public final Integer invoke(int i3) {
                                    return Integer.valueOf(i2);
                                }
                            }).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(100, 100, null, 4, null), 0.0f, 2, null)), EnterExitTransitionKt.slideOutVertically$default(null, new Function1<Integer, Integer>() { // from class: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$2$1.4
                                public final Integer invoke(int fullHeight) {
                                    return Integer.valueOf(fullHeight);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                                    return invoke(num.intValue());
                                }
                            }, 1, null).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, 2, null)));
                        }
                        return animatedContentTransitionScope.using(contentTransform, AnimatedContentKt.SizeTransform(true, new Function2<IntSize, IntSize, FiniteAnimationSpec<IntSize>>() { // from class: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$2$1.5
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ FiniteAnimationSpec<IntSize> invoke(IntSize intSize, IntSize intSize2) {
                                return m2409invokeTemP2vQ(intSize.getPackedValue(), intSize2.getPackedValue());
                            }

                            /* JADX INFO: renamed from: invoke-TemP2vQ, reason: not valid java name */
                            public final FiniteAnimationSpec<IntSize> m2409invokeTemP2vQ(long j, long j2) {
                                return AnimationSpecKt.tween$default(500, 0, MotionTokens.INSTANCE.getEasingEmphasizedDecelerateCubicBezier(), 2, null);
                            }
                        }));
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AnimatedContentKt.AnimatedContent(displayModeM2435boximpl, modifierSemantics$default, (Function1) value$iv, null, "DatePickerDisplayModeAnimation", null, ComposableLambdaKt.rememberComposableLambda(-459778869, true, new Function4<AnimatedContentScope, DisplayMode, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, DisplayMode displayMode2, Composer composer, Integer num) {
                    m2410invokefYndouo(animatedContentScope, displayMode2.getValue(), composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-fYndouo, reason: not valid java name */
                public final void m2410invokefYndouo(AnimatedContentScope $this$AnimatedContent, int mode, Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "CP(0:c#material3.DisplayMode):DatePicker.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-459778869, $changed2, -1, "androidx.compose.material3.SwitchableDateEntryContent.<anonymous> (DatePicker.kt:1452)");
                    }
                    if (DisplayMode.m2438equalsimpl0(mode, DisplayMode.INSTANCE.m2443getPickerjFl4v0())) {
                        $composer3.startReplaceGroup(-1870116901);
                        ComposerKt.sourceInformation($composer3, "1454@67304L534");
                        DatePickerKt.DatePickerContent(selectedDateMillis, displayedMonthMillis, function1, function2, calendarModel, yearRange, dateFormatter, selectableDates, colors, $composer3, 0);
                        $composer3.endReplaceGroup();
                    } else if (DisplayMode.m2438equalsimpl0(mode, DisplayMode.INSTANCE.m2442getInputjFl4v0())) {
                        $composer3.startReplaceGroup(-1870098348);
                        ComposerKt.sourceInformation($composer3, "1466@67888L399");
                        DateInputKt.DateInputContent(selectedDateMillis, function1, calendarModel, yearRange, dateFormatter, selectableDates, colors, $composer3, 0);
                        $composer3.endReplaceGroup();
                    } else {
                        $composer3.startReplaceGroup(-2138080579);
                        $composer3.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, (($dirty2 >> 6) & 14) | 1597440, 40);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) throws Throwable {
                    DatePickerKt.m2405SwitchableDateEntryContentd7iavvg(selectedDateMillis, displayedMonthMillis, displayMode, function1, function2, calendarModel, yearRange, dateFormatter, selectableDates, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:109:0x0320  */
    /* JADX WARN: Code duplicated, block: B:112:0x0347  */
    /* JADX WARN: Code duplicated, block: B:115:0x0352  */
    /* JADX WARN: Code duplicated, block: B:116:0x0357  */
    /* JADX WARN: Code duplicated, block: B:120:0x038b  */
    /* JADX WARN: Code duplicated, block: B:124:0x0398 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:128:0x03c6  */
    /* JADX WARN: Code duplicated, block: B:132:0x03d4 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:136:0x0482  */
    /* JADX WARN: Code duplicated, block: B:139:0x048e  */
    /* JADX WARN: Code duplicated, block: B:140:0x0492  */
    /* JADX WARN: Code duplicated, block: B:143:0x04be  */
    /* JADX WARN: Code duplicated, block: B:147:0x04d2 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:151:0x0592  */
    /* JADX WARN: Code duplicated, block: B:154:0x059e  */
    /* JADX WARN: Code duplicated, block: B:155:0x05a4  */
    /* JADX WARN: Code duplicated, block: B:158:0x05d5  */
    /* JADX WARN: Code duplicated, block: B:161:0x05e8  */
    /* JADX WARN: Code duplicated, block: B:162:0x05eb  */
    /* JADX WARN: Code duplicated, block: B:166:0x0756  */
    public static final void DatePickerContent(final Long selectedDateMillis, final long displayedMonthMillis, final Function1<? super Long, Unit> function1, final Function1<? super Long, Unit> function2, final CalendarModel calendarModel, final IntRange yearRange, final DatePickerFormatter dateFormatter, final SelectableDates selectableDates, final DatePickerColors colors, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        Modifier modifier$iv;
        String str;
        String monthYear;
        boolean invalid$iv;
        Object value$iv;
        Composer $composer2;
        boolean invalid$iv2;
        Object value$iv2;
        boolean invalid$iv3;
        Object value$iv3;
        Composer $composer3;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        MeasurePolicy measurePolicy$iv;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor2;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        MeasurePolicy measurePolicy$iv2;
        Composer $composer4 = $composer.startRestartGroup(-434467002);
        ComposerKt.sourceInformation($composer4, "C(DatePickerContent)P(7,3,4,5!1,8,2,6)1494@68907L64,1497@69061L466,1497@69034L493,1509@69554L24,1510@69608L42,1511@69675L15,1512@69695L5031:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer4.changed(selectedDateMillis) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer4.changed(displayedMonthMillis) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer4.changedInstance(function1) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer4.changedInstance(function2) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer4.changedInstance(calendarModel) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer4.changedInstance(yearRange) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= (2097152 & $changed) == 0 ? $composer4.changed(dateFormatter) : $composer4.changedInstance(dateFormatter) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer4.changed(selectableDates) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty |= $composer4.changed(colors) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty2 = $dirty;
        if ((38347923 & $dirty2) != 38347922 || !$composer4.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-434467002, $dirty2, -1, "androidx.compose.material3.DatePickerContent (DatePicker.kt:1491)");
            }
            final CalendarMonth displayedMonth = calendarModel.getMonth(displayedMonthMillis);
            int monthIndex = RangesKt.coerceAtLeast(displayedMonth.indexIn(yearRange), 0);
            final LazyListState monthsListState = LazyListStateKt.rememberLazyListState(monthIndex, 0, $composer4, 0, 2);
            Integer numValueOf = Integer.valueOf(monthIndex);
            ComposerKt.sourceInformationMarkerStart($composer4, -1235295380, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv4 = $composer4.changed(monthsListState) | $composer4.changed(monthIndex);
            DatePickerKt$DatePickerContent$1$1 value$iv4 = $composer4.rememberedValue();
            if (invalid$iv4 || value$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = new DatePickerKt$DatePickerContent$1$1(monthsListState, monthIndex, null);
                $composer4.updateRememberedValue(value$iv4);
            }
            ComposerKt.sourceInformationMarkerEnd($composer4);
            EffectsKt.LaunchedEffect(numValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv4, $composer4, 0);
            ComposerKt.sourceInformationMarkerStart($composer4, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer4, -954363344, "CC(remember):Effects.kt#9igjgp");
            Object value$iv$iv = $composer4.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer4));
                $composer4.updateRememberedValue(value$iv$iv);
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            final CoroutineScope coroutineScope = wrapper$iv.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer4);
            final MutableState yearPickerVisible$delegate = (MutableState) RememberSaveableKt.m4120rememberSaveable(new Object[0], (Saver) null, (String) null, (Function0) new Function0<MutableState<Boolean>>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$yearPickerVisible$2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final MutableState<Boolean> invoke() {
                    return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                }
            }, $composer4, 3072, 6);
            Locale defaultLocale = CalendarLocale_androidKt.defaultLocale($composer4, 0);
            ComposerKt.sourceInformationMarkerStart($composer4, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Modifier modifier$iv2 = Modifier.INSTANCE;
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv3 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
            CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv2);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer4.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer4.startReusableNode();
            if ($composer4.getInserting()) {
                function0 = constructor3;
                $composer4.createNode(function0);
            } else {
                function0 = constructor3;
                $composer4.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4027constructorimpl($composer4);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                modifier$iv = modifier$iv2;
                str = "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo";
            } else {
                modifier$iv = modifier$iv2;
                str = "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo";
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i2 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer4, -1436856422, "C1523@70222L511,1535@70767L520,1547@71329L42,1513@69712L1698,1551@71420L3300:DatePicker.kt#uh7d8r");
                Modifier modifierM1036paddingVpY3zN4$default = PaddingKt.m1036paddingVpY3zN4$default(Modifier.INSTANCE, DatePickerHorizontalPadding, 0.0f, 2, null);
                boolean canScrollForward = monthsListState.getCanScrollForward();
                boolean canScrollBackward = monthsListState.getCanScrollBackward();
                boolean zDatePickerContent$lambda$10 = DatePickerContent$lambda$10(yearPickerVisible$delegate);
                monthYear = dateFormatter.formatMonthYear(Long.valueOf(displayedMonthMillis), defaultLocale);
                if (monthYear == null) {
                    monthYear = "-";
                }
                ComposerKt.sourceInformationMarkerStart($composer4, 230756280, "CC(remember):DatePicker.kt#9igjgp");
                invalid$iv = $composer4.changedInstance(coroutineScope) | $composer4.changed(monthsListState);
                value$iv = $composer4.rememberedValue();
                if (!invalid$iv) {
                    $composer2 = $composer4;
                    if (value$iv == Composer.INSTANCE.getEmpty()) {
                    }
                    Function0 function3 = (Function0) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerStart($composer4, 230773729, "CC(remember):DatePicker.kt#9igjgp");
                    invalid$iv2 = $composer4.changedInstance(coroutineScope) | $composer4.changed(monthsListState);
                    Object it$iv = $composer4.rememberedValue();
                    if (!invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$2$1$1, reason: invalid class name */
                            /* JADX INFO: compiled from: DatePicker.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$2$1$1", f = "DatePicker.kt", i = {}, l = {1539}, m = "invokeSuspend", n = {}, s = {})
                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ LazyListState $monthsListState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                AnonymousClass1(LazyListState lazyListState, Continuation<? super AnonymousClass1> continuation) {
                                    super(2, continuation);
                                    this.$monthsListState = lazyListState;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new AnonymousClass1(this.$monthsListState, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object $result) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch (this.label) {
                                        case 0:
                                            ResultKt.throwOnFailure($result);
                                            try {
                                                this.label = 1;
                                                if (LazyListState.animateScrollToItem$default(this.$monthsListState, this.$monthsListState.getFirstVisibleItemIndex() - 1, 0, this, 2, null) == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                            } catch (IllegalArgumentException e) {
                                            }
                                            break;
                                        case 1:
                                            try {
                                                ResultKt.throwOnFailure($result);
                                            } catch (IllegalArgumentException e2) {
                                            }
                                            break;
                                        default:
                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    return Unit.INSTANCE;
                                }
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(monthsListState, null), 3, null);
                            }
                        };
                        $composer4.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv;
                    }
                    Function0 function4 = (Function0) value$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerStart($composer4, 230791235, "CC(remember):DatePicker.kt#9igjgp");
                    invalid$iv3 = $composer4.changed(yearPickerVisible$delegate);
                    value$iv3 = $composer4.rememberedValue();
                    if (!invalid$iv3 || value$iv3 == Composer.INSTANCE.getEmpty()) {
                        value$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$3$1
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
                                DatePickerKt.DatePickerContent$lambda$11(yearPickerVisible$delegate, !DatePickerKt.DatePickerContent$lambda$10(yearPickerVisible$delegate));
                            }
                        };
                        $composer4.updateRememberedValue(value$iv3);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    String str2 = str;
                    $composer3 = $composer2;
                    MonthsNavigation(modifierM1036paddingVpY3zN4$default, canScrollForward, canScrollBackward, zDatePickerContent$lambda$10, monthYear, function3, function4, (Function0) value$iv3, colors, $composer4, ($dirty2 & 234881024) | 6);
                    ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Modifier modifier$iv3 = Modifier.INSTANCE;
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv2 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer4, modifier$iv3);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        $composer4.createNode(constructor);
                    } else {
                        $composer4.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer4);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i4 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, 2145982723, "C1552@71438L683,1571@72420L2290,1566@72161L2549:DatePicker.kt#uh7d8r");
                    Modifier modifier$iv4 = PaddingKt.m1036paddingVpY3zN4$default(Modifier.INSTANCE, DatePickerHorizontalPadding, 0.0f, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer4, -483455358, str2);
                    Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
                    measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer4, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                    int $changed$iv$iv3 = (6 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv3 = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer4, modifier$iv4);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
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
                    $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer4);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                        measurePolicy$iv2 = measurePolicy$iv;
                    } else {
                        measurePolicy$iv2 = measurePolicy$iv;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                        int i5 = ($changed$iv$iv$iv3 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                        int i6 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 455260175, "C1553@71534L31,1554@71582L525:DatePicker.kt#uh7d8r");
                        WeekDays(colors, calendarModel, $composer4, (($dirty2 >> 24) & 14) | (($dirty2 >> 9) & 112));
                        HorizontalMonthsList(monthsListState, selectedDateMillis, function1, function2, calendarModel, yearRange, dateFormatter, selectableDates, colors, $composer4, (($dirty2 << 3) & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        AnimatedVisibilityKt.AnimatedVisibility(DatePickerContent$lambda$10(yearPickerVisible$delegate), ClipKt.clipToBounds(Modifier.INSTANCE), EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.6f, 1, null)), EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1193716082, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                                invoke(animatedVisibilityScope, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer5, int $changed2) {
                                Object value$iv5;
                                Object value$iv6;
                                ComposerKt.sourceInformation($composer5, "C1576@72744L48,1577@72846L30,1577@72809L1887:DatePicker.kt#uh7d8r");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1193716082, $changed2, -1, "androidx.compose.material3.DatePickerContent.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1576)");
                                }
                                Strings.Companion companion = Strings.INSTANCE;
                                final String yearsPaneTitle = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_year_picker_pane_title), $composer5, 0);
                                Modifier.Companion companion2 = Modifier.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart($composer5, 14727253, "CC(remember):DatePicker.kt#9igjgp");
                                boolean invalid$iv5 = $composer5.changed(yearsPaneTitle);
                                Object it$iv2 = $composer5.rememberedValue();
                                if (invalid$iv5 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv5 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$1$1
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
                                            SemanticsPropertiesKt.setPaneTitle($this$semantics, yearsPaneTitle);
                                        }
                                    };
                                    $composer5.updateRememberedValue(value$iv5);
                                } else {
                                    value$iv5 = it$iv2;
                                }
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                Modifier modifier$iv5 = SemanticsModifierKt.semantics$default(companion2, false, (Function1) value$iv5, 1, null);
                                long j = displayedMonthMillis;
                                final MutableState<Boolean> mutableState = yearPickerVisible$delegate;
                                final CoroutineScope coroutineScope2 = coroutineScope;
                                final LazyListState lazyListState = monthsListState;
                                final IntRange intRange = yearRange;
                                final CalendarMonth calendarMonth = displayedMonth;
                                SelectableDates selectableDates2 = selectableDates;
                                CalendarModel calendarModel2 = calendarModel;
                                DatePickerColors datePickerColors = colors;
                                ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                                Arrangement.Vertical verticalArrangement$iv3 = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv3 = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv5 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv3, horizontalAlignment$iv3, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                int $changed$iv$iv4 = (0 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                CompositionLocalMap localMap$iv$iv4 = $composer5.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer5, modifier$iv5);
                                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                                int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!($composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer5.startReusableNode();
                                if ($composer5.getInserting()) {
                                    $composer5.createNode(constructor4);
                                } else {
                                    $composer5.useNode();
                                }
                                Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer5);
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash4);
                                }
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                                int i7 = ($changed$iv$iv$iv4 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                                int i8 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer5, 536387483, "C1589@73620L767,1578@72900L1711,1606@74632L46:DatePicker.kt#uh7d8r");
                                Modifier.Companion companion3 = Modifier.INSTANCE;
                                float arg0$iv = DatePickerKt.getRecommendedSizeForAccessibility();
                                float arg0$iv2 = Dp.m7016constructorimpl(7 * arg0$iv);
                                float other$iv = DividerDefaults.INSTANCE.m2444getThicknessD9Ej5fM();
                                Modifier modifierM1036paddingVpY3zN4$default2 = PaddingKt.m1036paddingVpY3zN4$default(SizeKt.m1068requiredHeight3ABfNKs(companion3, Dp.m7016constructorimpl(arg0$iv2 - other$iv)), DatePickerKt.getDatePickerHorizontalPadding(), 0.0f, 2, null);
                                ComposerKt.sourceInformationMarkerStart($composer5, -2060885131, "CC(remember):DatePicker.kt#9igjgp");
                                boolean invalid$iv6 = $composer5.changed(mutableState) | $composer5.changedInstance(coroutineScope2) | $composer5.changed(lazyListState) | $composer5.changedInstance(intRange) | $composer5.changed(calendarMonth);
                                Object it$iv3 = $composer5.rememberedValue();
                                if (invalid$iv6 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                                    value$iv6 = new Function1<Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                            invoke(num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1, reason: invalid class name */
                                        /* JADX INFO: compiled from: DatePicker.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                        @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1", f = "DatePicker.kt", i = {}, l = {1597}, m = "invokeSuspend", n = {}, s = {})
                                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            final /* synthetic */ CalendarMonth $displayedMonth;
                                            final /* synthetic */ LazyListState $monthsListState;
                                            final /* synthetic */ int $year;
                                            final /* synthetic */ IntRange $yearRange;
                                            int label;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            AnonymousClass1(LazyListState lazyListState, int i, IntRange intRange, CalendarMonth calendarMonth, Continuation<? super AnonymousClass1> continuation) {
                                                super(2, continuation);
                                                this.$monthsListState = lazyListState;
                                                this.$year = i;
                                                this.$yearRange = intRange;
                                                this.$displayedMonth = calendarMonth;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new AnonymousClass1(this.$monthsListState, this.$year, this.$yearRange, this.$displayedMonth, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Object invokeSuspend(Object $result) {
                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                switch (this.label) {
                                                    case 0:
                                                        ResultKt.throwOnFailure($result);
                                                        this.label = 1;
                                                        if (LazyListState.scrollToItem$default(this.$monthsListState, (((this.$year - this.$yearRange.getFirst()) * 12) + this.$displayedMonth.getMonth()) - 1, 0, this, 2, null) == coroutine_suspended) {
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

                                        public final void invoke(int year) {
                                            DatePickerKt.DatePickerContent$lambda$11(mutableState, !DatePickerKt.DatePickerContent$lambda$10(mutableState));
                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(lazyListState, year, intRange, calendarMonth, null), 3, null);
                                        }
                                    };
                                    $composer5.updateRememberedValue(value$iv6);
                                } else {
                                    value$iv6 = it$iv3;
                                }
                                ComposerKt.sourceInformationMarkerEnd(r12);
                                DatePickerKt.YearPicker(modifierM1036paddingVpY3zN4$default2, j, (Function1) value$iv6, selectableDates2, calendarModel2, intRange, datePickerColors, r12, 6);
                                DividerKt.m2446HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors.getDividerColor(), r12, 0, 3);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                $composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer4, 54), $composer4, 200112, 16);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i7 = ($changed$iv$iv$iv3 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                    int i8 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, 455260175, "C1553@71534L31,1554@71582L525:DatePicker.kt#uh7d8r");
                    WeekDays(colors, calendarModel, $composer4, (($dirty2 >> 24) & 14) | (($dirty2 >> 9) & 112));
                    HorizontalMonthsList(monthsListState, selectedDateMillis, function1, function2, calendarModel, yearRange, dateFormatter, selectableDates, colors, $composer4, (($dirty2 << 3) & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2));
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    AnimatedVisibilityKt.AnimatedVisibility(DatePickerContent$lambda$10(yearPickerVisible$delegate), ClipKt.clipToBounds(Modifier.INSTANCE), EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.6f, 1, null)), EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1193716082, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                            invoke(animatedVisibilityScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer5, int $changed2) {
                            Object value$iv5;
                            Object value$iv6;
                            ComposerKt.sourceInformation($composer5, "C1576@72744L48,1577@72846L30,1577@72809L1887:DatePicker.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1193716082, $changed2, -1, "androidx.compose.material3.DatePickerContent.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1576)");
                            }
                            Strings.Companion companion = Strings.INSTANCE;
                            final String yearsPaneTitle = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_year_picker_pane_title), $composer5, 0);
                            Modifier.Companion companion2 = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer5, 14727253, "CC(remember):DatePicker.kt#9igjgp");
                            boolean invalid$iv5 = $composer5.changed(yearsPaneTitle);
                            Object it$iv2 = $composer5.rememberedValue();
                            if (invalid$iv5 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                value$iv5 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$1$1
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
                                        SemanticsPropertiesKt.setPaneTitle($this$semantics, yearsPaneTitle);
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv5);
                            } else {
                                value$iv5 = it$iv2;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            Modifier modifier$iv5 = SemanticsModifierKt.semantics$default(companion2, false, (Function1) value$iv5, 1, null);
                            long j = displayedMonthMillis;
                            final MutableState<Boolean> mutableState = yearPickerVisible$delegate;
                            final CoroutineScope coroutineScope2 = coroutineScope;
                            final LazyListState lazyListState = monthsListState;
                            final IntRange intRange = yearRange;
                            final CalendarMonth calendarMonth = displayedMonth;
                            SelectableDates selectableDates2 = selectableDates;
                            CalendarModel calendarModel2 = calendarModel;
                            DatePickerColors datePickerColors = colors;
                            ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv3 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv3 = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv5 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv3, horizontalAlignment$iv3, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv4 = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                            CompositionLocalMap localMap$iv$iv4 = $composer5.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer5, modifier$iv5);
                            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer5.startReusableNode();
                            if ($composer5.getInserting()) {
                                $composer5.createNode(constructor4);
                            } else {
                                $composer5.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer5);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash4);
                            }
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                            int i9 = ($changed$iv$iv$iv4 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                            int i10 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, 536387483, "C1589@73620L767,1578@72900L1711,1606@74632L46:DatePicker.kt#uh7d8r");
                            Modifier.Companion companion3 = Modifier.INSTANCE;
                            float arg0$iv = DatePickerKt.getRecommendedSizeForAccessibility();
                            float arg0$iv2 = Dp.m7016constructorimpl(7 * arg0$iv);
                            float other$iv = DividerDefaults.INSTANCE.m2444getThicknessD9Ej5fM();
                            Modifier modifierM1036paddingVpY3zN4$default2 = PaddingKt.m1036paddingVpY3zN4$default(SizeKt.m1068requiredHeight3ABfNKs(companion3, Dp.m7016constructorimpl(arg0$iv2 - other$iv)), DatePickerKt.getDatePickerHorizontalPadding(), 0.0f, 2, null);
                            ComposerKt.sourceInformationMarkerStart($composer5, -2060885131, "CC(remember):DatePicker.kt#9igjgp");
                            boolean invalid$iv6 = $composer5.changed(mutableState) | $composer5.changedInstance(coroutineScope2) | $composer5.changed(lazyListState) | $composer5.changedInstance(intRange) | $composer5.changed(calendarMonth);
                            Object it$iv3 = $composer5.rememberedValue();
                            if (invalid$iv6 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                                value$iv6 = new Function1<Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                        invoke(num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1, reason: invalid class name */
                                    /* JADX INFO: compiled from: DatePicker.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                    @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1", f = "DatePicker.kt", i = {}, l = {1597}, m = "invokeSuspend", n = {}, s = {})
                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        final /* synthetic */ CalendarMonth $displayedMonth;
                                        final /* synthetic */ LazyListState $monthsListState;
                                        final /* synthetic */ int $year;
                                        final /* synthetic */ IntRange $yearRange;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        AnonymousClass1(LazyListState lazyListState, int i, IntRange intRange, CalendarMonth calendarMonth, Continuation<? super AnonymousClass1> continuation) {
                                            super(2, continuation);
                                            this.$monthsListState = lazyListState;
                                            this.$year = i;
                                            this.$yearRange = intRange;
                                            this.$displayedMonth = calendarMonth;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new AnonymousClass1(this.$monthsListState, this.$year, this.$yearRange, this.$displayedMonth, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object $result) {
                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            switch (this.label) {
                                                case 0:
                                                    ResultKt.throwOnFailure($result);
                                                    this.label = 1;
                                                    if (LazyListState.scrollToItem$default(this.$monthsListState, (((this.$year - this.$yearRange.getFirst()) * 12) + this.$displayedMonth.getMonth()) - 1, 0, this, 2, null) == coroutine_suspended) {
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

                                    public final void invoke(int year) {
                                        DatePickerKt.DatePickerContent$lambda$11(mutableState, !DatePickerKt.DatePickerContent$lambda$10(mutableState));
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(lazyListState, year, intRange, calendarMonth, null), 3, null);
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv6);
                            } else {
                                value$iv6 = it$iv3;
                            }
                            ComposerKt.sourceInformationMarkerEnd(r12);
                            DatePickerKt.YearPicker(modifierM1036paddingVpY3zN4$default2, j, (Function1) value$iv6, selectableDates2, calendarModel2, intRange, datePickerColors, r12, 6);
                            DividerKt.m2446HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors.getDividerColor(), r12, 0, 3);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            $composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer4, 54), $composer4, 200112, 16);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer2 = $composer4;
                }
                value$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$1$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: DatePicker.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$1$1$1", f = "DatePicker.kt", i = {}, l = {1527}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ LazyListState $monthsListState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(LazyListState lazyListState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$monthsListState = lazyListState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$monthsListState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    try {
                                        this.label = 1;
                                        if (LazyListState.animateScrollToItem$default(this.$monthsListState, this.$monthsListState.getFirstVisibleItemIndex() + 1, 0, this, 2, null) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    } catch (IllegalArgumentException e) {
                                    }
                                    break;
                                case 1:
                                    try {
                                        ResultKt.throwOnFailure($result);
                                    } catch (IllegalArgumentException e2) {
                                    }
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(monthsListState, null), 3, null);
                    }
                };
                $composer4.updateRememberedValue(value$iv);
                Function0 function5 = (Function0) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, 230773729, "CC(remember):DatePicker.kt#9igjgp");
                invalid$iv2 = $composer4.changedInstance(coroutineScope) | $composer4.changed(monthsListState);
                Object it$iv2 = $composer4.rememberedValue();
                if (!invalid$iv2) {
                }
                value$iv2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$2$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: DatePicker.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$2$1$1", f = "DatePicker.kt", i = {}, l = {1539}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ LazyListState $monthsListState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(LazyListState lazyListState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$monthsListState = lazyListState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$monthsListState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    try {
                                        this.label = 1;
                                        if (LazyListState.animateScrollToItem$default(this.$monthsListState, this.$monthsListState.getFirstVisibleItemIndex() - 1, 0, this, 2, null) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    } catch (IllegalArgumentException e) {
                                    }
                                    break;
                                case 1:
                                    try {
                                        ResultKt.throwOnFailure($result);
                                    } catch (IllegalArgumentException e2) {
                                    }
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(monthsListState, null), 3, null);
                    }
                };
                $composer4.updateRememberedValue(value$iv2);
                Function0 function6 = (Function0) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, 230791235, "CC(remember):DatePicker.kt#9igjgp");
                invalid$iv3 = $composer4.changed(yearPickerVisible$delegate);
                value$iv3 = $composer4.rememberedValue();
                if (!invalid$iv3) {
                }
                value$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$3$1
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
                        DatePickerKt.DatePickerContent$lambda$11(yearPickerVisible$delegate, !DatePickerKt.DatePickerContent$lambda$10(yearPickerVisible$delegate));
                    }
                };
                $composer4.updateRememberedValue(value$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                String str3 = str;
                $composer3 = $composer2;
                MonthsNavigation(modifierM1036paddingVpY3zN4$default, canScrollForward, canScrollBackward, zDatePickerContent$lambda$10, monthYear, function5, function6, (Function0) value$iv3, colors, $composer4, ($dirty2 & 234881024) | 6);
                ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Modifier modifier$iv5 = Modifier.INSTANCE;
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                int $changed$iv$iv4 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                CompositionLocalMap localMap$iv$iv4 = $composer4.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer4, modifier$iv5);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer4.startReusableNode();
                if ($composer4.getInserting()) {
                    $composer4.createNode(constructor);
                } else {
                    $composer4.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer4);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash4);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                int i9 = ($changed$iv$iv$iv4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i10 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer4, 2145982723, "C1552@71438L683,1571@72420L2290,1566@72161L2549:DatePicker.kt#uh7d8r");
                Modifier modifier$iv6 = PaddingKt.m1036paddingVpY3zN4$default(Modifier.INSTANCE, DatePickerHorizontalPadding, 0.0f, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer4, -483455358, str3);
                Arrangement.Vertical verticalArrangement$iv3 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv3 = Alignment.INSTANCE.getStart();
                measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv3, horizontalAlignment$iv3, $composer4, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                int $changed$iv$iv5 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                CompositionLocalMap localMap$iv$iv5 = $composer4.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer4, modifier$iv6);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 6) & 896) | 6;
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
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer4);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    measurePolicy$iv2 = measurePolicy$iv;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                    int i11 = ($changed$iv$iv$iv5 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                    int i12 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, 455260175, "C1553@71534L31,1554@71582L525:DatePicker.kt#uh7d8r");
                    WeekDays(colors, calendarModel, $composer4, (($dirty2 >> 24) & 14) | (($dirty2 >> 9) & 112));
                    HorizontalMonthsList(monthsListState, selectedDateMillis, function1, function2, calendarModel, yearRange, dateFormatter, selectableDates, colors, $composer4, (($dirty2 << 3) & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2));
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    AnimatedVisibilityKt.AnimatedVisibility(DatePickerContent$lambda$10(yearPickerVisible$delegate), ClipKt.clipToBounds(Modifier.INSTANCE), EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.6f, 1, null)), EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1193716082, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                            invoke(animatedVisibilityScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer5, int $changed2) {
                            Object value$iv5;
                            Object value$iv6;
                            ComposerKt.sourceInformation($composer5, "C1576@72744L48,1577@72846L30,1577@72809L1887:DatePicker.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1193716082, $changed2, -1, "androidx.compose.material3.DatePickerContent.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1576)");
                            }
                            Strings.Companion companion = Strings.INSTANCE;
                            final String yearsPaneTitle = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_year_picker_pane_title), $composer5, 0);
                            Modifier.Companion companion2 = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer5, 14727253, "CC(remember):DatePicker.kt#9igjgp");
                            boolean invalid$iv5 = $composer5.changed(yearsPaneTitle);
                            Object it$iv3 = $composer5.rememberedValue();
                            if (invalid$iv5 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                                value$iv5 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$1$1
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
                                        SemanticsPropertiesKt.setPaneTitle($this$semantics, yearsPaneTitle);
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv5);
                            } else {
                                value$iv5 = it$iv3;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            Modifier modifier$iv7 = SemanticsModifierKt.semantics$default(companion2, false, (Function1) value$iv5, 1, null);
                            long j = displayedMonthMillis;
                            final MutableState<Boolean> mutableState = yearPickerVisible$delegate;
                            final CoroutineScope coroutineScope2 = coroutineScope;
                            final LazyListState lazyListState = monthsListState;
                            final IntRange intRange = yearRange;
                            final CalendarMonth calendarMonth = displayedMonth;
                            SelectableDates selectableDates2 = selectableDates;
                            CalendarModel calendarModel2 = calendarModel;
                            DatePickerColors datePickerColors = colors;
                            ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv4 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv4 = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv6 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv4, horizontalAlignment$iv4, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv6 = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                            CompositionLocalMap localMap$iv$iv6 = $composer5.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer5, modifier$iv7);
                            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv6 = (($changed$iv$iv6 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer5.startReusableNode();
                            if ($composer5.getInserting()) {
                                $composer5.createNode(constructor4);
                            } else {
                                $composer5.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer5);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash6);
                            }
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv6, ComposeUiNode.INSTANCE.getSetModifier());
                            int i13 = ($changed$iv$iv$iv6 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                            int i14 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, 536387483, "C1589@73620L767,1578@72900L1711,1606@74632L46:DatePicker.kt#uh7d8r");
                            Modifier.Companion companion3 = Modifier.INSTANCE;
                            float arg0$iv = DatePickerKt.getRecommendedSizeForAccessibility();
                            float arg0$iv2 = Dp.m7016constructorimpl(7 * arg0$iv);
                            float other$iv = DividerDefaults.INSTANCE.m2444getThicknessD9Ej5fM();
                            Modifier modifierM1036paddingVpY3zN4$default2 = PaddingKt.m1036paddingVpY3zN4$default(SizeKt.m1068requiredHeight3ABfNKs(companion3, Dp.m7016constructorimpl(arg0$iv2 - other$iv)), DatePickerKt.getDatePickerHorizontalPadding(), 0.0f, 2, null);
                            ComposerKt.sourceInformationMarkerStart($composer5, -2060885131, "CC(remember):DatePicker.kt#9igjgp");
                            boolean invalid$iv6 = $composer5.changed(mutableState) | $composer5.changedInstance(coroutineScope2) | $composer5.changed(lazyListState) | $composer5.changedInstance(intRange) | $composer5.changed(calendarMonth);
                            Object it$iv4 = $composer5.rememberedValue();
                            if (invalid$iv6 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                                value$iv6 = new Function1<Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                        invoke(num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1, reason: invalid class name */
                                    /* JADX INFO: compiled from: DatePicker.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                    @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1", f = "DatePicker.kt", i = {}, l = {1597}, m = "invokeSuspend", n = {}, s = {})
                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        final /* synthetic */ CalendarMonth $displayedMonth;
                                        final /* synthetic */ LazyListState $monthsListState;
                                        final /* synthetic */ int $year;
                                        final /* synthetic */ IntRange $yearRange;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        AnonymousClass1(LazyListState lazyListState, int i, IntRange intRange, CalendarMonth calendarMonth, Continuation<? super AnonymousClass1> continuation) {
                                            super(2, continuation);
                                            this.$monthsListState = lazyListState;
                                            this.$year = i;
                                            this.$yearRange = intRange;
                                            this.$displayedMonth = calendarMonth;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new AnonymousClass1(this.$monthsListState, this.$year, this.$yearRange, this.$displayedMonth, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object $result) {
                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            switch (this.label) {
                                                case 0:
                                                    ResultKt.throwOnFailure($result);
                                                    this.label = 1;
                                                    if (LazyListState.scrollToItem$default(this.$monthsListState, (((this.$year - this.$yearRange.getFirst()) * 12) + this.$displayedMonth.getMonth()) - 1, 0, this, 2, null) == coroutine_suspended) {
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

                                    public final void invoke(int year) {
                                        DatePickerKt.DatePickerContent$lambda$11(mutableState, !DatePickerKt.DatePickerContent$lambda$10(mutableState));
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(lazyListState, year, intRange, calendarMonth, null), 3, null);
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv6);
                            } else {
                                value$iv6 = it$iv4;
                            }
                            ComposerKt.sourceInformationMarkerEnd(r12);
                            DatePickerKt.YearPicker(modifierM1036paddingVpY3zN4$default2, j, (Function1) value$iv6, selectableDates2, calendarModel2, intRange, datePickerColors, r12, 6);
                            DividerKt.m2446HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors.getDividerColor(), r12, 0, 3);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            $composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer4, 54), $composer4, 200112, 16);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    measurePolicy$iv2 = measurePolicy$iv;
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash5);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                int i13 = ($changed$iv$iv$iv5 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                int i14 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer4, 455260175, "C1553@71534L31,1554@71582L525:DatePicker.kt#uh7d8r");
                WeekDays(colors, calendarModel, $composer4, (($dirty2 >> 24) & 14) | (($dirty2 >> 9) & 112));
                HorizontalMonthsList(monthsListState, selectedDateMillis, function1, function2, calendarModel, yearRange, dateFormatter, selectableDates, colors, $composer4, (($dirty2 << 3) & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2));
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                AnimatedVisibilityKt.AnimatedVisibility(DatePickerContent$lambda$10(yearPickerVisible$delegate), ClipKt.clipToBounds(Modifier.INSTANCE), EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.6f, 1, null)), EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1193716082, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                        invoke(animatedVisibilityScope, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer5, int $changed2) {
                        Object value$iv5;
                        Object value$iv6;
                        ComposerKt.sourceInformation($composer5, "C1576@72744L48,1577@72846L30,1577@72809L1887:DatePicker.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1193716082, $changed2, -1, "androidx.compose.material3.DatePickerContent.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1576)");
                        }
                        Strings.Companion companion = Strings.INSTANCE;
                        final String yearsPaneTitle = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_year_picker_pane_title), $composer5, 0);
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer5, 14727253, "CC(remember):DatePicker.kt#9igjgp");
                        boolean invalid$iv5 = $composer5.changed(yearsPaneTitle);
                        Object it$iv3 = $composer5.rememberedValue();
                        if (invalid$iv5 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv5 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$1$1
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
                                    SemanticsPropertiesKt.setPaneTitle($this$semantics, yearsPaneTitle);
                                }
                            };
                            $composer5.updateRememberedValue(value$iv5);
                        } else {
                            value$iv5 = it$iv3;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        Modifier modifier$iv7 = SemanticsModifierKt.semantics$default(companion2, false, (Function1) value$iv5, 1, null);
                        long j = displayedMonthMillis;
                        final MutableState<Boolean> mutableState = yearPickerVisible$delegate;
                        final CoroutineScope coroutineScope2 = coroutineScope;
                        final LazyListState lazyListState = monthsListState;
                        final IntRange intRange = yearRange;
                        final CalendarMonth calendarMonth = displayedMonth;
                        SelectableDates selectableDates2 = selectableDates;
                        CalendarModel calendarModel2 = calendarModel;
                        DatePickerColors datePickerColors = colors;
                        ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv4 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv4 = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv6 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv4, horizontalAlignment$iv4, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv6 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                        CompositionLocalMap localMap$iv$iv6 = $composer5.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer5, modifier$iv7);
                        Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv6 = (($changed$iv$iv6 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer5.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer5.startReusableNode();
                        if ($composer5.getInserting()) {
                            $composer5.createNode(constructor4);
                        } else {
                            $composer5.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer5);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash6);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv6, ComposeUiNode.INSTANCE.getSetModifier());
                        int i15 = ($changed$iv$iv$iv6 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
                        int i16 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer5, 536387483, "C1589@73620L767,1578@72900L1711,1606@74632L46:DatePicker.kt#uh7d8r");
                        Modifier.Companion companion3 = Modifier.INSTANCE;
                        float arg0$iv = DatePickerKt.getRecommendedSizeForAccessibility();
                        float arg0$iv2 = Dp.m7016constructorimpl(7 * arg0$iv);
                        float other$iv = DividerDefaults.INSTANCE.m2444getThicknessD9Ej5fM();
                        Modifier modifierM1036paddingVpY3zN4$default2 = PaddingKt.m1036paddingVpY3zN4$default(SizeKt.m1068requiredHeight3ABfNKs(companion3, Dp.m7016constructorimpl(arg0$iv2 - other$iv)), DatePickerKt.getDatePickerHorizontalPadding(), 0.0f, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer5, -2060885131, "CC(remember):DatePicker.kt#9igjgp");
                        boolean invalid$iv6 = $composer5.changed(mutableState) | $composer5.changedInstance(coroutineScope2) | $composer5.changed(lazyListState) | $composer5.changedInstance(intRange) | $composer5.changed(calendarMonth);
                        Object it$iv4 = $composer5.rememberedValue();
                        if (invalid$iv6 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                            value$iv6 = new Function1<Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                    invoke(num.intValue());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1, reason: invalid class name */
                                /* JADX INFO: compiled from: DatePicker.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1", f = "DatePicker.kt", i = {}, l = {1597}, m = "invokeSuspend", n = {}, s = {})
                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ CalendarMonth $displayedMonth;
                                    final /* synthetic */ LazyListState $monthsListState;
                                    final /* synthetic */ int $year;
                                    final /* synthetic */ IntRange $yearRange;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(LazyListState lazyListState, int i, IntRange intRange, CalendarMonth calendarMonth, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.$monthsListState = lazyListState;
                                        this.$year = i;
                                        this.$yearRange = intRange;
                                        this.$displayedMonth = calendarMonth;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass1(this.$monthsListState, this.$year, this.$yearRange, this.$displayedMonth, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object $result) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch (this.label) {
                                            case 0:
                                                ResultKt.throwOnFailure($result);
                                                this.label = 1;
                                                if (LazyListState.scrollToItem$default(this.$monthsListState, (((this.$year - this.$yearRange.getFirst()) * 12) + this.$displayedMonth.getMonth()) - 1, 0, this, 2, null) == coroutine_suspended) {
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

                                public final void invoke(int year) {
                                    DatePickerKt.DatePickerContent$lambda$11(mutableState, !DatePickerKt.DatePickerContent$lambda$10(mutableState));
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(lazyListState, year, intRange, calendarMonth, null), 3, null);
                                }
                            };
                            $composer5.updateRememberedValue(value$iv6);
                        } else {
                            value$iv6 = it$iv4;
                        }
                        ComposerKt.sourceInformationMarkerEnd(r12);
                        DatePickerKt.YearPicker(modifierM1036paddingVpY3zN4$default2, j, (Function1) value$iv6, selectableDates2, calendarModel2, intRange, datePickerColors, r12, 6);
                        DividerKt.m2446HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors.getDividerColor(), r12, 0, 3);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        $composer5.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer4, 54), $composer4, 200112, 16);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i15 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
            int i16 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer4, -1436856422, "C1523@70222L511,1535@70767L520,1547@71329L42,1513@69712L1698,1551@71420L3300:DatePicker.kt#uh7d8r");
            Modifier modifierM1036paddingVpY3zN4$default2 = PaddingKt.m1036paddingVpY3zN4$default(Modifier.INSTANCE, DatePickerHorizontalPadding, 0.0f, 2, null);
            boolean canScrollForward2 = monthsListState.getCanScrollForward();
            boolean canScrollBackward2 = monthsListState.getCanScrollBackward();
            boolean zDatePickerContent$lambda$11 = DatePickerContent$lambda$10(yearPickerVisible$delegate);
            monthYear = dateFormatter.formatMonthYear(Long.valueOf(displayedMonthMillis), defaultLocale);
            if (monthYear == null) {
                monthYear = "-";
            }
            ComposerKt.sourceInformationMarkerStart($composer4, 230756280, "CC(remember):DatePicker.kt#9igjgp");
            invalid$iv = $composer4.changedInstance(coroutineScope) | $composer4.changed(monthsListState);
            value$iv = $composer4.rememberedValue();
            if (!invalid$iv) {
                $composer2 = $composer4;
                if (value$iv == Composer.INSTANCE.getEmpty()) {
                }
                Function0 function7 = (Function0) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, 230773729, "CC(remember):DatePicker.kt#9igjgp");
                invalid$iv2 = $composer4.changedInstance(coroutineScope) | $composer4.changed(monthsListState);
                Object it$iv3 = $composer4.rememberedValue();
                if (!invalid$iv2) {
                }
                value$iv2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$2$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: DatePicker.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$2$1$1", f = "DatePicker.kt", i = {}, l = {1539}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ LazyListState $monthsListState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(LazyListState lazyListState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$monthsListState = lazyListState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$monthsListState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    try {
                                        this.label = 1;
                                        if (LazyListState.animateScrollToItem$default(this.$monthsListState, this.$monthsListState.getFirstVisibleItemIndex() - 1, 0, this, 2, null) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    } catch (IllegalArgumentException e) {
                                    }
                                    break;
                                case 1:
                                    try {
                                        ResultKt.throwOnFailure($result);
                                    } catch (IllegalArgumentException e2) {
                                    }
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(monthsListState, null), 3, null);
                    }
                };
                $composer4.updateRememberedValue(value$iv2);
                Function0 function8 = (Function0) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerStart($composer4, 230791235, "CC(remember):DatePicker.kt#9igjgp");
                invalid$iv3 = $composer4.changed(yearPickerVisible$delegate);
                value$iv3 = $composer4.rememberedValue();
                if (!invalid$iv3) {
                }
                value$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$3$1
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
                        DatePickerKt.DatePickerContent$lambda$11(yearPickerVisible$delegate, !DatePickerKt.DatePickerContent$lambda$10(yearPickerVisible$delegate));
                    }
                };
                $composer4.updateRememberedValue(value$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                String str4 = str;
                $composer3 = $composer2;
                MonthsNavigation(modifierM1036paddingVpY3zN4$default2, canScrollForward2, canScrollBackward2, zDatePickerContent$lambda$11, monthYear, function7, function8, (Function0) value$iv3, colors, $composer4, ($dirty2 & 234881024) | 6);
                ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Modifier modifier$iv7 = Modifier.INSTANCE;
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv6 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                int $changed$iv$iv6 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                CompositionLocalMap localMap$iv$iv6 = $composer4.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer4, modifier$iv7);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv6 = (($changed$iv$iv6 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer4.startReusableNode();
                if ($composer4.getInserting()) {
                    $composer4.createNode(constructor);
                } else {
                    $composer4.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer4);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash6);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv6, ComposeUiNode.INSTANCE.getSetModifier());
                int i17 = ($changed$iv$iv$iv6 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i18 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer4, 2145982723, "C1552@71438L683,1571@72420L2290,1566@72161L2549:DatePicker.kt#uh7d8r");
                Modifier modifier$iv8 = PaddingKt.m1036paddingVpY3zN4$default(Modifier.INSTANCE, DatePickerHorizontalPadding, 0.0f, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer4, -483455358, str4);
                Arrangement.Vertical verticalArrangement$iv4 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv4 = Alignment.INSTANCE.getStart();
                measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv4, horizontalAlignment$iv4, $composer4, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                int $changed$iv$iv7 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                CompositionLocalMap localMap$iv$iv7 = $composer4.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv7 = ComposedModifierKt.materializeModifier($composer4, modifier$iv8);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv7 = (($changed$iv$iv7 << 6) & 896) | 6;
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
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer4);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    measurePolicy$iv2 = measurePolicy$iv;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv7, ComposeUiNode.INSTANCE.getSetModifier());
                    int i19 = ($changed$iv$iv$iv7 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance7 = ColumnScopeInstance.INSTANCE;
                    int i110 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, 455260175, "C1553@71534L31,1554@71582L525:DatePicker.kt#uh7d8r");
                    WeekDays(colors, calendarModel, $composer4, (($dirty2 >> 24) & 14) | (($dirty2 >> 9) & 112));
                    HorizontalMonthsList(monthsListState, selectedDateMillis, function1, function2, calendarModel, yearRange, dateFormatter, selectableDates, colors, $composer4, (($dirty2 << 3) & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2));
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    AnimatedVisibilityKt.AnimatedVisibility(DatePickerContent$lambda$10(yearPickerVisible$delegate), ClipKt.clipToBounds(Modifier.INSTANCE), EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.6f, 1, null)), EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1193716082, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                            invoke(animatedVisibilityScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer5, int $changed2) {
                            Object value$iv5;
                            Object value$iv6;
                            ComposerKt.sourceInformation($composer5, "C1576@72744L48,1577@72846L30,1577@72809L1887:DatePicker.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1193716082, $changed2, -1, "androidx.compose.material3.DatePickerContent.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1576)");
                            }
                            Strings.Companion companion = Strings.INSTANCE;
                            final String yearsPaneTitle = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_year_picker_pane_title), $composer5, 0);
                            Modifier.Companion companion2 = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer5, 14727253, "CC(remember):DatePicker.kt#9igjgp");
                            boolean invalid$iv5 = $composer5.changed(yearsPaneTitle);
                            Object it$iv4 = $composer5.rememberedValue();
                            if (invalid$iv5 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                                value$iv5 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$1$1
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
                                        SemanticsPropertiesKt.setPaneTitle($this$semantics, yearsPaneTitle);
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv5);
                            } else {
                                value$iv5 = it$iv4;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            Modifier modifier$iv9 = SemanticsModifierKt.semantics$default(companion2, false, (Function1) value$iv5, 1, null);
                            long j = displayedMonthMillis;
                            final MutableState<Boolean> mutableState = yearPickerVisible$delegate;
                            final CoroutineScope coroutineScope2 = coroutineScope;
                            final LazyListState lazyListState = monthsListState;
                            final IntRange intRange = yearRange;
                            final CalendarMonth calendarMonth = displayedMonth;
                            SelectableDates selectableDates2 = selectableDates;
                            CalendarModel calendarModel2 = calendarModel;
                            DatePickerColors datePickerColors = colors;
                            ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv5 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv5 = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv7 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv5, horizontalAlignment$iv5, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv8 = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                            CompositionLocalMap localMap$iv$iv8 = $composer5.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv8 = ComposedModifierKt.materializeModifier($composer5, modifier$iv9);
                            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv8 = (($changed$iv$iv8 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer5.startReusableNode();
                            if ($composer5.getInserting()) {
                                $composer5.createNode(constructor4);
                            } else {
                                $composer5.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer5);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash8);
                            }
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv8, ComposeUiNode.INSTANCE.getSetModifier());
                            int i111 = ($changed$iv$iv$iv8 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance8 = ColumnScopeInstance.INSTANCE;
                            int i112 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, 536387483, "C1589@73620L767,1578@72900L1711,1606@74632L46:DatePicker.kt#uh7d8r");
                            Modifier.Companion companion3 = Modifier.INSTANCE;
                            float arg0$iv = DatePickerKt.getRecommendedSizeForAccessibility();
                            float arg0$iv2 = Dp.m7016constructorimpl(7 * arg0$iv);
                            float other$iv = DividerDefaults.INSTANCE.m2444getThicknessD9Ej5fM();
                            Modifier modifierM1036paddingVpY3zN4$default3 = PaddingKt.m1036paddingVpY3zN4$default(SizeKt.m1068requiredHeight3ABfNKs(companion3, Dp.m7016constructorimpl(arg0$iv2 - other$iv)), DatePickerKt.getDatePickerHorizontalPadding(), 0.0f, 2, null);
                            ComposerKt.sourceInformationMarkerStart($composer5, -2060885131, "CC(remember):DatePicker.kt#9igjgp");
                            boolean invalid$iv6 = $composer5.changed(mutableState) | $composer5.changedInstance(coroutineScope2) | $composer5.changed(lazyListState) | $composer5.changedInstance(intRange) | $composer5.changed(calendarMonth);
                            Object it$iv5 = $composer5.rememberedValue();
                            if (invalid$iv6 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                                value$iv6 = new Function1<Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                        invoke(num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1, reason: invalid class name */
                                    /* JADX INFO: compiled from: DatePicker.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                    @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1", f = "DatePicker.kt", i = {}, l = {1597}, m = "invokeSuspend", n = {}, s = {})
                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        final /* synthetic */ CalendarMonth $displayedMonth;
                                        final /* synthetic */ LazyListState $monthsListState;
                                        final /* synthetic */ int $year;
                                        final /* synthetic */ IntRange $yearRange;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        AnonymousClass1(LazyListState lazyListState, int i, IntRange intRange, CalendarMonth calendarMonth, Continuation<? super AnonymousClass1> continuation) {
                                            super(2, continuation);
                                            this.$monthsListState = lazyListState;
                                            this.$year = i;
                                            this.$yearRange = intRange;
                                            this.$displayedMonth = calendarMonth;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new AnonymousClass1(this.$monthsListState, this.$year, this.$yearRange, this.$displayedMonth, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object $result) {
                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            switch (this.label) {
                                                case 0:
                                                    ResultKt.throwOnFailure($result);
                                                    this.label = 1;
                                                    if (LazyListState.scrollToItem$default(this.$monthsListState, (((this.$year - this.$yearRange.getFirst()) * 12) + this.$displayedMonth.getMonth()) - 1, 0, this, 2, null) == coroutine_suspended) {
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

                                    public final void invoke(int year) {
                                        DatePickerKt.DatePickerContent$lambda$11(mutableState, !DatePickerKt.DatePickerContent$lambda$10(mutableState));
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(lazyListState, year, intRange, calendarMonth, null), 3, null);
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv6);
                            } else {
                                value$iv6 = it$iv5;
                            }
                            ComposerKt.sourceInformationMarkerEnd(r12);
                            DatePickerKt.YearPicker(modifierM1036paddingVpY3zN4$default3, j, (Function1) value$iv6, selectableDates2, calendarModel2, intRange, datePickerColors, r12, 6);
                            DividerKt.m2446HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors.getDividerColor(), r12, 0, 3);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            $composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer4, 54), $composer4, 200112, 16);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    measurePolicy$iv2 = measurePolicy$iv;
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash7);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv7, ComposeUiNode.INSTANCE.getSetModifier());
                int i111 = ($changed$iv$iv$iv7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance8 = ColumnScopeInstance.INSTANCE;
                int i112 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer4, 455260175, "C1553@71534L31,1554@71582L525:DatePicker.kt#uh7d8r");
                WeekDays(colors, calendarModel, $composer4, (($dirty2 >> 24) & 14) | (($dirty2 >> 9) & 112));
                HorizontalMonthsList(monthsListState, selectedDateMillis, function1, function2, calendarModel, yearRange, dateFormatter, selectableDates, colors, $composer4, (($dirty2 << 3) & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2));
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                AnimatedVisibilityKt.AnimatedVisibility(DatePickerContent$lambda$10(yearPickerVisible$delegate), ClipKt.clipToBounds(Modifier.INSTANCE), EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.6f, 1, null)), EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1193716082, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                        invoke(animatedVisibilityScope, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer5, int $changed2) {
                        Object value$iv5;
                        Object value$iv6;
                        ComposerKt.sourceInformation($composer5, "C1576@72744L48,1577@72846L30,1577@72809L1887:DatePicker.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1193716082, $changed2, -1, "androidx.compose.material3.DatePickerContent.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1576)");
                        }
                        Strings.Companion companion = Strings.INSTANCE;
                        final String yearsPaneTitle = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_year_picker_pane_title), $composer5, 0);
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer5, 14727253, "CC(remember):DatePicker.kt#9igjgp");
                        boolean invalid$iv5 = $composer5.changed(yearsPaneTitle);
                        Object it$iv4 = $composer5.rememberedValue();
                        if (invalid$iv5 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                            value$iv5 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$1$1
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
                                    SemanticsPropertiesKt.setPaneTitle($this$semantics, yearsPaneTitle);
                                }
                            };
                            $composer5.updateRememberedValue(value$iv5);
                        } else {
                            value$iv5 = it$iv4;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        Modifier modifier$iv9 = SemanticsModifierKt.semantics$default(companion2, false, (Function1) value$iv5, 1, null);
                        long j = displayedMonthMillis;
                        final MutableState<Boolean> mutableState = yearPickerVisible$delegate;
                        final CoroutineScope coroutineScope2 = coroutineScope;
                        final LazyListState lazyListState = monthsListState;
                        final IntRange intRange = yearRange;
                        final CalendarMonth calendarMonth = displayedMonth;
                        SelectableDates selectableDates2 = selectableDates;
                        CalendarModel calendarModel2 = calendarModel;
                        DatePickerColors datePickerColors = colors;
                        ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv5 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv5 = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv7 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv5, horizontalAlignment$iv5, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv8 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                        CompositionLocalMap localMap$iv$iv8 = $composer5.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv8 = ComposedModifierKt.materializeModifier($composer5, modifier$iv9);
                        Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv8 = (($changed$iv$iv8 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer5.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer5.startReusableNode();
                        if ($composer5.getInserting()) {
                            $composer5.createNode(constructor4);
                        } else {
                            $composer5.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer5);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash8);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv8, ComposeUiNode.INSTANCE.getSetModifier());
                        int i113 = ($changed$iv$iv$iv8 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance9 = ColumnScopeInstance.INSTANCE;
                        int i114 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer5, 536387483, "C1589@73620L767,1578@72900L1711,1606@74632L46:DatePicker.kt#uh7d8r");
                        Modifier.Companion companion3 = Modifier.INSTANCE;
                        float arg0$iv = DatePickerKt.getRecommendedSizeForAccessibility();
                        float arg0$iv2 = Dp.m7016constructorimpl(7 * arg0$iv);
                        float other$iv = DividerDefaults.INSTANCE.m2444getThicknessD9Ej5fM();
                        Modifier modifierM1036paddingVpY3zN4$default3 = PaddingKt.m1036paddingVpY3zN4$default(SizeKt.m1068requiredHeight3ABfNKs(companion3, Dp.m7016constructorimpl(arg0$iv2 - other$iv)), DatePickerKt.getDatePickerHorizontalPadding(), 0.0f, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer5, -2060885131, "CC(remember):DatePicker.kt#9igjgp");
                        boolean invalid$iv6 = $composer5.changed(mutableState) | $composer5.changedInstance(coroutineScope2) | $composer5.changed(lazyListState) | $composer5.changedInstance(intRange) | $composer5.changed(calendarMonth);
                        Object it$iv5 = $composer5.rememberedValue();
                        if (invalid$iv6 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                            value$iv6 = new Function1<Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                    invoke(num.intValue());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1, reason: invalid class name */
                                /* JADX INFO: compiled from: DatePicker.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1", f = "DatePicker.kt", i = {}, l = {1597}, m = "invokeSuspend", n = {}, s = {})
                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ CalendarMonth $displayedMonth;
                                    final /* synthetic */ LazyListState $monthsListState;
                                    final /* synthetic */ int $year;
                                    final /* synthetic */ IntRange $yearRange;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(LazyListState lazyListState, int i, IntRange intRange, CalendarMonth calendarMonth, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.$monthsListState = lazyListState;
                                        this.$year = i;
                                        this.$yearRange = intRange;
                                        this.$displayedMonth = calendarMonth;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass1(this.$monthsListState, this.$year, this.$yearRange, this.$displayedMonth, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object $result) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch (this.label) {
                                            case 0:
                                                ResultKt.throwOnFailure($result);
                                                this.label = 1;
                                                if (LazyListState.scrollToItem$default(this.$monthsListState, (((this.$year - this.$yearRange.getFirst()) * 12) + this.$displayedMonth.getMonth()) - 1, 0, this, 2, null) == coroutine_suspended) {
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

                                public final void invoke(int year) {
                                    DatePickerKt.DatePickerContent$lambda$11(mutableState, !DatePickerKt.DatePickerContent$lambda$10(mutableState));
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(lazyListState, year, intRange, calendarMonth, null), 3, null);
                                }
                            };
                            $composer5.updateRememberedValue(value$iv6);
                        } else {
                            value$iv6 = it$iv5;
                        }
                        ComposerKt.sourceInformationMarkerEnd(r12);
                        DatePickerKt.YearPicker(modifierM1036paddingVpY3zN4$default3, j, (Function1) value$iv6, selectableDates2, calendarModel2, intRange, datePickerColors, r12, 6);
                        DividerKt.m2446HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors.getDividerColor(), r12, 0, 3);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        $composer5.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer4, 54), $composer4, 200112, 16);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2 = $composer4;
            }
            value$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$1$1$1, reason: invalid class name */
                /* JADX INFO: compiled from: DatePicker.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$1$1$1", f = "DatePicker.kt", i = {}, l = {1527}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ LazyListState $monthsListState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(LazyListState lazyListState, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$monthsListState = lazyListState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$monthsListState, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                try {
                                    this.label = 1;
                                    if (LazyListState.animateScrollToItem$default(this.$monthsListState, this.$monthsListState.getFirstVisibleItemIndex() + 1, 0, this, 2, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                } catch (IllegalArgumentException e) {
                                }
                                break;
                            case 1:
                                try {
                                    ResultKt.throwOnFailure($result);
                                } catch (IllegalArgumentException e2) {
                                }
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        return Unit.INSTANCE;
                    }
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(monthsListState, null), 3, null);
                }
            };
            $composer4.updateRememberedValue(value$iv);
            Function0 function9 = (Function0) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, 230773729, "CC(remember):DatePicker.kt#9igjgp");
            invalid$iv2 = $composer4.changedInstance(coroutineScope) | $composer4.changed(monthsListState);
            Object it$iv4 = $composer4.rememberedValue();
            if (!invalid$iv2) {
            }
            value$iv2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$2$1$1, reason: invalid class name */
                /* JADX INFO: compiled from: DatePicker.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$2$1$1", f = "DatePicker.kt", i = {}, l = {1539}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ LazyListState $monthsListState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(LazyListState lazyListState, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$monthsListState = lazyListState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$monthsListState, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                try {
                                    this.label = 1;
                                    if (LazyListState.animateScrollToItem$default(this.$monthsListState, this.$monthsListState.getFirstVisibleItemIndex() - 1, 0, this, 2, null) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                } catch (IllegalArgumentException e) {
                                }
                                break;
                            case 1:
                                try {
                                    ResultKt.throwOnFailure($result);
                                } catch (IllegalArgumentException e2) {
                                }
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        return Unit.INSTANCE;
                    }
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(monthsListState, null), 3, null);
                }
            };
            $composer4.updateRememberedValue(value$iv2);
            Function0 function10 = (Function0) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, 230791235, "CC(remember):DatePicker.kt#9igjgp");
            invalid$iv3 = $composer4.changed(yearPickerVisible$delegate);
            value$iv3 = $composer4.rememberedValue();
            if (!invalid$iv3) {
            }
            value$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$3$1
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
                    DatePickerKt.DatePickerContent$lambda$11(yearPickerVisible$delegate, !DatePickerKt.DatePickerContent$lambda$10(yearPickerVisible$delegate));
                }
            };
            $composer4.updateRememberedValue(value$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            String str5 = str;
            $composer3 = $composer2;
            MonthsNavigation(modifierM1036paddingVpY3zN4$default2, canScrollForward2, canScrollBackward2, zDatePickerContent$lambda$11, monthYear, function9, function10, (Function0) value$iv3, colors, $composer4, ($dirty2 & 234881024) | 6);
            ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Modifier modifier$iv9 = Modifier.INSTANCE;
            Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv7 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
            int $changed$iv$iv8 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
            CompositionLocalMap localMap$iv$iv8 = $composer4.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv8 = ComposedModifierKt.materializeModifier($composer4, modifier$iv9);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv8 = (($changed$iv$iv8 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer4.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer4.startReusableNode();
            if ($composer4.getInserting()) {
                $composer4.createNode(constructor);
            } else {
                $composer4.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer4);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash8);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv8, ComposeUiNode.INSTANCE.getSetModifier());
            int i113 = ($changed$iv$iv$iv8 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            int i114 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer4, 2145982723, "C1552@71438L683,1571@72420L2290,1566@72161L2549:DatePicker.kt#uh7d8r");
            Modifier modifier$iv10 = PaddingKt.m1036paddingVpY3zN4$default(Modifier.INSTANCE, DatePickerHorizontalPadding, 0.0f, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer4, -483455358, str5);
            Arrangement.Vertical verticalArrangement$iv5 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv5 = Alignment.INSTANCE.getStart();
            measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv5, horizontalAlignment$iv5, $composer4, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            int $changed$iv$iv9 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
            CompositionLocalMap localMap$iv$iv9 = $composer4.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv9 = ComposedModifierKt.materializeModifier($composer4, modifier$iv10);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv9 = (($changed$iv$iv9 << 6) & 896) | 6;
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
            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer4);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash9 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                measurePolicy$iv2 = measurePolicy$iv;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv9, ComposeUiNode.INSTANCE.getSetModifier());
                int i115 = ($changed$iv$iv$iv9 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance9 = ColumnScopeInstance.INSTANCE;
                int i116 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer4, 455260175, "C1553@71534L31,1554@71582L525:DatePicker.kt#uh7d8r");
                WeekDays(colors, calendarModel, $composer4, (($dirty2 >> 24) & 14) | (($dirty2 >> 9) & 112));
                HorizontalMonthsList(monthsListState, selectedDateMillis, function1, function2, calendarModel, yearRange, dateFormatter, selectableDates, colors, $composer4, (($dirty2 << 3) & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2));
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                AnimatedVisibilityKt.AnimatedVisibility(DatePickerContent$lambda$10(yearPickerVisible$delegate), ClipKt.clipToBounds(Modifier.INSTANCE), EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.6f, 1, null)), EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1193716082, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                        invoke(animatedVisibilityScope, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer5, int $changed2) {
                        Object value$iv5;
                        Object value$iv6;
                        ComposerKt.sourceInformation($composer5, "C1576@72744L48,1577@72846L30,1577@72809L1887:DatePicker.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1193716082, $changed2, -1, "androidx.compose.material3.DatePickerContent.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1576)");
                        }
                        Strings.Companion companion = Strings.INSTANCE;
                        final String yearsPaneTitle = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_year_picker_pane_title), $composer5, 0);
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer5, 14727253, "CC(remember):DatePicker.kt#9igjgp");
                        boolean invalid$iv5 = $composer5.changed(yearsPaneTitle);
                        Object it$iv5 = $composer5.rememberedValue();
                        if (invalid$iv5 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                            value$iv5 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$1$1
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
                                    SemanticsPropertiesKt.setPaneTitle($this$semantics, yearsPaneTitle);
                                }
                            };
                            $composer5.updateRememberedValue(value$iv5);
                        } else {
                            value$iv5 = it$iv5;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        Modifier modifier$iv11 = SemanticsModifierKt.semantics$default(companion2, false, (Function1) value$iv5, 1, null);
                        long j = displayedMonthMillis;
                        final MutableState<Boolean> mutableState = yearPickerVisible$delegate;
                        final CoroutineScope coroutineScope2 = coroutineScope;
                        final LazyListState lazyListState = monthsListState;
                        final IntRange intRange = yearRange;
                        final CalendarMonth calendarMonth = displayedMonth;
                        SelectableDates selectableDates2 = selectableDates;
                        CalendarModel calendarModel2 = calendarModel;
                        DatePickerColors datePickerColors = colors;
                        ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv6 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv6 = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv8 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv6, horizontalAlignment$iv6, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv10 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                        CompositionLocalMap localMap$iv$iv10 = $composer5.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv10 = ComposedModifierKt.materializeModifier($composer5, modifier$iv11);
                        Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv10 = (($changed$iv$iv10 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer5.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer5.startReusableNode();
                        if ($composer5.getInserting()) {
                            $composer5.createNode(constructor4);
                        } else {
                            $composer5.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer5);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash10 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash10);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv10, ComposeUiNode.INSTANCE.getSetModifier());
                        int i117 = ($changed$iv$iv$iv10 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance10 = ColumnScopeInstance.INSTANCE;
                        int i118 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer5, 536387483, "C1589@73620L767,1578@72900L1711,1606@74632L46:DatePicker.kt#uh7d8r");
                        Modifier.Companion companion3 = Modifier.INSTANCE;
                        float arg0$iv = DatePickerKt.getRecommendedSizeForAccessibility();
                        float arg0$iv2 = Dp.m7016constructorimpl(7 * arg0$iv);
                        float other$iv = DividerDefaults.INSTANCE.m2444getThicknessD9Ej5fM();
                        Modifier modifierM1036paddingVpY3zN4$default3 = PaddingKt.m1036paddingVpY3zN4$default(SizeKt.m1068requiredHeight3ABfNKs(companion3, Dp.m7016constructorimpl(arg0$iv2 - other$iv)), DatePickerKt.getDatePickerHorizontalPadding(), 0.0f, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer5, -2060885131, "CC(remember):DatePicker.kt#9igjgp");
                        boolean invalid$iv6 = $composer5.changed(mutableState) | $composer5.changedInstance(coroutineScope2) | $composer5.changed(lazyListState) | $composer5.changedInstance(intRange) | $composer5.changed(calendarMonth);
                        Object it$iv6 = $composer5.rememberedValue();
                        if (invalid$iv6 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                            value$iv6 = new Function1<Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                    invoke(num.intValue());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1, reason: invalid class name */
                                /* JADX INFO: compiled from: DatePicker.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1", f = "DatePicker.kt", i = {}, l = {1597}, m = "invokeSuspend", n = {}, s = {})
                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ CalendarMonth $displayedMonth;
                                    final /* synthetic */ LazyListState $monthsListState;
                                    final /* synthetic */ int $year;
                                    final /* synthetic */ IntRange $yearRange;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(LazyListState lazyListState, int i, IntRange intRange, CalendarMonth calendarMonth, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.$monthsListState = lazyListState;
                                        this.$year = i;
                                        this.$yearRange = intRange;
                                        this.$displayedMonth = calendarMonth;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass1(this.$monthsListState, this.$year, this.$yearRange, this.$displayedMonth, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object $result) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch (this.label) {
                                            case 0:
                                                ResultKt.throwOnFailure($result);
                                                this.label = 1;
                                                if (LazyListState.scrollToItem$default(this.$monthsListState, (((this.$year - this.$yearRange.getFirst()) * 12) + this.$displayedMonth.getMonth()) - 1, 0, this, 2, null) == coroutine_suspended) {
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

                                public final void invoke(int year) {
                                    DatePickerKt.DatePickerContent$lambda$11(mutableState, !DatePickerKt.DatePickerContent$lambda$10(mutableState));
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(lazyListState, year, intRange, calendarMonth, null), 3, null);
                                }
                            };
                            $composer5.updateRememberedValue(value$iv6);
                        } else {
                            value$iv6 = it$iv6;
                        }
                        ComposerKt.sourceInformationMarkerEnd(r12);
                        DatePickerKt.YearPicker(modifierM1036paddingVpY3zN4$default3, j, (Function1) value$iv6, selectableDates2, calendarModel2, intRange, datePickerColors, r12, 6);
                        DividerKt.m2446HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors.getDividerColor(), r12, 0, 3);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        $composer5.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer4, 54), $composer4, 200112, 16);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                measurePolicy$iv2 = measurePolicy$iv;
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash9);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv9, ComposeUiNode.INSTANCE.getSetModifier());
            int i117 = ($changed$iv$iv$iv9 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance10 = ColumnScopeInstance.INSTANCE;
            int i118 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer4, 455260175, "C1553@71534L31,1554@71582L525:DatePicker.kt#uh7d8r");
            WeekDays(colors, calendarModel, $composer4, (($dirty2 >> 24) & 14) | (($dirty2 >> 9) & 112));
            HorizontalMonthsList(monthsListState, selectedDateMillis, function1, function2, calendarModel, yearRange, dateFormatter, selectableDates, colors, $composer4, (($dirty2 << 3) & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2));
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            $composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            AnimatedVisibilityKt.AnimatedVisibility(DatePickerContent$lambda$10(yearPickerVisible$delegate), ClipKt.clipToBounds(Modifier.INSTANCE), EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.6f, 1, null)), EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1193716082, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                    invoke(animatedVisibilityScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer5, int $changed2) {
                    Object value$iv5;
                    Object value$iv6;
                    ComposerKt.sourceInformation($composer5, "C1576@72744L48,1577@72846L30,1577@72809L1887:DatePicker.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1193716082, $changed2, -1, "androidx.compose.material3.DatePickerContent.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1576)");
                    }
                    Strings.Companion companion = Strings.INSTANCE;
                    final String yearsPaneTitle = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_year_picker_pane_title), $composer5, 0);
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer5, 14727253, "CC(remember):DatePicker.kt#9igjgp");
                    boolean invalid$iv5 = $composer5.changed(yearsPaneTitle);
                    Object it$iv5 = $composer5.rememberedValue();
                    if (invalid$iv5 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                        value$iv5 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$1$1
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
                                SemanticsPropertiesKt.setPaneTitle($this$semantics, yearsPaneTitle);
                            }
                        };
                        $composer5.updateRememberedValue(value$iv5);
                    } else {
                        value$iv5 = it$iv5;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    Modifier modifier$iv11 = SemanticsModifierKt.semantics$default(companion2, false, (Function1) value$iv5, 1, null);
                    long j = displayedMonthMillis;
                    final MutableState<Boolean> mutableState = yearPickerVisible$delegate;
                    final CoroutineScope coroutineScope2 = coroutineScope;
                    final LazyListState lazyListState = monthsListState;
                    final IntRange intRange = yearRange;
                    final CalendarMonth calendarMonth = displayedMonth;
                    SelectableDates selectableDates2 = selectableDates;
                    CalendarModel calendarModel2 = calendarModel;
                    DatePickerColors datePickerColors = colors;
                    ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv6 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv6 = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv8 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv6, horizontalAlignment$iv6, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv10 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                    CompositionLocalMap localMap$iv$iv10 = $composer5.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv10 = ComposedModifierKt.materializeModifier($composer5, modifier$iv11);
                    Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv10 = (($changed$iv$iv10 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer5.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer5.startReusableNode();
                    if ($composer5.getInserting()) {
                        $composer5.createNode(constructor4);
                    } else {
                        $composer5.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer5);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash10 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash10);
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv10, ComposeUiNode.INSTANCE.getSetModifier());
                    int i119 = ($changed$iv$iv$iv10 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance11 = ColumnScopeInstance.INSTANCE;
                    int i1110 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer5, 536387483, "C1589@73620L767,1578@72900L1711,1606@74632L46:DatePicker.kt#uh7d8r");
                    Modifier.Companion companion3 = Modifier.INSTANCE;
                    float arg0$iv = DatePickerKt.getRecommendedSizeForAccessibility();
                    float arg0$iv2 = Dp.m7016constructorimpl(7 * arg0$iv);
                    float other$iv = DividerDefaults.INSTANCE.m2444getThicknessD9Ej5fM();
                    Modifier modifierM1036paddingVpY3zN4$default3 = PaddingKt.m1036paddingVpY3zN4$default(SizeKt.m1068requiredHeight3ABfNKs(companion3, Dp.m7016constructorimpl(arg0$iv2 - other$iv)), DatePickerKt.getDatePickerHorizontalPadding(), 0.0f, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer5, -2060885131, "CC(remember):DatePicker.kt#9igjgp");
                    boolean invalid$iv6 = $composer5.changed(mutableState) | $composer5.changedInstance(coroutineScope2) | $composer5.changed(lazyListState) | $composer5.changedInstance(intRange) | $composer5.changed(calendarMonth);
                    Object it$iv6 = $composer5.rememberedValue();
                    if (invalid$iv6 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                        value$iv6 = new Function1<Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                invoke(num.intValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1, reason: invalid class name */
                            /* JADX INFO: compiled from: DatePicker.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$2$1$1$1", f = "DatePicker.kt", i = {}, l = {1597}, m = "invokeSuspend", n = {}, s = {})
                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ CalendarMonth $displayedMonth;
                                final /* synthetic */ LazyListState $monthsListState;
                                final /* synthetic */ int $year;
                                final /* synthetic */ IntRange $yearRange;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                AnonymousClass1(LazyListState lazyListState, int i, IntRange intRange, CalendarMonth calendarMonth, Continuation<? super AnonymousClass1> continuation) {
                                    super(2, continuation);
                                    this.$monthsListState = lazyListState;
                                    this.$year = i;
                                    this.$yearRange = intRange;
                                    this.$displayedMonth = calendarMonth;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new AnonymousClass1(this.$monthsListState, this.$year, this.$yearRange, this.$displayedMonth, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object $result) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch (this.label) {
                                        case 0:
                                            ResultKt.throwOnFailure($result);
                                            this.label = 1;
                                            if (LazyListState.scrollToItem$default(this.$monthsListState, (((this.$year - this.$yearRange.getFirst()) * 12) + this.$displayedMonth.getMonth()) - 1, 0, this, 2, null) == coroutine_suspended) {
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

                            public final void invoke(int year) {
                                DatePickerKt.DatePickerContent$lambda$11(mutableState, !DatePickerKt.DatePickerContent$lambda$10(mutableState));
                                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(lazyListState, year, intRange, calendarMonth, null), 3, null);
                            }
                        };
                        $composer5.updateRememberedValue(value$iv6);
                    } else {
                        value$iv6 = it$iv6;
                    }
                    ComposerKt.sourceInformationMarkerEnd(r12);
                    DatePickerKt.YearPicker(modifierM1036paddingVpY3zN4$default3, j, (Function1) value$iv6, selectableDates2, calendarModel2, intRange, datePickerColors, r12, 6);
                    DividerKt.m2446HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors.getDividerColor(), r12, 0, 3);
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    $composer5.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer4, 54), $composer4, 200112, 16);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            $composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer4.skipToGroupEnd();
            $composer3 = $composer4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.DatePickerContent.3
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

                public final void invoke(Composer composer, int i20) {
                    DatePickerKt.DatePickerContent(selectedDateMillis, displayedMonthMillis, function1, function2, calendarModel, yearRange, dateFormatter, selectableDates, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DatePickerContent$lambda$10(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DatePickerContent$lambda$11(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX WARN: Code duplicated, block: B:76:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:77:0x0215  */
    /* JADX WARN: Code duplicated, block: B:80:0x025a  */
    /* JADX INFO: renamed from: DatePickerHeader-pc5RIQQ, reason: not valid java name */
    public static final void m2401DatePickerHeaderpc5RIQQ(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final long titleContentColor, final long headlineContentColor, final float minHeight, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Modifier.Companion companionM1064defaultMinSizeVpY3zN4$default;
        CompositionLocalMap localMap$iv$iv;
        MeasurePolicy measurePolicy$iv;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-996037719);
        ComposerKt.sourceInformation($composer3, "C(DatePickerHeader)P(3,4,5:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.unit.Dp)1629@75185L539:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(titleContentColor) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(headlineContentColor) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(minHeight) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 131072 : 65536;
        }
        int $dirty2 = $dirty;
        if ((74899 & $dirty2) != 74898 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-996037719, $dirty2, -1, "androidx.compose.material3.DatePickerHeader (DatePicker.kt:1621)");
            }
            if (function2 != null) {
                companionM1064defaultMinSizeVpY3zN4$default = SizeKt.m1064defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, minHeight, 1, null);
            } else {
                companionM1064defaultMinSizeVpY3zN4$default = Modifier.INSTANCE;
            }
            Modifier heightModifier = companionM1064defaultMinSizeVpY3zN4$default;
            Modifier modifier$iv = SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null).then(heightModifier);
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
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
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                localMap$iv$iv = localMap$iv$iv2;
                measurePolicy$iv = measurePolicy$iv2;
            } else {
                localMap$iv$iv = localMap$iv$iv2;
                measurePolicy$iv = measurePolicy$iv2;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i2 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1244227240, "C1639@75626L92:DatePicker.kt#uh7d8r");
                $composer3.startReplaceGroup(594325590);
                ComposerKt.sourceInformation($composer3, "1634@75414L5,1635@75518L89,1635@75432L175");
                if (function2 != null) {
                    TextStyle textStyle = TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getHeaderSupportingTextFont(), $composer3, 6);
                    $composer2 = $composer3;
                    ProvideContentColorTextStyleKt.m3277ProvideContentColorTextStyle3JVO9M(titleContentColor, textStyle, ComposableLambdaKt.rememberComposableLambda(1936268514, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerHeader$1$1
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

                        public final void invoke(Composer $composer4, int $changed2) {
                            Function0<ComposeUiNode> function0;
                            ComposerKt.sourceInformation($composer4, "C1636@75536L57:DatePicker.kt#uh7d8r");
                            if (($changed2 & 3) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1936268514, $changed2, -1, "androidx.compose.material3.DatePickerHeader.<anonymous>.<anonymous> (DatePicker.kt:1636)");
                            }
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getBottomStart();
                            Function2<Composer, Integer, Unit> function4 = function2;
                            ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            Modifier modifier$iv2 = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                            int $changed$iv$iv2 = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                            CompositionLocalMap localMap$iv$iv3 = $composer4.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer4, modifier$iv2);
                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                function0 = constructor2;
                                $composer4.createNode(function0);
                            } else {
                                function0 = constructor2;
                                $composer4.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer4);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                            }
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                            int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i4 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -397532909, "C1636@75584L7:DatePicker.kt#uh7d8r");
                            function4.invoke($composer4, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54), $composer2, (($dirty2 >> 6) & 14) | 384);
                } else {
                    $composer2 = $composer3;
                }
                $composer2.endReplaceGroup();
                Composer $composer4 = $composer2;
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4524boximpl(headlineContentColor)), function3, $composer4, ProvidedValue.$stable | (($dirty2 >> 12) & 112));
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i4 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1244227240, "C1639@75626L92:DatePicker.kt#uh7d8r");
            $composer3.startReplaceGroup(594325590);
            ComposerKt.sourceInformation($composer3, "1634@75414L5,1635@75518L89,1635@75432L175");
            if (function2 != null) {
                TextStyle textStyle2 = TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getHeaderSupportingTextFont(), $composer3, 6);
                $composer2 = $composer3;
                ProvideContentColorTextStyleKt.m3277ProvideContentColorTextStyle3JVO9M(titleContentColor, textStyle2, ComposableLambdaKt.rememberComposableLambda(1936268514, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerHeader$1$1
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

                    public final void invoke(Composer $composer5, int $changed2) {
                        Function0<ComposeUiNode> function0;
                        ComposerKt.sourceInformation($composer5, "C1636@75536L57:DatePicker.kt#uh7d8r");
                        if (($changed2 & 3) == 2 && $composer5.getSkipping()) {
                            $composer5.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1936268514, $changed2, -1, "androidx.compose.material3.DatePickerHeader.<anonymous>.<anonymous> (DatePicker.kt:1636)");
                        }
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getBottomStart();
                        Function2<Composer, Integer, Unit> function4 = function2;
                        ComposerKt.sourceInformationMarkerStart($composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Modifier modifier$iv2 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv2 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                        CompositionLocalMap localMap$iv$iv3 = $composer5.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer5, modifier$iv2);
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer5.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer5.startReusableNode();
                        if ($composer5.getInserting()) {
                            function0 = constructor2;
                            $composer5.createNode(function0);
                        } else {
                            function0 = constructor2;
                            $composer5.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer5);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i6 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer5, -397532909, "C1636@75584L7:DatePicker.kt#uh7d8r");
                        function4.invoke($composer5, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        $composer5.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54), $composer2, (($dirty2 >> 6) & 14) | 384);
            } else {
                $composer2 = $composer3;
            }
            $composer2.endReplaceGroup();
            Composer $composer5 = $composer2;
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4524boximpl(headlineContentColor)), function3, $composer5, ProvidedValue.$stable | (($dirty2 >> 12) & 112));
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$DatePickerHeader$2
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

                public final void invoke(Composer composer, int i5) {
                    DatePickerKt.m2401DatePickerHeaderpc5RIQQ(modifier, function2, titleContentColor, headlineContentColor, minHeight, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HorizontalMonthsList(final LazyListState lazyListState, final Long selectedDateMillis, final Function1<? super Long, Unit> function1, final Function1<? super Long, Unit> function2, final CalendarModel calendarModel, final IntRange yearRange, final DatePickerFormatter dateFormatter, final SelectableDates selectableDates, final DatePickerColors colors, Composer $composer, final int $changed) {
        Object value$iv;
        Composer $composer2;
        Object value$iv2;
        Composer $composer3 = $composer.startRestartGroup(-1994757941);
        ComposerKt.sourceInformation($composer3, "C(HorizontalMonthsList)P(3,7,4,5!1,8,2,6)1659@76284L158,1665@76504L5,1665@76511L1365,1665@76447L1429,1696@77912L228,1696@77882L258:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(lazyListState) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(selectedDateMillis) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(calendarModel) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(yearRange) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= (2097152 & $changed) == 0 ? $composer3.changed(dateFormatter) : $composer3.changedInstance(dateFormatter) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer3.changed(selectableDates) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty |= $composer3.changed(colors) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((38347923 & $dirty) != 38347922 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1994757941, $dirty, -1, "androidx.compose.material3.HorizontalMonthsList (DatePicker.kt:1656)");
            }
            final CalendarDate today = calendarModel.getToday();
            ComposerKt.sourceInformationMarkerStart($composer3, -1217520394, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(yearRange);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = calendarModel.getMonth(yearRange.getFirst(), 1);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final CalendarMonth firstMonth = (CalendarMonth) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            int $dirty2 = $dirty;
            $composer2 = $composer3;
            TextKt.ProvideTextStyle(TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getDateLabelTextFont(), $composer3, 6), ComposableLambdaKt.rememberComposableLambda(1504086906, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.HorizontalMonthsList.1
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

                public final void invoke(Composer $composer4, int $changed2) {
                    Object value$iv3;
                    ComposerKt.sourceInformation($composer4, "C1675@77032L40,1676@77083L787,1666@76521L1349:DatePicker.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1504086906, $changed2, -1, "androidx.compose.material3.HorizontalMonthsList.<anonymous> (DatePicker.kt:1666)");
                        }
                        Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt.HorizontalMonthsList.1.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                SemanticsPropertiesKt.setHorizontalScrollAxisRange($this$semantics, new ScrollAxisRange(new Function0<Float>() { // from class: androidx.compose.material3.DatePickerKt.HorizontalMonthsList.1.1.1
                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Float invoke() {
                                        return Float.valueOf(0.0f);
                                    }
                                }, new Function0<Float>() { // from class: androidx.compose.material3.DatePickerKt.HorizontalMonthsList.1.1.2
                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Float invoke() {
                                        return Float.valueOf(0.0f);
                                    }
                                }, false, 4, null));
                            }
                        }, 1, null);
                        LazyListState lazyListState2 = lazyListState;
                        FlingBehavior flingBehaviorRememberSnapFlingBehavior$material3_release = DatePickerDefaults.INSTANCE.rememberSnapFlingBehavior$material3_release(lazyListState, null, $composer4, 384, 2);
                        ComposerKt.sourceInformationMarkerStart($composer4, 1232814410, "CC(remember):DatePicker.kt#9igjgp");
                        boolean invalid$iv2 = $composer4.changedInstance(yearRange) | $composer4.changedInstance(calendarModel) | $composer4.changed(firstMonth) | $composer4.changed(function1) | $composer4.changed(today) | $composer4.changed(selectedDateMillis) | $composer4.changedInstance(dateFormatter) | $composer4.changed(selectableDates) | $composer4.changed(colors);
                        final IntRange intRange = yearRange;
                        final CalendarModel calendarModel2 = calendarModel;
                        final CalendarMonth calendarMonth = firstMonth;
                        final Function1<Long, Unit> function3 = function1;
                        final CalendarDate calendarDate = today;
                        final Long l = selectedDateMillis;
                        final DatePickerFormatter datePickerFormatter = dateFormatter;
                        final SelectableDates selectableDates2 = selectableDates;
                        final DatePickerColors datePickerColors = colors;
                        Object it$iv2 = $composer4.rememberedValue();
                        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                            value$iv3 = new Function1<LazyListScope, Unit>() { // from class: androidx.compose.material3.DatePickerKt$HorizontalMonthsList$1$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(LazyListScope lazyListScope) {
                                    invoke2(lazyListScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(LazyListScope $this$LazyRow) {
                                    int iNumberOfMonthsInRange = DatePickerKt.numberOfMonthsInRange(intRange);
                                    final CalendarModel calendarModel3 = calendarModel2;
                                    final CalendarMonth calendarMonth2 = calendarMonth;
                                    final Function1<Long, Unit> function4 = function3;
                                    final CalendarDate calendarDate2 = calendarDate;
                                    final Long l2 = l;
                                    final DatePickerFormatter datePickerFormatter2 = datePickerFormatter;
                                    final SelectableDates selectableDates3 = selectableDates2;
                                    final DatePickerColors datePickerColors2 = datePickerColors;
                                    LazyListScope.items$default($this$LazyRow, iNumberOfMonthsInRange, null, null, ComposableLambdaKt.composableLambdaInstance(1137566309, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$HorizontalMonthsList$1$2$1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(4);
                                        }

                                        @Override // kotlin.jvm.functions.Function4
                                        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                                            invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(LazyItemScope $this$items, int it, Composer $composer5, int $changed3) {
                                            Function0<ComposeUiNode> function0;
                                            ComposerKt.sourceInformation($composer5, "C1679@77250L596:DatePicker.kt#uh7d8r");
                                            int $dirty3 = $changed3;
                                            if (($changed3 & 6) == 0) {
                                                $dirty3 |= $composer5.changed($this$items) ? 4 : 2;
                                            }
                                            if (($changed3 & 48) == 0) {
                                                $dirty3 |= $composer5.changed(it) ? 32 : 16;
                                            }
                                            if (($dirty3 & 147) != 146 || !$composer5.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1137566309, $dirty3, -1, "androidx.compose.material3.HorizontalMonthsList.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1678)");
                                                }
                                                CalendarMonth month = calendarModel3.plusMonths(calendarMonth2, it);
                                                Modifier modifier$iv = LazyItemScope.fillParentMaxWidth$default($this$items, Modifier.INSTANCE, 0.0f, 1, null);
                                                Function1<Long, Unit> function5 = function4;
                                                CalendarDate calendarDate3 = calendarDate2;
                                                Long l3 = l2;
                                                DatePickerFormatter datePickerFormatter3 = datePickerFormatter2;
                                                SelectableDates selectableDates4 = selectableDates3;
                                                DatePickerColors datePickerColors3 = datePickerColors2;
                                                ComposerKt.sourceInformationMarkerStart($composer5, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                                int $changed$iv$iv = (0 << 3) & 112;
                                                ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                                CompositionLocalMap localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
                                                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer5, modifier$iv);
                                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                                                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer5);
                                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                                                }
                                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                                int i = ($changed$iv$iv$iv >> 6) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                int i2 = ((0 >> 6) & 112) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer5, 32177554, "C1680@77318L510:DatePicker.kt#uh7d8r");
                                                DatePickerKt.Month(month, function5, calendarDate3.getUtcTimeMillis(), l3, null, null, datePickerFormatter3, selectableDates4, datePickerColors3, $composer5, 221184);
                                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                                $composer5.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer5.skipToGroupEnd();
                                        }
                                    }), 6, null);
                                }
                            };
                            $composer4.updateRememberedValue(value$iv3);
                        } else {
                            value$iv3 = it$iv2;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LazyDslKt.LazyRow(modifierSemantics$default, lazyListState2, null, false, null, null, flingBehaviorRememberSnapFlingBehavior$material3_release, false, (Function1) value$iv3, $composer4, 0, 188);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, 48);
            ComposerKt.sourceInformationMarkerStart($composer2, -1217468228, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv2 = (($dirty2 & 14) == 4) | (($dirty2 & 7168) == 2048) | $composer2.changedInstance(calendarModel) | $composer2.changedInstance(yearRange);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new DatePickerKt$HorizontalMonthsList$2$1(lazyListState, function2, calendarModel, yearRange, null);
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(lazyListState, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer2, $dirty2 & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.HorizontalMonthsList.3
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
                    DatePickerKt.HorizontalMonthsList(lazyListState, selectedDateMillis, function1, function2, calendarModel, yearRange, dateFormatter, selectableDates, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public static final Object updateDisplayedMonth(final LazyListState lazyListState, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange yearRange, Continuation<? super Unit> continuation) {
        Object objCollect = SnapshotStateKt.snapshotFlow(new Function0<Integer>() { // from class: androidx.compose.material3.DatePickerKt.updateDisplayedMonth.2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(lazyListState.getFirstVisibleItemIndex());
            }
        }).collect(new FlowCollector() { // from class: androidx.compose.material3.DatePickerKt.updateDisplayedMonth.3
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                return emit(((Number) value).intValue(), (Continuation<? super Unit>) $completion);
            }

            public final Object emit(int it, Continuation<? super Unit> continuation2) {
                int yearOffset = lazyListState.getFirstVisibleItemIndex() / 12;
                int month = (lazyListState.getFirstVisibleItemIndex() % 12) + 1;
                function1.invoke(Boxing.boxLong(calendarModel.getMonth(yearRange.getFirst() + yearOffset, month).getStartUtcTimeMillis()));
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:48:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:58:0x0293  */
    /* JADX WARN: Code duplicated, block: B:61:0x029f  */
    /* JADX WARN: Code duplicated, block: B:62:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:65:0x02d8  */
    /* JADX WARN: Code duplicated, block: B:68:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:69:0x02ee  */
    /* JADX WARN: Code duplicated, block: B:74:0x042e  */
    public static final void WeekDays(final DatePickerColors colors, final CalendarModel calendarModel, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        CompositionLocalMap localMap$iv$iv;
        MeasurePolicy measurePolicy$iv;
        int $changed$iv;
        Composer $composer$iv;
        Composer $composer2;
        ArrayList $this$fastForEach$iv;
        int index$iv;
        Function0<ComposeUiNode> function1;
        int size;
        boolean invalid$iv;
        Object value$iv;
        MeasurePolicy measurePolicy$iv2;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function2;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        MeasurePolicy measurePolicy$iv3;
        Composer $composer3 = $composer.startRestartGroup(-1849465391);
        ComposerKt.sourceInformation($composer3, "C(WeekDays)P(1)1739@79443L5,1741@79454L974:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(colors) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(calendarModel) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1849465391, $dirty, -1, "androidx.compose.material3.WeekDays (DatePicker.kt:1728)");
            }
            int firstDayOfWeek = calendarModel.getFirstDayOfWeek();
            List<Pair<String, String>> weekdayNames = calendarModel.getWeekdayNames();
            ArrayList dayNames = new ArrayList();
            int size2 = weekdayNames.size();
            for (int i = firstDayOfWeek - 1; i < size2; i++) {
                dayNames.add(weekdayNames.get(i));
            }
            int i2 = firstDayOfWeek - 1;
            for (int i3 = 0; i3 < i2; i3++) {
                dayNames.add(weekdayNames.get(i3));
            }
            TextStyle textStyle = TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getWeekdaysLabelTextFont(), $composer3, 6);
            Modifier modifier$iv = SizeKt.fillMaxWidth$default(SizeKt.m1064defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, RecommendedSizeForAccessibility, 1, null), 0.0f, 1, null);
            Arrangement.Horizontal spaceEvenly = Arrangement.INSTANCE.getSpaceEvenly();
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalArrangement$iv = spaceEvenly;
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv4 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            int $changed$iv$iv = (438 << 3) & 112;
            String str = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            Modifier modifier$iv2 = modifier$iv;
            String str2 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor2;
                $composer3.createNode(function0);
            } else {
                function0 = constructor2;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer3);
            Alignment.Vertical verticalAlignment$iv2 = verticalAlignment$iv;
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                localMap$iv$iv = localMap$iv$iv2;
                measurePolicy$iv = measurePolicy$iv4;
            } else {
                localMap$iv$iv = localMap$iv$iv2;
                measurePolicy$iv = measurePolicy$iv4;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                $changed$iv = ($changed$iv$iv$iv >> 6) & 14;
                $composer$iv = $composer3;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i4 = ((438 >> 6) & 112) | 6;
                $composer2 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer2, -602795257, "C:DatePicker.kt#uh7d8r");
                $composer2.startReplaceGroup(396197267);
                ComposerKt.sourceInformation($composer2, "*1750@79820L33,1748@79738L674");
                $this$fastForEach$iv = dayNames;
                index$iv = 0;
                function1 = function0;
                size = $this$fastForEach$iv.size();
                while (index$iv < size) {
                    Object item$iv = $this$fastForEach$iv.get(index$iv);
                    int i5 = size;
                    final Pair it = (Pair) item$iv;
                    List $this$fastForEach$iv2 = $this$fastForEach$iv;
                    Modifier.Companion companion = Modifier.INSTANCE;
                    int $changed$iv2 = $changed$iv;
                    ArrayList dayNames2 = dayNames;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1458700563, "CC(remember):DatePicker.kt#9igjgp");
                    invalid$iv = $composer2.changed(it);
                    Composer $this$cache$iv = $composer2;
                    Modifier materialized$iv$iv2 = materialized$iv$iv;
                    Object it$iv = $this$cache$iv.rememberedValue();
                    if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$WeekDays$1$1$1$1
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
                                SemanticsPropertiesKt.setContentDescription($this$clearAndSetSemantics, it.getFirst());
                            }
                        };
                        $this$cache$iv.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifier$iv3 = SizeKt.m1081sizeVpY3zN4(SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) value$iv), RecommendedSizeForAccessibility, RecommendedSizeForAccessibility);
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                    int index$iv2 = index$iv;
                    ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv2 = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str);
                    compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    CompositionLocalMap localMap$iv$iv3 = $composer2.getCurrentCompositionLocalMap();
                    String str3 = str;
                    Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv3);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -692256719, str2);
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                        function2 = constructor;
                        $composer2.createNode(function2);
                    } else {
                        function2 = constructor;
                        $composer2.useNode();
                    }
                    String str4 = str2;
                    $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                        measurePolicy$iv3 = measurePolicy$iv2;
                    } else {
                        measurePolicy$iv3 = measurePolicy$iv2;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                        int i6 = ($changed$iv$iv$iv2 >> 6) & 14;
                        Composer $composer$iv2 = $composer2;
                        ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i7 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer$iv2, 87886672, "C1757@80134L264:DatePicker.kt#uh7d8r");
                        Composer $composer4 = $composer2;
                        TextKt.m3067Text4IGK_g((String) it.getSecond(), SizeKt.wrapContentSize$default(Modifier.INSTANCE, null, false, 3, null), colors.getWeekdayContentColor(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, textStyle, $composer$iv2, 48, 0, 65016);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        index$iv = index$iv2 + 1;
                        verticalAlignment$iv2 = verticalAlignment$iv2;
                        measurePolicy$iv = measurePolicy$iv;
                        modifier$iv2 = modifier$iv2;
                        $this$fastForEach$iv = $this$fastForEach$iv2;
                        $changed$iv = $changed$iv2;
                        dayNames = dayNames2;
                        materialized$iv$iv = materialized$iv$iv2;
                        str2 = str4;
                        localMap$iv$iv = localMap$iv$iv;
                        $composer2 = $composer4;
                        horizontalArrangement$iv = horizontalArrangement$iv;
                        $composer$iv = $composer$iv;
                        function1 = function1;
                        size = i5;
                        str = str3;
                    }
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i8 = ($changed$iv$iv$iv2 >> 6) & 14;
                    Composer $composer$iv3 = $composer2;
                    ComposerKt.sourceInformationMarkerStart($composer$iv3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i9 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv3, 87886672, "C1757@80134L264:DatePicker.kt#uh7d8r");
                    Composer $composer5 = $composer2;
                    TextKt.m3067Text4IGK_g((String) it.getSecond(), SizeKt.wrapContentSize$default(Modifier.INSTANCE, null, false, 3, null), colors.getWeekdayContentColor(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, textStyle, $composer$iv3, 48, 0, 65016);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                    $composer5.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    index$iv = index$iv2 + 1;
                    verticalAlignment$iv2 = verticalAlignment$iv2;
                    measurePolicy$iv = measurePolicy$iv;
                    modifier$iv2 = modifier$iv2;
                    $this$fastForEach$iv = $this$fastForEach$iv2;
                    $changed$iv = $changed$iv2;
                    dayNames = dayNames2;
                    materialized$iv$iv = materialized$iv$iv2;
                    str2 = str4;
                    localMap$iv$iv = localMap$iv$iv;
                    $composer2 = $composer5;
                    horizontalArrangement$iv = horizontalArrangement$iv;
                    $composer$iv = $composer$iv;
                    function1 = function1;
                    size = i5;
                    str = str3;
                }
                Composer $composer6 = $composer2;
                $composer6.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer6);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            $changed$iv = ($changed$iv$iv$iv >> 6) & 14;
            $composer$iv = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer$iv, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            int i10 = ((438 >> 6) & 112) | 6;
            $composer2 = $composer$iv;
            ComposerKt.sourceInformationMarkerStart($composer2, -602795257, "C:DatePicker.kt#uh7d8r");
            $composer2.startReplaceGroup(396197267);
            ComposerKt.sourceInformation($composer2, "*1750@79820L33,1748@79738L674");
            $this$fastForEach$iv = dayNames;
            index$iv = 0;
            function1 = function0;
            size = $this$fastForEach$iv.size();
            while (index$iv < size) {
                Object item$iv2 = $this$fastForEach$iv.get(index$iv);
                int i11 = size;
                final Pair<String, String> it2 = (Pair) item$iv2;
                List $this$fastForEach$iv3 = $this$fastForEach$iv;
                Modifier.Companion companion2 = Modifier.INSTANCE;
                int $changed$iv3 = $changed$iv;
                ArrayList dayNames3 = dayNames;
                ComposerKt.sourceInformationMarkerStart($composer2, -1458700563, "CC(remember):DatePicker.kt#9igjgp");
                invalid$iv = $composer2.changed(it2);
                Composer $this$cache$iv2 = $composer2;
                Modifier materialized$iv$iv4 = materialized$iv$iv;
                Object it$iv2 = $this$cache$iv2.rememberedValue();
                if (!invalid$iv) {
                }
                value$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$WeekDays$1$1$1$1
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
                        SemanticsPropertiesKt.setContentDescription($this$clearAndSetSemantics, it2.getFirst());
                    }
                };
                $this$cache$iv2.updateRememberedValue(value$iv);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifier$iv4 = SizeKt.m1081sizeVpY3zN4(SemanticsModifierKt.clearAndSetSemantics(companion2, (Function1) value$iv), RecommendedSizeForAccessibility, RecommendedSizeForAccessibility);
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
                int index$iv3 = index$iv;
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                int $changed$iv$iv3 = (48 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str);
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv4 = $composer2.getCurrentCompositionLocalMap();
                String str5 = str;
                Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer2, modifier$iv4);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, str2);
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function2 = constructor;
                    $composer2.createNode(function2);
                } else {
                    function2 = constructor;
                    $composer2.useNode();
                }
                String str6 = str2;
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer2);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    measurePolicy$iv3 = measurePolicy$iv2;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                    int i12 = ($changed$iv$iv$iv3 >> 6) & 14;
                    Composer $composer$iv4 = $composer2;
                    ComposerKt.sourceInformationMarkerStart($composer$iv4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    int i13 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv4, 87886672, "C1757@80134L264:DatePicker.kt#uh7d8r");
                    Composer $composer7 = $composer2;
                    TextKt.m3067Text4IGK_g((String) it2.getSecond(), SizeKt.wrapContentSize$default(Modifier.INSTANCE, null, false, 3, null), colors.getWeekdayContentColor(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, textStyle, $composer$iv4, 48, 0, 65016);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv4);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv4);
                    $composer7.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer7);
                    ComposerKt.sourceInformationMarkerEnd($composer7);
                    ComposerKt.sourceInformationMarkerEnd($composer7);
                    index$iv = index$iv3 + 1;
                    verticalAlignment$iv2 = verticalAlignment$iv2;
                    measurePolicy$iv = measurePolicy$iv;
                    modifier$iv2 = modifier$iv2;
                    $this$fastForEach$iv = $this$fastForEach$iv3;
                    $changed$iv = $changed$iv3;
                    dayNames = dayNames3;
                    materialized$iv$iv = materialized$iv$iv4;
                    str2 = str6;
                    localMap$iv$iv = localMap$iv$iv;
                    $composer2 = $composer7;
                    horizontalArrangement$iv = horizontalArrangement$iv;
                    $composer$iv = $composer$iv;
                    function1 = function1;
                    size = i11;
                    str = str5;
                } else {
                    measurePolicy$iv3 = measurePolicy$iv2;
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                int i14 = ($changed$iv$iv$iv3 >> 6) & 14;
                Composer $composer$iv5 = $composer2;
                ComposerKt.sourceInformationMarkerStart($composer$iv5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                int i15 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv5, 87886672, "C1757@80134L264:DatePicker.kt#uh7d8r");
                Composer $composer8 = $composer2;
                TextKt.m3067Text4IGK_g((String) it2.getSecond(), SizeKt.wrapContentSize$default(Modifier.INSTANCE, null, false, 3, null), colors.getWeekdayContentColor(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, textStyle, $composer$iv5, 48, 0, 65016);
                ComposerKt.sourceInformationMarkerEnd($composer$iv5);
                ComposerKt.sourceInformationMarkerEnd($composer$iv5);
                $composer8.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer8);
                ComposerKt.sourceInformationMarkerEnd($composer8);
                ComposerKt.sourceInformationMarkerEnd($composer8);
                index$iv = index$iv3 + 1;
                verticalAlignment$iv2 = verticalAlignment$iv2;
                measurePolicy$iv = measurePolicy$iv;
                modifier$iv2 = modifier$iv2;
                $this$fastForEach$iv = $this$fastForEach$iv3;
                $changed$iv = $changed$iv3;
                dayNames = dayNames3;
                materialized$iv$iv = materialized$iv$iv4;
                str2 = str6;
                localMap$iv$iv = localMap$iv$iv;
                $composer2 = $composer8;
                horizontalArrangement$iv = horizontalArrangement$iv;
                $composer$iv = $composer$iv;
                function1 = function1;
                size = i11;
                str = str5;
            }
            Composer $composer9 = $composer2;
            $composer9.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer9);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.WeekDays.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i16) {
                    DatePickerKt.WeekDays(colors, calendarModel, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:182:0x04d5  */
    /* JADX WARN: Code duplicated, block: B:230:0x0605  */
    /* JADX WARN: Code duplicated, block: B:231:0x061f  */
    public static final void Month(final CalendarMonth month, final Function1<? super Long, Unit> function1, final long todayMillis, final Long startDateMillis, final Long endDateMillis, final SelectedRangeInfo rangeSelectionInfo, final DatePickerFormatter dateFormatter, final SelectableDates selectableDates, final DatePickerColors colors, Composer $composer, final int $changed) {
        Modifier.Companion rangeSelectionDrawModifier;
        Function0<ComposeUiNode> function0;
        int $dirty;
        Function0<ComposeUiNode> function2;
        boolean inRange;
        Object value$iv;
        String str;
        boolean z;
        Object value$iv2;
        long j = todayMillis;
        Object obj = endDateMillis;
        final SelectedRangeInfo selectedRangeInfo = rangeSelectionInfo;
        Composer $composer2 = $composer.startRestartGroup(-1912870997);
        ComposerKt.sourceInformation($composer2, "C(Month)P(3,4,8,7,2,5,1,6)1793@81233L15,1795@81275L5273:DatePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(month) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changed(j) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changed(startDateMillis) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changed(obj) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= $composer2.changed(selectedRangeInfo) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= (2097152 & $changed) == 0 ? $composer2.changed(dateFormatter) : $composer2.changedInstance(dateFormatter) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty2 |= $composer2.changed(selectableDates) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer2.changed(colors) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($dirty2 & 38347923) != 38347922 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1912870997, $dirty2, -1, "androidx.compose.material3.Month (DatePicker.kt:1782)");
            }
            $composer2.startReplaceGroup(1821433443);
            ComposerKt.sourceInformation($composer2, "1785@81016L143");
            String str2 = "CC(remember):DatePicker.kt#9igjgp";
            if (selectedRangeInfo != null) {
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, 1821435596, "CC(remember):DatePicker.kt#9igjgp");
                boolean invalid$iv = (($dirty2 & 458752) == 131072) | (($dirty2 & 234881024) == 67108864);
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = (Function1) new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.material3.DatePickerKt$Month$rangeSelectionDrawModifier$1$1
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
                            DateRangePickerKt.m2421drawRangeBackgroundmxwnekA($this$drawWithContent, selectedRangeInfo, colors.getDayInSelectionRangeContainerColor());
                            $this$drawWithContent.drawContent();
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                rangeSelectionDrawModifier = DrawModifierKt.drawWithContent(companion, (Function1) value$iv2);
            } else {
                rangeSelectionDrawModifier = Modifier.INSTANCE;
            }
            $composer2.endReplaceGroup();
            Locale defaultLocale = CalendarLocale_androidKt.defaultLocale($composer2, 0);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            float arg0$iv = RecommendedSizeForAccessibility;
            Modifier modifier$iv = SizeKt.m1068requiredHeight3ABfNKs(companion2, Dp.m7016constructorimpl(6 * arg0$iv)).then(rangeSelectionDrawModifier);
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getSpaceEvenly();
            int $changed$iv = 48;
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            String str3 = "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Locale defaultLocale2 = defaultLocale;
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            String str4 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
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
            $dirty = $dirty2;
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            Composer $composer$iv = $composer2;
            ComposerKt.sourceInformationMarkerStart($composer$iv, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int $changed2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer$iv, 1403534943, "C:DatePicker.kt#uh7d8r");
            $composer$iv.startReplaceGroup(-647461340);
            ComposerKt.sourceInformation($composer$iv, "*1802@81567L4965");
            int weekIndex = 0;
            int cellIndex = 0;
            while (true) {
                int cellIndex2 = cellIndex;
                if (weekIndex >= 6) {
                    break;
                }
                int $changed$iv2 = $changed$iv;
                int $changed3 = $changed2;
                Modifier materialized$iv$iv2 = materialized$iv$iv;
                Modifier modifier$iv2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getSpaceEvenly();
                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                Composer $composer$iv2 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer$iv, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer$iv, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                int $changed$iv$iv2 = (438 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, str3);
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer$iv.getCurrentCompositionLocalMap();
                String str5 = str3;
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, str4);
                if (!($composer$iv.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer$iv.startReusableNode();
                if ($composer$iv.getInserting()) {
                    function2 = constructor2;
                    $composer$iv.createNode(function2);
                } else {
                    function2 = constructor2;
                    $composer$iv.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer$iv);
                String str6 = str4;
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = ($changed$iv$iv$iv2 >> 6) & 14;
                int i3 = 0;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int $changed4 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv, 1554692074, "C:DatePicker.kt#uh7d8r");
                $composer$iv.startReplaceGroup(-88395975);
                ComposerKt.sourceInformation($composer$iv, "");
                int dayIndex = 0;
                cellIndex = cellIndex2;
                while (true) {
                    int i4 = i3;
                    if (dayIndex < 7) {
                        if (cellIndex < month.getDaysFromStartOfWeekToFirstOfMonth() || cellIndex >= month.getDaysFromStartOfWeekToFirstOfMonth() + month.getNumberOfDays()) {
                            $composer$iv.startReplaceGroup(1554856342);
                            ComposerKt.sourceInformation($composer$iv, "1814@82149L315");
                            SpacerKt.Spacer(SizeKt.m1073requiredSizeVpY3zN4(Modifier.INSTANCE, RecommendedSizeForAccessibility, RecommendedSizeForAccessibility), $composer$iv, 6);
                            $composer$iv.endReplaceGroup();
                        } else {
                            $composer$iv.startReplaceGroup(1555370911);
                            ComposerKt.sourceInformation($composer$iv, "1842@83738L365,1858@84596L39,1864@85003L567,1882@86070L376,1855@84427L2019");
                            final int dayNumber = cellIndex - month.getDaysFromStartOfWeekToFirstOfMonth();
                            weekIndex = weekIndex;
                            dayIndex = dayIndex;
                            final long dateInMillis = month.getStartUtcTimeMillis() + (((long) dayNumber) * CalendarModelKt.MillisecondsIn24Hours);
                            boolean isToday = dateInMillis == j;
                            boolean startDateSelected = startDateMillis != null && dateInMillis == startDateMillis.longValue();
                            boolean endDateSelected = obj != null && dateInMillis == endDateMillis.longValue();
                            $changed4 = $changed4;
                            $composer$iv.startReplaceGroup(-88360892);
                            ComposerKt.sourceInformation($composer$iv, "1830@83074L435");
                            if (selectedRangeInfo != null) {
                                ComposerKt.sourceInformationMarkerStart($composer$iv, -88358994, str2);
                                boolean invalid$iv2 = (($dirty & 458752) == 131072) | $composer2.changed(dateInMillis);
                                Object value$iv3 = $composer$iv.rememberedValue();
                                if (invalid$iv2 || value$iv3 == Composer.INSTANCE.getEmpty()) {
                                    if (dateInMillis < (startDateMillis != null ? startDateMillis.longValue() : Long.MAX_VALUE)) {
                                        z = false;
                                    } else {
                                        if (dateInMillis <= (obj != null ? endDateMillis.longValue() : Long.MIN_VALUE)) {
                                            z = true;
                                        } else {
                                            z = false;
                                        }
                                    }
                                    value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
                                    $composer$iv.updateRememberedValue(value$iv3);
                                }
                                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                                inRange = ((Boolean) ((MutableState) value$iv3).getValue()).booleanValue();
                            } else {
                                inRange = false;
                            }
                            $composer$iv.endReplaceGroup();
                            String dayContentDescription = dayContentDescription(selectedRangeInfo != null, isToday, startDateSelected, endDateSelected, inRange, $composer$iv, 0);
                            Locale defaultLocale3 = defaultLocale2;
                            str6 = str6;
                            String formattedDateDescription = dateFormatter.formatDate(Long.valueOf(dateInMillis), defaultLocale3, true);
                            if (formattedDateDescription == null) {
                                formattedDateDescription = "";
                            }
                            Modifier.Companion companion3 = Modifier.INSTANCE;
                            boolean z2 = startDateSelected || endDateSelected;
                            ComposerKt.sourceInformationMarkerStart($composer$iv, -88310686, str2);
                            defaultLocale2 = defaultLocale3;
                            boolean invalid$iv3 = (($dirty & 112) == 32) | $composer2.changed(dateInMillis);
                            Object value$iv4 = $composer$iv.rememberedValue();
                            if (invalid$iv3 || value$iv4 == Composer.INSTANCE.getEmpty()) {
                                value$iv4 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$Month$1$1$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
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
                                        function1.invoke(Long.valueOf(dateInMillis));
                                    }
                                };
                                $composer$iv.updateRememberedValue(value$iv4);
                            }
                            Function0 function3 = (Function0) value$iv4;
                            ComposerKt.sourceInformationMarkerEnd($composer$iv);
                            ComposerKt.sourceInformationMarkerStart($composer$iv, -88297134, str2);
                            boolean invalid$iv4 = $composer2.changed(dateInMillis) | (($dirty & 29360128) == 8388608);
                            Object it$iv2 = $composer$iv.rememberedValue();
                            if (!invalid$iv4) {
                                str2 = str2;
                                if (it$iv2 != Composer.INSTANCE.getEmpty()) {
                                    value$iv = it$iv2;
                                }
                                boolean zBooleanValue = ((Boolean) value$iv).booleanValue();
                                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                                if (dayContentDescription != null) {
                                    str = dayContentDescription + ", " + formattedDateDescription;
                                } else {
                                    str = formattedDateDescription;
                                }
                                Day(companion3, z2, function3, startDateSelected, zBooleanValue, isToday, inRange, str, colors, ComposableLambdaKt.rememberComposableLambda(-2095706591, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$Month$1$1$3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer3, int $changed5) {
                                        ComposerKt.sourceInformation($composer3, "C1883@86100L320:DatePicker.kt#uh7d8r");
                                        if (($changed5 & 3) != 2 || !$composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-2095706591, $changed5, -1, "androidx.compose.material3.Month.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1883)");
                                            }
                                            TextKt.m3067Text4IGK_g(CalendarLocale_jvmKt.toLocalString$default(dayNumber + 1, 0, 0, false, 7, null), SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$Month$1$1$3.1
                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                    invoke2(semanticsPropertyReceiver);
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
                                                }
                                            }), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 0, 0, 130556);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer3.skipToGroupEnd();
                                    }
                                }, $composer$iv, 54), $composer$iv, ($dirty & 234881024) | 805306374);
                                $composer$iv.endReplaceGroup();
                            } else {
                                str2 = str2;
                            }
                            value$iv = Boolean.valueOf(selectableDates.isSelectableYear(month.getYear()) && selectableDates.isSelectableDate(dateInMillis));
                            $composer$iv.updateRememberedValue(value$iv);
                            boolean zBooleanValue2 = ((Boolean) value$iv).booleanValue();
                            ComposerKt.sourceInformationMarkerEnd($composer$iv);
                            if (dayContentDescription != null) {
                                str = dayContentDescription + ", " + formattedDateDescription;
                            } else {
                                str = formattedDateDescription;
                            }
                            Day(companion3, z2, function3, startDateSelected, zBooleanValue2, isToday, inRange, str, colors, ComposableLambdaKt.rememberComposableLambda(-2095706591, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$Month$1$1$3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                    invoke(composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer $composer3, int $changed5) {
                                    ComposerKt.sourceInformation($composer3, "C1883@86100L320:DatePicker.kt#uh7d8r");
                                    if (($changed5 & 3) != 2 || !$composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-2095706591, $changed5, -1, "androidx.compose.material3.Month.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1883)");
                                        }
                                        TextKt.m3067Text4IGK_g(CalendarLocale_jvmKt.toLocalString$default(dayNumber + 1, 0, 0, false, 7, null), SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$Month$1$1$3.1
                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                invoke2(semanticsPropertyReceiver);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
                                            }
                                        }), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer3, 0, 0, 130556);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer3.skipToGroupEnd();
                                }
                            }, $composer$iv, 54), $composer$iv, ($dirty & 234881024) | 805306374);
                            $composer$iv.endReplaceGroup();
                        }
                        cellIndex++;
                        j = todayMillis;
                        obj = endDateMillis;
                        selectedRangeInfo = rangeSelectionInfo;
                        str2 = str2;
                        dayIndex++;
                        verticalAlignment$iv = verticalAlignment$iv;
                        str6 = str6;
                        defaultLocale2 = defaultLocale2;
                        materialized$iv$iv2 = materialized$iv$iv2;
                        i3 = i4;
                        weekIndex = weekIndex;
                        $changed4 = $changed4;
                    }
                }
                $composer$iv.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                weekIndex++;
                j = todayMillis;
                obj = endDateMillis;
                selectedRangeInfo = rangeSelectionInfo;
                str4 = str6;
                $changed$iv = $changed$iv2;
                $changed2 = $changed3;
                $composer$iv = $composer$iv2;
                str3 = str5;
                defaultLocale2 = defaultLocale2;
                materialized$iv$iv = materialized$iv$iv2;
            }
            $composer$iv.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
            $dirty = $dirty2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.Month.2
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

                public final void invoke(Composer composer, int i5) {
                    DatePickerKt.Month(month, function1, todayMillis, startDateMillis, endDateMillis, rangeSelectionInfo, dateFormatter, selectableDates, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public static final int numberOfMonthsInRange(IntRange yearRange) {
        return ((yearRange.getLast() - yearRange.getFirst()) + 1) * 12;
    }

    private static final String dayContentDescription(boolean rangeSelectionEnabled, boolean isToday, boolean isStartDate, boolean isEndDate, boolean isInRange, Composer $composer, int $changed) {
        boolean z;
        ComposerKt.sourceInformationMarkerStart($composer, 502032503, "C(dayContentDescription)P(4,3,2):DatePicker.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(502032503, $changed, -1, "androidx.compose.material3.dayContentDescription (DatePicker.kt:1909)");
        }
        StringBuilder descriptionBuilder = new StringBuilder();
        $composer.startReplaceGroup(-647730741);
        ComposerKt.sourceInformation($composer, "");
        boolean z2 = false;
        if (rangeSelectionEnabled) {
            if (isStartDate) {
                $composer.startReplaceGroup(-647727716);
                ComposerKt.sourceInformation($composer, "1914@87077L56");
                Strings.Companion companion = Strings.INSTANCE;
                descriptionBuilder.append(Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_range_picker_start_headline), $composer, 0));
                $composer.endReplaceGroup();
            } else if (isEndDate) {
                $composer.startReplaceGroup(-647723718);
                ComposerKt.sourceInformation($composer, "1916@87202L54");
                Strings.Companion companion2 = Strings.INSTANCE;
                descriptionBuilder.append(Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_range_picker_end_headline), $composer, 0));
                $composer.endReplaceGroup();
            } else if (isInRange) {
                $composer.startReplaceGroup(-647719783);
                ComposerKt.sourceInformation($composer, "1918@87325L53");
                Strings.Companion companion3 = Strings.INSTANCE;
                descriptionBuilder.append(Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_range_picker_day_in_range), $composer, 0));
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(1395591750);
                $composer.endReplaceGroup();
            }
        }
        $composer.endReplaceGroup();
        $composer.startReplaceGroup(-647717033);
        ComposerKt.sourceInformation($composer, "1923@87526L54");
        if (isToday) {
            if (descriptionBuilder.length() <= 0) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                descriptionBuilder.append(", ");
            }
            Strings.Companion companion4 = Strings.INSTANCE;
            descriptionBuilder.append(Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_today_description), $composer, 0));
        }
        $composer.endReplaceGroup();
        if (descriptionBuilder.length() == 0) {
            z2 = true;
        }
        String string = z2 ? null : descriptionBuilder.toString();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Day(final Modifier modifier, final boolean selected, final Function0<Unit> function0, final boolean animateChecked, final boolean enabled, final boolean today, final boolean inRange, final String description, final DatePickerColors colors, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Object value$iv;
        BorderStroke borderStrokeM616BorderStrokecXLIe8U;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-1434777861);
        ComposerKt.sourceInformation($composer3, "C(Day)P(6,8,7!1,4,9,5,3)1951@88438L114,1956@88638L5,1959@88697L83,1963@88864L190,1979@89366L319,1942@88009L1676:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(selected) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(animateChecked) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(enabled) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changed(today) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer3.changed(inRange) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer3.changed(description) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty |= $composer3.changed(colors) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int $dirty2 = $dirty;
        if ((306783379 & $dirty2) != 306783378 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1434777861, $dirty2, -1, "androidx.compose.material3.Day (DatePicker.kt:1941)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 67509195, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv = (29360128 & $dirty2) == 8388608;
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$Day$1$1
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
                        SemanticsPropertiesKt.setText($this$semantics, new AnnotatedString(description, null, null, 6, null));
                        SemanticsPropertiesKt.m6308setRolekuIjeqM($this$semantics, Role.INSTANCE.m6289getButtono7Vup1c());
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifier, true, (Function1) value$iv);
            Shape value = ShapesKt.getValue(DatePickerModalTokens.INSTANCE.getDateContainerShape(), $composer3, 6);
            long jM4544unboximpl = colors.dayContainerColor$material3_release(selected, enabled, animateChecked, $composer3, (($dirty2 >> 3) & 14) | (($dirty2 >> 9) & 112) | (($dirty2 >> 3) & 896) | (($dirty2 >> 15) & 7168)).getValue().m4544unboximpl();
            long jM4544unboximpl2 = colors.dayContentColor$material3_release(today, selected, inRange, enabled, $composer3, (($dirty2 >> 15) & 14) | ($dirty2 & 112) | (($dirty2 >> 12) & 896) | (($dirty2 >> 3) & 7168) | (57344 & ($dirty2 >> 12))).getValue().m4544unboximpl();
            if (today && !selected) {
                borderStrokeM616BorderStrokecXLIe8U = BorderStrokeKt.m616BorderStrokecXLIe8U(DatePickerModalTokens.INSTANCE.m3520getDateTodayContainerOutlineWidthD9Ej5fM(), colors.getTodayDateBorderColor());
            } else {
                borderStrokeM616BorderStrokecXLIe8U = null;
            }
            $composer2 = $composer3;
            SurfaceKt.m2918Surfaced85dljk(selected, function0, modifierSemantics, enabled, value, jM4544unboximpl, jM4544unboximpl2, 0.0f, 0.0f, borderStrokeM616BorderStrokecXLIe8U, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(-2031780827, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.Day.2
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

                public final void invoke(Composer $composer4, int $changed2) {
                    Function0<ComposeUiNode> function1;
                    ComposerKt.sourceInformation($composer4, "C1980@89376L303:DatePicker.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2031780827, $changed2, -1, "androidx.compose.material3.Day.<anonymous> (DatePicker.kt:1980)");
                        }
                        Modifier modifier$iv = SizeKt.m1073requiredSizeVpY3zN4(Modifier.INSTANCE, DatePickerModalTokens.INSTANCE.m3519getDateStateLayerWidthD9Ej5fM(), DatePickerModalTokens.INSTANCE.m3518getDateStateLayerHeightD9Ej5fM());
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function3 = function2;
                        ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv = (54 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            function1 = constructor;
                            $composer4.createNode(function1);
                        } else {
                            function1 = constructor;
                            $composer4.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer4);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i2 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 2118753635, "C1988@89660L9:DatePicker.kt#uh7d8r");
                        function3.invoke($composer4, 0);
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
            }, $composer3, 54), $composer2, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112) | (($dirty2 >> 3) & 7168), 48, 1408);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.Day.3
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
                    DatePickerKt.Day(modifier, selected, function0, animateChecked, enabled, today, inRange, description, colors, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void YearPicker(final Modifier modifier, final long displayedMonthMillis, final Function1<? super Integer, Unit> function1, final SelectableDates selectableDates, final CalendarModel calendarModel, final IntRange yearRange, final DatePickerColors colors, Composer $composer, final int $changed) {
        int $dirty;
        Composer $composer2 = $composer.startRestartGroup(-1286899812);
        ComposerKt.sourceInformation($composer2, "C(YearPicker)P(3,2,4,5!1,6)2004@90070L5,2004@90077L4227,2004@89996L4308:DatePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changed(displayedMonthMillis) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changed(selectableDates) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changedInstance(calendarModel) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(yearRange) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= $composer2.changed(colors) ? 1048576 : 524288;
        }
        if ((599187 & $dirty2) != 599186 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1286899812, $dirty2, -1, "androidx.compose.material3.YearPicker (DatePicker.kt:2003)");
            }
            $dirty = $dirty2;
            TextKt.ProvideTextStyle(TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getSelectionYearLabelTextFont(), $composer2, 6), ComposableLambdaKt.rememberComposableLambda(1301915789, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.YearPicker.1
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

                public final void invoke(Composer $composer3, int $changed2) {
                    Object value$iv$iv;
                    ComposerKt.sourceInformation($composer3, "C2008@90272L281,2015@90734L24,2016@90799L53,2017@90891L51,2032@91637L2661,2018@90951L3347:DatePicker.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1301915789, $changed2, -1, "androidx.compose.material3.YearPicker.<anonymous> (DatePicker.kt:2005)");
                        }
                        final int currentYear = calendarModel.getMonth(calendarModel.getToday()).getYear();
                        final int displayedYear = calendarModel.getMonth(displayedMonthMillis).getYear();
                        final LazyGridState lazyGridState = LazyGridStateKt.rememberLazyGridState(Math.max(0, (displayedYear - yearRange.getFirst()) - 3), 0, $composer3, 0, 2);
                        long containerColor = colors.getContainerColor();
                        ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart($composer3, -954363344, "CC(remember):Effects.kt#9igjgp");
                        Object it$iv$iv = $composer3.rememberedValue();
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        final CoroutineScope coroutineScope = wrapper$iv.getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Strings.Companion companion = Strings.INSTANCE;
                        final String scrollToEarlierYearsLabel = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_scroll_to_earlier_years), $composer3, 0);
                        Strings.Companion companion2 = Strings.INSTANCE;
                        final String scrollToLaterYearsLabel = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_scroll_to_later_years), $composer3, 0);
                        GridCells.Fixed fixed = new GridCells.Fixed(3);
                        Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(BackgroundKt.m589backgroundbw27NRU$default(modifier, containerColor, null, 2, null), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt.YearPicker.1.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                SemanticsPropertiesKt.setVerticalScrollAxisRange($this$semantics, new ScrollAxisRange(new Function0<Float>() { // from class: androidx.compose.material3.DatePickerKt.YearPicker.1.1.1
                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Float invoke() {
                                        return Float.valueOf(0.0f);
                                    }
                                }, new Function0<Float>() { // from class: androidx.compose.material3.DatePickerKt.YearPicker.1.1.2
                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Float invoke() {
                                        return Float.valueOf(0.0f);
                                    }
                                }, false, 4, null));
                            }
                        }, 1, null);
                        Arrangement.HorizontalOrVertical spaceEvenly = Arrangement.INSTANCE.getSpaceEvenly();
                        GridCells.Fixed fixed2 = fixed;
                        Arrangement.HorizontalOrVertical horizontalOrVerticalM914spacedBy0680j_4 = Arrangement.INSTANCE.m914spacedBy0680j_4(DatePickerKt.YearsVerticalPadding);
                        Arrangement.HorizontalOrVertical horizontalOrVertical = spaceEvenly;
                        ComposerKt.sourceInformationMarkerStart($composer3, 15784914, "CC(remember):DatePicker.kt#9igjgp");
                        boolean invalid$iv = $composer3.changedInstance(yearRange) | $composer3.changed(lazyGridState) | $composer3.changedInstance(coroutineScope) | $composer3.changed(scrollToEarlierYearsLabel) | $composer3.changed(scrollToLaterYearsLabel) | $composer3.changed(displayedYear) | $composer3.changed(currentYear) | $composer3.changed(function1) | $composer3.changed(selectableDates) | $composer3.changed(colors);
                        final IntRange intRange = yearRange;
                        final Function1<Integer, Unit> function2 = function1;
                        final SelectableDates selectableDates2 = selectableDates;
                        final DatePickerColors datePickerColors = colors;
                        Object value$iv = $composer3.rememberedValue();
                        if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv = new Function1<LazyGridScope, Unit>() { // from class: androidx.compose.material3.DatePickerKt$YearPicker$1$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(LazyGridScope lazyGridScope) {
                                    invoke2(lazyGridScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(LazyGridScope $this$LazyVerticalGrid) {
                                    int iCount = CollectionsKt.count(intRange);
                                    final IntRange intRange2 = intRange;
                                    final LazyGridState lazyGridState2 = lazyGridState;
                                    final CoroutineScope coroutineScope2 = coroutineScope;
                                    final String str = scrollToEarlierYearsLabel;
                                    final String str2 = scrollToLaterYearsLabel;
                                    final int i = displayedYear;
                                    final int i2 = currentYear;
                                    final Function1<Integer, Unit> function3 = function2;
                                    final SelectableDates selectableDates3 = selectableDates2;
                                    final DatePickerColors datePickerColors2 = datePickerColors;
                                    LazyGridScope.items$default($this$LazyVerticalGrid, iCount, null, null, null, ComposableLambdaKt.composableLambdaInstance(1040623618, true, new Function4<LazyGridItemScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$YearPicker$1$2$1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(4);
                                        }

                                        @Override // kotlin.jvm.functions.Function4
                                        public /* bridge */ /* synthetic */ Unit invoke(LazyGridItemScope lazyGridItemScope, Integer num, Composer composer, Integer num2) {
                                            invoke(lazyGridItemScope, num.intValue(), composer, num2.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(LazyGridItemScope $this$items, final int it, Composer $composer4, int $changed3) {
                                            Object value$iv2;
                                            Object value$iv3;
                                            ComposerKt.sourceInformation($composer4, "C2042@92151L1332,2067@93640L32,*2070@93810L54,2073@93971L303,2036@91815L2459:DatePicker.kt#uh7d8r");
                                            int $dirty3 = $changed3;
                                            if (($changed3 & 48) == 0) {
                                                $dirty3 |= $composer4.changed(it) ? 32 : 16;
                                            }
                                            int $dirty4 = $dirty3;
                                            if (($dirty4 & 145) != 144 || !$composer4.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1040623618, $dirty4, -1, "androidx.compose.material3.YearPicker.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:2034)");
                                                }
                                                final int selectedYear = it + intRange2.getFirst();
                                                final String localizedYear = CalendarLocale_jvmKt.toLocalString$default(selectedYear, 0, 0, false, 7, null);
                                                Modifier modifierM1073requiredSizeVpY3zN4 = SizeKt.m1073requiredSizeVpY3zN4(Modifier.INSTANCE, DatePickerModalTokens.INSTANCE.m3527getSelectionYearContainerWidthD9Ej5fM(), DatePickerModalTokens.INSTANCE.m3526getSelectionYearContainerHeightD9Ej5fM());
                                                ComposerKt.sourceInformationMarkerStart($composer4, -213653794, "CC(remember):DatePicker.kt#9igjgp");
                                                boolean invalid$iv2 = $composer4.changed(lazyGridState2) | (($dirty4 & 112) == 32) | $composer4.changedInstance(coroutineScope2) | $composer4.changed(str) | $composer4.changed(str2);
                                                final LazyGridState lazyGridState3 = lazyGridState2;
                                                final CoroutineScope coroutineScope3 = coroutineScope2;
                                                final String str3 = str;
                                                final String str4 = str2;
                                                Object it$iv = $composer4.rememberedValue();
                                                if (invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                                                    value$iv2 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$YearPicker$1$2$1$1$1$1
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(1);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function1
                                                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                            invoke2(semanticsPropertyReceiver);
                                                            return Unit.INSTANCE;
                                                        }

                                                        /* JADX WARN: Code duplicated, block: B:13:0x0034  */
                                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                        public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                                            List listCustomScrollActions;
                                                            if (lazyGridState3.getFirstVisibleItemIndex() == it) {
                                                                listCustomScrollActions = DatePickerKt.customScrollActions(lazyGridState3, coroutineScope3, str3, str4);
                                                            } else {
                                                                LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) CollectionsKt.lastOrNull((List) lazyGridState3.getLayoutInfo().getVisibleItemsInfo());
                                                                boolean z = false;
                                                                if (lazyGridItemInfo != null && lazyGridItemInfo.getIndex() == it) {
                                                                    z = true;
                                                                }
                                                                if (z) {
                                                                    listCustomScrollActions = DatePickerKt.customScrollActions(lazyGridState3, coroutineScope3, str3, str4);
                                                                } else {
                                                                    listCustomScrollActions = CollectionsKt.emptyList();
                                                                }
                                                            }
                                                            SemanticsPropertiesKt.setCustomActions($this$semantics, listCustomScrollActions);
                                                        }
                                                    };
                                                    $composer4.updateRememberedValue(value$iv2);
                                                } else {
                                                    value$iv2 = it$iv;
                                                }
                                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                                Modifier modifierSemantics$default2 = SemanticsModifierKt.semantics$default(modifierM1073requiredSizeVpY3zN4, false, (Function1) value$iv2, 1, null);
                                                boolean z = selectedYear == i;
                                                boolean z2 = selectedYear == i2;
                                                ComposerKt.sourceInformationMarkerStart($composer4, -213607446, "CC(remember):DatePicker.kt#9igjgp");
                                                boolean invalid$iv3 = $composer4.changed(function3) | $composer4.changed(selectedYear);
                                                final Function1<Integer, Unit> function4 = function3;
                                                Object it$iv2 = $composer4.rememberedValue();
                                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                                    value$iv3 = new Function0<Unit>() { // from class: androidx.compose.material3.DatePickerKt$YearPicker$1$2$1$1$2$1
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        /* JADX WARN: Multi-variable type inference failed */
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
                                                            function4.invoke(Integer.valueOf(selectedYear));
                                                        }
                                                    };
                                                    $composer4.updateRememberedValue(value$iv3);
                                                } else {
                                                    value$iv3 = it$iv2;
                                                }
                                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                                boolean zIsSelectableYear = selectableDates3.isSelectableYear(selectedYear);
                                                Strings.Companion companion3 = Strings.INSTANCE;
                                                String str5 = String.format(Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_navigate_to_year_description), $composer4, 0), Arrays.copyOf(new Object[]{localizedYear}, 1));
                                                Intrinsics.checkNotNullExpressionValue(str5, "format(this, *args)");
                                                DatePickerKt.Year(modifierSemantics$default2, z, z2, (Function0) value$iv3, zIsSelectableYear, str5, datePickerColors2, ComposableLambdaKt.rememberComposableLambda(882189459, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.YearPicker.1.2.1.1.3
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
                                                        ComposerKt.sourceInformation($composer5, "C2074@93993L263:DatePicker.kt#uh7d8r");
                                                        if (($changed4 & 3) != 2 || !$composer5.getSkipping()) {
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(882189459, $changed4, -1, "androidx.compose.material3.YearPicker.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:2074)");
                                                            }
                                                            TextKt.m3067Text4IGK_g(localizedYear, SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt.YearPicker.1.2.1.1.3.1
                                                                @Override // kotlin.jvm.functions.Function1
                                                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                                                    invoke2(semanticsPropertyReceiver);
                                                                    return Unit.INSTANCE;
                                                                }

                                                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                public final void invoke2(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
                                                                }
                                                            }), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer5, 0, 0, 130556);
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventEnd();
                                                                return;
                                                            }
                                                            return;
                                                        }
                                                        $composer5.skipToGroupEnd();
                                                    }
                                                }, $composer4, 54), $composer4, 12582912);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer4.skipToGroupEnd();
                                        }
                                    }), 14, null);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv);
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        LazyGridDslKt.LazyVerticalGrid(fixed2, modifierSemantics$default, lazyGridState, null, false, horizontalOrVerticalM914spacedBy0680j_4, horizontalOrVertical, null, false, (Function1) value$iv, $composer3, 1769472, 408);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
            $dirty = $dirty2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.YearPicker.2
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
                    DatePickerKt.YearPicker(modifier, displayedMonthMillis, function1, selectableDates, calendarModel, yearRange, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Year(final Modifier modifier, final boolean selected, final boolean currentYear, final Function0<Unit> function0, final boolean enabled, final String description, final DatePickerColors colors, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(238547184);
        ComposerKt.sourceInformation($composer3, "C(Year)P(5,7,2,6,4,3)2099@94618L393,2117@95386L102,2122@95584L5,2123@95614L58,2126@95739L83,2129@95878L106,2110@95016L968:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(selected) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(currentYear) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(enabled) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changed(description) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer3.changed(colors) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty) != 4793490 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(238547184, $dirty, -1, "androidx.compose.material3.Year (DatePicker.kt:2097)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1682587287, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv = (($dirty & 896) == 256) | (($dirty & 112) == 32);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                if (currentYear && !selected) {
                    value$iv = BorderStrokeKt.m616BorderStrokecXLIe8U(DatePickerModalTokens.INSTANCE.m3520getDateTodayContainerOutlineWidthD9Ej5fM(), colors.getTodayDateBorderColor());
                } else {
                    value$iv = null;
                }
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            BorderStroke border = (BorderStroke) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1682563002, "CC(remember):DatePicker.kt#9igjgp");
            boolean invalid$iv2 = (458752 & $dirty) == 131072;
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$Year$1$1
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
                        SemanticsPropertiesKt.setText($this$semantics, new AnnotatedString(description, null, null, 6, null));
                        SemanticsPropertiesKt.m6308setRolekuIjeqM($this$semantics, Role.INSTANCE.m6289getButtono7Vup1c());
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            int $dirty2 = $dirty;
            $composer2 = $composer3;
            SurfaceKt.m2918Surfaced85dljk(selected, function0, SemanticsModifierKt.semantics(modifier, true, (Function1) value$iv2), enabled, ShapesKt.getValue(DatePickerModalTokens.INSTANCE.getSelectionYearStateLayerShape(), $composer3, 6), colors.yearContainerColor$material3_release(selected, enabled, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112) | (($dirty >> 12) & 896)).getValue().m4544unboximpl(), colors.yearContentColor$material3_release(currentYear, selected, enabled, $composer3, (($dirty >> 6) & 14) | ($dirty & 112) | (($dirty >> 6) & 896) | (($dirty >> 9) & 7168)).getValue().m4544unboximpl(), 0.0f, 0.0f, border, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(-1573188346, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.Year.2
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

                public final void invoke(Composer $composer4, int $changed2) {
                    Function0<ComposeUiNode> function1;
                    ComposerKt.sourceInformation($composer4, "C2130@95888L90:DatePicker.kt#uh7d8r");
                    if (($changed2 & 3) == 2 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1573188346, $changed2, -1, "androidx.compose.material3.Year.<anonymous> (DatePicker.kt:2130)");
                    }
                    Modifier modifier$iv = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                    Function2<Composer, Integer, Unit> function3 = function2;
                    ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv = (54 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        function1 = constructor;
                        $composer4.createNode(function1);
                    } else {
                        function1 = constructor;
                        $composer4.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer4);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i2 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -2124301604, "C2130@95967L9:DatePicker.kt#uh7d8r");
                    function3.invoke($composer4, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, (($dirty2 >> 3) & 14) | (($dirty2 >> 6) & 112) | (($dirty2 >> 3) & 7168), 48, 1408);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.Year.3
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
                    DatePickerKt.Year(modifier, selected, currentYear, function0, enabled, description, colors, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MonthsNavigation(final Modifier modifier, final boolean nextAvailable, final boolean previousAvailable, final boolean yearPickerVisible, final String yearPickerText, final Function0<Unit> function0, final Function0<Unit> function1, final Function0<Unit> function2, final DatePickerColors colors, Composer $composer, final int $changed) {
        Arrangement.HorizontalOrVertical horizontalArrangement$iv;
        Composer $composer2 = $composer.startRestartGroup(-773929258);
        ComposerKt.sourceInformation($composer2, "C(MonthsNavigation)P(1,2,6,8,7,3,4,5)2151@96490L2049:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(nextAvailable) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(previousAvailable) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(yearPickerVisible) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(yearPickerText) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty |= $composer2.changed(colors) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty2 = $dirty;
        if ((38347923 & $dirty2) != 38347922 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-773929258, $dirty2, -1, "androidx.compose.material3.MonthsNavigation (DatePicker.kt:2150)");
            }
            Modifier modifier$iv = SizeKt.m1068requiredHeight3ABfNKs(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), MonthYearHeight);
            if (yearPickerVisible) {
                horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            } else {
                horizontalArrangement$iv = Arrangement.INSTANCE.getSpaceBetween();
            }
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
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
            ComposerKt.sourceInformationMarkerStart($composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -856633141, "C2161@96905L1628,2161@96822L1711:DatePicker.kt#uh7d8r");
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4524boximpl(colors.getNavigationContentColor())), ComposableLambdaKt.rememberComposableLambda(-962805198, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$MonthsNavigation$1$1
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
                    ComposerKt.sourceInformation($composer3, "C2166@97104L559,2163@96970L693,2181@97813L696:DatePicker.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-962805198, $changed2, -1, "androidx.compose.material3.MonthsNavigation.<anonymous>.<anonymous> (DatePicker.kt:2163)");
                        }
                        Function0<Unit> function3 = function2;
                        boolean z = yearPickerVisible;
                        final String str = yearPickerText;
                        DatePickerKt.YearPickerMenuButton(function3, z, null, ComposableLambdaKt.rememberComposableLambda(1377272806, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt$MonthsNavigation$1$1.1
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
                                Object value$iv;
                                ComposerKt.sourceInformation($composer4, "C2170@97245L386,2167@97122L527:DatePicker.kt#uh7d8r");
                                if (($changed3 & 3) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1377272806, $changed3, -1, "androidx.compose.material3.MonthsNavigation.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:2167)");
                                    }
                                    String str2 = str;
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -557901719, "CC(remember):DatePicker.kt#9igjgp");
                                    boolean invalid$iv = $composer4.changed(str);
                                    final String str3 = str;
                                    Object it$iv = $composer4.rememberedValue();
                                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                        value$iv = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DatePickerKt$MonthsNavigation$1$1$1$1$1
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
                                                SemanticsPropertiesKt.m6307setLiveRegionhR3wRGc($this$semantics, LiveRegionMode.INSTANCE.m6281getPolite0phEisY());
                                                SemanticsPropertiesKt.setContentDescription($this$semantics, str3);
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv);
                                    } else {
                                        value$iv = it$iv;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    TextKt.m3067Text4IGK_g(str2, SemanticsModifierKt.semantics$default(companion, false, (Function1) value$iv, 1, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131068);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer4.skipToGroupEnd();
                            }
                        }, $composer3, 54), $composer3, 3072, 4);
                        if (!yearPickerVisible) {
                            Function0<Unit> function4 = function1;
                            boolean z2 = previousAvailable;
                            Function0<Unit> function5 = function0;
                            boolean z3 = nextAvailable;
                            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                            Modifier modifier$iv2 = Modifier.INSTANCE;
                            Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getStart();
                            Alignment.Vertical verticalAlignment$iv2 = Alignment.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv2 = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
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
                            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer3);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                            }
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                            int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                            int i4 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -114486608, "C2182@97839L321,2188@98181L310:DatePicker.kt#uh7d8r");
                            IconButtonKt.IconButton(function4, null, z2, null, null, ComposableSingletons$DatePickerKt.INSTANCE.m2337getLambda3$material3_release(), $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 26);
                            IconButtonKt.IconButton(function5, null, z3, null, null, ComposableSingletons$DatePickerKt.INSTANCE.m2338getLambda4$material3_release(), $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 26);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.MonthsNavigation.2
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
                    DatePickerKt.MonthsNavigation(modifier, nextAvailable, previousAvailable, yearPickerVisible, yearPickerText, function0, function1, function2, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void YearPickerMenuButton(final Function0<Unit> function0, final boolean expanded, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(409654418);
        ComposerKt.sourceInformation($composer2, "C(YearPickerMenuButton)P(3,1,2)2212@98959L7,2212@98909L58,2215@99024L453,2208@98780L697:DatePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(expanded) ? 32 : 16;
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
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(409654418, $dirty2, -1, "androidx.compose.material3.YearPickerMenuButton (DatePicker.kt:2207)");
            }
            RoundedCornerShape circleShape = RoundedCornerShapeKt.getCircleShape();
            ButtonDefaults buttonDefaults = ButtonDefaults.INSTANCE;
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ButtonKt.TextButton(function0, modifier4, false, circleShape, buttonDefaults.m2194textButtonColorsro_MJ88(0L, ((Color) objConsume).m4544unboximpl(), 0L, 0L, $composer2, 24576, 13), null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1899012021, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.YearPickerMenuButton.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                    invoke(rowScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RowScope $this$TextButton, Composer $composer3, int $changed2) {
                    String str;
                    ComposerKt.sourceInformation($composer3, "C2216@99034L9,2217@99052L49,2218@99110L361:DatePicker.kt#uh7d8r");
                    if (($changed2 & 17) != 16 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1899012021, $changed2, -1, "androidx.compose.material3.YearPickerMenuButton.<anonymous> (DatePicker.kt:2216)");
                        }
                        function2.invoke($composer3, 0);
                        SpacerKt.Spacer(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, ButtonDefaults.INSTANCE.m2190getIconSpacingD9Ej5fM()), $composer3, 6);
                        ImageVector arrowDropDown = ArrowDropDownKt.getArrowDropDown(Icons.Filled.INSTANCE);
                        if (expanded) {
                            $composer3.startReplaceGroup(-1360040181);
                            ComposerKt.sourceInformation($composer3, "2222@99241L49");
                            Strings.Companion companion = Strings.INSTANCE;
                            String strM3349getString2EP1pXo = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_switch_to_day_selection), $composer3, 0);
                            $composer3.endReplaceGroup();
                            str = strM3349getString2EP1pXo;
                        } else {
                            $composer3.startReplaceGroup(-1359945910);
                            ComposerKt.sourceInformation($composer3, "2224@99336L50");
                            Strings.Companion companion2 = Strings.INSTANCE;
                            String strM3349getString2EP1pXo2 = Strings_androidKt.m3349getString2EP1pXo(Strings.m3279constructorimpl(R.string.m3c_date_picker_switch_to_year_selection), $composer3, 0);
                            $composer3.endReplaceGroup();
                            str = strM3349getString2EP1pXo2;
                        }
                        IconKt.m2524Iconww6aTOc(arrowDropDown, str, RotateKt.rotate(Modifier.INSTANCE, expanded ? 180.0f : 0.0f), 0L, $composer3, 0, 8);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ($dirty2 & 14) | 807075840 | (($dirty2 >> 3) & 112), 388);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerKt.YearPickerMenuButton.2
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
                    DatePickerKt.YearPickerMenuButton(function0, expanded, modifier5, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<CustomAccessibilityAction> customScrollActions(final LazyGridState state, final CoroutineScope coroutineScope, String scrollUpLabel, String scrollDownLabel) {
        return CollectionsKt.listOf((Object[]) new CustomAccessibilityAction[]{new CustomAccessibilityAction(scrollUpLabel, new Function0<Boolean>() { // from class: androidx.compose.material3.DatePickerKt$customScrollActions$scrollUpAction$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                boolean z;
                if (state.getCanScrollBackward()) {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(state, null), 3, null);
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }

            /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$customScrollActions$scrollUpAction$1$1, reason: invalid class name */
            /* JADX INFO: compiled from: DatePicker.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$customScrollActions$scrollUpAction$1$1", f = "DatePicker.kt", i = {}, l = {2242}, m = "invokeSuspend", n = {}, s = {})
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ LazyGridState $state;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(LazyGridState lazyGridState, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$state = lazyGridState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$state, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            this.label = 1;
                            if (LazyGridState.scrollToItem$default(this.$state, this.$state.getFirstVisibleItemIndex() - 3, 0, this, 2, null) == coroutine_suspended) {
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
        }), new CustomAccessibilityAction(scrollDownLabel, new Function0<Boolean>() { // from class: androidx.compose.material3.DatePickerKt$customScrollActions$scrollDownAction$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                boolean z;
                if (state.getCanScrollForward()) {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(state, null), 3, null);
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }

            /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$customScrollActions$scrollDownAction$1$1, reason: invalid class name */
            /* JADX INFO: compiled from: DatePicker.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.DatePickerKt$customScrollActions$scrollDownAction$1$1", f = "DatePicker.kt", i = {}, l = {2250}, m = "invokeSuspend", n = {}, s = {})
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ LazyGridState $state;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(LazyGridState lazyGridState, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$state = lazyGridState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$state, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            this.label = 1;
                            if (LazyGridState.scrollToItem$default(this.$state, this.$state.getFirstVisibleItemIndex() + 3, 0, this, 2, null) == coroutine_suspended) {
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
        })});
    }

    public static final float getRecommendedSizeForAccessibility() {
        return RecommendedSizeForAccessibility;
    }

    public static final float getMonthYearHeight() {
        return MonthYearHeight;
    }

    public static final float getDatePickerHorizontalPadding() {
        return DatePickerHorizontalPadding;
    }

    public static final PaddingValues getDatePickerModeTogglePadding() {
        return DatePickerModeTogglePadding;
    }
}
