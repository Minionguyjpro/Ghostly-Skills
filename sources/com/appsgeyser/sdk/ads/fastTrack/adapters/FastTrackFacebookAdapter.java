package com.appsgeyser.sdk.ads.fastTrack.adapters;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import com.appsgeyser.sdk.GuidGenerator;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackFacebookAdapter;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.ui.AppsgeyserProgressDialog;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import java.util.HashMap;
import java.util.Random;

public class FastTrackFacebookAdapter extends FastTrackBaseAdapter {
    /* access modifiers changed from: private */
    public AdListener adListener;
    /* access modifiers changed from: private */
    public AdView adView;
    /* access modifiers changed from: private */
    public String bannerPlacementId;
    /* access modifiers changed from: private */
    public boolean bannerRequestFailReported;
    /* access modifiers changed from: private */
    public Runnable bannerViewRefreshRunnable = new Runnable() {
        public void run() {
            if (FastTrackFacebookAdapter.this.adView != null) {
                Log.d("fastTrackTag", "facebook banner attempt to load");
                HashMap hashMap = FastTrackFacebookAdapter.this.bannerDetails;
                hashMap.put("details", "banner id: " + FastTrackFacebookAdapter.this.bannerPlacementId);
                FastTrackFacebookAdapter.this.bannerDetails.put("uniqid", GuidGenerator.generateNewGuid());
                StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_request", FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                FastTrackFacebookAdapter.this.adView.loadAd(FastTrackFacebookAdapter.this.adView.buildLoadAdConfig().withAdListener(FastTrackFacebookAdapter.this.adListener).build());
                boolean unused = FastTrackFacebookAdapter.this.bannerRequestFailReported = false;
                FastTrackFacebookAdapter.this.handler.postDelayed(FastTrackFacebookAdapter.this.bannerViewRepeatRequestRunnable, 15000);
                return;
            }
            Log.d("fastTrackTag", "facebook banner attempt to load failed: bannerView null");
        }
    };
    /* access modifiers changed from: private */
    public Runnable bannerViewRepeatRequestRunnable = new Runnable() {
        public void run() {
            if (FastTrackFacebookAdapter.this.adView != null) {
                Log.d("fastTrackTag", "admob banner repeat attempt to load");
                HashMap hashMap = FastTrackFacebookAdapter.this.bannerDetails;
                hashMap.put("details", "banner id: " + FastTrackFacebookAdapter.this.bannerPlacementId);
                if (!FastTrackFacebookAdapter.this.bannerRequestFailReported) {
                    StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_nofill", FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                }
                FastTrackFacebookAdapter.this.bannerDetails.put("uniqid", GuidGenerator.generateNewGuid());
                StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_request", FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                FastTrackFacebookAdapter.this.adView.loadAd(FastTrackFacebookAdapter.this.adView.buildLoadAdConfig().withAdListener(FastTrackFacebookAdapter.this.adListener).build());
                boolean unused = FastTrackFacebookAdapter.this.bannerRequestFailReported = false;
                FastTrackFacebookAdapter.this.handler.postDelayed(FastTrackFacebookAdapter.this.bannerViewRepeatRequestRunnable, 15000);
            }
        }
    };
    /* access modifiers changed from: private */
    public Runnable fullscreenPendingRequestCancelRunnable = new Runnable() {
        public final void run() {
            FastTrackFacebookAdapter.this.lambda$new$1$FastTrackFacebookAdapter();
        }
    };
    /* access modifiers changed from: private */
    public String fullscreenPlacementId;
    /* access modifiers changed from: private */
    public InterstitialAd interstitialAd;

    public void loadNativeAds(int i) {
    }

    public void loadRewardedVideo() {
    }

    public FastTrackFacebookAdapter(FastTrackSdkModel fastTrackSdkModel, Context context) {
        super(fastTrackSdkModel, context);
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.fullscreenPlacementId = this.fastTrackSdkModel.getFullscreenPlacementId();
        this.bannerPlacementId = this.fastTrackSdkModel.getBannerPlacementId();
        AudienceNetworkAds.initialize(this.context.getApplicationContext());
        String str = this.fullscreenPlacementId;
        if (str == null || str.isEmpty()) {
            Log.d("fastTrackTag", "facebook fullscreen disabled");
        } else {
            this.interstitialDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomFullscreenActivated()) {
                this.interstitialDetails.put("ad_source", "ft_facebook_custom");
                this.interstitialDetails.put("net_name", "ft_facebook_custom");
                this.interstitialDetails.put("net_name_FS", "ft_facebook_custom");
                Log.d("fastTrackTag", "facebook fullscreen: custom");
            } else {
                this.interstitialDetails.put("ad_source", "ft_facebook");
                this.interstitialDetails.put("net_name", "ft_facebook");
                this.interstitialDetails.put("net_name_FS", "ft_facebook");
                Log.d("fastTrackTag", "facebook fullscreen: platform");
            }
        }
        String str2 = this.bannerPlacementId;
        if (str2 == null || str2.isEmpty()) {
            Log.d("fastTrackTag", "facebook banner disabled");
            return;
        }
        this.bannerDetails.putAll(this.appDetails);
        if (this.fastTrackSdkModel.isCustomBannerActivated()) {
            this.bannerDetails.put("ad_source", "ft_facebook_custom");
            this.bannerDetails.put("net_name", "ft_facebook_custom");
            this.bannerDetails.put("net_name_FS", "ft_facebook_custom");
            Log.d("fastTrackTag", "facebook banner: custom");
            return;
        }
        this.bannerDetails.put("ad_source", "ft_facebook");
        this.bannerDetails.put("net_name", "ft_facebook");
        this.bannerDetails.put("net_name_FS", "ft_facebook");
        Log.d("fastTrackTag", "facebook banner: platform");
    }

