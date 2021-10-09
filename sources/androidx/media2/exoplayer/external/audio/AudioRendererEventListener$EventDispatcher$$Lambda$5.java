package androidx.media2.exoplayer.external.audio;

import androidx.media2.exoplayer.external.audio.AudioRendererEventListener;

final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$Lambda$5 implements Runnable {
    private final AudioRendererEventListener.EventDispatcher arg$1;
    private final int arg$2;

    AudioRendererEventListener$EventDispatcher$$Lambda$5(AudioRendererEventListener.EventDispatcher eventDispatcher, int i) {
        this.arg$1 = eventDispatcher;
        this.arg$2 = i;
    }

    public void run() {
        this.arg$1.lambda$audioSessionId$5$AudioRendererEventListener$EventDispatcher(this.arg$2);
    }
}
