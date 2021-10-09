package com.b.a.a.a.e;

import android.text.TextUtils;
import com.b.a.a.a.a;
import com.b.a.a.a.b.f;
import com.b.a.a.a.b.i;

public class e {
    public static void a() {
        if (!a.b()) {
            throw new IllegalStateException("Method called before OMID activation");
        }
    }

    public static void a(f fVar) {
        if (fVar.equals(f.NONE)) {
            throw new IllegalArgumentException("Impression owner is none");
        }
    }

    public static void a(i iVar) {
        if (iVar.j()) {
            throw new IllegalStateException("AdSession is started");
        }
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void a(String str, int i, String str2) {
        if (str.length() > i) {
            throw new IllegalArgumentException(str2);
        }
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(str2);
        }
    }

    public static void b(i iVar) {
        if (iVar.k()) {
            throw new IllegalStateException("AdSession is finished");
        }
    }

    public static void c(i iVar) {
        h(iVar);
        b(iVar);
    }

    public static void d(i iVar) {
        if (iVar.f().d() != null) {
            throw new IllegalStateException("AdEvents already exists for AdSession");
        }
    }

    public static void e(i iVar) {
        if (iVar.f().e() != null) {
            throw new IllegalStateException("VideoEvents already exists for AdSession");
        }
    }

    public static void f(i iVar) {
        if (!iVar.l()) {
            throw new IllegalStateException("Impression event is not expected from the Native AdSession");
        }
    }

    public static void g(i iVar) {
        if (!iVar.m()) {
            throw new IllegalStateException("Cannot create VideoEvents for JavaScript AdSession");
        }
    }

    private static void h(i iVar) {
        if (!iVar.j()) {
            throw new IllegalStateException("AdSession is not started");
        }
    }
}
