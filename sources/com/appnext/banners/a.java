package com.appnext.banners;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.VideoView;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.ECPM;
import com.appnext.core.a.b;
import com.appnext.core.callbacks.OnECPMLoaded;
import com.appnext.core.d;
import com.appnext.core.e;
import com.appnext.core.f;
import com.appnext.core.j;
import com.appnext.core.k;
import com.appnext.core.p;
import com.appnext.core.q;
import java.util.ArrayList;
import java.util.HashMap;

public class a extends e {
    private final int TICK = 330;
    /* access modifiers changed from: private */
    public BannerAdRequest adRequest;
    /* access modifiers changed from: private */
    public ArrayList<AppnextAd> ads;
    /* access modifiers changed from: private */
    public BannerAd bannerAd;
    private boolean clickEnabled = true;
    /* access modifiers changed from: private */
    public BannerAdData currentAd;
    /* access modifiers changed from: private */
    public int currentPosition = 0;
    /* access modifiers changed from: private */
    public boolean finished = false;
    private int lastProgress = 0;
    /* access modifiers changed from: private */
    public boolean loaded = false;
    /* access modifiers changed from: private */
    public Handler mHandler;
    /* access modifiers changed from: private */
    public MediaPlayer mediaPlayer;
    /* access modifiers changed from: private */
    public boolean reportedImpression = false;
    private i serviceHelper;
    /* access modifiers changed from: private */
    public boolean started = false;
    /* access modifiers changed from: private */
    public String template = "";
    /* access modifiers changed from: private */
    public Runnable tick = new Runnable() {
        public final void run() {
            try {
                a.this.checkProgress();
                int unused = a.this.currentPosition = a.this.mediaPlayer.getCurrentPosition();
                if (a.this.mediaPlayer.getCurrentPosition() < a.this.mediaPlayer.getDuration() && !a.this.finished) {
                    a.this.mHandler.postDelayed(a.this.tick, 330);
                }
            } catch (Throwable unused2) {
            }
        }
    };
    /* access modifiers changed from: private */
    public q userAction;
    /* access modifiers changed from: private */
    public boolean userMute = true;
    private VideoView videoView;

