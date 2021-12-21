package androidx.media2.exoplayer.external.extractor.mkv;

import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import java.io.IOException;

interface EbmlReader {
    void init(EbmlProcessor ebmlProcessor);

    boolean read(ExtractorInput extractorInput) throws IOException, InterruptedException;

    void reset();
}
