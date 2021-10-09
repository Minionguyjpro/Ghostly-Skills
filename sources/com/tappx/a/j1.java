package com.tappx.a;

import android.content.Context;

public class j1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f478a;
    public final String b;
    public final int c;
    public final long d;

    public j1(String str, String str2, int i, long j) {
        this.f478a = str;
        this.b = str2;
        this.c = i;
        this.d = j;
    }

    public static class a {
        private static volatile a b;

        /* renamed from: a  reason: collision with root package name */
        private final Context f479a;

        public a(Context context) {
            this.f479a = context;
        }

        public static final a a(Context context) {
            if (b == null) {
                synchronized (a.class) {
                    if (b == null) {
                        b = new a(context.getApplicationContext());
                    }
                }
            }
            return b;
        }

        public j1 a() {
            long j;
            int i;
            s2 c = o2.a(this.f479a).c().c();
            Boolean a2 = c.a();
            p2 c2 = c.c();
            long b2 = c.b();
            if (c2.a()) {
                j = b2;
                i = 0;
            } else if (c2.c()) {
                j = b2;
                i = 1;
            } else if (Boolean.FALSE.equals(a2)) {
                j = 0;
                i = -1;
            } else if (Boolean.TRUE.equals(a2)) {
                j = 0;
                i = -2;
            } else {
                j = 0;
                i = -3;
            }
            return new j1(c.d(), c.e(), i, j);
        }
    }
}
