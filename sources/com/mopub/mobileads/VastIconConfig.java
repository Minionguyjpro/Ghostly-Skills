package com.mopub.mobileads;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.List;

class VastIconConfig implements Serializable {
    private static final long serialVersionUID = 0;
    @SerializedName("clickthrough_url")
    @Expose
    private final String mClickThroughUri;
    @SerializedName("click_trackers")
    @Expose
    private final List<VastTracker> mClickTrackingUris;
    @SerializedName("duration_ms")
    @Expose
    private final Integer mDurationMS;
    @SerializedName("height")
    @Expose
    private final int mHeight;
    @SerializedName("skip_offset_ms")
    @Expose
    private final int mOffsetMS;
    @SerializedName("resource")
    @Expose
    private final VastResource mVastResource;
    @SerializedName("video_viewability_tracker")
    @Expose
    private final List<VastTracker> mViewTrackingUris;
    @SerializedName("width")
    @Expose
    private final int mWidth;

    VastIconConfig(int i, int i2, Integer num, Integer num2, VastResource vastResource, List<VastTracker> list, String str, List<VastTracker> list2) {
        int i3;
        Preconditions.checkNotNull(vastResource);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        this.mWidth = i;
        this.mHeight = i2;
        if (num == null) {
            i3 = 0;
        } else {
            i3 = num.intValue();
        }
        this.mOffsetMS = i3;
        this.mDurationMS = num2;
        this.mVastResource = vastResource;
        this.mClickTrackingUris = list;
        this.mClickThroughUri = str;
        this.mViewTrackingUris = list2;
    }

    /* access modifiers changed from: package-private */
    public int getWidth() {
        return this.mWidth;
    }

    /* access modifiers changed from: package-private */
    public int getHeight() {
        return this.mHeight;
    }

    /* access modifiers changed from: package-private */
    public int getOffsetMS() {
        return this.mOffsetMS;
    }

    /* access modifiers changed from: package-private */
    public Integer getDurationMS() {
        return this.mDurationMS;
    }

    /* access modifiers changed from: package-private */
    public VastResource getVastResource() {
        return this.mVastResource;
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getClickTrackingUris() {
        return this.mClickTrackingUris;
    }

    /* access modifiers changed from: package-private */
    public String getClickThroughUri() {
        return this.mClickThroughUri;
    }

    /* access modifiers changed from: package-private */
    public List<VastTracker> getViewTrackingUris() {
        return this.mViewTrackingUris;
    }

    /* access modifiers changed from: package-private */
    public void handleImpression(Context context, int i, String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        TrackingRequest.makeVastTrackingHttpRequest(this.mViewTrackingUris, (VastErrorCode) null, Integer.valueOf(i), str, context);
    }

    /* access modifiers changed from: package-private */
    public void handleClick(final Context context, String str, final String str2) {
        Preconditions.checkNotNull(context);
        String correctClickThroughUrl = this.mVastResource.getCorrectClickThroughUrl(this.mClickThroughUri, str);
        if (!TextUtils.isEmpty(correctClickThroughUrl)) {
            new UrlHandler.Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER).withResultActions(new UrlHandler.ResultActions() {
                public void urlHandlingFailed(String str, UrlAction urlAction) {
                }

                public void urlHandlingSucceeded(String str, UrlAction urlAction) {
                    if (urlAction == UrlAction.OPEN_IN_APP_BROWSER) {
                        Bundle bundle = new Bundle();
                        bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, str);
                        if (!TextUtils.isEmpty(str2)) {
                            bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, str2);
                        }
                        try {
                            Intents.startActivity(context, Intents.getStartActivityIntent(context, MoPubBrowser.class, bundle));
                        } catch (IntentNotResolvableException e) {
                            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, e.getMessage());
                        }
                    }
                }
            }).withoutMoPubBrowser().build().handleUrl(context, correctClickThroughUrl);
        }
    }
}
