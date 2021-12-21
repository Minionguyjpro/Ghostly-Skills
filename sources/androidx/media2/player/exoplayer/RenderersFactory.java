package androidx.media2.player.exoplayer;

import android.content.Context;
import android.os.Handler;
import androidx.media2.exoplayer.external.Renderer;
import androidx.media2.exoplayer.external.audio.AudioRendererEventListener;
import androidx.media2.exoplayer.external.audio.AudioSink;
import androidx.media2.exoplayer.external.audio.MediaCodecAudioRenderer;
import androidx.media2.exoplayer.external.drm.DrmSessionManager;
import androidx.media2.exoplayer.external.drm.FrameworkMediaCrypto;
import androidx.media2.exoplayer.external.mediacodec.MediaCodecSelector;
import androidx.media2.exoplayer.external.metadata.MetadataOutput;
import androidx.media2.exoplayer.external.metadata.MetadataRenderer;
import androidx.media2.exoplayer.external.text.TextOutput;
import androidx.media2.exoplayer.external.video.MediaCodecVideoRenderer;
import androidx.media2.exoplayer.external.video.VideoRendererEventListener;

final class RenderersFactory implements androidx.media2.exoplayer.external.RenderersFactory {
    private final AudioSink mAudioSink;
    private final Context mContext;
    private final TextRenderer mTextRenderer;

    RenderersFactory(Context context, AudioSink audioSink, TextRenderer textRenderer) {
        this.mContext = context;
        this.mAudioSink = audioSink;
        this.mTextRenderer = textRenderer;
    }

    public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager) {
        return new Renderer[]{new MediaCodecVideoRenderer(this.mContext, MediaCodecSelector.DEFAULT, 5000, drmSessionManager, false, handler, videoRendererEventListener, 50), new MediaCodecAudioRenderer(this.mContext, MediaCodecSelector.DEFAULT, drmSessionManager, false, handler, audioRendererEventListener, this.mAudioSink), this.mTextRenderer, new MetadataRenderer(metadataOutput, handler.getLooper(), new Id3MetadataDecoderFactory())};
    }
}
