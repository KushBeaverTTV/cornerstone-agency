package com.termux.terminal;

import ai.nous.aurora.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes3.dex */
public final class KeyHandler {
    public static final int KEYMOD_ALT = Integer.MIN_VALUE;
    public static final int KEYMOD_CTRL = 1073741824;
    public static final int KEYMOD_NUM_LOCK = 268435456;
    public static final int KEYMOD_SHIFT = 536870912;
    private static final Map<String, Integer> TERMCAP_TO_KEYCODE = new HashMap();

    static {
        TERMCAP_TO_KEYCODE.put("%i", 536870934);
        TERMCAP_TO_KEYCODE.put("#2", 536871034);
        TERMCAP_TO_KEYCODE.put("#4", 536870933);
        TERMCAP_TO_KEYCODE.put("*7", 536871035);
        TERMCAP_TO_KEYCODE.put("k1", Integer.valueOf(BuildConfig.VERSION_CODE));
        TERMCAP_TO_KEYCODE.put("k2", 132);
        TERMCAP_TO_KEYCODE.put("k3", 133);
        TERMCAP_TO_KEYCODE.put("k4", 134);
        TERMCAP_TO_KEYCODE.put("k5", 135);
        TERMCAP_TO_KEYCODE.put("k6", 136);
        TERMCAP_TO_KEYCODE.put("k7", 137);
        TERMCAP_TO_KEYCODE.put("k8", 138);
        TERMCAP_TO_KEYCODE.put("k9", 139);
        TERMCAP_TO_KEYCODE.put("k;", 140);
        TERMCAP_TO_KEYCODE.put("F1", 141);
        TERMCAP_TO_KEYCODE.put("F2", 142);
        TERMCAP_TO_KEYCODE.put("F3", 536871043);
        TERMCAP_TO_KEYCODE.put("F4", 536871044);
        TERMCAP_TO_KEYCODE.put("F5", 536871045);
        TERMCAP_TO_KEYCODE.put("F6", 536871046);
        TERMCAP_TO_KEYCODE.put("F7", 536871047);
        TERMCAP_TO_KEYCODE.put("F8", 536871048);
        TERMCAP_TO_KEYCODE.put("F9", 536871049);
        TERMCAP_TO_KEYCODE.put("FA", 536871050);
        TERMCAP_TO_KEYCODE.put("FB", 536871051);
        TERMCAP_TO_KEYCODE.put("FC", 536871052);
        TERMCAP_TO_KEYCODE.put("FD", 536871053);
        TERMCAP_TO_KEYCODE.put("FE", 536871054);
        TERMCAP_TO_KEYCODE.put("kb", 67);
        TERMCAP_TO_KEYCODE.put("kd", 20);
        TERMCAP_TO_KEYCODE.put("kh", 122);
        TERMCAP_TO_KEYCODE.put("kl", 21);
        TERMCAP_TO_KEYCODE.put("kr", 22);
        TERMCAP_TO_KEYCODE.put("K1", 122);
        TERMCAP_TO_KEYCODE.put("K3", 92);
        TERMCAP_TO_KEYCODE.put("K4", 123);
        TERMCAP_TO_KEYCODE.put("K5", 93);
        TERMCAP_TO_KEYCODE.put("ku", 19);
        TERMCAP_TO_KEYCODE.put("kB", 536870973);
        TERMCAP_TO_KEYCODE.put("kD", 112);
        TERMCAP_TO_KEYCODE.put("kDN", 536870932);
        TERMCAP_TO_KEYCODE.put("kF", 536870932);
        TERMCAP_TO_KEYCODE.put("kI", 124);
        TERMCAP_TO_KEYCODE.put("kP", 92);
        TERMCAP_TO_KEYCODE.put("kN", 93);
        TERMCAP_TO_KEYCODE.put("kR", 536870931);
        TERMCAP_TO_KEYCODE.put("kUP", 536870931);
        TERMCAP_TO_KEYCODE.put("@7", 123);
        TERMCAP_TO_KEYCODE.put("@8", 160);
    }

