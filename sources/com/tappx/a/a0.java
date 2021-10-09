package com.tappx.a;

import java.util.Map;

public class a0 extends Exception {

    /* renamed from: a  reason: collision with root package name */
    public final a f365a;
    public final int b;

    public enum a {
        SERVER_ERROR,
        NETWORK_ERROR,
        PARSE_ERROR,
        NO_FILL
    }

    public a0(a aVar, Map<String, String> map, int i) {
        this.f365a = aVar;
        this.b = i;
    }

    public a a() {
        return this.f365a;
    }

    public a0(a aVar) {
        this(aVar, (Map<String, String>) null, -1);
    }
}
