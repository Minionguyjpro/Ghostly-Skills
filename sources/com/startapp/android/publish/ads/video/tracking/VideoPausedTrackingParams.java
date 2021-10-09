package com.startapp.android.publish.ads.video.tracking;

/* compiled from: StartAppSDK */
public class VideoPausedTrackingParams extends VideoTrackingParams {
    private static final long serialVersionUID = 1;
    private int pauseNum;
    private PauseOrigin pauseOrigin;

    /* compiled from: StartAppSDK */
    public enum PauseOrigin {
        INAPP,
        EXTERNAL
    }

    public VideoPausedTrackingParams(String str, int i, int i2, int i3, PauseOrigin pauseOrigin2, String str2) {
        super(str, i, i2, str2);
        this.pauseNum = i3;
        this.pauseOrigin = pauseOrigin2;
    }

    public int getPauseNum() {
        return this.pauseNum;
    }

    public PauseOrigin getPauseOrigin() {
        return this.pauseOrigin;
    }

    public String getQueryString() {
        return getQueryString(getCompletedQuery() + getPauseOriginQuery() + getPauseNumQuery() + getVideoPlayingModeQuery());
    }

    private String getPauseNumQuery() {
        return "&pn=" + getPauseNum();
    }

    private String getPauseOriginQuery() {
        return "&po=" + getPauseOrigin().toString();
    }
}
