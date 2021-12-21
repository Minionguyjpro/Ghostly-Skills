package androidx.media2.exoplayer.external.extractor.mkv;

import android.util.Pair;
import android.util.SparseArray;
import androidx.media2.exoplayer.external.C;
import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.audio.Ac3Util;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.extractor.ChunkIndex;
import androidx.media2.exoplayer.external.extractor.Extractor;
import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.extractor.ExtractorOutput;
import androidx.media2.exoplayer.external.extractor.ExtractorsFactory;
import androidx.media2.exoplayer.external.extractor.PositionHolder;
import androidx.media2.exoplayer.external.extractor.SeekMap;
import androidx.media2.exoplayer.external.extractor.TrackOutput;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.util.LongArray;
import androidx.media2.exoplayer.external.util.NalUnitUtil;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import androidx.media2.exoplayer.external.util.Util;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class MatroskaExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = MatroskaExtractor$$Lambda$0.$instance;
    /* access modifiers changed from: private */
    public static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final byte[] SSA_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final byte[] SUBRIP_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    /* access modifiers changed from: private */
    public static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private long blockDurationUs;
    private int blockFlags;
    private int blockLacingSampleCount;
    private int blockLacingSampleIndex;
    private int[] blockLacingSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private boolean sampleRead;
    private boolean sampleSeenReferenceBlock;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final ParsableByteArray subtitleSample;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    /* access modifiers changed from: protected */
    public int getElementType(int i) {
        switch (i) {
            case 131:
            case 136:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case 215:
            case 231:
            case 241:
            case 251:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21930:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 22186:
            case 22203:
            case 25188:
            case 30321:
            case 2352003:
            case 2807729:
                return 2;
            case 134:
            case 17026:
            case 21358:
            case 2274716:
                return 3;
            case 160:
            case 174:
            case 183:
            case 187:
            case 224:
            case 225:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case 161:
            case 163:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
            case 30323:
            case 30324:
            case 30325:
                return 5;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isLevel1Element(int i) {
        return i == 357149030 || i == 524531317 || i == 475249515 || i == 374648427;
    }

    public final void release() {
    }

    static final /* synthetic */ Extractor[] lambda$static$0$MatroskaExtractor() {
        return new Extractor[]{new MatroskaExtractor()};
    }

    public MatroskaExtractor() {
        this(0);
    }

    public MatroskaExtractor(int i) {
        this(new DefaultEbmlReader(), i);
    }

    MatroskaExtractor(EbmlReader ebmlReader, int i) {
        this.segmentContentPosition = -1;
        this.timecodeScale = -9223372036854775807L;
        this.durationTimecode = -9223372036854775807L;
        this.durationUs = -9223372036854775807L;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = -9223372036854775807L;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlProcessor());
        this.seekForCuesEnabled = (i & 1) != 0 ? false : true;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
    }

    public final boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return new Sniffer().sniff(extractorInput);
    }

    public final void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    public void seek(long j, long j2) {
        this.clusterTimecodeUs = -9223372036854775807L;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetSample();
        for (int i = 0; i < this.tracks.size(); i++) {
            this.tracks.valueAt(i).reset();
        }
    }

    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        this.sampleRead = false;
        boolean z = true;
        while (z && !this.sampleRead) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        for (int i = 0; i < this.tracks.size(); i++) {
            this.tracks.valueAt(i).outputPendingSampleMetadata();
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public void startMasterElement(int i, long j, long j2) throws ParserException {
        if (i == 160) {
            this.sampleSeenReferenceBlock = false;
        } else if (i == 174) {
            this.currentTrack = new Track();
        } else if (i == 187) {
            this.seenClusterPositionForCurrentCuePoint = false;
        } else if (i == 19899) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1;
        } else if (i == 20533) {
            this.currentTrack.hasContentEncryption = true;
        } else if (i == 21968) {
            this.currentTrack.hasColorInfo = true;
        } else if (i == 408125543) {
            long j3 = this.segmentContentPosition;
            if (j3 == -1 || j3 == j) {
                this.segmentContentPosition = j;
                this.segmentContentSize = j2;
                return;
            }
            throw new ParserException("Multiple Segment elements not supported");
        } else if (i == 475249515) {
            this.cueTimesUs = new LongArray();
            this.cueClusterPositions = new LongArray();
        } else if (i != 524531317 || this.sentSeekMap) {
        } else {
            if (!this.seekForCuesEnabled || this.cuesContentPosition == -1) {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.sentSeekMap = true;
                return;
            }
            this.seekForCues = true;
        }
    }

    /* access modifiers changed from: protected */
    public void endMasterElement(int i) throws ParserException {
        if (i != 160) {
            if (i == 174) {
                if (isCodecSupported(this.currentTrack.codecId)) {
                    Track track = this.currentTrack;
                    track.initializeOutput(this.extractorOutput, track.number);
                    this.tracks.put(this.currentTrack.number, this.currentTrack);
                }
                this.currentTrack = null;
            } else if (i == 19899) {
                int i2 = this.seekEntryId;
                if (i2 != -1) {
                    long j = this.seekEntryPosition;
                    if (j != -1) {
                        if (i2 == 475249515) {
                            this.cuesContentPosition = j;
                            return;
                        }
                        return;
                    }
                }
                throw new ParserException("Mandatory element SeekID or SeekPosition not found");
            } else if (i != 25152) {
                if (i != 28032) {
                    if (i == 357149030) {
                        if (this.timecodeScale == -9223372036854775807L) {
                            this.timecodeScale = 1000000;
                        }
                        long j2 = this.durationTimecode;
                        if (j2 != -9223372036854775807L) {
                            this.durationUs = scaleTimecodeToUs(j2);
                        }
                    } else if (i != 374648427) {
                        if (i == 475249515 && !this.sentSeekMap) {
                            this.extractorOutput.seekMap(buildSeekMap());
                            this.sentSeekMap = true;
                        }
                    } else if (this.tracks.size() != 0) {
                        this.extractorOutput.endTracks();
                    } else {
                        throw new ParserException("No valid tracks were found");
                    }
                } else if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                    throw new ParserException("Combining encryption and compression is not supported");
                }
            } else if (!this.currentTrack.hasContentEncryption) {
            } else {
                if (this.currentTrack.cryptoData != null) {
                    this.currentTrack.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C.UUID_NIL, "video/webm", this.currentTrack.cryptoData.encryptionKey));
                    return;
                }
                throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
            }
        } else if (this.blockState == 2) {
            if (!this.sampleSeenReferenceBlock) {
                this.blockFlags |= 1;
            }
            commitSampleToOutput(this.tracks.get(this.blockTrackNumber), this.blockTimeUs);
            this.blockState = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void integerElement(int i, long j) throws ParserException {
        if (i != 20529) {
            if (i != 20530) {
                boolean z = false;
                switch (i) {
                    case 131:
                        this.currentTrack.type = (int) j;
                        return;
                    case 136:
                        Track track = this.currentTrack;
                        if (j == 1) {
                            z = true;
                        }
                        track.flagDefault = z;
                        return;
                    case 155:
                        this.blockDurationUs = scaleTimecodeToUs(j);
                        return;
                    case 159:
                        this.currentTrack.channelCount = (int) j;
                        return;
                    case 176:
                        this.currentTrack.width = (int) j;
                        return;
                    case 179:
                        this.cueTimesUs.add(scaleTimecodeToUs(j));
                        return;
                    case 186:
                        this.currentTrack.height = (int) j;
                        return;
                    case 215:
                        this.currentTrack.number = (int) j;
                        return;
                    case 231:
                        this.clusterTimecodeUs = scaleTimecodeToUs(j);
                        return;
                    case 241:
                        if (!this.seenClusterPositionForCurrentCuePoint) {
                            this.cueClusterPositions.add(j);
                            this.seenClusterPositionForCurrentCuePoint = true;
                            return;
                        }
                        return;
                    case 251:
                        this.sampleSeenReferenceBlock = true;
                        return;
                    case 16980:
                        if (j != 3) {
                            StringBuilder sb = new StringBuilder(50);
                            sb.append("ContentCompAlgo ");
                            sb.append(j);
                            sb.append(" not supported");
                            throw new ParserException(sb.toString());
                        }
                        return;
                    case 17029:
                        if (j < 1 || j > 2) {
                            StringBuilder sb2 = new StringBuilder(53);
                            sb2.append("DocTypeReadVersion ");
                            sb2.append(j);
                            sb2.append(" not supported");
                            throw new ParserException(sb2.toString());
                        }
                        return;
                    case 17143:
                        if (j != 1) {
                            StringBuilder sb3 = new StringBuilder(50);
                            sb3.append("EBMLReadVersion ");
                            sb3.append(j);
                            sb3.append(" not supported");
                            throw new ParserException(sb3.toString());
                        }
                        return;
                    case 18401:
                        if (j != 5) {
                            StringBuilder sb4 = new StringBuilder(49);
                            sb4.append("ContentEncAlgo ");
                            sb4.append(j);
                            sb4.append(" not supported");
                            throw new ParserException(sb4.toString());
                        }
                        return;
                    case 18408:
                        if (j != 1) {
                            StringBuilder sb5 = new StringBuilder(56);
                            sb5.append("AESSettingsCipherMode ");
                            sb5.append(j);
                            sb5.append(" not supported");
                            throw new ParserException(sb5.toString());
                        }
                        return;
                    case 21420:
                        this.seekEntryPosition = j + this.segmentContentPosition;
                        return;
                    case 21432:
                        int i2 = (int) j;
                        if (i2 == 0) {
                            this.currentTrack.stereoMode = 0;
                            return;
                        } else if (i2 == 1) {
                            this.currentTrack.stereoMode = 2;
                            return;
                        } else if (i2 == 3) {
                            this.currentTrack.stereoMode = 1;
                            return;
                        } else if (i2 == 15) {
                            this.currentTrack.stereoMode = 3;
                            return;
                        } else {
                            return;
                        }
                    case 21680:
                        this.currentTrack.displayWidth = (int) j;
                        return;
                    case 21682:
                        this.currentTrack.displayUnit = (int) j;
                        return;
                    case 21690:
                        this.currentTrack.displayHeight = (int) j;
                        return;
                    case 21930:
                        Track track2 = this.currentTrack;
                        if (j == 1) {
                            z = true;
                        }
                        track2.flagForced = z;
                        return;
                    case 22186:
                        this.currentTrack.codecDelayNs = j;
                        return;
                    case 22203:
                        this.currentTrack.seekPreRollNs = j;
                        return;
                    case 25188:
                        this.currentTrack.audioBitDepth = (int) j;
                        return;
                    case 30321:
                        int i3 = (int) j;
                        if (i3 == 0) {
                            this.currentTrack.projectionType = 0;
                            return;
                        } else if (i3 == 1) {
                            this.currentTrack.projectionType = 1;
                            return;
                        } else if (i3 == 2) {
                            this.currentTrack.projectionType = 2;
                            return;
                        } else if (i3 == 3) {
                            this.currentTrack.projectionType = 3;
                            return;
                        } else {
                            return;
                        }
                    case 2352003:
                        this.currentTrack.defaultSampleDurationNs = (int) j;
                        return;
                    case 2807729:
                        this.timecodeScale = j;
                        return;
                    default:
                        switch (i) {
                            case 21945:
                                int i4 = (int) j;
                                if (i4 == 1) {
                                    this.currentTrack.colorRange = 2;
                                    return;
                                } else if (i4 == 2) {
                                    this.currentTrack.colorRange = 1;
                                    return;
                                } else {
                                    return;
                                }
                            case 21946:
                                int i5 = (int) j;
                                if (i5 != 1) {
                                    if (i5 == 16) {
                                        this.currentTrack.colorTransfer = 6;
                                        return;
                                    } else if (i5 == 18) {
                                        this.currentTrack.colorTransfer = 7;
                                        return;
                                    } else if (!(i5 == 6 || i5 == 7)) {
                                        return;
                                    }
                                }
                                this.currentTrack.colorTransfer = 3;
                                return;
                            case 21947:
                                this.currentTrack.hasColorInfo = true;
                                int i6 = (int) j;
                                if (i6 == 1) {
                                    this.currentTrack.colorSpace = 1;
                                    return;
                                } else if (i6 == 9) {
                                    this.currentTrack.colorSpace = 6;
                                    return;
                                } else if (i6 == 4 || i6 == 5 || i6 == 6 || i6 == 7) {
                                    this.currentTrack.colorSpace = 2;
                                    return;
                                } else {
                                    return;
                                }
                            case 21948:
                                this.currentTrack.maxContentLuminance = (int) j;
                                return;
                            case 21949:
                                this.currentTrack.maxFrameAverageLuminance = (int) j;
                                return;
                            default:
                                return;
                        }
                }
            } else if (j != 1) {
                StringBuilder sb6 = new StringBuilder(55);
                sb6.append("ContentEncodingScope ");
                sb6.append(j);
                sb6.append(" not supported");
                throw new ParserException(sb6.toString());
            }
        } else if (j != 0) {
            StringBuilder sb7 = new StringBuilder(55);
            sb7.append("ContentEncodingOrder ");
            sb7.append(j);
            sb7.append(" not supported");
            throw new ParserException(sb7.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void floatElement(int i, double d) throws ParserException {
        if (i == 181) {
            this.currentTrack.sampleRate = (int) d;
        } else if (i != 17545) {
            switch (i) {
                case 21969:
                    this.currentTrack.primaryRChromaticityX = (float) d;
                    return;
                case 21970:
                    this.currentTrack.primaryRChromaticityY = (float) d;
                    return;
                case 21971:
                    this.currentTrack.primaryGChromaticityX = (float) d;
                    return;
                case 21972:
                    this.currentTrack.primaryGChromaticityY = (float) d;
                    return;
                case 21973:
                    this.currentTrack.primaryBChromaticityX = (float) d;
                    return;
                case 21974:
                    this.currentTrack.primaryBChromaticityY = (float) d;
                    return;
                case 21975:
                    this.currentTrack.whitePointChromaticityX = (float) d;
                    return;
                case 21976:
                    this.currentTrack.whitePointChromaticityY = (float) d;
                    return;
                case 21977:
                    this.currentTrack.maxMasteringLuminance = (float) d;
                    return;
                case 21978:
                    this.currentTrack.minMasteringLuminance = (float) d;
                    return;
                default:
                    switch (i) {
                        case 30323:
                            this.currentTrack.projectionPoseYaw = (float) d;
                            return;
                        case 30324:
                            this.currentTrack.projectionPosePitch = (float) d;
                            return;
                        case 30325:
                            this.currentTrack.projectionPoseRoll = (float) d;
                            return;
                        default:
                            return;
                    }
            }
        } else {
            this.durationTimecode = (long) d;
        }
    }

    /* access modifiers changed from: protected */
    public void stringElement(int i, String str) throws ParserException {
        if (i == 134) {
            this.currentTrack.codecId = str;
        } else if (i != 17026) {
            if (i == 21358) {
                this.currentTrack.name = str;
            } else if (i == 2274716) {
                String unused = this.currentTrack.language = str;
            }
        } else if (!"webm".equals(str) && !"matroska".equals(str)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
            sb.append("DocType ");
            sb.append(str);
            sb.append(" not supported");
            throw new ParserException(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0202, code lost:
        throw new androidx.media2.exoplayer.external.ParserException("EBML lacing sample size out of range.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void binaryElement(int r20, int r21, androidx.media2.exoplayer.external.extractor.ExtractorInput r22) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = 161(0xa1, float:2.26E-43)
            r5 = 163(0xa3, float:2.28E-43)
            r6 = 0
            r7 = 1
            if (r1 == r4) goto L_0x009c
            if (r1 == r5) goto L_0x009c
            r4 = 16981(0x4255, float:2.3795E-41)
            if (r1 == r4) goto L_0x008d
            r4 = 18402(0x47e2, float:2.5787E-41)
            if (r1 == r4) goto L_0x007d
            r4 = 21419(0x53ab, float:3.0014E-41)
            if (r1 == r4) goto L_0x005d
            r4 = 25506(0x63a2, float:3.5742E-41)
            if (r1 == r4) goto L_0x004e
            r4 = 30322(0x7672, float:4.249E-41)
            if (r1 != r4) goto L_0x0035
            androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r4 = new byte[r2]
            r1.projectionData = r4
            androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r1 = r1.projectionData
            r3.readFully(r1, r6, r2)
            goto L_0x02bb
        L_0x0035:
            androidx.media2.exoplayer.external.ParserException r2 = new androidx.media2.exoplayer.external.ParserException
            r3 = 26
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Unexpected id: "
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r2.<init>(r1)
            throw r2
        L_0x004e:
            androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r4 = new byte[r2]
            r1.codecPrivate = r4
            androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r1 = r1.codecPrivate
            r3.readFully(r1, r6, r2)
            goto L_0x02bb
        L_0x005d:
            androidx.media2.exoplayer.external.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            byte[] r1 = r1.data
            java.util.Arrays.fill(r1, r6)
            androidx.media2.exoplayer.external.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            byte[] r1 = r1.data
            int r4 = 4 - r2
            r3.readFully(r1, r4, r2)
            androidx.media2.exoplayer.external.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            r1.setPosition(r6)
            androidx.media2.exoplayer.external.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            long r1 = r1.readUnsignedInt()
            int r2 = (int) r1
            r0.seekEntryId = r2
            goto L_0x02bb
        L_0x007d:
            byte[] r1 = new byte[r2]
            r3.readFully(r1, r6, r2)
            androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor$Track r2 = r0.currentTrack
            androidx.media2.exoplayer.external.extractor.TrackOutput$CryptoData r3 = new androidx.media2.exoplayer.external.extractor.TrackOutput$CryptoData
            r3.<init>(r7, r1, r6, r6)
            r2.cryptoData = r3
            goto L_0x02bb
        L_0x008d:
            androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r4 = new byte[r2]
            r1.sampleStrippedBytes = r4
            androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r1 = r1.sampleStrippedBytes
            r3.readFully(r1, r6, r2)
            goto L_0x02bb
        L_0x009c:
            int r4 = r0.blockState
            r8 = 8
            if (r4 != 0) goto L_0x00c1
            androidx.media2.exoplayer.external.extractor.mkv.VarintReader r4 = r0.varintReader
            long r9 = r4.readUnsignedVarint(r3, r6, r7, r8)
            int r4 = (int) r9
            r0.blockTrackNumber = r4
            androidx.media2.exoplayer.external.extractor.mkv.VarintReader r4 = r0.varintReader
            int r4 = r4.getLastLength()
            r0.blockTrackNumberLength = r4
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r0.blockDurationUs = r9
            r0.blockState = r7
            androidx.media2.exoplayer.external.util.ParsableByteArray r4 = r0.scratch
            r4.reset()
        L_0x00c1:
            android.util.SparseArray<androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor$Track> r4 = r0.tracks
            int r9 = r0.blockTrackNumber
            java.lang.Object r4 = r4.get(r9)
            androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor$Track r4 = (androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor.Track) r4
            if (r4 != 0) goto L_0x00d7
            int r1 = r0.blockTrackNumberLength
            int r1 = r2 - r1
            r3.skipFully(r1)
            r0.blockState = r6
            return
        L_0x00d7:
            int r9 = r0.blockState
            if (r9 != r7) goto L_0x0288
            r9 = 3
            r0.readScratch(r3, r9)
            androidx.media2.exoplayer.external.util.ParsableByteArray r10 = r0.scratch
            byte[] r10 = r10.data
            r11 = 2
            byte r10 = r10[r11]
            r10 = r10 & 6
            int r10 = r10 >> r7
            r12 = 255(0xff, float:3.57E-43)
            if (r10 != 0) goto L_0x00ff
            r0.blockLacingSampleCount = r7
            int[] r10 = r0.blockLacingSampleSizes
            int[] r10 = ensureArrayCapacity(r10, r7)
            r0.blockLacingSampleSizes = r10
            int r13 = r0.blockTrackNumberLength
            int r2 = r2 - r13
            int r2 = r2 - r9
            r10[r6] = r2
            goto L_0x0216
        L_0x00ff:
            if (r1 != r5) goto L_0x0280
            r13 = 4
            r0.readScratch(r3, r13)
            androidx.media2.exoplayer.external.util.ParsableByteArray r14 = r0.scratch
            byte[] r14 = r14.data
            byte r14 = r14[r9]
            r14 = r14 & r12
            int r14 = r14 + r7
            r0.blockLacingSampleCount = r14
            int[] r15 = r0.blockLacingSampleSizes
            int[] r14 = ensureArrayCapacity(r15, r14)
            r0.blockLacingSampleSizes = r14
            if (r10 != r11) goto L_0x0125
            int r9 = r0.blockTrackNumberLength
            int r2 = r2 - r9
            int r2 = r2 - r13
            int r9 = r0.blockLacingSampleCount
            int r2 = r2 / r9
            java.util.Arrays.fill(r14, r6, r9, r2)
            goto L_0x0216
        L_0x0125:
            if (r10 != r7) goto L_0x015c
            r9 = 0
            r10 = 0
        L_0x0129:
            int r14 = r0.blockLacingSampleCount
            int r15 = r14 + -1
            if (r9 >= r15) goto L_0x0150
            int[] r14 = r0.blockLacingSampleSizes
            r14[r9] = r6
        L_0x0133:
            int r13 = r13 + r7
            r0.readScratch(r3, r13)
            androidx.media2.exoplayer.external.util.ParsableByteArray r14 = r0.scratch
            byte[] r14 = r14.data
            int r15 = r13 + -1
            byte r14 = r14[r15]
            r14 = r14 & r12
            int[] r15 = r0.blockLacingSampleSizes
            r16 = r15[r9]
            int r16 = r16 + r14
            r15[r9] = r16
            if (r14 == r12) goto L_0x0133
            r14 = r15[r9]
            int r10 = r10 + r14
            int r9 = r9 + 1
            goto L_0x0129
        L_0x0150:
            int[] r9 = r0.blockLacingSampleSizes
            int r14 = r14 - r7
            int r15 = r0.blockTrackNumberLength
            int r2 = r2 - r15
            int r2 = r2 - r13
            int r2 = r2 - r10
            r9[r14] = r2
            goto L_0x0216
        L_0x015c:
            if (r10 != r9) goto L_0x0267
            r9 = 0
            r10 = 0
        L_0x0160:
            int r14 = r0.blockLacingSampleCount
            int r15 = r14 + -1
            if (r9 >= r15) goto L_0x020b
            int[] r14 = r0.blockLacingSampleSizes
            r14[r9] = r6
            int r13 = r13 + 1
            r0.readScratch(r3, r13)
            androidx.media2.exoplayer.external.util.ParsableByteArray r14 = r0.scratch
            byte[] r14 = r14.data
            int r15 = r13 + -1
            byte r14 = r14[r15]
            if (r14 == 0) goto L_0x0203
            r16 = 0
            r14 = 0
        L_0x017c:
            if (r14 >= r8) goto L_0x01ce
            int r18 = 7 - r14
            int r18 = r7 << r18
            androidx.media2.exoplayer.external.util.ParsableByteArray r5 = r0.scratch
            byte[] r5 = r5.data
            byte r5 = r5[r15]
            r5 = r5 & r18
            if (r5 == 0) goto L_0x01c4
            int r13 = r13 + r14
            r0.readScratch(r3, r13)
            androidx.media2.exoplayer.external.util.ParsableByteArray r5 = r0.scratch
            byte[] r5 = r5.data
            int r16 = r15 + 1
            byte r5 = r5[r15]
            r5 = r5 & r12
            r15 = r18 ^ -1
            r5 = r5 & r15
            long r6 = (long) r5
            r5 = r16
        L_0x019f:
            r16 = r6
            if (r5 >= r13) goto L_0x01b6
            long r6 = r16 << r8
            androidx.media2.exoplayer.external.util.ParsableByteArray r15 = r0.scratch
            byte[] r15 = r15.data
            int r16 = r5 + 1
            byte r5 = r15[r5]
            r5 = r5 & r12
            long r11 = (long) r5
            long r6 = r6 | r11
            r5 = r16
            r11 = 2
            r12 = 255(0xff, float:3.57E-43)
            goto L_0x019f
        L_0x01b6:
            if (r9 <= 0) goto L_0x01ce
            int r14 = r14 * 7
            int r14 = r14 + 6
            r5 = 1
            long r11 = r5 << r14
            long r11 = r11 - r5
            long r16 = r16 - r11
            goto L_0x01ce
        L_0x01c4:
            int r14 = r14 + 1
            r5 = 163(0xa3, float:2.28E-43)
            r6 = 0
            r7 = 1
            r11 = 2
            r12 = 255(0xff, float:3.57E-43)
            goto L_0x017c
        L_0x01ce:
            r5 = r16
            r11 = -2147483648(0xffffffff80000000, double:NaN)
            int r7 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r7 < 0) goto L_0x01fb
            r11 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r7 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r7 > 0) goto L_0x01fb
            int r6 = (int) r5
            int[] r5 = r0.blockLacingSampleSizes
            if (r9 != 0) goto L_0x01e4
            goto L_0x01e9
        L_0x01e4:
            int r7 = r9 + -1
            r7 = r5[r7]
            int r6 = r6 + r7
        L_0x01e9:
            r5[r9] = r6
            int[] r5 = r0.blockLacingSampleSizes
            r5 = r5[r9]
            int r10 = r10 + r5
            int r9 = r9 + 1
            r5 = 163(0xa3, float:2.28E-43)
            r6 = 0
            r7 = 1
            r11 = 2
            r12 = 255(0xff, float:3.57E-43)
            goto L_0x0160
        L_0x01fb:
            androidx.media2.exoplayer.external.ParserException r1 = new androidx.media2.exoplayer.external.ParserException
            java.lang.String r2 = "EBML lacing sample size out of range."
            r1.<init>(r2)
            throw r1
        L_0x0203:
            androidx.media2.exoplayer.external.ParserException r1 = new androidx.media2.exoplayer.external.ParserException
            java.lang.String r2 = "No valid varint length mask found"
            r1.<init>(r2)
            throw r1
        L_0x020b:
            int[] r5 = r0.blockLacingSampleSizes
            r6 = 1
            int r14 = r14 - r6
            int r6 = r0.blockTrackNumberLength
            int r2 = r2 - r6
            int r2 = r2 - r13
            int r2 = r2 - r10
            r5[r14] = r2
        L_0x0216:
            androidx.media2.exoplayer.external.util.ParsableByteArray r2 = r0.scratch
            byte[] r2 = r2.data
            r5 = 0
            byte r2 = r2[r5]
            int r2 = r2 << r8
            androidx.media2.exoplayer.external.util.ParsableByteArray r5 = r0.scratch
            byte[] r5 = r5.data
            r6 = 1
            byte r5 = r5[r6]
            r6 = 255(0xff, float:3.57E-43)
            r5 = r5 & r6
            r2 = r2 | r5
            long r5 = r0.clusterTimecodeUs
            long r9 = (long) r2
            long r9 = r0.scaleTimecodeToUs(r9)
            long r5 = r5 + r9
            r0.blockTimeUs = r5
            androidx.media2.exoplayer.external.util.ParsableByteArray r2 = r0.scratch
            byte[] r2 = r2.data
            r5 = 2
            byte r2 = r2[r5]
            r2 = r2 & r8
            if (r2 != r8) goto L_0x023f
            r2 = 1
            goto L_0x0240
        L_0x023f:
            r2 = 0
        L_0x0240:
            int r6 = r4.type
            if (r6 == r5) goto L_0x0256
            r6 = 163(0xa3, float:2.28E-43)
            if (r1 != r6) goto L_0x0254
            androidx.media2.exoplayer.external.util.ParsableByteArray r6 = r0.scratch
            byte[] r6 = r6.data
            byte r6 = r6[r5]
            r5 = 128(0x80, float:1.794E-43)
            r6 = r6 & r5
            if (r6 != r5) goto L_0x0254
            goto L_0x0256
        L_0x0254:
            r5 = 0
            goto L_0x0257
        L_0x0256:
            r5 = 1
        L_0x0257:
            if (r2 == 0) goto L_0x025c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x025d
        L_0x025c:
            r2 = 0
        L_0x025d:
            r2 = r2 | r5
            r0.blockFlags = r2
            r2 = 2
            r0.blockState = r2
            r2 = 0
            r0.blockLacingSampleIndex = r2
            goto L_0x0288
        L_0x0267:
            androidx.media2.exoplayer.external.ParserException r1 = new androidx.media2.exoplayer.external.ParserException
            r2 = 36
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Unexpected lacing value: "
            r3.append(r2)
            r3.append(r10)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            throw r1
        L_0x0280:
            androidx.media2.exoplayer.external.ParserException r1 = new androidx.media2.exoplayer.external.ParserException
            java.lang.String r2 = "Lacing only supported in SimpleBlocks."
            r1.<init>(r2)
            throw r1
        L_0x0288:
            r2 = 163(0xa3, float:2.28E-43)
            if (r1 != r2) goto L_0x02b3
        L_0x028c:
            int r1 = r0.blockLacingSampleIndex
            int r2 = r0.blockLacingSampleCount
            if (r1 >= r2) goto L_0x02af
            int[] r2 = r0.blockLacingSampleSizes
            r1 = r2[r1]
            r0.writeSampleData(r3, r4, r1)
            long r1 = r0.blockTimeUs
            int r5 = r0.blockLacingSampleIndex
            int r6 = r4.defaultSampleDurationNs
            int r5 = r5 * r6
            int r5 = r5 / 1000
            long r5 = (long) r5
            long r1 = r1 + r5
            r0.commitSampleToOutput(r4, r1)
            int r1 = r0.blockLacingSampleIndex
            r2 = 1
            int r1 = r1 + r2
            r0.blockLacingSampleIndex = r1
            goto L_0x028c
        L_0x02af:
            r1 = 0
            r0.blockState = r1
            goto L_0x02bb
        L_0x02b3:
            r1 = 0
            int[] r2 = r0.blockLacingSampleSizes
            r1 = r2[r1]
            r0.writeSampleData(r3, r4, r1)
        L_0x02bb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor.binaryElement(int, int, androidx.media2.exoplayer.external.extractor.ExtractorInput):void");
    }

    private void commitSampleToOutput(Track track, long j) {
        Track track2 = track;
        if (track2.trueHdSampleRechunker != null) {
            track2.trueHdSampleRechunker.sampleMetadata(track2, j);
        } else {
            long j2 = j;
            if ("S_TEXT/UTF8".equals(track2.codecId)) {
                commitSubtitleSample(track, "%02d:%02d:%02d,%03d", 19, 1000, SUBRIP_TIMECODE_EMPTY);
            } else if ("S_TEXT/ASS".equals(track2.codecId)) {
                commitSubtitleSample(track, "%01d:%02d:%02d:%02d", 21, 10000, SSA_TIMECODE_EMPTY);
            }
            track2.output.sampleMetadata(j, this.blockFlags, this.sampleBytesWritten, 0, track2.cryptoData);
        }
        this.sampleRead = true;
        resetSample();
    }

    private void resetSample() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }

    private void readScratch(ExtractorInput extractorInput, int i) throws IOException, InterruptedException {
        if (this.scratch.limit() < i) {
            if (this.scratch.capacity() < i) {
                ParsableByteArray parsableByteArray = this.scratch;
                parsableByteArray.reset(Arrays.copyOf(parsableByteArray.data, Math.max(this.scratch.data.length * 2, i)), this.scratch.limit());
            }
            extractorInput.readFully(this.scratch.data, this.scratch.limit(), i - this.scratch.limit());
            this.scratch.setLimit(i);
        }
    }

    private void writeSampleData(ExtractorInput extractorInput, Track track, int i) throws IOException, InterruptedException {
        int i2;
        if ("S_TEXT/UTF8".equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i);
        } else if ("S_TEXT/ASS".equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i);
        } else {
            TrackOutput trackOutput = track.output;
            boolean z = true;
            if (!this.sampleEncodingHandled) {
                if (track.hasContentEncryption) {
                    this.blockFlags &= -1073741825;
                    int i3 = 128;
                    if (!this.sampleSignalByteRead) {
                        extractorInput.readFully(this.scratch.data, 0, 1);
                        this.sampleBytesRead++;
                        if ((this.scratch.data[0] & 128) != 128) {
                            this.sampleSignalByte = this.scratch.data[0];
                            this.sampleSignalByteRead = true;
                        } else {
                            throw new ParserException("Extension bit is set in signal byte");
                        }
                    }
                    if ((this.sampleSignalByte & 1) == 1) {
                        boolean z2 = (this.sampleSignalByte & 2) == 2;
                        this.blockFlags |= 1073741824;
                        if (!this.sampleInitializationVectorRead) {
                            extractorInput.readFully(this.encryptionInitializationVector.data, 0, 8);
                            this.sampleBytesRead += 8;
                            this.sampleInitializationVectorRead = true;
                            byte[] bArr = this.scratch.data;
                            if (!z2) {
                                i3 = 0;
                            }
                            bArr[0] = (byte) (i3 | 8);
                            this.scratch.setPosition(0);
                            trackOutput.sampleData(this.scratch, 1);
                            this.sampleBytesWritten++;
                            this.encryptionInitializationVector.setPosition(0);
                            trackOutput.sampleData(this.encryptionInitializationVector, 8);
                            this.sampleBytesWritten += 8;
                        }
                        if (z2) {
                            if (!this.samplePartitionCountRead) {
                                extractorInput.readFully(this.scratch.data, 0, 1);
                                this.sampleBytesRead++;
                                this.scratch.setPosition(0);
                                this.samplePartitionCount = this.scratch.readUnsignedByte();
                                this.samplePartitionCountRead = true;
                            }
                            int i4 = this.samplePartitionCount * 4;
                            this.scratch.reset(i4);
                            extractorInput.readFully(this.scratch.data, 0, i4);
                            this.sampleBytesRead += i4;
                            short s = (short) ((this.samplePartitionCount / 2) + 1);
                            int i5 = (s * 6) + 2;
                            ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                            if (byteBuffer == null || byteBuffer.capacity() < i5) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i5);
                            }
                            this.encryptionSubsampleDataBuffer.position(0);
                            this.encryptionSubsampleDataBuffer.putShort(s);
                            int i6 = 0;
                            int i7 = 0;
                            while (true) {
                                i2 = this.samplePartitionCount;
                                if (i6 >= i2) {
                                    break;
                                }
                                int readUnsignedIntToInt = this.scratch.readUnsignedIntToInt();
                                if (i6 % 2 == 0) {
                                    this.encryptionSubsampleDataBuffer.putShort((short) (readUnsignedIntToInt - i7));
                                } else {
                                    this.encryptionSubsampleDataBuffer.putInt(readUnsignedIntToInt - i7);
                                }
                                i6++;
                                i7 = readUnsignedIntToInt;
                            }
                            int i8 = (i - this.sampleBytesRead) - i7;
                            if (i2 % 2 == 1) {
                                this.encryptionSubsampleDataBuffer.putInt(i8);
                            } else {
                                this.encryptionSubsampleDataBuffer.putShort((short) i8);
                                this.encryptionSubsampleDataBuffer.putInt(0);
                            }
                            this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i5);
                            trackOutput.sampleData(this.encryptionSubsampleData, i5);
                            this.sampleBytesWritten += i5;
                        }
                    }
                } else if (track.sampleStrippedBytes != null) {
                    this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
                }
                this.sampleEncodingHandled = true;
            }
            int limit = i + this.sampleStrippedBytes.limit();
            if (!"V_MPEG4/ISO/AVC".equals(track.codecId) && !"V_MPEGH/ISO/HEVC".equals(track.codecId)) {
                if (track.trueHdSampleRechunker != null) {
                    if (this.sampleStrippedBytes.limit() != 0) {
                        z = false;
                    }
                    Assertions.checkState(z);
                    track.trueHdSampleRechunker.startSample(extractorInput, this.blockFlags, limit);
                }
                while (true) {
                    int i9 = this.sampleBytesRead;
                    if (i9 >= limit) {
                        break;
                    }
                    readToOutput(extractorInput, trackOutput, limit - i9);
                }
            } else {
                byte[] bArr2 = this.nalLength.data;
                bArr2[0] = 0;
                bArr2[1] = 0;
                bArr2[2] = 0;
                int i10 = track.nalUnitLengthFieldLength;
                int i11 = 4 - track.nalUnitLengthFieldLength;
                while (this.sampleBytesRead < limit) {
                    int i12 = this.sampleCurrentNalBytesRemaining;
                    if (i12 == 0) {
                        readToTarget(extractorInput, bArr2, i11, i10);
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    } else {
                        this.sampleCurrentNalBytesRemaining = i12 - readToOutput(extractorInput, trackOutput, i12);
                    }
                }
            }
            if ("A_VORBIS".equals(track.codecId)) {
                this.vorbisNumPageSamples.setPosition(0);
                trackOutput.sampleData(this.vorbisNumPageSamples, 4);
                this.sampleBytesWritten += 4;
            }
        }
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i) throws IOException, InterruptedException {
        int length = bArr.length + i;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.data = Arrays.copyOf(bArr, length + i);
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.data, 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.data, bArr.length, i);
        this.subtitleSample.reset(length);
    }

    private void commitSubtitleSample(Track track, String str, int i, long j, byte[] bArr) {
        setSampleDuration(this.subtitleSample.data, this.blockDurationUs, str, i, j, bArr);
        TrackOutput trackOutput = track.output;
        ParsableByteArray parsableByteArray = this.subtitleSample;
        trackOutput.sampleData(parsableByteArray, parsableByteArray.limit());
        this.sampleBytesWritten += this.subtitleSample.limit();
    }

    private static void setSampleDuration(byte[] bArr, long j, String str, int i, long j2, byte[] bArr2) {
        byte[] bArr3;
        byte[] bArr4;
        if (j == -9223372036854775807L) {
            bArr4 = bArr2;
            bArr3 = bArr4;
        } else {
            int i2 = (int) (j / 3600000000L);
            long j3 = j - (((long) (i2 * 3600)) * 1000000);
            int i3 = (int) (j3 / 60000000);
            long j4 = j3 - (((long) (i3 * 60)) * 1000000);
            int i4 = (int) (j4 / 1000000);
            int i5 = (int) ((j4 - (((long) i4) * 1000000)) / j2);
            Locale locale = Locale.US;
            Object[] objArr = {Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)};
            String str2 = str;
            bArr4 = Util.getUtf8Bytes(String.format(locale, str, objArr));
            bArr3 = bArr2;
        }
        byte[] bArr5 = bArr;
        int i6 = i;
        System.arraycopy(bArr4, 0, bArr, i, bArr3.length);
    }

    private void readToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int min = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + min, i2 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, min);
        }
        this.sampleBytesRead += i2;
    }

    private int readToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws IOException, InterruptedException {
        int i2;
        int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft > 0) {
            i2 = Math.min(i, bytesLeft);
            trackOutput.sampleData(this.sampleStrippedBytes, i2);
        } else {
            i2 = trackOutput.sampleData(extractorInput, i, false);
        }
        this.sampleBytesRead += i2;
        this.sampleBytesWritten += i2;
        return i2;
    }

    private SeekMap buildSeekMap() {
        LongArray longArray;
        LongArray longArray2;
        if (this.segmentContentPosition == -1 || this.durationUs == -9223372036854775807L || (longArray = this.cueTimesUs) == null || longArray.size() == 0 || (longArray2 = this.cueClusterPositions) == null || longArray2.size() != this.cueTimesUs.size()) {
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
            return new SeekMap.Unseekable(this.durationUs);
        }
        int size = this.cueTimesUs.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jArr3[i2] = this.cueTimesUs.get(i2);
            jArr[i2] = this.segmentContentPosition + this.cueClusterPositions.get(i2);
        }
        while (true) {
            int i3 = size - 1;
            if (i < i3) {
                int i4 = i + 1;
                iArr[i] = (int) (jArr[i4] - jArr[i]);
                jArr2[i] = jArr3[i4] - jArr3[i];
                i = i4;
            } else {
                iArr[i3] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i3]);
                jArr2[i3] = this.durationUs - jArr3[i3];
                this.cueTimesUs = null;
                this.cueClusterPositions = null;
                return new ChunkIndex(iArr, jArr, jArr2, jArr3);
            }
        }
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j2 = this.seekPositionAfterBuildingCues;
            if (j2 != -1) {
                positionHolder.position = j2;
                this.seekPositionAfterBuildingCues = -1;
                return true;
            }
        }
        return false;
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        long j2 = this.timecodeScale;
        if (j2 != -9223372036854775807L) {
            return Util.scaleLargeTimestamp(j, j2, 1000);
        }
        throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
    }

    private static boolean isCodecSupported(String str) {
        return "V_VP8".equals(str) || "V_VP9".equals(str) || "V_AV1".equals(str) || "V_MPEG2".equals(str) || "V_MPEG4/ISO/SP".equals(str) || "V_MPEG4/ISO/ASP".equals(str) || "V_MPEG4/ISO/AP".equals(str) || "V_MPEG4/ISO/AVC".equals(str) || "V_MPEGH/ISO/HEVC".equals(str) || "V_MS/VFW/FOURCC".equals(str) || "V_THEORA".equals(str) || "A_OPUS".equals(str) || "A_VORBIS".equals(str) || "A_AAC".equals(str) || "A_MPEG/L2".equals(str) || "A_MPEG/L3".equals(str) || "A_AC3".equals(str) || "A_EAC3".equals(str) || "A_TRUEHD".equals(str) || "A_DTS".equals(str) || "A_DTS/EXPRESS".equals(str) || "A_DTS/LOSSLESS".equals(str) || "A_FLAC".equals(str) || "A_MS/ACM".equals(str) || "A_PCM/INT/LIT".equals(str) || "S_TEXT/UTF8".equals(str) || "S_TEXT/ASS".equals(str) || "S_VOBSUB".equals(str) || "S_HDMV/PGS".equals(str) || "S_DVBSUB".equals(str);
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length * 2, i)];
    }

    private final class InnerEbmlProcessor implements EbmlProcessor {
        private InnerEbmlProcessor() {
        }

        public int getElementType(int i) {
            return MatroskaExtractor.this.getElementType(i);
        }

        public boolean isLevel1Element(int i) {
            return MatroskaExtractor.this.isLevel1Element(i);
        }

        public void startMasterElement(int i, long j, long j2) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i, j, j2);
        }

        public void endMasterElement(int i) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i);
        }

        public void integerElement(int i, long j) throws ParserException {
            MatroskaExtractor.this.integerElement(i, j);
        }

        public void floatElement(int i, double d) throws ParserException {
            MatroskaExtractor.this.floatElement(i, d);
        }

        public void stringElement(int i, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i, str);
        }

        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException, InterruptedException {
            MatroskaExtractor.this.binaryElement(i, i2, extractorInput);
        }
    }

    private static final class TrueHdSampleRechunker {
        private int blockFlags;
        private int chunkSize;
        private boolean foundSyncframe;
        private int sampleCount;
        private final byte[] syncframePrefix = new byte[10];
        private long timeUs;

        public void reset() {
            this.foundSyncframe = false;
        }

        public void startSample(ExtractorInput extractorInput, int i, int i2) throws IOException, InterruptedException {
            if (!this.foundSyncframe) {
                extractorInput.peekFully(this.syncframePrefix, 0, 10);
                extractorInput.resetPeekPosition();
                if (Ac3Util.parseTrueHdSyncframeAudioSampleCount(this.syncframePrefix) != 0) {
                    this.foundSyncframe = true;
                    this.sampleCount = 0;
                } else {
                    return;
                }
            }
            if (this.sampleCount == 0) {
                this.blockFlags = i;
                this.chunkSize = 0;
            }
            this.chunkSize += i2;
        }

        public void sampleMetadata(Track track, long j) {
            if (this.foundSyncframe) {
                int i = this.sampleCount;
                this.sampleCount = i + 1;
                if (i == 0) {
                    this.timeUs = j;
                }
                if (this.sampleCount >= 16) {
                    track.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, track.cryptoData);
                    this.sampleCount = 0;
                }
            }
        }

        public void outputPendingSampleMetadata(Track track) {
            if (this.foundSyncframe && this.sampleCount > 0) {
                track.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, track.cryptoData);
                this.sampleCount = 0;
            }
        }
    }

    private static final class Track {
        public int audioBitDepth;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange;
        public int colorSpace;
        public int colorTransfer;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public DrmInitData drmInitData;
        public boolean flagDefault;
        public boolean flagForced;
        public boolean hasColorInfo;
        public boolean hasContentEncryption;
        public int height;
        /* access modifiers changed from: private */
        public String language;
        public int maxContentLuminance;
        public int maxFrameAverageLuminance;
        public float maxMasteringLuminance;
        public float minMasteringLuminance;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX;
        public float primaryBChromaticityY;
        public float primaryGChromaticityX;
        public float primaryGChromaticityY;
        public float primaryRChromaticityX;
        public float primaryRChromaticityY;
        public byte[] projectionData;
        public float projectionPosePitch;
        public float projectionPoseRoll;
        public float projectionPoseYaw;
        public int projectionType;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int stereoMode;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public float whitePointChromaticityX;
        public float whitePointChromaticityY;
        public int width;

        private Track() {
            this.width = -1;
            this.height = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.projectionType = -1;
            this.projectionPoseYaw = 0.0f;
            this.projectionPosePitch = 0.0f;
            this.projectionPoseRoll = 0.0f;
            this.projectionData = null;
            this.stereoMode = -1;
            this.hasColorInfo = false;
            this.colorSpace = -1;
            this.colorTransfer = -1;
            this.colorRange = -1;
            this.maxContentLuminance = 1000;
            this.maxFrameAverageLuminance = 200;
            this.primaryRChromaticityX = -1.0f;
            this.primaryRChromaticityY = -1.0f;
            this.primaryGChromaticityX = -1.0f;
            this.primaryGChromaticityY = -1.0f;
            this.primaryBChromaticityX = -1.0f;
            this.primaryBChromaticityY = -1.0f;
            this.whitePointChromaticityX = -1.0f;
            this.whitePointChromaticityY = -1.0f;
            this.maxMasteringLuminance = -1.0f;
            this.minMasteringLuminance = -1.0f;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0;
            this.seekPreRollNs = 0;
            this.flagDefault = true;
            this.language = "eng";
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v52, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v11, resolved type: java.lang.String} */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:105:0x01e4, code lost:
            r26 = r1;
            r4 = "audio/raw";
            r1 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:124:0x0265, code lost:
            r4 = r16;
            r1 = null;
            r26 = -1;
            r31 = 4096;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:132:0x0315, code lost:
            r1 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:133:0x0316, code lost:
            r4 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:143:0x0332, code lost:
            r4 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:144:0x0334, code lost:
            r1 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:145:0x0336, code lost:
            r26 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:146:0x0338, code lost:
            r31 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:147:0x033a, code lost:
            r2 = r0.flagDefault | 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:148:0x033f, code lost:
            if (r0.flagForced == false) goto L_0x0343;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:149:0x0341, code lost:
            r3 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:150:0x0343, code lost:
            r3 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:151:0x0344, code lost:
            r2 = r2 | r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:152:0x0349, code lost:
            if (androidx.media2.exoplayer.external.util.MimeTypes.isAudio(r4) == false) goto L_0x0372;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:153:0x034b, code lost:
            r1 = androidx.media2.exoplayer.external.Format.createAudioSampleFormat(java.lang.Integer.toString(r44), r4, (java.lang.String) null, -1, r31, r0.channelCount, r0.sampleRate, r26, r1, r0.drmInitData, r2, r0.language);
            r5 = 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:155:0x0376, code lost:
            if (androidx.media2.exoplayer.external.util.MimeTypes.isVideo(r4) == false) goto L_0x0478;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:157:0x037a, code lost:
            if (r0.displayUnit != 0) goto L_0x038e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:158:0x037c, code lost:
            r2 = r0.displayWidth;
            r3 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:159:0x037f, code lost:
            if (r2 != -1) goto L_0x0383;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:160:0x0381, code lost:
            r2 = r0.width;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:161:0x0383, code lost:
            r0.displayWidth = r2;
            r2 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:162:0x0387, code lost:
            if (r2 != -1) goto L_0x038b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:163:0x0389, code lost:
            r2 = r0.height;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:164:0x038b, code lost:
            r0.displayHeight = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:165:0x038e, code lost:
            r3 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:166:0x038f, code lost:
            r5 = r0.displayWidth;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:167:0x0393, code lost:
            if (r5 == r3) goto L_0x03a7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:168:0x0395, code lost:
            r8 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:169:0x0397, code lost:
            if (r8 == r3) goto L_0x03a7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:170:0x0399, code lost:
            r37 = ((float) (r0.height * r5)) / ((float) (r0.width * r8));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:171:0x03a7, code lost:
            r37 = -1.0f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:173:0x03ab, code lost:
            if (r0.hasColorInfo == false) goto L_0x03bf;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:174:0x03ad, code lost:
            r40 = new androidx.media2.exoplayer.external.video.ColorInfo(r0.colorSpace, r0.colorRange, r0.colorTransfer, getHdrStaticInfo());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:175:0x03bf, code lost:
            r40 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:177:0x03cb, code lost:
            if ("htc_video_rotA-000".equals(r0.name) == false) goto L_0x03cf;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:178:0x03cd, code lost:
            r9 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:180:0x03d7, code lost:
            if ("htc_video_rotA-090".equals(r0.name) == false) goto L_0x03dc;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:181:0x03d9, code lost:
            r9 = 90;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:183:0x03e4, code lost:
            if ("htc_video_rotA-180".equals(r0.name) == false) goto L_0x03e9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:184:0x03e6, code lost:
            r9 = 180;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:186:0x03f1, code lost:
            if ("htc_video_rotA-270".equals(r0.name) == false) goto L_0x03f6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:187:0x03f3, code lost:
            r9 = 270;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:188:0x03f6, code lost:
            r9 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:190:0x03f9, code lost:
            if (r0.projectionType != 0) goto L_0x044d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:192:0x0402, code lost:
            if (java.lang.Float.compare(r0.projectionPoseYaw, 0.0f) != 0) goto L_0x044d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:194:0x040a, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 0.0f) != 0) goto L_0x044d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:196:0x0412, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, 0.0f) != 0) goto L_0x0417;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:197:0x0414, code lost:
            r36 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:199:0x041f, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 90.0f) != 0) goto L_0x0424;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:200:0x0421, code lost:
            r36 = 90;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:202:0x042c, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, -180.0f) == 0) goto L_0x0448;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:204:0x0436, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 180.0f) != 0) goto L_0x0439;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:206:0x0441, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, -90.0f) != 0) goto L_0x044d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:207:0x0443, code lost:
            r36 = 270;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:208:0x0448, code lost:
            r36 = 180;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:209:0x044d, code lost:
            r36 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:210:0x044f, code lost:
            r1 = androidx.media2.exoplayer.external.Format.createVideoSampleFormat(java.lang.Integer.toString(r44), r4, (java.lang.String) null, -1, r31, r0.width, r0.height, -1.0f, r1, r36, r37, r0.projectionData, r0.stereoMode, r40, r0.drmInitData);
            r5 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:212:0x047c, code lost:
            if ("application/x-subrip".equals(r4) == false) goto L_0x048d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:213:0x047e, code lost:
            r1 = androidx.media2.exoplayer.external.Format.createTextSampleFormat(java.lang.Integer.toString(r44), r4, r2, r0.language, r0.drmInitData);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:214:0x048a, code lost:
            r5 = 3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:216:0x0491, code lost:
            if ("text/x-ssa".equals(r4) == false) goto L_0x04c6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:217:0x0493, code lost:
            r1 = new java.util.ArrayList(2);
            r1.add(androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor.access$300());
            r1.add(r0.codecPrivate);
            r1 = androidx.media2.exoplayer.external.Format.createTextSampleFormat(java.lang.Integer.toString(r44), r4, (java.lang.String) null, -1, r2, r0.language, -1, r0.drmInitData, Long.MAX_VALUE, r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:219:0x04ca, code lost:
            if ("application/vobsub".equals(r4) != false) goto L_0x04e1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:221:0x04d0, code lost:
            if ("application/pgs".equals(r4) != false) goto L_0x04e1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:223:0x04d6, code lost:
            if ("application/dvbsubs".equals(r4) == false) goto L_0x04d9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:225:0x04e0, code lost:
            throw new androidx.media2.exoplayer.external.ParserException("Unexpected MIME type.");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:226:0x04e1, code lost:
            r1 = androidx.media2.exoplayer.external.Format.createImageSampleFormat(java.lang.Integer.toString(r44), r4, (java.lang.String) null, -1, r2, r1, r0.language, r0.drmInitData);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:227:0x04fc, code lost:
            r2 = r43.track(r0.number, r5);
            r0.output = r2;
            r2.format(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:228:0x0509, code lost:
            return;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void initializeOutput(androidx.media2.exoplayer.external.extractor.ExtractorOutput r43, int r44) throws androidx.media2.exoplayer.external.ParserException {
            /*
                r42 = this;
                r0 = r42
                java.lang.String r1 = r0.codecId
                int r2 = r1.hashCode()
                r3 = 4
                r5 = 1
                r6 = 2
                r7 = 0
                r8 = 3
                switch(r2) {
                    case -2095576542: goto L_0x015f;
                    case -2095575984: goto L_0x0155;
                    case -1985379776: goto L_0x014a;
                    case -1784763192: goto L_0x013f;
                    case -1730367663: goto L_0x0134;
                    case -1482641358: goto L_0x0129;
                    case -1482641357: goto L_0x011e;
                    case -1373388978: goto L_0x0113;
                    case -933872740: goto L_0x0108;
                    case -538363189: goto L_0x00fd;
                    case -538363109: goto L_0x00f2;
                    case -425012669: goto L_0x00e6;
                    case -356037306: goto L_0x00da;
                    case 62923557: goto L_0x00ce;
                    case 62923603: goto L_0x00c2;
                    case 62927045: goto L_0x00b6;
                    case 82318131: goto L_0x00ab;
                    case 82338133: goto L_0x00a0;
                    case 82338134: goto L_0x0095;
                    case 99146302: goto L_0x0089;
                    case 444813526: goto L_0x007d;
                    case 542569478: goto L_0x0071;
                    case 725957860: goto L_0x0065;
                    case 738597099: goto L_0x0059;
                    case 855502857: goto L_0x004d;
                    case 1422270023: goto L_0x0041;
                    case 1809237540: goto L_0x0036;
                    case 1950749482: goto L_0x002a;
                    case 1950789798: goto L_0x001e;
                    case 1951062397: goto L_0x0012;
                    default: goto L_0x0010;
                }
            L_0x0010:
                goto L_0x0169
            L_0x0012:
                java.lang.String r2 = "A_OPUS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 12
                goto L_0x016a
            L_0x001e:
                java.lang.String r2 = "A_FLAC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 22
                goto L_0x016a
            L_0x002a:
                java.lang.String r2 = "A_EAC3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 17
                goto L_0x016a
            L_0x0036:
                java.lang.String r2 = "V_MPEG2"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 3
                goto L_0x016a
            L_0x0041:
                java.lang.String r2 = "S_TEXT/UTF8"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 25
                goto L_0x016a
            L_0x004d:
                java.lang.String r2 = "V_MPEGH/ISO/HEVC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 8
                goto L_0x016a
            L_0x0059:
                java.lang.String r2 = "S_TEXT/ASS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 26
                goto L_0x016a
            L_0x0065:
                java.lang.String r2 = "A_PCM/INT/LIT"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 24
                goto L_0x016a
            L_0x0071:
                java.lang.String r2 = "A_DTS/EXPRESS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 20
                goto L_0x016a
            L_0x007d:
                java.lang.String r2 = "V_THEORA"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 10
                goto L_0x016a
            L_0x0089:
                java.lang.String r2 = "S_HDMV/PGS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 28
                goto L_0x016a
            L_0x0095:
                java.lang.String r2 = "V_VP9"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 1
                goto L_0x016a
            L_0x00a0:
                java.lang.String r2 = "V_VP8"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 0
                goto L_0x016a
            L_0x00ab:
                java.lang.String r2 = "V_AV1"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 2
                goto L_0x016a
            L_0x00b6:
                java.lang.String r2 = "A_DTS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 19
                goto L_0x016a
            L_0x00c2:
                java.lang.String r2 = "A_AC3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 16
                goto L_0x016a
            L_0x00ce:
                java.lang.String r2 = "A_AAC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 13
                goto L_0x016a
            L_0x00da:
                java.lang.String r2 = "A_DTS/LOSSLESS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 21
                goto L_0x016a
            L_0x00e6:
                java.lang.String r2 = "S_VOBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 27
                goto L_0x016a
            L_0x00f2:
                java.lang.String r2 = "V_MPEG4/ISO/AVC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 7
                goto L_0x016a
            L_0x00fd:
                java.lang.String r2 = "V_MPEG4/ISO/ASP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 5
                goto L_0x016a
            L_0x0108:
                java.lang.String r2 = "S_DVBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 29
                goto L_0x016a
            L_0x0113:
                java.lang.String r2 = "V_MS/VFW/FOURCC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 9
                goto L_0x016a
            L_0x011e:
                java.lang.String r2 = "A_MPEG/L3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 15
                goto L_0x016a
            L_0x0129:
                java.lang.String r2 = "A_MPEG/L2"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 14
                goto L_0x016a
            L_0x0134:
                java.lang.String r2 = "A_VORBIS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 11
                goto L_0x016a
            L_0x013f:
                java.lang.String r2 = "A_TRUEHD"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 18
                goto L_0x016a
            L_0x014a:
                java.lang.String r2 = "A_MS/ACM"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 23
                goto L_0x016a
            L_0x0155:
                java.lang.String r2 = "V_MPEG4/ISO/SP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 4
                goto L_0x016a
            L_0x015f:
                java.lang.String r2 = "V_MPEG4/ISO/AP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 6
                goto L_0x016a
            L_0x0169:
                r1 = -1
            L_0x016a:
                java.lang.String r2 = ". Setting mimeType to "
                java.lang.String r10 = "Unsupported PCM bit depth: "
                java.lang.String r11 = "application/dvbsubs"
                java.lang.String r12 = "application/pgs"
                java.lang.String r13 = "application/vobsub"
                java.lang.String r14 = "text/x-ssa"
                java.lang.String r15 = "application/x-subrip"
                java.lang.String r16 = "audio/raw"
                r17 = 4096(0x1000, float:5.74E-42)
                java.lang.String r9 = "MatroskaExtractor"
                java.lang.String r4 = "audio/x-unknown"
                r18 = 0
                switch(r1) {
                    case 0: goto L_0x0330;
                    case 1: goto L_0x032d;
                    case 2: goto L_0x032a;
                    case 3: goto L_0x0327;
                    case 4: goto L_0x0319;
                    case 5: goto L_0x0319;
                    case 6: goto L_0x0319;
                    case 7: goto L_0x0302;
                    case 8: goto L_0x02ee;
                    case 9: goto L_0x02d8;
                    case 10: goto L_0x02d5;
                    case 11: goto L_0x02c3;
                    case 12: goto L_0x0279;
                    case 13: goto L_0x026f;
                    case 14: goto L_0x0263;
                    case 15: goto L_0x0260;
                    case 16: goto L_0x025c;
                    case 17: goto L_0x0258;
                    case 18: goto L_0x024d;
                    case 19: goto L_0x0249;
                    case 20: goto L_0x0249;
                    case 21: goto L_0x0245;
                    case 22: goto L_0x023b;
                    case 23: goto L_0x01ec;
                    case 24: goto L_0x01ba;
                    case 25: goto L_0x01b7;
                    case 26: goto L_0x01b4;
                    case 27: goto L_0x01ab;
                    case 28: goto L_0x01a8;
                    case 29: goto L_0x018d;
                    default: goto L_0x0185;
                }
            L_0x0185:
                androidx.media2.exoplayer.external.ParserException r1 = new androidx.media2.exoplayer.external.ParserException
                java.lang.String r2 = "Unrecognized codec identifier."
                r1.<init>(r2)
                throw r1
            L_0x018d:
                byte[] r1 = new byte[r3]
                byte[] r2 = r0.codecPrivate
                byte r3 = r2[r7]
                r1[r7] = r3
                byte r3 = r2[r5]
                r1[r5] = r3
                byte r3 = r2[r6]
                r1[r6] = r3
                byte r2 = r2[r8]
                r1[r8] = r2
                java.util.List r1 = java.util.Collections.singletonList(r1)
                r4 = r11
                goto L_0x0336
            L_0x01a8:
                r4 = r12
                goto L_0x0334
            L_0x01ab:
                byte[] r1 = r0.codecPrivate
                java.util.List r1 = java.util.Collections.singletonList(r1)
                r4 = r13
                goto L_0x0336
            L_0x01b4:
                r4 = r14
                goto L_0x0334
            L_0x01b7:
                r4 = r15
                goto L_0x0334
            L_0x01ba:
                int r1 = r0.audioBitDepth
                int r1 = androidx.media2.exoplayer.external.util.Util.getPcmEncoding(r1)
                if (r1 != 0) goto L_0x01e4
                int r1 = r0.audioBitDepth
                int r3 = r4.length()
                int r3 = r3 + 60
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>(r3)
                r5.append(r10)
                r5.append(r1)
                r5.append(r2)
                r5.append(r4)
                java.lang.String r1 = r5.toString()
                androidx.media2.exoplayer.external.util.Log.w(r9, r1)
                goto L_0x0334
            L_0x01e4:
                r26 = r1
                r4 = r16
                r1 = r18
                goto L_0x0338
            L_0x01ec:
                androidx.media2.exoplayer.external.util.ParsableByteArray r1 = new androidx.media2.exoplayer.external.util.ParsableByteArray
                byte[] r3 = r0.codecPrivate
                r1.<init>((byte[]) r3)
                boolean r1 = parseMsAcmCodecPrivate(r1)
                if (r1 == 0) goto L_0x0223
                int r1 = r0.audioBitDepth
                int r1 = androidx.media2.exoplayer.external.util.Util.getPcmEncoding(r1)
                if (r1 != 0) goto L_0x01e4
                int r1 = r0.audioBitDepth
                int r3 = r4.length()
                int r3 = r3 + 60
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>(r3)
                r5.append(r10)
                r5.append(r1)
                r5.append(r2)
                r5.append(r4)
                java.lang.String r1 = r5.toString()
                androidx.media2.exoplayer.external.util.Log.w(r9, r1)
                goto L_0x0334
            L_0x0223:
                java.lang.String r1 = "Non-PCM MS/ACM is unsupported. Setting mimeType to "
                int r2 = r4.length()
                if (r2 == 0) goto L_0x0230
                java.lang.String r1 = r1.concat(r4)
                goto L_0x0236
            L_0x0230:
                java.lang.String r2 = new java.lang.String
                r2.<init>(r1)
                r1 = r2
            L_0x0236:
                androidx.media2.exoplayer.external.util.Log.w(r9, r1)
                goto L_0x0334
            L_0x023b:
                byte[] r1 = r0.codecPrivate
                java.util.List r1 = java.util.Collections.singletonList(r1)
                java.lang.String r16 = "audio/flac"
                goto L_0x0316
            L_0x0245:
                java.lang.String r16 = "audio/vnd.dts.hd"
                goto L_0x0332
            L_0x0249:
                java.lang.String r16 = "audio/vnd.dts"
                goto L_0x0332
            L_0x024d:
                androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker r1 = new androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker
                r1.<init>()
                r0.trueHdSampleRechunker = r1
                java.lang.String r16 = "audio/true-hd"
                goto L_0x0332
            L_0x0258:
                java.lang.String r16 = "audio/eac3"
                goto L_0x0332
            L_0x025c:
                java.lang.String r16 = "audio/ac3"
                goto L_0x0332
            L_0x0260:
                java.lang.String r16 = "audio/mpeg"
                goto L_0x0265
            L_0x0263:
                java.lang.String r16 = "audio/mpeg-L2"
            L_0x0265:
                r4 = r16
                r1 = r18
                r26 = -1
                r31 = 4096(0x1000, float:5.74E-42)
                goto L_0x033a
            L_0x026f:
                byte[] r1 = r0.codecPrivate
                java.util.List r1 = java.util.Collections.singletonList(r1)
                java.lang.String r16 = "audio/mp4a-latm"
                goto L_0x0316
            L_0x0279:
                r17 = 5760(0x1680, float:8.071E-42)
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>(r8)
                byte[] r2 = r0.codecPrivate
                r1.add(r2)
                r2 = 8
                java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r2)
                java.nio.ByteOrder r4 = java.nio.ByteOrder.nativeOrder()
                java.nio.ByteBuffer r3 = r3.order(r4)
                long r4 = r0.codecDelayNs
                java.nio.ByteBuffer r3 = r3.putLong(r4)
                byte[] r3 = r3.array()
                r1.add(r3)
                java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r2)
                java.nio.ByteOrder r3 = java.nio.ByteOrder.nativeOrder()
                java.nio.ByteBuffer r2 = r2.order(r3)
                long r3 = r0.seekPreRollNs
                java.nio.ByteBuffer r2 = r2.putLong(r3)
                byte[] r2 = r2.array()
                r1.add(r2)
                java.lang.String r16 = "audio/opus"
                r4 = r16
                r26 = -1
                r31 = 5760(0x1680, float:8.071E-42)
                goto L_0x033a
            L_0x02c3:
                r17 = 8192(0x2000, float:1.14794E-41)
                byte[] r1 = r0.codecPrivate
                java.util.List r1 = parseVorbisCodecPrivate(r1)
                java.lang.String r16 = "audio/vorbis"
                r4 = r16
                r26 = -1
                r31 = 8192(0x2000, float:1.14794E-41)
                goto L_0x033a
            L_0x02d5:
                java.lang.String r16 = "video/x-unknown"
                goto L_0x0332
            L_0x02d8:
                androidx.media2.exoplayer.external.util.ParsableByteArray r1 = new androidx.media2.exoplayer.external.util.ParsableByteArray
                byte[] r2 = r0.codecPrivate
                r1.<init>((byte[]) r2)
                android.util.Pair r1 = parseFourCcPrivate(r1)
                java.lang.Object r2 = r1.first
                r16 = r2
                java.lang.String r16 = (java.lang.String) r16
                java.lang.Object r1 = r1.second
                java.util.List r1 = (java.util.List) r1
                goto L_0x0316
            L_0x02ee:
                androidx.media2.exoplayer.external.util.ParsableByteArray r1 = new androidx.media2.exoplayer.external.util.ParsableByteArray
                byte[] r2 = r0.codecPrivate
                r1.<init>((byte[]) r2)
                androidx.media2.exoplayer.external.video.HevcConfig r1 = androidx.media2.exoplayer.external.video.HevcConfig.parse(r1)
                java.util.List<byte[]> r2 = r1.initializationData
                int r1 = r1.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r1
                java.lang.String r16 = "video/hevc"
                goto L_0x0315
            L_0x0302:
                androidx.media2.exoplayer.external.util.ParsableByteArray r1 = new androidx.media2.exoplayer.external.util.ParsableByteArray
                byte[] r2 = r0.codecPrivate
                r1.<init>((byte[]) r2)
                androidx.media2.exoplayer.external.video.AvcConfig r1 = androidx.media2.exoplayer.external.video.AvcConfig.parse(r1)
                java.util.List<byte[]> r2 = r1.initializationData
                int r1 = r1.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r1
                java.lang.String r16 = "video/avc"
            L_0x0315:
                r1 = r2
            L_0x0316:
                r4 = r16
                goto L_0x0336
            L_0x0319:
                byte[] r1 = r0.codecPrivate
                if (r1 != 0) goto L_0x0320
                r1 = r18
                goto L_0x0324
            L_0x0320:
                java.util.List r1 = java.util.Collections.singletonList(r1)
            L_0x0324:
                java.lang.String r16 = "video/mp4v-es"
                goto L_0x0316
            L_0x0327:
                java.lang.String r16 = "video/mpeg2"
                goto L_0x0332
            L_0x032a:
                java.lang.String r16 = "video/av01"
                goto L_0x0332
            L_0x032d:
                java.lang.String r16 = "video/x-vnd.on2.vp9"
                goto L_0x0332
            L_0x0330:
                java.lang.String r16 = "video/x-vnd.on2.vp8"
            L_0x0332:
                r4 = r16
            L_0x0334:
                r1 = r18
            L_0x0336:
                r26 = -1
            L_0x0338:
                r31 = -1
            L_0x033a:
                boolean r2 = r0.flagDefault
                r2 = r2 | r7
                boolean r3 = r0.flagForced
                if (r3 == 0) goto L_0x0343
                r3 = 2
                goto L_0x0344
            L_0x0343:
                r3 = 0
            L_0x0344:
                r2 = r2 | r3
                boolean r3 = androidx.media2.exoplayer.external.util.MimeTypes.isAudio(r4)
                if (r3 == 0) goto L_0x0372
                java.lang.String r19 = java.lang.Integer.toString(r44)
                r21 = 0
                r22 = -1
                int r3 = r0.channelCount
                int r5 = r0.sampleRate
                androidx.media2.exoplayer.external.drm.DrmInitData r6 = r0.drmInitData
                java.lang.String r7 = r0.language
                r20 = r4
                r23 = r31
                r24 = r3
                r25 = r5
                r27 = r1
                r28 = r6
                r29 = r2
                r30 = r7
                androidx.media2.exoplayer.external.Format r1 = androidx.media2.exoplayer.external.Format.createAudioSampleFormat(r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30)
                r5 = 1
                goto L_0x04fc
            L_0x0372:
                boolean r3 = androidx.media2.exoplayer.external.util.MimeTypes.isVideo(r4)
                if (r3 == 0) goto L_0x0478
                int r2 = r0.displayUnit
                if (r2 != 0) goto L_0x038e
                int r2 = r0.displayWidth
                r3 = -1
                if (r2 != r3) goto L_0x0383
                int r2 = r0.width
            L_0x0383:
                r0.displayWidth = r2
                int r2 = r0.displayHeight
                if (r2 != r3) goto L_0x038b
                int r2 = r0.height
            L_0x038b:
                r0.displayHeight = r2
                goto L_0x038f
            L_0x038e:
                r3 = -1
            L_0x038f:
                r2 = -1082130432(0xffffffffbf800000, float:-1.0)
                int r5 = r0.displayWidth
                if (r5 == r3) goto L_0x03a7
                int r8 = r0.displayHeight
                if (r8 == r3) goto L_0x03a7
                int r2 = r0.height
                int r2 = r2 * r5
                float r2 = (float) r2
                int r5 = r0.width
                int r5 = r5 * r8
                float r5 = (float) r5
                float r2 = r2 / r5
                r37 = r2
                goto L_0x03a9
            L_0x03a7:
                r37 = -1082130432(0xffffffffbf800000, float:-1.0)
            L_0x03a9:
                boolean r2 = r0.hasColorInfo
                if (r2 == 0) goto L_0x03bf
                byte[] r2 = r42.getHdrStaticInfo()
                androidx.media2.exoplayer.external.video.ColorInfo r5 = new androidx.media2.exoplayer.external.video.ColorInfo
                int r8 = r0.colorSpace
                int r9 = r0.colorRange
                int r10 = r0.colorTransfer
                r5.<init>(r8, r9, r10, r2)
                r40 = r5
                goto L_0x03c1
            L_0x03bf:
                r40 = r18
            L_0x03c1:
                java.lang.String r2 = r0.name
                java.lang.String r5 = "htc_video_rotA-000"
                boolean r2 = r5.equals(r2)
                r5 = 90
                if (r2 == 0) goto L_0x03cf
                r9 = 0
                goto L_0x03f7
            L_0x03cf:
                java.lang.String r2 = r0.name
                java.lang.String r8 = "htc_video_rotA-090"
                boolean r2 = r8.equals(r2)
                if (r2 == 0) goto L_0x03dc
                r9 = 90
                goto L_0x03f7
            L_0x03dc:
                java.lang.String r2 = r0.name
                java.lang.String r8 = "htc_video_rotA-180"
                boolean r2 = r8.equals(r2)
                if (r2 == 0) goto L_0x03e9
                r9 = 180(0xb4, float:2.52E-43)
                goto L_0x03f7
            L_0x03e9:
                java.lang.String r2 = r0.name
                java.lang.String r8 = "htc_video_rotA-270"
                boolean r2 = r8.equals(r2)
                if (r2 == 0) goto L_0x03f6
                r9 = 270(0x10e, float:3.78E-43)
                goto L_0x03f7
            L_0x03f6:
                r9 = -1
            L_0x03f7:
                int r2 = r0.projectionType
                if (r2 != 0) goto L_0x044d
                float r2 = r0.projectionPoseYaw
                r3 = 0
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 != 0) goto L_0x044d
                float r2 = r0.projectionPosePitch
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 != 0) goto L_0x044d
                float r2 = r0.projectionPoseRoll
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 != 0) goto L_0x0417
                r36 = 0
                goto L_0x044f
            L_0x0417:
                float r2 = r0.projectionPosePitch
                r3 = 1119092736(0x42b40000, float:90.0)
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 != 0) goto L_0x0424
                r36 = 90
                goto L_0x044f
            L_0x0424:
                float r2 = r0.projectionPosePitch
                r3 = -1020002304(0xffffffffc3340000, float:-180.0)
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 == 0) goto L_0x0448
                float r2 = r0.projectionPosePitch
                r3 = 1127481344(0x43340000, float:180.0)
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 != 0) goto L_0x0439
                goto L_0x0448
            L_0x0439:
                float r2 = r0.projectionPosePitch
                r3 = -1028390912(0xffffffffc2b40000, float:-90.0)
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 != 0) goto L_0x044d
                r7 = 270(0x10e, float:3.78E-43)
                r36 = 270(0x10e, float:3.78E-43)
                goto L_0x044f
            L_0x0448:
                r7 = 180(0xb4, float:2.52E-43)
                r36 = 180(0xb4, float:2.52E-43)
                goto L_0x044f
            L_0x044d:
                r36 = r9
            L_0x044f:
                java.lang.String r27 = java.lang.Integer.toString(r44)
                r29 = 0
                r30 = -1
                int r2 = r0.width
                int r3 = r0.height
                r34 = -1082130432(0xffffffffbf800000, float:-1.0)
                byte[] r5 = r0.projectionData
                int r7 = r0.stereoMode
                androidx.media2.exoplayer.external.drm.DrmInitData r8 = r0.drmInitData
                r28 = r4
                r32 = r2
                r33 = r3
                r35 = r1
                r38 = r5
                r39 = r7
                r41 = r8
                androidx.media2.exoplayer.external.Format r1 = androidx.media2.exoplayer.external.Format.createVideoSampleFormat(r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41)
                r5 = 2
                goto L_0x04fc
            L_0x0478:
                boolean r3 = r15.equals(r4)
                if (r3 == 0) goto L_0x048d
                java.lang.String r1 = java.lang.Integer.toString(r44)
                java.lang.String r3 = r0.language
                androidx.media2.exoplayer.external.drm.DrmInitData r5 = r0.drmInitData
                androidx.media2.exoplayer.external.Format r1 = androidx.media2.exoplayer.external.Format.createTextSampleFormat(r1, r4, r2, r3, r5)
            L_0x048a:
                r5 = 3
                goto L_0x04fc
            L_0x048d:
                boolean r3 = r14.equals(r4)
                if (r3 == 0) goto L_0x04c6
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>(r6)
                byte[] r3 = androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor.SSA_DIALOGUE_FORMAT
                r1.add(r3)
                byte[] r3 = r0.codecPrivate
                r1.add(r3)
                java.lang.String r27 = java.lang.Integer.toString(r44)
                r29 = 0
                r30 = -1
                java.lang.String r3 = r0.language
                r33 = -1
                androidx.media2.exoplayer.external.drm.DrmInitData r5 = r0.drmInitData
                r35 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r28 = r4
                r31 = r2
                r32 = r3
                r34 = r5
                r37 = r1
                androidx.media2.exoplayer.external.Format r1 = androidx.media2.exoplayer.external.Format.createTextSampleFormat(r27, r28, r29, r30, r31, r32, r33, r34, r35, r37)
                goto L_0x048a
            L_0x04c6:
                boolean r3 = r13.equals(r4)
                if (r3 != 0) goto L_0x04e1
                boolean r3 = r12.equals(r4)
                if (r3 != 0) goto L_0x04e1
                boolean r3 = r11.equals(r4)
                if (r3 == 0) goto L_0x04d9
                goto L_0x04e1
            L_0x04d9:
                androidx.media2.exoplayer.external.ParserException r1 = new androidx.media2.exoplayer.external.ParserException
                java.lang.String r2 = "Unexpected MIME type."
                r1.<init>(r2)
                throw r1
            L_0x04e1:
                java.lang.String r27 = java.lang.Integer.toString(r44)
                r29 = 0
                r30 = -1
                java.lang.String r3 = r0.language
                androidx.media2.exoplayer.external.drm.DrmInitData r5 = r0.drmInitData
                r28 = r4
                r31 = r2
                r32 = r1
                r33 = r3
                r34 = r5
                androidx.media2.exoplayer.external.Format r1 = androidx.media2.exoplayer.external.Format.createImageSampleFormat(r27, r28, r29, r30, r31, r32, r33, r34)
                goto L_0x048a
            L_0x04fc:
                int r2 = r0.number
                r3 = r43
                androidx.media2.exoplayer.external.extractor.TrackOutput r2 = r3.track(r2, r5)
                r0.output = r2
                r2.format(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.extractor.mkv.MatroskaExtractor.Track.initializeOutput(androidx.media2.exoplayer.external.extractor.ExtractorOutput, int):void");
        }

        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.outputPendingSampleMetadata(this);
            }
        }

        public void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.reset();
            }
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.put((byte) 0);
            wrap.putShort((short) ((int) ((this.primaryRChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryRChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) (this.maxMasteringLuminance + 0.5f)));
            wrap.putShort((short) ((int) (this.minMasteringLuminance + 0.5f)));
            wrap.putShort((short) this.maxContentLuminance);
            wrap.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long readLittleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (readLittleEndianUnsignedInt == 1482049860) {
                    return new Pair<>("video/divx", (Object) null);
                }
                if (readLittleEndianUnsignedInt == 859189832) {
                    return new Pair<>("video/3gpp", (Object) null);
                }
                if (readLittleEndianUnsignedInt == 826496599) {
                    byte[] bArr = parsableByteArray.data;
                    for (int position = parsableByteArray.getPosition() + 20; position < bArr.length - 4; position++) {
                        if (bArr[position] == 0 && bArr[position + 1] == 0 && bArr[position + 2] == 1 && bArr[position + 3] == 15) {
                            return new Pair<>("video/wvc1", Collections.singletonList(Arrays.copyOfRange(bArr, position, bArr.length)));
                        }
                    }
                    throw new ParserException("Failed to find FourCC VC1 initialization data");
                }
                Log.w("MatroskaExtractor", "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair<>("video/x-unknown", (Object) null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing FourCC private data");
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            try {
                if (bArr[0] == 2) {
                    int i = 1;
                    int i2 = 0;
                    while (bArr[i] == -1) {
                        i2 += 255;
                        i++;
                    }
                    int i3 = i + 1;
                    int i4 = i2 + bArr[i];
                    int i5 = 0;
                    while (bArr[i3] == -1) {
                        i5 += 255;
                        i3++;
                    }
                    int i6 = i3 + 1;
                    int i7 = i5 + bArr[i3];
                    if (bArr[i6] == 1) {
                        byte[] bArr2 = new byte[i4];
                        System.arraycopy(bArr, i6, bArr2, 0, i4);
                        int i8 = i6 + i4;
                        if (bArr[i8] == 3) {
                            int i9 = i8 + i7;
                            if (bArr[i9] == 5) {
                                byte[] bArr3 = new byte[(bArr.length - i9)];
                                System.arraycopy(bArr, i9, bArr3, 0, bArr.length - i9);
                                ArrayList arrayList = new ArrayList(2);
                                arrayList.add(bArr2);
                                arrayList.add(bArr3);
                                return arrayList;
                            }
                            throw new ParserException("Error parsing vorbis codec private");
                        }
                        throw new ParserException("Error parsing vorbis codec private");
                    }
                    throw new ParserException("Error parsing vorbis codec private");
                }
                throw new ParserException("Error parsing vorbis codec private");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (readLittleEndianUnsignedShort == 1) {
                    return true;
                }
                if (readLittleEndianUnsignedShort != 65534) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                    return true;
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }
    }
}
