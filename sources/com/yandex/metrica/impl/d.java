package com.yandex.metrica.impl;

import java.util.concurrent.TimeUnit;

public interface d {

    public static class a<T> {

        /* renamed from: a  reason: collision with root package name */
        public static final long f760a = TimeUnit.SECONDS.toMillis(10);
        private long b;
        private long c;
        private T d;

        public a() {
            this(f760a);
        }

        public a(long j) {
            this.c = 0;
            this.d = null;
            this.b = j;
        }

        public T a() {
            return this.d;
        }

        public void a(T t) {
            this.d = t;
            this.c = System.currentTimeMillis();
        }

        public final boolean b() {
            return this.d == null;
        }

        public final boolean c() {
            long j = this.b;
            long currentTimeMillis = System.currentTimeMillis() - this.c;
            return currentTimeMillis > j || currentTimeMillis < 0;
        }

        public T d() {
            if (c()) {
                return null;
            }
            return this.d;
        }
    }
}
