package com.startapp.android.publish.adsCommon.f;

import com.startapp.android.publish.adsCommon.Utils.c;
import com.startapp.android.publish.adsCommon.Utils.e;
import com.startapp.common.a.a;

/* compiled from: StartAppSDK */
public class b extends e {

    /* renamed from: a  reason: collision with root package name */
    private String f241a;
    private String b;
    private boolean c;
    private String d;

    public b(d dVar) {
        super(dVar);
    }

    public e getNameValueJson() {
        e nameValueJson = super.getNameValueJson();
        if (nameValueJson == null) {
            nameValueJson = new c();
        }
        nameValueJson.a("sens", (Object) a(), false);
        nameValueJson.a("bt", (Object) b(), false);
        nameValueJson.a("isService", (Object) Boolean.valueOf(c()), false);
        nameValueJson.a("packagingType", (Object) d(), false);
        return nameValueJson;
    }

    public String a() {
        return this.f241a;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        if (str != null) {
            this.f241a = a.c(str);
        }
    }

    public void b(String str) {
        if (str != null) {
            this.b = a.c(str);
        }
    }

    public boolean c() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public String toString() {
        return super.toString() + " DataEventRequest [" + "sensors=" + this.f241a + ", bluetooth=" + this.b + ", isService=" + this.c + ", packagingType=" + this.d + "]";
    }
}
