package com.mopub.mobileads.dfp.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.common.util.Drawables;
import com.mopub.nativeads.NativeImageHelper;
import com.mopub.nativeads.StaticNativeAd;
import java.util.ArrayList;
import java.util.HashMap;

public class MoPubNativeAppInstallAdMapper extends NativeAppInstallAdMapper {
    private StaticNativeAd mMopubNativeAdData;
    private int mPrivacyIconSize;
    private int privacyIconPlacement;
    private ImageView privacyInformationIconImageView;

    public void handleClick(View view) {
    }

    public void recordImpression() {
    }

    public MoPubNativeAppInstallAdMapper(StaticNativeAd staticNativeAd, HashMap<String, Drawable> hashMap, int i, int i2) {
        this.mMopubNativeAdData = staticNativeAd;
        setHeadline(staticNativeAd.getTitle());
        setBody(this.mMopubNativeAdData.getText());
        setCallToAction(this.mMopubNativeAdData.getCallToAction());
        this.privacyIconPlacement = i;
        this.mPrivacyIconSize = i2;
        if (hashMap != null) {
            setIcon(new MoPubNativeMappedImage(hashMap.get(DownloadDrawablesAsync.KEY_ICON), this.mMopubNativeAdData.getIconImageUrl(), 1.0d));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new MoPubNativeMappedImage(hashMap.get(DownloadDrawablesAsync.KEY_IMAGE), this.mMopubNativeAdData.getMainImageUrl(), 1.0d));
            setImages(arrayList);
        } else {
            setIcon(new MoPubNativeMappedImage((Drawable) null, this.mMopubNativeAdData.getIconImageUrl(), 1.0d));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new MoPubNativeMappedImage((Drawable) null, this.mMopubNativeAdData.getMainImageUrl(), 1.0d));
            setImages(arrayList2);
        }
        setOverrideClickHandling(true);
        setOverrideImpressionRecording(true);
    }

    public void untrackView(View view) {
        super.untrackView(view);
        this.mMopubNativeAdData.clear(view);
        ImageView imageView = this.privacyInformationIconImageView;
        if (imageView != null && ((ViewGroup) imageView.getParent()) != null) {
            ((ViewGroup) this.privacyInformationIconImageView.getParent()).removeView(this.privacyInformationIconImageView);
        }
    }

    public void trackView(View view) {
        this.mMopubNativeAdData.prepare(view);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            View childAt = viewGroup.getChildAt(viewGroup.getChildCount() - 1);
            if (childAt instanceof FrameLayout) {
                final Context context = view.getContext();
                if (context != null) {
                    this.privacyInformationIconImageView = new ImageView(context);
                    String privacyInformationIconImageUrl = this.mMopubNativeAdData.getPrivacyInformationIconImageUrl();
                    final String privacyInformationIconClickThroughUrl = this.mMopubNativeAdData.getPrivacyInformationIconClickThroughUrl();
                    if (privacyInformationIconImageUrl == null) {
                        this.privacyInformationIconImageView.setImageDrawable(Drawables.NATIVE_PRIVACY_INFORMATION_ICON.createDrawable(context));
                    } else {
                        NativeImageHelper.loadImageView(privacyInformationIconImageUrl, this.privacyInformationIconImageView);
                    }
                    this.privacyInformationIconImageView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            new UrlHandler.Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).build().handleUrl(context, privacyInformationIconClickThroughUrl);
                        }
                    });
                    this.privacyInformationIconImageView.setVisibility(0);
                    ((ViewGroup) childAt).addView(this.privacyInformationIconImageView);
                    double d = (double) (((float) this.mPrivacyIconSize) * context.getResources().getDisplayMetrics().density);
                    Double.isNaN(d);
                    int i = (int) (d + 0.5d);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i);
                    int i2 = this.privacyIconPlacement;
                    if (i2 == 0) {
                        layoutParams.gravity = 51;
                    } else if (i2 == 1) {
                        layoutParams.gravity = 53;
                    } else if (i2 == 2) {
                        layoutParams.gravity = 85;
                    } else if (i2 != 3) {
                        layoutParams.gravity = 53;
                    } else {
                        layoutParams.gravity = 83;
                    }
                    this.privacyInformationIconImageView.setLayoutParams(layoutParams);
                    viewGroup.requestLayout();
                    return;
                }
                return;
            }
            Log.d(MoPubAdapter.TAG, "Failed to show AdChoices icon.");
        }
    }
}
