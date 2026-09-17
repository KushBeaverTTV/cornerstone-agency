package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "(Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 48)
final class AppBarKt$TwoRowsTopAppBar$6 extends Lambda implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ Function2<Composer, Integer, Unit> $actionsRow;
    final /* synthetic */ float $bottomTitleAlpha;
    final /* synthetic */ float $collapsedHeight;
    final /* synthetic */ TopAppBarColors $colors;
    final /* synthetic */ float $expandedHeight;
    final /* synthetic */ boolean $hideBottomRowSemantics;
    final /* synthetic */ boolean $hideTopRowSemantics;
    final /* synthetic */ Function2<Composer, Integer, Unit> $navigationIcon;
    final /* synthetic */ TopAppBarScrollBehavior $scrollBehavior;
    final /* synthetic */ Function2<Composer, Integer, Unit> $smallTitle;
    final /* synthetic */ TextStyle $smallTitleTextStyle;
    final /* synthetic */ Function2<Composer, Integer, Unit> $title;
    final /* synthetic */ Ref.IntRef $titleBottomPaddingPx;
    final /* synthetic */ TextStyle $titleTextStyle;
    final /* synthetic */ float $topTitleAlpha;
    final /* synthetic */ WindowInsets $windowInsets;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    AppBarKt$TwoRowsTopAppBar$6(WindowInsets windowInsets, float f, TopAppBarColors topAppBarColors, Function2<? super Composer, ? super Integer, Unit> function2, TextStyle textStyle, float f2, boolean z, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, float f3, TopAppBarScrollBehavior topAppBarScrollBehavior, Function2<? super Composer, ? super Integer, Unit> function5, TextStyle textStyle2, float f4, Ref.IntRef intRef, boolean z2) {
        super(2);
        this.$windowInsets = windowInsets;
        this.$collapsedHeight = f;
        this.$colors = topAppBarColors;
        this.$smallTitle = function2;
        this.$smallTitleTextStyle = textStyle;
        this.$topTitleAlpha = f2;
        this.$hideTopRowSemantics = z;
        this.$navigationIcon = function3;
        this.$actionsRow = function4;
        this.$expandedHeight = f3;
        this.$scrollBehavior = topAppBarScrollBehavior;
        this.$title = function5;
        this.$titleTextStyle = textStyle2;
        this.$bottomTitleAlpha = f4;
        this.$titleBottomPaddingPx = intRef;
        this.$hideBottomRowSemantics = z2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x022a  */
    /* JADX WARN: Code duplicated, block: B:35:0x0295  */
    /* JADX WARN: Code duplicated, block: B:38:? A[RETURN, SYNTHETIC] */
    public final void invoke(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        CompositionLocalMap localMap$iv$iv;
        MeasurePolicy measurePolicy$iv;
        boolean invalid$iv;
        Object value$iv;
        ComposerKt.sourceInformation($composer, "C2043@93243L2293:AppBar.kt#uh7d8r");
        if (($changed & 3) != 2 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1350062619, $changed, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:2043)");
            }
            WindowInsets windowInsets = this.$windowInsets;
            float f = this.$collapsedHeight;
            TopAppBarColors topAppBarColors = this.$colors;
            Function2<Composer, Integer, Unit> function2 = this.$smallTitle;
            TextStyle textStyle = this.$smallTitleTextStyle;
            float f2 = this.$topTitleAlpha;
            boolean z = this.$hideTopRowSemantics;
            Function2<Composer, Integer, Unit> function3 = this.$navigationIcon;
            Function2<Composer, Integer, Unit> function4 = this.$actionsRow;
            float f3 = this.$expandedHeight;
            final TopAppBarScrollBehavior topAppBarScrollBehavior = this.$scrollBehavior;
            Function2<Composer, Integer, Unit> function5 = this.$title;
            TextStyle textStyle2 = this.$titleTextStyle;
            float f4 = this.$bottomTitleAlpha;
            Ref.IntRef intRef = this.$titleBottomPaddingPx;
            boolean z2 = this.$hideBottomRowSemantics;
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                localMap$iv$iv = localMap$iv$iv2;
                measurePolicy$iv = measurePolicy$iv2;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i2 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 251887626, "C2044@93264L1012,2073@94812L45,2064@94289L1237:AppBar.kt#uh7d8r");
                AppBarKt.m2148TopAppBarLayoutkXwM9vE(SizeKt.m1067heightInVpY3zN4$default(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets)), 0.0f, f, 1, null), new ScrolledOffset() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$6$$ExternalSyntheticLambda0
                    @Override // androidx.compose.material3.ScrolledOffset
                    public final float offset() {
                        return AppBarKt$TwoRowsTopAppBar$6.invoke$lambda$3$lambda$0();
                    }
                }, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function2, textStyle, f2, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z, function3, function4, $composer, 905969712, 3078);
                Modifier modifierM1067heightInVpY3zN4$default = SizeKt.m1067heightInVpY3zN4$default(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m1105onlybOOhFvg(windowInsets, WindowInsetsSides.INSTANCE.m1127getHorizontalJoeWqyM()))), 0.0f, Dp.m7016constructorimpl(f3 - f), 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1947835376, "CC(remember):AppBar.kt#9igjgp");
                invalid$iv = $composer.changed(topAppBarScrollBehavior);
                Object it$iv = $composer.rememberedValue();
                if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new ScrolledOffset() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$6$$ExternalSyntheticLambda1
                        @Override // androidx.compose.material3.ScrolledOffset
                        public final float offset() {
                            return AppBarKt$TwoRowsTopAppBar$6.invoke$lambda$3$lambda$2$lambda$1(topAppBarScrollBehavior);
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                AppBarKt.m2148TopAppBarLayoutkXwM9vE(modifierM1067heightInVpY3zN4$default, (ScrolledOffset) value$iv, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function5, textStyle2, f4, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z2, ComposableSingletons$AppBarKt.INSTANCE.m2322getLambda17$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m2323getLambda18$material3_release(), $composer, 905969664, 3456);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            localMap$iv$iv = localMap$iv$iv2;
            measurePolicy$iv = measurePolicy$iv2;
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i4 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 251887626, "C2044@93264L1012,2073@94812L45,2064@94289L1237:AppBar.kt#uh7d8r");
            AppBarKt.m2148TopAppBarLayoutkXwM9vE(SizeKt.m1067heightInVpY3zN4$default(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets)), 0.0f, f, 1, null), new ScrolledOffset() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$6$$ExternalSyntheticLambda0
                @Override // androidx.compose.material3.ScrolledOffset
                public final float offset() {
                    return AppBarKt$TwoRowsTopAppBar$6.invoke$lambda$3$lambda$0();
                }
            }, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function2, textStyle, f2, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z, function3, function4, $composer, 905969712, 3078);
            Modifier modifierM1067heightInVpY3zN4$default2 = SizeKt.m1067heightInVpY3zN4$default(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m1105onlybOOhFvg(windowInsets, WindowInsetsSides.INSTANCE.m1127getHorizontalJoeWqyM()))), 0.0f, Dp.m7016constructorimpl(f3 - f), 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1947835376, "CC(remember):AppBar.kt#9igjgp");
            invalid$iv = $composer.changed(topAppBarScrollBehavior);
            Object it$iv2 = $composer.rememberedValue();
            if (!invalid$iv) {
                value$iv = new ScrolledOffset() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$6$$ExternalSyntheticLambda1
                    @Override // androidx.compose.material3.ScrolledOffset
                    public final float offset() {
                        return AppBarKt$TwoRowsTopAppBar$6.invoke$lambda$3$lambda$2$lambda$1(topAppBarScrollBehavior);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = new ScrolledOffset() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$6$$ExternalSyntheticLambda1
                    @Override // androidx.compose.material3.ScrolledOffset
                    public final float offset() {
                        return AppBarKt$TwoRowsTopAppBar$6.invoke$lambda$3$lambda$2$lambda$1(topAppBarScrollBehavior);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            AppBarKt.m2148TopAppBarLayoutkXwM9vE(modifierM1067heightInVpY3zN4$default2, (ScrolledOffset) value$iv, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function5, textStyle2, f4, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z2, ComposableSingletons$AppBarKt.INSTANCE.m2322getLambda17$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m2323getLambda18$material3_release(), $composer, 905969664, 3456);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        $composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float invoke$lambda$3$lambda$0() {
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float invoke$lambda$3$lambda$2$lambda$1(TopAppBarScrollBehavior $scrollBehavior) {
        TopAppBarState state;
        if ($scrollBehavior == null || (state = $scrollBehavior.getState()) == null) {
            return 0.0f;
        }
        return state.getHeightOffset();
    }
}
