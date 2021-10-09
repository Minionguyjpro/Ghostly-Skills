package com.tappx.a;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class g6 {

    /* renamed from: a  reason: collision with root package name */
    private final int f453a;
    private final List<m5> b;
    private final int c;
    private final InputStream d;

    public g6(int i, List<m5> list) {
        this(i, list, -1, (InputStream) null);
    }

    public final InputStream a() {
        return this.d;
    }

    public final int b() {
        return this.c;
    }

    public final List<m5> c() {
        return Collections.unmodifiableList(this.b);
    }

    public final int d() {
        return this.f453a;
    }

    public g6(int i, List<m5> list, int i2, InputStream inputStream) {
        this.f453a = i;
        this.b = list;
        this.c = i2;
        this.d = inputStream;
    }
}
