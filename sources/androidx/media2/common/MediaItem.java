package androidx.media2.common;

import androidx.core.util.Pair;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class MediaItem extends CustomVersionedParcelable {
    long mEndPositionMs;
    private final List<Pair<Object, Executor>> mListeners;
    private final Object mLock;
    MediaMetadata mMetadata;
    long mStartPositionMs;

    MediaItem() {
        this.mLock = new Object();
        this.mStartPositionMs = 0;
        this.mEndPositionMs = 576460752303423487L;
        this.mListeners = new ArrayList();
    }

    MediaItem(Builder builder) {
        this(builder.mMetadata, builder.mStartPositionMs, builder.mEndPositionMs);
    }

    MediaItem(MediaItem mediaItem) {
        this(mediaItem.mMetadata, mediaItem.mStartPositionMs, mediaItem.mEndPositionMs);
    }

    MediaItem(MediaMetadata mediaMetadata, long j, long j2) {
        this.mLock = new Object();
        this.mStartPositionMs = 0;
        this.mEndPositionMs = 576460752303423487L;
        this.mListeners = new ArrayList();
        if (j <= j2) {
            if (mediaMetadata != null && mediaMetadata.containsKey("android.media.metadata.DURATION")) {
                long j3 = mediaMetadata.getLong("android.media.metadata.DURATION");
                if (!(j3 == Long.MIN_VALUE || j2 == 576460752303423487L || j2 <= j3)) {
                    throw new IllegalStateException("endPositionMs shouldn't be greater than duration in the metdata, endPositionMs=" + j2 + ", durationMs=" + j3);
                }
            }
            this.mMetadata = mediaMetadata;
            this.mStartPositionMs = j;
            this.mEndPositionMs = j2;
            return;
        }
        throw new IllegalStateException("Illegal start/end position: " + j + " : " + j2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        synchronized (this.mLock) {
            sb.append("{mMetadata=");
            sb.append(this.mMetadata);
            sb.append(", mStartPositionMs=");
            sb.append(this.mStartPositionMs);
            sb.append(", mEndPositionMs=");
            sb.append(this.mEndPositionMs);
            sb.append('}');
        }
        return sb.toString();
    }

    public MediaMetadata getMetadata() {
        MediaMetadata mediaMetadata;
        synchronized (this.mLock) {
            mediaMetadata = this.mMetadata;
        }
        return mediaMetadata;
    }

    public long getStartPosition() {
        return this.mStartPositionMs;
    }

    public long getEndPosition() {
        return this.mEndPositionMs;
    }

    public String getMediaId() {
        String string;
        synchronized (this.mLock) {
            string = this.mMetadata != null ? this.mMetadata.getString("android.media.metadata.MEDIA_ID") : null;
        }
        return string;
    }

    public static class Builder {
        long mEndPositionMs = 576460752303423487L;
        MediaMetadata mMetadata;
        long mStartPositionMs = 0;

        public Builder setMetadata(MediaMetadata mediaMetadata) {
            this.mMetadata = mediaMetadata;
            return this;
        }

        public Builder setStartPosition(long j) {
            if (j < 0) {
                j = 0;
            }
            this.mStartPositionMs = j;
            return this;
        }

        public Builder setEndPosition(long j) {
            if (j < 0) {
                j = 576460752303423487L;
            }
            this.mEndPositionMs = j;
            return this;
        }

        public MediaItem build() {
            return new MediaItem(this);
        }
    }

    public void onPreParceling(boolean z) {
        if (getClass() == MediaItem.class) {
            super.onPreParceling(z);
            return;
        }
        throw new RuntimeException("MediaItem's subclasses shouldn't be parcelized.");
    }
}
