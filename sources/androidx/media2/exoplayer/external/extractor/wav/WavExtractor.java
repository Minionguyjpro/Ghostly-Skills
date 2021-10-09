package androidx.media2.exoplayer.external.extractor.wav;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.extractor.Extractor;
import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.extractor.ExtractorOutput;
import androidx.media2.exoplayer.external.extractor.ExtractorsFactory;
import androidx.media2.exoplayer.external.extractor.PositionHolder;
import androidx.media2.exoplayer.external.extractor.TrackOutput;
import androidx.media2.exoplayer.external.util.Assertions;
import java.io.IOException;
import java.util.List;

public final class WavExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = WavExtractor$$Lambda$0.$instance;
    private int bytesPerFrame;
    private ExtractorOutput extractorOutput;
    private int pendingBytes;
    private TrackOutput trackOutput;
    private WavHeader wavHeader;

    public void release() {
    }

    static final /* synthetic */ Extractor[] lambda$static$0$WavExtractor() {
        return new Extractor[]{new WavExtractor()};
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return WavHeaderReader.peek(extractorInput) != null;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        this.trackOutput = extractorOutput2.track(0, 1);
        this.wavHeader = null;
        extractorOutput2.endTracks();
    }

    public void seek(long j, long j2) {
        this.pendingBytes = 0;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        if (this.wavHeader == null) {
            WavHeader peek = WavHeaderReader.peek(extractorInput);
            this.wavHeader = peek;
            if (peek != null) {
                this.trackOutput.format(Format.createAudioSampleFormat((String) null, "audio/raw", (String) null, peek.getBitrate(), 32768, this.wavHeader.getNumChannels(), this.wavHeader.getSampleRateHz(), this.wavHeader.getEncoding(), (List<byte[]>) null, (DrmInitData) null, 0, (String) null));
                this.bytesPerFrame = this.wavHeader.getBytesPerFrame();
            } else {
                throw new ParserException("Unsupported or unrecognized wav header.");
            }
        }
        if (!this.wavHeader.hasDataBounds()) {
            WavHeaderReader.skipToData(extractorInput, this.wavHeader);
            this.extractorOutput.seekMap(this.wavHeader);
        }
        long dataLimit = this.wavHeader.getDataLimit();
        Assertions.checkState(dataLimit != -1);
        long position = dataLimit - extractorInput.getPosition();
        if (position <= 0) {
            return -1;
        }
        int sampleData = this.trackOutput.sampleData(extractorInput, (int) Math.min((long) (32768 - this.pendingBytes), position), true);
        if (sampleData != -1) {
            this.pendingBytes += sampleData;
        }
        int i = this.pendingBytes / this.bytesPerFrame;
        if (i > 0) {
            long timeUs = this.wavHeader.getTimeUs(extractorInput.getPosition() - ((long) this.pendingBytes));
            int i2 = i * this.bytesPerFrame;
            int i3 = this.pendingBytes - i2;
            this.pendingBytes = i3;
            this.trackOutput.sampleMetadata(timeUs, 1, i2, i3, (TrackOutput.CryptoData) null);
        }
        if (sampleData == -1) {
            return -1;
        }
        return 0;
    }
}
