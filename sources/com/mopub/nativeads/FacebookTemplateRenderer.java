package com.mopub.nativeads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;
import com.mopub.common.Preconditions;
import com.mopub.nativeads.FacebookNative;

public class FacebookTemplateRenderer implements MoPubAdRenderer<FacebookNative.FacebookNativeAd> {
    private NativeAdViewAttributes mTemplateAttributes;
    private NativeBannerAdView.Type mViewType;

    public FacebookTemplateRenderer(NativeAdViewAttributes nativeAdViewAttributes) {
        this.mTemplateAttributes = nativeAdViewAttributes;
    }

    public FacebookTemplateRenderer(NativeAdViewAttributes nativeAdViewAttributes, NativeBannerAdView.Type type) {
        Preconditions.checkNotNull(type);
        this.mTemplateAttributes = nativeAdViewAttributes;
        this.mViewType = type;
    }

    public View createAdView(Context context, ViewGroup viewGroup) {
        Preconditions.checkNotNull(context);
        return new FrameLayout(context);
    }

    public void renderAdView(View view, FacebookNative.FacebookNativeAd facebookNativeAd) {
        View view2;
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(facebookNativeAd);
        NativeAdBase facebookNativeAd2 = facebookNativeAd.getFacebookNativeAd();
        if (facebookNativeAd2 instanceof NativeAd) {
            view2 = NativeAdView.render(view.getContext(), (NativeAd) facebookNativeAd2, this.mTemplateAttributes);
        } else {
            view2 = facebookNativeAd2 instanceof NativeBannerAd ? NativeBannerAdView.render(view.getContext(), (NativeBannerAd) facebookNativeAd2, this.mViewType, this.mTemplateAttributes) : null;
        }
        ((FrameLayout) view).addView(view2, new FrameLayout.LayoutParams(-2, -2));
    }

    public boolean supports(BaseNativeAd baseNativeAd) {
        Preconditions.checkNotNull(baseNativeAd);
        return baseNativeAd instanceof FacebookNative.FacebookNativeAd;
    }
}
