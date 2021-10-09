package com.mopub.mobileads;

import android.app.Activity;
import android.os.Handler;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.AdTypeTranslator;
import com.mopub.mobileads.CustomEventInterstitial;
import java.util.Map;

public abstract class MoPubRewardedAd extends CustomEventRewardedAd {
    protected String mAdUnitId;
    /* access modifiers changed from: private */
    public boolean mIsLoaded;
    private int mRewardedAdCurrencyAmount;
    private String mRewardedAdCurrencyName;

    /* access modifiers changed from: protected */
    public boolean checkAndInitializeSdk(Activity activity, Map<String, Object> map, Map<String, String> map2) throws Exception {
        return false;
    }

    /* access modifiers changed from: protected */
    public LifecycleListener getLifecycleListener() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void loadWithSdkInitialized(Activity activity, Map<String, Object> map, Map<String, String> map2) throws Exception {
        Preconditions.checkNotNull(activity, "activity cannot be null");
        Preconditions.checkNotNull(map, "localExtras cannot be null");
        Preconditions.checkNotNull(map2, "serverExtras cannot be null");
        Object obj = map.get(DataKeys.REWARDED_AD_CURRENCY_NAME_KEY);
        if (obj instanceof String) {
            this.mRewardedAdCurrencyName = (String) obj;
        } else {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "No currency name specified for rewarded video. Using the default name.");
            this.mRewardedAdCurrencyName = "";
        }
        Object obj2 = map.get(DataKeys.REWARDED_AD_CURRENCY_AMOUNT_STRING_KEY);
        if (obj2 instanceof String) {
            try {
                this.mRewardedAdCurrencyAmount = Integer.parseInt((String) obj2);
            } catch (NumberFormatException unused) {
                MoPubLog.AdLogEvent adLogEvent = MoPubLog.AdLogEvent.CUSTOM;
                MoPubLog.log(adLogEvent, "Unable to convert currency amount: " + obj2 + ". Using the default reward amount: " + 0);
                this.mRewardedAdCurrencyAmount = 0;
            }
        } else {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "No currency amount specified for rewarded ad. Using the default reward amount: 0");
            this.mRewardedAdCurrencyAmount = 0;
        }
        if (this.mRewardedAdCurrencyAmount < 0) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Negative currency amount specified for rewarded ad. Using the default reward amount: 0");
            this.mRewardedAdCurrencyAmount = 0;
        }
        Object obj3 = map.get(DataKeys.AD_UNIT_ID_KEY);
        if (obj3 instanceof String) {
            this.mAdUnitId = (String) obj3;
            return;
        }
        MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Unable to set ad unit for rewarded ad.");
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        this.mIsLoaded = false;
    }

    /* access modifiers changed from: protected */
    public boolean isReady() {
        return this.mIsLoaded;
    }

    protected class MoPubRewardedAdListener implements CustomEventInterstitial.CustomEventInterstitialListener {
        private final Runnable mAdExpiration;
        final Class<? extends MoPubRewardedAd> mCustomEventClass;
        private Handler mHandler = new Handler();

        public void onInterstitialImpression() {
        }

        public MoPubRewardedAdListener(Class<? extends MoPubRewardedAd> cls) {
            Preconditions.checkNotNull(cls);
            this.mCustomEventClass = cls;
            this.mAdExpiration = new Runnable(MoPubRewardedAd.this) {
                public void run() {
                    MoPubLog.log(MoPubLog.AdLogEvent.EXPIRED, "time in seconds");
                    MoPubRewardedAdListener.this.onInterstitialFailed(MoPubErrorCode.EXPIRED);
                }
            };
        }

        public void onInterstitialLoaded() {
            MoPubLog.log(MoPubLog.AdLogEvent.LOAD_SUCCESS, new Object[0]);
            boolean unused = MoPubRewardedAd.this.mIsLoaded = true;
            if (AdTypeTranslator.CustomEventType.isMoPubSpecific(this.mCustomEventClass.getName())) {
                this.mHandler.postDelayed(this.mAdExpiration, 14400000);
            }
            MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId());
        }

        public void onInterstitialFailed(MoPubErrorCode moPubErrorCode) {
            MoPubLog.log(MoPubLog.AdLogEvent.SHOW_FAILED, Integer.valueOf(moPubErrorCode.getIntCode()), moPubErrorCode);
            this.mHandler.removeCallbacks(this.mAdExpiration);
            if (AnonymousClass1.$SwitchMap$com$mopub$mobileads$MoPubErrorCode[moPubErrorCode.ordinal()] != 1) {
                MoPubRewardedVideoManager.onRewardedVideoLoadFailure(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId(), moPubErrorCode);
            } else {
                MoPubRewardedVideoManager.onRewardedVideoPlaybackError(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId(), moPubErrorCode);
            }
        }

        public void onInterstitialShown() {
            MoPubLog.log(MoPubLog.AdLogEvent.SHOW_SUCCESS, new Object[0]);
            this.mHandler.removeCallbacks(this.mAdExpiration);
            MoPubRewardedVideoManager.onRewardedVideoStarted(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId());
        }

        public void onInterstitialClicked() {
            MoPubLog.log(MoPubLog.AdLogEvent.CLICKED, new Object[0]);
            MoPubRewardedVideoManager.onRewardedVideoClicked(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId());
        }

        public void onLeaveApplication() {
            MoPubLog.log(MoPubLog.AdLogEvent.WILL_LEAVE_APPLICATION, new Object[0]);
        }

        public void onInterstitialDismissed() {
            MoPubLog.log(MoPubLog.AdLogEvent.WILL_DISAPPEAR, new Object[0]);
            MoPubRewardedVideoManager.onRewardedVideoClosed(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId());
            MoPubRewardedAd.this.onInvalidate();
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public void setHandler(Handler handler) {
            this.mHandler = handler;
        }
    }

    /* renamed from: com.mopub.mobileads.MoPubRewardedAd$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$mobileads$MoPubErrorCode;

        static {
            int[] iArr = new int[MoPubErrorCode.values().length];
            $SwitchMap$com$mopub$mobileads$MoPubErrorCode = iArr;
            try {
                iArr[MoPubErrorCode.VIDEO_PLAYBACK_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getRewardedAdCurrencyName() {
        return this.mRewardedAdCurrencyName;
    }

    /* access modifiers changed from: protected */
    public int getRewardedAdCurrencyAmount() {
        return this.mRewardedAdCurrencyAmount;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setIsLoaded(boolean z) {
        this.mIsLoaded = z;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public MoPubRewardedAdListener createListener(Class<? extends MoPubRewardedAd> cls) {
        return new MoPubRewardedAdListener(cls);
    }
}
