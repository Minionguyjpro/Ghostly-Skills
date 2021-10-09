package com.mopub.mobileads;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.BannerVisibilityTracker;
import com.mopub.mobileads.factories.CustomEventBannerFactory;
import java.util.Map;
import java.util.TreeMap;

public class CustomEventBannerAdapter implements InternalCustomEventBannerListener {
    public static final int DEFAULT_BANNER_TIMEOUT_DELAY = 10000;
    private Context mContext;
    /* access modifiers changed from: private */
    public CustomEventBanner mCustomEventBanner;
    private final Handler mHandler;
    private int mImpressionMinVisibleDips = RecyclerView.UNDEFINED_DURATION;
    private int mImpressionMinVisibleMs = RecyclerView.UNDEFINED_DURATION;
    private boolean mInvalidated;
    private Map<String, Object> mLocalExtras;
    /* access modifiers changed from: private */
    public MoPubView mMoPubView;
    private Map<String, String> mServerExtras;
    private final Runnable mTimeout;
    private BannerVisibilityTracker mVisibilityTracker;

    public CustomEventBannerAdapter(MoPubView moPubView, String str, Map<String, String> map, long j, AdReport adReport) {
        Preconditions.checkNotNull(map);
        this.mHandler = new Handler();
        this.mMoPubView = moPubView;
        this.mContext = moPubView.getContext();
        this.mTimeout = new Runnable() {
            public void run() {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "CustomEventBannerAdapter failed with code " + MoPubErrorCode.NETWORK_TIMEOUT.getIntCode() + " and message " + MoPubErrorCode.NETWORK_TIMEOUT);
                CustomEventBannerAdapter.this.onBannerFailed(MoPubErrorCode.NETWORK_TIMEOUT);
                CustomEventBannerAdapter.this.invalidate();
            }
        };
        MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
        MoPubLog.log(sdkLogEvent, "Attempting to invoke custom event: " + str);
        try {
            this.mCustomEventBanner = CustomEventBannerFactory.create(str);
            this.mServerExtras = new TreeMap(map);
            parseBannerImpressionTrackingHeaders();
            this.mLocalExtras = this.mMoPubView.getLocalExtras();
            if (this.mMoPubView.getLocation() != null) {
                this.mLocalExtras.put("location", this.mMoPubView.getLocation());
            }
            this.mLocalExtras.put(DataKeys.BROADCAST_IDENTIFIER_KEY, Long.valueOf(j));
            this.mLocalExtras.put(DataKeys.AD_REPORT_KEY, adReport);
            this.mLocalExtras.put(DataKeys.AD_WIDTH, Integer.valueOf(this.mMoPubView.getAdWidth()));
            this.mLocalExtras.put(DataKeys.AD_HEIGHT, Integer.valueOf(this.mMoPubView.getAdHeight()));
        } catch (Exception unused) {
            MoPubLog.SdkLogEvent sdkLogEvent2 = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent2, "Couldn't locate or instantiate custom event: " + str + ".");
            this.mMoPubView.loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
        }
    }

    /* access modifiers changed from: package-private */
    public void loadAd() {
        if (!isInvalidated() && this.mCustomEventBanner != null) {
            this.mHandler.postDelayed(this.mTimeout, (long) getTimeoutDelayMilliseconds());
            try {
                this.mCustomEventBanner.loadBanner(this.mContext, this, this.mLocalExtras, this.mServerExtras);
            } catch (Exception unused) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "loadAd() failed with code " + MoPubErrorCode.INTERNAL_ERROR.getIntCode() + " and message " + MoPubErrorCode.INTERNAL_ERROR);
                onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void invalidate() {
        CustomEventBanner customEventBanner = this.mCustomEventBanner;
        if (customEventBanner != null) {
            try {
                customEventBanner.onInvalidate();
            } catch (Exception e) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM_WITH_THROWABLE, "Invalidating a custom event banner threw an exception", e);
            }
        }
        BannerVisibilityTracker bannerVisibilityTracker = this.mVisibilityTracker;
        if (bannerVisibilityTracker != null) {
            try {
                bannerVisibilityTracker.destroy();
            } catch (Exception e2) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM_WITH_THROWABLE, "Destroying a banner visibility tracker threw an exception", e2);
            }
            this.mVisibilityTracker = null;
        }
        this.mContext = null;
        this.mCustomEventBanner = null;
        this.mLocalExtras = null;
        this.mServerExtras = null;
        this.mInvalidated = true;
    }

    /* access modifiers changed from: package-private */
    public boolean isInvalidated() {
        return this.mInvalidated;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public int getImpressionMinVisibleDips() {
        return this.mImpressionMinVisibleDips;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public int getImpressionMinVisibleMs() {
        return this.mImpressionMinVisibleMs;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public BannerVisibilityTracker getVisibilityTracker() {
        return this.mVisibilityTracker;
    }

    private void cancelTimeout() {
        this.mHandler.removeCallbacks(this.mTimeout);
    }

    private int getTimeoutDelayMilliseconds() {
        MoPubView moPubView = this.mMoPubView;
        if (moPubView == null) {
            return 10000;
        }
        return moPubView.getAdTimeoutDelay(10000).intValue();
    }

    private void parseBannerImpressionTrackingHeaders() {
        String str = this.mServerExtras.get(DataKeys.BANNER_IMPRESSION_MIN_VISIBLE_DIPS);
        String str2 = this.mServerExtras.get(DataKeys.BANNER_IMPRESSION_MIN_VISIBLE_MS);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                this.mImpressionMinVisibleDips = Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Cannot parse integer from header banner-impression-min-pixels");
            }
            try {
                this.mImpressionMinVisibleMs = Integer.parseInt(str2);
            } catch (NumberFormatException unused2) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Cannot parse integer from header banner-impression-min-ms");
            }
        }
    }

    public void onBannerLoaded(View view) {
        if (!isInvalidated()) {
            Preconditions.checkNotNull(view);
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "onBannerLoaded() success. Attempting to show.");
            cancelTimeout();
            MoPubView moPubView = this.mMoPubView;
            if (moPubView != null) {
                moPubView.creativeDownloaded();
                CustomEventBanner customEventBanner = this.mCustomEventBanner;
                if (customEventBanner != null && customEventBanner.isAutomaticImpressionAndClickTrackingEnabled()) {
                    this.mMoPubView.pauseAutoRefresh();
                    BannerVisibilityTracker bannerVisibilityTracker = new BannerVisibilityTracker(this.mContext, this.mMoPubView, view, this.mImpressionMinVisibleDips, this.mImpressionMinVisibleMs);
                    this.mVisibilityTracker = bannerVisibilityTracker;
                    bannerVisibilityTracker.setBannerVisibilityTrackerListener(new BannerVisibilityTracker.BannerVisibilityTrackerListener() {
                        public void onVisibilityChanged() {
                            CustomEventBannerAdapter.this.mMoPubView.trackNativeImpression();
                            if (CustomEventBannerAdapter.this.mCustomEventBanner != null) {
                                CustomEventBannerAdapter.this.mCustomEventBanner.trackMpxAndThirdPartyImpressions();
                            }
                            CustomEventBannerAdapter.this.mMoPubView.resumeAutoRefresh();
                        }
                    });
                }
                this.mMoPubView.setAdContentView(view);
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "onBannerLoaded() - Show successful.");
                return;
            }
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "onBannerLoaded() - Show failed with code " + MoPubErrorCode.INTERNAL_ERROR.getIntCode() + " and message " + MoPubErrorCode.INTERNAL_ERROR);
        }
    }

    public void onBannerFailed(MoPubErrorCode moPubErrorCode) {
        if (!isInvalidated()) {
            cancelTimeout();
            if (this.mMoPubView != null) {
                if (moPubErrorCode == null) {
                    moPubErrorCode = MoPubErrorCode.UNSPECIFIED;
                }
                this.mMoPubView.loadFailUrl(moPubErrorCode);
            }
        }
    }

    public void onBannerExpanded() {
        if (!isInvalidated()) {
            this.mMoPubView.engageOverlay();
            this.mMoPubView.adPresentedOverlay();
        }
    }

    public void onBannerCollapsed() {
        if (!isInvalidated()) {
            this.mMoPubView.dismissOverlay();
            this.mMoPubView.adClosed();
        }
    }

    public void onBannerClicked() {
        MoPubView moPubView;
        if (!isInvalidated() && (moPubView = this.mMoPubView) != null) {
            moPubView.registerClick();
        }
    }

    public void onBannerImpression() {
        CustomEventBanner customEventBanner;
        if (!isInvalidated() && this.mMoPubView != null && (customEventBanner = this.mCustomEventBanner) != null && !customEventBanner.isAutomaticImpressionAndClickTrackingEnabled()) {
            this.mMoPubView.trackNativeImpression();
            this.mCustomEventBanner.trackMpxAndThirdPartyImpressions();
        }
    }

    public void onLeaveApplication() {
        onBannerClicked();
    }

    public void onPauseAutoRefresh() {
        MoPubView moPubView = this.mMoPubView;
        if (moPubView != null) {
            moPubView.engageOverlay();
        }
    }

    public void onResumeAutoRefresh() {
        MoPubView moPubView = this.mMoPubView;
        if (moPubView != null) {
            moPubView.dismissOverlay();
        }
    }
}
