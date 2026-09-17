package com.termux.view.textselection;

import android.content.ClipboardManager;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import com.termux.terminal.TerminalBuffer;
import com.termux.terminal.WcWidth;
import com.termux.view.R;
import com.termux.view.TerminalView;

/* JADX INFO: loaded from: classes2.dex */
public class TextSelectionCursorController implements CursorController {
    private ActionMode mActionMode;
    private final TextSelectionHandleView mEndHandle;
    private final int mHandleHeight;
    private final TextSelectionHandleView mStartHandle;
    private String mStoredSelectedText;
    private final TerminalView terminalView;
    private boolean mIsSelectingText = false;
    private long mShowStartTime = System.currentTimeMillis();
    private int mSelX1 = -1;
    private int mSelX2 = -1;
    private int mSelY1 = -1;
    private int mSelY2 = -1;
    public final int ACTION_COPY = 1;
    public final int ACTION_PASTE = 2;
    public final int ACTION_MORE = 3;

    public TextSelectionCursorController(TerminalView terminalView) {
        this.terminalView = terminalView;
        this.mStartHandle = new TextSelectionHandleView(terminalView, this, 0);
        this.mEndHandle = new TextSelectionHandleView(terminalView, this, 2);
        this.mHandleHeight = Math.max(this.mStartHandle.getHandleHeight(), this.mEndHandle.getHandleHeight());
    }

    @Override // com.termux.view.textselection.CursorController
    public void show(MotionEvent event) {
        setInitialTextSelectionPosition(event);
        this.mStartHandle.positionAtCursor(this.mSelX1, this.mSelY1, true);
        this.mEndHandle.positionAtCursor(this.mSelX2 + 1, this.mSelY2, true);
        setActionModeCallBacks();
        this.mShowStartTime = System.currentTimeMillis();
        this.mIsSelectingText = true;
    }

    @Override // com.termux.view.textselection.CursorController
    public boolean hide() {
        if (!isActive() || System.currentTimeMillis() - this.mShowStartTime < 300) {
            return false;
        }
        this.mStartHandle.hide();
        this.mEndHandle.hide();
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        this.mSelY2 = -1;
        this.mSelX2 = -1;
        this.mSelY1 = -1;
        this.mSelX1 = -1;
        this.mIsSelectingText = false;
        return true;
    }

    @Override // com.termux.view.textselection.CursorController
    public void render() {
        if (isActive()) {
            this.mStartHandle.positionAtCursor(this.mSelX1, this.mSelY1, false);
            this.mEndHandle.positionAtCursor(this.mSelX2 + 1, this.mSelY2, false);
            if (this.mActionMode != null) {
                this.mActionMode.invalidate();
            }
        }
    }

    public void setInitialTextSelectionPosition(MotionEvent event) {
        int[] columnAndRow = this.terminalView.getColumnAndRow(event, true);
        int i = columnAndRow[0];
        this.mSelX2 = i;
        this.mSelX1 = i;
        int i2 = columnAndRow[1];
        this.mSelY2 = i2;
        this.mSelY1 = i2;
        TerminalBuffer screen = this.terminalView.mEmulator.getScreen();
        if (!" ".equals(screen.getSelectedText(this.mSelX1, this.mSelY1, this.mSelX1, this.mSelY1))) {
            while (this.mSelX1 > 0 && !"".equals(screen.getSelectedText(this.mSelX1 - 1, this.mSelY1, this.mSelX1 - 1, this.mSelY1))) {
                this.mSelX1--;
            }
            while (this.mSelX2 < this.terminalView.mEmulator.mColumns - 1 && !"".equals(screen.getSelectedText(this.mSelX2 + 1, this.mSelY1, this.mSelX2 + 1, this.mSelY1))) {
                this.mSelX2++;
            }
        }
    }

