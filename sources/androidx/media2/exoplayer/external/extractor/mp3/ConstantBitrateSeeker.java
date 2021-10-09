package androidx.media2.exoplayer.external.extractor.mp3;

import androidx.media2.exoplayer.external.extractor.ConstantBitrateSeekMap;
import androidx.media2.exoplayer.external.extractor.MpegAudioHeader;
import androidx.media2.exoplayer.external.extractor.mp3.Mp3Extractor;

final class ConstantBitrateSeeker extends ConstantBitrateSeekMap implements Mp3Extractor.Seeker {
    public long getDataEndPosition() {
        return -1;
    }

    public ConstantBitrateSeeker(long j, long j2, MpegAudioHeader mpegAudioHeader) {
        super(j, j2, mpegAudioHeader.bitrate, mpegAudioHeader.frameSize);
    }

    public long getTimeUs(long j) {
        return getTimeUsAtPosition(j);
    }
}
