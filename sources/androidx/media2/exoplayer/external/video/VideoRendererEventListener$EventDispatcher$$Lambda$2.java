package androidx.media2.exoplayer.external.video;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.video.VideoRendererEventListener;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$2 implements Runnable {
    private final VideoRendererEventListener.EventDispatcher arg$1;
    private final Format arg$2;

    VideoRendererEventListener$EventDispatcher$$Lambda$2(VideoRendererEventListener.EventDispatcher eventDispatcher, Format format) {
        this.arg$1 = eventDispatcher;
        this.arg$2 = format;
    }

    public void run() {
        this.arg$1.lambda$inputFormatChanged$2$VideoRendererEventListener$EventDispatcher(this.arg$2);
    }
}
