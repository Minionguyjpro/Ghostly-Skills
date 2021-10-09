package androidx.media2.exoplayer.external.video;

import android.os.Handler;
import android.view.Surface;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.decoder.DecoderCounters;
import androidx.media2.exoplayer.external.util.Assertions;

public interface VideoRendererEventListener {
    void onDroppedFrames(int i, long j);

    void onRenderedFirstFrame(Surface surface);

    void onVideoDecoderInitialized(String str, long j, long j2);

    void onVideoDisabled(DecoderCounters decoderCounters);

    void onVideoEnabled(DecoderCounters decoderCounters);

    void onVideoInputFormatChanged(Format format);

    void onVideoSizeChanged(int i, int i2, int i3, float f);

    public static final class EventDispatcher {
        private final Handler handler;
        private final VideoRendererEventListener listener;

        public EventDispatcher(Handler handler2, VideoRendererEventListener videoRendererEventListener) {
            this.handler = videoRendererEventListener != null ? (Handler) Assertions.checkNotNull(handler2) : null;
            this.listener = videoRendererEventListener;
        }

        public void enabled(DecoderCounters decoderCounters) {
            if (this.listener != null) {
                this.handler.post(new VideoRendererEventListener$EventDispatcher$$Lambda$0(this, decoderCounters));
            }
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$enabled$0$VideoRendererEventListener$EventDispatcher(DecoderCounters decoderCounters) {
            this.listener.onVideoEnabled(decoderCounters);
        }

        public void decoderInitialized(String str, long j, long j2) {
            if (this.listener != null) {
                this.handler.post(new VideoRendererEventListener$EventDispatcher$$Lambda$1(this, str, j, j2));
            }
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$decoderInitialized$1$VideoRendererEventListener$EventDispatcher(String str, long j, long j2) {
            this.listener.onVideoDecoderInitialized(str, j, j2);
        }

        public void inputFormatChanged(Format format) {
            if (this.listener != null) {
                this.handler.post(new VideoRendererEventListener$EventDispatcher$$Lambda$2(this, format));
            }
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$inputFormatChanged$2$VideoRendererEventListener$EventDispatcher(Format format) {
            this.listener.onVideoInputFormatChanged(format);
        }

        public void droppedFrames(int i, long j) {
            if (this.listener != null) {
                this.handler.post(new VideoRendererEventListener$EventDispatcher$$Lambda$3(this, i, j));
            }
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$droppedFrames$3$VideoRendererEventListener$EventDispatcher(int i, long j) {
            this.listener.onDroppedFrames(i, j);
        }

        public void videoSizeChanged(int i, int i2, int i3, float f) {
            if (this.listener != null) {
                this.handler.post(new VideoRendererEventListener$EventDispatcher$$Lambda$4(this, i, i2, i3, f));
            }
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$videoSizeChanged$4$VideoRendererEventListener$EventDispatcher(int i, int i2, int i3, float f) {
            this.listener.onVideoSizeChanged(i, i2, i3, f);
        }

        public void renderedFirstFrame(Surface surface) {
            if (this.listener != null) {
                this.handler.post(new VideoRendererEventListener$EventDispatcher$$Lambda$5(this, surface));
            }
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$renderedFirstFrame$5$VideoRendererEventListener$EventDispatcher(Surface surface) {
            this.listener.onRenderedFirstFrame(surface);
        }

        public void disabled(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            if (this.listener != null) {
                this.handler.post(new VideoRendererEventListener$EventDispatcher$$Lambda$6(this, decoderCounters));
            }
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$disabled$6$VideoRendererEventListener$EventDispatcher(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            this.listener.onVideoDisabled(decoderCounters);
        }
    }
}
