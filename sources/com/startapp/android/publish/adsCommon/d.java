package com.startapp.android.publish.adsCommon;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Pair;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.adListeners.b;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.common.g;

/* compiled from: StartAppSDK */
public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    protected final Context f230a;
    /* access modifiers changed from: protected */
    public final Ad b;
    protected final AdPreferences c;
    /* access modifiers changed from: protected */
    public final AdEventListener d;
    protected AdPreferences.Placement e;
    protected String f = null;

    /* access modifiers changed from: protected */
    public abstract boolean a(Object obj);

    /* access modifiers changed from: protected */
    public abstract Object e();

    public d(Context context, Ad ad, AdPreferences adPreferences, AdEventListener adEventListener, AdPreferences.Placement placement) {
        this.f230a = context;
        this.b = ad;
        this.c = adPreferences;
        this.d = new b(adEventListener);
        this.e = placement;
    }

    public void c() {
        g.a(g.a.HIGH, (Runnable) new Runnable() {
            public void run() {
                Process.setThreadPriority(10);
                final Boolean d = d.this.d();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        d.this.a(d);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public Boolean d() {
        return Boolean.valueOf(a(e()));
    }

    /* access modifiers changed from: protected */
    public void a(Boolean bool) {
        b(bool);
        if (!bool.booleanValue()) {
            this.b.setErrorMessage(this.f);
            this.d.onFailedToReceiveAd(this.b);
        }
    }

    /* access modifiers changed from: protected */
    public void b(Boolean bool) {
        this.b.setState(bool.booleanValue() ? Ad.AdState.READY : Ad.AdState.UN_INITIALIZED);
    }

    /* access modifiers changed from: protected */
    public GetAdRequest a() {
        return b(new GetAdRequest());
    }

    /* access modifiers changed from: protected */
    public GetAdRequest b(GetAdRequest getAdRequest) {
        Pair<String, String> d2 = l.d(this.f230a);
        try {
            getAdRequest.fillAdPreferences(this.f230a, this.c, this.e, d2);
            if (!b.a().D()) {
                com.startapp.common.a.g.a(6, "forceExternal - check - request - metadata false disabletwoclicks");
                if (c.a(this.f230a, this.e)) {
                    getAdRequest.setDisableTwoClicks(true);
                }
            }
            try {
                getAdRequest.fillApplicationDetails(this.f230a, this.c, false);
            } catch (Exception e2) {
                f.a(this.f230a, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "BaseService.GetAdRequest - fillApplicationDetails failed", e2.getMessage(), "");
            }
            return getAdRequest;
        } catch (Exception unused) {
            l.a(d2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public AdPreferences.Placement f() {
        return this.e;
    }
}
