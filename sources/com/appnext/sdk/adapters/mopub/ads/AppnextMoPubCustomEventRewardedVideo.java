package com.appnext.sdk.adapters.mopub.ads;

import android.content.Context;
import com.appnext.ads.fullscreen.RewardedConfig;
import com.appnext.ads.fullscreen.RewardedServerSidePostback;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.appnext.core.Ad;
import com.appnext.core.Configuration;
import java.util.Map;

public class AppnextMoPubCustomEventRewardedVideo extends AppnextMoPubCustomEvent {

    private class CustomEventRewardedVideoAd extends RewardedVideo {
        protected static final String TID = "311";

        public String getTID() {
            return TID;
        }

        public CustomEventRewardedVideoAd(Context context, String str) {
            super(context.getApplicationContext(), str);
        }

        public CustomEventRewardedVideoAd(Context context, String str, RewardedConfig rewardedConfig) {
            super(context.getApplicationContext(), str, rewardedConfig);
        }
    }

    /* access modifiers changed from: protected */
    public Ad createAd(Context context, Map<String, Object> map, Map<String, String> map2) {
        Object obj;
        Object obj2;
        CustomEventRewardedVideoAd customEventRewardedVideoAd;
        CustomEventRewardedVideoAd customEventRewardedVideoAd2 = null;
        if (map != null) {
            try {
                obj2 = map.get("AppnextConfiguration");
                obj = map.get("AppnextRewardPostback");
            } catch (Throwable th) {
                new StringBuilder("AppnextMoPubCustomEventRewardedVideo createAd: ").append(th.getMessage());
            }
        } else {
            obj = null;
            obj2 = null;
        }
        String appnextPlacementIdExtraKey = Helper.getAppnextPlacementIdExtraKey(map2);
        if (hasAdConfigServerExtras(map2)) {
            if (obj2 == null) {
                obj2 = new RewardedConfig();
            }
            setConfigFromExtras((Configuration) obj2, map2);
        }
        if (obj2 == null || !(obj2 instanceof RewardedConfig)) {
            customEventRewardedVideoAd = new CustomEventRewardedVideoAd(context.getApplicationContext(), appnextPlacementIdExtraKey);
        } else {
            customEventRewardedVideoAd = new CustomEventRewardedVideoAd(context.getApplicationContext(), appnextPlacementIdExtraKey, (RewardedConfig) obj2);
        }
        customEventRewardedVideoAd2 = customEventRewardedVideoAd;
        if (obj != null && (obj instanceof RewardedServerSidePostback)) {
            RewardedServerSidePostback rewardedServerSidePostback = (RewardedServerSidePostback) obj;
            customEventRewardedVideoAd2.setRewardedServerSidePostback(rewardedServerSidePostback.getRewardsTransactionId(), rewardedServerSidePostback.getRewardsUserId(), rewardedServerSidePostback.getRewardsRewardTypeCurrency(), rewardedServerSidePostback.getRewardsAmountRewarded(), rewardedServerSidePostback.getRewardsCustomParameter());
        }
        return customEventRewardedVideoAd2;
    }

    /* access modifiers changed from: protected */
    public boolean hasAdConfigServerExtras(Map<String, String> map) {
        return map != null && (Helper.hasAdConfigServerExtras(map) || Helper.hasVideoConfigServerExtras(map));
    }

    /* access modifiers changed from: protected */
    public void setConfigFromExtras(Configuration configuration, Map<String, String> map) {
        if (configuration != null && (configuration instanceof RewardedConfig)) {
            RewardedConfig rewardedConfig = (RewardedConfig) configuration;
            Helper.setConfigFromExtras(rewardedConfig, map);
            Helper.setVideoConfigFromExtras(rewardedConfig, map);
        }
    }
}
