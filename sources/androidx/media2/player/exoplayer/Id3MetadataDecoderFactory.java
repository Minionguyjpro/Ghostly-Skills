package androidx.media2.player.exoplayer;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.metadata.MetadataDecoder;
import androidx.media2.exoplayer.external.metadata.MetadataDecoderFactory;
import androidx.media2.exoplayer.external.metadata.MetadataInputBuffer;
import java.util.Arrays;

final class Id3MetadataDecoderFactory implements MetadataDecoderFactory {
    Id3MetadataDecoderFactory() {
    }

    public boolean supportsFormat(Format format) {
        return "application/id3".equals(format.sampleMimeType);
    }

    public MetadataDecoder createDecoder(Format format) {
        return new MetadataDecoder() {
            public Metadata decode(MetadataInputBuffer metadataInputBuffer) {
                long j = metadataInputBuffer.timeUs;
                byte[] array = metadataInputBuffer.data.array();
                return new Metadata(new ByteArrayFrame(j, Arrays.copyOf(array, array.length)));
            }
        };
    }
}
