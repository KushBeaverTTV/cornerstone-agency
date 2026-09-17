package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.PlatformMagnifierFactory;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.contextmenu.ContextMenuState_androidKt;
import androidx.compose.foundation.text.ContextMenu_androidKt;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: TextFieldSelectionManager.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u001a%\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0014\u0010\f\u001a\u00020\r*\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\u000f²\u0006\n\u0010\u0010\u001a\u00020\u0011X\u008a\u008e\u0002"}, d2 = {"isShiftPressed", "", "Landroidx/compose/ui/input/pointer/PointerEvent;", "(Landroidx/compose/ui/input/pointer/PointerEvent;)Z", "contextMenuBuilder", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "contextMenuState", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "textFieldMagnifier", "Landroidx/compose/ui/Modifier;", "manager", "foundation_release", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldSelectionManager_androidKt {
    public static final boolean isShiftPressed(PointerEvent $this$isShiftPressed) {
        return false;
    }

    public static final Modifier textFieldMagnifier(Modifier $this$textFieldMagnifier, TextFieldSelectionManager manager) {
        if (!Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null)) {
            return $this$textFieldMagnifier;
        }
        return ComposedModifierKt.composed$default($this$textFieldMagnifier, null, new C04591(manager), 1, null);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionManager.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    static final class C04591 extends Lambda implements Function3<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ TextFieldSelectionManager $manager;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04591(TextFieldSelectionManager textFieldSelectionManager) {
            super(3);
            this.$manager = textFieldSelectionManager;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
            return invoke(modifier, composer, num.intValue());
        }

        public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
            Object value$iv;
            Object value$iv2;
            Object value$iv3;
            $composer.startReplaceGroup(1980580247);
            ComposerKt.sourceInformation($composer, "C48@2080L7,49@2117L41,51@2225L96,54@2355L503:TextFieldSelectionManager.android.kt#eksfi3");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1980580247, $changed, -1, "androidx.compose.foundation.text.selection.textFieldMagnifier.<anonymous> (TextFieldSelectionManager.android.kt:48)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer);
            final Density density = (Density) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer, 1684878850, "CC(remember):TextFieldSelectionManager.android.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntSize.m7178boximpl(IntSize.INSTANCE.m7191getZeroYbymL2g()), null, 2, null);
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableState magnifierSize$delegate = (MutableState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, 1684882361, "CC(remember):TextFieldSelectionManager.android.kt#9igjgp");
            boolean invalid$iv = $composer.changedInstance(this.$manager);
            final TextFieldSelectionManager textFieldSelectionManager = this.$manager;
            Object it$iv2 = $composer.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new Function0<Offset>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Offset invoke() {
                        return Offset.m4282boximpl(m1759invokeF1C5BW0());
                    }

                    /* JADX INFO: renamed from: invoke-F1C5BW0, reason: not valid java name */
                    public final long m1759invokeF1C5BW0() {
                        return TextFieldSelectionManagerKt.m1758calculateSelectionMagnifierCenterAndroidO0kMr_c(textFieldSelectionManager, TextFieldSelectionManager_androidKt.C04591.invoke$lambda$1(magnifierSize$delegate));
                    }
                };
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            Function0 function0 = (Function0) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, 1684886928, "CC(remember):TextFieldSelectionManager.android.kt#9igjgp");
            boolean invalid$iv2 = $composer.changed(density);
            Object it$iv3 = $composer.rememberedValue();
            if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = new Function1<Function0<? extends Offset>, Modifier>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Modifier invoke(Function0<? extends Offset> function1) {
                        return invoke2((Function0<Offset>) function1);
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final Modifier invoke2(final Function0<Offset> function1) {
                        Modifier.Companion companion = Modifier.INSTANCE;
                        Function1<Density, Offset> function2 = new Function1<Density, Offset>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1$2$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Offset invoke(Density density2) {
                                return Offset.m4282boximpl(m1760invoketuRUvjQ(density2));
                            }

                            /* JADX INFO: renamed from: invoke-tuRUvjQ, reason: not valid java name */
                            public final long m1760invoketuRUvjQ(Density $this$magnifier) {
                                return function1.invoke().getPackedValue();
                            }
                        };
                        final Density density2 = density;
                        final MutableState<IntSize> mutableState = magnifierSize$delegate;
                        return Magnifier_androidKt.m659magnifierjPUL71Q$default(companion, function2, null, new Function1<DpSize, Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1$2$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(DpSize dpSize) {
                                m1761invokeEaSLcWc(dpSize.getPackedValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-EaSLcWc, reason: not valid java name */
                            public final void m1761invokeEaSLcWc(long size) {
                                MutableState<IntSize> mutableState2 = mutableState;
                                Density $this$invoke_EaSLcWc_u24lambda_u240 = density2;
                                TextFieldSelectionManager_androidKt.C04591.invoke$lambda$2(mutableState2, IntSizeKt.IntSize($this$invoke_EaSLcWc_u24lambda_u240.mo720roundToPx0680j_4(DpSize.m7114getWidthD9Ej5fM(size)), $this$invoke_EaSLcWc_u24lambda_u240.mo720roundToPx0680j_4(DpSize.m7112getHeightD9Ej5fM(size))));
                            }
                        }, 0.0f, true, 0L, 0.0f, 0.0f, false, PlatformMagnifierFactory.INSTANCE.getForCurrentPlatform(), 490, null);
                    }
                };
                $composer.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierAnimatedSelectionMagnifier = SelectionMagnifierKt.animatedSelectionMagnifier($this$composed, function0, (Function1) value$iv3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return modifierAnimatedSelectionMagnifier;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long invoke$lambda$1(MutableState<IntSize> mutableState) {
            MutableState<IntSize> $this$getValue$iv = mutableState;
            return $this$getValue$iv.getValue().getPackedValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$2(MutableState<IntSize> mutableState, long value) {
            mutableState.setValue(IntSize.m7178boximpl(value));
        }
    }

    public static final Function1<ContextMenuScope, Unit> contextMenuBuilder(final TextFieldSelectionManager $this$contextMenuBuilder, final ContextMenuState contextMenuState) {
        return new Function1<ContextMenuScope, Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt.contextMenuBuilder.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContextMenuScope contextMenuScope) {
                invoke2(contextMenuScope);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code duplicated, block: B:24:0x009d  */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContextMenuScope $this$null) {
                boolean enabled$iv;
                boolean isPassword = $this$contextMenuBuilder.getVisualTransformation() instanceof PasswordVisualTransformation;
                boolean hasSelection = !TextRange.m6473getCollapsedimpl($this$contextMenuBuilder.getValue$foundation_release().getSelection());
                final ContextMenuState state$iv = contextMenuState;
                TextContextMenuItems label$iv = TextContextMenuItems.Cut;
                boolean enabled$iv2 = hasSelection && $this$contextMenuBuilder.getEditable() && !isPassword;
                final TextFieldSelectionManager textFieldSelectionManager = $this$contextMenuBuilder;
                $this$null.item(new ContextMenu_androidKt.AnonymousClass1(label$iv), (10 & 2) != 0 ? Modifier.INSTANCE : null, (10 & 4) != 0 ? true : enabled$iv2, (10 & 8) != 0 ? null : null, new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$1
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
                        textFieldSelectionManager.cut$foundation_release();
                        ContextMenuState_androidKt.close(state$iv);
                    }
                });
                final ContextMenuState state$iv2 = contextMenuState;
                TextContextMenuItems label$iv2 = TextContextMenuItems.Copy;
                boolean enabled$iv3 = hasSelection && !isPassword;
                final TextFieldSelectionManager textFieldSelectionManager2 = $this$contextMenuBuilder;
                $this$null.item(new ContextMenu_androidKt.AnonymousClass1(label$iv2), (10 & 2) != 0 ? Modifier.INSTANCE : null, (10 & 4) != 0 ? true : enabled$iv3, (10 & 8) != 0 ? null : null, new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$2
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
                        textFieldSelectionManager2.copy$foundation_release(false);
                        ContextMenuState_androidKt.close(state$iv2);
                    }
                });
                final ContextMenuState state$iv3 = contextMenuState;
                TextContextMenuItems label$iv3 = TextContextMenuItems.Paste;
                if ($this$contextMenuBuilder.getEditable()) {
                    ClipboardManager clipboardManager = $this$contextMenuBuilder.getClipboardManager();
                    if (clipboardManager != null && clipboardManager.hasText()) {
                        enabled$iv = true;
                    } else {
                        enabled$iv = false;
                    }
                } else {
                    enabled$iv = false;
                }
                final TextFieldSelectionManager textFieldSelectionManager3 = $this$contextMenuBuilder;
                $this$null.item(new ContextMenu_androidKt.AnonymousClass1(label$iv3), (10 & 2) != 0 ? Modifier.INSTANCE : null, (10 & 4) != 0 ? true : enabled$iv, (10 & 8) != 0 ? null : null, new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$3
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
                        textFieldSelectionManager3.paste$foundation_release();
                        ContextMenuState_androidKt.close(state$iv3);
                    }
                });
                final ContextMenuState state$iv4 = contextMenuState;
                TextContextMenuItems label$iv4 = TextContextMenuItems.SelectAll;
                boolean enabled$iv4 = TextRange.m6475getLengthimpl($this$contextMenuBuilder.getValue$foundation_release().getSelection()) != $this$contextMenuBuilder.getValue$foundation_release().getText().length();
                final TextFieldSelectionManager textFieldSelectionManager4 = $this$contextMenuBuilder;
                $this$null.item(new ContextMenu_androidKt.AnonymousClass1(label$iv4), (10 & 2) != 0 ? Modifier.INSTANCE : null, (10 & 4) != 0 ? true : enabled$iv4, (10 & 8) != 0 ? null : null, new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$4
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
                        textFieldSelectionManager4.selectAll$foundation_release();
                        ContextMenuState_androidKt.close(state$iv4);
                    }
                });
            }
        };
    }
}
