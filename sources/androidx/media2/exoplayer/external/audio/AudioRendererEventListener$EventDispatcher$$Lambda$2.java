package androidx.media2.exoplayer.external.audio;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.audio.AudioRendererEventListener;

final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$Lambda$2 implements Runnable {
    private final AudioRendererEventListener.EventDispatcher arg$1;
    private final Format arg$2;

    AudioRendererEventListener$EventDispatcher$$Lambda$2(AudioRendererEventListener.EventDispatcher eventDispatcher, Format format) {
        this.arg$1 = eventDispatcher;
        this.arg$2 = format;
    }

    public void run() {
        this.arg$1.lambda$inputFormatChanged$2$AudioRendererEventListener$EventDispatcher(this.arg$2);
    }
}
