package com.termux.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import com.termux.terminal.TerminalBuffer;
import com.termux.terminal.TerminalEmulator;
import com.termux.terminal.TerminalRow;
import com.termux.terminal.TextStyle;
import com.termux.terminal.WcWidth;

/* JADX INFO: loaded from: classes3.dex */
public final class TerminalRenderer {
    private final int mFontAscent;
    final int mFontLineSpacing;
    final int mFontLineSpacingAndAscent;
    final float mFontWidth;
    final int mTextSize;
    final Typeface mTypeface;
    private final Paint mTextPaint = new Paint();
    private final float[] asciiMeasures = new float[127];

    public TerminalRenderer(int textSize, Typeface typeface) {
        this.mTextSize = textSize;
        this.mTypeface = typeface;
        this.mTextPaint.setTypeface(typeface);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextSize(textSize);
        this.mFontLineSpacing = (int) Math.ceil(this.mTextPaint.getFontSpacing());
        this.mFontAscent = (int) Math.ceil(this.mTextPaint.ascent());
        this.mFontLineSpacingAndAscent = this.mFontLineSpacing + this.mFontAscent;
        float rawWidth = this.mTextPaint.measureText("X");
        this.mFontWidth = Math.max(1.0f, Math.round(rawWidth));
        StringBuilder sb = new StringBuilder(" ");
        int i = 0;
        while (i < this.asciiMeasures.length) {
            sb.setCharAt(0, (char) i);
            float w = this.mTextPaint.measureText(sb, 0, 1);
            this.asciiMeasures[i] = (i < 32 || i >= 127) ? w : this.mFontWidth;
            i++;
        }
    }

