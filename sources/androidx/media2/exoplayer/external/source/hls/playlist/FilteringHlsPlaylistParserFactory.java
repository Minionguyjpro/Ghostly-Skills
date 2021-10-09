package androidx.media2.exoplayer.external.source.hls.playlist;

import androidx.media2.exoplayer.external.offline.FilteringManifestParser;
import androidx.media2.exoplayer.external.offline.StreamKey;
import androidx.media2.exoplayer.external.upstream.ParsingLoadable;
import java.util.List;

public final class FilteringHlsPlaylistParserFactory implements HlsPlaylistParserFactory {
    private final HlsPlaylistParserFactory hlsPlaylistParserFactory;
    private final List<StreamKey> streamKeys;

    public FilteringHlsPlaylistParserFactory(HlsPlaylistParserFactory hlsPlaylistParserFactory2, List<StreamKey> list) {
        this.hlsPlaylistParserFactory = hlsPlaylistParserFactory2;
        this.streamKeys = list;
    }

    public ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser() {
        return new FilteringManifestParser(this.hlsPlaylistParserFactory.createPlaylistParser(), this.streamKeys);
    }

    public ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser(HlsMasterPlaylist hlsMasterPlaylist) {
        return new FilteringManifestParser(this.hlsPlaylistParserFactory.createPlaylistParser(hlsMasterPlaylist), this.streamKeys);
    }
}
