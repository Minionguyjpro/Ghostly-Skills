package androidx.media2.exoplayer.external.text.cea;

import java.util.Collections;
import java.util.List;

public final class Cea708InitializationData {
    public static List<byte[]> buildData(boolean z) {
        return Collections.singletonList(new byte[]{z ? (byte) 1 : 0});
    }
}
