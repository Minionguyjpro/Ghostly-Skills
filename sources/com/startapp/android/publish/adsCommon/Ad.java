package com.startapp.android.publish.adsCommon;

import android.content.Context;
import com.startapp.android.publish.adsCommon.Utils.g;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.adListeners.b;
import com.startapp.android.publish.adsCommon.adinformation.c;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.d;
import com.startapp.android.publish.common.model.AdPreferences;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public abstract class Ad implements Serializable {
    private static boolean init = false;
    private static final long serialVersionUID = 1;
    protected a activityExtra;
    protected Long adCacheTtl = null;
    private c adInfoOverride;
    protected boolean belowMinCPM = false;
    protected transient Context context;
    protected String errorMessage = null;
    protected Serializable extraData = null;
    private Long lastLoadTime = null;
    private AdDisplayListener.NotDisplayedReason notDisplayedReason;
    protected AdPreferences.Placement placement;
    private AdState state = AdState.UN_INITIALIZED;
    private AdType type;
    private boolean videoCancelCallBack;

    /* compiled from: StartAppSDK */
    public enum AdState {
        UN_INITIALIZED,
        PROCESSING,
        READY
    }

    /* compiled from: StartAppSDK */
    public enum AdType {
        INTERSTITIAL,
        RICH_TEXT,
        VIDEO,
        REWARDED_VIDEO,
        NON_VIDEO,
        VIDEO_NO_VAST
    }

    /* access modifiers changed from: protected */
    public abstract void loadAds(AdPreferences adPreferences, AdEventListener adEventListener);

    @Deprecated
    public boolean show() {
        return false;
    }

    public Ad(Context context2, AdPreferences.Placement placement2) {
        this.context = context2;
        this.placement = placement2;
        if (i.e()) {
            this.adInfoOverride = c.a();
        }
    }

    public Serializable getExtraData() {
        return this.extraData;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    public void setActivityExtra(a aVar) {
        this.activityExtra = aVar;
    }

    public void setExtraData(Serializable serializable) {
        this.extraData = serializable;
    }

    public AdState getState() {
        return this.state;
    }

    public boolean isBelowMinCPM() {
        return this.belowMinCPM;
    }

    public void setState(AdState adState) {
        this.state = adState;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public c getAdInfoOverride() {
        return this.adInfoOverride;
    }

    public void setAdInfoOverride(c cVar) {
        this.adInfoOverride = cVar;
    }

    /* access modifiers changed from: protected */
    public AdPreferences.Placement getPlacement() {
        return this.placement;
    }

    /* access modifiers changed from: protected */
    public void setPlacement(AdPreferences.Placement placement2) {
        this.placement = placement2;
    }

    @Deprecated
    public boolean load() {
        return load(new AdPreferences(), (AdEventListener) null);
    }

    @Deprecated
    public boolean load(AdEventListener adEventListener) {
        return load(new AdPreferences(), adEventListener);
    }

    @Deprecated
    public boolean load(AdPreferences adPreferences) {
        return load(adPreferences, (AdEventListener) null);
    }

    @Deprecated
    public boolean load(AdPreferences adPreferences, AdEventListener adEventListener) {
        return load(adPreferences, adEventListener, true);
    }

    /* access modifiers changed from: protected */
    public boolean load(final AdPreferences adPreferences, AdEventListener adEventListener, boolean z) {
        boolean z2;
        final b bVar = new b(adEventListener);
        final AnonymousClass1 r10 = new AdEventListener() {
            public void onReceiveAd(Ad ad) {
                Ad.this.setLastLoadTime(Long.valueOf(System.currentTimeMillis()));
                bVar.onReceiveAd(ad);
            }

            public void onFailedToReceiveAd(Ad ad) {
                bVar.onFailedToReceiveAd(ad);
            }
        };
        if (!init) {
            l.c(this.context);
            init = true;
        }
        i.a(this.context, adPreferences);
        String str = "";
        if (adPreferences.getProductId() == null || str.equals(adPreferences.getProductId())) {
            str = "app ID was not set.";
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.state != AdState.UN_INITIALIZED) {
            str = "load() was already called.";
            z2 = true;
        }
        if (!i.a(this.context)) {
            str = "network not available.";
            z2 = true;
        }
        if (!canShowAd()) {
            str = "serving ads disabled";
            z2 = true;
        }
        if (z2) {
            setErrorMessage("Ad wasn't loaded: " + str);
            r10.onFailedToReceiveAd(this);
            return false;
        }
        setState(AdState.PROCESSING);
        AnonymousClass2 r7 = new d() {
            public void a() {
                MetaData.getInstance().removeMetaDataListener(this);
                Ad.this.loadAds(adPreferences, r10);
            }

            public void b() {
                MetaData.getInstance().removeMetaDataListener(this);
                Ad.this.loadAds(adPreferences, r10);
            }
        };
        if (adPreferences.getType() != null) {
            setType(adPreferences.getType());
        }
        MetaData.getInstance().loadFromServer(this.context, adPreferences, g.d().c(), z, r7);
        return true;
    }

    public boolean isReady() {
        return this.state == AdState.READY && !hasAdCacheTtlPassed();
    }

    public AdDisplayListener.NotDisplayedReason getNotDisplayedReason() {
        return this.notDisplayedReason;
    }

    /* access modifiers changed from: protected */
    public void setNotDisplayedReason(AdDisplayListener.NotDisplayedReason notDisplayedReason2) {
        this.notDisplayedReason = notDisplayedReason2;
    }

    /* access modifiers changed from: protected */
    public Long getAdCacheTtl() {
        long fallbackAdCacheTtl = getFallbackAdCacheTtl();
        Long l = this.adCacheTtl;
        if (l != null) {
            fallbackAdCacheTtl = Math.min(l.longValue(), fallbackAdCacheTtl);
        }
        return Long.valueOf(fallbackAdCacheTtl);
    }

    /* access modifiers changed from: protected */
    public long getFallbackAdCacheTtl() {
        return com.startapp.android.publish.cache.d.a().b().getAdCacheTtl();
    }

    /* access modifiers changed from: protected */
    public Long getLastLoadTime() {
        return this.lastLoadTime;
    }

    /* access modifiers changed from: private */
    public void setLastLoadTime(Long l) {
        this.lastLoadTime = l;
    }

    /* access modifiers changed from: protected */
    public boolean hasAdCacheTtlPassed() {
        if (this.lastLoadTime != null && System.currentTimeMillis() - this.lastLoadTime.longValue() > getAdCacheTtl().longValue()) {
            return true;
        }
        return false;
    }

    private void setType(AdType adType) {
        this.type = adType;
    }

    public AdType getType() {
        return this.type;
    }

    /* access modifiers changed from: protected */
    public boolean getVideoCancelCallBack() {
        return this.videoCancelCallBack;
    }

    /* access modifiers changed from: protected */
    public void setVideoCancelCallBack(boolean z) {
        this.videoCancelCallBack = z;
    }

    /* access modifiers changed from: protected */
    public boolean canShowAd() {
        return MetaData.getInstance().canShowAd();
    }
}
