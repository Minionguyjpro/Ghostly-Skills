package com.startapp.android.publish.a;

import android.content.Context;
import android.content.Intent;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.b.c;
import com.startapp.android.publish.adsCommon.d;
import com.startapp.android.publish.adsCommon.h;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.android.publish.common.model.GetAdResponse;
import com.startapp.common.a.g;
import com.startapp.common.b;
import com.startapp.common.e;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: StartAppSDK */
public abstract class a extends d {
    private int g = 0;
    private Set<String> h = new HashSet();

    /* access modifiers changed from: protected */
    public abstract void a(Ad ad);

    public a(Context context, Ad ad, AdPreferences adPreferences, AdEventListener adEventListener, AdPreferences.Placement placement) {
        super(context, ad, adPreferences, adEventListener, placement);
    }

    /* access modifiers changed from: protected */
    public Object e() {
        GetAdRequest a2 = a();
        if (a2 == null) {
            return null;
        }
        if (this.h.size() == 0) {
            this.h.add(this.f230a.getPackageName());
        }
        boolean z = false;
        if (this.g > 0) {
            a2.setEngInclude(false);
        }
        a2.setPackageExclude(this.h);
        if (this.g == 0) {
            z = true;
        }
        a2.setEngInclude(z);
        try {
            return (GetAdResponse) com.startapp.android.publish.adsCommon.k.a.a(this.f230a, AdsConstants.a(AdsConstants.AdApiType.JSON, f()), a2, (Map<String, String>) null, GetAdResponse.class);
        } catch (e e) {
            g.a("AppPresence", 6, "Unable to handle GetAdsSetService command!!!!", e);
            this.f = e.getMessage();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(Object obj) {
        GetAdResponse getAdResponse = (GetAdResponse) obj;
        boolean z = false;
        if (obj == null) {
            this.f = "Empty Response";
            g.a("AppPresence", 6, "Error Empty Response");
            return false;
        } else if (!getAdResponse.isValidResponse()) {
            this.f = getAdResponse.getErrorMessage();
            g.a("AppPresence", 6, "Error msg = [" + this.f + "]");
            return false;
        } else {
            h hVar = (h) this.b;
            List<AdDetails> a2 = c.a(this.f230a, getAdResponse.getAdsDetails(), this.g, this.h);
            hVar.a(a2);
            hVar.setAdInfoOverride(getAdResponse.getAdInfoOverride());
            if (getAdResponse.getAdsDetails() != null && getAdResponse.getAdsDetails().size() > 0) {
                z = true;
            }
            if (!z) {
                this.f = "Empty Response";
            } else if (a2.size() == 0 && this.g == 0) {
                g.a("AppPresence", 3, "Packages exists - another request");
                return b();
            }
            return z;
        }
    }

    private boolean b() {
        this.g++;
        return d().booleanValue();
    }

    /* access modifiers changed from: protected */
    public void a(Boolean bool) {
        super.a(bool);
        Intent intent = new Intent("com.startapp.android.OnReceiveResponseBroadcastListener");
        intent.putExtra("adHashcode", this.b.hashCode());
        intent.putExtra("adResult", bool);
        b.a(this.f230a).a(intent);
        if (bool.booleanValue()) {
            a((Ad) (h) this.b);
            this.d.onReceiveAd(this.b);
        }
    }
}
