package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Constants;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.VastAbsoluteProgressTrackerTwo;
import com.mopub.mobileads.VastFractionalProgressTrackerTwo;
import com.mopub.mobileads.VastTrackerTwo;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

@Mockable
/* compiled from: VastVideoConfigTwo.kt */
public class VastVideoConfigTwo implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 3;
    @SerializedName("absolute_trackers")
    @Expose
    private final List<VastAbsoluteProgressTrackerTwo> _absoluteTrackers = new ArrayList();
    @SerializedName("avid_javascript_resources")
    @Expose
    private final Set<String> _avidJavascriptResources = new LinkedHashSet();
    @SerializedName("click_trackers")
    @Expose
    private final List<VastTrackerTwo> _clickTrackers = new ArrayList();
    @SerializedName("close_trackers")
    @Expose
    private final List<VastTrackerTwo> _closeTrackers = new ArrayList();
    @SerializedName("complete_trackers")
    @Expose
    private final List<VastTrackerTwo> _completeTrackers = new ArrayList();
    @SerializedName("error_trackers")
    @Expose
    private final List<VastTrackerTwo> _errorTrackers = new ArrayList();
    @SerializedName("external_viewability_trackers")
    @Expose
    private final Map<String, String> _externalViewabilityTrackers = new LinkedHashMap();
    @SerializedName("fractional_trackers")
    @Expose
    private final List<VastFractionalProgressTrackerTwo> _fractionalTrackers = new ArrayList();
    @SerializedName("impression_trackers")
    @Expose
    private final List<VastTrackerTwo> _impressionTrackers = new ArrayList();
    @SerializedName("moat_impression_pixels")
    @Expose
    private final Set<String> _moatImpressionPixels = new LinkedHashSet();
    @SerializedName("pause_trackers")
    @Expose
    private final List<VastTrackerTwo> _pauseTrackers = new ArrayList();
    @SerializedName("resume_trackers")
    @Expose
    private final List<VastTrackerTwo> _resumeTrackers = new ArrayList();
    @SerializedName("skip_trackers")
    @Expose
    private final List<VastTrackerTwo> _skipTrackers = new ArrayList();
    @SerializedName("clickthrough_url")
    @Expose
    private String clickThroughUrl;
    @SerializedName("custom_close_icon_url")
    @Expose
    private String customCloseIconUrl;
    @SerializedName("custom_cta_text")
    @Expose
    private String customCtaText;
    @SerializedName("custom_skip_text")
    @Expose
    private String customSkipText;
    @SerializedName("disk_media_file_url")
    @Expose
    private String diskMediaFileUrl;
    @SerializedName("dsp_creative_id")
    @Expose
    private String dspCreativeId;
    @SerializedName("enable_click_exp")
    @Expose
    private boolean enableClickExperiment;
    @SerializedName("is_rewarded")
    @Expose
    private boolean isRewarded;
    @SerializedName("landscape_companion_ad")
    @Expose
    private VastCompanionAdConfigTwo landscapeVastCompanionAdConfig;
    @SerializedName("network_media_file_url")
    @Expose
    private String networkMediaFileUrl;
    @SerializedName("portrait_companion_ad")
    @Expose
    private VastCompanionAdConfigTwo portraitVastCompanionAdConfig;
    @SerializedName("privacy_icon_click_url")
    @Expose
    private String privacyInformationIconClickthroughUrl;
    @SerializedName("privacy_icon_image_url")
    @Expose
    private String privacyInformationIconImageUrl;
    @SerializedName("skip_offset")
    @Expose
    private String skipOffset;
    @SerializedName("icon_config")
    @Expose
    private VastIconConfigTwo vastIconConfig;
    @SerializedName("video_viewability_tracker")
    @Expose
    private VideoViewabilityTracker videoViewabilityTracker;

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VideoTrackingEvent.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[VideoTrackingEvent.START.ordinal()] = 1;
            $EnumSwitchMapping$0[VideoTrackingEvent.FIRST_QUARTILE.ordinal()] = 2;
            $EnumSwitchMapping$0[VideoTrackingEvent.MIDPOINT.ordinal()] = 3;
            $EnumSwitchMapping$0[VideoTrackingEvent.THIRD_QUARTILE.ordinal()] = 4;
            $EnumSwitchMapping$0[VideoTrackingEvent.COMPLETE.ordinal()] = 5;
            $EnumSwitchMapping$0[VideoTrackingEvent.COMPANION_AD_VIEW.ordinal()] = 6;
            $EnumSwitchMapping$0[VideoTrackingEvent.COMPANION_AD_CLICK.ordinal()] = 7;
        }
    }

    /* compiled from: VastVideoConfigTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ArrayList<VastTrackerTwo> getImpressionTrackers() {
        return new ArrayList<>(this._impressionTrackers);
    }

    public ArrayList<VastTrackerTwo> getPauseTrackers() {
        return new ArrayList<>(this._pauseTrackers);
    }

    public ArrayList<VastTrackerTwo> getResumeTrackers() {
        return new ArrayList<>(this._resumeTrackers);
    }

    public ArrayList<VastTrackerTwo> getCompleteTrackers() {
        return new ArrayList<>(this._completeTrackers);
    }

    public ArrayList<VastTrackerTwo> getCloseTrackers() {
        return new ArrayList<>(this._closeTrackers);
    }

    public ArrayList<VastTrackerTwo> getSkipTrackers() {
        return new ArrayList<>(this._skipTrackers);
    }

    public ArrayList<VastTrackerTwo> getClickTrackers() {
        return new ArrayList<>(this._clickTrackers);
    }

    public ArrayList<VastTrackerTwo> getErrorTrackers() {
        return new ArrayList<>(this._errorTrackers);
    }

    public ArrayList<VastFractionalProgressTrackerTwo> getFractionalTrackers() {
        return new ArrayList<>(this._fractionalTrackers);
    }

    public ArrayList<VastAbsoluteProgressTrackerTwo> getAbsoluteTrackers() {
        return new ArrayList<>(this._absoluteTrackers);
    }

    public Map<String, String> getExternalViewabilityTrackers() {
        return new HashMap<>(this._externalViewabilityTrackers);
    }

    public Set<String> getAvidJavascriptResources() {
        return new HashSet<>(this._avidJavascriptResources);
    }

    public Set<String> getMoatImpressionPixels() {
        return new HashSet<>(this._moatImpressionPixels);
    }

    public String getClickThroughUrl() {
        return this.clickThroughUrl;
    }

    public void setClickThroughUrl(String str) {
        this.clickThroughUrl = str;
    }

    public String getNetworkMediaFileUrl() {
        return this.networkMediaFileUrl;
    }

    public void setNetworkMediaFileUrl(String str) {
        this.networkMediaFileUrl = str;
    }

    public String getDiskMediaFileUrl() {
        return this.diskMediaFileUrl;
    }

    public void setDiskMediaFileUrl(String str) {
        this.diskMediaFileUrl = str;
    }

    public String getSkipOffset() {
        return this.skipOffset;
    }

    public void setSkipOffset$mopub_sdk_base_release(String str) {
        if (str == null) {
            str = this.skipOffset;
        }
        this.skipOffset = str;
    }

    public VastIconConfigTwo getVastIconConfig() {
        return this.vastIconConfig;
    }

    public void setVastIconConfig(VastIconConfigTwo vastIconConfigTwo) {
        this.vastIconConfig = vastIconConfigTwo;
    }

    public boolean isRewarded() {
        return this.isRewarded;
    }

    public void setRewarded$mopub_sdk_base_release(boolean z) {
        this.isRewarded = z;
    }

    public boolean getEnableClickExperiment() {
        return this.enableClickExperiment;
    }

    public void setEnableClickExperiment(boolean z) {
        this.enableClickExperiment = z;
    }

    public String getCustomCtaText() {
        return this.customCtaText;
    }

    public void setCustomCtaText(String str) {
        if (str == null) {
            str = this.customCtaText;
        }
        this.customCtaText = str;
    }

    public String getCustomSkipText() {
        return this.customSkipText;
    }

    public void setCustomSkipText(String str) {
        if (str == null) {
            str = this.customSkipText;
        }
        this.customSkipText = str;
    }

    public String getCustomCloseIconUrl() {
        return this.customCloseIconUrl;
    }

    public void setCustomCloseIconUrl(String str) {
        if (str == null) {
            str = this.customCloseIconUrl;
        }
        this.customCloseIconUrl = str;
    }

    public VideoViewabilityTracker getVideoViewabilityTracker() {
        return this.videoViewabilityTracker;
    }

    public void setVideoViewabilityTracker(VideoViewabilityTracker videoViewabilityTracker2) {
        if (videoViewabilityTracker2 == null) {
            videoViewabilityTracker2 = this.videoViewabilityTracker;
        }
        this.videoViewabilityTracker = videoViewabilityTracker2;
    }

    public String getDspCreativeId() {
        return this.dspCreativeId;
    }

    public void setDspCreativeId(String str) {
        if (str == null) {
            str = this.dspCreativeId;
        }
        this.dspCreativeId = str;
    }

    public String getPrivacyInformationIconImageUrl() {
        return this.privacyInformationIconImageUrl;
    }

    public void setPrivacyInformationIconImageUrl(String str) {
        if (str == null) {
            str = this.privacyInformationIconImageUrl;
        }
        this.privacyInformationIconImageUrl = str;
    }

    public String getPrivacyInformationIconClickthroughUrl() {
        return this.privacyInformationIconClickthroughUrl;
    }

    public void setPrivacyInformationIconClickthroughUrl(String str) {
        this.privacyInformationIconClickthroughUrl = str;
    }

    public void addImpressionTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "impressionTrackers");
        this._impressionTrackers.addAll(list);
    }

    public void addResumeTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "resumeTrackers");
        this._resumeTrackers.addAll(list);
    }

    public void addFractionalTrackers(List<VastFractionalProgressTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "fractionalTrackers");
        this._fractionalTrackers.addAll(list);
        CollectionsKt.sort(this._fractionalTrackers);
    }

    public void addAbsoluteTrackers(List<? extends VastAbsoluteProgressTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "absoluteTrackers");
        this._absoluteTrackers.addAll(list);
        CollectionsKt.sort(this._absoluteTrackers);
    }

    public void addCompleteTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "completeTrackers");
        this._completeTrackers.addAll(list);
    }

    public void addPauseTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "pauseTrackers");
        this._pauseTrackers.addAll(list);
    }

    public void addCloseTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "closeTrackers");
        this._closeTrackers.addAll(list);
    }

    public void addSkipTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "skipTrackers");
        this._skipTrackers.addAll(list);
    }

    public void addClickTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "clickTrackers");
        this._clickTrackers.addAll(list);
    }

    public void addErrorTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "errorTrackers");
        this._errorTrackers.addAll(list);
    }

    public void addExternalViewabilityTrackers(Map<String, String> map) {
        if (map != null) {
            this._externalViewabilityTrackers.putAll(map);
        }
    }

    public void addAvidJavascriptResources(Set<String> set) {
        if (set != null) {
            this._avidJavascriptResources.addAll(set);
        }
    }

    public void addMoatImpressionPixels(Set<String> set) {
        if (set != null) {
            this._moatImpressionPixels.addAll(set);
        }
    }

    public void addVideoTrackers(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray(Constants.VIDEO_TRACKING_URLS_KEY);
            JSONArray optJSONArray2 = jSONObject.optJSONArray(Constants.VIDEO_TRACKING_EVENTS_KEY);
            if (optJSONArray != null && optJSONArray2 != null) {
                int length = optJSONArray2.length();
                for (int i = 0; i < length; i++) {
                    String optString = optJSONArray2.optString(i);
                    List<String> hydrateUrls = hydrateUrls(optString, optJSONArray);
                    VideoTrackingEvent fromString = VideoTrackingEvent.Companion.fromString(optString);
                    if (!(optString == null || hydrateUrls == null)) {
                        switch (WhenMappings.$EnumSwitchMapping$0[fromString.ordinal()]) {
                            case 1:
                                addStartTrackersForUrls(hydrateUrls);
                                break;
                            case 2:
                            case 3:
                            case 4:
                                addFractionalTrackersForUrls(hydrateUrls, fromString.toFloat());
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
                                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Encountered unknown video tracking event: " + optString);
                                break;
                        }
                    }
                }
            }
        }
    }

    public void setVastCompanionAd(VastCompanionAdConfigTwo vastCompanionAdConfigTwo, VastCompanionAdConfigTwo vastCompanionAdConfigTwo2) {
        this.landscapeVastCompanionAdConfig = vastCompanionAdConfigTwo;
        this.portraitVastCompanionAdConfig = vastCompanionAdConfigTwo2;
    }

    public VastCompanionAdConfigTwo getVastCompanionAd(int i) {
        if (i == 1) {
            return this.portraitVastCompanionAdConfig;
        }
        if (i != 2) {
            return this.landscapeVastCompanionAdConfig;
        }
        return this.landscapeVastCompanionAdConfig;
    }

    public boolean hasCompanionAd() {
        return (this.landscapeVastCompanionAdConfig == null || this.portraitVastCompanionAdConfig == null) ? false : true;
    }

    public void handleImpression(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._impressionTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public void handleClickForResult(Activity activity, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        handleClick(activity, i, Integer.valueOf(i2));
    }

    public void handleClickWithoutResult(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        handleClick(applicationContext, i, (Integer) null);
    }

    private void handleClick(Context context, int i, Integer num) {
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._clickTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
        CharSequence clickThroughUrl2 = getClickThroughUrl();
        if (!(clickThroughUrl2 == null || clickThroughUrl2.length() == 0)) {
            UrlHandler build = new UrlHandler.Builder().withDspCreativeId(getDspCreativeId()).withoutMoPubBrowser().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).withResultActions(new VastVideoConfigTwo$handleClick$urlHandler$1(this, context, num)).build();
            String clickThroughUrl3 = getClickThroughUrl();
            if (clickThroughUrl3 != null) {
                build.handleUrl(context, clickThroughUrl3);
            }
        }
    }

    public void handleResume(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._resumeTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public void handlePause(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._pauseTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public void handleClose(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._closeTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public void handleSkip(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._skipTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public void handleComplete(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._completeTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public void handleError(Context context, VastErrorCode vastErrorCode, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._errorTrackers, vastErrorCode, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public List<VastTrackerTwo> getUntriggeredTrackersBefore(int i, int i2) {
        if (i2 <= 0 || i < 0) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        VastAbsoluteProgressTrackerTwo build = new VastAbsoluteProgressTrackerTwo.Builder("", i).build();
        for (VastAbsoluteProgressTrackerTwo vastAbsoluteProgressTrackerTwo : this._absoluteTrackers) {
            if (vastAbsoluteProgressTrackerTwo.compareTo(build) <= 0 && !vastAbsoluteProgressTrackerTwo.isTracked()) {
                arrayList.add(vastAbsoluteProgressTrackerTwo);
            }
        }
        VastFractionalProgressTrackerTwo build2 = new VastFractionalProgressTrackerTwo.Builder("", ((float) i) / ((float) i2)).build();
        for (VastFractionalProgressTrackerTwo vastFractionalProgressTrackerTwo : this._fractionalTrackers) {
            if (vastFractionalProgressTrackerTwo.compareTo(build2) <= 0 && !vastFractionalProgressTrackerTwo.isTracked()) {
                arrayList.add(vastFractionalProgressTrackerTwo);
            }
        }
        return arrayList;
    }

    public int getRemainingProgressTrackerCount() {
        return getUntriggeredTrackersBefore(Integer.MAX_VALUE, Integer.MAX_VALUE).size();
    }

    public Integer getSkipOffsetMillis(int i) throws NumberFormatException {
        Integer num;
        String skipOffset2 = getSkipOffset();
        if (skipOffset2 == null) {
            return null;
        }
        if (VastAbsoluteProgressTrackerTwo.Companion.isAbsoluteTracker(skipOffset2)) {
            num = VastAbsoluteProgressTrackerTwo.Companion.parseAbsoluteOffset(skipOffset2);
        } else if (VastFractionalProgressTrackerTwo.Companion.isPercentageTracker(skipOffset2)) {
            num = VastFractionalProgressTrackerTwo.Companion.parsePercentageOffset(skipOffset2, i);
        } else {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Invalid VAST skipoffset format: " + skipOffset2);
            num = null;
        }
        if (num != null) {
            return Integer.valueOf(Math.min(num.intValue(), i));
        }
        return null;
    }

    private List<String> hydrateUrls(String str, JSONArray jSONArray) {
        if (str == null) {
            return null;
        }
        List<String> arrayList = new ArrayList<>();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            String optString = jSONArray.optString(i);
            if (optString != null) {
                arrayList.add(StringsKt.replace$default(optString, Constants.VIDEO_TRACKING_URL_MACRO, str, false, 4, (Object) null));
            }
        }
        return arrayList;
    }

    private List<VastTrackerTwo> createVastTrackersForUrls(List<String> list) {
        Iterable<String> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String builder : iterable) {
            arrayList.add(new VastTrackerTwo.Builder(builder).build());
        }
        return (List) arrayList;
    }

    private void addCompleteTrackersForUrls(List<String> list) {
        addCompleteTrackers(createVastTrackersForUrls(list));
    }

    private void addStartTrackersForUrls(List<String> list) {
        Iterable<String> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String builder : iterable) {
            arrayList.add(new VastAbsoluteProgressTrackerTwo.Builder(builder, 0).build());
        }
        addAbsoluteTrackers((List) arrayList);
    }

    private void addFractionalTrackersForUrls(List<String> list, float f) {
        Iterable<String> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String builder : iterable) {
            arrayList.add(new VastFractionalProgressTrackerTwo.Builder(builder, f).build());
        }
        addFractionalTrackers((List) arrayList);
    }

    private void addCompanionAdViewTrackersForUrls(List<String> list) {
        List<VastTrackerTwo> createVastTrackersForUrls = createVastTrackersForUrls(list);
        VastCompanionAdConfigTwo vastCompanionAdConfigTwo = this.landscapeVastCompanionAdConfig;
        if (vastCompanionAdConfigTwo != null) {
            vastCompanionAdConfigTwo.addCreativeViewTrackers(createVastTrackersForUrls);
        }
        VastCompanionAdConfigTwo vastCompanionAdConfigTwo2 = this.portraitVastCompanionAdConfig;
        if (vastCompanionAdConfigTwo2 != null) {
            vastCompanionAdConfigTwo2.addCreativeViewTrackers(createVastTrackersForUrls);
        }
    }

    private void addCompanionAdClickTrackersForUrls(List<String> list) {
        List<VastTrackerTwo> createVastTrackersForUrls = createVastTrackersForUrls(list);
        VastCompanionAdConfigTwo vastCompanionAdConfigTwo = this.landscapeVastCompanionAdConfig;
        if (vastCompanionAdConfigTwo != null) {
            vastCompanionAdConfigTwo.addClickTrackers(createVastTrackersForUrls);
        }
        VastCompanionAdConfigTwo vastCompanionAdConfigTwo2 = this.portraitVastCompanionAdConfig;
        if (vastCompanionAdConfigTwo2 != null) {
            vastCompanionAdConfigTwo2.addClickTrackers(createVastTrackersForUrls);
        }
    }
}
