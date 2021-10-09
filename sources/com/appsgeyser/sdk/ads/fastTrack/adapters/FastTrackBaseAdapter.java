package com.appsgeyser.sdk.ads.fastTrack.adapters;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import com.appnext.base.b.d;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.ui.AppsgeyserProgressDialog;
import java.util.HashMap;

public abstract class FastTrackBaseAdapter {
    final HashMap<String, String> appDetails = new HashMap<>();
    final HashMap<String, String> bannerDetails = new HashMap<>();
    ViewGroup bannerViewContainer;
    Context context;
    FastTrackSdkModel fastTrackSdkModel;
    FullscreenListener fullscreenListener;
    Handler handler;
    final HashMap<String, String> interstitialDetails = new HashMap<>();
    boolean isInForeground = true;
    final HashMap<String, String> nativeAdsDetails = new HashMap<>();
    boolean pendingFullscreenRequest;
    PreferencesCoder preferencesCoder;
    AppsgeyserProgressDialog progressDialog;
    final HashMap<String, String> rewardedDetails = new HashMap<>();
    String rewardedVideoCurrentPlacement;
    RewardedVideoListener rewardedVideoListener;
    boolean videoDownloadError;
    boolean videoShowRequested;

    public interface FullscreenListener {
        void onClose();

        void onFailedToShow();

        void onRequest();

        void onShow();
    }

    public interface RewardedVideoListener {
        void onVideoClicked();

        void onVideoClosed();

        void onVideoDeactivated();

        void onVideoError(String str);

        void onVideoFinished();

        void onVideoOpened();
    }

    /* access modifiers changed from: protected */
    public abstract void init();

    public abstract void initBannerView(ViewGroup viewGroup, String str);

    public abstract void loadFullscreen();

    public abstract void loadNativeAds(int i);

    public abstract void loadRewardedVideo();

    public abstract void showFullscreen(String str, String str2, boolean z);

    public abstract void showRewardedVideo(RewardedVideoListener rewardedVideoListener2, String str);

    FastTrackBaseAdapter(FastTrackSdkModel fastTrackSdkModel2, Context context2) {
        this.fastTrackSdkModel = fastTrackSdkModel2;
        this.context = context2;
        Configuration instance = Configuration.getInstance(context2);
        this.appDetails.put("widget_id", instance.getApplicationId());
        this.appDetails.put("wdid", instance.getApplicationId());
        if (fastTrackSdkModel2.getAdditionalReportingParams() != null) {
            try {
                this.appDetails.putAll(fastTrackSdkModel2.getAdditionalReportingParams());
            } catch (NullPointerException unused) {
                Log.d("fastTrackTag", "NPE while adding reporting params");
            }
        }
        this.handler = new Handler(context2.getMainLooper());
        this.preferencesCoder = new PreferencesCoder(context2);
        init();
    }

    /* access modifiers changed from: package-private */
    public Integer getBannerViewRefreshRate(String str) {
        Integer num = (this.fastTrackSdkModel.getBannerPlacementsRefreshTimerMap() == null || str == null) ? null : this.fastTrackSdkModel.getBannerPlacementsRefreshTimerMap().get(str);
        if (num != null) {
            return num;
        }
        return Integer.valueOf(this.fastTrackSdkModel.getDefaultBannerRefreshTimer() != null ? this.fastTrackSdkModel.getDefaultBannerRefreshTimer().intValue() : d.fd);
    }

    public void onResume(Context context2) {
        Context context3 = this.context;
        if (context3 != null && !context3.equals(context2)) {
            this.context = context2;
        }
        this.isInForeground = true;
    }

    public void onPause() {
        this.isInForeground = false;
    }

    /* access modifiers changed from: package-private */
    public Integer getFullscreenIntensityPoints(String str) {
        Integer num = (this.fastTrackSdkModel.getFullscreenPlacementsIntensityMap() == null || str == null) ? null : this.fastTrackSdkModel.getFullscreenPlacementsIntensityMap().get(str);
        if (num != null) {
            return num;
        }
        return Integer.valueOf(this.fastTrackSdkModel.getDefaultFullscreenIntensity() != null ? this.fastTrackSdkModel.getDefaultFullscreenIntensity().intValue() : 100);
    }

    /* access modifiers changed from: package-private */
    public Integer getFullscreenFrequencyTimerValue() {
        Integer fullscreenFrequencyTimer = this.fastTrackSdkModel.getFullscreenFrequencyTimer();
        return Integer.valueOf((fullscreenFrequencyTimer == null || fullscreenFrequencyTimer.intValue() < 15000) ? 120000 : fullscreenFrequencyTimer.intValue());
    }

    /* access modifiers changed from: package-private */
    public Integer getFullscreenPendingDelayTimerValue() {
        Integer fullscreenPendingDelayTimer = this.fastTrackSdkModel.getFullscreenPendingDelayTimer();
        return Integer.valueOf((fullscreenPendingDelayTimer == null || fullscreenPendingDelayTimer.intValue() < 10000) ? d.fd : fullscreenPendingDelayTimer.intValue());
    }

    public void setFullscreenListener(FullscreenListener fullscreenListener2) {
        this.fullscreenListener = fullscreenListener2;
    }

    public Boolean getRewardedVideoActivationStatus(String str) {
        boolean z = true;
        Boolean bool = true;
        if (!(this.fastTrackSdkModel.getRewardedPlacementsActivationMap() == null || str == null)) {
            bool = this.fastTrackSdkModel.getRewardedPlacementsActivationMap().get(str);
        }
        if (bool != null) {
            z = bool.booleanValue();
        }
        return Boolean.valueOf(z);
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    public Context getContext() {
        return this.context;
    }

    public void setProgressDialog(AppsgeyserProgressDialog appsgeyserProgressDialog) {
        this.progressDialog = appsgeyserProgressDialog;
    }
}
