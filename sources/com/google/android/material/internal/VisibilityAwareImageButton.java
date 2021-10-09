package com.google.android.material.internal;

import android.widget.ImageButton;

public class VisibilityAwareImageButton extends ImageButton {
    private int userSetVisibility;

    public void setVisibility(int i) {
        internalSetVisibility(i, true);
    }

    public final void internalSetVisibility(int i, boolean z) {
        super.setVisibility(i);
        if (z) {
            this.userSetVisibility = i;
        }
    }

    public final int getUserSetVisibility() {
        return this.userSetVisibility;
    }
}
