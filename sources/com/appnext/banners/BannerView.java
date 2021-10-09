package com.appnext.banners;

import android.content.Context;
import android.util.AttributeSet;
import com.appnext.core.callbacks.OnECPMLoaded;

public class BannerView extends BaseBannerView {
    public BannerView(Context context) {
        super(context);
    }

    public BannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public BannerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public e getBannerAdapter() {
        if (this.bannerAdapter == null) {
            this.bannerAdapter = new g();
        }
        return this.bannerAdapter;
    }

    public void getECPM(BannerAdRequest bannerAdRequest, OnECPMLoaded onECPMLoaded) {
        super.getECPM(bannerAdRequest, onECPMLoaded);
    }
}
