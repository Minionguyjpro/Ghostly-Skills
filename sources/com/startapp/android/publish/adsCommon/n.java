package com.startapp.android.publish.adsCommon;

import com.appnext.ads.fullscreen.RewardedVideo;
import com.startapp.common.c.f;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class n implements Serializable {
    private static final long serialVersionUID = 1;
    @f(b = a.class)
    private a backMode = a.DISABLED;
    private int maxCachedVideos = 3;
    private int maxTimeForCachingIndicator = 10;
    private int maxVastCampaignExclude = 10;
    private int maxVastLevels = 7;
    private long minAvailableStorageRequired = 20;
    private int minTimeForCachingIndicator = 1;
    private int nativePlayerProbability = 100;
    private boolean progressive = false;
    private int progressiveBufferInPercentage = 20;
    private int progressiveInitialBufferInPercentage = 5;
    private int progressiveMinBufferToPlayFromCache = 30;
    private int rewardGrantPercentage = 100;
    private String soundMode = RewardedVideo.VIDEO_MODE_DEFAULT;
    private int vastMediaPicker = 0;
    private int vastPreferredBitrate = 0;
    private int vastRetryTimeout = 60000;
    private int vastTimeout = 30000;
    private int videoErrorsThreshold = 2;
    private boolean videoFallback = false;

    /* compiled from: StartAppSDK */
    public enum a {
        DISABLED,
        SKIP,
        CLOSE,
        BOTH
    }

    public a a() {
        return this.backMode;
    }

    public int b() {
        return this.maxCachedVideos;
    }

    public long c() {
        return this.minAvailableStorageRequired;
    }

    public int d() {
        return this.rewardGrantPercentage;
    }

    public int e() {
        return this.videoErrorsThreshold;
    }

    public long f() {
        return TimeUnit.SECONDS.toMillis((long) this.minTimeForCachingIndicator);
    }

    public long g() {
        return TimeUnit.SECONDS.toMillis((long) this.maxTimeForCachingIndicator);
    }

    public boolean h() {
        return this.videoFallback;
    }

    public boolean i() {
        return this.progressive;
    }

    public int j() {
        return this.progressiveBufferInPercentage;
    }

    public int k() {
        return this.progressiveInitialBufferInPercentage;
    }

    public int l() {
        return this.progressiveMinBufferToPlayFromCache;
    }

    public String m() {
        return this.soundMode;
    }

    public int n() {
        return this.maxVastLevels;
    }

    public int o() {
        return this.vastTimeout;
    }

    public int p() {
        return this.vastRetryTimeout;
    }

    public int q() {
        return this.maxVastCampaignExclude;
    }

    public int r() {
        return this.vastMediaPicker;
    }

    public int s() {
        return this.vastPreferredBitrate;
    }
}
