package com.appnext.sdk.adapters.admob.ads;

import android.content.Context;
import android.os.Bundle;
import com.appnext.ads.fullscreen.RewardedConfig;
import com.appnext.ads.fullscreen.RewardedServerSidePostback;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.appnext.core.Ad;
import java.io.Serializable;

public class AppnextAdMobCustomEventRewardedVideo extends AppnextAdMobCustomEvent {

    private class CustomEventRewardedVideoAd extends RewardedVideo {
        protected static final String TID = "321";

        public String getTID() {
            return TID;
        }

        public CustomEventRewardedVideoAd(Context context, String str) {
            super(context, str);
        }

        public CustomEventRewardedVideoAd(Context context, String str, RewardedConfig rewardedConfig) {
            super(context, str, rewardedConfig);
        }
    }

    /* access modifiers changed from: protected */
    public Ad createAd(Context context, String str, Bundle bundle) {
        Serializable serializable;
        Serializable serializable2;
        CustomEventRewardedVideoAd customEventRewardedVideoAd = null;
        if (bundle != null) {
            try {
                serializable2 = bundle.getSerializable("AppnextConfiguration");
                serializable = bundle.getSerializable("AppnextRewardPostback");
            } catch (Throwable th) {
                this.mListener.onAdFailedToLoad(0);
                new StringBuilder("AppnextAdMobCustomEventRewardedVideo createAd: ").append(th.getMessage());
            }
        } else {
            serializable = null;
            serializable2 = null;
        }
        if (serializable2 == null || !(serializable2 instanceof RewardedConfig)) {
            customEventRewardedVideoAd = new CustomEventRewardedVideoAd(context, str);
        } else {
            customEventRewardedVideoAd = new CustomEventRewardedVideoAd(context, str, (RewardedConfig) serializable2);
        }
        if (serializable != null && (serializable instanceof RewardedServerSidePostback)) {
            RewardedServerSidePostback rewardedServerSidePostback = (RewardedServerSidePostback) serializable;
            customEventRewardedVideoAd.setRewardedServerSidePostback(rewardedServerSidePostback.getRewardsTransactionId(), rewardedServerSidePostback.getRewardsUserId(), rewardedServerSidePostback.getRewardsRewardTypeCurrency(), rewardedServerSidePostback.getRewardsAmountRewarded(), rewardedServerSidePostback.getRewardsCustomParameter());
        }
        return customEventRewardedVideoAd;
    }
}
