package androidx.media2.exoplayer.external.metadata;

public interface MetadataDecoder {
    Metadata decode(MetadataInputBuffer metadataInputBuffer);
}
