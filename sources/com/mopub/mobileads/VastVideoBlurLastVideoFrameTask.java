package com.mopub.mobileads;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ImageUtils;

public class VastVideoBlurLastVideoFrameTask extends AsyncTask<String, Void, Boolean> {
    private static final int MICROSECONDS_PER_MILLISECOND = 1000;
    private static final int OFFSET_IN_MICROSECONDS = 200000;
    private Bitmap mBlurredLastVideoFrame;
    private final ImageView mBlurredLastVideoFrameImageView;
    private Bitmap mLastVideoFrame;
    private final MediaMetadataRetriever mMediaMetadataRetriever;
    private int mVideoDuration;

    public VastVideoBlurLastVideoFrameTask(MediaMetadataRetriever mediaMetadataRetriever, ImageView imageView, int i) {
        this.mMediaMetadataRetriever = mediaMetadataRetriever;
        this.mBlurredLastVideoFrameImageView = imageView;
        this.mVideoDuration = i;
    }

    /* access modifiers changed from: protected */
    public Boolean doInBackground(String... strArr) {
        if (!(strArr == null || strArr.length == 0 || strArr[0] == null)) {
            try {
                this.mMediaMetadataRetriever.setDataSource(strArr[0]);
                Bitmap frameAtTime = this.mMediaMetadataRetriever.getFrameAtTime((long) ((this.mVideoDuration * 1000) - OFFSET_IN_MICROSECONDS), 3);
                this.mLastVideoFrame = frameAtTime;
                if (frameAtTime == null) {
                    return false;
                }
                this.mBlurredLastVideoFrame = ImageUtils.applyFastGaussianBlurToBitmap(frameAtTime, 4);
                return true;
            } catch (Exception e) {
                MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, "Failed to blur last video frame", e);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Boolean bool) {
        if (isCancelled()) {
            onCancelled();
        } else if (bool != null && bool.booleanValue()) {
            this.mBlurredLastVideoFrameImageView.setImageBitmap(this.mBlurredLastVideoFrame);
            this.mBlurredLastVideoFrameImageView.setImageAlpha(100);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "VastVideoBlurLastVideoFrameTask was cancelled.");
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public Bitmap getBlurredLastVideoFrame() {
        return this.mBlurredLastVideoFrame;
    }
}
