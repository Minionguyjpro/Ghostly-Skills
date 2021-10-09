package com.tappx.a;

import android.content.Context;
import android.content.pm.PackageManager;
import android.webkit.WebView;

public class g4 {

    /* renamed from: a  reason: collision with root package name */
    private static final String f452a = (o4.b + i4.b);
    private static int b = 0;

    public g4(Context context) {
        b = a(context);
    }

    private int a(Context context) {
        int i = b;
        if (i != 0) {
            return i;
        }
        try {
            int i2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.targetSdkVersion;
            b = i2;
            return i2;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private boolean b() {
        return a();
    }

    public void a(WebView webView) {
        if (!b()) {
            webView.loadUrl("javascript:" + i4.f471a);
        }
    }

    private boolean a() {
        return b >= 24;
    }

    public String a(String str) {
        return b() ? str.replaceFirst(o4.f539a, f452a) : str;
    }
}
