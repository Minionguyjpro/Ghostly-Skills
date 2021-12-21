package com.mopub.mobileads;

import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.mopub.common.CacheService;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.mobileads.VastXmlManagerAggregator;
import com.mopub.mobileads.VideoDownloader;

public class VastManager implements VastXmlManagerAggregator.VastXmlManagerAggregatorListener {
    private String mDspCreativeId;
    private double mScreenAspectRatio;
    private int mScreenWidthDp;
    private final boolean mShouldPreCacheVideo;
    /* access modifiers changed from: private */
    public VastManagerListener mVastManagerListener;
    private VastXmlManagerAggregator mVastXmlManagerAggregator;

    public interface VastManagerListener {
        void onVastVideoConfigurationPrepared(VastVideoConfig vastVideoConfig);
    }

    public VastManager(Context context, boolean z) {
        initializeScreenDimensions(context);
        this.mShouldPreCacheVideo = z;
    }

    public void prepareVastVideoConfiguration(String str, VastManagerListener vastManagerListener, String str2, Context context) {
        Preconditions.checkNotNull(vastManagerListener, "vastManagerListener cannot be null");
        Preconditions.checkNotNull(context, "context cannot be null");
        if (this.mVastXmlManagerAggregator == null) {
            this.mVastManagerListener = vastManagerListener;
            VastXmlManagerAggregator vastXmlManagerAggregator = new VastXmlManagerAggregator(this, this.mScreenAspectRatio, this.mScreenWidthDp, context.getApplicationContext());
            this.mVastXmlManagerAggregator = vastXmlManagerAggregator;
            this.mDspCreativeId = str2;
            try {
                AsyncTasks.safeExecuteOnExecutor(vastXmlManagerAggregator, str);
            } catch (Exception e) {
                MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, "Failed to aggregate vast xml", e);
                this.mVastManagerListener.onVastVideoConfigurationPrepared((VastVideoConfig) null);
            }
        }
    }

    public void cancel() {
        VastXmlManagerAggregator vastXmlManagerAggregator = this.mVastXmlManagerAggregator;
        if (vastXmlManagerAggregator != null) {
            vastXmlManagerAggregator.cancel(true);
            this.mVastXmlManagerAggregator = null;
        }
    }

    public void onAggregationComplete(final VastVideoConfig vastVideoConfig) {
        VastManagerListener vastManagerListener = this.mVastManagerListener;
        if (vastManagerListener == null) {
            throw new IllegalStateException("mVastManagerListener cannot be null here. Did you call prepareVastVideoConfiguration()?");
        } else if (vastVideoConfig == null) {
            vastManagerListener.onVastVideoConfigurationPrepared((VastVideoConfig) null);
        } else {
            if (!TextUtils.isEmpty(this.mDspCreativeId)) {
                vastVideoConfig.setDspCreativeId(this.mDspCreativeId);
            }
            if (!this.mShouldPreCacheVideo || updateDiskMediaFileUrl(vastVideoConfig)) {
                this.mVastManagerListener.onVastVideoConfigurationPrepared(vastVideoConfig);
                return;
            }
            VideoDownloader.cache(vastVideoConfig.getNetworkMediaFileUrl(), new VideoDownloader.VideoDownloaderListener() {
                public void onComplete(boolean z) {
                    if (!z || !VastManager.this.updateDiskMediaFileUrl(vastVideoConfig)) {
                        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Failed to download VAST video.");
                        VastManager.this.mVastManagerListener.onVastVideoConfigurationPrepared((VastVideoConfig) null);
                        return;
                    }
                    VastManager.this.mVastManagerListener.onVastVideoConfigurationPrepared(vastVideoConfig);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public boolean updateDiskMediaFileUrl(VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(vastVideoConfig, "vastVideoConfig cannot be null");
        String networkMediaFileUrl = vastVideoConfig.getNetworkMediaFileUrl();
        if (!CacheService.containsKeyDiskCache(networkMediaFileUrl)) {
            return false;
        }
        vastVideoConfig.setDiskMediaFileUrl(CacheService.getFilePathDiskCache(networkMediaFileUrl));
        return true;
    }

    private void initializeScreenDimensions(Context context) {
        Preconditions.checkNotNull(context, "context cannot be null");
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        float f = context.getResources().getDisplayMetrics().density;
        if (f <= 0.0f) {
            f = 1.0f;
        }
        double d = (double) width;
        double d2 = (double) height;
        Double.isNaN(d);
        Double.isNaN(d2);
        this.mScreenAspectRatio = d / d2;
        this.mScreenWidthDp = (int) (((float) width) / f);
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public int getScreenWidthDp() {
        return this.mScreenWidthDp;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public double getScreenAspectRatio() {
        return this.mScreenAspectRatio;
    }
}
