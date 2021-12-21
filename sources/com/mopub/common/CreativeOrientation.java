package com.mopub.common;

public enum CreativeOrientation {
    PORTRAIT,
    LANDSCAPE,
    DEVICE;

    public static CreativeOrientation fromString(String str) {
        if ("l".equalsIgnoreCase(str)) {
            return LANDSCAPE;
        }
        if ("p".equalsIgnoreCase(str)) {
            return PORTRAIT;
        }
        return DEVICE;
    }
}
