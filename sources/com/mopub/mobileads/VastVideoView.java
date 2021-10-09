package com.mopub.mobileads;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.VideoView;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;

public class VastVideoView extends VideoView {
    private static final int MAX_VIDEO_RETRIES = 1;
    private static final int VIDEO_VIEW_FILE_PERMISSION_ERROR = Integer.MIN_VALUE;
    private VastVideoBlurLastVideoFrameTask mBlurLastVideoFrameTask;
    private MediaMetadataRetriever mMediaMetadataRetriever = new MediaMetadataRetriever();

    public VastVideoView(Context context) {
        super(context);
        Preconditions.checkNotNull(context, "context cannot be null");
    }

    public void prepareBlurredLastVideoFrame(ImageView imageView, String str) {
        if (this.mMediaMetadataRetriever != null) {
            VastVideoBlurLastVideoFrameTask vastVideoBlurLastVideoFrameTask = new VastVideoBlurLastVideoFrameTask(this.mMediaMetadataRetriever, imageView, getDuration());
            this.mBlurLastVideoFrameTask = vastVideoBlurLastVideoFrameTask;
            try {
                AsyncTasks.safeExecuteOnExecutor(vastVideoBlurLastVideoFrameTask, str);
            } catch (Exception e) {
                MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, "Failed to blur last video frame", e);
            }
        }
    }

    public void onDestroy() {
        VastVideoBlurLastVideoFrameTask vastVideoBlurLastVideoFrameTask = this.mBlurLastVideoFrameTask;
        if (vastVideoBlurLastVideoFrameTask != null && vastVideoBlurLastVideoFrameTask.getStatus() != AsyncTask.Status.FINISHED) {
            this.mBlurLastVideoFrameTask.cancel(true);
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setMediaMetadataRetriever(MediaMetadataRetriever mediaMetadataRetriever) {
        this.mMediaMetadataRetriever = mediaMetadataRetriever;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public VastVideoBlurLastVideoFrameTask getBlurLastVideoFrameTask() {
        return this.mBlurLastVideoFrameTask;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setBlurLastVideoFrameTask(VastVideoBlurLastVideoFrameTask vastVideoBlurLastVideoFrameTask) {
        this.mBlurLastVideoFrameTask = vastVideoBlurLastVideoFrameTask;
    }
}
