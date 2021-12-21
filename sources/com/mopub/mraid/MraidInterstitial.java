package com.mopub.mraid;

import com.mopub.common.CreativeOrientation;
import com.mopub.common.DataKeys;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.MraidActivity;
import com.mopub.mobileads.ResponseBodyInterstitial;
import java.util.Map;

class MraidInterstitial extends ResponseBodyInterstitial {
    public static final String ADAPTER_NAME = MraidInterstitial.class.getSimpleName();
    protected String mHtmlData;
    private CreativeOrientation mOrientation;

    MraidInterstitial() {
    }

    /* access modifiers changed from: protected */
    public void extractExtras(Map<String, String> map) {
        this.mHtmlData = map.get(DataKeys.HTML_RESPONSE_BODY_KEY);
        this.mOrientation = CreativeOrientation.fromString(map.get(DataKeys.CREATIVE_ORIENTATION_KEY));
    }

    /* access modifiers changed from: protected */
    public void preRenderHtml(CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener) {
        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
        MraidActivity.preRenderHtml(this, this.mContext, customEventInterstitialListener, Long.valueOf(this.mBroadcastIdentifier), this.mAdReport);
    }

    public void showInterstitial() {
        MoPubLog.log(MoPubLog.AdapterLogEvent.SHOW_ATTEMPTED, ADAPTER_NAME);
        MraidActivity.start(this.mContext, this.mAdReport, this.mBroadcastIdentifier, this.mOrientation);
    }
}
