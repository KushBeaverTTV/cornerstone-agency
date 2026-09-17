package com.termux.view.textselection;

import android.view.MotionEvent;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes2.dex */
public interface CursorController extends ViewTreeObserver.OnTouchModeChangeListener {
    boolean hide();

    boolean isActive();

    void onDetached();

    boolean onTouchEvent(MotionEvent motionEvent);

    void render();

    void show(MotionEvent motionEvent);

    void updatePosition(TextSelectionHandleView textSelectionHandleView, int i, int i2);
}
