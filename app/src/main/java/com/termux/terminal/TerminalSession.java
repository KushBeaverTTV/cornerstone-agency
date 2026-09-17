package com.termux.terminal;

import ai.nous.aurora.presentation.theme.AuroraMotion;
import android.os.Handler;
import android.os.Message;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public final class TerminalSession extends TerminalOutput {
    private static final String LOG_TAG = "TerminalSession";
    private static final int MSG_NEW_INPUT = 1;
    private static final int MSG_PROCESS_EXITED = 4;
    public static final String REMOTE_SHELL = "remote://bridge";
    private final String[] mArgs;
    TerminalSessionClient mClient;
    private final String mCwd;
    TerminalEmulator mEmulator;
    private final String[] mEnv;
    private final boolean mRemote;
    private RemoteIoCallback mRemoteIo;
    public String mSessionName;
    int mShellExitStatus;
    private final String mShellPath;
    int mShellPid;
    private final Integer mTranscriptRows;
    public final String mHandle = UUID.randomUUID().toString();
    final ByteQueue mProcessToTerminalIOQueue = new ByteQueue(65536);
    final ByteQueue mTerminalToProcessIOQueue = new ByteQueue(4096);
    private final byte[] mUtf8InputBuffer = new byte[5];
    private int mTerminalFileDescriptor = -1;
    final Handler mMainThreadHandler = new MainThreadHandler();

    public interface RemoteIoCallback {
        void onClientOutput(byte[] bArr, int i, int i2);
    }

    public TerminalSession(String shellPath, String cwd, String[] args, String[] env, Integer transcriptRows, TerminalSessionClient client) {
        this.mShellPath = shellPath;
        this.mCwd = cwd;
        this.mArgs = args;
        this.mEnv = env;
        this.mTranscriptRows = transcriptRows;
        this.mClient = client;
        this.mRemote = REMOTE_SHELL.equals(shellPath);
    }

    public boolean isRemote() {
        return this.mRemote;
    }

    public void setRemoteIoCallback(RemoteIoCallback cb) {
        this.mRemoteIo = cb;
    }

    public void writeToEmulator(byte[] data, int length) {
        if (this.mEmulator == null || data == null || length <= 0) {
            return;
        }
        this.mEmulator.append(data, length);
        notifyScreenUpdate();
    }

    public void writeToEmulator(String utf8) {
        if (utf8 == null || utf8.isEmpty()) {
            return;
        }
        byte[] bytes = utf8.getBytes(StandardCharsets.UTF_8);
        writeToEmulator(bytes, bytes.length);
    }

    public void updateTerminalSessionClient(TerminalSessionClient client) {
        this.mClient = client;
        if (this.mEmulator != null) {
            this.mEmulator.updateTerminalSessionClient(client);
        }
    }

    public void updateSize(int columns, int rows, int cellWidthPixels, int cellHeightPixels) {
        if (this.mEmulator == null) {
            initializeEmulator(columns, rows, cellWidthPixels, cellHeightPixels);
            return;
        }
        if (!this.mRemote && this.mTerminalFileDescriptor >= 0) {
            JNI.setPtyWindowSize(this.mTerminalFileDescriptor, rows, columns, cellWidthPixels, cellHeightPixels);
        }
        this.mEmulator.resize(columns, rows, cellWidthPixels, cellHeightPixels);
    }

    public String getTitle() {
        if (this.mEmulator == null) {
            return null;
        }
        return this.mEmulator.getTitle();
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [com.termux.terminal.TerminalSession$1] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.termux.terminal.TerminalSession$2] */
    /* JADX WARN: Type inference failed for: r2v6, types: [com.termux.terminal.TerminalSession$3] */
    public void initializeEmulator(int columns, int rows, int cellWidthPixels, int cellHeightPixels) {
        this.mEmulator = new TerminalEmulator(this, columns, rows, cellWidthPixels, cellHeightPixels, this.mTranscriptRows, this.mClient);
        if (this.mRemote) {
            this.mShellPid = 1;
            if (this.mClient != null) {
                this.mClient.setTerminalShellPid(this, this.mShellPid);
                return;
            }
            return;
        }
        int[] processId = new int[1];
        this.mTerminalFileDescriptor = JNI.createSubprocess(this.mShellPath, this.mCwd, this.mArgs, this.mEnv, processId, rows, columns, cellWidthPixels, cellHeightPixels);
        this.mShellPid = processId[0];
        this.mClient.setTerminalShellPid(this, this.mShellPid);
        final FileDescriptor terminalFileDescriptorWrapped = wrapFileDescriptor(this.mTerminalFileDescriptor, this.mClient);
        new Thread("TermSessionInputReader[pid=" + this.mShellPid + "]") { // from class: com.termux.terminal.TerminalSession.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    InputStream termIn = new FileInputStream(terminalFileDescriptorWrapped);
                    try {
                        byte[] buffer = new byte[4096];
                        while (true) {
                            int read = termIn.read(buffer);
                            if (read != -1) {
                                if (TerminalSession.this.mProcessToTerminalIOQueue.write(buffer, 0, read)) {
                                    TerminalSession.this.mMainThreadHandler.sendEmptyMessage(1);
                                } else {
                                    termIn.close();
                                    return;
                                }
                            } else {
                                termIn.close();
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            termIn.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                Object e = null;
                } catch (Exception e) {
                }
            }
        }.start();
        new Thread("TermSessionOutputWriter[pid=" + this.mShellPid + "]") { // from class: com.termux.terminal.TerminalSession.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                byte[] buffer = new byte[4096];
                try {
                    FileOutputStream termOut = new FileOutputStream(terminalFileDescriptorWrapped);
                    while (true) {
                        try {
                            int bytesToWrite = TerminalSession.this.mTerminalToProcessIOQueue.read(buffer, true);
                            if (bytesToWrite != -1) {
                                termOut.write(buffer, 0, bytesToWrite);
                            } else {
                                termOut.close();
                                return;
                            }
                        } catch (Throwable th) {
                            try {
                                termOut.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    }
                } catch (IOException e) {
                }
            }
        }.start();
        new Thread("TermSessionWaiter[pid=" + this.mShellPid + "]") { // from class: com.termux.terminal.TerminalSession.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                int processExitCode = JNI.waitFor(TerminalSession.this.mShellPid);
                TerminalSession.this.mMainThreadHandler.sendMessage(TerminalSession.this.mMainThreadHandler.obtainMessage(4, Integer.valueOf(processExitCode)));
            }
        }.start();
    }

    @Override // com.termux.terminal.TerminalOutput
    public void write(byte[] data, int offset, int count) {
        if (this.mRemote) {
            if (this.mRemoteIo == null || count <= 0) {
                return;
            }
            this.mRemoteIo.onClientOutput(data, offset, count);
            return;
        }
        if (this.mShellPid > 0) {
            this.mTerminalToProcessIOQueue.write(data, offset, count);
        }
    }

    public void writeCodePoint(boolean prependEscape, int codePoint) {
        int bufferPosition;
        if (codePoint > 1114111 || (codePoint >= 55296 && codePoint <= 57343)) {
            throw new IllegalArgumentException("Invalid code point: " + codePoint);
        }
        int bufferPosition2 = 0;
        if (prependEscape) {
            int bufferPosition3 = 0 + 1;
            this.mUtf8InputBuffer[0] = 27;
            bufferPosition2 = bufferPosition3;
        }
        if (codePoint <= 127) {
            bufferPosition = bufferPosition2 + 1;
            this.mUtf8InputBuffer[bufferPosition2] = (byte) codePoint;
        } else if (codePoint <= 2047) {
            int bufferPosition4 = bufferPosition2 + 1;
            this.mUtf8InputBuffer[bufferPosition2] = (byte) ((codePoint >> 6) | 192);
            this.mUtf8InputBuffer[bufferPosition4] = (byte) ((codePoint & 63) | 128);
            bufferPosition = bufferPosition4 + 1;
        } else if (codePoint <= 65535) {
            int bufferPosition5 = bufferPosition2 + 1;
            this.mUtf8InputBuffer[bufferPosition2] = (byte) ((codePoint >> 12) | 224);
            int bufferPosition6 = bufferPosition5 + 1;
            this.mUtf8InputBuffer[bufferPosition5] = (byte) (((codePoint >> 6) & 63) | 128);
            bufferPosition = bufferPosition6 + 1;
            this.mUtf8InputBuffer[bufferPosition6] = (byte) ((codePoint & 63) | 128);
        } else {
            int bufferPosition7 = bufferPosition2 + 1;
            this.mUtf8InputBuffer[bufferPosition2] = (byte) ((codePoint >> 18) | AuroraMotion.TAB_MS);
            int bufferPosition8 = bufferPosition7 + 1;
            this.mUtf8InputBuffer[bufferPosition7] = (byte) (((codePoint >> 12) & 63) | 128);
            int bufferPosition9 = bufferPosition8 + 1;
            this.mUtf8InputBuffer[bufferPosition8] = (byte) (((codePoint >> 6) & 63) | 128);
            this.mUtf8InputBuffer[bufferPosition9] = (byte) ((codePoint & 63) | 128);
            bufferPosition = bufferPosition9 + 1;
        }
        write(this.mUtf8InputBuffer, 0, bufferPosition);
    }

    public TerminalEmulator getEmulator() {
        return this.mEmulator;
    }

    protected void notifyScreenUpdate() {
        if (this.mClient != null) {
            this.mClient.onTextChanged(this);
        }
    }

    public void reset() {
        if (this.mEmulator != null) {
            this.mEmulator.reset();
            notifyScreenUpdate();
        }
    }

    public void finishIfRunning() {
        if (this.mRemote) {
            synchronized (this) {
                this.mShellPid = -1;
            }
            if (this.mClient != null) {
                this.mClient.onSessionFinished(this);
                return;
            }
            return;
        }
        if (isRunning()) {
            try {
                Os.kill(this.mShellPid, OsConstants.SIGKILL);
            } catch (ErrnoException e) {
                Logger.logWarn(this.mClient, LOG_TAG, "Failed sending SIGKILL: " + e.getMessage());
            }
        }
    }

    void cleanupResources(int exitStatus) {
        synchronized (this) {
            this.mShellPid = -1;
            this.mShellExitStatus = exitStatus;
        }
        this.mTerminalToProcessIOQueue.close();
        this.mProcessToTerminalIOQueue.close();
        if (!this.mRemote && this.mTerminalFileDescriptor >= 0) {
            JNI.close(this.mTerminalFileDescriptor);
            this.mTerminalFileDescriptor = -1;
        }
    }

    @Override // com.termux.terminal.TerminalOutput
    public void titleChanged(String oldTitle, String newTitle) {
        if (this.mClient != null) {
            this.mClient.onTitleChanged(this);
        }
    }

    public synchronized boolean isRunning() {
        return this.mShellPid != -1;
    }

    public synchronized int getExitStatus() {
        return this.mShellExitStatus;
    }

    @Override // com.termux.terminal.TerminalOutput
    public void onCopyTextToClipboard(String text) {
        if (this.mClient != null) {
            this.mClient.onCopyTextToClipboard(this, text);
        }
    }

    @Override // com.termux.terminal.TerminalOutput
    public void onPasteTextFromClipboard() {
        if (this.mClient != null) {
            this.mClient.onPasteTextFromClipboard(this);
        }
    }

    @Override // com.termux.terminal.TerminalOutput
    public void onBell() {
        if (this.mClient != null) {
            this.mClient.onBell(this);
        }
    }

    @Override // com.termux.terminal.TerminalOutput
    public void onColorsChanged() {
        if (this.mClient != null) {
            this.mClient.onColorsChanged(this);
        }
    }

    public int getPid() {
        return this.mShellPid;
    }

    public String getCwd() {
        if (this.mRemote || this.mShellPid < 1) {
            return this.mCwd;
        }
        try {
            String cwdSymlink = String.format("/proc/%s/cwd/", Integer.valueOf(this.mShellPid));
            String outputPath = new File(cwdSymlink).getCanonicalPath();
            String outputPathWithTrailingSlash = outputPath;
            if (!outputPath.endsWith("/")) {
                outputPathWithTrailingSlash = outputPathWithTrailingSlash + '/';
            }
            if (!cwdSymlink.equals(outputPathWithTrailingSlash)) {
                return outputPath;
            }
            return null;
        } catch (IOException | SecurityException e) {
            Logger.logStackTraceWithMessage(this.mClient, LOG_TAG, "Error getting current directory", e);
            return null;
        }
    }

    private static FileDescriptor wrapFileDescriptor(int fileDescriptor, TerminalSessionClient client) {
        Field descriptorField;
        FileDescriptor result = new FileDescriptor();
        try {
            try {
                descriptorField = FileDescriptor.class.getDeclaredField("descriptor");
            } catch (NoSuchFieldException e) {
                try {
                    descriptorField = FileDescriptor.class.getDeclaredField("fd");
                } catch (NoSuchFieldException e2) {
                    Logger.logStackTraceWithMessage(client, LOG_TAG, "Error accessing FileDescriptor#descriptor private field", e2);
                    System.exit(1);
                }
            }
            descriptorField.setAccessible(true);
            descriptorField.set(result, Integer.valueOf(fileDescriptor));
        } catch (IllegalAccessException e3) {
            Logger.logStackTraceWithMessage(client, LOG_TAG, "Error accessing FileDescriptor#descriptor private field", e3);
            System.exit(1);
        } catch (IllegalArgumentException e4) {
            Logger.logStackTraceWithMessage(client, LOG_TAG, "Error accessing FileDescriptor#descriptor private field", e4);
            System.exit(1);
        }
        return result;
    }

    class MainThreadHandler extends Handler {
        final byte[] mReceiveBuffer = new byte[65536];

        MainThreadHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int bytesRead = TerminalSession.this.mProcessToTerminalIOQueue.read(this.mReceiveBuffer, false);
            if (bytesRead > 0 && TerminalSession.this.mEmulator != null) {
                TerminalSession.this.mEmulator.append(this.mReceiveBuffer, bytesRead);
                TerminalSession.this.notifyScreenUpdate();
            }
            if (msg.what == 4) {
                int exitCode = ((Integer) msg.obj).intValue();
                TerminalSession.this.cleanupResources(exitCode);
                String exitDescription = "\r\n[Process completed";
                if (exitCode > 0) {
                    exitDescription = "\r\n[Process completed (code " + exitCode + ")";
                } else if (exitCode < 0) {
                    exitDescription = "\r\n[Process completed (signal " + (-exitCode) + ")";
                }
                byte[] bytesToWrite = (exitDescription + " - press Enter]").getBytes(StandardCharsets.UTF_8);
                if (TerminalSession.this.mEmulator != null) {
                    TerminalSession.this.mEmulator.append(bytesToWrite, bytesToWrite.length);
                    TerminalSession.this.notifyScreenUpdate();
                }
                if (TerminalSession.this.mClient != null) {
                    TerminalSession.this.mClient.onSessionFinished(TerminalSession.this);
                }
            }
        }
    }
}
