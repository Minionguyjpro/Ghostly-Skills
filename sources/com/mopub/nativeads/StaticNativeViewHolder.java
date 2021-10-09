package com.mopub.nativeads;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mopub.common.logging.MoPubLog;

class StaticNativeViewHolder {
    static final StaticNativeViewHolder EMPTY_VIEW_HOLDER = new StaticNativeViewHolder();
    TextView callToActionView;
    ImageView iconImageView;
    ImageView mainImageView;
    View mainView;
    ImageView privacyInformationIconImageView;
    TextView sponsoredTextView;
    TextView textView;
    TextView titleView;

    private StaticNativeViewHolder() {
    }

    static StaticNativeViewHolder fromViewBinder(View view, ViewBinder viewBinder) {
        StaticNativeViewHolder staticNativeViewHolder = new StaticNativeViewHolder();
        staticNativeViewHolder.mainView = view;
        try {
            staticNativeViewHolder.titleView = (TextView) view.findViewById(viewBinder.titleId);
            staticNativeViewHolder.textView = (TextView) view.findViewById(viewBinder.textId);
            staticNativeViewHolder.callToActionView = (TextView) view.findViewById(viewBinder.callToActionId);
            staticNativeViewHolder.mainImageView = (ImageView) view.findViewById(viewBinder.mainImageId);
            staticNativeViewHolder.iconImageView = (ImageView) view.findViewById(viewBinder.iconImageId);
            staticNativeViewHolder.privacyInformationIconImageView = (ImageView) view.findViewById(viewBinder.privacyInformationIconImageId);
            staticNativeViewHolder.sponsoredTextView = (TextView) view.findViewById(viewBinder.sponsoredTextId);
            return staticNativeViewHolder;
        } catch (ClassCastException e) {
            MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, "Could not cast from id in ViewBinder to expected View type", e);
            return EMPTY_VIEW_HOLDER;
        }
    }
}
