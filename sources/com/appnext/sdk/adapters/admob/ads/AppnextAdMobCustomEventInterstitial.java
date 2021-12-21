package com.appnext.sdk.adapters.admob.ads;

import android.content.Context;
import android.os.Bundle;
import com.appnext.ads.interstitial.Interstitial;
import com.appnext.ads.interstitial.InterstitialConfig;
import com.appnext.core.Ad;
import java.io.Serializable;

public class AppnextAdMobCustomEventInterstitial extends AppnextAdMobCustomEvent {

    private class CustomEventInterstitialAd extends Interstitial {
        protected static final String TID = "321";

        public String getTID() {
            return TID;
        }

        public CustomEventInterstitialAd(Context context, String str) {
            super(context, str);
        }

        public CustomEventInterstitialAd(Context context, String str, InterstitialConfig interstitialConfig) {
            super(context, str, interstitialConfig);
        }
    }

    /* access modifiers changed from: protected */
    public Ad createAd(Context context, String str, Bundle bundle) {
        Serializable serializable;
        if (bundle != null) {
            try {
                serializable = bundle.getSerializable("AppnextConfiguration");
            } catch (Throwable th) {
                this.mListener.onAdFailedToLoad(0);
                new StringBuilder("AppnextAdMobCustomEventInterstitial createAd: ").append(th.getMessage());
                return null;
            }
        } else {
            serializable = null;
        }
        if (serializable == null || !(serializable instanceof InterstitialConfig)) {
            return new CustomEventInterstitialAd(context, str);
        }
        return new CustomEventInterstitialAd(context, str, (InterstitialConfig) serializable);
    }
}
