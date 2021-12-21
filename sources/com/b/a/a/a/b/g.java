package com.b.a.a.a.b;

import com.b.a.a.a.e.e;

public class g {

    /* renamed from: a  reason: collision with root package name */
    private final String f985a;
    private final String b;

    private g(String str, String str2) {
        this.f985a = str;
        this.b = str2;
    }

    public static g a(String str, String str2) {
        e.a(str, "Name is null or empty");
        e.a(str2, "Version is null or empty");
        return new g(str, str2);
    }

    public String a() {
        return this.f985a;
    }

    public String b() {
        return this.b;
    }
}
