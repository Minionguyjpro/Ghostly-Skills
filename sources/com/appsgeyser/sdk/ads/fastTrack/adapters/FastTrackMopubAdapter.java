package com.appsgeyser.sdk.ads.fastTrack.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.appsgeyser.sdk.GuidGenerator;
import com.appsgeyser.sdk.R;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackMopubAdapter;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.ui.AppsgeyserProgressDialog;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubConversionTracker;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideos;
import com.mopub.mobileads.MoPubView;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class FastTrackMopubAdapter extends FastTrackBaseAdapter {
    /* access modifiers changed from: private */
    public boolean bannerClickReportedRecently;
    /* access modifiers changed from: private */
    public String bannerPlacementId;
    /* access modifiers changed from: private */
    public boolean bannerRequestFailReported;
    /* access modifiers changed from: private */
    public MoPubView bannerView;
    /* access modifiers changed from: private */
    public Runnable bannerViewRefreshRunnable = new Runnable() {
        public void run() {
            if (FastTrackMopubAdapter.this.bannerView != null) {
                Log.d("fastTrackTag", "mopub banner attempt to load");
                HashMap hashMap = FastTrackMopubAdapter.this.bannerDetails;
                hashMap.put("details", "banner id: " + FastTrackMopubAdapter.this.bannerPlacementId);
                FastTrackMopubAdapter.this.bannerDetails.put("uniqid", GuidGenerator.generateNewGuid());
                FastTrackMopubAdapter.this.bannerView.loadAd();
                FastTrackMopubAdapter.this.bannerView.setVisibility(8);
                FastTrackMopubAdapter.this.noFillPlaceholder.setVisibility(8);
                FastTrackMopubAdapter.this.progressBar.setVisibility(0);
                boolean unused = FastTrackMopubAdapter.this.bannerRequestFailReported = false;
                StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_request", FastTrackMopubAdapter.this.bannerDetails, FastTrackMopubAdapter.this.context, true);
                FastTrackMopubAdapter.this.handler.postDelayed(FastTrackMopubAdapter.this.bannerViewRepeatRequestRunnable, 15000);
                return;
            }
            Log.d("fastTrackTag", "mopub banner attempt to load failed: bannerView null");
        }
    };
    /* access modifiers changed from: private */
    public Runnable bannerViewRepeatRequestRunnable = new Runnable() {
        public void run() {
            if (FastTrackMopubAdapter.this.bannerView != null) {
                Log.d("fastTrackTag", "mopub banner repeat attempt to load");
                HashMap hashMap = FastTrackMopubAdapter.this.bannerDetails;
                hashMap.put("details", "banner id: " + FastTrackMopubAdapter.this.bannerPlacementId);
                if (!FastTrackMopubAdapter.this.bannerRequestFailReported) {
                    StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_nofill", FastTrackMopubAdapter.this.bannerDetails, FastTrackMopubAdapter.this.context, true);
                }
                FastTrackMopubAdapter.this.bannerDetails.put("uniqid", GuidGenerator.generateNewGuid());
                FastTrackMopubAdapter.this.bannerView.loadAd();
                FastTrackMopubAdapter.this.noFillPlaceholder.setVisibility(8);
                FastTrackMopubAdapter.this.progressBar.setVisibility(0);
                boolean unused = FastTrackMopubAdapter.this.bannerRequestFailReported = false;
                StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_request", FastTrackMopubAdapter.this.bannerDetails, FastTrackMopubAdapter.this.context, true);
                FastTrackMopubAdapter.this.handler.postDelayed(FastTrackMopubAdapter.this.bannerViewRepeatRequestRunnable, 15000);
                return;
            }
            Log.d("fastTrackTag", "mopub banner attempt to load failed: bannerView null");
        }
    };
    /* access modifiers changed from: private */
    public Runnable fullscreenPendingRequestCancelRunnable = new Runnable() {
        public final void run() {
            FastTrackMopubAdapter.this.lambda$new$1$FastTrackMopubAdapter();
        }
    };
    /* access modifiers changed from: private */
    public String fullscreenPlacementId;
    /* access modifiers changed from: private */
    public MoPubInterstitial interstitialAd;
    private boolean isMopubRewardedVideosActive;
    /* access modifiers changed from: private */
    public ImageView noFillPlaceholder;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    /* access modifiers changed from: private */
    public String rewardedVideoPlacementId;
    /* access modifiers changed from: private */
    public Runnable rewardedVideoShowCancelRunnable = new Runnable() {
        public final void run() {
            FastTrackMopubAdapter.this.lambda$new$2$FastTrackMopubAdapter();
        }
    };

    public void loadNativeAds(int i) {
    }

    public FastTrackMopubAdapter(FastTrackSdkModel fastTrackSdkModel, Context context) {
        super(fastTrackSdkModel, context);
    }

    /* access modifiers changed from: protected */
    public void init() {
        String str;
        this.fullscreenPlacementId = this.fastTrackSdkModel.getFullscreenPlacementId();
        this.bannerPlacementId = this.fastTrackSdkModel.getBannerPlacementId();
        this.rewardedVideoPlacementId = this.fastTrackSdkModel.getRewardedVideoPlacementId();
        String str2 = this.fullscreenPlacementId;
        if (str2 != null && !str2.isEmpty()) {
            this.interstitialDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomFullscreenActivated()) {
                this.interstitialDetails.put("ad_source", "ft_mopub_custom");
                this.interstitialDetails.put("net_name", "ft_mopub_custom");
                this.interstitialDetails.put("net_name_FS", "ft_mopub_custom");
                Log.d("fastTrackTag", "mopub fullscreen: custom");
            } else {
                this.interstitialDetails.put("ad_source", "ft_mopub");
                this.interstitialDetails.put("net_name", "ft_mopub");
                this.interstitialDetails.put("net_name_FS", "ft_mopub");
                Log.d("fastTrackTag", "mopub fullscreen: platform");
            }
        }
        String str3 = this.bannerPlacementId;
        if (str3 != null && !str3.isEmpty()) {
            this.bannerDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomBannerActivated()) {
                this.bannerDetails.put("ad_source", "ft_mopub_custom");
                this.bannerDetails.put("net_name", "ft_mopub_custom");
                this.bannerDetails.put("net_name_FS", "ft_mopub_custom");
                Log.d("fastTrackTag", "mopub banner: custom");
            } else {
                this.bannerDetails.put("ad_source", "ft_mopub");
                this.bannerDetails.put("net_name", "ft_mopub");
                this.bannerDetails.put("net_name_FS", "ft_mopub");
                Log.d("fastTrackTag", "mopub banner: platform");
            }
        }
        String str4 = this.rewardedVideoPlacementId;
        if (str4 != null && !str4.isEmpty()) {
            this.rewardedDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomRewardedActivated()) {
                this.rewardedDetails.put("ad_source", "ft_mopub_custom");
                this.rewardedDetails.put("net_name", "ft_mopub_custom");
                this.rewardedDetails.put("net_name_FS", "ft_mopub_custom");
                Log.d("fastTrackTag", "mopub rewarded: custom");
            } else {
                this.rewardedDetails.put("ad_source", "ft_mopub");
                this.rewardedDetails.put("net_name", "ft_mopub");
                this.rewardedDetails.put("net_name_FS", "ft_mopub");
                Log.d("fastTrackTag", "mopub rewarded: platform");
            }
        }
        String str5 = this.fullscreenPlacementId;
        if (str5 == null || str5.isEmpty()) {
            String str6 = this.bannerPlacementId;
            if (str6 == null || str6.isEmpty()) {
                String str7 = this.rewardedVideoPlacementId;
                str = (str7 == null || str7.isEmpty()) ? "" : this.rewardedVideoPlacementId;
            } else {
                str = this.bannerPlacementId;
            }
        } else {
            str = this.fullscreenPlacementId;
        }
        MoPub.initializeSdk(this.context, new SdkConfiguration.Builder(str).build(), new SdkInitializationListener() {
            public void onInitializationFinished() {
                Log.d("fastTrackTag", "MoPub SDK onInitializationFinished");
            }
        });
        new MoPubConversionTracker(this.context).reportAppOpen();
        if (this.fastTrackSdkModel.getStartAppId() != null && !this.fastTrackSdkModel.getStartAppId().isEmpty()) {
            StartAppSDK.init(this.context, this.fastTrackSdkModel.getStartAppId(), false);
            StartAppAd.disableSplash();
            StartAppSDK.setUserConsent(this.context, "pas", System.currentTimeMillis(), true);
        }
    }

    public void initBannerView(final ViewGroup viewGroup, String str) {
        final Integer bannerViewRefreshRate = getBannerViewRefreshRate(str);
        viewGroup.setVisibility(8);
        String str2 = this.bannerPlacementId;
        if (str2 != null && !str2.isEmpty() && bannerViewRefreshRate.intValue() != 0) {
            Log.d("fastTrackTag", "mopub banner initializing: " + this.bannerPlacementId);
            MoPubView moPubView = new MoPubView(this.context);
            this.bannerView = moPubView;
            moPubView.setAutorefreshEnabled(false);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, (int) TypedValue.applyDimension(1, 50.0f, this.context.getResources().getDisplayMetrics()));
            this.bannerView.setLayoutParams(layoutParams);
            this.bannerView.setAdUnitId(this.bannerPlacementId);
            ProgressBar progressBar2 = new ProgressBar(this.context);
            this.progressBar = progressBar2;
            progressBar2.setLayoutParams(layoutParams);
            viewGroup.addView(this.progressBar);
            this.progressBar.setVisibility(0);
            ImageView imageView = new ImageView(this.context);
            this.noFillPlaceholder = imageView;
            imageView.setLayoutParams(layoutParams);
            this.noFillPlaceholder.setBackgroundColor(-1);
            this.noFillPlaceholder.setImageResource(R.drawable.appsgeysersdk_banner_placeholder);
            this.noFillPlaceholder.setVisibility(8);
            viewGroup.addView(this.noFillPlaceholder);
            this.bannerView.setBannerAdListener(new MoPubView.BannerAdListener() {
                public void onBannerLoaded(MoPubView moPubView) {
                    if (viewGroup == null || FastTrackMopubAdapter.this.bannerView == null) {
                        Log.d("fastTrackTag", "mopub banner loaded, but bannerViewContainer is null");
                    } else {
                        viewGroup.setVisibility(0);
                        FastTrackMopubAdapter.this.bannerView.setVisibility(0);
                        FastTrackMopubAdapter.this.progressBar.setVisibility(8);
                        HashMap hashMap = FastTrackMopubAdapter.this.bannerDetails;
                        hashMap.put("details", "banner id: " + FastTrackMopubAdapter.this.bannerPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_impression", FastTrackMopubAdapter.this.bannerDetails, FastTrackMopubAdapter.this.context, true);
                        FastTrackMopubAdapter.this.handler.removeCallbacks(FastTrackMopubAdapter.this.bannerViewRepeatRequestRunnable);
                        FastTrackMopubAdapter.this.handler.removeCallbacks(FastTrackMopubAdapter.this.bannerViewRefreshRunnable);
                        FastTrackMopubAdapter.this.handler.postDelayed(FastTrackMopubAdapter.this.bannerViewRefreshRunnable, (long) bannerViewRefreshRate.intValue());
                    }
                    Log.d("fastTrackTag", "mopub banner onBannerLoaded");
                }

                public void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode) {
                    Log.d("fastTrackTag", "mopub banner onBannerFailed: " + moPubErrorCode.toString());
                    boolean unused = FastTrackMopubAdapter.this.bannerRequestFailReported = true;
                    FastTrackMopubAdapter.this.progressBar.setVisibility(8);
                    FastTrackMopubAdapter.this.noFillPlaceholder.setVisibility(0);
                    if (moPubErrorCode == MoPubErrorCode.NO_FILL) {
                        HashMap hashMap = FastTrackMopubAdapter.this.bannerDetails;
                        hashMap.put("details", "banner id: " + FastTrackMopubAdapter.this.bannerPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_nofill", FastTrackMopubAdapter.this.bannerDetails, FastTrackMopubAdapter.this.context, true);
                        return;
                    }
                    HashMap hashMap2 = FastTrackMopubAdapter.this.bannerDetails;
                    hashMap2.put("details", "banner id: " + FastTrackMopubAdapter.this.bannerPlacementId + "; error_desc: error code " + moPubErrorCode.toString());
                    StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_error", FastTrackMopubAdapter.this.bannerDetails, FastTrackMopubAdapter.this.context, true);
                }

                public void onBannerClicked(MoPubView moPubView) {
                    if (!FastTrackMopubAdapter.this.bannerClickReportedRecently) {
                        HashMap hashMap = FastTrackMopubAdapter.this.bannerDetails;
                        hashMap.put("details", "banner id: " + FastTrackMopubAdapter.this.bannerPlacementId);
                        boolean unused = FastTrackMopubAdapter.this.bannerClickReportedRecently = true;
                        FastTrackMopubAdapter.this.handler.postDelayed(new Runnable() {
                            public final void run() {
                                FastTrackMopubAdapter.AnonymousClass2.this.lambda$onBannerClicked$0$FastTrackMopubAdapter$2();
                            }
                        }, 1000);
                        StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_click", FastTrackMopubAdapter.this.bannerDetails, FastTrackMopubAdapter.this.context, true);
                        Log.d("fastTrackTag", "mopub banner onBannerClicked");
                    }
                }

                public /* synthetic */ void lambda$onBannerClicked$0$FastTrackMopubAdapter$2() {
                    boolean unused = FastTrackMopubAdapter.this.bannerClickReportedRecently = false;
                }

                public void onBannerExpanded(MoPubView moPubView) {
                    Log.d("fastTrackTag", "mopub banner onBannerExpanded");
                }

                public void onBannerCollapsed(MoPubView moPubView) {
                    Log.d("fastTrackTag", "mopub banner onBannerCollapsed");
                }
            });
            Log.d("fastTrackTag", "mopub banner attempt to attach bannerView to container");
            this.bannerViewContainer = viewGroup;
            this.bannerViewContainer.addView(this.bannerView);
            this.handler.post(this.bannerViewRefreshRunnable);
        }
    }

    public void loadFullscreen() {
        String str = this.fullscreenPlacementId;
        if (str != null && !str.isEmpty()) {
            this.interstitialAd = new MoPubInterstitial((Activity) this.context, this.fullscreenPlacementId);
            Log.d("fastTrackTag", "mopub fullscreen initializing: " + this.fullscreenPlacementId);
            this.interstitialAd.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
                    if (FastTrackMopubAdapter.this.pendingFullscreenRequest && FastTrackMopubAdapter.this.isInForeground) {
                        FastTrackMopubAdapter.this.pendingFullscreenRequest = false;
                        Log.d("fastTrackTag", "mopub fullscreen loaded, pending request processing");
                        FastTrackMopubAdapter.this.handler.removeCallbacks(FastTrackMopubAdapter.this.fullscreenPendingRequestCancelRunnable);
                        AppsgeyserProgressDialog appsgeyserProgressDialog = FastTrackMopubAdapter.this.progressDialog;
                        appsgeyserProgressDialog.show();
                        FastTrackMopubAdapter.this.handler.postDelayed(new Runnable(appsgeyserProgressDialog) {
                            public final /* synthetic */ AppsgeyserProgressDialog f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                FastTrackMopubAdapter.AnonymousClass5.this.lambda$onInterstitialLoaded$0$FastTrackMopubAdapter$5(this.f$1);
                            }
                        }, 2000);
                    }
                    Log.d("fastTrackTag", "mopub fullscreen onInterstitialLoaded");
                }

                public /* synthetic */ void lambda$onInterstitialLoaded$0$FastTrackMopubAdapter$5(AppsgeyserProgressDialog appsgeyserProgressDialog) {
                    try {
                        appsgeyserProgressDialog.dismiss();
                    } catch (IllegalArgumentException unused) {
                        Log.d("fastTrackTag", "progressDialog dismissal IAE");
                    }
                    FastTrackMopubAdapter.this.interstitialAd.show();
                }

                public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
                    if (moPubErrorCode == MoPubErrorCode.NO_FILL) {
                        HashMap hashMap = FastTrackMopubAdapter.this.interstitialDetails;
                        hashMap.put("details", "fs id: " + FastTrackMopubAdapter.this.fullscreenPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey("ft_interstitial_sdk_nofill", FastTrackMopubAdapter.this.interstitialDetails, FastTrackMopubAdapter.this.context, true);
                    } else {
                        HashMap hashMap2 = FastTrackMopubAdapter.this.interstitialDetails;
                        hashMap2.put("details", "fs id: " + FastTrackMopubAdapter.this.fullscreenPlacementId + "; error_desc: error code " + moPubErrorCode.toString());
                        StatController.getInstance().sendRequestAsyncByKey("ft_interstitial_sdk_error", FastTrackMopubAdapter.this.interstitialDetails, FastTrackMopubAdapter.this.context, true);
                    }
                    FastTrackMopubAdapter.this.handler.postDelayed(new Runnable() {
                        public final void run() {
                            FastTrackMopubAdapter.AnonymousClass5.this.lambda$onInterstitialFailed$1$FastTrackMopubAdapter$5();
                        }
                    }, 30000);
                    Log.d("fastTrackTag", "mopub fullscreen onInterstitialFailed: " + moPubErrorCode.toString());
                }

                public /* synthetic */ void lambda$onInterstitialFailed$1$FastTrackMopubAdapter$5() {
                    FastTrackMopubAdapter.this.loadFullscreen();
                }

                public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {
                    Log.d("fastTrackTag", "mopub fullscreen onInterstitialShown");
                    HashMap hashMap = FastTrackMopubAdapter.this.interstitialDetails;
                    hashMap.put("details", "fs id: " + FastTrackMopubAdapter.this.fullscreenPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey("ft_interstitial_sdk_impression", FastTrackMopubAdapter.this.interstitialDetails, FastTrackMopubAdapter.this.context, true);
                    if (FastTrackMopubAdapter.this.fullscreenListener != null) {
                        FastTrackMopubAdapter.this.fullscreenListener.onShow();
                    }
                }

                public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {
                    Log.d("fastTrackTag", "mopub fullscreen onInterstitialClicked");
                    HashMap hashMap = FastTrackMopubAdapter.this.interstitialDetails;
                    hashMap.put("details", "fs id: " + FastTrackMopubAdapter.this.fullscreenPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey("ft_interstitial_sdk_click", FastTrackMopubAdapter.this.interstitialDetails, FastTrackMopubAdapter.this.context, true);
                }

                public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {
                    FastTrackMopubAdapter.this.loadFullscreen();
                    if (FastTrackMopubAdapter.this.progressDialog != null && FastTrackMopubAdapter.this.progressDialog.isShowing()) {
                        try {
                            FastTrackMopubAdapter.this.progressDialog.dismiss();
                        } catch (IllegalArgumentException unused) {
                            Log.d("fastTrackTag", "progressDialog dismissal IAE");
                        }
                    }
                    if (FastTrackMopubAdapter.this.fullscreenListener != null) {
                        FastTrackMopubAdapter.this.fullscreenListener.onClose();
                    }
                    Log.d("fastTrackTag", "mopub fullscreen onInterstitialDismissed");
                }
            });
            Log.d("fastTrackTag", "mopub fullscreen attempt to load");
            HashMap hashMap = this.interstitialDetails;
            hashMap.put("details", "fs id: " + this.fullscreenPlacementId);
            this.interstitialDetails.put("uniqid", GuidGenerator.generateNewGuid());
            this.interstitialAd.load();
            StatController.getInstance().sendRequestAsyncByKey("ft_interstitial_sdk_request", this.interstitialDetails, this.context, true);
        }
    }

    public void showFullscreen(String str, String str2, boolean z) {
        if (!z || System.currentTimeMillis() - this.preferencesCoder.getPrefLong("appsgeyserSdk_lastRequestTiming", 0) > ((long) getFullscreenFrequencyTimerValue().intValue())) {
            this.preferencesCoder.savePrefLong("appsgeyserSdk_lastRequestTiming", System.currentTimeMillis());
            Log.d("fastTrackTag", "mopub fullscreen show request");
            if (this.fullscreenListener != null) {
                this.fullscreenListener.onRequest();
            }
            if (new Random().nextInt(100) + 1 > getFullscreenIntensityPoints(str2).intValue()) {
                Log.d("fastTrackTag", "mopub fullscreen attempt to show canceled due to intensity settings");
                if (this.fullscreenListener != null) {
                    this.fullscreenListener.onFailedToShow();
                }
            } else if (this.interstitialAd != null) {
                HashMap hashMap = this.interstitialDetails;
                hashMap.put("details", "fs id: " + this.fullscreenPlacementId);
                StatController.getInstance().sendRequestAsyncByKey("ft_interstitial_sdk_attempt", this.interstitialDetails, this.context, true);
                if (this.interstitialAd.isReady()) {
                    Log.d("fastTrackTag", "mopub fullscreen attempt to show");
                    AppsgeyserProgressDialog appsgeyserProgressDialog = this.progressDialog;
                    appsgeyserProgressDialog.show();
                    this.handler.postDelayed(new Runnable(appsgeyserProgressDialog) {
                        public final /* synthetic */ AppsgeyserProgressDialog f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            FastTrackMopubAdapter.this.lambda$showFullscreen$0$FastTrackMopubAdapter(this.f$1);
                        }
                    }, 2000);
                    return;
                }
                Log.d("fastTrackTag", "mopub fullscreen not loaded yet, waiting for load");
                this.pendingFullscreenRequest = true;
                this.handler.postDelayed(this.fullscreenPendingRequestCancelRunnable, (long) getFullscreenPendingDelayTimerValue().intValue());
            } else {
                Log.d("fastTrackTag", "mopub fullscreen disabled");
                if (this.fullscreenListener != null) {
                    this.fullscreenListener.onFailedToShow();
                }
            }
        } else {
            Log.d("fastTrackTag", "mopub fullscreen show request was cancelled due to frequency timing settings");
        }
    }

    public /* synthetic */ void lambda$showFullscreen$0$FastTrackMopubAdapter(AppsgeyserProgressDialog appsgeyserProgressDialog) {
        try {
            appsgeyserProgressDialog.dismiss();
        } catch (IllegalArgumentException unused) {
            Log.d("fastTrackTag", "progressDialog dismissal IAE");
        }
        this.interstitialAd.show();
    }

    public /* synthetic */ void lambda$new$1$FastTrackMopubAdapter() {
        this.pendingFullscreenRequest = false;
        if (this.fullscreenListener != null) {
            this.fullscreenListener.onFailedToShow();
        }
        Log.d("fastTrackTag", "mopub fullscreen not loaded, cancelling wait");
    }

    public void loadRewardedVideo() {
        String str = this.rewardedVideoPlacementId;
        if (str != null && !str.isEmpty()) {
            MoPub.onCreate((Activity) this.context);
            this.isMopubRewardedVideosActive = true;
            MoPubRewardedVideos.setRewardedVideoListener(new MoPubRewardedVideoListener() {
                public void onRewardedVideoLoadSuccess(String str) {
                    FastTrackMopubAdapter.this.videoDownloadError = false;
                    if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                        FastTrackMopubAdapter fastTrackMopubAdapter = FastTrackMopubAdapter.this;
                        fastTrackMopubAdapter.showRewardedVideo(fastTrackMopubAdapter.rewardedVideoListener, FastTrackMopubAdapter.this.rewardedVideoCurrentPlacement);
                    }
                    FastTrackMopubAdapter.this.handler.removeCallbacks(FastTrackMopubAdapter.this.rewardedVideoShowCancelRunnable);
                    Log.d("fastTrackTag", "mopub rewarded onRewardedVideoLoadSuccess");
                }

                public void onRewardedVideoLoadFailure(String str, MoPubErrorCode moPubErrorCode) {
                    FastTrackMopubAdapter.this.videoDownloadError = true;
                    if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                        if (FastTrackMopubAdapter.this.videoShowRequested) {
                            FastTrackMopubAdapter.this.rewardedVideoListener.onVideoError(FastTrackMopubAdapter.this.context.getResources().getString(R.string.appsgeysersdk_fasttrack_no_rew_video));
                            FastTrackMopubAdapter.this.videoShowRequested = false;
                        }
                        FastTrackMopubAdapter.this.rewardedVideoListener = null;
                    }
                    if (moPubErrorCode == MoPubErrorCode.NO_FILL) {
                        HashMap hashMap = FastTrackMopubAdapter.this.rewardedDetails;
                        hashMap.put("details", "rewarded id: " + FastTrackMopubAdapter.this.rewardedVideoPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey("ft_rewarded_sdk_nofill", FastTrackMopubAdapter.this.rewardedDetails, FastTrackMopubAdapter.this.context, true);
                    } else {
                        HashMap hashMap2 = FastTrackMopubAdapter.this.rewardedDetails;
                        hashMap2.put("details", "rewarded id: " + FastTrackMopubAdapter.this.rewardedVideoPlacementId + "; error_desc: error code " + moPubErrorCode.toString());
                        StatController.getInstance().sendRequestAsyncByKey("ft_rewarded_sdk_error", FastTrackMopubAdapter.this.rewardedDetails, FastTrackMopubAdapter.this.context, true);
                    }
                    FastTrackMopubAdapter.this.handler.postDelayed(new Runnable() {
                        public final void run() {
                            FastTrackMopubAdapter.AnonymousClass6.this.lambda$onRewardedVideoLoadFailure$0$FastTrackMopubAdapter$6();
                        }
                    }, 30000);
                    Log.d("fastTrackTag", "mopub rewarded onRewardedVideoLoadFailure: " + moPubErrorCode.toString());
                }

                public /* synthetic */ void lambda$onRewardedVideoLoadFailure$0$FastTrackMopubAdapter$6() {
                    FastTrackMopubAdapter.this.loadRewardedVideo();
                }

                public void onRewardedVideoStarted(String str) {
                    if (FastTrackMopubAdapter.this.progressDialog != null) {
                        FastTrackMopubAdapter.this.progressDialog.dismiss();
                    }
                    HashMap hashMap = FastTrackMopubAdapter.this.rewardedDetails;
                    hashMap.put("details", "rewarded id: " + FastTrackMopubAdapter.this.rewardedVideoPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey("ft_rewarded_sdk_impression", FastTrackMopubAdapter.this.rewardedDetails, FastTrackMopubAdapter.this.context, true);
                    if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                        FastTrackMopubAdapter.this.rewardedVideoListener.onVideoOpened();
                    }
                    Log.d("fastTrackTag", "mopub rewarded onRewardedVideoStarted");
                }

                public void onRewardedVideoPlaybackError(String str, MoPubErrorCode moPubErrorCode) {
                    if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                        FastTrackMopubAdapter.this.rewardedVideoListener.onVideoError(FastTrackMopubAdapter.this.context.getResources().getString(R.string.appsgeysersdk_fasttrack_no_rew_video));
                    }
                    Log.d("fastTrackTag", "mopub rewarded onRewardedVideoPlaybackError: " + moPubErrorCode.toString());
                }

                public void onRewardedVideoClicked(String str) {
                    HashMap hashMap = FastTrackMopubAdapter.this.rewardedDetails;
                    hashMap.put("details", "rewarded id: " + FastTrackMopubAdapter.this.rewardedVideoPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey("ft_rewarded_sdk_click", FastTrackMopubAdapter.this.rewardedDetails, FastTrackMopubAdapter.this.context, true);
                    if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                        FastTrackMopubAdapter.this.rewardedVideoListener.onVideoClicked();
                    }
                    Log.d("fastTrackTag", "mopub rewarded onRewardedVideoClicked");
                }

                public void onRewardedVideoClosed(String str) {
                    if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                        FastTrackMopubAdapter.this.rewardedVideoListener.onVideoClosed();
                        FastTrackMopubAdapter.this.rewardedVideoListener = null;
                    }
                    FastTrackMopubAdapter.this.loadRewardedVideo();
                    Log.d("fastTrackTag", "mopub rewarded onRewardedVideoClosed");
                }

                public void onRewardedVideoCompleted(Set<String> set, MoPubReward moPubReward) {
                    if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                        FastTrackMopubAdapter.this.rewardedVideoListener.onVideoFinished();
                    }
                    HashMap hashMap = FastTrackMopubAdapter.this.rewardedDetails;
                    hashMap.put("details", "rewarded id: " + FastTrackMopubAdapter.this.rewardedVideoPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey("ft_rewarded_sdk_completion", FastTrackMopubAdapter.this.rewardedDetails, FastTrackMopubAdapter.this.context, true);
                    Log.d("fastTrackTag", "mopub rewarded onRewardedVideoCompleted");
                }
            });
            Log.d("fastTrackTag", "mopub rewarded attempt to load");
            HashMap hashMap = this.rewardedDetails;
            hashMap.put("details", "rewarded id: " + this.rewardedVideoPlacementId);
            this.rewardedDetails.put("uniqid", GuidGenerator.generateNewGuid());
            MoPubRewardedVideos.loadRewardedVideo(this.rewardedVideoPlacementId, new MediationSettings[0]);
            StatController.getInstance().sendRequestAsyncByKey("ft_rewarded_sdk_request", this.rewardedDetails, this.context, true);
        }
    }

    public void showRewardedVideo(FastTrackBaseAdapter.RewardedVideoListener rewardedVideoListener, String str) {
        this.rewardedVideoListener = rewardedVideoListener;
        this.rewardedVideoCurrentPlacement = str;
        if (!this.isMopubRewardedVideosActive || !getRewardedVideoActivationStatus(str).booleanValue()) {
            Log.d("fastTrackTag", "Rewarded video placement disabled");
            this.rewardedVideoListener.onVideoDeactivated();
            this.rewardedVideoListener = null;
        } else if (MoPubRewardedVideos.hasRewardedVideo(this.rewardedVideoPlacementId)) {
            this.videoShowRequested = true;
            MoPubRewardedVideos.showRewardedVideo(this.rewardedVideoPlacementId);
        } else if (this.videoDownloadError) {
            this.rewardedVideoListener.onVideoError(this.context.getResources().getString(R.string.appsgeysersdk_fasttrack_no_rew_video));
            this.rewardedVideoListener = null;
        } else {
            if (this.progressDialog == null) {
                this.progressDialog = new AppsgeyserProgressDialog(this.context);
            }
            this.progressDialog.show();
            this.handler.postDelayed(this.rewardedVideoShowCancelRunnable, 10000);
        }
    }

    public /* synthetic */ void lambda$new$2$FastTrackMopubAdapter() {
        this.progressDialog.dismiss();
        this.progressDialog = null;
        if (this.rewardedVideoListener != null) {
            this.rewardedVideoListener.onVideoError(this.context.getResources().getString(R.string.appsgeysersdk_fasttrack_no_rew_video));
            this.rewardedVideoListener = null;
        }
    }

    public void onResume(Context context) {
        super.onResume(context);
        if (this.isMopubRewardedVideosActive) {
            MoPub.onResume((Activity) this.context);
        }
    }

    public void onPause() {
        super.onPause();
        if (this.bannerView != null) {
            if (this.bannerViewContainer != null) {
                Log.d("fastTrackTag", "mopub banner attempt to detach bannerView from container");
                this.bannerViewContainer.removeView(this.bannerView);
                this.bannerViewContainer.removeView(this.progressBar);
                this.bannerViewContainer.removeView(this.noFillPlaceholder);
                this.bannerViewContainer = null;
            }
            this.bannerView.setBannerAdListener((MoPubView.BannerAdListener) null);
            this.bannerView.destroy();
            this.bannerView = null;
            this.progressBar = null;
            this.noFillPlaceholder = null;
        }
        this.handler.removeCallbacks(this.bannerViewRepeatRequestRunnable);
        this.handler.removeCallbacks(this.bannerViewRefreshRunnable);
        if (this.isMopubRewardedVideosActive) {
            MoPub.onPause((Activity) this.context);
        }
    }
}
