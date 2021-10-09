package com.startapp.android.publish.ads.video.tracking;

import com.startapp.common.c.f;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public abstract class VideoTrackingLink implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean appendReplayParameter;
    private String replayParameter;
    @f(b = TrackingSource.class)
    private TrackingSource trackingSource;
    private String trackingUrl;

    /* compiled from: StartAppSDK */
    public enum TrackingSource {
        STARTAPP,
        EXTERNAL
    }

    public String getTrackingUrl() {
        return this.trackingUrl;
    }

    public void setTrackingUrl(String str) {
        this.trackingUrl = str;
    }

    public boolean shouldAppendReplay() {
        return this.appendReplayParameter;
    }

    public void setAppendReplayParameter(boolean z) {
        this.appendReplayParameter = z;
    }

    public String getReplayParameter() {
        return this.replayParameter;
    }

    public void setReplayParameter(String str) {
        this.replayParameter = str;
    }

    public TrackingSource getTrackingSource() {
        return this.trackingSource;
    }

    public String toString() {
        return "trackingSource=" + this.trackingSource + ", trackingUrl=" + this.trackingUrl + ", replayParameter=" + this.replayParameter + ", appendReplayParameter=" + this.appendReplayParameter;
    }
}
