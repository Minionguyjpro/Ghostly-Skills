package androidx.media2.exoplayer.external.video;

import androidx.media2.exoplayer.external.video.VideoRendererEventListener;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$4 implements Runnable {
    private final VideoRendererEventListener.EventDispatcher arg$1;
    private final int arg$2;
    private final int arg$3;
    private final int arg$4;
    private final float arg$5;

    VideoRendererEventListener$EventDispatcher$$Lambda$4(VideoRendererEventListener.EventDispatcher eventDispatcher, int i, int i2, int i3, float f) {
        this.arg$1 = eventDispatcher;
        this.arg$2 = i;
        this.arg$3 = i2;
        this.arg$4 = i3;
        this.arg$5 = f;
    }

    public void run() {
        this.arg$1.lambda$videoSizeChanged$4$VideoRendererEventListener$EventDispatcher(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
