package androidx.media2.exoplayer.external.video;

import androidx.media2.exoplayer.external.video.VideoRendererEventListener;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$1 implements Runnable {
    private final VideoRendererEventListener.EventDispatcher arg$1;
    private final String arg$2;
    private final long arg$3;
    private final long arg$4;

    VideoRendererEventListener$EventDispatcher$$Lambda$1(VideoRendererEventListener.EventDispatcher eventDispatcher, String str, long j, long j2) {
        this.arg$1 = eventDispatcher;
        this.arg$2 = str;
        this.arg$3 = j;
        this.arg$4 = j2;
    }

    public void run() {
        this.arg$1.lambda$decoderInitialized$1$VideoRendererEventListener$EventDispatcher(this.arg$2, this.arg$3, this.arg$4);
    }
}
