package androidx.compose.foundation.pager;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ScrollingContainerKt;
import androidx.compose.foundation.gestures.BringIntoViewSpec;
import androidx.compose.foundation.gestures.BringIntoViewSpec_androidKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocalKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsState;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.reflect.KProperty0;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: LazyLayoutPager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÞ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162#\u0010\u0017\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"21\u0010#\u001a-\u0012\u0004\u0012\u00020%\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u00010$¢\u0006\u0002\b'¢\u0006\u0002\b(H\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a\u0081\u0001\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,2\u0006\u0010\u0004\u001a\u00020\u000521\u0010#\u001a-\u0012\u0004\u0012\u00020%\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u00010$¢\u0006\u0002\b'¢\u0006\u0002\b(2#\u0010\u0017\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00182\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00100,H\u0003¢\u0006\u0002\u0010/\u001a\u0014\u00100\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00061"}, d2 = {"Pager", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/pager/PagerState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "flingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "userScrollEnabled", "beyondViewportPageCount", "", "pageSpacing", "Landroidx/compose/ui/unit/Dp;", "pageSize", "Landroidx/compose/foundation/pager/PageSize;", "pageNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "key", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "index", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "snapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "pageContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/pager/PagerScope;", "page", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "Pager-uYRUAWA", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/gestures/TargetedFlingBehavior;ZIFLandroidx/compose/foundation/pager/PageSize;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;III)V", "rememberPagerItemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "pageCount", "(Landroidx/compose/foundation/pager/PagerState;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function0;", "dragDirectionDetector", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutPagerKt {
    /* JADX WARN: Code duplicated, block: B:101:0x0130  */
    /* JADX WARN: Code duplicated, block: B:103:0x0138  */
    /* JADX WARN: Code duplicated, block: B:104:0x013b  */
    /* JADX WARN: Code duplicated, block: B:106:0x0140  */
    /* JADX WARN: Code duplicated, block: B:109:0x0146  */
    /* JADX WARN: Code duplicated, block: B:110:0x0149  */
    /* JADX WARN: Code duplicated, block: B:112:0x014d  */
    /* JADX WARN: Code duplicated, block: B:114:0x0153  */
    /* JADX WARN: Code duplicated, block: B:115:0x0155  */
    /* JADX WARN: Code duplicated, block: B:119:0x015b  */
    /* JADX WARN: Code duplicated, block: B:120:0x0160  */
    /* JADX WARN: Code duplicated, block: B:122:0x0164  */
    /* JADX WARN: Code duplicated, block: B:124:0x016c  */
    /* JADX WARN: Code duplicated, block: B:125:0x016f  */
    /* JADX WARN: Code duplicated, block: B:127:0x0174  */
    /* JADX WARN: Code duplicated, block: B:130:0x017a  */
    /* JADX WARN: Code duplicated, block: B:131:0x017f  */
    /* JADX WARN: Code duplicated, block: B:133:0x0183  */
    /* JADX WARN: Code duplicated, block: B:136:0x018c  */
    /* JADX WARN: Code duplicated, block: B:138:0x0191  */
    /* JADX WARN: Code duplicated, block: B:141:0x0197  */
    /* JADX WARN: Code duplicated, block: B:142:0x019c  */
    /* JADX WARN: Code duplicated, block: B:144:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:147:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:149:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:152:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:153:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:155:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:157:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:158:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:160:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:163:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:164:0x01db  */
    /* JADX WARN: Code duplicated, block: B:166:0x01df  */
    /* JADX WARN: Code duplicated, block: B:168:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:169:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:171:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:180:0x021c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:181:0x021e  */
    /* JADX WARN: Code duplicated, block: B:183:0x0222  */
    /* JADX WARN: Code duplicated, block: B:184:0x022b  */
    /* JADX WARN: Code duplicated, block: B:187:0x0233  */
    /* JADX WARN: Code duplicated, block: B:190:0x0242  */
    /* JADX WARN: Code duplicated, block: B:191:0x0245  */
    /* JADX WARN: Code duplicated, block: B:193:0x024c  */
    /* JADX WARN: Code duplicated, block: B:195:0x025a  */
    /* JADX WARN: Code duplicated, block: B:196:0x025d  */
    /* JADX WARN: Code duplicated, block: B:199:0x026c  */
    /* JADX WARN: Code duplicated, block: B:203:0x0279 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:207:0x02e0  */
    /* JADX WARN: Code duplicated, block: B:208:0x02fd  */
    /* JADX WARN: Code duplicated, block: B:211:0x0327  */
    /* JADX WARN: Code duplicated, block: B:212:0x032a  */
    /* JADX WARN: Code duplicated, block: B:215:0x0335  */
    /* JADX WARN: Code duplicated, block: B:219:0x0342 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:223:0x03b0  */
    /* JADX WARN: Code duplicated, block: B:224:0x03b3  */
    /* JADX WARN: Code duplicated, block: B:227:0x03c9  */
    /* JADX WARN: Code duplicated, block: B:228:0x03cc  */
    /* JADX WARN: Code duplicated, block: B:231:0x03d4  */
    /* JADX WARN: Code duplicated, block: B:232:0x03d7  */
    /* JADX WARN: Code duplicated, block: B:235:0x03e3  */
    /* JADX WARN: Code duplicated, block: B:239:0x03f0 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:243:0x042e  */
    /* JADX WARN: Code duplicated, block: B:244:0x0431  */
    /* JADX WARN: Code duplicated, block: B:247:0x0441  */
    /* JADX WARN: Code duplicated, block: B:251:0x044e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:255:0x04ae  */
    /* JADX WARN: Code duplicated, block: B:256:0x04b1  */
    /* JADX WARN: Code duplicated, block: B:259:0x0570  */
    /* JADX WARN: Code duplicated, block: B:263:0x057d  */
    /* JADX WARN: Code duplicated, block: B:265:0x05b6  */
    /* JADX WARN: Code duplicated, block: B:267:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:76:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:77:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:79:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:81:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:82:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:84:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:87:0x0106  */
    /* JADX WARN: Code duplicated, block: B:88:0x010b  */
    /* JADX WARN: Code duplicated, block: B:90:0x010f  */
    /* JADX WARN: Code duplicated, block: B:92:0x0117  */
    /* JADX WARN: Code duplicated, block: B:93:0x011a  */
    /* JADX WARN: Code duplicated, block: B:95:0x011f  */
    /* JADX WARN: Code duplicated, block: B:98:0x0125  */
    /* JADX WARN: Code duplicated, block: B:99:0x012b  */
    /* JADX INFO: renamed from: Pager-uYRUAWA, reason: not valid java name */
    public static final void m1267PageruYRUAWA(final Modifier modifier, final PagerState state, final PaddingValues contentPadding, final boolean reverseLayout, final Orientation orientation, final TargetedFlingBehavior flingBehavior, final boolean userScrollEnabled, int beyondViewportPageCount, float pageSpacing, final PageSize pageSize, final NestedScrollConnection pageNestedScrollConnection, final Function1<? super Integer, ? extends Object> function1, final Alignment.Horizontal horizontalAlignment, final Alignment.Vertical verticalAlignment, final SnapPosition snapPosition, final Function4<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        int i3;
        int beyondViewportPageCount2;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        float pageSpacing2;
        boolean z;
        boolean invalid$iv;
        Object value$iv;
        int $dirty;
        Object value$iv$iv;
        boolean invalid$iv2;
        Object value$iv2;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean invalid$iv3;
        Object value$iv3;
        boolean z5;
        boolean invalid$iv4;
        Object value$iv4;
        boolean z6;
        Composer $composer2;
        float pageSpacing3;
        int beyondViewportPageCount3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer $composer3 = $composer.startRestartGroup(538371694);
        ComposerKt.sourceInformation($composer3, "C(Pager)P(5,13,1,11,6,2,14!1,10:c#ui.unit.Dp,9,8,4!1,15,12)111@5277L19,107@5162L134,113@5323L24,128@5906L19,115@5373L558,131@5957L92,136@6083L94,140@6237L7,141@6278L147,152@6576L277,166@7108L154,172@7425L7,165@7052L482,176@7548L385,148@6431L1730:LazyLayoutPager.kt#g6yjnt");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(state) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(contentPadding) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changed(reverseLayout) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changed(orientation) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changed(flingBehavior) ? 131072 : 65536;
        }
        if ((i & 64) == 0) {
            if ((1572864 & $changed) == 0) {
                i2 = $composer3.changed(userScrollEnabled) ? 1048576 : 524288;
            }
            i3 = i & 128;
            if (i3 != 0) {
                $dirty2 |= 12582912;
                beyondViewportPageCount2 = beyondViewportPageCount;
            } else if (($changed & 12582912) == 0) {
                beyondViewportPageCount2 = beyondViewportPageCount;
                if ($composer3.changed(beyondViewportPageCount2)) {
                    i4 = 8388608;
                } else {
                    i4 = 4194304;
                }
                $dirty2 |= i4;
            } else {
                beyondViewportPageCount2 = beyondViewportPageCount;
            }
            i5 = i & 256;
            if (i5 != 0) {
                $dirty2 |= 100663296;
            } else if (($changed & 100663296) != 0) {
                if ($composer3.changed(pageSpacing)) {
                    i6 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i6 = 33554432;
                }
                $dirty2 |= i6;
            }
            if ((i & 512) != 0) {
                $dirty2 |= 805306368;
            } else if ((805306368 & $changed) != 0) {
                if ($composer3.changed(pageSize)) {
                    i7 = KeyHandler.KEYMOD_SHIFT;
                } else {
                    i7 = KeyHandler.KEYMOD_NUM_LOCK;
                }
                $dirty2 |= i7;
            }
            if ((i & 1024) != 0) {
                $dirty1 |= 6;
            } else if (($changed1 & 6) == 0) {
                if ($composer3.changedInstance(pageNestedScrollConnection)) {
                    i8 = 4;
                } else {
                    i8 = 2;
                }
                $dirty1 |= i8;
            }
            if ((i & 2048) != 0) {
                $dirty1 |= 48;
            } else if (($changed1 & 48) != 0) {
                if ($composer3.changedInstance(function1)) {
                    i9 = 32;
                } else {
                    i9 = 16;
                }
                $dirty1 |= i9;
            }
            if ((i & 4096) != 0) {
                $dirty1 |= 384;
            } else if (($changed1 & 384) != 0) {
                $dirty1 |= $composer3.changed(horizontalAlignment) ? 256 : 128;
            }
            if ((i & 8192) != 0) {
                $dirty1 |= 3072;
            } else if (($changed1 & 3072) != 0) {
                $dirty1 |= $composer3.changed(verticalAlignment) ? 2048 : 1024;
            }
            if ((i & 16384) != 0) {
                $dirty1 |= 24576;
            } else if (($changed1 & 24576) != 0) {
                if ($composer3.changed(snapPosition)) {
                    i10 = 16384;
                } else {
                    i10 = 8192;
                }
                $dirty1 |= i10;
            }
            if ((i & 32768) != 0) {
                $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                if ($composer3.changedInstance(function4)) {
                    i11 = 131072;
                } else {
                    i11 = 65536;
                }
                $dirty1 |= i11;
            }
            if (($dirty2 & 306783379) == 306783378 || (74899 & $dirty1) != 74898 || !$composer3.getSkipping()) {
                if (i3 != 0) {
                    beyondViewportPageCount2 = 0;
                }
                if (i5 == 0) {
                    pageSpacing2 = pageSpacing;
                } else {
                    pageSpacing2 = Dp.m7016constructorimpl(0);
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(538371694, $dirty2, $dirty1, "androidx.compose.foundation.pager.Pager (LazyLayoutPager.kt:101)");
                }
                if (beyondViewportPageCount2 >= 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    throw new IllegalArgumentException(("beyondViewportPageCount should be greater than or equal to 0, you selected " + beyondViewportPageCount2).toString());
                }
                ComposerKt.sourceInformationMarkerStart($composer3, 1863502769, "CC(remember):LazyLayoutPager.kt#9igjgp");
                if (($dirty2 & 112) == 32) {
                    invalid$iv = true;
                } else {
                    invalid$iv = false;
                }
                Object it$iv = $composer3.rememberedValue();
                if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = (Function0) new Function0<Integer>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$pagerItemProvider$1$1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Integer invoke() {
                            return Integer.valueOf(state.getPageCount());
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $dirty = $dirty2;
                int $dirty3 = $dirty1;
                int beyondViewportPageCount4 = beyondViewportPageCount2;
                Function0<PagerLazyLayoutItemProvider> function0RememberPagerItemProviderLambda = rememberPagerItemProviderLambda(state, function4, function1, (Function0) value$iv, $composer3, (($dirty2 >> 3) & 14) | (($dirty1 >> 12) & 112) | (($dirty1 << 3) & 896));
                ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
                value$iv$iv = $composer3.rememberedValue();
                if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                    $composer3.updateRememberedValue(value$iv$iv);
                }
                CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                CoroutineScope coroutineScope = wrapper$iv.getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 1863522897, "CC(remember):LazyLayoutPager.kt#9igjgp");
                if (($dirty & 112) == 32) {
                    invalid$iv2 = true;
                } else {
                    invalid$iv2 = false;
                }
                Object it$iv2 = $composer3.rememberedValue();
                if (!invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = (Function0) new Function0<Integer>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$measurePolicy$1$1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Integer invoke() {
                            return Integer.valueOf(state.getPageCount());
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2M1277rememberPagerMeasurePolicy8u0NR3k = PagerMeasurePolicyKt.m1277rememberPagerMeasurePolicy8u0NR3k(function0RememberPagerItemProviderLambda, state, contentPadding, reverseLayout, orientation, beyondViewportPageCount4, pageSpacing2, pageSize, horizontalAlignment, verticalAlignment, snapPosition, coroutineScope, (Function0) value$iv2, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 6) & 458752) | (($dirty >> 6) & 3670016) | (($dirty >> 6) & 29360128) | (($dirty3 << 18) & 234881024) | (($dirty3 << 18) & 1879048192), ($dirty3 >> 12) & 14);
                if (orientation == Orientation.Vertical) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                LazyLayoutSemanticState semanticState = PagerSemanticsKt.rememberPagerSemanticState(state, z2, $composer3, ($dirty >> 3) & 14);
                ComposerKt.sourceInformationMarkerStart($composer3, 1863528636, "CC(remember):LazyLayoutPager.kt#9igjgp");
                if (($dirty & 112) == 32) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (($dirty & 458752) == 131072) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                invalid$iv3 = z3 | z4;
                Object it$iv3 = $composer3.rememberedValue();
                if (!invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new PagerWrapperFlingBehavior(flingBehavior, state);
                    $composer3.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv3;
                }
                PagerWrapperFlingBehavior resolvedFlingBehavior = (PagerWrapperFlingBehavior) value$iv3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ProvidableCompositionLocal<BringIntoViewSpec> localBringIntoViewSpec = BringIntoViewSpec_androidKt.getLocalBringIntoViewSpec();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localBringIntoViewSpec);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                BringIntoViewSpec defaultBringIntoViewSpec = (BringIntoViewSpec) objConsume;
                ComposerKt.sourceInformationMarkerStart($composer3, 1863534929, "CC(remember):LazyLayoutPager.kt#9igjgp");
                if (($dirty & 112) == 32) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                invalid$iv4 = z5 | $composer3.changed(defaultBringIntoViewSpec);
                Object it$iv4 = $composer3.rememberedValue();
                if (!invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                    value$iv4 = new PagerBringIntoViewSpec(state, defaultBringIntoViewSpec);
                    $composer3.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv4;
                }
                PagerBringIntoViewSpec pagerBringIntoViewSpec = (PagerBringIntoViewSpec) value$iv4;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierLazyLayoutSemantics = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberPagerItemProviderLambda, semanticState, orientation, userScrollEnabled, reverseLayout, $composer3, (($dirty >> 3) & 7168) | (($dirty >> 6) & 57344) | (($dirty << 6) & 458752));
                if (orientation == Orientation.Vertical) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                Modifier modifierPagerSemantics = PagerKt.pagerSemantics(modifierLazyLayoutSemantics, state, z6, coroutineScope, userScrollEnabled);
                LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberPagerBeyondBoundsState = PagerBeyondBoundsModifierKt.rememberPagerBeyondBoundsState(state, beyondViewportPageCount4, $composer3, (($dirty >> 3) & 14) | (($dirty >> 18) & 112));
                LazyLayoutBeyondBoundsInfo beyondBoundsInfo = state.getBeyondBoundsInfo();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer3.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2 = $composer3;
                float pageSpacing4 = pageSpacing2;
                LazyLayoutKt.LazyLayout(function0RememberPagerItemProviderLambda, NestedScrollModifierKt.nestedScroll$default(dragDirectionDetector(ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierPagerSemantics, lazyLayoutBeyondBoundsStateRememberPagerBeyondBoundsState, beyondBoundsInfo, reverseLayout, (LayoutDirection) objConsume2, orientation, userScrollEnabled, $composer3, (MutableVector.$stable << 6) | ($dirty & 7168) | (($dirty << 3) & 458752) | (3670016 & $dirty)), state, orientation, userScrollEnabled, reverseLayout, resolvedFlingBehavior, state.getInternalInteractionSource(), pagerBringIntoViewSpec, $composer2, ($dirty & 112) | (($dirty >> 6) & 896) | (($dirty >> 9) & 7168) | (57344 & ($dirty << 3)), 0), state), pageNestedScrollConnection, null, 2, null), state.getPrefetchState(), function2M1277rememberPagerMeasurePolicy8u0NR3k, $composer2, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                pageSpacing3 = pageSpacing4;
                beyondViewportPageCount3 = beyondViewportPageCount4;
            } else {
                $composer3.skipToGroupEnd();
                pageSpacing3 = pageSpacing;
                beyondViewportPageCount3 = beyondViewportPageCount2;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final int i12 = beyondViewportPageCount3;
                final float f = pageSpacing3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$2
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

                    public final void invoke(Composer composer, int i13) {
                        LazyLayoutPagerKt.m1267PageruYRUAWA(modifier, state, contentPadding, reverseLayout, orientation, flingBehavior, userScrollEnabled, i12, f, pageSize, pageNestedScrollConnection, function1, horizontalAlignment, verticalAlignment, snapPosition, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                    }
                });
            }
        }
        i2 = 1572864;
        $dirty2 |= i2;
        i3 = i & 128;
        if (i3 != 0) {
            $dirty2 |= 12582912;
            beyondViewportPageCount2 = beyondViewportPageCount;
        } else if (($changed & 12582912) == 0) {
            beyondViewportPageCount2 = beyondViewportPageCount;
            if ($composer3.changed(beyondViewportPageCount2)) {
                i4 = 8388608;
            } else {
                i4 = 4194304;
            }
            $dirty2 |= i4;
        } else {
            beyondViewportPageCount2 = beyondViewportPageCount;
        }
        i5 = i & 256;
        if (i5 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) != 0) {
            if ($composer3.changed(pageSpacing)) {
                i6 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i6 = 33554432;
            }
            $dirty2 |= i6;
        }
        if ((i & 512) != 0) {
            $dirty2 |= 805306368;
        } else if ((805306368 & $changed) != 0) {
            if ($composer3.changed(pageSize)) {
                i7 = KeyHandler.KEYMOD_SHIFT;
            } else {
                i7 = KeyHandler.KEYMOD_NUM_LOCK;
            }
            $dirty2 |= i7;
        }
        if ((i & 1024) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            if ($composer3.changedInstance(pageNestedScrollConnection)) {
                i8 = 4;
            } else {
                i8 = 2;
            }
            $dirty1 |= i8;
        }
        if ((i & 2048) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) != 0) {
            if ($composer3.changedInstance(function1)) {
                i9 = 32;
            } else {
                i9 = 16;
            }
            $dirty1 |= i9;
        }
        if ((i & 4096) != 0) {
            $dirty1 |= 384;
        } else if (($changed1 & 384) != 0) {
            $dirty1 |= $composer3.changed(horizontalAlignment) ? 256 : 128;
        }
        if ((i & 8192) != 0) {
            $dirty1 |= 3072;
        } else if (($changed1 & 3072) != 0) {
            $dirty1 |= $composer3.changed(verticalAlignment) ? 2048 : 1024;
        }
        if ((i & 16384) != 0) {
            $dirty1 |= 24576;
        } else if (($changed1 & 24576) != 0) {
            if ($composer3.changed(snapPosition)) {
                i10 = 16384;
            } else {
                i10 = 8192;
            }
            $dirty1 |= i10;
        }
        if ((i & 32768) != 0) {
            $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
            if ($composer3.changedInstance(function4)) {
                i11 = 131072;
            } else {
                i11 = 65536;
            }
            $dirty1 |= i11;
        }
        if (($dirty2 & 306783379) == 306783378) {
            if (i3 != 0) {
                beyondViewportPageCount2 = 0;
            }
            if (i5 == 0) {
                pageSpacing2 = pageSpacing;
            } else {
                pageSpacing2 = Dp.m7016constructorimpl(0);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(538371694, $dirty2, $dirty1, "androidx.compose.foundation.pager.Pager (LazyLayoutPager.kt:101)");
            }
            if (beyondViewportPageCount2 >= 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                throw new IllegalArgumentException(("beyondViewportPageCount should be greater than or equal to 0, you selected " + beyondViewportPageCount2).toString());
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1863502769, "CC(remember):LazyLayoutPager.kt#9igjgp");
            if (($dirty2 & 112) == 32) {
                invalid$iv = true;
            } else {
                invalid$iv = false;
            }
            Object it$iv5 = $composer3.rememberedValue();
            if (!invalid$iv) {
            }
            value$iv = (Function0) new Function0<Integer>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$pagerItemProvider$1$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(state.getPageCount());
                }
            };
            $composer3.updateRememberedValue(value$iv);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $dirty = $dirty2;
            int $dirty4 = $dirty1;
            int beyondViewportPageCount5 = beyondViewportPageCount2;
            Function0<PagerLazyLayoutItemProvider> function0RememberPagerItemProviderLambda2 = rememberPagerItemProviderLambda(state, function4, function1, (Function0) value$iv, $composer3, (($dirty2 >> 3) & 14) | (($dirty1 >> 12) & 112) | (($dirty1 << 3) & 896));
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
            value$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv);
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv2 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            CoroutineScope coroutineScope2 = wrapper$iv2.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1863522897, "CC(remember):LazyLayoutPager.kt#9igjgp");
            if (($dirty & 112) == 32) {
                invalid$iv2 = true;
            } else {
                invalid$iv2 = false;
            }
            Object it$iv6 = $composer3.rememberedValue();
            if (!invalid$iv2) {
            }
            value$iv2 = (Function0) new Function0<Integer>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$measurePolicy$1$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(state.getPageCount());
                }
            };
            $composer3.updateRememberedValue(value$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2M1277rememberPagerMeasurePolicy8u0NR3k2 = PagerMeasurePolicyKt.m1277rememberPagerMeasurePolicy8u0NR3k(function0RememberPagerItemProviderLambda2, state, contentPadding, reverseLayout, orientation, beyondViewportPageCount5, pageSpacing2, pageSize, horizontalAlignment, verticalAlignment, snapPosition, coroutineScope2, (Function0) value$iv2, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 6) & 458752) | (($dirty >> 6) & 3670016) | (($dirty >> 6) & 29360128) | (($dirty4 << 18) & 234881024) | (($dirty4 << 18) & 1879048192), ($dirty4 >> 12) & 14);
            if (orientation == Orientation.Vertical) {
                z2 = true;
            } else {
                z2 = false;
            }
            LazyLayoutSemanticState semanticState2 = PagerSemanticsKt.rememberPagerSemanticState(state, z2, $composer3, ($dirty >> 3) & 14);
            ComposerKt.sourceInformationMarkerStart($composer3, 1863528636, "CC(remember):LazyLayoutPager.kt#9igjgp");
            if (($dirty & 112) == 32) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (($dirty & 458752) == 131072) {
                z4 = true;
            } else {
                z4 = false;
            }
            invalid$iv3 = z3 | z4;
            Object it$iv7 = $composer3.rememberedValue();
            if (!invalid$iv3) {
            }
            value$iv3 = new PagerWrapperFlingBehavior(flingBehavior, state);
            $composer3.updateRememberedValue(value$iv3);
            PagerWrapperFlingBehavior resolvedFlingBehavior2 = (PagerWrapperFlingBehavior) value$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<BringIntoViewSpec> localBringIntoViewSpec2 = BringIntoViewSpec_androidKt.getLocalBringIntoViewSpec();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localBringIntoViewSpec2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BringIntoViewSpec defaultBringIntoViewSpec2 = (BringIntoViewSpec) objConsume3;
            ComposerKt.sourceInformationMarkerStart($composer3, 1863534929, "CC(remember):LazyLayoutPager.kt#9igjgp");
            if (($dirty & 112) == 32) {
                z5 = true;
            } else {
                z5 = false;
            }
            invalid$iv4 = z5 | $composer3.changed(defaultBringIntoViewSpec2);
            Object it$iv8 = $composer3.rememberedValue();
            if (!invalid$iv4) {
            }
            value$iv4 = new PagerBringIntoViewSpec(state, defaultBringIntoViewSpec2);
            $composer3.updateRememberedValue(value$iv4);
            PagerBringIntoViewSpec pagerBringIntoViewSpec2 = (PagerBringIntoViewSpec) value$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierLazyLayoutSemantics2 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberPagerItemProviderLambda2, semanticState2, orientation, userScrollEnabled, reverseLayout, $composer3, (($dirty >> 3) & 7168) | (($dirty >> 6) & 57344) | (($dirty << 6) & 458752));
            if (orientation == Orientation.Vertical) {
                z6 = true;
            } else {
                z6 = false;
            }
            Modifier modifierPagerSemantics2 = PagerKt.pagerSemantics(modifierLazyLayoutSemantics2, state, z6, coroutineScope2, userScrollEnabled);
            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberPagerBeyondBoundsState2 = PagerBeyondBoundsModifierKt.rememberPagerBeyondBoundsState(state, beyondViewportPageCount5, $composer3, (($dirty >> 3) & 14) | (($dirty >> 18) & 112));
            LazyLayoutBeyondBoundsInfo beyondBoundsInfo2 = state.getBeyondBoundsInfo();
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer3.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            float pageSpacing5 = pageSpacing2;
            LazyLayoutKt.LazyLayout(function0RememberPagerItemProviderLambda2, NestedScrollModifierKt.nestedScroll$default(dragDirectionDetector(ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierPagerSemantics2, lazyLayoutBeyondBoundsStateRememberPagerBeyondBoundsState2, beyondBoundsInfo2, reverseLayout, (LayoutDirection) objConsume4, orientation, userScrollEnabled, $composer3, (MutableVector.$stable << 6) | ($dirty & 7168) | (($dirty << 3) & 458752) | (3670016 & $dirty)), state, orientation, userScrollEnabled, reverseLayout, resolvedFlingBehavior2, state.getInternalInteractionSource(), pagerBringIntoViewSpec2, $composer2, ($dirty & 112) | (($dirty >> 6) & 896) | (($dirty >> 9) & 7168) | (57344 & ($dirty << 3)), 0), state), pageNestedScrollConnection, null, 2, null), state.getPrefetchState(), function2M1277rememberPagerMeasurePolicy8u0NR3k2, $composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            pageSpacing3 = pageSpacing5;
            beyondViewportPageCount3 = beyondViewportPageCount5;
        } else {
            if (i3 != 0) {
                beyondViewportPageCount2 = 0;
            }
            if (i5 == 0) {
                pageSpacing2 = pageSpacing;
            } else {
                pageSpacing2 = Dp.m7016constructorimpl(0);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(538371694, $dirty2, $dirty1, "androidx.compose.foundation.pager.Pager (LazyLayoutPager.kt:101)");
            }
            if (beyondViewportPageCount2 >= 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                throw new IllegalArgumentException(("beyondViewportPageCount should be greater than or equal to 0, you selected " + beyondViewportPageCount2).toString());
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1863502769, "CC(remember):LazyLayoutPager.kt#9igjgp");
            if (($dirty2 & 112) == 32) {
                invalid$iv = true;
            } else {
                invalid$iv = false;
            }
            Object it$iv9 = $composer3.rememberedValue();
            if (!invalid$iv) {
            }
            value$iv = (Function0) new Function0<Integer>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$pagerItemProvider$1$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(state.getPageCount());
                }
            };
            $composer3.updateRememberedValue(value$iv);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $dirty = $dirty2;
            int $dirty5 = $dirty1;
            int beyondViewportPageCount6 = beyondViewportPageCount2;
            Function0<PagerLazyLayoutItemProvider> function0RememberPagerItemProviderLambda3 = rememberPagerItemProviderLambda(state, function4, function1, (Function0) value$iv, $composer3, (($dirty2 >> 3) & 14) | (($dirty1 >> 12) & 112) | (($dirty1 << 3) & 896));
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
            value$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv);
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv3 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            CoroutineScope coroutineScope3 = wrapper$iv3.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1863522897, "CC(remember):LazyLayoutPager.kt#9igjgp");
            if (($dirty & 112) == 32) {
                invalid$iv2 = true;
            } else {
                invalid$iv2 = false;
            }
            Object it$iv10 = $composer3.rememberedValue();
            if (!invalid$iv2) {
            }
            value$iv2 = (Function0) new Function0<Integer>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$measurePolicy$1$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(state.getPageCount());
                }
            };
            $composer3.updateRememberedValue(value$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2M1277rememberPagerMeasurePolicy8u0NR3k3 = PagerMeasurePolicyKt.m1277rememberPagerMeasurePolicy8u0NR3k(function0RememberPagerItemProviderLambda3, state, contentPadding, reverseLayout, orientation, beyondViewportPageCount6, pageSpacing2, pageSize, horizontalAlignment, verticalAlignment, snapPosition, coroutineScope3, (Function0) value$iv2, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 6) & 458752) | (($dirty >> 6) & 3670016) | (($dirty >> 6) & 29360128) | (($dirty5 << 18) & 234881024) | (($dirty5 << 18) & 1879048192), ($dirty5 >> 12) & 14);
            if (orientation == Orientation.Vertical) {
                z2 = true;
            } else {
                z2 = false;
            }
            LazyLayoutSemanticState semanticState3 = PagerSemanticsKt.rememberPagerSemanticState(state, z2, $composer3, ($dirty >> 3) & 14);
            ComposerKt.sourceInformationMarkerStart($composer3, 1863528636, "CC(remember):LazyLayoutPager.kt#9igjgp");
            if (($dirty & 112) == 32) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (($dirty & 458752) == 131072) {
                z4 = true;
            } else {
                z4 = false;
            }
            invalid$iv3 = z3 | z4;
            Object it$iv11 = $composer3.rememberedValue();
            if (!invalid$iv3) {
            }
            value$iv3 = new PagerWrapperFlingBehavior(flingBehavior, state);
            $composer3.updateRememberedValue(value$iv3);
            PagerWrapperFlingBehavior resolvedFlingBehavior3 = (PagerWrapperFlingBehavior) value$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<BringIntoViewSpec> localBringIntoViewSpec3 = BringIntoViewSpec_androidKt.getLocalBringIntoViewSpec();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume5 = $composer3.consume(localBringIntoViewSpec3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BringIntoViewSpec defaultBringIntoViewSpec3 = (BringIntoViewSpec) objConsume5;
            ComposerKt.sourceInformationMarkerStart($composer3, 1863534929, "CC(remember):LazyLayoutPager.kt#9igjgp");
            if (($dirty & 112) == 32) {
                z5 = true;
            } else {
                z5 = false;
            }
            invalid$iv4 = z5 | $composer3.changed(defaultBringIntoViewSpec3);
            Object it$iv12 = $composer3.rememberedValue();
            if (!invalid$iv4) {
            }
            value$iv4 = new PagerBringIntoViewSpec(state, defaultBringIntoViewSpec3);
            $composer3.updateRememberedValue(value$iv4);
            PagerBringIntoViewSpec pagerBringIntoViewSpec3 = (PagerBringIntoViewSpec) value$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierLazyLayoutSemantics3 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberPagerItemProviderLambda3, semanticState3, orientation, userScrollEnabled, reverseLayout, $composer3, (($dirty >> 3) & 7168) | (($dirty >> 6) & 57344) | (($dirty << 6) & 458752));
            if (orientation == Orientation.Vertical) {
                z6 = true;
            } else {
                z6 = false;
            }
            Modifier modifierPagerSemantics3 = PagerKt.pagerSemantics(modifierLazyLayoutSemantics3, state, z6, coroutineScope3, userScrollEnabled);
            LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberPagerBeyondBoundsState3 = PagerBeyondBoundsModifierKt.rememberPagerBeyondBoundsState(state, beyondViewportPageCount6, $composer3, (($dirty >> 3) & 14) | (($dirty >> 18) & 112));
            LazyLayoutBeyondBoundsInfo beyondBoundsInfo3 = state.getBeyondBoundsInfo();
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer3.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            float pageSpacing6 = pageSpacing2;
            LazyLayoutKt.LazyLayout(function0RememberPagerItemProviderLambda3, NestedScrollModifierKt.nestedScroll$default(dragDirectionDetector(ScrollingContainerKt.scrollingContainer(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierPagerSemantics3, lazyLayoutBeyondBoundsStateRememberPagerBeyondBoundsState3, beyondBoundsInfo3, reverseLayout, (LayoutDirection) objConsume6, orientation, userScrollEnabled, $composer3, (MutableVector.$stable << 6) | ($dirty & 7168) | (($dirty << 3) & 458752) | (3670016 & $dirty)), state, orientation, userScrollEnabled, reverseLayout, resolvedFlingBehavior3, state.getInternalInteractionSource(), pagerBringIntoViewSpec3, $composer2, ($dirty & 112) | (($dirty >> 6) & 896) | (($dirty >> 9) & 7168) | (57344 & ($dirty << 3)), 0), state), pageNestedScrollConnection, null, 2, null), state.getPrefetchState(), function2M1277rememberPagerMeasurePolicy8u0NR3k3, $composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            pageSpacing3 = pageSpacing6;
            beyondViewportPageCount3 = beyondViewportPageCount6;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final int i13 = beyondViewportPageCount3;
            final float f2 = pageSpacing3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$2
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
                    LazyLayoutPagerKt.m1267PageruYRUAWA(modifier, state, contentPadding, reverseLayout, orientation, flingBehavior, userScrollEnabled, i13, f2, pageSize, pageNestedScrollConnection, function1, horizontalAlignment, verticalAlignment, snapPosition, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    private static final Function0<PagerLazyLayoutItemProvider> rememberPagerItemProviderLambda(final PagerState state, Function4<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Function1<? super Integer, ? extends Object> function1, final Function0<Integer> function0, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1372505274, "C(rememberPagerItemProviderLambda)P(3,1)259@10522L33,260@10576L25,261@10613L677:LazyLayoutPager.kt#g6yjnt");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1372505274, $changed, -1, "androidx.compose.foundation.pager.rememberPagerItemProviderLambda (LazyLayoutPager.kt:258)");
        }
        final State latestContent = SnapshotStateKt.rememberUpdatedState(function4, $composer, ($changed >> 3) & 14);
        final State latestKey = SnapshotStateKt.rememberUpdatedState(function1, $composer, ($changed >> 6) & 14);
        ComposerKt.sourceInformationMarkerStart($composer, 1578864961, "CC(remember):LazyLayoutPager.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(state)) || ($changed & 6) == 4) | $composer.changed(latestContent) | $composer.changed(latestKey) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(function0)) || ($changed & 3072) == 2048);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            final State intervalContentState = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0<PagerLayoutIntervalContent>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$intervalContentState$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final PagerLayoutIntervalContent invoke() {
                    return new PagerLayoutIntervalContent(latestContent.getValue(), latestKey.getValue(), function0.invoke().intValue());
                }
            });
            final State itemProviderState = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0<PagerLazyLayoutItemProvider>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$itemProviderState$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final PagerLazyLayoutItemProvider invoke() {
                    PagerLayoutIntervalContent intervalContent = intervalContentState.getValue();
                    NearestRangeKeyIndexMap map = new NearestRangeKeyIndexMap(state.getNearestRange$foundation_release(), intervalContent);
                    return new PagerLazyLayoutItemProvider(state, intervalContent, map);
                }
            });
            value$iv = new PropertyReference0Impl(itemProviderState) { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$1
                @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return ((State) this.receiver).getValue();
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        KProperty0 kProperty0 = (KProperty0) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return kProperty0;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1, reason: invalid class name */
    /* JADX INFO: compiled from: LazyLayoutPager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1", f = "LazyLayoutPager.kt", i = {}, l = {284}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PagerState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PagerState pagerState, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$state = pagerState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$state, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: LazyLayoutPager.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1", f = "LazyLayoutPager.kt", i = {}, l = {285}, m = "invokeSuspend", n = {}, s = {})
        static final class C00401 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ PagerState $state;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00401(PointerInputScope pointerInputScope, PagerState pagerState, Continuation<? super C00401> continuation) {
                super(2, continuation);
                this.$$this$pointerInput = pointerInputScope;
                this.$state = pagerState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00401(this.$$this$pointerInput, this.$state, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00401) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: LazyLayoutPager.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1$1", f = "LazyLayoutPager.kt", i = {0, 1, 1, 1}, l = {287, 291}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "downEvent", "upEventOrCancellation"}, s = {"L$0", "L$0", "L$1", "L$2"})
            static final class C00411 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PagerState $state;
                private /* synthetic */ Object L$0;
                Object L$1;
                Object L$2;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00411(PagerState pagerState, Continuation<? super C00411> continuation) {
                    super(2, continuation);
                    this.$state = pagerState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00411 c00411 = new C00411(this.$state, continuation);
                    c00411.L$0 = obj;
                    return c00411;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((C00411) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:20:0x00a5  */
                /* JADX WARN: Code duplicated, block: B:23:0x00bb A[LOOP:0: B:19:0x00a3->B:23:0x00bb, LOOP_END] */
                /* JADX WARN: Code duplicated, block: B:31:0x00b9 A[SYNTHETIC] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x008b -> B:18:0x0093). Please report as a decompilation issue!!! */
                /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                    jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
                    	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                    	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                    	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                    */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final java.lang.Object invokeSuspend(java.lang.Object r22) {
                    /*
                        Method dump skipped, instruction units count: 250
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.LazyLayoutPagerKt.AnonymousClass1.C00401.C00411.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (ForEachGestureKt.awaitEachGesture(this.$$this$pointerInput, new C00411(this.$state, null), this) == coroutine_suspended) {
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                    this.label = 1;
                    if (CoroutineScopeKt.coroutineScope(new C00401($this$pointerInput, this.$state, null), this) == coroutine_suspended) {
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

    private static final Modifier dragDirectionDetector(Modifier $this$dragDirectionDetector, PagerState state) {
        return $this$dragDirectionDetector.then(SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, state, new AnonymousClass1(state, null)));
    }
}
