package com.mopub.nativeads;

import android.content.Context;
import android.view.View;
import com.mopub.mobileads.StartAppCustomEventUtils;
import com.mopub.nativeads.CustomEventNative;
import com.mopub.nativeads.NativeImageHelper;
import com.startapp.android.publish.ads.nativead.NativeAdDetails;
import com.startapp.android.publish.ads.nativead.NativeAdPreferences;
import com.startapp.android.publish.ads.nativead.StartAppNativeAd;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StartAppCustomEventNative extends CustomEventNative {
    private static final String APP_CTA = "Install";
    private static final String WEB_CTA = "Open";

    /* access modifiers changed from: protected */
    public void loadNativeAd(Context context, CustomEventNative.CustomEventNativeListener customEventNativeListener, Map<String, Object> map, Map<String, String> map2) {
        StartAppCustomEventUtils.checkInit(context, map2);
        new StartAppStaticNativeAd(context, StartAppCustomEventUtils.extractNativeAdPrefs(context, map, map2), customEventNativeListener, new ImpressionTracker(context), new NativeClickHandler(context), StartAppCustomEventUtils.getStringFromExtras("adTag", map2)).loadAd();
    }

    static class StartAppStaticNativeAd extends StaticNativeAd {
        private NativeAdDetails adDetails;
        private NativeAdPreferences adPrefs;
        /* access modifiers changed from: private */
        public String adTagNative;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public CustomEventNative.CustomEventNativeListener listener;
        private final ImpressionTracker mImpressionTracker;
        private final NativeClickHandler mNativeClickHandler;

        StartAppStaticNativeAd(Context context2, NativeAdPreferences nativeAdPreferences, CustomEventNative.CustomEventNativeListener customEventNativeListener, ImpressionTracker impressionTracker, NativeClickHandler nativeClickHandler, String str) {
            this.context = context2;
            this.adPrefs = nativeAdPreferences;
            this.listener = customEventNativeListener;
            this.mImpressionTracker = impressionTracker;
            this.mNativeClickHandler = nativeClickHandler;
            this.adTagNative = str;
        }

        /* access modifiers changed from: private */
        public boolean addUrl(List list, String str) {
            return str != null && list.add(str);
        }

        /* access modifiers changed from: package-private */
        public void loadAd() {
            final StartAppNativeAd startAppNativeAd = new StartAppNativeAd(this.context);
            startAppNativeAd.loadAd(this.adPrefs, new AdEventListener() {
                public void onReceiveAd(Ad ad) {
                    NativeAdDetails nativeAdDetails = startAppNativeAd.getNativeAds(StartAppStaticNativeAd.this.adTagNative).get(0);
                    StartAppStaticNativeAd.this.setAdDetails(nativeAdDetails);
                    StartAppStaticNativeAd.this.setTitle(nativeAdDetails.getTitle());
                    StartAppStaticNativeAd.this.setText(nativeAdDetails.getDescription());
                    StartAppStaticNativeAd.this.setCallToAction(nativeAdDetails.isApp().booleanValue() ? StartAppCustomEventNative.APP_CTA : StartAppCustomEventNative.WEB_CTA);
                    StartAppStaticNativeAd.this.setMainImageUrl(nativeAdDetails.getImageUrl());
                    StartAppStaticNativeAd.this.setIconImageUrl(nativeAdDetails.getSecondaryImageUrl());
                    StartAppStaticNativeAd.this.setStarRating(Double.valueOf((double) nativeAdDetails.getRating()));
                    ArrayList arrayList = new ArrayList();
                    boolean unused = StartAppStaticNativeAd.this.addUrl(arrayList, nativeAdDetails.getImageUrl());
                    boolean unused2 = StartAppStaticNativeAd.this.addUrl(arrayList, nativeAdDetails.getSecondaryImageUrl());
                    NativeImageHelper.preCacheImages(StartAppStaticNativeAd.this.context, arrayList, new NativeImageHelper.ImageListener() {
                        public void onImagesFailedToCache(NativeErrorCode nativeErrorCode) {
                            StartAppStaticNativeAd.this.listener.onNativeAdFailed(nativeErrorCode);
                        }

                        public void onImagesCached() {
                            StartAppStaticNativeAd.this.listener.onNativeAdLoaded(StartAppStaticNativeAd.this);
                        }
                    });
                }

                public void onFailedToReceiveAd(Ad ad) {
                    if (ad.getErrorMessage() == null || !ad.getErrorMessage().equals("Empty Response")) {
                        StartAppStaticNativeAd.this.listener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
                    } else {
                        StartAppStaticNativeAd.this.listener.onNativeAdFailed(NativeErrorCode.NETWORK_NO_FILL);
                    }
                }
            });
        }

        public void recordImpression(View view) {
            super.recordImpression(view);
            notifyAdImpressed();
            getAdDetails().sendImpression(this.context);
        }

        /* access modifiers changed from: protected */
        public NativeAdDetails getAdDetails() {
            return this.adDetails;
        }

        public void prepare(View view) {
            this.mImpressionTracker.addView(view, this);
            this.mNativeClickHandler.setOnClickListener(view, (ClickInterface) this);
        }

        public void clear(View view) {
            this.mImpressionTracker.removeView(view);
            this.mNativeClickHandler.clearOnClickListener(view);
        }

        public void handleClick(View view) {
            notifyAdClicked();
            getAdDetails().sendClick(this.context);
        }

        /* access modifiers changed from: protected */
        public void setAdDetails(NativeAdDetails nativeAdDetails) {
            this.adDetails = nativeAdDetails;
        }
    }
}
