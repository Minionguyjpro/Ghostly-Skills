package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.AdFormat;
import com.mopub.common.Preconditions;
import com.mopub.network.AdLoader;
import com.mopub.network.AdResponse;
import com.mopub.volley.Request;
import com.mopub.volley.VolleyError;
import java.util.HashMap;
import java.util.Map;

class RewardedAdsLoaders {
    private final HashMap<String, AdLoaderRewardedVideo> mAdUnitToAdLoader = new HashMap<>();
    /* access modifiers changed from: private */
    public final MoPubRewardedVideoManager moPubRewardedVideoManager;

    public class RewardedVideoRequestListener implements AdLoader.Listener {
        public final String adUnitId;

        RewardedVideoRequestListener(String str) {
            this.adUnitId = str;
        }

        public void onSuccess(AdResponse adResponse) {
            RewardedAdsLoaders.this.moPubRewardedVideoManager.onAdSuccess(adResponse);
        }

        public void onErrorResponse(VolleyError volleyError) {
            RewardedAdsLoaders.this.moPubRewardedVideoManager.onAdError(volleyError, this.adUnitId);
        }
    }

    RewardedAdsLoaders(MoPubRewardedVideoManager moPubRewardedVideoManager2) {
        this.moPubRewardedVideoManager = moPubRewardedVideoManager2;
    }

    /* access modifiers changed from: package-private */
    public Request<?> loadNextAd(Context context, String str, String str2, MoPubErrorCode moPubErrorCode) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(context);
        AdLoaderRewardedVideo adLoaderRewardedVideo = this.mAdUnitToAdLoader.get(str);
        if (adLoaderRewardedVideo == null || !adLoaderRewardedVideo.hasMoreAds()) {
            adLoaderRewardedVideo = new AdLoaderRewardedVideo(str2, AdFormat.REWARDED_VIDEO, str, context, new RewardedVideoRequestListener(str));
            this.mAdUnitToAdLoader.put(str, adLoaderRewardedVideo);
        }
        return adLoaderRewardedVideo.loadNextAd(moPubErrorCode);
    }

    /* access modifiers changed from: package-private */
    public boolean isLoading(String str) {
        return this.mAdUnitToAdLoader.containsKey(str) && this.mAdUnitToAdLoader.get(str).isRunning();
    }

    /* access modifiers changed from: package-private */
    public void markFail(String str) {
        Preconditions.checkNotNull(str);
        if (this.mAdUnitToAdLoader.containsKey(str)) {
            this.mAdUnitToAdLoader.remove(str);
        }
    }

    /* access modifiers changed from: package-private */
    public void markPlayed(String str) {
        Preconditions.checkNotNull(str);
        if (this.mAdUnitToAdLoader.containsKey(str)) {
            this.mAdUnitToAdLoader.remove(str);
        }
    }

    /* access modifiers changed from: package-private */
    public void onRewardedVideoStarted(String str, Context context) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(context);
        AdLoaderRewardedVideo adLoaderRewardedVideo = this.mAdUnitToAdLoader.get(str);
        if (adLoaderRewardedVideo != null) {
            adLoaderRewardedVideo.trackImpression(context);
        }
    }

    /* access modifiers changed from: package-private */
    public void onRewardedVideoClicked(String str, Context context) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(context);
        AdLoaderRewardedVideo adLoaderRewardedVideo = this.mAdUnitToAdLoader.get(str);
        if (adLoaderRewardedVideo != null) {
            adLoaderRewardedVideo.trackClick(context);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean canPlay(String str) {
        AdLoaderRewardedVideo adLoaderRewardedVideo = this.mAdUnitToAdLoader.get(str);
        if (adLoaderRewardedVideo == null || adLoaderRewardedVideo.getLastDeliveredResponse() == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean hasMoreAds(String str) {
        AdLoaderRewardedVideo adLoaderRewardedVideo = this.mAdUnitToAdLoader.get(str);
        return adLoaderRewardedVideo != null && adLoaderRewardedVideo.hasMoreAds();
    }

    /* access modifiers changed from: package-private */
    public void creativeDownloadSuccess(String str) {
        AdLoaderRewardedVideo adLoaderRewardedVideo = this.mAdUnitToAdLoader.get(str);
        if (adLoaderRewardedVideo != null) {
            adLoaderRewardedVideo.creativeDownloadSuccess();
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void clearMapping() {
        this.mAdUnitToAdLoader.clear();
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public Map<String, AdLoaderRewardedVideo> getLoadersMap() {
        return this.mAdUnitToAdLoader;
    }
}
