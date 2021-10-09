package com.mopub.nativeads;

import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import com.mopub.nativeads.MoPubNative;
import java.util.ArrayList;
import java.util.List;

class NativeAdSource {
    private static final int CACHE_LIMIT = 1;
    private static final int EXPIRATION_TIME_MILLISECONDS = 14400000;
    private static final int MAXIMUM_RETRY_TIME_MILLISECONDS = 300000;
    static final int[] RETRY_TIME_ARRAY_MILLISECONDS = {1000, 3000, 5000, 25000, 60000, MAXIMUM_RETRY_TIME_MILLISECONDS};
    private final AdRendererRegistry mAdRendererRegistry;
    /* access modifiers changed from: private */
    public AdSourceListener mAdSourceListener;
    int mCurrentRetries;
    /* access modifiers changed from: private */
    public MoPubNative mMoPubNative;
    private final MoPubNative.MoPubNativeNetworkListener mMoPubNativeNetworkListener;
    /* access modifiers changed from: private */
    public final List<TimestampWrapper<NativeAd>> mNativeAdCache;
    /* access modifiers changed from: private */
    public final Handler mReplenishCacheHandler;
    /* access modifiers changed from: private */
    public final Runnable mReplenishCacheRunnable;
    boolean mRequestInFlight;
    private RequestParameters mRequestParameters;
    boolean mRetryInFlight;
    int mSequenceNumber;

    interface AdSourceListener {
        void onAdsAvailable();
    }

    NativeAdSource() {
        this(new ArrayList(1), new Handler(), new AdRendererRegistry());
    }

    NativeAdSource(List<TimestampWrapper<NativeAd>> list, Handler handler, AdRendererRegistry adRendererRegistry) {
        this.mNativeAdCache = list;
        this.mReplenishCacheHandler = handler;
        this.mReplenishCacheRunnable = new Runnable() {
            public void run() {
                NativeAdSource.this.mRetryInFlight = false;
                NativeAdSource.this.replenishCache();
            }
        };
        this.mAdRendererRegistry = adRendererRegistry;
        this.mMoPubNativeNetworkListener = new MoPubNative.MoPubNativeNetworkListener() {
            public void onNativeLoad(NativeAd nativeAd) {
                if (NativeAdSource.this.mMoPubNative != null) {
                    NativeAdSource.this.mRequestInFlight = false;
                    NativeAdSource.this.mSequenceNumber++;
                    NativeAdSource.this.resetRetryTime();
                    NativeAdSource.this.mNativeAdCache.add(new TimestampWrapper(nativeAd));
                    if (NativeAdSource.this.mNativeAdCache.size() == 1 && NativeAdSource.this.mAdSourceListener != null) {
                        NativeAdSource.this.mAdSourceListener.onAdsAvailable();
                    }
                    NativeAdSource.this.replenishCache();
                }
            }

            public void onNativeFail(NativeErrorCode nativeErrorCode) {
                NativeAdSource.this.mRequestInFlight = false;
                if (NativeAdSource.this.mCurrentRetries >= NativeAdSource.RETRY_TIME_ARRAY_MILLISECONDS.length - 1) {
                    NativeAdSource.this.resetRetryTime();
                    return;
                }
                NativeAdSource.this.updateRetryTime();
                NativeAdSource.this.mRetryInFlight = true;
                NativeAdSource.this.mReplenishCacheHandler.postDelayed(NativeAdSource.this.mReplenishCacheRunnable, (long) NativeAdSource.this.getRetryTime());
            }
        };
        this.mSequenceNumber = 0;
        resetRetryTime();
    }

    /* access modifiers changed from: package-private */
    public int getAdRendererCount() {
        return this.mAdRendererRegistry.getAdRendererCount();
    }

    public int getViewTypeForAd(NativeAd nativeAd) {
        return this.mAdRendererRegistry.getViewTypeForAd(nativeAd);
    }

    /* access modifiers changed from: package-private */
    public void registerAdRenderer(MoPubAdRenderer moPubAdRenderer) {
        this.mAdRendererRegistry.registerAdRenderer(moPubAdRenderer);
        MoPubNative moPubNative = this.mMoPubNative;
        if (moPubNative != null) {
            moPubNative.registerAdRenderer(moPubAdRenderer);
        }
    }

    public MoPubAdRenderer getAdRendererForViewType(int i) {
        return this.mAdRendererRegistry.getRendererForViewType(i);
    }

    /* access modifiers changed from: package-private */
    public void setAdSourceListener(AdSourceListener adSourceListener) {
        this.mAdSourceListener = adSourceListener;
    }

    /* access modifiers changed from: package-private */
    public void loadAds(Activity activity, String str, RequestParameters requestParameters) {
        loadAds(requestParameters, new MoPubNative(activity, str, this.mMoPubNativeNetworkListener));
    }

    /* access modifiers changed from: package-private */
    public void loadAds(RequestParameters requestParameters, MoPubNative moPubNative) {
        clear();
        for (MoPubAdRenderer registerAdRenderer : this.mAdRendererRegistry.getRendererIterable()) {
            moPubNative.registerAdRenderer(registerAdRenderer);
        }
        this.mRequestParameters = requestParameters;
        this.mMoPubNative = moPubNative;
        replenishCache();
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        MoPubNative moPubNative = this.mMoPubNative;
        if (moPubNative != null) {
            moPubNative.destroy();
            this.mMoPubNative = null;
        }
        this.mRequestParameters = null;
        for (TimestampWrapper<NativeAd> timestampWrapper : this.mNativeAdCache) {
            ((NativeAd) timestampWrapper.mInstance).destroy();
        }
        this.mNativeAdCache.clear();
        this.mReplenishCacheHandler.removeMessages(0);
        this.mRequestInFlight = false;
        this.mSequenceNumber = 0;
        resetRetryTime();
    }

    /* access modifiers changed from: package-private */
    public NativeAd dequeueAd() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (!this.mRequestInFlight && !this.mRetryInFlight) {
            this.mReplenishCacheHandler.post(this.mReplenishCacheRunnable);
        }
        while (!this.mNativeAdCache.isEmpty()) {
            TimestampWrapper remove = this.mNativeAdCache.remove(0);
            if (uptimeMillis - remove.mCreatedTimestamp < 14400000) {
                return (NativeAd) remove.mInstance;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void updateRetryTime() {
        int i = this.mCurrentRetries;
        if (i < RETRY_TIME_ARRAY_MILLISECONDS.length - 1) {
            this.mCurrentRetries = i + 1;
        }
    }

    /* access modifiers changed from: package-private */
    public void resetRetryTime() {
        this.mCurrentRetries = 0;
    }

    /* access modifiers changed from: package-private */
    public int getRetryTime() {
        int i = this.mCurrentRetries;
        int[] iArr = RETRY_TIME_ARRAY_MILLISECONDS;
        if (i >= iArr.length) {
            this.mCurrentRetries = iArr.length - 1;
        }
        return RETRY_TIME_ARRAY_MILLISECONDS[this.mCurrentRetries];
    }

    /* access modifiers changed from: package-private */
    public void replenishCache() {
        if (!this.mRequestInFlight && this.mMoPubNative != null && this.mNativeAdCache.size() < 1) {
            this.mRequestInFlight = true;
            this.mMoPubNative.makeRequest(this.mRequestParameters, Integer.valueOf(this.mSequenceNumber));
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setMoPubNative(MoPubNative moPubNative) {
        this.mMoPubNative = moPubNative;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public MoPubNative.MoPubNativeNetworkListener getMoPubNativeNetworkListener() {
        return this.mMoPubNativeNetworkListener;
    }
}
