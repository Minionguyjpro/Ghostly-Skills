package com.appnext.sdk.adapters.admob.ads;

import android.content.Context;
import android.os.Bundle;
import com.appnext.ads.fullscreen.FullScreenVideo;
import com.appnext.ads.fullscreen.FullscreenConfig;
import com.appnext.core.Ad;
import java.io.Serializable;

public class AppnextAdMobCustomEventFullScreenVideo extends AppnextAdMobCustomEvent {

    private class CustomEventFullScreenVideoAd extends FullScreenVideo {
        protected static final String TID = "321";

        public String getTID() {
            return TID;
        }

        public CustomEventFullScreenVideoAd(Context context, String str) {
            super(context, str);
        }

        public CustomEventFullScreenVideoAd(Context context, String str, FullscreenConfig fullscreenConfig) {
            super(context, str, fullscreenConfig);
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
                new StringBuilder("AppnextAdMobCustomEventFullScreenVideo createAd: ").append(th.getMessage());
                return null;
            }
        } else {
            serializable = null;
        }
        if (serializable == null || !(serializable instanceof FullscreenConfig)) {
            return new CustomEventFullScreenVideoAd(context, str);
        }
        return new CustomEventFullScreenVideoAd(context, str, (FullscreenConfig) serializable);
    }
}
