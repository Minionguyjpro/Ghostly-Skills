package com.mopub.mobileads;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.mopub.common.BaseLifecycleListener;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MoPubReward;
import com.mopub.common.logging.MoPubLog;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class GooglePlayServicesRewardedVideo extends CustomEventRewardedVideo implements RewardedVideoAdListener {
    private static final String ADAPTER_VERSION = "0.1.0";
    private static final String KEY_EXTRA_AD_UNIT_ID = "adunit";
    private static final String KEY_EXTRA_APPLICATION_ID = "appid";
    private static final String TAG = "MoPubToAdMobRewarded";
    private static AtomicBoolean sIsInitialized;
    private String mAdUnitId;
    private LifecycleListener mLifecycleListener = new BaseLifecycleListener() {
        public void onPause(Activity activity) {
            super.onPause(activity);
            if (GooglePlayServicesRewardedVideo.this.mRewardedVideoAd != null) {
                GooglePlayServicesRewardedVideo.this.mRewardedVideoAd.pause(activity);
            }
        }

        public void onResume(Activity activity) {
            super.onResume(activity);
            if (GooglePlayServicesRewardedVideo.this.mRewardedVideoAd != null) {
                GooglePlayServicesRewardedVideo.this.mRewardedVideoAd.resume(activity);
            }
        }
    };
    /* access modifiers changed from: private */
    public RewardedVideoAd mRewardedVideoAd;

    public void onRewardedVideoAdOpened() {
    }

    public void onRewardedVideoCompleted() {
    }

    public GooglePlayServicesRewardedVideo() {
        sIsInitialized = new AtomicBoolean(false);
    }

    /* access modifiers changed from: protected */
    public LifecycleListener getLifecycleListener() {
        return this.mLifecycleListener;
    }

    /* access modifiers changed from: protected */
    public String getAdNetworkId() {
        return this.mAdUnitId;
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        RewardedVideoAd rewardedVideoAd = this.mRewardedVideoAd;
        if (rewardedVideoAd != null) {
            rewardedVideoAd.setRewardedVideoAdListener((RewardedVideoAdListener) null);
            this.mRewardedVideoAd = null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkAndInitializeSdk(Activity activity, Map<String, Object> map, Map<String, String> map2) throws Exception {
        Class<GooglePlayServicesRewardedVideo> cls = GooglePlayServicesRewardedVideo.class;
        if (sIsInitialized.getAndSet(true)) {
            return false;
        }
        Log.i(TAG, "Adapter version - 0.1.0");
        if (TextUtils.isEmpty(map2.get(KEY_EXTRA_APPLICATION_ID))) {
            MobileAds.initialize(activity);
        } else {
            MobileAds.initialize(activity, map2.get(KEY_EXTRA_APPLICATION_ID));
        }
        if (TextUtils.isEmpty(map2.get(KEY_EXTRA_AD_UNIT_ID))) {
            MoPubRewardedVideoManager.onRewardedVideoLoadFailure(cls, cls.getSimpleName(), MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return false;
        }
        this.mAdUnitId = map2.get(KEY_EXTRA_AD_UNIT_ID);
        RewardedVideoAd rewardedVideoAdInstance = MobileAds.getRewardedVideoAdInstance(activity);
        this.mRewardedVideoAd = rewardedVideoAdInstance;
        rewardedVideoAdInstance.setRewardedVideoAdListener(this);
        return true;
    }

    /* access modifiers changed from: protected */
    public void loadWithSdkInitialized(Activity activity, Map<String, Object> map, Map<String, String> map2) throws Exception {
        Class<GooglePlayServicesRewardedVideo> cls = GooglePlayServicesRewardedVideo.class;
        if (TextUtils.isEmpty(map2.get(KEY_EXTRA_AD_UNIT_ID))) {
            MoPubRewardedVideoManager.onRewardedVideoLoadFailure(cls, cls.getSimpleName(), MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return;
        }
        this.mAdUnitId = map2.get(KEY_EXTRA_AD_UNIT_ID);
        if (this.mRewardedVideoAd == null) {
            RewardedVideoAd rewardedVideoAdInstance = MobileAds.getRewardedVideoAdInstance(activity);
            this.mRewardedVideoAd = rewardedVideoAdInstance;
            rewardedVideoAdInstance.setRewardedVideoAdListener(this);
        }
        if (this.mRewardedVideoAd.isLoaded()) {
            MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(cls, this.mAdUnitId);
        } else {
            this.mRewardedVideoAd.loadAd(this.mAdUnitId, new AdRequest.Builder().setRequestAgent(MoPubLog.LOGTAG).build());
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasVideoAvailable() {
        RewardedVideoAd rewardedVideoAd = this.mRewardedVideoAd;
        return rewardedVideoAd != null && rewardedVideoAd.isLoaded();
    }

    /* access modifiers changed from: protected */
    public void showVideo() {
        if (hasVideoAvailable()) {
            this.mRewardedVideoAd.show();
        } else {
            MoPubRewardedVideoManager.onRewardedVideoPlaybackError(GooglePlayServicesRewardedVideo.class, this.mAdUnitId, getMoPubErrorCode(0));
        }
    }

    public void onRewardedVideoAdLoaded() {
        MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(GooglePlayServicesRewardedVideo.class, this.mAdUnitId);
    }

    public void onRewardedVideoStarted() {
        MoPubRewardedVideoManager.onRewardedVideoStarted(GooglePlayServicesRewardedVideo.class, this.mAdUnitId);
    }

    public void onRewardedVideoAdClosed() {
        MoPubRewardedVideoManager.onRewardedVideoClosed(GooglePlayServicesRewardedVideo.class, this.mAdUnitId);
    }

    public void onRewarded(RewardItem rewardItem) {
        MoPubRewardedVideoManager.onRewardedVideoCompleted(GooglePlayServicesRewardedVideo.class, this.mAdUnitId, MoPubReward.success(rewardItem.getType(), rewardItem.getAmount()));
    }

    public void onRewardedVideoAdLeftApplication() {
        MoPubRewardedVideoManager.onRewardedVideoClicked(GooglePlayServicesRewardedVideo.class, this.mAdUnitId);
    }

    public void onRewardedVideoAdFailedToLoad(int i) {
        MoPubRewardedVideoManager.onRewardedVideoLoadFailure(GooglePlayServicesRewardedVideo.class, this.mAdUnitId, getMoPubErrorCode(i));
    }

    private MoPubErrorCode getMoPubErrorCode(int i) {
        if (i == 0) {
            return MoPubErrorCode.INTERNAL_ERROR;
        }
        if (i == 1) {
            return MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR;
        }
        if (i == 2) {
            return MoPubErrorCode.NO_CONNECTION;
        }
        if (i != 3) {
            return MoPubErrorCode.UNSPECIFIED;
        }
        return MoPubErrorCode.NO_FILL;
    }
}
