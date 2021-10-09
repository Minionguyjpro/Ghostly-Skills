package com.mopub.nativeads;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.formats.AdChoicesView;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.GooglePlayServicesNative;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;

public class GooglePlayServicesAdRenderer implements MoPubAdRenderer<GooglePlayServicesNative.GooglePlayServicesNativeAd> {
    private static final int ID_GOOGLE_NATIVE_VIEW = 1002;
    private static final int ID_WRAPPING_FRAME = 1001;
    public static final String VIEW_BINDER_KEY_ADVERTISER = "key_advertiser";
    public static final String VIEW_BINDER_KEY_AD_CHOICES_ICON_CONTAINER = "ad_choices_container";
    public static final String VIEW_BINDER_KEY_PRICE = "key_price";
    public static final String VIEW_BINDER_KEY_STAR_RATING = "key_star_rating";
    public static final String VIEW_BINDER_KEY_STORE = "key_store";
    private final ViewBinder mViewBinder;
    private final WeakHashMap<View, GoogleStaticNativeViewHolder> mViewHolderMap = new WeakHashMap<>();

    public GooglePlayServicesAdRenderer(ViewBinder viewBinder) {
        this.mViewBinder = viewBinder;
    }

    public View createAdView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(this.mViewBinder.layoutId, viewGroup, false);
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setId(1001);
        frameLayout.addView(inflate);
        Log.i("MoPubToAdMobNative", "Ad view created.");
        return frameLayout;
    }

    public void renderAdView(View view, GooglePlayServicesNative.GooglePlayServicesNativeAd googlePlayServicesNativeAd) {
        GoogleStaticNativeViewHolder googleStaticNativeViewHolder = this.mViewHolderMap.get(view);
        if (googleStaticNativeViewHolder == null) {
            googleStaticNativeViewHolder = GoogleStaticNativeViewHolder.fromViewBinder(view, this.mViewBinder);
            this.mViewHolderMap.put(view, googleStaticNativeViewHolder);
        }
        removeGoogleNativeAdView(view, googlePlayServicesNativeAd.shouldSwapMargins());
        NativeAdView nativeAdView = null;
        if (googlePlayServicesNativeAd.isNativeAppInstallAd()) {
            nativeAdView = new NativeAppInstallAdView(view.getContext());
            updateAppInstallAdView(googlePlayServicesNativeAd, googleStaticNativeViewHolder, (NativeAppInstallAdView) nativeAdView);
        } else if (googlePlayServicesNativeAd.isNativeContentAd()) {
            nativeAdView = new NativeContentAdView(view.getContext());
            updateContentAdView(googlePlayServicesNativeAd, googleStaticNativeViewHolder, (NativeContentAdView) nativeAdView);
        }
        if (nativeAdView != null) {
            insertGoogleNativeAdView(nativeAdView, view, googlePlayServicesNativeAd.shouldSwapMargins());
        } else {
            Log.w("MoPubToAdMobNative", "Couldn't add Google native ad view. NativeAdView is null.");
        }
    }

    private static void insertGoogleNativeAdView(NativeAdView nativeAdView, View view, boolean z) {
        if (!(view instanceof FrameLayout) || view.getId() != 1001) {
            Log.w("MoPubToAdMobNative", "Couldn't add Google native ad view. Wrapping view not found.");
            return;
        }
        nativeAdView.setId(1002);
        FrameLayout frameLayout = (FrameLayout) view;
        View childAt = frameLayout.getChildAt(0);
        if (z) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            layoutParams.setMargins(layoutParams2.leftMargin, layoutParams2.topMargin, layoutParams2.rightMargin, layoutParams2.bottomMargin);
            nativeAdView.setLayoutParams(layoutParams);
            layoutParams2.setMargins(0, 0, 0, 0);
        } else {
            nativeAdView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        }
        frameLayout.removeView(childAt);
        nativeAdView.addView(childAt);
        frameLayout.addView(nativeAdView);
    }

    protected static void removeGoogleNativeAdView(View view, boolean z) {
        View findViewById;
        if ((view instanceof FrameLayout) && view.getId() == 1001 && (findViewById = view.findViewById(1002)) != null) {
            ViewGroup viewGroup = (ViewGroup) view;
            int indexOfChild = viewGroup.indexOfChild(findViewById);
            viewGroup.removeView(findViewById);
            ViewGroup viewGroup2 = (ViewGroup) findViewById;
            View childAt = viewGroup2.getChildAt(0);
            if (childAt != null) {
                if (z) {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) findViewById.getLayoutParams();
                    ((FrameLayout.LayoutParams) childAt.getLayoutParams()).setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
                }
                viewGroup2.removeView(childAt);
                viewGroup.addView(childAt, indexOfChild);
            }
            if (findViewById instanceof NativeAdView) {
                ((NativeAdView) findViewById).destroy();
            }
        }
    }

    private void updateContentAdView(GooglePlayServicesNative.GooglePlayServicesNativeAd googlePlayServicesNativeAd, GoogleStaticNativeViewHolder googleStaticNativeViewHolder, NativeContentAdView nativeContentAdView) {
        NativeRendererHelper.addTextView(googleStaticNativeViewHolder.mTitleView, googlePlayServicesNativeAd.getTitle());
        nativeContentAdView.setHeadlineView(googleStaticNativeViewHolder.mTitleView);
        NativeRendererHelper.addTextView(googleStaticNativeViewHolder.mTextView, googlePlayServicesNativeAd.getText());
        nativeContentAdView.setBodyView(googleStaticNativeViewHolder.mTextView);
        NativeRendererHelper.addTextView(googleStaticNativeViewHolder.mCallToActionView, googlePlayServicesNativeAd.getCallToAction());
        nativeContentAdView.setCallToActionView(googleStaticNativeViewHolder.mCallToActionView);
        NativeImageHelper.loadImageView(googlePlayServicesNativeAd.getMainImageUrl(), googleStaticNativeViewHolder.mMainImageView);
        nativeContentAdView.setImageView(googleStaticNativeViewHolder.mMainImageView);
        NativeImageHelper.loadImageView(googlePlayServicesNativeAd.getIconImageUrl(), googleStaticNativeViewHolder.mIconImageView);
        nativeContentAdView.setLogoView(googleStaticNativeViewHolder.mIconImageView);
        if (googlePlayServicesNativeAd.getAdvertiser() != null) {
            NativeRendererHelper.addTextView(googleStaticNativeViewHolder.mAdvertiserTextView, googlePlayServicesNativeAd.getAdvertiser());
            nativeContentAdView.setAdvertiserView(googleStaticNativeViewHolder.mAdvertiserTextView);
        }
        if (googleStaticNativeViewHolder.mAdChoicesIconContainer != null) {
            AdChoicesView adChoicesView = new AdChoicesView(nativeContentAdView.getContext());
            googleStaticNativeViewHolder.mAdChoicesIconContainer.removeAllViews();
            googleStaticNativeViewHolder.mAdChoicesIconContainer.addView(adChoicesView);
            nativeContentAdView.setAdChoicesView(adChoicesView);
        }
        NativeRendererHelper.addPrivacyInformationIcon(googleStaticNativeViewHolder.mPrivacyInformationIconImageView, (String) null, (String) null);
        nativeContentAdView.setNativeAd(googlePlayServicesNativeAd.getContentAd());
    }

    private void updateAppInstallAdView(GooglePlayServicesNative.GooglePlayServicesNativeAd googlePlayServicesNativeAd, GoogleStaticNativeViewHolder googleStaticNativeViewHolder, NativeAppInstallAdView nativeAppInstallAdView) {
        NativeRendererHelper.addTextView(googleStaticNativeViewHolder.mTitleView, googlePlayServicesNativeAd.getTitle());
        nativeAppInstallAdView.setHeadlineView(googleStaticNativeViewHolder.mTitleView);
        NativeRendererHelper.addTextView(googleStaticNativeViewHolder.mTextView, googlePlayServicesNativeAd.getText());
        nativeAppInstallAdView.setBodyView(googleStaticNativeViewHolder.mTextView);
        NativeRendererHelper.addTextView(googleStaticNativeViewHolder.mCallToActionView, googlePlayServicesNativeAd.getCallToAction());
        nativeAppInstallAdView.setCallToActionView(googleStaticNativeViewHolder.mCallToActionView);
        NativeImageHelper.loadImageView(googlePlayServicesNativeAd.getMainImageUrl(), googleStaticNativeViewHolder.mMainImageView);
        nativeAppInstallAdView.setImageView(googleStaticNativeViewHolder.mMainImageView);
        NativeImageHelper.loadImageView(googlePlayServicesNativeAd.getIconImageUrl(), googleStaticNativeViewHolder.mIconImageView);
        nativeAppInstallAdView.setIconView(googleStaticNativeViewHolder.mIconImageView);
        if (googlePlayServicesNativeAd.getStarRating() != null) {
            NativeRendererHelper.addTextView(googleStaticNativeViewHolder.mStarRatingTextView, String.format(Locale.getDefault(), "%.1f/5 Stars", new Object[]{googlePlayServicesNativeAd.getStarRating()}));
            nativeAppInstallAdView.setStarRatingView(googleStaticNativeViewHolder.mStarRatingTextView);
        }
        if (googlePlayServicesNativeAd.getPrice() != null) {
            NativeRendererHelper.addTextView(googleStaticNativeViewHolder.mPriceTextView, googlePlayServicesNativeAd.getPrice());
            nativeAppInstallAdView.setPriceView(googleStaticNativeViewHolder.mPriceTextView);
        }
        if (googlePlayServicesNativeAd.getStore() != null) {
            NativeRendererHelper.addTextView(googleStaticNativeViewHolder.mStoreTextView, googlePlayServicesNativeAd.getStore());
            nativeAppInstallAdView.setStoreView(googleStaticNativeViewHolder.mStoreTextView);
        }
        NativeRendererHelper.addPrivacyInformationIcon(googleStaticNativeViewHolder.mPrivacyInformationIconImageView, (String) null, (String) null);
        if (googleStaticNativeViewHolder.mAdChoicesIconContainer != null) {
            AdChoicesView adChoicesView = new AdChoicesView(nativeAppInstallAdView.getContext());
            googleStaticNativeViewHolder.mAdChoicesIconContainer.removeAllViews();
            googleStaticNativeViewHolder.mAdChoicesIconContainer.addView(adChoicesView);
            nativeAppInstallAdView.setAdChoicesView(adChoicesView);
        }
        nativeAppInstallAdView.setNativeAd(googlePlayServicesNativeAd.getAppInstallAd());
    }

    public boolean supports(BaseNativeAd baseNativeAd) {
        return baseNativeAd instanceof GooglePlayServicesNative.GooglePlayServicesNativeAd;
    }

    private static class GoogleStaticNativeViewHolder {
        private static final GoogleStaticNativeViewHolder EMPTY_VIEW_HOLDER = new GoogleStaticNativeViewHolder();
        FrameLayout mAdChoicesIconContainer;
        TextView mAdvertiserTextView;
        TextView mCallToActionView;
        ImageView mIconImageView;
        ImageView mMainImageView;
        View mMainView;
        TextView mPriceTextView;
        ImageView mPrivacyInformationIconImageView;
        TextView mStarRatingTextView;
        TextView mStoreTextView;
        TextView mTextView;
        TextView mTitleView;

        private GoogleStaticNativeViewHolder() {
        }

        public static GoogleStaticNativeViewHolder fromViewBinder(View view, ViewBinder viewBinder) {
            GoogleStaticNativeViewHolder googleStaticNativeViewHolder = new GoogleStaticNativeViewHolder();
            googleStaticNativeViewHolder.mMainView = view;
            try {
                googleStaticNativeViewHolder.mTitleView = (TextView) view.findViewById(viewBinder.titleId);
                googleStaticNativeViewHolder.mTextView = (TextView) view.findViewById(viewBinder.textId);
                googleStaticNativeViewHolder.mCallToActionView = (TextView) view.findViewById(viewBinder.callToActionId);
                googleStaticNativeViewHolder.mMainImageView = (ImageView) view.findViewById(viewBinder.mainImageId);
                googleStaticNativeViewHolder.mIconImageView = (ImageView) view.findViewById(viewBinder.iconImageId);
                googleStaticNativeViewHolder.mPrivacyInformationIconImageView = (ImageView) view.findViewById(viewBinder.privacyInformationIconImageId);
                Map<String, Integer> map = viewBinder.extras;
                Integer num = map.get(GooglePlayServicesAdRenderer.VIEW_BINDER_KEY_STAR_RATING);
                if (num != null) {
                    googleStaticNativeViewHolder.mStarRatingTextView = (TextView) view.findViewById(num.intValue());
                }
                Integer num2 = map.get(GooglePlayServicesAdRenderer.VIEW_BINDER_KEY_ADVERTISER);
                if (num2 != null) {
                    googleStaticNativeViewHolder.mAdvertiserTextView = (TextView) view.findViewById(num2.intValue());
                }
                Integer num3 = map.get(GooglePlayServicesAdRenderer.VIEW_BINDER_KEY_STORE);
                if (num3 != null) {
                    googleStaticNativeViewHolder.mStoreTextView = (TextView) view.findViewById(num3.intValue());
                }
                Integer num4 = map.get(GooglePlayServicesAdRenderer.VIEW_BINDER_KEY_PRICE);
                if (num4 != null) {
                    googleStaticNativeViewHolder.mPriceTextView = (TextView) view.findViewById(num4.intValue());
                }
                Integer num5 = map.get(GooglePlayServicesAdRenderer.VIEW_BINDER_KEY_AD_CHOICES_ICON_CONTAINER);
                if (num5 != null) {
                    googleStaticNativeViewHolder.mAdChoicesIconContainer = (FrameLayout) view.findViewById(num5.intValue());
                }
                return googleStaticNativeViewHolder;
            } catch (ClassCastException e) {
                MoPubLog.w("Could not cast from id in ViewBinder to expected View type", e);
                return EMPTY_VIEW_HOLDER;
            }
        }
    }
}
