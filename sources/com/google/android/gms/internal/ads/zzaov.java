package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import com.google.android.gms.ads.internal.zzbv;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@zzadh
public final class zzaov extends zzapg implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener {
    private static final Map<Integer, String> zzcwo = new HashMap();
    private final zzapx zzcwp;
    private final boolean zzcwq;
    private int zzcwr = 0;
    private int zzcws = 0;
    private MediaPlayer zzcwt;
    private Uri zzcwu;
    private int zzcwv;
    private int zzcww;
    private int zzcwx;
    private int zzcwy;
    private int zzcwz;
    private zzapu zzcxa;
    private boolean zzcxb;
    private int zzcxc;
    /* access modifiers changed from: private */
    public zzapf zzcxd;

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            zzcwo.put(-1004, "MEDIA_ERROR_IO");
            zzcwo.put(-1007, "MEDIA_ERROR_MALFORMED");
            zzcwo.put(-1010, "MEDIA_ERROR_UNSUPPORTED");
            zzcwo.put(-110, "MEDIA_ERROR_TIMED_OUT");
            zzcwo.put(3, "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzcwo.put(100, "MEDIA_ERROR_SERVER_DIED");
        zzcwo.put(1, "MEDIA_ERROR_UNKNOWN");
        zzcwo.put(1, "MEDIA_INFO_UNKNOWN");
        zzcwo.put(700, "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzcwo.put(701, "MEDIA_INFO_BUFFERING_START");
        zzcwo.put(702, "MEDIA_INFO_BUFFERING_END");
        zzcwo.put(800, "MEDIA_INFO_BAD_INTERLEAVING");
        zzcwo.put(801, "MEDIA_INFO_NOT_SEEKABLE");
        zzcwo.put(802, "MEDIA_INFO_METADATA_UPDATE");
        if (Build.VERSION.SDK_INT >= 19) {
            zzcwo.put(901, "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzcwo.put(902, "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }

    public zzaov(Context context, boolean z, boolean z2, zzapv zzapv, zzapx zzapx) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzcwp = zzapx;
        this.zzcxb = z;
        this.zzcwq = z2;
        zzapx.zzb(this);
    }

    private final void zza(float f) {
        MediaPlayer mediaPlayer = this.zzcwt;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.setVolume(f, f);
            } catch (IllegalStateException unused) {
            }
        } else {
            zzakb.zzdk("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
        }
    }

    private final void zzag(int i) {
        if (i == 3) {
            this.zzcwp.zztt();
            this.zzcxl.zztt();
        } else if (this.zzcwr == 3) {
            this.zzcwp.zztu();
            this.zzcxl.zztu();
        }
        this.zzcwr = i;
    }

    private final void zzag(boolean z) {
        zzakb.v("AdMediaPlayerView release");
        zzapu zzapu = this.zzcxa;
        if (zzapu != null) {
            zzapu.zzti();
            this.zzcxa = null;
        }
        MediaPlayer mediaPlayer = this.zzcwt;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.zzcwt.release();
            this.zzcwt = null;
            zzag(0);
            if (z) {
                this.zzcws = 0;
                this.zzcws = 0;
            }
        }
    }

