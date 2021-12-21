package com.startapp.android.publish.ads.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.activities.AppWallActivity;
import com.startapp.android.publish.adsCommon.activities.FullScreenActivity;
import com.startapp.android.publish.adsCommon.activities.OverlayActivity;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.b;
import com.startapp.android.publish.adsCommon.e;
import com.startapp.android.publish.adsCommon.g;
import com.startapp.android.publish.adsCommon.n;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public abstract class c extends e implements g {
    private static final long serialVersionUID = 1;

    /* access modifiers changed from: protected */
    public boolean a() {
        return false;
    }

    public c(Context context, AdPreferences.Placement placement) {
        super(context, placement);
    }

    /* JADX WARNING: type inference failed for: r10v5, types: [java.lang.Boolean[], java.io.Serializable] */
    public boolean a(String str) {
        String b = com.startapp.android.publish.adsCommon.c.b();
        if (!a() || !b.a().H().a().equals(n.a.DISABLED) || !b.equals("back")) {
            if (!AdsConstants.OVERRIDE_NETWORK.booleanValue()) {
                setState(Ad.AdState.UN_INITIALIZED);
            }
            if (f() == null) {
                setNotDisplayedReason(AdDisplayListener.NotDisplayedReason.INTERNAL_ERROR);
                return false;
            } else if (hasAdCacheTtlPassed()) {
                setNotDisplayedReason(AdDisplayListener.NotDisplayedReason.AD_EXPIRED);
                return false;
            } else {
                boolean a2 = this.activityExtra != null ? this.activityExtra.a() : false;
                Intent intent = new Intent(this.context, f(b));
                intent.putExtra("fileUrl", "exit.html");
                String[] l = l();
                String a3 = com.startapp.android.publish.adsCommon.c.a();
                for (int i = 0; i < l.length; i++) {
                    if (l[i] != null && !"".equals(l[i])) {
                        l[i] = l[i] + a3;
                    }
                }
                intent.putExtra("tracking", l);
                intent.putExtra("trackingClickUrl", m());
                intent.putExtra("packageNames", o());
                intent.putExtra("htmlUuid", g());
                intent.putExtra("smartRedirect", this.smartRedirect);
                intent.putExtra("browserEnabled", k());
                intent.putExtra("placement", this.placement.getIndex());
                intent.putExtra("adInfoOverride", getAdInfoOverride());
                intent.putExtra("ad", this);
                intent.putExtra("videoAd", a());
                intent.putExtra("fullscreen", a2);
                intent.putExtra("orientation", b());
                intent.putExtra("adTag", str);
                intent.putExtra("lastLoadTime", getLastLoadTime());
                intent.putExtra("adCacheTtl", getAdCacheTtl());
                intent.putExtra("closingUrl", i());
                if (p() != null) {
                    intent.putExtra("delayImpressionSeconds", p());
                }
                intent.putExtra("sendRedirectHops", q());
                intent.putExtra("mraidAd", r());
                if (r()) {
                    intent.putExtra("activityShouldLockOrientation", false);
                }
                if (i.a(8) && (this instanceof com.startapp.android.publish.ads.splash.b)) {
                    intent.putExtra("isSplash", true);
                }
                intent.putExtra("position", b);
                intent.addFlags(343932928);
                this.context.startActivity(intent);
                return true;
            }
        } else {
            setNotDisplayedReason(AdDisplayListener.NotDisplayedReason.VIDEO_BACK);
            return false;
        }
    }

    private Class<?> f(String str) {
        if (g(str)) {
            return FullScreenActivity.class;
        }
        return i.a(getContext(), (Class<? extends Activity>) OverlayActivity.class, (Class<? extends Activity>) AppWallActivity.class);
    }

    private boolean g(String str) {
        return (d() || a() || r() || str.equals("back")) && i.a(getContext(), (Class<? extends Activity>) FullScreenActivity.class);
    }

    private boolean d() {
        return (n() == 0 || n() == this.context.getResources().getConfiguration().orientation) ? false : true;
    }

    /* access modifiers changed from: protected */
    public int b() {
        return n() == 0 ? this.context.getResources().getConfiguration().orientation : n();
    }

    public String a_() {
        return super.a_();
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
