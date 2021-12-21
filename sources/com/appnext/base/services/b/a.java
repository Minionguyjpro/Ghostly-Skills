package com.appnext.base.services.b;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.appnext.base.services.a.b;
import com.appnext.base.services.a.c;
import java.util.List;

public class a {
    private static volatile a eM;
    private c eN;

    public a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                this.eN = new b(context);
            } else {
                this.eN = new com.appnext.base.services.a.a(context);
            }
        } catch (Throwable unused) {
        }
    }

    public static a d(Context context) {
        if (eM == null) {
            synchronized (a.class) {
                if (eM == null) {
                    eM = new a(context);
                }
            }
        }
        return eM;
    }

    public final void a(com.appnext.base.a.b.c cVar, boolean z) {
        this.eN.a(cVar, z, (Bundle) null);
    }

    public final void c(com.appnext.base.a.b.c cVar) {
        this.eN.c(cVar);
    }

    public final void h(List<com.appnext.base.a.b.c> list) {
        this.eN.h(list);
    }
}
