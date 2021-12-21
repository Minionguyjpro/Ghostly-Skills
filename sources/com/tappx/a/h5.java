package com.tappx.a;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface h5 {

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f460a;
        public String b;
        public long c;
        public long d;
        public long e;
        public long f;
        public Map<String, String> g = Collections.emptyMap();
        public List<m5> h;

        public boolean a() {
            return this.e < System.currentTimeMillis();
        }

        public boolean b() {
            return this.f < System.currentTimeMillis();
        }
    }

    a a(String str);

    void a();

    void a(String str, a aVar);
}
