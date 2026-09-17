package com.termux.view;

import android.view.KeyEvent;
import android.view.MotionEvent;
import com.termux.terminal.TerminalSession;

/* JADX INFO: loaded from: classes3.dex */
public interface TerminalViewClient {
    void copyModeChanged(boolean z);

    boolean isTerminalViewSelected();

    void logDebug(String str, String str2);

    void logError(String str, String str2);

    void logInfo(String str, String str2);

    void logStackTrace(String str, Exception exc);

    void logStackTraceWithMessage(String str, String str2, Exception exc);

    void logVerbose(String str, String str2);

    void logWarn(String str, String str2);

    boolean onCodePoint(int i, boolean z, TerminalSession terminalSession);

    void onEmulatorSet();

    boolean onKeyDown(int i, KeyEvent keyEvent, TerminalSession terminalSession);

    boolean onKeyUp(int i, KeyEvent keyEvent);

    boolean onLongPress(MotionEvent motionEvent);

    float onScale(float f);

    void onSingleTapUp(MotionEvent motionEvent);

    boolean readAltKey();

    boolean readControlKey();

    boolean readFnKey();

    boolean readShiftKey();

    boolean shouldBackButtonBeMappedToEscape();

    boolean shouldEnforceCharBasedInput();

    boolean shouldUseCtrlSpaceWorkaround();
}
