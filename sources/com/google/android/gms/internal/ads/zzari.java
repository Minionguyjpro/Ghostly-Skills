package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.Predicate;
import com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
final class zzari extends WebView implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzaqw {
    private zzamt zzaee;
    private final WindowManager zzaeu;
    private final DisplayMetrics zzagj;
    private final zzci zzbjc;
    private int zzbwy = -1;
    private int zzbwz = -1;
    private int zzbxb = -1;
    private int zzbxc = -1;
    private final zzhs zzcch;
    private String zzchp = "";
    private Boolean zzcpp;
    private zznv zzdad;
    private final zzash zzdda;
    private final zzbo zzddb;
    private final float zzddc;
    private boolean zzddd = false;
    private boolean zzdde = false;
    private zzaqx zzddf;
    private zzd zzddg;
    private zzasi zzddh;
    private boolean zzddi;
    private boolean zzddj;
    private boolean zzddk;
    private boolean zzddl;
    private int zzddm;
    private boolean zzddn = true;
    private boolean zzddo = false;
    private zzarl zzddp;
    private boolean zzddq;
    private boolean zzddr;
    private zzox zzdds;
    private int zzddt;
    /* access modifiers changed from: private */
    public int zzddu;
    private zznv zzddv;
    private zznv zzddw;
    private zznw zzddx;
    private WeakReference<View.OnClickListener> zzddy;
    private zzd zzddz;
    private boolean zzdea;
    private Map<String, zzaqh> zzdeb;
    private String zzus;
    private final zzw zzwc;
    private final zzang zzyf;

