package androidx.media2.common;

import androidx.core.util.ObjectsCompat;
import androidx.versionedparcelable.VersionedParcelable;
import java.util.Arrays;

public final class SubtitleData implements VersionedParcelable {
    byte[] mData;
    long mDurationUs;
    long mStartTimeUs;

    SubtitleData() {
    }

    public SubtitleData(long j, long j2, byte[] bArr) {
        this.mStartTimeUs = j;
        this.mDurationUs = j2;
        this.mData = bArr;
    }

    public long getStartTimeUs() {
        return this.mStartTimeUs;
    }

    public long getDurationUs() {
        return this.mDurationUs;
    }

    public byte[] getData() {
        return this.mData;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SubtitleData subtitleData = (SubtitleData) obj;
        if (this.mStartTimeUs == subtitleData.mStartTimeUs && this.mDurationUs == subtitleData.mDurationUs && Arrays.equals(this.mData, subtitleData.mData)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ObjectsCompat.hash(Long.valueOf(this.mStartTimeUs), Long.valueOf(this.mDurationUs), Integer.valueOf(Arrays.hashCode(this.mData)));
    }
}
