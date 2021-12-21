package androidx.media2.exoplayer.external.source;

import androidx.media2.exoplayer.external.FormatHolder;
import androidx.media2.exoplayer.external.decoder.DecoderInputBuffer;
import java.io.IOException;

public final class EmptySampleStream implements SampleStream {
    public boolean isReady() {
        return true;
    }

    public void maybeThrowError() throws IOException {
    }

    public int skipData(long j) {
        return 0;
    }

    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        decoderInputBuffer.setFlags(4);
        return -4;
    }
}