    public void initBannerView(final ViewGroup viewGroup, String str) {
        final Integer bannerViewRefreshRate = getBannerViewRefreshRate(str);
        String str2 = this.bannerPlacementId;
        if (str2 != null && !str2.isEmpty() && bannerViewRefreshRate.intValue() != 0) {
            Log.d("fastTrackTag", "facebook banner initializing: " + this.bannerPlacementId);
            this.adView = new AdView(this.context, this.bannerPlacementId, AdSize.BANNER_HEIGHT_50);
            this.adListener = new AdListener() {
                public void onError(Ad ad, AdError adError) {
                    if (adError.getErrorCode() == 1001) {
                        HashMap hashMap = FastTrackFacebookAdapter.this.bannerDetails;
                        hashMap.put("details", "banner id: " + FastTrackFacebookAdapter.this.bannerPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_nofill", FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                    } else {
                        HashMap hashMap2 = FastTrackFacebookAdapter.this.bannerDetails;
                        hashMap2.put("details", "banner id: " + FastTrackFacebookAdapter.this.bannerPlacementId + "; error_desc: error code " + adError.getErrorCode() + " " + adError.getErrorMessage());
                        StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_error", FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                    }
                    boolean unused = FastTrackFacebookAdapter.this.bannerRequestFailReported = true;
                    Log.d("fastTrackTag", "facebook banner onError: " + adError.getErrorCode() + " " + adError.getErrorMessage());
                }

                public void onAdClicked(Ad ad) {
                    HashMap hashMap = FastTrackFacebookAdapter.this.bannerDetails;
                    hashMap.put("details", "banner id: " + FastTrackFacebookAdapter.this.bannerPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_click", FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                    Log.d("fastTrackTag", "facebook banner onAdClicked");
                }

                public void onAdLoaded(Ad ad) {
                    if (viewGroup == null || FastTrackFacebookAdapter.this.adView == null) {
                        Log.d("fastTrackTag", "facebook banner loaded, but bannerViewContainer is null");
                    } else {
                        HashMap hashMap = FastTrackFacebookAdapter.this.bannerDetails;
                        hashMap.put("details", "banner id: " + FastTrackFacebookAdapter.this.bannerPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey("ft_banner_sdk_impression", FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                        viewGroup.setVisibility(0);
                        FastTrackFacebookAdapter.this.handler.removeCallbacks(FastTrackFacebookAdapter.this.bannerViewRepeatRequestRunnable);
                        FastTrackFacebookAdapter.this.handler.removeCallbacks(FastTrackFacebookAdapter.this.bannerViewRefreshRunnable);
                        FastTrackFacebookAdapter.this.handler.postDelayed(FastTrackFacebookAdapter.this.bannerViewRefreshRunnable, (long) bannerViewRefreshRate.intValue());
                    }
                    Log.d("fastTrackTag", "facebook banner onAdLoaded");
                }

                public void onLoggingImpression(Ad ad) {
                    Log.d("fastTrackTag", "facebook banner onLoggingImpression");
                }
            };
            Log.d("fastTrackTag", "admob banner attempt to attach bannerView to container");
            this.bannerViewContainer = viewGroup;
            this.bannerViewContainer.addView(this.adView);
            this.handler.post(this.bannerViewRefreshRunnable);
        }
    }

    public void loadFullscreen() {
        String str = this.fullscreenPlacementId;
        if (str != null && !str.isEmpty()) {
            this.interstitialAd = new InterstitialAd(this.context, this.fullscreenPlacementId);
            Log.d("fastTrackTag", "facebook fullscreen initializing: " + this.fullscreenPlacementId);
            AnonymousClass4 r0 = new InterstitialAdListener() {
                public void onInterstitialDismissed(Ad ad) {
                    if (FastTrackFacebookAdapter.this.progressDialog != null && FastTrackFacebookAdapter.this.progressDialog.isShowing()) {
                        try {
                            FastTrackFacebookAdapter.this.progressDialog.dismiss();
                        } catch (IllegalArgumentException unused) {
                            Log.d("fastTrackTag", "progressDialog dismissal IAE");
                        }
                    }
                    FastTrackFacebookAdapter.this.loadFullscreen();
                    if (FastTrackFacebookAdapter.this.fullscreenListener != null) {
                        FastTrackFacebookAdapter.this.fullscreenListener.onClose();
                    }
                    Log.d("fastTrackTag", "facebook fs onInterstitialDismissed");
                }

                public void onError(Ad ad, AdError adError) {
                    if (adError.getErrorCode() == 1001) {
                        HashMap hashMap = FastTrackFacebookAdapter.this.interstitialDetails;
                        hashMap.put("details", "fs id: " + FastTrackFacebookAdapter.this.fullscreenPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey("ft_interstitial_sdk_nofill", FastTrackFacebookAdapter.this.interstitialDetails, FastTrackFacebookAdapter.this.context, true);
                    } else {
                        HashMap hashMap2 = FastTrackFacebookAdapter.this.interstitialDetails;
                        hashMap2.put("details", "fs id: " + FastTrackFacebookAdapter.this.fullscreenPlacementId + "; error_desc: error code " + adError.getErrorCode() + " " + adError.getErrorMessage());
                        StatController.getInstance().sendRequestAsyncByKey("ft_interstitial_sdk_error", FastTrackFacebookAdapter.this.interstitialDetails, FastTrackFacebookAdapter.this.context, true);
                    }
                    FastTrackFacebookAdapter.this.handler.postDelayed(new Runnable() {
                        public final void run() {
                            FastTrackFacebookAdapter.AnonymousClass4.this.lambda$onError$0$FastTrackFacebookAdapter$4();
                        }
                    }, 30000);
                    Log.d("fastTrackTag", "facebook fs onError " + adError.getErrorCode() + " " + adError.getErrorMessage());
                }

                public /* synthetic */ void lambda$onError$0$FastTrackFacebookAdapter$4() {
                    FastTrackFacebookAdapter.this.loadFullscreen();
                }

                public void onAdClicked(Ad ad) {
                    HashMap hashMap = FastTrackFacebookAdapter.this.interstitialDetails;
                    hashMap.put("details", "fs id: " + FastTrackFacebookAdapter.this.fullscreenPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey("ft_interstitial_sdk_click", FastTrackFacebookAdapter.this.interstitialDetails, FastTrackFacebookAdapter.this.context, true);
                    Log.d("fastTrackTag", "facebook fs onAdClicked");
                }

                public void onInterstitialDisplayed(Ad ad) {
                    HashMap hashMap = FastTrackFacebookAdapter.this.interstitialDetails;
                    hashMap.put("details", "fs id: " + FastTrackFacebookAdapter.this.fullscreenPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey("ft_interstitial_sdk_impression", FastTrackFacebookAdapter.this.interstitialDetails, FastTrackFacebookAdapter.this.context, true);
                    if (FastTrackFacebookAdapter.this.fullscreenListener != null) {
                        FastTrackFacebookAdapter.this.fullscreenListener.onShow();
                    }
                    Log.d("fastTrackTag", "facebook fs onAdOpened");
                }

                public void onAdLoaded(Ad ad) {
                    if (FastTrackFacebookAdapter.this.pendingFullscreenRequest && FastTrackFacebookAdapter.this.isInForeground) {
                        FastTrackFacebookAdapter.this.pendingFullscreenRequest = false;
                        Log.d("fastTrackTag", "facebook fullscreen loaded, pending request processing");
                        FastTrackFacebookAdapter.this.handler.removeCallbacks(FastTrackFacebookAdapter.this.fullscreenPendingRequestCancelRunnable);
                        AppsgeyserProgressDialog appsgeyserProgressDialog = FastTrackFacebookAdapter.this.progressDialog;
                        appsgeyserProgressDialog.show();
                        FastTrackFacebookAdapter.this.handler.postDelayed(new Runnable(appsgeyserProgressDialog) {
                            public final /* synthetic */ AppsgeyserProgressDialog f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                FastTrackFacebookAdapter.AnonymousClass4.this.lambda$onAdLoaded$1$FastTrackFacebookAdapter$4(this.f$1);
                            }
                        }, 2000);
                    }
                    Log.d("fastTrackTag", "facebook fs onAdLoaded");
                }

                public /* synthetic */ void lambda$onAdLoaded$1$FastTrackFacebookAdapter$4(AppsgeyserProgressDialog appsgeyserProgressDialog) {
                    try {
                        appsgeyserProgressDialog.dismiss();
                    } catch (IllegalArgumentException unused) {
                        Log.d("fastTrackTag", "progressDialog dismissal IAE");
                    }
                    FastTrackFacebookAdapter.this.interstitialAd.show();
                }

                public void onLoggingImpression(Ad ad) {
                    Log.d("fastTrackTag", "facebook fs onLoggingImpression");
                }
            };
            Log.d("fastTrackTag", "facebook fullscreen attempt to load");
            HashMap hashMap = this.interstitialDetails;
            hashMap.put("details", "fs id: " + this.fullscreenPlacementId);
            this.interstitialDetails.put("uniqid", GuidGenerator.generateNewGuid());
            InterstitialAd interstitialAd2 = this.interstitialAd;
            interstitialAd2.loadAd(interstitialAd2.buildLoadAdConfig().withAdListener(r0).build());
            StatController.getInstance().sendRequestAsyncByKey("ft_interstitial_sdk_request", this.interstitialDetails, this.context, true);
        }
    }

    public void showFullscreen(String str, String str2, boolean z) {
        if (!z || System.currentTimeMillis() - this.preferencesCoder.getPrefLong("appsgeyserSdk_lastRequestTiming", 0) > ((long) getFullscreenFrequencyTimerValue().intValue())) {
            this.preferencesCoder.savePrefLong("appsgeyserSdk_lastRequestTiming", System.currentTimeMillis());
            Log.d("fastTrackTag", "facebook fullscreen show request");
            if (this.fullscreenListener != null) {
                this.fullscreenListener.onRequest();
            }
            if (new Random().nextInt(100) + 1 > getFullscreenIntensityPoints(str2).intValue()) {
                Log.d("fastTrackTag", "facebook fullscreen attempt to show canceled due to intensity settings");
                if (this.fullscreenListener != null) {
                    this.fullscreenListener.onFailedToShow();
                }
            } else if (this.interstitialAd != null) {
                HashMap hashMap = this.interstitialDetails;
                hashMap.put("details", "fs id: " + this.fullscreenPlacementId);
                StatController.getInstance().sendRequestAsyncByKey("ft_interstitial_sdk_attempt", this.interstitialDetails, this.context, true);
                if (this.interstitialAd.isAdLoaded()) {
                    Log.d("fastTrackTag", "facebook fullscreen attempt to show");
                    AppsgeyserProgressDialog appsgeyserProgressDialog = this.progressDialog;
                    appsgeyserProgressDialog.show();
                    this.handler.postDelayed(new Runnable(appsgeyserProgressDialog) {
                        public final /* synthetic */ AppsgeyserProgressDialog f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            FastTrackFacebookAdapter.this.lambda$showFullscreen$0$FastTrackFacebookAdapter(this.f$1);
                        }
                    }, 2000);
                    return;
                }
                Log.d("fastTrackTag", "facebook fullscreen not loaded yet, waiting for load");
                this.pendingFullscreenRequest = true;
                this.handler.postDelayed(this.fullscreenPendingRequestCancelRunnable, (long) getFullscreenPendingDelayTimerValue().intValue());
            } else {
                Log.d("fastTrackTag", "facebook fullscreen disabled");
                if (this.fullscreenListener != null) {
                    this.fullscreenListener.onFailedToShow();
                }
            }
        } else {
            Log.d("fastTrackTag", "facebook fullscreen show request was cancelled due to frequency timing settings");
        }
    }

    public /* synthetic */ void lambda$showFullscreen$0$FastTrackFacebookAdapter(AppsgeyserProgressDialog appsgeyserProgressDialog) {
        try {
            appsgeyserProgressDialog.dismiss();
        } catch (IllegalArgumentException unused) {
            Log.d("fastTrackTag", "progressDialog dismissal IAE");
        }
        this.interstitialAd.show();
    }

    public /* synthetic */ void lambda$new$1$FastTrackFacebookAdapter() {
        this.pendingFullscreenRequest = false;
        if (this.fullscreenListener != null) {
            this.fullscreenListener.onFailedToShow();
        }
        Log.d("fastTrackTag", "facebook fullscreen not loaded, cancelling wait");
    }

    public void showRewardedVideo(FastTrackBaseAdapter.RewardedVideoListener rewardedVideoListener, String str) {
        rewardedVideoListener.onVideoDeactivated();
    }

    public void onPause() {
        super.onPause();
        if (this.adView != null) {
            if (this.bannerViewContainer != null) {
                Log.d("fastTrackTag", "facebook banner attempt to detach bannerView from container");
                this.bannerViewContainer.removeView(this.adView);
                this.bannerViewContainer = null;
            }
            this.adView.destroy();
            this.adView = null;
        }
        this.handler.removeCallbacks(this.bannerViewRefreshRunnable);
        this.handler.removeCallbacks(this.bannerViewRepeatRequestRunnable);
    }
}
