package com.startapp.android.publish.adsCommon.g.b;

import android.content.Context;
import android.os.Build;
import com.startapp.common.a.c;
import com.startapp.common.a.g;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private Context f250a;
    private List<String> b = new ArrayList();

    public b(Context context) {
        this.f250a = context.getApplicationContext();
    }

    public boolean a() {
        boolean z = this.b.contains("calendar") && Build.VERSION.SDK_INT >= 14 && c.a(this.f250a, "android.permission.WRITE_CALENDAR");
        g.a("MraidNativeFeatureManager", 3, "isCalendarSupported " + z);
        return z;
    }

    public boolean b() {
        boolean contains = this.b.contains("inlineVideo");
        g.a("MraidNativeFeatureManager", 3, "isInlineVideoSupported " + contains);
        return contains;
    }

    public boolean c() {
        boolean z = this.b.contains("sms") && c.a(this.f250a, "android.permission.SEND_SMS");
        g.a("MraidNativeFeatureManager", 3, "isSmsSupported " + z);
        return z;
    }

    public boolean d() {
        boolean contains = this.b.contains("storePicture");
        g.a("MraidNativeFeatureManager", 3, "isStorePictureSupported " + contains);
        return contains;
    }

    public boolean e() {
        boolean z = this.b.contains("tel") && c.a(this.f250a, "android.permission.CALL_PHONE");
        g.a("MraidNativeFeatureManager", 3, "isTelSupported " + z);
        return z;
    }

    public List<String> f() {
        return this.b;
    }

    public boolean a(String str) {
        return f().contains(str);
    }
}
