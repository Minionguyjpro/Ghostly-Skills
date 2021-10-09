package com.mopub.mobileads;

import android.text.TextUtils;
import com.mopub.common.CacheService;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.DataKeys;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Json;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.VastManager;
import com.mopub.mobileads.factories.VastManagerFactory;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class VastVideoInterstitial extends ResponseBodyInterstitial implements VastManager.VastManagerListener {
    public static final String ADAPTER_NAME = VastVideoInterstitial.class.getSimpleName();
    private CustomEventInterstitial.CustomEventInterstitialListener mCustomEventInterstitialListener;
    private Map<String, String> mExternalViewabilityTrackers;
    private CreativeOrientation mOrientation;
    private VastManager mVastManager;
    private String mVastResponse;
    private VastVideoConfig mVastVideoConfig;
    private JSONObject mVideoTrackers;

    VastVideoInterstitial() {
    }

    /* access modifiers changed from: protected */
    public void extractExtras(Map<String, String> map) {
        this.mVastResponse = map.get(DataKeys.HTML_RESPONSE_BODY_KEY);
        this.mOrientation = CreativeOrientation.fromString(map.get(DataKeys.CREATIVE_ORIENTATION_KEY));
        String str = map.get(DataKeys.EXTERNAL_VIDEO_VIEWABILITY_TRACKERS_KEY);
        try {
            this.mExternalViewabilityTrackers = Json.jsonStringToMap(str);
        } catch (JSONException unused) {
            MoPubLog.AdapterLogEvent adapterLogEvent = MoPubLog.AdapterLogEvent.CUSTOM;
            MoPubLog.log(adapterLogEvent, "Failed to parse video viewability trackers to JSON: " + str);
        }
        String str2 = map.get(DataKeys.VIDEO_TRACKERS_KEY);
        if (!TextUtils.isEmpty(str2)) {
            try {
                this.mVideoTrackers = new JSONObject(str2);
            } catch (JSONException e) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE;
                MoPubLog.log(sdkLogEvent, "Failed to parse video trackers to JSON: " + str2, e);
                this.mVideoTrackers = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void preRenderHtml(CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener) {
        this.mCustomEventInterstitialListener = customEventInterstitialListener;
        if (!CacheService.initializeDiskCache(this.mContext)) {
            MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.VIDEO_CACHE_ERROR.getIntCode()), MoPubErrorCode.VIDEO_CACHE_ERROR);
            this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.VIDEO_CACHE_ERROR);
            return;
        }
        VastManager create = VastManagerFactory.create(this.mContext);
        this.mVastManager = create;
        create.prepareVastVideoConfiguration(this.mVastResponse, this, this.mAdReport.getDspCreativeId(), this.mContext);
        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_SUCCESS, ADAPTER_NAME);
    }

    public void showInterstitial() {
        MoPubLog.log(MoPubLog.AdapterLogEvent.SHOW_ATTEMPTED, ADAPTER_NAME);
        MraidVideoPlayerActivity.startVast(this.mContext, this.mVastVideoConfig, this.mBroadcastIdentifier, this.mOrientation);
    }

    public void onInvalidate() {
        VastManager vastManager = this.mVastManager;
        if (vastManager != null) {
            vastManager.cancel();
        }
        super.onInvalidate();
    }

    public void onVastVideoConfigurationPrepared(VastVideoConfig vastVideoConfig) {
        if (vastVideoConfig == null) {
            this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.VIDEO_DOWNLOAD_ERROR);
            return;
        }
        this.mVastVideoConfig = vastVideoConfig;
        vastVideoConfig.addVideoTrackers(this.mVideoTrackers);
        this.mVastVideoConfig.addExternalViewabilityTrackers(this.mExternalViewabilityTrackers);
        this.mCustomEventInterstitialListener.onInterstitialLoaded();
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public String getVastResponse() {
        return this.mVastResponse;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setVastManager(VastManager vastManager) {
        this.mVastManager = vastManager;
    }
}
