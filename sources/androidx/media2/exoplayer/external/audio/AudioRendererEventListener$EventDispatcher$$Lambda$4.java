package androidx.media2.exoplayer.external.audio;

import androidx.media2.exoplayer.external.audio.AudioRendererEventListener;
import androidx.media2.exoplayer.external.decoder.DecoderCounters;

final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$Lambda$4 implements Runnable {
    private final AudioRendererEventListener.EventDispatcher arg$1;
    private final DecoderCounters arg$2;

    AudioRendererEventListener$EventDispatcher$$Lambda$4(AudioRendererEventListener.EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
        this.arg$1 = eventDispatcher;
        this.arg$2 = decoderCounters;
    }

    public void run() {
        this.arg$1.lambda$disabled$4$AudioRendererEventListener$EventDispatcher(this.arg$2);
    }
}
