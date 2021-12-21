package com.google.android.exoplayer2;

import com.appnext.base.b.d;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.mopub.volley.DefaultRetryPolicy;

public class DefaultLoadControl implements LoadControl {
    private final DefaultAllocator allocator;
    private final long backBufferDurationUs;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private boolean hasVideo;
    private boolean isBuffering;
    private final long maxBufferUs;
    private final long minBufferAudioUs;
    private final long minBufferVideoUs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final boolean retainBackBufferFromKeyframe;
    private final int targetBufferBytesOverwrite;
    private int targetBufferSize;

    public static final class Builder {
        private DefaultAllocator allocator;
        private int backBufferDurationMs = 0;
        private int bufferForPlaybackAfterRebufferMs = 5000;
        private int bufferForPlaybackMs = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
        private boolean createDefaultLoadControlCalled;
        private int maxBufferMs = 50000;
        private int minBufferAudioMs = d.fd;
        private int minBufferVideoMs = 50000;
        private boolean prioritizeTimeOverSizeThresholds = true;
        private boolean retainBackBufferFromKeyframe = false;
        private int targetBufferBytes = -1;

        public Builder setAllocator(DefaultAllocator defaultAllocator) {
            Assertions.checkState(!this.createDefaultLoadControlCalled);
            this.allocator = defaultAllocator;
            return this;
        }

        public DefaultLoadControl createDefaultLoadControl() {
            Assertions.checkState(!this.createDefaultLoadControlCalled);
            this.createDefaultLoadControlCalled = true;
            if (this.allocator == null) {
                this.allocator = new DefaultAllocator(true, 65536);
            }
            return new DefaultLoadControl(this.allocator, this.minBufferAudioMs, this.minBufferVideoMs, this.maxBufferMs, this.bufferForPlaybackMs, this.bufferForPlaybackAfterRebufferMs, this.targetBufferBytes, this.prioritizeTimeOverSizeThresholds, this.backBufferDurationMs, this.retainBackBufferFromKeyframe);
        }
    }

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, 65536));
    }

    @Deprecated
    public DefaultLoadControl(DefaultAllocator defaultAllocator) {
        this(defaultAllocator, d.fd, 50000, 50000, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 5000, -1, true, 0, false);
    }

    protected DefaultLoadControl(DefaultAllocator defaultAllocator, int i, int i2, int i3, int i4, int i5, int i6, boolean z, int i7, boolean z2) {
        int i8 = i2;
        int i9 = i3;
        int i10 = i4;
        int i11 = i5;
        int i12 = i7;
        assertGreaterOrEqual(i10, 0, "bufferForPlaybackMs", "0");
        assertGreaterOrEqual(i11, 0, "bufferForPlaybackAfterRebufferMs", "0");
        assertGreaterOrEqual(i, i10, "minBufferAudioMs", "bufferForPlaybackMs");
        assertGreaterOrEqual(i8, i10, "minBufferVideoMs", "bufferForPlaybackMs");
        assertGreaterOrEqual(i, i11, "minBufferAudioMs", "bufferForPlaybackAfterRebufferMs");
        assertGreaterOrEqual(i8, i11, "minBufferVideoMs", "bufferForPlaybackAfterRebufferMs");
        assertGreaterOrEqual(i9, i, "maxBufferMs", "minBufferAudioMs");
        assertGreaterOrEqual(i9, i8, "maxBufferMs", "minBufferVideoMs");
        assertGreaterOrEqual(i12, 0, "backBufferDurationMs", "0");
        this.allocator = defaultAllocator;
        this.minBufferAudioUs = C.msToUs((long) i);
        this.minBufferVideoUs = C.msToUs((long) i8);
        this.maxBufferUs = C.msToUs((long) i9);
        this.bufferForPlaybackUs = C.msToUs((long) i10);
        this.bufferForPlaybackAfterRebufferUs = C.msToUs((long) i11);
        this.targetBufferBytesOverwrite = i6;
        this.prioritizeTimeOverSizeThresholds = z;
        this.backBufferDurationUs = C.msToUs((long) i12);
        this.retainBackBufferFromKeyframe = z2;
    }

    public void onPrepared() {
        reset(false);
    }

    public void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        this.hasVideo = hasVideo(rendererArr, trackSelectionArray);
        int i = this.targetBufferBytesOverwrite;
        if (i == -1) {
            i = calculateTargetBufferSize(rendererArr, trackSelectionArray);
        }
        this.targetBufferSize = i;
        this.allocator.setTargetBufferSize(i);
    }

    public void onStopped() {
        reset(true);
    }

    public void onReleased() {
        reset(true);
    }

    public Allocator getAllocator() {
        return this.allocator;
    }

    public long getBackBufferDurationUs() {
        return this.backBufferDurationUs;
    }

    public boolean retainBackBufferFromKeyframe() {
        return this.retainBackBufferFromKeyframe;
    }

    public boolean shouldContinueLoading(long j, float f) {
        boolean z = true;
        boolean z2 = this.allocator.getTotalBytesAllocated() >= this.targetBufferSize;
        long j2 = this.hasVideo ? this.minBufferVideoUs : this.minBufferAudioUs;
        if (f > 1.0f) {
            j2 = Math.min(Util.getMediaDurationForPlayoutDuration(j2, f), this.maxBufferUs);
        }
        if (j < j2) {
            if (!this.prioritizeTimeOverSizeThresholds && z2) {
                z = false;
            }
            this.isBuffering = z;
        } else if (j >= this.maxBufferUs || z2) {
            this.isBuffering = false;
        }
        return this.isBuffering;
    }

    public boolean shouldStartPlayback(long j, float f, boolean z) {
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(j, f);
        long j2 = z ? this.bufferForPlaybackAfterRebufferUs : this.bufferForPlaybackUs;
        return j2 <= 0 || playoutDurationForMediaDuration >= j2 || (!this.prioritizeTimeOverSizeThresholds && this.allocator.getTotalBytesAllocated() >= this.targetBufferSize);
    }

    /* access modifiers changed from: protected */
    public int calculateTargetBufferSize(Renderer[] rendererArr, TrackSelectionArray trackSelectionArray) {
        int i = 0;
        for (int i2 = 0; i2 < rendererArr.length; i2++) {
            if (trackSelectionArray.get(i2) != null) {
                i += getDefaultBufferSize(rendererArr[i2].getTrackType());
            }
        }
        return i;
    }

    private void reset(boolean z) {
        this.targetBufferSize = 0;
        this.isBuffering = false;
        if (z) {
            this.allocator.reset();
        }
    }

    private static int getDefaultBufferSize(int i) {
        switch (i) {
            case 0:
                return 36438016;
            case 1:
                return 3538944;
            case 2:
                return 32768000;
            case 3:
            case 4:
            case 5:
                return 131072;
            case 6:
                return 0;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static boolean hasVideo(Renderer[] rendererArr, TrackSelectionArray trackSelectionArray) {
        for (int i = 0; i < rendererArr.length; i++) {
            if (rendererArr[i].getTrackType() == 2 && trackSelectionArray.get(i) != null) {
                return true;
            }
        }
        return false;
    }

    private static void assertGreaterOrEqual(int i, int i2, String str, String str2) {
        boolean z = i >= i2;
        Assertions.checkArgument(z, str + " cannot be less than " + str2);
    }
}
