package com.mopub.nativeads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.Preconditions;
import java.util.WeakHashMap;

public class MoPubStaticNativeAdRenderer implements MoPubAdRenderer<StaticNativeAd> {
    private final ViewBinder mViewBinder;
    final WeakHashMap<View, StaticNativeViewHolder> mViewHolderMap = new WeakHashMap<>();

    public MoPubStaticNativeAdRenderer(ViewBinder viewBinder) {
        this.mViewBinder = viewBinder;
    }

    public View createAdView(Context context, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(this.mViewBinder.layoutId, viewGroup, false);
    }

    public void renderAdView(View view, StaticNativeAd staticNativeAd) {
        StaticNativeViewHolder staticNativeViewHolder = this.mViewHolderMap.get(view);
        if (staticNativeViewHolder == null) {
            staticNativeViewHolder = StaticNativeViewHolder.fromViewBinder(view, this.mViewBinder);
            this.mViewHolderMap.put(view, staticNativeViewHolder);
        }
        update(staticNativeViewHolder, staticNativeAd);
        NativeRendererHelper.updateExtras(staticNativeViewHolder.mainView, this.mViewBinder.extras, staticNativeAd.getExtras());
        setViewVisibility(staticNativeViewHolder, 0);
    }

    public boolean supports(BaseNativeAd baseNativeAd) {
        Preconditions.checkNotNull(baseNativeAd);
        return baseNativeAd instanceof StaticNativeAd;
    }

    private void update(StaticNativeViewHolder staticNativeViewHolder, StaticNativeAd staticNativeAd) {
        NativeRendererHelper.addTextView(staticNativeViewHolder.titleView, staticNativeAd.getTitle());
        NativeRendererHelper.addTextView(staticNativeViewHolder.textView, staticNativeAd.getText());
        NativeRendererHelper.addTextView(staticNativeViewHolder.callToActionView, staticNativeAd.getCallToAction());
        NativeImageHelper.loadImageView(staticNativeAd.getMainImageUrl(), staticNativeViewHolder.mainImageView);
        NativeImageHelper.loadImageView(staticNativeAd.getIconImageUrl(), staticNativeViewHolder.iconImageView);
        NativeRendererHelper.addPrivacyInformationIcon(staticNativeViewHolder.privacyInformationIconImageView, staticNativeAd.getPrivacyInformationIconImageUrl(), staticNativeAd.getPrivacyInformationIconClickThroughUrl());
        NativeRendererHelper.addSponsoredView(staticNativeAd.getSponsored(), staticNativeViewHolder.sponsoredTextView);
    }

    private void setViewVisibility(StaticNativeViewHolder staticNativeViewHolder, int i) {
        if (staticNativeViewHolder.mainView != null) {
            staticNativeViewHolder.mainView.setVisibility(i);
        }
    }
}
