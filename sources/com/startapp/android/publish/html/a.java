package com.startapp.android.publish.html;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.b;
import com.startapp.android.publish.adsCommon.b.c;
import com.startapp.android.publish.adsCommon.d;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.adsCommon.l;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.common.a.g;
import com.startapp.common.a.h;
import com.startapp.common.e;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: StartAppSDK */
public abstract class a extends d {
    protected Set<String> g = new HashSet();
    protected GetAdRequest h;
    private Set<String> i = new HashSet();
    private List<com.startapp.android.publish.adsCommon.b.a> j = new ArrayList();
    private int k = 0;
    private boolean l;

    /* access modifiers changed from: protected */
    public boolean a(GetAdRequest getAdRequest) {
        return getAdRequest != null;
    }

    public a(Context context, Ad ad, AdPreferences adPreferences, AdEventListener adEventListener, AdPreferences.Placement placement, boolean z) {
        super(context, ad, adPreferences, adEventListener, placement);
        this.l = z;
    }

    /* access modifiers changed from: protected */
    public Object e() {
        GetAdRequest a2 = a();
        this.h = a2;
        if (a(a2)) {
            if (this.i.size() == 0) {
                this.i.add(this.f230a.getPackageName());
            }
            this.h.setPackageExclude(this.i);
            this.h.setCampaignExclude(this.g);
            if (this.k > 0) {
                this.h.setEngInclude(false);
                if (MetaData.getInstance().getSimpleTokenConfig().b(this.f230a)) {
                    l.b(this.f230a);
                }
            }
            try {
                return com.startapp.android.publish.adsCommon.k.a.a(this.f230a, AdsConstants.a(AdsConstants.AdApiType.HTML, f()), this.h, (Map<String, String>) null);
            } catch (e e) {
                if (!MetaData.getInstance().getInvalidNetworkCodesInfoEvents().contains(Integer.valueOf(e.a()))) {
                    f.a(this.f230a, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "BaseHtmlService.sendCommand - network failure", e.getMessage(), "");
                }
                g.a("BaseHtmlService", 6, "Unable to handle GetHtmlAdService command!!!!", e);
                this.f = e.getMessage();
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean a(Object obj) {
        g.a("BaseHtmlService", 4, "Handle Html Response");
        try {
            this.j = new ArrayList();
            String a2 = ((h.a) obj).a();
            if (TextUtils.isEmpty(a2)) {
                if (this.f == null) {
                    if (this.h == null || !this.h.isAdTypeVideo()) {
                        this.f = "Empty Ad";
                    } else {
                        this.f = "Video isn't available";
                    }
                }
                return false;
            }
            List<com.startapp.android.publish.adsCommon.b.a> a3 = c.a(a2, this.k);
            if (b.a().E() ? c.a(this.f230a, a3, this.k, this.i, this.j).booleanValue() : false) {
                return g();
            }
            ((com.startapp.android.publish.adsCommon.e) this.b).a(a3);
            return a(a2);
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void a(Boolean bool) {
        super.a(bool);
        g.a("BaseHtmlService", 4, "Html onPostExecute, result=[" + bool + "]");
    }

    /* access modifiers changed from: protected */
    public void b(Boolean bool) {
        super.b(bool);
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        Intent intent = new Intent("com.startapp.android.OnReceiveResponseBroadcastListener");
        intent.putExtra("adHashcode", this.b.hashCode());
        intent.putExtra("adResult", z);
        com.startapp.common.b.a(this.f230a).a(intent);
        if (!z || this.b == null) {
            g.a("BaseHtmlService", 6, "Html onPostExecute failed error=[" + this.f + "]");
        } else if (this.l) {
            i.a(this.f230a, ((com.startapp.android.publish.adsCommon.e) this.b).f(), (i.a) new i.a() {
                public void a() {
                    a.this.d.onReceiveAd(a.this.b);
                }

                public void a(String str) {
                    a.this.b.setErrorMessage(str);
                    a.this.d.onFailedToReceiveAd(a.this.b);
                }
            });
        } else if (z) {
            this.d.onReceiveAd(this.b);
        } else {
            this.d.onFailedToReceiveAd(this.b);
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(String str) {
        com.startapp.android.publish.adsCommon.e eVar = (com.startapp.android.publish.adsCommon.e) this.b;
        if (com.startapp.android.publish.adsCommon.g.d.a.b(str)) {
            str = com.startapp.android.publish.adsCommon.g.d.a.a(str);
        }
        eVar.b(str);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean g() {
        g.a("BaseHtmlService", 3, "At least one package is present. sending another request to AdPlatform");
        this.k++;
        new com.startapp.android.publish.adsCommon.b.b(this.f230a, this.j).a();
        return d().booleanValue();
    }
}
