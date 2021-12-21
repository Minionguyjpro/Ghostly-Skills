package com.mopub.nativeads;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.CustomEventNative;
import com.mopub.nativeads.NativeImageHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class GooglePlayServicesNative extends CustomEventNative {
    private static final String ADAPTER_VERSION = "0.3.1";
    public static final String KEY_EXPERIMENTAL_EXTRA_SWAP_MARGINS = "swap_margins";
    public static final String KEY_EXTRA_AD_CHOICES_PLACEMENT = "ad_choices_placement";
    private static final String KEY_EXTRA_AD_UNIT_ID = "adunit";
    private static final String KEY_EXTRA_APPLICATION_ID = "appid";
    public static final String KEY_EXTRA_ORIENTATION_PREFERENCE = "orientation_preference";
    protected static final String TAG = "MoPubToAdMobNative";
    private static AtomicBoolean sIsInitialized = new AtomicBoolean(false);

    /* access modifiers changed from: protected */
    public void loadNativeAd(Context context, CustomEventNative.CustomEventNativeListener customEventNativeListener, Map<String, Object> map, Map<String, String> map2) {
        if (!sIsInitialized.getAndSet(true)) {
            Log.i(TAG, "Adapter version - 0.3.1");
            if (!map2.containsKey(KEY_EXTRA_APPLICATION_ID) || TextUtils.isEmpty(map2.get(KEY_EXTRA_APPLICATION_ID))) {
                MobileAds.initialize(context);
            } else {
                MobileAds.initialize(context, map2.get(KEY_EXTRA_APPLICATION_ID));
            }
        }
        String str = map2.get(KEY_EXTRA_AD_UNIT_ID);
        if (TextUtils.isEmpty(str)) {
            customEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_INVALID_REQUEST);
        } else {
            new GooglePlayServicesNativeAd(customEventNativeListener).loadAd(context, str, map);
        }
    }

    static class GooglePlayServicesNativeAd extends BaseNativeAd {
        private String mAdvertiser;
        private String mCallToAction;
        /* access modifiers changed from: private */
        public CustomEventNative.CustomEventNativeListener mCustomEventNativeListener;
        private String mIconImageUrl;
        private String mMainImageUrl;
        /* access modifiers changed from: private */
        public NativeAppInstallAd mNativeAppInstallAd;
        /* access modifiers changed from: private */
        public NativeContentAd mNativeContentAd;
        private String mPrice;
        private Double mStarRating;
        private String mStore;
        private boolean mSwapMargins;
        private String mText;
        private String mTitle;

        public void prepare(View view) {
        }

        public GooglePlayServicesNativeAd(CustomEventNative.CustomEventNativeListener customEventNativeListener) {
            this.mCustomEventNativeListener = customEventNativeListener;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getText() {
            return this.mText;
        }

        public String getMainImageUrl() {
            return this.mMainImageUrl;
        }

        public String getIconImageUrl() {
            return this.mIconImageUrl;
        }

        public String getCallToAction() {
            return this.mCallToAction;
        }

        public Double getStarRating() {
            return this.mStarRating;
        }

        public String getAdvertiser() {
            return this.mAdvertiser;
        }

        public String getStore() {
            return this.mStore;
        }

        public String getPrice() {
            return this.mPrice;
        }

        public void setTitle(String str) {
            this.mTitle = str;
        }

        public void setText(String str) {
            this.mText = str;
        }

        public void setMainImageUrl(String str) {
            this.mMainImageUrl = str;
        }

        public void setIconImageUrl(String str) {
            this.mIconImageUrl = str;
        }

        public void setCallToAction(String str) {
            this.mCallToAction = str;
        }

        public void setStarRating(Double d) {
            this.mStarRating = d;
        }

        public void setAdvertiser(String str) {
            this.mAdvertiser = str;
        }

        public void setStore(String str) {
            this.mStore = str;
        }

        public void setPrice(String str) {
            this.mPrice = str;
        }

        public boolean isNativeContentAd() {
            return this.mNativeContentAd != null;
        }

        public boolean shouldSwapMargins() {
            return this.mSwapMargins;
        }

        public boolean isNativeAppInstallAd() {
            return this.mNativeAppInstallAd != null;
        }

        public NativeContentAd getContentAd() {
            return this.mNativeContentAd;
        }

        public NativeAppInstallAd getAppInstallAd() {
            return this.mNativeAppInstallAd;
        }

        public void loadAd(final Context context, String str, Map<String, Object> map) {
            AdLoader.Builder builder = new AdLoader.Builder(context, str);
            if (map.containsKey(GooglePlayServicesNative.KEY_EXPERIMENTAL_EXTRA_SWAP_MARGINS)) {
                Object obj = map.get(GooglePlayServicesNative.KEY_EXPERIMENTAL_EXTRA_SWAP_MARGINS);
                if (obj instanceof Boolean) {
                    this.mSwapMargins = ((Boolean) obj).booleanValue();
                }
            }
            NativeAdOptions.Builder builder2 = new NativeAdOptions.Builder();
            builder2.setReturnUrlsForImageAssets(true);
            builder2.setRequestMultipleImages(false);
            if (map.containsKey(GooglePlayServicesNative.KEY_EXTRA_ORIENTATION_PREFERENCE) && isValidOrientationExtra(map.get(GooglePlayServicesNative.KEY_EXTRA_ORIENTATION_PREFERENCE))) {
                builder2.setImageOrientation(((Integer) map.get(GooglePlayServicesNative.KEY_EXTRA_ORIENTATION_PREFERENCE)).intValue());
            }
            if (map.containsKey(GooglePlayServicesNative.KEY_EXTRA_AD_CHOICES_PLACEMENT) && isValidAdChoicesPlacementExtra(map.get(GooglePlayServicesNative.KEY_EXTRA_AD_CHOICES_PLACEMENT))) {
                builder2.setAdChoicesPlacement(((Integer) map.get(GooglePlayServicesNative.KEY_EXTRA_AD_CHOICES_PLACEMENT)).intValue());
            }
            builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
                public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                    if (!GooglePlayServicesNativeAd.this.isValidContentAd(nativeContentAd)) {
                        Log.i(GooglePlayServicesNative.TAG, "The Google native content ad is missing one or more required assets, failing request.");
                        GooglePlayServicesNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
                        return;
                    }
                    NativeContentAd unused = GooglePlayServicesNativeAd.this.mNativeContentAd = nativeContentAd;
                    List<NativeAd.Image> images = nativeContentAd.getImages();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(images.get(0).getUri().toString());
                    arrayList.add(nativeContentAd.getLogo().getUri().toString());
                    GooglePlayServicesNativeAd.this.preCacheImages(context, arrayList);
                }
            }).forAppInstallAd(new NativeAppInstallAd.OnAppInstallAdLoadedListener() {
                public void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd) {
                    if (!GooglePlayServicesNativeAd.this.isValidAppInstallAd(nativeAppInstallAd)) {
                        Log.i(GooglePlayServicesNative.TAG, "The Google native app install ad is missing one or more required assets, failing request.");
                        GooglePlayServicesNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
                        return;
                    }
                    NativeAppInstallAd unused = GooglePlayServicesNativeAd.this.mNativeAppInstallAd = nativeAppInstallAd;
                    List<NativeAd.Image> images = nativeAppInstallAd.getImages();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(images.get(0).getUri().toString());
                    arrayList.add(nativeAppInstallAd.getIcon().getUri().toString());
                    GooglePlayServicesNativeAd.this.preCacheImages(context, arrayList);
                }
            }).withAdListener(new AdListener() {
                public void onAdClicked() {
                    super.onAdClicked();
                    GooglePlayServicesNativeAd.this.notifyAdClicked();
                }

                public void onAdImpression() {
                    super.onAdImpression();
                    GooglePlayServicesNativeAd.this.notifyAdImpressed();
                }

                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    if (i == 0) {
                        GooglePlayServicesNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NATIVE_ADAPTER_CONFIGURATION_ERROR);
                    } else if (i == 1) {
                        GooglePlayServicesNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_INVALID_REQUEST);
                    } else if (i == 2) {
                        GooglePlayServicesNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.CONNECTION_ERROR);
                    } else if (i != 3) {
                        GooglePlayServicesNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
                    } else {
                        GooglePlayServicesNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_NO_FILL);
                    }
                }
            }).withNativeAdOptions(builder2.build()).build().loadAd(new AdRequest.Builder().setRequestAgent(MoPubLog.LOGTAG).build());
        }

        private boolean isValidOrientationExtra(Object obj) {
            if (obj == null || !(obj instanceof Integer)) {
                return false;
            }
            Integer num = (Integer) obj;
            if (num.intValue() == 0 || num.intValue() == 2 || num.intValue() == 1) {
                return true;
            }
            return false;
        }

        private boolean isValidAdChoicesPlacementExtra(Object obj) {
            if (obj == null || !(obj instanceof Integer)) {
                return false;
            }
            Integer num = (Integer) obj;
            if (num.intValue() == 0 || num.intValue() == 1 || num.intValue() == 3 || num.intValue() == 2) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public boolean isValidContentAd(NativeContentAd nativeContentAd) {
            if (nativeContentAd.getHeadline() == null || nativeContentAd.getBody() == null || nativeContentAd.getImages() == null || nativeContentAd.getImages().size() <= 0 || nativeContentAd.getImages().get(0) == null || nativeContentAd.getLogo() == null || nativeContentAd.getCallToAction() == null) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public boolean isValidAppInstallAd(NativeAppInstallAd nativeAppInstallAd) {
            if (nativeAppInstallAd.getHeadline() == null || nativeAppInstallAd.getBody() == null || nativeAppInstallAd.getImages() == null || nativeAppInstallAd.getImages().size() <= 0 || nativeAppInstallAd.getImages().get(0) == null || nativeAppInstallAd.getIcon() == null || nativeAppInstallAd.getCallToAction() == null) {
                return false;
            }
            return true;
        }

        public void clear(View view) {
            GooglePlayServicesAdRenderer.removeGoogleNativeAdView(view, shouldSwapMargins());
        }

        public void destroy() {
            NativeContentAd nativeContentAd = this.mNativeContentAd;
            if (nativeContentAd != null) {
                nativeContentAd.destroy();
            }
            NativeAppInstallAd nativeAppInstallAd = this.mNativeAppInstallAd;
            if (nativeAppInstallAd != null) {
                nativeAppInstallAd.destroy();
            }
        }

        /* access modifiers changed from: private */
        public void preCacheImages(Context context, List<String> list) {
            NativeImageHelper.preCacheImages(context, list, new NativeImageHelper.ImageListener() {
                public void onImagesCached() {
                    if (GooglePlayServicesNativeAd.this.mNativeContentAd != null) {
                        GooglePlayServicesNativeAd googlePlayServicesNativeAd = GooglePlayServicesNativeAd.this;
                        googlePlayServicesNativeAd.prepareNativeContentAd(googlePlayServicesNativeAd.mNativeContentAd);
                        GooglePlayServicesNativeAd.this.mCustomEventNativeListener.onNativeAdLoaded(GooglePlayServicesNativeAd.this);
                    } else if (GooglePlayServicesNativeAd.this.mNativeAppInstallAd != null) {
                        GooglePlayServicesNativeAd googlePlayServicesNativeAd2 = GooglePlayServicesNativeAd.this;
                        googlePlayServicesNativeAd2.prepareNativeAppInstallAd(googlePlayServicesNativeAd2.mNativeAppInstallAd);
                        GooglePlayServicesNativeAd.this.mCustomEventNativeListener.onNativeAdLoaded(GooglePlayServicesNativeAd.this);
                    }
                }

                public void onImagesFailedToCache(NativeErrorCode nativeErrorCode) {
                    GooglePlayServicesNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(nativeErrorCode);
                }
            });
        }

        /* access modifiers changed from: private */
        public void prepareNativeContentAd(NativeContentAd nativeContentAd) {
            setMainImageUrl(nativeContentAd.getImages().get(0).getUri().toString());
            setIconImageUrl(nativeContentAd.getLogo().getUri().toString());
            setCallToAction(nativeContentAd.getCallToAction().toString());
            setTitle(nativeContentAd.getHeadline().toString());
            setText(nativeContentAd.getBody().toString());
            setAdvertiser(nativeContentAd.getAdvertiser().toString());
        }

        /* access modifiers changed from: private */
        public void prepareNativeAppInstallAd(NativeAppInstallAd nativeAppInstallAd) {
            setMainImageUrl(nativeAppInstallAd.getImages().get(0).getUri().toString());
            setIconImageUrl(nativeAppInstallAd.getIcon().getUri().toString());
            setCallToAction(nativeAppInstallAd.getCallToAction().toString());
            setTitle(nativeAppInstallAd.getHeadline().toString());
            setText(nativeAppInstallAd.getBody().toString());
            if (nativeAppInstallAd.getStarRating() != null) {
                setStarRating(nativeAppInstallAd.getStarRating());
            }
            if (nativeAppInstallAd.getStore() != null) {
                setStore(nativeAppInstallAd.getStore().toString());
            }
            if (nativeAppInstallAd.getPrice() != null) {
                setPrice(nativeAppInstallAd.getPrice().toString());
            }
        }
    }
}