    private final void zzsq() {
        zzakb.v("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzcwu != null && surfaceTexture != null) {
            zzag(false);
            try {
                zzbv.zzfb();
                MediaPlayer mediaPlayer = new MediaPlayer();
                this.zzcwt = mediaPlayer;
                mediaPlayer.setOnBufferingUpdateListener(this);
                this.zzcwt.setOnCompletionListener(this);
                this.zzcwt.setOnErrorListener(this);
                this.zzcwt.setOnInfoListener(this);
                this.zzcwt.setOnPreparedListener(this);
                this.zzcwt.setOnVideoSizeChangedListener(this);
                this.zzcwx = 0;
                if (this.zzcxb) {
                    zzapu zzapu = new zzapu(getContext());
                    this.zzcxa = zzapu;
                    zzapu.zza(surfaceTexture, getWidth(), getHeight());
                    this.zzcxa.start();
                    SurfaceTexture zztj = this.zzcxa.zztj();
                    if (zztj != null) {
                        surfaceTexture = zztj;
                    } else {
                        this.zzcxa.zzti();
                        this.zzcxa = null;
                    }
                }
                this.zzcwt.setDataSource(getContext(), this.zzcwu);
                zzbv.zzfc();
                this.zzcwt.setSurface(new Surface(surfaceTexture));
                this.zzcwt.setAudioStreamType(3);
                this.zzcwt.setScreenOnWhilePlaying(true);
                this.zzcwt.prepareAsync();
                zzag(1);
            } catch (IOException | IllegalArgumentException | IllegalStateException e) {
                String valueOf = String.valueOf(this.zzcwu);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36);
                sb.append("Failed to initialize MediaPlayer at ");
                sb.append(valueOf);
                zzakb.zzc(sb.toString(), e);
                onError(this.zzcwt, 1, 0);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034 A[LOOP:0: B:10:0x0034->B:15:0x004f, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzsr() {
        /*
            r8 = this;
            boolean r0 = r8.zzcwq
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            boolean r0 = r8.zzss()
            if (r0 == 0) goto L_0x0059
            android.media.MediaPlayer r0 = r8.zzcwt
            int r0 = r0.getCurrentPosition()
            if (r0 <= 0) goto L_0x0059
            int r0 = r8.zzcws
            r1 = 3
            if (r0 == r1) goto L_0x0059
            java.lang.String r0 = "AdMediaPlayerView nudging MediaPlayer"
            com.google.android.gms.internal.ads.zzakb.v(r0)
            r0 = 0
            r8.zza((float) r0)
            android.media.MediaPlayer r0 = r8.zzcwt
            r0.start()
            android.media.MediaPlayer r0 = r8.zzcwt
            int r0 = r0.getCurrentPosition()
            com.google.android.gms.common.util.Clock r1 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r1 = r1.currentTimeMillis()
        L_0x0034:
            boolean r3 = r8.zzss()
            if (r3 == 0) goto L_0x0051
            android.media.MediaPlayer r3 = r8.zzcwt
            int r3 = r3.getCurrentPosition()
            if (r3 != r0) goto L_0x0051
            com.google.android.gms.common.util.Clock r3 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r3 = r3.currentTimeMillis()
            long r3 = r3 - r1
            r5 = 250(0xfa, double:1.235E-321)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0034
        L_0x0051:
            android.media.MediaPlayer r0 = r8.zzcwt
            r0.pause()
            r8.zzst()
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaov.zzsr():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r2.zzcwr;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzss() {
        /*
            r2 = this;
            android.media.MediaPlayer r0 = r2.zzcwt
            if (r0 == 0) goto L_0x000f
            int r0 = r2.zzcwr
            r1 = -1
            if (r0 == r1) goto L_0x000f
            if (r0 == 0) goto L_0x000f
            r1 = 1
            if (r0 == r1) goto L_0x000f
            return r1
        L_0x000f:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaov.zzss():boolean");
    }

    public final int getCurrentPosition() {
        if (zzss()) {
            return this.zzcwt.getCurrentPosition();
        }
        return 0;
    }

    public final int getDuration() {
        if (zzss()) {
            return this.zzcwt.getDuration();
        }
        return -1;
    }

    public final int getVideoHeight() {
        MediaPlayer mediaPlayer = this.zzcwt;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoHeight();
        }
        return 0;
    }

    public final int getVideoWidth() {
        MediaPlayer mediaPlayer = this.zzcwt;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoWidth();
        }
        return 0;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzcwx = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        zzakb.v("AdMediaPlayerView completion");
        zzag(5);
        this.zzcws = 5;
        zzakk.zzcrm.post(new zzaoy(this));
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str = zzcwo.get(Integer.valueOf(i));
        String str2 = zzcwo.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 38 + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer error: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzakb.zzdk(sb.toString());
        zzag(-1);
        this.zzcws = -1;
        zzakk.zzcrm.post(new zzaoz(this, str, str2));
        return true;
    }

    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = zzcwo.get(Integer.valueOf(i));
        String str2 = zzcwo.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 37 + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer info: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzakb.v(sb.toString());
        return true;
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int defaultSize = getDefaultSize(this.zzcwv, i);
        int defaultSize2 = getDefaultSize(this.zzcww, i2);
        if (this.zzcwv > 0 && this.zzcww > 0 && this.zzcxa == null) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                int i5 = this.zzcwv;
                int i6 = i5 * size2;
                int i7 = this.zzcww;
                if (i6 < size * i7) {
                    defaultSize = (i5 * size2) / i7;
                    defaultSize2 = size2;
                } else {
                    if (i5 * size2 > size * i7) {
                        i4 = (i7 * size) / i5;
                    }
                    defaultSize = size;
                    defaultSize2 = size2;
                }
            } else if (mode == 1073741824) {
                int i8 = (this.zzcww * size) / this.zzcwv;
                if (mode2 != Integer.MIN_VALUE || i8 <= size2) {
                    i4 = i8;
                }
                defaultSize = size;
                defaultSize2 = size2;
            } else if (mode2 == 1073741824) {
                int i9 = (this.zzcwv * size2) / this.zzcww;
                if (mode != Integer.MIN_VALUE || i9 <= size) {
                    defaultSize = i9;
                    defaultSize2 = size2;
                }
                defaultSize = size;
                defaultSize2 = size2;
            } else {
                int i10 = this.zzcwv;
                int i11 = this.zzcww;
                if (mode2 != Integer.MIN_VALUE || i11 <= size2) {
                    defaultSize2 = i11;
                } else {
                    i10 = (i10 * size2) / i11;
                    defaultSize2 = size2;
                }
                if (mode != Integer.MIN_VALUE || i10 <= size) {
                    defaultSize = i10;
                } else {
                    i4 = (this.zzcww * size) / this.zzcwv;
                }
            }
            defaultSize = size;
        }
        setMeasuredDimension(defaultSize, defaultSize2);
        zzapu zzapu = this.zzcxa;
        if (zzapu != null) {
            zzapu.zzh(defaultSize, defaultSize2);
        }
        if (Build.VERSION.SDK_INT == 16) {
            int i12 = this.zzcwy;
            if ((i12 > 0 && i12 != defaultSize) || ((i3 = this.zzcwz) > 0 && i3 != defaultSize2)) {
                zzsr();
            }
            this.zzcwy = defaultSize;
            this.zzcwz = defaultSize2;
        }
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        zzakb.v("AdMediaPlayerView prepared");
        zzag(2);
        this.zzcwp.zzsv();
        zzakk.zzcrm.post(new zzaox(this));
        this.zzcwv = mediaPlayer.getVideoWidth();
        this.zzcww = mediaPlayer.getVideoHeight();
        int i = this.zzcxc;
        if (i != 0) {
            seekTo(i);
        }
        zzsr();
        int i2 = this.zzcwv;
        int i3 = this.zzcww;
        StringBuilder sb = new StringBuilder(62);
        sb.append("AdMediaPlayerView stream dimensions: ");
        sb.append(i2);
        sb.append(" x ");
        sb.append(i3);
        zzakb.zzdj(sb.toString());
        if (this.zzcws == 3) {
            play();
        }
        zzst();
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzakb.v("AdMediaPlayerView surface created");
        zzsq();
        zzakk.zzcrm.post(new zzapa(this));
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzakb.v("AdMediaPlayerView surface destroyed");
        MediaPlayer mediaPlayer = this.zzcwt;
        if (mediaPlayer != null && this.zzcxc == 0) {
            this.zzcxc = mediaPlayer.getCurrentPosition();
        }
        zzapu zzapu = this.zzcxa;
        if (zzapu != null) {
            zzapu.zzti();
        }
        zzakk.zzcrm.post(new zzapc(this));
        zzag(true);
        return true;
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        zzakb.v("AdMediaPlayerView surface changed");
        boolean z = true;
        boolean z2 = this.zzcws == 3;
        if (!(this.zzcwv == i && this.zzcww == i2)) {
            z = false;
        }
        if (this.zzcwt != null && z2 && z) {
            int i3 = this.zzcxc;
            if (i3 != 0) {
                seekTo(i3);
            }
            play();
        }
        zzapu zzapu = this.zzcxa;
        if (zzapu != null) {
            zzapu.zzh(i, i2);
        }
        zzakk.zzcrm.post(new zzapb(this, i, i2));
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzcwp.zzc(this);
        this.zzcxk.zza(surfaceTexture, this.zzcxd);
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder sb = new StringBuilder(57);
        sb.append("AdMediaPlayerView size changed: ");
        sb.append(i);
        sb.append(" x ");
        sb.append(i2);
        zzakb.v(sb.toString());
        this.zzcwv = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        this.zzcww = videoHeight;
        if (this.zzcwv != 0 && videoHeight != 0) {
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public final void onWindowVisibilityChanged(int i) {
        StringBuilder sb = new StringBuilder(58);
        sb.append("AdMediaPlayerView window visibility changed to ");
        sb.append(i);
        zzakb.v(sb.toString());
        zzakk.zzcrm.post(new zzaow(this, i));
        super.onWindowVisibilityChanged(i);
    }

    public final void pause() {
        zzakb.v("AdMediaPlayerView pause");
        if (zzss() && this.zzcwt.isPlaying()) {
            this.zzcwt.pause();
            zzag(4);
            zzakk.zzcrm.post(new zzape(this));
        }
        this.zzcws = 4;
    }

    public final void play() {
        zzakb.v("AdMediaPlayerView play");
        if (zzss()) {
            this.zzcwt.start();
            zzag(3);
            this.zzcxk.zzsw();
            zzakk.zzcrm.post(new zzapd(this));
        }
        this.zzcws = 3;
    }

    public final void seekTo(int i) {
        StringBuilder sb = new StringBuilder(34);
        sb.append("AdMediaPlayerView seek ");
        sb.append(i);
        zzakb.v(sb.toString());
        if (zzss()) {
            this.zzcwt.seekTo(i);
            this.zzcxc = 0;
            return;
        }
        this.zzcxc = i;
    }

    public final void setVideoPath(String str) {
        Uri parse = Uri.parse(str);
        zzhl zzd = zzhl.zzd(parse);
        if (zzd != null) {
            parse = Uri.parse(zzd.url);
        }
        this.zzcwu = parse;
        this.zzcxc = 0;
        zzsq();
        requestLayout();
        invalidate();
    }

    public final void stop() {
        zzakb.v("AdMediaPlayerView stop");
        MediaPlayer mediaPlayer = this.zzcwt;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.zzcwt.release();
            this.zzcwt = null;
            zzag(0);
            this.zzcws = 0;
        }
        this.zzcwp.onStop();
    }

    public final String toString() {
        String name = getClass().getName();
        String hexString = Integer.toHexString(hashCode());
        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length());
        sb.append(name);
        sb.append("@");
        sb.append(hexString);
        return sb.toString();
    }

    public final void zza(float f, float f2) {
        zzapu zzapu = this.zzcxa;
        if (zzapu != null) {
            zzapu.zzb(f, f2);
        }
    }

    public final void zza(zzapf zzapf) {
        this.zzcxd = zzapf;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzah(int i) {
        zzapf zzapf = this.zzcxd;
        if (zzapf != null) {
            zzapf.onWindowVisibilityChanged(i);
        }
    }

    public final String zzsp() {
        String str = this.zzcxb ? " spherical" : "";
        return str.length() != 0 ? "MediaPlayer".concat(str) : new String("MediaPlayer");
    }

    public final void zzst() {
        zza(this.zzcxl.getVolume());
    }
}
