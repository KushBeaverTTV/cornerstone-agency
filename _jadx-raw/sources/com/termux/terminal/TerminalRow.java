package com.termux.terminal;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class TerminalRow {
    private static final int MAX_COMBINING_CHARACTERS_PER_COLUMN = 15;
    private static final float SPARE_CAPACITY_FACTOR = 1.5f;
    private final int mColumns;
    boolean mHasNonOneWidthOrSurrogateChars;
    boolean mLineWrap;
    private short mSpaceUsed;
    final long[] mStyle;
    public char[] mText;

    public TerminalRow(int columns, long style) {
        this.mColumns = columns;
        this.mText = new char[(int) (columns * SPARE_CAPACITY_FACTOR)];
        this.mStyle = new long[columns];
        clear(style);
    }

    public void copyInterval(TerminalRow line, int sourceX1, int sourceX2, int destinationX) {
        int codePoint;
        this.mHasNonOneWidthOrSurrogateChars |= line.mHasNonOneWidthOrSurrogateChars;
        int x1 = line.findStartOfColumn(sourceX1);
        int x2 = line.findStartOfColumn(sourceX2);
        boolean startingFromSecondHalfOfWideChar = sourceX1 > 0 && line.wideDisplayCharacterStartingAt(sourceX1 + (-1));
        char[] sourceChars = line.mText;
        if (this == line) {
            sourceChars = Arrays.copyOf(sourceChars, line.mText.length);
        }
        int latestNonCombiningWidth = 0;
        int i = x1;
        int destinationX2 = destinationX;
        boolean startingFromSecondHalfOfWideChar2 = startingFromSecondHalfOfWideChar;
        int sourceX3 = sourceX1;
        while (i < x2) {
            char sourceChar = sourceChars[i];
            if (Character.isHighSurrogate(sourceChar)) {
                i++;
                codePoint = Character.toCodePoint(sourceChar, sourceChars[i]);
            } else {
                codePoint = sourceChar;
            }
            if (startingFromSecondHalfOfWideChar2) {
                codePoint = 32;
                startingFromSecondHalfOfWideChar2 = false;
            }
            int w = WcWidth.width(codePoint);
            if (w > 0) {
                destinationX2 += latestNonCombiningWidth;
                sourceX3 += latestNonCombiningWidth;
                latestNonCombiningWidth = w;
            }
            setChar(destinationX2, codePoint, line.getStyle(sourceX3));
            i++;
            x1 = x1;
        }
    }

    public int getSpaceUsed() {
        return this.mSpaceUsed;
    }

    public int findStartOfColumn(int column) {
        int newCharIndex;
        int newCharIndex2;
        if (column == this.mColumns) {
            return getSpaceUsed();
        }
        int currentColumn = 0;
        int currentCharIndex = 0;
        while (true) {
            int newCharIndex3 = currentCharIndex;
            int newCharIndex4 = newCharIndex3 + 1;
            char c = this.mText[newCharIndex3];
            boolean isHigh = Character.isHighSurrogate(c);
            if (isHigh) {
                newCharIndex = newCharIndex4 + 1;
                newCharIndex2 = Character.toCodePoint(c, this.mText[newCharIndex4]);
            } else {
                newCharIndex = newCharIndex4;
                newCharIndex2 = c;
            }
            int wcwidth = WcWidth.width(newCharIndex2);
            if (wcwidth > 0) {
                currentColumn += wcwidth;
                if (currentColumn == column) {
                    while (newCharIndex < this.mSpaceUsed) {
                        if (Character.isHighSurrogate(this.mText[newCharIndex])) {
                            if (WcWidth.width(Character.toCodePoint(this.mText[newCharIndex], this.mText[newCharIndex + 1])) > 0) {
                                break;
                            }
                            newCharIndex += 2;
                        } else {
                            if (WcWidth.width(this.mText[newCharIndex]) > 0) {
                                break;
                            }
                            newCharIndex++;
                        }
                    }
                    return newCharIndex;
                }
                if (currentColumn > column) {
                    return currentCharIndex;
                }
            }
            currentCharIndex = newCharIndex;
        }
    }

    private boolean wideDisplayCharacterStartingAt(int column) {
        int codePoint;
        int currentCharIndex = 0;
        int currentColumn = 0;
        while (currentCharIndex < this.mSpaceUsed) {
            int currentCharIndex2 = currentCharIndex + 1;
            char c = this.mText[currentCharIndex];
            if (Character.isHighSurrogate(c)) {
                codePoint = Character.toCodePoint(c, this.mText[currentCharIndex2]);
                currentCharIndex2++;
            } else {
                codePoint = c;
            }
            int wcwidth = WcWidth.width(codePoint);
            if (wcwidth > 0) {
                if (currentColumn == column && wcwidth == 2) {
                    return true;
                }
                currentColumn += wcwidth;
                if (currentColumn > column) {
                    return false;
                }
            }
            currentCharIndex = currentCharIndex2;
        }
        return false;
    }

    public void clear(long style) {
        Arrays.fill(this.mText, ' ');
        Arrays.fill(this.mStyle, style);
        this.mSpaceUsed = (short) this.mColumns;
        this.mHasNonOneWidthOrSurrogateChars = false;
    }

    public void setChar(int columnToSet, int codePoint, long style) {
        int oldEndOfColumnIndex;
        int i;
        int columnToSet2 = columnToSet;
        if (columnToSet2 < 0 || columnToSet2 >= this.mStyle.length) {
            throw new IllegalArgumentException("TerminalRow.setChar(): columnToSet=" + columnToSet2 + ", codePoint=" + codePoint + ", style=" + style);
        }
        this.mStyle[columnToSet2] = style;
        int newCodePointDisplayWidth = WcWidth.width(codePoint);
        if (!this.mHasNonOneWidthOrSurrogateChars) {
            if (codePoint >= 65536 || newCodePointDisplayWidth != 1) {
                this.mHasNonOneWidthOrSurrogateChars = true;
            } else {
                this.mText[columnToSet2] = (char) codePoint;
                return;
            }
        }
        boolean newIsCombining = newCodePointDisplayWidth <= 0;
        boolean wasExtraColForWideChar = columnToSet2 > 0 && wideDisplayCharacterStartingAt(columnToSet2 + (-1));
        if (newIsCombining) {
            if (wasExtraColForWideChar) {
                columnToSet2--;
            }
        } else {
            if (wasExtraColForWideChar) {
                setChar(columnToSet2 - 1, 32, style);
            }
            boolean overwritingWideCharInNextColumn = newCodePointDisplayWidth == 2 && wideDisplayCharacterStartingAt(columnToSet2 + 1);
            if (overwritingWideCharInNextColumn) {
                setChar(columnToSet2 + 1, 32, style);
            }
        }
        char[] text = this.mText;
        int oldStartOfColumnIndex = findStartOfColumn(columnToSet2);
        int oldCodePointDisplayWidth = WcWidth.width(text, oldStartOfColumnIndex);
        if (columnToSet2 + oldCodePointDisplayWidth < this.mColumns) {
            int oldEndOfColumnIndex2 = findStartOfColumn(columnToSet2 + oldCodePointDisplayWidth);
            oldEndOfColumnIndex = oldEndOfColumnIndex2 - oldStartOfColumnIndex;
        } else {
            int oldCharactersUsedForColumn = this.mSpaceUsed;
            oldEndOfColumnIndex = oldCharactersUsedForColumn - oldStartOfColumnIndex;
        }
        if (newIsCombining) {
            int combiningCharsCount = WcWidth.zeroWidthCharsCount(this.mText, oldStartOfColumnIndex, oldStartOfColumnIndex + oldEndOfColumnIndex);
            if (combiningCharsCount >= 15) {
                return;
            }
        }
        int newCharactersUsedForColumn = Character.charCount(codePoint);
        if (newIsCombining) {
            newCharactersUsedForColumn += oldEndOfColumnIndex;
        }
        int oldNextColumnIndex = oldStartOfColumnIndex + oldEndOfColumnIndex;
        int newNextColumnIndex = oldStartOfColumnIndex + newCharactersUsedForColumn;
        int javaCharDifference = newCharactersUsedForColumn - oldEndOfColumnIndex;
        if (javaCharDifference > 0) {
            int oldCharactersAfterColumn = this.mSpaceUsed - oldNextColumnIndex;
            if (this.mSpaceUsed + javaCharDifference > text.length) {
                char[] newText = new char[text.length + this.mColumns];
                System.arraycopy(text, 0, newText, 0, oldNextColumnIndex);
                System.arraycopy(text, oldNextColumnIndex, newText, newNextColumnIndex, oldCharactersAfterColumn);
                text = newText;
                this.mText = newText;
            } else {
                System.arraycopy(text, oldNextColumnIndex, text, newNextColumnIndex, oldCharactersAfterColumn);
            }
        } else if (javaCharDifference < 0) {
            System.arraycopy(text, oldNextColumnIndex, text, newNextColumnIndex, this.mSpaceUsed - oldNextColumnIndex);
        }
        this.mSpaceUsed = (short) (this.mSpaceUsed + javaCharDifference);
        Character.toChars(codePoint, text, (newIsCombining ? oldEndOfColumnIndex : 0) + oldStartOfColumnIndex);
        if (oldCodePointDisplayWidth != 2) {
            i = 1;
        } else if (newCodePointDisplayWidth != 1) {
            i = 1;
        } else {
            if (this.mSpaceUsed + 1 > text.length) {
                char[] newText2 = new char[text.length + this.mColumns];
                System.arraycopy(text, 0, newText2, 0, newNextColumnIndex);
                System.arraycopy(text, newNextColumnIndex, newText2, newNextColumnIndex + 1, this.mSpaceUsed - newNextColumnIndex);
                text = newText2;
                this.mText = newText2;
            } else {
                System.arraycopy(text, newNextColumnIndex, text, newNextColumnIndex + 1, this.mSpaceUsed - newNextColumnIndex);
            }
            text[newNextColumnIndex] = ' ';
            this.mSpaceUsed = (short) (this.mSpaceUsed + 1);
            return;
        }
        if (oldCodePointDisplayWidth == i && newCodePointDisplayWidth == 2) {
            if (columnToSet2 == this.mColumns - i) {
                throw new IllegalArgumentException("Cannot put wide character in last column");
            }
            if (columnToSet2 == this.mColumns - 2) {
                this.mSpaceUsed = (short) newNextColumnIndex;
                return;
            }
            if (Character.isHighSurrogate(this.mText[newNextColumnIndex])) {
                i = 2;
            }
            int newNextNextColumnIndex = i + newNextColumnIndex;
            int nextLen = newNextNextColumnIndex - newNextColumnIndex;
            System.arraycopy(text, newNextNextColumnIndex, text, newNextColumnIndex, this.mSpaceUsed - newNextNextColumnIndex);
            this.mSpaceUsed = (short) (this.mSpaceUsed - nextLen);
        }
    }

    boolean isBlank() {
        int charLen = getSpaceUsed();
        for (int charIndex = 0; charIndex < charLen; charIndex++) {
            if (this.mText[charIndex] != ' ') {
                return false;
            }
        }
        return true;
    }

    public final long getStyle(int column) {
        return this.mStyle[column];
    }
}
