package com.mopub.mobileads;

import com.mopub.common.CreativeOrientation;
import com.mopub.common.DataKeys;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial;
import java.util.Map;

public class HtmlInterstitial extends ResponseBodyInterstitial {
    public static final String ADAPTER_NAME = HtmlInterstitial.class.getSimpleName();
    private String mClickthroughUrl;
    private String mHtmlData;
    private CreativeOrientation mOrientation;

    /* access modifiers changed from: protected */
    public void extractExtras(Map<String, String> map) {
        this.mHtmlData = map.get(DataKeys.HTML_RESPONSE_BODY_KEY);
        this.mClickthroughUrl = map.get(DataKeys.CLICKTHROUGH_URL_KEY);
        this.mOrientation = CreativeOrientation.fromString(map.get(DataKeys.CREATIVE_ORIENTATION_KEY));
    }

    /* access modifiers changed from: protected */
    public void preRenderHtml(CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener) {
        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
        MoPubActivity.preRenderHtml(this, this.mContext, this.mAdReport, customEventInterstitialListener, this.mClickthroughUrl, this.mBroadcastIdentifier);
    }

    public void showInterstitial() {
        MoPubLog.log(MoPubLog.AdapterLogEvent.SHOW_ATTEMPTED, ADAPTER_NAME);
        MoPubActivity.start(this.mContext, this.mAdReport, this.mClickthroughUrl, this.mOrientation, this.mBroadcastIdentifier);
    }
}
