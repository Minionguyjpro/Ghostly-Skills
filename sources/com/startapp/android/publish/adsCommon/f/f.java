package com.startapp.android.publish.adsCommon.f;

import android.app.ActivityManager;
import android.content.Context;
import com.appnext.base.b.d;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.f.g;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.a.c;

/* compiled from: StartAppSDK */
public class f {
    public static void a(Context context, d dVar, String str, String str2, String str3) {
        a(context, new e(dVar, str, str2), str3, (g.a) null);
    }

    public static void a(Context context, d dVar, String str, String str2, String str3, g.a aVar) {
        a(context, new e(dVar, str, str2), str3, aVar);
    }

    public static void a(Context context, e eVar, String str) {
        a(context, eVar, str, (g.a) null);
    }

    public static void a(Context context, e eVar, String str, g.a aVar) {
        if (!MetaData.getInstance().getAnalyticsConfig().c()) {
            eVar.e(str);
            eVar.a(context);
            try {
                eVar.f(i.b(context));
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
                eVar.h(Long.toString(memoryInfo.availMem / d.fc));
                Long a2 = c.a(memoryInfo);
                if (a2 != null) {
                    eVar.g(Long.toString((a2.longValue() - memoryInfo.availMem) / d.fc));
                }
            } catch (Throwable th) {
                com.startapp.common.a.g.a("InfoEventsManager", 6, "Error filling infoEvent", th);
            }
            com.startapp.common.a.g.a("InfoEventsManager", 3, "Sending " + eVar);
            new g(context, new AdPreferences(), eVar, aVar).a();
        }
    }
}
