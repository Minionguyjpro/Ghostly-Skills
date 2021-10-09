package com.yandex.metrica.impl.ob;

import java.io.UnsupportedEncodingException;

public abstract class fp<T> extends fu<T> {

    /* renamed from: a  reason: collision with root package name */
    private final String f908a;

    /* access modifiers changed from: protected */
    public abstract T b(ft ftVar) throws fr;

    static {
        String.format("application/json; charset=%s", new Object[]{"utf-8"});
    }

    public fp(int i, String str, String str2) {
        super(i, str);
        this.f908a = str2;
    }

    public byte[] c() {
        try {
            if (this.f908a == null) {
                return null;
            }
            return this.f908a.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }
}
