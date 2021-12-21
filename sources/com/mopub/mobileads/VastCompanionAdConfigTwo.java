package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VastCompanionAdConfigTwo.kt */
public final class VastCompanionAdConfigTwo implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 3;
    @SerializedName("clickthrough_url")
    @Expose
    private final String clickThroughUrl;
    @SerializedName("click_trackers")
    @Expose
    private final List<VastTrackerTwo> clickTrackers;
    @SerializedName("impression_trackers")
    @Expose
    private final List<VastTrackerTwo> creativeViewTrackers;
    @SerializedName("height")
    @Expose
    private final int height;
    @SerializedName("resource")
    @Expose
    private final VastResourceTwo vastResource;
    @SerializedName("width")
    @Expose
    private final int width;

    public VastCompanionAdConfigTwo(int i, int i2, VastResourceTwo vastResourceTwo, String str, List<VastTrackerTwo> list, List<VastTrackerTwo> list2) {
        Intrinsics.checkParameterIsNotNull(vastResourceTwo, "vastResource");
        Intrinsics.checkParameterIsNotNull(list, "clickTrackers");
        Intrinsics.checkParameterIsNotNull(list2, "creativeViewTrackers");
        this.width = i;
        this.height = i2;
        this.vastResource = vastResourceTwo;
        this.clickThroughUrl = str;
        this.clickTrackers = list;
        this.creativeViewTrackers = list2;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final VastResourceTwo getVastResource() {
        return this.vastResource;
    }

    public final String getClickThroughUrl() {
        return this.clickThroughUrl;
    }

    public final List<VastTrackerTwo> getClickTrackers() {
        return this.clickTrackers;
    }

    public final List<VastTrackerTwo> getCreativeViewTrackers() {
        return this.creativeViewTrackers;
    }

    /* compiled from: VastCompanionAdConfigTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void addClickTrackers(Collection<? extends VastTrackerTwo> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "clickTrackers");
        this.clickTrackers.addAll(collection);
    }

    public final void addCreativeViewTrackers(Collection<? extends VastTrackerTwo> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "creativeViewTrackers");
        this.creativeViewTrackers.addAll(collection);
    }

    public final void handleImpression(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this.creativeViewTrackers, (VastErrorCode) null, Integer.valueOf(i), (String) null, context);
    }

    public final void handleClick(Context context, int i, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (context instanceof Activity) {
            String correctClickThroughUrl = this.vastResource.getCorrectClickThroughUrl(this.clickThroughUrl, str);
            if (correctClickThroughUrl != null) {
                if (!(correctClickThroughUrl.length() > 0)) {
                    correctClickThroughUrl = null;
                }
                if (correctClickThroughUrl != null) {
                    new UrlHandler.Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).withResultActions(new VastCompanionAdConfigTwo$handleClick$$inlined$let$lambda$1(str2, context, i)).withDspCreativeId(str2).withoutMoPubBrowser().build().handleUrl(context, correctClickThroughUrl);
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("context must be an activity".toString());
    }
}
