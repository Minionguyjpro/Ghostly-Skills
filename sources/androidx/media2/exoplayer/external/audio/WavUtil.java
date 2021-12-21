package androidx.media2.exoplayer.external.audio;

import androidx.media2.exoplayer.external.util.Util;

public final class WavUtil {
    public static int getEncodingForType(int i, int i2) {
        if (i != 1) {
            if (i == 3) {
                return i2 == 32 ? 4 : 0;
            }
            if (i != 65534) {
                if (i != 6) {
                    return i != 7 ? 0 : 268435456;
                }
                return 536870912;
            }
        }
        return Util.getPcmEncoding(i2);
    }
}
