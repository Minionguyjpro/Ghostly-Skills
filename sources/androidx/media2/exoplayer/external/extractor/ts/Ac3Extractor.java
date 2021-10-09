package androidx.media2.exoplayer.external.extractor.ts;

import androidx.media2.exoplayer.external.extractor.Extractor;
import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.extractor.ExtractorOutput;
import androidx.media2.exoplayer.external.extractor.ExtractorsFactory;
import androidx.media2.exoplayer.external.extractor.PositionHolder;
import androidx.media2.exoplayer.external.extractor.SeekMap;
import androidx.media2.exoplayer.external.extractor.ts.TsPayloadReader;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import java.io.IOException;

public final class Ac3Extractor implements Extractor {
    public static final ExtractorsFactory FACTORY = Ac3Extractor$$Lambda$0.$instance;
    private final long firstSampleTimestampUs;
    private final Ac3Reader reader;
    private final ParsableByteArray sampleData;
    private boolean startedPacket;

    public void release() {
    }

    static final /* synthetic */ Extractor[] lambda$static$0$Ac3Extractor() {
        return new Extractor[]{new Ac3Extractor()};
    }

    public Ac3Extractor() {
        this(0);
    }

    public Ac3Extractor(long j) {
        this.firstSampleTimestampUs = j;
        this.reader = new Ac3Reader();
        this.sampleData = new ParsableByteArray(2786);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0033, code lost:
        r8.resetPeekPosition();
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003c, code lost:
        if ((r4 - r3) < 8192) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003e, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean sniff(androidx.media2.exoplayer.external.extractor.ExtractorInput r8) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r7 = this;
            androidx.media2.exoplayer.external.util.ParsableByteArray r0 = new androidx.media2.exoplayer.external.util.ParsableByteArray
            r1 = 10
            r0.<init>((int) r1)
            r2 = 0
            r3 = 0
        L_0x0009:
            byte[] r4 = r0.data
            r8.peekFully(r4, r2, r1)
            r0.setPosition(r2)
            int r4 = r0.readUnsignedInt24()
            r5 = 4801587(0x494433, float:6.728456E-39)
            if (r4 == r5) goto L_0x0059
            r8.resetPeekPosition()
            r8.advancePeekPosition(r3)
            r4 = r3
        L_0x0021:
            r1 = 0
        L_0x0022:
            byte[] r5 = r0.data
            r6 = 6
            r8.peekFully(r5, r2, r6)
            r0.setPosition(r2)
            int r5 = r0.readUnsignedShort()
            r6 = 2935(0xb77, float:4.113E-42)
            if (r5 == r6) goto L_0x0043
            r8.resetPeekPosition()
            int r4 = r4 + 1
            int r1 = r4 - r3
            r5 = 8192(0x2000, float:1.14794E-41)
            if (r1 < r5) goto L_0x003f
            return r2
        L_0x003f:
            r8.advancePeekPosition(r4)
            goto L_0x0021
        L_0x0043:
            r5 = 1
            int r1 = r1 + r5
            r6 = 4
            if (r1 < r6) goto L_0x0049
            return r5
        L_0x0049:
            byte[] r5 = r0.data
            int r5 = androidx.media2.exoplayer.external.audio.Ac3Util.parseAc3SyncframeSize(r5)
            r6 = -1
            if (r5 != r6) goto L_0x0053
            return r2
        L_0x0053:
            int r5 = r5 + -6
            r8.advancePeekPosition(r5)
            goto L_0x0022
        L_0x0059:
            r4 = 3
            r0.skipBytes(r4)
            int r4 = r0.readSynchSafeInt()
            int r5 = r4 + 10
            int r3 = r3 + r5
            r8.advancePeekPosition(r4)
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.extractor.ts.Ac3Extractor.sniff(androidx.media2.exoplayer.external.extractor.ExtractorInput):boolean");
    }

    public void init(ExtractorOutput extractorOutput) {
        this.reader.createTracks(extractorOutput, new TsPayloadReader.TrackIdGenerator(0, 1));
        extractorOutput.endTracks();
        extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
    }

    public void seek(long j, long j2) {
        this.startedPacket = false;
        this.reader.seek();
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        int read = extractorInput.read(this.sampleData.data, 0, 2786);
        if (read == -1) {
            return -1;
        }
        this.sampleData.setPosition(0);
        this.sampleData.setLimit(read);
        if (!this.startedPacket) {
            this.reader.packetStarted(this.firstSampleTimestampUs, 4);
            this.startedPacket = true;
        }
        this.reader.consume(this.sampleData);
        return 0;
    }
}