    public void init(ViewGroup viewGroup) {
        super.init(viewGroup);
        this.userAction = new q(this.context, new q.a() {
            public final void report(String str) {
                a.this.report(str);
            }

            public final Ad e() {
                return a.this.bannerAd;
            }

            public final AppnextAd f() {
                return a.this.getSelectedAd();
            }

            public final p g() {
                return d.S();
            }
        });
        this.mHandler = new Handler();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.appnext.core.Ad createAd(android.content.Context r6, java.lang.String r7) {
        /*
            r5 = this;
            com.appnext.banners.BannerSize r0 = r5.getBannerSize()
            java.lang.String r0 = r0.toString()
            int r1 = r0.hashCode()
            r2 = -1966536496(0xffffffff8ac908d0, float:-1.9358911E-32)
            r3 = 2
            r4 = 1
            if (r1 == r2) goto L_0x0032
            r2 = -96588539(0xfffffffffa3e2d05, float:-2.4686238E35)
            if (r1 == r2) goto L_0x0028
            r2 = 1951953708(0x7458732c, float:6.859571E31)
            if (r1 == r2) goto L_0x001e
            goto L_0x003c
        L_0x001e:
            java.lang.String r1 = "BANNER"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003c
            r0 = 0
            goto L_0x003d
        L_0x0028:
            java.lang.String r1 = "MEDIUM_RECTANGLE"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003c
            r0 = 2
            goto L_0x003d
        L_0x0032:
            java.lang.String r1 = "LARGE_BANNER"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003c
            r0 = 1
            goto L_0x003d
        L_0x003c:
            r0 = -1
        L_0x003d:
            if (r0 == 0) goto L_0x0067
            if (r0 == r4) goto L_0x0061
            if (r0 != r3) goto L_0x0049
            com.appnext.banners.MediumRectangleAd r0 = new com.appnext.banners.MediumRectangleAd
            r0.<init>(r6, r7)
            return r0
        L_0x0049:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "Wrong banner size "
            r7.<init>(r0)
            com.appnext.banners.BannerSize r0 = r5.getBannerSize()
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x0061:
            com.appnext.banners.LargeBannerAd r0 = new com.appnext.banners.LargeBannerAd
            r0.<init>(r6, r7)
            return r0
        L_0x0067:
            com.appnext.banners.SmallBannerAd r0 = new com.appnext.banners.SmallBannerAd
            r0.<init>(r6, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.a.createAd(android.content.Context, java.lang.String):com.appnext.core.Ad");
    }

    public void loadAd(BannerAdRequest bannerAdRequest) {
        if (bannerAdRequest == null) {
            throw new IllegalStateException("BannerAdRequest cannot be null.");
        } else if (getPlacementId() == null) {
            throw new IllegalStateException("Missing placement id.");
        } else if (getBannerSize() != null) {
            if (this.bannerAd == null) {
                this.bannerAd = (BannerAd) createAd(this.context, getPlacementId());
            }
            this.bannerAd.setCategories(bannerAdRequest.getCategories());
            this.bannerAd.setPostback(bannerAdRequest.getPostback());
            this.adRequest = new BannerAdRequest(bannerAdRequest);
            setClickEnabled(bannerAdRequest.isClickEnabled());
            this.loaded = false;
            this.reportedImpression = false;
            if (f.Z(f.o(this.context)) == 0) {
                this.adRequest.setCreativeType("static");
            }
            d.S().a(this.context, (p.a) new p.a() {
                public final void b(HashMap<String, Object> hashMap) {
                    a.this.load();
                }

                public final void error(String str) {
                    a.this.load();
                }
            });
        } else {
            throw new IllegalStateException("Missing banner size.");
        }
    }

    /* access modifiers changed from: private */
    public void load() {
        if (this.adRequest != null) {
            j.bj().b(Integer.parseInt(d.S().get("banner_expiration_time")));
            b.R().a(this.context, (Ad) this.bannerAd, getPlacementId(), (d.a) new d.a() {
                /* JADX WARNING: Removed duplicated region for block: B:38:0x0123  */
                /* JADX WARNING: Removed duplicated region for block: B:43:0x0130  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final <T> void a(T r13) {
                    /*
                        r12 = this;
                        com.appnext.banners.a r0 = com.appnext.banners.a.this
                        com.appnext.banners.BannerAdRequest r0 = r0.adRequest
                        if (r0 != 0) goto L_0x0009
                        return
                    L_0x0009:
                        com.appnext.banners.b r0 = com.appnext.banners.b.R()
                        com.appnext.banners.a r1 = com.appnext.banners.a.this
                        android.content.Context r1 = r1.context
                        com.appnext.banners.a r2 = com.appnext.banners.a.this
                        com.appnext.banners.BannerAd r2 = r2.bannerAd
                        r3 = r13
                        java.util.ArrayList r3 = (java.util.ArrayList) r3
                        com.appnext.banners.a r4 = com.appnext.banners.a.this
                        com.appnext.banners.BannerAdRequest r4 = r4.adRequest
                        java.lang.String r4 = r4.getCreativeType()
                        com.appnext.core.AppnextAd r0 = r0.a((android.content.Context) r1, (com.appnext.core.Ad) r2, (java.util.ArrayList<?>) r3, (java.lang.String) r4)
                        if (r0 != 0) goto L_0x004a
                        com.appnext.banners.a r13 = com.appnext.banners.a.this
                        java.lang.String r0 = "error_no_ads"
                        r13.report(r0)
                        com.appnext.banners.a r13 = com.appnext.banners.a.this
                        com.appnext.banners.BannerListener r13 = r13.getBannerListener()
                        if (r13 == 0) goto L_0x0049
                        com.appnext.banners.a r13 = com.appnext.banners.a.this
                        com.appnext.banners.BannerListener r13 = r13.getBannerListener()
                        com.appnext.core.AppnextError r0 = new com.appnext.core.AppnextError
                        java.lang.String r1 = "No Ads"
                        r0.<init>(r1)
                        r13.onError(r0)
                    L_0x0049:
                        return
                    L_0x004a:
                        com.appnext.banners.a r1 = com.appnext.banners.a.this
                        android.view.ViewGroup r1 = r1.rootView
                        if (r1 != 0) goto L_0x0051
                        return
                    L_0x0051:
                        com.appnext.banners.a r1 = com.appnext.banners.a.this
                        android.content.Context r1 = r1.context
                        if (r1 != 0) goto L_0x005f
                        com.appnext.banners.a r13 = com.appnext.banners.a.this
                        android.view.ViewGroup r13 = r13.rootView
                        r13.removeAllViews()
                        return
                    L_0x005f:
                        com.appnext.banners.a r1 = com.appnext.banners.a.this     // Catch:{ all -> 0x0086 }
                        java.util.ArrayList r1 = r1.ads     // Catch:{ all -> 0x0086 }
                        if (r1 != 0) goto L_0x0071
                        com.appnext.banners.a r1 = com.appnext.banners.a.this     // Catch:{ all -> 0x0086 }
                        java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0086 }
                        r2.<init>()     // Catch:{ all -> 0x0086 }
                        java.util.ArrayList unused = r1.ads = r2     // Catch:{ all -> 0x0086 }
                    L_0x0071:
                        com.appnext.banners.a r1 = com.appnext.banners.a.this     // Catch:{ all -> 0x0086 }
                        java.util.ArrayList r1 = r1.ads     // Catch:{ all -> 0x0086 }
                        r1.clear()     // Catch:{ all -> 0x0086 }
                        com.appnext.banners.a r1 = com.appnext.banners.a.this     // Catch:{ all -> 0x0086 }
                        java.util.ArrayList r1 = r1.ads     // Catch:{ all -> 0x0086 }
                        java.util.ArrayList r13 = (java.util.ArrayList) r13     // Catch:{ all -> 0x0086 }
                        r1.addAll(r13)     // Catch:{ all -> 0x0086 }
                        goto L_0x0087
                    L_0x0086:
                    L_0x0087:
                        com.appnext.banners.a r13 = com.appnext.banners.a.this
                        com.appnext.banners.BannerAdData r1 = new com.appnext.banners.BannerAdData
                        r1.<init>(r0)
                        com.appnext.banners.BannerAdData unused = r13.currentAd = r1
                        com.appnext.banners.d r13 = com.appnext.banners.d.S()
                        com.appnext.banners.a r1 = com.appnext.banners.a.this
                        com.appnext.banners.BannerSize r1 = r1.getBannerSize()
                        java.lang.String r1 = r1.toString()
                        java.lang.String r13 = r13.get(r1)
                        java.lang.String r13 = com.appnext.banners.j.a(r13)
                        com.appnext.banners.a r1 = com.appnext.banners.a.this
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder
                        java.lang.String r3 = "apnxt_"
                        r2.<init>(r3)
                        r2.append(r13)
                        java.lang.String r13 = r2.toString()
                        java.lang.String r13 = r13.toLowerCase()
                        java.lang.String unused = r1.template = r13
                        com.appnext.banners.a r13 = com.appnext.banners.a.this
                        android.view.ViewGroup r13 = r13.rootView
                        android.content.Context r13 = r13.getContext()
                        android.content.res.Resources r13 = r13.getResources()
                        com.appnext.banners.a r1 = com.appnext.banners.a.this
                        java.lang.String r1 = r1.template
                        com.appnext.banners.a r2 = com.appnext.banners.a.this
                        android.content.Context r2 = r2.context
                        java.lang.String r2 = r2.getPackageName()
                        java.lang.String r3 = "layout"
                        int r13 = r13.getIdentifier(r1, r3, r2)
                        java.lang.String r1 = "BANNER"
                        java.lang.String r2 = "MEDIUM_RECTANGLE"
                        java.lang.String r3 = "LARGE_BANNER"
                        r4 = 1951953708(0x7458732c, float:6.859571E31)
                        r5 = -96588539(0xfffffffffa3e2d05, float:-2.4686238E35)
                        r6 = -1966536496(0xffffffff8ac908d0, float:-1.9358911E-32)
                        r7 = -1
                        r8 = 0
                        r9 = 2
                        r10 = 1
                        if (r13 != 0) goto L_0x0132
                        com.appnext.banners.a r13 = com.appnext.banners.a.this
                        com.appnext.banners.BannerSize r13 = r13.getBannerSize()
                        java.lang.String r13 = r13.toString()
                        int r11 = r13.hashCode()
                        if (r11 == r6) goto L_0x0118
                        if (r11 == r5) goto L_0x0110
                        if (r11 == r4) goto L_0x0108
                        goto L_0x0120
                    L_0x0108:
                        boolean r13 = r13.equals(r1)
                        if (r13 == 0) goto L_0x0120
                        r13 = 0
                        goto L_0x0121
                    L_0x0110:
                        boolean r13 = r13.equals(r2)
                        if (r13 == 0) goto L_0x0120
                        r13 = 2
                        goto L_0x0121
                    L_0x0118:
                        boolean r13 = r13.equals(r3)
                        if (r13 == 0) goto L_0x0120
                        r13 = 1
                        goto L_0x0121
                    L_0x0120:
                        r13 = -1
                    L_0x0121:
                        if (r13 == 0) goto L_0x0130
                        if (r13 == r10) goto L_0x012d
                        if (r13 == r9) goto L_0x012a
                        int r13 = com.appnext.banners.R.layout.apnxt_banner_1
                        goto L_0x0132
                    L_0x012a:
                        int r13 = com.appnext.banners.R.layout.apnxt_medium_rectangle_1
                        goto L_0x0132
                    L_0x012d:
                        int r13 = com.appnext.banners.R.layout.apnxt_large_banner_1
                        goto L_0x0132
                    L_0x0130:
                        int r13 = com.appnext.banners.R.layout.apnxt_banner_1
                    L_0x0132:
                        com.appnext.banners.a r11 = com.appnext.banners.a.this
                        boolean unused = r11.loaded = r10
                        com.appnext.banners.a r11 = com.appnext.banners.a.this
                        boolean unused = r11.reportedImpression = r8
                        com.appnext.banners.a r11 = com.appnext.banners.a.this
                        r11.inflateView(r13, r0)
                        com.appnext.banners.a r13 = com.appnext.banners.a.this
                        com.appnext.banners.BannerListener r13 = r13.getBannerListener()
                        if (r13 == 0) goto L_0x015a
                        com.appnext.banners.a r13 = com.appnext.banners.a.this
                        com.appnext.banners.BannerListener r13 = r13.getBannerListener()
                        java.lang.String r11 = r0.getBannerID()
                        com.appnext.core.AppnextAdCreativeType r0 = r0.getCreativeType()
                        r13.onAdLoaded(r11, r0)
                    L_0x015a:
                        com.appnext.banners.a r13 = com.appnext.banners.a.this
                        com.appnext.banners.BannerSize r13 = r13.getBannerSize()
                        java.lang.String r13 = r13.toString()
                        int r0 = r13.hashCode()
                        if (r0 == r6) goto L_0x017f
                        if (r0 == r5) goto L_0x0177
                        if (r0 == r4) goto L_0x016f
                        goto L_0x0186
                    L_0x016f:
                        boolean r13 = r13.equals(r1)
                        if (r13 == 0) goto L_0x0186
                        r7 = 0
                        goto L_0x0186
                    L_0x0177:
                        boolean r13 = r13.equals(r2)
                        if (r13 == 0) goto L_0x0186
                        r7 = 2
                        goto L_0x0186
                    L_0x017f:
                        boolean r13 = r13.equals(r3)
                        if (r13 == 0) goto L_0x0186
                        r7 = 1
                    L_0x0186:
                        if (r7 == 0) goto L_0x019d
                        if (r7 == r10) goto L_0x0195
                        if (r7 == r9) goto L_0x018d
                        goto L_0x0194
                    L_0x018d:
                        com.appnext.banners.a r13 = com.appnext.banners.a.this
                        java.lang.String r0 = "loaded_medium_rectangle"
                        r13.report(r0)
                    L_0x0194:
                        return
                    L_0x0195:
                        com.appnext.banners.a r13 = com.appnext.banners.a.this
                        java.lang.String r0 = "loaded_large_banner"
                        r13.report(r0)
                        return
                    L_0x019d:
                        com.appnext.banners.a r13 = com.appnext.banners.a.this
                        java.lang.String r0 = "loaded_banner"
                        r13.report(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.a.AnonymousClass18.a(java.lang.Object):void");
                }

                /* JADX WARNING: Can't fix incorrect switch cases order */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final void error(java.lang.String r7) {
                    /*
                        r6 = this;
                        int r0 = r7.hashCode()
                        r1 = 5
                        r2 = 4
                        r3 = 3
                        r4 = 2
                        r5 = 1
                        switch(r0) {
                            case -2026653947: goto L_0x003f;
                            case -1958363695: goto L_0x0035;
                            case -1477010874: goto L_0x002b;
                            case -507110949: goto L_0x0021;
                            case 350741825: goto L_0x0017;
                            case 844170097: goto L_0x000d;
                            default: goto L_0x000c;
                        }
                    L_0x000c:
                        goto L_0x0049
                    L_0x000d:
                        java.lang.String r0 = "Too Slow Connection"
                        boolean r0 = r7.equals(r0)
                        if (r0 == 0) goto L_0x0049
                        r0 = 4
                        goto L_0x004a
                    L_0x0017:
                        java.lang.String r0 = "Timeout"
                        boolean r0 = r7.equals(r0)
                        if (r0 == 0) goto L_0x0049
                        r0 = 5
                        goto L_0x004a
                    L_0x0021:
                        java.lang.String r0 = "No market installed on the device"
                        boolean r0 = r7.equals(r0)
                        if (r0 == 0) goto L_0x0049
                        r0 = 3
                        goto L_0x004a
                    L_0x002b:
                        java.lang.String r0 = "Connection Error"
                        boolean r0 = r7.equals(r0)
                        if (r0 == 0) goto L_0x0049
                        r0 = 0
                        goto L_0x004a
                    L_0x0035:
                        java.lang.String r0 = "No Ads"
                        boolean r0 = r7.equals(r0)
                        if (r0 == 0) goto L_0x0049
                        r0 = 2
                        goto L_0x004a
                    L_0x003f:
                        java.lang.String r0 = "Internal error"
                        boolean r0 = r7.equals(r0)
                        if (r0 == 0) goto L_0x0049
                        r0 = 1
                        goto L_0x004a
                    L_0x0049:
                        r0 = -1
                    L_0x004a:
                        if (r0 == 0) goto L_0x0068
                        if (r0 == r5) goto L_0x0065
                        if (r0 == r4) goto L_0x0062
                        if (r0 == r3) goto L_0x005f
                        if (r0 == r2) goto L_0x005c
                        if (r0 == r1) goto L_0x0059
                        java.lang.String r0 = ""
                        goto L_0x006a
                    L_0x0059:
                        java.lang.String r0 = "error_timeout"
                        goto L_0x006a
                    L_0x005c:
                        java.lang.String r0 = "error_slow_connection"
                        goto L_0x006a
                    L_0x005f:
                        java.lang.String r0 = "error_no_market"
                        goto L_0x006a
                    L_0x0062:
                        java.lang.String r0 = "error_no_ads"
                        goto L_0x006a
                    L_0x0065:
                        java.lang.String r0 = "error_internal_error"
                        goto L_0x006a
                    L_0x0068:
                        java.lang.String r0 = "error_connection_error"
                    L_0x006a:
                        com.appnext.banners.a r1 = com.appnext.banners.a.this
                        r1.report(r0)
                        com.appnext.banners.a r0 = com.appnext.banners.a.this
                        com.appnext.banners.BannerListener r0 = r0.getBannerListener()
                        if (r0 == 0) goto L_0x0085
                        com.appnext.banners.a r0 = com.appnext.banners.a.this
                        com.appnext.banners.BannerListener r0 = r0.getBannerListener()
                        com.appnext.core.AppnextError r1 = new com.appnext.core.AppnextError
                        r1.<init>(r7)
                        r0.onError(r1)
                    L_0x0085:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.a.AnonymousClass18.error(java.lang.String):void");
                }
            }, this.adRequest);
        }
    }

    public void getECPM(final BannerAdRequest bannerAdRequest, final OnECPMLoaded onECPMLoaded) {
        if (bannerAdRequest == null) {
            throw new IllegalStateException("BannerAdRequest cannot be null.");
        } else if (getPlacementId() == null) {
            throw new IllegalStateException("Missing placement id.");
        } else if (getBannerSize() == null) {
            throw new IllegalStateException("Missing banner size.");
        } else if (onECPMLoaded != null) {
            if (this.bannerAd == null) {
                this.bannerAd = (BannerAd) createAd(this.context, getPlacementId());
            }
            this.bannerAd.setCategories(bannerAdRequest.getCategories());
            this.bannerAd.setPostback(bannerAdRequest.getPostback());
            b.R().a(this.context, (Ad) this.bannerAd, getPlacementId(), (d.a) new d.a() {
                public final <T> void a(T t) {
                    AppnextAd a2 = b.R().a(a.this.context, (Ad) a.this.bannerAd, bannerAdRequest.getCreativeType());
                    if (a2 == null) {
                        a.this.report(com.appnext.ads.a.w);
                        OnECPMLoaded onECPMLoaded = onECPMLoaded;
                        if (onECPMLoaded != null) {
                            onECPMLoaded.error(AppnextError.NO_ADS);
                            return;
                        }
                        return;
                    }
                    OnECPMLoaded onECPMLoaded2 = onECPMLoaded;
                    if (onECPMLoaded2 != null) {
                        onECPMLoaded2.ecpm(new ECPM(a2.getECPM(), a2.getPPR(), a2.getBannerID()));
                    }
                }

                public final void error(String str) {
                    OnECPMLoaded onECPMLoaded = onECPMLoaded;
                    if (onECPMLoaded != null) {
                        onECPMLoaded.error(str);
                    }
                }
            }, bannerAdRequest);
        } else {
            throw new IllegalStateException("callback cannot be null.");
        }
    }

    /* access modifiers changed from: protected */
    public void inflateView(int i, final AppnextAd appnextAd) {
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(i, this.rootView, false);
        inflate.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                a.this.report(com.appnext.ads.a.ab);
                a.this.click();
            }
        });
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.icon);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    a.this.report(com.appnext.ads.a.Z);
                    a.this.click();
                }
            });
            new Thread(new Runnable() {
                public final void run() {
                    final Bitmap Y = f.Y(appnextAd.getImageURL());
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            imageView.setImageBitmap(Y);
                        }
                    });
                }
            }).start();
        }
        TextView textView = (TextView) inflate.findViewById(R.id.title);
        if (textView != null) {
            textView.setText(appnextAd.getAdTitle());
            textView.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    a.this.report(com.appnext.ads.a.ab);
                    a.this.click();
                }
            });
        }
        RatingBar ratingBar = (RatingBar) inflate.findViewById(R.id.ratingBar);
        if (ratingBar != null) {
            try {
                ratingBar.setRating(Float.parseFloat(appnextAd.getStoreRating()));
            } catch (Throwable unused) {
                ratingBar.setVisibility(4);
            }
            ratingBar.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    a.this.report(com.appnext.ads.a.ab);
                    a.this.click();
                }
            });
        }
        TextView textView2 = (TextView) inflate.findViewById(R.id.description);
        if (textView2 != null) {
            textView2.setText(appnextAd.getAdDescription());
            textView2.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    a.this.report(com.appnext.ads.a.ab);
                    a.this.click();
                }
            });
        }
        View findViewById = inflate.findViewById(R.id.install);
        ((TextView) findViewById).setText(getButtonText(appnextAd));
        findViewById.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                a.this.report(com.appnext.ads.a.aa);
                a.this.click();
            }
        });
        View findViewById2 = inflate.findViewById(R.id.media);
        if (findViewById2 != null) {
            if (getCreativeType(appnextAd) != 0) {
                createWideImage((ImageView) inflate.findViewById(R.id.wide_image));
            } else {
                createVideo((ViewGroup) findViewById2);
            }
        }
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.privacy);
        imageView2.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                a.this.openLink(f.g(appnextAd));
            }
        });
        if (k.a(appnextAd, (p) d.S())) {
            k.a(this.context, imageView2);
        }
        if (this.rootView.getChildCount() > 0) {
            this.rootView.removeViewAt(0);
        }
        this.rootView.addView(inflate);
    }

    /* access modifiers changed from: protected */
    public String getButtonText(AppnextAd appnextAd) {
        String buttonText = new BannerAdData(appnextAd).getButtonText();
        boolean c = f.c(this.context, getSelectedAd().getAdPackage());
        String str = b.hY;
        if (appnextAd == null || !buttonText.equals("")) {
            b bp = b.bp();
            String language = getLanguage();
            if (!c) {
                str = b.hX;
            }
            return bp.b(language, str, buttonText);
        } else if (c) {
            return b.bp().b(getLanguage(), str, d.S().get("existing_button_text"));
        } else {
            return b.bp().b(getLanguage(), b.hX, d.S().get("new_button_text"));
        }
    }

    /* access modifiers changed from: protected */
    public int getCreativeType(AppnextAd appnextAd) {
        return this.adRequest.getCreativeType().equals(BannerAdRequest.TYPE_ALL) ? b.hasVideo(appnextAd) ? 0 : 1 : (!this.adRequest.getCreativeType().equals("video") || !b.hasVideo(appnextAd)) ? 1 : 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        if (getSelectedAd() == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
        r4.mHandler.postDelayed(new com.appnext.banners.a.AnonymousClass5(r4), (long) (java.lang.Integer.parseInt(com.appnext.banners.d.S().get("postpone_impression_sec")) * 1000));
        report(com.appnext.ads.a.G);
        com.appnext.core.j.bj().n(getSelectedAd().getBannerID(), getPlacementId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0063, code lost:
        if (java.lang.Boolean.parseBoolean(com.appnext.banners.d.S().get("pview")) == false) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0065, code lost:
        r4.mHandler.postDelayed(new com.appnext.banners.a.AnonymousClass6(r4), (long) (java.lang.Integer.parseInt(com.appnext.banners.d.S().get("postpone_vta_sec")) * 1000));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0084, code lost:
        if (getBannerListener() == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0086, code lost:
        getBannerListener().adImpression();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void impression() {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.loaded     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x008e
            boolean r0 = r4.reportedImpression     // Catch:{ all -> 0x0090 }
            if (r0 != 0) goto L_0x008e
            android.view.ViewGroup r0 = r4.rootView     // Catch:{ all -> 0x0090 }
            int r0 = r4.getVisiblePercent(r0)     // Catch:{ all -> 0x0090 }
            r1 = 50
            if (r0 < r1) goto L_0x008e
            com.appnext.core.q r0 = r4.userAction     // Catch:{ all -> 0x0090 }
            if (r0 != 0) goto L_0x0018
            goto L_0x008e
        L_0x0018:
            r0 = 1
            r4.reportedImpression = r0     // Catch:{ all -> 0x0090 }
            monitor-exit(r4)     // Catch:{ all -> 0x0090 }
            com.appnext.banners.BannerAdData r0 = r4.getSelectedAd()
            if (r0 == 0) goto L_0x008d
            android.os.Handler r0 = r4.mHandler
            com.appnext.banners.a$5 r1 = new com.appnext.banners.a$5
            r1.<init>()
            com.appnext.banners.d r2 = com.appnext.banners.d.S()
            java.lang.String r3 = "postpone_impression_sec"
            java.lang.String r2 = r2.get(r3)
            int r2 = java.lang.Integer.parseInt(r2)
            int r2 = r2 * 1000
            long r2 = (long) r2
            r0.postDelayed(r1, r2)
            java.lang.String r0 = "impression_event"
            r4.report(r0)
            com.appnext.core.j r0 = com.appnext.core.j.bj()
            com.appnext.banners.BannerAdData r1 = r4.getSelectedAd()
            java.lang.String r1 = r1.getBannerID()
            java.lang.String r2 = r4.getPlacementId()
            r0.n(r1, r2)
            com.appnext.banners.d r0 = com.appnext.banners.d.S()
            java.lang.String r1 = "pview"
            java.lang.String r0 = r0.get(r1)
            boolean r0 = java.lang.Boolean.parseBoolean(r0)
            if (r0 == 0) goto L_0x0080
            android.os.Handler r0 = r4.mHandler
            com.appnext.banners.a$6 r1 = new com.appnext.banners.a$6
            r1.<init>()
            com.appnext.banners.d r2 = com.appnext.banners.d.S()
            java.lang.String r3 = "postpone_vta_sec"
            java.lang.String r2 = r2.get(r3)
            int r2 = java.lang.Integer.parseInt(r2)
            int r2 = r2 * 1000
            long r2 = (long) r2
            r0.postDelayed(r1, r2)
        L_0x0080:
            com.appnext.banners.BannerListener r0 = r4.getBannerListener()
            if (r0 == 0) goto L_0x008d
            com.appnext.banners.BannerListener r0 = r4.getBannerListener()
            r0.adImpression()
        L_0x008d:
            return
        L_0x008e:
            monitor-exit(r4)     // Catch:{ all -> 0x0090 }
            return
        L_0x0090:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0090 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.a.impression():void");
    }

    public void click() {
        report(com.appnext.ads.a.V);
        if (getBannerListener() != null) {
            getBannerListener().onAdClicked();
        }
        this.userAction.a(getSelectedAd(), getSelectedAd().getAppURL(), getPlacementId(), new e.a() {
            public final void error(String str) {
            }

            public final void onMarket(String str) {
                try {
                    if (a.this.mediaPlayer != null && a.this.mediaPlayer.isPlaying()) {
                        a.this.pause();
                        ((ImageView) a.this.rootView.findViewById(R.id.media).findViewById(R.id.play)).setImageResource(R.drawable.apnxt_banner_pause);
                        a.this.rootView.findViewById(R.id.media).findViewById(R.id.play).setVisibility(0);
                    }
                } catch (Throwable unused) {
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0036 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void openLink(java.lang.String r3) {
        /*
            r2 = this;
            android.media.MediaPlayer r0 = r2.mediaPlayer     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x0036
            android.media.MediaPlayer r0 = r2.mediaPlayer     // Catch:{ all -> 0x0036 }
            boolean r0 = r0.isPlaying()     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x0036
            r2.pause()     // Catch:{ all -> 0x0036 }
            android.view.ViewGroup r0 = r2.rootView     // Catch:{ all -> 0x0036 }
            int r1 = com.appnext.banners.R.id.media     // Catch:{ all -> 0x0036 }
            android.view.View r0 = r0.findViewById(r1)     // Catch:{ all -> 0x0036 }
            int r1 = com.appnext.banners.R.id.play     // Catch:{ all -> 0x0036 }
            android.view.View r0 = r0.findViewById(r1)     // Catch:{ all -> 0x0036 }
            android.widget.ImageView r0 = (android.widget.ImageView) r0     // Catch:{ all -> 0x0036 }
            int r1 = com.appnext.banners.R.drawable.apnxt_banner_pause     // Catch:{ all -> 0x0036 }
            r0.setImageResource(r1)     // Catch:{ all -> 0x0036 }
            android.view.ViewGroup r0 = r2.rootView     // Catch:{ all -> 0x0036 }
            int r1 = com.appnext.banners.R.id.media     // Catch:{ all -> 0x0036 }
            android.view.View r0 = r0.findViewById(r1)     // Catch:{ all -> 0x0036 }
            int r1 = com.appnext.banners.R.id.play     // Catch:{ all -> 0x0036 }
            android.view.View r0 = r0.findViewById(r1)     // Catch:{ all -> 0x0036 }
            r1 = 0
            r0.setVisibility(r1)     // Catch:{ all -> 0x0036 }
        L_0x0036:
            super.openLink(r3)     // Catch:{ all -> 0x003a }
            return
        L_0x003a:
            java.lang.String r3 = "error_no_market"
            r2.report(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.a.openLink(java.lang.String):void");
    }

    private void createWideImage(final ImageView imageView) {
        report(com.appnext.ads.a.ac);
        new Thread(new Runnable() {
            public final void run() {
                final Bitmap Y = f.Y(a.this.getSelectedAd().getWideImageURL());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public final void run() {
                        imageView.setImageBitmap(Y);
                        imageView.setVisibility(0);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            public final void onClick(View view) {
                                a.this.report(com.appnext.ads.a.ab);
                                a.this.click();
                            }
                        });
                    }
                });
            }
        }).start();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0068 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createVideo(final android.view.ViewGroup r6) {
        /*
            r5 = this;
            com.appnext.banners.BannerAdRequest r0 = r5.adRequest     // Catch:{ all -> 0x00f1 }
            boolean r0 = r0.isAutoPlay()     // Catch:{ all -> 0x00f1 }
            if (r0 == 0) goto L_0x000e
            java.lang.String r0 = "auto_play_on"
            r5.report(r0)     // Catch:{ all -> 0x00f1 }
            goto L_0x0013
        L_0x000e:
            java.lang.String r0 = "auto_play_off"
            r5.report(r0)     // Catch:{ all -> 0x00f1 }
        L_0x0013:
            com.appnext.banners.BannerAdRequest r0 = r5.adRequest     // Catch:{ all -> 0x00f1 }
            boolean r0 = r0.isMute()     // Catch:{ all -> 0x00f1 }
            if (r0 == 0) goto L_0x0021
            java.lang.String r0 = "mute_on"
            r5.report(r0)     // Catch:{ all -> 0x00f1 }
            goto L_0x0026
        L_0x0021:
            java.lang.String r0 = "mute_off"
            r5.report(r0)     // Catch:{ all -> 0x00f1 }
        L_0x0026:
            com.appnext.banners.BannerAdRequest r0 = r5.adRequest     // Catch:{ all -> 0x00f1 }
            boolean r0 = r0.isMute()     // Catch:{ all -> 0x00f1 }
            r5.userMute = r0     // Catch:{ all -> 0x00f1 }
            int r0 = com.appnext.banners.R.id.mute     // Catch:{ all -> 0x00f1 }
            android.view.View r0 = r6.findViewById(r0)     // Catch:{ all -> 0x00f1 }
            android.widget.ImageView r0 = (android.widget.ImageView) r0     // Catch:{ all -> 0x00f1 }
            boolean r1 = r5.userMute     // Catch:{ all -> 0x00f1 }
            if (r1 == 0) goto L_0x003d
            int r1 = com.appnext.banners.R.drawable.apnxt_banner_unmute     // Catch:{ all -> 0x00f1 }
            goto L_0x003f
        L_0x003d:
            int r1 = com.appnext.banners.R.drawable.apnxt_banner_mute     // Catch:{ all -> 0x00f1 }
        L_0x003f:
            r0.setImageResource(r1)     // Catch:{ all -> 0x00f1 }
            int r0 = com.appnext.banners.R.id.mute     // Catch:{ all -> 0x00f1 }
            android.view.View r0 = r6.findViewById(r0)     // Catch:{ all -> 0x00f1 }
            r1 = 0
            r0.setVisibility(r1)     // Catch:{ all -> 0x00f1 }
            int r0 = com.appnext.banners.R.id.mute     // Catch:{ all -> 0x00f1 }
            android.view.View r0 = r6.findViewById(r0)     // Catch:{ all -> 0x00f1 }
            com.appnext.banners.a$9 r2 = new com.appnext.banners.a$9     // Catch:{ all -> 0x00f1 }
            r2.<init>(r6)     // Catch:{ all -> 0x00f1 }
            r0.setOnClickListener(r2)     // Catch:{ all -> 0x00f1 }
            android.widget.VideoView r0 = new android.widget.VideoView     // Catch:{ all -> 0x0068 }
            android.content.Context r2 = r5.context     // Catch:{ all -> 0x0068 }
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x0068 }
            r0.<init>(r2)     // Catch:{ all -> 0x0068 }
            r5.videoView = r0     // Catch:{ all -> 0x0068 }
            goto L_0x0071
        L_0x0068:
            android.widget.VideoView r0 = new android.widget.VideoView     // Catch:{ all -> 0x00f1 }
            android.content.Context r2 = r5.context     // Catch:{ all -> 0x00f1 }
            r0.<init>(r2)     // Catch:{ all -> 0x00f1 }
            r5.videoView = r0     // Catch:{ all -> 0x00f1 }
        L_0x0071:
            android.widget.VideoView r0 = r5.videoView     // Catch:{ all -> 0x00f1 }
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams     // Catch:{ all -> 0x00f1 }
            r3 = -1
            r4 = -2
            r2.<init>(r3, r4)     // Catch:{ all -> 0x00f1 }
            r0.setLayoutParams(r2)     // Catch:{ all -> 0x00f1 }
            android.widget.VideoView r0 = r5.videoView     // Catch:{ all -> 0x00f1 }
            r6.addView(r0, r1)     // Catch:{ all -> 0x00f1 }
            int r0 = com.appnext.banners.R.id.click     // Catch:{ all -> 0x00f1 }
            android.view.View r0 = r6.findViewById(r0)     // Catch:{ all -> 0x00f1 }
            com.appnext.banners.a$10 r2 = new com.appnext.banners.a$10     // Catch:{ all -> 0x00f1 }
            r2.<init>(r6)     // Catch:{ all -> 0x00f1 }
            r0.setOnClickListener(r2)     // Catch:{ all -> 0x00f1 }
            android.widget.VideoView r0 = r5.videoView     // Catch:{ all -> 0x00f1 }
            com.appnext.banners.a$11 r2 = new com.appnext.banners.a$11     // Catch:{ all -> 0x00f1 }
            r2.<init>()     // Catch:{ all -> 0x00f1 }
            r0.setOnPreparedListener(r2)     // Catch:{ all -> 0x00f1 }
            android.widget.VideoView r0 = r5.videoView     // Catch:{ all -> 0x00f1 }
            com.appnext.banners.a$13 r2 = new com.appnext.banners.a$13     // Catch:{ all -> 0x00f1 }
            r2.<init>()     // Catch:{ all -> 0x00f1 }
            r0.setOnCompletionListener(r2)     // Catch:{ all -> 0x00f1 }
            android.widget.VideoView r0 = r5.videoView     // Catch:{ all -> 0x00f1 }
            com.appnext.banners.a$14 r2 = new com.appnext.banners.a$14     // Catch:{ all -> 0x00f1 }
            r2.<init>()     // Catch:{ all -> 0x00f1 }
            r0.setOnErrorListener(r2)     // Catch:{ all -> 0x00f1 }
            android.widget.VideoView r0 = r5.videoView     // Catch:{ all -> 0x00f1 }
            com.appnext.banners.BannerAdData r2 = r5.getSelectedAd()     // Catch:{ all -> 0x00f1 }
            com.appnext.banners.BannerAdRequest r3 = r5.adRequest     // Catch:{ all -> 0x00f1 }
            java.lang.String r3 = r3.getVideoLength()     // Catch:{ all -> 0x00f1 }
            java.lang.String r2 = r5.getVideoUrl(r2, r3)     // Catch:{ all -> 0x00f1 }
            android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch:{ all -> 0x00f1 }
            r0.setVideoURI(r2)     // Catch:{ all -> 0x00f1 }
            int r0 = com.appnext.banners.R.id.play     // Catch:{ all -> 0x00f1 }
            android.view.View r0 = r6.findViewById(r0)     // Catch:{ all -> 0x00f1 }
            com.appnext.banners.a$15 r2 = new com.appnext.banners.a$15     // Catch:{ all -> 0x00f1 }
            r2.<init>(r6)     // Catch:{ all -> 0x00f1 }
            r0.setOnClickListener(r2)     // Catch:{ all -> 0x00f1 }
            com.appnext.banners.BannerAdRequest r0 = r5.adRequest     // Catch:{ all -> 0x00f1 }
            boolean r0 = r0.isAutoPlay()     // Catch:{ all -> 0x00f1 }
            if (r0 != 0) goto L_0x00f1
            int r0 = com.appnext.banners.R.id.play     // Catch:{ all -> 0x00f1 }
            android.view.View r0 = r6.findViewById(r0)     // Catch:{ all -> 0x00f1 }
            r0.setVisibility(r1)     // Catch:{ all -> 0x00f1 }
            java.lang.Thread r0 = new java.lang.Thread     // Catch:{ all -> 0x00f1 }
            com.appnext.banners.a$16 r1 = new com.appnext.banners.a$16     // Catch:{ all -> 0x00f1 }
            r1.<init>(r6)     // Catch:{ all -> 0x00f1 }
            r0.<init>(r1)     // Catch:{ all -> 0x00f1 }
            r0.start()     // Catch:{ all -> 0x00f1 }
        L_0x00f1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.a.createVideo(android.view.ViewGroup):void");
    }

    /* access modifiers changed from: protected */
    public String getVideoUrl(AppnextAd appnextAd, String str) {
        if (str.equals("30")) {
            String videoUrl30Sec = appnextAd.getVideoUrl30Sec();
            if (videoUrl30Sec.equals("")) {
                videoUrl30Sec = appnextAd.getVideoUrl();
            }
            if (videoUrl30Sec.equals("")) {
                videoUrl30Sec = appnextAd.getVideoUrlHigh30Sec();
            }
            if (videoUrl30Sec.equals("")) {
                return appnextAd.getVideoUrlHigh();
            }
            return videoUrl30Sec;
        }
        String videoUrl = appnextAd.getVideoUrl();
        if (videoUrl.equals("")) {
            videoUrl = appnextAd.getVideoUrl30Sec();
        }
        if (videoUrl.equals("")) {
            videoUrl = appnextAd.getVideoUrlHigh();
        }
        return videoUrl.equals("") ? appnextAd.getVideoUrlHigh30Sec() : videoUrl;
    }

    /* access modifiers changed from: private */
    public void checkProgress() {
        try {
            if (this.mediaPlayer != null) {
                int currentPosition2 = (int) ((((float) this.mediaPlayer.getCurrentPosition()) / ((float) this.mediaPlayer.getDuration())) * 100.0f);
                if (currentPosition2 > 25 && this.lastProgress == 0) {
                    this.lastProgress = 25;
                    report(com.appnext.ads.a.J);
                } else if (currentPosition2 > 50 && this.lastProgress == 25) {
                    this.lastProgress = 50;
                    report(com.appnext.ads.a.K);
                } else if (currentPosition2 > 75 && this.lastProgress == 50) {
                    this.lastProgress = 75;
                    report(com.appnext.ads.a.L);
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0026 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x006f */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0073 A[Catch:{ all -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onScrollChanged(int r4) {
        /*
            r3 = this;
            android.media.MediaPlayer r0 = r3.mediaPlayer     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x007b
            boolean r0 = r3.finished     // Catch:{ all -> 0x007b }
            if (r0 != 0) goto L_0x007b
            android.media.MediaPlayer r0 = r3.mediaPlayer     // Catch:{ all -> 0x007b }
            boolean r0 = r0.isPlaying()     // Catch:{ all -> 0x007b }
            r1 = 90
            if (r0 == 0) goto L_0x0029
            if (r4 >= r1) goto L_0x0029
            android.view.ViewGroup r0 = r3.rootView     // Catch:{ all -> 0x0026 }
            int r2 = com.appnext.banners.R.id.media     // Catch:{ all -> 0x0026 }
            android.view.View r0 = r0.findViewById(r2)     // Catch:{ all -> 0x0026 }
            int r2 = com.appnext.banners.R.id.play     // Catch:{ all -> 0x0026 }
            android.view.View r0 = r0.findViewById(r2)     // Catch:{ all -> 0x0026 }
            r2 = 0
            r0.setVisibility(r2)     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r3.pause()     // Catch:{ all -> 0x007b }
        L_0x0029:
            android.media.MediaPlayer r0 = r3.mediaPlayer     // Catch:{ all -> 0x007b }
            boolean r0 = r0.isPlaying()     // Catch:{ all -> 0x007b }
            if (r0 != 0) goto L_0x007b
            if (r4 <= r1) goto L_0x007b
            com.appnext.banners.BannerAdRequest r4 = r3.adRequest     // Catch:{ all -> 0x007b }
            boolean r4 = r4.isAutoPlay()     // Catch:{ all -> 0x007b }
            if (r4 == 0) goto L_0x007b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x007b }
            java.lang.String r0 = "start. "
            r4.<init>(r0)     // Catch:{ all -> 0x007b }
            android.media.MediaPlayer r0 = r3.mediaPlayer     // Catch:{ all -> 0x007b }
            int r0 = r0.getCurrentPosition()     // Catch:{ all -> 0x007b }
            r4.append(r0)     // Catch:{ all -> 0x007b }
            java.lang.String r0 = "/"
            r4.append(r0)     // Catch:{ all -> 0x007b }
            android.media.MediaPlayer r0 = r3.mediaPlayer     // Catch:{ all -> 0x007b }
            int r0 = r0.getDuration()     // Catch:{ all -> 0x007b }
            r4.append(r0)     // Catch:{ all -> 0x007b }
            r3.play()     // Catch:{ all -> 0x007b }
            android.view.ViewGroup r4 = r3.rootView     // Catch:{ all -> 0x006f }
            int r0 = com.appnext.banners.R.id.media     // Catch:{ all -> 0x006f }
            android.view.View r4 = r4.findViewById(r0)     // Catch:{ all -> 0x006f }
            int r0 = com.appnext.banners.R.id.play     // Catch:{ all -> 0x006f }
            android.view.View r4 = r4.findViewById(r0)     // Catch:{ all -> 0x006f }
            r0 = 8
            r4.setVisibility(r0)     // Catch:{ all -> 0x006f }
        L_0x006f:
            boolean r4 = r3.started     // Catch:{ all -> 0x007b }
            if (r4 != 0) goto L_0x007b
            java.lang.String r4 = "video_started"
            r3.report(r4)     // Catch:{ all -> 0x007b }
            r4 = 1
            r3.started = r4     // Catch:{ all -> 0x007b }
        L_0x007b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.a.onScrollChanged(int):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|5|6|(1:8)|9|10|(1:12)|13|14|15|16|(3:18|19|21)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0037 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002a */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002e A[Catch:{ all -> 0x0037 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void destroy() {
        /*
            r3 = this;
            super.destroy()
            com.appnext.core.q r0 = r3.userAction     // Catch:{ all -> 0x0008 }
            r0.destroy()     // Catch:{ all -> 0x0008 }
        L_0x0008:
            r0 = 0
            android.widget.VideoView r1 = r3.videoView     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x002a
            android.widget.VideoView r1 = r3.videoView     // Catch:{ all -> 0x002a }
            r1.setOnCompletionListener(r0)     // Catch:{ all -> 0x002a }
            android.widget.VideoView r1 = r3.videoView     // Catch:{ all -> 0x002a }
            r1.setOnErrorListener(r0)     // Catch:{ all -> 0x002a }
            android.widget.VideoView r1 = r3.videoView     // Catch:{ all -> 0x002a }
            r1.setOnPreparedListener(r0)     // Catch:{ all -> 0x002a }
            android.widget.VideoView r1 = r3.videoView     // Catch:{ all -> 0x002a }
            r1.suspend()     // Catch:{ all -> 0x002a }
            r3.videoView = r0     // Catch:{ all -> 0x002a }
            android.media.MediaPlayer r1 = r3.mediaPlayer     // Catch:{ all -> 0x002a }
            r1.release()     // Catch:{ all -> 0x002a }
            r3.mediaPlayer = r0     // Catch:{ all -> 0x002a }
        L_0x002a:
            com.appnext.banners.i r1 = r3.serviceHelper     // Catch:{ all -> 0x0037 }
            if (r1 == 0) goto L_0x0035
            com.appnext.banners.i r1 = r3.serviceHelper     // Catch:{ all -> 0x0037 }
            android.content.Context r2 = r3.context     // Catch:{ all -> 0x0037 }
            r1.a((android.content.Context) r2)     // Catch:{ all -> 0x0037 }
        L_0x0035:
            r3.serviceHelper = r0     // Catch:{ all -> 0x0037 }
        L_0x0037:
            com.appnext.banners.BannerAd r1 = r3.bannerAd     // Catch:{ all -> 0x003e }
            r1.destroy()     // Catch:{ all -> 0x003e }
            r3.bannerAd = r0     // Catch:{ all -> 0x003e }
        L_0x003e:
            r3.adRequest = r0
            android.os.Handler r1 = r3.mHandler     // Catch:{ all -> 0x0045 }
            r1.removeCallbacksAndMessages(r0)     // Catch:{ all -> 0x0045 }
        L_0x0045:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.a.destroy():void");
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        destroy();
    }

    public void play() {
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 != null && !mediaPlayer2.isPlaying()) {
            this.mediaPlayer.start();
        }
    }

    public void pause() {
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 != null && mediaPlayer2.isPlaying()) {
            this.mediaPlayer.pause();
        }
    }

    /* access modifiers changed from: private */
    public void report(String str) {
        try {
            if (this.bannerAd != null) {
                String tid = this.bannerAd.getTID();
                String vid = this.bannerAd.getVID();
                String auid = this.bannerAd.getAUID();
                String str2 = "";
                String placementId = getPlacementId() == null ? str2 : getPlacementId();
                String sessionId = this.bannerAd.getSessionId();
                String str3 = this.template;
                String bannerID = getSelectedAd() != null ? getSelectedAd().getBannerID() : str2;
                if (getSelectedAd() != null) {
                    str2 = getSelectedAd().getCampaignID();
                }
                f.a(tid, vid, auid, placementId, sessionId, str, str3, bannerID, str2);
            }
        } catch (Throwable unused) {
        }
    }

    public boolean isClickEnabled() {
        return this.clickEnabled;
    }

    public void setClickEnabled(boolean z) {
        this.clickEnabled = z;
    }

    /* access modifiers changed from: protected */
    public BannerAd getBannerAd() {
        return this.bannerAd;
    }

    /* access modifiers changed from: protected */
    public BannerAdData getSelectedAd() {
        return this.currentAd;
    }

    /* access modifiers changed from: protected */
    public void setSelectedAd(BannerAdData bannerAdData) {
        this.currentAd = bannerAdData;
    }

    /* access modifiers changed from: protected */
    public BannerAdRequest getAdRequest() {
        return this.adRequest;
    }

    /* access modifiers changed from: protected */
    public void setReportedImpression(boolean z) {
        this.reportedImpression = z;
    }

    /* access modifiers changed from: protected */
    public boolean isReportedImpression() {
        return this.reportedImpression;
    }

    /* access modifiers changed from: protected */
    public q getUserAction() {
        return this.userAction;
    }

    /* access modifiers changed from: protected */
    public ArrayList<AppnextAd> getAds() {
        return this.ads;
    }
}
