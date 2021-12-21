package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.source.TrackGroup;
import androidx.media2.exoplayer.external.source.chunk.MediaChunk;
import androidx.media2.exoplayer.external.source.chunk.MediaChunkIterator;
import androidx.media2.exoplayer.external.trackselection.TrackSelection;
import androidx.media2.exoplayer.external.upstream.BandwidthMeter;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Clock;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AdaptiveTrackSelection extends BaseTrackSelection {
    private final BandwidthProvider bandwidthProvider;
    private final float bufferedFractionToLiveEdgeForQualityIncrease;
    private final Clock clock;
    private final int[] formatBitrates;
    private final Format[] formats;
    private long lastBufferEvaluationMs;
    private final long maxDurationForQualityDecreaseUs;
    private final long minDurationForQualityIncreaseUs;
    private final long minDurationToRetainAfterDiscardUs;
    private final long minTimeBetweenBufferReevaluationMs;
    private float playbackSpeed;
    private int reason;
    private int selectedIndex;
    private TrackBitrateEstimator trackBitrateEstimator;
    private final int[] trackBitrates;

    private interface BandwidthProvider {
        long getAllocatedBandwidth();
    }

    public Object getSelectionData() {
        return null;
    }

    public static class Factory implements TrackSelection.Factory {
        private final float bandwidthFraction;
        private final BandwidthMeter bandwidthMeter;
        private boolean blockFixedTrackSelectionBandwidth;
        private final float bufferedFractionToLiveEdgeForQualityIncrease;
        private final Clock clock;
        private final int maxDurationForQualityDecreaseMs;
        private final int minDurationForQualityIncreaseMs;
        private final int minDurationToRetainAfterDiscardMs;
        private final long minTimeBetweenBufferReevaluationMs;
        private TrackBitrateEstimator trackBitrateEstimator;

        public Factory() {
            this(10000, 25000, 25000, 0.75f, 0.75f, 2000, Clock.DEFAULT);
        }

        public Factory(int i, int i2, int i3, float f, float f2, long j, Clock clock2) {
            this((BandwidthMeter) null, i, i2, i3, f, f2, j, clock2);
        }

        @Deprecated
        public Factory(BandwidthMeter bandwidthMeter2, int i, int i2, int i3, float f, float f2, long j, Clock clock2) {
            this.bandwidthMeter = bandwidthMeter2;
            this.minDurationForQualityIncreaseMs = i;
            this.maxDurationForQualityDecreaseMs = i2;
            this.minDurationToRetainAfterDiscardMs = i3;
            this.bandwidthFraction = f;
            this.bufferedFractionToLiveEdgeForQualityIncrease = f2;
            this.minTimeBetweenBufferReevaluationMs = j;
            this.clock = clock2;
            this.trackBitrateEstimator = TrackBitrateEstimator.DEFAULT;
        }

        public final TrackSelection[] createTrackSelections(TrackSelection.Definition[] definitionArr, BandwidthMeter bandwidthMeter2) {
            BandwidthMeter bandwidthMeter3 = this.bandwidthMeter;
            if (bandwidthMeter3 != null) {
                bandwidthMeter2 = bandwidthMeter3;
            }
            TrackSelection[] trackSelectionArr = new TrackSelection[definitionArr.length];
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (int i2 = 0; i2 < definitionArr.length; i2++) {
                TrackSelection.Definition definition = definitionArr[i2];
                if (definition != null) {
                    if (definition.tracks.length > 1) {
                        AdaptiveTrackSelection createAdaptiveTrackSelection = createAdaptiveTrackSelection(definition.group, bandwidthMeter2, definition.tracks);
                        createAdaptiveTrackSelection.experimental_setTrackBitrateEstimator(this.trackBitrateEstimator);
                        arrayList.add(createAdaptiveTrackSelection);
                        trackSelectionArr[i2] = createAdaptiveTrackSelection;
                    } else {
                        trackSelectionArr[i2] = new FixedTrackSelection(definition.group, definition.tracks[0], definition.reason, definition.data);
                        int i3 = definition.group.getFormat(definition.tracks[0]).bitrate;
                        if (i3 != -1) {
                            i += i3;
                        }
                    }
                }
            }
            if (this.blockFixedTrackSelectionBandwidth) {
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    ((AdaptiveTrackSelection) arrayList.get(i4)).experimental_setNonAllocatableBandwidth((long) i);
                }
            }
            if (arrayList.size() > 1) {
                long[][] jArr = new long[arrayList.size()][];
                for (int i5 = 0; i5 < arrayList.size(); i5++) {
                    AdaptiveTrackSelection adaptiveTrackSelection = (AdaptiveTrackSelection) arrayList.get(i5);
                    jArr[i5] = new long[adaptiveTrackSelection.length()];
                    for (int i6 = 0; i6 < adaptiveTrackSelection.length(); i6++) {
                        jArr[i5][i6] = (long) adaptiveTrackSelection.getFormat((adaptiveTrackSelection.length() - i6) - 1).bitrate;
                    }
                }
                long[][][] access$000 = AdaptiveTrackSelection.getAllocationCheckpoints(jArr);
                for (int i7 = 0; i7 < arrayList.size(); i7++) {
                    ((AdaptiveTrackSelection) arrayList.get(i7)).experimental_setBandwidthAllocationCheckpoints(access$000[i7]);
                }
            }
            return trackSelectionArr;
        }

        /* access modifiers changed from: protected */
        public AdaptiveTrackSelection createAdaptiveTrackSelection(TrackGroup trackGroup, BandwidthMeter bandwidthMeter2, int[] iArr) {
            return new AdaptiveTrackSelection(trackGroup, iArr, new DefaultBandwidthProvider(bandwidthMeter2, this.bandwidthFraction), (long) this.minDurationForQualityIncreaseMs, (long) this.maxDurationForQualityDecreaseMs, (long) this.minDurationToRetainAfterDiscardMs, this.bufferedFractionToLiveEdgeForQualityIncrease, this.minTimeBetweenBufferReevaluationMs, this.clock);
        }
    }

    private AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, BandwidthProvider bandwidthProvider2, long j, long j2, long j3, float f, long j4, Clock clock2) {
        super(trackGroup, iArr);
        this.bandwidthProvider = bandwidthProvider2;
        this.minDurationForQualityIncreaseUs = j * 1000;
        this.maxDurationForQualityDecreaseUs = j2 * 1000;
        this.minDurationToRetainAfterDiscardUs = j3 * 1000;
        this.bufferedFractionToLiveEdgeForQualityIncrease = f;
        this.minTimeBetweenBufferReevaluationMs = j4;
        this.clock = clock2;
        this.playbackSpeed = 1.0f;
        this.reason = 0;
        this.lastBufferEvaluationMs = -9223372036854775807L;
        this.trackBitrateEstimator = TrackBitrateEstimator.DEFAULT;
        this.formats = new Format[this.length];
        this.formatBitrates = new int[this.length];
        this.trackBitrates = new int[this.length];
        for (int i = 0; i < this.length; i++) {
            Format format = getFormat(i);
            Format[] formatArr = this.formats;
            formatArr[i] = format;
            this.formatBitrates[i] = formatArr[i].bitrate;
        }
    }

    public void experimental_setTrackBitrateEstimator(TrackBitrateEstimator trackBitrateEstimator2) {
        this.trackBitrateEstimator = trackBitrateEstimator2;
    }

    public void experimental_setNonAllocatableBandwidth(long j) {
        ((DefaultBandwidthProvider) this.bandwidthProvider).experimental_setNonAllocatableBandwidth(j);
    }

    public void experimental_setBandwidthAllocationCheckpoints(long[][] jArr) {
        ((DefaultBandwidthProvider) this.bandwidthProvider).experimental_setBandwidthAllocationCheckpoints(jArr);
    }

    public void enable() {
        this.lastBufferEvaluationMs = -9223372036854775807L;
    }

    public void onPlaybackSpeed(float f) {
        this.playbackSpeed = f;
    }

    public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
        long elapsedRealtime = this.clock.elapsedRealtime();
        this.trackBitrateEstimator.getBitrates(this.formats, list, mediaChunkIteratorArr, this.trackBitrates);
        if (this.reason == 0) {
            this.reason = 1;
            this.selectedIndex = determineIdealSelectedIndex(elapsedRealtime, this.trackBitrates);
            return;
        }
        int i = this.selectedIndex;
        int determineIdealSelectedIndex = determineIdealSelectedIndex(elapsedRealtime, this.trackBitrates);
        this.selectedIndex = determineIdealSelectedIndex;
        if (determineIdealSelectedIndex != i) {
            if (!isBlacklisted(i, elapsedRealtime)) {
                Format format = getFormat(i);
                Format format2 = getFormat(this.selectedIndex);
                if (format2.bitrate > format.bitrate && j2 < minDurationForQualityIncreaseUs(j3)) {
                    this.selectedIndex = i;
                } else if (format2.bitrate < format.bitrate && j2 >= this.maxDurationForQualityDecreaseUs) {
                    this.selectedIndex = i;
                }
            }
            if (this.selectedIndex != i) {
                this.reason = 3;
            }
        }
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public int getSelectionReason() {
        return this.reason;
    }

    /* access modifiers changed from: protected */
    public boolean canSelectFormat(Format format, int i, float f, long j) {
        return ((long) Math.round(((float) i) * f)) <= j;
    }

    private int determineIdealSelectedIndex(long j, int[] iArr) {
        long allocatedBandwidth = this.bandwidthProvider.getAllocatedBandwidth();
        int i = 0;
        for (int i2 = 0; i2 < this.length; i2++) {
            if (j == Long.MIN_VALUE || !isBlacklisted(i2, j)) {
                if (canSelectFormat(getFormat(i2), iArr[i2], this.playbackSpeed, allocatedBandwidth)) {
                    return i2;
                }
                i = i2;
            }
        }
        return i;
    }

    private long minDurationForQualityIncreaseUs(long j) {
        if (j != -9223372036854775807L && j <= this.minDurationForQualityIncreaseUs) {
            return (long) (((float) j) * this.bufferedFractionToLiveEdgeForQualityIncrease);
        }
        return this.minDurationForQualityIncreaseUs;
    }

    private static final class DefaultBandwidthProvider implements BandwidthProvider {
        private long[][] allocationCheckpoints;
        private final float bandwidthFraction;
        private final BandwidthMeter bandwidthMeter;
        private long nonAllocatableBandwidth;

        DefaultBandwidthProvider(BandwidthMeter bandwidthMeter2, float f) {
            this.bandwidthMeter = bandwidthMeter2;
            this.bandwidthFraction = f;
        }

        public long getAllocatedBandwidth() {
            long max = Math.max(0, ((long) (((float) this.bandwidthMeter.getBitrateEstimate()) * this.bandwidthFraction)) - this.nonAllocatableBandwidth);
            if (this.allocationCheckpoints == null) {
                return max;
            }
            int i = 1;
            while (true) {
                long[][] jArr = this.allocationCheckpoints;
                if (i >= jArr.length - 1 || jArr[i][0] >= max) {
                    long[][] jArr2 = this.allocationCheckpoints;
                    long[] jArr3 = jArr2[i - 1];
                    long[] jArr4 = jArr2[i];
                } else {
                    i++;
                }
            }
            long[][] jArr22 = this.allocationCheckpoints;
            long[] jArr32 = jArr22[i - 1];
            long[] jArr42 = jArr22[i];
            return jArr32[1] + ((long) ((((float) (max - jArr32[0])) / ((float) (jArr42[0] - jArr32[0]))) * ((float) (jArr42[1] - jArr32[1]))));
        }

        /* access modifiers changed from: package-private */
        public void experimental_setNonAllocatableBandwidth(long j) {
            this.nonAllocatableBandwidth = j;
        }

        /* access modifiers changed from: package-private */
        public void experimental_setBandwidthAllocationCheckpoints(long[][] jArr) {
            Assertions.checkArgument(jArr.length >= 2);
            this.allocationCheckpoints = jArr;
        }
    }

    /* access modifiers changed from: private */
    public static long[][][] getAllocationCheckpoints(long[][] jArr) {
        int i;
        long[][] jArr2 = jArr;
        double[][] logArrayValues = getLogArrayValues(jArr);
        double[][] switchPoints = getSwitchPoints(logArrayValues);
        int countArrayElements = countArrayElements(switchPoints) + 3;
        int length = logArrayValues.length;
        int[] iArr = new int[3];
        iArr[2] = 2;
        iArr[1] = countArrayElements;
        iArr[0] = length;
        long[][][] jArr3 = (long[][][]) Array.newInstance(long.class, iArr);
        int[] iArr2 = new int[logArrayValues.length];
        setCheckpointValues(jArr3, 1, jArr2, iArr2);
        int i2 = 2;
        while (true) {
            i = countArrayElements - 1;
            if (i2 >= i) {
                break;
            }
            double d = Double.MAX_VALUE;
            int i3 = 0;
            for (int i4 = 0; i4 < logArrayValues.length; i4++) {
                if (iArr2[i4] + 1 != logArrayValues[i4].length) {
                    double d2 = switchPoints[i4][iArr2[i4]];
                    if (d2 < d) {
                        i3 = i4;
                        d = d2;
                    }
                }
            }
            iArr2[i3] = iArr2[i3] + 1;
            setCheckpointValues(jArr3, i2, jArr2, iArr2);
            i2++;
        }
        for (long[][] jArr4 : jArr3) {
            int i5 = countArrayElements - 2;
            jArr4[i][0] = jArr4[i5][0] * 2;
            jArr4[i][1] = jArr4[i5][1] * 2;
        }
        return jArr3;
    }

    private static double[][] getLogArrayValues(long[][] jArr) {
        double[][] dArr = new double[jArr.length][];
        for (int i = 0; i < jArr.length; i++) {
            dArr[i] = new double[jArr[i].length];
            for (int i2 = 0; i2 < jArr[i].length; i2++) {
                dArr[i][i2] = Math.log((double) jArr[i][i2]);
            }
        }
        return dArr;
    }

    private static double[][] getSwitchPoints(double[][] dArr) {
        double[][] dArr2 = new double[dArr.length][];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = new double[(dArr[i].length - 1)];
            if (dArr2[i].length != 0) {
                double d = dArr[i][dArr[i].length - 1] - dArr[i][0];
                int i2 = 0;
                while (i2 < dArr[i].length - 1) {
                    int i3 = i2 + 1;
                    dArr2[i][i2] = (((dArr[i][i2] + dArr[i][i3]) * 0.5d) - dArr[i][0]) / d;
                    i2 = i3;
                }
            }
        }
        return dArr2;
    }

    private static int countArrayElements(double[][] dArr) {
        int i = 0;
        for (double[] length : dArr) {
            i += length.length;
        }
        return i;
    }

    private static void setCheckpointValues(long[][][] jArr, int i, long[][] jArr2, int[] iArr) {
        long j = 0;
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2][i][1] = jArr2[i2][iArr[i2]];
            j += jArr[i2][i][1];
        }
        for (long[][] jArr3 : jArr) {
            jArr3[i][0] = j;
        }
    }
}