    /* JADX WARN: Code duplicated, block: B:113:0x0214 A[EDGE_INSN: B:113:0x0214->B:92:0x0214 BREAK  A[LOOP:2: B:83:0x01f7->B:90:0x020e], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:114:0x0212 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:86:0x0201  */
    /* JADX WARN: Code duplicated, block: B:88:0x0209  */
    /* JADX WARN: Code duplicated, block: B:89:0x020c  */
    public final void render(TerminalEmulator mEmulator, Canvas canvas, int topRow, int selectionY1, int selectionY2, int selectionX1, int selectionX2) {
        int selx1 = 0;
        boolean z = false;
        boolean invertCursorTextColor = false;
        int codePoint = 0;
        float fMeasureText = 0.0f;
        boolean insideSelection = false;
        char[] line = null;
        boolean invertCursorTextColor2 = false;
        int i = 0;
        char[] line2 = null;
        int i2 = 0;
        TerminalRenderer terminalRenderer = this;
        TerminalEmulator terminalEmulator = mEmulator;
        boolean reverseVideo = mEmulator.isReverseVideo();
        int endRow = topRow + terminalEmulator.mRows;
        int columns = terminalEmulator.mColumns;
        int cursorCol = mEmulator.getCursorCol();
        int cursorRow = mEmulator.getCursorRow();
        boolean cursorVisible = mEmulator.shouldCursorBeVisible();
        TerminalBuffer screen = mEmulator.getScreen();
        int[] palette = terminalEmulator.mColors.mCurrentColors;
        int cursorShape = mEmulator.getCursorStyle();
        if (reverseVideo) {
            canvas.drawColor(palette[256], PorterDuff.Mode.SRC);
        }
        float heightOffset = terminalRenderer.mFontLineSpacingAndAscent;
        int row = topRow;
        while (row < endRow) {
            float heightOffset2 = heightOffset + terminalRenderer.mFontLineSpacing;
            int cursorX = (row == cursorRow && cursorVisible) ? cursorCol : -1;
            int selx2 = -1;
            if (row >= selectionY1 && row <= selectionY2) {
                int selx3 = row == selectionY1 ? selectionX1 : -1;
                selx2 = row == selectionY2 ? selectionX2 : terminalEmulator.mColumns;
                selx1 = selx3;
            } else {
                selx1 = -1;
            }
            int selx4 = screen.externalToInternalRow(row);
            TerminalRow lineObject = screen.allocateFullLineIfNecessary(selx4);
            char[] line3 = lineObject.mText;
            int charsUsedInLine = lineObject.getSpaceUsed();
            long lastRunStyle = 0;
            int lastRunStartColumn = -1;
            int lastRunStartIndex = 0;
            boolean lastRunFontWidthMismatch = false;
            float measuredWidthForRun = 0.0f;
            boolean lastRunInsideSelection = false;
            int endRow2 = endRow;
            int currentCharIndex = 0;
            int currentCharIndex2 = cursorRow;
            int column = 0;
            int charsUsedInLine2 = charsUsedInLine;
            int charsUsedInLine3 = 0;
            while (true) {
                z = false;
                if (column >= columns) {
                    break;
                }
                int columns2 = columns;
                char charAtIndex = line3[currentCharIndex];
                boolean charIsHighsurrogate = Character.isHighSurrogate(charAtIndex);
                TerminalBuffer screen2 = screen;
                int charsForCodePoint = charIsHighsurrogate ? 2 : 1;
                int codePoint2 = charIsHighsurrogate ? Character.toCodePoint(charAtIndex, line3[currentCharIndex + 1]) : charAtIndex;
                int codePointWcWidth = WcWidth.width(codePoint2);
                int i3 = (cursorX == column || (codePointWcWidth == 2 && cursorX == column + 1)) ? 1 : 0;
                boolean insideSelection2 = column >= selx1 && column <= selx2;
                long style = lineObject.getStyle(column);
                TerminalRow lineObject2 = lineObject;
                if (codePoint2 < terminalRenderer.asciiMeasures.length) {
                    fMeasureText = terminalRenderer.asciiMeasures[codePoint2];
                    codePoint = charsForCodePoint;
                } else {
                    codePoint = charsForCodePoint;
                    fMeasureText = terminalRenderer.mTextPaint.measureText(line3, currentCharIndex, codePoint);
                }
                float measuredCodePointWidth = fMeasureText;
                int selx5 = selx2;
                boolean fontWidthMismatch = ((double) Math.abs((measuredCodePointWidth / terminalRenderer.mFontWidth) - ((float) codePointWcWidth))) > 0.01d;
                if (style == lastRunStyle && i3 == charsUsedInLine3) {
                    insideSelection = insideSelection2;
                    if (insideSelection == lastRunInsideSelection && !fontWidthMismatch && !lastRunFontWidthMismatch) {
                        line = line3;
                        i = 2;
                    }
                    measuredWidthForRun += measuredCodePointWidth;
                    column += codePointWcWidth;
                    currentCharIndex += codePoint;
                    while (true) {
                        if (currentCharIndex < charsUsedInLine2) {
                            line2 = line;
                            break;
                        }
                        line2 = line;
                        if (WcWidth.width(line2, currentCharIndex) <= 0) {
                            break;
                        }
                        if (Character.isHighSurrogate(line2[currentCharIndex])) {
                            i2 = i;
                        } else {
                            i2 = 1;
                        }
                        currentCharIndex += i2;
                        line = line2;
                    }
                    line3 = line2;
                    columns = columns2;
                    lineObject = lineObject2;
                    screen = screen2;
                    selx2 = selx5;
                    terminalRenderer = this;
                    terminalEmulator = mEmulator;
                } else {
                    insideSelection = insideSelection2;
                }
                if (column == 0) {
                    line = line3;
                    i = 2;
                } else {
                    int columnWidthSinceLastRun = column - lastRunStartColumn;
                    int charsSinceLastRun = currentCharIndex - lastRunStartIndex;
                    line = line3;
                    int cursorColor = charsUsedInLine3 != 0 ? terminalEmulator.mColors.mCurrentColors[258] : 0;
                    if (charsUsedInLine3 != 0 && cursorShape == 0) {
                        invertCursorTextColor2 = true;
                    } else {
                        invertCursorTextColor2 = false;
                    }
                    i = 2;
                    drawTextRun(canvas, line, palette, heightOffset2, lastRunStartColumn, columnWidthSinceLastRun, lastRunStartIndex, charsSinceLastRun, measuredWidthForRun, cursorColor, cursorShape, lastRunStyle, reverseVideo || invertCursorTextColor2 || lastRunInsideSelection);
                }
                measuredWidthForRun = 0.0f;
                int i4 = i3;
                boolean lastRunInsideSelection2 = insideSelection;
                int lastRunStartColumn2 = column;
                int lastRunStartIndex2 = currentCharIndex;
                lastRunStyle = style;
                charsUsedInLine3 = i4;
                lastRunInsideSelection = lastRunInsideSelection2;
                lastRunStartColumn = lastRunStartColumn2;
                lastRunStartIndex = lastRunStartIndex2;
                lastRunFontWidthMismatch = fontWidthMismatch;
                measuredWidthForRun += measuredCodePointWidth;
                column += codePointWcWidth;
                currentCharIndex += codePoint;
                while (true) {
                    if (currentCharIndex < charsUsedInLine2) {
                        line2 = line;
                        break;
                    }
                    line2 = line;
                    if (WcWidth.width(line2, currentCharIndex) <= 0) {
                        break;
                    }
                    if (Character.isHighSurrogate(line2[currentCharIndex])) {
                        i2 = i;
                    } else {
                        i2 = 1;
                    }
                    currentCharIndex += i2;
                    line = line2;
                }
                line3 = line2;
                columns = columns2;
                lineObject = lineObject2;
                screen = screen2;
                selx2 = selx5;
                terminalRenderer = this;
                terminalEmulator = mEmulator;
            }
            int row2 = row;
            int[] palette2 = palette;
            TerminalBuffer screen3 = screen;
            int columns3 = columns;
            char[] line4 = line3;
            boolean lastRunInsideSelection3 = lastRunInsideSelection;
            int charsUsedInLine4 = charsUsedInLine3;
            int columnWidthSinceLastRun2 = columns3 - lastRunStartColumn;
            int charsSinceLastRun2 = currentCharIndex - lastRunStartIndex;
            int cursorColor2 = charsUsedInLine4 != 0 ? mEmulator.mColors.mCurrentColors[258] : 0;
            if (charsUsedInLine4 != 0 && cursorShape == 0) {
                invertCursorTextColor = true;
            } else {
                invertCursorTextColor = false;
            }
            if (reverseVideo || invertCursorTextColor || lastRunInsideSelection3) {
                z = true;
            }
            drawTextRun(canvas, line4, palette2, heightOffset2, lastRunStartColumn, columnWidthSinceLastRun2, lastRunStartIndex, charsSinceLastRun2, measuredWidthForRun, cursorColor2, cursorShape, lastRunStyle, z);
            row = row2 + 1;
            terminalRenderer = this;
            terminalEmulator = mEmulator;
            heightOffset = heightOffset2;
            endRow = endRow2;
            cursorRow = currentCharIndex2;
            columns = columns3;
            screen = screen3;
            palette = palette2;
        }
    }

