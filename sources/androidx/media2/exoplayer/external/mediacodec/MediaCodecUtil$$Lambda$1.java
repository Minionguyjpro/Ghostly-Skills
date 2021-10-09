package androidx.media2.exoplayer.external.mediacodec;

import androidx.media2.exoplayer.external.mediacodec.MediaCodecUtil;

final /* synthetic */ class MediaCodecUtil$$Lambda$1 implements MediaCodecUtil.ScoreProvider {
    static final MediaCodecUtil.ScoreProvider $instance = new MediaCodecUtil$$Lambda$1();

    private MediaCodecUtil$$Lambda$1() {
    }

    public int getScore(Object obj) {
        return MediaCodecUtil.lambda$applyWorkarounds$1$MediaCodecUtil((MediaCodecInfo) obj);
    }
}
