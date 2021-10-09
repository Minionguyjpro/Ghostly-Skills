package com.startapp.android.publish.ads.video;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.facebook.ads.AdSDKNotificationListener;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.mopub.mobileads.resource.DrawableConstants;
import com.startapp.android.publish.ads.video.VideoAdDetails;
import com.startapp.android.publish.ads.video.b.b;
import com.startapp.android.publish.ads.video.b.c;
import com.startapp.android.publish.ads.video.tracking.AbsoluteTrackingLink;
import com.startapp.android.publish.ads.video.tracking.ActionTrackingLink;
import com.startapp.android.publish.ads.video.tracking.FractionTrackingLink;
import com.startapp.android.publish.ads.video.tracking.VideoClickedTrackingParams;
import com.startapp.android.publish.ads.video.tracking.VideoPausedTrackingParams;
import com.startapp.android.publish.ads.video.tracking.VideoProgressTrackingParams;
import com.startapp.android.publish.ads.video.tracking.VideoTrackingLink;
import com.startapp.android.publish.ads.video.tracking.VideoTrackingParams;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.f.e;
import com.startapp.android.publish.adsCommon.n;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.html.JsInterface;
import com.startapp.common.a.c;
import com.startapp.common.a.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class f extends com.startapp.android.publish.ads.a.c {
    protected int A;
    protected String B = null;
    protected Handler C = new Handler();
    protected Handler D = new Handler();
    protected Handler E = new Handler();
    protected Handler F = new Handler();
    private RelativeLayout G;
    private RelativeLayout H;
    private int I = 0;
    private int J = 0;
    private boolean K = false;
    private HashMap<Integer, Boolean> L = new HashMap<>();
    private HashMap<Integer, Boolean> M = new HashMap<>();
    private int N = 1;
    private boolean O = false;
    private boolean P = false;
    private Map<Integer, List<FractionTrackingLink>> Q = new HashMap();
    private Map<Integer, List<AbsoluteTrackingLink>> R = new HashMap();
    private long S;
    private VideoClickedTrackingParams.ClickOrigin T;
    private long U;
    private long V;
    /* access modifiers changed from: private */
    public com.b.a.a.a.b.a.a W;
    protected com.startapp.android.publish.ads.video.b.c i;
    protected VideoView j;
    protected ProgressBar k;
    protected boolean l = false;
    protected int m = 0;
    protected int n = 0;
    protected boolean o;
    protected boolean p = false;
    protected boolean q = false;
    protected boolean r = false;
    protected boolean s = false;
    protected boolean t = false;
    protected int u = 0;
    protected boolean v = false;
    protected boolean w = false;
    protected boolean x = false;
    protected boolean y = false;
    protected int z = 0;

    /* compiled from: StartAppSDK */
    private enum a {
        PLAYER,
        POST_ROLL
    }

    /* compiled from: StartAppSDK */
    private enum b {
        ON,
        OFF
    }

    /* compiled from: StartAppSDK */
    private enum c {
        COMPLETE,
        CLICKED,
        SKIPPED
    }

    /* access modifiers changed from: protected */
    public void z() {
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        try {
            this.S = System.currentTimeMillis();
            this.A = 100 / com.startapp.android.publish.adsCommon.b.a().H().j();
            ac();
            ay();
            ab();
            if (bundle != null && bundle.containsKey("currentPosition")) {
                this.n = bundle.getInt("currentPosition");
                this.I = bundle.getInt("latestPosition");
                this.L = (HashMap) bundle.getSerializable("fractionProgressImpressionsSent");
                this.M = (HashMap) bundle.getSerializable("absoluteProgressImpressionsSent");
                this.l = bundle.getBoolean("isMuted");
                this.o = bundle.getBoolean("shouldSetBg");
                this.m = bundle.getInt("replayNum");
                this.K = bundle.getBoolean("videoCompletedBroadcastSent", false);
                this.N = bundle.getInt("pauseNum");
            }
        } catch (Exception unused) {
            au();
            Context applicationContext = b().getApplicationContext();
            d dVar = d.EXCEPTION;
            com.startapp.android.publish.adsCommon.f.f.a(applicationContext, dVar, "VideoMode.onCreate", "packages : " + j(), "");
            p();
        }
    }

    public void a(WebView webView) {
        super.a(webView);
        webView.setBackgroundColor(33554431);
        com.startapp.common.a.c.a(webView, (Paint) null);
    }

    /* access modifiers changed from: protected */
    public void A() {
        this.p = true;
        if (this.q && X()) {
            G();
        } else if (S()) {
            a((View) this.d);
        }
        if (Y()) {
            K();
        }
        if (S()) {
            ae();
        }
        VideoAdDetails U2 = U();
        if (MetaData.getInstance().isOmsdkEnabled() && this.e == null && U2 != null && U2.getAdVerifications() != null && U2.getAdVerifications().getAdVerification() != null) {
            this.e = com.startapp.android.publish.omsdk.a.a(this.d.getContext(), U().getAdVerifications());
            if (this.e != null) {
                this.W = com.b.a.a.a.b.a.a.a(this.e);
                View a2 = this.f28a.a();
                if (a2 != null) {
                    this.e.b(a2);
                }
                this.e.b(this.d);
                this.e.b(this.H);
                this.e.a(this.j);
                this.e.a();
                com.b.a.a.a.b.a.a(this.e).a();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void G() {
        if (this.r) {
            a((View) this.j);
            if (!S()) {
                af();
            }
        }
    }

    public void u() {
        super.u();
        if (!b().isFinishing()) {
            aa();
        }
    }

    private void aa() {
        if (this.j == null) {
            a(b().getApplicationContext());
        }
        if (this.i == null) {
            this.i = new com.startapp.android.publish.ads.video.b.b(this.j);
        }
        this.q = false;
        this.G.setBackgroundColor(DrawableConstants.CtaButton.BACKGROUND_COLOR);
        H();
        if (S()) {
            this.f28a.a().setVisibility(0);
            this.j.setVisibility(4);
        } else {
            int i2 = this.n;
            if (i2 != 0) {
                this.i.a(i2);
                b(VideoPausedTrackingParams.PauseOrigin.EXTERNAL);
            }
        }
        this.i.a((c.f) new c.f() {
            public void a() {
                f.this.w = true;
                if (f.this.p && f.this.q) {
                    f.this.G();
                }
                if (f.this.Y()) {
                    f.this.K();
                }
            }
        });
        this.i.a((c.d) new c.d() {
            public void a() {
                if (!f.this.S()) {
                    f.this.a(c.COMPLETE);
                }
                f.this.i.c();
            }
        });
        AnonymousClass14 r0 = new c.C0002c() {
            public void a(int i) {
                if (f.this.v && f.this.w && f.this.i != null && f.this.i.e() != 0) {
                    g.a("VideoMode", 3, "buffered percent = [" + i + "]");
                    f.this.u = i;
                    int d = (f.this.i.d() * 100) / f.this.i.e();
                    if (f.this.N()) {
                        if (!f.this.x && f.this.Y()) {
                            f.this.K();
                        } else if (f.this.u == 100 || f.this.u - d > com.startapp.android.publish.adsCommon.b.a().H().j()) {
                            f.this.I();
                        }
                    } else if (f.this.u < 100 && f.this.u - d <= com.startapp.android.publish.adsCommon.b.a().H().k()) {
                        f.this.J();
                    }
                }
            }
        };
        this.i.a((c.e) new c.e() {
            public boolean a(c.g gVar) {
                f.this.w = false;
                if (!f.this.v || f.this.z > f.this.A || gVar.c() <= 0 || !gVar.b().equals(b.a.MEDIA_ERROR_IO.toString())) {
                    f.this.a(gVar);
                } else {
                    f.this.z++;
                    f.this.L();
                    f.this.i.a(f.this.U().getLocalVideoPath());
                    f.this.i.a(gVar.c());
                }
                return true;
            }
        });
        this.i.a((c.b) new c.b() {
        });
        this.i.a((c.C0002c) r0);
        this.i.a((c.a) new c.a() {
        });
        com.startapp.common.a.c.a((View) this.j, (c.a) new c.a() {
            public void a(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                f.this.q = true;
                if (f.this.p && f.this.X()) {
                    f.this.G();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void H() {
        boolean i2 = com.startapp.android.publish.adsCommon.b.a().H().i();
        String localVideoPath = U().getLocalVideoPath();
        if (localVideoPath != null) {
            this.i.a(localVideoPath);
            if (i2 && c.a().b(localVideoPath)) {
                g.a("VideoMode", 3, "progressive video from local file");
                this.v = true;
                this.y = true;
                this.u = com.startapp.android.publish.adsCommon.b.a().H().k();
            }
        } else if (i2) {
            g.a("VideoMode", 3, "progressive video from url");
            String videoUrl = U().getVideoUrl();
            c.a().a(videoUrl);
            this.i.a(videoUrl);
            this.v = true;
            L();
        } else {
            a(c.SKIPPED);
        }
        if (this.B == null) {
            this.B = this.v ? InternalAvidAdSessionContext.AVID_API_LEVEL : "1";
        }
    }

    /* access modifiers changed from: protected */
    public void I() {
        g.a("VideoMode", 3, "progressive video resumed, buffered percent: [" + Integer.toString(this.u) + "]");
        this.i.a();
        M();
    }

    /* access modifiers changed from: protected */
    public void J() {
        g.a("VideoMode", 3, "progressive video paused, buffered percent: [" + Integer.toString(this.u) + "]");
        this.i.b();
        L();
    }

    /* access modifiers changed from: protected */
    public void K() {
        this.x = true;
        ag();
        if (S()) {
            this.i.b();
            return;
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (f.this.i != null) {
                    f.this.i.a();
                    if (f.this.W != null) {
                        f.this.W.a((float) f.this.i.e(), f.this.l ? 0.0f : 1.0f);
                    }
                    f.this.r = true;
                    f.this.M();
                    new Handler().post(new Runnable() {
                        public void run() {
                            f.this.G();
                        }
                    });
                }
            }
        }, ad());
        if (this.n == 0) {
            this.C.postDelayed(new Runnable() {
                public void run() {
                    try {
                        if (f.this.i == null) {
                            return;
                        }
                        if (f.this.i.d() > 0) {
                            f.this.f(0);
                            f.this.g(0);
                            if (f.this.m == 0) {
                                f.this.Z();
                                com.startapp.common.b.a((Context) f.this.b()).a(new Intent("com.startapp.android.ShowDisplayBroadcastListener"));
                            }
                        } else if (!f.this.s) {
                            f.this.C.postDelayed(this, 100);
                        }
                    } catch (Exception e) {
                        com.startapp.android.publish.adsCommon.f.f.a(f.this.b().getApplicationContext(), new e(d.EXCEPTION, "VideoMode.startVideoPlayback", e.getMessage()), f.this.ax());
                        f.this.p();
                    }
                }
            }, 100);
        }
        am();
        ap();
        ah();
        ai();
        this.f28a.a().setVisibility(4);
        W();
    }

    /* access modifiers changed from: protected */
    public void L() {
        if (!N()) {
            this.t = false;
            this.F.postDelayed(new Runnable() {
                public void run() {
                    try {
                        f.this.k.setVisibility(0);
                        if (f.this.W != null) {
                            f.this.W.f();
                        }
                        f.this.F.postDelayed(new Runnable() {
                            public void run() {
                                g.a("VideoMode", 5, "Buffering timeout reached");
                                try {
                                    f.this.M();
                                    f.this.t = true;
                                    f.this.a(new c.g(c.h.BUFFERING_TIMEOUT, "Buffering timeout reached", f.this.n));
                                } catch (Exception e) {
                                    com.startapp.android.publish.adsCommon.f.f.a(f.this.b().getApplicationContext(), new e(d.EXCEPTION, "VideoMode.startBufferingIndicator", e.getMessage()), "");
                                }
                            }
                        }, com.startapp.android.publish.adsCommon.b.a().H().g());
                    } catch (Exception e) {
                        f.this.M();
                        com.startapp.android.publish.adsCommon.f.f.a(f.this.b().getApplicationContext(), new e(d.EXCEPTION, "VideoMode.startBufferingIndicator", e.getMessage()), f.this.ax());
                    }
                }
            }, com.startapp.android.publish.adsCommon.b.a().H().f());
        }
    }

    /* access modifiers changed from: protected */
    public void M() {
        this.F.removeCallbacksAndMessages((Object) null);
        if (N()) {
            this.k.setVisibility(8);
            com.b.a.a.a.b.a.a aVar = this.W;
            if (aVar != null) {
                aVar.g();
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean N() {
        ProgressBar progressBar = this.k;
        return progressBar != null && progressBar.isShown();
    }

    private void ab() {
        this.l = U().isVideoMuted() || com.startapp.android.publish.adsCommon.b.a().H().m().equals("muted");
    }

    private void ac() {
        if (!g().equals("back")) {
            return;
        }
        if (com.startapp.android.publish.adsCommon.b.a().H().a().equals(n.a.BOTH)) {
            this.O = true;
            this.P = true;
        } else if (com.startapp.android.publish.adsCommon.b.a().H().a().equals(n.a.SKIP)) {
            this.O = true;
            this.P = false;
        } else if (com.startapp.android.publish.adsCommon.b.a().H().a().equals(n.a.CLOSE)) {
            this.O = false;
            this.P = true;
        } else if (com.startapp.android.publish.adsCommon.b.a().H().a().equals(n.a.DISABLED)) {
            this.O = false;
            this.P = false;
        } else {
            this.O = false;
            this.P = false;
        }
    }

    private long ad() {
        long currentTimeMillis = System.currentTimeMillis() - this.S;
        if (this.n == 0 && this.m == 0 && currentTimeMillis < 500) {
            return Math.max(200, 500 - currentTimeMillis);
        }
        return 0;
    }

    private void ae() {
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(this.i != null);
        a("videoApi.setReplayEnabled", objArr);
        a("videoApi.setMode", a.POST_ROLL + "_" + U().getPostRollType());
        a("videoApi.setCloseable", true);
    }

    private void af() {
        a("videoApi.setClickableVideo", Boolean.valueOf(U().isClickable()));
        a("videoApi.setMode", a.PLAYER.toString());
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(U().isCloseable() || this.P);
        a("videoApi.setCloseable", objArr);
        a("videoApi.setSkippable", Boolean.valueOf(aw()));
    }

    private void ag() {
        a("videoApi.setVideoDuration", Integer.valueOf(this.i.e() / 1000));
        P();
        aj();
        a("videoApi.setVideoCurrentPosition", Integer.valueOf(this.n / 1000));
    }

    /* access modifiers changed from: protected */
    public void O() {
        a("videoApi.setVideoCurrentPosition", 0);
        a("videoApi.setSkipTimer", 0);
    }

    private void a(View view) {
        a("videoApi.setVideoFrame", Integer.valueOf(h.b(b(), view.getLeft())), Integer.valueOf(h.b(b(), view.getTop())), Integer.valueOf(h.b(b(), view.getWidth())), Integer.valueOf(h.b(b(), view.getHeight())));
    }

    private void ah() {
        this.D.post(new Runnable() {
            public void run() {
                int P = f.this.P();
                if (P >= 1000) {
                    f.this.D.postDelayed(this, f.this.c(P));
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public int P() {
        int ak = ak();
        int i2 = ak / 1000;
        if (i2 > 0 && ak % 1000 < 100) {
            i2--;
        }
        a("videoApi.setVideoRemainingTimer", Integer.valueOf(i2));
        return ak;
    }

    private void ai() {
        aj();
        this.D.post(new Runnable() {
            private boolean b;
            private final int c = f.this.e(com.startapp.android.publish.adsCommon.b.a().H().d());

            public void run() {
                try {
                    int d = f.this.d(f.this.i.d() + 50);
                    if (d >= 0 && !this.b) {
                        if (d != 0) {
                            if (f.this.n < f.this.U().getSkippableAfter() * 1000) {
                                f.this.a("videoApi.setSkipTimer", Integer.valueOf(d));
                            }
                        }
                        this.b = true;
                        f.this.a("videoApi.setSkipTimer", 0);
                    }
                    if (f.this.v && f.this.i.d() >= this.c) {
                        f.this.T();
                    }
                    int d2 = (f.this.i.d() + 50) / 1000;
                    f.this.a("videoApi.setVideoCurrentPosition", Integer.valueOf(d2));
                    if (d2 < f.this.i.e() / 1000) {
                        f.this.D.postDelayed(this, f.this.Q());
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    private void aj() {
        a("videoApi.setSkipTimer", Integer.valueOf(d(this.n + 50)));
    }

    private int ak() {
        if (this.i.d() != this.i.e() || S()) {
            return this.i.e() - this.i.d();
        }
        return this.i.e();
    }

    /* access modifiers changed from: protected */
    public long c(int i2) {
        int i3 = 1000;
        int i4 = i2 % 1000;
        if (i4 != 0) {
            i3 = i4;
        }
        return (long) (i3 + 50);
    }

    /* access modifiers changed from: protected */
    public long Q() {
        return (long) (1000 - (this.i.d() % 1000));
    }

    /* access modifiers changed from: protected */
    public int d(int i2) {
        int skippableAfter;
        if (!this.O && this.m <= 0 && (skippableAfter = (U().getSkippableAfter() * 1000) - i2) > 0) {
            return (skippableAfter / 1000) + 1;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void a(c cVar) {
        com.b.a.a.a.b.a.a aVar;
        com.b.a.a.a.b.a.a aVar2;
        if (cVar == c.COMPLETE && (aVar2 = this.W) != null) {
            aVar2.d();
        }
        if (cVar == c.SKIPPED && (aVar = this.W) != null) {
            aVar.h();
        }
        if (cVar == c.SKIPPED || cVar == c.CLICKED) {
            this.C.removeCallbacksAndMessages((Object) null);
            this.E.removeCallbacksAndMessages((Object) null);
            com.startapp.android.publish.ads.video.b.c cVar2 = this.i;
            if (cVar2 != null) {
                this.I = cVar2.d();
                this.i.b();
            }
        } else {
            this.I = this.J;
            T();
        }
        this.D.removeCallbacksAndMessages((Object) null);
        this.L.clear();
        this.M.clear();
        if (cVar == c.CLICKED) {
            al();
            return;
        }
        if (U().getPostRollType() != VideoAdDetails.PostRollType.NONE) {
            ae();
            this.f28a.a().setVisibility(0);
        }
        if (U().getPostRollType() == VideoAdDetails.PostRollType.IMAGE) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (!f.this.t) {
                        f.this.j.setVisibility(4);
                    }
                }
            }, 1000);
        } else if (U().getPostRollType() == VideoAdDetails.PostRollType.NONE) {
            p();
        }
        al();
        if (U().getPostRollType() != VideoAdDetails.PostRollType.NONE) {
            az();
        }
    }

    private void al() {
        this.n = -1;
    }

    /* access modifiers changed from: protected */
    public void R() {
        this.n = 0;
    }

    /* access modifiers changed from: protected */
    public boolean S() {
        return this.n == -1;
    }

    private void am() {
        this.J = this.i.e();
        an();
        ao();
    }

    private void an() {
        for (Integer intValue : this.Q.keySet()) {
            final int intValue2 = intValue.intValue();
            a(e(intValue2), this.C, (Runnable) new Runnable() {
                public void run() {
                    try {
                        f.this.f(intValue2);
                    } catch (Exception e) {
                        com.startapp.android.publish.adsCommon.f.f.a(f.this.b().getApplicationContext(), new e(d.EXCEPTION, "VideoMode.scheduleFractionProgressEvents", e.getMessage()), f.this.ax());
                    }
                }
            });
        }
    }

    private void ao() {
        for (Integer intValue : this.R.keySet()) {
            final int intValue2 = intValue.intValue();
            a(intValue2, this.C, (Runnable) new Runnable() {
                public void run() {
                    try {
                        f.this.g(intValue2);
                    } catch (Exception e) {
                        com.startapp.android.publish.adsCommon.f.f.a(f.this.b().getApplicationContext(), new e(d.EXCEPTION, "VideoMode.scheduleAbsoluteProgressEvents", e.getMessage()), f.this.ax());
                    }
                }
            });
        }
    }

    private void ap() {
        if (!this.v) {
            a(e(com.startapp.android.publish.adsCommon.b.a().H().d()), this.E, (Runnable) new Runnable() {
                public void run() {
                    try {
                        f.this.T();
                    } catch (Exception e) {
                        com.startapp.android.publish.adsCommon.f.f.a(f.this.b().getApplicationContext(), new e(d.EXCEPTION, "VideoMode.scheduleVideoListenerEvents", e.getMessage()), f.this.ax());
                    }
                }
            });
        }
    }

    private void a(int i2, Handler handler, Runnable runnable) {
        int i3 = this.n;
        if (i3 < i2) {
            handler.postDelayed(runnable, (long) (i2 - i3));
        }
    }

    /* access modifiers changed from: protected */
    public int e(int i2) {
        return (this.J * i2) / 100;
    }

    /* access modifiers changed from: protected */
    public void T() {
        if (aq() && !this.K && this.m == 0) {
            this.K = true;
            g.a("VideoMode", 3, "Sending rewarded video completion broadcast.");
            if (com.startapp.common.b.a((Context) b()).a(new Intent("com.startapp.android.OnVideoCompleted"))) {
                g.a("VideoMode", 3, "Rewarded video completion broadcast sent successfully.");
            }
            aA();
        }
    }

    private boolean aq() {
        return w().getType() == Ad.AdType.REWARDED_VIDEO;
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        bundle.putInt("currentPosition", this.n);
        bundle.putInt("latestPosition", this.I);
        bundle.putSerializable("fractionProgressImpressionsSent", this.L);
        bundle.putSerializable("absoluteProgressImpressionsSent", this.M);
        bundle.putBoolean("isMuted", this.l);
        bundle.putBoolean("shouldSetBg", this.o);
        bundle.putInt("replayNum", this.m);
        bundle.putInt("pauseNum", this.N);
        bundle.putBoolean("videoCompletedBroadcastSent", this.K);
    }

    /* access modifiers changed from: protected */
    public VideoAdDetails U() {
        return ((e) w()).d();
    }

    public void s() {
        if (!S() && !b().isFinishing() && !this.P && !this.O) {
            a(VideoPausedTrackingParams.PauseOrigin.EXTERNAL);
        }
        av();
        this.C.removeCallbacksAndMessages((Object) null);
        this.D.removeCallbacksAndMessages((Object) null);
        this.E.removeCallbacksAndMessages((Object) null);
        M();
        this.o = true;
        super.s();
    }

    public void p() {
        super.p();
        if (this.y) {
            c.a().c(U().getLocalVideoPath());
        }
    }

    /* access modifiers changed from: protected */
    public JsInterface y() {
        return new VideoJsInterface(b(), this.g, this.g, at(), as(), ar(), new com.startapp.android.publish.adsCommon.d.b(n()), a(0));
    }

    private Runnable ar() {
        return new Runnable() {
            public void run() {
                f fVar = f.this;
                fVar.l = !fVar.l;
                f.this.W();
                f fVar2 = f.this;
                fVar2.a(fVar2.l);
            }
        };
    }

    private Runnable as() {
        return new Runnable() {
            public void run() {
                f.this.V();
            }
        };
    }

    /* access modifiers changed from: protected */
    public void V() {
        if (N()) {
            M();
        }
        a(c.SKIPPED);
        aB();
    }

    private Runnable at() {
        return new Runnable() {
            public void run() {
                f.this.m++;
                f.this.j.setVisibility(0);
                f.this.o = false;
                f.this.R();
                f.this.O();
                f.this.H();
            }
        };
    }

    private RelativeLayout a(Context context) {
        this.U = System.currentTimeMillis();
        this.H = (RelativeLayout) b().findViewById(AdsConstants.STARTAPP_AD_MAIN_LAYOUT_ID);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        VideoView videoView = new VideoView(context);
        this.j = videoView;
        videoView.setId(100);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(13);
        ProgressBar progressBar = new ProgressBar(context, (AttributeSet) null, 16843399);
        this.k = progressBar;
        progressBar.setVisibility(4);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(14);
        layoutParams3.addRule(15);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        this.G = relativeLayout;
        relativeLayout.setId(1475346436);
        b().setContentView(this.G);
        this.G.addView(this.j, layoutParams2);
        this.G.addView(this.H, layoutParams);
        this.G.addView(this.k, layoutParams3);
        if (AdsConstants.a().booleanValue()) {
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams4.addRule(12);
            layoutParams4.addRule(14);
            this.G.addView(b(context), layoutParams4);
        }
        this.f28a.a().setVisibility(4);
        return this.G;
    }

    private View b(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("url=" + U().getVideoUrl());
        TextView textView = new TextView(context);
        textView.setBackgroundColor(DrawableConstants.CtaButton.BACKGROUND_COLOR);
        com.startapp.common.a.c.a((View) textView, 0.5f);
        textView.setTextColor(-7829368);
        textView.setSingleLine(false);
        textView.setText(sb.toString());
        return textView;
    }

    /* access modifiers changed from: protected */
    public void W() {
        String str;
        com.startapp.android.publish.ads.video.b.c cVar = this.i;
        if (cVar != null) {
            try {
                if (this.l) {
                    cVar.a(true);
                } else {
                    cVar.a(false);
                }
            } catch (IllegalStateException unused) {
            }
        }
        Object[] objArr = new Object[1];
        if (this.l) {
            str = b.OFF.toString();
        } else {
            str = b.ON.toString();
        }
        objArr[0] = str;
        a("videoApi.setSound", objArr);
    }

    private void a(VideoPausedTrackingParams.PauseOrigin pauseOrigin) {
        com.startapp.android.publish.ads.video.b.c cVar = this.i;
        if (cVar != null) {
            int d = cVar.d();
            this.n = d;
            this.I = d;
            this.i.b();
            com.b.a.a.a.b.a.a aVar = this.W;
            if (aVar != null) {
                aVar.e();
            }
        }
        c(pauseOrigin);
    }

    private void b(VideoPausedTrackingParams.PauseOrigin pauseOrigin) {
        d(pauseOrigin);
        this.N++;
    }

    /* access modifiers changed from: protected */
    public void a(c.g gVar) {
        com.startapp.android.publish.ads.video.c.a.a aVar;
        com.startapp.android.publish.adsCommon.f.f.a(b(), d.VIDEO_MEDIA_PLAYER_ERROR, gVar.a().toString(), gVar.b(), ax());
        int i2 = AnonymousClass13.f140a[gVar.a().ordinal()];
        if (i2 == 1) {
            aVar = com.startapp.android.publish.ads.video.c.a.a.GeneralLinearError;
        } else if (i2 == 2) {
            aVar = com.startapp.android.publish.ads.video.c.a.a.TimeoutMediaFileURI;
        } else if (i2 != 3) {
            aVar = com.startapp.android.publish.ads.video.c.a.a.UndefinedError;
        } else {
            aVar = com.startapp.android.publish.ads.video.c.a.a.MediaFileDisplayError;
        }
        a(aVar);
        if ((this.v ? this.i.d() : this.n) == 0) {
            com.startapp.android.publish.adsCommon.c.a((Context) b(), h(), n(), this.m, AdDisplayListener.NotDisplayedReason.VIDEO_ERROR.toString());
            if (!this.v) {
                h.b(b());
            } else if (!gVar.a().equals(c.h.BUFFERING_TIMEOUT)) {
                h.b(b());
            }
        }
        if ((!aq() || this.K) && U().getPostRollType() != VideoAdDetails.PostRollType.NONE) {
            a(c.SKIPPED);
            return;
        }
        au();
        p();
    }

    /* renamed from: com.startapp.android.publish.ads.video.f$13  reason: invalid class name */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class AnonymousClass13 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f140a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.startapp.android.publish.ads.video.b.c$h[] r0 = com.startapp.android.publish.ads.video.b.c.h.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f140a = r0
                com.startapp.android.publish.ads.video.b.c$h r1 = com.startapp.android.publish.ads.video.b.c.h.SERVER_DIED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f140a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.ads.video.b.c$h r1 = com.startapp.android.publish.ads.video.b.c.h.BUFFERING_TIMEOUT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f140a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.ads.video.b.c$h r1 = com.startapp.android.publish.ads.video.b.c.h.PLAYER_CREATION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f140a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.ads.video.b.c$h r1 = com.startapp.android.publish.ads.video.b.c.h.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.video.f.AnonymousClass13.<clinit>():void");
        }
    }

    private void au() {
        Intent intent = new Intent("com.startapp.android.ShowFailedDisplayBroadcastListener");
        intent.putExtra("showFailedReason", AdDisplayListener.NotDisplayedReason.VIDEO_ERROR);
        com.startapp.common.b.a((Context) b()).a(intent);
        this.s = true;
    }

    /* access modifiers changed from: protected */
    public boolean X() {
        com.startapp.android.publish.ads.video.b.c cVar = this.i;
        return cVar != null && cVar.f();
    }

    /* access modifiers changed from: protected */
    public boolean Y() {
        if (!this.v) {
            if (!X() || !this.p) {
                return false;
            }
            return true;
        } else if (this.u < com.startapp.android.publish.adsCommon.b.a().H().k() || !X() || !this.p) {
            return false;
        } else {
            return true;
        }
    }

    private void av() {
        g.a("VideoMode", 3, "Releasing video player");
        com.startapp.android.publish.ads.video.b.c cVar = this.i;
        if (cVar != null) {
            cVar.g();
            this.i = null;
        }
    }

    public boolean r() {
        if (S()) {
            B();
            return false;
        }
        int d = d(this.i.d() + 50);
        if (aw() && d == 0) {
            V();
            return true;
        } else if (!U().isCloseable() && !this.P) {
            return true;
        } else {
            B();
            return false;
        }
    }

    private boolean aw() {
        return this.m > 0 || U().isSkippable() || this.O;
    }

    private int h(int i2) {
        int i3 = this.J;
        if (i3 > 0) {
            return (i2 * 100) / i3;
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public String ax() {
        try {
            String[] h = h();
            if (h != null && h.length > 0) {
                return com.startapp.android.publish.adsCommon.c.e(h[0]);
            }
            g.a("VideoMode", 5, "dParam is not available.");
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public void q() {
        if (!this.s) {
            super.q();
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(String str, boolean z2) {
        if (!TextUtils.isEmpty(U().getClickUrl())) {
            str = U().getClickUrl();
            z2 = true;
        }
        this.T = S() ? VideoClickedTrackingParams.ClickOrigin.POSTROLL : VideoClickedTrackingParams.ClickOrigin.VIDEO;
        g.a("VideoMode", 3, "Video clicked from: " + this.T);
        if (this.T == VideoClickedTrackingParams.ClickOrigin.VIDEO) {
            a(c.CLICKED);
        }
        a(this.T);
        return super.a(str, z2);
    }

    /* access modifiers changed from: protected */
    public void B() {
        if (this.s) {
            g.a("VideoMode", 3, "Not sending close events due to media player error");
        } else if (S() || this.j == null) {
            aC();
            super.B();
        } else {
            aD();
        }
    }

    /* access modifiers changed from: protected */
    public String E() {
        long currentTimeMillis = System.currentTimeMillis();
        this.V = currentTimeMillis;
        double d = (double) (currentTimeMillis - this.U);
        Double.isNaN(d);
        return String.valueOf(d / 1000.0d);
    }

    private void ay() {
        FractionTrackingLink[] fractionTrackingUrls = U().getVideoTrackingDetails().getFractionTrackingUrls();
        if (fractionTrackingUrls != null) {
            for (FractionTrackingLink fractionTrackingLink : fractionTrackingUrls) {
                List list = this.Q.get(Integer.valueOf(fractionTrackingLink.getFraction()));
                if (list == null) {
                    list = new ArrayList();
                    this.Q.put(Integer.valueOf(fractionTrackingLink.getFraction()), list);
                }
                list.add(fractionTrackingLink);
            }
        }
        AbsoluteTrackingLink[] absoluteTrackingUrls = U().getVideoTrackingDetails().getAbsoluteTrackingUrls();
        if (absoluteTrackingUrls != null) {
            for (AbsoluteTrackingLink absoluteTrackingLink : absoluteTrackingUrls) {
                List list2 = this.R.get(Integer.valueOf(absoluteTrackingLink.getVideoOffsetMillis()));
                if (list2 == null) {
                    list2 = new ArrayList();
                    this.R.put(Integer.valueOf(absoluteTrackingLink.getVideoOffsetMillis()), list2);
                }
                list2.add(absoluteTrackingLink);
            }
        }
    }

    /* access modifiers changed from: protected */
    public com.startapp.android.publish.adsCommon.d.b D() {
        return new VideoTrackingParams(n(), 0, this.m, this.B);
    }

    /* access modifiers changed from: protected */
    public long F() {
        if (o() != null) {
            return TimeUnit.SECONDS.toMillis(o().longValue());
        }
        return TimeUnit.SECONDS.toMillis(MetaData.getInstance().getIABVideoImpressionDelayInSeconds());
    }

    /* access modifiers changed from: protected */
    public void Z() {
        super.z();
        a(U().getVideoTrackingDetails().getImpressionUrls(), new VideoTrackingParams(n(), 0, this.m, this.B), 0, AdSDKNotificationListener.IMPRESSION_EVENT);
        a(U().getVideoTrackingDetails().getCreativeViewUrls(), new VideoTrackingParams(n(), 0, this.m, this.B), 0, "creativeView");
    }

    /* access modifiers changed from: protected */
    public void f(int i2) {
        if (this.L.get(Integer.valueOf(i2)) == null) {
            if (this.Q.containsKey(Integer.valueOf(i2))) {
                List list = this.Q.get(Integer.valueOf(i2));
                g.a("VideoMode", 3, "Sending fraction progress event with fraction: " + i2 + ", total: " + list.size());
                a((VideoTrackingLink[]) list.toArray(new FractionTrackingLink[list.size()]), new VideoProgressTrackingParams(n(), i2, this.m, this.B), e(i2), "fraction");
                com.b.a.a.a.b.a.a aVar = this.W;
                if (aVar != null) {
                    if (i2 == 25) {
                        aVar.a();
                    } else if (i2 == 50) {
                        aVar.b();
                    } else if (i2 == 75) {
                        aVar.c();
                    }
                }
            }
            this.L.put(Integer.valueOf(i2), true);
            return;
        }
        g.a("VideoMode", 3, "Fraction progress event already sent for fraction: " + i2);
    }

    /* access modifiers changed from: protected */
    public void g(int i2) {
        if (this.M.get(Integer.valueOf(i2)) == null) {
            if (this.R.containsKey(Integer.valueOf(i2))) {
                List list = this.R.get(Integer.valueOf(i2));
                g.a("VideoMode", 3, "Sending absolute progress event with video progress: " + i2 + ", total: " + list.size());
                a((VideoTrackingLink[]) list.toArray(new AbsoluteTrackingLink[list.size()]), new VideoProgressTrackingParams(n(), i2, this.m, this.B), i2, "absolute");
            }
            this.M.put(Integer.valueOf(i2), true);
            return;
        }
        g.a("VideoMode", 3, "Absolute progress event already sent for video progress: " + i2);
    }

    private void az() {
        g.a("VideoMode", 3, "Sending postroll impression event");
        a(U().getVideoTrackingDetails().getVideoPostRollImpressionUrls(), new VideoTrackingParams(n(), h(this.I), this.m, this.B), this.I, "postrollImression");
    }

    private void aA() {
        g.a("VideoMode", 3, "Sending rewarded event");
        a(U().getVideoTrackingDetails().getVideoRewardedUrls(), new VideoTrackingParams(n(), com.startapp.android.publish.adsCommon.b.a().H().d(), this.m, this.B), e(com.startapp.android.publish.adsCommon.b.a().H().d()), "rewarded");
    }

    /* access modifiers changed from: protected */
    public void a(boolean z2) {
        ActionTrackingLink[] actionTrackingLinkArr;
        StringBuilder sb = new StringBuilder();
        sb.append("Sending sound ");
        sb.append(z2 ? "muted " : "unmuted ");
        sb.append("event");
        g.a("VideoMode", 3, sb.toString());
        if (z2) {
            actionTrackingLinkArr = U().getVideoTrackingDetails().getSoundMuteUrls();
        } else {
            actionTrackingLinkArr = U().getVideoTrackingDetails().getSoundUnmuteUrls();
        }
        a(actionTrackingLinkArr, new VideoTrackingParams(n(), h(this.i.d()), this.m, this.B), this.i.d(), "sound");
        com.b.a.a.a.b.a.a aVar = this.W;
        if (aVar != null) {
            aVar.a(z2 ? 0.0f : 1.0f);
        }
    }

    private void aB() {
        g.a("VideoMode", 3, "Sending skip event");
        a(U().getVideoTrackingDetails().getVideoSkippedUrls(), new VideoTrackingParams(n(), h(this.I), this.m, this.B), this.I, "skipped");
    }

    private void c(VideoPausedTrackingParams.PauseOrigin pauseOrigin) {
        g.a("VideoMode", 3, "Sending pause event with origin: " + pauseOrigin);
        a(U().getVideoTrackingDetails().getVideoPausedUrls(), new VideoPausedTrackingParams(n(), h(this.I), this.m, this.N, pauseOrigin, this.B), this.I, "paused");
    }

    private void d(VideoPausedTrackingParams.PauseOrigin pauseOrigin) {
        g.a("VideoMode", 3, "Sending resume event with pause origin: " + pauseOrigin);
        a(U().getVideoTrackingDetails().getVideoResumedUrls(), new VideoPausedTrackingParams(n(), h(this.I), this.m, this.N, pauseOrigin, this.B), this.I, "resumed");
    }

    private void aC() {
        g.a("VideoMode", 3, "Sending postroll closed event");
        a(U().getVideoTrackingDetails().getVideoPostRollClosedUrls(), new VideoTrackingParams(n(), h(this.I), this.m, this.B), this.I, "postrollClosed");
    }

    private void aD() {
        g.a("VideoMode", 3, "Sending video closed event");
        a(U().getVideoTrackingDetails().getVideoClosedUrls(), new VideoTrackingParams(n(), h(this.i.d()), this.m, this.B), this.i.d(), "closed");
    }

    private void a(VideoClickedTrackingParams.ClickOrigin clickOrigin) {
        g.a("VideoMode", 3, "Sending video clicked event with origin: " + clickOrigin.toString());
        a(U().getVideoTrackingDetails().getVideoClickTrackingUrls(), new VideoClickedTrackingParams(n(), h(this.I), this.m, clickOrigin, this.B), this.I, "clicked");
    }

    private void a(VideoTrackingLink[] videoTrackingLinkArr, VideoTrackingParams videoTrackingParams, int i2, String str) {
        h.a((Context) b(), new com.startapp.android.publish.ads.video.a.b(videoTrackingLinkArr, videoTrackingParams, U().getVideoUrl(), i2).a(str).a());
    }

    private void a(com.startapp.android.publish.ads.video.c.a.a aVar) {
        g.a("VideoMode", 3, "Sending internal video event");
        h.a((Context) b(), new com.startapp.android.publish.ads.video.a.b(U().getVideoTrackingDetails().getInlineErrorTrackingUrls(), new VideoTrackingParams(n(), h(this.I), this.m, this.B), U().getVideoUrl(), this.I).a(aVar).a("error").a());
    }
}
