package androidx.media2.exoplayer.external.source.hls;

import android.net.Uri;
import androidx.media2.exoplayer.external.C;
import androidx.media2.exoplayer.external.ExoPlayerLibraryInfo;
import androidx.media2.exoplayer.external.offline.StreamKey;
import androidx.media2.exoplayer.external.source.BaseMediaSource;
import androidx.media2.exoplayer.external.source.CompositeSequenceableLoaderFactory;
import androidx.media2.exoplayer.external.source.DefaultCompositeSequenceableLoaderFactory;
import androidx.media2.exoplayer.external.source.MediaPeriod;
import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.source.SinglePeriodTimeline;
import androidx.media2.exoplayer.external.source.hls.playlist.DefaultHlsPlaylistParserFactory;
import androidx.media2.exoplayer.external.source.hls.playlist.DefaultHlsPlaylistTracker;
import androidx.media2.exoplayer.external.source.hls.playlist.FilteringHlsPlaylistParserFactory;
import androidx.media2.exoplayer.external.source.hls.playlist.HlsMediaPlaylist;
import androidx.media2.exoplayer.external.source.hls.playlist.HlsPlaylistParserFactory;
import androidx.media2.exoplayer.external.source.hls.playlist.HlsPlaylistTracker;
import androidx.media2.exoplayer.external.upstream.Allocator;
import androidx.media2.exoplayer.external.upstream.DataSource;
import androidx.media2.exoplayer.external.upstream.DefaultLoadErrorHandlingPolicy;
import androidx.media2.exoplayer.external.upstream.LoadErrorHandlingPolicy;
import androidx.media2.exoplayer.external.upstream.TransferListener;
import androidx.media2.exoplayer.external.util.Assertions;
import java.io.IOException;
import java.util.List;

