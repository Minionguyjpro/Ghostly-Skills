package androidx.media2.exoplayer.external.extractor.ts;

import androidx.media2.exoplayer.external.extractor.BinarySearchSeeker;
import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import androidx.media2.exoplayer.external.util.TimestampAdjuster;
import androidx.media2.exoplayer.external.util.Util;
import java.io.IOException;

final class TsBinarySearchSeeker extends BinarySearchSeeker {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TsBinarySearchSeeker(androidx.media2.exoplayer.external.util.TimestampAdjuster r17, long r18, long r20, int r22) {
        /*
            r16 = this;
            androidx.media2.exoplayer.external.extractor.BinarySearchSeeker$DefaultSeekTimestampConverter r1 = new androidx.media2.exoplayer.external.extractor.BinarySearchSeeker$DefaultSeekTimestampConverter
            r1.<init>()
            androidx.media2.exoplayer.external.extractor.ts.TsBinarySearchSeeker$TsPcrSeeker r2 = new androidx.media2.exoplayer.external.extractor.ts.TsBinarySearchSeeker$TsPcrSeeker
            r0 = r17
            r3 = r22
            r2.<init>(r3, r0)
            r3 = 1
            long r7 = r18 + r3
            r5 = 0
            r9 = 0
            r13 = 188(0xbc, double:9.3E-322)
            r15 = 940(0x3ac, float:1.317E-42)
            r0 = r16
            r3 = r18
            r11 = r20
            r0.<init>(r1, r2, r3, r5, r7, r9, r11, r13, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.extractor.ts.TsBinarySearchSeeker.<init>(androidx.media2.exoplayer.external.util.TimestampAdjuster, long, long, int):void");
    }

    private static final class TsPcrSeeker implements BinarySearchSeeker.TimestampSeeker {
        private final ParsableByteArray packetBuffer = new ParsableByteArray();
        private final int pcrPid;
        private final TimestampAdjuster pcrTimestampAdjuster;

        public TsPcrSeeker(int i, TimestampAdjuster timestampAdjuster) {
            this.pcrPid = i;
            this.pcrTimestampAdjuster = timestampAdjuster;
        }

        public BinarySearchSeeker.TimestampSearchResult searchForTimestamp(ExtractorInput extractorInput, long j, BinarySearchSeeker.OutputFrameHolder outputFrameHolder) throws IOException, InterruptedException {
            long position = extractorInput.getPosition();
            int min = (int) Math.min(112800, extractorInput.getLength() - position);
            this.packetBuffer.reset(min);
            extractorInput.peekFully(this.packetBuffer.data, 0, min);
            return searchForPcrValueInBuffer(this.packetBuffer, j, position);
        }

        private BinarySearchSeeker.TimestampSearchResult searchForPcrValueInBuffer(ParsableByteArray parsableByteArray, long j, long j2) {
            int findSyncBytePosition;
            int i;
            ParsableByteArray parsableByteArray2 = parsableByteArray;
            long j3 = j2;
            int limit = parsableByteArray.limit();
            long j4 = -1;
            long j5 = -1;
            long j6 = -9223372036854775807L;
            while (parsableByteArray.bytesLeft() >= 188 && (i = findSyncBytePosition + 188) <= limit) {
                long readPcrFromPacket = TsUtil.readPcrFromPacket(parsableByteArray2, (findSyncBytePosition = TsUtil.findSyncBytePosition(parsableByteArray2.data, parsableByteArray.getPosition(), limit)), this.pcrPid);
                if (readPcrFromPacket != -9223372036854775807L) {
                    long adjustTsTimestamp = this.pcrTimestampAdjuster.adjustTsTimestamp(readPcrFromPacket);
                    if (adjustTsTimestamp > j) {
                        if (j6 == -9223372036854775807L) {
                            return BinarySearchSeeker.TimestampSearchResult.overestimatedResult(adjustTsTimestamp, j3);
                        }
                        return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(j3 + j5);
                    } else if (100000 + adjustTsTimestamp > j) {
                        return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(j3 + ((long) findSyncBytePosition));
                    } else {
                        j5 = (long) findSyncBytePosition;
                        j6 = adjustTsTimestamp;
                    }
                }
                parsableByteArray2.setPosition(i);
                j4 = (long) i;
            }
            if (j6 != -9223372036854775807L) {
                return BinarySearchSeeker.TimestampSearchResult.underestimatedResult(j6, j3 + j4);
            }
            return BinarySearchSeeker.TimestampSearchResult.NO_TIMESTAMP_IN_RANGE_RESULT;
        }

        public void onSeekFinished() {
            this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        }
    }
}
