package com.appnext.sdk.adapters.mopub.ads;

import android.content.Context;
import com.appnext.ads.interstitial.Interstitial;
import com.appnext.ads.interstitial.InterstitialConfig;
import com.appnext.core.Ad;
import com.appnext.core.Configuration;
import java.util.Map;

public class AppnextMoPubCustomEventInterstitial extends AppnextMoPubCustomEvent {

    private class CustomEventInterstitialAd extends Interstitial {
        protected static final String TID = "311";

        public String getTID() {
            return TID;
        }

        public CustomEventInterstitialAd(Context context, String str) {
            super(context.getApplicationContext(), str);
        }

        public CustomEventInterstitialAd(Context context, String str, InterstitialConfig interstitialConfig) {
            super(context.getApplicationContext(), str, interstitialConfig);
        }
    }

    /* access modifiers changed from: protected */
    public Ad createAd(Context context, Map<String, Object> map, Map<String, String> map2) {
        Object obj;
        if (map != null) {
            try {
                obj = map.get("AppnextConfiguration");
            } catch (Throwable th) {
                new StringBuilder("AppnextMoPubCustomEventInterstitial createAd: ").append(th.getMessage());
                return null;
            }
        } else {
            obj = null;
        }
        String appnextPlacementIdExtraKey = Helper.getAppnextPlacementIdExtraKey(map2);
        if (hasAdConfigServerExtras(map2)) {
            if (obj == null) {
                obj = new InterstitialConfig();
            }
            setConfigFromExtras((Configuration) obj, map2);
        }
        if (obj == null || !(obj instanceof InterstitialConfig)) {
            return new CustomEventInterstitialAd(context.getApplicationContext(), appnextPlacementIdExtraKey);
        }
        return new CustomEventInterstitialAd(context.getApplicationContext(), appnextPlacementIdExtraKey, (InterstitialConfig) obj);
    }

    /* access modifiers changed from: protected */
    public boolean hasAdConfigServerExtras(Map<String, String> map) {
        return map != null && (Helper.hasAdConfigServerExtras(map) || map.containsKey("AppnextCreativeType") || map.containsKey("AppnextSkipText") || map.containsKey("AppnextAutoPlay"));
    }

    /* access modifiers changed from: protected */
    public void setConfigFromExtras(Configuration configuration, Map<String, String> map) {
        if (configuration != null && (configuration instanceof InterstitialConfig)) {
            InterstitialConfig interstitialConfig = (InterstitialConfig) configuration;
            Helper.setConfigFromExtras(interstitialConfig, map);
            if (map.containsKey("AppnextCreativeType")) {
                try {
                    interstitialConfig.setCreativeType(map.get("AppnextCreativeType"));
                } catch (Throwable th) {
                    new StringBuilder("setCreativeType: ").append(th.getMessage());
                }
            }
            if (map.containsKey("AppnextSkipText")) {
                try {
                    interstitialConfig.setSkipText(map.get("AppnextSkipText"));
                } catch (Throwable th2) {
                    new StringBuilder("setSkipText: ").append(th2.getMessage());
                }
            }
            if (map.containsKey("AppnextAutoPlay")) {
                try {
                    interstitialConfig.setAutoPlay(Boolean.parseBoolean(map.get("AppnextAutoPlay")));
                } catch (Throwable th3) {
                    new StringBuilder("setAutoPlay: ").append(th3.getMessage());
                }
            }
            if (map.containsKey("AppnextButtonColor")) {
                try {
                    interstitialConfig.setButtonColor(map.get("AppnextButtonColor"));
                } catch (Throwable th4) {
                    new StringBuilder("setButtonColor: ").append(th4.getMessage());
                }
            }
            if (map.containsKey("AppnextBackButtonCanClose")) {
                try {
                    interstitialConfig.setBackButtonCanClose(Boolean.parseBoolean(map.get("AppnextBackButtonCanClose")));
                } catch (Throwable th5) {
                    new StringBuilder("setBackButtonCanClose: ").append(th5.getMessage());
                }
            }
        }
    }
}
