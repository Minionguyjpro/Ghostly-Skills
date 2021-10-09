package com.tappx.a;

import android.content.Context;
import android.view.View;
import com.tappx.a.m0;

public interface q0 {

    public static class a {
        public static q0 a(Context context) {
            try {
                return new s0(context);
            } catch (Throwable unused) {
                j0.b(f.b("krJOYpdJwB0z9kroej+tvgvunIIlLf/GdGehIr+r2OSbd/1jAuDbW6Z7w8Rb+zP0p97z+Ss5rCSYnT4eKWDNHxv5azbxwwxG3XGQe+SC2+3s6Z9kUQ084l1qIWDEae3FGWLeg8k8luby4GoV6Q0RRg"), new Object[0]);
                return new r0();
            }
        }
    }

    View a();

    void a(m0.c cVar, Runnable runnable);

    void a(String str, int i, int i2);

    void destroy();

    void loadAd();
}
