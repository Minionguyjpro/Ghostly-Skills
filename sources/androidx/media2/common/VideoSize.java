package androidx.media2.common;

import androidx.versionedparcelable.VersionedParcelable;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;

public final class VideoSize implements VersionedParcelable {
    int mHeight;
    int mWidth;

    VideoSize() {
    }

    public VideoSize(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("width can not be negative");
        } else if (i2 >= 0) {
            this.mWidth = i;
            this.mHeight = i2;
        } else {
            throw new IllegalArgumentException("height can not be negative");
        }
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoSize)) {
            return false;
        }
        VideoSize videoSize = (VideoSize) obj;
        if (this.mWidth == videoSize.mWidth && this.mHeight == videoSize.mHeight) {
            return true;
        }
        return false;
    }

    public String toString() {
        return this.mWidth + AvidJSONUtil.KEY_X + this.mHeight;
    }

    public int hashCode() {
        int i = this.mHeight;
        int i2 = this.mWidth;
        return i ^ ((i2 >>> 16) | (i2 << 16));
    }
}