public final class HlsMediaSource extends BaseMediaSource implements HlsPlaylistTracker.PrimaryPlaylistListener {
    private final boolean allowChunklessPreparation;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private final HlsExtractorFactory extractorFactory;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Uri manifestUri;
    private TransferListener mediaTransferListener;
    private final HlsPlaylistTracker playlistTracker;
    private final Object tag;
    private final boolean useSessionKeys;

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.hls");
    }

    public static final class Factory {
        private boolean allowChunklessPreparation;
        private CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
        private HlsExtractorFactory extractorFactory;
        private final HlsDataSourceFactory hlsDataSourceFactory;
        private boolean isCreateCalled;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private HlsPlaylistParserFactory playlistParserFactory;
        private HlsPlaylistTracker.Factory playlistTrackerFactory;
        private List<StreamKey> streamKeys;
        private Object tag;
        private boolean useSessionKeys;

        public Factory(DataSource.Factory factory) {
            this((HlsDataSourceFactory) new DefaultHlsDataSourceFactory(factory));
        }

        public Factory(HlsDataSourceFactory hlsDataSourceFactory2) {
            this.hlsDataSourceFactory = (HlsDataSourceFactory) Assertions.checkNotNull(hlsDataSourceFactory2);
            this.playlistParserFactory = new DefaultHlsPlaylistParserFactory();
            this.playlistTrackerFactory = DefaultHlsPlaylistTracker.FACTORY;
            this.extractorFactory = HlsExtractorFactory.DEFAULT;
            this.loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
            this.compositeSequenceableLoaderFactory = new DefaultCompositeSequenceableLoaderFactory();
        }

        public Factory setTag(Object obj) {
            Assertions.checkState(!this.isCreateCalled);
            this.tag = obj;
            return this;
        }

        public HlsMediaSource createMediaSource(Uri uri) {
            this.isCreateCalled = true;
            List<StreamKey> list = this.streamKeys;
            if (list != null) {
                this.playlistParserFactory = new FilteringHlsPlaylistParserFactory(this.playlistParserFactory, list);
            }
            HlsDataSourceFactory hlsDataSourceFactory2 = this.hlsDataSourceFactory;
            HlsExtractorFactory hlsExtractorFactory = this.extractorFactory;
            CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2 = this.compositeSequenceableLoaderFactory;
            LoadErrorHandlingPolicy loadErrorHandlingPolicy2 = this.loadErrorHandlingPolicy;
            return new HlsMediaSource(uri, hlsDataSourceFactory2, hlsExtractorFactory, compositeSequenceableLoaderFactory2, loadErrorHandlingPolicy2, this.playlistTrackerFactory.createTracker(hlsDataSourceFactory2, loadErrorHandlingPolicy2, this.playlistParserFactory), this.allowChunklessPreparation, this.useSessionKeys, this.tag);
        }
    }

    private HlsMediaSource(Uri uri, HlsDataSourceFactory hlsDataSourceFactory, HlsExtractorFactory hlsExtractorFactory, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, HlsPlaylistTracker hlsPlaylistTracker, boolean z, boolean z2, Object obj) {
        this.manifestUri = uri;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.extractorFactory = hlsExtractorFactory;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.playlistTracker = hlsPlaylistTracker;
        this.allowChunklessPreparation = z;
        this.useSessionKeys = z2;
        this.tag = obj;
    }

    public Object getTag() {
        return this.tag;
    }

    public void prepareSourceInternal(TransferListener transferListener) {
        this.mediaTransferListener = transferListener;
        this.playlistTracker.start(this.manifestUri, createEventDispatcher((MediaSource.MediaPeriodId) null), this);
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.playlistTracker.maybeThrowPrimaryPlaylistRefreshError();
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        return new HlsMediaPeriod(this.extractorFactory, this.playlistTracker, this.dataSourceFactory, this.mediaTransferListener, this.loadErrorHandlingPolicy, createEventDispatcher(mediaPeriodId), allocator, this.compositeSequenceableLoaderFactory, this.allowChunklessPreparation, this.useSessionKeys);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((HlsMediaPeriod) mediaPeriod).release();
    }

    public void releaseSourceInternal() {
        this.playlistTracker.stop();
    }

    public void onPrimaryPlaylistRefreshed(HlsMediaPlaylist hlsMediaPlaylist) {
        SinglePeriodTimeline singlePeriodTimeline;
        long j;
        long j2;
        HlsMediaPlaylist hlsMediaPlaylist2 = hlsMediaPlaylist;
        long usToMs = hlsMediaPlaylist2.hasProgramDateTime ? C.usToMs(hlsMediaPlaylist2.startTimeUs) : -9223372036854775807L;
        long j3 = (hlsMediaPlaylist2.playlistType == 2 || hlsMediaPlaylist2.playlistType == 1) ? usToMs : -9223372036854775807L;
        long j4 = hlsMediaPlaylist2.startOffsetUs;
        if (this.playlistTracker.isLive()) {
            long initialStartTimeUs = hlsMediaPlaylist2.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
            long j5 = hlsMediaPlaylist2.hasEndTag ? initialStartTimeUs + hlsMediaPlaylist2.durationUs : -9223372036854775807L;
            List<HlsMediaPlaylist.Segment> list = hlsMediaPlaylist2.segments;
            if (j4 == -9223372036854775807L) {
                if (list.isEmpty()) {
                    j2 = 0;
                } else {
                    j2 = list.get(Math.max(0, list.size() - 3)).relativeStartTimeUs;
                }
                j = j2;
            } else {
                j = j4;
            }
            singlePeriodTimeline = new SinglePeriodTimeline(j3, usToMs, j5, hlsMediaPlaylist2.durationUs, initialStartTimeUs, j, true, !hlsMediaPlaylist2.hasEndTag, this.tag);
        } else {
            singlePeriodTimeline = new SinglePeriodTimeline(j3, usToMs, hlsMediaPlaylist2.durationUs, hlsMediaPlaylist2.durationUs, 0, j4 == -9223372036854775807L ? 0 : j4, true, false, this.tag);
        }
        refreshSourceInfo(singlePeriodTimeline, new HlsManifest(this.playlistTracker.getMasterPlaylist(), hlsMediaPlaylist2));
    }
}
