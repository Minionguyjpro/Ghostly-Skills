package androidx.media2.player;

public final class MediaTimestamp {
    public static final MediaTimestamp TIMESTAMP_UNKNOWN = new MediaTimestamp(-1, -1, 0.0f);
    private final float mClockRate;
    private final long mMediaTimeUs;
    private final long mNanoTime;

    public MediaTimestamp(long j, long j2, float f) {
        this.mMediaTimeUs = j;
        this.mNanoTime = j2;
        this.mClockRate = f;
    }

    MediaTimestamp() {
        this.mMediaTimeUs = 0;
        this.mNanoTime = 0;
        this.mClockRate = 1.0f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaTimestamp mediaTimestamp = (MediaTimestamp) obj;
        if (this.mMediaTimeUs == mediaTimestamp.mMediaTimeUs && this.mNanoTime == mediaTimestamp.mNanoTime && this.mClockRate == mediaTimestamp.mClockRate) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) (((float) (((int) (((long) (Long.valueOf(this.mMediaTimeUs).hashCode() * 31)) + this.mNanoTime)) * 31)) + this.mClockRate);
    }

    public String toString() {
        return getClass().getName() + "{AnchorMediaTimeUs=" + this.mMediaTimeUs + " AnchorSystemNanoTime=" + this.mNanoTime + " ClockRate=" + this.mClockRate + "}";
    }
}
