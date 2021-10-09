package com.startapp.common.b;

import java.util.HashMap;
import java.util.Map;

/* compiled from: StartAppSDK */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final a f354a;

    private b(a aVar) {
        this.f354a = aVar;
    }

    public int a() {
        return this.f354a.f355a;
    }

    public Map<String, String> b() {
        return this.f354a.b;
    }

    public long c() {
        return this.f354a.c;
    }

    public long d() {
        return this.f354a.d;
    }

    public boolean e() {
        return this.f354a.e;
    }

    public boolean f() {
        return this.f354a.f;
    }

    public String toString() {
        return "RunnerRequest: " + this.f354a.f355a + " " + this.f354a.c + " " + this.f354a.e + " " + this.f354a.d + " " + this.f354a.b;
    }

    /* compiled from: StartAppSDK */
    public static class a {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f355a;
        /* access modifiers changed from: private */
        public Map<String, String> b = new HashMap();
        /* access modifiers changed from: private */
        public long c;
        /* access modifiers changed from: private */
        public long d = 100;
        /* access modifiers changed from: private */
        public boolean e = false;
        /* access modifiers changed from: private */
        public boolean f = false;

        public a(int i) {
            this.f355a = i;
        }

        public a a(Map<String, String> map) {
            if (map != null) {
                this.b.putAll(map);
            }
            return this;
        }

        public a a(String str, String str2) {
            this.b.put(str, str2);
            return this;
        }

        public a a(long j) {
            this.c = j;
            return this;
        }

        public a a(boolean z) {
            this.e = z;
            return this;
        }

        public a b(boolean z) {
            this.f = z;
            return this;
        }

        public b a() {
            return new b(this);
        }
    }
}
