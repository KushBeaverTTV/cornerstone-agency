package com.termux.terminal;

import ai.nous.aurora.domain.model.ComposerChipPolicy;
import ai.nous.aurora.domain.model.ThreadVoidDensityPolicy;
import ai.nous.aurora.presentation.theme.AuroraMotion;
import androidx.core.location.LocationRequestCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Stack;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.Utf8;

/* JADX INFO: loaded from: classes3.dex */
public final class TerminalEmulator {
    private static final int DECSET_BIT_APPLICATION_CURSOR_KEYS = 1;
    private static final int DECSET_BIT_APPLICATION_KEYPAD = 32;
    private static final int DECSET_BIT_AUTOWRAP = 8;
    private static final int DECSET_BIT_BRACKETED_PASTE_MODE = 1024;
    private static final int DECSET_BIT_CURSOR_ENABLED = 16;
    private static final int DECSET_BIT_LEFTRIGHT_MARGIN_MODE = 2048;
    private static final int DECSET_BIT_MOUSE_PROTOCOL_SGR = 512;
    private static final int DECSET_BIT_MOUSE_TRACKING_BUTTON_EVENT = 128;
    private static final int DECSET_BIT_MOUSE_TRACKING_PRESS_RELEASE = 64;
    private static final int DECSET_BIT_ORIGIN_MODE = 4;
    private static final int DECSET_BIT_RECTANGULAR_CHANGEATTRIBUTE = 4096;
    private static final int DECSET_BIT_REVERSE_VIDEO = 2;
    private static final int DECSET_BIT_SEND_FOCUS_EVENTS = 256;
    public static final int DEFAULT_TERMINAL_CURSOR_STYLE = 0;
    public static final int DEFAULT_TERMINAL_TRANSCRIPT_ROWS = 2000;
    private static final int ESC = 1;
    private static final int ESC_APC = 20;
    private static final int ESC_APC_ESCAPE = 21;
    private static final int ESC_CSI = 6;
    private static final int ESC_CSI_ARGS_ASTERIX = 16;
    private static final int ESC_CSI_ARGS_SPACE = 15;
    private static final int ESC_CSI_BIGGERTHAN = 12;
    private static final int ESC_CSI_DOLLAR = 8;
    private static final int ESC_CSI_DOUBLE_QUOTE = 17;
    private static final int ESC_CSI_EXCLAMATION = 19;
    private static final int ESC_CSI_QUESTIONMARK = 7;
    private static final int ESC_CSI_QUESTIONMARK_ARG_DOLLAR = 14;
    private static final int ESC_CSI_SINGLE_QUOTE = 18;
    private static final int ESC_CSI_UNSUPPORTED_INTERMEDIATE_BYTE = 23;
    private static final int ESC_CSI_UNSUPPORTED_PARAMETER_BYTE = 22;
    private static final int ESC_NONE = 0;
    private static final int ESC_OSC = 10;
    private static final int ESC_OSC_ESC = 11;
    private static final int ESC_P = 13;
    private static final int ESC_PERCENT = 9;
    private static final int ESC_POUND = 2;
    private static final int ESC_SELECT_LEFT_PAREN = 3;
    private static final int ESC_SELECT_RIGHT_PAREN = 4;
    private static final boolean LOG_ESCAPE_SEQUENCES = false;
    private static final String LOG_TAG = "TerminalEmulator";
    private static final int MAX_ESCAPE_PARAMETERS = 32;
    private static final int MAX_OSC_STRING_LENGTH = 8192;
    public static final int MOUSE_LEFT_BUTTON = 0;
    public static final int MOUSE_LEFT_BUTTON_MOVED = 32;
    public static final int MOUSE_WHEELDOWN_BUTTON = 65;
    public static final int MOUSE_WHEELUP_BUTTON = 64;
    public static final Integer[] TERMINAL_CURSOR_STYLES_LIST = {0, 1, 2};
    public static final int TERMINAL_CURSOR_STYLE_BAR = 2;
    public static final int TERMINAL_CURSOR_STYLE_BLOCK = 0;
    public static final int TERMINAL_CURSOR_STYLE_UNDERLINE = 1;
    public static final int TERMINAL_TRANSCRIPT_ROWS_MAX = 50000;
    public static final int TERMINAL_TRANSCRIPT_ROWS_MIN = 100;
    public static final int UNICODE_REPLACEMENT_CHAR = 65533;
    public boolean mAboutToAutoWrap;
    final TerminalBuffer mAltBuffer;
    public int mArgIndex;
    public boolean mAutoScrollDisabled;
    int mBackColor;
    public int mBottomMargin;
    public int mCellHeightPixels;
    public int mCellWidthPixels;
    TerminalSessionClient mClient;
    public int mColumns;
    public boolean mContinueSequence;
    public int mCurrentDecSetFlags;
    public boolean mCursorBlinkState;
    public boolean mCursorBlinkingEnabled;
    public int mCursorCol;
    public int mCursorRow;
    int mEffect;
    public int mEscapeState;
    int mForeColor;
    public boolean mInsertMode;
    public int mLeftMargin;
    public final TerminalBuffer mMainBuffer;
    public int mRightMargin;
    public int mRows;
    public int mSavedDecSetFlags;
    public TerminalBuffer mScreen;
    public final TerminalOutput mSession;
    public boolean[] mTabStop;
    public String mTitle;
    public int mTopMargin;
    int mUnderlineColor;
    public boolean mUseLineDrawingG0;
    public boolean mUseLineDrawingG1;
    public byte mUtf8Index;
    public byte mUtf8ToFollow;
    private final Stack<String> mTitleStack = new Stack<>();
    private int mCursorStyle = 0;
    private final int[] mArgs = new int[32];
    private int mArgsSubParamsBitSet = 0;
    private final StringBuilder mOSCOrDeviceControlArgs = new StringBuilder();
    private final SavedScreenState mSavedStateMain = new SavedScreenState();
    private final SavedScreenState mSavedStateAlt = new SavedScreenState();
    private boolean mUseLineDrawingUsesG0 = true;
    private int mScrollCounter = 0;
    private final byte[] mUtf8InputBuffer = new byte[4];
    private int mLastEmittedCodePoint = -1;
    public final TerminalColors mColors = new TerminalColors();

    private boolean isDecsetInternalBitSet(int bit) {
        return (this.mCurrentDecSetFlags & bit) != 0;
    }

    private void setDecsetinternalBit(int internalBit, boolean set) {
        if (set) {
            if (internalBit == 64) {
                setDecsetinternalBit(128, false);
            } else if (internalBit == 128) {
                setDecsetinternalBit(64, false);
            }
        }
        if (set) {
            this.mCurrentDecSetFlags |= internalBit;
        } else {
            this.mCurrentDecSetFlags &= ~internalBit;
        }
    }

    static int mapDecSetBitToInternalBit(int decsetBit) {
        switch (decsetBit) {
            case 1:
                return 1;
            case 5:
                return 2;
            case 6:
                return 4;
            case 7:
                return 8;
            case 25:
                return 16;
            case 66:
                return 32;
            case 69:
                return 2048;
            case 1000:
                return 64;
            case PointerIconCompat.TYPE_HAND /* 1002 */:
                return 128;
            case PointerIconCompat.TYPE_WAIT /* 1004 */:
                return 256;
            case PointerIconCompat.TYPE_CELL /* 1006 */:
                return 512;
            case 2004:
                return 1024;
            default:
                return -1;
        }
    }

    public TerminalEmulator(TerminalOutput session, int columns, int rows, int cellWidthPixels, int cellHeightPixels, Integer transcriptRows, TerminalSessionClient client) {
        this.mSession = session;
        TerminalBuffer terminalBuffer = new TerminalBuffer(columns, getTerminalTranscriptRows(transcriptRows), rows);
        this.mMainBuffer = terminalBuffer;
        this.mScreen = terminalBuffer;
        this.mAltBuffer = new TerminalBuffer(columns, rows, rows);
        this.mClient = client;
        this.mRows = rows;
        this.mColumns = columns;
        this.mCellWidthPixels = cellWidthPixels;
        this.mCellHeightPixels = cellHeightPixels;
        this.mTabStop = new boolean[this.mColumns];
        reset();
    }

    public void updateTerminalSessionClient(TerminalSessionClient client) {
        this.mClient = client;
        setCursorStyle();
        setCursorBlinkState(true);
    }

    public TerminalBuffer getScreen() {
        return this.mScreen;
    }

    public boolean isAlternateBufferActive() {
        return this.mScreen == this.mAltBuffer;
    }

    private int getTerminalTranscriptRows(Integer transcriptRows) {
        if (transcriptRows == null || transcriptRows.intValue() < 100 || transcriptRows.intValue() > 50000) {
            return 2000;
        }
        return transcriptRows.intValue();
    }

    public void sendMouseEvent(int mouseButton, int column, int row, boolean pressed) {
        if (column < 1) {
            column = 1;
        }
        if (column > this.mColumns) {
            column = this.mColumns;
        }
        if (row < 1) {
            row = 1;
        }
        if (row > this.mRows) {
            row = this.mRows;
        }
        if (mouseButton != 32 || isDecsetInternalBitSet(128)) {
            if (isDecsetInternalBitSet(512)) {
                this.mSession.write(String.format("\u001b[<%d;%d;%d" + (pressed ? 'M' : 'm'), Integer.valueOf(mouseButton), Integer.valueOf(column), Integer.valueOf(row)));
                return;
            }
            int mouseButton2 = pressed ? mouseButton : 3;
            boolean out_of_bounds = column > 223 || row > 223;
            if (!out_of_bounds) {
                byte[] data = {27, 91, 77, (byte) (mouseButton2 + 32), (byte) (column + 32), (byte) (row + 32)};
                this.mSession.write(data, 0, data.length);
            }
        }
    }

    public void resize(int columns, int rows, int cellWidthPixels, int cellHeightPixels) {
        this.mCellWidthPixels = cellWidthPixels;
        this.mCellHeightPixels = cellHeightPixels;
        if (this.mRows == rows && this.mColumns == columns) {
            return;
        }
        if (columns < 2 || rows < 2) {
            throw new IllegalArgumentException("rows=" + rows + ", columns=" + columns);
        }
        if (this.mRows != rows) {
            this.mRows = rows;
            this.mTopMargin = 0;
            this.mBottomMargin = this.mRows;
        }
        if (this.mColumns != columns) {
            int oldColumns = this.mColumns;
            this.mColumns = columns;
            boolean[] oldTabStop = this.mTabStop;
            this.mTabStop = new boolean[this.mColumns];
            setDefaultTabStops();
            int toTransfer = Math.min(oldColumns, columns);
            System.arraycopy(oldTabStop, 0, this.mTabStop, 0, toTransfer);
            this.mLeftMargin = 0;
            this.mRightMargin = this.mColumns;
        }
        resizeScreen();
    }

    private void resizeScreen() {
        int[] cursor = {this.mCursorCol, this.mCursorRow};
        int newTotalRows = this.mScreen == this.mAltBuffer ? this.mRows : this.mMainBuffer.mTotalRows;
        this.mScreen.resize(this.mColumns, this.mRows, newTotalRows, cursor, getStyle(), isAlternateBufferActive());
        this.mCursorCol = cursor[0];
        this.mCursorRow = cursor[1];
    }

