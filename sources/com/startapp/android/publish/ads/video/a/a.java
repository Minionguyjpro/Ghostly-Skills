package com.startapp.android.publish.ads.video.a;

import java.util.List;

/* compiled from: StartAppSDK */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private List<String> f106a;
    private String b;

    public a(List<String> list, String str) {
        this.f106a = list;
        this.b = str;
    }

    public List<String> a() {
        return this.f106a;
    }

    public String toString() {
        return "[VideoEvent: tag=" + this.b + ", fullUrls=" + this.f106a.toString() + "]";
    }
}
