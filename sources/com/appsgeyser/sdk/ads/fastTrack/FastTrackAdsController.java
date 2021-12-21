package com.appsgeyser.sdk.ads.fastTrack;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.ui.AppsgeyserProgressDialog;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

public class FastTrackAdsController {
    private static FastTrackAdsController instance;
    private FastTrackBaseAdapter adsAdapter;
    private BehaviorSubject<BannerViewPlacementWrapper> bannerViewPlacementPublishSubject = BehaviorSubject.create();
    private BehaviorSubject<ContextConfigWrapper> consentPendingBlocker = BehaviorSubject.create();
    private BehaviorSubject<FastTrackBaseAdapter.FullscreenListener> fullscreenListenerPublishSubject = BehaviorSubject.create();
    private String fullscreenPendingLoadTag;
    private String fullscreenPendingPlacementTag;
    private boolean fullscreenPendingUseFrequencyTimer;
    private boolean isActive;
    private BehaviorSubject<Integer> nativeAdsPublishSubject = BehaviorSubject.create();

    private FastTrackAdsController() {
    }

    public static synchronized FastTrackAdsController getInstance() {
        FastTrackAdsController fastTrackAdsController;
        synchronized (FastTrackAdsController.class) {
            if (instance == null) {
                instance = new FastTrackAdsController();
            }
            fastTrackAdsController = instance;
        }
        return fastTrackAdsController;
    }

    public void requestInit(ConfigPhp configPhp, Context context) {
        this.consentPendingBlocker.onNext(new ContextConfigWrapper(configPhp, context));
    }