    public int getCursorRow() {
        return this.mCursorRow;
    }

    public int getCursorCol() {
        return this.mCursorCol;
    }

    public int getCursorStyle() {
        return this.mCursorStyle;
    }

    public void setCursorStyle() {
        Integer cursorStyle = null;
        if (this.mClient != null) {
            cursorStyle = this.mClient.getTerminalCursorStyle();
        }
        if (cursorStyle == null || !Arrays.asList(TERMINAL_CURSOR_STYLES_LIST).contains(cursorStyle)) {
            this.mCursorStyle = 0;
        } else {
            this.mCursorStyle = cursorStyle.intValue();
        }
    }

    public boolean isReverseVideo() {
        return isDecsetInternalBitSet(2);
    }

    public boolean isCursorEnabled() {
        return isDecsetInternalBitSet(16);
    }

    public boolean shouldCursorBeVisible() {
        if (!isCursorEnabled()) {
            return false;
        }
        if (this.mCursorBlinkingEnabled) {
            return this.mCursorBlinkState;
        }
        return true;
    }

    public void setCursorBlinkingEnabled(boolean cursorBlinkingEnabled) {
        this.mCursorBlinkingEnabled = cursorBlinkingEnabled;
    }

    public void setCursorBlinkState(boolean cursorBlinkState) {
        this.mCursorBlinkState = cursorBlinkState;
    }

    public boolean isKeypadApplicationMode() {
        return isDecsetInternalBitSet(32);
    }

    public boolean isCursorKeysApplicationMode() {
        return isDecsetInternalBitSet(1);
    }

    public boolean isMouseTrackingActive() {
        return isDecsetInternalBitSet(64) || isDecsetInternalBitSet(128);
    }

    private void setDefaultTabStops() {
        int i = 0;
        while (i < this.mColumns) {
            this.mTabStop[i] = (i & 7) == 0 && i != 0;
            i++;
        }
    }

    public void append(byte[] buffer, int length) {
        for (int i = 0; i < length; i++) {
            processByte(buffer[i]);
        }
    }

