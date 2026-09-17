package com.termux.terminal;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class TerminalBuffer {
    int mColumns;
    TerminalRow[] mLines;
    int mScreenRows;
    int mTotalRows;
    private int mActiveTranscriptRows = 0;
    private int mScreenFirstRow = 0;

    public TerminalBuffer(int columns, int totalRows, int screenRows) {
        this.mColumns = columns;
        this.mTotalRows = totalRows;
        this.mScreenRows = screenRows;
        this.mLines = new TerminalRow[totalRows];
        blockSet(0, 0, columns, screenRows, 32, TextStyle.NORMAL);
    }

    public String getTranscriptText() {
        return getSelectedText(0, -getActiveTranscriptRows(), this.mColumns, this.mScreenRows).trim();
    }

    public String getTranscriptTextWithoutJoinedLines() {
        return getSelectedText(0, -getActiveTranscriptRows(), this.mColumns, this.mScreenRows, false).trim();
    }

    public String getTranscriptTextWithFullLinesJoined() {
        return getSelectedText(0, -getActiveTranscriptRows(), this.mColumns, this.mScreenRows, true, true).trim();
    }

    public String getSelectedText(int selX1, int selY1, int selX2, int selY2) {
        return getSelectedText(selX1, selY1, selX2, selY2, true);
    }

    public String getSelectedText(int selX1, int selY1, int selX2, int selY2, boolean joinBackLines) {
        return getSelectedText(selX1, selY1, selX2, selY2, joinBackLines, false);
    }

    public String getSelectedText(int selX1, int selY1, int selX2, int selY2, boolean joinBackLines, boolean joinFullLines) {
        int x2;
        int columns;
        boolean z;
        StringBuilder builder = new StringBuilder();
        int columns2 = this.mColumns;
        int selY3 = selY1 < (-getActiveTranscriptRows()) ? -getActiveTranscriptRows() : selY1;
        int selY4 = selY2 >= this.mScreenRows ? this.mScreenRows - 1 : selY2;
        int row = selY3;
        while (row <= selY4) {
            int x1 = row == selY3 ? selX1 : 0;
            if (row != selY4 || (x2 = selX2 + 1) > columns2) {
                x2 = columns2;
            }
            TerminalRow lineObject = this.mLines[externalToInternalRow(row)];
            int x1Index = lineObject.findStartOfColumn(x1);
            int x2Index = x2 < this.mColumns ? lineObject.findStartOfColumn(x2) : lineObject.getSpaceUsed();
            if (x2Index == x1Index) {
                x2Index = lineObject.findStartOfColumn(x2 + 1);
            }
            char[] line = lineObject.mText;
            int lastPrintingCharIndex = -1;
            boolean rowLineWrap = getLineWrap(row);
            if (rowLineWrap && x2 == columns2) {
                lastPrintingCharIndex = x2Index - 1;
                columns = columns2;
            } else {
                int i = x1Index;
                while (i < x2Index) {
                    char c = line[i];
                    int columns3 = columns2;
                    if (c != ' ') {
                        lastPrintingCharIndex = i;
                    }
                    i++;
                    columns2 = columns3;
                }
                columns = columns2;
            }
            int columns4 = lastPrintingCharIndex - x1Index;
            int len = columns4 + 1;
            if (lastPrintingCharIndex != -1 && len > 0) {
                builder.append(line, x1Index, len);
            }
            boolean lineFillsWidth = lastPrintingCharIndex == x2Index + (-1);
            if ((joinBackLines && rowLineWrap) || (joinFullLines && lineFillsWidth)) {
                z = true;
            } else if (row < selY4) {
                z = true;
                if (row < this.mScreenRows - 1) {
                    builder.append('\n');
                }
            } else {
                z = true;
            }
            row++;
            columns2 = columns;
        }
        return builder.toString();
    }

    public String getWordAtLocation(int x, int y) {
        int y1 = y;
        int y2 = y;
        while (y1 > 0 && !getSelectedText(0, y1 - 1, this.mColumns, y, true, true).contains("\n")) {
            y1--;
        }
        while (y2 < this.mScreenRows && !getSelectedText(0, y, this.mColumns, y2 + 1, true, true).contains("\n")) {
            y2++;
        }
        String text = getSelectedText(0, y1, this.mColumns, y2, true, true);
        int textOffset = ((y - y1) * this.mColumns) + x;
        if (textOffset >= text.length()) {
            return "";
        }
        int x1 = text.lastIndexOf(32, textOffset);
        int x2 = text.indexOf(32, textOffset);
        if (x2 == -1) {
            x2 = text.length();
        }
        return x1 == x2 ? "" : text.substring(x1 + 1, x2);
    }

    public int getActiveTranscriptRows() {
        return this.mActiveTranscriptRows;
    }

    public int getActiveRows() {
        return this.mActiveTranscriptRows + this.mScreenRows;
    }

    public int externalToInternalRow(int externalRow) {
        if (externalRow < (-this.mActiveTranscriptRows) || externalRow > this.mScreenRows) {
            throw new IllegalArgumentException("extRow=" + externalRow + ", mScreenRows=" + this.mScreenRows + ", mActiveTranscriptRows=" + this.mActiveTranscriptRows);
        }
        int internalRow = this.mScreenFirstRow + externalRow;
        int i = this.mTotalRows;
        return internalRow < 0 ? i + internalRow : internalRow % i;
    }

    public void setLineWrap(int row) {
        this.mLines[externalToInternalRow(row)].mLineWrap = true;
    }

    public boolean getLineWrap(int row) {
        return this.mLines[externalToInternalRow(row)].mLineWrap;
    }

    public void clearLineWrap(int row) {
        this.mLines[externalToInternalRow(row)].mLineWrap = false;
    }

    public void resize(int newColumns, int newRows, int newTotalRows, int[] cursor, long currentStyle, boolean altScreen) {
        int oldTotalRows;
        char c;
        int i;
        boolean justToCursor;
        int skippedBlankLines;
        int i2;
        int i3;
        int currentOutputExternalRow;
        int currentOutputExternalColumn;
        int currentOutputExternalColumn2;
        int actualShift;
        if (newColumns == this.mColumns && newRows <= this.mTotalRows) {
            int shiftDownOfTopRow = this.mScreenRows - newRows;
            if (shiftDownOfTopRow > 0 && shiftDownOfTopRow < this.mScreenRows) {
                for (int i4 = this.mScreenRows - 1; i4 > 0 && cursor[1] < i4; i4--) {
                    int r = externalToInternalRow(i4);
                    if ((this.mLines[r] == null || this.mLines[r].isBlank()) && (shiftDownOfTopRow = shiftDownOfTopRow - 1) == 0) {
                        break;
                    }
                }
            } else if (shiftDownOfTopRow < 0 && shiftDownOfTopRow != (actualShift = Math.max(shiftDownOfTopRow, -this.mActiveTranscriptRows))) {
                for (int i5 = 0; i5 < actualShift - shiftDownOfTopRow; i5++) {
                    allocateFullLineIfNecessary(((this.mScreenFirstRow + this.mScreenRows) + i5) % this.mTotalRows).clear(currentStyle);
                }
                shiftDownOfTopRow = actualShift;
            }
            this.mScreenFirstRow += shiftDownOfTopRow;
            this.mScreenFirstRow = this.mScreenFirstRow < 0 ? this.mScreenFirstRow + this.mTotalRows : this.mScreenFirstRow % this.mTotalRows;
            this.mTotalRows = newTotalRows;
            this.mActiveTranscriptRows = altScreen ? 0 : Math.max(0, this.mActiveTranscriptRows + shiftDownOfTopRow);
            cursor[1] = cursor[1] - shiftDownOfTopRow;
            this.mScreenRows = newRows;
            oldTotalRows = 0;
            c = 1;
        } else {
            TerminalRow[] oldLines = this.mLines;
            this.mLines = new TerminalRow[newTotalRows];
            for (int i6 = 0; i6 < newTotalRows; i6++) {
                this.mLines[i6] = new TerminalRow(newColumns, currentStyle);
            }
            int oldActiveTranscriptRows = this.mActiveTranscriptRows;
            int oldScreenFirstRow = this.mScreenFirstRow;
            int oldScreenRows = this.mScreenRows;
            int oldTotalRows2 = this.mTotalRows;
            this.mTotalRows = newTotalRows;
            this.mScreenRows = newRows;
            this.mScreenFirstRow = 0;
            this.mActiveTranscriptRows = 0;
            this.mColumns = newColumns;
            int newCursorRow = -1;
            int currentOutputExternalRow2 = -1;
            int oldCursorRow = cursor[1];
            int oldCursorColumn = cursor[0];
            boolean newCursorPlaced = false;
            int currentOutputExternalRow3 = 0;
            int currentOutputExternalColumn3 = 0;
            int externalOldRow = -oldActiveTranscriptRows;
            int skippedBlankLines2 = 0;
            while (externalOldRow < oldScreenRows) {
                int internalOldRow = oldScreenFirstRow + externalOldRow;
                TerminalRow oldLine = oldLines[internalOldRow < 0 ? oldTotalRows2 + internalOldRow : internalOldRow % oldTotalRows2];
                boolean cursorAtThisRow = externalOldRow == oldCursorRow;
                if (oldLine == null || ((newCursorPlaced || !cursorAtThisRow) && oldLine.isBlank())) {
                    skippedBlankLines2++;
                } else {
                    if (skippedBlankLines2 > 0) {
                        int oldTotalRows3 = currentOutputExternalRow3;
                        int oldCursorRow2 = 0;
                        while (oldCursorRow2 < skippedBlankLines2) {
                            int oldScreenFirstRow2 = oldScreenFirstRow;
                            int oldScreenFirstRow3 = this.mScreenRows;
                            if (oldTotalRows3 == oldScreenFirstRow3 - 1) {
                                scrollDownOneLine(0, this.mScreenRows, currentStyle);
                            } else {
                                oldTotalRows3++;
                            }
                            currentOutputExternalColumn3 = 0;
                            oldCursorRow2++;
                            oldScreenFirstRow = oldScreenFirstRow2;
                        }
                        skippedBlankLines2 = 0;
                        currentOutputExternalRow3 = oldTotalRows3;
                    } else {
                    }
                    int lastNonSpaceIndex = 0;
                    if (cursorAtThisRow || oldLine.mLineWrap) {
                        int lastNonSpaceIndex2 = oldLine.getSpaceUsed();
                        boolean justToCursor2 = cursorAtThisRow;
                        i = lastNonSpaceIndex2;
                        justToCursor = justToCursor2;
                    } else {
                        for (int i7 = 0; i7 < oldLine.getSpaceUsed(); i7++) {
                            int lastNonSpaceIndex3 = lastNonSpaceIndex;
                            if (oldLine.mText[i7] == ' ') {
                                lastNonSpaceIndex = lastNonSpaceIndex3;
                            } else {
                                lastNonSpaceIndex = i7 + 1;
                            }
                        }
                        i = lastNonSpaceIndex;
                        justToCursor = false;
                    }
                    long styleAtCol = 0;
                    int codePoint = 0;
                    int currentOldCol = 0;
                    boolean z = newCursorPlaced;
                    int newCursorColumn = currentOutputExternalRow2;
                    int currentOutputExternalRow4 = currentOutputExternalRow3;
                    boolean newCursorPlaced2 = z;
                    while (true) {
                        if (codePoint >= i) {
                            skippedBlankLines = skippedBlankLines2;
                            break;
                        }
                        char c2 = oldLine.mText[codePoint];
                        if (Character.isHighSurrogate(c2)) {
                            int i8 = codePoint + 1;
                            i2 = i8;
                            i3 = Character.toCodePoint(c2, oldLine.mText[i8]);
                        } else {
                            i2 = codePoint;
                            i3 = c2;
                        }
                        int oldCursorRow3 = oldCursorRow;
                        int displayWidth = WcWidth.width(i3);
                        if (displayWidth > 0) {
                            styleAtCol = oldLine.getStyle(currentOldCol);
                        }
                        int i9 = currentOutputExternalColumn3 + displayWidth;
                        int oldCursorRow4 = this.mColumns;
                        if (i9 <= oldCursorRow4) {
                            currentOutputExternalRow = currentOutputExternalRow4;
                            currentOutputExternalColumn = currentOutputExternalColumn3;
                            currentOutputExternalColumn2 = newCursorRow;
                        } else {
                            setLineWrap(currentOutputExternalRow4);
                            if (currentOutputExternalRow4 == this.mScreenRows - 1) {
                                if (newCursorPlaced2) {
                                    newCursorRow--;
                                }
                                scrollDownOneLine(0, this.mScreenRows, currentStyle);
                            } else {
                                currentOutputExternalRow4++;
                            }
                            currentOutputExternalRow = currentOutputExternalRow4;
                            currentOutputExternalColumn = 0;
                            currentOutputExternalColumn2 = newCursorRow;
                        }
                        int offsetDueToCombiningChar = (displayWidth > 0 || currentOutputExternalColumn <= 0) ? 0 : 1;
                        int outputColumn = currentOutputExternalColumn - offsetDueToCombiningChar;
                        skippedBlankLines = skippedBlankLines2;
                        oldCursorRow = oldCursorRow3;
                        int skippedBlankLines3 = currentOldCol;
                        setChar(outputColumn, currentOutputExternalRow, i3, styleAtCol);
                        if (displayWidth <= 0) {
                            currentOldCol = skippedBlankLines3;
                            newCursorRow = currentOutputExternalColumn2;
                            currentOutputExternalColumn3 = currentOutputExternalColumn;
                        } else {
                            if (oldCursorRow == externalOldRow && oldCursorColumn == skippedBlankLines3) {
                                newCursorColumn = currentOutputExternalColumn;
                                newCursorRow = currentOutputExternalRow;
                                newCursorPlaced2 = true;
                            } else {
                                newCursorRow = currentOutputExternalColumn2;
                            }
                            currentOldCol = skippedBlankLines3 + displayWidth;
                            currentOutputExternalColumn3 = currentOutputExternalColumn + displayWidth;
                            if (justToCursor && newCursorPlaced2) {
                                currentOutputExternalRow4 = currentOutputExternalRow;
                                break;
                            }
                        }
                        codePoint = i2 + 1;
                        currentOutputExternalRow4 = currentOutputExternalRow;
                        skippedBlankLines2 = skippedBlankLines;
                    }
                    if (externalOldRow == oldScreenRows - 1 || oldLine.mLineWrap) {
                        skippedBlankLines2 = skippedBlankLines;
                        boolean z2 = newCursorPlaced2;
                        currentOutputExternalRow3 = currentOutputExternalRow4;
                        currentOutputExternalRow2 = newCursorColumn;
                        newCursorPlaced = z2;
                    } else {
                        if (currentOutputExternalRow4 == this.mScreenRows - 1) {
                            if (newCursorPlaced2) {
                                newCursorRow--;
                            }
                            scrollDownOneLine(0, this.mScreenRows, currentStyle);
                        } else {
                            currentOutputExternalRow4++;
                        }
                        currentOutputExternalColumn3 = 0;
                        skippedBlankLines2 = skippedBlankLines;
                        boolean z3 = newCursorPlaced2;
                        currentOutputExternalRow3 = currentOutputExternalRow4;
                        currentOutputExternalRow2 = newCursorColumn;
                        newCursorPlaced = z3;
                    }
                }
                externalOldRow++;
            }
            oldTotalRows = 0;
            cursor[0] = currentOutputExternalRow2;
            c = 1;
            cursor[1] = newCursorRow;
        }
        int newCursorRow2 = cursor[oldTotalRows];
        if (newCursorRow2 < 0 || cursor[c] < 0) {
            cursor[c] = 0;
            cursor[0] = 0;
        }
    }

    private void blockCopyLinesDown(int srcInternal, int len) {
        if (len == 0) {
            return;
        }
        int totalRows = this.mTotalRows;
        int start = len - 1;
        TerminalRow lineToBeOverWritten = this.mLines[((srcInternal + start) + 1) % totalRows];
        for (int i = start; i >= 0; i--) {
            this.mLines[((srcInternal + i) + 1) % totalRows] = this.mLines[(srcInternal + i) % totalRows];
        }
        this.mLines[srcInternal % totalRows] = lineToBeOverWritten;
    }

    public void scrollDownOneLine(int topMargin, int bottomMargin, long style) {
        if (topMargin > bottomMargin - 1 || topMargin < 0 || bottomMargin > this.mScreenRows) {
            throw new IllegalArgumentException("topMargin=" + topMargin + ", bottomMargin=" + bottomMargin + ", mScreenRows=" + this.mScreenRows);
        }
        blockCopyLinesDown(this.mScreenFirstRow, topMargin);
        blockCopyLinesDown(externalToInternalRow(bottomMargin), this.mScreenRows - bottomMargin);
        this.mScreenFirstRow = (this.mScreenFirstRow + 1) % this.mTotalRows;
        if (this.mActiveTranscriptRows < this.mTotalRows - this.mScreenRows) {
            this.mActiveTranscriptRows++;
        }
        int blankRow = externalToInternalRow(bottomMargin - 1);
        if (this.mLines[blankRow] == null) {
            this.mLines[blankRow] = new TerminalRow(this.mColumns, style);
        } else {
            this.mLines[blankRow].clear(style);
        }
    }

    public void blockCopy(int sx, int sy, int w, int h, int dx, int dy) {
        if (w == 0) {
            return;
        }
        if (sx < 0 || sx + w > this.mColumns || sy < 0 || sy + h > this.mScreenRows || dx < 0 || dx + w > this.mColumns || dy < 0 || dy + h > this.mScreenRows) {
            throw new IllegalArgumentException();
        }
        boolean copyingUp = sy > dy;
        for (int y = 0; y < h; y++) {
            int y2 = copyingUp ? y : h - (y + 1);
            TerminalRow sourceRow = allocateFullLineIfNecessary(externalToInternalRow(sy + y2));
            allocateFullLineIfNecessary(externalToInternalRow(dy + y2)).copyInterval(sourceRow, sx, sx + w, dx);
        }
    }

    public void blockSet(int sx, int sy, int w, int h, int val, long style) {
        if (sx < 0 || sx + w > this.mColumns || sy < 0 || sy + h > this.mScreenRows) {
            throw new IllegalArgumentException("Illegal arguments! blockSet(" + sx + ", " + sy + ", " + w + ", " + h + ", " + val + ", " + this.mColumns + ", " + this.mScreenRows + ")");
        }
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                setChar(sx + x, sy + y, val, style);
            }
        }
    }

    public TerminalRow allocateFullLineIfNecessary(int row) {
        if (this.mLines[row] != null) {
            return this.mLines[row];
        }
        TerminalRow[] terminalRowArr = this.mLines;
        TerminalRow terminalRow = new TerminalRow(this.mColumns, 0L);
        terminalRowArr[row] = terminalRow;
        return terminalRow;
    }

    public void setChar(int column, int row, int codePoint, long style) {
        if (row < 0 || row >= this.mScreenRows || column < 0 || column >= this.mColumns) {
            throw new IllegalArgumentException("TerminalBuffer.setChar(): row=" + row + ", column=" + column + ", mScreenRows=" + this.mScreenRows + ", mColumns=" + this.mColumns);
        }
        allocateFullLineIfNecessary(externalToInternalRow(row)).setChar(column, codePoint, style);
    }

    public long getStyleAt(int externalRow, int column) {
        return allocateFullLineIfNecessary(externalToInternalRow(externalRow)).getStyle(column);
    }

    public void setOrClearEffect(int bits, boolean setOrClear, boolean reverse, boolean rectangular, int leftMargin, int rightMargin, int top, int left, int bottom, int right) {
        int effect;
        int y = top;
        while (y < bottom) {
            TerminalRow line = this.mLines[externalToInternalRow(y)];
            int startOfLine = (rectangular || y == top) ? left : leftMargin;
            int endOfLine = (rectangular || y + 1 == bottom) ? right : rightMargin;
            for (int x = startOfLine; x < endOfLine; x++) {
                long currentStyle = line.getStyle(x);
                int foreColor = TextStyle.decodeForeColor(currentStyle);
                int backColor = TextStyle.decodeBackColor(currentStyle);
                int effect2 = TextStyle.decodeEffect(currentStyle);
                if (reverse) {
                    effect = ((~bits) & effect2) | ((~effect2) & bits);
                } else if (setOrClear) {
                    effect = effect2 | bits;
                } else {
                    effect = effect2 & (~bits);
                }
                line.mStyle[x] = TextStyle.encode(foreColor, backColor, effect);
            }
            y++;
        }
    }

    public void clearTranscript() {
        if (this.mScreenFirstRow < this.mActiveTranscriptRows) {
            Arrays.fill(this.mLines, (this.mTotalRows + this.mScreenFirstRow) - this.mActiveTranscriptRows, this.mTotalRows, (Object) null);
            Arrays.fill(this.mLines, 0, this.mScreenFirstRow, (Object) null);
        } else {
            Arrays.fill(this.mLines, this.mScreenFirstRow - this.mActiveTranscriptRows, this.mScreenFirstRow, (Object) null);
        }
        this.mActiveTranscriptRows = 0;
    }
}
