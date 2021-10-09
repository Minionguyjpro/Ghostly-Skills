package com.appnext.sdk.adapters.mopub.ads;

import android.app.Activity;
import android.content.Context;
import com.appnext.ads.fullscreen.RewardedConfig;
import com.appnext.ads.fullscreen.RewardedServerSidePostback;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.appnext.core.AppnextAdCreativeType;
import com.appnext.core.Configuration;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.appnext.core.callbacks.OnVideoEnded;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.CustomEventRewardedVideo;
import com.mopub.mobileads.MoPubRewardedVideoManager;
import java.util.Map;

public class AppnextMoPubRewardedVideo extends CustomEventRewardedVideo {
    RewardedVideo ad;
    String adUnitId = "";

    /* access modifiers changed from: protected */
    public String getAdNetworkId() {
        return "appnext_id";
    }

    /* access modifiers changed from: protected */
    public LifecycleListener getLifecycleListener() {
        return null;
    }

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
    public boolean hasVideoAvailable() {
        RewardedVideo rewardedVideo = this.ad;
        return rewardedVideo != null && rewardedVideo.isAdLoaded();
    }

    /* access modifiers changed from: protected */
    public void showVideo() {
        AppnextMediationSettings appnextMediationSettings = !this.adUnitId.equals("") ? (AppnextMediationSettings) MoPubRewardedVideoManager.getInstanceMediationSettings(AppnextMediationSettings.class, this.adUnitId) : null;
        if (appnextMediationSettings == null) {
            appnextMediationSettings = (AppnextMediationSettings) MoPubRewardedVideoManager.getGlobalMediationSettings(AppnextMediationSettings.class);
        }
        if (appnextMediationSettings != null && (!appnextMediationSettings._rewardsAmountRewarded.equals("") || !appnextMediationSettings._rewardsCustomParameter.equals("") || !appnextMediationSettings._rewardsRewardTypeCurrency.equals("") || !appnextMediationSettings._rewardsTransactionId.equals("") || !appnextMediationSettings._rewardsUserId.equals(""))) {
            this.ad.setRewardedServerSidePostback(appnextMediationSettings._rewardsTransactionId, appnextMediationSettings._rewardsUserId, appnextMediationSettings._rewardsRewardTypeCurrency, appnextMediationSettings._rewardsAmountRewarded, appnextMediationSettings._rewardsCustomParameter);
        }
        RewardedVideo rewardedVideo = this.ad;
        if (rewardedVideo != null) {
            rewardedVideo.showAd();
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkAndInitializeSdk(Activity activity, Map<String, Object> map, Map<String, String> map2) throws Exception {
        if (this.ad != null) {
            return false;
        }
        try {
            init(activity, map, map2);
            return true;
        } catch (Throwable th) {
            new StringBuilder("AppnextMoPubRewardedVideo createAd: ").append(th.getMessage());
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void loadWithSdkInitialized(Activity activity, Map<String, Object> map, Map<String, String> map2) throws Exception {
        checkAndInitializeSdk(activity, map, map2);
        RewardedVideo rewardedVideo = this.ad;
        if (rewardedVideo != null) {
            rewardedVideo.loadAd();
        }
    }

    private void init(Activity activity, Map<String, Object> map, Map<String, String> map2) {
        Object obj;
        Object obj2 = null;
        if (map != null) {
            obj2 = map.get("AppnextConfiguration");
            obj = map.get("AppnextRewardPostback");
            Object obj3 = map.get(DataKeys.AD_UNIT_ID_KEY);
            if (obj3 instanceof String) {
                this.adUnitId = (String) obj3;
            }
        } else {
            obj = null;
        }
        String appnextPlacementIdExtraKey = Helper.getAppnextPlacementIdExtraKey(map2);
        if (hasAdConfigServerExtras(map2)) {
            if (obj2 == null) {
                obj2 = new RewardedConfig();
            }
            setConfigFromExtras((Configuration) obj2, map2);
        }
        if (obj2 == null || !(obj2 instanceof RewardedConfig)) {
            this.ad = new CustomEventRewardedVideoAd(activity, appnextPlacementIdExtraKey);
        } else {
            this.ad = new CustomEventRewardedVideoAd(activity, appnextPlacementIdExtraKey, (RewardedConfig) obj2);
        }
        if (obj != null && (obj instanceof RewardedServerSidePostback)) {
            RewardedServerSidePostback rewardedServerSidePostback = (RewardedServerSidePostback) obj;
            this.ad.setRewardedServerSidePostback(rewardedServerSidePostback.getRewardsTransactionId(), rewardedServerSidePostback.getRewardsUserId(), rewardedServerSidePostback.getRewardsRewardTypeCurrency(), rewardedServerSidePostback.getRewardsAmountRewarded(), rewardedServerSidePostback.getRewardsCustomParameter());
        }
        this.ad.setOnVideoEndedCallback(new OnVideoEnded() {
            public void videoEnded() {
                MoPubRewardedVideoManager.onRewardedVideoCompleted(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId(), MoPubReward.success("", MoPubReward.NO_REWARD_AMOUNT));
            }
        });
        this.ad.setOnAdLoadedCallback(new OnAdLoaded() {
            public void adLoaded(String str, AppnextAdCreativeType appnextAdCreativeType) {
                MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId());
            }
        });
        this.ad.setOnAdOpenedCallback(new OnAdOpened() {
            public void adOpened() {
                MoPubRewardedVideoManager.onRewardedVideoStarted(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId());
            }
        });
        this.ad.setOnAdClickedCallback(new OnAdClicked() {
            public void adClicked() {
                MoPubRewardedVideoManager.onRewardedVideoClicked(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId());
            }
        });
        this.ad.setOnAdClosedCallback(new OnAdClosed() {
            public void onAdClosed() {
                MoPubRewardedVideoManager.onRewardedVideoClosed(AppnextMoPubRewardedVideo.this.getClass(), AppnextMoPubRewardedVideo.this.getAdNetworkId());
            }
        });
        this.ad.setOnAdErrorCallback(new OnAdError() {
            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void adError(java.lang.String r6) {
                /*
                    r5 = this;
                    int r0 = r6.hashCode()
                    r1 = 4
                    r2 = 3
                    r3 = 2
                    r4 = 1
                    switch(r0) {
                        case -1958363695: goto L_0x0034;
                        case -1477010874: goto L_0x002a;
                        case 297538105: goto L_0x0020;
                        case 350741825: goto L_0x0016;
                        case 844170097: goto L_0x000c;
                        default: goto L_0x000b;
                    }
                L_0x000b:
                    goto L_0x003e
                L_0x000c:
                    java.lang.String r0 = "Too Slow Connection"
                    boolean r6 = r6.equals(r0)
                    if (r6 == 0) goto L_0x003e
                    r6 = 1
                    goto L_0x003f
                L_0x0016:
                    java.lang.String r0 = "Timeout"
                    boolean r6 = r6.equals(r0)
                    if (r6 == 0) goto L_0x003e
                    r6 = 2
                    goto L_0x003f
                L_0x0020:
                    java.lang.String r0 = "Ad Not Ready"
                    boolean r6 = r6.equals(r0)
                    if (r6 == 0) goto L_0x003e
                    r6 = 0
                    goto L_0x003f
                L_0x002a:
                    java.lang.String r0 = "Connection Error"
                    boolean r6 = r6.equals(r0)
                    if (r6 == 0) goto L_0x003e
                    r6 = 3
                    goto L_0x003f
                L_0x0034:
                    java.lang.String r0 = "No Ads"
                    boolean r6 = r6.equals(r0)
                    if (r6 == 0) goto L_0x003e
                    r6 = 4
                    goto L_0x003f
                L_0x003e:
                    r6 = -1
                L_0x003f:
                    if (r6 == 0) goto L_0x0091
                    if (r6 == r4) goto L_0x007f
                    if (r6 == r3) goto L_0x007f
                    if (r6 == r2) goto L_0x006d
                    if (r6 == r1) goto L_0x005b
                    com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo r6 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo.this
                    java.lang.Class r6 = r6.getClass()
                    com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo r0 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo.this
                    java.lang.String r0 = r0.getAdNetworkId()
                    com.mopub.mobileads.MoPubErrorCode r1 = com.mopub.mobileads.MoPubErrorCode.INTERNAL_ERROR
                    com.mopub.mobileads.MoPubRewardedVideoManager.onRewardedVideoLoadFailure(r6, r0, r1)
                    return
                L_0x005b:
                    com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo r6 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo.this
                    java.lang.Class r6 = r6.getClass()
                    com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo r0 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo.this
                    java.lang.String r0 = r0.getAdNetworkId()
                    com.mopub.mobileads.MoPubErrorCode r1 = com.mopub.mobileads.MoPubErrorCode.NO_FILL
                    com.mopub.mobileads.MoPubRewardedVideoManager.onRewardedVideoLoadFailure(r6, r0, r1)
                    return
                L_0x006d:
                    com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo r6 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo.this
                    java.lang.Class r6 = r6.getClass()
                    com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo r0 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo.this
                    java.lang.String r0 = r0.getAdNetworkId()
                    com.mopub.mobileads.MoPubErrorCode r1 = com.mopub.mobileads.MoPubErrorCode.NO_CONNECTION
                    com.mopub.mobileads.MoPubRewardedVideoManager.onRewardedVideoLoadFailure(r6, r0, r1)
                    return
                L_0x007f:
                    com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo r6 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo.this
                    java.lang.Class r6 = r6.getClass()
                    com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo r0 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo.this
                    java.lang.String r0 = r0.getAdNetworkId()
                    com.mopub.mobileads.MoPubErrorCode r1 = com.mopub.mobileads.MoPubErrorCode.NETWORK_TIMEOUT
                    com.mopub.mobileads.MoPubRewardedVideoManager.onRewardedVideoLoadFailure(r6, r0, r1)
                    return
                L_0x0091:
                    com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo r6 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo.this
                    java.lang.Class r6 = r6.getClass()
                    com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo r0 = com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo.this
                    java.lang.String r0 = r0.getAdNetworkId()
                    com.mopub.mobileads.MoPubErrorCode r1 = com.mopub.mobileads.MoPubErrorCode.WARMUP
                    com.mopub.mobileads.MoPubRewardedVideoManager.onRewardedVideoLoadFailure(r6, r0, r1)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appnext.sdk.adapters.mopub.ads.AppnextMoPubRewardedVideo.AnonymousClass6.adError(java.lang.String):void");
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        RewardedVideo rewardedVideo = this.ad;
        if (rewardedVideo != null) {
            rewardedVideo.destroy();
        }
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
            Helper.setRewardedVideoConfigFromExtras(rewardedConfig, map);
        }
    }

    public static class AppnextMediationSettings implements MediationSettings {
        /* access modifiers changed from: private */
        public String _rewardsAmountRewarded;
        /* access modifiers changed from: private */
        public String _rewardsCustomParameter;
        /* access modifiers changed from: private */
        public String _rewardsRewardTypeCurrency;
        /* access modifiers changed from: private */
        public String _rewardsTransactionId;
        /* access modifiers changed from: private */
        public String _rewardsUserId;

        public static class Builder {
            /* access modifiers changed from: private */
            public String _rewardsAmountRewarded = "";
            /* access modifiers changed from: private */
            public String _rewardsCustomParameter = "";
            /* access modifiers changed from: private */
            public String _rewardsRewardTypeCurrency = "";
            /* access modifiers changed from: private */
            public String _rewardsTransactionId = "";
            /* access modifiers changed from: private */
            public String _rewardsUserId = "";

            public Builder withRewardsTransactionId(String str) {
                this._rewardsTransactionId = str;
                return this;
            }

            public Builder withRewardsUserId(String str) {
                this._rewardsUserId = str;
                return this;
            }

            public Builder withRewardsRewardTypeCurrency(String str) {
                this._rewardsRewardTypeCurrency = str;
                return this;
            }

            public Builder withRewardsAmountRewarded(String str) {
                this._rewardsAmountRewarded = str;
                return this;
            }

            public Builder withRewardsCustomParameter(String str) {
                this._rewardsCustomParameter = str;
                return this;
            }

            public AppnextMediationSettings build() {
                return new AppnextMediationSettings(this);
            }
        }

        private AppnextMediationSettings(Builder builder) {
            this._rewardsTransactionId = "";
            this._rewardsUserId = "";
            this._rewardsRewardTypeCurrency = "";
            this._rewardsAmountRewarded = "";
            this._rewardsCustomParameter = "";
            this._rewardsTransactionId = builder._rewardsTransactionId;
            this._rewardsUserId = builder._rewardsUserId;
            this._rewardsRewardTypeCurrency = builder._rewardsRewardTypeCurrency;
            this._rewardsAmountRewarded = builder._rewardsAmountRewarded;
            this._rewardsCustomParameter = builder._rewardsCustomParameter;
        }
    }
}
