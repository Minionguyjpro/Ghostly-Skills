package com.startapp.android.publish.ads.c.b;

import android.content.Context;
import android.content.Intent;
import com.startapp.android.publish.ads.list3d.List3DActivity;
import com.startapp.android.publish.ads.list3d.f;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.c;
import com.startapp.android.publish.adsCommon.g;
import com.startapp.android.publish.adsCommon.h;
import com.startapp.android.publish.common.model.AdPreferences;
import java.util.UUID;

/* compiled from: StartAppSDK */
public class b extends h implements g {

    /* renamed from: a  reason: collision with root package name */
    private static String f56a = null;
    private static final long serialVersionUID = 1;
    private final String uuid = UUID.randomUUID().toString();

    public b(Context context) {
        super(context, AdPreferences.Placement.INAPP_OFFER_WALL);
        if (f56a == null) {
            f56a = i.c(context);
        }
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new a(this.context, this, adPreferences, adEventListener).c();
    }

    public boolean a(String str) {
        f.a().a(this.uuid).b(c.a());
        boolean a2 = this.activityExtra != null ? this.activityExtra.a() : false;
        if (hasAdCacheTtlPassed()) {
            setNotDisplayedReason(AdDisplayListener.NotDisplayedReason.AD_EXPIRED);
            return false;
        }
        Intent intent = new Intent(this.context, List3DActivity.class);
        intent.putExtra("adInfoOverride", getAdInfoOverride());
        intent.putExtra("fullscreen", a2);
        intent.putExtra("adTag", str);
        intent.putExtra("lastLoadTime", getLastLoadTime());
        intent.putExtra("adCacheTtl", getAdCacheTtl());
        intent.putExtra("position", c.b());
        intent.putExtra("listModelUuid", this.uuid);
        intent.addFlags(343932928);
        this.context.startActivity(intent);
        if (AdsConstants.OVERRIDE_NETWORK.booleanValue()) {
            return true;
        }
        setState(Ad.AdState.UN_INITIALIZED);
        return true;
    }

    public String a_() {
        return f56a;
    }

    public String a() {
        return this.uuid;
    }

    public Long getLastLoadTime() {
        return super.getLastLoadTime();
    }

    public Long getAdCacheTtl() {
        return super.getAdCacheTtl();
    }

    public boolean hasAdCacheTtlPassed() {
        return super.hasAdCacheTtlPassed();
    }

    public boolean getVideoCancelCallBack() {
        return super.getVideoCancelCallBack();
    }

    public void setVideoCancelCallBack(boolean z) {
        super.setVideoCancelCallBack(z);
    }
}
