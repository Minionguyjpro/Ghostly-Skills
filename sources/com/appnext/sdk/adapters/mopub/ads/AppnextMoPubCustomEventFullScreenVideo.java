package com.appnext.sdk.adapters.mopub.ads;

import android.content.Context;
import com.appnext.ads.fullscreen.FullScreenVideo;
import com.appnext.ads.fullscreen.FullscreenConfig;
import com.appnext.core.Ad;
import com.appnext.core.Configuration;
import java.util.Map;

public class AppnextMoPubCustomEventFullScreenVideo extends AppnextMoPubCustomEvent {

    private class CustomEventFullScreenVideoAd extends FullScreenVideo {
        protected static final String TID = "311";

        public String getTID() {
            return TID;
        }

        public CustomEventFullScreenVideoAd(Context context, String str) {
            super(context.getApplicationContext(), str);
        }

        public CustomEventFullScreenVideoAd(Context context, String str, FullscreenConfig fullscreenConfig) {
            super(context.getApplicationContext(), str, fullscreenConfig);
        }
    }

    /* access modifiers changed from: protected */
    public Ad createAd(Context context, Map<String, Object> map, Map<String, String> map2) {
        Object obj;
        if (map != null) {
            try {
                obj = map.get("AppnextConfiguration");
            } catch (Throwable th) {
                new StringBuilder("AppnextMoPubCustomEventFullScreenVideo createAd: ").append(th.getMessage());
                return null;
            }
        } else {
            obj = null;
        }
        String appnextPlacementIdExtraKey = Helper.getAppnextPlacementIdExtraKey(map2);
        if (hasAdConfigServerExtras(map2)) {
            if (obj == null) {
                obj = new FullscreenConfig();
            }
            setConfigFromExtras((Configuration) obj, map2);
        }
        if (obj == null || !(obj instanceof FullscreenConfig)) {
            return new CustomEventFullScreenVideoAd(context.getApplicationContext(), appnextPlacementIdExtraKey);
        }
        return new CustomEventFullScreenVideoAd(context.getApplicationContext(), appnextPlacementIdExtraKey, (FullscreenConfig) obj);
    }

    /* access modifiers changed from: protected */
    public boolean hasAdConfigServerExtras(Map<String, String> map) {
        return map != null && (Helper.hasAdConfigServerExtras(map) || Helper.hasVideoConfigServerExtras(map));
    }

    /* access modifiers changed from: protected */
    public void setConfigFromExtras(Configuration configuration, Map<String, String> map) {
        if (configuration != null && (configuration instanceof FullscreenConfig)) {
            FullscreenConfig fullscreenConfig = (FullscreenConfig) configuration;
            Helper.setConfigFromExtras(fullscreenConfig, map);
            Helper.setVideoConfigFromExtras(fullscreenConfig, map);
            Helper.setFullscreenConfigFromExtras(fullscreenConfig, map);
        }
    }
}
