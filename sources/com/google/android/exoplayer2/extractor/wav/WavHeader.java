package com.google.android.exoplayer2.extractor.wav;

import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Util;

final class WavHeader implements SeekMap {
    private final int averageBytesPerSecond;
    private final int bitsPerSample;
    private final int blockAlignment;
    private long dataEndPosition = -1;
    private int dataStartPosition = -1;
    private final int encoding;
    private final int numChannels;
    private final int sampleRateHz;

    public boolean isSeekable() {
        return true;
    }

    public WavHeader(int i, int i2, int i3, int i4, int i5, int i6) {
        this.numChannels = i;
        this.sampleRateHz = i2;
        this.averageBytesPerSecond = i3;
        this.blockAlignment = i4;
        this.bitsPerSample = i5;
        this.encoding = i6;
    }

    public void setDataBounds(int i, long j) {
        this.dataStartPosition = i;
        this.dataEndPosition = j;
    }

    public int getDataStartPosition() {
        return this.dataStartPosition;
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    public boolean hasDataBounds() {
        return this.dataStartPosition != -1;
    }

    public long getDurationUs() {
        return (((this.dataEndPosition - ((long) this.dataStartPosition)) / ((long) this.blockAlignment)) * 1000000) / ((long) this.sampleRateHz);
    }

    public SeekMap.SeekPoints getSeekPoints(long j) {
        long j2 = this.dataEndPosition - ((long) this.dataStartPosition);
        int i = this.blockAlignment;
        long constrainValue = Util.constrainValue((((((long) this.averageBytesPerSecond) * j) / 1000000) / ((long) i)) * ((long) i), 0, j2 - ((long) i));
        long j3 = ((long) this.dataStartPosition) + constrainValue;
        long timeUs = getTimeUs(j3);
        SeekPoint seekPoint = new SeekPoint(timeUs, j3);
        if (timeUs < j) {
            int i2 = this.blockAlignment;
            if (constrainValue != j2 - ((long) i2)) {
                long j4 = j3 + ((long) i2);
                return new SeekMap.SeekPoints(seekPoint, new SeekPoint(getTimeUs(j4), j4));
            }
        }
        return new SeekMap.SeekPoints(seekPoint);
    }

    public long getTimeUs(long j) {
        return (Math.max(0, j - ((long) this.dataStartPosition)) * 1000000) / ((long) this.averageBytesPerSecond);
    }

    public int getBytesPerFrame() {
        return this.blockAlignment;
    }

    public int getBitrate() {
        return this.sampleRateHz * this.bitsPerSample * this.numChannels;
    }

    public int getSampleRateHz() {
        return this.sampleRateHz;
    }

    public int getNumChannels() {
        return this.numChannels;
    }

    public int getEncoding() {
        return this.encoding;
    }
}