    public void setActionModeCallBacks() {
        final ActionMode.Callback callback = new ActionMode.Callback() { // from class: com.termux.view.textselection.TextSelectionCursorController.1
            @Override // android.view.ActionMode.Callback
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                ClipboardManager clipboard = (ClipboardManager) TextSelectionCursorController.this.terminalView.getContext().getSystemService("clipboard");
                menu.add(0, 1, 0, R.string.copy_text).setShowAsAction(5);
                menu.add(0, 2, 0, R.string.paste_text).setEnabled(clipboard != null && clipboard.hasPrimaryClip()).setShowAsAction(5);
                menu.add(0, 3, 0, R.string.text_selection_more);
                return true;
            }

            @Override // android.view.ActionMode.Callback
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // android.view.ActionMode.Callback
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (!TextSelectionCursorController.this.isActive()) {
                    return true;
                }
                switch (item.getItemId()) {
                    case 1:
                        String selectedText = TextSelectionCursorController.this.getSelectedText();
                        TextSelectionCursorController.this.terminalView.mTermSession.onCopyTextToClipboard(selectedText);
                        TextSelectionCursorController.this.terminalView.stopTextSelectionMode();
                        return true;
                    case 2:
                        TextSelectionCursorController.this.terminalView.stopTextSelectionMode();
                        TextSelectionCursorController.this.terminalView.mTermSession.onPasteTextFromClipboard();
                        return true;
                    case 3:
                        TextSelectionCursorController.this.mStoredSelectedText = TextSelectionCursorController.this.getSelectedText();
                        TextSelectionCursorController.this.terminalView.stopTextSelectionMode();
                        TextSelectionCursorController.this.terminalView.showContextMenu();
                        return true;
                    default:
                        return true;
                }
            }

