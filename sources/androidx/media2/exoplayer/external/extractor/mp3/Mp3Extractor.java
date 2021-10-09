package androidx.media2.exoplayer.external.extractor.mp3;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.extractor.Extractor;
import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.extractor.ExtractorOutput;
import androidx.media2.exoplayer.external.extractor.ExtractorsFactory;
import androidx.media2.exoplayer.external.extractor.GaplessInfoHolder;
import androidx.media2.exoplayer.external.extractor.Id3Peeker;
import androidx.media2.exoplayer.external.extractor.MpegAudioHeader;
import androidx.media2.exoplayer.external.extractor.PositionHolder;
import androidx.media2.exoplayer.external.extractor.SeekMap;
import androidx.media2.exoplayer.external.extractor.TrackOutput;
import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.metadata.id3.Id3Decoder;
import androidx.media2.exoplayer.external.metadata.id3.MlltFrame;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;

public final class Mp3Extractor implements Extractor {
    public static final ExtractorsFactory FACTORY = Mp3Extractor$$Lambda$0.$instance;
    private static final Id3Decoder.FramePredicate REQUIRED_ID3_FRAME_PREDICATE = Mp3Extractor$$Lambda$1.$instance;
    private long basisTimeUs;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private final long forcedFirstSampleTimestampUs;
    private final GaplessInfoHolder gaplessInfoHolder;
    private final Id3Peeker id3Peeker;
    private Metadata metadata;
    private int sampleBytesRemaining;
    private long samplesRead;
    private final ParsableByteArray scratch;
    private Seeker seeker;
    private final MpegAudioHeader synchronizedHeader;
    private int synchronizedHeaderData;
    private TrackOutput trackOutput;

    interface Seeker extends SeekMap {
        long getDataEndPosition();

        long getTimeUs(long j);
    }

    private static boolean headersMatch(int i, long j) {
        return ((long) (i & -128000)) == (j & -128000);
    }

    static final /* synthetic */ boolean lambda$static$1$Mp3Extractor(int i, int i2, int i3, int i4, int i5) {
        return (i2 == 67 && i3 == 79 && i4 == 77 && (i5 == 77 || i == 2)) || (i2 == 77 && i3 == 76 && i4 == 76 && (i5 == 84 || i == 2));
    }

    public void release() {
    }

    static final /* synthetic */ Extractor[] lambda$static$0$Mp3Extractor() {
        return new Extractor[]{new Mp3Extractor()};
    }

    public Mp3Extractor() {
        this(0);
    }

    public Mp3Extractor(int i) {
        this(i, -9223372036854775807L);
    }

    public Mp3Extractor(int i, long j) {
        this.flags = i;
        this.forcedFirstSampleTimestampUs = j;
        this.scratch = new ParsableByteArray(10);
        this.synchronizedHeader = new MpegAudioHeader();
        this.gaplessInfoHolder = new GaplessInfoHolder();
        this.basisTimeUs = -9223372036854775807L;
        this.id3Peeker = new Id3Peeker();
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return synchronize(extractorInput, true);
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        this.trackOutput = extractorOutput2.track(0, 1);
        this.extractorOutput.endTracks();
    }

