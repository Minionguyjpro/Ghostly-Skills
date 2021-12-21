package com.startapp.android.publish.adsCommon.Utils;

import android.content.Context;
import com.startapp.android.publish.adsCommon.a.b;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.MetaDataRequest;
import com.startapp.android.publish.common.metaData.d;
import com.startapp.android.publish.common.model.AdPreferences;
import java.util.UUID;

/* compiled from: StartAppSDK */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private static g f180a = new g();
    private String b = "";
    private long c = 0;
    private MetaDataRequest.a d = MetaDataRequest.a.LAUNCH;

    public String a() {
        return this.b;
    }

    public long b() {
        return this.c;
    }

    public MetaDataRequest.a c() {
        return this.d;
    }

    public synchronized void a(Context context, MetaDataRequest.a aVar) {
        this.b = UUID.randomUUID().toString();
        this.c = System.currentTimeMillis();
        this.d = aVar;
        com.startapp.common.a.g.a("SessionManager", 3, "Starting new session: reason=" + aVar + " sessionId=" + this.b);
        if (!i.a()) {
            b.a().b();
        }
        AdPreferences adPreferences = new AdPreferences();
        i.a(context, adPreferences);
        MetaData.getInstance().loadFromServer(context, adPreferences, aVar, false, (d) null, true);
    }

    public static g d() {
        return f180a;
    }
}
