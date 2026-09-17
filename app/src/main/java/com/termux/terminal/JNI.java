package com.termux.terminal;

/* JADX INFO: loaded from: classes3.dex */
final class JNI {
    JNI() {
    }

    public static int createSubprocess(String cmd, String cwd, String[] args, String[] envVars, int[] processId, int rows, int columns, int cellWidth, int cellHeight) {
        throw new UnsupportedOperationException("Local PTY not available — use TerminalSession.REMOTE_SHELL");
    }

    public static void setPtyWindowSize(int fd, int rows, int cols, int cellWidth, int cellHeight) {
    }

    public static int waitFor(int processId) {
        return 0;
    }

    public static void close(int fileDescriptor) {
    }
}
