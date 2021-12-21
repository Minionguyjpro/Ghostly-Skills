package com.b.a.a.a;

import android.content.Context;
import com.b.a.a.a.c.b;
import com.b.a.a.a.e.e;

public class c {

    /* renamed from: a  reason: collision with root package name */
    private boolean f988a;

    private void b(String str, Context context) {
        c(str);
        e.a((Object) context, "Application Context cannot be null");
    }

    private void c(String str) {
        e.a((Object) str, "Version cannot be null");
        if (!str.matches("[0-9]+\\.[0-9]+\\.[0-9]+.*")) {
            throw new IllegalArgumentException("Invalid version format : " + str);
        }
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return "1.2.0-Startapp";
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.f988a = z;
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str) {
        return b(a()) == b(str);
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str, Context context) {
        b(str, context);
        if (!a(str)) {
            return false;
        }
        if (!b()) {
            a(true);
            com.b.a.a.a.c.e.a().a(context);
            b.a().a(context);
            com.b.a.a.a.e.b.a(context);
            com.b.a.a.a.c.c.a().a(context);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public int b(String str) {
        c(str);
        return Integer.parseInt(str.split("\\.", 2)[0]);
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.f988a;
    }
}
