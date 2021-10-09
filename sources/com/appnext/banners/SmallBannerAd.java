package com.appnext.banners;

import android.content.Context;
import com.appnext.core.Ad;
import com.appnext.core.callbacks.OnECPMLoaded;

public class SmallBannerAd extends BannerAd {
    public String getAUID() {
        return "1000";
    }

    public /* bridge */ /* synthetic */ void getECPM(OnECPMLoaded onECPMLoaded) {
        super.getECPM(onECPMLoaded);
    }

    public /* bridge */ /* synthetic */ String getTID() {
        return super.getTID();
    }

    public /* bridge */ /* synthetic */ String getTemId(BannerAdData bannerAdData) {
        return super.getTemId(bannerAdData);
    }

    public /* bridge */ /* synthetic */ String getVID() {
        return super.getVID();
    }

    public /* bridge */ /* synthetic */ boolean isAdLoaded() {
        return super.isAdLoaded();
    }

    public /* bridge */ /* synthetic */ void loadAd() {
        super.loadAd();
    }

    public /* bridge */ /* synthetic */ void showAd() {
        super.showAd();
    }

    public SmallBannerAd(Context context, String str) {
        super(context, str);
    }

    protected SmallBannerAd(Ad ad) {
        super(ad);
    }
}
