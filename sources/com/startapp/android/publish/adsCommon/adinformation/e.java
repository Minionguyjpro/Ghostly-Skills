package com.startapp.android.publish.adsCommon.adinformation;

import android.content.Context;
import android.graphics.Bitmap;
import com.startapp.common.a;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class e implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: a  reason: collision with root package name */
    private transient Bitmap f215a;
    private transient Bitmap b;
    private transient Bitmap c = null;
    private int height = 1;
    private String imageFallbackUrl = "";
    private String imageUrlSecured = "";
    private String name;
    private int width = 1;

    private e() {
    }

    public String a() {
        return this.name;
    }

    public Bitmap a(Context context) {
        if (this.c == null) {
            Bitmap f = f();
            this.c = f;
            if (f == null) {
                this.c = b(context);
            }
        }
        return this.c;
    }

    public int b() {
        return this.width;
    }

    public int c() {
        return this.height;
    }

    public void a(int i) {
        this.width = i;
    }

    public void b(int i) {
        this.height = i;
    }

    public String d() {
        String str = this.imageUrlSecured;
        return str != null ? str : "";
    }

    /* access modifiers changed from: protected */
    public void e() {
        a((Bitmap) null);
        new a(d(), new a.C0009a() {
            public void a(Bitmap bitmap, int i) {
                e.this.a(bitmap);
            }
        }, 0).a();
    }

    public void a(String str) {
        this.imageFallbackUrl = str;
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
        this.name = str;
    }

    /* access modifiers changed from: protected */
    public void a(Bitmap bitmap) {
        this.f215a = bitmap;
        if (bitmap != null) {
            this.c = bitmap;
        }
    }

    /* access modifiers changed from: protected */
    public Bitmap f() {
        return this.f215a;
    }

    /* access modifiers changed from: protected */
    public String g() {
        return this.imageFallbackUrl;
    }

    /* access modifiers changed from: protected */
    public Bitmap b(Context context) {
        if (this.b == null) {
            this.b = com.startapp.android.publish.adsCommon.Utils.a.a(context, g());
        }
        return this.b;
    }

    public static e c(String str) {
        e eVar = new e();
        eVar.b(str);
        return eVar;
    }
}
