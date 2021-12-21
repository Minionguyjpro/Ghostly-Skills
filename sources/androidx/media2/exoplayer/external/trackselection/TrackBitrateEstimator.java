package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.source.chunk.MediaChunk;
import androidx.media2.exoplayer.external.source.chunk.MediaChunkIterator;
import java.util.List;

public interface TrackBitrateEstimator {
    public static final TrackBitrateEstimator DEFAULT = TrackBitrateEstimator$$Lambda$0.$instance;

    int[] getBitrates(Format[] formatArr, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr, int[] iArr);
}