            @Override // android.view.ActionMode.Callback
            public void onDestroyActionMode(ActionMode mode) {
            }
        };
        this.mActionMode = this.terminalView.startActionMode(new ActionMode.Callback2() { // from class: com.termux.view.textselection.TextSelectionCursorController.2
            @Override // android.view.ActionMode.Callback
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return callback.onCreateActionMode(mode, menu);
            }

            @Override // android.view.ActionMode.Callback
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return callback.onActionItemClicked(mode, item);
            }

            @Override // android.view.ActionMode.Callback
            public void onDestroyActionMode(ActionMode mode) {
            }

            @Override // android.view.ActionMode.Callback2
            public void onGetContentRect(ActionMode mode, View view, Rect outRect) {
                int x1 = Math.round(TextSelectionCursorController.this.mSelX1 * TextSelectionCursorController.this.terminalView.mRenderer.getFontWidth());
                int x2 = Math.round(TextSelectionCursorController.this.mSelX2 * TextSelectionCursorController.this.terminalView.mRenderer.getFontWidth());
                int y1 = Math.round(((TextSelectionCursorController.this.mSelY1 - 1) - TextSelectionCursorController.this.terminalView.getTopRow()) * TextSelectionCursorController.this.terminalView.mRenderer.getFontLineSpacing());
                int y2 = Math.round(((TextSelectionCursorController.this.mSelY2 + 1) - TextSelectionCursorController.this.terminalView.getTopRow()) * TextSelectionCursorController.this.terminalView.mRenderer.getFontLineSpacing());
                if (x1 > x2) {
                    x1 = x2;
                    x2 = x1;
                }
                int terminalBottom = TextSelectionCursorController.this.terminalView.getBottom();
                int top = TextSelectionCursorController.this.mHandleHeight + y1;
                int bottom = TextSelectionCursorController.this.mHandleHeight + y2;
                if (top > terminalBottom) {
                    top = terminalBottom;
                }
                if (bottom > terminalBottom) {
                    bottom = terminalBottom;
                }
                outRect.set(x1, top, x2, bottom);
            }
        }, 1);
    }

    @Override // com.termux.view.textselection.CursorController
    public void updatePosition(TextSelectionHandleView handle, int x, int y) {
        TerminalBuffer screen = this.terminalView.mEmulator.getScreen();
        int scrollRows = screen.getActiveRows() - this.terminalView.mEmulator.mRows;
        if (handle == this.mStartHandle) {
            this.mSelX1 = this.terminalView.getCursorX(x);
            this.mSelY1 = this.terminalView.getCursorY(y);
            if (this.mSelX1 < 0) {
                this.mSelX1 = 0;
            }
            if (this.mSelY1 < (-scrollRows)) {
                this.mSelY1 = -scrollRows;
            } else if (this.mSelY1 > this.terminalView.mEmulator.mRows - 1) {
                this.mSelY1 = this.terminalView.mEmulator.mRows - 1;
            }
            if (this.mSelY1 > this.mSelY2) {
                this.mSelY1 = this.mSelY2;
            }
            if (this.mSelY1 == this.mSelY2 && this.mSelX1 > this.mSelX2) {
                this.mSelX1 = this.mSelX2;
            }
            if (!this.terminalView.mEmulator.isAlternateBufferActive()) {
                int topRow = this.terminalView.getTopRow();
                if (this.mSelY1 <= topRow) {
                    topRow--;
                    if (topRow < (-scrollRows)) {
                        topRow = -scrollRows;
                    }
                } else if (this.mSelY1 >= this.terminalView.mEmulator.mRows + topRow && (topRow = topRow + 1) > 0) {
                    topRow = 0;
                }
                this.terminalView.setTopRow(topRow);
            }
            this.mSelX1 = getValidCurX(screen, this.mSelY1, this.mSelX1);
        } else {
            this.mSelX2 = this.terminalView.getCursorX(x);
            this.mSelY2 = this.terminalView.getCursorY(y);
            if (this.mSelX2 < 0) {
                this.mSelX2 = 0;
            }
            if (this.mSelY2 < (-scrollRows)) {
                this.mSelY2 = -scrollRows;
            } else if (this.mSelY2 > this.terminalView.mEmulator.mRows - 1) {
                this.mSelY2 = this.terminalView.mEmulator.mRows - 1;
            }
            if (this.mSelY1 > this.mSelY2) {
                this.mSelY2 = this.mSelY1;
            }
            if (this.mSelY1 == this.mSelY2 && this.mSelX1 > this.mSelX2) {
                this.mSelX2 = this.mSelX1;
            }
            if (!this.terminalView.mEmulator.isAlternateBufferActive()) {
                int topRow2 = this.terminalView.getTopRow();
                if (this.mSelY2 <= topRow2) {
                    topRow2--;
                    if (topRow2 < (-scrollRows)) {
                        topRow2 = -scrollRows;
                    }
                } else if (this.mSelY2 >= this.terminalView.mEmulator.mRows + topRow2 && (topRow2 = topRow2 + 1) > 0) {
                    topRow2 = 0;
                }
                this.terminalView.setTopRow(topRow2);
            }
            this.mSelX2 = getValidCurX(screen, this.mSelY2, this.mSelX2);
        }
        this.terminalView.invalidate();
    }

    private int getValidCurX(TerminalBuffer screen, int cy, int cx) {
        int wc;
        String line = screen.getSelectedText(0, cy, cx, cy);
        if (!TextUtils.isEmpty(line)) {
            int col = 0;
            int i = 0;
            int len = line.length();
            while (i < len) {
                char ch1 = line.charAt(i);
                if (ch1 == 0) {
                    break;
                }
                if (Character.isHighSurrogate(ch1) && i + 1 < len) {
                    i++;
                    char ch2 = line.charAt(i);
                    wc = WcWidth.width(Character.toCodePoint(ch1, ch2));
                } else {
                    wc = WcWidth.width(ch1);
                }
                int cend = col + wc;
                if (cx > col && cx < cend) {
                    return cend;
                }
                if (cend == col) {
                    return col;
                }
                col = cend;
                i++;
            }
        }
        return cx;
    }

    public void decrementYTextSelectionCursors(int decrement) {
        this.mSelY1 -= decrement;
        this.mSelY2 -= decrement;
    }

    @Override // com.termux.view.textselection.CursorController
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
    public void onTouchModeChanged(boolean isInTouchMode) {
        if (!isInTouchMode) {
            this.terminalView.stopTextSelectionMode();
        }
    }

    @Override // com.termux.view.textselection.CursorController
    public void onDetached() {
    }

    @Override // com.termux.view.textselection.CursorController
    public boolean isActive() {
        return this.mIsSelectingText;
    }

    public void getSelectors(int[] sel) {
        if (sel == null || sel.length != 4) {
            return;
        }
        sel[0] = this.mSelY1;
        sel[1] = this.mSelY2;
        sel[2] = this.mSelX1;
        sel[3] = this.mSelX2;
    }

    public String getSelectedText() {
        return this.terminalView.mEmulator.getSelectedText(this.mSelX1, this.mSelY1, this.mSelX2, this.mSelY2);
    }

    public String getStoredSelectedText() {
        return this.mStoredSelectedText;
    }

    public void unsetStoredSelectedText() {
        this.mStoredSelectedText = null;
    }

    public ActionMode getActionMode() {
        return this.mActionMode;
    }

    public boolean isSelectionStartDragged() {
        return this.mStartHandle.isDragging();
    }

    public boolean isSelectionEndDragged() {
        return this.mEndHandle.isDragging();
    }
}
