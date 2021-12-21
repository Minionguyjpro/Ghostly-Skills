package com.mopub.nativeads;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.FacebookAdapterConfiguration;
import com.mopub.nativeads.CustomEventNative;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacebookNative extends CustomEventNative {
    /* access modifiers changed from: private */
    public static final String ADAPTER_NAME = FacebookNative.class.getSimpleName();
    private static final String NATIVE_BANNER_KEY = "native_banner";
    private static final String PLACEMENT_ID_KEY = "placement_id";
    private static String mPlacementId;
    private Boolean isNativeBanner;
    private FacebookAdapterConfiguration mFacebookAdapterConfiguration = new FacebookAdapterConfiguration();

    /* access modifiers changed from: protected */
    public void loadNativeAd(Context context, CustomEventNative.CustomEventNativeListener customEventNativeListener, Map<String, Object> map, Map<String, String> map2) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(customEventNativeListener);
        Preconditions.checkNotNull(map);
        Preconditions.checkNotNull(map2);
        if (!AudienceNetworkAds.isInitialized(context)) {
            AudienceNetworkAds.initialize(context);
        }
        if (extrasAreValid(map2)) {
            String str = map2.get(PLACEMENT_ID_KEY);
            this.mFacebookAdapterConfiguration.setCachedInitializationParameters(context, map2);
            String str2 = map2.get(DataKeys.ADM_KEY);
            if (!map.isEmpty()) {
                Object obj = map.get(NATIVE_BANNER_KEY);
                if (obj instanceof Boolean) {
                    this.isNativeBanner = (Boolean) obj;
                }
            }
            Boolean bool = this.isNativeBanner;
            if (bool == null) {
                bool = FacebookAdapterConfiguration.getNativeBannerPref();
            }
            this.isNativeBanner = bool;
            if (bool == null || !bool.booleanValue()) {
                new FacebookNativeAd(context, new NativeAd(context, str), customEventNativeListener, str2).loadAd();
            } else {
                new FacebookNativeAd(context, new NativeBannerAd(context, str), customEventNativeListener, str2).loadAd();
            }
        } else {
            customEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_NO_FILL);
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(NativeErrorCode.NETWORK_NO_FILL.getIntCode()), NativeErrorCode.NETWORK_NO_FILL);
        }
    }

    private boolean extrasAreValid(Map<String, String> map) {
        String str = map.get(PLACEMENT_ID_KEY);
        mPlacementId = str;
        return !TextUtils.isEmpty(str);
    }

    /* access modifiers changed from: private */
    public static void registerChildViewsForInteraction(View view, NativeAdBase nativeAdBase, MediaView mediaView, MediaView mediaView2) {
        if (nativeAdBase != null) {
            ArrayList arrayList = new ArrayList();
            assembleChildViewsWithLimit(view, arrayList, 10);
            if ((nativeAdBase instanceof NativeAd) && mediaView != null) {
                NativeAd nativeAd = (NativeAd) nativeAdBase;
                if (arrayList.size() == 1) {
                    nativeAd.registerViewForInteraction(view, mediaView, mediaView2);
                } else {
                    nativeAd.registerViewForInteraction(view, mediaView, mediaView2, (List<View>) arrayList);
                }
            } else if (nativeAdBase instanceof NativeBannerAd) {
                NativeBannerAd nativeBannerAd = (NativeBannerAd) nativeAdBase;
                if (arrayList.size() == 1) {
                    nativeBannerAd.registerViewForInteraction(view, mediaView2);
                } else {
                    nativeBannerAd.registerViewForInteraction(view, mediaView2, (List<View>) arrayList);
                }
            }
        }
    }

    private static void assembleChildViewsWithLimit(View view, List<View> list, int i) {
        if (view == null) {
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CUSTOM, "View given is null. Ignoring");
        } else if (i <= 0) {
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CUSTOM, "Depth limit reached; adding this view regardless of its type.");
            list.add(view);
        } else {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (viewGroup.getChildCount() > 0) {
                    for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                        assembleChildViewsWithLimit(viewGroup.getChildAt(i2), list, i - 1);
                    }
                    return;
                }
            }
            list.add(view);
        }
    }

    /* access modifiers changed from: private */
    public static String getAdNetworkId() {
        return mPlacementId;
    }

    static class FacebookNativeAd extends BaseNativeAd implements NativeAdListener {
        private static final String SOCIAL_CONTEXT_FOR_AD = "socialContextForAd";
        private final String mBid;
        private final CustomEventNative.CustomEventNativeListener mCustomEventNativeListener;
        private final Map<String, Object> mExtras = new HashMap();
        private final NativeAdBase mNativeAd;

        public void onMediaDownloaded(Ad ad) {
        }

        public void prepare(View view) {
        }

        FacebookNativeAd(Context context, NativeAdBase nativeAdBase, CustomEventNative.CustomEventNativeListener customEventNativeListener, String str) {
            this.mNativeAd = nativeAdBase;
            this.mCustomEventNativeListener = customEventNativeListener;
            this.mBid = str;
        }

        /* access modifiers changed from: package-private */
        public void loadAd() {
            NativeAdBase.NativeAdLoadConfigBuilder withAdListener = this.mNativeAd.buildLoadAdConfig().withAdListener(this);
            if (!TextUtils.isEmpty(this.mBid)) {
                this.mNativeAd.loadAd(withAdListener.withBid(this.mBid).build());
                MoPubLog.log(FacebookNative.getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, FacebookNative.ADAPTER_NAME);
                return;
            }
            this.mNativeAd.loadAd(withAdListener.build());
            MoPubLog.log(FacebookNative.getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, FacebookNative.ADAPTER_NAME);
        }

        public final String getAdvertiserName() {
            return this.mNativeAd.getAdvertiserName();
        }

        public final String getTitle() {
            return this.mNativeAd.getAdHeadline();
        }

        public final String getText() {
            return this.mNativeAd.getAdBodyText();
        }

        public final String getCallToAction() {
            return this.mNativeAd.getAdCallToAction();
        }

        public final String getSponsoredName() {
            NativeAdBase nativeAdBase = this.mNativeAd;
            if (nativeAdBase instanceof NativeBannerAd) {
                return nativeAdBase.getSponsoredTranslation();
            }
            return null;
        }

        public final String getPrivacyInformationIconClickThroughUrl() {
            return this.mNativeAd.getAdChoicesLinkUrl();
        }

        public void onAdLoaded(Ad ad) {
            if (!this.mNativeAd.equals(ad) || !this.mNativeAd.isAdLoaded()) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.NETWORK_NO_FILL);
                MoPubLog.log(FacebookNative.getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_FAILED, FacebookNative.ADAPTER_NAME, Integer.valueOf(NativeErrorCode.NETWORK_NO_FILL.getIntCode()), NativeErrorCode.NETWORK_NO_FILL);
                return;
            }
            addExtra(SOCIAL_CONTEXT_FOR_AD, this.mNativeAd.getAdSocialContext());
            this.mCustomEventNativeListener.onNativeAdLoaded(this);
            MoPubLog.log(FacebookNative.getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_SUCCESS, FacebookNative.ADAPTER_NAME);
        }

        public void onError(Ad ad, AdError adError) {
            NativeErrorCode nativeErrorCode;
            int errorCode = adError.getErrorCode();
            if (errorCode == 2000) {
                nativeErrorCode = NativeErrorCode.INVALID_RESPONSE;
            } else if (errorCode == 2002) {
                nativeErrorCode = NativeErrorCode.IMAGE_DOWNLOAD_FAILURE;
            } else if (errorCode != 3001) {
                switch (errorCode) {
                    case 1000:
                        nativeErrorCode = NativeErrorCode.CONNECTION_ERROR;
                        break;
                    case 1001:
                        nativeErrorCode = NativeErrorCode.NETWORK_NO_FILL;
                        break;
                    case 1002:
                        nativeErrorCode = NativeErrorCode.NETWORK_INVALID_REQUEST;
                        break;
                    default:
                        nativeErrorCode = NativeErrorCode.UNSPECIFIED;
                        break;
                }
            } else {
                nativeErrorCode = NativeErrorCode.UNEXPECTED_RESPONSE_CODE;
            }
            MoPubLog.log(FacebookNative.getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_FAILED, FacebookNative.ADAPTER_NAME, Integer.valueOf(nativeErrorCode.getIntCode()), nativeErrorCode);
            CustomEventNative.CustomEventNativeListener customEventNativeListener = this.mCustomEventNativeListener;
            if (customEventNativeListener != null) {
                customEventNativeListener.onNativeAdFailed(nativeErrorCode);
            }
        }

        public void onAdClicked(Ad ad) {
            notifyAdClicked();
            MoPubLog.log(FacebookNative.getAdNetworkId(), MoPubLog.AdapterLogEvent.CLICKED, FacebookNative.ADAPTER_NAME);
        }

        public void onLoggingImpression(Ad ad) {
            notifyAdImpressed();
            MoPubLog.log(FacebookNative.getAdNetworkId(), MoPubLog.AdapterLogEvent.SHOW_SUCCESS, FacebookNative.ADAPTER_NAME);
        }

        public void clear(View view) {
            Preconditions.checkNotNull(view);
            this.mNativeAd.unregisterView();
        }

        public void destroy() {
            this.mNativeAd.destroy();
        }

        public final Object getExtra(String str) {
            if (!Preconditions.NoThrow.checkNotNull(str, "getExtra key is not allowed to be null")) {
                return null;
            }
            return this.mExtras.get(str);
        }

        public final Map<String, Object> getExtras() {
            return new HashMap(this.mExtras);
        }

        public final void addExtra(String str, Object obj) {
            if (Preconditions.NoThrow.checkNotNull(str, "addExtra key is not allowed to be null")) {
                this.mExtras.put(str, obj);
            }
        }

        /* access modifiers changed from: package-private */
        public void registerChildViewsForInteraction(View view, MediaView mediaView, MediaView mediaView2) {
            FacebookNative.registerChildViewsForInteraction(view, this.mNativeAd, mediaView, mediaView2);
        }

        /* access modifiers changed from: package-private */
        public NativeAdBase getFacebookNativeAd() {
            return this.mNativeAd;
        }
    }
}
