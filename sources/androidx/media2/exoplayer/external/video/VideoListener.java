package androidx.media2.exoplayer.external.video;

public interface VideoListener {
    void onRenderedFirstFrame();

    void onSurfaceSizeChanged(int i, int i2);

    void onVideoSizeChanged(int i, int i2, int i3, float f);
}
