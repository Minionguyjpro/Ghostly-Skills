package com.tappx.a;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.tappx.sdk.android.AdActivity;
import com.tappx.sdk.android.InterstitialAdActivity;

class z4 {
    public static void a(Context context, String str, f5 f5Var, int i) {
        Class cls;
        if (!f5Var.f() || !a()) {
            cls = AdActivity.class;
        } else {
            cls = InterstitialAdActivity.class;
        }
        Intent intent = new Intent(context, cls);
        y3.a(intent, str);
        intent.putExtra("aavc_fagZVUC6pOQOxawVwpVy", f5Var);
        intent.putExtra("aavc_otZMuRlffpTHI9DsaLyI", i);
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            String name = cls.getName();
            String simpleName = cls.getSimpleName();
            j0.b(f.b("dfKcWOaG8KPoMfm5zts08Qlu05+R8BIzO3YcOMbimy7M7b66oYD1J20myZSpOoOWRYcUsjDmTjtwSPWh2TgTXA"), name, simpleName);
        }
    }

    private static boolean a() {
        return Build.VERSION.SDK_INT < 26;
    }
}
