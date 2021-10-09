package androidx.media2.exoplayer.external.metadata;

import androidx.media2.exoplayer.external.decoder.DecoderInputBuffer;

public final class MetadataInputBuffer extends DecoderInputBuffer {
    public long subsampleOffsetUs;

    public MetadataInputBuffer() {
        super(1);
    }
}
