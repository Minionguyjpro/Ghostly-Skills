package com.startapp.android.mediation.admob;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.VideoListener;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;

public class StartAppRewardedVideo implements MediationRewardedVideoAdAdapter {
    private static final String TAG = StartAppRewardedVideo.class.getSimpleName();
    /* access modifiers changed from: private */
    public static MediationRewardedVideoAdListener listener;
    private StartAppAd ad;
    private boolean mInitialized;

    public void onPause() {
    }

    public void onResume() {
    }

    private class StartAppReward implements RewardItem {
        private final int mAmount;
        private final String mType;

        StartAppReward(String str, int i) {
            this.mType = str;
            this.mAmount = i;
        }

        public int getAmount() {
            return this.mAmount;
        }

        public String getType() {
            return this.mType;
        }
    }

    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        listener = mediationRewardedVideoAdListener;
        if (!this.mInitialized) {
            StartAppAd startAppAd = new StartAppAd(context);
            this.ad = startAppAd;
            startAppAd.setVideoListener(new VideoListener() {
                public void onVideoCompleted() {
                    StartAppRewardedVideo.listener.onVideoCompleted(StartAppRewardedVideo.this);
                    MediationRewardedVideoAdListener access$000 = StartAppRewardedVideo.listener;
                    StartAppRewardedVideo startAppRewardedVideo = StartAppRewardedVideo.this;
                    access$000.onRewarded(startAppRewardedVideo, new StartAppReward("startapp", 1));
                }
            });
            this.mInitialized = true;
        }
        listener.onInitializationSucceeded(this);
    }

    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        this.ad.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, (AdEventListener) new AdEventListener() {
            public void onReceiveAd(Ad ad) {
                StartAppRewardedVideo.listener.onAdLoaded(StartAppRewardedVideo.this);
            }

            public void onFailedToReceiveAd(Ad ad) {
                StartAppRewardedVideo.listener.onAdFailedToLoad(StartAppRewardedVideo.this, 0);
            }
        });
    }

    public void showVideo() {
        this.ad.showAd((AdDisplayListener) new AdDisplayListener() {
            public void adNotDisplayed(Ad ad) {
            }

            public void adHidden(Ad ad) {
                StartAppRewardedVideo.listener.onAdClosed(StartAppRewardedVideo.this);
            }

            public void adDisplayed(Ad ad) {
                StartAppRewardedVideo.listener.onAdOpened(StartAppRewardedVideo.this);
                StartAppRewardedVideo.listener.onVideoStarted(StartAppRewardedVideo.this);
            }

            public void adClicked(Ad ad) {
                StartAppRewardedVideo.listener.onAdClicked(StartAppRewardedVideo.this);
            }
        });
    }

    public boolean isInitialized() {
        return this.mInitialized;
    }

    public void onDestroy() {
        this.mInitialized = false;
    }
}