    private void drawTextRun(Canvas canvas, char[] text, int[] palette, float y, int startColumn, int runWidthColumns, int startCharIndex, int runWidthChars, float mes, int cursor, int cursorStyle, long textStyle, boolean reverseVideo) {
        int foreColor;
        int backColor;
        boolean savedMatrix;
        float left;
        float right;
        int i;
        int foreColor2;
        float cursorHeight;
        int foreColor3 = TextStyle.decodeForeColor(textStyle);
        int effect = TextStyle.decodeEffect(textStyle);
        int backColor2 = TextStyle.decodeBackColor(textStyle);
        boolean bold = (effect & 9) != 0;
        boolean underline = (effect & 4) != 0;
        boolean z = (effect & 2) != 0;
        boolean strikeThrough = (effect & 64) != 0;
        boolean dim = (effect & 256) != 0;
        if ((foreColor3 & ViewCompat.MEASURED_STATE_MASK) != -16777216) {
            if (bold && foreColor3 >= 0 && foreColor3 < 8) {
                foreColor3 += 8;
            }
            foreColor3 = palette[foreColor3];
        }
        if ((backColor2 & ViewCompat.MEASURED_STATE_MASK) != -16777216) {
            backColor2 = palette[backColor2];
        }
        boolean reverseVideoHere = reverseVideo ^ ((effect & 16) != 0);
        if (!reverseVideoHere) {
            foreColor = foreColor3;
            backColor = backColor2;
        } else {
            int tmp = foreColor3;
            foreColor = backColor2;
            backColor = tmp;
        }
        float left2 = startColumn * this.mFontWidth;
        float right2 = (runWidthColumns * this.mFontWidth) + left2;
        float mes2 = mes / this.mFontWidth;
        if (Math.abs(mes2 - runWidthColumns) <= 0.15d) {
            savedMatrix = false;
            left = left2;
            right = right2;
        } else {
            canvas.save();
            canvas.scale(runWidthColumns / mes2, 1.0f);
            left = left2 * (mes2 / runWidthColumns);
            savedMatrix = true;
            right = right2 * (mes2 / runWidthColumns);
        }
        if (backColor != palette[257]) {
            this.mTextPaint.setColor(backColor);
            float f = (y - this.mFontLineSpacingAndAscent) + this.mFontAscent;
            Paint paint = this.mTextPaint;
            i = ViewCompat.MEASURED_STATE_MASK;
            canvas.drawRect(left, f, right, y, paint);
        } else {
            i = ViewCompat.MEASURED_STATE_MASK;
        }
        if (cursor != 0) {
            this.mTextPaint.setColor(cursor);
            float cursorHeight2 = this.mFontLineSpacingAndAscent - this.mFontAscent;
            if (cursorStyle == 1) {
                cursorHeight = (float) (((double) cursorHeight2) / 4.0d);
            } else if (cursorStyle == 2) {
                cursorHeight = cursorHeight2;
                right = (float) (((double) right) - (((double) ((right - left) * 3.0f)) / 4.0d));
            } else {
                cursorHeight = cursorHeight2;
            }
            canvas.drawRect(left, y - cursorHeight, right, y, this.mTextPaint);
        }
        if ((effect & 32) == 0) {
            if (!dim) {
                foreColor2 = foreColor;
            } else {
                int red = (foreColor >> 16) & 255;
                int green = (foreColor >> 8) & 255;
                int blue = foreColor & 255;
                int red2 = (red * 2) / 3;
                int red3 = green * 2;
                int green2 = red3 / 3;
                int green3 = blue * 2;
                int blue2 = green3 / 3;
                int blue3 = red2 << 16;
                foreColor2 = blue3 + i + (green2 << 8) + blue2;
            }
            this.mTextPaint.setFakeBoldText(bold);
            this.mTextPaint.setUnderlineText(underline);
            this.mTextPaint.setTextSkewX(0.0f);
            this.mTextPaint.setStrikeThruText(strikeThrough);
            this.mTextPaint.setColor(foreColor2);
            canvas.drawTextRun(text, startCharIndex, runWidthChars, startCharIndex, runWidthChars, left, y - this.mFontLineSpacingAndAscent, false, this.mTextPaint);
        }
        if (savedMatrix) {
            canvas.restore();
        }
    }

    public float getFontWidth() {
        return this.mFontWidth;
    }

    public int getFontLineSpacing() {
        return this.mFontLineSpacing;
    }
}
