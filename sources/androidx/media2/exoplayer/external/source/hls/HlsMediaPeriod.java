package androidx.media2.exoplayer.external.source.hls;

import android.net.Uri;
import android.text.TextUtils;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.SeekParameters;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.source.CompositeSequenceableLoaderFactory;
import androidx.media2.exoplayer.external.source.MediaPeriod;
import androidx.media2.exoplayer.external.source.MediaSourceEventListener;
import androidx.media2.exoplayer.external.source.SampleStream;
import androidx.media2.exoplayer.external.source.SequenceableLoader;
import androidx.media2.exoplayer.external.source.TrackGroup;
import androidx.media2.exoplayer.external.source.TrackGroupArray;
import androidx.media2.exoplayer.external.source.hls.HlsSampleStreamWrapper;
import androidx.media2.exoplayer.external.source.hls.playlist.HlsMasterPlaylist;
import androidx.media2.exoplayer.external.source.hls.playlist.HlsPlaylistTracker;
import androidx.media2.exoplayer.external.trackselection.TrackSelection;
import androidx.media2.exoplayer.external.upstream.Allocator;
import androidx.media2.exoplayer.external.upstream.LoadErrorHandlingPolicy;
import androidx.media2.exoplayer.external.upstream.TransferListener;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.MimeTypes;
import androidx.media2.exoplayer.external.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public final class HlsMediaPeriod implements MediaPeriod, HlsSampleStreamWrapper.Callback, HlsPlaylistTracker.PlaylistEventListener {
    private final Allocator allocator;
    private final boolean allowChunklessPreparation;
    private MediaPeriod.Callback callback;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private HlsSampleStreamWrapper[] enabledSampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final HlsExtractorFactory extractorFactory;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private int[][] manifestUrlIndicesPerWrapper = new int[0][];
    private final TransferListener mediaTransferListener;
    private boolean notifiedReadingStarted;
    private int pendingPrepareCount;
    private final HlsPlaylistTracker playlistTracker;
    private HlsSampleStreamWrapper[] sampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private final IdentityHashMap<SampleStream, Integer> streamWrapperIndices = new IdentityHashMap<>();
    private final TimestampAdjusterProvider timestampAdjusterProvider = new TimestampAdjusterProvider();
    private TrackGroupArray trackGroups;
    private final boolean useSessionKeys;

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return j;
    }

    public HlsMediaPeriod(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsDataSourceFactory hlsDataSourceFactory, TransferListener transferListener, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, MediaSourceEventListener.EventDispatcher eventDispatcher2, Allocator allocator2, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, boolean z, boolean z2) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.mediaTransferListener = transferListener;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.eventDispatcher = eventDispatcher2;
        this.allocator = allocator2;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.allowChunklessPreparation = z;
        this.useSessionKeys = z2;
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory2.createCompositeSequenceableLoader(new SequenceableLoader[0]);
        eventDispatcher2.mediaPeriodCreated();
    }

    public void release() {
        this.playlistTracker.removeListener(this);
        for (HlsSampleStreamWrapper release : this.sampleStreamWrappers) {
            release.release();
        }
        this.callback = null;
        this.eventDispatcher.mediaPeriodReleased();
    }

    public void prepare(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        this.playlistTracker.addListener(this);
        buildAndPrepareSampleStreamWrappers(j);
    }

    public void maybeThrowPrepareError() throws IOException {
        for (HlsSampleStreamWrapper maybeThrowPrepareError : this.sampleStreamWrappers) {
            maybeThrowPrepareError.maybeThrowPrepareError();
        }
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        int i;
        TrackSelection[] trackSelectionArr2 = trackSelectionArr;
        SampleStream[] sampleStreamArr2 = sampleStreamArr;
        int[] iArr = new int[trackSelectionArr2.length];
        int[] iArr2 = new int[trackSelectionArr2.length];
        for (int i2 = 0; i2 < trackSelectionArr2.length; i2++) {
            if (sampleStreamArr2[i2] == null) {
                i = -1;
            } else {
                i = this.streamWrapperIndices.get(sampleStreamArr2[i2]).intValue();
            }
            iArr[i2] = i;
            iArr2[i2] = -1;
            if (trackSelectionArr2[i2] != null) {
                TrackGroup trackGroup = trackSelectionArr2[i2].getTrackGroup();
                int i3 = 0;
                while (true) {
                    HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.sampleStreamWrappers;
                    if (i3 >= hlsSampleStreamWrapperArr.length) {
                        break;
                    } else if (hlsSampleStreamWrapperArr[i3].getTrackGroups().indexOf(trackGroup) != -1) {
                        iArr2[i2] = i3;
                        break;
                    } else {
                        i3++;
                    }
                }
            }
        }
        this.streamWrapperIndices.clear();
        int length = trackSelectionArr2.length;
        SampleStream[] sampleStreamArr3 = new SampleStream[length];
        SampleStream[] sampleStreamArr4 = new SampleStream[trackSelectionArr2.length];
        TrackSelection[] trackSelectionArr3 = new TrackSelection[trackSelectionArr2.length];
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = new HlsSampleStreamWrapper[this.sampleStreamWrappers.length];
        int i4 = 0;
        int i5 = 0;
        boolean z = false;
        while (i5 < this.sampleStreamWrappers.length) {
            for (int i6 = 0; i6 < trackSelectionArr2.length; i6++) {
                TrackSelection trackSelection = null;
                sampleStreamArr4[i6] = iArr[i6] == i5 ? sampleStreamArr2[i6] : null;
                if (iArr2[i6] == i5) {
                    trackSelection = trackSelectionArr2[i6];
                }
                trackSelectionArr3[i6] = trackSelection;
            }
            HlsSampleStreamWrapper hlsSampleStreamWrapper = this.sampleStreamWrappers[i5];
            HlsSampleStreamWrapper hlsSampleStreamWrapper2 = hlsSampleStreamWrapper;
            int i7 = length;
            int i8 = i5;
            int i9 = i4;
            TrackSelection[] trackSelectionArr4 = trackSelectionArr3;
            HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr3 = hlsSampleStreamWrapperArr2;
            boolean selectTracks = hlsSampleStreamWrapper.selectTracks(trackSelectionArr3, zArr, sampleStreamArr4, zArr2, j, z);
            int i10 = 0;
            boolean z2 = false;
            while (true) {
                boolean z3 = true;
                if (i10 >= trackSelectionArr2.length) {
                    break;
                }
                if (iArr2[i10] == i8) {
                    Assertions.checkState(sampleStreamArr4[i10] != null);
                    sampleStreamArr3[i10] = sampleStreamArr4[i10];
                    this.streamWrapperIndices.put(sampleStreamArr4[i10], Integer.valueOf(i8));
                    z2 = true;
                } else if (iArr[i10] == i8) {
                    if (sampleStreamArr4[i10] != null) {
                        z3 = false;
                    }
                    Assertions.checkState(z3);
                }
                i10++;
            }
            if (z2) {
                hlsSampleStreamWrapperArr3[i9] = hlsSampleStreamWrapper2;
                i4 = i9 + 1;
                if (i9 == 0) {
                    hlsSampleStreamWrapper2.setIsTimestampMaster(true);
                    if (!selectTracks) {
                        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr4 = this.enabledSampleStreamWrappers;
                        if (hlsSampleStreamWrapperArr4.length != 0) {
                            if (hlsSampleStreamWrapper2 == hlsSampleStreamWrapperArr4[0]) {
                            }
                            this.timestampAdjusterProvider.reset();
                            z = true;
                        }
                    }
                    this.timestampAdjusterProvider.reset();
                    z = true;
                } else {
                    hlsSampleStreamWrapper2.setIsTimestampMaster(false);
                }
            } else {
                i4 = i9;
            }
            i5 = i8 + 1;
            hlsSampleStreamWrapperArr2 = hlsSampleStreamWrapperArr3;
            length = i7;
            trackSelectionArr3 = trackSelectionArr4;
            sampleStreamArr2 = sampleStreamArr;
        }
        System.arraycopy(sampleStreamArr3, 0, sampleStreamArr2, 0, length);
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr5 = (HlsSampleStreamWrapper[]) Arrays.copyOf(hlsSampleStreamWrapperArr2, i4);
        this.enabledSampleStreamWrappers = hlsSampleStreamWrapperArr5;
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(hlsSampleStreamWrapperArr5);
        return j;
    }

    public void discardBuffer(long j, boolean z) {
        for (HlsSampleStreamWrapper discardBuffer : this.enabledSampleStreamWrappers) {
            discardBuffer.discardBuffer(j, z);
        }
    }

    public void reevaluateBuffer(long j) {
        this.compositeSequenceableLoader.reevaluateBuffer(j);
    }

    public boolean continueLoading(long j) {
        if (this.trackGroups != null) {
            return this.compositeSequenceableLoader.continueLoading(j);
        }
        for (HlsSampleStreamWrapper continuePreparing : this.sampleStreamWrappers) {
            continuePreparing.continuePreparing();
        }
        return false;
    }

    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    public long readDiscontinuity() {
        if (this.notifiedReadingStarted) {
            return -9223372036854775807L;
        }
        this.eventDispatcher.readingStarted();
        this.notifiedReadingStarted = true;
        return -9223372036854775807L;
    }

    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    public long seekToUs(long j) {
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.enabledSampleStreamWrappers;
        if (hlsSampleStreamWrapperArr.length > 0) {
            boolean seekToUs = hlsSampleStreamWrapperArr[0].seekToUs(j, false);
            int i = 1;
            while (true) {
                HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = this.enabledSampleStreamWrappers;
                if (i >= hlsSampleStreamWrapperArr2.length) {
                    break;
                }
                hlsSampleStreamWrapperArr2[i].seekToUs(j, seekToUs);
                i++;
            }
            if (seekToUs) {
                this.timestampAdjusterProvider.reset();
            }
        }
        return j;
    }

    public void onPrepared() {
        int i = this.pendingPrepareCount - 1;
        this.pendingPrepareCount = i;
        if (i <= 0) {
            int i2 = 0;
            for (HlsSampleStreamWrapper trackGroups2 : this.sampleStreamWrappers) {
                i2 += trackGroups2.getTrackGroups().length;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[i2];
            int i3 = 0;
            for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
                int i4 = hlsSampleStreamWrapper.getTrackGroups().length;
                int i5 = 0;
                while (i5 < i4) {
                    trackGroupArr[i3] = hlsSampleStreamWrapper.getTrackGroups().get(i5);
                    i5++;
                    i3++;
                }
            }
            this.trackGroups = new TrackGroupArray(trackGroupArr);
            this.callback.onPrepared(this);
        }
    }

    public void onPlaylistRefreshRequired(Uri uri) {
        this.playlistTracker.refreshPlaylist(uri);
    }

    public void onContinueLoadingRequested(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        this.callback.onContinueLoadingRequested(this);
    }

    public void onPlaylistChanged() {
        this.callback.onContinueLoadingRequested(this);
    }

    public boolean onPlaylistError(Uri uri, long j) {
        boolean z = true;
        for (HlsSampleStreamWrapper onPlaylistError : this.sampleStreamWrappers) {
            z &= onPlaylistError.onPlaylistError(uri, j);
        }
        this.callback.onContinueLoadingRequested(this);
        return z;
    }

    private void buildAndPrepareSampleStreamWrappers(long j) {
        Map<String, DrmInitData> map;
        HlsMasterPlaylist hlsMasterPlaylist = (HlsMasterPlaylist) Assertions.checkNotNull(this.playlistTracker.getMasterPlaylist());
        if (this.useSessionKeys) {
            map = deriveOverridingDrmInitData(hlsMasterPlaylist.sessionKeyDrmInitData);
        } else {
            map = Collections.emptyMap();
        }
        Map<String, DrmInitData> map2 = map;
        boolean z = !hlsMasterPlaylist.variants.isEmpty();
        List<HlsMasterPlaylist.Rendition> list = hlsMasterPlaylist.audios;
        List<HlsMasterPlaylist.Rendition> list2 = hlsMasterPlaylist.subtitles;
        this.pendingPrepareCount = 0;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (z) {
            buildAndPrepareMainSampleStreamWrapper(hlsMasterPlaylist, j, arrayList, arrayList2, map2);
        }
        buildAndPrepareAudioSampleStreamWrappers(j, list, arrayList, arrayList2, map2);
        int i = 0;
        while (i < list2.size()) {
            HlsMasterPlaylist.Rendition rendition = list2.get(i);
            int i2 = i;
            HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper(3, new Uri[]{rendition.url}, new Format[]{rendition.format}, (Format) null, Collections.emptyList(), map2, j);
            arrayList2.add(new int[]{i2});
            arrayList.add(buildSampleStreamWrapper);
            buildSampleStreamWrapper.prepareWithMasterPlaylistInfo(new TrackGroupArray(new TrackGroup(rendition.format)), 0, TrackGroupArray.EMPTY);
            i = i2 + 1;
        }
        this.sampleStreamWrappers = (HlsSampleStreamWrapper[]) arrayList.toArray(new HlsSampleStreamWrapper[0]);
        this.manifestUrlIndicesPerWrapper = (int[][]) arrayList2.toArray(new int[0][]);
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.sampleStreamWrappers;
        this.pendingPrepareCount = hlsSampleStreamWrapperArr.length;
        hlsSampleStreamWrapperArr[0].setIsTimestampMaster(true);
        for (HlsSampleStreamWrapper continuePreparing : this.sampleStreamWrappers) {
            continuePreparing.continuePreparing();
        }
        this.enabledSampleStreamWrappers = this.sampleStreamWrappers;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0064 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void buildAndPrepareMainSampleStreamWrapper(androidx.media2.exoplayer.external.source.hls.playlist.HlsMasterPlaylist r21, long r22, java.util.List<androidx.media2.exoplayer.external.source.hls.HlsSampleStreamWrapper> r24, java.util.List<int[]> r25, java.util.Map<java.lang.String, androidx.media2.exoplayer.external.drm.DrmInitData> r26) {
        /*
            r20 = this;
            r0 = r21
            java.util.List<androidx.media2.exoplayer.external.source.hls.playlist.HlsMasterPlaylist$Variant> r1 = r0.variants
            int r1 = r1.size()
            int[] r2 = new int[r1]
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
        L_0x000e:
            java.util.List<androidx.media2.exoplayer.external.source.hls.playlist.HlsMasterPlaylist$Variant> r7 = r0.variants
            int r7 = r7.size()
            r8 = -1
            r9 = 2
            r10 = 1
            if (r4 >= r7) goto L_0x0047
            java.util.List<androidx.media2.exoplayer.external.source.hls.playlist.HlsMasterPlaylist$Variant> r7 = r0.variants
            java.lang.Object r7 = r7.get(r4)
            androidx.media2.exoplayer.external.source.hls.playlist.HlsMasterPlaylist$Variant r7 = (androidx.media2.exoplayer.external.source.hls.playlist.HlsMasterPlaylist.Variant) r7
            androidx.media2.exoplayer.external.Format r7 = r7.format
            int r11 = r7.height
            if (r11 > 0) goto L_0x0040
            java.lang.String r11 = r7.codecs
            java.lang.String r11 = androidx.media2.exoplayer.external.util.Util.getCodecsOfType(r11, r9)
            if (r11 == 0) goto L_0x0030
            goto L_0x0040
        L_0x0030:
            java.lang.String r7 = r7.codecs
            java.lang.String r7 = androidx.media2.exoplayer.external.util.Util.getCodecsOfType(r7, r10)
            if (r7 == 0) goto L_0x003d
            r2[r4] = r10
            int r6 = r6 + 1
            goto L_0x0044
        L_0x003d:
            r2[r4] = r8
            goto L_0x0044
        L_0x0040:
            r2[r4] = r9
            int r5 = r5 + 1
        L_0x0044:
            int r4 = r4 + 1
            goto L_0x000e
        L_0x0047:
            if (r5 <= 0) goto L_0x004c
            r1 = r5
            r4 = 1
            goto L_0x0053
        L_0x004c:
            if (r6 >= r1) goto L_0x0052
            int r1 = r1 - r6
            r4 = 0
            r5 = 1
            goto L_0x0054
        L_0x0052:
            r4 = 0
        L_0x0053:
            r5 = 0
        L_0x0054:
            android.net.Uri[] r13 = new android.net.Uri[r1]
            androidx.media2.exoplayer.external.Format[] r6 = new androidx.media2.exoplayer.external.Format[r1]
            int[] r7 = new int[r1]
            r11 = 0
            r12 = 0
        L_0x005c:
            java.util.List<androidx.media2.exoplayer.external.source.hls.playlist.HlsMasterPlaylist$Variant> r14 = r0.variants
            int r14 = r14.size()
            if (r11 >= r14) goto L_0x0088
            if (r4 == 0) goto L_0x006a
            r14 = r2[r11]
            if (r14 != r9) goto L_0x0085
        L_0x006a:
            if (r5 == 0) goto L_0x0070
            r14 = r2[r11]
            if (r14 == r10) goto L_0x0085
        L_0x0070:
            java.util.List<androidx.media2.exoplayer.external.source.hls.playlist.HlsMasterPlaylist$Variant> r14 = r0.variants
            java.lang.Object r14 = r14.get(r11)
            androidx.media2.exoplayer.external.source.hls.playlist.HlsMasterPlaylist$Variant r14 = (androidx.media2.exoplayer.external.source.hls.playlist.HlsMasterPlaylist.Variant) r14
            android.net.Uri r15 = r14.url
            r13[r12] = r15
            androidx.media2.exoplayer.external.Format r14 = r14.format
            r6[r12] = r14
            int r14 = r12 + 1
            r7[r12] = r11
            r12 = r14
        L_0x0085:
            int r11 = r11 + 1
            goto L_0x005c
        L_0x0088:
            r2 = r6[r3]
            java.lang.String r2 = r2.codecs
            r12 = 0
            androidx.media2.exoplayer.external.Format r15 = r0.muxedAudioFormat
            java.util.List<androidx.media2.exoplayer.external.Format> r4 = r0.muxedCaptionFormats
            r11 = r20
            r14 = r6
            r16 = r4
            r17 = r26
            r18 = r22
            androidx.media2.exoplayer.external.source.hls.HlsSampleStreamWrapper r4 = r11.buildSampleStreamWrapper(r12, r13, r14, r15, r16, r17, r18)
            r5 = r24
            r5.add(r4)
            r5 = r25
            r5.add(r7)
            r5 = r20
            boolean r7 = r5.allowChunklessPreparation
            if (r7 == 0) goto L_0x018b
            if (r2 == 0) goto L_0x018b
            java.lang.String r7 = androidx.media2.exoplayer.external.util.Util.getCodecsOfType(r2, r9)
            if (r7 == 0) goto L_0x00b8
            r7 = 1
            goto L_0x00b9
        L_0x00b8:
            r7 = 0
        L_0x00b9:
            java.lang.String r9 = androidx.media2.exoplayer.external.util.Util.getCodecsOfType(r2, r10)
            if (r9 == 0) goto L_0x00c1
            r9 = 1
            goto L_0x00c2
        L_0x00c1:
            r9 = 0
        L_0x00c2:
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            if (r7 == 0) goto L_0x0123
            androidx.media2.exoplayer.external.Format[] r2 = new androidx.media2.exoplayer.external.Format[r1]
            r7 = 0
        L_0x00cc:
            if (r7 >= r1) goto L_0x00d9
            r12 = r6[r7]
            androidx.media2.exoplayer.external.Format r12 = deriveVideoFormat(r12)
            r2[r7] = r12
            int r7 = r7 + 1
            goto L_0x00cc
        L_0x00d9:
            androidx.media2.exoplayer.external.source.TrackGroup r1 = new androidx.media2.exoplayer.external.source.TrackGroup
            r1.<init>((androidx.media2.exoplayer.external.Format[]) r2)
            r11.add(r1)
            if (r9 == 0) goto L_0x0103
            androidx.media2.exoplayer.external.Format r1 = r0.muxedAudioFormat
            if (r1 != 0) goto L_0x00ef
            java.util.List<androidx.media2.exoplayer.external.source.hls.playlist.HlsMasterPlaylist$Rendition> r1 = r0.audios
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0103
        L_0x00ef:
            androidx.media2.exoplayer.external.source.TrackGroup r1 = new androidx.media2.exoplayer.external.source.TrackGroup
            androidx.media2.exoplayer.external.Format[] r2 = new androidx.media2.exoplayer.external.Format[r10]
            r6 = r6[r3]
            androidx.media2.exoplayer.external.Format r7 = r0.muxedAudioFormat
            androidx.media2.exoplayer.external.Format r6 = deriveAudioFormat(r6, r7, r3)
            r2[r3] = r6
            r1.<init>((androidx.media2.exoplayer.external.Format[]) r2)
            r11.add(r1)
        L_0x0103:
            java.util.List<androidx.media2.exoplayer.external.Format> r0 = r0.muxedCaptionFormats
            if (r0 == 0) goto L_0x013f
            r1 = 0
        L_0x0108:
            int r2 = r0.size()
            if (r1 >= r2) goto L_0x013f
            androidx.media2.exoplayer.external.source.TrackGroup r2 = new androidx.media2.exoplayer.external.source.TrackGroup
            androidx.media2.exoplayer.external.Format[] r6 = new androidx.media2.exoplayer.external.Format[r10]
            java.lang.Object r7 = r0.get(r1)
            androidx.media2.exoplayer.external.Format r7 = (androidx.media2.exoplayer.external.Format) r7
            r6[r3] = r7
            r2.<init>((androidx.media2.exoplayer.external.Format[]) r6)
            r11.add(r2)
            int r1 = r1 + 1
            goto L_0x0108
        L_0x0123:
            if (r9 == 0) goto L_0x016e
            androidx.media2.exoplayer.external.Format[] r2 = new androidx.media2.exoplayer.external.Format[r1]
            r7 = 0
        L_0x0128:
            if (r7 >= r1) goto L_0x0137
            r9 = r6[r7]
            androidx.media2.exoplayer.external.Format r12 = r0.muxedAudioFormat
            androidx.media2.exoplayer.external.Format r9 = deriveAudioFormat(r9, r12, r10)
            r2[r7] = r9
            int r7 = r7 + 1
            goto L_0x0128
        L_0x0137:
            androidx.media2.exoplayer.external.source.TrackGroup r0 = new androidx.media2.exoplayer.external.source.TrackGroup
            r0.<init>((androidx.media2.exoplayer.external.Format[]) r2)
            r11.add(r0)
        L_0x013f:
            androidx.media2.exoplayer.external.source.TrackGroup r0 = new androidx.media2.exoplayer.external.source.TrackGroup
            androidx.media2.exoplayer.external.Format[] r1 = new androidx.media2.exoplayer.external.Format[r10]
            java.lang.String r2 = "ID3"
            java.lang.String r6 = "application/id3"
            r7 = 0
            androidx.media2.exoplayer.external.Format r2 = androidx.media2.exoplayer.external.Format.createSampleFormat(r2, r6, r7, r8, r7)
            r1[r3] = r2
            r0.<init>((androidx.media2.exoplayer.external.Format[]) r1)
            r11.add(r0)
            androidx.media2.exoplayer.external.source.TrackGroupArray r1 = new androidx.media2.exoplayer.external.source.TrackGroupArray
            androidx.media2.exoplayer.external.source.TrackGroup[] r2 = new androidx.media2.exoplayer.external.source.TrackGroup[r3]
            java.lang.Object[] r2 = r11.toArray(r2)
            androidx.media2.exoplayer.external.source.TrackGroup[] r2 = (androidx.media2.exoplayer.external.source.TrackGroup[]) r2
            r1.<init>((androidx.media2.exoplayer.external.source.TrackGroup[]) r2)
            androidx.media2.exoplayer.external.source.TrackGroupArray r2 = new androidx.media2.exoplayer.external.source.TrackGroupArray
            androidx.media2.exoplayer.external.source.TrackGroup[] r6 = new androidx.media2.exoplayer.external.source.TrackGroup[r10]
            r6[r3] = r0
            r2.<init>((androidx.media2.exoplayer.external.source.TrackGroup[]) r6)
            r4.prepareWithMasterPlaylistInfo(r1, r3, r2)
            goto L_0x018b
        L_0x016e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Unexpected codecs attribute: "
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r3 = r2.length()
            if (r3 == 0) goto L_0x0181
            java.lang.String r1 = r1.concat(r2)
            goto L_0x0187
        L_0x0181:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            r1 = r2
        L_0x0187:
            r0.<init>(r1)
            throw r0
        L_0x018b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.source.hls.HlsMediaPeriod.buildAndPrepareMainSampleStreamWrapper(androidx.media2.exoplayer.external.source.hls.playlist.HlsMasterPlaylist, long, java.util.List, java.util.List, java.util.Map):void");
    }

    private void buildAndPrepareAudioSampleStreamWrappers(long j, List<HlsMasterPlaylist.Rendition> list, List<HlsSampleStreamWrapper> list2, List<int[]> list3, Map<String, DrmInitData> map) {
        List<HlsMasterPlaylist.Rendition> list4 = list;
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        ArrayList arrayList3 = new ArrayList(list.size());
        HashSet hashSet = new HashSet();
        for (int i = 0; i < list.size(); i++) {
            String str = list4.get(i).name;
            if (!hashSet.add(str)) {
                List<HlsSampleStreamWrapper> list5 = list2;
                List<int[]> list6 = list3;
            } else {
                arrayList.clear();
                arrayList2.clear();
                arrayList3.clear();
                boolean z = true;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (Util.areEqual(str, list4.get(i2).name)) {
                        HlsMasterPlaylist.Rendition rendition = list4.get(i2);
                        arrayList3.add(Integer.valueOf(i2));
                        arrayList.add(rendition.url);
                        arrayList2.add(rendition.format);
                        z &= rendition.format.codecs != null;
                    }
                }
                HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper(1, (Uri[]) arrayList.toArray(new Uri[0]), (Format[]) arrayList2.toArray(new Format[0]), (Format) null, Collections.emptyList(), map, j);
                list3.add(Util.toArray(arrayList3));
                list2.add(buildSampleStreamWrapper);
                if (this.allowChunklessPreparation && z) {
                    buildSampleStreamWrapper.prepareWithMasterPlaylistInfo(new TrackGroupArray(new TrackGroup((Format[]) arrayList2.toArray(new Format[0]))), 0, TrackGroupArray.EMPTY);
                }
            }
        }
    }

    private HlsSampleStreamWrapper buildSampleStreamWrapper(int i, Uri[] uriArr, Format[] formatArr, Format format, List<Format> list, Map<String, DrmInitData> map, long j) {
        return new HlsSampleStreamWrapper(i, this, new HlsChunkSource(this.extractorFactory, this.playlistTracker, uriArr, formatArr, this.dataSourceFactory, this.mediaTransferListener, this.timestampAdjusterProvider, list), map, this.allocator, j, format, this.loadErrorHandlingPolicy, this.eventDispatcher);
    }

    private static Map<String, DrmInitData> deriveOverridingDrmInitData(List<DrmInitData> list) {
        ArrayList arrayList = new ArrayList(list);
        HashMap hashMap = new HashMap();
        int i = 0;
        while (i < arrayList.size()) {
            DrmInitData drmInitData = list.get(i);
            String str = drmInitData.schemeType;
            i++;
            int i2 = i;
            while (i2 < arrayList.size()) {
                DrmInitData drmInitData2 = (DrmInitData) arrayList.get(i2);
                if (TextUtils.equals(drmInitData2.schemeType, str)) {
                    drmInitData = drmInitData.merge(drmInitData2);
                    arrayList.remove(i2);
                } else {
                    i2++;
                }
            }
            hashMap.put(str, drmInitData);
        }
        return hashMap;
    }

    private static Format deriveVideoFormat(Format format) {
        String codecsOfType = Util.getCodecsOfType(format.codecs, 2);
        return Format.createVideoContainerFormat(format.id, format.label, format.containerMimeType, MimeTypes.getMediaMimeType(codecsOfType), codecsOfType, format.bitrate, format.width, format.height, format.frameRate, (List<byte[]>) null, format.selectionFlags, format.roleFlags);
    }

    private static Format deriveAudioFormat(Format format, Format format2, boolean z) {
        String str;
        int i;
        int i2;
        int i3;
        String str2;
        String str3;
        Format format3 = format;
        Format format4 = format2;
        if (format4 != null) {
            String str4 = format4.codecs;
            int i4 = format4.channelCount;
            int i5 = format4.selectionFlags;
            int i6 = format4.roleFlags;
            String str5 = format4.language;
            str3 = format4.label;
            str2 = str4;
            i3 = i4;
            i2 = i5;
            i = i6;
            str = str5;
        } else {
            String codecsOfType = Util.getCodecsOfType(format3.codecs, 1);
            if (z) {
                int i7 = format3.channelCount;
                str2 = codecsOfType;
                i2 = format3.selectionFlags;
                i3 = i7;
                i = format3.roleFlags;
                str = format3.language;
                str3 = format3.label;
            } else {
                str2 = codecsOfType;
                str3 = null;
                str = null;
                i3 = -1;
                i2 = 0;
                i = 0;
            }
        }
        return Format.createAudioContainerFormat(format3.id, str3, format3.containerMimeType, MimeTypes.getMediaMimeType(str2), str2, z ? format3.bitrate : -1, i3, -1, (List<byte[]>) null, i2, i, str);
    }
}
