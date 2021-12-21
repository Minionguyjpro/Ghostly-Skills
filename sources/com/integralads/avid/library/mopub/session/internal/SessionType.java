package com.integralads.avid.library.mopub.session.internal;

public enum SessionType {
    DISPLAY("display"),
    VIDEO("video"),
    MANAGED_DISPLAY("managedDisplay"),
    MANAGED_VIDEO("managedVideo");
    
    private final String value;

    private SessionType(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }
}
