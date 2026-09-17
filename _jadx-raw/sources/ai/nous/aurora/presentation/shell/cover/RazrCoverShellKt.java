package ai.nous.aurora.presentation.shell.cover;

import ai.nous.aurora.AssistActivity;
import ai.nous.aurora.BuildConfig;
import ai.nous.aurora.MainViewModel;
import ai.nous.aurora.R;
import ai.nous.aurora.domain.model.AuroraSession;
import ai.nous.aurora.domain.model.ConnectionState;
import ai.nous.aurora.domain.model.CoverShellPolicy;
import ai.nous.aurora.domain.model.SessionPresentation;
import ai.nous.aurora.presentation.theme.AuroraBackdropKt;
import ai.nous.aurora.presentation.theme.AuroraColorSchemeKt;
import ai.nous.aurora.presentation.theme.AuroraSpacing;
import android.content.Context;
import android.content.Intent;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.location.LocationRequestCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RazrCoverShell.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a;\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\t\u001a=\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0003¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"RazrCoverShell", "", "state", "Lai/nous/aurora/MainViewModel$UiState;", "onOpenLatest", "Lkotlin/Function0;", "onNewChat", "modifier", "Landroidx/compose/ui/Modifier;", "(Lai/nous/aurora/MainViewModel$UiState;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "CoverAction", "label", "", "subtitle", "enabled", "", "onClick", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app_debug"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RazrCoverShellKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CoverAction$lambda$3(String str, String str2, boolean z, Function0 function0, Modifier modifier, int i, int i2, Composer composer, int i3) {
        CoverAction(str, str2, z, function0, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RazrCoverShell$lambda$1(MainViewModel.UiState uiState, Function0 function0, Function0 function1, Modifier modifier, int i, int i2, Composer composer, int i3) {
        RazrCoverShell(uiState, function0, function1, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void RazrCoverShell(final MainViewModel.UiState state, final Function0<Unit> onOpenLatest, final Function0<Unit> onNewChat, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        String status;
        Modifier modifier3;
        Composer $composer2;
        String preview;
        Sequence<String> sequenceLineSequence;
        String str;
        String strDisplayTitle;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(onOpenLatest, "onOpenLatest");
        Intrinsics.checkNotNullParameter(onNewChat, "onNewChat");
        Composer $composer3 = $composer.startRestartGroup(-1584078538);
        ComposerKt.sourceInformation($composer3, "C(RazrCoverShell)P(3,2,1)70@3032L7,82@3604L9790,82@3554L9840:RazrCoverShell.kt#z0j3je");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onOpenLatest) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(onNewChat) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            $composer2 = $composer3;
        } else {
            Modifier modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1584078538, $dirty2, -1, "ai.nous.aurora.presentation.shell.cover.RazrCoverShell (RazrCoverShell.kt:69)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Context context = (Context) objConsume;
            boolean online = state.getConnection() instanceof ConnectionState.Online;
            ConnectionState connection = state.getConnection();
            if (Intrinsics.areEqual(connection, ConnectionState.Online.INSTANCE)) {
                status = "ONLINE";
            } else if (Intrinsics.areEqual(connection, ConnectionState.Connecting.INSTANCE)) {
                status = "CONNECTING";
            } else {
                if (!(connection instanceof ConnectionState.Error)) {
                    throw new NoWhenBranchMatchedException();
                }
                status = "OFFLINE";
            }
            AuroraSession latest = CoverShellPolicy.INSTANCE.latestSession(state.getSessions());
            String thread = (latest == null || (strDisplayTitle = SessionPresentation.INSTANCE.displayTitle(latest)) == null) ? BuildConfig.HERMES_DISPLAY_NAME : strDisplayTitle;
            String string = (latest == null || (preview = latest.getPreview()) == null || (sequenceLineSequence = StringsKt.lineSequence(preview)) == null || (str = (String) SequencesKt.firstOrNull(sequenceLineSequence)) == null) ? null : StringsKt.trim((CharSequence) str).toString();
            if (string == null) {
                string = "";
            }
            String preview2 = string;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, null);
            modifier3 = modifier4;
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(2105080434, true, new AnonymousClass1(online, status, thread, preview2, state, context, latest, onOpenLatest, onNewChat), $composer3, 54);
            $composer2 = $composer3;
            AuroraBackdropKt.AuroraBackdrop(modifierFillMaxSize$default, 0, composableLambdaRememberComposableLambda, $composer2, 384, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RazrCoverShellKt.RazrCoverShell$lambda$1(state, onOpenLatest, onNewChat, modifier5, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1, reason: invalid class name */
    /* JADX INFO: compiled from: RazrCoverShell.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements Function3<BoxScope, Composer, Integer, Unit> {
        final /* synthetic */ Context $context;
        final /* synthetic */ AuroraSession $latest;
        final /* synthetic */ Function0<Unit> $onNewChat;
        final /* synthetic */ Function0<Unit> $onOpenLatest;
        final /* synthetic */ boolean $online;
        final /* synthetic */ String $preview;
        final /* synthetic */ MainViewModel.UiState $state;
        final /* synthetic */ String $status;
        final /* synthetic */ String $thread;

        AnonymousClass1(boolean z, String str, String str2, String str3, MainViewModel.UiState uiState, Context context, AuroraSession auroraSession, Function0<Unit> function0, Function0<Unit> function1) {
            this.$online = z;
            this.$status = str;
            this.$thread = str2;
            this.$preview = str3;
            this.$state = uiState;
            this.$context = context;
            this.$latest = auroraSession;
            this.$onOpenLatest = function0;
            this.$onNewChat = function1;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer, Integer num) {
            invoke(boxScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:103:0x08ba  */
        /* JADX WARN: Code duplicated, block: B:104:0x08cb  */
        /* JADX WARN: Code duplicated, block: B:107:0x0906  */
        /* JADX WARN: Code duplicated, block: B:108:0x0917  */
        /* JADX WARN: Code duplicated, block: B:111:0x0a8d  */
        /* JADX WARN: Code duplicated, block: B:114:0x0a99  */
        /* JADX WARN: Code duplicated, block: B:115:0x0a9f  */
        /* JADX WARN: Code duplicated, block: B:118:0x0ad2  */
        /* JADX WARN: Code duplicated, block: B:121:0x0ae5  */
        /* JADX WARN: Code duplicated, block: B:122:0x0ae8  */
        /* JADX WARN: Code duplicated, block: B:126:0x0bad  */
        /* JADX WARN: Code duplicated, block: B:129:0x0bb9  */
        /* JADX WARN: Code duplicated, block: B:130:0x0bbf  */
        /* JADX WARN: Code duplicated, block: B:133:0x0bf2  */
        /* JADX WARN: Code duplicated, block: B:136:0x0c05  */
        /* JADX WARN: Code duplicated, block: B:137:0x0c08  */
        /* JADX WARN: Code duplicated, block: B:141:0x0d27  */
        /* JADX WARN: Code duplicated, block: B:144:0x0e3f  */
        /* JADX WARN: Code duplicated, block: B:147:0x0e4b  */
        /* JADX WARN: Code duplicated, block: B:148:0x0e51  */
        /* JADX WARN: Code duplicated, block: B:151:0x0e84  */
        /* JADX WARN: Code duplicated, block: B:155:0x0e9a A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:159:0x0ef7  */
        /* JADX WARN: Code duplicated, block: B:160:0x0f60  */
        /* JADX WARN: Code duplicated, block: B:163:0x0fd5  */
        /* JADX WARN: Code duplicated, block: B:166:0x10e8  */
        /* JADX WARN: Code duplicated, block: B:169:0x10f4  */
        /* JADX WARN: Code duplicated, block: B:170:0x10fa  */
        /* JADX WARN: Code duplicated, block: B:173:0x112d  */
        /* JADX WARN: Code duplicated, block: B:177:0x1143 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:181:0x11f5  */
        /* JADX WARN: Code duplicated, block: B:184:0x1201  */
        /* JADX WARN: Code duplicated, block: B:185:0x1207  */
        /* JADX WARN: Code duplicated, block: B:188:0x123a  */
        /* JADX WARN: Code duplicated, block: B:192:0x1250 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:196:0x136e  */
        /* JADX WARN: Code duplicated, block: B:200:0x137b A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:204:0x13ba  */
        /* JADX WARN: Code duplicated, block: B:208:0x13c7 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:212:0x1446  */
        /* JADX WARN: Code duplicated, block: B:215:0x1452  */
        /* JADX WARN: Code duplicated, block: B:216:0x1458  */
        /* JADX WARN: Code duplicated, block: B:219:0x148b  */
        /* JADX WARN: Code duplicated, block: B:223:0x14a1 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:227:0x1512  */
        /* JADX WARN: Code duplicated, block: B:228:0x1523  */
        /* JADX WARN: Code duplicated, block: B:231:0x1589  */
        /* JADX WARN: Code duplicated, block: B:232:0x158c  */
        /* JADX WARN: Code duplicated, block: B:235:0x166a  */
        /* JADX WARN: Code duplicated, block: B:238:0x1676  */
        /* JADX WARN: Code duplicated, block: B:239:0x167a  */
        /* JADX WARN: Code duplicated, block: B:242:0x16a9  */
        /* JADX WARN: Code duplicated, block: B:246:0x16bf A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:250:0x1798  */
        /* JADX WARN: Code duplicated, block: B:253:0x17a4  */
        /* JADX WARN: Code duplicated, block: B:254:0x17aa  */
        /* JADX WARN: Code duplicated, block: B:257:0x17db  */
        /* JADX WARN: Code duplicated, block: B:261:0x17f1 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:265:0x1844  */
        /* JADX WARN: Code duplicated, block: B:266:0x1846  */
        /* JADX WARN: Code duplicated, block: B:269:0x1941  */
        /* JADX WARN: Code duplicated, block: B:272:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:58:0x0474  */
        /* JADX WARN: Code duplicated, block: B:61:0x0480  */
        /* JADX WARN: Code duplicated, block: B:62:0x0486  */
        /* JADX WARN: Code duplicated, block: B:73:0x05e2  */
        /* JADX WARN: Code duplicated, block: B:76:0x05ee  */
        /* JADX WARN: Code duplicated, block: B:77:0x05f4  */
        /* JADX WARN: Code duplicated, block: B:88:0x07f3  */
        /* JADX WARN: Code duplicated, block: B:91:0x07ff  */
        /* JADX WARN: Code duplicated, block: B:92:0x0805  */
        /* JADX WARN: Code duplicated, block: B:95:0x0838  */
        /* JADX WARN: Code duplicated, block: B:98:0x084b  */
        /* JADX WARN: Code duplicated, block: B:99:0x084e  */
        public final void invoke(BoxScope AuroraBackdrop, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Function0<ComposeUiNode> function2;
            MeasurePolicy measurePolicy$iv;
            int compositeKeyHash$iv$iv;
            Function0<ComposeUiNode> constructor;
            Function0<ComposeUiNode> function3;
            Composer $this$Layout_u24lambda_u240$iv$iv;
            int compositeKeyHash$iv$iv2;
            Function0<ComposeUiNode> constructor2;
            Function0<ComposeUiNode> function4;
            Composer $this$Layout_u24lambda_u240$iv$iv2;
            MeasurePolicy measurePolicy$iv2;
            int compositeKeyHash$iv$iv3;
            Function0<ComposeUiNode> constructor3;
            Function0<ComposeUiNode> function5;
            Composer $this$Layout_u24lambda_u240$iv$iv3;
            MeasurePolicy measurePolicy$iv3;
            long auroraTextMuted;
            long auroraText;
            MeasurePolicy measurePolicy$iv4;
            int compositeKeyHash$iv$iv4;
            CompositionLocalMap localMap$iv$iv;
            Function0<ComposeUiNode> constructor4;
            Function0<ComposeUiNode> function6;
            Composer $this$Layout_u24lambda_u240$iv$iv4;
            MeasurePolicy measurePolicy$iv5;
            CompositionLocalMap localMap$iv$iv2;
            int compositeKeyHash$iv$iv5;
            CompositionLocalMap localMap$iv$iv3;
            Function0<ComposeUiNode> constructor5;
            Function0<ComposeUiNode> function7;
            Composer $this$Layout_u24lambda_u240$iv$iv5;
            CompositionLocalMap localMap$iv$iv4;
            int compositeKeyHash$iv$iv6;
            Function0<ComposeUiNode> constructor6;
            Function0<ComposeUiNode> function8;
            Composer $this$Layout_u24lambda_u240$iv$iv6;
            RowScope $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244;
            int compositeKeyHash$iv$iv7;
            Function0<ComposeUiNode> constructor7;
            Function0<ComposeUiNode> function9;
            Composer $this$Layout_u24lambda_u240$iv$iv7;
            int compositeKeyHash$iv$iv8;
            Function0<ComposeUiNode> constructor8;
            Function0<ComposeUiNode> function10;
            Composer $this$Layout_u24lambda_u240$iv$iv8;
            boolean invalid$iv;
            Object value$iv;
            boolean invalid$iv2;
            Object value$iv2;
            int compositeKeyHash$iv$iv9;
            Function0<ComposeUiNode> constructor9;
            Function0<ComposeUiNode> function11;
            Composer $this$Layout_u24lambda_u240$iv$iv9;
            long auroraTextMuted2;
            String str;
            int compositeKeyHash$iv$iv10;
            Function0<ComposeUiNode> constructor10;
            Composer $this$Layout_u24lambda_u240$iv$iv10;
            int compositeKeyHash$iv$iv11;
            Function0<ComposeUiNode> constructor11;
            Function0<ComposeUiNode> function12;
            Composer $this$Layout_u24lambda_u240$iv$iv11;
            boolean z;
            Intrinsics.checkNotNullParameter(AuroraBackdrop, "$this$AuroraBackdrop");
            ComposerKt.sourceInformation($composer, "C83@3614L9774:RazrCoverShell.kt#z0j3je");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2105080434, $changed, -1, "ai.nous.aurora.presentation.shell.cover.RazrCoverShell.<anonymous> (RazrCoverShell.kt:83)");
                }
                Modifier modifier$iv = PaddingKt.m1034padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), AuroraSpacing.INSTANCE.m238getMdD9Ej5fM());
                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getCenterHorizontally();
                final boolean z2 = this.$online;
                String str2 = this.$status;
                String str3 = this.$thread;
                String str4 = this.$preview;
                MainViewModel.UiState uiState = this.$state;
                final Context context = this.$context;
                AuroraSession auroraSession = this.$latest;
                Function0<Unit> function13 = this.$onOpenLatest;
                Function0<Unit> function14 = this.$onNewChat;
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv6 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                int $changed$iv$iv = (438 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv12 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv5 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
                Function0<ComposeUiNode> constructor12 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor12;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor12;
                    $composer.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv12 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv12, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv12, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv12.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv12.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv12))) {
                    $this$Layout_u24lambda_u240$iv$iv12.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv12));
                    $this$Layout_u24lambda_u240$iv$iv12.apply(Integer.valueOf(compositeKeyHash$iv$iv12), setCompositeKeyHash);
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv12, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i2 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1381604096, "C90@3873L8312,301@12262L1116:RazrCoverShell.kt#z0j3je");
                Modifier modifier$iv2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv7 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                int $changed$iv$iv2 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv13 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv6 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer, modifier$iv2);
                Function0<ComposeUiNode> constructor13 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor13;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor13;
                    $composer.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv13 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv13, measurePolicy$iv7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv13, localMap$iv$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv13.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv13.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv13))) {
                    $this$Layout_u24lambda_u240$iv$iv13.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv13));
                    $this$Layout_u24lambda_u240$iv$iv13.apply(Integer.valueOf(compositeKeyHash$iv$iv13), setCompositeKeyHash2);
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv13, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1122320070, "C92@4007L2483,156@6500L30,163@6722L13,164@6767L14,159@6576L3113,241@9921L30,243@10041L2134:RazrCoverShell.kt#z0j3je");
                Modifier modifier$iv3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv8 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                int $changed$iv$iv3 = (390 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv14 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv7 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer, modifier$iv3);
                Function0<ComposeUiNode> constructor14 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor14;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor14;
                    $composer.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv14 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv14, measurePolicy$iv8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv14, localMap$iv$iv7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv14.getInserting()) {
                    measurePolicy$iv = measurePolicy$iv8;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv14.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv14))) {
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv14, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = ($changed$iv$iv$iv3 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i6 = ((390 >> 6) & 112) | 6;
                    RowScope $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u243 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, -412972733, "C100@4291L19,101@4376L19,96@4144L563,110@4720L29,111@4762L653,129@5428L27,134@5659L13,135@5708L14,131@5536L944:RazrCoverShell.kt#z0j3je");
                    Modifier modifierClip = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(36)), RoundedCornerShapeKt.getCircleShape());
                    float fM7016constructorimpl = Dp.m7016constructorimpl(1);
                    long auroraOutlineStrong = AuroraColorSchemeKt.getAuroraOutlineStrong($composer, 0);
                    Modifier modifier$iv4 = BackgroundKt.m589backgroundbw27NRU$default(BorderKt.m600borderxT4_qwU(modifierClip, fM7016constructorimpl, Color.m4532copywmQWz5c(auroraOutlineStrong, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraOutlineStrong) : 0.7f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraOutlineStrong) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraOutlineStrong) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraOutlineStrong) : 0.0f), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer, 0), null, 2, null);
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv9 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv4 = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv8 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer, modifier$iv4);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function3 = constructor;
                        $composer.createNode(function3);
                    } else {
                        function3 = constructor;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash4);
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                    int i7 = ($changed$iv$iv$iv4 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i8 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 760878930, "C105@4520L50,104@4483L210:RazrCoverShell.kt#z0j3je");
                    ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.ic_launcher_foreground, $composer, 0), "Aurora", SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(28)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, $composer, 432, 120);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv5 = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv3 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv3 = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv10 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv3, horizontalAlignment$iv3, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv5 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv9 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer, modifier$iv5);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function4 = constructor2;
                        $composer.createNode(function4);
                    } else {
                        function4 = constructor2;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv10, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash5);
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                    int i9 = ($changed$iv$iv$iv5 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                    int i10 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 761193022, "C114@4859L15,112@4787L295,122@5186L15,120@5099L302:RazrCoverShell.kt#z0j3je");
                    TextKt.m3067Text4IGK_g("AURORA · COVER", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                    TextKt.m3067Text4IGK_g(BuildConfig.HERMES_DISPLAY_NAME, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 3120, 120754);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u243, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                    Modifier modifier$iv6 = PaddingKt.m1035paddingVpY3zN4(BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.RoundedCornerShape(50)), AuroraColorSchemeKt.getAuroraSurface($composer, 0), null, 2, null), Dp.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer, 0), RoundedCornerShapeKt.RoundedCornerShape(50)), Dp.m7016constructorimpl(10), Dp.m7016constructorimpl(5));
                    Alignment.Vertical verticalAlignment$iv2 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getStart();
                    measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    int $changed$iv$iv6 = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv10 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer, modifier$iv6);
                    constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv6 = (($changed$iv$iv6 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function5 = constructor3;
                        $composer.createNode(function5);
                    } else {
                        function5 = constructor3;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                        measurePolicy$iv3 = measurePolicy$iv2;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv6, ComposeUiNode.INSTANCE.getSetModifier());
                        int i11 = ($changed$iv$iv$iv6 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        int i12 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 762306201, "C139@5911L223,145@6151L28,146@6196L270:RazrCoverShell.kt#z0j3je");
                        Modifier modifierClip2 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(7)), RoundedCornerShapeKt.getCircleShape());
                        if (z2) {
                            $composer.startReplaceGroup(-529593847);
                            ComposerKt.sourceInformation($composer, "143@6083L10");
                            auroraTextMuted = AuroraColorSchemeKt.getAuroraLive($composer, 0);
                        } else {
                            $composer.startReplaceGroup(-529593330);
                            ComposerKt.sourceInformation($composer, "143@6099L15");
                            auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                        }
                        $composer.endReplaceGroup();
                        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip2, auroraTextMuted, null, 2, null), $composer, 0);
                        SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                        if (z2) {
                            $composer.startReplaceGroup(-529587638);
                            ComposerKt.sourceInformation($composer, "148@6277L11");
                            auroraText = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        } else {
                            $composer.startReplaceGroup(-529587095);
                            ComposerKt.sourceInformation($composer, "148@6294L10");
                            auroraText = AuroraColorSchemeKt.getAuroraText($composer, 0);
                        }
                        $composer.endReplaceGroup();
                        TextKt.m3067Text4IGK_g(str2, (Modifier) null, auroraText, TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(12)), $composer, 6);
                        Modifier modifier$iv7 = PaddingKt.m1035paddingVpY3zN4(BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(16))), AuroraColorSchemeKt.getAuroraSurface($composer, 0), null, 2, null), Dp.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer, 0), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(16))), Dp.m7016constructorimpl(14), Dp.m7016constructorimpl(13));
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                        measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                        int $changed$iv$iv7 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv7 = ComposedModifierKt.materializeModifier($composer, modifier$iv7);
                        constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv7 = (($changed$iv$iv7 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function6 = constructor4;
                            $composer.createNode(function6);
                        } else {
                            function6 = constructor4;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                            measurePolicy$iv5 = measurePolicy$iv4;
                            localMap$iv$iv2 = localMap$iv$iv;
                            if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                            }
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv7, ComposeUiNode.INSTANCE.getSetModifier());
                            int i13 = ($changed$iv$iv$iv7 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                            int i14 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -410226970, "C167@6898L2781:RazrCoverShell.kt#z0j3je");
                            Modifier modifier$iv8 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv4 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv4 = Alignment.INSTANCE.getStart();
                            int $i$f$Column = ((6 >> 3) & 14) | ((6 >> 3) & 112);
                            MeasurePolicy measurePolicy$iv11 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv4, horizontalAlignment$iv4, $composer, $i$f$Column);
                            int $changed$iv$iv8 = (6 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv8 = ComposedModifierKt.materializeModifier($composer, modifier$iv8);
                            constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv8 = (($changed$iv$iv8 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function7 = constructor5;
                                $composer.createNode(function7);
                            } else {
                                function7 = constructor5;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv5 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv11, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting()) {
                                localMap$iv$iv4 = localMap$iv$iv3;
                                if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                                }
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv8, ComposeUiNode.INSTANCE.getSetModifier());
                                int i15 = ($changed$iv$iv$iv8 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                                int i16 = ((6 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                                TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                                TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                                $composer.startReplaceGroup(-529544623);
                                ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                                if (!StringsKt.isBlank(str4)) {
                                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                                    TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                                }
                                $composer.endReplaceGroup();
                                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                                BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                                Modifier modifier$iv9 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                Alignment.Vertical verticalAlignment$iv3 = Alignment.INSTANCE.getCenterVertically();
                                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                                Arrangement.Horizontal horizontalArrangement$iv3 = Arrangement.INSTANCE.getStart();
                                int $changed$iv = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                                MeasurePolicy measurePolicy$iv12 = RowKt.rowMeasurePolicy(horizontalArrangement$iv3, verticalAlignment$iv3, $composer, $changed$iv);
                                int $changed$iv$iv9 = (390 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                                CompositionLocalMap localMap$iv$iv11 = $composer.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv9 = ComposedModifierKt.materializeModifier($composer, modifier$iv9);
                                constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                                int $changed$iv$iv$iv9 = (($changed$iv$iv9 << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!($composer.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer.startReusableNode();
                                if ($composer.getInserting()) {
                                    function8 = constructor6;
                                    $composer.createNode(function8);
                                } else {
                                    function8 = constructor6;
                                    $composer.useNode();
                                }
                                $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv12, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv11, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash9 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv6.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv6))) {
                                    $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                                    $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash9);
                                }
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv9, ComposeUiNode.INSTANCE.getSetModifier());
                                int i17 = ($changed$iv$iv$iv9 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                                int i18 = ((390 >> 6) & 112) | 6;
                                $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                                $composer.startReplaceGroup(-618069607);
                                ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                                if (uiState.getSelectedModel() != null) {
                                    TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                                    SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                                }
                                $composer.endReplaceGroup();
                                TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                                $composer.startReplaceGroup(-618043174);
                                ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                                if (uiState.getSending()) {
                                    SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                                    TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                                }
                                $composer.endReplaceGroup();
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                $composer.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                $composer.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                $composer.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                                Alignment.Horizontal horizontalAlignment$iv5 = Alignment.INSTANCE.getCenterHorizontally();
                                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                                Modifier modifier$iv10 = Modifier.INSTANCE;
                                Arrangement.Vertical verticalArrangement$iv5 = Arrangement.INSTANCE.getTop();
                                MeasurePolicy measurePolicy$iv13 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv5, horizontalAlignment$iv5, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                int $changed$iv$iv10 = (384 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                                CompositionLocalMap localMap$iv$iv12 = $composer.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv10 = ComposedModifierKt.materializeModifier($composer, modifier$iv10);
                                constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                                int $i$f$Column2 = $changed$iv$iv10 << 6;
                                int $changed$iv$iv$iv10 = ($i$f$Column2 & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!($composer.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer.startReusableNode();
                                if ($composer.getInserting()) {
                                    function9 = constructor7;
                                    $composer.createNode(function9);
                                } else {
                                    function9 = constructor7;
                                    $composer.useNode();
                                }
                                $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv13, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv12, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash10 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv7.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv7))) {
                                    $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                                    $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash10);
                                }
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv10, ComposeUiNode.INSTANCE.getSetModifier());
                                int i19 = ($changed$iv$iv$iv10 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                                int i20 = ((384 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getCenter();
                                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                Modifier modifier$iv11 = Modifier.INSTANCE;
                                MeasurePolicy measurePolicy$iv14 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                                int $changed$iv$iv11 = (48 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                                CompositionLocalMap localMap$iv$iv13 = $composer.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv11 = ComposedModifierKt.materializeModifier($composer, modifier$iv11);
                                constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                                int $changed$iv$iv$iv11 = (($changed$iv$iv11 << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!($composer.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer.startReusableNode();
                                if ($composer.getInserting()) {
                                    function10 = constructor8;
                                    $composer.createNode(function10);
                                } else {
                                    function10 = constructor8;
                                    $composer.useNode();
                                }
                                $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv14, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv13, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv8.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv8))) {
                                    $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                                    $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash11);
                                }
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv11, ComposeUiNode.INSTANCE.getSetModifier());
                                int i21 = ($changed$iv$iv$iv11 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                                int i22 = ((48 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                                Modifier modifierM1079size3ABfNKs = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                                Brush.Companion companion = Brush.INSTANCE;
                                long auroraAmber = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                                BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs, companion.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                                Modifier modifierClip3 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                                long auroraAmber2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                                Modifier modifierM600borderxT4_qwU = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip3, Color.m4532copywmQWz5c(auroraAmber2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber2) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber2) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                                $composer.startReplaceGroup(-529441171);
                                ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                                invalid$iv = $composer.changed(z2);
                                Object it$iv = $composer.rememberedValue();
                                if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                                        }
                                    };
                                    $composer.updateRememberedValue(value$iv);
                                } else {
                                    value$iv = it$iv;
                                }
                                $composer.endReplaceGroup();
                                Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU, false, (Function1) value$iv, 1, null);
                                boolean zCanTalk = CoverShellPolicy.INSTANCE.canTalk(z2);
                                $composer.startReplaceGroup(-529427629);
                                ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                                invalid$iv2 = $composer.changedInstance(context);
                                Object it$iv2 = $composer.rememberedValue();
                                if (!invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                                        }
                                    };
                                    $composer.updateRememberedValue(value$iv2);
                                } else {
                                    value$iv2 = it$iv2;
                                }
                                $composer.endReplaceGroup();
                                Modifier modifier$iv12 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default, zCanTalk, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                                Alignment contentAlignment$iv4 = Alignment.INSTANCE.getCenter();
                                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicy$iv15 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
                                int $changed$iv$iv12 = (48 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                                CompositionLocalMap localMap$iv$iv14 = $composer.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv12 = ComposedModifierKt.materializeModifier($composer, modifier$iv12);
                                constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                                int $changed$iv$iv$iv12 = (($changed$iv$iv12 << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!($composer.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer.startReusableNode();
                                if ($composer.getInserting()) {
                                    function11 = constructor9;
                                    $composer.createNode(function11);
                                } else {
                                    function11 = constructor9;
                                    $composer.useNode();
                                }
                                $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv15, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv14, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash12 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv9.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv9))) {
                                    $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                                    $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash12);
                                }
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv12, ComposeUiNode.INSTANCE.getSetModifier());
                                int i23 = ($changed$iv$iv$iv12 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                                int i24 = ((48 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                                Modifier modifierClip4 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                                if (z2) {
                                    $composer.startReplaceGroup(-617963319);
                                    ComposerKt.sourceInformation($composer, "285@11805L11");
                                    auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                                } else {
                                    $composer.startReplaceGroup(-617962771);
                                    ComposerKt.sourceInformation($composer, "285@11822L15");
                                    auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                                }
                                $composer.endReplaceGroup();
                                BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip4, auroraTextMuted2, null, 2, null), $composer, 0);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                $composer.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                $composer.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                                if (z2) {
                                    str = "TALK · tap orb or power";
                                } else {
                                    str = "Offline";
                                }
                                TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                $composer.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                $composer.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                Modifier modifier$iv13 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                Alignment.Horizontal horizontalAlignment$iv6 = Alignment.INSTANCE.getCenterHorizontally();
                                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                                Arrangement.Vertical verticalArrangement$iv6 = Arrangement.INSTANCE.getTop();
                                MeasurePolicy measurePolicy$iv16 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv6, horizontalAlignment$iv6, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                                int $changed$iv$iv13 = (390 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                                CompositionLocalMap localMap$iv$iv15 = $composer.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv13 = ComposedModifierKt.materializeModifier($composer, modifier$iv13);
                                constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                                int $changed$iv$iv$iv13 = (($changed$iv$iv13 << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!($composer.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer.startReusableNode();
                                if ($composer.getInserting()) {
                                    $composer.createNode(constructor10);
                                } else {
                                    $composer.useNode();
                                }
                                $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv16, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv15, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash13 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv10.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv10))) {
                                    $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                                    $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash13);
                                }
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv13, ComposeUiNode.INSTANCE.getSetModifier());
                                int i25 = ($changed$iv$iv$iv13 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
                                int i26 = ((390 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                                Modifier modifier$iv14 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                Arrangement.Horizontal horizontalArrangement$iv4 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                                Alignment.Vertical verticalAlignment$iv4 = Alignment.INSTANCE.getTop();
                                MeasurePolicy measurePolicy$iv17 = RowKt.rowMeasurePolicy(horizontalArrangement$iv4, verticalAlignment$iv4, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                                int $changed$iv$iv14 = (54 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                                CompositionLocalMap localMap$iv$iv16 = $composer.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv14 = ComposedModifierKt.materializeModifier($composer, modifier$iv14);
                                constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                                int $changed$iv$iv$iv14 = (($changed$iv$iv14 << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!($composer.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer.startReusableNode();
                                if ($composer.getInserting()) {
                                    function12 = constructor11;
                                    $composer.createNode(function12);
                                } else {
                                    function12 = constructor11;
                                    $composer.useNode();
                                }
                                $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv17, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv16, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash14 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv11.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv11))) {
                                    $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                                    $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash14);
                                }
                                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv14, ComposeUiNode.INSTANCE.getSetModifier());
                                int i27 = ($changed$iv$iv$iv14 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                                int i28 = ((54 >> 6) & 112) | 6;
                                RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2415 = RowScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                                CoverShellPolicy coverShellPolicy = CoverShellPolicy.INSTANCE;
                                if (auroraSession != null) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2415, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                                RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2415, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                $composer.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                                TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                $composer.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                ComposerKt.sourceInformationMarkerEnd($composer);
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
                            localMap$iv$iv4 = localMap$iv$iv3;
                            $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                            $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash8);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv8, ComposeUiNode.INSTANCE.getSetModifier());
                            int i110 = ($changed$iv$iv$iv8 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance7 = ColumnScopeInstance.INSTANCE;
                            int i111 = ((6 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                            TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                            TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                            $composer.startReplaceGroup(-529544623);
                            ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                            if (!StringsKt.isBlank(str4)) {
                                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                                TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                            }
                            $composer.endReplaceGroup();
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                            BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                            Modifier modifier$iv15 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Alignment.Vertical verticalAlignment$iv5 = Alignment.INSTANCE.getCenterVertically();
                            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                            Arrangement.Horizontal horizontalArrangement$iv5 = Arrangement.INSTANCE.getStart();
                            int $changed$iv2 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                            MeasurePolicy measurePolicy$iv18 = RowKt.rowMeasurePolicy(horizontalArrangement$iv5, verticalAlignment$iv5, $composer, $changed$iv2);
                            int $changed$iv$iv15 = (390 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv17 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv15 = ComposedModifierKt.materializeModifier($composer, modifier$iv15);
                            constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv15 = (($changed$iv$iv15 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function8 = constructor6;
                                $composer.createNode(function8);
                            } else {
                                function8 = constructor6;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv18, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv17, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash15 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                            $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash15);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv15, ComposeUiNode.INSTANCE.getSetModifier());
                            int i112 = ($changed$iv$iv$iv15 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                            int i113 = ((390 >> 6) & 112) | 6;
                            $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                            $composer.startReplaceGroup(-618069607);
                            ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                            if (uiState.getSelectedModel() != null) {
                                TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                                SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                            }
                            $composer.endReplaceGroup();
                            TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                            $composer.startReplaceGroup(-618043174);
                            ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                            if (uiState.getSending()) {
                                SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                                TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                            }
                            $composer.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                            Alignment.Horizontal horizontalAlignment$iv7 = Alignment.INSTANCE.getCenterHorizontally();
                            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                            Modifier modifier$iv16 = Modifier.INSTANCE;
                            Arrangement.Vertical verticalArrangement$iv7 = Arrangement.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv19 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv7, horizontalAlignment$iv7, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            int $changed$iv$iv16 = (384 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv18 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv16 = ComposedModifierKt.materializeModifier($composer, modifier$iv16);
                            constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                            int $i$f$Column3 = $changed$iv$iv16 << 6;
                            int $changed$iv$iv$iv16 = ($i$f$Column3 & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function9 = constructor7;
                                $composer.createNode(function9);
                            } else {
                                function9 = constructor7;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv19, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv18, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash16 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                            $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash16);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv16, ComposeUiNode.INSTANCE.getSetModifier());
                            int i114 = ($changed$iv$iv$iv16 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance8 = ColumnScopeInstance.INSTANCE;
                            int i29 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                            Alignment contentAlignment$iv5 = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            Modifier modifier$iv17 = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv110 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv5, false);
                            int $changed$iv$iv17 = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv19 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv17 = ComposedModifierKt.materializeModifier($composer, modifier$iv17);
                            constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv17 = (($changed$iv$iv17 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function10 = constructor8;
                                $composer.createNode(function10);
                            } else {
                                function10 = constructor8;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv110, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv19, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash17 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                            $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash17);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv17, ComposeUiNode.INSTANCE.getSetModifier());
                            int i210 = ($changed$iv$iv$iv17 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                            int i211 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                            Modifier modifierM1079size3ABfNKs2 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                            Brush.Companion companion2 = Brush.INSTANCE;
                            long auroraAmber3 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                            BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs2, companion2.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber3) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber3) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                            Modifier modifierClip5 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                            long auroraAmber4 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                            Modifier modifierM600borderxT4_qwU2 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip5, Color.m4532copywmQWz5c(auroraAmber4, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber4) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber4) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber4) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber4) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                            $composer.startReplaceGroup(-529441171);
                            ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                            invalid$iv = $composer.changed(z2);
                            Object it$iv3 = $composer.rememberedValue();
                            if (!invalid$iv) {
                            }
                            value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                            $composer.endReplaceGroup();
                            Modifier modifierSemantics$default2 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU2, false, (Function1) value$iv, 1, null);
                            boolean zCanTalk2 = CoverShellPolicy.INSTANCE.canTalk(z2);
                            $composer.startReplaceGroup(-529427629);
                            ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                            invalid$iv2 = $composer.changedInstance(context);
                            Object it$iv4 = $composer.rememberedValue();
                            if (!invalid$iv2) {
                            }
                            value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                                }
                            };
                            $composer.updateRememberedValue(value$iv2);
                            $composer.endReplaceGroup();
                            Modifier modifier$iv18 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default2, zCanTalk2, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                            Alignment contentAlignment$iv6 = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv111 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv6, false);
                            int $changed$iv$iv18 = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv110 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv18 = ComposedModifierKt.materializeModifier($composer, modifier$iv18);
                            constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv18 = (($changed$iv$iv18 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function11 = constructor9;
                                $composer.createNode(function11);
                            } else {
                                function11 = constructor9;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv111, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash18 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                            $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash18);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv18, ComposeUiNode.INSTANCE.getSetModifier());
                            int i212 = ($changed$iv$iv$iv18 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                            int i213 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                            Modifier modifierClip6 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                            if (z2) {
                                $composer.startReplaceGroup(-617963319);
                                ComposerKt.sourceInformation($composer, "285@11805L11");
                                auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                            } else {
                                $composer.startReplaceGroup(-617962771);
                                ComposerKt.sourceInformation($composer, "285@11822L15");
                                auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                            }
                            $composer.endReplaceGroup();
                            BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip6, auroraTextMuted2, null, 2, null), $composer, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                            if (z2) {
                                str = "TALK · tap orb or power";
                            } else {
                                str = "Offline";
                            }
                            TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            Modifier modifier$iv19 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Alignment.Horizontal horizontalAlignment$iv8 = Alignment.INSTANCE.getCenterHorizontally();
                            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv8 = Arrangement.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv112 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv8, horizontalAlignment$iv8, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                            int $changed$iv$iv19 = (390 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv111 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv19 = ComposedModifierKt.materializeModifier($composer, modifier$iv19);
                            constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv19 = (($changed$iv$iv19 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                $composer.createNode(constructor10);
                            } else {
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv112, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash19 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                            $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash19);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv19, ComposeUiNode.INSTANCE.getSetModifier());
                            int i214 = ($changed$iv$iv$iv19 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance9 = ColumnScopeInstance.INSTANCE;
                            int i215 = ((390 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                            Modifier modifier$iv110 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Arrangement.Horizontal horizontalArrangement$iv6 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                            Alignment.Vertical verticalAlignment$iv6 = Alignment.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv113 = RowKt.rowMeasurePolicy(horizontalArrangement$iv6, verticalAlignment$iv6, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                            int $changed$iv$iv110 = (54 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv112 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv110 = ComposedModifierKt.materializeModifier($composer, modifier$iv110);
                            constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv110 = (($changed$iv$iv110 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function12 = constructor11;
                                $composer.createNode(function12);
                            } else {
                                function12 = constructor11;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv113, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv112, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash110 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                            $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash110);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv110, ComposeUiNode.INSTANCE.getSetModifier());
                            int i216 = ($changed$iv$iv$iv110 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                            int i217 = ((54 >> 6) & 112) | 6;
                            RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2416 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                            CoverShellPolicy coverShellPolicy2 = CoverShellPolicy.INSTANCE;
                            if (auroraSession != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy2.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2416, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                            RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2416, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                            TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
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
                        measurePolicy$iv5 = measurePolicy$iv4;
                        localMap$iv$iv2 = localMap$iv$iv;
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash7);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv7, ComposeUiNode.INSTANCE.getSetModifier());
                        int i115 = ($changed$iv$iv$iv7 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                        int i116 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -410226970, "C167@6898L2781:RazrCoverShell.kt#z0j3je");
                        Modifier modifier$iv20 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv9 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv9 = Alignment.INSTANCE.getStart();
                        int $i$f$Column4 = ((6 >> 3) & 14) | ((6 >> 3) & 112);
                        MeasurePolicy measurePolicy$iv114 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv9, horizontalAlignment$iv9, $composer, $i$f$Column4);
                        int $changed$iv$iv20 = (6 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv20 = ComposedModifierKt.materializeModifier($composer, modifier$iv20);
                        constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv20 = (($changed$iv$iv20 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function7 = constructor5;
                            $composer.createNode(function7);
                        } else {
                            function7 = constructor5;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv5 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv114, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash20 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting()) {
                            localMap$iv$iv4 = localMap$iv$iv3;
                            if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                            }
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv20, ComposeUiNode.INSTANCE.getSetModifier());
                            int i117 = ($changed$iv$iv$iv20 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance10 = ColumnScopeInstance.INSTANCE;
                            int i118 = ((6 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                            TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                            TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                            $composer.startReplaceGroup(-529544623);
                            ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                            if (!StringsKt.isBlank(str4)) {
                                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                                TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                            }
                            $composer.endReplaceGroup();
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                            BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                            Modifier modifier$iv111 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Alignment.Vertical verticalAlignment$iv7 = Alignment.INSTANCE.getCenterVertically();
                            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                            Arrangement.Horizontal horizontalArrangement$iv7 = Arrangement.INSTANCE.getStart();
                            int $changed$iv3 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                            MeasurePolicy measurePolicy$iv115 = RowKt.rowMeasurePolicy(horizontalArrangement$iv7, verticalAlignment$iv7, $composer, $changed$iv3);
                            int $changed$iv$iv111 = (390 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv113 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv111 = ComposedModifierKt.materializeModifier($composer, modifier$iv111);
                            constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv111 = (($changed$iv$iv111 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function8 = constructor6;
                                $composer.createNode(function8);
                            } else {
                                function8 = constructor6;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv115, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv113, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                            $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash111);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv111, ComposeUiNode.INSTANCE.getSetModifier());
                            int i119 = ($changed$iv$iv$iv111 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                            int i1110 = ((390 >> 6) & 112) | 6;
                            $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                            $composer.startReplaceGroup(-618069607);
                            ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                            if (uiState.getSelectedModel() != null) {
                                TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                                SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                            }
                            $composer.endReplaceGroup();
                            TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                            $composer.startReplaceGroup(-618043174);
                            ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                            if (uiState.getSending()) {
                                SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                                TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                            }
                            $composer.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                            Alignment.Horizontal horizontalAlignment$iv10 = Alignment.INSTANCE.getCenterHorizontally();
                            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                            Modifier modifier$iv112 = Modifier.INSTANCE;
                            Arrangement.Vertical verticalArrangement$iv10 = Arrangement.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv116 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv10, horizontalAlignment$iv10, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            int $changed$iv$iv112 = (384 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv114 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv112 = ComposedModifierKt.materializeModifier($composer, modifier$iv112);
                            constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                            int $i$f$Column5 = $changed$iv$iv112 << 6;
                            int $changed$iv$iv$iv112 = ($i$f$Column5 & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function9 = constructor7;
                                $composer.createNode(function9);
                            } else {
                                function9 = constructor7;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv116, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv114, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash112 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                            $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash112);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv112, ComposeUiNode.INSTANCE.getSetModifier());
                            int i1111 = ($changed$iv$iv$iv112 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance11 = ColumnScopeInstance.INSTANCE;
                            int i218 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                            Alignment contentAlignment$iv7 = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            Modifier modifier$iv113 = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv117 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv7, false);
                            int $changed$iv$iv113 = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv115 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv113 = ComposedModifierKt.materializeModifier($composer, modifier$iv113);
                            constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv113 = (($changed$iv$iv113 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function10 = constructor8;
                                $composer.createNode(function10);
                            } else {
                                function10 = constructor8;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv117, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv115, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash113 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                            $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash113);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv113, ComposeUiNode.INSTANCE.getSetModifier());
                            int i219 = ($changed$iv$iv$iv113 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
                            int i2110 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                            Modifier modifierM1079size3ABfNKs3 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                            Brush.Companion companion3 = Brush.INSTANCE;
                            long auroraAmber5 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                            BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs3, companion3.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber5, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber5) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber5) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber5) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber5) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                            Modifier modifierClip7 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                            long auroraAmber6 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                            Modifier modifierM600borderxT4_qwU3 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip7, Color.m4532copywmQWz5c(auroraAmber6, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber6) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber6) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber6) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber6) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                            $composer.startReplaceGroup(-529441171);
                            ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                            invalid$iv = $composer.changed(z2);
                            Object it$iv5 = $composer.rememberedValue();
                            if (!invalid$iv) {
                            }
                            value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                            $composer.endReplaceGroup();
                            Modifier modifierSemantics$default3 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU3, false, (Function1) value$iv, 1, null);
                            boolean zCanTalk3 = CoverShellPolicy.INSTANCE.canTalk(z2);
                            $composer.startReplaceGroup(-529427629);
                            ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                            invalid$iv2 = $composer.changedInstance(context);
                            Object it$iv6 = $composer.rememberedValue();
                            if (!invalid$iv2) {
                            }
                            value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                                }
                            };
                            $composer.updateRememberedValue(value$iv2);
                            $composer.endReplaceGroup();
                            Modifier modifier$iv114 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default3, zCanTalk3, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                            Alignment contentAlignment$iv8 = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv118 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv8, false);
                            int $changed$iv$iv114 = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv116 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv114 = ComposedModifierKt.materializeModifier($composer, modifier$iv114);
                            constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv114 = (($changed$iv$iv114 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function11 = constructor9;
                                $composer.createNode(function11);
                            } else {
                                function11 = constructor9;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv118, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv116, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash114 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                            $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash114);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv114, ComposeUiNode.INSTANCE.getSetModifier());
                            int i2111 = ($changed$iv$iv$iv114 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance9 = BoxScopeInstance.INSTANCE;
                            int i2112 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                            Modifier modifierClip8 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                            if (z2) {
                                $composer.startReplaceGroup(-617963319);
                                ComposerKt.sourceInformation($composer, "285@11805L11");
                                auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                            } else {
                                $composer.startReplaceGroup(-617962771);
                                ComposerKt.sourceInformation($composer, "285@11822L15");
                                auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                            }
                            $composer.endReplaceGroup();
                            BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip8, auroraTextMuted2, null, 2, null), $composer, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                            if (z2) {
                                str = "TALK · tap orb or power";
                            } else {
                                str = "Offline";
                            }
                            TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            Modifier modifier$iv115 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Alignment.Horizontal horizontalAlignment$iv11 = Alignment.INSTANCE.getCenterHorizontally();
                            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv11 = Arrangement.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv119 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv11, horizontalAlignment$iv11, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                            int $changed$iv$iv115 = (390 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv117 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv115 = ComposedModifierKt.materializeModifier($composer, modifier$iv115);
                            constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv115 = (($changed$iv$iv115 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                $composer.createNode(constructor10);
                            } else {
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv119, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv117, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash115 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                            $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash115);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv115, ComposeUiNode.INSTANCE.getSetModifier());
                            int i2113 = ($changed$iv$iv$iv115 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance12 = ColumnScopeInstance.INSTANCE;
                            int i2114 = ((390 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                            Modifier modifier$iv116 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Arrangement.Horizontal horizontalArrangement$iv8 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                            Alignment.Vertical verticalAlignment$iv8 = Alignment.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv1110 = RowKt.rowMeasurePolicy(horizontalArrangement$iv8, verticalAlignment$iv8, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                            int $changed$iv$iv116 = (54 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv118 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv116 = ComposedModifierKt.materializeModifier($composer, modifier$iv116);
                            constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv116 = (($changed$iv$iv116 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function12 = constructor11;
                                $composer.createNode(function12);
                            } else {
                                function12 = constructor11;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv1110, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv118, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash116 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                            $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash116);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv116, ComposeUiNode.INSTANCE.getSetModifier());
                            int i2115 = ($changed$iv$iv$iv116 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                            int i2116 = ((54 >> 6) & 112) | 6;
                            RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2417 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                            CoverShellPolicy coverShellPolicy3 = CoverShellPolicy.INSTANCE;
                            if (auroraSession != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy3.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2417, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                            RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2417, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                            TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
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
                        localMap$iv$iv4 = localMap$iv$iv3;
                        $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                        $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash20);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv20, ComposeUiNode.INSTANCE.getSetModifier());
                        int i1112 = ($changed$iv$iv$iv20 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance13 = ColumnScopeInstance.INSTANCE;
                        int i1113 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                        TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                        TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                        $composer.startReplaceGroup(-529544623);
                        ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                        if (!StringsKt.isBlank(str4)) {
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                            TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                        }
                        $composer.endReplaceGroup();
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                        Modifier modifier$iv117 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Vertical verticalAlignment$iv9 = Alignment.INSTANCE.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv9 = Arrangement.INSTANCE.getStart();
                        int $changed$iv4 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                        MeasurePolicy measurePolicy$iv1111 = RowKt.rowMeasurePolicy(horizontalArrangement$iv9, verticalAlignment$iv9, $composer, $changed$iv4);
                        int $changed$iv$iv117 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv119 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv117 = ComposedModifierKt.materializeModifier($composer, modifier$iv117);
                        constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv117 = (($changed$iv$iv117 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function8 = constructor6;
                            $composer.createNode(function8);
                        } else {
                            function8 = constructor6;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv1111, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv119, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash117 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                        $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash117);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv117, ComposeUiNode.INSTANCE.getSetModifier());
                        int i1114 = ($changed$iv$iv$iv117 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i1115 = ((390 >> 6) & 112) | 6;
                        $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                        $composer.startReplaceGroup(-618069607);
                        ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                        if (uiState.getSelectedModel() != null) {
                            TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                            SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                        }
                        $composer.endReplaceGroup();
                        TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                        $composer.startReplaceGroup(-618043174);
                        ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                        if (uiState.getSending()) {
                            SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                            TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                        }
                        $composer.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                        Alignment.Horizontal horizontalAlignment$iv12 = Alignment.INSTANCE.getCenterHorizontally();
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Modifier modifier$iv118 = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv12 = Arrangement.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv1112 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv12, horizontalAlignment$iv12, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv118 = (384 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv1110 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv118 = ComposedModifierKt.materializeModifier($composer, modifier$iv118);
                        constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                        int $i$f$Column6 = $changed$iv$iv118 << 6;
                        int $changed$iv$iv$iv118 = ($i$f$Column6 & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function9 = constructor7;
                            $composer.createNode(function9);
                        } else {
                            function9 = constructor7;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv1112, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv1110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash118 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                        $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash118);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv118, ComposeUiNode.INSTANCE.getSetModifier());
                        int i1116 = ($changed$iv$iv$iv118 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance14 = ColumnScopeInstance.INSTANCE;
                        int i2117 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                        Alignment contentAlignment$iv9 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Modifier modifier$iv119 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicy$iv1113 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv9, false);
                        int $changed$iv$iv119 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv1111 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv119 = ComposedModifierKt.materializeModifier($composer, modifier$iv119);
                        constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv119 = (($changed$iv$iv119 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function10 = constructor8;
                            $composer.createNode(function10);
                        } else {
                            function10 = constructor8;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv1113, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv1111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash119 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                        $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash119);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv119, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2118 = ($changed$iv$iv$iv119 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance10 = BoxScopeInstance.INSTANCE;
                        int i2119 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                        Modifier modifierM1079size3ABfNKs4 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                        Brush.Companion companion4 = Brush.INSTANCE;
                        long auroraAmber7 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs4, companion4.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber7, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber7) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber7) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber7) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber7) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                        Modifier modifierClip9 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                        long auroraAmber8 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        Modifier modifierM600borderxT4_qwU4 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip9, Color.m4532copywmQWz5c(auroraAmber8, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber8) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber8) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber8) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber8) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                        $composer.startReplaceGroup(-529441171);
                        ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                        invalid$iv = $composer.changed(z2);
                        Object it$iv7 = $composer.rememberedValue();
                        if (!invalid$iv) {
                        }
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                        $composer.endReplaceGroup();
                        Modifier modifierSemantics$default4 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU4, false, (Function1) value$iv, 1, null);
                        boolean zCanTalk4 = CoverShellPolicy.INSTANCE.canTalk(z2);
                        $composer.startReplaceGroup(-529427629);
                        ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                        invalid$iv2 = $composer.changedInstance(context);
                        Object it$iv8 = $composer.rememberedValue();
                        if (!invalid$iv2) {
                        }
                        value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                            }
                        };
                        $composer.updateRememberedValue(value$iv2);
                        $composer.endReplaceGroup();
                        Modifier modifier$iv1110 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default4, zCanTalk4, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                        Alignment contentAlignment$iv10 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv1114 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv10, false);
                        int $changed$iv$iv1110 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv1112 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv1110 = ComposedModifierKt.materializeModifier($composer, modifier$iv1110);
                        constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv1110 = (($changed$iv$iv1110 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function11 = constructor9;
                            $composer.createNode(function11);
                        } else {
                            function11 = constructor9;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv1114, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv1112, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1110 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                        $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash1110);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv1110, ComposeUiNode.INSTANCE.getSetModifier());
                        int i21110 = ($changed$iv$iv$iv1110 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance11 = BoxScopeInstance.INSTANCE;
                        int i21111 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                        Modifier modifierClip10 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                        if (z2) {
                            $composer.startReplaceGroup(-617963319);
                            ComposerKt.sourceInformation($composer, "285@11805L11");
                            auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        } else {
                            $composer.startReplaceGroup(-617962771);
                            ComposerKt.sourceInformation($composer, "285@11822L15");
                            auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                        }
                        $composer.endReplaceGroup();
                        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip10, auroraTextMuted2, null, 2, null), $composer, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                        if (z2) {
                            str = "TALK · tap orb or power";
                        } else {
                            str = "Offline";
                        }
                        TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        Modifier modifier$iv1111 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Horizontal horizontalAlignment$iv13 = Alignment.INSTANCE.getCenterHorizontally();
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv13 = Arrangement.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv1115 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv13, horizontalAlignment$iv13, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                        int $changed$iv$iv1111 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv1113 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv1111 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111);
                        constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv1111 = (($changed$iv$iv1111 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            $composer.createNode(constructor10);
                        } else {
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv1115, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv1113, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                        $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash1111);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv1111, ComposeUiNode.INSTANCE.getSetModifier());
                        int i21112 = ($changed$iv$iv$iv1111 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance15 = ColumnScopeInstance.INSTANCE;
                        int i21113 = ((390 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                        Modifier modifier$iv1112 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Arrangement.Horizontal horizontalArrangement$iv10 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Alignment.Vertical verticalAlignment$iv10 = Alignment.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv1116 = RowKt.rowMeasurePolicy(horizontalArrangement$iv10, verticalAlignment$iv10, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv1112 = (54 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv1114 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv1112 = ComposedModifierKt.materializeModifier($composer, modifier$iv1112);
                        constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv1112 = (($changed$iv$iv1112 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function12 = constructor11;
                            $composer.createNode(function12);
                        } else {
                            function12 = constructor11;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv1116, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv1114, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1112 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                        $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash1112);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv1112, ComposeUiNode.INSTANCE.getSetModifier());
                        int i21114 = ($changed$iv$iv$iv1112 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i21115 = ((54 >> 6) & 112) | 6;
                        RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2418 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                        CoverShellPolicy coverShellPolicy4 = CoverShellPolicy.INSTANCE;
                        if (auroraSession != null) {
                            z = true;
                        } else {
                            z = false;
                        }
                        RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy4.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2418, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                        RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2418, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                        TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
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
                    measurePolicy$iv3 = measurePolicy$iv2;
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash6);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv6, ComposeUiNode.INSTANCE.getSetModifier());
                    int i120 = ($changed$iv$iv$iv6 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                    int i121 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 762306201, "C139@5911L223,145@6151L28,146@6196L270:RazrCoverShell.kt#z0j3je");
                    Modifier modifierClip11 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(7)), RoundedCornerShapeKt.getCircleShape());
                    if (z2) {
                        $composer.startReplaceGroup(-529593847);
                        ComposerKt.sourceInformation($composer, "143@6083L10");
                        auroraTextMuted = AuroraColorSchemeKt.getAuroraLive($composer, 0);
                    } else {
                        $composer.startReplaceGroup(-529593330);
                        ComposerKt.sourceInformation($composer, "143@6099L15");
                        auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                    }
                    $composer.endReplaceGroup();
                    BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip11, auroraTextMuted, null, 2, null), $composer, 0);
                    SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                    if (z2) {
                        $composer.startReplaceGroup(-529587638);
                        ComposerKt.sourceInformation($composer, "148@6277L11");
                        auroraText = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    } else {
                        $composer.startReplaceGroup(-529587095);
                        ComposerKt.sourceInformation($composer, "148@6294L10");
                        auroraText = AuroraColorSchemeKt.getAuroraText($composer, 0);
                    }
                    $composer.endReplaceGroup();
                    TextKt.m3067Text4IGK_g(str2, (Modifier) null, auroraText, TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(12)), $composer, 6);
                    Modifier modifier$iv21 = PaddingKt.m1035paddingVpY3zN4(BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(16))), AuroraColorSchemeKt.getAuroraSurface($composer, 0), null, 2, null), Dp.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer, 0), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(16))), Dp.m7016constructorimpl(14), Dp.m7016constructorimpl(13));
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv11 = Alignment.INSTANCE.getTopStart();
                    measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv11, false);
                    int $changed$iv$iv21 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv21 = ComposedModifierKt.materializeModifier($composer, modifier$iv21);
                    constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv21 = (($changed$iv$iv21 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function6 = constructor4;
                        $composer.createNode(function6);
                    } else {
                        function6 = constructor4;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash21 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                        measurePolicy$iv5 = measurePolicy$iv4;
                        localMap$iv$iv2 = localMap$iv$iv;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv21, ComposeUiNode.INSTANCE.getSetModifier());
                        int i1117 = ($changed$iv$iv$iv21 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance12 = BoxScopeInstance.INSTANCE;
                        int i1118 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -410226970, "C167@6898L2781:RazrCoverShell.kt#z0j3je");
                        Modifier modifier$iv22 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv14 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv14 = Alignment.INSTANCE.getStart();
                        int $i$f$Column7 = ((6 >> 3) & 14) | ((6 >> 3) & 112);
                        MeasurePolicy measurePolicy$iv1117 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv14, horizontalAlignment$iv14, $composer, $i$f$Column7);
                        int $changed$iv$iv22 = (6 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv22 = ComposedModifierKt.materializeModifier($composer, modifier$iv22);
                        constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv22 = (($changed$iv$iv22 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function7 = constructor5;
                            $composer.createNode(function7);
                        } else {
                            function7 = constructor5;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv5 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv1117, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash22 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting()) {
                            localMap$iv$iv4 = localMap$iv$iv3;
                            if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                            }
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv22, ComposeUiNode.INSTANCE.getSetModifier());
                            int i1119 = ($changed$iv$iv$iv22 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance16 = ColumnScopeInstance.INSTANCE;
                            int i11110 = ((6 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                            TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                            TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                            $composer.startReplaceGroup(-529544623);
                            ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                            if (!StringsKt.isBlank(str4)) {
                                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                                TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                            }
                            $composer.endReplaceGroup();
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                            BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                            Modifier modifier$iv1113 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Alignment.Vertical verticalAlignment$iv11 = Alignment.INSTANCE.getCenterVertically();
                            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                            Arrangement.Horizontal horizontalArrangement$iv11 = Arrangement.INSTANCE.getStart();
                            int $changed$iv5 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                            MeasurePolicy measurePolicy$iv1118 = RowKt.rowMeasurePolicy(horizontalArrangement$iv11, verticalAlignment$iv11, $composer, $changed$iv5);
                            int $changed$iv$iv1113 = (390 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv1115 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv1113 = ComposedModifierKt.materializeModifier($composer, modifier$iv1113);
                            constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv1113 = (($changed$iv$iv1113 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function8 = constructor6;
                                $composer.createNode(function8);
                            } else {
                                function8 = constructor6;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv1118, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv1115, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1113 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                            $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash1113);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv1113, ComposeUiNode.INSTANCE.getSetModifier());
                            int i11111 = ($changed$iv$iv$iv1113 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                            int i11112 = ((390 >> 6) & 112) | 6;
                            $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                            $composer.startReplaceGroup(-618069607);
                            ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                            if (uiState.getSelectedModel() != null) {
                                TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                                SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                            }
                            $composer.endReplaceGroup();
                            TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                            $composer.startReplaceGroup(-618043174);
                            ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                            if (uiState.getSending()) {
                                SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                                TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                            }
                            $composer.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                            Alignment.Horizontal horizontalAlignment$iv15 = Alignment.INSTANCE.getCenterHorizontally();
                            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                            Modifier modifier$iv1114 = Modifier.INSTANCE;
                            Arrangement.Vertical verticalArrangement$iv15 = Arrangement.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv1119 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv15, horizontalAlignment$iv15, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            int $changed$iv$iv1114 = (384 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv1116 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv1114 = ComposedModifierKt.materializeModifier($composer, modifier$iv1114);
                            constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                            int $i$f$Column8 = $changed$iv$iv1114 << 6;
                            int $changed$iv$iv$iv1114 = ($i$f$Column8 & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function9 = constructor7;
                                $composer.createNode(function9);
                            } else {
                                function9 = constructor7;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv1119, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv1116, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1114 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                            $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash1114);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv1114, ComposeUiNode.INSTANCE.getSetModifier());
                            int i11113 = ($changed$iv$iv$iv1114 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance17 = ColumnScopeInstance.INSTANCE;
                            int i21116 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                            Alignment contentAlignment$iv12 = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            Modifier modifier$iv1115 = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv11110 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv12, false);
                            int $changed$iv$iv1115 = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv1117 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv1115 = ComposedModifierKt.materializeModifier($composer, modifier$iv1115);
                            constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv1115 = (($changed$iv$iv1115 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function10 = constructor8;
                                $composer.createNode(function10);
                            } else {
                                function10 = constructor8;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv11110, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv1117, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1115 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                            $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash1115);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv1115, ComposeUiNode.INSTANCE.getSetModifier());
                            int i21117 = ($changed$iv$iv$iv1115 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance13 = BoxScopeInstance.INSTANCE;
                            int i21118 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                            Modifier modifierM1079size3ABfNKs5 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                            Brush.Companion companion5 = Brush.INSTANCE;
                            long auroraAmber9 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                            BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs5, companion5.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber9, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber9) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber9) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber9) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber9) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                            Modifier modifierClip12 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                            long auroraAmber10 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                            Modifier modifierM600borderxT4_qwU5 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip12, Color.m4532copywmQWz5c(auroraAmber10, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber10) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber10) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber10) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber10) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                            $composer.startReplaceGroup(-529441171);
                            ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                            invalid$iv = $composer.changed(z2);
                            Object it$iv9 = $composer.rememberedValue();
                            if (!invalid$iv) {
                            }
                            value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                            $composer.endReplaceGroup();
                            Modifier modifierSemantics$default5 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU5, false, (Function1) value$iv, 1, null);
                            boolean zCanTalk5 = CoverShellPolicy.INSTANCE.canTalk(z2);
                            $composer.startReplaceGroup(-529427629);
                            ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                            invalid$iv2 = $composer.changedInstance(context);
                            Object it$iv10 = $composer.rememberedValue();
                            if (!invalid$iv2) {
                            }
                            value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                                }
                            };
                            $composer.updateRememberedValue(value$iv2);
                            $composer.endReplaceGroup();
                            Modifier modifier$iv1116 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default5, zCanTalk5, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                            Alignment contentAlignment$iv13 = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv11111 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv13, false);
                            int $changed$iv$iv1116 = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv1118 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv1116 = ComposedModifierKt.materializeModifier($composer, modifier$iv1116);
                            constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv1116 = (($changed$iv$iv1116 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function11 = constructor9;
                                $composer.createNode(function11);
                            } else {
                                function11 = constructor9;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv11111, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv1118, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1116 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                            $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash1116);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv1116, ComposeUiNode.INSTANCE.getSetModifier());
                            int i21119 = ($changed$iv$iv$iv1116 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance14 = BoxScopeInstance.INSTANCE;
                            int i211110 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                            Modifier modifierClip13 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                            if (z2) {
                                $composer.startReplaceGroup(-617963319);
                                ComposerKt.sourceInformation($composer, "285@11805L11");
                                auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                            } else {
                                $composer.startReplaceGroup(-617962771);
                                ComposerKt.sourceInformation($composer, "285@11822L15");
                                auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                            }
                            $composer.endReplaceGroup();
                            BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip13, auroraTextMuted2, null, 2, null), $composer, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                            if (z2) {
                                str = "TALK · tap orb or power";
                            } else {
                                str = "Offline";
                            }
                            TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            Modifier modifier$iv1117 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Alignment.Horizontal horizontalAlignment$iv16 = Alignment.INSTANCE.getCenterHorizontally();
                            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv16 = Arrangement.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv11112 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv16, horizontalAlignment$iv16, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                            int $changed$iv$iv1117 = (390 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv1119 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv1117 = ComposedModifierKt.materializeModifier($composer, modifier$iv1117);
                            constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv1117 = (($changed$iv$iv1117 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                $composer.createNode(constructor10);
                            } else {
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv11112, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv1119, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1117 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                            $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash1117);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv1117, ComposeUiNode.INSTANCE.getSetModifier());
                            int i211111 = ($changed$iv$iv$iv1117 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance18 = ColumnScopeInstance.INSTANCE;
                            int i211112 = ((390 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                            Modifier modifier$iv1118 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Arrangement.Horizontal horizontalArrangement$iv12 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                            Alignment.Vertical verticalAlignment$iv12 = Alignment.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv11113 = RowKt.rowMeasurePolicy(horizontalArrangement$iv12, verticalAlignment$iv12, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                            int $changed$iv$iv1118 = (54 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv11110 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv1118 = ComposedModifierKt.materializeModifier($composer, modifier$iv1118);
                            constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv1118 = (($changed$iv$iv1118 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function12 = constructor11;
                                $composer.createNode(function12);
                            } else {
                                function12 = constructor11;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv11113, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv11110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1118 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                            $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash1118);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv1118, ComposeUiNode.INSTANCE.getSetModifier());
                            int i211113 = ($changed$iv$iv$iv1118 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                            int i211114 = ((54 >> 6) & 112) | 6;
                            RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2419 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                            CoverShellPolicy coverShellPolicy5 = CoverShellPolicy.INSTANCE;
                            if (auroraSession != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy5.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2419, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                            RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u2419, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                            TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
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
                        localMap$iv$iv4 = localMap$iv$iv3;
                        $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                        $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash22);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv22, ComposeUiNode.INSTANCE.getSetModifier());
                        int i11114 = ($changed$iv$iv$iv22 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance19 = ColumnScopeInstance.INSTANCE;
                        int i11115 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                        TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                        TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                        $composer.startReplaceGroup(-529544623);
                        ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                        if (!StringsKt.isBlank(str4)) {
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                            TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                        }
                        $composer.endReplaceGroup();
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                        Modifier modifier$iv1119 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Vertical verticalAlignment$iv13 = Alignment.INSTANCE.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv13 = Arrangement.INSTANCE.getStart();
                        int $changed$iv6 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                        MeasurePolicy measurePolicy$iv11114 = RowKt.rowMeasurePolicy(horizontalArrangement$iv13, verticalAlignment$iv13, $composer, $changed$iv6);
                        int $changed$iv$iv1119 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11111 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv1119 = ComposedModifierKt.materializeModifier($composer, modifier$iv1119);
                        constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv1119 = (($changed$iv$iv1119 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function8 = constructor6;
                            $composer.createNode(function8);
                        } else {
                            function8 = constructor6;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv11114, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv11111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1119 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                        $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash1119);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv1119, ComposeUiNode.INSTANCE.getSetModifier());
                        int i11116 = ($changed$iv$iv$iv1119 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i11117 = ((390 >> 6) & 112) | 6;
                        $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                        $composer.startReplaceGroup(-618069607);
                        ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                        if (uiState.getSelectedModel() != null) {
                            TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                            SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                        }
                        $composer.endReplaceGroup();
                        TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                        $composer.startReplaceGroup(-618043174);
                        ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                        if (uiState.getSending()) {
                            SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                            TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                        }
                        $composer.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                        Alignment.Horizontal horizontalAlignment$iv17 = Alignment.INSTANCE.getCenterHorizontally();
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Modifier modifier$iv11110 = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv17 = Arrangement.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv11115 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv17, horizontalAlignment$iv17, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv11110 = (384 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11112 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11110 = ComposedModifierKt.materializeModifier($composer, modifier$iv11110);
                        constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                        int $i$f$Column9 = $changed$iv$iv11110 << 6;
                        int $changed$iv$iv$iv11110 = ($i$f$Column9 & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function9 = constructor7;
                            $composer.createNode(function9);
                        } else {
                            function9 = constructor7;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv11115, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv11112, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11110 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                        $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash11110);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv11110, ComposeUiNode.INSTANCE.getSetModifier());
                        int i11118 = ($changed$iv$iv$iv11110 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance110 = ColumnScopeInstance.INSTANCE;
                        int i211115 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                        Alignment contentAlignment$iv14 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Modifier modifier$iv11111 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicy$iv11116 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv14, false);
                        int $changed$iv$iv11111 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11113 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11111 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111);
                        constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv11111 = (($changed$iv$iv11111 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function10 = constructor8;
                            $composer.createNode(function10);
                        } else {
                            function10 = constructor8;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv11116, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv11113, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                        $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash11111);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv11111, ComposeUiNode.INSTANCE.getSetModifier());
                        int i211116 = ($changed$iv$iv$iv11111 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance15 = BoxScopeInstance.INSTANCE;
                        int i211117 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                        Modifier modifierM1079size3ABfNKs6 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                        Brush.Companion companion6 = Brush.INSTANCE;
                        long auroraAmber11 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs6, companion6.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber11, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber11) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber11) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber11) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber11) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                        Modifier modifierClip14 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                        long auroraAmber12 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        Modifier modifierM600borderxT4_qwU6 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip14, Color.m4532copywmQWz5c(auroraAmber12, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber12) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber12) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber12) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber12) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                        $composer.startReplaceGroup(-529441171);
                        ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                        invalid$iv = $composer.changed(z2);
                        Object it$iv11 = $composer.rememberedValue();
                        if (!invalid$iv) {
                        }
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                        $composer.endReplaceGroup();
                        Modifier modifierSemantics$default6 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU6, false, (Function1) value$iv, 1, null);
                        boolean zCanTalk6 = CoverShellPolicy.INSTANCE.canTalk(z2);
                        $composer.startReplaceGroup(-529427629);
                        ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                        invalid$iv2 = $composer.changedInstance(context);
                        Object it$iv12 = $composer.rememberedValue();
                        if (!invalid$iv2) {
                        }
                        value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                            }
                        };
                        $composer.updateRememberedValue(value$iv2);
                        $composer.endReplaceGroup();
                        Modifier modifier$iv11112 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default6, zCanTalk6, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                        Alignment contentAlignment$iv15 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv11117 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv15, false);
                        int $changed$iv$iv11112 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11114 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11112 = ComposedModifierKt.materializeModifier($composer, modifier$iv11112);
                        constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv11112 = (($changed$iv$iv11112 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function11 = constructor9;
                            $composer.createNode(function11);
                        } else {
                            function11 = constructor9;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv11117, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv11114, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11112 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                        $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash11112);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv11112, ComposeUiNode.INSTANCE.getSetModifier());
                        int i211118 = ($changed$iv$iv$iv11112 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance16 = BoxScopeInstance.INSTANCE;
                        int i211119 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                        Modifier modifierClip15 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                        if (z2) {
                            $composer.startReplaceGroup(-617963319);
                            ComposerKt.sourceInformation($composer, "285@11805L11");
                            auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        } else {
                            $composer.startReplaceGroup(-617962771);
                            ComposerKt.sourceInformation($composer, "285@11822L15");
                            auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                        }
                        $composer.endReplaceGroup();
                        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip15, auroraTextMuted2, null, 2, null), $composer, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                        if (z2) {
                            str = "TALK · tap orb or power";
                        } else {
                            str = "Offline";
                        }
                        TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        Modifier modifier$iv11113 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Horizontal horizontalAlignment$iv18 = Alignment.INSTANCE.getCenterHorizontally();
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv18 = Arrangement.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv11118 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv18, horizontalAlignment$iv18, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                        int $changed$iv$iv11113 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11115 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11113 = ComposedModifierKt.materializeModifier($composer, modifier$iv11113);
                        constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv11113 = (($changed$iv$iv11113 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            $composer.createNode(constructor10);
                        } else {
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv11118, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv11115, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11113 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                        $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash11113);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv11113, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2111110 = ($changed$iv$iv$iv11113 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance111 = ColumnScopeInstance.INSTANCE;
                        int i2111111 = ((390 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                        Modifier modifier$iv11114 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Arrangement.Horizontal horizontalArrangement$iv14 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Alignment.Vertical verticalAlignment$iv14 = Alignment.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv11119 = RowKt.rowMeasurePolicy(horizontalArrangement$iv14, verticalAlignment$iv14, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv11114 = (54 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11116 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11114 = ComposedModifierKt.materializeModifier($composer, modifier$iv11114);
                        constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv11114 = (($changed$iv$iv11114 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function12 = constructor11;
                            $composer.createNode(function12);
                        } else {
                            function12 = constructor11;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv11119, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv11116, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11114 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                        $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash11114);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv11114, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2111112 = ($changed$iv$iv$iv11114 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i2111113 = ((54 >> 6) & 112) | 6;
                        RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24110 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                        CoverShellPolicy coverShellPolicy6 = CoverShellPolicy.INSTANCE;
                        if (auroraSession != null) {
                            z = true;
                        } else {
                            z = false;
                        }
                        RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy6.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24110, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                        RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24110, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                        TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
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
                    measurePolicy$iv5 = measurePolicy$iv4;
                    localMap$iv$iv2 = localMap$iv$iv;
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash21);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv21, ComposeUiNode.INSTANCE.getSetModifier());
                    int i11119 = ($changed$iv$iv$iv21 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance17 = BoxScopeInstance.INSTANCE;
                    int i11120 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -410226970, "C167@6898L2781:RazrCoverShell.kt#z0j3je");
                    Modifier modifier$iv23 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv19 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv19 = Alignment.INSTANCE.getStart();
                    int $i$f$Column10 = ((6 >> 3) & 14) | ((6 >> 3) & 112);
                    MeasurePolicy measurePolicy$iv11120 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv19, horizontalAlignment$iv19, $composer, $i$f$Column10);
                    int $changed$iv$iv23 = (6 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv23 = ComposedModifierKt.materializeModifier($composer, modifier$iv23);
                    constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv23 = (($changed$iv$iv23 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function7 = constructor5;
                        $composer.createNode(function7);
                    } else {
                        function7 = constructor5;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv5 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv11120, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash23 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting()) {
                        localMap$iv$iv4 = localMap$iv$iv3;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv23, ComposeUiNode.INSTANCE.getSetModifier());
                        int i111110 = ($changed$iv$iv$iv23 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance112 = ColumnScopeInstance.INSTANCE;
                        int i111111 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                        TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                        TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                        $composer.startReplaceGroup(-529544623);
                        ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                        if (!StringsKt.isBlank(str4)) {
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                            TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                        }
                        $composer.endReplaceGroup();
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                        Modifier modifier$iv11115 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Vertical verticalAlignment$iv15 = Alignment.INSTANCE.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv15 = Arrangement.INSTANCE.getStart();
                        int $changed$iv7 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                        MeasurePolicy measurePolicy$iv111110 = RowKt.rowMeasurePolicy(horizontalArrangement$iv15, verticalAlignment$iv15, $composer, $changed$iv7);
                        int $changed$iv$iv11115 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11117 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11115 = ComposedModifierKt.materializeModifier($composer, modifier$iv11115);
                        constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv11115 = (($changed$iv$iv11115 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function8 = constructor6;
                            $composer.createNode(function8);
                        } else {
                            function8 = constructor6;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv111110, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv11117, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11115 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                        $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash11115);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv11115, ComposeUiNode.INSTANCE.getSetModifier());
                        int i111112 = ($changed$iv$iv$iv11115 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i111113 = ((390 >> 6) & 112) | 6;
                        $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                        $composer.startReplaceGroup(-618069607);
                        ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                        if (uiState.getSelectedModel() != null) {
                            TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                            SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                        }
                        $composer.endReplaceGroup();
                        TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                        $composer.startReplaceGroup(-618043174);
                        ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                        if (uiState.getSending()) {
                            SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                            TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                        }
                        $composer.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                        Alignment.Horizontal horizontalAlignment$iv110 = Alignment.INSTANCE.getCenterHorizontally();
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Modifier modifier$iv11116 = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv110 = Arrangement.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv111111 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv110, horizontalAlignment$iv110, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv11116 = (384 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11118 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11116 = ComposedModifierKt.materializeModifier($composer, modifier$iv11116);
                        constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                        int $i$f$Column11 = $changed$iv$iv11116 << 6;
                        int $changed$iv$iv$iv11116 = ($i$f$Column11 & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function9 = constructor7;
                            $composer.createNode(function9);
                        } else {
                            function9 = constructor7;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv111111, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv11118, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11116 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                        $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash11116);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv11116, ComposeUiNode.INSTANCE.getSetModifier());
                        int i111114 = ($changed$iv$iv$iv11116 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance113 = ColumnScopeInstance.INSTANCE;
                        int i2111114 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                        Alignment contentAlignment$iv16 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Modifier modifier$iv11117 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicy$iv111112 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv16, false);
                        int $changed$iv$iv11117 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11119 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11117 = ComposedModifierKt.materializeModifier($composer, modifier$iv11117);
                        constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv11117 = (($changed$iv$iv11117 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function10 = constructor8;
                            $composer.createNode(function10);
                        } else {
                            function10 = constructor8;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv111112, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv11119, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11117 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                        $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash11117);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv11117, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2111115 = ($changed$iv$iv$iv11117 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance18 = BoxScopeInstance.INSTANCE;
                        int i2111116 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                        Modifier modifierM1079size3ABfNKs7 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                        Brush.Companion companion7 = Brush.INSTANCE;
                        long auroraAmber13 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs7, companion7.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber13, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber13) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber13) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber13) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber13) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                        Modifier modifierClip16 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                        long auroraAmber14 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        Modifier modifierM600borderxT4_qwU7 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip16, Color.m4532copywmQWz5c(auroraAmber14, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber14) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber14) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber14) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber14) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                        $composer.startReplaceGroup(-529441171);
                        ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                        invalid$iv = $composer.changed(z2);
                        Object it$iv13 = $composer.rememberedValue();
                        if (!invalid$iv) {
                        }
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                        $composer.endReplaceGroup();
                        Modifier modifierSemantics$default7 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU7, false, (Function1) value$iv, 1, null);
                        boolean zCanTalk7 = CoverShellPolicy.INSTANCE.canTalk(z2);
                        $composer.startReplaceGroup(-529427629);
                        ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                        invalid$iv2 = $composer.changedInstance(context);
                        Object it$iv14 = $composer.rememberedValue();
                        if (!invalid$iv2) {
                        }
                        value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                            }
                        };
                        $composer.updateRememberedValue(value$iv2);
                        $composer.endReplaceGroup();
                        Modifier modifier$iv11118 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default7, zCanTalk7, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                        Alignment contentAlignment$iv17 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv111113 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv17, false);
                        int $changed$iv$iv11118 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv111110 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11118 = ComposedModifierKt.materializeModifier($composer, modifier$iv11118);
                        constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv11118 = (($changed$iv$iv11118 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function11 = constructor9;
                            $composer.createNode(function11);
                        } else {
                            function11 = constructor9;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv111113, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv111110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11118 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                        $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash11118);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv11118, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2111117 = ($changed$iv$iv$iv11118 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance19 = BoxScopeInstance.INSTANCE;
                        int i2111118 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                        Modifier modifierClip17 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                        if (z2) {
                            $composer.startReplaceGroup(-617963319);
                            ComposerKt.sourceInformation($composer, "285@11805L11");
                            auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        } else {
                            $composer.startReplaceGroup(-617962771);
                            ComposerKt.sourceInformation($composer, "285@11822L15");
                            auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                        }
                        $composer.endReplaceGroup();
                        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip17, auroraTextMuted2, null, 2, null), $composer, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                        if (z2) {
                            str = "TALK · tap orb or power";
                        } else {
                            str = "Offline";
                        }
                        TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        Modifier modifier$iv11119 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Horizontal horizontalAlignment$iv111 = Alignment.INSTANCE.getCenterHorizontally();
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv111 = Arrangement.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv111114 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv111, horizontalAlignment$iv111, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                        int $changed$iv$iv11119 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv111111 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11119 = ComposedModifierKt.materializeModifier($composer, modifier$iv11119);
                        constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv11119 = (($changed$iv$iv11119 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            $composer.createNode(constructor10);
                        } else {
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv111114, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv111111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11119 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                        $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash11119);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv11119, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2111119 = ($changed$iv$iv$iv11119 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance114 = ColumnScopeInstance.INSTANCE;
                        int i21111110 = ((390 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                        Modifier modifier$iv111110 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Arrangement.Horizontal horizontalArrangement$iv16 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Alignment.Vertical verticalAlignment$iv16 = Alignment.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv111115 = RowKt.rowMeasurePolicy(horizontalArrangement$iv16, verticalAlignment$iv16, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv111110 = (54 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv111112 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv111110 = ComposedModifierKt.materializeModifier($composer, modifier$iv111110);
                        constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv111110 = (($changed$iv$iv111110 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function12 = constructor11;
                            $composer.createNode(function12);
                        } else {
                            function12 = constructor11;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv111115, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv111112, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111110 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                        $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash111110);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv111110, ComposeUiNode.INSTANCE.getSetModifier());
                        int i21111111 = ($changed$iv$iv$iv111110 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i21111112 = ((54 >> 6) & 112) | 6;
                        RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24111 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                        CoverShellPolicy coverShellPolicy7 = CoverShellPolicy.INSTANCE;
                        if (auroraSession != null) {
                            z = true;
                        } else {
                            z = false;
                        }
                        RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy7.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24111, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                        RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24111, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                        TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
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
                    localMap$iv$iv4 = localMap$iv$iv3;
                    $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                    $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash23);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv23, ComposeUiNode.INSTANCE.getSetModifier());
                    int i111115 = ($changed$iv$iv$iv23 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance115 = ColumnScopeInstance.INSTANCE;
                    int i111116 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                    TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                    TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                    $composer.startReplaceGroup(-529544623);
                    ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                    if (!StringsKt.isBlank(str4)) {
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                        TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                    }
                    $composer.endReplaceGroup();
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                    BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                    Modifier modifier$iv111111 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Vertical verticalAlignment$iv17 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv17 = Arrangement.INSTANCE.getStart();
                    int $changed$iv8 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                    MeasurePolicy measurePolicy$iv111116 = RowKt.rowMeasurePolicy(horizontalArrangement$iv17, verticalAlignment$iv17, $composer, $changed$iv8);
                    int $changed$iv$iv111111 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv111113 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111111 = ComposedModifierKt.materializeModifier($composer, modifier$iv111111);
                    constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv111111 = (($changed$iv$iv111111 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function8 = constructor6;
                        $composer.createNode(function8);
                    } else {
                        function8 = constructor6;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv111116, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv111113, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111111 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                    $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash111111);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv111111, ComposeUiNode.INSTANCE.getSetModifier());
                    int i111117 = ($changed$iv$iv$iv111111 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i111118 = ((390 >> 6) & 112) | 6;
                    $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                    $composer.startReplaceGroup(-618069607);
                    ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                    if (uiState.getSelectedModel() != null) {
                        TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                        SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                    }
                    $composer.endReplaceGroup();
                    TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                    $composer.startReplaceGroup(-618043174);
                    ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                    if (uiState.getSending()) {
                        SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                        TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                    }
                    $composer.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                    Alignment.Horizontal horizontalAlignment$iv112 = Alignment.INSTANCE.getCenterHorizontally();
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv111112 = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv112 = Arrangement.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv111117 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv112, horizontalAlignment$iv112, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    int $changed$iv$iv111112 = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv111114 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111112 = ComposedModifierKt.materializeModifier($composer, modifier$iv111112);
                    constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                    int $i$f$Column12 = $changed$iv$iv111112 << 6;
                    int $changed$iv$iv$iv111112 = ($i$f$Column12 & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function9 = constructor7;
                        $composer.createNode(function9);
                    } else {
                        function9 = constructor7;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv111117, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv111114, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111112 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                    $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash111112);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv111112, ComposeUiNode.INSTANCE.getSetModifier());
                    int i111119 = ($changed$iv$iv$iv111112 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance116 = ColumnScopeInstance.INSTANCE;
                    int i21111113 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                    Alignment contentAlignment$iv18 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Modifier modifier$iv111113 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicy$iv111118 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv18, false);
                    int $changed$iv$iv111113 = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv111115 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111113 = ComposedModifierKt.materializeModifier($composer, modifier$iv111113);
                    constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv111113 = (($changed$iv$iv111113 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function10 = constructor8;
                        $composer.createNode(function10);
                    } else {
                        function10 = constructor8;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv111118, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv111115, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111113 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                    $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash111113);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv111113, ComposeUiNode.INSTANCE.getSetModifier());
                    int i21111114 = ($changed$iv$iv$iv111113 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance110 = BoxScopeInstance.INSTANCE;
                    int i21111115 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                    Modifier modifierM1079size3ABfNKs8 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                    Brush.Companion companion8 = Brush.INSTANCE;
                    long auroraAmber15 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs8, companion8.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber15, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber15) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber15) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber15) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber15) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                    Modifier modifierClip18 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                    long auroraAmber16 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    Modifier modifierM600borderxT4_qwU8 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip18, Color.m4532copywmQWz5c(auroraAmber16, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber16) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber16) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber16) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber16) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                    $composer.startReplaceGroup(-529441171);
                    ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                    invalid$iv = $composer.changed(z2);
                    Object it$iv15 = $composer.rememberedValue();
                    if (!invalid$iv) {
                    }
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                    $composer.endReplaceGroup();
                    Modifier modifierSemantics$default8 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU8, false, (Function1) value$iv, 1, null);
                    boolean zCanTalk8 = CoverShellPolicy.INSTANCE.canTalk(z2);
                    $composer.startReplaceGroup(-529427629);
                    ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                    invalid$iv2 = $composer.changedInstance(context);
                    Object it$iv16 = $composer.rememberedValue();
                    if (!invalid$iv2) {
                    }
                    value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                        }
                    };
                    $composer.updateRememberedValue(value$iv2);
                    $composer.endReplaceGroup();
                    Modifier modifier$iv111114 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default8, zCanTalk8, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                    Alignment contentAlignment$iv19 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv111119 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv19, false);
                    int $changed$iv$iv111114 = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv111116 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111114 = ComposedModifierKt.materializeModifier($composer, modifier$iv111114);
                    constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv111114 = (($changed$iv$iv111114 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function11 = constructor9;
                        $composer.createNode(function11);
                    } else {
                        function11 = constructor9;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv111119, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv111116, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111114 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                    $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash111114);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv111114, ComposeUiNode.INSTANCE.getSetModifier());
                    int i21111116 = ($changed$iv$iv$iv111114 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance111 = BoxScopeInstance.INSTANCE;
                    int i21111117 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                    Modifier modifierClip19 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                    if (z2) {
                        $composer.startReplaceGroup(-617963319);
                        ComposerKt.sourceInformation($composer, "285@11805L11");
                        auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    } else {
                        $composer.startReplaceGroup(-617962771);
                        ComposerKt.sourceInformation($composer, "285@11822L15");
                        auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                    }
                    $composer.endReplaceGroup();
                    BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip19, auroraTextMuted2, null, 2, null), $composer, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                    if (z2) {
                        str = "TALK · tap orb or power";
                    } else {
                        str = "Offline";
                    }
                    TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifier$iv111115 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Horizontal horizontalAlignment$iv113 = Alignment.INSTANCE.getCenterHorizontally();
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv113 = Arrangement.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv1111110 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv113, horizontalAlignment$iv113, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    int $changed$iv$iv111115 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv111117 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111115 = ComposedModifierKt.materializeModifier($composer, modifier$iv111115);
                    constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv111115 = (($changed$iv$iv111115 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        $composer.createNode(constructor10);
                    } else {
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv1111110, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv111117, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111115 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                    $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash111115);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv111115, ComposeUiNode.INSTANCE.getSetModifier());
                    int i21111118 = ($changed$iv$iv$iv111115 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance117 = ColumnScopeInstance.INSTANCE;
                    int i21111119 = ((390 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                    Modifier modifier$iv111116 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Arrangement.Horizontal horizontalArrangement$iv18 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                    ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Alignment.Vertical verticalAlignment$iv18 = Alignment.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv1111111 = RowKt.rowMeasurePolicy(horizontalArrangement$iv18, verticalAlignment$iv18, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                    int $changed$iv$iv111116 = (54 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv111118 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111116 = ComposedModifierKt.materializeModifier($composer, modifier$iv111116);
                    constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv111116 = (($changed$iv$iv111116 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function12 = constructor11;
                        $composer.createNode(function12);
                    } else {
                        function12 = constructor11;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv1111111, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv111118, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111116 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                    $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash111116);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv111116, ComposeUiNode.INSTANCE.getSetModifier());
                    int i211111110 = ($changed$iv$iv$iv111116 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i211111111 = ((54 >> 6) & 112) | 6;
                    RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24112 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                    CoverShellPolicy coverShellPolicy8 = CoverShellPolicy.INSTANCE;
                    if (auroraSession != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy8.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24112, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                    RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24112, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                    TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
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
                measurePolicy$iv = measurePolicy$iv8;
                $this$Layout_u24lambda_u240$iv$iv14.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv14));
                $this$Layout_u24lambda_u240$iv$iv14.apply(Integer.valueOf(compositeKeyHash$iv$iv14), setCompositeKeyHash3);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv14, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i30 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i31 = ((390 >> 6) & 112) | 6;
                RowScope $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u244 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -412972733, "C100@4291L19,101@4376L19,96@4144L563,110@4720L29,111@4762L653,129@5428L27,134@5659L13,135@5708L14,131@5536L944:RazrCoverShell.kt#z0j3je");
                Modifier modifierClip20 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(36)), RoundedCornerShapeKt.getCircleShape());
                float fM7016constructorimpl2 = Dp.m7016constructorimpl(1);
                long auroraOutlineStrong2 = AuroraColorSchemeKt.getAuroraOutlineStrong($composer, 0);
                Modifier modifier$iv24 = BackgroundKt.m589backgroundbw27NRU$default(BorderKt.m600borderxT4_qwU(modifierClip20, fM7016constructorimpl2, Color.m4532copywmQWz5c(auroraOutlineStrong2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraOutlineStrong2) : 0.7f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraOutlineStrong2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraOutlineStrong2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraOutlineStrong2) : 0.0f), RoundedCornerShapeKt.getCircleShape()), AuroraColorSchemeKt.getAuroraSurfaceRaised($composer, 0), null, 2, null);
                Alignment contentAlignment$iv20 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv20 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv20, false);
                int $changed$iv$iv24 = (48 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv20 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv24 = ComposedModifierKt.materializeModifier($composer, modifier$iv24);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv24 = (($changed$iv$iv24 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function3 = constructor;
                    $composer.createNode(function3);
                } else {
                    function3 = constructor;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv20, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv20, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash24 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash24);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv24, ComposeUiNode.INSTANCE.getSetModifier());
                int i32 = ($changed$iv$iv$iv24 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance20 = BoxScopeInstance.INSTANCE;
                int i33 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 760878930, "C105@4520L50,104@4483L210:RazrCoverShell.kt#z0j3je");
                ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.ic_launcher_foreground, $composer, 0), "Aurora", SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(28)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, $composer, 432, 120);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Modifier modifier$iv25 = Modifier.INSTANCE;
                Arrangement.Vertical verticalArrangement$iv20 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv20 = Alignment.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv120 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv20, horizontalAlignment$iv20, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv25 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv21 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv25 = ComposedModifierKt.materializeModifier($composer, modifier$iv25);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv25 = (($changed$iv$iv25 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function4 = constructor2;
                    $composer.createNode(function4);
                } else {
                    function4 = constructor2;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv120, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv21, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash25 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash25);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv25, ComposeUiNode.INSTANCE.getSetModifier());
                int i34 = ($changed$iv$iv$iv25 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance20 = ColumnScopeInstance.INSTANCE;
                int i122 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 761193022, "C114@4859L15,112@4787L295,122@5186L15,120@5099L302:RazrCoverShell.kt#z0j3je");
                TextKt.m3067Text4IGK_g("AURORA · COVER", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                TextKt.m3067Text4IGK_g(BuildConfig.HERMES_DISPLAY_NAME, (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 3120, 120754);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                Modifier modifier$iv26 = PaddingKt.m1035paddingVpY3zN4(BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.RoundedCornerShape(50)), AuroraColorSchemeKt.getAuroraSurface($composer, 0), null, 2, null), Dp.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer, 0), RoundedCornerShapeKt.RoundedCornerShape(50)), Dp.m7016constructorimpl(10), Dp.m7016constructorimpl(5));
                Alignment.Vertical verticalAlignment$iv19 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv19 = Arrangement.INSTANCE.getStart();
                measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv19, verticalAlignment$iv19, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                int $changed$iv$iv26 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv120 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv26 = ComposedModifierKt.materializeModifier($composer, modifier$iv26);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv26 = (($changed$iv$iv26 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function5 = constructor3;
                    $composer.createNode(function5);
                } else {
                    function5 = constructor3;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv120, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash26 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    measurePolicy$iv3 = measurePolicy$iv2;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv26, ComposeUiNode.INSTANCE.getSetModifier());
                    int i123 = ($changed$iv$iv$iv26 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                    int i124 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 762306201, "C139@5911L223,145@6151L28,146@6196L270:RazrCoverShell.kt#z0j3je");
                    Modifier modifierClip110 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(7)), RoundedCornerShapeKt.getCircleShape());
                    if (z2) {
                        $composer.startReplaceGroup(-529593847);
                        ComposerKt.sourceInformation($composer, "143@6083L10");
                        auroraTextMuted = AuroraColorSchemeKt.getAuroraLive($composer, 0);
                    } else {
                        $composer.startReplaceGroup(-529593330);
                        ComposerKt.sourceInformation($composer, "143@6099L15");
                        auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                    }
                    $composer.endReplaceGroup();
                    BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip110, auroraTextMuted, null, 2, null), $composer, 0);
                    SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                    if (z2) {
                        $composer.startReplaceGroup(-529587638);
                        ComposerKt.sourceInformation($composer, "148@6277L11");
                        auroraText = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    } else {
                        $composer.startReplaceGroup(-529587095);
                        ComposerKt.sourceInformation($composer, "148@6294L10");
                        auroraText = AuroraColorSchemeKt.getAuroraText($composer, 0);
                    }
                    $composer.endReplaceGroup();
                    TextKt.m3067Text4IGK_g(str2, (Modifier) null, auroraText, TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(12)), $composer, 6);
                    Modifier modifier$iv27 = PaddingKt.m1035paddingVpY3zN4(BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(16))), AuroraColorSchemeKt.getAuroraSurface($composer, 0), null, 2, null), Dp.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer, 0), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(16))), Dp.m7016constructorimpl(14), Dp.m7016constructorimpl(13));
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv110 = Alignment.INSTANCE.getTopStart();
                    measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv110, false);
                    int $changed$iv$iv27 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv27 = ComposedModifierKt.materializeModifier($composer, modifier$iv27);
                    constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv27 = (($changed$iv$iv27 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function6 = constructor4;
                        $composer.createNode(function6);
                    } else {
                        function6 = constructor4;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash27 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                        measurePolicy$iv5 = measurePolicy$iv4;
                        localMap$iv$iv2 = localMap$iv$iv;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv27, ComposeUiNode.INSTANCE.getSetModifier());
                        int i111120 = ($changed$iv$iv$iv27 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance112 = BoxScopeInstance.INSTANCE;
                        int i11121 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -410226970, "C167@6898L2781:RazrCoverShell.kt#z0j3je");
                        Modifier modifier$iv28 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv114 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv114 = Alignment.INSTANCE.getStart();
                        int $i$f$Column13 = ((6 >> 3) & 14) | ((6 >> 3) & 112);
                        MeasurePolicy measurePolicy$iv11121 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv114, horizontalAlignment$iv114, $composer, $i$f$Column13);
                        int $changed$iv$iv28 = (6 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv28 = ComposedModifierKt.materializeModifier($composer, modifier$iv28);
                        constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv28 = (($changed$iv$iv28 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function7 = constructor5;
                            $composer.createNode(function7);
                        } else {
                            function7 = constructor5;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv5 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv11121, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash28 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting()) {
                            localMap$iv$iv4 = localMap$iv$iv3;
                            if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                            }
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv28, ComposeUiNode.INSTANCE.getSetModifier());
                            int i1111110 = ($changed$iv$iv$iv28 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance118 = ColumnScopeInstance.INSTANCE;
                            int i1111111 = ((6 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                            TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                            TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                            $composer.startReplaceGroup(-529544623);
                            ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                            if (!StringsKt.isBlank(str4)) {
                                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                                TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                            }
                            $composer.endReplaceGroup();
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                            BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                            Modifier modifier$iv111117 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Alignment.Vertical verticalAlignment$iv110 = Alignment.INSTANCE.getCenterVertically();
                            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                            Arrangement.Horizontal horizontalArrangement$iv110 = Arrangement.INSTANCE.getStart();
                            int $changed$iv9 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                            MeasurePolicy measurePolicy$iv1111112 = RowKt.rowMeasurePolicy(horizontalArrangement$iv110, verticalAlignment$iv110, $composer, $changed$iv9);
                            int $changed$iv$iv111117 = (390 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv111119 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv111117 = ComposedModifierKt.materializeModifier($composer, modifier$iv111117);
                            constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv111117 = (($changed$iv$iv111117 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function8 = constructor6;
                                $composer.createNode(function8);
                            } else {
                                function8 = constructor6;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv1111112, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv111119, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111117 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                            $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash111117);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv111117, ComposeUiNode.INSTANCE.getSetModifier());
                            int i1111112 = ($changed$iv$iv$iv111117 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                            int i1111113 = ((390 >> 6) & 112) | 6;
                            $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                            $composer.startReplaceGroup(-618069607);
                            ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                            if (uiState.getSelectedModel() != null) {
                                TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                                SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                            }
                            $composer.endReplaceGroup();
                            TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                            $composer.startReplaceGroup(-618043174);
                            ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                            if (uiState.getSending()) {
                                SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                                TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                            }
                            $composer.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                            Alignment.Horizontal horizontalAlignment$iv115 = Alignment.INSTANCE.getCenterHorizontally();
                            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                            Modifier modifier$iv111118 = Modifier.INSTANCE;
                            Arrangement.Vertical verticalArrangement$iv115 = Arrangement.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv1111113 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv115, horizontalAlignment$iv115, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            int $changed$iv$iv111118 = (384 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv1111110 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv111118 = ComposedModifierKt.materializeModifier($composer, modifier$iv111118);
                            constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                            int $i$f$Column14 = $changed$iv$iv111118 << 6;
                            int $changed$iv$iv$iv111118 = ($i$f$Column14 & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function9 = constructor7;
                                $composer.createNode(function9);
                            } else {
                                function9 = constructor7;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv1111113, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv1111110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111118 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                            $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash111118);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv111118, ComposeUiNode.INSTANCE.getSetModifier());
                            int i1111114 = ($changed$iv$iv$iv111118 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance119 = ColumnScopeInstance.INSTANCE;
                            int i211111112 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                            Alignment contentAlignment$iv111 = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            Modifier modifier$iv111119 = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv1111114 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv111, false);
                            int $changed$iv$iv111119 = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv1111111 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv111119 = ComposedModifierKt.materializeModifier($composer, modifier$iv111119);
                            constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv111119 = (($changed$iv$iv111119 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function10 = constructor8;
                                $composer.createNode(function10);
                            } else {
                                function10 = constructor8;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv1111114, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv1111111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111119 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                            $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash111119);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv111119, ComposeUiNode.INSTANCE.getSetModifier());
                            int i211111113 = ($changed$iv$iv$iv111119 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance113 = BoxScopeInstance.INSTANCE;
                            int i211111114 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                            Modifier modifierM1079size3ABfNKs9 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                            Brush.Companion companion9 = Brush.INSTANCE;
                            long auroraAmber17 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                            BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs9, companion9.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber17, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber17) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber17) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber17) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber17) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                            Modifier modifierClip111 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                            long auroraAmber18 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                            Modifier modifierM600borderxT4_qwU9 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip111, Color.m4532copywmQWz5c(auroraAmber18, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber18) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber18) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber18) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber18) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                            $composer.startReplaceGroup(-529441171);
                            ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                            invalid$iv = $composer.changed(z2);
                            Object it$iv17 = $composer.rememberedValue();
                            if (!invalid$iv) {
                            }
                            value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                            $composer.endReplaceGroup();
                            Modifier modifierSemantics$default9 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU9, false, (Function1) value$iv, 1, null);
                            boolean zCanTalk9 = CoverShellPolicy.INSTANCE.canTalk(z2);
                            $composer.startReplaceGroup(-529427629);
                            ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                            invalid$iv2 = $composer.changedInstance(context);
                            Object it$iv18 = $composer.rememberedValue();
                            if (!invalid$iv2) {
                            }
                            value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                                }
                            };
                            $composer.updateRememberedValue(value$iv2);
                            $composer.endReplaceGroup();
                            Modifier modifier$iv1111110 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default9, zCanTalk9, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                            Alignment contentAlignment$iv112 = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv1111115 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv112, false);
                            int $changed$iv$iv1111110 = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv1111112 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv1111110 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111110);
                            constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv1111110 = (($changed$iv$iv1111110 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function11 = constructor9;
                                $composer.createNode(function11);
                            } else {
                                function11 = constructor9;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv1111115, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv1111112, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111110 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                            $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash1111110);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv1111110, ComposeUiNode.INSTANCE.getSetModifier());
                            int i211111115 = ($changed$iv$iv$iv1111110 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance114 = BoxScopeInstance.INSTANCE;
                            int i211111116 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                            Modifier modifierClip112 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                            if (z2) {
                                $composer.startReplaceGroup(-617963319);
                                ComposerKt.sourceInformation($composer, "285@11805L11");
                                auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                            } else {
                                $composer.startReplaceGroup(-617962771);
                                ComposerKt.sourceInformation($composer, "285@11822L15");
                                auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                            }
                            $composer.endReplaceGroup();
                            BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip112, auroraTextMuted2, null, 2, null), $composer, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                            if (z2) {
                                str = "TALK · tap orb or power";
                            } else {
                                str = "Offline";
                            }
                            TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            Modifier modifier$iv1111111 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Alignment.Horizontal horizontalAlignment$iv116 = Alignment.INSTANCE.getCenterHorizontally();
                            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv116 = Arrangement.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv1111116 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv116, horizontalAlignment$iv116, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                            int $changed$iv$iv1111111 = (390 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv1111113 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv1111111 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111111);
                            constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv1111111 = (($changed$iv$iv1111111 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                $composer.createNode(constructor10);
                            } else {
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv1111116, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv1111113, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111111 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                            $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash1111111);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv1111111, ComposeUiNode.INSTANCE.getSetModifier());
                            int i211111117 = ($changed$iv$iv$iv1111111 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance1110 = ColumnScopeInstance.INSTANCE;
                            int i211111118 = ((390 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                            Modifier modifier$iv1111112 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Arrangement.Horizontal horizontalArrangement$iv111 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                            Alignment.Vertical verticalAlignment$iv111 = Alignment.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv1111117 = RowKt.rowMeasurePolicy(horizontalArrangement$iv111, verticalAlignment$iv111, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                            int $changed$iv$iv1111112 = (54 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                            CompositionLocalMap localMap$iv$iv1111114 = $composer.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv1111112 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111112);
                            constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv1111112 = (($changed$iv$iv1111112 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                            if (!($composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer.startReusableNode();
                            if ($composer.getInserting()) {
                                function12 = constructor11;
                                $composer.createNode(function12);
                            } else {
                                function12 = constructor11;
                                $composer.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv1111117, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv1111114, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111112 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                            $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash1111112);
                            Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv1111112, ComposeUiNode.INSTANCE.getSetModifier());
                            int i211111119 = ($changed$iv$iv$iv1111112 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                            int i2111111110 = ((54 >> 6) & 112) | 6;
                            RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24113 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                            CoverShellPolicy coverShellPolicy9 = CoverShellPolicy.INSTANCE;
                            if (auroraSession != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy9.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24113, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                            RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24113, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                            TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
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
                        localMap$iv$iv4 = localMap$iv$iv3;
                        $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                        $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash28);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv28, ComposeUiNode.INSTANCE.getSetModifier());
                        int i1111115 = ($changed$iv$iv$iv28 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance1111 = ColumnScopeInstance.INSTANCE;
                        int i1111116 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                        TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                        TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                        $composer.startReplaceGroup(-529544623);
                        ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                        if (!StringsKt.isBlank(str4)) {
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                            TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                        }
                        $composer.endReplaceGroup();
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                        Modifier modifier$iv1111113 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Vertical verticalAlignment$iv112 = Alignment.INSTANCE.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv112 = Arrangement.INSTANCE.getStart();
                        int $changed$iv10 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                        MeasurePolicy measurePolicy$iv1111118 = RowKt.rowMeasurePolicy(horizontalArrangement$iv112, verticalAlignment$iv112, $composer, $changed$iv10);
                        int $changed$iv$iv1111113 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv1111115 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv1111113 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111113);
                        constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv1111113 = (($changed$iv$iv1111113 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function8 = constructor6;
                            $composer.createNode(function8);
                        } else {
                            function8 = constructor6;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv1111118, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv1111115, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111113 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                        $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash1111113);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv1111113, ComposeUiNode.INSTANCE.getSetModifier());
                        int i1111117 = ($changed$iv$iv$iv1111113 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i1111118 = ((390 >> 6) & 112) | 6;
                        $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                        $composer.startReplaceGroup(-618069607);
                        ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                        if (uiState.getSelectedModel() != null) {
                            TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                            SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                        }
                        $composer.endReplaceGroup();
                        TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                        $composer.startReplaceGroup(-618043174);
                        ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                        if (uiState.getSending()) {
                            SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                            TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                        }
                        $composer.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                        Alignment.Horizontal horizontalAlignment$iv117 = Alignment.INSTANCE.getCenterHorizontally();
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Modifier modifier$iv1111114 = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv117 = Arrangement.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv1111119 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv117, horizontalAlignment$iv117, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv1111114 = (384 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv1111116 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv1111114 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111114);
                        constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                        int $i$f$Column15 = $changed$iv$iv1111114 << 6;
                        int $changed$iv$iv$iv1111114 = ($i$f$Column15 & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function9 = constructor7;
                            $composer.createNode(function9);
                        } else {
                            function9 = constructor7;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv1111119, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv1111116, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111114 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                        $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash1111114);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv1111114, ComposeUiNode.INSTANCE.getSetModifier());
                        int i1111119 = ($changed$iv$iv$iv1111114 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance1112 = ColumnScopeInstance.INSTANCE;
                        int i2111111111 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                        Alignment contentAlignment$iv113 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Modifier modifier$iv1111115 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicy$iv11111110 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv113, false);
                        int $changed$iv$iv1111115 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv1111117 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv1111115 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111115);
                        constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv1111115 = (($changed$iv$iv1111115 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function10 = constructor8;
                            $composer.createNode(function10);
                        } else {
                            function10 = constructor8;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv11111110, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv1111117, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111115 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                        $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash1111115);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv1111115, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2111111112 = ($changed$iv$iv$iv1111115 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance115 = BoxScopeInstance.INSTANCE;
                        int i2111111113 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                        Modifier modifierM1079size3ABfNKs10 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                        Brush.Companion companion10 = Brush.INSTANCE;
                        long auroraAmber19 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs10, companion10.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber19, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber19) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber19) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber19) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber19) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                        Modifier modifierClip113 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                        long auroraAmber110 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        Modifier modifierM600borderxT4_qwU10 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip113, Color.m4532copywmQWz5c(auroraAmber110, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber110) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber110) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber110) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber110) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                        $composer.startReplaceGroup(-529441171);
                        ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                        invalid$iv = $composer.changed(z2);
                        Object it$iv19 = $composer.rememberedValue();
                        if (!invalid$iv) {
                        }
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                        $composer.endReplaceGroup();
                        Modifier modifierSemantics$default10 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU10, false, (Function1) value$iv, 1, null);
                        boolean zCanTalk10 = CoverShellPolicy.INSTANCE.canTalk(z2);
                        $composer.startReplaceGroup(-529427629);
                        ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                        invalid$iv2 = $composer.changedInstance(context);
                        Object it$iv110 = $composer.rememberedValue();
                        if (!invalid$iv2) {
                        }
                        value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                            }
                        };
                        $composer.updateRememberedValue(value$iv2);
                        $composer.endReplaceGroup();
                        Modifier modifier$iv1111116 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default10, zCanTalk10, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                        Alignment contentAlignment$iv114 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv11111111 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv114, false);
                        int $changed$iv$iv1111116 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv1111118 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv1111116 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111116);
                        constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv1111116 = (($changed$iv$iv1111116 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function11 = constructor9;
                            $composer.createNode(function11);
                        } else {
                            function11 = constructor9;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv11111111, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv1111118, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111116 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                        $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash1111116);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv1111116, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2111111114 = ($changed$iv$iv$iv1111116 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance116 = BoxScopeInstance.INSTANCE;
                        int i2111111115 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                        Modifier modifierClip114 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                        if (z2) {
                            $composer.startReplaceGroup(-617963319);
                            ComposerKt.sourceInformation($composer, "285@11805L11");
                            auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        } else {
                            $composer.startReplaceGroup(-617962771);
                            ComposerKt.sourceInformation($composer, "285@11822L15");
                            auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                        }
                        $composer.endReplaceGroup();
                        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip114, auroraTextMuted2, null, 2, null), $composer, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                        if (z2) {
                            str = "TALK · tap orb or power";
                        } else {
                            str = "Offline";
                        }
                        TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        Modifier modifier$iv1111117 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Horizontal horizontalAlignment$iv118 = Alignment.INSTANCE.getCenterHorizontally();
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv118 = Arrangement.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv11111112 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv118, horizontalAlignment$iv118, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                        int $changed$iv$iv1111117 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv1111119 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv1111117 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111117);
                        constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv1111117 = (($changed$iv$iv1111117 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            $composer.createNode(constructor10);
                        } else {
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv11111112, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv1111119, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111117 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                        $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash1111117);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv1111117, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2111111116 = ($changed$iv$iv$iv1111117 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance1113 = ColumnScopeInstance.INSTANCE;
                        int i2111111117 = ((390 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                        Modifier modifier$iv1111118 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Arrangement.Horizontal horizontalArrangement$iv113 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Alignment.Vertical verticalAlignment$iv113 = Alignment.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv11111113 = RowKt.rowMeasurePolicy(horizontalArrangement$iv113, verticalAlignment$iv113, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv1111118 = (54 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11111110 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv1111118 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111118);
                        constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv1111118 = (($changed$iv$iv1111118 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function12 = constructor11;
                            $composer.createNode(function12);
                        } else {
                            function12 = constructor11;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv11111113, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv11111110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111118 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                        $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash1111118);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv1111118, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2111111118 = ($changed$iv$iv$iv1111118 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i2111111119 = ((54 >> 6) & 112) | 6;
                        RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24114 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                        CoverShellPolicy coverShellPolicy10 = CoverShellPolicy.INSTANCE;
                        if (auroraSession != null) {
                            z = true;
                        } else {
                            z = false;
                        }
                        RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy10.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24114, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                        RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24114, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                        TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
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
                    measurePolicy$iv5 = measurePolicy$iv4;
                    localMap$iv$iv2 = localMap$iv$iv;
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash27);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv27, ComposeUiNode.INSTANCE.getSetModifier());
                    int i111121 = ($changed$iv$iv$iv27 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance117 = BoxScopeInstance.INSTANCE;
                    int i11122 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -410226970, "C167@6898L2781:RazrCoverShell.kt#z0j3je");
                    Modifier modifier$iv29 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv119 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv119 = Alignment.INSTANCE.getStart();
                    int $i$f$Column16 = ((6 >> 3) & 14) | ((6 >> 3) & 112);
                    MeasurePolicy measurePolicy$iv11122 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv119, horizontalAlignment$iv119, $composer, $i$f$Column16);
                    int $changed$iv$iv29 = (6 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv29 = ComposedModifierKt.materializeModifier($composer, modifier$iv29);
                    constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv29 = (($changed$iv$iv29 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function7 = constructor5;
                        $composer.createNode(function7);
                    } else {
                        function7 = constructor5;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv5 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv11122, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash29 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting()) {
                        localMap$iv$iv4 = localMap$iv$iv3;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv29, ComposeUiNode.INSTANCE.getSetModifier());
                        int i11111110 = ($changed$iv$iv$iv29 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance1114 = ColumnScopeInstance.INSTANCE;
                        int i11111111 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                        TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                        TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                        $composer.startReplaceGroup(-529544623);
                        ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                        if (!StringsKt.isBlank(str4)) {
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                            TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                        }
                        $composer.endReplaceGroup();
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                        Modifier modifier$iv1111119 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Vertical verticalAlignment$iv114 = Alignment.INSTANCE.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv114 = Arrangement.INSTANCE.getStart();
                        int $changed$iv11 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                        MeasurePolicy measurePolicy$iv11111114 = RowKt.rowMeasurePolicy(horizontalArrangement$iv114, verticalAlignment$iv114, $composer, $changed$iv11);
                        int $changed$iv$iv1111119 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11111111 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv1111119 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111119);
                        constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv1111119 = (($changed$iv$iv1111119 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function8 = constructor6;
                            $composer.createNode(function8);
                        } else {
                            function8 = constructor6;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv11111114, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv11111111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111119 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                        $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash1111119);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv1111119, ComposeUiNode.INSTANCE.getSetModifier());
                        int i11111112 = ($changed$iv$iv$iv1111119 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i11111113 = ((390 >> 6) & 112) | 6;
                        $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                        $composer.startReplaceGroup(-618069607);
                        ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                        if (uiState.getSelectedModel() != null) {
                            TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                            SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                        }
                        $composer.endReplaceGroup();
                        TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                        $composer.startReplaceGroup(-618043174);
                        ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                        if (uiState.getSending()) {
                            SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                            TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                        }
                        $composer.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                        Alignment.Horizontal horizontalAlignment$iv1110 = Alignment.INSTANCE.getCenterHorizontally();
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Modifier modifier$iv11111110 = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv1110 = Arrangement.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv11111115 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1110, horizontalAlignment$iv1110, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv11111110 = (384 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11111112 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11111110 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111110);
                        constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                        int $i$f$Column17 = $changed$iv$iv11111110 << 6;
                        int $changed$iv$iv$iv11111110 = ($i$f$Column17 & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function9 = constructor7;
                            $composer.createNode(function9);
                        } else {
                            function9 = constructor7;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv11111115, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv11111112, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111110 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                        $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash11111110);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv11111110, ComposeUiNode.INSTANCE.getSetModifier());
                        int i11111114 = ($changed$iv$iv$iv11111110 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance1115 = ColumnScopeInstance.INSTANCE;
                        int i21111111110 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                        Alignment contentAlignment$iv115 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Modifier modifier$iv11111111 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicy$iv11111116 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv115, false);
                        int $changed$iv$iv11111111 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11111113 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11111111 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111111);
                        constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv11111111 = (($changed$iv$iv11111111 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function10 = constructor8;
                            $composer.createNode(function10);
                        } else {
                            function10 = constructor8;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv11111116, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv11111113, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111111 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                        $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash11111111);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv11111111, ComposeUiNode.INSTANCE.getSetModifier());
                        int i21111111111 = ($changed$iv$iv$iv11111111 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance118 = BoxScopeInstance.INSTANCE;
                        int i21111111112 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                        Modifier modifierM1079size3ABfNKs11 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                        Brush.Companion companion11 = Brush.INSTANCE;
                        long auroraAmber111 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs11, companion11.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber111, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber111) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber111) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber111) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber111) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                        Modifier modifierClip115 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                        long auroraAmber112 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        Modifier modifierM600borderxT4_qwU11 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip115, Color.m4532copywmQWz5c(auroraAmber112, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber112) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber112) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber112) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber112) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                        $composer.startReplaceGroup(-529441171);
                        ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                        invalid$iv = $composer.changed(z2);
                        Object it$iv111 = $composer.rememberedValue();
                        if (!invalid$iv) {
                        }
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                        $composer.endReplaceGroup();
                        Modifier modifierSemantics$default11 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU11, false, (Function1) value$iv, 1, null);
                        boolean zCanTalk11 = CoverShellPolicy.INSTANCE.canTalk(z2);
                        $composer.startReplaceGroup(-529427629);
                        ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                        invalid$iv2 = $composer.changedInstance(context);
                        Object it$iv112 = $composer.rememberedValue();
                        if (!invalid$iv2) {
                        }
                        value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                            }
                        };
                        $composer.updateRememberedValue(value$iv2);
                        $composer.endReplaceGroup();
                        Modifier modifier$iv11111112 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default11, zCanTalk11, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                        Alignment contentAlignment$iv116 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv11111117 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv116, false);
                        int $changed$iv$iv11111112 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11111114 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11111112 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111112);
                        constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv11111112 = (($changed$iv$iv11111112 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function11 = constructor9;
                            $composer.createNode(function11);
                        } else {
                            function11 = constructor9;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv11111117, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv11111114, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111112 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                        $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash11111112);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv11111112, ComposeUiNode.INSTANCE.getSetModifier());
                        int i21111111113 = ($changed$iv$iv$iv11111112 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance119 = BoxScopeInstance.INSTANCE;
                        int i21111111114 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                        Modifier modifierClip116 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                        if (z2) {
                            $composer.startReplaceGroup(-617963319);
                            ComposerKt.sourceInformation($composer, "285@11805L11");
                            auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        } else {
                            $composer.startReplaceGroup(-617962771);
                            ComposerKt.sourceInformation($composer, "285@11822L15");
                            auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                        }
                        $composer.endReplaceGroup();
                        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip116, auroraTextMuted2, null, 2, null), $composer, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                        if (z2) {
                            str = "TALK · tap orb or power";
                        } else {
                            str = "Offline";
                        }
                        TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        Modifier modifier$iv11111113 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Horizontal horizontalAlignment$iv1111 = Alignment.INSTANCE.getCenterHorizontally();
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv1111 = Arrangement.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv11111118 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1111, horizontalAlignment$iv1111, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                        int $changed$iv$iv11111113 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11111115 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11111113 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111113);
                        constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv11111113 = (($changed$iv$iv11111113 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            $composer.createNode(constructor10);
                        } else {
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv11111118, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv11111115, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111113 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                        $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash11111113);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv11111113, ComposeUiNode.INSTANCE.getSetModifier());
                        int i21111111115 = ($changed$iv$iv$iv11111113 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance1116 = ColumnScopeInstance.INSTANCE;
                        int i21111111116 = ((390 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                        Modifier modifier$iv11111114 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Arrangement.Horizontal horizontalArrangement$iv115 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Alignment.Vertical verticalAlignment$iv115 = Alignment.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv11111119 = RowKt.rowMeasurePolicy(horizontalArrangement$iv115, verticalAlignment$iv115, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv11111114 = (54 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv11111116 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv11111114 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111114);
                        constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv11111114 = (($changed$iv$iv11111114 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function12 = constructor11;
                            $composer.createNode(function12);
                        } else {
                            function12 = constructor11;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv11111119, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv11111116, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111114 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                        $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash11111114);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv11111114, ComposeUiNode.INSTANCE.getSetModifier());
                        int i21111111117 = ($changed$iv$iv$iv11111114 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i21111111118 = ((54 >> 6) & 112) | 6;
                        RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24115 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                        CoverShellPolicy coverShellPolicy11 = CoverShellPolicy.INSTANCE;
                        if (auroraSession != null) {
                            z = true;
                        } else {
                            z = false;
                        }
                        RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy11.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24115, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                        RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24115, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                        TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
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
                    localMap$iv$iv4 = localMap$iv$iv3;
                    $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                    $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash29);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv29, ComposeUiNode.INSTANCE.getSetModifier());
                    int i11111115 = ($changed$iv$iv$iv29 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance1117 = ColumnScopeInstance.INSTANCE;
                    int i11111116 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                    TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                    TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                    $composer.startReplaceGroup(-529544623);
                    ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                    if (!StringsKt.isBlank(str4)) {
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                        TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                    }
                    $composer.endReplaceGroup();
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                    BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                    Modifier modifier$iv11111115 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Vertical verticalAlignment$iv116 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv116 = Arrangement.INSTANCE.getStart();
                    int $changed$iv12 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                    MeasurePolicy measurePolicy$iv111111110 = RowKt.rowMeasurePolicy(horizontalArrangement$iv116, verticalAlignment$iv116, $composer, $changed$iv12);
                    int $changed$iv$iv11111115 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv11111117 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv11111115 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111115);
                    constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv11111115 = (($changed$iv$iv11111115 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function8 = constructor6;
                        $composer.createNode(function8);
                    } else {
                        function8 = constructor6;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv111111110, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv11111117, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111115 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                    $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash11111115);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv11111115, ComposeUiNode.INSTANCE.getSetModifier());
                    int i11111117 = ($changed$iv$iv$iv11111115 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i11111118 = ((390 >> 6) & 112) | 6;
                    $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                    $composer.startReplaceGroup(-618069607);
                    ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                    if (uiState.getSelectedModel() != null) {
                        TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                        SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                    }
                    $composer.endReplaceGroup();
                    TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                    $composer.startReplaceGroup(-618043174);
                    ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                    if (uiState.getSending()) {
                        SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                        TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                    }
                    $composer.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                    Alignment.Horizontal horizontalAlignment$iv1112 = Alignment.INSTANCE.getCenterHorizontally();
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv11111116 = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv1112 = Arrangement.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv111111111 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1112, horizontalAlignment$iv1112, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    int $changed$iv$iv11111116 = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv11111118 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv11111116 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111116);
                    constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                    int $i$f$Column18 = $changed$iv$iv11111116 << 6;
                    int $changed$iv$iv$iv11111116 = ($i$f$Column18 & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function9 = constructor7;
                        $composer.createNode(function9);
                    } else {
                        function9 = constructor7;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv111111111, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv11111118, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111116 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                    $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash11111116);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv11111116, ComposeUiNode.INSTANCE.getSetModifier());
                    int i11111119 = ($changed$iv$iv$iv11111116 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance1118 = ColumnScopeInstance.INSTANCE;
                    int i21111111119 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                    Alignment contentAlignment$iv117 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Modifier modifier$iv11111117 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicy$iv111111112 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv117, false);
                    int $changed$iv$iv11111117 = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv11111119 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv11111117 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111117);
                    constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv11111117 = (($changed$iv$iv11111117 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function10 = constructor8;
                        $composer.createNode(function10);
                    } else {
                        function10 = constructor8;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv111111112, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv11111119, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111117 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                    $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash11111117);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv11111117, ComposeUiNode.INSTANCE.getSetModifier());
                    int i211111111110 = ($changed$iv$iv$iv11111117 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance1110 = BoxScopeInstance.INSTANCE;
                    int i211111111111 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                    Modifier modifierM1079size3ABfNKs12 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                    Brush.Companion companion12 = Brush.INSTANCE;
                    long auroraAmber113 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs12, companion12.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber113, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber113) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber113) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber113) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber113) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                    Modifier modifierClip117 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                    long auroraAmber114 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    Modifier modifierM600borderxT4_qwU12 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip117, Color.m4532copywmQWz5c(auroraAmber114, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber114) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber114) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber114) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber114) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                    $composer.startReplaceGroup(-529441171);
                    ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                    invalid$iv = $composer.changed(z2);
                    Object it$iv113 = $composer.rememberedValue();
                    if (!invalid$iv) {
                    }
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                    $composer.endReplaceGroup();
                    Modifier modifierSemantics$default12 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU12, false, (Function1) value$iv, 1, null);
                    boolean zCanTalk12 = CoverShellPolicy.INSTANCE.canTalk(z2);
                    $composer.startReplaceGroup(-529427629);
                    ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                    invalid$iv2 = $composer.changedInstance(context);
                    Object it$iv114 = $composer.rememberedValue();
                    if (!invalid$iv2) {
                    }
                    value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                        }
                    };
                    $composer.updateRememberedValue(value$iv2);
                    $composer.endReplaceGroup();
                    Modifier modifier$iv11111118 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default12, zCanTalk12, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                    Alignment contentAlignment$iv118 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv111111113 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv118, false);
                    int $changed$iv$iv11111118 = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv111111110 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv11111118 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111118);
                    constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv11111118 = (($changed$iv$iv11111118 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function11 = constructor9;
                        $composer.createNode(function11);
                    } else {
                        function11 = constructor9;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv111111113, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv111111110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111118 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                    $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash11111118);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv11111118, ComposeUiNode.INSTANCE.getSetModifier());
                    int i211111111112 = ($changed$iv$iv$iv11111118 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance1111 = BoxScopeInstance.INSTANCE;
                    int i211111111113 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                    Modifier modifierClip118 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                    if (z2) {
                        $composer.startReplaceGroup(-617963319);
                        ComposerKt.sourceInformation($composer, "285@11805L11");
                        auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    } else {
                        $composer.startReplaceGroup(-617962771);
                        ComposerKt.sourceInformation($composer, "285@11822L15");
                        auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                    }
                    $composer.endReplaceGroup();
                    BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip118, auroraTextMuted2, null, 2, null), $composer, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                    if (z2) {
                        str = "TALK · tap orb or power";
                    } else {
                        str = "Offline";
                    }
                    TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifier$iv11111119 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Horizontal horizontalAlignment$iv1113 = Alignment.INSTANCE.getCenterHorizontally();
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv1113 = Arrangement.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv111111114 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1113, horizontalAlignment$iv1113, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    int $changed$iv$iv11111119 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv111111111 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv11111119 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111119);
                    constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv11111119 = (($changed$iv$iv11111119 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        $composer.createNode(constructor10);
                    } else {
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv111111114, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv111111111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111119 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                    $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash11111119);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv11111119, ComposeUiNode.INSTANCE.getSetModifier());
                    int i211111111114 = ($changed$iv$iv$iv11111119 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance1119 = ColumnScopeInstance.INSTANCE;
                    int i211111111115 = ((390 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                    Modifier modifier$iv111111110 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Arrangement.Horizontal horizontalArrangement$iv117 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                    ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Alignment.Vertical verticalAlignment$iv117 = Alignment.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv111111115 = RowKt.rowMeasurePolicy(horizontalArrangement$iv117, verticalAlignment$iv117, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                    int $changed$iv$iv111111110 = (54 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv111111112 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111111110 = ComposedModifierKt.materializeModifier($composer, modifier$iv111111110);
                    constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv111111110 = (($changed$iv$iv111111110 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function12 = constructor11;
                        $composer.createNode(function12);
                    } else {
                        function12 = constructor11;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv111111115, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv111111112, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111111110 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                    $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash111111110);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv111111110, ComposeUiNode.INSTANCE.getSetModifier());
                    int i211111111116 = ($changed$iv$iv$iv111111110 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i211111111117 = ((54 >> 6) & 112) | 6;
                    RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24116 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                    CoverShellPolicy coverShellPolicy12 = CoverShellPolicy.INSTANCE;
                    if (auroraSession != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy12.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24116, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                    RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24116, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                    TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
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
                measurePolicy$iv3 = measurePolicy$iv2;
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash26);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv26, ComposeUiNode.INSTANCE.getSetModifier());
                int i125 = ($changed$iv$iv$iv26 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                int i126 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 762306201, "C139@5911L223,145@6151L28,146@6196L270:RazrCoverShell.kt#z0j3je");
                Modifier modifierClip119 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(7)), RoundedCornerShapeKt.getCircleShape());
                if (z2) {
                    $composer.startReplaceGroup(-529593847);
                    ComposerKt.sourceInformation($composer, "143@6083L10");
                    auroraTextMuted = AuroraColorSchemeKt.getAuroraLive($composer, 0);
                } else {
                    $composer.startReplaceGroup(-529593330);
                    ComposerKt.sourceInformation($composer, "143@6099L15");
                    auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                }
                $composer.endReplaceGroup();
                BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip119, auroraTextMuted, null, 2, null), $composer, 0);
                SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                if (z2) {
                    $composer.startReplaceGroup(-529587638);
                    ComposerKt.sourceInformation($composer, "148@6277L11");
                    auroraText = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                } else {
                    $composer.startReplaceGroup(-529587095);
                    ComposerKt.sourceInformation($composer, "148@6294L10");
                    auroraText = AuroraColorSchemeKt.getAuroraText($composer, 0);
                }
                $composer.endReplaceGroup();
                TextKt.m3067Text4IGK_g(str2, (Modifier) null, auroraText, TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(12)), $composer, 6);
                Modifier modifier$iv210 = PaddingKt.m1035paddingVpY3zN4(BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(16))), AuroraColorSchemeKt.getAuroraSurface($composer, 0), null, 2, null), Dp.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer, 0), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(16))), Dp.m7016constructorimpl(14), Dp.m7016constructorimpl(13));
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv119 = Alignment.INSTANCE.getTopStart();
                measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv119, false);
                int $changed$iv$iv210 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv210 = ComposedModifierKt.materializeModifier($composer, modifier$iv210);
                constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv210 = (($changed$iv$iv210 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function6 = constructor4;
                    $composer.createNode(function6);
                } else {
                    function6 = constructor4;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash210 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                    measurePolicy$iv5 = measurePolicy$iv4;
                    localMap$iv$iv2 = localMap$iv$iv;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv210, ComposeUiNode.INSTANCE.getSetModifier());
                    int i111122 = ($changed$iv$iv$iv210 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance1112 = BoxScopeInstance.INSTANCE;
                    int i11123 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -410226970, "C167@6898L2781:RazrCoverShell.kt#z0j3je");
                    Modifier modifier$iv211 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv1114 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv1114 = Alignment.INSTANCE.getStart();
                    int $i$f$Column19 = ((6 >> 3) & 14) | ((6 >> 3) & 112);
                    MeasurePolicy measurePolicy$iv11123 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1114, horizontalAlignment$iv1114, $composer, $i$f$Column19);
                    int $changed$iv$iv211 = (6 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv211 = ComposedModifierKt.materializeModifier($composer, modifier$iv211);
                    constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv211 = (($changed$iv$iv211 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function7 = constructor5;
                        $composer.createNode(function7);
                    } else {
                        function7 = constructor5;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv5 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv11123, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash211 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting()) {
                        localMap$iv$iv4 = localMap$iv$iv3;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                        }
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv211, ComposeUiNode.INSTANCE.getSetModifier());
                        int i111111110 = ($changed$iv$iv$iv211 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance11110 = ColumnScopeInstance.INSTANCE;
                        int i111111111 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                        TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                        TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                        $composer.startReplaceGroup(-529544623);
                        ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                        if (!StringsKt.isBlank(str4)) {
                            SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                            TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                        }
                        $composer.endReplaceGroup();
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                        Modifier modifier$iv111111111 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Vertical verticalAlignment$iv118 = Alignment.INSTANCE.getCenterVertically();
                        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv118 = Arrangement.INSTANCE.getStart();
                        int $changed$iv13 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                        MeasurePolicy measurePolicy$iv111111116 = RowKt.rowMeasurePolicy(horizontalArrangement$iv118, verticalAlignment$iv118, $composer, $changed$iv13);
                        int $changed$iv$iv111111111 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv111111113 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv111111111 = ComposedModifierKt.materializeModifier($composer, modifier$iv111111111);
                        constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv111111111 = (($changed$iv$iv111111111 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function8 = constructor6;
                            $composer.createNode(function8);
                        } else {
                            function8 = constructor6;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv111111116, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv111111113, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111111111 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                        $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash111111111);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv111111111, ComposeUiNode.INSTANCE.getSetModifier());
                        int i111111112 = ($changed$iv$iv$iv111111111 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i111111113 = ((390 >> 6) & 112) | 6;
                        $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                        $composer.startReplaceGroup(-618069607);
                        ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                        if (uiState.getSelectedModel() != null) {
                            TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                            SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                        }
                        $composer.endReplaceGroup();
                        TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                        $composer.startReplaceGroup(-618043174);
                        ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                        if (uiState.getSending()) {
                            SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                            TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                        }
                        $composer.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                        Alignment.Horizontal horizontalAlignment$iv1115 = Alignment.INSTANCE.getCenterHorizontally();
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Modifier modifier$iv111111112 = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv1115 = Arrangement.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv111111117 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1115, horizontalAlignment$iv1115, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv111111112 = (384 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv111111114 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv111111112 = ComposedModifierKt.materializeModifier($composer, modifier$iv111111112);
                        constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                        int $i$f$Column110 = $changed$iv$iv111111112 << 6;
                        int $changed$iv$iv$iv111111112 = ($i$f$Column110 & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function9 = constructor7;
                            $composer.createNode(function9);
                        } else {
                            function9 = constructor7;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv111111117, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv111111114, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111111112 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                        $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash111111112);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv111111112, ComposeUiNode.INSTANCE.getSetModifier());
                        int i111111114 = ($changed$iv$iv$iv111111112 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance11111 = ColumnScopeInstance.INSTANCE;
                        int i211111111118 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                        Alignment contentAlignment$iv1110 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        Modifier modifier$iv111111113 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicy$iv111111118 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv1110, false);
                        int $changed$iv$iv111111113 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv111111115 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv111111113 = ComposedModifierKt.materializeModifier($composer, modifier$iv111111113);
                        constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv111111113 = (($changed$iv$iv111111113 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function10 = constructor8;
                            $composer.createNode(function10);
                        } else {
                            function10 = constructor8;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv111111118, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv111111115, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111111113 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                        $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash111111113);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv111111113, ComposeUiNode.INSTANCE.getSetModifier());
                        int i211111111119 = ($changed$iv$iv$iv111111113 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance1113 = BoxScopeInstance.INSTANCE;
                        int i2111111111110 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                        Modifier modifierM1079size3ABfNKs13 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                        Brush.Companion companion13 = Brush.INSTANCE;
                        long auroraAmber115 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs13, companion13.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber115, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber115) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber115) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber115) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber115) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                        Modifier modifierClip1110 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                        long auroraAmber116 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        Modifier modifierM600borderxT4_qwU13 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip1110, Color.m4532copywmQWz5c(auroraAmber116, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber116) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber116) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber116) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber116) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                        $composer.startReplaceGroup(-529441171);
                        ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                        invalid$iv = $composer.changed(z2);
                        Object it$iv115 = $composer.rememberedValue();
                        if (!invalid$iv) {
                        }
                        value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                        $composer.endReplaceGroup();
                        Modifier modifierSemantics$default13 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU13, false, (Function1) value$iv, 1, null);
                        boolean zCanTalk13 = CoverShellPolicy.INSTANCE.canTalk(z2);
                        $composer.startReplaceGroup(-529427629);
                        ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                        invalid$iv2 = $composer.changedInstance(context);
                        Object it$iv116 = $composer.rememberedValue();
                        if (!invalid$iv2) {
                        }
                        value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                            }
                        };
                        $composer.updateRememberedValue(value$iv2);
                        $composer.endReplaceGroup();
                        Modifier modifier$iv111111114 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default13, zCanTalk13, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                        Alignment contentAlignment$iv1111 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv111111119 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv1111, false);
                        int $changed$iv$iv111111114 = (48 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv111111116 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv111111114 = ComposedModifierKt.materializeModifier($composer, modifier$iv111111114);
                        constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv111111114 = (($changed$iv$iv111111114 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function11 = constructor9;
                            $composer.createNode(function11);
                        } else {
                            function11 = constructor9;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv111111119, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv111111116, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111111114 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                        $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash111111114);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv111111114, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2111111111111 = ($changed$iv$iv$iv111111114 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance1114 = BoxScopeInstance.INSTANCE;
                        int i2111111111112 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                        Modifier modifierClip1111 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                        if (z2) {
                            $composer.startReplaceGroup(-617963319);
                            ComposerKt.sourceInformation($composer, "285@11805L11");
                            auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                        } else {
                            $composer.startReplaceGroup(-617962771);
                            ComposerKt.sourceInformation($composer, "285@11822L15");
                            auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                        }
                        $composer.endReplaceGroup();
                        BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip1111, auroraTextMuted2, null, 2, null), $composer, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                        if (z2) {
                            str = "TALK · tap orb or power";
                        } else {
                            str = "Offline";
                        }
                        TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        Modifier modifier$iv111111115 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Alignment.Horizontal horizontalAlignment$iv1116 = Alignment.INSTANCE.getCenterHorizontally();
                        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv1116 = Arrangement.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv1111111110 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1116, horizontalAlignment$iv1116, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                        int $changed$iv$iv111111115 = (390 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv111111117 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv111111115 = ComposedModifierKt.materializeModifier($composer, modifier$iv111111115);
                        constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv111111115 = (($changed$iv$iv111111115 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            $composer.createNode(constructor10);
                        } else {
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv1111111110, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv111111117, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111111115 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                        $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash111111115);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv111111115, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2111111111113 = ($changed$iv$iv$iv111111115 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance11112 = ColumnScopeInstance.INSTANCE;
                        int i2111111111114 = ((390 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                        Modifier modifier$iv111111116 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Arrangement.Horizontal horizontalArrangement$iv119 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                        Alignment.Vertical verticalAlignment$iv119 = Alignment.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv1111111111 = RowKt.rowMeasurePolicy(horizontalArrangement$iv119, verticalAlignment$iv119, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv111111116 = (54 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv111111118 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv111111116 = ComposedModifierKt.materializeModifier($composer, modifier$iv111111116);
                        constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv111111116 = (($changed$iv$iv111111116 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function12 = constructor11;
                            $composer.createNode(function12);
                        } else {
                            function12 = constructor11;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv1111111111, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv111111118, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111111116 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                        $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash111111116);
                        Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv111111116, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2111111111115 = ($changed$iv$iv$iv111111116 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                        int i2111111111116 = ((54 >> 6) & 112) | 6;
                        RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24117 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                        CoverShellPolicy coverShellPolicy13 = CoverShellPolicy.INSTANCE;
                        if (auroraSession != null) {
                            z = true;
                        } else {
                            z = false;
                        }
                        RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy13.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24117, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                        RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24117, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                        TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
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
                    localMap$iv$iv4 = localMap$iv$iv3;
                    $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                    $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash211);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv211, ComposeUiNode.INSTANCE.getSetModifier());
                    int i111111115 = ($changed$iv$iv$iv211 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance11113 = ColumnScopeInstance.INSTANCE;
                    int i111111116 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                    TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                    TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                    $composer.startReplaceGroup(-529544623);
                    ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                    if (!StringsKt.isBlank(str4)) {
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                        TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                    }
                    $composer.endReplaceGroup();
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                    BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                    Modifier modifier$iv111111117 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Vertical verticalAlignment$iv1110 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv1110 = Arrangement.INSTANCE.getStart();
                    int $changed$iv14 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                    MeasurePolicy measurePolicy$iv1111111112 = RowKt.rowMeasurePolicy(horizontalArrangement$iv1110, verticalAlignment$iv1110, $composer, $changed$iv14);
                    int $changed$iv$iv111111117 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv111111119 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111111117 = ComposedModifierKt.materializeModifier($composer, modifier$iv111111117);
                    constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv111111117 = (($changed$iv$iv111111117 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function8 = constructor6;
                        $composer.createNode(function8);
                    } else {
                        function8 = constructor6;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv1111111112, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv111111119, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111111117 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                    $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash111111117);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv111111117, ComposeUiNode.INSTANCE.getSetModifier());
                    int i111111117 = ($changed$iv$iv$iv111111117 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i111111118 = ((390 >> 6) & 112) | 6;
                    $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                    $composer.startReplaceGroup(-618069607);
                    ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                    if (uiState.getSelectedModel() != null) {
                        TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                        SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                    }
                    $composer.endReplaceGroup();
                    TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                    $composer.startReplaceGroup(-618043174);
                    ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                    if (uiState.getSending()) {
                        SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                        TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                    }
                    $composer.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                    Alignment.Horizontal horizontalAlignment$iv1117 = Alignment.INSTANCE.getCenterHorizontally();
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv111111118 = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv1117 = Arrangement.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv1111111113 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1117, horizontalAlignment$iv1117, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    int $changed$iv$iv111111118 = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv1111111110 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111111118 = ComposedModifierKt.materializeModifier($composer, modifier$iv111111118);
                    constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                    int $i$f$Column111 = $changed$iv$iv111111118 << 6;
                    int $changed$iv$iv$iv111111118 = ($i$f$Column111 & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function9 = constructor7;
                        $composer.createNode(function9);
                    } else {
                        function9 = constructor7;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv1111111113, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv1111111110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111111118 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                    $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash111111118);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv111111118, ComposeUiNode.INSTANCE.getSetModifier());
                    int i111111119 = ($changed$iv$iv$iv111111118 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance11114 = ColumnScopeInstance.INSTANCE;
                    int i2111111111117 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                    Alignment contentAlignment$iv1112 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Modifier modifier$iv111111119 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicy$iv1111111114 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv1112, false);
                    int $changed$iv$iv111111119 = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv1111111111 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv111111119 = ComposedModifierKt.materializeModifier($composer, modifier$iv111111119);
                    constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv111111119 = (($changed$iv$iv111111119 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function10 = constructor8;
                        $composer.createNode(function10);
                    } else {
                        function10 = constructor8;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv1111111114, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv1111111111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash111111119 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                    $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash111111119);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv111111119, ComposeUiNode.INSTANCE.getSetModifier());
                    int i2111111111118 = ($changed$iv$iv$iv111111119 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance1115 = BoxScopeInstance.INSTANCE;
                    int i2111111111119 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                    Modifier modifierM1079size3ABfNKs14 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                    Brush.Companion companion14 = Brush.INSTANCE;
                    long auroraAmber117 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs14, companion14.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber117, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber117) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber117) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber117) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber117) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                    Modifier modifierClip1112 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                    long auroraAmber118 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    Modifier modifierM600borderxT4_qwU14 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip1112, Color.m4532copywmQWz5c(auroraAmber118, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber118) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber118) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber118) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber118) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                    $composer.startReplaceGroup(-529441171);
                    ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                    invalid$iv = $composer.changed(z2);
                    Object it$iv117 = $composer.rememberedValue();
                    if (!invalid$iv) {
                    }
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                    $composer.endReplaceGroup();
                    Modifier modifierSemantics$default14 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU14, false, (Function1) value$iv, 1, null);
                    boolean zCanTalk14 = CoverShellPolicy.INSTANCE.canTalk(z2);
                    $composer.startReplaceGroup(-529427629);
                    ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                    invalid$iv2 = $composer.changedInstance(context);
                    Object it$iv118 = $composer.rememberedValue();
                    if (!invalid$iv2) {
                    }
                    value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                        }
                    };
                    $composer.updateRememberedValue(value$iv2);
                    $composer.endReplaceGroup();
                    Modifier modifier$iv1111111110 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default14, zCanTalk14, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                    Alignment contentAlignment$iv1113 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv1111111115 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv1113, false);
                    int $changed$iv$iv1111111110 = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv1111111112 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1111111110 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111111110);
                    constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv1111111110 = (($changed$iv$iv1111111110 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function11 = constructor9;
                        $composer.createNode(function11);
                    } else {
                        function11 = constructor9;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv1111111115, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv1111111112, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111111110 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                    $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash1111111110);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv1111111110, ComposeUiNode.INSTANCE.getSetModifier());
                    int i21111111111110 = ($changed$iv$iv$iv1111111110 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance1116 = BoxScopeInstance.INSTANCE;
                    int i21111111111111 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                    Modifier modifierClip1113 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                    if (z2) {
                        $composer.startReplaceGroup(-617963319);
                        ComposerKt.sourceInformation($composer, "285@11805L11");
                        auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    } else {
                        $composer.startReplaceGroup(-617962771);
                        ComposerKt.sourceInformation($composer, "285@11822L15");
                        auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                    }
                    $composer.endReplaceGroup();
                    BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip1113, auroraTextMuted2, null, 2, null), $composer, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                    if (z2) {
                        str = "TALK · tap orb or power";
                    } else {
                        str = "Offline";
                    }
                    TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifier$iv1111111111 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Horizontal horizontalAlignment$iv1118 = Alignment.INSTANCE.getCenterHorizontally();
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv1118 = Arrangement.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv1111111116 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1118, horizontalAlignment$iv1118, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    int $changed$iv$iv1111111111 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv1111111113 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1111111111 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111111111);
                    constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv1111111111 = (($changed$iv$iv1111111111 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        $composer.createNode(constructor10);
                    } else {
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv1111111116, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv1111111113, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111111111 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                    $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash1111111111);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv1111111111, ComposeUiNode.INSTANCE.getSetModifier());
                    int i21111111111112 = ($changed$iv$iv$iv1111111111 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance11115 = ColumnScopeInstance.INSTANCE;
                    int i21111111111113 = ((390 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                    Modifier modifier$iv1111111112 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Arrangement.Horizontal horizontalArrangement$iv1111 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                    ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Alignment.Vertical verticalAlignment$iv1111 = Alignment.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv1111111117 = RowKt.rowMeasurePolicy(horizontalArrangement$iv1111, verticalAlignment$iv1111, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                    int $changed$iv$iv1111111112 = (54 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv1111111114 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1111111112 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111111112);
                    constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv1111111112 = (($changed$iv$iv1111111112 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function12 = constructor11;
                        $composer.createNode(function12);
                    } else {
                        function12 = constructor11;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv1111111117, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv1111111114, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111111112 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                    $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash1111111112);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv1111111112, ComposeUiNode.INSTANCE.getSetModifier());
                    int i21111111111114 = ($changed$iv$iv$iv1111111112 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i21111111111115 = ((54 >> 6) & 112) | 6;
                    RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24118 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                    CoverShellPolicy coverShellPolicy14 = CoverShellPolicy.INSTANCE;
                    if (auroraSession != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy14.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24118, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                    RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24118, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                    TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
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
                measurePolicy$iv5 = measurePolicy$iv4;
                localMap$iv$iv2 = localMap$iv$iv;
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash210);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv210, ComposeUiNode.INSTANCE.getSetModifier());
                int i111123 = ($changed$iv$iv$iv210 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance1117 = BoxScopeInstance.INSTANCE;
                int i11124 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -410226970, "C167@6898L2781:RazrCoverShell.kt#z0j3je");
                Modifier modifier$iv212 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv1119 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv1119 = Alignment.INSTANCE.getStart();
                int $i$f$Column112 = ((6 >> 3) & 14) | ((6 >> 3) & 112);
                MeasurePolicy measurePolicy$iv11124 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv1119, horizontalAlignment$iv1119, $composer, $i$f$Column112);
                int $changed$iv$iv212 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv212 = ComposedModifierKt.materializeModifier($composer, modifier$iv212);
                constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv212 = (($changed$iv$iv212 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function7 = constructor5;
                    $composer.createNode(function7);
                } else {
                    function7 = constructor5;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv5 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv11124, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash212 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting()) {
                    localMap$iv$iv4 = localMap$iv$iv3;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                    }
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv212, ComposeUiNode.INSTANCE.getSetModifier());
                    int i1111111110 = ($changed$iv$iv$iv212 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance11116 = ColumnScopeInstance.INSTANCE;
                    int i1111111111 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                    TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                    TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                    $composer.startReplaceGroup(-529544623);
                    ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                    if (!StringsKt.isBlank(str4)) {
                        SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                        TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                    }
                    $composer.endReplaceGroup();
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                    BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                    Modifier modifier$iv1111111113 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Vertical verticalAlignment$iv1112 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv1112 = Arrangement.INSTANCE.getStart();
                    int $changed$iv15 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                    MeasurePolicy measurePolicy$iv1111111118 = RowKt.rowMeasurePolicy(horizontalArrangement$iv1112, verticalAlignment$iv1112, $composer, $changed$iv15);
                    int $changed$iv$iv1111111113 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv1111111115 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1111111113 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111111113);
                    constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv1111111113 = (($changed$iv$iv1111111113 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function8 = constructor6;
                        $composer.createNode(function8);
                    } else {
                        function8 = constructor6;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv1111111118, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv1111111115, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111111113 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                    $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash1111111113);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv1111111113, ComposeUiNode.INSTANCE.getSetModifier());
                    int i1111111112 = ($changed$iv$iv$iv1111111113 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i1111111113 = ((390 >> 6) & 112) | 6;
                    $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                    $composer.startReplaceGroup(-618069607);
                    ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                    if (uiState.getSelectedModel() != null) {
                        TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                        SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                    }
                    $composer.endReplaceGroup();
                    TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                    $composer.startReplaceGroup(-618043174);
                    ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                    if (uiState.getSending()) {
                        SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                        TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                    }
                    $composer.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                    Alignment.Horizontal horizontalAlignment$iv11110 = Alignment.INSTANCE.getCenterHorizontally();
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Modifier modifier$iv1111111114 = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv11110 = Arrangement.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv1111111119 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv11110, horizontalAlignment$iv11110, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    int $changed$iv$iv1111111114 = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv1111111116 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1111111114 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111111114);
                    constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                    int $i$f$Column113 = $changed$iv$iv1111111114 << 6;
                    int $changed$iv$iv$iv1111111114 = ($i$f$Column113 & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function9 = constructor7;
                        $composer.createNode(function9);
                    } else {
                        function9 = constructor7;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv1111111119, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv1111111116, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111111114 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                    $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash1111111114);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv1111111114, ComposeUiNode.INSTANCE.getSetModifier());
                    int i1111111114 = ($changed$iv$iv$iv1111111114 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance11117 = ColumnScopeInstance.INSTANCE;
                    int i21111111111116 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                    Alignment contentAlignment$iv1114 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    Modifier modifier$iv1111111115 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicy$iv11111111110 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv1114, false);
                    int $changed$iv$iv1111111115 = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv1111111117 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1111111115 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111111115);
                    constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv1111111115 = (($changed$iv$iv1111111115 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function10 = constructor8;
                        $composer.createNode(function10);
                    } else {
                        function10 = constructor8;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv11111111110, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv1111111117, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111111115 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                    $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash1111111115);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv1111111115, ComposeUiNode.INSTANCE.getSetModifier());
                    int i21111111111117 = ($changed$iv$iv$iv1111111115 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance1118 = BoxScopeInstance.INSTANCE;
                    int i21111111111118 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                    Modifier modifierM1079size3ABfNKs15 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                    Brush.Companion companion15 = Brush.INSTANCE;
                    long auroraAmber119 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs15, companion15.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber119, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber119) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber119) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber119) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber119) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                    Modifier modifierClip1114 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                    long auroraAmber1110 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    Modifier modifierM600borderxT4_qwU15 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip1114, Color.m4532copywmQWz5c(auroraAmber1110, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber1110) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber1110) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber1110) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber1110) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                    $composer.startReplaceGroup(-529441171);
                    ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                    invalid$iv = $composer.changed(z2);
                    Object it$iv119 = $composer.rememberedValue();
                    if (!invalid$iv) {
                    }
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                    $composer.endReplaceGroup();
                    Modifier modifierSemantics$default15 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU15, false, (Function1) value$iv, 1, null);
                    boolean zCanTalk15 = CoverShellPolicy.INSTANCE.canTalk(z2);
                    $composer.startReplaceGroup(-529427629);
                    ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                    invalid$iv2 = $composer.changedInstance(context);
                    Object it$iv1110 = $composer.rememberedValue();
                    if (!invalid$iv2) {
                    }
                    value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                        }
                    };
                    $composer.updateRememberedValue(value$iv2);
                    $composer.endReplaceGroup();
                    Modifier modifier$iv1111111116 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default15, zCanTalk15, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                    Alignment contentAlignment$iv1115 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv11111111111 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv1115, false);
                    int $changed$iv$iv1111111116 = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv1111111118 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1111111116 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111111116);
                    constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv1111111116 = (($changed$iv$iv1111111116 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function11 = constructor9;
                        $composer.createNode(function11);
                    } else {
                        function11 = constructor9;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv11111111111, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv1111111118, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111111116 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                    $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash1111111116);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv1111111116, ComposeUiNode.INSTANCE.getSetModifier());
                    int i21111111111119 = ($changed$iv$iv$iv1111111116 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance1119 = BoxScopeInstance.INSTANCE;
                    int i211111111111110 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                    Modifier modifierClip1115 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                    if (z2) {
                        $composer.startReplaceGroup(-617963319);
                        ComposerKt.sourceInformation($composer, "285@11805L11");
                        auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                    } else {
                        $composer.startReplaceGroup(-617962771);
                        ComposerKt.sourceInformation($composer, "285@11822L15");
                        auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                    }
                    $composer.endReplaceGroup();
                    BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip1115, auroraTextMuted2, null, 2, null), $composer, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                    if (z2) {
                        str = "TALK · tap orb or power";
                    } else {
                        str = "Offline";
                    }
                    TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifier$iv1111111117 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment.Horizontal horizontalAlignment$iv11111 = Alignment.INSTANCE.getCenterHorizontally();
                    ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv11111 = Arrangement.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv11111111112 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv11111, horizontalAlignment$iv11111, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    int $changed$iv$iv1111111117 = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv1111111119 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1111111117 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111111117);
                    constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv1111111117 = (($changed$iv$iv1111111117 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        $composer.createNode(constructor10);
                    } else {
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv11111111112, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv1111111119, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111111117 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                    $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash1111111117);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv1111111117, ComposeUiNode.INSTANCE.getSetModifier());
                    int i211111111111111 = ($changed$iv$iv$iv1111111117 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance11118 = ColumnScopeInstance.INSTANCE;
                    int i211111111111112 = ((390 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                    Modifier modifier$iv1111111118 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Arrangement.Horizontal horizontalArrangement$iv1113 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                    ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    Alignment.Vertical verticalAlignment$iv1113 = Alignment.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv11111111113 = RowKt.rowMeasurePolicy(horizontalArrangement$iv1113, verticalAlignment$iv1113, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                    int $changed$iv$iv1111111118 = (54 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv11111111110 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv1111111118 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111111118);
                    constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv1111111118 = (($changed$iv$iv1111111118 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function12 = constructor11;
                        $composer.createNode(function12);
                    } else {
                        function12 = constructor11;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv11111111113, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv11111111110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111111118 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                    $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash1111111118);
                    Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv1111111118, ComposeUiNode.INSTANCE.getSetModifier());
                    int i211111111111113 = ($changed$iv$iv$iv1111111118 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    int i211111111111114 = ((54 >> 6) & 112) | 6;
                    RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24119 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                    CoverShellPolicy coverShellPolicy15 = CoverShellPolicy.INSTANCE;
                    if (auroraSession != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy15.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24119, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                    RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u24119, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                    TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
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
                localMap$iv$iv4 = localMap$iv$iv3;
                $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash212);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv212, ComposeUiNode.INSTANCE.getSetModifier());
                int i1111111115 = ($changed$iv$iv$iv212 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance11119 = ColumnScopeInstance.INSTANCE;
                int i1111111116 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 763412498, "C170@7030L15,168@6959L293,176@7269L29,179@7384L10,177@7315L278,195@7997L29,200@8202L14,196@8043L193,202@8253L29,203@8299L1366:RazrCoverShell.kt#z0j3je");
                TextKt.m3067Text4IGK_g("LATEST THREAD", (Modifier) null, AuroraColorSchemeKt.getAuroraTextFaint($composer, 0), TextUnitKt.getSp(9), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), TextUnitKt.getSp(1), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 12782598, 0, 130834);
                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(5)), $composer, 6);
                TextKt.m3067Text4IGK_g(str3, (Modifier) null, AuroraColorSchemeKt.getAuroraText($composer, 0), TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 3120, 120786);
                $composer.startReplaceGroup(-529544623);
                ComposerKt.sourceInformation($composer, "186@7658L29,189@7786L15,187@7708L254");
                if (!StringsKt.isBlank(str4)) {
                    SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(3)), $composer, 6);
                    TextKt.m3067Text4IGK_g(str4, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 2, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                }
                $composer.endReplaceGroup();
                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(9)), $composer, 6);
                BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7016constructorimpl(1)), AuroraColorSchemeKt.getAuroraHairline($composer, 0), null, 2, null), $composer, 0);
                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                Modifier modifier$iv1111111119 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment.Vertical verticalAlignment$iv1114 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv1114 = Arrangement.INSTANCE.getStart();
                int $changed$iv16 = ((390 >> 3) & 14) | ((390 >> 3) & 112);
                MeasurePolicy measurePolicy$iv11111111114 = RowKt.rowMeasurePolicy(horizontalArrangement$iv1114, verticalAlignment$iv1114, $composer, $changed$iv16);
                int $changed$iv$iv1111111119 = (390 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv11111111111 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv1111111119 = ComposedModifierKt.materializeModifier($composer, modifier$iv1111111119);
                constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv1111111119 = (($changed$iv$iv1111111119 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function8 = constructor6;
                    $composer.createNode(function8);
                } else {
                    function8 = constructor6;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv11111111114, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv11111111111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash1111111119 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash1111111119);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv1111111119, ComposeUiNode.INSTANCE.getSetModifier());
                int i1111111117 = ($changed$iv$iv$iv1111111119 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i1111111118 = ((390 >> 6) & 112) | 6;
                $this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -1980267798, "C220@9102L15,218@8996L282:RazrCoverShell.kt#z0j3je");
                $composer.startReplaceGroup(-618069607);
                ComposerKt.sourceInformation($composer, "210@8649L11,208@8527L373,216@8925L28");
                if (uiState.getSelectedModel() != null) {
                    TextKt.m3067Text4IGK_g(StringsKt.substringAfterLast$default(uiState.getSelectedModel(), '/', (String) null, 2, (Object) null), (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120754);
                    SpacerKt.Spacer(SizeKt.m1084width3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(8)), $composer, 6);
                }
                $composer.endReplaceGroup();
                TextKt.m3067Text4IGK_g("·  " + uiState.getSessions().size() + " threads", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6933getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 3120, 120818);
                $composer.startReplaceGroup(-618043174);
                ComposerKt.sourceInformation($composer, "226@9344L27,229@9479L11,227@9396L229");
                if (uiState.getSending()) {
                    SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2414_u24lambda_u246_u24lambda_u245_u24lambda_u244, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
                    TextKt.m3067Text4IGK_g("thinking…", (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131026);
                }
                $composer.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(16)), $composer, 6);
                Alignment.Horizontal horizontalAlignment$iv11112 = Alignment.INSTANCE.getCenterHorizontally();
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Modifier modifier$iv11111111110 = Modifier.INSTANCE;
                Arrangement.Vertical verticalArrangement$iv11112 = Arrangement.INSTANCE.getTop();
                MeasurePolicy measurePolicy$iv11111111115 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv11112, horizontalAlignment$iv11112, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                int $changed$iv$iv11111111110 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv11111111112 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv11111111110 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111111110);
                constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                int $i$f$Column114 = $changed$iv$iv11111111110 << 6;
                int $changed$iv$iv$iv11111111110 = ($i$f$Column114 & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function9 = constructor7;
                    $composer.createNode(function9);
                } else {
                    function9 = constructor7;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv11111111115, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv11111111112, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111111110 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash11111111110);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv11111111110, ComposeUiNode.INSTANCE.getSetModifier());
                int i1111111119 = ($changed$iv$iv$iv11111111110 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance111110 = ColumnScopeInstance.INSTANCE;
                int i211111111111115 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -407063172, "C244@10110L1771,289@11890L29,292@12020L11,290@11928L237:RazrCoverShell.kt#z0j3je");
                Alignment contentAlignment$iv1116 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Modifier modifier$iv11111111111 = Modifier.INSTANCE;
                MeasurePolicy measurePolicy$iv11111111116 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv1116, false);
                int $changed$iv$iv11111111111 = (48 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv8 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv11111111113 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv11111111111 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111111111);
                constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv11111111111 = (($changed$iv$iv11111111111 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function10 = constructor8;
                    $composer.createNode(function10);
                } else {
                    function10 = constructor8;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv8 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, measurePolicy$iv11111111116, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, localMap$iv$iv11111111113, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111111111 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv8.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv8.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv8));
                $this$Layout_u24lambda_u240$iv$iv8.apply(Integer.valueOf(compositeKeyHash$iv$iv8), setCompositeKeyHash11111111111);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv8, materialized$iv$iv11111111111, ComposeUiNode.INSTANCE.getSetModifier());
                int i211111111111116 = ($changed$iv$iv$iv11111111111 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance11110 = BoxScopeInstance.INSTANCE;
                int i211111111111117 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 766561850, "C251@10387L11,245@10165L400,261@10723L11,262@10790L11,263@10847L238,273@11269L276,257@10578L1293:RazrCoverShell.kt#z0j3je");
                Modifier modifierM1079size3ABfNKs16 = SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(LocationRequestCompat.QUALITY_LOW_POWER));
                Brush.Companion companion16 = Brush.INSTANCE;
                long auroraAmber1111 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                BoxKt.Box(BackgroundKt.background$default(modifierM1079size3ABfNKs16, companion16.m4497radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4524boximpl(Color.m4532copywmQWz5c(auroraAmber1111, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber1111) : 0.2f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber1111) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber1111) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber1111) : 0.0f)), Color.m4524boximpl(Color.INSTANCE.m4569getTransparent0d7_KjU())}), (14 & 2) != 0 ? Offset.INSTANCE.m4308getUnspecifiedF1C5BW0() : 0L, (14 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (14 & 8) != 0 ? TileMode.INSTANCE.m4907getClamp3opZhB0() : 0), null, 0.0f, 6, null), $composer, 0);
                Modifier modifierClip1116 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(88)), RoundedCornerShapeKt.getCircleShape());
                long auroraAmber1112 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                Modifier modifierM600borderxT4_qwU16 = BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(modifierClip1116, Color.m4532copywmQWz5c(auroraAmber1112, (14 & 1) != 0 ? Color.m4536getAlphaimpl(auroraAmber1112) : 0.22f, (14 & 2) != 0 ? Color.m4540getRedimpl(auroraAmber1112) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(auroraAmber1112) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(auroraAmber1112) : 0.0f), null, 2, null), Dp.m7016constructorimpl(2), AuroraColorSchemeKt.getAuroraAmber($composer, 0), RoundedCornerShapeKt.getCircleShape());
                $composer.startReplaceGroup(-529441171);
                ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                invalid$iv = $composer.changed(z2);
                Object it$iv1111 = $composer.rememberedValue();
                if (!invalid$iv) {
                }
                value$iv = new Function1() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(z2, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                $composer.endReplaceGroup();
                Modifier modifierSemantics$default16 = SemanticsModifierKt.semantics$default(modifierM600borderxT4_qwU16, false, (Function1) value$iv, 1, null);
                boolean zCanTalk16 = CoverShellPolicy.INSTANCE.canTalk(z2);
                $composer.startReplaceGroup(-529427629);
                ComposerKt.sourceInformation($composer, "CC(remember):RazrCoverShell.kt#9igjgp");
                invalid$iv2 = $composer.changedInstance(context);
                Object it$iv1112 = $composer.rememberedValue();
                if (!invalid$iv2) {
                }
                value$iv2 = new Function0() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$RazrCoverShell$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RazrCoverShellKt.AnonymousClass1.invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(context);
                    }
                };
                $composer.updateRememberedValue(value$iv2);
                $composer.endReplaceGroup();
                Modifier modifier$iv11111111112 = ClickableKt.m622clickableXHw0xAI$default(modifierSemantics$default16, zCanTalk16, "Start voice Assist", null, (Function0) value$iv2, 4, null);
                Alignment contentAlignment$iv1117 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv11111111117 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv1117, false);
                int $changed$iv$iv11111111112 = (48 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv9 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv11111111114 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv11111111112 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111111112);
                constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv11111111112 = (($changed$iv$iv11111111112 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function11 = constructor9;
                    $composer.createNode(function11);
                } else {
                    function11 = constructor9;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv9 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, measurePolicy$iv11111111117, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, localMap$iv$iv11111111114, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111111112 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv9.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv9.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv9));
                $this$Layout_u24lambda_u240$iv$iv9.apply(Integer.valueOf(compositeKeyHash$iv$iv9), setCompositeKeyHash11111111112);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv9, materialized$iv$iv11111111112, ComposeUiNode.INSTANCE.getSetModifier());
                int i211111111111118 = ($changed$iv$iv$iv11111111112 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance11111 = BoxScopeInstance.INSTANCE;
                int i211111111111119 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1977158684, "C281@11632L225:RazrCoverShell.kt#z0j3je");
                Modifier modifierClip1117 = ClipKt.clip(SizeKt.m1079size3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
                if (z2) {
                    $composer.startReplaceGroup(-617963319);
                    ComposerKt.sourceInformation($composer, "285@11805L11");
                    auroraTextMuted2 = AuroraColorSchemeKt.getAuroraAmber($composer, 0);
                } else {
                    $composer.startReplaceGroup(-617962771);
                    ComposerKt.sourceInformation($composer, "285@11822L15");
                    auroraTextMuted2 = AuroraColorSchemeKt.getAuroraTextMuted($composer, 0);
                }
                $composer.endReplaceGroup();
                BoxKt.Box(BackgroundKt.m589backgroundbw27NRU$default(modifierClip1117, auroraTextMuted2, null, 2, null), $composer, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(6)), $composer, 6);
                if (z2) {
                    str = "TALK · tap orb or power";
                } else {
                    str = "Offline";
                }
                TextKt.m3067Text4IGK_g(str, (Modifier) null, AuroraColorSchemeKt.getAuroraAmber($composer, 0), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199680, 0, 130962);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier modifier$iv11111111113 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment.Horizontal horizontalAlignment$iv11113 = Alignment.INSTANCE.getCenterHorizontally();
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv11113 = Arrangement.INSTANCE.getTop();
                MeasurePolicy measurePolicy$iv11111111118 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv11113, horizontalAlignment$iv11113, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                int $changed$iv$iv11111111113 = (390 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv10 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv11111111115 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv11111111113 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111111113);
                constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv11111111113 = (($changed$iv$iv11111111113 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(constructor10);
                } else {
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv10 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, measurePolicy$iv11111111118, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, localMap$iv$iv11111111115, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111111113 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv10.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv10.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv10));
                $this$Layout_u24lambda_u240$iv$iv10.apply(Integer.valueOf(compositeKeyHash$iv$iv10), setCompositeKeyHash11111111113);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv10, materialized$iv$iv11111111113, ComposeUiNode.INSTANCE.getSetModifier());
                int i2111111111111110 = ($changed$iv$iv$iv11111111113 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance111111 = ColumnScopeInstance.INSTANCE;
                int i2111111111111111 = ((390 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1114215492, "C305@12402L676,325@13088L30,328@13210L15,326@13131L237:RazrCoverShell.kt#z0j3je");
                Modifier modifier$iv11111111114 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.Horizontal horizontalArrangement$iv1115 = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Alignment.Vertical verticalAlignment$iv1115 = Alignment.INSTANCE.getTop();
                MeasurePolicy measurePolicy$iv11111111119 = RowKt.rowMeasurePolicy(horizontalArrangement$iv1115, verticalAlignment$iv1115, $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                int $changed$iv$iv11111111114 = (54 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv11 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv11111111116 = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv11111111114 = ComposedModifierKt.materializeModifier($composer, modifier$iv11111111114);
                constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv11111111114 = (($changed$iv$iv11111111114 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function12 = constructor11;
                    $composer.createNode(function12);
                } else {
                    function12 = constructor11;
                    $composer.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv11 = Updater.m4027constructorimpl($composer);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, measurePolicy$iv11111111119, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, localMap$iv$iv11111111116, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11111111114 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv11.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv11.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv11));
                $this$Layout_u24lambda_u240$iv$iv11.apply(Integer.valueOf(compositeKeyHash$iv$iv11), setCompositeKeyHash11111111114);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv11, materialized$iv$iv11111111114, ComposeUiNode.INSTANCE.getSetModifier());
                int i2111111111111112 = ($changed$iv$iv$iv11111111114 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                int i2111111111111113 = ((54 >> 6) & 112) | 6;
                RowScope $this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u241110 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -404697066, "C309@12543L264,316@12820L248:RazrCoverShell.kt#z0j3je");
                CoverShellPolicy coverShellPolicy16 = CoverShellPolicy.INSTANCE;
                if (auroraSession != null) {
                    z = true;
                } else {
                    z = false;
                }
                RazrCoverShellKt.CoverAction("OPEN", "Latest thread", coverShellPolicy16.canOpenLatest(z), function13, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u241110, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                RazrCoverShellKt.CoverAction("NEW", "Start chat", CoverShellPolicy.INSTANCE.canStartChat(z2), function14, RowScope.weight$default($this$invoke_u24lambda_u2417_u24lambda_u2416_u24lambda_u241110, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 54, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m1065height3ABfNKs(Modifier.INSTANCE, Dp.m7016constructorimpl(10)), $composer, 6);
                TextKt.m3067Text4IGK_g("Unfold for Terminal & Control", (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer, 0), TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 130514);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
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
        public static final Unit invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$8$lambda$7(boolean $online, SemanticsPropertyReceiver semantics) {
            String str;
            Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
            if ($online) {
                str = "Talk to Aurora Assist";
            } else {
                str = "Assist offline";
            }
            SemanticsPropertiesKt.setContentDescription(semantics, str);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$17$lambda$14$lambda$13$lambda$12$lambda$10$lambda$9(Context $context) {
            $context.startActivity(new Intent($context, (Class<?>) AssistActivity.class).addFlags(335544320));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CoverAction(final String label, final String subtitle, final boolean enabled, final Function0<Unit> function0, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        int i2;
        long auroraTextMuted;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(649808294);
        ComposerKt.sourceInformation($composer2, "C(CoverAction)P(1,4!1,3)350@13706L13,351@13747L14,346@13566L763:RazrCoverShell.kt#z0j3je");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(label) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(subtitle) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(enabled) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 2048 : 1024;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(649808294, $dirty2, -1, "ai.nous.aurora.presentation.shell.cover.CoverAction (RazrCoverShell.kt:345)");
            }
            Modifier modifier5 = modifier4;
            Modifier modifierM1035paddingVpY3zN4 = PaddingKt.m1035paddingVpY3zN4(ClickableKt.m622clickableXHw0xAI$default(BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(ClipKt.clip(SizeKt.m1067heightInVpY3zN4$default(modifier4, Dp.m7016constructorimpl(56), 0.0f, 2, null), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(12))), AuroraColorSchemeKt.getAuroraSurface($composer2, 0), null, 2, null), Dp.m7016constructorimpl(1), AuroraColorSchemeKt.getAuroraHairline($composer2, 0), RoundedCornerShapeKt.m1317RoundedCornerShape0680j_4(Dp.m7016constructorimpl(12))), enabled, null, null, function0, 6, null), Dp.m7016constructorimpl(10), Dp.m7016constructorimpl(12));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(center, centerHorizontally, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifierM1035paddingVpY3zN4);
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
            int i4 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1500353187, "C357@14037L220,364@14289L15,364@14266L57:RazrCoverShell.kt#z0j3je");
            if (enabled) {
                $composer2.startReplaceGroup(-48396909);
                ComposerKt.sourceInformation($composer2, "359@14095L11");
                i2 = 0;
                auroraTextMuted = AuroraColorSchemeKt.getAuroraAmber($composer2, 0);
            } else {
                i2 = 0;
                $composer2.startReplaceGroup(-48396361);
                ComposerKt.sourceInformation($composer2, "359@14112L15");
                auroraTextMuted = AuroraColorSchemeKt.getAuroraTextMuted($composer2, 0);
            }
            $composer2.endReplaceGroup();
            modifier3 = modifier5;
            TextKt.m3067Text4IGK_g(label, (Modifier) null, auroraTextMuted, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, ($dirty2 & 14) | 199680, 0, 130962);
            TextKt.m3067Text4IGK_g(subtitle, (Modifier) null, AuroraColorSchemeKt.getAuroraTextMuted($composer2, i2), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, (($dirty2 >> 3) & 14) | 3072, 0, 131058);
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
            final Modifier modifier6 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.shell.cover.RazrCoverShellKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RazrCoverShellKt.CoverAction$lambda$3(label, subtitle, enabled, function0, modifier6, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
