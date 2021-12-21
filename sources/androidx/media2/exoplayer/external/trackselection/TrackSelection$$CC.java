package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.source.chunk.MediaChunkIterator;
import java.util.List;

public abstract /* synthetic */ class TrackSelection$$CC {
    public static void onDiscontinuity$$dflt$$(TrackSelection trackSelection) {
    }

    @Deprecated
    public static void updateSelectedTrack$$dflt$$(TrackSelection trackSelection, long j, long j2, long j3) {
        throw new UnsupportedOperationException();
    }

    public static void updateSelectedTrack$$dflt$$(TrackSelection trackSelection, long j, long j2, long j3, List list, MediaChunkIterator[] mediaChunkIteratorArr) {
        trackSelection.updateSelectedTrack(j, j2, j3);
    }
}
