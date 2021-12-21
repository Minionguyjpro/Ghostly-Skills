package androidx.media2.exoplayer.external.extractor.flv;

import android.util.Pair;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.extractor.TrackOutput;
import androidx.media2.exoplayer.external.extractor.flv.TagPayloadReader;
import androidx.media2.exoplayer.external.util.CodecSpecificDataUtil;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import java.util.Collections;
import java.util.List;

final class AudioTagPayloadReader extends TagPayloadReader {
    private static final int[] AUDIO_SAMPLING_RATE_TABLE = {5512, 11025, 22050, 44100};
    private int audioFormat;
    private boolean hasOutputFormat;
    private boolean hasParsedAudioDataHeader;

    public AudioTagPayloadReader(TrackOutput trackOutput) {
        super(trackOutput);
    }

    /* access modifiers changed from: protected */
    public boolean parseHeader(ParsableByteArray parsableByteArray) throws TagPayloadReader.UnsupportedFormatException {
        if (!this.hasParsedAudioDataHeader) {
            int readUnsignedByte = parsableByteArray.readUnsignedByte();
            int i = (readUnsignedByte >> 4) & 15;
            this.audioFormat = i;
            if (i == 2) {
                this.output.format(Format.createAudioSampleFormat((String) null, "audio/mpeg", (String) null, -1, -1, 1, AUDIO_SAMPLING_RATE_TABLE[(readUnsignedByte >> 2) & 3], (List<byte[]>) null, (DrmInitData) null, 0, (String) null));
                this.hasOutputFormat = true;
            } else if (i == 7 || i == 8) {
                this.output.format(Format.createAudioSampleFormat((String) null, this.audioFormat == 7 ? "audio/g711-alaw" : "audio/g711-mlaw", (String) null, -1, -1, 1, 8000, (readUnsignedByte & 1) == 1 ? 2 : 3, (List<byte[]>) null, (DrmInitData) null, 0, (String) null));
                this.hasOutputFormat = true;
            } else if (i != 10) {
                int i2 = this.audioFormat;
                StringBuilder sb = new StringBuilder(39);
                sb.append("Audio format not supported: ");
                sb.append(i2);
                throw new TagPayloadReader.UnsupportedFormatException(sb.toString());
            }
            this.hasParsedAudioDataHeader = true;
        } else {
            parsableByteArray.skipBytes(1);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void parsePayload(ParsableByteArray parsableByteArray, long j) throws ParserException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        if (this.audioFormat == 2) {
            int bytesLeft = parsableByteArray.bytesLeft();
            this.output.sampleData(parsableByteArray2, bytesLeft);
            this.output.sampleMetadata(j, 1, bytesLeft, 0, (TrackOutput.CryptoData) null);
            return;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if (readUnsignedByte == 0 && !this.hasOutputFormat) {
            int bytesLeft2 = parsableByteArray.bytesLeft();
            byte[] bArr = new byte[bytesLeft2];
            parsableByteArray2.readBytes(bArr, 0, bytesLeft2);
            Pair<Integer, Integer> parseAacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArr);
            this.output.format(Format.createAudioSampleFormat((String) null, "audio/mp4a-latm", (String) null, -1, -1, ((Integer) parseAacAudioSpecificConfig.second).intValue(), ((Integer) parseAacAudioSpecificConfig.first).intValue(), Collections.singletonList(bArr), (DrmInitData) null, 0, (String) null));
            this.hasOutputFormat = true;
        } else if (this.audioFormat != 10 || readUnsignedByte == 1) {
            int bytesLeft3 = parsableByteArray.bytesLeft();
            this.output.sampleData(parsableByteArray2, bytesLeft3);
            this.output.sampleMetadata(j, 1, bytesLeft3, 0, (TrackOutput.CryptoData) null);
        }
    }
}
