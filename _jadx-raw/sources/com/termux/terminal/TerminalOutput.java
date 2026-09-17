package com.termux.terminal;

import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes3.dex */
public abstract class TerminalOutput {
    public abstract void onBell();

    public abstract void onColorsChanged();

    public abstract void onCopyTextToClipboard(String str);

    public abstract void onPasteTextFromClipboard();

    public abstract void titleChanged(String str, String str2);

    public abstract void write(byte[] bArr, int i, int i2);

    public final void write(String data) {
        if (data == null) {
            return;
        }
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        write(bytes, 0, bytes.length);
    }
}