    private zzari(zzash zzash, zzasi zzasi, String str, boolean z, boolean z2, zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) {
        super(zzash);
        this.zzdda = zzash;
        this.zzddh = zzasi;
        this.zzus = str;
        this.zzddk = z;
        this.zzddm = -1;
        this.zzbjc = zzci;
        this.zzyf = zzang;
        this.zzddb = zzbo;
        this.zzwc = zzw;
        this.zzaeu = (WindowManager) getContext().getSystemService("window");
        zzbv.zzek();
        DisplayMetrics zza = zzakk.zza(this.zzaeu);
        this.zzagj = zza;
        this.zzddc = zza.density;
        this.zzcch = zzhs;
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        try {
            settings.setJavaScriptEnabled(true);
        } catch (NullPointerException e) {
            zzakb.zzb("Unable to enable Javascript.", e);
        }
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzbv.zzek().zza((Context) zzash, zzang.zzcw, settings);
        zzbv.zzem().zza(getContext(), settings);
        setDownloadListener(this);
        zzvk();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            addJavascriptInterface(zzaro.zzk(this), "googleAdsJsInterface");
        }
        if (PlatformVersion.isAtLeastHoneycomb()) {
            removeJavascriptInterface("accessibility");
            removeJavascriptInterface("accessibilityTraversal");
        }
        this.zzaee = new zzamt(this.zzdda.zzto(), this, this, (ViewTreeObserver.OnScrollChangedListener) null);
        zzvo();
        zznw zznw = new zznw(new zznx(true, "make_wv", this.zzus));
        this.zzddx = zznw;
        zznw.zzji().zzc(zznx);
        zznv zzb = zznq.zzb(this.zzddx.zzji());
        this.zzdad = zzb;
        this.zzddx.zza("native:view_create", zzb);
        this.zzddw = null;
        this.zzddv = null;
        zzbv.zzem().zzaw(zzash);
        zzbv.zzeo().zzqe();
    }

    private final void zza(Boolean bool) {
        synchronized (this) {
            this.zzcpp = bool;
        }
        zzbv.zzeo().zza(bool);
    }

    private final synchronized void zza(String str, ValueCallback<String> valueCallback) {
        if (!isDestroyed()) {
            evaluateJavascript(str, (ValueCallback<String>) null);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    private final void zzal(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z ? "1" : "0");
        zza("onAdVisibilityChanged", (Map<String, ?>) hashMap);
    }

    static zzari zzb(Context context, zzasi zzasi, String str, boolean z, boolean z2, zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) {
        Context context2 = context;
        return new zzari(new zzash(context), zzasi, str, z, z2, zzci, zzang, zznx, zzbo, zzw, zzhs);
    }

    private final synchronized void zzds(String str) {
        if (!isDestroyed()) {
            loadUrl(str);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    private final synchronized void zzdt(String str) {
        try {
            super.loadUrl(str);
        } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError | UnsatisfiedLinkError e) {
            zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrlUnsafe");
            zzakb.zzc("Could not call loadUrl. ", e);
        }
    }

    private final void zzdu(String str) {
        if (PlatformVersion.isAtLeastKitKat()) {
            if (zzpz() == null) {
                zzvi();
            }
            if (zzpz().booleanValue()) {
                zza(str, (ValueCallback<String>) null);
                return;
            }
            String valueOf = String.valueOf(str);
            zzds(valueOf.length() != 0 ? "javascript:".concat(valueOf) : new String("javascript:"));
            return;
        }
        String valueOf2 = String.valueOf(str);
        zzds(valueOf2.length() != 0 ? "javascript:".concat(valueOf2) : new String("javascript:"));
    }

    private final synchronized Boolean zzpz() {
        return this.zzcpp;
    }

    private final synchronized void zzqf() {
        if (!this.zzdea) {
            this.zzdea = true;
            zzbv.zzeo().zzqf();
        }
    }

    private final boolean zzvh() {
        int i;
        int i2;
        boolean z = false;
        if (!this.zzddf.zzfz() && !this.zzddf.zzuu()) {
            return false;
        }
        zzkb.zzif();
        DisplayMetrics displayMetrics = this.zzagj;
        int zzb = zzamu.zzb(displayMetrics, displayMetrics.widthPixels);
        zzkb.zzif();
        DisplayMetrics displayMetrics2 = this.zzagj;
        int zzb2 = zzamu.zzb(displayMetrics2, displayMetrics2.heightPixels);
        Activity zzto = this.zzdda.zzto();
        if (zzto == null || zzto.getWindow() == null) {
            i2 = zzb;
            i = zzb2;
        } else {
            zzbv.zzek();
            int[] zzf = zzakk.zzf(zzto);
            zzkb.zzif();
            int zzb3 = zzamu.zzb(this.zzagj, zzf[0]);
            zzkb.zzif();
            i = zzamu.zzb(this.zzagj, zzf[1]);
            i2 = zzb3;
        }
        if (this.zzbwy == zzb && this.zzbwz == zzb2 && this.zzbxb == i2 && this.zzbxc == i) {
            return false;
        }
        if (!(this.zzbwy == zzb && this.zzbwz == zzb2)) {
            z = true;
        }
        this.zzbwy = zzb;
        this.zzbwz = zzb2;
        this.zzbxb = i2;
        this.zzbxc = i;
        new zzaal(this).zza(zzb, zzb2, i2, i, this.zzagj.density, this.zzaeu.getDefaultDisplay().getRotation());
        return z;
    }

    private final synchronized void zzvi() {
        Boolean zzpz = zzbv.zzeo().zzpz();
        this.zzcpp = zzpz;
        if (zzpz == null) {
            try {
                evaluateJavascript("(function(){})()", (ValueCallback<String>) null);
                zza((Boolean) true);
            } catch (IllegalStateException unused) {
                zza((Boolean) false);
            }
        }
    }

    private final void zzvj() {
        zznq.zza(this.zzddx.zzji(), this.zzdad, "aeh2");
    }

    private final synchronized void zzvk() {
        if (!this.zzddk) {
            if (!this.zzddh.zzvs()) {
                if (Build.VERSION.SDK_INT < 18) {
                    zzakb.zzck("Disabling hardware acceleration on an AdView.");
                    zzvl();
                    return;
                }
                zzakb.zzck("Enabling hardware acceleration on an AdView.");
                zzvm();
                return;
            }
        }
        zzakb.zzck("Enabling hardware acceleration on an overlay.");
        zzvm();
    }

    private final synchronized void zzvl() {
        if (!this.zzddl) {
            zzbv.zzem().zzz(this);
        }
        this.zzddl = true;
    }

    private final synchronized void zzvm() {
        if (this.zzddl) {
            zzbv.zzem().zzy(this);
        }
        this.zzddl = false;
    }

    private final synchronized void zzvn() {
        this.zzdeb = null;
    }

    private final void zzvo() {
        zznx zzji;
        zznw zznw = this.zzddx;
        if (zznw != null && (zzji = zznw.zzji()) != null && zzbv.zzeo().zzpy() != null) {
            zzbv.zzeo().zzpy().zza(zzji);
        }
    }

    public final synchronized void destroy() {
        zzvo();
        this.zzaee.zzsd();
        if (this.zzddg != null) {
            this.zzddg.close();
            this.zzddg.onDestroy();
            this.zzddg = null;
        }
        this.zzddf.reset();
        if (!this.zzddj) {
            zzbv.zzff();
            zzaqg.zzb((zzapw) this);
            zzvn();
            this.zzddj = true;
            zzakb.v("Initiating WebView self destruct sequence in 3...");
            zzakb.v("Loading blank page in WebView, 2...");
            zzdt("about:blank");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void evaluateJavascript(java.lang.String r2, android.webkit.ValueCallback<java.lang.String> r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.isDestroyed()     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0014
            java.lang.String r2 = "#004 The webview is destroyed. Ignoring action."
            com.google.android.gms.internal.ads.zzakb.zzdm(r2)     // Catch:{ all -> 0x0019 }
            if (r3 == 0) goto L_0x0012
            r2 = 0
            r3.onReceiveValue(r2)     // Catch:{ all -> 0x0019 }
        L_0x0012:
            monitor-exit(r1)
            return
        L_0x0014:
            super.evaluateJavascript(r2, r3)     // Catch:{ all -> 0x0019 }
            monitor-exit(r1)
            return
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzari.evaluateJavascript(java.lang.String, android.webkit.ValueCallback):void");
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.zzddj) {
                    this.zzddf.reset();
                    zzbv.zzff();
                    zzaqg.zzb((zzapw) this);
                    zzvn();
                    zzqf();
                }
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    public final View.OnClickListener getOnClickListener() {
        return (View.OnClickListener) this.zzddy.get();
    }

    public final synchronized int getRequestedOrientation() {
        return this.zzddm;
    }

    public final View getView() {
        return this;
    }

    public final WebView getWebView() {
        return this;
    }

    public final synchronized boolean isDestroyed() {
        return this.zzddj;
    }

    public final synchronized void loadData(String str, String str2, String str3) {
        if (!isDestroyed()) {
            super.loadData(str, str2, str3);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!isDestroyed()) {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void loadUrl(String str) {
        if (!isDestroyed()) {
            try {
                super.loadUrl(str);
            } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError e) {
                zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrl");
                zzakb.zzc("Could not call loadUrl. ", e);
            }
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isDestroyed()) {
            this.zzaee.onAttachedToWindow();
        }
        boolean z = this.zzddq;
        if (this.zzddf != null && this.zzddf.zzuu()) {
            if (!this.zzddr) {
                ViewTreeObserver.OnGlobalLayoutListener zzuv = this.zzddf.zzuv();
                if (zzuv != null) {
                    zzbv.zzfg();
                    zzaor.zza((View) this, zzuv);
                }
                ViewTreeObserver.OnScrollChangedListener zzuw = this.zzddf.zzuw();
                if (zzuw != null) {
                    zzbv.zzfg();
                    zzaor.zza((View) this, zzuw);
                }
                this.zzddr = true;
            }
            zzvh();
            z = true;
        }
        zzal(z);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        synchronized (this) {
            if (!isDestroyed()) {
                this.zzaee.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzddr && this.zzddf != null && this.zzddf.zzuu() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                ViewTreeObserver.OnGlobalLayoutListener zzuv = this.zzddf.zzuv();
                if (zzuv != null) {
                    zzbv.zzem().zza(getViewTreeObserver(), zzuv);
                }
                ViewTreeObserver.OnScrollChangedListener zzuw = this.zzddf.zzuw();
                if (zzuw != null) {
                    getViewTreeObserver().removeOnScrollChangedListener(zzuw);
                }
                this.zzddr = false;
            }
        }
        zzal(false);
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            zzbv.zzek();
            zzakk.zza(getContext(), intent);
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51 + String.valueOf(str4).length());
            sb.append("Couldn't find an Activity to view url/mimetype: ");
            sb.append(str);
            sb.append(" / ");
            sb.append(str4);
            zzakb.zzck(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            if (Build.VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
                super.onDraw(canvas);
                zzaqx zzaqx = this.zzddf;
                if (zzaqx != null && zzaqx.zzve() != null) {
                    this.zzddf.zzve().zzda();
                }
            }
        }
    }

    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzaxx)).booleanValue()) {
            float axisValue = motionEvent.getAxisValue(9);
            float axisValue2 = motionEvent.getAxisValue(10);
            if (motionEvent.getActionMasked() == 8) {
                if (axisValue > 0.0f && !canScrollVertically(-1)) {
                    return false;
                }
                if (axisValue < 0.0f && !canScrollVertically(1)) {
                    return false;
                }
                if (axisValue2 > 0.0f && !canScrollHorizontally(-1)) {
                    return false;
                }
                if (axisValue2 < 0.0f && !canScrollHorizontally(1)) {
                    return false;
                }
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public final void onGlobalLayout() {
        boolean zzvh = zzvh();
        zzd zzub = zzub();
        if (zzub != null && zzvh) {
            zzub.zznn();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01d4, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01b2 A[SYNTHETIC, Splitter:B:99:0x01b2] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:109:0x01d5=Splitter:B:109:0x01d5, B:52:0x00ba=Splitter:B:52:0x00ba} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.isDestroyed()     // Catch:{ all -> 0x01da }
            r1 = 0
            if (r0 == 0) goto L_0x000d
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01da }
            monitor-exit(r7)
            return
        L_0x000d:
            boolean r0 = r7.isInEditMode()     // Catch:{ all -> 0x01da }
            if (r0 != 0) goto L_0x01d5
            boolean r0 = r7.zzddk     // Catch:{ all -> 0x01da }
            if (r0 != 0) goto L_0x01d5
            com.google.android.gms.internal.ads.zzasi r0 = r7.zzddh     // Catch:{ all -> 0x01da }
            boolean r0 = r0.zzvt()     // Catch:{ all -> 0x01da }
            if (r0 == 0) goto L_0x0021
            goto L_0x01d5
        L_0x0021:
            com.google.android.gms.internal.ads.zzasi r0 = r7.zzddh     // Catch:{ all -> 0x01da }
            boolean r0 = r0.zzvu()     // Catch:{ all -> 0x01da }
            if (r0 == 0) goto L_0x006d
            com.google.android.gms.internal.ads.zzarl r0 = r7.zztm()     // Catch:{ all -> 0x01da }
            r1 = 0
            if (r0 == 0) goto L_0x0035
            float r0 = r0.getAspectRatio()     // Catch:{ all -> 0x01da }
            goto L_0x0036
        L_0x0035:
            r0 = 0
        L_0x0036:
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x003f
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01da }
            monitor-exit(r7)
            return
        L_0x003f:
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01da }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01da }
            float r1 = (float) r9     // Catch:{ all -> 0x01da }
            float r1 = r1 * r0
            int r1 = (int) r1     // Catch:{ all -> 0x01da }
            float r2 = (float) r8     // Catch:{ all -> 0x01da }
            float r2 = r2 / r0
            int r2 = (int) r2     // Catch:{ all -> 0x01da }
            if (r9 != 0) goto L_0x0058
            if (r2 == 0) goto L_0x0058
            float r9 = (float) r2     // Catch:{ all -> 0x01da }
            float r9 = r9 * r0
            int r1 = (int) r9     // Catch:{ all -> 0x01da }
            r9 = r2
            goto L_0x0060
        L_0x0058:
            if (r8 != 0) goto L_0x0060
            if (r1 == 0) goto L_0x0060
            float r8 = (float) r1     // Catch:{ all -> 0x01da }
            float r8 = r8 / r0
            int r2 = (int) r8     // Catch:{ all -> 0x01da }
            r8 = r1
        L_0x0060:
            int r8 = java.lang.Math.min(r1, r8)     // Catch:{ all -> 0x01da }
            int r9 = java.lang.Math.min(r2, r9)     // Catch:{ all -> 0x01da }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01da }
            monitor-exit(r7)
            return
        L_0x006d:
            com.google.android.gms.internal.ads.zzasi r0 = r7.zzddh     // Catch:{ all -> 0x01da }
            boolean r0 = r0.isFluid()     // Catch:{ all -> 0x01da }
            if (r0 == 0) goto L_0x00bf
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzbch     // Catch:{ all -> 0x01da }
            com.google.android.gms.internal.ads.zzni r1 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x01da }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x01da }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01da }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01da }
            if (r0 != 0) goto L_0x00ba
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBeanMR1()     // Catch:{ all -> 0x01da }
            if (r0 != 0) goto L_0x008e
            goto L_0x00ba
        L_0x008e:
            java.lang.String r0 = "/contentHeight"
            com.google.android.gms.internal.ads.zzarj r1 = new com.google.android.gms.internal.ads.zzarj     // Catch:{ all -> 0x01da }
            r1.<init>(r7)     // Catch:{ all -> 0x01da }
            r7.zza((java.lang.String) r0, (com.google.android.gms.ads.internal.gmsg.zzv<? super com.google.android.gms.internal.ads.zzaqw>) r1)     // Catch:{ all -> 0x01da }
            java.lang.String r0 = "(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();"
            r7.zzdu(r0)     // Catch:{ all -> 0x01da }
            android.util.DisplayMetrics r0 = r7.zzagj     // Catch:{ all -> 0x01da }
            float r0 = r0.density     // Catch:{ all -> 0x01da }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01da }
            int r1 = r7.zzddu     // Catch:{ all -> 0x01da }
            r2 = -1
            if (r1 == r2) goto L_0x00b1
            int r9 = r7.zzddu     // Catch:{ all -> 0x01da }
            float r9 = (float) r9     // Catch:{ all -> 0x01da }
            float r9 = r9 * r0
            int r9 = (int) r9     // Catch:{ all -> 0x01da }
            goto L_0x00b5
        L_0x00b1:
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01da }
        L_0x00b5:
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01da }
            monitor-exit(r7)
            return
        L_0x00ba:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01da }
            monitor-exit(r7)
            return
        L_0x00bf:
            com.google.android.gms.internal.ads.zzasi r0 = r7.zzddh     // Catch:{ all -> 0x01da }
            boolean r0 = r0.zzvs()     // Catch:{ all -> 0x01da }
            if (r0 == 0) goto L_0x00d4
            android.util.DisplayMetrics r8 = r7.zzagj     // Catch:{ all -> 0x01da }
            int r8 = r8.widthPixels     // Catch:{ all -> 0x01da }
            android.util.DisplayMetrics r9 = r7.zzagj     // Catch:{ all -> 0x01da }
            int r9 = r9.heightPixels     // Catch:{ all -> 0x01da }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01da }
            monitor-exit(r7)
            return
        L_0x00d4:
            int r0 = android.view.View.MeasureSpec.getMode(r8)     // Catch:{ all -> 0x01da }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01da }
            int r2 = android.view.View.MeasureSpec.getMode(r9)     // Catch:{ all -> 0x01da }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01da }
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == r4) goto L_0x00f4
            if (r0 != r3) goto L_0x00f0
            goto L_0x00f4
        L_0x00f0:
            r0 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x00f5
        L_0x00f4:
            r0 = r8
        L_0x00f5:
            if (r2 == r4) goto L_0x00f9
            if (r2 != r3) goto L_0x00fa
        L_0x00f9:
            r5 = r9
        L_0x00fa:
            com.google.android.gms.internal.ads.zzasi r2 = r7.zzddh     // Catch:{ all -> 0x01da }
            int r2 = r2.widthPixels     // Catch:{ all -> 0x01da }
            r3 = 1
            if (r2 > r0) goto L_0x010a
            com.google.android.gms.internal.ads.zzasi r2 = r7.zzddh     // Catch:{ all -> 0x01da }
            int r2 = r2.heightPixels     // Catch:{ all -> 0x01da }
            if (r2 <= r5) goto L_0x0108
            goto L_0x010a
        L_0x0108:
            r2 = 0
            goto L_0x010b
        L_0x010a:
            r2 = 1
        L_0x010b:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r4 = com.google.android.gms.internal.ads.zznk.zzbfe     // Catch:{ all -> 0x01da }
            com.google.android.gms.internal.ads.zzni r6 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x01da }
            java.lang.Object r4 = r6.zzd(r4)     // Catch:{ all -> 0x01da }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x01da }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x01da }
            if (r4 == 0) goto L_0x0143
            com.google.android.gms.internal.ads.zzasi r4 = r7.zzddh     // Catch:{ all -> 0x01da }
            int r4 = r4.widthPixels     // Catch:{ all -> 0x01da }
            float r4 = (float) r4     // Catch:{ all -> 0x01da }
            float r6 = r7.zzddc     // Catch:{ all -> 0x01da }
            float r4 = r4 / r6
            float r0 = (float) r0     // Catch:{ all -> 0x01da }
            float r6 = r7.zzddc     // Catch:{ all -> 0x01da }
            float r0 = r0 / r6
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x013f
            com.google.android.gms.internal.ads.zzasi r0 = r7.zzddh     // Catch:{ all -> 0x01da }
            int r0 = r0.heightPixels     // Catch:{ all -> 0x01da }
            float r0 = (float) r0     // Catch:{ all -> 0x01da }
            float r4 = r7.zzddc     // Catch:{ all -> 0x01da }
            float r0 = r0 / r4
            float r4 = (float) r5     // Catch:{ all -> 0x01da }
            float r5 = r7.zzddc     // Catch:{ all -> 0x01da }
            float r4 = r4 / r5
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 > 0) goto L_0x013f
            r0 = 1
            goto L_0x0140
        L_0x013f:
            r0 = 0
        L_0x0140:
            if (r2 == 0) goto L_0x0143
            r2 = r0
        L_0x0143:
            r0 = 8
            if (r2 == 0) goto L_0x01b2
            com.google.android.gms.internal.ads.zzasi r2 = r7.zzddh     // Catch:{ all -> 0x01da }
            int r2 = r2.widthPixels     // Catch:{ all -> 0x01da }
            float r2 = (float) r2     // Catch:{ all -> 0x01da }
            float r4 = r7.zzddc     // Catch:{ all -> 0x01da }
            float r2 = r2 / r4
            int r2 = (int) r2     // Catch:{ all -> 0x01da }
            com.google.android.gms.internal.ads.zzasi r4 = r7.zzddh     // Catch:{ all -> 0x01da }
            int r4 = r4.heightPixels     // Catch:{ all -> 0x01da }
            float r4 = (float) r4     // Catch:{ all -> 0x01da }
            float r5 = r7.zzddc     // Catch:{ all -> 0x01da }
            float r4 = r4 / r5
            int r4 = (int) r4     // Catch:{ all -> 0x01da }
            float r8 = (float) r8     // Catch:{ all -> 0x01da }
            float r5 = r7.zzddc     // Catch:{ all -> 0x01da }
            float r8 = r8 / r5
            int r8 = (int) r8     // Catch:{ all -> 0x01da }
            float r9 = (float) r9     // Catch:{ all -> 0x01da }
            float r5 = r7.zzddc     // Catch:{ all -> 0x01da }
            float r9 = r9 / r5
            int r9 = (int) r9     // Catch:{ all -> 0x01da }
            r5 = 103(0x67, float:1.44E-43)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01da }
            r6.<init>(r5)     // Catch:{ all -> 0x01da }
            java.lang.String r5 = "Not enough space to show ad. Needs "
            r6.append(r5)     // Catch:{ all -> 0x01da }
            r6.append(r2)     // Catch:{ all -> 0x01da }
            java.lang.String r2 = "x"
            r6.append(r2)     // Catch:{ all -> 0x01da }
            r6.append(r4)     // Catch:{ all -> 0x01da }
            java.lang.String r2 = " dp, but only has "
            r6.append(r2)     // Catch:{ all -> 0x01da }
            r6.append(r8)     // Catch:{ all -> 0x01da }
            java.lang.String r8 = "x"
            r6.append(r8)     // Catch:{ all -> 0x01da }
            r6.append(r9)     // Catch:{ all -> 0x01da }
            java.lang.String r8 = " dp."
            r6.append(r8)     // Catch:{ all -> 0x01da }
            java.lang.String r8 = r6.toString()     // Catch:{ all -> 0x01da }
            com.google.android.gms.internal.ads.zzakb.zzdk(r8)     // Catch:{ all -> 0x01da }
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01da }
            if (r8 == r0) goto L_0x01a0
            r8 = 4
            r7.setVisibility(r8)     // Catch:{ all -> 0x01da }
        L_0x01a0:
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01da }
            boolean r8 = r7.zzddd     // Catch:{ all -> 0x01da }
            if (r8 != 0) goto L_0x01d3
            com.google.android.gms.internal.ads.zzhs r8 = r7.zzcch     // Catch:{ all -> 0x01da }
            com.google.android.gms.internal.ads.zzhu$zza$zzb r9 = com.google.android.gms.internal.ads.zzhu.zza.zzb.BANNER_SIZE_INVALID     // Catch:{ all -> 0x01da }
            r8.zza((com.google.android.gms.internal.ads.zzhu.zza.zzb) r9)     // Catch:{ all -> 0x01da }
            r7.zzddd = r3     // Catch:{ all -> 0x01da }
            monitor-exit(r7)
            return
        L_0x01b2:
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01da }
            if (r8 == r0) goto L_0x01bb
            r7.setVisibility(r1)     // Catch:{ all -> 0x01da }
        L_0x01bb:
            boolean r8 = r7.zzdde     // Catch:{ all -> 0x01da }
            if (r8 != 0) goto L_0x01c8
            com.google.android.gms.internal.ads.zzhs r8 = r7.zzcch     // Catch:{ all -> 0x01da }
            com.google.android.gms.internal.ads.zzhu$zza$zzb r9 = com.google.android.gms.internal.ads.zzhu.zza.zzb.BANNER_SIZE_VALID     // Catch:{ all -> 0x01da }
            r8.zza((com.google.android.gms.internal.ads.zzhu.zza.zzb) r9)     // Catch:{ all -> 0x01da }
            r7.zzdde = r3     // Catch:{ all -> 0x01da }
        L_0x01c8:
            com.google.android.gms.internal.ads.zzasi r8 = r7.zzddh     // Catch:{ all -> 0x01da }
            int r8 = r8.widthPixels     // Catch:{ all -> 0x01da }
            com.google.android.gms.internal.ads.zzasi r9 = r7.zzddh     // Catch:{ all -> 0x01da }
            int r9 = r9.heightPixels     // Catch:{ all -> 0x01da }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01da }
        L_0x01d3:
            monitor-exit(r7)
            return
        L_0x01d5:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01da }
            monitor-exit(r7)
            return
        L_0x01da:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzari.onMeasure(int, int):void");
    }

    public final void onPause() {
        if (!isDestroyed()) {
            try {
                if (PlatformVersion.isAtLeastHoneycomb()) {
                    super.onPause();
                }
            } catch (Exception e) {
                zzakb.zzb("Could not pause webview.", e);
            }
        }
    }

    public final void onResume() {
        if (!isDestroyed()) {
            try {
                if (PlatformVersion.isAtLeastHoneycomb()) {
                    super.onResume();
                }
            } catch (Exception e) {
                zzakb.zzb("Could not resume webview.", e);
            }
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.zzddf.zzuu()) {
            synchronized (this) {
                if (this.zzdds != null) {
                    this.zzdds.zzc(motionEvent);
                }
            }
        } else {
            zzci zzci = this.zzbjc;
            if (zzci != null) {
                zzci.zza(motionEvent);
            }
        }
        if (isDestroyed()) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzddy = new WeakReference<>(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    public final synchronized void setRequestedOrientation(int i) {
        this.zzddm = i;
        if (this.zzddg != null) {
            this.zzddg.setRequestedOrientation(i);
        }
    }

    public final void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzaqx) {
            this.zzddf = (zzaqx) webViewClient;
        }
    }

    public final void stopLoading() {
        if (!isDestroyed()) {
            try {
                super.stopLoading();
            } catch (Exception e) {
                zzakb.zzb("Could not stop loading webview.", e);
            }
        }
    }

    public final void zza(zzc zzc) {
        this.zzddf.zza(zzc);
    }

    public final synchronized void zza(zzd zzd) {
        this.zzddg = zzd;
    }

    public final synchronized void zza(zzarl zzarl) {
        if (this.zzddp != null) {
            zzakb.e("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.zzddp = zzarl;
        }
    }

    public final synchronized void zza(zzasi zzasi) {
        this.zzddh = zzasi;
        requestLayout();
    }

    public final void zza(zzfs zzfs) {
        synchronized (this) {
            this.zzddq = zzfs.zztg;
        }
        zzal(zzfs.zztg);
    }

    public final void zza(String str, zzv<? super zzaqw> zzv) {
        zzaqx zzaqx = this.zzddf;
        if (zzaqx != null) {
            zzaqx.zza(str, zzv);
        }
    }

    public final void zza(String str, Predicate<zzv<? super zzaqw>> predicate) {
        zzaqx zzaqx = this.zzddf;
        if (zzaqx != null) {
            zzaqx.zza(str, predicate);
        }
    }

    public final void zza(String str, Map<String, ?> map) {
        try {
            zza(str, zzbv.zzek().zzn(map));
        } catch (JSONException unused) {
            zzakb.zzdk("Could not convert parameters to JSON.");
        }
    }

    public final void zza(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        String valueOf = String.valueOf(sb.toString());
        zzakb.zzck(valueOf.length() != 0 ? "Dispatching AFMA event: ".concat(valueOf) : new String("Dispatching AFMA event: "));
        zzdu(sb.toString());
    }

    public final void zza(boolean z, int i) {
        this.zzddf.zza(z, i);
    }

    public final void zza(boolean z, int i, String str) {
        this.zzddf.zza(z, i, str);
    }

    public final void zza(boolean z, int i, String str, String str2) {
        this.zzddf.zza(z, i, str, str2);
    }

    public final void zzah(boolean z) {
        this.zzddf.zzah(z);
    }

    public final void zzai(int i) {
        if (i == 0) {
            zznq.zza(this.zzddx.zzji(), this.zzdad, "aebb2");
        }
        zzvj();
        if (this.zzddx.zzji() != null) {
            this.zzddx.zzji().zze("close_type", String.valueOf(i));
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i));
        hashMap.put("version", this.zzyf.zzcw);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    public final synchronized void zzai(boolean z) {
        boolean z2 = z != this.zzddk;
        this.zzddk = z;
        zzvk();
        if (z2) {
            new zzaal(this).zzby(z ? "expanded" : RewardedVideo.VIDEO_MODE_DEFAULT);
        }
    }

    public final synchronized void zzaj(boolean z) {
        this.zzddn = z;
    }

    public final synchronized void zzak(boolean z) {
        int i = this.zzddt + (z ? 1 : -1);
        this.zzddt = i;
        if (i <= 0 && this.zzddg != null) {
            this.zzddg.zznq();
        }
    }

    public final synchronized void zzb(zzd zzd) {
        this.zzddz = zzd;
    }

    public final synchronized void zzb(zzox zzox) {
        this.zzdds = zzox;
    }

    public final void zzb(String str, zzv<? super zzaqw> zzv) {
        zzaqx zzaqx = this.zzddf;
        if (zzaqx != null) {
            zzaqx.zzb(str, zzv);
        }
    }

    public final void zzb(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(jSONObject2).length());
        sb.append(str);
        sb.append("(");
        sb.append(jSONObject2);
        sb.append(");");
        zzdu(sb.toString());
    }

    public final void zzbe(String str) {
        zzdu(str);
    }

    public final zzw zzbi() {
        return this.zzwc;
    }

    public final void zzbm(Context context) {
        this.zzdda.setBaseContext(context);
        this.zzaee.zzi(this.zzdda.zzto());
    }

    public final synchronized void zzc(String str, String str2, String str3) {
        if (!isDestroyed()) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzaya)).booleanValue()) {
                str2 = zzarx.zzb(str2, zzarx.zzvp());
            }
            super.loadDataWithBaseURL(str, str2, "text/html", "UTF-8", str3);
            return;
        }
        zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
    }

    public final synchronized void zzcl() {
        this.zzddo = true;
        if (this.zzddb != null) {
            this.zzddb.zzcl();
        }
    }

    public final synchronized void zzcm() {
        this.zzddo = false;
        if (this.zzddb != null) {
            this.zzddb.zzcm();
        }
    }

    public final synchronized void zzdr(String str) {
        if (str == null) {
            str = "";
        }
        this.zzchp = str;
    }

    public final void zzno() {
        if (this.zzddv == null) {
            zznq.zza(this.zzddx.zzji(), this.zzdad, "aes2");
            zznv zzb = zznq.zzb(this.zzddx.zzji());
            this.zzddv = zzb;
            this.zzddx.zza("native:view_show", zzb);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzyf.zzcw);
        zza("onshow", (Map<String, ?>) hashMap);
    }

    public final void zznp() {
        zzd zzub = zzub();
        if (zzub != null) {
            zzub.zznp();
        }
    }

    public final synchronized String zzol() {
        return this.zzchp;
    }

    public final zzapn zztl() {
        return null;
    }

    public final synchronized zzarl zztm() {
        return this.zzddp;
    }

    public final zznv zztn() {
        return this.zzdad;
    }

    public final Activity zzto() {
        return this.zzdda.zzto();
    }

    public final zznw zztp() {
        return this.zzddx;
    }

    public final zzang zztq() {
        return this.zzyf;
    }

    public final int zztr() {
        return getMeasuredHeight();
    }

    public final int zzts() {
        return getMeasuredWidth();
    }

    public final void zzty() {
        zzvj();
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzyf.zzcw);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    public final void zztz() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzbv.zzfj().zzdp()));
        hashMap.put("app_volume", String.valueOf(zzbv.zzfj().zzdo()));
        hashMap.put("device_volume", String.valueOf(zzalb.zzay(getContext())));
        zza(AvidVideoPlaybackListenerImpl.VOLUME, (Map<String, ?>) hashMap);
    }

    public final synchronized void zzu(boolean z) {
        if (this.zzddg != null) {
            this.zzddg.zza(this.zzddf.zzfz(), z);
        } else {
            this.zzddi = z;
        }
    }

    public final Context zzua() {
        return this.zzdda.zzua();
    }

    public final synchronized zzd zzub() {
        return this.zzddg;
    }

    public final synchronized zzd zzuc() {
        return this.zzddz;
    }

    public final synchronized zzasi zzud() {
        return this.zzddh;
    }

    public final synchronized String zzue() {
        return this.zzus;
    }

    public final /* synthetic */ zzasc zzuf() {
        return this.zzddf;
    }

    public final WebViewClient zzug() {
        return this.zzddf;
    }

    public final synchronized boolean zzuh() {
        return this.zzddi;
    }

    public final zzci zzui() {
        return this.zzbjc;
    }

    public final synchronized boolean zzuj() {
        return this.zzddk;
    }

    public final synchronized void zzuk() {
        zzakb.v("Destroying WebView!");
        zzqf();
        zzakk.zzcrm.post(new zzark(this));
    }

    public final synchronized boolean zzul() {
        return this.zzddn;
    }

    public final synchronized boolean zzum() {
        return this.zzddo;
    }

    public final synchronized boolean zzun() {
        return this.zzddt > 0;
    }

    public final void zzuo() {
        this.zzaee.zzsc();
    }

    public final void zzup() {
        if (this.zzddw == null) {
            zznv zzb = zznq.zzb(this.zzddx.zzji());
            this.zzddw = zzb;
            this.zzddx.zza("native:view_load", zzb);
        }
    }

    public final synchronized zzox zzuq() {
        return this.zzdds;
    }

    public final void zzur() {
        setBackgroundColor(0);
    }

    public final void zzus() {
        zzakb.v("Cannot add text view to inner AdWebView");
    }
}
