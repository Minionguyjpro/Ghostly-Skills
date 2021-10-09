package com.tappx.a;

import android.text.TextUtils;

public final class m5 {

    /* renamed from: a  reason: collision with root package name */
    private final String f522a;
    private final String b;

    public m5(String str, String str2) {
        this.f522a = str;
        this.b = str2;
    }

    public final String a() {
        return this.f522a;
    }

    public final String b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || m5.class != obj.getClass()) {
            return false;
        }
        m5 m5Var = (m5) obj;
        if (!TextUtils.equals(this.f522a, m5Var.f522a) || !TextUtils.equals(this.b, m5Var.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.f522a.hashCode() * 31) + this.b.hashCode();
    }

    public String toString() {
        return "Header[name=" + this.f522a + ",value=" + this.b + "]";
    }
}
