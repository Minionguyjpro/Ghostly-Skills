package com.moat.analytics.mobile.mpub;

import android.app.Application;
import com.moat.analytics.mobile.mpub.v;

public abstract class MoatAnalytics {

    /* renamed from: a  reason: collision with root package name */
    private static MoatAnalytics f1148a;

    public static synchronized MoatAnalytics getInstance() {
        MoatAnalytics moatAnalytics;
        synchronized (MoatAnalytics.class) {
            if (f1148a == null) {
                try {
                    f1148a = new k();
                } catch (Exception e) {
                    n.a(e);
                    f1148a = new v.a();
                }
            }
            moatAnalytics = f1148a;
        }
        return moatAnalytics;
    }

    public abstract void prepareNativeDisplayTracking(String str);

    public abstract void start(Application application);

    public abstract void start(MoatOptions moatOptions, Application application);
}
