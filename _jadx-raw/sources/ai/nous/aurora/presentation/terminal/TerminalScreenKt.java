package ai.nous.aurora.presentation.terminal;

import ai.nous.aurora.BuildConfig;
import ai.nous.aurora.presentation.theme.AuroraShapeKt;
import ai.nous.aurora.presentation.theme.TerminalTheme;
import android.content.Context;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
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
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.app.NotificationCompat;
import com.termux.terminal.TerminalEmulator;
import com.termux.terminal.TerminalSession;
import com.termux.view.TerminalView;
import com.termux.view.TerminalViewClient;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import okio.Buffer;

/* JADX INFO: compiled from: TerminalScreen.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\u001a!\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a%\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0003¢\u0006\u0004\b\r\u0010\u000e\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011²\u0006\n\u0010\u0012\u001a\u00020\u0010X\u008a\u008e\u0002²\u0006\n\u0010\u0013\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u0014\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0017X\u008a\u008e\u0002²\u0006\n\u0010\u0018\u001a\u00020\u0017X\u008a\u008e\u0002²\u0006\n\u0010\u0019\u001a\u00020\u0017X\u008a\u008e\u0002²\u0006\u0016\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u001bX\u008a\u008e\u0002"}, d2 = {"TerminalScreen", "", "modifier", "Landroidx/compose/ui/Modifier;", "preview", "", "(Landroidx/compose/ui/Modifier;ZLandroidx/compose/runtime/Composer;II)V", "EqualKeyRow", "height", "Landroidx/compose/ui/unit/Dp;", "keys", "", "Lai/nous/aurora/presentation/terminal/KeySpec;", "EqualKeyRow-kHDZbjc", "(FLjava/util/List;Landroidx/compose/runtime/Composer;I)V", "TERMINAL_PROFILE", "", "app_debug", NotificationCompat.CATEGORY_STATUS, "ptyReady", "ctrl", "moreKeys", "connectEpoch", "", "fontSp", "geometryEpoch", "lastGeom", "Lkotlin/Pair;"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TerminalScreenKt {
    private static final String TERMINAL_PROFILE = "hermes";

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EqualKeyRow_kHDZbjc$lambda$121(float f, List list, int i, Composer composer, int i2) {
        m230EqualKeyRowkHDZbjc(f, list, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$115(Modifier modifier, boolean z, int i, int i2, Composer composer, int i3) {
        TerminalScreen(modifier, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r15v2 java.lang.Object
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.visitors.ModVisitor.anonymousCallArgMod(ModVisitor.java:535)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.ModVisitor.processAnonymousConstructor(ModVisitor.java:528)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:111)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    public static final void TerminalScreen(androidx.compose.ui.Modifier r128, boolean r129, androidx.compose.runtime.Composer r130, final int r131, final int r132) {
        /*
            Method dump skipped, instruction units count: 7651
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ai.nous.aurora.presentation.terminal.TerminalScreenKt.TerminalScreen(androidx.compose.ui.Modifier, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String TerminalScreen$lambda$1(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean TerminalScreen$lambda$4(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TerminalScreen$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean TerminalScreen$lambda$7(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    private static final void TerminalScreen$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean TerminalScreen$lambda$10(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    private static final void TerminalScreen$lambda$11(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int TerminalScreen$lambda$13(MutableIntState $connectEpoch$delegate) {
        MutableIntState $this$getValue$iv = $connectEpoch$delegate;
        return $this$getValue$iv.getIntValue();
    }

    private static final int TerminalScreen$lambda$16(MutableIntState $fontSp$delegate) {
        MutableIntState $this$getValue$iv = $fontSp$delegate;
        return $this$getValue$iv.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int TerminalScreen$lambda$19(MutableIntState $geometryEpoch$delegate) {
        MutableIntState $this$getValue$iv = $geometryEpoch$delegate;
        return $this$getValue$iv.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair<Integer, Integer> TerminalScreen$lambda$22(MutableState<Pair<Integer, Integer>> mutableState) {
        MutableState<Pair<Integer, Integer>> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TerminalScreen$flushPendingInput(AtomicReference<WebSocket> atomicReference, ArrayDeque<byte[]> arrayDeque) {
        Iterable chunks;
        WebSocket sock = atomicReference.get();
        if (sock == null) {
            return;
        }
        synchronized (arrayDeque) {
            List $this$TerminalScreen_u24flushPendingInput_u24lambda_u2435_u24lambda_u2434 = CollectionsKt.createListBuilder(arrayDeque.size());
            while (!arrayDeque.isEmpty()) {
                $this$TerminalScreen_u24flushPendingInput_u24lambda_u2435_u24lambda_u2434.add(arrayDeque.removeFirst());
            }
            chunks = CollectionsKt.build($this$TerminalScreen_u24flushPendingInput_u24lambda_u2435_u24lambda_u2434);
        }
        Iterable $this$forEach$iv = chunks;
        for (Object element$iv : $this$forEach$iv) {
            byte[] chunk = (byte[]) element$iv;
            Buffer buffer = new Buffer();
            Intrinsics.checkNotNull(chunk);
            sock.send(buffer.write(chunk).readByteString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TerminalScreen$enqueuePtyOutput(Object outLock, CoroutineScope scope, TerminalOutputBuffer outputBuffer, boolean[] flushScheduled, AtomicReference<TerminalSession> atomicReference, byte[] bytes) {
        boolean z = true;
        if (bytes.length == 0) {
            return;
        }
        synchronized (outLock) {
            outputBuffer.append(bytes);
            if (!flushScheduled[0]) {
                flushScheduled[0] = true;
            } else {
                z = false;
            }
        }
        boolean shouldSchedule = z;
        if (shouldSchedule) {
            BuildersKt__Builders_commonKt.launch$default(scope, Dispatchers.getMain().getImmediate(), null, new TerminalScreenKt$TerminalScreen$enqueuePtyOutput$1(outLock, atomicReference, outputBuffer, flushScheduled, null), 2, null);
        }
    }

    private static final void TerminalScreen$clearLocalEmulator(Object outLock, AtomicReference<TerminalSession> atomicReference, AtomicReference<TerminalView> atomicReference2, TerminalOutputBuffer outputBuffer, boolean[] flushScheduled) {
        synchronized (outLock) {
            outputBuffer.clear();
            flushScheduled[0] = false;
            Unit unit = Unit.INSTANCE;
        }
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] wipe = "\u001b[2J\u001b[H\u001b[0m".getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(wipe, "getBytes(...)");
        TerminalSession terminalSession = atomicReference.get();
        if (terminalSession != null) {
            terminalSession.writeToEmulator(wipe, wipe.length);
        }
        TerminalView terminalView = atomicReference2.get();
        if (terminalView != null) {
            terminalView.onScreenUpdated();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String TerminalScreen$bridgeWsBase(String gatewayHttp) {
        Object objM7388constructorimpl;
        String http = StringsKt.trimEnd(StringsKt.trim((CharSequence) gatewayHttp).toString(), '/');
        try {
            Result.Companion companion = Result.INSTANCE;
            URI uri = new URI(http);
            String host = uri.getHost();
            if (host == null || StringsKt.isBlank(host)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            String scheme = uri.getScheme();
            if (scheme == null) {
                scheme = "http";
            }
            objM7388constructorimpl = Result.m7388constructorimpl(new URI(scheme, null, uri.getHost(), 8765, null, null, null).toString());
            if (Result.m7391exceptionOrNullimpl(objM7388constructorimpl) != null) {
                objM7388constructorimpl = BuildConfig.HERMES_CONFIG_URL;
            }
            Intrinsics.checkNotNullExpressionValue(objM7388constructorimpl, "getOrElse(...)");
            String bridgeHttp = (String) objM7388constructorimpl;
            return StringsKt.replace$default(StringsKt.replace$default(StringsKt.trimEnd(bridgeHttp, '/'), "https://", "wss://", false, 4, (Object) null), "http://", "ws://", false, 4, (Object) null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7388constructorimpl = Result.m7388constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TerminalScreen$connect(boolean demo, AtomicReference<WebSocket> atomicReference, CoroutineScope scope, MutableState<String> mutableState, MutableState<Boolean> mutableState2, Object outLock, AtomicReference<TerminalSession> atomicReference2, AtomicReference<TerminalView> atomicReference3, TerminalOutputBuffer outputBuffer, boolean[] flushScheduled, Context context, AtomicReference<OkHttpClient> atomicReference4, MutableIntState connectEpoch$delegate, MutableState<Pair<Integer, Integer>> mutableState3, ArrayDeque<byte[]> arrayDeque) {
        if (!demo) {
            WebSocket andSet = atomicReference.getAndSet(null);
            if (andSet != null) {
                andSet.close(1000, "switch");
            }
            TerminalScreen$lambda$5(mutableState2, false);
            mutableState.setValue("Idle · waiting for PTY");
            TerminalScreen$clearLocalEmulator(outLock, atomicReference2, atomicReference3, outputBuffer, flushScheduled);
            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new TerminalScreenKt$TerminalScreen$connect$1(context, atomicReference3, atomicReference, atomicReference4, mutableState, connectEpoch$delegate, mutableState3, scope, mutableState2, arrayDeque, outLock, outputBuffer, flushScheduled, atomicReference2, null), 3, null);
            return;
        }
        mutableState.setValue("Preview · live PTY disabled");
        TerminalScreen$lambda$5(mutableState2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TerminalScreen$sendKey(AtomicReference<TerminalSession> atomicReference, AtomicReference<WebSocket> atomicReference2, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, String seq) {
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = seq.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        TerminalSession s = atomicReference.get();
        if (s != null) {
            s.write(bytes, 0, bytes.length);
        } else {
            WebSocket webSocket = atomicReference2.get();
            if (webSocket != null) {
                if (!TerminalScreen$lambda$4(mutableState)) {
                    webSocket = null;
                }
                if (webSocket != null) {
                    webSocket.send(new Buffer().write(bytes).readByteString());
                }
            }
        }
        TerminalScreen$lambda$8(mutableState2, false);
    }

    private static final void TerminalScreen$applyFont(float[] baseFontForScale, AtomicReference<TerminalView> atomicReference, int[] appliedFontSp, AtomicReference<WebSocket> atomicReference2, MutableIntState fontSp$delegate, MutableState<Boolean> mutableState, MutableState<Pair<Integer, Integer>> mutableState2, MutableIntState connectEpoch$delegate, int size) {
        int clamped = RangesKt.coerceIn(size, 12, 28);
        fontSp$delegate.setIntValue(clamped);
        baseFontForScale[0] = clamped;
        TerminalView tv = atomicReference.get();
        if (tv == null) {
            return;
        }
        int px = TerminalFontMetrics.INSTANCE.spToPx(clamped, tv.getResources().getDisplayMetrics().scaledDensity);
        tv.setTextSize(px);
        appliedFontSp[0] = clamped;
        TerminalEmulator emu = tv.mEmulator;
        if (emu != null && TerminalScreen$lambda$4(mutableState)) {
            Pair geom = TuplesKt.to(Integer.valueOf(emu.mColumns), Integer.valueOf(emu.mRows));
            if (Intrinsics.areEqual(geom, TerminalScreen$lambda$22(mutableState2)) || ((Number) geom.getFirst()).intValue() < 20) {
                WebSocket webSocket = atomicReference2.get();
                if (webSocket != null) {
                    webSocket.send("{\"type\":\"resize\",\"rows\":" + emu.mRows + ",\"cols\":" + emu.mColumns + "}");
                    return;
                }
                return;
            }
            mutableState2.setValue(geom);
            connectEpoch$delegate.setIntValue(TerminalScreen$lambda$13(connectEpoch$delegate) + 1);
        }
    }

    static /* synthetic */ void TerminalScreen$hideKeyboard$default(AtomicReference atomicReference, TerminalView terminalView, int i, Object obj) {
        if ((i & 2) != 0) {
            terminalView = (TerminalView) atomicReference.get();
        }
        TerminalScreen$hideKeyboard(atomicReference, terminalView);
    }

    private static final void TerminalScreen$hideKeyboard(AtomicReference<TerminalView> atomicReference, TerminalView view) {
        if (view == null) {
            return;
        }
        Object systemService = view.getContext().getSystemService("input_method");
        InputMethodManager imm = systemService instanceof InputMethodManager ? (InputMethodManager) systemService : null;
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        view.clearFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TerminalScreen$showKeyboard(TerminalView view) {
        view.requestFocus();
        Object systemService = view.getContext().getSystemService("input_method");
        InputMethodManager imm = systemService instanceof InputMethodManager ? (InputMethodManager) systemService : null;
        if (imm != null) {
            imm.showSoftInput(view, 1);
        }
    }

    static /* synthetic */ void TerminalScreen$toggleKeyboard$default(AtomicReference atomicReference, boolean z, TerminalView terminalView, int i, Object obj) {
        if ((i & 4) != 0) {
            terminalView = (TerminalView) atomicReference.get();
        }
        TerminalScreen$toggleKeyboard(atomicReference, z, terminalView);
    }

    private static final void TerminalScreen$toggleKeyboard(AtomicReference<TerminalView> atomicReference, boolean imeOpen, TerminalView view) {
        if (view == null) {
            return;
        }
        if (imeOpen) {
            TerminalScreen$hideKeyboard(atomicReference, view);
        } else {
            TerminalScreen$showKeyboard(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$46$lambda$45(AtomicReference $viewRef) {
        TerminalScreen$hideKeyboard$default($viewRef, null, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult TerminalScreen$lambda$49$lambda$48(final boolean $demo, final AtomicReference $sessionRef, final AtomicReference $socketRef, final AtomicReference $clientRef, final AtomicReference $viewRef, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        return new DisposableEffectResult() { // from class: ai.nous.aurora.presentation.terminal.TerminalScreenKt$TerminalScreen$lambda$49$lambda$48$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Dispatcher dispatcher;
                ExecutorService executorService;
                TerminalScreenKt.TerminalScreen$hideKeyboard$default($viewRef, null, 2, null);
                if ($demo) {
                    return;
                }
                TerminalSession terminalSession = (TerminalSession) $sessionRef.getAndSet(null);
                if (terminalSession != null) {
                    terminalSession.finishIfRunning();
                }
                WebSocket webSocket = (WebSocket) $socketRef.getAndSet(null);
                if (webSocket != null) {
                    webSocket.close(1000, "bye");
                }
                OkHttpClient okHttpClient = (OkHttpClient) $clientRef.getAndSet(null);
                if (okHttpClient == null || (dispatcher = okHttpClient.dispatcher()) == null || (executorService = dispatcher.executorService()) == null) {
                    return;
                }
                executorService.shutdown();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$58$lambda$51$lambda$50(AtomicReference $viewRef, boolean $imeOpen) {
        TerminalScreen$toggleKeyboard$default($viewRef, $imeOpen, null, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$58$lambda$53$lambda$52(MutableIntState $fontSp$delegate, float[] $baseFontForScale, AtomicReference $viewRef, int[] $appliedFontSp, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $lastGeom$delegate, MutableIntState $connectEpoch$delegate) {
        TerminalScreen$applyFont($baseFontForScale, $viewRef, $appliedFontSp, $socketRef, $fontSp$delegate, $ptyReady$delegate, $lastGeom$delegate, $connectEpoch$delegate, TerminalScreen$lambda$16($fontSp$delegate) - 2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$58$lambda$55$lambda$54(MutableIntState $fontSp$delegate, float[] $baseFontForScale, AtomicReference $viewRef, int[] $appliedFontSp, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $lastGeom$delegate, MutableIntState $connectEpoch$delegate) {
        TerminalScreen$applyFont($baseFontForScale, $viewRef, $appliedFontSp, $socketRef, $fontSp$delegate, $ptyReady$delegate, $lastGeom$delegate, $connectEpoch$delegate, TerminalScreen$lambda$16($fontSp$delegate) + 2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$58$lambda$57$lambda$56(AtomicReference $viewRef, MutableIntState $connectEpoch$delegate) {
        TerminalScreen$hideKeyboard$default($viewRef, null, 2, null);
        $connectEpoch$delegate.setIntValue(TerminalScreen$lambda$13($connectEpoch$delegate) + 1);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TerminalView TerminalScreen$lambda$114$lambda$63$lambda$62(final int[] $appliedFontSp, AtomicReference $viewRef, TerminalScreenKt$TerminalScreen$sessionClient$1$1 $sessionClient, final AtomicReference $sessionRef, final MutableIntState $fontSp$delegate, final AtomicReference $socketRef, final boolean $demo, final ArrayDeque $pendingInput, final MutableState $ptyReady$delegate, final float[] $baseFontForScale, final MutableState $ctrl$delegate, final MutableState $lastGeom$delegate, final MutableIntState $geometryEpoch$delegate, Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        final TerminalView $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461 = new TerminalView(ctx, null);
        $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        Typeface mono = TerminalTypefaces.INSTANCE.mono(ctx);
        int px = TerminalFontMetrics.INSTANCE.spToPx(TerminalScreen$lambda$16($fontSp$delegate), $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.getResources().getDisplayMetrics().scaledDensity);
        $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.setTextSize(px);
        $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.setTypeface(mono);
        $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.setTextSize(px);
        $appliedFontSp[0] = TerminalScreen$lambda$16($fontSp$delegate);
        $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.setFocusable(true);
        $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.setFocusableInTouchMode(true);
        $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.setMinimumHeight((int) (48 * $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.getResources().getDisplayMetrics().density));
        $viewRef.set($this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461);
        TerminalSession session = new TerminalSession(TerminalSession.REMOTE_SHELL, "/opt/data", new String[]{TerminalSession.REMOTE_SHELL}, new String[]{"TERM=xterm-256color", "COLORTERM=truecolor", "HOME=/opt/data", "HERMES_HOME=/opt/data"}, 3000, $sessionClient);
        session.setRemoteIoCallback(new TerminalSession.RemoteIoCallback() { // from class: ai.nous.aurora.presentation.terminal.TerminalScreenKt$$ExternalSyntheticLambda26
            @Override // com.termux.terminal.TerminalSession.RemoteIoCallback
            public final void onClientOutput(byte[] bArr, int i, int i2) {
                TerminalScreenKt.TerminalScreen$lambda$114$lambda$63$lambda$62$lambda$61$lambda$60($socketRef, $demo, $pendingInput, $ptyReady$delegate, bArr, i, i2);
            }
        });
        $sessionRef.set(session);
        $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.setTerminalViewClient(new TerminalViewClient() { // from class: ai.nous.aurora.presentation.terminal.TerminalScreenKt$TerminalScreen$5$2$1$1$viewClient$1
            @Override // com.termux.view.TerminalViewClient
            public float onScale(float scale) {
                float base = $baseFontForScale[0];
                int newSize = RangesKt.coerceIn((int) (base * scale), 12, 28);
                int px2 = TerminalFontMetrics.INSTANCE.spToPx(newSize, $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.getResources().getDisplayMetrics().scaledDensity);
                $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.setTextSize(px2);
                $appliedFontSp[0] = newSize;
                $fontSp$delegate.setIntValue(newSize);
                if (Math.abs(scale - 1.0f) < 0.02f || scale < 0.5f || scale > 2.0f) {
                    $baseFontForScale[0] = newSize;
                    return 1.0f;
                }
                return scale;
            }

            @Override // com.termux.view.TerminalViewClient
            public void onSingleTapUp(MotionEvent e) {
                Intrinsics.checkNotNullParameter(e, "e");
                TerminalScreenKt.TerminalScreen$showKeyboard($this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461);
            }

            @Override // com.termux.view.TerminalViewClient
            public boolean shouldBackButtonBeMappedToEscape() {
                return false;
            }

            @Override // com.termux.view.TerminalViewClient
            public boolean shouldEnforceCharBasedInput() {
                return true;
            }

            @Override // com.termux.view.TerminalViewClient
            public boolean shouldUseCtrlSpaceWorkaround() {
                return false;
            }

            @Override // com.termux.view.TerminalViewClient
            public boolean isTerminalViewSelected() {
                return true;
            }

            @Override // com.termux.view.TerminalViewClient
            public void copyModeChanged(boolean copyMode) {
            }

            @Override // com.termux.view.TerminalViewClient
            public boolean onKeyDown(int keyCode, KeyEvent e, TerminalSession session2) {
                Intrinsics.checkNotNullParameter(e, "e");
                Intrinsics.checkNotNullParameter(session2, "session");
                return keyCode == 4 ? false : false;
            }

            @Override // com.termux.view.TerminalViewClient
            public boolean onKeyUp(int keyCode, KeyEvent e) {
                Intrinsics.checkNotNullParameter(e, "e");
                return keyCode == 4 ? false : false;
            }

            @Override // com.termux.view.TerminalViewClient
            public boolean onLongPress(MotionEvent event) {
                Intrinsics.checkNotNullParameter(event, "event");
                return false;
            }

            @Override // com.termux.view.TerminalViewClient
            public boolean readControlKey() {
                return TerminalScreenKt.TerminalScreen$lambda$7($ctrl$delegate);
            }

            @Override // com.termux.view.TerminalViewClient
            public boolean readAltKey() {
                return false;
            }

            @Override // com.termux.view.TerminalViewClient
            public boolean readShiftKey() {
                return false;
            }

            @Override // com.termux.view.TerminalViewClient
            public boolean readFnKey() {
                return false;
            }

            @Override // com.termux.view.TerminalViewClient
            public boolean onCodePoint(int codePoint, boolean ctrlDown, TerminalSession session2) {
                Intrinsics.checkNotNullParameter(session2, "session");
                if (TerminalScreenKt.TerminalScreen$lambda$7($ctrl$delegate)) {
                    if (97 <= codePoint && codePoint < 123) {
                        TerminalScreenKt.TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, String.valueOf((char) ((codePoint - 97) + 1)));
                        return true;
                    }
                }
                if (TerminalScreenKt.TerminalScreen$lambda$7($ctrl$delegate)) {
                    if (65 <= codePoint && codePoint < 91) {
                        TerminalScreenKt.TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, String.valueOf((char) ((codePoint - 65) + 1)));
                        return true;
                    }
                }
                return false;
            }

            @Override // com.termux.view.TerminalViewClient
            public void onEmulatorSet() {
                TerminalEmulator emu = $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.mEmulator;
                if (emu != null) {
                    Pair geom = TuplesKt.to(Integer.valueOf(emu.mColumns), Integer.valueOf(emu.mRows));
                    if (((Number) geom.getFirst()).intValue() < 20 || ((Number) geom.getSecond()).intValue() < 8 || Intrinsics.areEqual(geom, TerminalScreenKt.TerminalScreen$lambda$22($lastGeom$delegate))) {
                        return;
                    }
                    $lastGeom$delegate.setValue(geom);
                    if (!TerminalScreenKt.TerminalScreen$lambda$4($ptyReady$delegate)) {
                        $geometryEpoch$delegate.setIntValue(TerminalScreenKt.TerminalScreen$lambda$19($geometryEpoch$delegate) + 1);
                        return;
                    }
                    WebSocket webSocket = $socketRef.get();
                    if (webSocket != null) {
                        webSocket.send("{\"type\":\"resize\",\"rows\":" + emu.mRows + ",\"cols\":" + emu.mColumns + "}");
                    }
                }
            }

            @Override // com.termux.view.TerminalViewClient
            public void logError(String tag, String message) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                Intrinsics.checkNotNullParameter(message, "message");
            }

            @Override // com.termux.view.TerminalViewClient
            public void logWarn(String tag, String message) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                Intrinsics.checkNotNullParameter(message, "message");
            }

            @Override // com.termux.view.TerminalViewClient
            public void logInfo(String tag, String message) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                Intrinsics.checkNotNullParameter(message, "message");
            }

            @Override // com.termux.view.TerminalViewClient
            public void logDebug(String tag, String message) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                Intrinsics.checkNotNullParameter(message, "message");
            }

            @Override // com.termux.view.TerminalViewClient
            public void logVerbose(String tag, String message) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                Intrinsics.checkNotNullParameter(message, "message");
            }

            @Override // com.termux.view.TerminalViewClient
            public void logStackTraceWithMessage(String tag, String message, Exception e) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                Intrinsics.checkNotNullParameter(message, "message");
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // com.termux.view.TerminalViewClient
            public void logStackTrace(String tag, Exception e) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                Intrinsics.checkNotNullParameter(e, "e");
            }
        });
        $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461.attachSession(session);
        return $this$TerminalScreen_u24lambda_u24114_u24lambda_u2463_u24lambda_u2462_u24lambda_u2461;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TerminalScreen$lambda$114$lambda$63$lambda$62$lambda$61$lambda$60(AtomicReference $socketRef, boolean $demo, ArrayDeque $pendingInput, MutableState $ptyReady$delegate, byte[] data, int offset, int count) {
        byte[] slice;
        if (offset == 0 && count == data.length) {
            slice = data;
        } else {
            Intrinsics.checkNotNull(data);
            slice = ArraysKt.copyOfRange(data, offset, offset + count);
        }
        WebSocket sock = (WebSocket) $socketRef.get();
        if (sock != null && (TerminalScreen$lambda$4($ptyReady$delegate) || $demo)) {
            Buffer buffer = new Buffer();
            Intrinsics.checkNotNull(slice);
            sock.send(buffer.write(slice).readByteString());
        } else if (!$demo) {
            synchronized ($pendingInput) {
                if ($pendingInput.size() < 64) {
                    Intrinsics.checkNotNull(slice);
                    byte[] bArrCopyOf = Arrays.copyOf(slice, slice.length);
                    Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
                    $pendingInput.addLast(bArrCopyOf);
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$66$lambda$65(AtomicReference $viewRef, AtomicReference $sessionRef, int[] $appliedFontSp, MutableIntState $fontSp$delegate, TerminalView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        $viewRef.set(view);
        TerminalSession s = (TerminalSession) $sessionRef.get();
        if (s != null && view.mTermSession != s) {
            view.attachSession(s);
        }
        if ($appliedFontSp[0] != TerminalScreen$lambda$16($fontSp$delegate)) {
            TerminalTypefaces terminalTypefaces = TerminalTypefaces.INSTANCE;
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            Typeface mono = terminalTypefaces.mono(context);
            int px = TerminalFontMetrics.INSTANCE.spToPx(TerminalScreen$lambda$16($fontSp$delegate), view.getResources().getDisplayMetrics().scaledDensity);
            view.setTypeface(mono);
            view.setTextSize(px);
            $appliedFontSp[0] = TerminalScreen$lambda$16($fontSp$delegate);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$68$lambda$67(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\u001b");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$70$lambda$69(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\t");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$72$lambda$71(MutableState $ctrl$delegate) {
        TerminalScreen$lambda$8($ctrl$delegate, !TerminalScreen$lambda$7($ctrl$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$74$lambda$73(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\u0003");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$76$lambda$75(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\u001b[A");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$78$lambda$77(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\u001b[B");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$80$lambda$79(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\u001b[D");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$82$lambda$81(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\u001b[C");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$84$lambda$83(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\r");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$86$lambda$85(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\u007f");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$88$lambda$87(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\u0004");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$90$lambda$89(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\f");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$92$lambda$91(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\u001b[H");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$94$lambda$93(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\u001b[F");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$96$lambda$95(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\u001b[5~");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$98$lambda$97(AtomicReference $sessionRef, AtomicReference $socketRef, MutableState $ptyReady$delegate, MutableState $ctrl$delegate) {
        TerminalScreen$sendKey($sessionRef, $socketRef, $ptyReady$delegate, $ctrl$delegate, "\u001b[6~");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$107$lambda$100$lambda$99(MutableState $moreKeys$delegate, SemanticsPropertyReceiver semantics) {
        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
        SemanticsPropertiesKt.setContentDescription(semantics, TerminalSecondaryActions.INSTANCE.moreKeysContentDescription(TerminalScreen$lambda$10($moreKeys$delegate)));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$107$lambda$102$lambda$101(MutableState $moreKeys$delegate) {
        TerminalScreen$lambda$11($moreKeys$delegate, !TerminalScreen$lambda$10($moreKeys$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$107$lambda$104$lambda$103(SemanticsPropertyReceiver semantics) {
        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
        SemanticsPropertiesKt.setContentDescription(semantics, TerminalSecondaryActions.INSTANCE.showKeyboardContentDescription());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$108$lambda$107$lambda$106$lambda$105(AtomicReference $viewRef, boolean $imeOpen) {
        TerminalScreen$toggleKeyboard$default($viewRef, $imeOpen, null, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$113$lambda$110$lambda$109(SemanticsPropertyReceiver semantics) {
        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
        SemanticsPropertiesKt.setContentDescription(semantics, TerminalSecondaryActions.INSTANCE.hideKeyboardContentDescription());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TerminalScreen$lambda$114$lambda$113$lambda$112$lambda$111(AtomicReference $viewRef, boolean $imeOpen) {
        TerminalScreen$toggleKeyboard$default($viewRef, $imeOpen, null, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: EqualKeyRow-kHDZbjc, reason: not valid java name */
    private static final void m230EqualKeyRowkHDZbjc(final float height, final List<KeySpec> list, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        long fg;
        Object value$iv;
        Function0<ComposeUiNode> function1;
        Composer $composer2 = $composer.startRestartGroup(1692472104);
        ComposerKt.sourceInformation($composer2, "C(EqualKeyRow)P(0:c#ui.unit.Dp)801@36052L1478:TerminalScreen.kt#16qzv9");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(height) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(list) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1692472104, $dirty, -1, "ai.nous.aurora.presentation.terminal.EqualKeyRow (TerminalScreen.kt:800)");
            }
            Modifier modifier$iv = SizeKt.m1065height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ((Dp) RangesKt.coerceAtLeast(Dp.m7014boximpl(height), Dp.m7014boximpl(Dp.m7016constructorimpl(52)))).m7030unboximpl());
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.m914spacedBy0680j_4(Dp.m7016constructorimpl(4));
            int $changed$iv = 48;
            int $i$f$Row = 0;
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            String str = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            String str2 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
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
            int i2 = 0;
            ComposerKt.sourceInformationMarkerStart($composer2, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            int $changed2 = ((48 >> 6) & 112) | 6;
            RowScope $this$EqualKeyRow_kHDZbjc_u24lambda_u24120 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 234844787, "C:TerminalScreen.kt#16qzv9");
            $composer2.startReplaceGroup(1947238443);
            ComposerKt.sourceInformation($composer2, "*826@37049L47,814@36550L964");
            List<KeySpec> $this$forEach$iv = list;
            int $i$f$forEach = 0;
            for (Object element$iv : $this$forEach$iv) {
                Iterable $this$forEach$iv2 = $this$forEach$iv;
                final KeySpec key = (KeySpec) element$iv;
                long bg = key.getSelected() ? TerminalTheme.INSTANCE.m253getGold0d7_KjU() : TerminalTheme.INSTANCE.m258getKeySurface0d7_KjU();
                if (key.getSelected()) {
                    fg = TerminalTheme.INSTANCE.m260getOnGold0d7_KjU();
                } else {
                    fg = key.getDanger() ? TerminalTheme.INSTANCE.m250getDanger0d7_KjU() : TerminalTheme.INSTANCE.m253getGold0d7_KjU();
                }
                int $i$f$forEach2 = $i$f$forEach;
                int i3 = i2;
                int $changed3 = $changed2;
                int $changed$iv2 = $changed$iv;
                int $i$f$Row2 = $i$f$Row;
                Modifier modifierM622clickableXHw0xAI$default = ClickableKt.m622clickableXHw0xAI$default(BorderKt.m600borderxT4_qwU(BackgroundKt.m589backgroundbw27NRU$default(ClipKt.clip(SizeKt.m1067heightInVpY3zN4$default(SizeKt.fillMaxHeight$default(RowScope.weight$default($this$EqualKeyRow_kHDZbjc_u24lambda_u24120, Modifier.INSTANCE, 1.0f, false, 2, null), 0.0f, 1, null), Dp.m7016constructorimpl(52), 0.0f, 2, null), AuroraShapeKt.getAuroraShapeSm()), bg, null, 2, null), Dp.m7016constructorimpl(1), TerminalTheme.INSTANCE.m257getKeyBorder0d7_KjU(), AuroraShapeKt.getAuroraShapeSm()), false, key.getContentDescription(), null, key.getOnClick(), 5, null);
                $composer2.startReplaceGroup(2073262457);
                ComposerKt.sourceInformation($composer2, "CC(remember):TerminalScreen.kt#9igjgp");
                boolean invalid$iv = $composer2.changed(key);
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new Function1() { // from class: ai.nous.aurora.presentation.terminal.TerminalScreenKt$$ExternalSyntheticLambda24
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return TerminalScreenKt.EqualKeyRow_kHDZbjc$lambda$120$lambda$119$lambda$117$lambda$116(key, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                $composer2.endReplaceGroup();
                Modifier modifier$iv2 = SemanticsModifierKt.semantics$default(modifierM622clickableXHw0xAI$default, false, (Function1) value$iv, 1, null);
                Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (48 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str);
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                String str3 = str;
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, str2);
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
                String str4 = str2;
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4027constructorimpl($composer2);
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4034setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i5 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1294133885, "C829@37183L317:TerminalScreen.kt#16qzv9");
                TextKt.m3067Text4IGK_g(key.getLabel(), (Modifier) null, fg, TextUnitKt.getSp(13), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, TextAlign.m6876boximpl(TextAlign.INSTANCE.m6883getCentere0LSkKk()), 0L, 0, false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, 199680, 3072, 122258);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $i$f$Row = $i$f$Row2;
                $changed2 = $changed3;
                i2 = i3;
                $changed$iv = $changed$iv2;
                $this$forEach$iv = $this$forEach$iv2;
                $i$f$forEach = $i$f$forEach2;
                str = str3;
                str2 = str4;
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ai.nous.aurora.presentation.terminal.TerminalScreenKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TerminalScreenKt.EqualKeyRow_kHDZbjc$lambda$121(height, list, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EqualKeyRow_kHDZbjc$lambda$120$lambda$119$lambda$117$lambda$116(KeySpec $key, SemanticsPropertyReceiver semantics) {
        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
        SemanticsPropertiesKt.setContentDescription(semantics, $key.getContentDescription());
        return Unit.INSTANCE;
    }
}
