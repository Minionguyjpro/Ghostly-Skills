package com.mopub.common.util;

public class Visibility {
    public static boolean isScreenVisible(int i) {
        return i == 0;
    }

    private Visibility() {
    }

    public static boolean hasScreenVisibilityChanged(int i, int i2) {
        return isScreenVisible(i) != isScreenVisible(i2);
    }
}
