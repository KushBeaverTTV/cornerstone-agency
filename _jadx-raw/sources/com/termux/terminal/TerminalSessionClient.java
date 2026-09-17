package com.termux.terminal;

/* JADX INFO: loaded from: classes3.dex */
public interface TerminalSessionClient {
    Integer getTerminalCursorStyle();

    void logDebug(String str, String str2);

    void logError(String str, String str2);

    void logInfo(String str, String str2);

    void logStackTrace(String str, Exception exc);

    void logStackTraceWithMessage(String str, String str2, Exception exc);

    void logVerbose(String str, String str2);

    void logWarn(String str, String str2);

    void onBell(TerminalSession terminalSession);

    void onColorsChanged(TerminalSession terminalSession);

    void onCopyTextToClipboard(TerminalSession terminalSession, String str);

    void onPasteTextFromClipboard(TerminalSession terminalSession);

    void onSessionFinished(TerminalSession terminalSession);

    void onTerminalCursorStateChange(boolean z);

    void onTextChanged(TerminalSession terminalSession);

    void onTitleChanged(TerminalSession terminalSession);

    void setTerminalShellPid(TerminalSession terminalSession, int i);
}
