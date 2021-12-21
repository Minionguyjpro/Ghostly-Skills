package com.startapp.android.publish.ads.video.c.a.a;

import android.text.TextUtils;
import com.startapp.common.a.g;

/* compiled from: StartAppSDK */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private String f121a;
    private String b;
    private String c;
    private String d;
    private Integer e;
    private Integer f;
    private Integer g;
    private Boolean h;
    private Boolean i;
    private String j;

    private boolean a(int i2) {
        return i2 > 0 && i2 < 5000;
    }

    public String a() {
        return this.f121a;
    }

    public void a(String str) {
        this.f121a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public String b() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public Integer c() {
        return this.e;
    }

    public void a(Integer num) {
        this.e = num;
    }

    public Integer d() {
        return this.f;
    }

    public void b(Integer num) {
        this.f = num;
    }

    public Integer e() {
        return this.g;
    }

    public void c(Integer num) {
        this.g = num;
    }

    public void a(Boolean bool) {
        this.h = bool;
    }

    public void b(Boolean bool) {
        this.i = bool;
    }

    public void e(String str) {
        this.j = str;
    }

    public boolean f() {
        if (TextUtils.isEmpty(b())) {
            g.a("VASTMediaFile", 3, "Validator error: mediaFile type empty");
            return false;
        }
        Integer e2 = e();
        Integer d2 = d();
        if (e2 == null || d2 == null || !a(e2.intValue()) || !a(d2.intValue())) {
            g.a("VASTMediaFile", 3, "Validator error: mediaFile invalid size");
            return false;
        } else if (!TextUtils.isEmpty(a())) {
            return true;
        } else {
            g.a("VASTMediaFile", 3, "Validator error: mediaFile url empty");
            return false;
        }
    }

    public String toString() {
        return "MediaFile [url=" + this.f121a + ", id=" + this.b + ", delivery=" + this.c + ", type=" + this.d + ", bitrate=" + this.e + ", width=" + this.f + ", height=" + this.g + ", scalable=" + this.h + ", maintainAspectRatio=" + this.i + ", apiFramework=" + this.j + "]";
    }
}
