package androidx.media2.exoplayer.external.extractor.ts;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.audio.Ac4Util;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.extractor.ExtractorOutput;
import androidx.media2.exoplayer.external.extractor.TrackOutput;
import androidx.media2.exoplayer.external.extractor.ts.TsPayloadReader;
import androidx.media2.exoplayer.external.util.ParsableBitArray;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import java.util.List;

public final class Ac4Reader implements ElementaryStreamReader {
    private int bytesRead;
    private Format format;
    private boolean hasCRC;
    private final ParsableBitArray headerScratchBits;
    private final ParsableByteArray headerScratchBytes;
    private final String language;
    private boolean lastByteWasAC;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;
    private String trackFormatId;

    public void packetFinished() {
    }

    public Ac4Reader() {
        this((String) null);
    }

    public Ac4Reader(String str) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(new byte[16]);
        this.headerScratchBits = parsableBitArray;
        this.headerScratchBytes = new ParsableByteArray(parsableBitArray.data);
        this.state = 0;
        this.bytesRead = 0;
        this.lastByteWasAC = false;
        this.hasCRC = false;
        this.language = str;
    }

    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.lastByteWasAC = false;
        this.hasCRC = false;
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.trackFormatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
    }

    public void packetStarted(long j, int i) {
        this.timeUs = j;
    }

    public void consume(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.state;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                        this.output.sampleData(parsableByteArray, min);
                        int i2 = this.bytesRead + min;
                        this.bytesRead = i2;
                        int i3 = this.sampleSize;
                        if (i2 == i3) {
                            this.output.sampleMetadata(this.timeUs, 1, i3, 0, (TrackOutput.CryptoData) null);
                            this.timeUs += this.sampleDurationUs;
                            this.state = 0;
                        }
                    }
                } else if (continueRead(parsableByteArray, this.headerScratchBytes.data, 16)) {
                    parseHeader();
                    this.headerScratchBytes.setPosition(0);
                    this.output.sampleData(this.headerScratchBytes, 16);
                    this.state = 2;
                }
            } else if (skipToNextSync(parsableByteArray)) {
                this.state = 1;
                this.headerScratchBytes.data[0] = -84;
                this.headerScratchBytes.data[1] = (byte) (this.hasCRC ? 65 : 64);
                this.bytesRead = 2;
            }
        }
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i) {
        int min = Math.min(parsableByteArray.bytesLeft(), i - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, min);
        int i2 = this.bytesRead + min;
        this.bytesRead = i2;
        return i2 == i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean skipToNextSync(androidx.media2.exoplayer.external.util.ParsableByteArray r6) {
        /*
            r5 = this;
        L_0x0000:
            int r0 = r6.bytesLeft()
            r1 = 0
            if (r0 <= 0) goto L_0x0031
            boolean r0 = r5.lastByteWasAC
            r2 = 172(0xac, float:2.41E-43)
            r3 = 1
            if (r0 != 0) goto L_0x0018
            int r0 = r6.readUnsignedByte()
            if (r0 != r2) goto L_0x0015
            r1 = 1
        L_0x0015:
            r5.lastByteWasAC = r1
            goto L_0x0000
        L_0x0018:
            int r0 = r6.readUnsignedByte()
            if (r0 != r2) goto L_0x0020
            r2 = 1
            goto L_0x0021
        L_0x0020:
            r2 = 0
        L_0x0021:
            r5.lastByteWasAC = r2
            r2 = 64
            r4 = 65
            if (r0 == r2) goto L_0x002b
            if (r0 != r4) goto L_0x0000
        L_0x002b:
            if (r0 != r4) goto L_0x002e
            r1 = 1
        L_0x002e:
            r5.hasCRC = r1
            return r3
        L_0x0031:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.extractor.ts.Ac4Reader.skipToNextSync(androidx.media2.exoplayer.external.util.ParsableByteArray):boolean");
    }

    private void parseHeader() {
        this.headerScratchBits.setPosition(0);
        Ac4Util.SyncFrameInfo parseAc4SyncframeInfo = Ac4Util.parseAc4SyncframeInfo(this.headerScratchBits);
        if (this.format == null || parseAc4SyncframeInfo.channelCount != this.format.channelCount || parseAc4SyncframeInfo.sampleRate != this.format.sampleRate || !"audio/ac4".equals(this.format.sampleMimeType)) {
            Format createAudioSampleFormat = Format.createAudioSampleFormat(this.trackFormatId, "audio/ac4", (String) null, -1, -1, parseAc4SyncframeInfo.channelCount, parseAc4SyncframeInfo.sampleRate, (List<byte[]>) null, (DrmInitData) null, 0, this.language);
            this.format = createAudioSampleFormat;
            this.output.format(createAudioSampleFormat);
        }
        this.sampleSize = parseAc4SyncframeInfo.frameSize;
        this.sampleDurationUs = (((long) parseAc4SyncframeInfo.sampleCount) * 1000000) / ((long) this.format.sampleRate);
    }
}