    public void consentRequestProcessFinished() {
        this.consentPendingBlocker.subscribe(new Action1() {
            public final void call(Object obj) {
                FastTrackAdsController.this.init((FastTrackAdsController.ContextConfigWrapper) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void init(com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController.ContextConfigWrapper r7) {
        /*
            r6 = this;
            com.appsgeyser.sdk.configuration.models.ConfigPhp r0 = r7.getConfigPhp()
            com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel r0 = r0.getActiveAdsSDK()
            java.lang.String r1 = r0.getName()
            int r2 = r1.hashCode()
            r3 = -963943683(0xffffffffc68b62fd, float:-17841.494)
            r4 = 2
            r5 = 1
            if (r2 == r3) goto L_0x0036
            r3 = -261021665(0xfffffffff071201f, float:-2.9849888E29)
            if (r2 == r3) goto L_0x002c
            r3 = 958098324(0x391b6b94, float:1.4822028E-4)
            if (r2 == r3) goto L_0x0022
            goto L_0x0040
        L_0x0022:
            java.lang.String r2 = "facebookSdk"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0040
            r1 = 2
            goto L_0x0041
        L_0x002c:
            java.lang.String r2 = "mopubSdk"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0040
            r1 = 1
            goto L_0x0041
        L_0x0036:
            java.lang.String r2 = "admobSdk"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0040
            r1 = 0
            goto L_0x0041
        L_0x0040:
            r1 = -1
        L_0x0041:
            java.lang.String r2 = "fastTrackTag"
            if (r1 == 0) goto L_0x0094
            if (r1 == r5) goto L_0x0083
            if (r1 == r4) goto L_0x0072
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Unknown adsAdapter: "
            r1.append(r3)
            java.lang.String r3 = r0.getName()
            r1.append(r3)
            java.lang.String r3 = " . Disabling ads controller."
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r2, r1)
            com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackDisabledAdapter r1 = new com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackDisabledAdapter
            android.content.Context r2 = r7.getContext()
            r1.<init>(r0, r2)
            r6.adsAdapter = r1
            goto L_0x00a4
        L_0x0072:
            java.lang.String r1 = "initializing facebook adsAdapter"
            android.util.Log.d(r2, r1)
            com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackFacebookAdapter r1 = new com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackFacebookAdapter
            android.content.Context r2 = r7.getContext()
            r1.<init>(r0, r2)
            r6.adsAdapter = r1
            goto L_0x00a4
        L_0x0083:
            java.lang.String r1 = "initializing mopub adsAdapter"
            android.util.Log.d(r2, r1)
            com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackMopubAdapter r1 = new com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackMopubAdapter
            android.content.Context r2 = r7.getContext()
            r1.<init>(r0, r2)
            r6.adsAdapter = r1
            goto L_0x00a4
        L_0x0094:
            java.lang.String r1 = "initializing admob adsAdapter"
            android.util.Log.d(r2, r1)
            com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackAdmobAdapter r1 = new com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackAdmobAdapter
            android.content.Context r2 = r7.getContext()
            r1.<init>(r0, r2)
            r6.adsAdapter = r1
        L_0x00a4:
            com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter r0 = r6.adsAdapter
            r0.loadFullscreen()
            rx.subjects.BehaviorSubject<com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter$FullscreenListener> r0 = r6.fullscreenListenerPublishSubject
            com.appsgeyser.sdk.ads.fastTrack.-$$Lambda$FastTrackAdsController$MJ40-uST79s2Q36nLMwJceHje8A r1 = new com.appsgeyser.sdk.ads.fastTrack.-$$Lambda$FastTrackAdsController$MJ40-uST79s2Q36nLMwJceHje8A
            r1.<init>()
            r0.subscribe(r1)
            rx.subjects.BehaviorSubject<com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController$BannerViewPlacementWrapper> r0 = r6.bannerViewPlacementPublishSubject
            com.appsgeyser.sdk.ads.fastTrack.-$$Lambda$FastTrackAdsController$Bhk5kkCzW4qovUastg9szpspU1Y r1 = new com.appsgeyser.sdk.ads.fastTrack.-$$Lambda$FastTrackAdsController$Bhk5kkCzW4qovUastg9szpspU1Y
            r1.<init>()
            r0.subscribe(r1)
            rx.subjects.BehaviorSubject<java.lang.Integer> r0 = r6.nativeAdsPublishSubject
            com.appsgeyser.sdk.ads.fastTrack.-$$Lambda$FastTrackAdsController$H_4Q9xBKHf-RuzsMP0oeIUbwgzw r1 = new com.appsgeyser.sdk.ads.fastTrack.-$$Lambda$FastTrackAdsController$H_4Q9xBKHf-RuzsMP0oeIUbwgzw
            r1.<init>()
            r0.subscribe(r1)
            com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter r0 = r6.adsAdapter
            r0.loadRewardedVideo()
            r6.isActive = r5
            java.lang.String r0 = r6.fullscreenPendingLoadTag
            if (r0 == 0) goto L_0x00dd
            android.content.Context r7 = r7.getContext()
            java.lang.String r1 = r6.fullscreenPendingPlacementTag
            boolean r2 = r6.fullscreenPendingUseFrequencyTimer
            r6.showFullscreen(r0, r7, r1, r2)
        L_0x00dd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController.init(com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController$ContextConfigWrapper):void");
    }

    public /* synthetic */ void lambda$init$0$FastTrackAdsController(FastTrackBaseAdapter.FullscreenListener fullscreenListener) {
        this.adsAdapter.setFullscreenListener(fullscreenListener);
    }

    public /* synthetic */ void lambda$init$1$FastTrackAdsController(BannerViewPlacementWrapper bannerViewPlacementWrapper) {
        this.adsAdapter.initBannerView(bannerViewPlacementWrapper.getBannerViewContainer(), bannerViewPlacementWrapper.getPlacementTag());
    }

    public /* synthetic */ void lambda$init$2$FastTrackAdsController(Integer num) {
        this.adsAdapter.loadNativeAds(num.intValue());
    }

    public void showFullscreen(String str, Context context, String str2, boolean z) {
        if (!this.isActive || InternalEntryPoint.getInstance().isConsentRequestProcessActive()) {
            this.fullscreenPendingLoadTag = str;
            this.fullscreenPendingPlacementTag = str2;
            this.fullscreenPendingUseFrequencyTimer = z;
            Log.d("fastTrackTag", "fasttrack controller not activated");
            return;
        }
        if (context != null) {
            this.adsAdapter.setContext(context);
            this.adsAdapter.setProgressDialog(new AppsgeyserProgressDialog(context));
        }
        new Handler(this.adsAdapter.getContext().getMainLooper()).postDelayed(new Runnable(str, str2, z) {
            public final /* synthetic */ String f$1;
            public final /* synthetic */ String f$2;
            public final /* synthetic */ boolean f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                FastTrackAdsController.this.lambda$showFullscreen$3$FastTrackAdsController(this.f$1, this.f$2, this.f$3);
            }
        }, 1000);
        this.fullscreenPendingLoadTag = null;
        this.fullscreenPendingPlacementTag = null;
        this.fullscreenPendingUseFrequencyTimer = z;
    }

    public /* synthetic */ void lambda$showFullscreen$3$FastTrackAdsController(String str, String str2, boolean z) {
        this.adsAdapter.showFullscreen(str, str2, z);
        Log.d("fastTrackTag", "attempt to show fullscreen");
    }

    public void showPendingFullscreen(Context context) {
        String str = this.fullscreenPendingLoadTag;
        if (str != null) {
            showFullscreen(str, context, this.fullscreenPendingPlacementTag, this.fullscreenPendingUseFrequencyTimer);
        }
    }

    public void setFullscreenListener(FastTrackBaseAdapter.FullscreenListener fullscreenListener) {
        if (fullscreenListener != null) {
            this.fullscreenListenerPublishSubject.onNext(fullscreenListener);
        }
    }

    public void setBannerViewContainer(ViewGroup viewGroup, String str) {
        if (viewGroup != null) {
            viewGroup.setVisibility(8);
            this.bannerViewPlacementPublishSubject.onNext(new BannerViewPlacementWrapper(viewGroup, str));
        }
    }

    public void showRewardedVideo(FastTrackBaseAdapter.RewardedVideoListener rewardedVideoListener, String str) {
        if (rewardedVideoListener == null) {
            return;
        }
        if (this.isActive) {
            this.adsAdapter.showRewardedVideo(rewardedVideoListener, str);
            Log.d("fastTrackTag", "attempt to show rewardedVideo");
            return;
        }
        rewardedVideoListener.onVideoDeactivated();
        Log.d("fastTrackTag", "fasttrack controller not activated");
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void onPause() {
        FastTrackBaseAdapter fastTrackBaseAdapter = this.adsAdapter;
        if (fastTrackBaseAdapter != null) {
            fastTrackBaseAdapter.onPause();
        }
    }

    public void onResume(Context context) {
        FastTrackBaseAdapter fastTrackBaseAdapter = this.adsAdapter;
        if (fastTrackBaseAdapter != null) {
            fastTrackBaseAdapter.onResume(context);
        }
    }

    private class ContextConfigWrapper {
        ConfigPhp configPhp;
        Context context;

        ContextConfigWrapper(ConfigPhp configPhp2, Context context2) {
            this.configPhp = configPhp2;
            this.context = context2;
        }

        /* access modifiers changed from: package-private */
        public ConfigPhp getConfigPhp() {
            return this.configPhp;
        }

        /* access modifiers changed from: package-private */
        public Context getContext() {
            return this.context;
        }
    }

    private class BannerViewPlacementWrapper {
        ViewGroup bannerViewContainer;
        String placementTag;

        BannerViewPlacementWrapper(ViewGroup viewGroup, String str) {
            this.bannerViewContainer = viewGroup;
            this.placementTag = str;
        }

        /* access modifiers changed from: package-private */
        public ViewGroup getBannerViewContainer() {
            return this.bannerViewContainer;
        }

        /* access modifiers changed from: package-private */
        public String getPlacementTag() {
            return this.placementTag;
        }
    }
}
