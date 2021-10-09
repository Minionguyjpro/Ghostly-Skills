package com.mopub.mobileads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoViewabilityTracker extends VastTracker {
    @SerializedName("percent_viewable")
    @Expose
    private final int mPercentViewable;
    @SerializedName("playtime_ms")
    @Expose
    private final int mViewablePlaytimeMS;

    public VideoViewabilityTracker(int i, int i2, String str) {
        super(str);
        this.mViewablePlaytimeMS = i;
        this.mPercentViewable = i2;
    }

    public int getViewablePlaytimeMS() {
        return this.mViewablePlaytimeMS;
    }

    public int getPercentViewable() {
        return this.mPercentViewable;
    }
}
