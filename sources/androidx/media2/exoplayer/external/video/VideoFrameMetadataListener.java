package androidx.media2.exoplayer.external.video;

import androidx.media2.exoplayer.external.Format;

public interface VideoFrameMetadataListener {
    void onVideoFrameAboutToBeRendered(long j, long j2, Format format);
}
