package androidx.media2.exoplayer.external.source;

import android.net.Uri;
import androidx.media2.exoplayer.external.Timeline;
import androidx.media2.exoplayer.external.extractor.DefaultExtractorsFactory;
import androidx.media2.exoplayer.external.extractor.ExtractorsFactory;
import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.upstream.Allocator;
import androidx.media2.exoplayer.external.upstream.DataSource;
import androidx.media2.exoplayer.external.upstream.DefaultLoadErrorHandlingPolicy;
import androidx.media2.exoplayer.external.upstream.LoadErrorHandlingPolicy;
import androidx.media2.exoplayer.external.upstream.TransferListener;
import androidx.media2.exoplayer.external.util.Assertions;
import java.io.IOException;

@Deprecated
public final class ExtractorMediaSource extends BaseMediaSource implements MediaSource.SourceInfoRefreshListener {
    private final ProgressiveMediaSource progressiveMediaSource;

    @Deprecated
    public static final class Factory {
        private int continueLoadingCheckIntervalBytes = 1048576;
        private String customCacheKey;
        private final DataSource.Factory dataSourceFactory;
        private ExtractorsFactory extractorsFactory;
        private boolean isCreateCalled;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
        private Object tag;

        public Factory(DataSource.Factory factory) {
            this.dataSourceFactory = factory;
        }

        public Factory setExtractorsFactory(ExtractorsFactory extractorsFactory2) {
            Assertions.checkState(!this.isCreateCalled);
            this.extractorsFactory = extractorsFactory2;
            return this;
        }

        public Factory setTag(Object obj) {
            Assertions.checkState(!this.isCreateCalled);
            this.tag = obj;
            return this;
        }

        public ExtractorMediaSource createMediaSource(Uri uri) {
            this.isCreateCalled = true;
            if (this.extractorsFactory == null) {
                this.extractorsFactory = new DefaultExtractorsFactory();
            }
            return new ExtractorMediaSource(uri, this.dataSourceFactory, this.extractorsFactory, this.loadErrorHandlingPolicy, this.customCacheKey, this.continueLoadingCheckIntervalBytes, this.tag);
        }
    }

    private ExtractorMediaSource(Uri uri, DataSource.Factory factory, ExtractorsFactory extractorsFactory, LoadErrorHandlingPolicy loadErrorHandlingPolicy, String str, int i, Object obj) {
        this.progressiveMediaSource = new ProgressiveMediaSource(uri, factory, extractorsFactory, loadErrorHandlingPolicy, str, i, obj);
    }

    public Object getTag() {
        return this.progressiveMediaSource.getTag();
    }

    public void prepareSourceInternal(TransferListener transferListener) {
        this.progressiveMediaSource.prepareSource(this, transferListener);
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.progressiveMediaSource.maybeThrowSourceInfoRefreshError();
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        return this.progressiveMediaSource.createPeriod(mediaPeriodId, allocator, j);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.progressiveMediaSource.releasePeriod(mediaPeriod);
    }

    public void releaseSourceInternal() {
        this.progressiveMediaSource.releaseSource(this);
    }

    public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline, Object obj) {
        refreshSourceInfo(timeline, obj);
    }
}
