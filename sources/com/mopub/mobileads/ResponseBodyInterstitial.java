package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial;
import java.util.Map;

public abstract class ResponseBodyInterstitial extends CustomEventInterstitial {
    public static final String ADAPTER_NAME = ResponseBodyInterstitial.class.getSimpleName();
    protected AdReport mAdReport;
    protected long mBroadcastIdentifier;
    private EventForwardingBroadcastReceiver mBroadcastReceiver;
    protected Context mContext;
    protected ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    protected Map<String, Object> mLocalExtras;

    /* access modifiers changed from: protected */
    public abstract void extractExtras(Map<String, String> map);

    /* access modifiers changed from: protected */
    public abstract void preRenderHtml(CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener);

    public abstract void showInterstitial();

    public void loadInterstitial(Context context, CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2) {
        MoPubLog.log(MoPubLog.AdLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
        this.mContext = context;
        this.mLocalExtras = map;
        if (extrasAreValid(map2)) {
            extractExtras(map2);
            try {
                this.mAdReport = (AdReport) map.get(DataKeys.AD_REPORT_KEY);
                Long l = (Long) map.get(DataKeys.BROADCAST_IDENTIFIER_KEY);
                if (l == null) {
                    MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Broadcast Identifier was not set in localExtras");
                    MoPubLog.log(MoPubLog.AdLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.INTERNAL_ERROR.getIntCode()), MoPubErrorCode.INTERNAL_ERROR);
                    customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
                    return;
                }
                this.mBroadcastIdentifier = l.longValue();
                EventForwardingBroadcastReceiver eventForwardingBroadcastReceiver = new EventForwardingBroadcastReceiver(customEventInterstitialListener, this.mBroadcastIdentifier);
                this.mBroadcastReceiver = eventForwardingBroadcastReceiver;
                eventForwardingBroadcastReceiver.register(eventForwardingBroadcastReceiver, context);
                preRenderHtml(customEventInterstitialListener);
                MoPubLog.log(MoPubLog.AdLogEvent.LOAD_SUCCESS, ADAPTER_NAME);
            } catch (ClassCastException unused) {
                MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "LocalExtras contained an incorrect type.");
                MoPubLog.log(MoPubLog.AdLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.INTERNAL_ERROR.getIntCode()), MoPubErrorCode.INTERNAL_ERROR);
                customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        } else {
            MoPubLog.log(MoPubLog.AdLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.NETWORK_INVALID_STATE.getIntCode()), MoPubErrorCode.NETWORK_INVALID_STATE);
            customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
        }
    }

    public void onInvalidate() {
        EventForwardingBroadcastReceiver eventForwardingBroadcastReceiver = this.mBroadcastReceiver;
        if (eventForwardingBroadcastReceiver != null) {
            eventForwardingBroadcastReceiver.unregister(eventForwardingBroadcastReceiver);
            this.mBroadcastReceiver = null;
        }
    }

    private boolean extrasAreValid(Map<String, String> map) {
        return map.containsKey(DataKeys.HTML_RESPONSE_BODY_KEY);
    }
}
