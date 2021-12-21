package androidx.media2.exoplayer.external.video;

import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Pair;
import android.view.Surface;
import androidx.media2.exoplayer.external.ExoPlaybackException;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.FormatHolder;
import androidx.media2.exoplayer.external.decoder.DecoderInputBuffer;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.drm.DrmSessionManager;
import androidx.media2.exoplayer.external.drm.FrameworkMediaCrypto;
import androidx.media2.exoplayer.external.mediacodec.MediaCodecInfo;
import androidx.media2.exoplayer.external.mediacodec.MediaCodecRenderer;
import androidx.media2.exoplayer.external.mediacodec.MediaCodecSelector;
import androidx.media2.exoplayer.external.mediacodec.MediaCodecUtil;
import androidx.media2.exoplayer.external.mediacodec.MediaFormatUtil;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.util.MimeTypes;
import androidx.media2.exoplayer.external.util.TraceUtil;
import androidx.media2.exoplayer.external.util.Util;
import androidx.media2.exoplayer.external.video.VideoRendererEventListener;
import com.google.android.gms.common.Scopes;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public class MediaCodecVideoRenderer extends MediaCodecRenderer {
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = {1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    private static boolean deviceNeedsSetOutputSurfaceWorkaround;
    private static boolean evaluatedDeviceNeedsSetOutputSurfaceWorkaround;
    private final long allowedJoiningTimeMs;
    private int buffersInCodecCount;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private final Context context;
    private int currentHeight;
    private float currentPixelWidthHeightRatio;
    private int currentUnappliedRotationDegrees;
    private int currentWidth;
    private final boolean deviceNeedsNoPostProcessWorkaround;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private Surface dummySurface;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    private VideoFrameMetadataListener frameMetadataListener;
    private final VideoFrameReleaseTimeHelper frameReleaseTimeHelper;
    private long initialPositionUs;
    private long joiningDeadlineMs;
    private long lastInputTimeUs;
    private long lastRenderTimeUs;
    private final int maxDroppedFramesToNotify;
    private long outputStreamOffsetUs;
    private int pendingOutputStreamOffsetCount;
    private final long[] pendingOutputStreamOffsetsUs;
    private final long[] pendingOutputStreamSwitchTimesUs;
    private float pendingPixelWidthHeightRatio;
    private int pendingRotationDegrees;
    private boolean renderedFirstFrame;
    private int reportedHeight;
    private float reportedPixelWidthHeightRatio;
    private int reportedUnappliedRotationDegrees;
    private int reportedWidth;
    private int scalingMode;
    private Surface surface;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;

    private static boolean isBufferLate(long j) {
        return j < -30000;
    }

    private static boolean isBufferVeryLate(long j) {
        return j < -500000;
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        this(context2, mediaCodecSelector, j, drmSessionManager, z, false, handler, videoRendererEventListener, i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, boolean z2, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        super(2, mediaCodecSelector, drmSessionManager, z, z2, 30.0f);
        this.allowedJoiningTimeMs = j;
        this.maxDroppedFramesToNotify = i;
        Context applicationContext = context2.getApplicationContext();
        this.context = applicationContext;
        this.frameReleaseTimeHelper = new VideoFrameReleaseTimeHelper(applicationContext);
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.deviceNeedsNoPostProcessWorkaround = deviceNeedsNoPostProcessWorkaround();
        this.pendingOutputStreamOffsetsUs = new long[10];
        this.pendingOutputStreamSwitchTimesUs = new long[10];
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.lastInputTimeUs = -9223372036854775807L;
        this.joiningDeadlineMs = -9223372036854775807L;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.scalingMode = 1;
        clearReportedVideoSize();
    }

    /* access modifiers changed from: protected */
    public int supportsFormat(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, Format format) throws MediaCodecUtil.DecoderQueryException {
        int i = 0;
        if (!MimeTypes.isVideo(format.sampleMimeType)) {
            return 0;
        }
        DrmInitData drmInitData = format.drmInitData;
        boolean z = drmInitData != null;
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(mediaCodecSelector, format, z, false);
        if (z && decoderInfos.isEmpty()) {
            decoderInfos = getDecoderInfos(mediaCodecSelector, format, false, false);
        }
        if (decoderInfos.isEmpty()) {
            return 1;
        }
        if (!supportsFormatDrm(drmSessionManager, drmInitData)) {
            return 2;
        }
        MediaCodecInfo mediaCodecInfo = decoderInfos.get(0);
        boolean isFormatSupported = mediaCodecInfo.isFormatSupported(format);
        int i2 = mediaCodecInfo.isSeamlessAdaptationSupported(format) ? 16 : 8;
        if (isFormatSupported) {
            List<MediaCodecInfo> decoderInfos2 = getDecoderInfos(mediaCodecSelector, format, z, true);
            if (!decoderInfos2.isEmpty()) {
                MediaCodecInfo mediaCodecInfo2 = decoderInfos2.get(0);
                if (mediaCodecInfo2.isFormatSupported(format) && mediaCodecInfo2.isSeamlessAdaptationSupported(format)) {
                    i = 32;
                }
            }
        }
        return (isFormatSupported ? 4 : 3) | i2 | i;
    }

    /* access modifiers changed from: protected */
    public List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return getDecoderInfos(mediaCodecSelector, format, z, this.tunneling);
    }

    private static List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException {
        Pair<Integer, Integer> codecProfileAndLevel;
        List<MediaCodecInfo> decoderInfosSortedByFormatSupport = MediaCodecUtil.getDecoderInfosSortedByFormatSupport(mediaCodecSelector.getDecoderInfos(format.sampleMimeType, z, z2), format);
        if ("video/dolby-vision".equals(format.sampleMimeType) && (codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format.codecs)) != null) {
            int intValue = ((Integer) codecProfileAndLevel.first).intValue();
            if (intValue == 4 || intValue == 8) {
                decoderInfosSortedByFormatSupport.addAll(mediaCodecSelector.getDecoderInfos("video/hevc", z, z2));
            } else if (intValue == 9) {
                decoderInfosSortedByFormatSupport.addAll(mediaCodecSelector.getDecoderInfos("video/avc", z, z2));
            }
        }
        return Collections.unmodifiableList(decoderInfosSortedByFormatSupport);
    }

    /* access modifiers changed from: protected */
    public void onEnabled(boolean z) throws ExoPlaybackException {
        super.onEnabled(z);
        int i = this.tunnelingAudioSessionId;
        int i2 = getConfiguration().tunnelingAudioSessionId;
        this.tunnelingAudioSessionId = i2;
        this.tunneling = i2 != 0;
        if (this.tunnelingAudioSessionId != i) {
            releaseCodec();
        }
        this.eventDispatcher.enabled(this.decoderCounters);
        this.frameReleaseTimeHelper.enable();
    }

    /* access modifiers changed from: protected */
    public void onStreamChanged(Format[] formatArr, long j) throws ExoPlaybackException {
        if (this.outputStreamOffsetUs == -9223372036854775807L) {
            this.outputStreamOffsetUs = j;
        } else {
            int i = this.pendingOutputStreamOffsetCount;
            long[] jArr = this.pendingOutputStreamOffsetsUs;
            if (i == jArr.length) {
                long j2 = jArr[i - 1];
                StringBuilder sb = new StringBuilder(65);
                sb.append("Too many stream changes, so dropping offset: ");
                sb.append(j2);
                Log.w("MediaCodecVideoRenderer", sb.toString());
            } else {
                this.pendingOutputStreamOffsetCount = i + 1;
            }
            long[] jArr2 = this.pendingOutputStreamOffsetsUs;
            int i2 = this.pendingOutputStreamOffsetCount;
            jArr2[i2 - 1] = j;
            this.pendingOutputStreamSwitchTimesUs[i2 - 1] = this.lastInputTimeUs;
        }
        super.onStreamChanged(formatArr, j);
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        super.onPositionReset(j, z);
        clearRenderedFirstFrame();
        this.initialPositionUs = -9223372036854775807L;
        this.consecutiveDroppedFrameCount = 0;
        this.lastInputTimeUs = -9223372036854775807L;
        int i = this.pendingOutputStreamOffsetCount;
        if (i != 0) {
            this.outputStreamOffsetUs = this.pendingOutputStreamOffsetsUs[i - 1];
            this.pendingOutputStreamOffsetCount = 0;
        }
        if (z) {
            setJoiningDeadlineMs();
        } else {
            this.joiningDeadlineMs = -9223372036854775807L;
        }
    }

    public boolean isReady() {
        Surface surface2;
        if (super.isReady() && (this.renderedFirstFrame || (((surface2 = this.dummySurface) != null && this.surface == surface2) || getCodec() == null || this.tunneling))) {
            this.joiningDeadlineMs = -9223372036854775807L;
            return true;
        } else if (this.joiningDeadlineMs == -9223372036854775807L) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() < this.joiningDeadlineMs) {
                return true;
            }
            this.joiningDeadlineMs = -9223372036854775807L;
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
    }

    /* access modifiers changed from: protected */
    public void onStopped() {
        this.joiningDeadlineMs = -9223372036854775807L;
        maybeNotifyDroppedFrames();
        super.onStopped();
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        this.lastInputTimeUs = -9223372036854775807L;
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.pendingOutputStreamOffsetCount = 0;
        clearReportedVideoSize();
        clearRenderedFirstFrame();
        this.frameReleaseTimeHelper.disable();
        this.tunnelingOnFrameRenderedListener = null;
        try {
            super.onDisabled();
        } finally {
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        try {
            super.onReset();
        } finally {
            Surface surface2 = this.dummySurface;
            if (surface2 != null) {
                if (this.surface == surface2) {
                    this.surface = null;
                }
                this.dummySurface.release();
                this.dummySurface = null;
            }
        }
    }

    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setSurface((Surface) obj);
        } else if (i == 4) {
            this.scalingMode = ((Integer) obj).intValue();
            MediaCodec codec = getCodec();
            if (codec != null) {
                codec.setVideoScalingMode(this.scalingMode);
            }
        } else if (i == 6) {
            this.frameMetadataListener = (VideoFrameMetadataListener) obj;
        } else {
            super.handleMessage(i, obj);
        }
    }

    private void setSurface(Surface surface2) throws ExoPlaybackException {
        if (surface2 == null) {
            Surface surface3 = this.dummySurface;
            if (surface3 != null) {
                surface2 = surface3;
            } else {
                MediaCodecInfo codecInfo = getCodecInfo();
                if (codecInfo != null && shouldUseDummySurface(codecInfo)) {
                    surface2 = DummySurface.newInstanceV17(this.context, codecInfo.secure);
                    this.dummySurface = surface2;
                }
            }
        }
        if (this.surface != surface2) {
            this.surface = surface2;
            int state = getState();
            MediaCodec codec = getCodec();
            if (codec != null) {
                if (Util.SDK_INT < 23 || surface2 == null || this.codecNeedsSetOutputSurfaceWorkaround) {
                    releaseCodec();
                    maybeInitCodec();
                } else {
                    setOutputSurfaceV23(codec, surface2);
                }
            }
            if (surface2 == null || surface2 == this.dummySurface) {
                clearReportedVideoSize();
                clearRenderedFirstFrame();
                return;
            }
            maybeRenotifyVideoSizeChanged();
            clearRenderedFirstFrame();
            if (state == 2) {
                setJoiningDeadlineMs();
            }
        } else if (surface2 != null && surface2 != this.dummySurface) {
            maybeRenotifyVideoSizeChanged();
            maybeRenotifyRenderedFirstFrame();
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return this.surface != null || shouldUseDummySurface(mediaCodecInfo);
    }

    /* access modifiers changed from: protected */
    public boolean getCodecNeedsEosPropagation() {
        return this.tunneling;
    }

    /* access modifiers changed from: protected */
    public void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto, float f) throws MediaCodecUtil.DecoderQueryException {
        CodecMaxValues codecMaxValues2 = getCodecMaxValues(mediaCodecInfo, format, getStreamFormats());
        this.codecMaxValues = codecMaxValues2;
        MediaFormat mediaFormat = getMediaFormat(format, codecMaxValues2, f, this.deviceNeedsNoPostProcessWorkaround, this.tunnelingAudioSessionId);
        if (this.surface == null) {
            Assertions.checkState(shouldUseDummySurface(mediaCodecInfo));
            if (this.dummySurface == null) {
                this.dummySurface = DummySurface.newInstanceV17(this.context, mediaCodecInfo.secure);
            }
            this.surface = this.dummySurface;
        }
        mediaCodec.configure(mediaFormat, this.surface, mediaCrypto, 0);
        if (Util.SDK_INT >= 23 && this.tunneling) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(mediaCodec);
        }
    }

    /* access modifiers changed from: protected */
    public int canKeepCodec(MediaCodec mediaCodec, MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        if (!mediaCodecInfo.isSeamlessAdaptationSupported(format, format2, true) || format2.width > this.codecMaxValues.width || format2.height > this.codecMaxValues.height || getMaxInputSize(mediaCodecInfo, format2) > this.codecMaxValues.inputSize) {
            return 0;
        }
        return format.initializationDataEquals(format2) ? 3 : 2;
    }

    /* access modifiers changed from: protected */
    public void releaseCodec() {
        try {
            super.releaseCodec();
        } finally {
            this.buffersInCodecCount = 0;
        }
    }

    /* access modifiers changed from: protected */
    public boolean flushOrReleaseCodec() {
        try {
            return super.flushOrReleaseCodec();
        } finally {
            this.buffersInCodecCount = 0;
        }
    }

    /* access modifiers changed from: protected */
    public float getCodecOperatingRateV23(float f, Format format, Format[] formatArr) {
        float f2 = -1.0f;
        for (Format format2 : formatArr) {
            float f3 = format2.frameRate;
            if (f3 != -1.0f) {
                f2 = Math.max(f2, f3);
            }
        }
        if (f2 == -1.0f) {
            return -1.0f;
        }
        return f2 * f;
    }

    /* access modifiers changed from: protected */
    public void onCodecInitialized(String str, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(str);
    }

    /* access modifiers changed from: protected */
    public void onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        super.onInputFormatChanged(formatHolder);
        Format format = formatHolder.format;
        this.eventDispatcher.inputFormatChanged(format);
        this.pendingPixelWidthHeightRatio = format.pixelWidthHeightRatio;
        this.pendingRotationDegrees = format.rotationDegrees;
    }

    /* access modifiers changed from: protected */
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        this.buffersInCodecCount++;
        this.lastInputTimeUs = Math.max(decoderInputBuffer.timeUs, this.lastInputTimeUs);
        if (Util.SDK_INT < 23 && this.tunneling) {
            onProcessedTunneledBuffer(decoderInputBuffer.timeUs);
        }
    }

    /* access modifiers changed from: protected */
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int i;
        int i2;
        boolean z = mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
        if (z) {
            i = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            i = mediaFormat.getInteger("width");
        }
        if (z) {
            i2 = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            i2 = mediaFormat.getInteger("height");
        }
        processOutputFormat(mediaCodec, i, i2);
    }

    /* access modifiers changed from: protected */
    public boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z, Format format) throws ExoPlaybackException {
        long j4 = j;
        long j5 = j2;
        MediaCodec mediaCodec2 = mediaCodec;
        int i3 = i;
        long j6 = j3;
        if (this.initialPositionUs == -9223372036854775807L) {
            this.initialPositionUs = j4;
        }
        long j7 = j6 - this.outputStreamOffsetUs;
        if (z) {
            skipOutputBuffer(mediaCodec2, i3, j7);
            return true;
        }
        long j8 = j6 - j4;
        if (this.surface != this.dummySurface) {
            long elapsedRealtime = SystemClock.elapsedRealtime() * 1000;
            boolean z2 = getState() == 2;
            if (!this.renderedFirstFrame || (z2 && shouldForceRenderOutputBuffer(j8, elapsedRealtime - this.lastRenderTimeUs))) {
                long nanoTime = System.nanoTime();
                notifyFrameMetadataListener(j7, nanoTime, format);
                if (Util.SDK_INT >= 21) {
                    renderOutputBufferV21(mediaCodec, i, j7, nanoTime);
                    return true;
                }
                renderOutputBuffer(mediaCodec2, i3, j7);
                return true;
            }
            if (z2 && j4 != this.initialPositionUs) {
                long nanoTime2 = System.nanoTime();
                long adjustReleaseTime = this.frameReleaseTimeHelper.adjustReleaseTime(j6, ((j8 - (elapsedRealtime - j5)) * 1000) + nanoTime2);
                long j9 = (adjustReleaseTime - nanoTime2) / 1000;
                if (shouldDropBuffersToKeyframe(j9, j5) && maybeDropBuffersToKeyframe(mediaCodec, i, j7, j)) {
                    return false;
                }
                if (shouldDropOutputBuffer(j9, j5)) {
                    dropOutputBuffer(mediaCodec2, i3, j7);
                    return true;
                } else if (Util.SDK_INT >= 21) {
                    if (j9 < 50000) {
                        notifyFrameMetadataListener(j7, adjustReleaseTime, format);
                        renderOutputBufferV21(mediaCodec, i, j7, adjustReleaseTime);
                        return true;
                    }
                } else if (j9 < 30000) {
                    if (j9 > 11000) {
                        try {
                            Thread.sleep((j9 - 10000) / 1000);
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                            return false;
                        }
                    }
                    notifyFrameMetadataListener(j7, adjustReleaseTime, format);
                    renderOutputBuffer(mediaCodec2, i3, j7);
                    return true;
                }
            }
            return false;
        } else if (!isBufferLate(j8)) {
            return false;
        } else {
            skipOutputBuffer(mediaCodec2, i3, j7);
            return true;
        }
    }

    private void processOutputFormat(MediaCodec mediaCodec, int i, int i2) {
        this.currentWidth = i;
        this.currentHeight = i2;
        this.currentPixelWidthHeightRatio = this.pendingPixelWidthHeightRatio;
        if (Util.SDK_INT >= 21) {
            int i3 = this.pendingRotationDegrees;
            if (i3 == 90 || i3 == 270) {
                int i4 = this.currentWidth;
                this.currentWidth = this.currentHeight;
                this.currentHeight = i4;
                this.currentPixelWidthHeightRatio = 1.0f / this.currentPixelWidthHeightRatio;
            }
        } else {
            this.currentUnappliedRotationDegrees = this.pendingRotationDegrees;
        }
        mediaCodec.setVideoScalingMode(this.scalingMode);
    }

    private void notifyFrameMetadataListener(long j, long j2, Format format) {
        VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
        if (videoFrameMetadataListener != null) {
            videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j, j2, format);
        }
    }

    /* access modifiers changed from: protected */
    public void onProcessedTunneledBuffer(long j) {
        Format updateOutputFormatForTime = updateOutputFormatForTime(j);
        if (updateOutputFormatForTime != null) {
            processOutputFormat(getCodec(), updateOutputFormatForTime.width, updateOutputFormatForTime.height);
        }
        maybeNotifyVideoSizeChanged();
        maybeNotifyRenderedFirstFrame();
        onProcessedOutputBuffer(j);
    }

    /* access modifiers changed from: protected */
    public void onProcessedOutputBuffer(long j) {
        this.buffersInCodecCount--;
        while (true) {
            int i = this.pendingOutputStreamOffsetCount;
            if (i != 0 && j >= this.pendingOutputStreamSwitchTimesUs[0]) {
                long[] jArr = this.pendingOutputStreamOffsetsUs;
                this.outputStreamOffsetUs = jArr[0];
                int i2 = i - 1;
                this.pendingOutputStreamOffsetCount = i2;
                System.arraycopy(jArr, 1, jArr, 0, i2);
                long[] jArr2 = this.pendingOutputStreamSwitchTimesUs;
                System.arraycopy(jArr2, 1, jArr2, 0, this.pendingOutputStreamOffsetCount);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropOutputBuffer(long j, long j2) {
        return isBufferLate(j);
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropBuffersToKeyframe(long j, long j2) {
        return isBufferVeryLate(j);
    }

    /* access modifiers changed from: protected */
    public boolean shouldForceRenderOutputBuffer(long j, long j2) {
        return isBufferLate(j) && j2 > 100000;
    }

    /* access modifiers changed from: protected */
    public void skipOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        this.decoderCounters.skippedOutputBufferCount++;
    }

    /* access modifiers changed from: protected */
    public void dropOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        updateDroppedBufferCounters(1);
    }

    /* access modifiers changed from: protected */
    public boolean maybeDropBuffersToKeyframe(MediaCodec mediaCodec, int i, long j, long j2) throws ExoPlaybackException {
        int skipSource = skipSource(j2);
        if (skipSource == 0) {
            return false;
        }
        this.decoderCounters.droppedToKeyframeCount++;
        updateDroppedBufferCounters(this.buffersInCodecCount + skipSource);
        flushOrReinitializeCodec();
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateDroppedBufferCounters(int i) {
        this.decoderCounters.droppedBufferCount += i;
        this.droppedFrames += i;
        this.consecutiveDroppedFrameCount += i;
        this.decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(this.consecutiveDroppedFrameCount, this.decoderCounters.maxConsecutiveDroppedBufferCount);
        int i2 = this.maxDroppedFramesToNotify;
        if (i2 > 0 && this.droppedFrames >= i2) {
            maybeNotifyDroppedFrames();
        }
    }

    /* access modifiers changed from: protected */
    public void renderOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    /* access modifiers changed from: protected */
    public void renderOutputBufferV21(MediaCodec mediaCodec, int i, long j, long j2) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, j2);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    private boolean shouldUseDummySurface(MediaCodecInfo mediaCodecInfo) {
        return Util.SDK_INT >= 23 && !this.tunneling && !codecNeedsSetOutputSurfaceWorkaround(mediaCodecInfo.name) && (!mediaCodecInfo.secure || DummySurface.isSecureSupported(this.context));
    }

    private void setJoiningDeadlineMs() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : -9223372036854775807L;
    }

    private void clearRenderedFirstFrame() {
        MediaCodec codec;
        this.renderedFirstFrame = false;
        if (Util.SDK_INT >= 23 && this.tunneling && (codec = getCodec()) != null) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(codec);
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeNotifyRenderedFirstFrame() {
        if (!this.renderedFirstFrame) {
            this.renderedFirstFrame = true;
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if (this.renderedFirstFrame) {
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void clearReportedVideoSize() {
        this.reportedWidth = -1;
        this.reportedHeight = -1;
        this.reportedPixelWidthHeightRatio = -1.0f;
        this.reportedUnappliedRotationDegrees = -1;
    }

    private void maybeNotifyVideoSizeChanged() {
        if (this.currentWidth != -1 || this.currentHeight != -1) {
            if (this.reportedWidth != this.currentWidth || this.reportedHeight != this.currentHeight || this.reportedUnappliedRotationDegrees != this.currentUnappliedRotationDegrees || this.reportedPixelWidthHeightRatio != this.currentPixelWidthHeightRatio) {
                this.eventDispatcher.videoSizeChanged(this.currentWidth, this.currentHeight, this.currentUnappliedRotationDegrees, this.currentPixelWidthHeightRatio);
                this.reportedWidth = this.currentWidth;
                this.reportedHeight = this.currentHeight;
                this.reportedUnappliedRotationDegrees = this.currentUnappliedRotationDegrees;
                this.reportedPixelWidthHeightRatio = this.currentPixelWidthHeightRatio;
            }
        }
    }

    private void maybeRenotifyVideoSizeChanged() {
        if (this.reportedWidth != -1 || this.reportedHeight != -1) {
            this.eventDispatcher.videoSizeChanged(this.reportedWidth, this.reportedHeight, this.reportedUnappliedRotationDegrees, this.reportedPixelWidthHeightRatio);
        }
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, elapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = elapsedRealtime;
        }
    }

    private static void setOutputSurfaceV23(MediaCodec mediaCodec, Surface surface2) {
        mediaCodec.setOutputSurface(surface2);
    }

    private static void configureTunnelingV21(MediaFormat mediaFormat, int i) {
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", i);
    }

    /* access modifiers changed from: protected */
    public MediaFormat getMediaFormat(Format format, CodecMaxValues codecMaxValues2, float f, boolean z, int i) {
        Pair<Integer, Integer> codecProfileAndLevel;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", format.sampleMimeType);
        mediaFormat.setInteger("width", format.width);
        mediaFormat.setInteger("height", format.height);
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        MediaFormatUtil.maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "rotation-degrees", format.rotationDegrees);
        MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
        if ("video/dolby-vision".equals(format.sampleMimeType) && (codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format.codecs)) != null) {
            MediaFormatUtil.maybeSetInteger(mediaFormat, Scopes.PROFILE, ((Integer) codecProfileAndLevel.first).intValue());
        }
        mediaFormat.setInteger("max-width", codecMaxValues2.width);
        mediaFormat.setInteger("max-height", codecMaxValues2.height);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "max-input-size", codecMaxValues2.inputSize);
        if (Util.SDK_INT >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f != -1.0f) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (z) {
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (i != 0) {
            configureTunnelingV21(mediaFormat, i);
        }
        return mediaFormat;
    }

    /* access modifiers changed from: protected */
    public CodecMaxValues getCodecMaxValues(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) throws MediaCodecUtil.DecoderQueryException {
        int codecMaxInputSize;
        int i = format.width;
        int i2 = format.height;
        int maxInputSize = getMaxInputSize(mediaCodecInfo, format);
        if (formatArr.length == 1) {
            if (!(maxInputSize == -1 || (codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, format.width, format.height)) == -1)) {
                maxInputSize = Math.min((int) (((float) maxInputSize) * 1.5f), codecMaxInputSize);
            }
            return new CodecMaxValues(i, i2, maxInputSize);
        }
        boolean z = false;
        for (Format format2 : formatArr) {
            if (mediaCodecInfo.isSeamlessAdaptationSupported(format, format2, false)) {
                z |= format2.width == -1 || format2.height == -1;
                i = Math.max(i, format2.width);
                i2 = Math.max(i2, format2.height);
                maxInputSize = Math.max(maxInputSize, getMaxInputSize(mediaCodecInfo, format2));
            }
        }
        if (z) {
            StringBuilder sb = new StringBuilder(66);
            sb.append("Resolutions unknown. Codec max resolution: ");
            sb.append(i);
            sb.append(AvidJSONUtil.KEY_X);
            sb.append(i2);
            Log.w("MediaCodecVideoRenderer", sb.toString());
            Point codecMaxSize = getCodecMaxSize(mediaCodecInfo, format);
            if (codecMaxSize != null) {
                i = Math.max(i, codecMaxSize.x);
                i2 = Math.max(i2, codecMaxSize.y);
                maxInputSize = Math.max(maxInputSize, getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, i, i2));
                StringBuilder sb2 = new StringBuilder(57);
                sb2.append("Codec max resolution adjusted to: ");
                sb2.append(i);
                sb2.append(AvidJSONUtil.KEY_X);
                sb2.append(i2);
                Log.w("MediaCodecVideoRenderer", sb2.toString());
            }
        }
        return new CodecMaxValues(i, i2, maxInputSize);
    }

    private static Point getCodecMaxSize(MediaCodecInfo mediaCodecInfo, Format format) throws MediaCodecUtil.DecoderQueryException {
        boolean z = format.height > format.width;
        int i = z ? format.height : format.width;
        int i2 = z ? format.width : format.height;
        float f = ((float) i2) / ((float) i);
        for (int i3 : STANDARD_LONG_EDGE_VIDEO_PX) {
            int i4 = (int) (((float) i3) * f);
            if (i3 <= i || i4 <= i2) {
                break;
            }
            if (Util.SDK_INT >= 21) {
                int i5 = z ? i4 : i3;
                if (!z) {
                    i3 = i4;
                }
                Point alignVideoSizeV21 = mediaCodecInfo.alignVideoSizeV21(i5, i3);
                if (mediaCodecInfo.isVideoSizeAndRateSupportedV21(alignVideoSizeV21.x, alignVideoSizeV21.y, (double) format.frameRate)) {
                    return alignVideoSizeV21;
                }
            } else {
                int ceilDivide = Util.ceilDivide(i3, 16) * 16;
                int ceilDivide2 = Util.ceilDivide(i4, 16) * 16;
                if (ceilDivide * ceilDivide2 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                    int i6 = z ? ceilDivide2 : ceilDivide;
                    if (!z) {
                        ceilDivide = ceilDivide2;
                    }
                    return new Point(i6, ceilDivide);
                }
            }
        }
        return null;
    }

    private static int getMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        if (format.maxInputSize == -1) {
            return getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, format.width, format.height);
        }
        int size = format.initializationData.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += format.initializationData.get(i2).length;
        }
        return format.maxInputSize + i;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int getCodecMaxInputSize(androidx.media2.exoplayer.external.mediacodec.MediaCodecInfo r7, java.lang.String r8, int r9, int r10) {
        /*
            r0 = -1
            if (r9 == r0) goto L_0x00a9
            if (r10 != r0) goto L_0x0007
            goto L_0x00a9
        L_0x0007:
            int r1 = r8.hashCode()
            r2 = 5
            r3 = 1
            r4 = 4
            r5 = 3
            r6 = 2
            switch(r1) {
                case -1664118616: goto L_0x0046;
                case -1662541442: goto L_0x003c;
                case 1187890754: goto L_0x0032;
                case 1331836730: goto L_0x0028;
                case 1599127256: goto L_0x001e;
                case 1599127257: goto L_0x0014;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x0050
        L_0x0014:
            java.lang.String r1 = "video/x-vnd.on2.vp9"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x0050
            r8 = 5
            goto L_0x0051
        L_0x001e:
            java.lang.String r1 = "video/x-vnd.on2.vp8"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x0050
            r8 = 3
            goto L_0x0051
        L_0x0028:
            java.lang.String r1 = "video/avc"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x0050
            r8 = 2
            goto L_0x0051
        L_0x0032:
            java.lang.String r1 = "video/mp4v-es"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x0050
            r8 = 1
            goto L_0x0051
        L_0x003c:
            java.lang.String r1 = "video/hevc"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x0050
            r8 = 4
            goto L_0x0051
        L_0x0046:
            java.lang.String r1 = "video/3gpp"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x0050
            r8 = 0
            goto L_0x0051
        L_0x0050:
            r8 = -1
        L_0x0051:
            if (r8 == 0) goto L_0x00a0
            if (r8 == r3) goto L_0x00a0
            if (r8 == r6) goto L_0x0061
            if (r8 == r5) goto L_0x00a0
            if (r8 == r4) goto L_0x005e
            if (r8 == r2) goto L_0x005e
            return r0
        L_0x005e:
            int r9 = r9 * r10
            goto L_0x00a3
        L_0x0061:
            java.lang.String r8 = androidx.media2.exoplayer.external.util.Util.MODEL
            java.lang.String r1 = "BRAVIA 4K 2015"
            boolean r8 = r1.equals(r8)
            if (r8 != 0) goto L_0x009f
            java.lang.String r8 = androidx.media2.exoplayer.external.util.Util.MANUFACTURER
            java.lang.String r1 = "Amazon"
            boolean r8 = r1.equals(r8)
            if (r8 == 0) goto L_0x008e
            java.lang.String r8 = androidx.media2.exoplayer.external.util.Util.MODEL
            java.lang.String r1 = "KFSOWI"
            boolean r8 = r1.equals(r8)
            if (r8 != 0) goto L_0x009f
            java.lang.String r8 = androidx.media2.exoplayer.external.util.Util.MODEL
            java.lang.String r1 = "AFTS"
            boolean r8 = r1.equals(r8)
            if (r8 == 0) goto L_0x008e
            boolean r7 = r7.secure
            if (r7 == 0) goto L_0x008e
            goto L_0x009f
        L_0x008e:
            r7 = 16
            int r8 = androidx.media2.exoplayer.external.util.Util.ceilDivide(r9, r7)
            int r9 = androidx.media2.exoplayer.external.util.Util.ceilDivide(r10, r7)
            int r8 = r8 * r9
            int r8 = r8 * 16
            int r9 = r8 * 16
            goto L_0x00a2
        L_0x009f:
            return r0
        L_0x00a0:
            int r9 = r9 * r10
        L_0x00a2:
            r4 = 2
        L_0x00a3:
            int r9 = r9 * 3
            int r4 = r4 * 2
            int r9 = r9 / r4
            return r9
        L_0x00a9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.video.MediaCodecVideoRenderer.getCodecMaxInputSize(androidx.media2.exoplayer.external.mediacodec.MediaCodecInfo, java.lang.String, int, int):int");
    }

    private static boolean deviceNeedsNoPostProcessWorkaround() {
        return "NVIDIA".equals(Util.MANUFACTURER);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:393:0x05ff, code lost:
        r2 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:394:0x0600, code lost:
        switch(r2) {
            case 0: goto L_0x0604;
            case 1: goto L_0x0604;
            case 2: goto L_0x0604;
            case 3: goto L_0x0604;
            case 4: goto L_0x0604;
            case 5: goto L_0x0604;
            case 6: goto L_0x0604;
            case 7: goto L_0x0604;
            case 8: goto L_0x0604;
            case 9: goto L_0x0604;
            case 10: goto L_0x0604;
            case 11: goto L_0x0604;
            case 12: goto L_0x0604;
            case 13: goto L_0x0604;
            case 14: goto L_0x0604;
            case 15: goto L_0x0604;
            case 16: goto L_0x0604;
            case 17: goto L_0x0604;
            case 18: goto L_0x0604;
            case 19: goto L_0x0604;
            case 20: goto L_0x0604;
            case 21: goto L_0x0604;
            case 22: goto L_0x0604;
            case 23: goto L_0x0604;
            case 24: goto L_0x0604;
            case 25: goto L_0x0604;
            case 26: goto L_0x0604;
            case 27: goto L_0x0604;
            case 28: goto L_0x0604;
            case 29: goto L_0x0604;
            case 30: goto L_0x0604;
            case 31: goto L_0x0604;
            case 32: goto L_0x0604;
            case 33: goto L_0x0604;
            case 34: goto L_0x0604;
            case 35: goto L_0x0604;
            case 36: goto L_0x0604;
            case 37: goto L_0x0604;
            case 38: goto L_0x0604;
            case 39: goto L_0x0604;
            case 40: goto L_0x0604;
            case 41: goto L_0x0604;
            case 42: goto L_0x0604;
            case 43: goto L_0x0604;
            case 44: goto L_0x0604;
            case 45: goto L_0x0604;
            case 46: goto L_0x0604;
            case 47: goto L_0x0604;
            case 48: goto L_0x0604;
            case 49: goto L_0x0604;
            case 50: goto L_0x0604;
            case 51: goto L_0x0604;
            case 52: goto L_0x0604;
            case 53: goto L_0x0604;
            case 54: goto L_0x0604;
            case 55: goto L_0x0604;
            case 56: goto L_0x0604;
            case 57: goto L_0x0604;
            case 58: goto L_0x0604;
            case 59: goto L_0x0604;
            case 60: goto L_0x0604;
            case 61: goto L_0x0604;
            case 62: goto L_0x0604;
            case 63: goto L_0x0604;
            case 64: goto L_0x0604;
            case 65: goto L_0x0604;
            case 66: goto L_0x0604;
            case 67: goto L_0x0604;
            case 68: goto L_0x0604;
            case 69: goto L_0x0604;
            case 70: goto L_0x0604;
            case 71: goto L_0x0604;
            case 72: goto L_0x0604;
            case 73: goto L_0x0604;
            case 74: goto L_0x0604;
            case 75: goto L_0x0604;
            case 76: goto L_0x0604;
            case 77: goto L_0x0604;
            case 78: goto L_0x0604;
            case 79: goto L_0x0604;
            case 80: goto L_0x0604;
            case 81: goto L_0x0604;
            case 82: goto L_0x0604;
            case 83: goto L_0x0604;
            case 84: goto L_0x0604;
            case 85: goto L_0x0604;
            case 86: goto L_0x0604;
            case 87: goto L_0x0604;
            case 88: goto L_0x0604;
            case 89: goto L_0x0604;
            case 90: goto L_0x0604;
            case 91: goto L_0x0604;
            case 92: goto L_0x0604;
            case 93: goto L_0x0604;
            case 94: goto L_0x0604;
            case 95: goto L_0x0604;
            case 96: goto L_0x0604;
            case 97: goto L_0x0604;
            case 98: goto L_0x0604;
            case 99: goto L_0x0604;
            case 100: goto L_0x0604;
            case 101: goto L_0x0604;
            case 102: goto L_0x0604;
            case 103: goto L_0x0604;
            case 104: goto L_0x0604;
            case 105: goto L_0x0604;
            case 106: goto L_0x0604;
            case 107: goto L_0x0604;
            case 108: goto L_0x0604;
            case 109: goto L_0x0604;
            case 110: goto L_0x0604;
            case 111: goto L_0x0604;
            case 112: goto L_0x0604;
            case 113: goto L_0x0604;
            case 114: goto L_0x0604;
            case 115: goto L_0x0604;
            case 116: goto L_0x0604;
            case 117: goto L_0x0604;
            case 118: goto L_0x0604;
            case 119: goto L_0x0604;
            case 120: goto L_0x0604;
            case 121: goto L_0x0604;
            case 122: goto L_0x0604;
            case 123: goto L_0x0604;
            default: goto L_0x0603;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:396:0x0604, code lost:
        deviceNeedsSetOutputSurfaceWorkaround = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:397:0x0606, code lost:
        r1 = androidx.media2.exoplayer.external.util.Util.MODEL;
        r2 = r1.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:398:0x060f, code lost:
        if (r2 == 2006354) goto L_0x0621;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:400:0x0614, code lost:
        if (r2 == 2006367) goto L_0x0617;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:403:0x061d, code lost:
        if (r1.equals("AFTN") == false) goto L_0x062a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:404:0x061f, code lost:
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:406:0x0627, code lost:
        if (r1.equals("AFTA") == false) goto L_0x062a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:408:0x062a, code lost:
        r0 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:409:0x062b, code lost:
        if (r0 == 0) goto L_0x0630;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:410:0x062d, code lost:
        if (r0 == 1) goto L_0x0630;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:412:0x0630, code lost:
        deviceNeedsSetOutputSurfaceWorkaround = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean codecNeedsSetOutputSurfaceWorkaround(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "OMX.google"
            boolean r7 = r7.startsWith(r0)
            r0 = 0
            if (r7 == 0) goto L_0x000a
            return r0
        L_0x000a:
            java.lang.Class<androidx.media2.exoplayer.external.video.MediaCodecVideoRenderer> r7 = androidx.media2.exoplayer.external.video.MediaCodecVideoRenderer.class
            monitor-enter(r7)
            boolean r1 = evaluatedDeviceNeedsSetOutputSurfaceWorkaround     // Catch:{ all -> 0x0638 }
            if (r1 != 0) goto L_0x0634
            int r1 = androidx.media2.exoplayer.external.util.Util.SDK_INT     // Catch:{ all -> 0x0638 }
            r2 = 27
            r3 = 1
            if (r1 > r2) goto L_0x0030
            java.lang.String r1 = "dangal"
            java.lang.String r4 = androidx.media2.exoplayer.external.util.Util.DEVICE     // Catch:{ all -> 0x0638 }
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0638 }
            if (r1 != 0) goto L_0x002c
            java.lang.String r1 = "HWEML"
            java.lang.String r4 = androidx.media2.exoplayer.external.util.Util.DEVICE     // Catch:{ all -> 0x0638 }
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x0030
        L_0x002c:
            deviceNeedsSetOutputSurfaceWorkaround = r3     // Catch:{ all -> 0x0638 }
            goto L_0x0632
        L_0x0030:
            int r1 = androidx.media2.exoplayer.external.util.Util.SDK_INT     // Catch:{ all -> 0x0638 }
            if (r1 < r2) goto L_0x0036
            goto L_0x0632
        L_0x0036:
            java.lang.String r1 = androidx.media2.exoplayer.external.util.Util.DEVICE     // Catch:{ all -> 0x0638 }
            int r4 = r1.hashCode()     // Catch:{ all -> 0x0638 }
            r5 = -1
            switch(r4) {
                case -2144781245: goto L_0x05f4;
                case -2144781185: goto L_0x05e9;
                case -2144781160: goto L_0x05de;
                case -2097309513: goto L_0x05d3;
                case -2022874474: goto L_0x05c8;
                case -1978993182: goto L_0x05bd;
                case -1978990237: goto L_0x05b2;
                case -1936688988: goto L_0x05a7;
                case -1936688066: goto L_0x059c;
                case -1936688065: goto L_0x0590;
                case -1931988508: goto L_0x0584;
                case -1696512866: goto L_0x0578;
                case -1680025915: goto L_0x056c;
                case -1615810839: goto L_0x0560;
                case -1554255044: goto L_0x0554;
                case -1481772737: goto L_0x0548;
                case -1481772730: goto L_0x053c;
                case -1481772729: goto L_0x0530;
                case -1320080169: goto L_0x0524;
                case -1217592143: goto L_0x0518;
                case -1180384755: goto L_0x050c;
                case -1139198265: goto L_0x0500;
                case -1052835013: goto L_0x04f4;
                case -993250464: goto L_0x04e9;
                case -965403638: goto L_0x04dd;
                case -958336948: goto L_0x04d1;
                case -879245230: goto L_0x04c5;
                case -842500323: goto L_0x04b9;
                case -821392978: goto L_0x04ae;
                case -797483286: goto L_0x04a2;
                case -794946968: goto L_0x0496;
                case -788334647: goto L_0x048a;
                case -782144577: goto L_0x047e;
                case -575125681: goto L_0x0472;
                case -521118391: goto L_0x0466;
                case -430914369: goto L_0x045a;
                case -290434366: goto L_0x044e;
                case -282781963: goto L_0x0442;
                case -277133239: goto L_0x0436;
                case -173639913: goto L_0x042a;
                case -56598463: goto L_0x041e;
                case 2126: goto L_0x0412;
                case 2564: goto L_0x0406;
                case 2715: goto L_0x03fa;
                case 2719: goto L_0x03ee;
                case 3483: goto L_0x03e2;
                case 73405: goto L_0x03d6;
                case 75739: goto L_0x03ca;
                case 76779: goto L_0x03be;
                case 78669: goto L_0x03b2;
                case 79305: goto L_0x03a6;
                case 80618: goto L_0x039a;
                case 88274: goto L_0x038e;
                case 98846: goto L_0x0382;
                case 98848: goto L_0x0376;
                case 99329: goto L_0x036a;
                case 101481: goto L_0x035e;
                case 1513190: goto L_0x0353;
                case 1514184: goto L_0x0348;
                case 1514185: goto L_0x033d;
                case 2436959: goto L_0x0331;
                case 2463773: goto L_0x0325;
                case 2464648: goto L_0x0319;
                case 2689555: goto L_0x030d;
                case 3154429: goto L_0x0301;
                case 3284551: goto L_0x02f5;
                case 3351335: goto L_0x02e9;
                case 3386211: goto L_0x02dd;
                case 41325051: goto L_0x02d1;
                case 55178625: goto L_0x02c5;
                case 61542055: goto L_0x02ba;
                case 65355429: goto L_0x02ae;
                case 66214468: goto L_0x02a2;
                case 66214470: goto L_0x0296;
                case 66214473: goto L_0x028a;
                case 66215429: goto L_0x027e;
                case 66215431: goto L_0x0272;
                case 66215433: goto L_0x0266;
                case 66216390: goto L_0x025a;
                case 76402249: goto L_0x024e;
                case 76404105: goto L_0x0242;
                case 76404911: goto L_0x0236;
                case 80963634: goto L_0x022a;
                case 82882791: goto L_0x021e;
                case 98715550: goto L_0x0212;
                case 102844228: goto L_0x0206;
                case 165221241: goto L_0x01fb;
                case 182191441: goto L_0x01ef;
                case 245388979: goto L_0x01e3;
                case 287431619: goto L_0x01d7;
                case 307593612: goto L_0x01cb;
                case 308517133: goto L_0x01bf;
                case 316215098: goto L_0x01b3;
                case 316215116: goto L_0x01a7;
                case 316246811: goto L_0x019b;
                case 316246818: goto L_0x018f;
                case 407160593: goto L_0x0183;
                case 507412548: goto L_0x0177;
                case 793982701: goto L_0x016b;
                case 794038622: goto L_0x015f;
                case 794040393: goto L_0x0153;
                case 835649806: goto L_0x0147;
                case 917340916: goto L_0x013c;
                case 958008161: goto L_0x0130;
                case 1060579533: goto L_0x0124;
                case 1150207623: goto L_0x0118;
                case 1176899427: goto L_0x010c;
                case 1280332038: goto L_0x0100;
                case 1306947716: goto L_0x00f4;
                case 1349174697: goto L_0x00e8;
                case 1522194893: goto L_0x00dc;
                case 1691543273: goto L_0x00d0;
                case 1709443163: goto L_0x00c4;
                case 1865889110: goto L_0x00b8;
                case 1906253259: goto L_0x00ac;
                case 1977196784: goto L_0x00a0;
                case 2006372676: goto L_0x0094;
                case 2029784656: goto L_0x0088;
                case 2030379515: goto L_0x007c;
                case 2033393791: goto L_0x0070;
                case 2047190025: goto L_0x0064;
                case 2047252157: goto L_0x005a;
                case 2048319463: goto L_0x004e;
                case 2048855701: goto L_0x0042;
                default: goto L_0x0040;
            }     // Catch:{ all -> 0x0638 }
        L_0x0040:
            goto L_0x05ff
        L_0x0042:
            java.lang.String r2 = "HWWAS-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 54
            goto L_0x0600
        L_0x004e:
            java.lang.String r2 = "HWVNS-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 53
            goto L_0x0600
        L_0x005a:
            java.lang.String r4 = "ELUGA_Prim"
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            goto L_0x0600
        L_0x0064:
            java.lang.String r2 = "ELUGA_Note"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 26
            goto L_0x0600
        L_0x0070:
            java.lang.String r2 = "ASUS_X00AD_2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 11
            goto L_0x0600
        L_0x007c:
            java.lang.String r2 = "HWCAM-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 52
            goto L_0x0600
        L_0x0088:
            java.lang.String r2 = "HWBLN-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 51
            goto L_0x0600
        L_0x0094:
            java.lang.String r2 = "BRAVIA_ATV3_4K"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 15
            goto L_0x0600
        L_0x00a0:
            java.lang.String r2 = "Infinix-X572"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 57
            goto L_0x0600
        L_0x00ac:
            java.lang.String r2 = "PB2-670M"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 85
            goto L_0x0600
        L_0x00b8:
            java.lang.String r2 = "santoni"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 101(0x65, float:1.42E-43)
            goto L_0x0600
        L_0x00c4:
            java.lang.String r2 = "iball8735_9806"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 56
            goto L_0x0600
        L_0x00d0:
            java.lang.String r2 = "CPH1609"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 19
            goto L_0x0600
        L_0x00dc:
            java.lang.String r2 = "woods_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 117(0x75, float:1.64E-43)
            goto L_0x0600
        L_0x00e8:
            java.lang.String r2 = "htc_e56ml_dtul"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 49
            goto L_0x0600
        L_0x00f4:
            java.lang.String r2 = "EverStar_S"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 29
            goto L_0x0600
        L_0x0100:
            java.lang.String r2 = "hwALE-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 50
            goto L_0x0600
        L_0x010c:
            java.lang.String r2 = "itel_S41"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 59
            goto L_0x0600
        L_0x0118:
            java.lang.String r2 = "LS-5017"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 65
            goto L_0x0600
        L_0x0124:
            java.lang.String r2 = "panell_d"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 81
            goto L_0x0600
        L_0x0130:
            java.lang.String r2 = "j2xlteins"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 60
            goto L_0x0600
        L_0x013c:
            java.lang.String r2 = "A7000plus"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 7
            goto L_0x0600
        L_0x0147:
            java.lang.String r2 = "manning"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 67
            goto L_0x0600
        L_0x0153:
            java.lang.String r2 = "GIONEE_WBL7519"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 47
            goto L_0x0600
        L_0x015f:
            java.lang.String r2 = "GIONEE_WBL7365"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 46
            goto L_0x0600
        L_0x016b:
            java.lang.String r2 = "GIONEE_WBL5708"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 45
            goto L_0x0600
        L_0x0177:
            java.lang.String r2 = "QM16XE_U"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 99
            goto L_0x0600
        L_0x0183:
            java.lang.String r2 = "Pixi5-10_4G"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 91
            goto L_0x0600
        L_0x018f:
            java.lang.String r2 = "TB3-850M"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 109(0x6d, float:1.53E-43)
            goto L_0x0600
        L_0x019b:
            java.lang.String r2 = "TB3-850F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 108(0x6c, float:1.51E-43)
            goto L_0x0600
        L_0x01a7:
            java.lang.String r2 = "TB3-730X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 107(0x6b, float:1.5E-43)
            goto L_0x0600
        L_0x01b3:
            java.lang.String r2 = "TB3-730F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 106(0x6a, float:1.49E-43)
            goto L_0x0600
        L_0x01bf:
            java.lang.String r2 = "A7020a48"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 9
            goto L_0x0600
        L_0x01cb:
            java.lang.String r2 = "A7010a48"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 8
            goto L_0x0600
        L_0x01d7:
            java.lang.String r2 = "griffin"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 48
            goto L_0x0600
        L_0x01e3:
            java.lang.String r2 = "marino_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 68
            goto L_0x0600
        L_0x01ef:
            java.lang.String r2 = "CPY83_I00"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 20
            goto L_0x0600
        L_0x01fb:
            java.lang.String r2 = "A2016a40"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 5
            goto L_0x0600
        L_0x0206:
            java.lang.String r2 = "le_x6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 64
            goto L_0x0600
        L_0x0212:
            java.lang.String r2 = "i9031"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 55
            goto L_0x0600
        L_0x021e:
            java.lang.String r2 = "X3_HK"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 119(0x77, float:1.67E-43)
            goto L_0x0600
        L_0x022a:
            java.lang.String r2 = "V23GB"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 112(0x70, float:1.57E-43)
            goto L_0x0600
        L_0x0236:
            java.lang.String r2 = "Q4310"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 97
            goto L_0x0600
        L_0x0242:
            java.lang.String r2 = "Q4260"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 95
            goto L_0x0600
        L_0x024e:
            java.lang.String r2 = "PRO7S"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 93
            goto L_0x0600
        L_0x025a:
            java.lang.String r2 = "F3311"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 36
            goto L_0x0600
        L_0x0266:
            java.lang.String r2 = "F3215"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 35
            goto L_0x0600
        L_0x0272:
            java.lang.String r2 = "F3213"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 34
            goto L_0x0600
        L_0x027e:
            java.lang.String r2 = "F3211"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 33
            goto L_0x0600
        L_0x028a:
            java.lang.String r2 = "F3116"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 32
            goto L_0x0600
        L_0x0296:
            java.lang.String r2 = "F3113"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 31
            goto L_0x0600
        L_0x02a2:
            java.lang.String r2 = "F3111"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 30
            goto L_0x0600
        L_0x02ae:
            java.lang.String r2 = "E5643"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 24
            goto L_0x0600
        L_0x02ba:
            java.lang.String r2 = "A1601"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 4
            goto L_0x0600
        L_0x02c5:
            java.lang.String r2 = "Aura_Note_2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 12
            goto L_0x0600
        L_0x02d1:
            java.lang.String r2 = "MEIZU_M5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 69
            goto L_0x0600
        L_0x02dd:
            java.lang.String r2 = "p212"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 78
            goto L_0x0600
        L_0x02e9:
            java.lang.String r2 = "mido"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 71
            goto L_0x0600
        L_0x02f5:
            java.lang.String r2 = "kate"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 63
            goto L_0x0600
        L_0x0301:
            java.lang.String r2 = "fugu"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 38
            goto L_0x0600
        L_0x030d:
            java.lang.String r2 = "XE2X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 120(0x78, float:1.68E-43)
            goto L_0x0600
        L_0x0319:
            java.lang.String r2 = "Q427"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 96
            goto L_0x0600
        L_0x0325:
            java.lang.String r2 = "Q350"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 94
            goto L_0x0600
        L_0x0331:
            java.lang.String r2 = "P681"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 79
            goto L_0x0600
        L_0x033d:
            java.lang.String r2 = "1714"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 2
            goto L_0x0600
        L_0x0348:
            java.lang.String r2 = "1713"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 1
            goto L_0x0600
        L_0x0353:
            java.lang.String r2 = "1601"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 0
            goto L_0x0600
        L_0x035e:
            java.lang.String r2 = "flo"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 37
            goto L_0x0600
        L_0x036a:
            java.lang.String r2 = "deb"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 23
            goto L_0x0600
        L_0x0376:
            java.lang.String r2 = "cv3"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 22
            goto L_0x0600
        L_0x0382:
            java.lang.String r2 = "cv1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 21
            goto L_0x0600
        L_0x038e:
            java.lang.String r2 = "Z80"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 123(0x7b, float:1.72E-43)
            goto L_0x0600
        L_0x039a:
            java.lang.String r2 = "QX1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 100
            goto L_0x0600
        L_0x03a6:
            java.lang.String r2 = "PLE"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 92
            goto L_0x0600
        L_0x03b2:
            java.lang.String r2 = "P85"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 80
            goto L_0x0600
        L_0x03be:
            java.lang.String r2 = "MX6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 72
            goto L_0x0600
        L_0x03ca:
            java.lang.String r2 = "M5c"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 66
            goto L_0x0600
        L_0x03d6:
            java.lang.String r2 = "JGZ"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 61
            goto L_0x0600
        L_0x03e2:
            java.lang.String r2 = "mh"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 70
            goto L_0x0600
        L_0x03ee:
            java.lang.String r2 = "V5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 113(0x71, float:1.58E-43)
            goto L_0x0600
        L_0x03fa:
            java.lang.String r2 = "V1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 111(0x6f, float:1.56E-43)
            goto L_0x0600
        L_0x0406:
            java.lang.String r2 = "Q5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 98
            goto L_0x0600
        L_0x0412:
            java.lang.String r2 = "C1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 16
            goto L_0x0600
        L_0x041e:
            java.lang.String r2 = "woods_fn"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 118(0x76, float:1.65E-43)
            goto L_0x0600
        L_0x042a:
            java.lang.String r2 = "ELUGA_A3_Pro"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 25
            goto L_0x0600
        L_0x0436:
            java.lang.String r2 = "Z12_PRO"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 122(0x7a, float:1.71E-43)
            goto L_0x0600
        L_0x0442:
            java.lang.String r2 = "BLACK-1X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 13
            goto L_0x0600
        L_0x044e:
            java.lang.String r2 = "taido_row"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 105(0x69, float:1.47E-43)
            goto L_0x0600
        L_0x045a:
            java.lang.String r2 = "Pixi4-7_3G"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 90
            goto L_0x0600
        L_0x0466:
            java.lang.String r2 = "GIONEE_GBL7360"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 41
            goto L_0x0600
        L_0x0472:
            java.lang.String r2 = "GiONEE_CBL7513"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 39
            goto L_0x0600
        L_0x047e:
            java.lang.String r2 = "OnePlus5T"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 77
            goto L_0x0600
        L_0x048a:
            java.lang.String r2 = "whyred"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 116(0x74, float:1.63E-43)
            goto L_0x0600
        L_0x0496:
            java.lang.String r2 = "watson"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 115(0x73, float:1.61E-43)
            goto L_0x0600
        L_0x04a2:
            java.lang.String r2 = "SVP-DTV15"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 103(0x67, float:1.44E-43)
            goto L_0x0600
        L_0x04ae:
            java.lang.String r2 = "A7000-a"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 6
            goto L_0x0600
        L_0x04b9:
            java.lang.String r2 = "nicklaus_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 74
            goto L_0x0600
        L_0x04c5:
            java.lang.String r2 = "tcl_eu"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 110(0x6e, float:1.54E-43)
            goto L_0x0600
        L_0x04d1:
            java.lang.String r2 = "ELUGA_Ray_X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 28
            goto L_0x0600
        L_0x04dd:
            java.lang.String r2 = "s905x018"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 104(0x68, float:1.46E-43)
            goto L_0x0600
        L_0x04e9:
            java.lang.String r2 = "A10-70F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 3
            goto L_0x0600
        L_0x04f4:
            java.lang.String r2 = "namath"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 73
            goto L_0x0600
        L_0x0500:
            java.lang.String r2 = "Slate_Pro"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 102(0x66, float:1.43E-43)
            goto L_0x0600
        L_0x050c:
            java.lang.String r2 = "iris60"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 58
            goto L_0x0600
        L_0x0518:
            java.lang.String r2 = "BRAVIA_ATV2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 14
            goto L_0x0600
        L_0x0524:
            java.lang.String r2 = "GiONEE_GBL7319"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 40
            goto L_0x0600
        L_0x0530:
            java.lang.String r2 = "panell_dt"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 84
            goto L_0x0600
        L_0x053c:
            java.lang.String r2 = "panell_ds"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 83
            goto L_0x0600
        L_0x0548:
            java.lang.String r2 = "panell_dl"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 82
            goto L_0x0600
        L_0x0554:
            java.lang.String r2 = "vernee_M5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 114(0x72, float:1.6E-43)
            goto L_0x0600
        L_0x0560:
            java.lang.String r2 = "Phantom6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 89
            goto L_0x0600
        L_0x056c:
            java.lang.String r2 = "ComioS1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 17
            goto L_0x0600
        L_0x0578:
            java.lang.String r2 = "XT1663"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 121(0x79, float:1.7E-43)
            goto L_0x0600
        L_0x0584:
            java.lang.String r2 = "AquaPowerM"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 10
            goto L_0x0600
        L_0x0590:
            java.lang.String r2 = "PGN611"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 88
            goto L_0x0600
        L_0x059c:
            java.lang.String r2 = "PGN610"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 87
            goto L_0x0600
        L_0x05a7:
            java.lang.String r2 = "PGN528"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 86
            goto L_0x0600
        L_0x05b2:
            java.lang.String r2 = "NX573J"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 76
            goto L_0x0600
        L_0x05bd:
            java.lang.String r2 = "NX541J"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 75
            goto L_0x0600
        L_0x05c8:
            java.lang.String r2 = "CP8676_I02"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 18
            goto L_0x0600
        L_0x05d3:
            java.lang.String r2 = "K50a40"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 62
            goto L_0x0600
        L_0x05de:
            java.lang.String r2 = "GIONEE_SWW1631"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 44
            goto L_0x0600
        L_0x05e9:
            java.lang.String r2 = "GIONEE_SWW1627"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 43
            goto L_0x0600
        L_0x05f4:
            java.lang.String r2 = "GIONEE_SWW1609"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x05ff
            r2 = 42
            goto L_0x0600
        L_0x05ff:
            r2 = -1
        L_0x0600:
            switch(r2) {
                case 0: goto L_0x0604;
                case 1: goto L_0x0604;
                case 2: goto L_0x0604;
                case 3: goto L_0x0604;
                case 4: goto L_0x0604;
                case 5: goto L_0x0604;
                case 6: goto L_0x0604;
                case 7: goto L_0x0604;
                case 8: goto L_0x0604;
                case 9: goto L_0x0604;
                case 10: goto L_0x0604;
                case 11: goto L_0x0604;
                case 12: goto L_0x0604;
                case 13: goto L_0x0604;
                case 14: goto L_0x0604;
                case 15: goto L_0x0604;
                case 16: goto L_0x0604;
                case 17: goto L_0x0604;
                case 18: goto L_0x0604;
                case 19: goto L_0x0604;
                case 20: goto L_0x0604;
                case 21: goto L_0x0604;
                case 22: goto L_0x0604;
                case 23: goto L_0x0604;
                case 24: goto L_0x0604;
                case 25: goto L_0x0604;
                case 26: goto L_0x0604;
                case 27: goto L_0x0604;
                case 28: goto L_0x0604;
                case 29: goto L_0x0604;
                case 30: goto L_0x0604;
                case 31: goto L_0x0604;
                case 32: goto L_0x0604;
                case 33: goto L_0x0604;
                case 34: goto L_0x0604;
                case 35: goto L_0x0604;
                case 36: goto L_0x0604;
                case 37: goto L_0x0604;
                case 38: goto L_0x0604;
                case 39: goto L_0x0604;
                case 40: goto L_0x0604;
                case 41: goto L_0x0604;
                case 42: goto L_0x0604;
                case 43: goto L_0x0604;
                case 44: goto L_0x0604;
                case 45: goto L_0x0604;
                case 46: goto L_0x0604;
                case 47: goto L_0x0604;
                case 48: goto L_0x0604;
                case 49: goto L_0x0604;
                case 50: goto L_0x0604;
                case 51: goto L_0x0604;
                case 52: goto L_0x0604;
                case 53: goto L_0x0604;
                case 54: goto L_0x0604;
                case 55: goto L_0x0604;
                case 56: goto L_0x0604;
                case 57: goto L_0x0604;
                case 58: goto L_0x0604;
                case 59: goto L_0x0604;
                case 60: goto L_0x0604;
                case 61: goto L_0x0604;
                case 62: goto L_0x0604;
                case 63: goto L_0x0604;
                case 64: goto L_0x0604;
                case 65: goto L_0x0604;
                case 66: goto L_0x0604;
                case 67: goto L_0x0604;
                case 68: goto L_0x0604;
                case 69: goto L_0x0604;
                case 70: goto L_0x0604;
                case 71: goto L_0x0604;
                case 72: goto L_0x0604;
                case 73: goto L_0x0604;
                case 74: goto L_0x0604;
                case 75: goto L_0x0604;
                case 76: goto L_0x0604;
                case 77: goto L_0x0604;
                case 78: goto L_0x0604;
                case 79: goto L_0x0604;
                case 80: goto L_0x0604;
                case 81: goto L_0x0604;
                case 82: goto L_0x0604;
                case 83: goto L_0x0604;
                case 84: goto L_0x0604;
                case 85: goto L_0x0604;
                case 86: goto L_0x0604;
                case 87: goto L_0x0604;
                case 88: goto L_0x0604;
                case 89: goto L_0x0604;
                case 90: goto L_0x0604;
                case 91: goto L_0x0604;
                case 92: goto L_0x0604;
                case 93: goto L_0x0604;
                case 94: goto L_0x0604;
                case 95: goto L_0x0604;
                case 96: goto L_0x0604;
                case 97: goto L_0x0604;
                case 98: goto L_0x0604;
                case 99: goto L_0x0604;
                case 100: goto L_0x0604;
                case 101: goto L_0x0604;
                case 102: goto L_0x0604;
                case 103: goto L_0x0604;
                case 104: goto L_0x0604;
                case 105: goto L_0x0604;
                case 106: goto L_0x0604;
                case 107: goto L_0x0604;
                case 108: goto L_0x0604;
                case 109: goto L_0x0604;
                case 110: goto L_0x0604;
                case 111: goto L_0x0604;
                case 112: goto L_0x0604;
                case 113: goto L_0x0604;
                case 114: goto L_0x0604;
                case 115: goto L_0x0604;
                case 116: goto L_0x0604;
                case 117: goto L_0x0604;
                case 118: goto L_0x0604;
                case 119: goto L_0x0604;
                case 120: goto L_0x0604;
                case 121: goto L_0x0604;
                case 122: goto L_0x0604;
                case 123: goto L_0x0604;
                default: goto L_0x0603;
            }     // Catch:{ all -> 0x0638 }
        L_0x0603:
            goto L_0x0606
        L_0x0604:
            deviceNeedsSetOutputSurfaceWorkaround = r3     // Catch:{ all -> 0x0638 }
        L_0x0606:
            java.lang.String r1 = androidx.media2.exoplayer.external.util.Util.MODEL     // Catch:{ all -> 0x0638 }
            int r2 = r1.hashCode()     // Catch:{ all -> 0x0638 }
            r4 = 2006354(0x1e9d52, float:2.811501E-39)
            if (r2 == r4) goto L_0x0621
            r0 = 2006367(0x1e9d5f, float:2.811519E-39)
            if (r2 == r0) goto L_0x0617
            goto L_0x062a
        L_0x0617:
            java.lang.String r0 = "AFTN"
            boolean r0 = r1.equals(r0)     // Catch:{ all -> 0x0638 }
            if (r0 == 0) goto L_0x062a
            r0 = 1
            goto L_0x062b
        L_0x0621:
            java.lang.String r2 = "AFTA"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0638 }
            if (r1 == 0) goto L_0x062a
            goto L_0x062b
        L_0x062a:
            r0 = -1
        L_0x062b:
            if (r0 == 0) goto L_0x0630
            if (r0 == r3) goto L_0x0630
            goto L_0x0632
        L_0x0630:
            deviceNeedsSetOutputSurfaceWorkaround = r3     // Catch:{ all -> 0x0638 }
        L_0x0632:
            evaluatedDeviceNeedsSetOutputSurfaceWorkaround = r3     // Catch:{ all -> 0x0638 }
        L_0x0634:
            monitor-exit(r7)     // Catch:{ all -> 0x0638 }
            boolean r7 = deviceNeedsSetOutputSurfaceWorkaround
            return r7
        L_0x0638:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0638 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.video.MediaCodecVideoRenderer.codecNeedsSetOutputSurfaceWorkaround(java.lang.String):boolean");
    }

    protected static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int i, int i2, int i3) {
            this.width = i;
            this.height = i2;
            this.inputSize = i3;
        }
    }

    private final class OnFrameRenderedListenerV23 implements MediaCodec.OnFrameRenderedListener {
        private OnFrameRenderedListenerV23(MediaCodec mediaCodec) {
            mediaCodec.setOnFrameRenderedListener(this, new Handler());
        }

        public void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
            if (this == MediaCodecVideoRenderer.this.tunnelingOnFrameRenderedListener) {
                MediaCodecVideoRenderer.this.onProcessedTunneledBuffer(j);
            }
        }
    }
}