    public void seek(long j, long j2) {
        this.synchronizedHeaderData = 0;
        this.basisTimeUs = -9223372036854775807L;
        this.samplesRead = 0;
        this.sampleBytesRemaining = 0;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        if (this.synchronizedHeaderData == 0) {
            try {
                synchronize(extractorInput, false);
            } catch (EOFException unused) {
                return -1;
            }
        } else {
            ExtractorInput extractorInput2 = extractorInput;
        }
        if (this.seeker == null) {
            Seeker maybeReadSeekFrame = maybeReadSeekFrame(extractorInput);
            MlltSeeker maybeHandleSeekMetadata = maybeHandleSeekMetadata(this.metadata, extractorInput.getPosition());
            if (maybeHandleSeekMetadata != null) {
                this.seeker = maybeHandleSeekMetadata;
            } else if (maybeReadSeekFrame != null) {
                this.seeker = maybeReadSeekFrame;
            }
            Seeker seeker2 = this.seeker;
            if (seeker2 == null || (!seeker2.isSeekable() && (this.flags & 1) != 0)) {
                this.seeker = getConstantBitrateSeeker(extractorInput);
            }
            this.extractorOutput.seekMap(this.seeker);
            this.trackOutput.format(Format.createAudioSampleFormat((String) null, this.synchronizedHeader.mimeType, (String) null, -1, 4096, this.synchronizedHeader.channels, this.synchronizedHeader.sampleRate, -1, this.gaplessInfoHolder.encoderDelay, this.gaplessInfoHolder.encoderPadding, (List<byte[]>) null, (DrmInitData) null, 0, (String) null, (this.flags & 2) != 0 ? null : this.metadata));
        }
        return readSample(extractorInput);
    }

    private int readSample(ExtractorInput extractorInput) throws IOException, InterruptedException {
        if (this.sampleBytesRemaining == 0) {
            extractorInput.resetPeekPosition();
            if (peekEndOfStreamOrHeader(extractorInput)) {
                return -1;
            }
            this.scratch.setPosition(0);
            int readInt = this.scratch.readInt();
            if (!headersMatch(readInt, (long) this.synchronizedHeaderData) || MpegAudioHeader.getFrameSize(readInt) == -1) {
                extractorInput.skipFully(1);
                this.synchronizedHeaderData = 0;
                return 0;
            }
            MpegAudioHeader.populateHeader(readInt, this.synchronizedHeader);
            if (this.basisTimeUs == -9223372036854775807L) {
                this.basisTimeUs = this.seeker.getTimeUs(extractorInput.getPosition());
                if (this.forcedFirstSampleTimestampUs != -9223372036854775807L) {
                    this.basisTimeUs += this.forcedFirstSampleTimestampUs - this.seeker.getTimeUs(0);
                }
            }
            this.sampleBytesRemaining = this.synchronizedHeader.frameSize;
        }
        int sampleData = this.trackOutput.sampleData(extractorInput, this.sampleBytesRemaining, true);
        if (sampleData == -1) {
            return -1;
        }
        int i = this.sampleBytesRemaining - sampleData;
        this.sampleBytesRemaining = i;
        if (i > 0) {
            return 0;
        }
        this.trackOutput.sampleMetadata(this.basisTimeUs + ((this.samplesRead * 1000000) / ((long) this.synchronizedHeader.sampleRate)), 1, this.synchronizedHeader.frameSize, 0, (TrackOutput.CryptoData) null);
        this.samplesRead += (long) this.synchronizedHeader.samplesPerFrame;
        this.sampleBytesRemaining = 0;
        return 0;
    }

