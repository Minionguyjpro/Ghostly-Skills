package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.source.TrackGroup;
import androidx.media2.exoplayer.external.source.chunk.MediaChunk;
import androidx.media2.exoplayer.external.source.chunk.MediaChunkIterator;
import java.util.List;

public final class FixedTrackSelection extends BaseTrackSelection {
    private final Object data;
    private final int reason;

    public int getSelectedIndex() {
        return 0;
    }

    public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
    }

    public FixedTrackSelection(TrackGroup trackGroup, int i, int i2, Object obj) {
        super(trackGroup, i);
        this.reason = i2;
        this.data = obj;
    }

    public int getSelectionReason() {
        return this.reason;
    }

    public Object getSelectionData() {
        return this.data;
    }
}
