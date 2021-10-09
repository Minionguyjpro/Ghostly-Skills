package com.google.android.exoplayer2.extractor.flv;

import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.mopub.mobileads.MoPubView;
import java.io.IOException;

public final class FlvExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = $$Lambda$FlvExtractor$bd1zICO7fFQot_hbozdu7LjVyE.INSTANCE;
    private AudioTagPayloadReader audioReader;
    private int bytesToNextTagHeader;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray headerBuffer = new ParsableByteArray(9);
    private long mediaTagTimestampOffsetUs;
    private final ScriptTagPayloadReader metadataReader = new ScriptTagPayloadReader();
    private boolean outputFirstSample;
    private boolean outputSeekMap;
    private final ParsableByteArray scratch = new ParsableByteArray(4);
    private int state = 1;
    private final ParsableByteArray tagData = new ParsableByteArray();
    private int tagDataSize;
    private final ParsableByteArray tagHeaderBuffer = new ParsableByteArray(11);
    private long tagTimestampUs;
    private int tagType;
    private VideoTagPayloadReader videoReader;

    public void release() {
    }

    static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new FlvExtractor()};
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        extractorInput.peekFully(this.scratch.data, 0, 3);
        this.scratch.setPosition(0);
        if (this.scratch.readUnsignedInt24() != 4607062) {
            return false;
        }
        extractorInput.peekFully(this.scratch.data, 0, 2);
        this.scratch.setPosition(0);
        if ((this.scratch.readUnsignedShort() & MoPubView.MoPubAdSizeInt.HEIGHT_250_INT) != 0) {
            return false;
        }
        extractorInput.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        int readInt = this.scratch.readInt();
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(readInt);
        extractorInput.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        if (this.scratch.readInt() == 0) {
            return true;
        }
        return false;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    public void seek(long j, long j2) {
        this.state = 1;
        this.outputFirstSample = false;
        this.bytesToNextTagHeader = 0;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        while (true) {
            int i = this.state;
            if (i != 1) {
                if (i == 2) {
                    skipToTagHeader(extractorInput);
                } else if (i != 3) {
                    if (i != 4) {
                        throw new IllegalStateException();
                    } else if (readTagData(extractorInput)) {
                        return 0;
                    }
                } else if (!readTagHeader(extractorInput)) {
                    return -1;
                }
            } else if (!readFlvHeader(extractorInput)) {
                return -1;
            }
        }
    }

    private boolean readFlvHeader(ExtractorInput extractorInput) throws IOException, InterruptedException {
        boolean z = false;
        if (!extractorInput.readFully(this.headerBuffer.data, 0, 9, true)) {
            return false;
        }
        this.headerBuffer.setPosition(0);
        this.headerBuffer.skipBytes(4);
        int readUnsignedByte = this.headerBuffer.readUnsignedByte();
        boolean z2 = (readUnsignedByte & 4) != 0;
        if ((readUnsignedByte & 1) != 0) {
            z = true;
        }
        if (z2 && this.audioReader == null) {
            this.audioReader = new AudioTagPayloadReader(this.extractorOutput.track(8, 1));
        }
        if (z && this.videoReader == null) {
            this.videoReader = new VideoTagPayloadReader(this.extractorOutput.track(9, 2));
        }
        this.extractorOutput.endTracks();
        this.bytesToNextTagHeader = (this.headerBuffer.readInt() - 9) + 4;
        this.state = 2;
        return true;
    }

    private void skipToTagHeader(ExtractorInput extractorInput) throws IOException, InterruptedException {
        extractorInput.skipFully(this.bytesToNextTagHeader);
        this.bytesToNextTagHeader = 0;
        this.state = 3;
    }

    private boolean readTagHeader(ExtractorInput extractorInput) throws IOException, InterruptedException {
        if (!extractorInput.readFully(this.tagHeaderBuffer.data, 0, 11, true)) {
            return false;
        }
        this.tagHeaderBuffer.setPosition(0);
        this.tagType = this.tagHeaderBuffer.readUnsignedByte();
        this.tagDataSize = this.tagHeaderBuffer.readUnsignedInt24();
        this.tagTimestampUs = (long) this.tagHeaderBuffer.readUnsignedInt24();
        this.tagTimestampUs = (((long) (this.tagHeaderBuffer.readUnsignedByte() << 24)) | this.tagTimestampUs) * 1000;
        this.tagHeaderBuffer.skipBytes(3);
        this.state = 4;
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean readTagData(com.google.android.exoplayer2.extractor.ExtractorInput r9) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r8 = this;
            long r0 = r8.getCurrentTimestampUs()
            int r2 = r8.tagType
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5 = 0
            r6 = 1
            r7 = 8
            if (r2 != r7) goto L_0x0024
            com.google.android.exoplayer2.extractor.flv.AudioTagPayloadReader r2 = r8.audioReader
            if (r2 == 0) goto L_0x0024
            r8.ensureReadyForMediaOutput()
            com.google.android.exoplayer2.extractor.flv.AudioTagPayloadReader r2 = r8.audioReader
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r8.prepareTagData(r9)
            boolean r5 = r2.consume(r9, r0)
        L_0x0022:
            r9 = 1
            goto L_0x006d
        L_0x0024:
            int r2 = r8.tagType
            r7 = 9
            if (r2 != r7) goto L_0x003c
            com.google.android.exoplayer2.extractor.flv.VideoTagPayloadReader r2 = r8.videoReader
            if (r2 == 0) goto L_0x003c
            r8.ensureReadyForMediaOutput()
            com.google.android.exoplayer2.extractor.flv.VideoTagPayloadReader r2 = r8.videoReader
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r8.prepareTagData(r9)
            boolean r5 = r2.consume(r9, r0)
            goto L_0x0022
        L_0x003c:
            int r2 = r8.tagType
            r7 = 18
            if (r2 != r7) goto L_0x0067
            boolean r2 = r8.outputSeekMap
            if (r2 != 0) goto L_0x0067
            com.google.android.exoplayer2.extractor.flv.ScriptTagPayloadReader r2 = r8.metadataReader
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r8.prepareTagData(r9)
            boolean r5 = r2.consume(r9, r0)
            com.google.android.exoplayer2.extractor.flv.ScriptTagPayloadReader r9 = r8.metadataReader
            long r0 = r9.getDurationUs()
            int r9 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r9 == 0) goto L_0x0022
            com.google.android.exoplayer2.extractor.ExtractorOutput r9 = r8.extractorOutput
            com.google.android.exoplayer2.extractor.SeekMap$Unseekable r2 = new com.google.android.exoplayer2.extractor.SeekMap$Unseekable
            r2.<init>(r0)
            r9.seekMap(r2)
            r8.outputSeekMap = r6
            goto L_0x0022
        L_0x0067:
            int r0 = r8.tagDataSize
            r9.skipFully(r0)
            r9 = 0
        L_0x006d:
            boolean r0 = r8.outputFirstSample
            if (r0 != 0) goto L_0x0087
            if (r5 == 0) goto L_0x0087
            r8.outputFirstSample = r6
            com.google.android.exoplayer2.extractor.flv.ScriptTagPayloadReader r0 = r8.metadataReader
            long r0 = r0.getDurationUs()
            int r2 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r2 != 0) goto L_0x0083
            long r0 = r8.tagTimestampUs
            long r0 = -r0
            goto L_0x0085
        L_0x0083:
            r0 = 0
        L_0x0085:
            r8.mediaTagTimestampOffsetUs = r0
        L_0x0087:
            r0 = 4
            r8.bytesToNextTagHeader = r0
            r0 = 2
            r8.state = r0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.flv.FlvExtractor.readTagData(com.google.android.exoplayer2.extractor.ExtractorInput):boolean");
    }

    private ParsableByteArray prepareTagData(ExtractorInput extractorInput) throws IOException, InterruptedException {
        if (this.tagDataSize > this.tagData.capacity()) {
            ParsableByteArray parsableByteArray = this.tagData;
            parsableByteArray.reset(new byte[Math.max(parsableByteArray.capacity() * 2, this.tagDataSize)], 0);
        } else {
            this.tagData.setPosition(0);
        }
        this.tagData.setLimit(this.tagDataSize);
        extractorInput.readFully(this.tagData.data, 0, this.tagDataSize);
        return this.tagData;
    }

    private void ensureReadyForMediaOutput() {
        if (!this.outputSeekMap) {
            this.extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
            this.outputSeekMap = true;
        }
    }

    private long getCurrentTimestampUs() {
        if (this.outputFirstSample) {
            return this.mediaTagTimestampOffsetUs + this.tagTimestampUs;
        }
        if (this.metadataReader.getDurationUs() == -9223372036854775807L) {
            return 0;
        }
        return this.tagTimestampUs;
    }
}
