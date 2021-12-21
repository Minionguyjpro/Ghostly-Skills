package com.tappx.a;

import com.mopub.common.AdType;

public enum v1 {
    b("banner"),
    INTERSTITIAL(AdType.INTERSTITIAL);
    

    /* renamed from: a  reason: collision with root package name */
    private final String f600a;

    private v1(String str) {
        this.f600a = str;
    }

    public String a() {
        return this.f600a;
    }
}
