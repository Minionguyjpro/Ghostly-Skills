package com.mopub.mobileads;

import android.app.Activity;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MoPubReward;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.VideoListener;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import java.util.Map;

public class StartAppRewardedVideo extends CustomEventRewardedVideo {
    private static final String thirdPartyId = "StartApp";
    private StartAppAd ad;
    private String adTag;
    private boolean isInitialized;
    /* access modifiers changed from: private */
    public boolean isLoaded;

    /* access modifiers changed from: protected */
    public String getAdNetworkId() {
        return thirdPartyId;
    }

    /* access modifiers changed from: protected */
    public LifecycleListener getLifecycleListener() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
    }

    /* access modifiers changed from: protected */
    public boolean hasVideoAvailable() {
        return this.isLoaded;
    }

    /* access modifiers changed from: protected */
    public void showVideo() {
        this.ad.showAd(this.adTag, new AdDisplayListener() {
            public void adHidden(Ad ad) {
                MoPubRewardedVideoManager.onRewardedVideoClosed(StartAppRewardedVideo.class, StartAppRewardedVideo.thirdPartyId);
            }

            public void adDisplayed(Ad ad) {
                MoPubRewardedVideoManager.onRewardedVideoStarted(StartAppRewardedVideo.class, StartAppRewardedVideo.thirdPartyId);
            }

            public void adClicked(Ad ad) {
                MoPubRewardedVideoManager.onRewardedVideoClicked(StartAppRewardedVideo.class, StartAppRewardedVideo.thirdPartyId);
            }

            public void adNotDisplayed(Ad ad) {
                MoPubRewardedVideoManager.onRewardedVideoPlaybackError(StartAppRewardedVideo.class, StartAppRewardedVideo.thirdPartyId, MoPubErrorCode.VIDEO_PLAYBACK_ERROR);
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean checkAndInitializeSdk(Activity activity, Map<String, Object> map, Map<String, String> map2) throws Exception {
        if (!this.isInitialized) {
            StartAppCustomEventUtils.checkInit(activity, map2);
            this.adTag = StartAppCustomEventUtils.getStringFromExtras("adTag", map2);
            StartAppAd startAppAd = new StartAppAd(activity);
            this.ad = startAppAd;
            startAppAd.setVideoListener(new VideoListener() {
                public void onVideoCompleted() {
                    MoPubRewardedVideoManager.onRewardedVideoCompleted(StartAppRewardedVideo.class, StartAppRewardedVideo.thirdPartyId, MoPubReward.success(StartAppRewardedVideo.thirdPartyId, 1));
                }
            });
            this.isInitialized = true;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void loadWithSdkInitialized(Activity activity, Map<String, Object> map, Map<String, String> map2) throws Exception {
        this.ad.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, (AdEventListener) new AdEventListener() {
            public void onReceiveAd(Ad ad) {
                boolean unused = StartAppRewardedVideo.this.isLoaded = true;
                MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(StartAppRewardedVideo.class, StartAppRewardedVideo.thirdPartyId);
            }

            public void onFailedToReceiveAd(Ad ad) {
                boolean unused = StartAppRewardedVideo.this.isLoaded = false;
                MoPubRewardedVideoManager.onRewardedVideoLoadFailure(StartAppRewardedVideo.class, StartAppRewardedVideo.thirdPartyId, MoPubErrorCode.NO_FILL);
            }
        });
    }
}
