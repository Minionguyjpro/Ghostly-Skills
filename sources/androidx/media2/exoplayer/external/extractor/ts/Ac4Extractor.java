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

public final class Ac4Extractor implements Extractor {
    public static final ExtractorsFactory FACTORY = Ac4Extractor$$Lambda$0.$instance;
    private final long firstSampleTimestampUs;
    private final Ac4Reader reader;
    private final ParsableByteArray sampleData;
    private boolean startedPacket;

    public void release() {
    }

    static final /* synthetic */ Extractor[] lambda$static$0$Ac4Extractor() {
        return new Extractor[]{new Ac4Extractor()};
    }

    public Ac4Extractor() {
        this(0);
    }

    public Ac4Extractor(long j) {
        this.firstSampleTimestampUs = j;
        this.reader = new Ac4Reader();
        this.sampleData = new ParsableByteArray(16384);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0042, code lost:
        if ((r4 - r3) < 8192) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0044, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0039, code lost:
        r9.resetPeekPosition();
        r4 = r4 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean sniff(androidx.media2.exoplayer.external.extractor.ExtractorInput r9) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r8 = this;
            androidx.media2.exoplayer.external.util.ParsableByteArray r0 = new androidx.media2.exoplayer.external.util.ParsableByteArray
            r1 = 10
            r0.<init>((int) r1)
            r2 = 0
            r3 = 0
        L_0x0009:
            byte[] r4 = r0.data
            r9.peekFully(r4, r2, r1)
            r0.setPosition(r2)
            int r4 = r0.readUnsignedInt24()
            r5 = 4801587(0x494433, float:6.728456E-39)
            if (r4 == r5) goto L_0x005f
            r9.resetPeekPosition()
            r9.advancePeekPosition(r3)
            r4 = r3
        L_0x0021:
            r1 = 0
        L_0x0022:
            byte[] r5 = r0.data
            r6 = 7
            r9.peekFully(r5, r2, r6)
            r0.setPosition(r2)
            int r5 = r0.readUnsignedShort()
            r6 = 44096(0xac40, float:6.1792E-41)
            if (r5 == r6) goto L_0x0049
            r6 = 44097(0xac41, float:6.1793E-41)
            if (r5 == r6) goto L_0x0049
            r9.resetPeekPosition()
            int r4 = r4 + 1
            int r1 = r4 - r3
            r5 = 8192(0x2000, float:1.14794E-41)
            if (r1 < r5) goto L_0x0045
            return r2
        L_0x0045:
            r9.advancePeekPosition(r4)
            goto L_0x0021
        L_0x0049:
            r6 = 1
            int r1 = r1 + r6
            r7 = 4
            if (r1 < r7) goto L_0x004f
            return r6
        L_0x004f:
            byte[] r6 = r0.data
            int r5 = androidx.media2.exoplayer.external.audio.Ac4Util.parseAc4SyncframeSize(r6, r5)
            r6 = -1
            if (r5 != r6) goto L_0x0059
            return r2
        L_0x0059:
            int r5 = r5 + -7
            r9.advancePeekPosition(r5)
            goto L_0x0022
        L_0x005f:
            r4 = 3
            r0.skipBytes(r4)
            int r4 = r0.readSynchSafeInt()
            int r5 = r4 + 10
            int r3 = r3 + r5
            r9.advancePeekPosition(r4)
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.extractor.ts.Ac4Extractor.sniff(androidx.media2.exoplayer.external.extractor.ExtractorInput):boolean");
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
        int read = extractorInput.read(this.sampleData.data, 0, 16384);
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
