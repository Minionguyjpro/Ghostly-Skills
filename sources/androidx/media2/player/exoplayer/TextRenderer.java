package androidx.media2.player.exoplayer;

import android.os.Handler;
import android.os.Looper;
import androidx.core.util.Preconditions;
import androidx.media2.exoplayer.external.BaseRenderer;
import androidx.media2.exoplayer.external.ExoPlaybackException;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.FormatHolder;
import androidx.media2.exoplayer.external.text.SubtitleInputBuffer;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

class TextRenderer extends BaseRenderer {
    private final ParsableByteArray mCcData = new ParsableByteArray();
    private final SortedMap<Long, byte[]> mCcMap = new TreeMap();
    private final DataBuilder mDtvDataBuilder = new DataBuilder();
    private final FormatHolder mFormatHolder = new FormatHolder();
    private final Handler mHandler = new Handler(Looper.myLooper());
    private boolean mHasPendingInputBuffer;
    private final SubtitleInputBuffer mInputBuffer = new SubtitleInputBuffer();
    private boolean mInputStreamEnded;
    private boolean[] mIsTypeAndChannelAdvertised;
    private final int[] mLine21Channels = new int[2];
    private final DataBuilder mLine21DataBuilder = new DataBuilder();
    final Output mOutput;
    private final ParsableByteArray mScratch = new ParsableByteArray();
    private int mSelectedChannel = -1;
    private int mSelectedType = -1;

    public interface Output {
        void onCcData(byte[] bArr, long j);

        void onChannelAvailable(int i, int i2);
    }

    public boolean isReady() {
        return true;
    }

    TextRenderer(Output output) {
        super(3);
        this.mOutput = output;
    }

    public int supportsFormat(Format format) {
        String str = format.sampleMimeType;
        return ("application/cea-608".equals(str) || "application/cea-708".equals(str) || "text/vtt".equals(str)) ? 4 : 0;
    }

    /* access modifiers changed from: protected */
    public void onStreamChanged(Format[] formatArr, long j) throws ExoPlaybackException {
        super.onStreamChanged(formatArr, j);
        this.mIsTypeAndChannelAdvertised = new boolean[128];
    }

