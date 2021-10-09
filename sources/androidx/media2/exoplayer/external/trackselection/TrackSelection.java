package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.source.TrackGroup;
import androidx.media2.exoplayer.external.source.chunk.MediaChunk;
import androidx.media2.exoplayer.external.source.chunk.MediaChunkIterator;
import androidx.media2.exoplayer.external.upstream.BandwidthMeter;
import java.util.List;

public interface TrackSelection {

    public interface Factory {
        TrackSelection[] createTrackSelections(Definition[] definitionArr, BandwidthMeter bandwidthMeter);
    }

    boolean blacklist(int i, long j);

    void disable();

    void enable();

    Format getFormat(int i);

    int getIndexInTrackGroup(int i);

    Format getSelectedFormat();

    int getSelectedIndex();

    int getSelectedIndexInTrackGroup();

    Object getSelectionData();

    int getSelectionReason();

    TrackGroup getTrackGroup();

    int indexOf(int i);

    int length();

    void onDiscontinuity();

    void onPlaybackSpeed(float f);

    @Deprecated
    void updateSelectedTrack(long j, long j2, long j3);

    void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr);

    public static final class Definition {
        public final Object data;
        public final TrackGroup group;
        public final int reason;
        public final int[] tracks;

        public Definition(TrackGroup trackGroup, int... iArr) {
            this(trackGroup, iArr, 0, (Object) null);
        }

        public Definition(TrackGroup trackGroup, int[] iArr, int i, Object obj) {
            this.group = trackGroup;
            this.tracks = iArr;
            this.reason = i;
            this.data = obj;
        }
    }
}
