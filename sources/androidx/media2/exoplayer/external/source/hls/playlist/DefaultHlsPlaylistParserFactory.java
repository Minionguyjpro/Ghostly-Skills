package androidx.media2.exoplayer.external.source.hls.playlist;

import androidx.media2.exoplayer.external.upstream.ParsingLoadable;

public final class DefaultHlsPlaylistParserFactory implements HlsPlaylistParserFactory {
    public ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser() {
        return new HlsPlaylistParser();
    }

    public ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser(HlsMasterPlaylist hlsMasterPlaylist) {
        return new HlsPlaylistParser(hlsMasterPlaylist);
    }
}
