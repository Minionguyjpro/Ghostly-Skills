package com.b.a.a.a.b;

import com.b.a.a.a.e.e;
import java.net.URL;

public final class h {

    /* renamed from: a  reason: collision with root package name */
    private final String f986a;
    private final URL b;
    private final String c;

    private h(String str, URL url, String str2) {
        this.f986a = str;
        this.b = url;
        this.c = str2;
    }

    public static h a(String str, URL url, String str2) {
        e.a(str, "VendorKey is null or empty");
        e.a((Object) url, "ResourceURL is null");
        e.a(str2, "VerificationParameters is null or empty");
        return new h(str, url, str2);
    }

    public String a() {
        return this.f986a;
    }

    public URL b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
