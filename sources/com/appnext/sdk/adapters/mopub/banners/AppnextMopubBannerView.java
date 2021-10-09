package com.appnext.sdk.adapters.mopub.banners;

import android.content.Context;
import android.util.AttributeSet;
import com.appnext.banners.BannerView;
import com.appnext.banners.e;

public class AppnextMopubBannerView extends BannerView {
    public AppnextMopubBannerView(Context context) {
        super(context);
    }

    public AppnextMopubBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AppnextMopubBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public AppnextMopubBannerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public e getBannerAdapter() {
        if (this.bannerAdapter == null) {
            this.bannerAdapter = new AppnextMoPubBannerAdapter();
        }
        return this.bannerAdapter;
    }
}
