package androidx.media2.exoplayer.external.source.hls.playlist;

import androidx.media2.exoplayer.external.upstream.ParsingLoadable;

public interface HlsPlaylistParserFactory {
    ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser();

    ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser(HlsMasterPlaylist hlsMasterPlaylist);
}