    private void processByte(byte byteToProcess) {
        if (this.mUtf8ToFollow > 0) {
            if ((byteToProcess & 192) == 128) {
                byte[] bArr = this.mUtf8InputBuffer;
                byte b = this.mUtf8Index;
                this.mUtf8Index = (byte) (b + 1);
                bArr[b] = byteToProcess;
                byte b2 = (byte) (this.mUtf8ToFollow - 1);
                this.mUtf8ToFollow = b2;
                if (b2 == 0) {
                    byte firstByteMask = (byte) (this.mUtf8Index == 2 ? 31 : this.mUtf8Index == 3 ? 15 : 7);
                    int codePoint = this.mUtf8InputBuffer[0] & firstByteMask;
                    for (int i = 1; i < this.mUtf8Index; i++) {
                        codePoint = (codePoint << 6) | (this.mUtf8InputBuffer[i] & Utf8.REPLACEMENT_BYTE);
                    }
                    if ((codePoint <= 127 && this.mUtf8Index > 1) || ((codePoint < 2047 && this.mUtf8Index > 2) || (codePoint < 65535 && this.mUtf8Index > 3))) {
                        codePoint = 65533;
                    }
                    this.mUtf8ToFollow = (byte) 0;
                    this.mUtf8Index = (byte) 0;
                    if (codePoint < 128 || codePoint > 159) {
                        switch (Character.getType(codePoint)) {
                            case 0:
                            case 19:
                                codePoint = 65533;
                                break;
                        }
                        processCodePoint(codePoint);
                        return;
                    }
                    return;
                }
                return;
            }
            this.mUtf8ToFollow = (byte) 0;
            this.mUtf8Index = (byte) 0;
            emitCodePoint(65533);
            processByte(byteToProcess);
            return;
        }
        if ((byteToProcess & ByteCompanionObject.MIN_VALUE) == 0) {
            processCodePoint(byteToProcess);
            return;
        }
        if ((byteToProcess & 224) == 192) {
            this.mUtf8ToFollow = (byte) 1;
        } else if ((byteToProcess & 240) == 224) {
            this.mUtf8ToFollow = (byte) 2;
        } else if ((byteToProcess & 248) == 240) {
            this.mUtf8ToFollow = (byte) 3;
        } else {
            processCodePoint(65533);
            return;
        }
        byte[] bArr2 = this.mUtf8InputBuffer;
        byte b3 = this.mUtf8Index;
        this.mUtf8Index = (byte) (b3 + 1);
        bArr2[b3] = byteToProcess;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void processCodePoint(int i) {
        int i2;
        int arg;
        int i3;
        if (this.mEscapeState == 20) {
            doApc(i);
        }
        if (this.mEscapeState == 21) {
            doApcEscape(i);
            return;
        }
        i = 1;
        int i4 = 1;
        switch (i) {
            case 0:
                break;
            case 7:
                if (this.mEscapeState == 10) {
                    doOsc(i);
                } else {
                    this.mSession.onBell();
                }
                break;
            case 8:
                if (this.mLeftMargin != this.mCursorCol) {
                    setCursorCol(this.mCursorCol - 1);
                    break;
                } else {
                    int i5 = this.mCursorRow - 1;
                    if (i5 >= 0 && this.mScreen.getLineWrap(i5)) {
                        this.mScreen.clearLineWrap(i5);
                        setCursorRowCol(i5, this.mRightMargin - 1);
                        break;
                    }
                }
                break;
            case 9:
                this.mCursorCol = nextTabStop(1);
                break;
            case 10:
            case 11:
            case 12:
                doLinefeed();
                break;
            case 13:
                setCursorCol(this.mLeftMargin);
                break;
            case 14:
                this.mUseLineDrawingUsesG0 = false;
                break;
            case 15:
                this.mUseLineDrawingUsesG0 = true;
                break;
            case 24:
            case 26:
                if (this.mEscapeState != 0) {
                    this.mEscapeState = 0;
                    emitCodePoint(127);
                }
                break;
            case 27:
                if (this.mEscapeState != 13) {
                    if (this.mEscapeState != 10) {
                        startEscapeSequence();
                    } else {
                        doOsc(i);
                    }
                    break;
                }
                break;
            default:
                this.mContinueSequence = false;
                switch (this.mEscapeState) {
                    case 0:
                        if (i >= 32) {
                            emitCodePoint(i);
                        }
                        break;
                    case 1:
                        doEsc(i);
                        break;
                    case 2:
                        doEscPound(i);
                        break;
                    case 3:
                        this.mUseLineDrawingG0 = i == 48;
                        break;
                    case 4:
                        this.mUseLineDrawingG1 = i == 48;
                        break;
                    case 5:
                    case 20:
                    case 21:
                    default:
                        unknownSequence(i);
                        break;
                    case 6:
                        doCsi(i);
                        break;
                    case 7:
                        doCsiQuestionMark(i);
                        break;
                    case 8:
                        boolean zIsDecsetInternalBitSet = isDecsetInternalBitSet(4);
                        int i6 = zIsDecsetInternalBitSet ? this.mTopMargin : 0;
                        int i7 = zIsDecsetInternalBitSet ? this.mBottomMargin : this.mRows;
                        int i8 = zIsDecsetInternalBitSet ? this.mLeftMargin : 0;
                        int i9 = zIsDecsetInternalBitSet ? this.mRightMargin : this.mColumns;
                        switch (i) {
                            case 114:
                            case 116:
                                boolean z = i == 116;
                                int iMin = Math.min(getArg(0, 1, true) - 1, i7) + i6;
                                int iMin2 = Math.min(getArg(1, 1, true) - 1, i9) + i8;
                                int iMin3 = Math.min(getArg(2, this.mRows, true) + 1, i7 - 1) + i6;
                                int iMin4 = Math.min(getArg(3, this.mColumns, true) + 1, i9 - 1) + i8;
                                if (this.mArgIndex >= 4) {
                                    if (this.mArgIndex >= this.mArgs.length) {
                                        this.mArgIndex = this.mArgs.length - 1;
                                    }
                                    int i10 = 4;
                                    while (i10 <= this.mArgIndex) {
                                        int i11 = 0;
                                        boolean z2 = true;
                                        switch (getArg(i10, 0, false)) {
                                            case 0:
                                                i11 = 29;
                                                if (!z) {
                                                    z2 = false;
                                                }
                                                break;
                                            case 1:
                                                i11 = 1;
                                                break;
                                            case 4:
                                                i11 = 4;
                                                break;
                                            case 5:
                                                i11 = 8;
                                                break;
                                            case 7:
                                                i11 = 16;
                                                break;
                                            case 22:
                                                i11 = 1;
                                                z2 = false;
                                                break;
                                            case 24:
                                                i11 = 4;
                                                z2 = false;
                                                break;
                                            case 25:
                                                i11 = 8;
                                                z2 = false;
                                                break;
                                            case 27:
                                                i11 = 16;
                                                z2 = false;
                                                break;
                                        }
                                        if (!z || z2) {
                                            i2 = i9;
                                            this.mScreen.setOrClearEffect(i11, z2, z, isDecsetInternalBitSet(4096), i8, i2, iMin, iMin2, iMin3, iMin4);
                                        } else {
                                            i2 = i9;
                                        }
                                        i10++;
                                        i9 = i2;
                                    }
                                }
                                break;
                            case 115:
                            case 117:
                            case 119:
                            case 121:
                            default:
                                unknownSequence(i);
                                break;
                            case ComposerChipPolicy.CHIP_MAX_WIDTH_DP /* 118 */:
                                int iMin5 = Math.min((getArg(0, 1, true) - 1) + i6, this.mRows);
                                int iMin6 = Math.min((getArg(1, 1, true) - 1) + i8, this.mColumns);
                                int iMin7 = Math.min(Math.max(getArg(2, this.mRows, true) + i6, iMin5), this.mRows);
                                int iMin8 = Math.min(Math.max(getArg(3, this.mColumns, true) + i8, iMin6), this.mColumns);
                                int iMin9 = Math.min((getArg(5, 1, true) - 1) + i6, this.mRows);
                                int iMin10 = Math.min((getArg(6, 1, true) - 1) + i8, this.mColumns);
                                this.mScreen.blockCopy(iMin6, iMin5, Math.min(this.mColumns - iMin10, iMin8 - iMin6), Math.min(this.mRows - iMin9, iMin7 - iMin5), iMin10, iMin9);
                                break;
                            case 120:
                            case 122:
                            case 123:
                                boolean objArr = i != 120;
                                boolean objArr2 = i == 123;
                                boolean objArr3 = objArr && objArr2;
                                int i12 = 0;
                                if (objArr) {
                                    arg = 32;
                                } else {
                                    arg = getArg(0, -1, true);
                                    i12 = 0 + 1;
                                }
                                if ((arg >= 32 && arg <= 126) || (arg >= 160 && arg <= 255)) {
                                    int i13 = i12 + 1;
                                    int iMin11 = Math.min(getArg(i12, 1, true) + i6, i7 + 1);
                                    int i14 = i13 + 1;
                                    int iMin12 = Math.min(getArg(i13, 1, true) + i8, i9 + 1);
                                    int iMin13 = Math.min(getArg(i14, this.mRows, true) + i6, i7);
                                    int iMin14 = Math.min(getArg(i14 + 1, this.mColumns, true) + i8, i9);
                                    long style = getStyle();
                                    int i15 = iMin11 - 1;
                                    while (i15 < iMin13) {
                                        int i16 = iMin13;
                                        int i17 = iMin12 - 1;
                                        while (i17 < iMin14) {
                                            if (objArr2) {
                                                i3 = iMin12;
                                                if ((TextStyle.decodeEffect(this.mScreen.getStyleAt(i15, i17)) & 128) != 0) {
                                                }
                                                i17++;
                                                iMin12 = i3;
                                            } else {
                                                i3 = iMin12;
                                            }
                                            this.mScreen.setChar(i17, i15, arg, objArr3 ? this.mScreen.getStyleAt(i15, i17) : style);
                                            i17++;
                                            iMin12 = i3;
                                        }
                                        i15++;
                                        iMin13 = i16;
                                    }
                                }
                                break;
                        }
                        break;
                    case 9:
                        break;
                    case 10:
                        doOsc(i);
                        break;
                    case 11:
                        doOscEsc(i);
                        break;
                    case 12:
                        doCsiBiggerThan(i);
                        break;
                    case 13:
                        doDeviceControl(i);
                        break;
                    case 14:
                        if (i == 112) {
                            int arg0 = getArg0(0);
                            if (arg0 == 47 || arg0 == 1047 || arg0 == 1049) {
                                if (this.mScreen != this.mAltBuffer) {
                                    i4 = 2;
                                }
                            } else {
                                int iMapDecSetBitToInternalBit = mapDecSetBitToInternalBit(arg0);
                                if (iMapDecSetBitToInternalBit != -1) {
                                    if (!isDecsetInternalBitSet(iMapDecSetBitToInternalBit)) {
                                        i4 = 2;
                                    }
                                } else {
                                    Logger.logError(this.mClient, LOG_TAG, "Got DECRQM for unrecognized private DEC mode=" + arg0);
                                    i4 = 0;
                                }
                            }
                            this.mSession.write(String.format(Locale.US, "\u001b[?%d;%d$y", Integer.valueOf(arg0), Integer.valueOf(i4)));
                        } else {
                            unknownSequence(i);
                        }
                        break;
                    case 15:
                        int arg1 = getArg0(0);
                        switch (i) {
                            case 113:
                                switch (arg1) {
                                    case 0:
                                    case 1:
                                    case 2:
                                        this.mCursorStyle = 0;
                                        break;
                                    case 3:
                                    case 4:
                                        this.mCursorStyle = 1;
                                        break;
                                    case 5:
                                    case 6:
                                        this.mCursorStyle = 2;
                                        break;
                                }
                                break;
                            case 114:
                            case 115:
                            default:
                                unknownSequence(i);
                                break;
                            case 116:
                            case 117:
                                break;
                        }
                        break;
                    case 16:
                        int arg2 = getArg0(0);
                        if (i == 120 && arg2 >= 0 && arg2 <= 2) {
                            setDecsetinternalBit(4096, arg2 == 2);
                        } else {
                            unknownSequence(i);
                        }
                        break;
                    case 17:
                        if (i == 113) {
                            int arg3 = getArg0(0);
                            if (arg3 == 0 || arg3 == 2) {
                                this.mEffect &= -129;
                            } else if (arg3 == 1) {
                                this.mEffect |= 128;
                            } else {
                                unknownSequence(i);
                            }
                        } else {
                            unknownSequence(i);
                        }
                        break;
                    case 18:
                        if (i == 125) {
                            int i18 = this.mRightMargin - this.mCursorCol;
                            int iMin15 = Math.min(getArg0(1), i18);
                            this.mScreen.blockCopy(this.mCursorCol, 0, i18 - iMin15, this.mRows, this.mCursorCol + iMin15, 0);
                            blockClear(this.mCursorCol, 0, iMin15, this.mRows);
                        } else if (i == 126) {
                            int i19 = this.mRightMargin - this.mCursorCol;
                            int iMin16 = Math.min(getArg0(1), i19);
                            this.mScreen.blockCopy(this.mCursorCol + iMin16, 0, i19 - iMin16, this.mRows, this.mCursorCol, 0);
                        } else {
                            unknownSequence(i);
                        }
                        break;
                    case 19:
                        if (i == 112) {
                            reset();
                        } else {
                            unknownSequence(i);
                        }
                        break;
                    case 22:
                    case 23:
                        doCsiUnsupportedParameterOrIntermediateByte(i);
                        break;
                }
                if (!this.mContinueSequence) {
                    this.mEscapeState = 0;
                }
                break;
        }
    }

    private void doDeviceControl(int b) {
        String[] strArr;
        String responseValue;
        String[] strArr2;
        switch (b) {
            case 92:
                String dcs = this.mOSCOrDeviceControlArgs.toString();
                if (dcs.startsWith("$q")) {
                    if (!dcs.equals("$q\"p")) {
                        finishSequenceAndLogError("Unrecognized DECRQSS string: '" + dcs + "'");
                    } else {
                        this.mSession.write("\u001bP1$r64;1\"p\u001b\\");
                    }
                } else if (dcs.startsWith("+q")) {
                    int i = 2;
                    String[] strArrSplit = dcs.substring(2).split(";");
                    int length = strArrSplit.length;
                    int i2 = 0;
                    while (i2 < length) {
                        String part = strArrSplit[i2];
                        if (part.length() % i == 0) {
                            StringBuilder transBuffer = new StringBuilder();
                            int i3 = 0;
                            while (i3 < part.length()) {
                                try {
                                    strArr2 = strArrSplit;
                                    try {
                                        char c = (char) Long.decode("0x" + part.charAt(i3) + "" + part.charAt(i3 + 1)).longValue();
                                        transBuffer.append(c);
                                    Object e = null;
                                    } catch (NumberFormatException e) {
                                        Logger.logStackTraceWithMessage(this.mClient, LOG_TAG, "Invalid device termcap/terminfo encoded name \"" + part + "\"", e);
                                    }
                                } catch (NumberFormatException e2) {
                                    e = e2;
                                    strArr2 = strArrSplit;
                                }
                                i3 += 2;
                                strArrSplit = strArr2;
                            }
                            strArr = strArrSplit;
                            String trans = transBuffer.toString();
                            switch (trans) {
                                case "Co":
                                case "colors":
                                    responseValue = "256";
                                    break;
                                case "TN":
                                case "name":
                                    responseValue = "xterm";
                                    break;
                                default:
                                    responseValue = KeyHandler.getCodeFromTermcap(trans, isDecsetInternalBitSet(1), isDecsetInternalBitSet(32));
                                    break;
                            }
                            if (responseValue == null) {
                                switch (trans) {
                                    case "%1":
                                    case "&8":
                                        break;
                                    default:
                                        Logger.logWarn(this.mClient, LOG_TAG, "Unhandled termcap/terminfo name: '" + trans + "'");
                                        break;
                                }
                                this.mSession.write("\u001bP0+r" + part + "\u001b\\");
                            } else {
                                StringBuilder hexEncoded = new StringBuilder();
                                for (int j = 0; j < responseValue.length(); j++) {
                                    hexEncoded.append(String.format("%02X", Integer.valueOf(responseValue.charAt(j))));
                                }
                                this.mSession.write("\u001bP1+r" + part + "=" + ((Object) hexEncoded) + "\u001b\\");
                            }
                        } else {
                            strArr = strArrSplit;
                            Logger.logError(this.mClient, LOG_TAG, "Invalid device termcap/terminfo name of odd length: " + part);
                        }
                        i2++;
                        strArrSplit = strArr;
                        i = 2;
                    }
                }
                finishSequence();
                break;
            default:
                if (this.mOSCOrDeviceControlArgs.length() > 8192) {
                    this.mOSCOrDeviceControlArgs.setLength(0);
                    finishSequence();
                } else {
                    this.mOSCOrDeviceControlArgs.appendCodePoint(b);
                    continueSequence(this.mEscapeState);
                }
                break;
        }
    }

    private void doApc(int b) {
        if (b == 27) {
            continueSequence(21);
        }
    }

    private void doApcEscape(int b) {
        if (b == 92) {
            finishSequence();
        } else {
            continueSequence(20);
        }
    }

    private int nextTabStop(int numTabs) {
        int i = this.mCursorCol;
        while (true) {
            i++;
            if (i < this.mColumns) {
                if (this.mTabStop[i] && (numTabs = numTabs - 1) == 0) {
                    return Math.min(i, this.mRightMargin);
                }
            } else {
                int i2 = this.mRightMargin;
                return i2 - 1;
            }
        }
    }

    private void doCsiUnsupportedParameterOrIntermediateByte(int b) {
        if (this.mEscapeState == 22 && b >= 48 && b <= 63) {
            continueSequence(22);
            return;
        }
        if (b >= 32 && b <= 47) {
            continueSequence(23);
        } else if (b >= 64 && b <= 126) {
            finishSequence();
        } else {
            unknownSequence(b);
        }
    }

    private void doCsiQuestionMark(int b) {
        int startCol;
        int startRow;
        int endCol;
        int endRow;
        switch (b) {
            case 36:
                continueSequence(14);
                break;
            case 74:
            case 75:
                this.mAboutToAutoWrap = false;
                boolean justRow = b == 75;
                switch (getArg0(0)) {
                    case 0:
                        int startCol2 = this.mCursorCol;
                        int startRow2 = this.mCursorRow;
                        int endCol2 = this.mColumns;
                        int endRow2 = justRow ? this.mCursorRow + 1 : this.mRows;
                        startCol = startCol2;
                        startRow = startRow2;
                        endCol = endCol2;
                        endRow = endRow2;
                        break;
                    case 1:
                        int startRow3 = justRow ? this.mCursorRow : 0;
                        int endCol3 = this.mCursorCol + 1;
                        int endRow3 = this.mCursorRow + 1;
                        startCol = 0;
                        startRow = startRow3;
                        endCol = endCol3;
                        endRow = endRow3;
                        break;
                    case 2:
                        int startRow4 = justRow ? this.mCursorRow : 0;
                        int endCol4 = this.mColumns;
                        int endRow4 = justRow ? this.mCursorRow + 1 : this.mRows;
                        startCol = 0;
                        startRow = startRow4;
                        endCol = endCol4;
                        endRow = endRow4;
                        break;
                    default:
                        unknownSequence(b);
                        startCol = -1;
                        startRow = -1;
                        endCol = -1;
                        endRow = -1;
                        break;
                }
                long style = getStyle();
                int row = startRow;
                while (row < endRow) {
                    int col = startCol;
                    while (col < endCol) {
                        if ((TextStyle.decodeEffect(this.mScreen.getStyleAt(row, col)) & 128) == 0) {
                            this.mScreen.setChar(col, row, 32, style);
                        }
                        col++;
                    }
                    row++;
                }
                break;
            case LocationRequestCompat.QUALITY_LOW_POWER /* 104 */:
            case 108:
                if (this.mArgIndex >= this.mArgs.length) {
                    this.mArgIndex = this.mArgs.length - 1;
                }
                for (int i = 0; i <= this.mArgIndex; i++) {
                    doDecSetOrReset(b == 104, this.mArgs[i]);
                }
                break;
            case AuroraMotion.PRESS_MS /* 110 */:
                switch (getArg0(-1)) {
                    case 6:
                        this.mSession.write(String.format(Locale.US, "\u001b[?%d;%d;1R", Integer.valueOf(this.mCursorRow + 1), Integer.valueOf(this.mCursorCol + 1)));
                        break;
                    default:
                        finishSequence();
                        break;
                }
                break;
            case 114:
            case 115:
                if (this.mArgIndex >= this.mArgs.length) {
                    this.mArgIndex = this.mArgs.length - 1;
                }
                for (int i2 = 0; i2 <= this.mArgIndex; i2++) {
                    int externalBit = this.mArgs[i2];
                    int internalBit = mapDecSetBitToInternalBit(externalBit);
                    if (internalBit == -1) {
                        Logger.logWarn(this.mClient, LOG_TAG, "Ignoring request to save/recall decset bit=" + externalBit);
                    } else if (b == 115) {
                        this.mSavedDecSetFlags |= internalBit;
                    } else {
                        doDecSetOrReset((this.mSavedDecSetFlags & internalBit) != 0, externalBit);
                    }
                }
                break;
            default:
                parseArg(b);
                break;
        }
    }

    public void doDecSetOrReset(boolean setting, int externalBit) {
        int internalBit = mapDecSetBitToInternalBit(externalBit);
        if (internalBit != -1) {
            setDecsetinternalBit(internalBit, setting);
        }
        switch (externalBit) {
            case 1:
            case 4:
            case 5:
            case 40:
            case MotionEventCompat.AXIS_GENERIC_14 /* 45 */:
            case 66:
            case 1000:
            case 1001:
            case PointerIconCompat.TYPE_HAND /* 1002 */:
            case PointerIconCompat.TYPE_HELP /* 1003 */:
            case PointerIconCompat.TYPE_WAIT /* 1004 */:
            case WebSocketProtocol.CLOSE_NO_STATUS_CODE /* 1005 */:
            case PointerIconCompat.TYPE_CELL /* 1006 */:
            case PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW /* 1015 */:
            case 1034:
            case 2004:
                break;
            case 3:
                this.mTopMargin = 0;
                this.mLeftMargin = 0;
                this.mBottomMargin = this.mRows;
                this.mRightMargin = this.mColumns;
                setDecsetinternalBit(2048, false);
                blockClear(0, 0, this.mColumns, this.mRows);
                setCursorRowCol(0, 0);
                break;
            case 6:
                if (setting) {
                    setCursorPosition(0, 0);
                }
                break;
            case 7:
            case 8:
            case 9:
            case 12:
            case 25:
                if (this.mClient != null) {
                    this.mClient.onTerminalCursorStateChange(setting);
                }
                break;
            case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
            case 1047:
            case 1049:
                TerminalBuffer newScreen = setting ? this.mAltBuffer : this.mMainBuffer;
                if (newScreen != this.mScreen) {
                    boolean resized = (newScreen.mColumns == this.mColumns && newScreen.mScreenRows == this.mRows) ? false : true;
                    if (setting) {
                        saveCursor();
                    }
                    this.mScreen = newScreen;
                    if (!setting) {
                        int col = this.mSavedStateMain.mSavedCursorCol;
                        int row = this.mSavedStateMain.mSavedCursorRow;
                        restoreCursor();
                        if (resized) {
                            this.mCursorCol = col;
                            this.mCursorRow = row;
                        }
                    }
                    if (resized) {
                        resizeScreen();
                    }
                    if (newScreen == this.mAltBuffer) {
                        newScreen.blockSet(0, 0, this.mColumns, this.mRows, 32, getStyle());
                    }
                }
                break;
            case 69:
                if (!setting) {
                    this.mLeftMargin = 0;
                    this.mRightMargin = this.mColumns;
                }
                break;
            case 1048:
                if (setting) {
                    saveCursor();
                } else {
                    restoreCursor();
                }
                break;
            default:
                unknownParameter(externalBit);
                break;
        }
    }

    private void doCsiBiggerThan(int b) {
        switch (b) {
            case 99:
                this.mSession.write("\u001b[>41;320;0c");
                break;
            case 109:
                Logger.logError(this.mClient, LOG_TAG, "(ignored) CSI > MODIFY RESOURCE: " + getArg0(-1) + " to " + getArg1(-1));
                break;
            default:
                parseArg(b);
                break;
        }
    }

    private void startEscapeSequence() {
        this.mEscapeState = 1;
        this.mArgIndex = 0;
        Arrays.fill(this.mArgs, -1);
        this.mArgsSubParamsBitSet = 0;
    }

    private void doLinefeed() {
        boolean belowScrollingRegion = this.mCursorRow >= this.mBottomMargin;
        int newCursorRow = this.mCursorRow + 1;
        if (belowScrollingRegion) {
            if (this.mCursorRow != this.mRows - 1) {
                setCursorRow(newCursorRow);
            }
        } else {
            if (newCursorRow == this.mBottomMargin) {
                scrollDownOneLine();
                newCursorRow = this.mBottomMargin - 1;
            }
            setCursorRow(newCursorRow);
        }
    }

    private void continueSequence(int state) {
        this.mEscapeState = state;
        this.mContinueSequence = true;
    }

    private void doEscPound(int b) {
        switch (b) {
            case 56:
                this.mScreen.blockSet(0, 0, this.mColumns, this.mRows, 69, getStyle());
                break;
            default:
                unknownSequence(b);
                break;
        }
    }

    private void doEsc(int b) {
        switch (b) {
            case MotionEventCompat.AXIS_GENERIC_4 /* 35 */:
                continueSequence(2);
                break;
            case 40:
                continueSequence(3);
                break;
            case MotionEventCompat.AXIS_GENERIC_10 /* 41 */:
                continueSequence(4);
                break;
            case 48:
            case 78:
                break;
            case 54:
                if (this.mCursorCol > this.mLeftMargin) {
                    this.mCursorCol--;
                } else {
                    int rows = this.mBottomMargin - this.mTopMargin;
                    this.mScreen.blockCopy(this.mLeftMargin, this.mTopMargin, (this.mRightMargin - this.mLeftMargin) - 1, rows, this.mLeftMargin + 1, this.mTopMargin);
                    this.mScreen.blockSet(this.mLeftMargin, this.mTopMargin, 1, rows, 32, TextStyle.encode(this.mForeColor, this.mBackColor, 0));
                }
                break;
            case 55:
                saveCursor();
                break;
            case 56:
                restoreCursor();
                break;
            case 57:
                if (this.mCursorCol < this.mRightMargin - 1) {
                    this.mCursorCol++;
                } else {
                    int rows2 = this.mBottomMargin - this.mTopMargin;
                    this.mScreen.blockCopy(this.mLeftMargin + 1, this.mTopMargin, (this.mRightMargin - this.mLeftMargin) - 1, rows2, this.mLeftMargin, this.mTopMargin);
                    this.mScreen.blockSet(this.mRightMargin - 1, this.mTopMargin, 1, rows2, 32, TextStyle.encode(this.mForeColor, this.mBackColor, 0));
                }
                break;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                setDecsetinternalBit(32, true);
                break;
            case 62:
                setDecsetinternalBit(32, false);
                break;
            case 68:
                doLinefeed();
                break;
            case 69:
                setCursorCol(isDecsetInternalBitSet(4) ? this.mLeftMargin : 0);
                doLinefeed();
                break;
            case 70:
                setCursorRowCol(0, this.mBottomMargin - 1);
                break;
            case 72:
                this.mTabStop[this.mCursorCol] = true;
                break;
            case 77:
                if (this.mCursorRow <= this.mTopMargin) {
                    this.mScreen.blockCopy(this.mLeftMargin, this.mTopMargin, this.mRightMargin - this.mLeftMargin, this.mBottomMargin - (this.mTopMargin + 1), this.mLeftMargin, this.mTopMargin + 1);
                    blockClear(this.mLeftMargin, this.mTopMargin, this.mRightMargin - this.mLeftMargin);
                } else {
                    this.mCursorRow--;
                }
                break;
            case 80:
                this.mOSCOrDeviceControlArgs.setLength(0);
                continueSequence(13);
                break;
            case 91:
                continueSequence(6);
                break;
            case 93:
                this.mOSCOrDeviceControlArgs.setLength(0);
                continueSequence(10);
                break;
            case 95:
                continueSequence(20);
                break;
            case 99:
                reset();
                this.mMainBuffer.clearTranscript();
                blockClear(0, 0, this.mColumns, this.mRows);
                setCursorPosition(0, 0);
                break;
            default:
                unknownSequence(b);
                break;
        }
    }

    private void saveCursor() {
        SavedScreenState state = this.mScreen == this.mMainBuffer ? this.mSavedStateMain : this.mSavedStateAlt;
        state.mSavedCursorRow = this.mCursorRow;
        state.mSavedCursorCol = this.mCursorCol;
        state.mSavedEffect = this.mEffect;
        state.mSavedForeColor = this.mForeColor;
        state.mSavedBackColor = this.mBackColor;
        state.mSavedDecFlags = this.mCurrentDecSetFlags;
        state.mUseLineDrawingG0 = this.mUseLineDrawingG0;
        state.mUseLineDrawingG1 = this.mUseLineDrawingG1;
        state.mUseLineDrawingUsesG0 = this.mUseLineDrawingUsesG0;
    }

    private void restoreCursor() {
        SavedScreenState state = this.mScreen == this.mMainBuffer ? this.mSavedStateMain : this.mSavedStateAlt;
        setCursorRowCol(state.mSavedCursorRow, state.mSavedCursorCol);
        this.mEffect = state.mSavedEffect;
        this.mForeColor = state.mSavedForeColor;
        this.mBackColor = state.mSavedBackColor;
        this.mCurrentDecSetFlags = (this.mCurrentDecSetFlags & (~12)) | (state.mSavedDecFlags & 12);
        this.mUseLineDrawingG0 = state.mUseLineDrawingG0;
        this.mUseLineDrawingG1 = state.mUseLineDrawingG1;
        this.mUseLineDrawingUsesG0 = state.mUseLineDrawingUsesG0;
    }

    private void doCsi(int b) {
        switch (b) {
            case 32:
                continueSequence(15);
                break;
            case 33:
                continueSequence(19);
                break;
            case 34:
                continueSequence(17);
                break;
            case MotionEventCompat.AXIS_GENERIC_4 /* 35 */:
            case MotionEventCompat.AXIS_GENERIC_6 /* 37 */:
            case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
            case 40:
            case MotionEventCompat.AXIS_GENERIC_10 /* 41 */:
            case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
            case MotionEventCompat.AXIS_GENERIC_13 /* 44 */:
            case MotionEventCompat.AXIS_GENERIC_14 /* 45 */:
            case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
            case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 78:
            case 79:
            case 81:
            case 82:
            case 85:
            case 86:
            case 87:
            case 89:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 105:
            case 106:
            case 107:
            case 111:
            case 112:
            case 113:
            default:
                parseArg(b);
                break;
            case 36:
                continueSequence(8);
                break;
            case MotionEventCompat.AXIS_GENERIC_8 /* 39 */:
                continueSequence(18);
                break;
            case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                continueSequence(16);
                break;
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                continueSequence(22);
                break;
            case 62:
                continueSequence(12);
                break;
            case 63:
                continueSequence(7);
                break;
            case 64:
                this.mAboutToAutoWrap = false;
                int columnsAfterCursor = this.mColumns - this.mCursorCol;
                int spacesToInsert = Math.min(getArg0(1), columnsAfterCursor);
                int charsToMove = columnsAfterCursor - spacesToInsert;
                this.mScreen.blockCopy(this.mCursorCol, this.mCursorRow, charsToMove, 1, this.mCursorCol + spacesToInsert, this.mCursorRow);
                blockClear(this.mCursorCol, this.mCursorRow, spacesToInsert);
                break;
            case MOUSE_WHEELDOWN_BUTTON /* 65 */:
                setCursorRow(Math.max(0, this.mCursorRow - getArg0(1)));
                break;
            case 66:
                setCursorRow(Math.min(this.mRows - 1, this.mCursorRow + getArg0(1)));
                break;
            case 67:
            case 97:
                setCursorCol(Math.min(this.mRightMargin - 1, this.mCursorCol + getArg0(1)));
                break;
            case 68:
                setCursorCol(Math.max(this.mLeftMargin, this.mCursorCol - getArg0(1)));
                break;
            case 69:
                setCursorPosition(0, this.mCursorRow + getArg0(1));
                break;
            case 70:
                setCursorPosition(0, this.mCursorRow - getArg0(1));
                break;
            case 71:
                setCursorCol(Math.min(Math.max(1, getArg0(1)), this.mColumns) - 1);
                break;
            case 72:
            case LocationRequestCompat.QUALITY_BALANCED_POWER_ACCURACY /* 102 */:
                setCursorPosition(getArg1(1) - 1, getArg0(1) - 1);
                break;
            case 73:
                setCursorCol(nextTabStop(getArg0(1)));
                break;
            case 74:
                switch (getArg0(0)) {
                    case 0:
                        blockClear(this.mCursorCol, this.mCursorRow, this.mColumns - this.mCursorCol);
                        blockClear(0, this.mCursorRow + 1, this.mColumns, this.mRows - (this.mCursorRow + 1));
                        break;
                    case 1:
                        blockClear(0, 0, this.mColumns, this.mCursorRow);
                        blockClear(0, this.mCursorRow, this.mCursorCol + 1);
                        break;
                    case 2:
                        blockClear(0, 0, this.mColumns, this.mRows);
                        break;
                    case 3:
                        this.mMainBuffer.clearTranscript();
                        break;
                    default:
                        unknownSequence(b);
                        break;
                }
                this.mAboutToAutoWrap = false;
                break;
            case 75:
                switch (getArg0(0)) {
                    case 0:
                        blockClear(this.mCursorCol, this.mCursorRow, this.mColumns - this.mCursorCol);
                        break;
                    case 1:
                        blockClear(0, this.mCursorRow, this.mCursorCol + 1);
                        break;
                    case 2:
                        blockClear(0, this.mCursorRow, this.mColumns);
                        break;
                    default:
                        unknownSequence(b);
                        break;
                }
                this.mAboutToAutoWrap = false;
                break;
            case Base64.mimeLineLength /* 76 */:
                int linesAfterCursor = this.mBottomMargin - this.mCursorRow;
                int linesToInsert = Math.min(getArg0(1), linesAfterCursor);
                this.mScreen.blockCopy(0, this.mCursorRow, this.mColumns, linesAfterCursor - linesToInsert, 0, this.mCursorRow + linesToInsert);
                blockClear(0, this.mCursorRow, this.mColumns, linesToInsert);
                break;
            case 77:
                this.mAboutToAutoWrap = false;
                int linesAfterCursor2 = this.mBottomMargin - this.mCursorRow;
                int linesToDelete = Math.min(getArg0(1), linesAfterCursor2);
                int linesToMove = linesAfterCursor2 - linesToDelete;
                this.mScreen.blockCopy(0, this.mCursorRow + linesToDelete, this.mColumns, linesToMove, 0, this.mCursorRow);
                blockClear(0, this.mCursorRow + linesToMove, this.mColumns, linesToDelete);
                break;
            case 80:
                this.mAboutToAutoWrap = false;
                int cellsAfterCursor = this.mColumns - this.mCursorCol;
                int cellsToDelete = Math.min(getArg0(1), cellsAfterCursor);
                int cellsToMove = cellsAfterCursor - cellsToDelete;
                this.mScreen.blockCopy(this.mCursorCol + cellsToDelete, this.mCursorRow, cellsToMove, 1, this.mCursorCol, this.mCursorRow);
                blockClear(this.mCursorCol + cellsToMove, this.mCursorRow, cellsToDelete);
                break;
            case 83:
                int linesToScroll = getArg0(1);
                for (int i = 0; i < linesToScroll; i++) {
                    scrollDownOneLine();
                }
                break;
            case 84:
                if (this.mArgIndex == 0) {
                    int linesToScrollArg = getArg0(1);
                    int linesBetweenTopAndBottomMargins = this.mBottomMargin - this.mTopMargin;
                    int linesToScroll2 = Math.min(linesBetweenTopAndBottomMargins, linesToScrollArg);
                    this.mScreen.blockCopy(this.mLeftMargin, this.mTopMargin, this.mRightMargin - this.mLeftMargin, linesBetweenTopAndBottomMargins - linesToScroll2, this.mLeftMargin, this.mTopMargin + linesToScroll2);
                    blockClear(this.mLeftMargin, this.mTopMargin, this.mRightMargin - this.mLeftMargin, linesToScroll2);
                } else {
                    unimplementedSequence(b);
                }
                break;
            case 88:
                this.mAboutToAutoWrap = false;
                this.mScreen.blockSet(this.mCursorCol, this.mCursorRow, Math.min(getArg0(1), this.mColumns - this.mCursorCol), 1, 32, getStyle());
                break;
            case 90:
                int numberOfTabs = getArg0(1);
                int newCol = this.mLeftMargin;
                for (int i2 = this.mCursorCol - 1; i2 >= 0; i2--) {
                    if (this.mTabStop[i2] && (numberOfTabs = numberOfTabs - 1) == 0) {
                        newCol = Math.max(i2, this.mLeftMargin);
                        this.mCursorCol = newCol;
                    }
                    break;
                }
                this.mCursorCol = newCol;
                break;
            case ThreadVoidDensityPolicy.VOID_MARK_TOP_DP /* 96 */:
                int numRepeat = getArg0(1);
                setCursorColRespectingOriginMode(numRepeat - 1);
                break;
            case 98:
                if (this.mLastEmittedCodePoint != -1) {
                    int numRepeat2 = getArg0(1);
                    for (int i3 = 0; i3 < numRepeat2; i3++) {
                        emitCodePoint(this.mLastEmittedCodePoint);
                    }
                    break;
                }
                break;
            case 99:
                if (getArg0(0) == 0) {
                    this.mSession.write("\u001b[?64;1;2;6;9;15;18;21;22c");
                }
                break;
            case 100:
                setCursorRow(Math.min(Math.max(1, getArg0(1)), this.mRows) - 1);
                break;
            case 101:
                setCursorPosition(this.mCursorCol, this.mCursorRow + getArg0(1));
                break;
            case 103:
                switch (getArg0(0)) {
                    case 0:
                        this.mTabStop[this.mCursorCol] = false;
                        break;
                    case 3:
                        for (int i4 = 0; i4 < this.mColumns; i4++) {
                            this.mTabStop[i4] = false;
                        }
                        break;
                }
                break;
            case LocationRequestCompat.QUALITY_LOW_POWER /* 104 */:
                doSetMode(true);
                break;
            case 108:
                doSetMode(false);
                break;
            case 109:
                selectGraphicRendition();
                break;
            case AuroraMotion.PRESS_MS /* 110 */:
                switch (getArg0(0)) {
                    case 5:
                        byte[] dsr = {27, 91, 48, 110};
                        this.mSession.write(dsr, 0, dsr.length);
                        break;
                    case 6:
                        this.mSession.write(String.format(Locale.US, "\u001b[%d;%dR", Integer.valueOf(this.mCursorRow + 1), Integer.valueOf(this.mCursorCol + 1)));
                        break;
                }
                break;
            case 114:
                this.mTopMargin = Math.max(0, Math.min(getArg0(1) - 1, this.mRows - 2));
                this.mBottomMargin = Math.max(this.mTopMargin + 2, Math.min(getArg1(this.mRows), this.mRows));
                setCursorPosition(0, 0);
                break;
            case 115:
                if (isDecsetInternalBitSet(2048)) {
                    this.mLeftMargin = Math.min(getArg0(1) - 1, this.mColumns - 2);
                    this.mRightMargin = Math.max(this.mLeftMargin + 1, Math.min(getArg1(this.mColumns), this.mColumns));
                    setCursorPosition(0, 0);
                } else {
                    saveCursor();
                }
                break;
            case 116:
                switch (getArg0(0)) {
                    case 11:
                        this.mSession.write("\u001b[1t");
                        break;
                    case 13:
                        this.mSession.write("\u001b[3;0;0t");
                        break;
                    case 14:
                        this.mSession.write(String.format(Locale.US, "\u001b[4;%d;%dt", Integer.valueOf(this.mRows * this.mCellHeightPixels), Integer.valueOf(this.mColumns * this.mCellWidthPixels)));
                        break;
                    case 16:
                        this.mSession.write(String.format(Locale.US, "\u001b[6;%d;%dt", Integer.valueOf(this.mCellHeightPixels), Integer.valueOf(this.mCellWidthPixels)));
                        break;
                    case 18:
                        this.mSession.write(String.format(Locale.US, "\u001b[8;%d;%dt", Integer.valueOf(this.mRows), Integer.valueOf(this.mColumns)));
                        break;
                    case 19:
                        this.mSession.write(String.format(Locale.US, "\u001b[9;%d;%dt", Integer.valueOf(this.mRows), Integer.valueOf(this.mColumns)));
                        break;
                    case 20:
                        this.mSession.write("\u001b]LIconLabel\u001b\\");
                        break;
                    case 21:
                        this.mSession.write("\u001b]l\u001b\\");
                        break;
                    case 22:
                        this.mTitleStack.push(this.mTitle);
                        if (this.mTitleStack.size() > 20) {
                            this.mTitleStack.remove(0);
                        }
                        break;
                    case 23:
                        if (!this.mTitleStack.isEmpty()) {
                            setTitle(this.mTitleStack.pop());
                        }
                        break;
                }
                break;
            case 117:
                restoreCursor();
                break;
        }
    }

    /* JADX WARN: Code duplicated, block: B:113:0x0171  */
    /* JADX WARN: Code duplicated, block: B:116:0x0179  */
    /* JADX WARN: Code duplicated, block: B:118:0x0181  */
    /* JADX WARN: Code duplicated, block: B:120:0x0187  */
    /* JADX WARN: Code duplicated, block: B:121:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:123:0x01bd A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:136:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:138:0x020c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:139:0x020e  */
    /* JADX WARN: Code duplicated, block: B:141:0x0218  */
    /* JADX WARN: Code duplicated, block: B:149:0x0229  */
    /* JADX WARN: Code duplicated, block: B:185:0x0240 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:190:0x0240 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:19:0x0030  */
    /* JADX WARN: Code duplicated, block: B:20:0x0038 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:21:0x003a  */
    /* JADX WARN: Code duplicated, block: B:22:0x0041  */
    /* JADX WARN: Code duplicated, block: B:24:0x0044  */
    /* JADX WARN: Code duplicated, block: B:25:0x004b  */
    /* JADX WARN: Code duplicated, block: B:27:0x004e  */
    /* JADX WARN: Code duplicated, block: B:28:0x0055  */
    /* JADX WARN: Code duplicated, block: B:30:0x0058  */
    /* JADX WARN: Code duplicated, block: B:38:0x007e  */
    /* JADX WARN: Code duplicated, block: B:39:0x0085  */
    /* JADX WARN: Code duplicated, block: B:41:0x008a  */
    /* JADX WARN: Code duplicated, block: B:42:0x0091  */
    /* JADX WARN: Code duplicated, block: B:44:0x0094  */
    /* JADX WARN: Code duplicated, block: B:45:0x009c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:46:0x009e  */
    /* JADX WARN: Code duplicated, block: B:47:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:49:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:50:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:58:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:59:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:61:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:62:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:64:0x00da  */
    /* JADX WARN: Code duplicated, block: B:65:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:67:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:68:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:70:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:71:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:73:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:74:0x0106  */
    /* JADX WARN: Code duplicated, block: B:76:0x010a  */
    /* JADX WARN: Code duplicated, block: B:77:0x0112  */
    /* JADX WARN: Code duplicated, block: B:82:0x0120  */
    /* JADX WARN: Code duplicated, block: B:84:0x0124  */
    private void selectGraphicRendition() {
        int firstArg;
        int color;
        int red;
        int green;
        int blue;
        if (this.mArgIndex >= this.mArgs.length) {
            this.mArgIndex = this.mArgs.length - 1;
        }
        int i = 0;
        while (i <= this.mArgIndex) {
            if ((this.mArgsSubParamsBitSet & (1 << i)) == 0) {
                int code = getArg(i, 0, false);
                if (code >= 0) {
                    if (code == 0) {
                        this.mForeColor = 256;
                        this.mBackColor = 257;
                        this.mEffect = 0;
                    } else if (code == 1) {
                        this.mEffect |= 1;
                    } else if (code == 2) {
                        this.mEffect |= 256;
                    } else if (code == 3) {
                        this.mEffect |= 2;
                    } else if (code == 4) {
                        if (i + 1 <= this.mArgIndex || (this.mArgsSubParamsBitSet & (1 << (i + 1))) == 0) {
                            this.mEffect |= 4;
                        } else {
                            i++;
                            if (this.mArgs[i] != 0) {
                                this.mEffect |= 4;
                            } else {
                                this.mEffect &= -5;
                            }
                        }
                    } else if (code == 5) {
                        this.mEffect |= 8;
                    } else if (code == 7) {
                        this.mEffect |= 16;
                    } else if (code == 8) {
                        this.mEffect |= 32;
                    } else if (code == 9) {
                        this.mEffect |= 64;
                    } else if (code != 10 && code != 11) {
                        if (code == 22) {
                            this.mEffect &= -258;
                        } else if (code == 23) {
                            this.mEffect &= -3;
                        } else if (code == 24) {
                            this.mEffect &= -5;
                        } else if (code == 25) {
                            this.mEffect &= -9;
                        } else if (code == 27) {
                            this.mEffect &= -17;
                        } else if (code == 28) {
                            this.mEffect &= -33;
                        } else if (code == 29) {
                            this.mEffect &= -65;
                        } else if (code < 30 && code <= 37) {
                            this.mForeColor = code - 30;
                        } else if (code != 38 || code == 48 || code == 58) {
                            if (i + 2 <= this.mArgIndex) {
                                firstArg = this.mArgs[i + 1];
                                if (firstArg == 2) {
                                    if (i + 4 <= this.mArgIndex) {
                                        red = getArg(i + 2, 0, false);
                                        green = getArg(i + 3, 0, false);
                                        blue = getArg(i + 4, 0, false);
                                        if (red >= 0 || green < 0 || blue < 0 || red > 255 || green > 255 || blue > 255) {
                                            finishSequenceAndLogError("Invalid RGB: " + red + "," + green + "," + blue);
                                        } else {
                                            int argbColor = (red << 16) | ViewCompat.MEASURED_STATE_MASK | (green << 8) | blue;
                                            switch (code) {
                                                case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                                                    this.mForeColor = argbColor;
                                                    break;
                                                case 48:
                                                    this.mBackColor = argbColor;
                                                    break;
                                                case 58:
                                                    this.mUnderlineColor = argbColor;
                                                    break;
                                            }
                                        }
                                        i += 4;
                                    } else {
                                        Logger.logWarn(this.mClient, LOG_TAG, "Too few CSI" + code + ";2 RGB arguments");
                                    }
                                } else if (firstArg == 5) {
                                    color = getArg(i + 2, 0, false);
                                    i += 2;
                                    if (color < 0 && color < 259) {
                                        switch (code) {
                                            case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                                                this.mForeColor = color;
                                                break;
                                            case 48:
                                                this.mBackColor = color;
                                                break;
                                            case 58:
                                                this.mUnderlineColor = color;
                                                break;
                                        }
                                    }
                                } else {
                                    finishSequenceAndLogError("Invalid ISO-8613-3 SGR first argument: " + firstArg);
                                }
                            }
                        } else if (code == 39) {
                            this.mForeColor = 256;
                        } else if (code >= 40 && code <= 47) {
                            this.mBackColor = code - 40;
                        } else if (code == 49) {
                            this.mBackColor = 257;
                        } else if (code == 59) {
                            this.mUnderlineColor = 256;
                        } else if (code >= 90 && code <= 97) {
                            this.mForeColor = (code - 90) + 8;
                        } else if (code >= 100 && code <= 107) {
                            this.mBackColor = (code - 100) + 8;
                        }
                    }
                } else if (this.mArgIndex <= 0) {
                    code = 0;
                    if (code == 0) {
                        this.mForeColor = 256;
                        this.mBackColor = 257;
                        this.mEffect = 0;
                    } else if (code == 1) {
                        this.mEffect |= 1;
                    } else if (code == 2) {
                        this.mEffect |= 256;
                    } else if (code == 3) {
                        this.mEffect |= 2;
                    } else if (code == 4) {
                        if (i + 1 <= this.mArgIndex) {
                            this.mEffect |= 4;
                        } else {
                            this.mEffect |= 4;
                        }
                    } else if (code == 5) {
                        this.mEffect |= 8;
                    } else if (code == 7) {
                        this.mEffect |= 16;
                    } else if (code == 8) {
                        this.mEffect |= 32;
                    } else if (code == 9) {
                        this.mEffect |= 64;
                    } else if (code != 10) {
                        if (code == 22) {
                            this.mEffect &= -258;
                        } else if (code == 23) {
                            this.mEffect &= -3;
                        } else if (code == 24) {
                            this.mEffect &= -5;
                        } else if (code == 25) {
                            this.mEffect &= -9;
                        } else if (code == 27) {
                            this.mEffect &= -17;
                        } else if (code == 28) {
                            this.mEffect &= -33;
                        } else if (code == 29) {
                            this.mEffect &= -65;
                        } else if (code < 30) {
                            if (code != 38) {
                                if (i + 2 <= this.mArgIndex) {
                                    firstArg = this.mArgs[i + 1];
                                    if (firstArg == 2) {
                                        if (i + 4 <= this.mArgIndex) {
                                            red = getArg(i + 2, 0, false);
                                            green = getArg(i + 3, 0, false);
                                            blue = getArg(i + 4, 0, false);
                                            if (red >= 0) {
                                                finishSequenceAndLogError("Invalid RGB: " + red + "," + green + "," + blue);
                                            } else {
                                                finishSequenceAndLogError("Invalid RGB: " + red + "," + green + "," + blue);
                                            }
                                            i += 4;
                                        } else {
                                            Logger.logWarn(this.mClient, LOG_TAG, "Too few CSI" + code + ";2 RGB arguments");
                                        }
                                    } else if (firstArg == 5) {
                                        color = getArg(i + 2, 0, false);
                                        i += 2;
                                        if (color < 0) {
                                        }
                                    } else {
                                        finishSequenceAndLogError("Invalid ISO-8613-3 SGR first argument: " + firstArg);
                                    }
                                }
                            } else if (i + 2 <= this.mArgIndex) {
                                firstArg = this.mArgs[i + 1];
                                if (firstArg == 2) {
                                    if (i + 4 <= this.mArgIndex) {
                                        red = getArg(i + 2, 0, false);
                                        green = getArg(i + 3, 0, false);
                                        blue = getArg(i + 4, 0, false);
                                        if (red >= 0) {
                                            finishSequenceAndLogError("Invalid RGB: " + red + "," + green + "," + blue);
                                        } else {
                                            finishSequenceAndLogError("Invalid RGB: " + red + "," + green + "," + blue);
                                        }
                                        i += 4;
                                    } else {
                                        Logger.logWarn(this.mClient, LOG_TAG, "Too few CSI" + code + ";2 RGB arguments");
                                    }
                                } else if (firstArg == 5) {
                                    color = getArg(i + 2, 0, false);
                                    i += 2;
                                    if (color < 0) {
                                    }
                                } else {
                                    finishSequenceAndLogError("Invalid ISO-8613-3 SGR first argument: " + firstArg);
                                }
                            }
                        } else if (code != 38) {
                            if (i + 2 <= this.mArgIndex) {
                                firstArg = this.mArgs[i + 1];
                                if (firstArg == 2) {
                                    if (i + 4 <= this.mArgIndex) {
                                        red = getArg(i + 2, 0, false);
                                        green = getArg(i + 3, 0, false);
                                        blue = getArg(i + 4, 0, false);
                                        if (red >= 0) {
                                            finishSequenceAndLogError("Invalid RGB: " + red + "," + green + "," + blue);
                                        } else {
                                            finishSequenceAndLogError("Invalid RGB: " + red + "," + green + "," + blue);
                                        }
                                        i += 4;
                                    } else {
                                        Logger.logWarn(this.mClient, LOG_TAG, "Too few CSI" + code + ";2 RGB arguments");
                                    }
                                } else if (firstArg == 5) {
                                    color = getArg(i + 2, 0, false);
                                    i += 2;
                                    if (color < 0) {
                                    }
                                } else {
                                    finishSequenceAndLogError("Invalid ISO-8613-3 SGR first argument: " + firstArg);
                                }
                            }
                        } else if (i + 2 <= this.mArgIndex) {
                            firstArg = this.mArgs[i + 1];
                            if (firstArg == 2) {
                                if (i + 4 <= this.mArgIndex) {
                                    red = getArg(i + 2, 0, false);
                                    green = getArg(i + 3, 0, false);
                                    blue = getArg(i + 4, 0, false);
                                    if (red >= 0) {
                                        finishSequenceAndLogError("Invalid RGB: " + red + "," + green + "," + blue);
                                    } else {
                                        finishSequenceAndLogError("Invalid RGB: " + red + "," + green + "," + blue);
                                    }
                                    i += 4;
                                } else {
                                    Logger.logWarn(this.mClient, LOG_TAG, "Too few CSI" + code + ";2 RGB arguments");
                                }
                            } else if (firstArg == 5) {
                                color = getArg(i + 2, 0, false);
                                i += 2;
                                if (color < 0) {
                                }
                            } else {
                                finishSequenceAndLogError("Invalid ISO-8613-3 SGR first argument: " + firstArg);
                            }
                        }
                    }
                }
            }
            i++;
        }
    }

    private void doOsc(int b) {
        switch (b) {
            case 7:
                doOscSetTextParameters("\u0007");
                break;
            case 27:
                continueSequence(11);
                break;
            default:
                collectOSCArgs(b);
                break;
        }
    }

    private void doOscEsc(int b) {
        switch (b) {
            case 92:
                doOscSetTextParameters("\u001b\\");
                break;
            default:
                collectOSCArgs(27);
                collectOSCArgs(b);
                continueSequence(10);
                break;
        }
    }

    /* JADX WARN: Code duplicated, block: B:66:0x013d A[Catch: NumberFormatException -> 0x01f9, TRY_ENTER, TRY_LEAVE, TryCatch #5 {NumberFormatException -> 0x01f9, blocks: (B:55:0x0115, B:57:0x0121, B:66:0x013d), top: B:138:0x0115 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x01cb  */
    private void doOscSetTextParameters(String bellOrStringTerminator) {
        char c;
        int i;
        char b;
        int lastSemiIndex;
        int i2;
        String textParameter = "";
        int value = -1;
        int value2 = 0;
        while (true) {
            c = ';';
            i = 0;
            if (value2 >= this.mOSCOrDeviceControlArgs.length()) {
                break;
            }
            char b2 = this.mOSCOrDeviceControlArgs.charAt(value2);
            if (b2 == ';') {
                textParameter = this.mOSCOrDeviceControlArgs.substring(value2 + 1);
                break;
            }
            if (b2 >= '0' && b2 <= '9') {
                if (value >= 0) {
                    i = value * 10;
                }
                value = i + (b2 - '0');
                value2++;
            } else {
                unknownSequence(b2);
                return;
            }
        }
        char b3 = 255;
        int i3 = 1;
        switch (value) {
            case 0:
            case 1:
            case 2:
                setTitle(textParameter);
                break;
            case 4:
                int colorIndex = -1;
                int parsingPairStart = -1;
                int i4 = 0;
                while (true) {
                    boolean endOfInput = i4 == textParameter.length();
                    b = endOfInput ? ';' : textParameter.charAt(i4);
                    if (b == ';') {
                        if (parsingPairStart < 0) {
                            parsingPairStart = i4 + 1;
                        } else if (colorIndex >= 0 && colorIndex <= 255) {
                            this.mColors.tryParseColor(colorIndex, textParameter.substring(parsingPairStart, i4));
                            this.mSession.onColorsChanged();
                            colorIndex = -1;
                            parsingPairStart = -1;
                        }
                    } else if (parsingPairStart < 0) {
                        if (parsingPairStart < 0 && b >= '0' && b <= '9') {
                            colorIndex = (colorIndex < 0 ? 0 : colorIndex * 10) + (b - '0');
                        } else {
                            unknownSequence(b);
                            return;
                        }
                    }
                    if (!endOfInput) {
                        i4++;
                    }
                    break;
                }
                unknownSequence(b);
                return;
            case 10:
            case 11:
            case 12:
                int specialIndex = (value - 10) + 256;
                int charIndex = 0;
                int lastSemiIndex2 = 0;
                int lastSemiIndex3 = specialIndex;
                while (true) {
                    int specialIndex2 = textParameter.length();
                    int i5 = charIndex == specialIndex2 ? i3 : i;
                    if (i5 != 0 || textParameter.charAt(charIndex) == c) {
                        try {
                            String colorSpec = textParameter.substring(lastSemiIndex2, charIndex);
                            if (!"?".equals(colorSpec)) {
                                this.mColors.tryParseColor(lastSemiIndex3, colorSpec);
                                this.mSession.onColorsChanged();
                            } else {
                                if (this.mSession instanceof TerminalSession) {
                                    try {
                                        if (((TerminalSession) this.mSession).isRemote()) {
                                            i2 = i3;
                                        }
                                        if (i2 == 0) {
                                            int rgb = this.mColors.mCurrentColors[lastSemiIndex3];
                                            int r = (((rgb & 16711680) >> 16) * 65535) / b3;
                                            int g = (((rgb & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8) * 65535) / b3;
                                            lastSemiIndex = lastSemiIndex2;
                                            try {
                                                try {
                                                    this.mSession.write("\u001b]" + value + ";rgb:" + String.format(Locale.US, "%04x", Integer.valueOf(r)) + "/" + String.format(Locale.US, "%04x", Integer.valueOf(g)) + "/" + String.format(Locale.US, "%04x", Integer.valueOf(((rgb & 255) * 65535) / b3)) + bellOrStringTerminator);
                                                } catch (NumberFormatException e) {
                                                    lastSemiIndex2 = lastSemiIndex;
                                                }
                                                break;
                                            } catch (NumberFormatException e2) {
                                            }
                                        }
                                    } catch (NumberFormatException e3) {
                                        lastSemiIndex = lastSemiIndex2;
                                        lastSemiIndex2 = lastSemiIndex;
                                        charIndex++;
                                        i3 = 1;
                                        b3 = 255;
                                        c = ';';
                                        i = 0;
                                    }
                                }
                                i2 = i;
                                if (i2 == 0) {
                                    int rgb2 = this.mColors.mCurrentColors[lastSemiIndex3];
                                    int r2 = (((rgb2 & 16711680) >> 16) * 65535) / b3;
                                    int g2 = (((rgb2 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8) * 65535) / b3;
                                    lastSemiIndex = lastSemiIndex2;
                                    this.mSession.write("\u001b]" + value + ";rgb:" + String.format(Locale.US, "%04x", Integer.valueOf(r2)) + "/" + String.format(Locale.US, "%04x", Integer.valueOf(g2)) + "/" + String.format(Locale.US, "%04x", Integer.valueOf(((rgb2 & 255) * 65535) / b3)) + bellOrStringTerminator);
                                    break;
                                }
                            }
                            lastSemiIndex3++;
                            if (i5 == 0 && lastSemiIndex3 <= 258 && (charIndex = charIndex + 1) < textParameter.length()) {
                                lastSemiIndex2 = charIndex;
                            }
                        } catch (NumberFormatException e4) {
                            lastSemiIndex = lastSemiIndex2;
                        }
                        break;
                    }
                    charIndex++;
                    i3 = 1;
                    b3 = 255;
                    c = ';';
                    i = 0;
                }
                break;
            case 52:
                int startIndex = textParameter.indexOf(";") + 1;
                try {
                    String clipboardText = new String(android.util.Base64.decode(textParameter.substring(startIndex), 0), StandardCharsets.UTF_8);
                    this.mSession.onCopyTextToClipboard(clipboardText);
                } catch (Exception e5) {
                    Logger.logError(this.mClient, LOG_TAG, "OSC Manipulate selection, invalid string '" + textParameter + "");
                }
                break;
            case LocationRequestCompat.QUALITY_LOW_POWER /* 104 */:
                if (textParameter.isEmpty()) {
                    this.mColors.reset();
                    this.mSession.onColorsChanged();
                } else {
                    int charIndex2 = 0;
                    int lastIndex = 0;
                    while (true) {
                        int lastIndex2 = textParameter.length();
                        boolean endOfInput2 = charIndex2 == lastIndex2;
                        if (endOfInput2 || textParameter.charAt(charIndex2) == ';') {
                            try {
                                int colorToReset = Integer.parseInt(textParameter.substring(lastIndex, charIndex2));
                                this.mColors.reset(colorToReset);
                                this.mSession.onColorsChanged();
                                if (!endOfInput2) {
                                    charIndex2++;
                                    lastIndex = charIndex2;
                                }
                                break;
                            } catch (NumberFormatException e6) {
                            }
                        }
                        charIndex2++;
                    }
                }
                break;
            case AuroraMotion.PRESS_MS /* 110 */:
            case 111:
            case 112:
                this.mColors.reset((value - 110) + 256);
                this.mSession.onColorsChanged();
                break;
            case 119:
                break;
            default:
                unknownParameter(value);
                break;
        }
        finishSequence();
    }

    private void blockClear(int sx, int sy, int w) {
        blockClear(sx, sy, w, 1);
    }

    private void blockClear(int sx, int sy, int w, int h) {
        this.mScreen.blockSet(sx, sy, w, h, 32, getStyle());
    }

    private long getStyle() {
        return TextStyle.encode(this.mForeColor, this.mBackColor, this.mEffect);
    }

    private void doSetMode(boolean newValue) {
        int modeBit = getArg0(0);
        switch (modeBit) {
            case 4:
                this.mInsertMode = newValue;
                break;
            case 20:
                unknownParameter(modeBit);
                break;
            case 34:
                break;
            default:
                unknownParameter(modeBit);
                break;
        }
    }

    private void setCursorPosition(int x, int y) {
        boolean originMode = isDecsetInternalBitSet(4);
        int effectiveTopMargin = originMode ? this.mTopMargin : 0;
        int effectiveBottomMargin = originMode ? this.mBottomMargin : this.mRows;
        int effectiveLeftMargin = originMode ? this.mLeftMargin : 0;
        int effectiveRightMargin = originMode ? this.mRightMargin : this.mColumns;
        int newRow = Math.max(effectiveTopMargin, Math.min(effectiveTopMargin + y, effectiveBottomMargin - 1));
        int newCol = Math.max(effectiveLeftMargin, Math.min(effectiveLeftMargin + x, effectiveRightMargin - 1));
        setCursorRowCol(newRow, newCol);
    }

    private void scrollDownOneLine() {
        this.mScrollCounter++;
        long currentStyle = getStyle();
        if (this.mLeftMargin != 0 || this.mRightMargin != this.mColumns) {
            this.mScreen.blockCopy(this.mLeftMargin, this.mTopMargin + 1, this.mRightMargin - this.mLeftMargin, (this.mBottomMargin - this.mTopMargin) - 1, this.mLeftMargin, this.mTopMargin);
            this.mScreen.blockSet(this.mLeftMargin, this.mBottomMargin - 1, this.mRightMargin - this.mLeftMargin, 1, 32, currentStyle);
        } else {
            this.mScreen.scrollDownOneLine(this.mTopMargin, this.mBottomMargin, currentStyle);
        }
    }

    private void parseArg(int b) {
        int value;
        if (b >= 48 && b <= 57) {
            if (this.mArgIndex < this.mArgs.length) {
                int oldValue = this.mArgs[this.mArgIndex];
                int thisDigit = b - 48;
                if (oldValue >= 0) {
                    value = (oldValue * 10) + thisDigit;
                } else {
                    value = thisDigit;
                }
                if (value > 9999) {
                    value = 9999;
                }
                this.mArgs[this.mArgIndex] = value;
            }
            continueSequence(this.mEscapeState);
            return;
        }
        if (b == 59 || b == 58) {
            if (this.mArgIndex + 1 < this.mArgs.length) {
                this.mArgIndex++;
                if (b == 58) {
                    this.mArgsSubParamsBitSet |= 1 << this.mArgIndex;
                }
            } else {
                logError("Too many parameters when in state: " + this.mEscapeState);
            }
            continueSequence(this.mEscapeState);
            return;
        }
        unknownSequence(b);
    }

    private int getArg0(int defaultValue) {
        return getArg(0, defaultValue, true);
    }

    private int getArg1(int defaultValue) {
        return getArg(1, defaultValue, true);
    }

    private int getArg(int index, int defaultValue, boolean treatZeroAsDefault) {
        int result = this.mArgs[index];
        if (result < 0 || (result == 0 && treatZeroAsDefault)) {
            return defaultValue;
        }
        return result;
    }

    private void collectOSCArgs(int b) {
        if (this.mOSCOrDeviceControlArgs.length() < 8192) {
            this.mOSCOrDeviceControlArgs.appendCodePoint(b);
            continueSequence(this.mEscapeState);
        } else {
            unknownSequence(b);
        }
    }

    private void unimplementedSequence(int b) {
        logError("Unimplemented sequence char '" + ((char) b) + "' (U+" + String.format("%04x", Integer.valueOf(b)) + ")");
        finishSequence();
    }

    private void unknownSequence(int b) {
        logError("Unknown sequence char '" + ((char) b) + "' (numeric value=" + b + ")");
        finishSequence();
    }

    private void unknownParameter(int parameter) {
        logError("Unknown parameter: " + parameter);
        finishSequence();
    }

    private void logError(String errorType) {
    }

    private void finishSequenceAndLogError(String error) {
        finishSequence();
    }

    private void finishSequence() {
        this.mEscapeState = 0;
    }

    private void emitCodePoint(int codePoint) {
        int destCol;
        int codePoint2 = codePoint;
        this.mLastEmittedCodePoint = codePoint2;
        if (!this.mUseLineDrawingUsesG0 ? this.mUseLineDrawingG1 : this.mUseLineDrawingG0) {
            switch (codePoint2) {
                case 48:
                    codePoint2 = 9608;
                    break;
                case 95:
                    codePoint2 = 32;
                    break;
                case ThreadVoidDensityPolicy.VOID_MARK_TOP_DP /* 96 */:
                    codePoint2 = 9670;
                    break;
                case 97:
                    codePoint2 = 9618;
                    break;
                case 98:
                    codePoint2 = 9225;
                    break;
                case 99:
                    codePoint2 = 9228;
                    break;
                case 100:
                    codePoint2 = 13;
                    break;
                case 101:
                    codePoint2 = 9226;
                    break;
                case LocationRequestCompat.QUALITY_BALANCED_POWER_ACCURACY /* 102 */:
                    codePoint2 = 176;
                    break;
                case 103:
                    codePoint2 = 177;
                    break;
                case LocationRequestCompat.QUALITY_LOW_POWER /* 104 */:
                    codePoint2 = 10;
                    break;
                case 105:
                    codePoint2 = 9227;
                    break;
                case 106:
                    codePoint2 = 9496;
                    break;
                case 107:
                    codePoint2 = 9488;
                    break;
                case 108:
                    codePoint2 = 9484;
                    break;
                case 109:
                    codePoint2 = 9492;
                    break;
                case AuroraMotion.PRESS_MS /* 110 */:
                    codePoint2 = 9532;
                    break;
                case 111:
                    codePoint2 = 9146;
                    break;
                case 112:
                    codePoint2 = 9147;
                    break;
                case 113:
                    codePoint2 = 9472;
                    break;
                case 114:
                    codePoint2 = 9148;
                    break;
                case 115:
                    codePoint2 = 9149;
                    break;
                case 116:
                    codePoint2 = 9500;
                    break;
                case 117:
                    codePoint2 = 9508;
                    break;
                case ComposerChipPolicy.CHIP_MAX_WIDTH_DP /* 118 */:
                    codePoint2 = 9524;
                    break;
                case 119:
                    codePoint2 = 9516;
                    break;
                case 120:
                    codePoint2 = 9474;
                    break;
                case 121:
                    codePoint2 = 8804;
                    break;
                case 122:
                    codePoint2 = 8805;
                    break;
                case 123:
                    codePoint2 = 960;
                    break;
                case 124:
                    codePoint2 = 8800;
                    break;
                case 125:
                    codePoint2 = 163;
                    break;
                case WebSocketProtocol.PAYLOAD_SHORT /* 126 */:
                    codePoint2 = 183;
                    break;
            }
        }
        boolean autoWrap = isDecsetInternalBitSet(8);
        int displayWidth = WcWidth.width(codePoint2);
        boolean cursorInLastColumn = this.mCursorCol == this.mRightMargin - 1;
        if (autoWrap) {
            if (cursorInLastColumn && ((this.mAboutToAutoWrap && displayWidth == 1) || displayWidth == 2)) {
                this.mScreen.setLineWrap(this.mCursorRow);
                this.mCursorCol = this.mLeftMargin;
                if (this.mCursorRow + 1 < this.mBottomMargin) {
                    this.mCursorRow++;
                } else {
                    scrollDownOneLine();
                }
            }
        } else if (cursorInLastColumn && displayWidth == 2) {
            return;
        }
        if (this.mInsertMode && displayWidth > 0 && (destCol = this.mCursorCol + displayWidth) < this.mRightMargin) {
            this.mScreen.blockCopy(this.mCursorCol, this.mCursorRow, this.mRightMargin - destCol, 1, destCol, this.mCursorRow);
        }
        int offsetDueToCombiningChar = (displayWidth > 0 || this.mCursorCol <= 0 || this.mAboutToAutoWrap) ? 0 : 1;
        int column = this.mCursorCol - offsetDueToCombiningChar;
        if (column < 0) {
            column = 0;
        }
        this.mScreen.setChar(column, this.mCursorRow, codePoint2, getStyle());
        if (autoWrap && displayWidth > 0) {
            this.mAboutToAutoWrap = this.mCursorCol == this.mRightMargin - displayWidth;
        }
        this.mCursorCol = Math.min(this.mCursorCol + displayWidth, this.mRightMargin - 1);
    }

    private void setCursorRow(int row) {
        this.mCursorRow = row;
        this.mAboutToAutoWrap = false;
    }

    private void setCursorCol(int col) {
        this.mCursorCol = col;
        this.mAboutToAutoWrap = false;
    }

    private void setCursorColRespectingOriginMode(int col) {
        setCursorPosition(col, this.mCursorRow);
    }

    private void setCursorRowCol(int row, int col) {
        this.mCursorRow = Math.max(0, Math.min(row, this.mRows - 1));
        this.mCursorCol = Math.max(0, Math.min(col, this.mColumns - 1));
        this.mAboutToAutoWrap = false;
    }

    public int getScrollCounter() {
        return this.mScrollCounter;
    }

    public void clearScrollCounter() {
        this.mScrollCounter = 0;
    }

    public boolean isAutoScrollDisabled() {
        return this.mAutoScrollDisabled;
    }

    public void toggleAutoScrollDisabled() {
        this.mAutoScrollDisabled = !this.mAutoScrollDisabled;
    }

    public void reset() {
        setCursorStyle();
        this.mArgIndex = 0;
        this.mContinueSequence = false;
        this.mEscapeState = 0;
        this.mInsertMode = false;
        this.mLeftMargin = 0;
        this.mTopMargin = 0;
        this.mBottomMargin = this.mRows;
        this.mRightMargin = this.mColumns;
        this.mAboutToAutoWrap = false;
        SavedScreenState savedScreenState = this.mSavedStateMain;
        this.mSavedStateAlt.mSavedForeColor = 256;
        savedScreenState.mSavedForeColor = 256;
        this.mForeColor = 256;
        SavedScreenState savedScreenState2 = this.mSavedStateMain;
        this.mSavedStateAlt.mSavedBackColor = 257;
        savedScreenState2.mSavedBackColor = 257;
        this.mBackColor = 257;
        setDefaultTabStops();
        this.mUseLineDrawingG1 = false;
        this.mUseLineDrawingG0 = false;
        this.mUseLineDrawingUsesG0 = true;
        SavedScreenState savedScreenState3 = this.mSavedStateMain;
        SavedScreenState savedScreenState4 = this.mSavedStateMain;
        SavedScreenState savedScreenState5 = this.mSavedStateMain;
        this.mSavedStateMain.mSavedDecFlags = 0;
        savedScreenState5.mSavedEffect = 0;
        savedScreenState4.mSavedCursorCol = 0;
        savedScreenState3.mSavedCursorRow = 0;
        SavedScreenState savedScreenState6 = this.mSavedStateAlt;
        SavedScreenState savedScreenState7 = this.mSavedStateAlt;
        SavedScreenState savedScreenState8 = this.mSavedStateAlt;
        this.mSavedStateAlt.mSavedDecFlags = 0;
        savedScreenState8.mSavedEffect = 0;
        savedScreenState7.mSavedCursorCol = 0;
        savedScreenState6.mSavedCursorRow = 0;
        this.mCurrentDecSetFlags = 0;
        setDecsetinternalBit(8, true);
        setDecsetinternalBit(16, true);
        SavedScreenState savedScreenState9 = this.mSavedStateMain;
        SavedScreenState savedScreenState10 = this.mSavedStateAlt;
        int i = this.mCurrentDecSetFlags;
        savedScreenState10.mSavedDecFlags = i;
        savedScreenState9.mSavedDecFlags = i;
        this.mSavedDecSetFlags = i;
        this.mUtf8ToFollow = (byte) 0;
        this.mUtf8Index = (byte) 0;
        this.mColors.reset();
        this.mSession.onColorsChanged();
    }

    public String getSelectedText(int x1, int y1, int x2, int y2) {
        return this.mScreen.getSelectedText(x1, y1, x2, y2);
    }

    public String getTitle() {
        return this.mTitle;
    }

    private void setTitle(String newTitle) {
        String oldTitle = this.mTitle;
        this.mTitle = newTitle;
        if (!Objects.equals(oldTitle, newTitle)) {
            this.mSession.titleChanged(oldTitle, newTitle);
        }
    }

    public void paste(String text) {
        String text2 = text.replaceAll("(\u001b|[\u0080-\u009f])", "").replaceAll("\r?\n", "\r");
        boolean bracketed = isDecsetInternalBitSet(1024);
        if (bracketed) {
            this.mSession.write("\u001b[200~");
        }
        this.mSession.write(text2);
        if (bracketed) {
            this.mSession.write("\u001b[201~");
        }
    }

    static final class SavedScreenState {
        int mSavedBackColor;
        int mSavedCursorCol;
        int mSavedCursorRow;
        int mSavedDecFlags;
        int mSavedEffect;
        int mSavedForeColor;
        boolean mUseLineDrawingG0;
        boolean mUseLineDrawingG1;
        boolean mUseLineDrawingUsesG0 = true;

        SavedScreenState() {
        }
    }

    public String toString() {
        return "TerminalEmulator[size=" + this.mScreen.mColumns + "x" + this.mScreen.mScreenRows + ", margins={" + this.mTopMargin + "," + this.mRightMargin + "," + this.mBottomMargin + "," + this.mLeftMargin + "}]";
    }
}
