package com.startapp.android.publish.ads.video.tracking;

import com.startapp.android.publish.adsCommon.d.b;

/* compiled from: StartAppSDK */
public class VideoTrackingParams extends b {
    private static final String REPLAY_PARAMETER_PLACEHOLDER = "%startapp_replay_count%";
    private static final long serialVersionUID = 1;
    private int completed;
    protected boolean internalParamsIndicator;
    private String replayParameter;
    private boolean shouldAppendOffset;
    private String videoPlayingMode;

    public VideoTrackingParams(String str, int i, int i2, String str2) {
        super(str);
        setOffset(i2);
        this.completed = i;
        this.videoPlayingMode = str2;
    }

    public int getCompleted() {
        return this.completed;
    }

    public boolean getInternalTrackingParamsIndicator() {
        return this.internalParamsIndicator;
    }

    public VideoTrackingParams setShouldAppendOffset(boolean z) {
        this.shouldAppendOffset = z;
        return this;
    }

    public VideoTrackingParams setReplayParameter(String str) {
        this.replayParameter = str;
        return this;
    }

    public VideoTrackingParams setInternalTrackingParamsIndicator(boolean z) {
        this.internalParamsIndicator = z;
        return this;
    }

    public String getQueryString() {
        return getQueryString(getCompletedQuery() + getVideoPlayingModeQuery());
    }

    /* access modifiers changed from: protected */
    public String getOffsetQuery() {
        if (!this.shouldAppendOffset) {
            return "";
        }
        String str = this.replayParameter;
        if (str != null) {
            return str.replace(REPLAY_PARAMETER_PLACEHOLDER, new Integer(getOffset()).toString());
        }
        return super.getOffsetQuery();
    }

    /* access modifiers changed from: protected */
    public String getCompletedQuery() {
        return "&cp=" + this.completed;
    }

    /* access modifiers changed from: protected */
    public String getVideoPlayingModeQuery() {
        return "&vpm=" + this.videoPlayingMode;
    }

    /* access modifiers changed from: protected */
    public String getQueryString(String str) {
        if (!this.internalParamsIndicator) {
            return getOffsetQuery();
        }
        return super.getQueryString() + str;
    }
}
