package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mopub.common.Constants;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.network.TrackingRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class VastVideoConfig implements Serializable {
    private static final long serialVersionUID = 2;
    @SerializedName("absolute_trackers")
    @Expose
    private final ArrayList<VastAbsoluteProgressTracker> mAbsoluteTrackers = new ArrayList<>();
    @SerializedName("avid_javascript_resources")
    @Expose
    private final Set<String> mAvidJavascriptResources = new HashSet();
    @SerializedName("clickthrough_url")
    @Expose
    private String mClickThroughUrl;
    @SerializedName("click_trackers")
    @Expose
    private final ArrayList<VastTracker> mClickTrackers = new ArrayList<>();
    @SerializedName("close_trackers")
    @Expose
    private final ArrayList<VastTracker> mCloseTrackers = new ArrayList<>();
    @SerializedName("complete_trackers")
    @Expose
    private final ArrayList<VastTracker> mCompleteTrackers = new ArrayList<>();
    @SerializedName("custom_close_icon_url")
    @Expose
    private String mCustomCloseIconUrl;
    @SerializedName("custom_cta_text")
    @Expose
    private String mCustomCtaText;
    @SerializedName("custom_skip_text")
    @Expose
    private String mCustomSkipText;
    @SerializedName("disk_media_file_url")
    @Expose
    private String mDiskMediaFileUrl;
    /* access modifiers changed from: private */
    @SerializedName("dsp_creative_id")
    @Expose
    public String mDspCreativeId;
    @SerializedName("error_trackers")
    @Expose
    private final ArrayList<VastTracker> mErrorTrackers = new ArrayList<>();
    @SerializedName("external_viewability_trackers")
    @Expose
    private final Map<String, String> mExternalViewabilityTrackers = new HashMap();
    @SerializedName("fractional_trackers")
    @Expose
    private final ArrayList<VastFractionalProgressTracker> mFractionalTrackers = new ArrayList<>();
    @SerializedName("impression_trackers")
    @Expose
    private final ArrayList<VastTracker> mImpressionTrackers = new ArrayList<>();
    @SerializedName("is_rewarded")
    @Expose
    private boolean mIsRewardedVideo = false;
    @SerializedName("landscape_companion_ad")
    @Expose
    private VastCompanionAdConfig mLandscapeVastCompanionAdConfig;
    @SerializedName("moat_impression_pixels")
    @Expose
    private final Set<String> mMoatImpressionPixels = new HashSet();
    @SerializedName("network_media_file_url")
    @Expose
    private String mNetworkMediaFileUrl;
    @SerializedName("pause_trackers")
    @Expose
    private final ArrayList<VastTracker> mPauseTrackers = new ArrayList<>();
    @SerializedName("portrait_companion_ad")
    @Expose
    private VastCompanionAdConfig mPortraitVastCompanionAdConfig;
    @SerializedName("privacy_icon_click_url")
    @Expose
    private String mPrivacyInformationIconClickthroughUrl;
    @SerializedName("privacy_icon_image_url")
    @Expose
    private String mPrivacyInformationIconImageUrl;
    @SerializedName("resume_trackers")
    @Expose
    private final ArrayList<VastTracker> mResumeTrackers = new ArrayList<>();
    @SerializedName("skip_offset")
    @Expose
    private String mSkipOffset;
    @SerializedName("skip_trackers")
    @Expose
    private final ArrayList<VastTracker> mSkipTrackers = new ArrayList<>();
    @SerializedName("icon_config")
    @Expose
    private VastIconConfig mVastIconConfig;
    @SerializedName("video_viewability_tracker")
    @Expose
    private VideoViewabilityTracker mVideoViewabilityTracker;

    public void setDspCreativeId(String str) {
        this.mDspCreativeId = str;
    }

    public String getDspCreativeId() {
        return this.mDspCreativeId;
    }

    public void addImpressionTrackers(List<VastTracker> list) {
        Preconditions.checkNotNull(list, "impressionTrackers cannot be null");
        this.mImpressionTrackers.addAll(list);
    }

    public void addFractionalTrackers(List<VastFractionalProgressTracker> list) {
        Preconditions.checkNotNull(list, "fractionalTrackers cannot be null");
        this.mFractionalTrackers.addAll(list);
        Collections.sort(this.mFractionalTrackers);
    }

    public void addAbsoluteTrackers(List<VastAbsoluteProgressTracker> list) {
        Preconditions.checkNotNull(list, "absoluteTrackers cannot be null");
        this.mAbsoluteTrackers.addAll(list);
        Collections.sort(this.mAbsoluteTrackers);
    }

    public void addCompleteTrackers(List<VastTracker> list) {
        Preconditions.checkNotNull(list, "completeTrackers cannot be null");
        this.mCompleteTrackers.addAll(list);
    }

    public void addPauseTrackers(List<VastTracker> list) {
        Preconditions.checkNotNull(list, "pauseTrackers cannot be null");
        this.mPauseTrackers.addAll(list);
    }

    public void addResumeTrackers(List<VastTracker> list) {
        Preconditions.checkNotNull(list, "resumeTrackers cannot be null");
        this.mResumeTrackers.addAll(list);
    }

    public void addCloseTrackers(List<VastTracker> list) {
        Preconditions.checkNotNull(list, "closeTrackers cannot be null");
        this.mCloseTrackers.addAll(list);
    }

    public void addSkipTrackers(List<VastTracker> list) {
        Preconditions.checkNotNull(list, "skipTrackers cannot be null");
        this.mSkipTrackers.addAll(list);
    }

    public void addClickTrackers(List<VastTracker> list) {
        Preconditions.checkNotNull(list, "clickTrackers cannot be null");
        this.mClickTrackers.addAll(list);
    }

    public void addErrorTrackers(List<VastTracker> list) {
        Preconditions.checkNotNull(list, "errorTrackers cannot be null");
        this.mErrorTrackers.addAll(list);
    }

    public void addVideoTrackers(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray(Constants.VIDEO_TRACKING_URLS_KEY);
            JSONArray optJSONArray2 = jSONObject.optJSONArray(Constants.VIDEO_TRACKING_EVENTS_KEY);
            if (optJSONArray != null && optJSONArray2 != null) {
                for (int i = 0; i < optJSONArray2.length(); i++) {
                    String optString = optJSONArray2.optString(i);
                    List<String> hydrateUrls = hydrateUrls(optString, optJSONArray);
                    VideoTrackingEvent fromString = VideoTrackingEvent.Companion.fromString(optString);
                    if (!(optString == null || hydrateUrls == null)) {
                        switch (AnonymousClass2.$SwitchMap$com$mopub$mobileads$VideoTrackingEvent[fromString.ordinal()]) {
                            case 1:
                                addStartTrackersForUrls(hydrateUrls);
                                break;
                            case 2:
                                addFractionalTrackersForUrls(hydrateUrls, 0.25f);
                                break;
                            case 3:
                                addFractionalTrackersForUrls(hydrateUrls, 0.5f);
                                break;
                            case 4:
                                addFractionalTrackersForUrls(hydrateUrls, 0.75f);
                                break;
                            case 5:
                                addCompleteTrackersForUrls(hydrateUrls);
                                break;
                            case 6:
                                addCompanionAdViewTrackersForUrls(hydrateUrls);
                                break;
                            case 7:
                                addCompanionAdClickTrackersForUrls(hydrateUrls);
                                break;
                            default:
                                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                                MoPubLog.log(sdkLogEvent, "Encountered unknown video tracking event: " + optString);
                                break;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: com.mopub.mobileads.VastVideoConfig$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$mobileads$VideoTrackingEvent;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.mopub.mobileads.VideoTrackingEvent[] r0 = com.mopub.mobileads.VideoTrackingEvent.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$mobileads$VideoTrackingEvent = r0
                com.mopub.mobileads.VideoTrackingEvent r1 = com.mopub.mobileads.VideoTrackingEvent.START     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$mobileads$VideoTrackingEvent     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.mobileads.VideoTrackingEvent r1 = com.mopub.mobileads.VideoTrackingEvent.FIRST_QUARTILE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mopub$mobileads$VideoTrackingEvent     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.mobileads.VideoTrackingEvent r1 = com.mopub.mobileads.VideoTrackingEvent.MIDPOINT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$mopub$mobileads$VideoTrackingEvent     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.mopub.mobileads.VideoTrackingEvent r1 = com.mopub.mobileads.VideoTrackingEvent.THIRD_QUARTILE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$mopub$mobileads$VideoTrackingEvent     // Catch:{ NoSuchFieldError -> 0x003e }
                com.mopub.mobileads.VideoTrackingEvent r1 = com.mopub.mobileads.VideoTrackingEvent.COMPLETE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$mopub$mobileads$VideoTrackingEvent     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.mopub.mobileads.VideoTrackingEvent r1 = com.mopub.mobileads.VideoTrackingEvent.COMPANION_AD_VIEW     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$mopub$mobileads$VideoTrackingEvent     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.mopub.mobileads.VideoTrackingEvent r1 = com.mopub.mobileads.VideoTrackingEvent.COMPANION_AD_CLICK     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$mopub$mobileads$VideoTrackingEvent     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.mopub.mobileads.VideoTrackingEvent r1 = com.mopub.mobileads.VideoTrackingEvent.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.VastVideoConfig.AnonymousClass2.<clinit>():void");
        }
    }

    public void addExternalViewabilityTrackers(Map<String, String> map) {
        if (map != null) {
            this.mExternalViewabilityTrackers.putAll(map);
        }
    }

    public void addAvidJavascriptResources(Set<String> set) {
        if (set != null) {
            this.mAvidJavascriptResources.addAll(set);
        }
    }

    public void addMoatImpressionPixels(Set<String> set) {
        if (set != null) {
            this.mMoatImpressionPixels.addAll(set);
        }
    }

    public void setClickThroughUrl(String str) {
        this.mClickThroughUrl = str;
    }

    public void setNetworkMediaFileUrl(String str) {
        this.mNetworkMediaFileUrl = str;
    }

    public void setDiskMediaFileUrl(String str) {
        this.mDiskMediaFileUrl = str;
    }

    public void setVastCompanionAd(VastCompanionAdConfig vastCompanionAdConfig, VastCompanionAdConfig vastCompanionAdConfig2) {
        this.mLandscapeVastCompanionAdConfig = vastCompanionAdConfig;
        this.mPortraitVastCompanionAdConfig = vastCompanionAdConfig2;
    }

    public void setVastIconConfig(VastIconConfig vastIconConfig) {
        this.mVastIconConfig = vastIconConfig;
    }

    public void setCustomCtaText(String str) {
        if (str != null) {
            this.mCustomCtaText = str;
        }
    }

    public void setCustomSkipText(String str) {
        if (str != null) {
            this.mCustomSkipText = str;
        }
    }

    public void setCustomCloseIconUrl(String str) {
        if (str != null) {
            this.mCustomCloseIconUrl = str;
        }
    }

    public void setSkipOffset(String str) {
        if (str != null) {
            this.mSkipOffset = str;
        }
    }

    public void setVideoViewabilityTracker(VideoViewabilityTracker videoViewabilityTracker) {
        if (videoViewabilityTracker != null) {
            this.mVideoViewabilityTracker = videoViewabilityTracker;
        }
    }

    public void setIsRewardedVideo(boolean z) {
        this.mIsRewardedVideo = z;
    }

    public void setPrivacyInformationIconImageUrl(String str) {
        this.mPrivacyInformationIconImageUrl = str;
    }

    public void setPrivacyInformationIconClickthroughUrl(String str) {
        this.mPrivacyInformationIconClickthroughUrl = str;
    }

    public List<VastTracker> getImpressionTrackers() {
        return this.mImpressionTrackers;
    }

    public ArrayList<VastAbsoluteProgressTracker> getAbsoluteTrackers() {
        return this.mAbsoluteTrackers;
    }

    public ArrayList<VastFractionalProgressTracker> getFractionalTrackers() {
        return this.mFractionalTrackers;
    }

    public List<VastTracker> getPauseTrackers() {
        return this.mPauseTrackers;
    }

    public List<VastTracker> getResumeTrackers() {
        return this.mResumeTrackers;
    }

    public List<VastTracker> getCompleteTrackers() {
        return this.mCompleteTrackers;
    }

    public List<VastTracker> getCloseTrackers() {
        return this.mCloseTrackers;
    }

    public List<VastTracker> getSkipTrackers() {
        return this.mSkipTrackers;
    }

    public List<VastTracker> getClickTrackers() {
        return this.mClickTrackers;
    }

    public List<VastTracker> getErrorTrackers() {
        return this.mErrorTrackers;
    }

    public String getClickThroughUrl() {
        return this.mClickThroughUrl;
    }

    public String getNetworkMediaFileUrl() {
        return this.mNetworkMediaFileUrl;
    }

    public String getDiskMediaFileUrl() {
        return this.mDiskMediaFileUrl;
    }

    public VastCompanionAdConfig getVastCompanionAd(int i) {
        if (i == 1) {
            return this.mPortraitVastCompanionAdConfig;
        }
        if (i != 2) {
            return this.mLandscapeVastCompanionAdConfig;
        }
        return this.mLandscapeVastCompanionAdConfig;
    }

    public VastIconConfig getVastIconConfig() {
        return this.mVastIconConfig;
    }

    public String getCustomCtaText() {
        return this.mCustomCtaText;
    }

    public String getCustomSkipText() {
        return this.mCustomSkipText;
    }

    public String getCustomCloseIconUrl() {
        return this.mCustomCloseIconUrl;
    }

    public VideoViewabilityTracker getVideoViewabilityTracker() {
        return this.mVideoViewabilityTracker;
    }

    public Map<String, String> getExternalViewabilityTrackers() {
        return this.mExternalViewabilityTrackers;
    }

    public Set<String> getAvidJavascriptResources() {
        return this.mAvidJavascriptResources;
    }

    public Set<String> getMoatImpressionPixels() {
        return this.mMoatImpressionPixels;
    }

    public boolean hasCompanionAd() {
        return (this.mLandscapeVastCompanionAdConfig == null || this.mPortraitVastCompanionAdConfig == null) ? false : true;
    }

    public String getSkipOffsetString() {
        return this.mSkipOffset;
    }

    public boolean isRewardedVideo() {
        return this.mIsRewardedVideo;
    }

    public String getPrivacyInformationIconImageUrl() {
        return this.mPrivacyInformationIconImageUrl;
    }

    public String getPrivacyInformationIconClickthroughUrl() {
        return this.mPrivacyInformationIconClickthroughUrl;
    }

    public void handleImpression(Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mImpressionTrackers, (VastErrorCode) null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handleClickForResult(Activity activity, int i, int i2) {
        handleClick(activity, i, Integer.valueOf(i2));
    }

    public void handleClickWithoutResult(Context context, int i) {
        handleClick(context.getApplicationContext(), i, (Integer) null);
    }

    private void handleClick(final Context context, int i, final Integer num) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mClickTrackers, (VastErrorCode) null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
        if (!TextUtils.isEmpty(this.mClickThroughUrl)) {
            new UrlHandler.Builder().withDspCreativeId(this.mDspCreativeId).withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).withResultActions(new UrlHandler.ResultActions() {
                public void urlHandlingFailed(String str, UrlAction urlAction) {
                }

                public void urlHandlingSucceeded(String str, UrlAction urlAction) {
                    if (urlAction == UrlAction.OPEN_IN_APP_BROWSER) {
                        Bundle bundle = new Bundle();
                        bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, str);
                        bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, VastVideoConfig.this.mDspCreativeId);
                        Class<MoPubBrowser> cls = MoPubBrowser.class;
                        Intent startActivityIntent = Intents.getStartActivityIntent(context, cls, bundle);
                        try {
                            if (context instanceof Activity) {
                                Preconditions.checkNotNull(num);
                                ((Activity) context).startActivityForResult(startActivityIntent, num.intValue());
                                return;
                            }
                            Intents.startActivity(context, startActivityIntent);
                        } catch (ActivityNotFoundException unused) {
                            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                            MoPubLog.log(sdkLogEvent, "Activity " + cls.getName() + " not found. Did you declare it in your AndroidManifest.xml?");
                        } catch (IntentNotResolvableException unused2) {
                            MoPubLog.SdkLogEvent sdkLogEvent2 = MoPubLog.SdkLogEvent.CUSTOM;
                            MoPubLog.log(sdkLogEvent2, "Activity " + cls.getName() + " not found. Did you declare it in your AndroidManifest.xml?");
                        }
                    }
                }
            }).withoutMoPubBrowser().build().handleUrl(context, this.mClickThroughUrl);
        }
    }

    public void handleResume(Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mResumeTrackers, (VastErrorCode) null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handlePause(Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mPauseTrackers, (VastErrorCode) null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handleClose(Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mCloseTrackers, (VastErrorCode) null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handleSkip(Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mSkipTrackers, (VastErrorCode) null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handleComplete(Context context, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mCompleteTrackers, (VastErrorCode) null, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public void handleError(Context context, VastErrorCode vastErrorCode, int i) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mErrorTrackers, vastErrorCode, Integer.valueOf(i), this.mNetworkMediaFileUrl, context);
    }

    public List<VastTracker> getUntriggeredTrackersBefore(int i, int i2) {
        if (!Preconditions.NoThrow.checkArgument(i2 > 0) || i < 0) {
            return Collections.emptyList();
        }
        float f = ((float) i) / ((float) i2);
        ArrayList arrayList = new ArrayList();
        VastAbsoluteProgressTracker vastAbsoluteProgressTracker = new VastAbsoluteProgressTracker("", i);
        int size = this.mAbsoluteTrackers.size();
        for (int i3 = 0; i3 < size; i3++) {
            VastAbsoluteProgressTracker vastAbsoluteProgressTracker2 = this.mAbsoluteTrackers.get(i3);
            if (vastAbsoluteProgressTracker2.compareTo(vastAbsoluteProgressTracker) > 0) {
                break;
            }
            if (!vastAbsoluteProgressTracker2.isTracked()) {
                arrayList.add(vastAbsoluteProgressTracker2);
            }
        }
        VastFractionalProgressTracker vastFractionalProgressTracker = new VastFractionalProgressTracker("", f);
        int size2 = this.mFractionalTrackers.size();
        for (int i4 = 0; i4 < size2; i4++) {
            VastFractionalProgressTracker vastFractionalProgressTracker2 = this.mFractionalTrackers.get(i4);
            if (vastFractionalProgressTracker2.compareTo(vastFractionalProgressTracker) > 0) {
                break;
            }
            if (!vastFractionalProgressTracker2.isTracked()) {
                arrayList.add(vastFractionalProgressTracker2);
            }
        }
        return arrayList;
    }

    public int getRemainingProgressTrackerCount() {
        return getUntriggeredTrackersBefore(Integer.MAX_VALUE, Integer.MAX_VALUE).size();
    }

    public Integer getSkipOffsetMillis(int i) {
        Integer num;
        String str = this.mSkipOffset;
        if (str != null) {
            if (VastAbsoluteProgressTracker.isAbsoluteTracker(str)) {
                num = VastAbsoluteProgressTracker.parseAbsoluteOffset(this.mSkipOffset);
            } else if (VastFractionalProgressTrackerTwo.Companion.isPercentageTracker(this.mSkipOffset)) {
                num = Integer.valueOf(Math.round(((float) i) * (Float.parseFloat(this.mSkipOffset.replace("%", "")) / 100.0f)));
            } else {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format("Invalid VAST skipoffset format: %s", new Object[]{this.mSkipOffset}));
            }
            if (num != null) {
                if (num.intValue() < i) {
                    return num;
                }
                return Integer.valueOf(i);
            }
        }
        return null;
    }

    private List<String> hydrateUrls(String str, JSONArray jSONArray) {
        Preconditions.checkNotNull(jSONArray);
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            String optString = jSONArray.optString(i);
            if (optString != null) {
                arrayList.add(optString.replace(Constants.VIDEO_TRACKING_URL_MACRO, str));
            }
        }
        return arrayList;
    }

    private List<VastTracker> createVastTrackersForUrls(List<String> list) {
        Preconditions.checkNotNull(list);
        ArrayList arrayList = new ArrayList();
        for (String vastTracker : list) {
            arrayList.add(new VastTracker(vastTracker));
        }
        return arrayList;
    }

    private void addCompleteTrackersForUrls(List<String> list) {
        Preconditions.checkNotNull(list);
        addCompleteTrackers(createVastTrackersForUrls(list));
    }

    private void addStartTrackersForUrls(List<String> list) {
        Preconditions.checkNotNull(list);
        ArrayList arrayList = new ArrayList();
        for (String vastAbsoluteProgressTracker : list) {
            arrayList.add(new VastAbsoluteProgressTracker(vastAbsoluteProgressTracker, 0));
        }
        addAbsoluteTrackers(arrayList);
    }

    private void addFractionalTrackersForUrls(List<String> list, float f) {
        Preconditions.checkNotNull(list);
        ArrayList arrayList = new ArrayList();
        for (String vastFractionalProgressTracker : list) {
            arrayList.add(new VastFractionalProgressTracker(vastFractionalProgressTracker, f));
        }
        addFractionalTrackers(arrayList);
    }

    private void addCompanionAdViewTrackersForUrls(List<String> list) {
        Preconditions.checkNotNull(list);
        if (hasCompanionAd()) {
            List<VastTracker> createVastTrackersForUrls = createVastTrackersForUrls(list);
            this.mLandscapeVastCompanionAdConfig.addCreativeViewTrackers(createVastTrackersForUrls);
            this.mPortraitVastCompanionAdConfig.addCreativeViewTrackers(createVastTrackersForUrls);
        }
    }

    private void addCompanionAdClickTrackersForUrls(List<String> list) {
        Preconditions.checkNotNull(list);
        if (hasCompanionAd()) {
            List<VastTracker> createVastTrackersForUrls = createVastTrackersForUrls(list);
            this.mLandscapeVastCompanionAdConfig.addClickTrackers(createVastTrackersForUrls);
            this.mPortraitVastCompanionAdConfig.addClickTrackers(createVastTrackersForUrls);
        }
    }

    public static class VastVideoConfigTypeAdapterFactory implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken == null || !Class.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            return new VastVideoConfigTypeAdapter();
        }
    }

    public static class VastVideoConfigTypeAdapter extends TypeAdapter<Class<?>> {
        public void write(JsonWriter jsonWriter, Class<?> cls) throws IOException {
            if (jsonWriter != null) {
                if (cls == null) {
                    jsonWriter.nullValue();
                } else {
                    jsonWriter.value(cls.getName());
                }
            }
        }

        public Class<?> read(JsonReader jsonReader) throws IOException {
            if (jsonReader == null) {
                return null;
            }
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Class.forName(jsonReader.nextString());
            } catch (ClassNotFoundException e) {
                throw new IOException(e);
            }
        }
    }
}