    /* access modifiers changed from: protected */
    public synchronized void onPositionReset(long j, boolean z) {
        flush();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0123, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void render(long r6, long r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            int r8 = r5.getState()     // Catch:{ all -> 0x0124 }
            r9 = 2
            if (r8 == r9) goto L_0x000a
            monitor-exit(r5)
            return
        L_0x000a:
            r5.display(r6)     // Catch:{ all -> 0x0124 }
            boolean r8 = r5.mHasPendingInputBuffer     // Catch:{ all -> 0x0124 }
            r0 = 1
            r1 = 0
            if (r8 != 0) goto L_0x003d
            androidx.media2.exoplayer.external.text.SubtitleInputBuffer r8 = r5.mInputBuffer     // Catch:{ all -> 0x0124 }
            r8.clear()     // Catch:{ all -> 0x0124 }
            androidx.media2.exoplayer.external.FormatHolder r8 = r5.mFormatHolder     // Catch:{ all -> 0x0124 }
            androidx.media2.exoplayer.external.text.SubtitleInputBuffer r2 = r5.mInputBuffer     // Catch:{ all -> 0x0124 }
            int r8 = r5.readSource(r8, r2, r1)     // Catch:{ all -> 0x0124 }
            r2 = -3
            if (r8 == r2) goto L_0x003b
            r2 = -5
            if (r8 != r2) goto L_0x0027
            goto L_0x003b
        L_0x0027:
            androidx.media2.exoplayer.external.text.SubtitleInputBuffer r8 = r5.mInputBuffer     // Catch:{ all -> 0x0124 }
            boolean r8 = r8.isEndOfStream()     // Catch:{ all -> 0x0124 }
            if (r8 == 0) goto L_0x0033
            r5.mInputStreamEnded = r0     // Catch:{ all -> 0x0124 }
            monitor-exit(r5)
            return
        L_0x0033:
            r5.mHasPendingInputBuffer = r0     // Catch:{ all -> 0x0124 }
            androidx.media2.exoplayer.external.text.SubtitleInputBuffer r8 = r5.mInputBuffer     // Catch:{ all -> 0x0124 }
            r8.flip()     // Catch:{ all -> 0x0124 }
            goto L_0x003d
        L_0x003b:
            monitor-exit(r5)
            return
        L_0x003d:
            androidx.media2.exoplayer.external.text.SubtitleInputBuffer r8 = r5.mInputBuffer     // Catch:{ all -> 0x0124 }
            long r2 = r8.timeUs     // Catch:{ all -> 0x0124 }
            long r2 = r2 - r6
            r6 = 110000(0x1adb0, double:5.4347E-319)
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x004b
            monitor-exit(r5)
            return
        L_0x004b:
            r5.mHasPendingInputBuffer = r1     // Catch:{ all -> 0x0124 }
            androidx.media2.exoplayer.external.util.ParsableByteArray r6 = r5.mCcData     // Catch:{ all -> 0x0124 }
            androidx.media2.exoplayer.external.text.SubtitleInputBuffer r7 = r5.mInputBuffer     // Catch:{ all -> 0x0124 }
            java.nio.ByteBuffer r7 = r7.data     // Catch:{ all -> 0x0124 }
            byte[] r7 = r7.array()     // Catch:{ all -> 0x0124 }
            androidx.media2.exoplayer.external.text.SubtitleInputBuffer r8 = r5.mInputBuffer     // Catch:{ all -> 0x0124 }
            java.nio.ByteBuffer r8 = r8.data     // Catch:{ all -> 0x0124 }
            int r8 = r8.limit()     // Catch:{ all -> 0x0124 }
            r6.reset(r7, r8)     // Catch:{ all -> 0x0124 }
            androidx.media2.player.exoplayer.TextRenderer$DataBuilder r6 = r5.mLine21DataBuilder     // Catch:{ all -> 0x0124 }
            r6.clear()     // Catch:{ all -> 0x0124 }
        L_0x0067:
            androidx.media2.exoplayer.external.util.ParsableByteArray r6 = r5.mCcData     // Catch:{ all -> 0x0124 }
            int r6 = r6.bytesLeft()     // Catch:{ all -> 0x0124 }
            r7 = 3
            if (r6 < r7) goto L_0x010d
            androidx.media2.exoplayer.external.util.ParsableByteArray r6 = r5.mCcData     // Catch:{ all -> 0x0124 }
            int r6 = r6.readUnsignedByte()     // Catch:{ all -> 0x0124 }
            byte r6 = (byte) r6     // Catch:{ all -> 0x0124 }
            androidx.media2.exoplayer.external.util.ParsableByteArray r8 = r5.mCcData     // Catch:{ all -> 0x0124 }
            int r8 = r8.readUnsignedByte()     // Catch:{ all -> 0x0124 }
            byte r8 = (byte) r8     // Catch:{ all -> 0x0124 }
            androidx.media2.exoplayer.external.util.ParsableByteArray r2 = r5.mCcData     // Catch:{ all -> 0x0124 }
            int r2 = r2.readUnsignedByte()     // Catch:{ all -> 0x0124 }
            byte r2 = (byte) r2     // Catch:{ all -> 0x0124 }
            r3 = r6 & 4
            if (r3 == 0) goto L_0x008b
            r3 = 1
            goto L_0x008c
        L_0x008b:
            r3 = 0
        L_0x008c:
            r4 = r6 & 3
            if (r3 == 0) goto L_0x00f6
            if (r4 != r7) goto L_0x00a9
            androidx.media2.player.exoplayer.TextRenderer$DataBuilder r6 = r5.mDtvDataBuilder     // Catch:{ all -> 0x0124 }
            boolean r6 = r6.hasData()     // Catch:{ all -> 0x0124 }
            if (r6 == 0) goto L_0x00a3
            androidx.media2.player.exoplayer.TextRenderer$DataBuilder r6 = r5.mDtvDataBuilder     // Catch:{ all -> 0x0124 }
            androidx.media2.exoplayer.external.text.SubtitleInputBuffer r7 = r5.mInputBuffer     // Catch:{ all -> 0x0124 }
            long r3 = r7.timeUs     // Catch:{ all -> 0x0124 }
            r5.handleDtvPacket(r6, r3)     // Catch:{ all -> 0x0124 }
        L_0x00a3:
            androidx.media2.player.exoplayer.TextRenderer$DataBuilder r6 = r5.mDtvDataBuilder     // Catch:{ all -> 0x0124 }
            r6.append(r8, r2)     // Catch:{ all -> 0x0124 }
            goto L_0x0067
        L_0x00a9:
            androidx.media2.player.exoplayer.TextRenderer$DataBuilder r7 = r5.mDtvDataBuilder     // Catch:{ all -> 0x0124 }
            int r7 = r7.mLength     // Catch:{ all -> 0x0124 }
            if (r7 <= 0) goto L_0x00b7
            if (r4 != r9) goto L_0x00b7
            androidx.media2.player.exoplayer.TextRenderer$DataBuilder r6 = r5.mDtvDataBuilder     // Catch:{ all -> 0x0124 }
            r6.append(r8, r2)     // Catch:{ all -> 0x0124 }
            goto L_0x0067
        L_0x00b7:
            if (r4 == 0) goto L_0x00bb
            if (r4 != r0) goto L_0x0067
        L_0x00bb:
            r7 = r8 & 127(0x7f, float:1.78E-43)
            byte r7 = (byte) r7     // Catch:{ all -> 0x0124 }
            r8 = r2 & 127(0x7f, float:1.78E-43)
            byte r8 = (byte) r8     // Catch:{ all -> 0x0124 }
            r2 = 16
            if (r7 >= r2) goto L_0x00c8
            if (r8 >= r2) goto L_0x00c8
            goto L_0x0067
        L_0x00c8:
            if (r7 < r2) goto L_0x00e2
            r2 = 31
            if (r7 > r2) goto L_0x00e2
            r2 = 24
            if (r7 < r2) goto L_0x00d4
            r2 = 1
            goto L_0x00d5
        L_0x00d4:
            r2 = 0
        L_0x00d5:
            if (r6 == 0) goto L_0x00d9
            r6 = 2
            goto L_0x00da
        L_0x00d9:
            r6 = 0
        L_0x00da:
            int r2 = r2 + r6
            int[] r6 = r5.mLine21Channels     // Catch:{ all -> 0x0124 }
            r6[r4] = r2     // Catch:{ all -> 0x0124 }
            r5.maybeAdvertiseChannel(r1, r2)     // Catch:{ all -> 0x0124 }
        L_0x00e2:
            int r6 = r5.mSelectedType     // Catch:{ all -> 0x0124 }
            if (r6 != 0) goto L_0x0067
            int r6 = r5.mSelectedChannel     // Catch:{ all -> 0x0124 }
            int[] r2 = r5.mLine21Channels     // Catch:{ all -> 0x0124 }
            r2 = r2[r4]     // Catch:{ all -> 0x0124 }
            if (r6 != r2) goto L_0x0067
            androidx.media2.player.exoplayer.TextRenderer$DataBuilder r6 = r5.mLine21DataBuilder     // Catch:{ all -> 0x0124 }
            byte r2 = (byte) r4     // Catch:{ all -> 0x0124 }
            r6.append(r2, r7, r8)     // Catch:{ all -> 0x0124 }
            goto L_0x0067
        L_0x00f6:
            if (r4 == r7) goto L_0x00fa
            if (r4 != r9) goto L_0x0067
        L_0x00fa:
            androidx.media2.player.exoplayer.TextRenderer$DataBuilder r6 = r5.mDtvDataBuilder     // Catch:{ all -> 0x0124 }
            boolean r6 = r6.hasData()     // Catch:{ all -> 0x0124 }
            if (r6 == 0) goto L_0x0067
            androidx.media2.player.exoplayer.TextRenderer$DataBuilder r6 = r5.mDtvDataBuilder     // Catch:{ all -> 0x0124 }
            androidx.media2.exoplayer.external.text.SubtitleInputBuffer r7 = r5.mInputBuffer     // Catch:{ all -> 0x0124 }
            long r7 = r7.timeUs     // Catch:{ all -> 0x0124 }
            r5.handleDtvPacket(r6, r7)     // Catch:{ all -> 0x0124 }
            goto L_0x0067
        L_0x010d:
            int r6 = r5.mSelectedType     // Catch:{ all -> 0x0124 }
            if (r6 != 0) goto L_0x0122
            androidx.media2.player.exoplayer.TextRenderer$DataBuilder r6 = r5.mLine21DataBuilder     // Catch:{ all -> 0x0124 }
            boolean r6 = r6.hasData()     // Catch:{ all -> 0x0124 }
            if (r6 == 0) goto L_0x0122
            androidx.media2.player.exoplayer.TextRenderer$DataBuilder r6 = r5.mLine21DataBuilder     // Catch:{ all -> 0x0124 }
            androidx.media2.exoplayer.external.text.SubtitleInputBuffer r7 = r5.mInputBuffer     // Catch:{ all -> 0x0124 }
            long r7 = r7.timeUs     // Catch:{ all -> 0x0124 }
            r5.handleLine21Packet(r6, r7)     // Catch:{ all -> 0x0124 }
        L_0x0122:
            monitor-exit(r5)
            return
        L_0x0124:
            r6 = move-exception
            monitor-exit(r5)
            goto L_0x0128
        L_0x0127:
            throw r6
        L_0x0128:
            goto L_0x0127
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.exoplayer.TextRenderer.render(long, long):void");
    }

    public boolean isEnded() {
        return this.mInputStreamEnded && this.mCcMap.isEmpty();
    }

    public synchronized void clearSelection() {
        select(-1, -1);
    }

    public synchronized void select(int i, int i2) {
        this.mSelectedType = i;
        this.mSelectedChannel = i2;
        flush();
    }

    private void flush() {
        this.mCcMap.clear();
        this.mLine21DataBuilder.clear();
        this.mDtvDataBuilder.clear();
        this.mInputStreamEnded = false;
        this.mHasPendingInputBuffer = false;
    }

    private void maybeAdvertiseChannel(final int i, final int i2) {
        int i3 = (i << 6) + i2;
        boolean[] zArr = this.mIsTypeAndChannelAdvertised;
        if (!zArr[i3]) {
            zArr[i3] = true;
            this.mHandler.post(new Runnable() {
                public void run() {
                    TextRenderer.this.mOutput.onChannelAvailable(i, i2);
                }
            });
        }
    }

    private void handleDtvPacket(DataBuilder dataBuilder, long j) {
        this.mScratch.reset(dataBuilder.mData, dataBuilder.mLength);
        dataBuilder.clear();
        int readUnsignedByte = this.mScratch.readUnsignedByte() & 31;
        if (readUnsignedByte == 0) {
            readUnsignedByte = 64;
        }
        if (this.mScratch.limit() == readUnsignedByte * 2) {
            while (this.mScratch.bytesLeft() >= 2) {
                int readUnsignedByte2 = this.mScratch.readUnsignedByte();
                int i = (readUnsignedByte2 & 224) >> 5;
                int i2 = readUnsignedByte2 & 31;
                if ((i == 7 && (i = this.mScratch.readUnsignedByte() & 63) < 7) || this.mScratch.bytesLeft() < i2) {
                    return;
                }
                if (i2 > 0) {
                    maybeAdvertiseChannel(1, i);
                    if (this.mSelectedType == 1 && this.mSelectedChannel == i) {
                        byte[] bArr = new byte[i2];
                        this.mScratch.readBytes(bArr, 0, i2);
                        this.mCcMap.put(Long.valueOf(j), bArr);
                    } else {
                        this.mScratch.skipBytes(i2);
                    }
                }
            }
        }
    }

    private void handleLine21Packet(DataBuilder dataBuilder, long j) {
        this.mCcMap.put(Long.valueOf(j), Arrays.copyOf(dataBuilder.mData, dataBuilder.mLength));
        dataBuilder.clear();
    }

    private void display(long j) {
        if (this.mSelectedType != -1 && this.mSelectedChannel != -1) {
            byte[] bArr = new byte[0];
            long j2 = -9223372036854775807L;
            while (!this.mCcMap.isEmpty()) {
                long longValue = this.mCcMap.firstKey().longValue();
                if (j < longValue) {
                    break;
                }
                byte[] bArr2 = (byte[]) Preconditions.checkNotNull(this.mCcMap.get(Long.valueOf(longValue)));
                int length = bArr.length;
                bArr = Arrays.copyOf(bArr, bArr2.length + length);
                System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
                SortedMap<Long, byte[]> sortedMap = this.mCcMap;
                sortedMap.remove(sortedMap.firstKey());
                j2 = longValue;
            }
            if (bArr.length > 0) {
                this.mOutput.onCcData(bArr, j2);
            }
        }
    }

    private static final class DataBuilder {
        public byte[] mData = new byte[3];
        public int mLength;

        DataBuilder() {
        }

        public void append(byte b, byte b2) {
            int i = this.mLength + 2;
            byte[] bArr = this.mData;
            if (i > bArr.length) {
                this.mData = Arrays.copyOf(bArr, bArr.length * 2);
            }
            byte[] bArr2 = this.mData;
            int i2 = this.mLength;
            int i3 = i2 + 1;
            this.mLength = i3;
            bArr2[i2] = b;
            this.mLength = i3 + 1;
            bArr2[i3] = b2;
        }

        public void append(byte b, byte b2, byte b3) {
            int i = this.mLength + 3;
            byte[] bArr = this.mData;
            if (i > bArr.length) {
                this.mData = Arrays.copyOf(bArr, bArr.length * 2);
            }
            byte[] bArr2 = this.mData;
            int i2 = this.mLength;
            int i3 = i2 + 1;
            this.mLength = i3;
            bArr2[i2] = b;
            int i4 = i3 + 1;
            this.mLength = i4;
            bArr2[i3] = b2;
            this.mLength = i4 + 1;
            bArr2[i4] = b3;
        }

        public boolean hasData() {
            return this.mLength > 0;
        }

        public void clear() {
            this.mLength = 0;
        }
    }
}
