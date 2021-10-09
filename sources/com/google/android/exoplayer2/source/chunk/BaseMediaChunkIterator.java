package com.google.android.exoplayer2.source.chunk;

public abstract class BaseMediaChunkIterator implements MediaChunkIterator {
    private long currentIndex;
    private final long fromIndex;
    private final long toIndex;

    public BaseMediaChunkIterator(long j, long j2) {
        this.fromIndex = j;
        this.toIndex = j2;
        reset();
    }

    public void reset() {
        this.currentIndex = this.fromIndex - 1;
    }
}