    private boolean synchronize(ExtractorInput extractorInput, boolean z) throws IOException, InterruptedException {
        int i;
        int i2;
        int frameSize;
        Id3Decoder.FramePredicate framePredicate;
        int i3 = z ? 16384 : 131072;
        extractorInput.resetPeekPosition();
        if (extractorInput.getPosition() == 0) {
            if ((this.flags & 2) == 0) {
                framePredicate = null;
            } else {
                framePredicate = REQUIRED_ID3_FRAME_PREDICATE;
            }
            Metadata peekId3Data = this.id3Peeker.peekId3Data(extractorInput, framePredicate);
            this.metadata = peekId3Data;
            if (peekId3Data != null) {
                this.gaplessInfoHolder.setFromMetadata(peekId3Data);
            }
            i = (int) extractorInput.getPeekPosition();
            if (!z) {
                extractorInput.skipFully(i);
            }
            i2 = 0;
        } else {
            i2 = 0;
            i = 0;
        }
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (!peekEndOfStreamOrHeader(extractorInput)) {
                this.scratch.setPosition(0);
                int readInt = this.scratch.readInt();
                if ((i2 == 0 || headersMatch(readInt, (long) i2)) && (frameSize = MpegAudioHeader.getFrameSize(readInt)) != -1) {
                    i4++;
                    if (i4 != 1) {
                        if (i4 == 4) {
                            break;
                        }
                    } else {
                        MpegAudioHeader.populateHeader(readInt, this.synchronizedHeader);
                        i2 = readInt;
                    }
                    extractorInput.advancePeekPosition(frameSize - 4);
                } else {
                    int i6 = i5 + 1;
                    if (i5 != i3) {
                        if (z) {
                            extractorInput.resetPeekPosition();
                            extractorInput.advancePeekPosition(i + i6);
                        } else {
                            extractorInput.skipFully(1);
                        }
                        i5 = i6;
                        i2 = 0;
                        i4 = 0;
                    } else if (z) {
                        return false;
                    } else {
                        throw new ParserException("Searched too many bytes.");
                    }
                }
            } else if (i4 <= 0) {
                throw new EOFException();
            }
        }
        if (z) {
            extractorInput.skipFully(i + i5);
        } else {
            extractorInput.resetPeekPosition();
        }
        this.synchronizedHeaderData = i2;
        return true;
    }

    private boolean peekEndOfStreamOrHeader(ExtractorInput extractorInput) throws IOException, InterruptedException {
        Seeker seeker2 = this.seeker;
        if (seeker2 != null) {
            long dataEndPosition = seeker2.getDataEndPosition();
            if (dataEndPosition != -1 && extractorInput.getPeekPosition() > dataEndPosition - 4) {
                return true;
            }
        }
        try {
            return !extractorInput.peekFully(this.scratch.data, 0, 4, true);
        } catch (EOFException unused) {
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0041 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ab A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.media2.exoplayer.external.extractor.mp3.Mp3Extractor.Seeker maybeReadSeekFrame(androidx.media2.exoplayer.external.extractor.ExtractorInput r11) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r10 = this;
            androidx.media2.exoplayer.external.util.ParsableByteArray r5 = new androidx.media2.exoplayer.external.util.ParsableByteArray
            androidx.media2.exoplayer.external.extractor.MpegAudioHeader r0 = r10.synchronizedHeader
            int r0 = r0.frameSize
            r5.<init>((int) r0)
            byte[] r0 = r5.data
            androidx.media2.exoplayer.external.extractor.MpegAudioHeader r1 = r10.synchronizedHeader
            int r1 = r1.frameSize
            r6 = 0
            r11.peekFully(r0, r6, r1)
            androidx.media2.exoplayer.external.extractor.MpegAudioHeader r0 = r10.synchronizedHeader
            int r0 = r0.version
            r1 = 1
            r0 = r0 & r1
            r2 = 21
            if (r0 == 0) goto L_0x0028
            androidx.media2.exoplayer.external.extractor.MpegAudioHeader r0 = r10.synchronizedHeader
            int r0 = r0.channels
            if (r0 == r1) goto L_0x002e
            r2 = 36
            r7 = 36
            goto L_0x0035
        L_0x0028:
            androidx.media2.exoplayer.external.extractor.MpegAudioHeader r0 = r10.synchronizedHeader
            int r0 = r0.channels
            if (r0 == r1) goto L_0x0031
        L_0x002e:
            r7 = 21
            goto L_0x0035
        L_0x0031:
            r2 = 13
            r7 = 13
        L_0x0035:
            int r8 = getSeekFrameHeader(r5, r7)
            r0 = 1483304551(0x58696e67, float:1.02664153E15)
            r9 = 1231971951(0x496e666f, float:976486.94)
            if (r8 == r0) goto L_0x0064
            if (r8 != r9) goto L_0x0044
            goto L_0x0064
        L_0x0044:
            r0 = 1447187017(0x56425249, float:5.3414667E13)
            if (r8 != r0) goto L_0x005f
            long r0 = r11.getLength()
            long r2 = r11.getPosition()
            androidx.media2.exoplayer.external.extractor.MpegAudioHeader r4 = r10.synchronizedHeader
            androidx.media2.exoplayer.external.extractor.mp3.VbriSeeker r0 = androidx.media2.exoplayer.external.extractor.mp3.VbriSeeker.create(r0, r2, r4, r5)
            androidx.media2.exoplayer.external.extractor.MpegAudioHeader r1 = r10.synchronizedHeader
            int r1 = r1.frameSize
            r11.skipFully(r1)
            goto L_0x00b2
        L_0x005f:
            r0 = 0
            r11.resetPeekPosition()
            goto L_0x00b2
        L_0x0064:
            long r0 = r11.getLength()
            long r2 = r11.getPosition()
            androidx.media2.exoplayer.external.extractor.MpegAudioHeader r4 = r10.synchronizedHeader
            androidx.media2.exoplayer.external.extractor.mp3.XingSeeker r0 = androidx.media2.exoplayer.external.extractor.mp3.XingSeeker.create(r0, r2, r4, r5)
            if (r0 == 0) goto L_0x009c
            androidx.media2.exoplayer.external.extractor.GaplessInfoHolder r1 = r10.gaplessInfoHolder
            boolean r1 = r1.hasGaplessInfo()
            if (r1 != 0) goto L_0x009c
            r11.resetPeekPosition()
            int r7 = r7 + 141
            r11.advancePeekPosition(r7)
            androidx.media2.exoplayer.external.util.ParsableByteArray r1 = r10.scratch
            byte[] r1 = r1.data
            r2 = 3
            r11.peekFully(r1, r6, r2)
            androidx.media2.exoplayer.external.util.ParsableByteArray r1 = r10.scratch
            r1.setPosition(r6)
            androidx.media2.exoplayer.external.extractor.GaplessInfoHolder r1 = r10.gaplessInfoHolder
            androidx.media2.exoplayer.external.util.ParsableByteArray r2 = r10.scratch
            int r2 = r2.readUnsignedInt24()
            r1.setFromXingHeaderValue(r2)
        L_0x009c:
            androidx.media2.exoplayer.external.extractor.MpegAudioHeader r1 = r10.synchronizedHeader
            int r1 = r1.frameSize
            r11.skipFully(r1)
            if (r0 == 0) goto L_0x00b2
            boolean r1 = r0.isSeekable()
            if (r1 != 0) goto L_0x00b2
            if (r8 != r9) goto L_0x00b2
            androidx.media2.exoplayer.external.extractor.mp3.Mp3Extractor$Seeker r11 = r10.getConstantBitrateSeeker(r11)
            return r11
        L_0x00b2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.extractor.mp3.Mp3Extractor.maybeReadSeekFrame(androidx.media2.exoplayer.external.extractor.ExtractorInput):androidx.media2.exoplayer.external.extractor.mp3.Mp3Extractor$Seeker");
    }

    private Seeker getConstantBitrateSeeker(ExtractorInput extractorInput) throws IOException, InterruptedException {
        extractorInput.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        MpegAudioHeader.populateHeader(this.scratch.readInt(), this.synchronizedHeader);
        return new ConstantBitrateSeeker(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader);
    }

    private static int getSeekFrameHeader(ParsableByteArray parsableByteArray, int i) {
        if (parsableByteArray.limit() >= i + 4) {
            parsableByteArray.setPosition(i);
            int readInt = parsableByteArray.readInt();
            if (readInt == 1483304551 || readInt == 1231971951) {
                return readInt;
            }
        }
        if (parsableByteArray.limit() < 40) {
            return 0;
        }
        parsableByteArray.setPosition(36);
        return parsableByteArray.readInt() == 1447187017 ? 1447187017 : 0;
    }

    private static MlltSeeker maybeHandleSeekMetadata(Metadata metadata2, long j) {
        if (metadata2 == null) {
            return null;
        }
        int length = metadata2.length();
        for (int i = 0; i < length; i++) {
            Metadata.Entry entry = metadata2.get(i);
            if (entry instanceof MlltFrame) {
                return MlltSeeker.create(j, (MlltFrame) entry);
            }
        }
        return null;
    }
}