    static String getCodeFromTermcap(String termcap, boolean cursorKeysApplication, boolean keypadApplication) {
        Integer keyCodeAndMod = TERMCAP_TO_KEYCODE.get(termcap);
        if (keyCodeAndMod == null) {
            return null;
        }
        int keyCode = keyCodeAndMod.intValue();
        int keyMod = 0;
        if ((keyCode & KEYMOD_SHIFT) != 0) {
            keyMod = 0 | KEYMOD_SHIFT;
            keyCode &= -536870913;
        }
        if ((keyCode & KEYMOD_CTRL) != 0) {
            keyMod |= KEYMOD_CTRL;
            keyCode &= -1073741825;
        }
        if ((keyCode & Integer.MIN_VALUE) != 0) {
            keyMod |= Integer.MIN_VALUE;
            keyCode &= Integer.MAX_VALUE;
        }
        if ((keyCode & KEYMOD_NUM_LOCK) != 0) {
            keyMod |= KEYMOD_NUM_LOCK;
            keyCode &= -268435457;
        }
        return getCode(keyCode, keyMod, cursorKeysApplication, keypadApplication);
    }

    public static String getCode(int keyCode, int keyMode, boolean cursorApp, boolean keypadApplication) {
        boolean numLockOn = (keyMode & KEYMOD_NUM_LOCK) != 0;
        int keyMode2 = keyMode & (-268435457);
        switch (keyCode) {
            case 4:
            case 111:
                return "\u001b";
            case 19:
                if (keyMode2 == 0) {
                    return cursorApp ? "\u001bOA" : "\u001b[A";
                }
                return transformForModifiers("\u001b[1", keyMode2, 'A');
            case 20:
                if (keyMode2 == 0) {
                    return cursorApp ? "\u001bOB" : "\u001b[B";
                }
                return transformForModifiers("\u001b[1", keyMode2, 'B');
            case 21:
                if (keyMode2 == 0) {
                    return cursorApp ? "\u001bOD" : "\u001b[D";
                }
                return transformForModifiers("\u001b[1", keyMode2, 'D');
            case 22:
                if (keyMode2 == 0) {
                    return cursorApp ? "\u001bOC" : "\u001b[C";
                }
                return transformForModifiers("\u001b[1", keyMode2, 'C');
            case 23:
                return "\r";
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                return (536870912 & keyMode2) == 0 ? "\t" : "\u001b[Z";
            case 62:
                if ((keyMode2 & KEYMOD_CTRL) == 0) {
                    return null;
                }
                return "\u0000";
            case 66:
                return (keyMode2 & Integer.MIN_VALUE) == 0 ? "\r" : "\u001b\r";
            case 67:
                String prefix = (keyMode2 & Integer.MIN_VALUE) == 0 ? "" : "\u001b";
                return prefix + ((keyMode2 & KEYMOD_CTRL) == 0 ? "\u007f" : "\b");
            case 92:
                return transformForModifiers("\u001b[5", keyMode2, '~');
            case 93:
                return transformForModifiers("\u001b[6", keyMode2, '~');
            case 112:
                return transformForModifiers("\u001b[3", keyMode2, '~');
            case 120:
                return "\u001b[32~";
            case 121:
                return "\u001b[34~";
            case 122:
                if (keyMode2 == 0) {
                    return cursorApp ? "\u001bOH" : "\u001b[H";
                }
                return transformForModifiers("\u001b[1", keyMode2, 'H');
            case 123:
                if (keyMode2 == 0) {
                    return cursorApp ? "\u001bOF" : "\u001b[F";
                }
                return transformForModifiers("\u001b[1", keyMode2, 'F');
            case 124:
                return transformForModifiers("\u001b[2", keyMode2, '~');
            case BuildConfig.VERSION_CODE /* 131 */:
                return keyMode2 == 0 ? "\u001bOP" : transformForModifiers("\u001b[1", keyMode2, 'P');
            case 132:
                return keyMode2 == 0 ? "\u001bOQ" : transformForModifiers("\u001b[1", keyMode2, 'Q');
            case 133:
                return keyMode2 == 0 ? "\u001bOR" : transformForModifiers("\u001b[1", keyMode2, 'R');
            case 134:
                return keyMode2 == 0 ? "\u001bOS" : transformForModifiers("\u001b[1", keyMode2, 'S');
            case 135:
                return transformForModifiers("\u001b[15", keyMode2, '~');
            case 136:
                return transformForModifiers("\u001b[17", keyMode2, '~');
            case 137:
                return transformForModifiers("\u001b[18", keyMode2, '~');
            case 138:
                return transformForModifiers("\u001b[19", keyMode2, '~');
            case 139:
                return transformForModifiers("\u001b[20", keyMode2, '~');
            case 140:
                return transformForModifiers("\u001b[21", keyMode2, '~');
            case 141:
                return transformForModifiers("\u001b[23", keyMode2, '~');
            case 142:
                return transformForModifiers("\u001b[24", keyMode2, '~');
            case 143:
                if (keypadApplication) {
                    return "\u001bOP";
                }
                return null;
            case 144:
                if (numLockOn) {
                    return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 'p') : "0";
                }
                return transformForModifiers("\u001b[2", keyMode2, '~');
            case 145:
                if (numLockOn) {
                    return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 'q') : "1";
                }
                if (keyMode2 == 0) {
                    return cursorApp ? "\u001bOF" : "\u001b[F";
                }
                return transformForModifiers("\u001b[1", keyMode2, 'F');
            case 146:
                if (numLockOn) {
                    return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 'r') : "2";
                }
                if (keyMode2 == 0) {
                    return cursorApp ? "\u001bOB" : "\u001b[B";
                }
                return transformForModifiers("\u001b[1", keyMode2, 'B');
            case 147:
                if (numLockOn) {
                    return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 's') : "3";
                }
                return "\u001b[6~";
            case 148:
                if (numLockOn) {
                    return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 't') : "4";
                }
                if (keyMode2 == 0) {
                    return cursorApp ? "\u001bOD" : "\u001b[D";
                }
                return transformForModifiers("\u001b[1", keyMode2, 'D');
            case 149:
                return keypadApplication ? transformForModifiers("\u001bO", keyMode2, AbstractJsonLexerKt.UNICODE_ESC) : "5";
            case 150:
                if (numLockOn) {
                    return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 'v') : "6";
                }
                if (keyMode2 == 0) {
                    return cursorApp ? "\u001bOC" : "\u001b[C";
                }
                return transformForModifiers("\u001b[1", keyMode2, 'C');
            case 151:
                if (numLockOn) {
                    return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 'w') : "7";
                }
                if (keyMode2 == 0) {
                    return cursorApp ? "\u001bOH" : "\u001b[H";
                }
                return transformForModifiers("\u001b[1", keyMode2, 'H');
            case 152:
                if (numLockOn) {
                    return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 'x') : "8";
                }
                if (keyMode2 == 0) {
                    return cursorApp ? "\u001bOA" : "\u001b[A";
                }
                return transformForModifiers("\u001b[1", keyMode2, 'A');
            case 153:
                if (numLockOn) {
                    return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 'y') : "9";
                }
                return "\u001b[5~";
            case 154:
                return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 'o') : "/";
            case 155:
                return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 'j') : "*";
            case 156:
                return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 'm') : "-";
            case 157:
                return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 'k') : "+";
            case 158:
                if (numLockOn) {
                    return keypadApplication ? "\u001bOn" : ".";
                }
                return transformForModifiers("\u001b[3", keyMode2, '~');
            case 159:
                return ",";
            case 160:
                return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 'M') : "\n";
            case 161:
                return keypadApplication ? transformForModifiers("\u001bO", keyMode2, 'X') : "=";
            default:
                return null;
        }
    }

    private static String transformForModifiers(String start, int keymod, char lastChar) {
        int modifier;
        switch (keymod) {
            case Integer.MIN_VALUE:
                modifier = 3;
                break;
            case -1610612736:
                modifier = 4;
                break;
            case -1073741824:
                modifier = 7;
                break;
            case -536870912:
                modifier = 8;
                break;
            case KEYMOD_SHIFT /* 536870912 */:
                modifier = 2;
                break;
            case KEYMOD_CTRL /* 1073741824 */:
                modifier = 5;
                break;
            case 1610612736:
                modifier = 6;
                break;
            default:
                return start + lastChar;
        }
        return start + ";" + modifier + lastChar;
    }
}
