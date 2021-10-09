package androidx.media2.exoplayer.external.source.hls.playlist;

import androidx.media2.exoplayer.external.source.hls.HlsDataSourceFactory;
import androidx.media2.exoplayer.external.source.hls.playlist.HlsPlaylistTracker;
import androidx.media2.exoplayer.external.upstream.LoadErrorHandlingPolicy;

final /* synthetic */ class DefaultHlsPlaylistTracker$$Lambda$0 implements HlsPlaylistTracker.Factory {
    static final HlsPlaylistTracker.Factory $instance = new DefaultHlsPlaylistTracker$$Lambda$0();

    private DefaultHlsPlaylistTracker$$Lambda$0() {
    }

    public HlsPlaylistTracker createTracker(HlsDataSourceFactory hlsDataSourceFactory, LoadErrorHandlingPolicy loadErrorHandlingPolicy, HlsPlaylistParserFactory hlsPlaylistParserFactory) {
        return new DefaultHlsPlaylistTracker(hlsDataSourceFactory, loadErrorHandlingPolicy, hlsPlaylistParserFactory);
    }
}
