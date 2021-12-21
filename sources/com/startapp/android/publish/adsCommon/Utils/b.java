package com.startapp.android.publish.adsCommon.Utils;

import android.content.Context;
import android.os.SystemClock;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.k;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.e;
import com.startapp.android.publish.common.metaData.f;
import com.startapp.common.a.g;
import com.startapp.common.b.a.c;
import com.startapp.common.b.b;

/* compiled from: StartAppSDK */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f175a = false;

    /* compiled from: StartAppSDK */
    public static final class a implements com.startapp.common.b.a.a {
        public com.startapp.common.b.a.b create(int i) {
            if (i == 586482792) {
                return new f();
            }
            if (i != 786564404) {
                return null;
            }
            return new e();
        }
    }

    public static void a(Context context) {
        if (!f175a) {
            f175a = true;
            com.startapp.common.b.a.a((c) new c() {
                public void a(int i, String str, String str2, Throwable th) {
                    g.a(str, i, str2, th);
                }
            });
            com.startapp.common.b.a.a(context);
            com.startapp.common.b.a.a((com.startapp.common.b.a.a) new a());
        }
    }

    public static long a() {
        return SystemClock.elapsedRealtime() + (((long) MetaData.getInstance().getPeriodicMetaDataInterval()) * 60000);
    }

    public static long b(Context context) {
        return SystemClock.elapsedRealtime() + (((long) MetaData.getInstance().getPeriodicInfoEventIntervalInMinutes(context)) * 60000);
    }

    public static void c(Context context) {
        a(context, Long.valueOf(b(context)));
    }

    public static void a(Context context, Long l) {
        g.a("StartAppWall.DataUtils", 3, "setMetaDataPeriodicAlarm executes " + l);
        if (!k.a(context, "periodicMetadataPaused", (Boolean) false).booleanValue() && MetaData.getInstance().isPeriodicMetaDataEnabled()) {
            a(context, 586482792, l.longValue() - SystemClock.elapsedRealtime(), "periodicMetadataTriggerTime");
        }
    }

    public static void d(Context context) {
        a(context, b(context));
    }

    public static void a(Context context, long j) {
        g.a("StartAppWall.DataUtils", 3, "setInfoEventPeriodicAlarm executes " + j);
        if (!k.a(context, "periodicInfoEventPaused", (Boolean) false).booleanValue() && MetaData.getInstance().isPeriodicInfoEventEnabled()) {
            a(context, 786564404, j - SystemClock.elapsedRealtime(), "periodicInfoEventTriggerTime");
        }
    }

    public static void a(int i) {
        com.startapp.common.b.a.a(i, false);
    }

    private static void a(Context context, int i, long j, String str) {
        if (com.startapp.common.b.a.a(new b.a(i).a(j).a())) {
            k.b(context, str, Long.valueOf(j + SystemClock.elapsedRealtime()));
            return;
        }
        d dVar = d.EXCEPTION;
        com.startapp.android.publish.adsCommon.f.f.a(context, dVar, "Util.setPeriodicAlarm - failed setting alarm " + i, "", "");
    }
}
