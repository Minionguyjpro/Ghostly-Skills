package androidx.media2.exoplayer.external.extractor.ts;

import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.extractor.ConstantBitrateSeekMap;
import androidx.media2.exoplayer.external.extractor.Extractor;
import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.extractor.ExtractorOutput;
import androidx.media2.exoplayer.external.extractor.ExtractorsFactory;
import androidx.media2.exoplayer.external.extractor.PositionHolder;
import androidx.media2.exoplayer.external.extractor.SeekMap;
import androidx.media2.exoplayer.external.extractor.ts.TsPayloadReader;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.ParsableBitArray;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import java.io.IOException;

public final class AdtsExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = AdtsExtractor$$Lambda$0.$instance;
    private int averageFrameSize;
    private ExtractorOutput extractorOutput;
    private long firstFramePosition;
    private long firstSampleTimestampUs;
    private final long firstStreamSampleTimestampUs;
    private final int flags;
    private boolean hasCalculatedAverageFrameSize;
    private boolean hasOutputSeekMap;
    private final ParsableByteArray packetBuffer;
    private final AdtsReader reader;
    private final ParsableByteArray scratch;
    private final ParsableBitArray scratchBits;
    private boolean startedPacket;

    public void release() {
    }

    static final /* synthetic */ Extractor[] lambda$static$0$AdtsExtractor() {
        return new Extractor[]{new AdtsExtractor()};
    }

    public AdtsExtractor() {
        this(0);
    }

    public AdtsExtractor(long j) {
        this(j, 0);
    }

    public AdtsExtractor(long j, int i) {
        this.firstStreamSampleTimestampUs = j;
        this.firstSampleTimestampUs = j;
        this.flags = i;
        this.reader = new AdtsReader(true);
        this.packetBuffer = new ParsableByteArray(2048);
        this.averageFrameSize = -1;
        this.firstFramePosition = -1;
        ParsableByteArray parsableByteArray = new ParsableByteArray(10);
        this.scratch = parsableByteArray;
        this.scratchBits = new ParsableBitArray(parsableByteArray.data);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0021, code lost:
        r9.resetPeekPosition();
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x002a, code lost:
        if ((r3 - r0) < 8192) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002c, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean sniff(androidx.media2.exoplayer.external.extractor.ExtractorInput r9) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r8 = this;
            int r0 = r8.peekId3Header(r9)
            r1 = 0
            r3 = r0
        L_0x0006:
            r2 = 0
            r4 = 0
        L_0x0008:
            androidx.media2.exoplayer.external.util.ParsableByteArray r5 = r8.scratch
            byte[] r5 = r5.data
            r6 = 2
            r9.peekFully(r5, r1, r6)
            androidx.media2.exoplayer.external.util.ParsableByteArray r5 = r8.scratch
            r5.setPosition(r1)
            androidx.media2.exoplayer.external.util.ParsableByteArray r5 = r8.scratch
            int r5 = r5.readUnsignedShort()
            boolean r5 = androidx.media2.exoplayer.external.extractor.ts.AdtsReader.isAdtsSyncWord(r5)
            if (r5 != 0) goto L_0x0031
            r9.resetPeekPosition()
            int r3 = r3 + 1
            int r2 = r3 - r0
            r4 = 8192(0x2000, float:1.14794E-41)
            if (r2 < r4) goto L_0x002d
            return r1
        L_0x002d:
            r9.advancePeekPosition(r3)
            goto L_0x0006
        L_0x0031:
            r5 = 1
            int r2 = r2 + r5
            r6 = 4
            if (r2 < r6) goto L_0x003b
            r7 = 188(0xbc, float:2.63E-43)
            if (r4 <= r7) goto L_0x003b
            return r5
        L_0x003b:
            androidx.media2.exoplayer.external.util.ParsableByteArray r5 = r8.scratch
            byte[] r5 = r5.data
            r9.peekFully(r5, r1, r6)
            androidx.media2.exoplayer.external.util.ParsableBitArray r5 = r8.scratchBits
            r6 = 14
            r5.setPosition(r6)
            androidx.media2.exoplayer.external.util.ParsableBitArray r5 = r8.scratchBits
            r6 = 13
            int r5 = r5.readBits(r6)
            r6 = 6
            if (r5 > r6) goto L_0x0055
            return r1
        L_0x0055:
            int r6 = r5 + -6
            r9.advancePeekPosition(r6)
            int r4 = r4 + r5
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.extractor.ts.AdtsExtractor.sniff(androidx.media2.exoplayer.external.extractor.ExtractorInput):boolean");
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        this.reader.createTracks(extractorOutput2, new TsPayloadReader.TrackIdGenerator(0, 1));
        extractorOutput2.endTracks();
    }

    public void seek(long j, long j2) {
        this.startedPacket = false;
        this.reader.seek();
        this.firstSampleTimestampUs = this.firstStreamSampleTimestampUs + j2;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        long length = extractorInput.getLength();
        boolean z = ((this.flags & 1) == 0 || length == -1) ? false : true;
        if (z) {
            calculateAverageFrameSize(extractorInput);
        }
        int read = extractorInput.read(this.packetBuffer.data, 0, 2048);
        boolean z2 = read == -1;
        maybeOutputSeekMap(length, z, z2);
        if (z2) {
            return -1;
        }
        this.packetBuffer.setPosition(0);
        this.packetBuffer.setLimit(read);
        if (!this.startedPacket) {
            this.reader.packetStarted(this.firstSampleTimestampUs, 4);
            this.startedPacket = true;
        }
        this.reader.consume(this.packetBuffer);
        return 0;
    }

    private int peekId3Header(ExtractorInput extractorInput) throws IOException, InterruptedException {
        int i = 0;
        while (true) {
            extractorInput.peekFully(this.scratch.data, 0, 10);
            this.scratch.setPosition(0);
            if (this.scratch.readUnsignedInt24() != 4801587) {
                break;
            }
            this.scratch.skipBytes(3);
            int readSynchSafeInt = this.scratch.readSynchSafeInt();
            i += readSynchSafeInt + 10;
            extractorInput.advancePeekPosition(readSynchSafeInt);
        }
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i);
        if (this.firstFramePosition == -1) {
            this.firstFramePosition = (long) i;
        }
        return i;
    }

    private void maybeOutputSeekMap(long j, boolean z, boolean z2) {
        if (!this.hasOutputSeekMap) {
            boolean z3 = z && this.averageFrameSize > 0;
            if (!z3 || this.reader.getSampleDurationUs() != -9223372036854775807L || z2) {
                ExtractorOutput extractorOutput2 = (ExtractorOutput) Assertions.checkNotNull(this.extractorOutput);
                if (!z3 || this.reader.getSampleDurationUs() == -9223372036854775807L) {
                    extractorOutput2.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
                } else {
                    extractorOutput2.seekMap(getConstantBitrateSeekMap(j));
                }
                this.hasOutputSeekMap = true;
            }
        }
    }

    private void calculateAverageFrameSize(ExtractorInput extractorInput) throws IOException, InterruptedException {
        if (!this.hasCalculatedAverageFrameSize) {
            this.averageFrameSize = -1;
            extractorInput.resetPeekPosition();
            long j = 0;
            if (extractorInput.getPosition() == 0) {
                peekId3Header(extractorInput);
            }
            int i = 0;
            int i2 = 0;
            while (true) {
                if (!extractorInput.peekFully(this.scratch.data, 0, 2, true)) {
                    break;
                }
                this.scratch.setPosition(0);
                if (!AdtsReader.isAdtsSyncWord(this.scratch.readUnsignedShort())) {
                    break;
                } else if (!extractorInput.peekFully(this.scratch.data, 0, 4, true)) {
                    break;
                } else {
                    this.scratchBits.setPosition(14);
                    int readBits = this.scratchBits.readBits(13);
                    if (readBits > 6) {
                        j += (long) readBits;
                        i2++;
                        if (i2 != 1000) {
                            if (!extractorInput.advancePeekPosition(readBits - 6, true)) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        this.hasCalculatedAverageFrameSize = true;
                        throw new ParserException("Malformed ADTS stream");
                    }
                }
            }
            i = i2;
            extractorInput.resetPeekPosition();
            if (i > 0) {
                this.averageFrameSize = (int) (j / ((long) i));
            } else {
                this.averageFrameSize = -1;
            }
            this.hasCalculatedAverageFrameSize = true;
        }
    }

    private SeekMap getConstantBitrateSeekMap(long j) {
        return new ConstantBitrateSeekMap(j, this.firstFramePosition, getBitrateFromFrameSize(this.averageFrameSize, this.reader.getSampleDurationUs()), this.averageFrameSize);
    }

    private static int getBitrateFromFrameSize(int i, long j) {
        return (int) ((((long) (i * 8)) * 1000000) / j);
    }
}
