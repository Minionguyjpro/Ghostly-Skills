package com.tappx.a;

import java.util.Locale;

public enum v4 {
    LOADING,
    DEFAULT,
    RESIZED,
    EXPANDED,
    HIDDEN;

    public String a() {
        return toString().toLowerCase(Locale.US);
    }
}
