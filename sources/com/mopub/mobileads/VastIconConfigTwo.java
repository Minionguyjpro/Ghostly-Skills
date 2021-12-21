package com.mopub.mobileads;

import android.content.Context;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

@Mockable
/* compiled from: VastIconConfigTwo.kt */
public class VastIconConfigTwo implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 1;
    @SerializedName("clickthrough_url")
    @Expose
    private final String clickThroughUri;
    @SerializedName("click_trackers")
    @Expose
    private final List<VastTrackerTwo> clickTrackingUris;
    @SerializedName("duration_ms")
    @Expose
    private final Integer durationMS;
    @SerializedName("height")
    @Expose
    private final int height;
    @SerializedName("skip_offset_ms")
    @Expose
    private final int offsetMS;
    @SerializedName("resource")
    @Expose
    private final VastResourceTwo vastResource;
    @SerializedName("video_viewability_tracker")
    @Expose
    private final List<VastTrackerTwo> viewTrackingUris;
    @SerializedName("width")
    @Expose
    private final int width;

    public VastIconConfigTwo(int i, int i2, Integer num, Integer num2, VastResourceTwo vastResourceTwo, List<? extends VastTrackerTwo> list, String str, List<? extends VastTrackerTwo> list2) {
        Intrinsics.checkParameterIsNotNull(vastResourceTwo, "vastResource");
        Intrinsics.checkParameterIsNotNull(list, "clickTrackingUris");
        Intrinsics.checkParameterIsNotNull(list2, "viewTrackingUris");
        this.width = i;
        this.height = i2;
        this.durationMS = num2;
        this.vastResource = vastResourceTwo;
        this.clickTrackingUris = list;
        this.clickThroughUri = str;
        this.viewTrackingUris = list2;
        this.offsetMS = num != null ? num.intValue() : 0;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Integer getDurationMS() {
        return this.durationMS;
    }

    public VastResourceTwo getVastResource() {
        return this.vastResource;
    }

    public List<VastTrackerTwo> getClickTrackingUris() {
        return this.clickTrackingUris;
    }

    public String getClickThroughUri() {
        return this.clickThroughUri;
    }

    public List<VastTrackerTwo> getViewTrackingUris() {
        return this.viewTrackingUris;
    }

    public int getOffsetMS() {
        return this.offsetMS;
    }

    public void handleImpression(Context context, int i, String str) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(str, "assetUri");
        TrackingRequest.makeVastTrackingTwoHttpRequest(getViewTrackingUris(), (VastErrorCode) null, Integer.valueOf(i), str, context);
    }

    public void handleClick(Context context, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String correctClickThroughUrl = getVastResource().getCorrectClickThroughUrl(getClickThroughUri(), str);
        if (correctClickThroughUrl != null) {
            if (!(correctClickThroughUrl.length() > 0)) {
                correctClickThroughUrl = null;
            }
            if (correctClickThroughUrl != null) {
                new UrlHandler.Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER).withResultActions(new VastIconConfigTwo$handleClick$$inlined$let$lambda$1(str2, context)).withoutMoPubBrowser().build().handleUrl(context, correctClickThroughUrl);
            }
        }
    }

    /* compiled from: VastIconConfigTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
