package androidx.media2.exoplayer.external;

public interface RendererCapabilities {
    int getTrackType();

    int supportsFormat(Format format) throws ExoPlaybackException;

    int supportsMixedMimeTypeAdaptation() throws ExoPlaybackException;
}
