package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.Format;

public final class TrackSelectionUtil {
    public static int[] getFormatBitrates(Format[] formatArr, int[] iArr) {
        int length = formatArr.length;
        if (iArr == null) {
            iArr = new int[length];
        }
        for (int i = 0; i < length; i++) {
            iArr[i] = formatArr[i].bitrate;
        }
        return iArr;
    }
}
