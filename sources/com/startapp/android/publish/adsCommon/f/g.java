package com.startapp.android.publish.adsCommon.f;

import android.content.Context;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.l;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.e;
import com.startapp.common.g;
import java.util.Map;

/* compiled from: StartAppSDK */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private final Context f246a;
    private final AdPreferences b;
    private final e c;
    /* access modifiers changed from: private */
    public final a d;

    /* compiled from: StartAppSDK */
    public interface a {
        void a(boolean z);
    }

    public g(Context context, AdPreferences adPreferences, e eVar, a aVar) {
        this.f246a = context;
        this.b = adPreferences;
        this.c = eVar;
        this.d = aVar;
    }

    public void a() {
        com.startapp.common.g.a(g.a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                boolean b = g.this.b();
                if (g.this.d != null) {
                    g.this.d.a(b);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        com.startapp.common.a.g.a(3, "Sending InfoEvent " + this.c);
        try {
            i.a(this.f246a, this.b);
            try {
                l.b(this.f246a);
                this.c.fillLocationDetails(this.b, this.f246a);
                this.c.fillApplicationDetails(this.f246a, this.b);
            } catch (Exception unused) {
            }
            try {
                com.startapp.common.a.g.a(3, "Networking InfoEvent");
                String a2 = MetaData.getInstance().getAnalyticsConfig().a();
                if (d.PERIODIC.equals(this.c.e())) {
                    a2 = MetaData.getInstance().getAnalyticsConfig().b();
                }
                com.startapp.android.publish.adsCommon.k.a.a(this.f246a, a2, this.c, (Map<String, String>) null, MetaData.getInstance().getAnalyticsConfig().d(), MetaData.getInstance().getAnalyticsConfig().e());
                return true;
            } catch (e e) {
                com.startapp.common.a.g.a(6, "Unable to send InfoEvent command!!!!", (Throwable) e);
                return false;
            }
        } catch (Exception e2) {
            com.startapp.common.a.g.a(6, "Unable to fill AdPreferences ", (Throwable) e2);
            return false;
        }
    }
}
