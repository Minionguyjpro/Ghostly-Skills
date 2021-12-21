package com.b.a.a.a.b;

import com.mopub.common.AdType;

public enum e {
    HTML(AdType.HTML),
    NATIVE("native");
    
    private final String typeString;

    private e(String str) {
        this.typeString = str;
    }

    public String toString() {
        return this.typeString;
    }
}
