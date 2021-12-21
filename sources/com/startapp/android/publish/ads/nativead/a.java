package com.startapp.android.publish.ads.nativead;

import android.content.Context;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;

/* compiled from: StartAppSDK */
public class a extends com.startapp.android.publish.a.a {
    private NativeAdPreferences g;

    /* access modifiers changed from: protected */
    public void a(Ad ad) {
    }

    public a(Context context, Ad ad, AdPreferences adPreferences, AdEventListener adEventListener, NativeAdPreferences nativeAdPreferences) {
        super(context, ad, adPreferences, adEventListener, AdPreferences.Placement.INAPP_NATIVE);
        this.g = nativeAdPreferences;
    }

    /* access modifiers changed from: protected */
    public GetAdRequest a() {
        GetAdRequest a2 = super.a();
        if (a2 == null) {
            return null;
        }
        a2.setAdsNumber(this.g.getAdsNumber());
        if (this.g.getImageSize() != null) {
            a2.setWidth(this.g.getImageSize().getWidth());
            a2.setHeight(this.g.getImageSize().getHeight());
        } else {
            int primaryImageSize = this.g.getPrimaryImageSize();
            int i = 2;
            if (primaryImageSize == -1) {
                primaryImageSize = 2;
            }
            a2.setPrimaryImg(Integer.toString(primaryImageSize));
            int secondaryImageSize = this.g.getSecondaryImageSize();
            if (secondaryImageSize != -1) {
                i = secondaryImageSize;
            }
            a2.setMoreImg(Integer.toString(i));
        }
        if (this.g.isContentAd()) {
            a2.setContentAd(this.g.isContentAd());
        }
        return a2;
    }
}
