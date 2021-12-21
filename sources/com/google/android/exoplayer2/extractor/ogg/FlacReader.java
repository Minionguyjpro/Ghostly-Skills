package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.ogg.StreamReader;
import com.google.android.exoplayer2.util.FlacStreamMetadata;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

final class FlacReader extends StreamReader {
    private FlacOggSeeker flacOggSeeker;
    /* access modifiers changed from: private */
    public FlacStreamMetadata streamMetadata;

    FlacReader() {
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        return parsableByteArray.bytesLeft() >= 5 && parsableByteArray.readUnsignedByte() == 127 && parsableByteArray.readUnsignedInt() == 1179402563;
    }

    /* access modifiers changed from: protected */
    public void reset(boolean z) {
        super.reset(z);
        if (z) {
            this.streamMetadata = null;
            this.flacOggSeeker = null;
        }
    }

    private static boolean isAudioPacket(byte[] bArr) {
        return bArr[0] == -1;
    }

    /* access modifiers changed from: protected */
    public long preparePayload(ParsableByteArray parsableByteArray) {
        if (!isAudioPacket(parsableByteArray.data)) {
            return -1;
        }
        return (long) getFlacFrameBlockSize(parsableByteArray);
    }

    /* access modifiers changed from: protected */
    public boolean readHeaders(ParsableByteArray parsableByteArray, long j, StreamReader.SetupData setupData) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        StreamReader.SetupData setupData2 = setupData;
        byte[] bArr = parsableByteArray2.data;
        if (this.streamMetadata == null) {
            FlacStreamMetadata flacStreamMetadata = new FlacStreamMetadata(bArr, 17);
            this.streamMetadata = flacStreamMetadata;
            int i = flacStreamMetadata.maxFrameSize == 0 ? -1 : this.streamMetadata.maxFrameSize;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 9, parsableByteArray.limit());
            copyOfRange[4] = Byte.MIN_VALUE;
            setupData2.format = Format.createAudioSampleFormat((String) null, "audio/flac", (String) null, this.streamMetadata.bitRate(), i, this.streamMetadata.channels, this.streamMetadata.sampleRate, Collections.singletonList(copyOfRange), (DrmInitData) null, 0, (String) null);
            return true;
        } else if ((bArr[0] & Byte.MAX_VALUE) == 3) {
            FlacOggSeeker flacOggSeeker2 = new FlacOggSeeker();
            this.flacOggSeeker = flacOggSeeker2;
            flacOggSeeker2.parseSeekTable(parsableByteArray2);
            return true;
        } else if (!isAudioPacket(bArr)) {
            return true;
        } else {
            FlacOggSeeker flacOggSeeker3 = this.flacOggSeeker;
            if (flacOggSeeker3 != null) {
                flacOggSeeker3.setFirstFrameOffset(j);
                setupData2.oggSeeker = this.flacOggSeeker;
            }
            return false;
        }
    }

    private int getFlacFrameBlockSize(ParsableByteArray parsableByteArray) {
        int i;
        int i2;
        int i3 = (parsableByteArray.data[2] & 255) >> 4;
        switch (i3) {
            case 1:
                return 192;
            case 2:
            case 3:
            case 4:
            case 5:
                i = 576;
                i2 = i3 - 2;
                break;
            case 6:
            case 7:
                parsableByteArray.skipBytes(4);
                parsableByteArray.readUtf8EncodedLong();
                int readUnsignedByte = i3 == 6 ? parsableByteArray.readUnsignedByte() : parsableByteArray.readUnsignedShort();
                parsableByteArray.setPosition(0);
                return readUnsignedByte + 1;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                i = 256;
                i2 = i3 - 8;
                break;
            default:
                return -1;
        }
        return i << i2;
    }

    private class FlacOggSeeker implements SeekMap, OggSeeker {
        private long firstFrameOffset = -1;
        private long pendingSeekGranule = -1;
        private long[] seekPointGranules;
        private long[] seekPointOffsets;

        public SeekMap createSeekMap() {
            return this;
        }

        public boolean isSeekable() {
            return true;
        }

        public FlacOggSeeker() {
        }

        public void setFirstFrameOffset(long j) {
            this.firstFrameOffset = j;
        }

        public void parseSeekTable(ParsableByteArray parsableByteArray) {
            parsableByteArray.skipBytes(1);
            int readUnsignedInt24 = parsableByteArray.readUnsignedInt24() / 18;
            this.seekPointGranules = new long[readUnsignedInt24];
            this.seekPointOffsets = new long[readUnsignedInt24];
            for (int i = 0; i < readUnsignedInt24; i++) {
                this.seekPointGranules[i] = parsableByteArray.readLong();
                this.seekPointOffsets[i] = parsableByteArray.readLong();
                parsableByteArray.skipBytes(2);
            }
        }

        public long read(ExtractorInput extractorInput) throws IOException, InterruptedException {
            long j = this.pendingSeekGranule;
            if (j < 0) {
                return -1;
            }
            long j2 = -(j + 2);
            this.pendingSeekGranule = -1;
            return j2;
        }

        public void startSeek(long j) {
            this.pendingSeekGranule = this.seekPointGranules[Util.binarySearchFloor(this.seekPointGranules, j, true, true)];
        }

        public SeekMap.SeekPoints getSeekPoints(long j) {
            int binarySearchFloor = Util.binarySearchFloor(this.seekPointGranules, FlacReader.this.convertTimeToGranule(j), true, true);
            long convertGranuleToTime = FlacReader.this.convertGranuleToTime(this.seekPointGranules[binarySearchFloor]);
            SeekPoint seekPoint = new SeekPoint(convertGranuleToTime, this.firstFrameOffset + this.seekPointOffsets[binarySearchFloor]);
            if (convertGranuleToTime < j) {
                long[] jArr = this.seekPointGranules;
                if (binarySearchFloor != jArr.length - 1) {
                    int i = binarySearchFloor + 1;
                    return new SeekMap.SeekPoints(seekPoint, new SeekPoint(FlacReader.this.convertGranuleToTime(jArr[i]), this.firstFrameOffset + this.seekPointOffsets[i]));
                }
            }
            return new SeekMap.SeekPoints(seekPoint);
        }

        public long getDurationUs() {
            return FlacReader.this.streamMetadata.durationUs();
        }
    }
}
