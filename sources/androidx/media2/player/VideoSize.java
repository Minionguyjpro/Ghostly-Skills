package androidx.media2.player;

public final class VideoSize {
    private final androidx.media2.common.VideoSize mInternal;

    VideoSize(androidx.media2.common.VideoSize videoSize) {
        this.mInternal = videoSize;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof VideoSize) {
            return this.mInternal.equals(((VideoSize) obj).mInternal);
        }
        return false;
    }

    public String toString() {
        return this.mInternal.toString();
    }

    public int hashCode() {
        return this.mInternal.hashCode();
    }
}
