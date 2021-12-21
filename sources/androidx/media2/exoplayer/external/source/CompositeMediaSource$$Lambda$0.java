package androidx.media2.exoplayer.external.source;

import androidx.media2.exoplayer.external.Timeline;
import androidx.media2.exoplayer.external.source.MediaSource;

final /* synthetic */ class CompositeMediaSource$$Lambda$0 implements MediaSource.SourceInfoRefreshListener {
    private final CompositeMediaSource arg$1;
    private final Object arg$2;

    CompositeMediaSource$$Lambda$0(CompositeMediaSource compositeMediaSource, Object obj) {
        this.arg$1 = compositeMediaSource;
        this.arg$2 = obj;
    }

    public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline, Object obj) {
        this.arg$1.lambda$prepareChildSource$0$CompositeMediaSource(this.arg$2, mediaSource, timeline, obj);
    }
}
