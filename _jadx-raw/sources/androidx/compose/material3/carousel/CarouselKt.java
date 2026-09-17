package androidx.compose.material3.carousel;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.pager.PagerKt;
import androidx.compose.foundation.pager.PagerScope;
import androidx.compose.foundation.pager.PagerState;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Carousel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a»\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000526\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\f\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u001621\u0010\u0017\u001a-\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0091\u0001\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00142\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\f\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010 \u001a\u00020\u00142\b\b\u0002\u0010!\u001a\u00020\u00142\b\b\u0002\u0010\u000e\u001a\u00020\u000f21\u0010\u0017\u001a-\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a}\u0010$\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00142\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\f\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u000e\u001a\u00020\u000f21\u0010\u0017\u001a-\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a\u0018\u0010(\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*H\u0000\u001a\u0018\u0010+\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*H\u0001\u001a \u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020\bH\u0002\u001a\u0019\u00101\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u00102\u001a\u0019\u00103\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u00102\u001a:\u00104\u001a\u00020\u0013*\u00020\u00132\u0006\u00105\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006;"}, d2 = {"Carousel", "", "state", "Landroidx/compose/material3/carousel/CarouselState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "keylineList", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "availableSpace", "itemSpacing", "Landroidx/compose/material3/carousel/KeylineList;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "maxNonFocalVisibleItemCount", "", "modifier", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/unit/Dp;", "flingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "content", "Landroidx/compose/material3/carousel/CarouselItemScope;", "itemIndex", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "Carousel-V-95POc", "(Landroidx/compose/material3/carousel/CarouselState;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;ILandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "HorizontalMultiBrowseCarousel", "preferredItemWidth", "minSmallItemWidth", "maxSmallItemWidth", "HorizontalMultiBrowseCarousel-zCIJ0Nk", "(Landroidx/compose/material3/carousel/CarouselState;FLandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;FFLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "HorizontalUncontainedCarousel", "itemWidth", "HorizontalUncontainedCarousel-9QcgTRs", "(Landroidx/compose/material3/carousel/CarouselState;FLandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "calculateCurrentScrollOffset", "strategy", "Landroidx/compose/material3/carousel/Strategy;", "calculateMaxScrollOffset", "getProgress", "before", "Landroidx/compose/material3/carousel/Keyline;", "after", "unadjustedOffset", "calculateAfterContentPadding", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/runtime/Composer;I)F", "calculateBeforeContentPadding", "carouselItem", "index", "Lkotlin/Function0;", "carouselItemInfo", "Landroidx/compose/material3/carousel/CarouselItemInfoImpl;", "clipShape", "Landroidx/compose/ui/graphics/Shape;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CarouselKt {
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: HorizontalMultiBrowseCarousel-zCIJ0Nk, reason: not valid java name */
    public static final void m3258HorizontalMultiBrowseCarouselzCIJ0Nk(final CarouselState state, final float preferredItemWidth, Modifier modifier, float itemSpacing, TargetedFlingBehavior flingBehavior, float minSmallItemWidth, float maxSmallItemWidth, PaddingValues contentPadding, final Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        float f;
        TargetedFlingBehavior flingBehavior2;
        float f2;
        float maxSmallItemWidth2;
        PaddingValues paddingValues;
        Modifier modifier2;
        float itemSpacing2;
        PaddingValues contentPadding2;
        TargetedFlingBehavior flingBehavior3;
        float maxSmallItemWidth3;
        int $dirty;
        float minSmallItemWidth2;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(-1825706865);
        ComposerKt.sourceInformation($composer2, "C(HorizontalMultiBrowseCarousel)P(8,7:c#ui.unit.Dp,6,3:c#ui.unit.Dp,2,5:c#ui.unit.Dp,4:c#ui.unit.Dp,1)106@5374L41,112@5703L7,116@5816L554,113@5715L1048:Carousel.kt#dcf9yb");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changed(preferredItemWidth) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            f = itemSpacing;
        } else if (($changed & 3072) == 0) {
            f = itemSpacing;
            $dirty2 |= $composer2.changed(f) ? 2048 : 1024;
        } else {
            f = itemSpacing;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                flingBehavior2 = flingBehavior;
                int i4 = $composer2.changed(flingBehavior2) ? 16384 : 8192;
                $dirty2 |= i4;
            } else {
                flingBehavior2 = flingBehavior;
            }
            $dirty2 |= i4;
        } else {
            flingBehavior2 = flingBehavior;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f2 = minSmallItemWidth;
        } else if ((196608 & $changed) == 0) {
            f2 = minSmallItemWidth;
            $dirty2 |= $composer2.changed(f2) ? 131072 : 65536;
        } else {
            f2 = minSmallItemWidth;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            maxSmallItemWidth2 = maxSmallItemWidth;
        } else if (($changed & 1572864) == 0) {
            maxSmallItemWidth2 = maxSmallItemWidth;
            $dirty2 |= $composer2.changed(maxSmallItemWidth2) ? 1048576 : 524288;
        } else {
            maxSmallItemWidth2 = maxSmallItemWidth;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty2 |= 12582912;
            paddingValues = contentPadding;
        } else if (($changed & 12582912) == 0) {
            paddingValues = contentPadding;
            $dirty2 |= $composer2.changed(paddingValues) ? 8388608 : 4194304;
        } else {
            paddingValues = contentPadding;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer2.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty3 = $dirty2;
        if (($dirty3 & 38347923) == 38347922 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            itemSpacing2 = f;
            contentPadding2 = paddingValues;
            flingBehavior3 = flingBehavior2;
            maxSmallItemWidth3 = maxSmallItemWidth2;
            minSmallItemWidth2 = f2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                itemSpacing2 = i3 != 0 ? Dp.m7016constructorimpl(0) : f;
                if ((i & 16) != 0) {
                    TargetedFlingBehavior flingBehavior4 = CarouselDefaults.INSTANCE.singleAdvanceFlingBehavior(state, null, $composer2, ($dirty3 & 14) | 384, 2);
                    $dirty3 &= -57345;
                    flingBehavior2 = flingBehavior4;
                }
                float minSmallItemWidth3 = i5 != 0 ? CarouselDefaults.INSTANCE.m3255getMinSmallItemSizeD9Ej5fM$material3_release() : minSmallItemWidth;
                if (i6 != 0) {
                    maxSmallItemWidth2 = CarouselDefaults.INSTANCE.m3254getMaxSmallItemSizeD9Ej5fM$material3_release();
                }
                if (i7 != 0) {
                    contentPadding2 = PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0));
                    flingBehavior3 = flingBehavior2;
                    maxSmallItemWidth3 = maxSmallItemWidth2;
                    $dirty = $dirty3;
                    minSmallItemWidth2 = minSmallItemWidth3;
                } else {
                    contentPadding2 = paddingValues;
                    flingBehavior3 = flingBehavior2;
                    maxSmallItemWidth3 = maxSmallItemWidth2;
                    $dirty = $dirty3;
                    minSmallItemWidth2 = minSmallItemWidth3;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                modifier2 = modifier;
                itemSpacing2 = f;
                contentPadding2 = paddingValues;
                flingBehavior3 = flingBehavior2;
                maxSmallItemWidth3 = maxSmallItemWidth2;
                $dirty = $dirty3;
                minSmallItemWidth2 = f2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1825706865, $dirty, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:111)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Density density = (Density) objConsume;
            Orientation orientation = Orientation.Horizontal;
            ComposerKt.sourceInformationMarkerStart($composer2, 1995157598, "CC(remember):Carousel.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(density) | (($dirty & 112) == 32) | $composer2.changedInstance(state) | ((458752 & $dirty) == 131072) | ((3670016 & $dirty) == 1048576);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                final float f3 = minSmallItemWidth2;
                final float f4 = maxSmallItemWidth3;
                value$iv = new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f5, Float f6) {
                        return invoke(f5.floatValue(), f6.floatValue());
                    }

                    public final KeylineList invoke(float availableSpace, float itemSpacingPx) {
                        Density $this$invoke_u24lambda_u240 = density;
                        return KeylinesKt.multiBrowseKeylineList($this$invoke_u24lambda_u240, availableSpace, $this$invoke_u24lambda_u240.mo726toPx0680j_4(preferredItemWidth), itemSpacingPx, state.getItemCountState().getValue().invoke().intValue(), $this$invoke_u24lambda_u240.mo726toPx0680j_4(f3), $this$invoke_u24lambda_u240.mo726toPx0680j_4(f4));
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            m3257CarouselV95POc(state, orientation, (Function2) value$iv, contentPadding2, 2, modifier2, itemSpacing2, flingBehavior3, function4, $composer2, ($dirty & 14) | 24624 | (($dirty >> 12) & 7168) | (($dirty << 9) & 458752) | (($dirty << 9) & 3670016) | (29360128 & ($dirty << 9)) | (234881024 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            final float f5 = itemSpacing2;
            final TargetedFlingBehavior targetedFlingBehavior = flingBehavior3;
            final float f6 = minSmallItemWidth2;
            final float f7 = maxSmallItemWidth3;
            final PaddingValues paddingValues2 = contentPadding2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalMultiBrowseCarousel$2
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

                public final void invoke(Composer composer, int i8) {
                    CarouselKt.m3258HorizontalMultiBrowseCarouselzCIJ0Nk(state, preferredItemWidth, modifier3, f5, targetedFlingBehavior, f6, f7, paddingValues2, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: HorizontalUncontainedCarousel-9QcgTRs, reason: not valid java name */
    public static final void m3259HorizontalUncontainedCarousel9QcgTRs(final CarouselState state, final float itemWidth, Modifier modifier, float itemSpacing, TargetedFlingBehavior flingBehavior, PaddingValues contentPadding, final Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float itemSpacing2;
        TargetedFlingBehavior flingBehavior2;
        PaddingValues paddingValues;
        Modifier.Companion modifier3;
        PaddingValues contentPadding2;
        Object value$iv;
        Modifier modifier4;
        PaddingValues contentPadding3;
        float itemSpacing3;
        TargetedFlingBehavior flingBehavior3;
        Composer $composer2 = $composer.startRestartGroup(529322840);
        ComposerKt.sourceInformation($composer2, "C(HorizontalUncontainedCarousel)P(6,4:c#ui.unit.Dp,5,3:c#ui.unit.Dp,2,1)175@8483L21,179@8666L7,183@8779L337,180@8678L818:Carousel.kt#dcf9yb");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(itemWidth) ? 32 : 16;
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
            itemSpacing2 = itemSpacing;
        } else if (($changed & 3072) == 0) {
            itemSpacing2 = itemSpacing;
            $dirty |= $composer2.changed(itemSpacing2) ? 2048 : 1024;
        } else {
            itemSpacing2 = itemSpacing;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                flingBehavior2 = flingBehavior;
                int i4 = $composer2.changed(flingBehavior2) ? 16384 : 8192;
                $dirty |= i4;
            } else {
                flingBehavior2 = flingBehavior;
            }
            $dirty |= i4;
        } else {
            flingBehavior2 = flingBehavior;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            paddingValues = contentPadding;
        } else if ((196608 & $changed) == 0) {
            paddingValues = contentPadding;
            $dirty |= $composer2.changed(paddingValues) ? 131072 : 65536;
        } else {
            paddingValues = contentPadding;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? 1048576 : 524288;
        }
        if ((599187 & $dirty) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            flingBehavior3 = flingBehavior2;
            contentPadding3 = paddingValues;
            itemSpacing3 = itemSpacing2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if (i3 != 0) {
                    itemSpacing2 = Dp.m7016constructorimpl(0);
                }
                int $this$dp$iv = i & 16;
                if ($this$dp$iv != 0) {
                    $dirty &= -57345;
                    flingBehavior2 = CarouselDefaults.INSTANCE.noSnapFlingBehavior($composer2, 6);
                }
                contentPadding2 = i5 != 0 ? PaddingKt.m1027PaddingValues0680j_4(Dp.m7016constructorimpl(0)) : paddingValues;
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                modifier3 = modifier2;
                contentPadding2 = paddingValues;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(529322840, $dirty, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:178)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Density density = (Density) objConsume;
            Orientation orientation = Orientation.Horizontal;
            ComposerKt.sourceInformationMarkerStart($composer2, 1883505148, "CC(remember):Carousel.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(density) | (($dirty & 112) == 32);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function2) new Function2<Float, Float, KeylineList>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ KeylineList invoke(Float f, Float f2) {
                        return invoke(f.floatValue(), f2.floatValue());
                    }

                    public final KeylineList invoke(float availableSpace, float itemSpacingPx) {
                        Density $this$invoke_u24lambda_u240 = density;
                        return KeylinesKt.uncontainedKeylineList($this$invoke_u24lambda_u240, availableSpace, $this$invoke_u24lambda_u240.mo726toPx0680j_4(itemWidth), itemSpacingPx);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            m3257CarouselV95POc(state, orientation, (Function2) value$iv, contentPadding2, 0, modifier3, itemSpacing2, flingBehavior2, function4, $composer2, ($dirty & 14) | 24624 | (($dirty >> 6) & 7168) | (($dirty << 9) & 458752) | (($dirty << 9) & 3670016) | (29360128 & ($dirty << 9)) | (($dirty << 6) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            contentPadding3 = contentPadding2;
            itemSpacing3 = itemSpacing2;
            flingBehavior3 = flingBehavior2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier4;
            final float f = itemSpacing3;
            final TargetedFlingBehavior targetedFlingBehavior = flingBehavior3;
            final PaddingValues paddingValues2 = contentPadding3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$HorizontalUncontainedCarousel$2
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

                public final void invoke(Composer composer, int i6) {
                    CarouselKt.m3259HorizontalUncontainedCarousel9QcgTRs(state, itemWidth, modifier5, f, targetedFlingBehavior, paddingValues2, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: Carousel-V-95POc, reason: not valid java name */
    public static final void m3257CarouselV95POc(final CarouselState state, final Orientation orientation, final Function2<? super Float, ? super Float, KeylineList> function2, final PaddingValues contentPadding, final int maxNonFocalVisibleItemCount, Modifier modifier, float itemSpacing, TargetedFlingBehavior flingBehavior, final Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        float itemSpacing2;
        TargetedFlingBehavior flingBehavior2;
        Modifier modifier2;
        Object value$iv;
        Modifier modifier3;
        float itemSpacing3;
        TargetedFlingBehavior flingBehavior3;
        Composer $composer2 = $composer.startRestartGroup(-2035733443);
        ComposerKt.sourceInformation($composer2, "C(Carousel)P(8,7,4,1,5,6,3:c#ui.unit.Dp,2)239@11411L41,242@11572L42,243@11660L41,245@11729L118:Carousel.kt#dcf9yb");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(orientation) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(contentPadding) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(maxNonFocalVisibleItemCount) ? 16384 : 8192;
        }
        int i2 = i & 32;
        if (i2 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(modifier) ? 131072 : 65536;
        }
        int i3 = i & 64;
        if (i3 != 0) {
            $dirty |= 1572864;
            itemSpacing2 = itemSpacing;
        } else if ((1572864 & $changed) == 0) {
            itemSpacing2 = itemSpacing;
            $dirty |= $composer2.changed(itemSpacing2) ? 1048576 : 524288;
        } else {
            itemSpacing2 = itemSpacing;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                flingBehavior2 = flingBehavior;
                int i4 = $composer2.changed(flingBehavior2) ? 8388608 : 4194304;
                $dirty |= i4;
            } else {
                flingBehavior2 = flingBehavior;
            }
            $dirty |= i4;
        } else {
            flingBehavior2 = flingBehavior;
        }
        if ((i & 256) != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty2 = $dirty;
        if ((38347923 & $dirty2) == 38347922 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            itemSpacing3 = itemSpacing2;
            flingBehavior3 = flingBehavior2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                float itemSpacing4 = i3 != 0 ? Dp.m7016constructorimpl(0) : itemSpacing2;
                if ((i & 128) != 0) {
                    TargetedFlingBehavior flingBehavior4 = CarouselDefaults.INSTANCE.singleAdvanceFlingBehavior(state, null, $composer2, ($dirty2 & 14) | 384, 2);
                    $dirty2 &= -29360129;
                    flingBehavior2 = flingBehavior4;
                    modifier2 = modifier4;
                    itemSpacing2 = itemSpacing4;
                } else {
                    modifier2 = modifier4;
                    itemSpacing2 = itemSpacing4;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 128) != 0) {
                    $dirty2 &= -29360129;
                }
                modifier2 = modifier;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2035733443, $dirty2, -1, "androidx.compose.material3.carousel.Carousel (Carousel.kt:241)");
            }
            float beforeContentPadding = calculateBeforeContentPadding(contentPadding, orientation, $composer2, (($dirty2 >> 9) & 14) | ($dirty2 & 112));
            float afterContentPadding = calculateAfterContentPadding(contentPadding, orientation, $composer2, (($dirty2 >> 9) & 14) | ($dirty2 & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, 2025986753, "CC(remember):Carousel.kt#9igjgp");
            boolean invalid$iv = ($dirty2 & 896) == 256;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new CarouselPageSize(function2, beforeContentPadding, afterContentPadding);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final CarouselPageSize pageSize = (CarouselPageSize) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SnapPosition snapPosition = KeylineSnapPositionKt.KeylineSnapPosition(pageSize);
            if (orientation == Orientation.Horizontal) {
                $composer2.startReplaceGroup(-1618653092);
                ComposerKt.sourceInformation($composer2, "266@12589L1014,252@11960L1643");
                PagerKt.m1271HorizontalPageroI3XNZo(state.getPagerState(), modifier2, PaddingKt.m1031PaddingValuesa9UjIt4$default(0.0f, contentPadding.getTop(), 0.0f, contentPadding.getBottom(), 5, null), pageSize, maxNonFocalVisibleItemCount, itemSpacing2, null, flingBehavior2, false, false, null, null, snapPosition, ComposableLambdaKt.rememberComposableLambda(687111200, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer, Integer num2) {
                        invoke(pagerScope, num.intValue(), composer, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(PagerScope $this$HorizontalPager, int page, Composer $composer3, int $changed2) {
                        Object value$iv2;
                        Object value$iv3;
                        Object value$iv4;
                        Object value$iv5;
                        Function0<ComposeUiNode> function0;
                        ComposerKt.sourceInformation($composer3, "C267@12634L35,268@12694L63,269@12786L389,286@13376L21,281@13189L404:Carousel.kt#dcf9yb");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(687111200, $changed2, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:267)");
                        }
                        ComposerKt.sourceInformationMarkerStart($composer3, -685906675, "CC(remember):Carousel.kt#9igjgp");
                        Object it$iv2 = $composer3.rememberedValue();
                        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                            value$iv2 = new CarouselItemInfoImpl();
                            $composer3.updateRememberedValue(value$iv2);
                        } else {
                            value$iv2 = it$iv2;
                        }
                        final CarouselItemInfoImpl carouselItemInfo = (CarouselItemInfoImpl) value$iv2;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerStart($composer3, -685904727, "CC(remember):Carousel.kt#9igjgp");
                        Object it$iv3 = $composer3.rememberedValue();
                        if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv3 = new CarouselItemScopeImpl(carouselItemInfo);
                            $composer3.updateRememberedValue(value$iv3);
                        } else {
                            value$iv3 = it$iv3;
                        }
                        CarouselItemScopeImpl scope = (CarouselItemScopeImpl) value$iv3;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerStart($composer3, -685901457, "CC(remember):Carousel.kt#9igjgp");
                        Object it$iv4 = $composer3.rememberedValue();
                        if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                            value$iv4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$clipShape$1$1
                                @Override // androidx.compose.ui.graphics.Shape
                                /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                public Outline mo637createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                    return new Outline.Rectangle(carouselItemInfo.getMaskRect());
                                }
                            };
                            $composer3.updateRememberedValue(value$iv4);
                        } else {
                            value$iv4 = it$iv4;
                        }
                        CarouselKt$Carousel$1$clipShape$1$1 clipShape = (CarouselKt$Carousel$1$clipShape$1$1) value$iv4;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Modifier.Companion companion = Modifier.INSTANCE;
                        CarouselState carouselState = state;
                        ComposerKt.sourceInformationMarkerStart($composer3, -685882945, "CC(remember):Carousel.kt#9igjgp");
                        boolean invalid$iv2 = $composer3.changed(pageSize);
                        final CarouselPageSize carouselPageSize = pageSize;
                        Object it$iv5 = $composer3.rememberedValue();
                        if (invalid$iv2 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                            value$iv5 = new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$1$1$1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Strategy invoke() {
                                    return carouselPageSize.getStrategy();
                                }
                            };
                            $composer3.updateRememberedValue(value$iv5);
                        } else {
                            value$iv5 = it$iv5;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Modifier modifier$iv = CarouselKt.carouselItem(companion, page, carouselState, (Function0) value$iv5, carouselItemInfo, clipShape);
                        Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
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
                            function0 = constructor;
                            $composer3.createNode(function0);
                        } else {
                            function0 = constructor;
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
                        int i5 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i6 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1112607687, "C291@13566L13:Carousel.kt#dcf9yb");
                        function5.invoke(scope, Integer.valueOf(page), $composer3, Integer.valueOf($changed2 & 112));
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
                }, $composer2, 54), $composer2, (($dirty2 >> 12) & 112) | (57344 & $dirty2) | (($dirty2 >> 3) & 458752) | (29360128 & $dirty2), 3072, 3904);
                $composer2.endReplaceGroup();
            } else if (orientation == Orientation.Vertical) {
                $composer2.startReplaceGroup(-1616959128);
                ComposerKt.sourceInformation($composer2, "300@13962L7,301@14054L7,309@14347L1014,295@13666L1695");
                PagerState pagerState = state.getPagerState();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                float fCalculateStartPadding = PaddingKt.calculateStartPadding(contentPadding, (LayoutDirection) objConsume);
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                PagerKt.m1272VerticalPageroI3XNZo(pagerState, modifier2, PaddingKt.m1031PaddingValuesa9UjIt4$default(fCalculateStartPadding, 0.0f, PaddingKt.calculateEndPadding(contentPadding, (LayoutDirection) objConsume2), 0.0f, 10, null), pageSize, maxNonFocalVisibleItemCount, itemSpacing2, null, flingBehavior2, false, false, null, null, snapPosition, ComposableLambdaKt.rememberComposableLambda(-817308503, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer, Integer num2) {
                        invoke(pagerScope, num.intValue(), composer, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(PagerScope $this$VerticalPager, int page, Composer $composer3, int $changed2) {
                        Object value$iv2;
                        Object value$iv3;
                        Object value$iv4;
                        Object value$iv5;
                        Function0<ComposeUiNode> function0;
                        ComposerKt.sourceInformation($composer3, "C310@14392L35,311@14452L63,312@14544L389,329@15134L21,324@14947L404:Carousel.kt#dcf9yb");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-817308503, $changed2, -1, "androidx.compose.material3.carousel.Carousel.<anonymous> (Carousel.kt:310)");
                        }
                        ComposerKt.sourceInformationMarkerStart($composer3, -685850419, "CC(remember):Carousel.kt#9igjgp");
                        Object it$iv2 = $composer3.rememberedValue();
                        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                            value$iv2 = new CarouselItemInfoImpl();
                            $composer3.updateRememberedValue(value$iv2);
                        } else {
                            value$iv2 = it$iv2;
                        }
                        final CarouselItemInfoImpl carouselItemInfo = (CarouselItemInfoImpl) value$iv2;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerStart($composer3, -685848471, "CC(remember):Carousel.kt#9igjgp");
                        Object it$iv3 = $composer3.rememberedValue();
                        if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv3 = new CarouselItemScopeImpl(carouselItemInfo);
                            $composer3.updateRememberedValue(value$iv3);
                        } else {
                            value$iv3 = it$iv3;
                        }
                        CarouselItemScopeImpl scope = (CarouselItemScopeImpl) value$iv3;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerStart($composer3, -685845201, "CC(remember):Carousel.kt#9igjgp");
                        Object it$iv4 = $composer3.rememberedValue();
                        if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                            value$iv4 = new Shape() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$clipShape$1$1
                                @Override // androidx.compose.ui.graphics.Shape
                                /* JADX INFO: renamed from: createOutline-Pq9zytI */
                                public Outline mo637createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                                    return new Outline.Rectangle(carouselItemInfo.getMaskRect());
                                }
                            };
                            $composer3.updateRememberedValue(value$iv4);
                        } else {
                            value$iv4 = it$iv4;
                        }
                        CarouselKt$Carousel$2$clipShape$1$1 clipShape = (CarouselKt$Carousel$2$clipShape$1$1) value$iv4;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Modifier.Companion companion = Modifier.INSTANCE;
                        CarouselState carouselState = state;
                        ComposerKt.sourceInformationMarkerStart($composer3, -685826689, "CC(remember):Carousel.kt#9igjgp");
                        boolean invalid$iv2 = $composer3.changed(pageSize);
                        final CarouselPageSize carouselPageSize = pageSize;
                        Object it$iv5 = $composer3.rememberedValue();
                        if (invalid$iv2 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                            value$iv5 = new Function0<Strategy>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$2$1$1
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Strategy invoke() {
                                    return carouselPageSize.getStrategy();
                                }
                            };
                            $composer3.updateRememberedValue(value$iv5);
                        } else {
                            value$iv5 = it$iv5;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Modifier modifier$iv = CarouselKt.carouselItem(companion, page, carouselState, (Function0) value$iv5, carouselItemInfo, clipShape);
                        Function4<CarouselItemScope, Integer, Composer, Integer, Unit> function5 = function4;
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
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
                            function0 = constructor;
                            $composer3.createNode(function0);
                        } else {
                            function0 = constructor;
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
                        int i5 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i6 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1114351623, "C334@15324L13:Carousel.kt#dcf9yb");
                        function5.invoke(scope, Integer.valueOf(page), $composer3, Integer.valueOf($changed2 & 112));
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
                }, $composer2, 54), $composer2, (($dirty2 >> 12) & 112) | (57344 & $dirty2) | (($dirty2 >> 3) & 458752) | (29360128 & $dirty2), 3072, 3904);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-1615314857);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            itemSpacing3 = itemSpacing2;
            flingBehavior3 = flingBehavior2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final float f = itemSpacing3;
            final TargetedFlingBehavior targetedFlingBehavior = flingBehavior3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt$Carousel$3
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
                    CarouselKt.m3257CarouselV95POc(state, orientation, function2, contentPadding, maxNonFocalVisibleItemCount, modifier5, f, targetedFlingBehavior, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    private static final float calculateBeforeContentPadding(PaddingValues $this$calculateBeforeContentPadding, Orientation orientation, Composer $composer, int $changed) {
        float dpValue;
        ComposerKt.sourceInformationMarkerStart($composer, 1896839347, "C(calculateBeforeContentPadding)*349@15698L7:Carousel.kt#dcf9yb");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1896839347, $changed, -1, "androidx.compose.material3.carousel.calculateBeforeContentPadding (Carousel.kt:341)");
        }
        $composer.startReplaceGroup(295830617);
        ComposerKt.sourceInformation($composer, "346@15649L7");
        if (orientation == Orientation.Vertical) {
            dpValue = $this$calculateBeforeContentPadding.getTop();
        } else {
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer);
            dpValue = PaddingKt.calculateStartPadding($this$calculateBeforeContentPadding, (LayoutDirection) objConsume);
        }
        $composer.endReplaceGroup();
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume2 = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density $this$calculateBeforeContentPadding_u24lambda_u243 = (Density) objConsume2;
        float fMo726toPx0680j_4 = $this$calculateBeforeContentPadding_u24lambda_u243.mo726toPx0680j_4(dpValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return fMo726toPx0680j_4;
    }

    private static final float calculateAfterContentPadding(PaddingValues $this$calculateAfterContentPadding, Orientation orientation, Composer $composer, int $changed) {
        float dpValue;
        ComposerKt.sourceInformationMarkerStart($composer, 1018496720, "C(calculateAfterContentPadding)*361@16056L7:Carousel.kt#dcf9yb");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1018496720, $changed, -1, "androidx.compose.material3.carousel.calculateAfterContentPadding (Carousel.kt:353)");
        }
        $composer.startReplaceGroup(-587616383);
        ComposerKt.sourceInformation($composer, "358@16007L7");
        if (orientation == Orientation.Vertical) {
            dpValue = $this$calculateAfterContentPadding.getBottom();
        } else {
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer);
            dpValue = PaddingKt.calculateEndPadding($this$calculateAfterContentPadding, (LayoutDirection) objConsume);
        }
        $composer.endReplaceGroup();
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume2 = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density $this$calculateAfterContentPadding_u24lambda_u244 = (Density) objConsume2;
        float fMo726toPx0680j_4 = $this$calculateAfterContentPadding_u24lambda_u244.mo726toPx0680j_4(dpValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return fMo726toPx0680j_4;
    }

    public static final Modifier carouselItem(Modifier $this$carouselItem, final int index, final CarouselState state, final Function0<Strategy> function0, final CarouselItemInfoImpl carouselItemInfo, final Shape clipShape) {
        return LayoutModifierKt.layout($this$carouselItem, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.material3.carousel.CarouselKt.carouselItem.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                return m3260invoke3p2s80s(measureScope, measurable, constraints.getValue());
            }

            /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
            public final MeasureResult m3260invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long constraints) {
                long jM6959copyZbe2FdA;
                final Strategy strategyResult = function0.invoke();
                if (!strategyResult.getIsValid()) {
                    return MeasureScope.layout$default($this$layout, 0, 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt.carouselItem.1.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope $this$layout2) {
                        }
                    }, 4, null);
                }
                final boolean isVertical = state.getPagerState().getLayoutInfo().getOrientation() == Orientation.Vertical;
                final boolean isRtl = $this$layout.getLayoutDirection() == LayoutDirection.Rtl;
                float mainAxisSize = strategyResult.getItemMainAxisSize();
                if (isVertical) {
                    jM6959copyZbe2FdA = Constraints.m6959copyZbe2FdA(constraints, Constraints.m6971getMinWidthimpl(constraints), Constraints.m6969getMaxWidthimpl(constraints), MathKt.roundToInt(mainAxisSize), MathKt.roundToInt(mainAxisSize));
                } else {
                    jM6959copyZbe2FdA = Constraints.m6959copyZbe2FdA(constraints, MathKt.roundToInt(mainAxisSize), MathKt.roundToInt(mainAxisSize), Constraints.m6970getMinHeightimpl(constraints), Constraints.m6968getMaxHeightimpl(constraints));
                }
                long itemConstraints = jM6959copyZbe2FdA;
                final Placeable placeable = measurable.mo5870measureBRTryo0(itemConstraints);
                int width = placeable.getWidth();
                int height = placeable.getHeight();
                final CarouselState carouselState = state;
                final int i = index;
                final CarouselItemInfoImpl carouselItemInfoImpl = carouselItemInfo;
                final Shape shape = clipShape;
                return MeasureScope.layout$default($this$layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt.carouselItem.1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                        invoke2(placementScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Placeable.PlacementScope $this$layout2) {
                        Placeable placeable2 = placeable;
                        final CarouselState carouselState2 = carouselState;
                        final Strategy strategy = strategyResult;
                        final int i2 = i;
                        final boolean z = isVertical;
                        final CarouselItemInfoImpl carouselItemInfoImpl2 = carouselItemInfoImpl;
                        final Shape shape2 = shape;
                        final boolean z2 = isRtl;
                        Placeable.PlacementScope.placeWithLayer$default($this$layout2, placeable2, 0, 0, 0.0f, new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.carousel.CarouselKt.carouselItem.1.2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                invoke2(graphicsLayerScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(GraphicsLayerScope $this$placeWithLayer) {
                                float f;
                                float size;
                                float scrollOffset = CarouselKt.calculateCurrentScrollOffset(carouselState2, strategy);
                                float maxScrollOffset = CarouselKt.calculateMaxScrollOffset(carouselState2, strategy);
                                KeylineList keylines = Strategy.getKeylineListForScrollOffset$material3_release$default(strategy, scrollOffset, maxScrollOffset, false, 4, null);
                                KeylineList roundedKeylines = strategy.getKeylineListForScrollOffset$material3_release(scrollOffset, maxScrollOffset, true);
                                float itemSizeWithSpacing = strategy.getItemMainAxisSize() + strategy.getItemSpacing();
                                float unadjustedCenter = ((i2 * itemSizeWithSpacing) + (strategy.getItemMainAxisSize() / 2.0f)) - scrollOffset;
                                Keyline keylineBefore = keylines.getKeylineBefore(unadjustedCenter);
                                Keyline keylineAfter = keylines.getKeylineAfter(unadjustedCenter);
                                float progress = CarouselKt.getProgress(keylineBefore, keylineAfter, unadjustedCenter);
                                Keyline interpolatedKeyline = KeylineListKt.lerp(keylineBefore, keylineAfter, progress);
                                boolean isOutOfKeylineBounds = Intrinsics.areEqual(keylineBefore, keylineAfter);
                                float centerX = (z ? Size.m4359getHeightimpl($this$placeWithLayer.getSize()) : strategy.getItemMainAxisSize()) / 2.0f;
                                float centerY = (z ? strategy.getItemMainAxisSize() : Size.m4359getHeightimpl($this$placeWithLayer.getSize())) / 2.0f;
                                if (z) {
                                    size = Size.m4362getWidthimpl($this$placeWithLayer.getSize());
                                    f = 2.0f;
                                } else {
                                    f = 2.0f;
                                    size = interpolatedKeyline.getSize();
                                }
                                float halfMaskWidth = size / f;
                                float halfMaskHeight = (z ? interpolatedKeyline.getSize() : Size.m4359getHeightimpl($this$placeWithLayer.getSize())) / f;
                                float itemSizeWithSpacing2 = centerY - halfMaskHeight;
                                Rect maskRect = new Rect(centerX - halfMaskWidth, itemSizeWithSpacing2, centerX + halfMaskWidth, centerY + halfMaskHeight);
                                carouselItemInfoImpl2.setSizeState(interpolatedKeyline.getSize());
                                CarouselItemInfoImpl carouselItemInfoImpl3 = carouselItemInfoImpl2;
                                KeylineList $this$minBy$iv = roundedKeylines;
                                Iterator<Keyline> it = $this$minBy$iv.iterator();
                                if (!it.hasNext()) {
                                    throw new NoSuchElementException();
                                }
                                Object minElem$iv = it.next();
                                if (it.hasNext()) {
                                    Keyline it2 = (Keyline) minElem$iv;
                                    float minValue$iv = it2.getSize();
                                    while (true) {
                                        Object e$iv = it.next();
                                        Keyline it3 = (Keyline) e$iv;
                                        float size2 = it3.getSize();
                                        float centerY2 = centerY;
                                        if (Float.compare(minValue$iv, size2) > 0) {
                                            minElem$iv = e$iv;
                                            minValue$iv = size2;
                                        }
                                        if (!it.hasNext()) {
                                            break;
                                        } else {
                                            centerY = centerY2;
                                        }
                                    }
                                }
                                carouselItemInfoImpl3.setMinSizeState(((Keyline) minElem$iv).getSize());
                                carouselItemInfoImpl2.setMaxSizeState(roundedKeylines.getFirstFocal().getSize());
                                carouselItemInfoImpl2.setMaskRectState(maskRect);
                                $this$placeWithLayer.setClip(!Intrinsics.areEqual(maskRect, new Rect(0.0f, 0.0f, Size.m4362getWidthimpl($this$placeWithLayer.getSize()), Size.m4359getHeightimpl($this$placeWithLayer.getSize()))));
                                $this$placeWithLayer.setShape(shape2);
                                float translation = interpolatedKeyline.getOffset() - unadjustedCenter;
                                if (isOutOfKeylineBounds) {
                                    float outOfBoundsOffset = (unadjustedCenter - interpolatedKeyline.getUnadjustedOffset()) / interpolatedKeyline.getSize();
                                    translation += outOfBoundsOffset;
                                }
                                if (z) {
                                    $this$placeWithLayer.setTranslationY(translation);
                                } else {
                                    $this$placeWithLayer.setTranslationX(z2 ? -translation : translation);
                                }
                            }
                        }, 4, (Object) null);
                    }
                }, 4, null);
            }
        });
    }

    public static final float calculateCurrentScrollOffset(CarouselState state, Strategy strategy) {
        float itemSizeWithSpacing = strategy.getItemMainAxisSize() + strategy.getItemSpacing();
        float currentItemScrollOffset = (state.getPagerState().getCurrentPage() * itemSizeWithSpacing) + (state.getPagerState().getCurrentPageOffsetFraction() * itemSizeWithSpacing);
        return currentItemScrollOffset - KeylineSnapPositionKt.getSnapPositionOffset(strategy, state.getPagerState().getCurrentPage(), state.getPagerState().getPageCount());
    }

    public static final float calculateMaxScrollOffset(CarouselState state, Strategy strategy) {
        float itemCount = state.getPagerState().getPageCount();
        float maxScrollPossible = (strategy.getItemMainAxisSize() * itemCount) + (strategy.getItemSpacing() * (itemCount - 1));
        return RangesKt.coerceAtLeast(maxScrollPossible - strategy.getAvailableSpace(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float getProgress(Keyline before, Keyline after, float unadjustedOffset) {
        if (Intrinsics.areEqual(before, after)) {
            return 1.0f;
        }
        float total = after.getUnadjustedOffset() - before.getUnadjustedOffset();
        return (unadjustedOffset - before.getUnadjustedOffset()) / total;
    }
}
