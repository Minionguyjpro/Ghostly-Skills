package com.startapp.android.publish.ads.video.b;

import android.media.MediaPlayer;
import android.widget.VideoView;
import com.startapp.android.publish.ads.video.b.c;
import com.startapp.android.publish.ads.video.c;
import com.startapp.common.a.g;

/* compiled from: StartAppSDK */
public class b extends a implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private MediaPlayer h;
    private VideoView i;

    /* renamed from: com.startapp.android.publish.ads.video.b.b$b  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    public enum C0001b {
        MEDIA_ERROR_UNKNOWN,
        MEDIA_ERROR_SERVER_DIED;

        public static C0001b a(int i) {
            if (i == 100) {
                return MEDIA_ERROR_SERVER_DIED;
            }
            return MEDIA_ERROR_UNKNOWN;
        }
    }

    /* compiled from: StartAppSDK */
    public enum a {
        MEDIA_ERROR_IO,
        MEDIA_ERROR_MALFORMED,
        MEDIA_ERROR_UNSUPPORTED,
        MEDIA_ERROR_TIMED_OUT;

        public static a a(int i) {
            if (i == -1010) {
                return MEDIA_ERROR_UNSUPPORTED;
            }
            if (i == -1007) {
                return MEDIA_ERROR_MALFORMED;
            }
            if (i == -1004) {
                return MEDIA_ERROR_IO;
            }
            if (i != -110) {
                return MEDIA_ERROR_IO;
            }
            return MEDIA_ERROR_TIMED_OUT;
        }
    }

    public b(VideoView videoView) {
        g.a("NativeVideoPlayer", 4, "Ctor");
        this.i = videoView;
        videoView.setOnPreparedListener(this);
        this.i.setOnCompletionListener(this);
        this.i.setOnErrorListener(this);
    }

    public void a() {
        g.a("NativeVideoPlayer", 4, "start");
        this.i.start();
    }

    public void a(int i2) {
        g.a("NativeVideoPlayer", 4, "seekTo(" + i2 + ")");
        this.i.seekTo(i2);
    }

    public void b() {
        g.a("NativeVideoPlayer", 4, "pause");
        this.i.pause();
    }

    public void c() {
        g.a("NativeVideoPlayer", 4, "stop");
        this.i.stopPlayback();
    }

    public void a(boolean z) {
        g.a("NativeVideoPlayer", 4, "setMute(" + z + ")");
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer == null) {
            return;
        }
        if (z) {
            mediaPlayer.setVolume(0.0f, 0.0f);
        } else {
            mediaPlayer.setVolume(1.0f, 1.0f);
        }
    }

    public int d() {
        return this.i.getCurrentPosition();
    }

    public int e() {
        return this.i.getDuration();
    }

    public boolean f() {
        return this.h != null;
    }

    public void a(String str) {
        g.a("NativeVideoPlayer", 4, "setVideoLocation(" + str + ")");
        super.a(str);
        this.i.setVideoPath(this.f110a);
    }

    public void g() {
        if (this.h != null) {
            this.h = null;
        }
        c.a().a((c.C0002c) null);
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        MediaPlayer mediaPlayer2;
        g.a("NativeVideoPlayer", 4, "onPrepared");
        this.h = mediaPlayer;
        if (this.b != null) {
            g.a("NativeVideoPlayer", 3, "Dispatching onPrepared");
            this.b.a();
        }
        if (com.startapp.android.publish.adsCommon.c.d(this.f110a) && (mediaPlayer2 = this.h) != null) {
            mediaPlayer2.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                    if (b.this.f != null) {
                        g.a("NativeVideoPlayer", 4, "onBufferingUpdate");
                        b.this.f.a(i);
                    }
                }
            });
        } else if (!com.startapp.android.publish.adsCommon.c.d(this.f110a)) {
            com.startapp.android.publish.ads.video.c.a().a(this.f);
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        g.a("NativeVideoPlayer", 4, "onCompletion");
        if (this.d != null) {
            g.a("NativeVideoPlayer", 3, "Dispatching onCompletion");
            this.d.a();
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
        g.a("NativeVideoPlayer", 6, "onError(" + i2 + ", " + i3 + ")");
        if (this.c == null) {
            return false;
        }
        g.a("NativeVideoPlayer", 3, "Dispatching onError");
        return this.c.a(a(i2, i3, mediaPlayer != null ? mediaPlayer.getCurrentPosition() : -1));
    }

    private c.g a(int i2, int i3, int i4) {
        return new c.g(C0001b.a(i2) == C0001b.MEDIA_ERROR_SERVER_DIED ? c.h.SERVER_DIED : c.h.UNKNOWN, a.a(i3).toString(), i4);
    }
}
