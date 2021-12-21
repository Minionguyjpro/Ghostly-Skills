package androidx.media2.player.common;

import android.media.MediaFormat;
import androidx.media2.player.MediaPlayer2;

public final class TrackInfoImpl extends MediaPlayer2.TrackInfo {
    private final MediaFormat mFormat;
    private final int mTrackType;

    public int getTrackType() {
        return this.mTrackType;
    }

    public MediaFormat getFormat() {
        if (this.mTrackType == 4) {
            return this.mFormat;
        }
        return null;
    }

    public TrackInfoImpl(int i, MediaFormat mediaFormat) {
        this.mTrackType = i;
        this.mFormat = mediaFormat;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getName());
        sb.append('{');
        int i = this.mTrackType;
        if (i == 1) {
            sb.append("VIDEO");
        } else if (i == 2) {
            sb.append("AUDIO");
        } else if (i == 4) {
            sb.append("SUBTITLE");
        } else if (i != 5) {
            sb.append("UNKNOWN");
        } else {
            sb.append("METADATA");
        }
        sb.append(", ");
        sb.append(this.mFormat);
        sb.append("}");
        return sb.toString();
    }
}
