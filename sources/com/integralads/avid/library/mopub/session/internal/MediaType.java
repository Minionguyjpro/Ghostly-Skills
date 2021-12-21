package com.integralads.avid.library.mopub.session.internal;

public enum MediaType {
    DISPLAY("display"),
    VIDEO("video");
    
    private final String value;

    private MediaType(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }
}
