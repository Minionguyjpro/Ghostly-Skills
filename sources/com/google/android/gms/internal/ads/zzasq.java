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
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
final class zzasq extends zzasv implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzaqw, zzuo {
    private zzamt zzaee;
    private final WindowManager zzaeu;
    private final zzci zzbjc;
    private int zzbwy = -1;
    private int zzbwz = -1;
    private int zzbxb = -1;
    private int zzbxc = -1;
    private String zzchp = "";
    private zznv zzdad;
    private final zzbo zzddb;
    private zzd zzddg;
    private zzasi zzddh;
    private boolean zzddi;
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
    private Map<String, zzaqh> zzdeb;
    private zzasj zzdet;
    private float zzdeu;
    private String zzus;
    private final zzw zzwc;
    private final zzang zzyf;

    private zzasq(zzash zzash, zzasi zzasi, String str, boolean z, boolean z2, zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) {
        super(zzash);
        this.zzddh = zzasi;
        this.zzus = str;
        this.zzddk = z;
        this.zzddm = -1;
        this.zzbjc = zzci;
        this.zzyf = zzang;
        this.zzddb = zzbo;
        this.zzwc = zzw;
        this.zzaeu = (WindowManager) getContext().getSystemService("window");
        this.zzaee = new zzamt(zzvv().zzto(), this, this, (ViewTreeObserver.OnScrollChangedListener) null);
        zzbv.zzek().zza((Context) zzash, zzang.zzcw, getSettings());
        setDownloadListener(this);
        this.zzdeu = zzvv().getResources().getDisplayMetrics().density;
        zzvk();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            addJavascriptInterface(zzaro.zzk(this), "googleAdsJsInterface");
        }
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
    }

    private final void zzal(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z ? "1" : "0");
        zzup.zza((zzuo) this, "onAdVisibilityChanged", (Map) hashMap);
    }

    static zzasq zzc(Context context, zzasi zzasi, String str, boolean z, boolean z2, zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) {
        Context context2 = context;
        return new zzasq(new zzash(context), zzasi, str, z, z2, zzci, zzang, zznx, zzbo, zzw, zzhs);
    }

    private final boolean zzvh() {
        int i;
        int i2;
        boolean z = false;
        if (!this.zzdet.zzfz() && !this.zzdet.zzuu()) {
            return false;
        }
        zzbv.zzek();
        DisplayMetrics zza = zzakk.zza(this.zzaeu);
        zzkb.zzif();
        int zzb = zzamu.zzb(zza, zza.widthPixels);
        zzkb.zzif();
        int zzb2 = zzamu.zzb(zza, zza.heightPixels);
        Activity zzto = zzvv().zzto();
        if (zzto == null || zzto.getWindow() == null) {
            i2 = zzb;
            i = zzb2;
        } else {
            zzbv.zzek();
            int[] zzf = zzakk.zzf(zzto);
            zzkb.zzif();
            i2 = zzamu.zzb(zza, zzf[0]);
            zzkb.zzif();
            i = zzamu.zzb(zza, zzf[1]);
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
        new zzaal(this).zza(zzb, zzb2, i2, i, zza.density, this.zzaeu.getDefaultDisplay().getRotation());
        return z;
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

    /* access modifiers changed from: protected */
    public final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isDestroyed()) {
            this.zzaee.onAttachedToWindow();
        }
        boolean z = this.zzddq;
        if (this.zzdet != null && this.zzdet.zzuu()) {
            if (!this.zzddr) {
                ViewTreeObserver.OnGlobalLayoutListener zzuv = this.zzdet.zzuv();
                if (zzuv != null) {
                    zzbv.zzfg();
                    zzaor.zza((View) this, zzuv);
                }
                ViewTreeObserver.OnScrollChangedListener zzuw = this.zzdet.zzuw();
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
            if (this.zzddr && this.zzdet != null && this.zzdet.zzuu() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                ViewTreeObserver.OnGlobalLayoutListener zzuv = this.zzdet.zzuv();
                if (zzuv != null) {
                    zzbv.zzem().zza(getViewTreeObserver(), zzuv);
                }
                ViewTreeObserver.OnScrollChangedListener zzuw = this.zzdet.zzuw();
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
        if (Build.VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
            super.onDraw(canvas);
            zzasj zzasj = this.zzdet;
            if (zzasj != null && zzasj.zzve() != null) {
                this.zzdet.zzve().zzda();
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
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01ac A[SYNTHETIC, Splitter:B:97:0x01ac] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:104:0x01c2=Splitter:B:104:0x01c2, B:52:0x00b8=Splitter:B:52:0x00b8} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.isDestroyed()     // Catch:{ all -> 0x01c7 }
            r1 = 0
            if (r0 == 0) goto L_0x000d
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01c7 }
            monitor-exit(r7)
            return
        L_0x000d:
            boolean r0 = r7.isInEditMode()     // Catch:{ all -> 0x01c7 }
            if (r0 != 0) goto L_0x01c2
            boolean r0 = r7.zzddk     // Catch:{ all -> 0x01c7 }
            if (r0 != 0) goto L_0x01c2
            com.google.android.gms.internal.ads.zzasi r0 = r7.zzddh     // Catch:{ all -> 0x01c7 }
            boolean r0 = r0.zzvt()     // Catch:{ all -> 0x01c7 }
            if (r0 == 0) goto L_0x0021
            goto L_0x01c2
        L_0x0021:
            com.google.android.gms.internal.ads.zzasi r0 = r7.zzddh     // Catch:{ all -> 0x01c7 }
            boolean r0 = r0.zzvu()     // Catch:{ all -> 0x01c7 }
            if (r0 == 0) goto L_0x006d
            com.google.android.gms.internal.ads.zzarl r0 = r7.zztm()     // Catch:{ all -> 0x01c7 }
            r1 = 0
            if (r0 == 0) goto L_0x0035
            float r0 = r0.getAspectRatio()     // Catch:{ all -> 0x01c7 }
            goto L_0x0036
        L_0x0035:
            r0 = 0
        L_0x0036:
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x003f
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01c7 }
            monitor-exit(r7)
            return
        L_0x003f:
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01c7 }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01c7 }
            float r1 = (float) r9     // Catch:{ all -> 0x01c7 }
            float r1 = r1 * r0
            int r1 = (int) r1     // Catch:{ all -> 0x01c7 }
            float r2 = (float) r8     // Catch:{ all -> 0x01c7 }
            float r2 = r2 / r0
            int r2 = (int) r2     // Catch:{ all -> 0x01c7 }
            if (r9 != 0) goto L_0x0058
            if (r2 == 0) goto L_0x0058
            float r9 = (float) r2     // Catch:{ all -> 0x01c7 }
            float r9 = r9 * r0
            int r1 = (int) r9     // Catch:{ all -> 0x01c7 }
            r9 = r2
            goto L_0x0060
        L_0x0058:
            if (r8 != 0) goto L_0x0060
            if (r1 == 0) goto L_0x0060
            float r8 = (float) r1     // Catch:{ all -> 0x01c7 }
            float r8 = r8 / r0
            int r2 = (int) r8     // Catch:{ all -> 0x01c7 }
            r8 = r1
        L_0x0060:
            int r8 = java.lang.Math.min(r1, r8)     // Catch:{ all -> 0x01c7 }
            int r9 = java.lang.Math.min(r2, r9)     // Catch:{ all -> 0x01c7 }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01c7 }
            monitor-exit(r7)
            return
        L_0x006d:
            com.google.android.gms.internal.ads.zzasi r0 = r7.zzddh     // Catch:{ all -> 0x01c7 }
            boolean r0 = r0.isFluid()     // Catch:{ all -> 0x01c7 }
            if (r0 == 0) goto L_0x00bd
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzbch     // Catch:{ all -> 0x01c7 }
            com.google.android.gms.internal.ads.zzni r1 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x01c7 }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x01c7 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01c7 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01c7 }
            if (r0 != 0) goto L_0x00b8
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBeanMR1()     // Catch:{ all -> 0x01c7 }
            if (r0 != 0) goto L_0x008e
            goto L_0x00b8
        L_0x008e:
            java.lang.String r0 = "/contentHeight"
            com.google.android.gms.internal.ads.zzasr r1 = new com.google.android.gms.internal.ads.zzasr     // Catch:{ all -> 0x01c7 }
            r1.<init>(r7)     // Catch:{ all -> 0x01c7 }
            r7.zza((java.lang.String) r0, (com.google.android.gms.ads.internal.gmsg.zzv<? super com.google.android.gms.internal.ads.zzaqw>) r1)     // Catch:{ all -> 0x01c7 }
            java.lang.String r0 = "(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();"
            r7.zzbe(r0)     // Catch:{ all -> 0x01c7 }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01c7 }
            int r0 = r7.zzddu     // Catch:{ all -> 0x01c7 }
            r1 = -1
            if (r0 == r1) goto L_0x00af
            int r9 = r7.zzddu     // Catch:{ all -> 0x01c7 }
            float r9 = (float) r9     // Catch:{ all -> 0x01c7 }
            float r0 = r7.zzdeu     // Catch:{ all -> 0x01c7 }
            float r9 = r9 * r0
            int r9 = (int) r9     // Catch:{ all -> 0x01c7 }
            goto L_0x00b3
        L_0x00af:
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01c7 }
        L_0x00b3:
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01c7 }
            monitor-exit(r7)
            return
        L_0x00b8:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01c7 }
            monitor-exit(r7)
            return
        L_0x00bd:
            com.google.android.gms.internal.ads.zzasi r0 = r7.zzddh     // Catch:{ all -> 0x01c7 }
            boolean r0 = r0.zzvs()     // Catch:{ all -> 0x01c7 }
            if (r0 == 0) goto L_0x00dc
            android.util.DisplayMetrics r8 = new android.util.DisplayMetrics     // Catch:{ all -> 0x01c7 }
            r8.<init>()     // Catch:{ all -> 0x01c7 }
            android.view.WindowManager r9 = r7.zzaeu     // Catch:{ all -> 0x01c7 }
            android.view.Display r9 = r9.getDefaultDisplay()     // Catch:{ all -> 0x01c7 }
            r9.getMetrics(r8)     // Catch:{ all -> 0x01c7 }
            int r9 = r8.widthPixels     // Catch:{ all -> 0x01c7 }
            int r8 = r8.heightPixels     // Catch:{ all -> 0x01c7 }
            r7.setMeasuredDimension(r9, r8)     // Catch:{ all -> 0x01c7 }
            monitor-exit(r7)
            return
        L_0x00dc:
            int r0 = android.view.View.MeasureSpec.getMode(r8)     // Catch:{ all -> 0x01c7 }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01c7 }
            int r2 = android.view.View.MeasureSpec.getMode(r9)     // Catch:{ all -> 0x01c7 }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01c7 }
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == r4) goto L_0x00fc
            if (r0 != r3) goto L_0x00f8
            goto L_0x00fc
        L_0x00f8:
            r0 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x00fd
        L_0x00fc:
            r0 = r8
        L_0x00fd:
            if (r2 == r4) goto L_0x0101
            if (r2 != r3) goto L_0x0102
        L_0x0101:
            r5 = r9
        L_0x0102:
            com.google.android.gms.internal.ads.zzasi r2 = r7.zzddh     // Catch:{ all -> 0x01c7 }
            int r2 = r2.widthPixels     // Catch:{ all -> 0x01c7 }
            r3 = 1
            if (r2 > r0) goto L_0x0112
            com.google.android.gms.internal.ads.zzasi r2 = r7.zzddh     // Catch:{ all -> 0x01c7 }
            int r2 = r2.heightPixels     // Catch:{ all -> 0x01c7 }
            if (r2 <= r5) goto L_0x0110
            goto L_0x0112
        L_0x0110:
            r2 = 0
            goto L_0x0113
        L_0x0112:
            r2 = 1
        L_0x0113:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r4 = com.google.android.gms.internal.ads.zznk.zzbfe     // Catch:{ all -> 0x01c7 }
            com.google.android.gms.internal.ads.zzni r6 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x01c7 }
            java.lang.Object r4 = r6.zzd(r4)     // Catch:{ all -> 0x01c7 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x01c7 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x01c7 }
            if (r4 == 0) goto L_0x014a
            com.google.android.gms.internal.ads.zzasi r4 = r7.zzddh     // Catch:{ all -> 0x01c7 }
            int r4 = r4.widthPixels     // Catch:{ all -> 0x01c7 }
            float r4 = (float) r4     // Catch:{ all -> 0x01c7 }
            float r6 = r7.zzdeu     // Catch:{ all -> 0x01c7 }
            float r4 = r4 / r6
            float r0 = (float) r0     // Catch:{ all -> 0x01c7 }
            float r6 = r7.zzdeu     // Catch:{ all -> 0x01c7 }
            float r0 = r0 / r6
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x0146
            com.google.android.gms.internal.ads.zzasi r0 = r7.zzddh     // Catch:{ all -> 0x01c7 }
            int r0 = r0.heightPixels     // Catch:{ all -> 0x01c7 }
            float r0 = (float) r0     // Catch:{ all -> 0x01c7 }
            float r4 = r7.zzdeu     // Catch:{ all -> 0x01c7 }
            float r0 = r0 / r4
            float r4 = (float) r5     // Catch:{ all -> 0x01c7 }
            float r5 = r7.zzdeu     // Catch:{ all -> 0x01c7 }
            float r4 = r4 / r5
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 > 0) goto L_0x0146
            goto L_0x0147
        L_0x0146:
            r3 = 0
        L_0x0147:
            if (r2 == 0) goto L_0x014a
            r2 = r3
        L_0x014a:
            r0 = 8
            if (r2 == 0) goto L_0x01ac
            com.google.android.gms.internal.ads.zzasi r2 = r7.zzddh     // Catch:{ all -> 0x01c7 }
            int r2 = r2.widthPixels     // Catch:{ all -> 0x01c7 }
            float r2 = (float) r2     // Catch:{ all -> 0x01c7 }
            float r3 = r7.zzdeu     // Catch:{ all -> 0x01c7 }
            float r2 = r2 / r3
            int r2 = (int) r2     // Catch:{ all -> 0x01c7 }
            com.google.android.gms.internal.ads.zzasi r3 = r7.zzddh     // Catch:{ all -> 0x01c7 }
            int r3 = r3.heightPixels     // Catch:{ all -> 0x01c7 }
            float r3 = (float) r3     // Catch:{ all -> 0x01c7 }
            float r4 = r7.zzdeu     // Catch:{ all -> 0x01c7 }
            float r3 = r3 / r4
            int r3 = (int) r3     // Catch:{ all -> 0x01c7 }
            float r8 = (float) r8     // Catch:{ all -> 0x01c7 }
            float r4 = r7.zzdeu     // Catch:{ all -> 0x01c7 }
            float r8 = r8 / r4
            int r8 = (int) r8     // Catch:{ all -> 0x01c7 }
            float r9 = (float) r9     // Catch:{ all -> 0x01c7 }
            float r4 = r7.zzdeu     // Catch:{ all -> 0x01c7 }
            float r9 = r9 / r4
            int r9 = (int) r9     // Catch:{ all -> 0x01c7 }
            r4 = 103(0x67, float:1.44E-43)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c7 }
            r5.<init>(r4)     // Catch:{ all -> 0x01c7 }
            java.lang.String r4 = "Not enough space to show ad. Needs "
            r5.append(r4)     // Catch:{ all -> 0x01c7 }
            r5.append(r2)     // Catch:{ all -> 0x01c7 }
            java.lang.String r2 = "x"
            r5.append(r2)     // Catch:{ all -> 0x01c7 }
            r5.append(r3)     // Catch:{ all -> 0x01c7 }
            java.lang.String r2 = " dp, but only has "
            r5.append(r2)     // Catch:{ all -> 0x01c7 }
            r5.append(r8)     // Catch:{ all -> 0x01c7 }
            java.lang.String r8 = "x"
            r5.append(r8)     // Catch:{ all -> 0x01c7 }
            r5.append(r9)     // Catch:{ all -> 0x01c7 }
            java.lang.String r8 = " dp."
            r5.append(r8)     // Catch:{ all -> 0x01c7 }
            java.lang.String r8 = r5.toString()     // Catch:{ all -> 0x01c7 }
            com.google.android.gms.internal.ads.zzakb.zzdk(r8)     // Catch:{ all -> 0x01c7 }
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01c7 }
            if (r8 == r0) goto L_0x01a7
            r8 = 4
            r7.setVisibility(r8)     // Catch:{ all -> 0x01c7 }
        L_0x01a7:
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01c7 }
            monitor-exit(r7)
            return
        L_0x01ac:
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01c7 }
            if (r8 == r0) goto L_0x01b5
            r7.setVisibility(r1)     // Catch:{ all -> 0x01c7 }
        L_0x01b5:
            com.google.android.gms.internal.ads.zzasi r8 = r7.zzddh     // Catch:{ all -> 0x01c7 }
            int r8 = r8.widthPixels     // Catch:{ all -> 0x01c7 }
            com.google.android.gms.internal.ads.zzasi r9 = r7.zzddh     // Catch:{ all -> 0x01c7 }
            int r9 = r9.heightPixels     // Catch:{ all -> 0x01c7 }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01c7 }
            monitor-exit(r7)
            return
        L_0x01c2:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01c7 }
            monitor-exit(r7)
            return
        L_0x01c7:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzasq.onMeasure(int, int):void");
    }

    public final void onPause() {
        try {
            if (PlatformVersion.isAtLeastHoneycomb()) {
                super.onPause();
            }
        } catch (Exception e) {
            zzakb.zzb("Could not pause webview.", e);
        }
    }

    public final void onResume() {
        try {
            if (PlatformVersion.isAtLeastHoneycomb()) {
                super.onResume();
            }
        } catch (Exception e) {
            zzakb.zzb("Could not resume webview.", e);
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.zzdet.zzuu()) {
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

    public final void stopLoading() {
        try {
            super.stopLoading();
        } catch (Exception e) {
            zzakb.zzb("Could not stop loading webview.", e);
        }
    }

    public final void zza(zzc zzc) {
        this.zzdet.zza(zzc);
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

    public final void zza(zzasj zzasj) {
        this.zzdet = zzasj;
    }

    public final void zza(zzfs zzfs) {
        synchronized (this) {
            this.zzddq = zzfs.zztg;
        }
        zzal(zzfs.zztg);
    }

    public final void zza(String str, zzv<? super zzaqw> zzv) {
        zzasj zzasj = this.zzdet;
        if (zzasj != null) {
            zzasj.zza(str, zzv);
        }
    }

    public final void zza(String str, Predicate<zzv<? super zzaqw>> predicate) {
        zzasj zzasj = this.zzdet;
        if (zzasj != null) {
            zzasj.zza(str, predicate);
        }
    }

    public final void zza(String str, Map map) {
        zzup.zza((zzuo) this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzup.zzb(this, str, jSONObject);
    }

    public final void zza(boolean z, int i) {
        this.zzdet.zza(z, i);
    }

    public final void zza(boolean z, int i, String str) {
        this.zzdet.zza(z, i, str);
    }

    public final void zza(boolean z, int i, String str, String str2) {
        this.zzdet.zza(z, i, str, str2);
    }

    public final void zzah(boolean z) {
        this.zzdet.zzah(z);
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
        zzup.zza((zzuo) this, "onhide", (Map) hashMap);
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

    /* access modifiers changed from: protected */
    public final synchronized void zzam(boolean z) {
        if (!z) {
            zzvo();
            this.zzaee.zzsd();
            if (this.zzddg != null) {
                this.zzddg.close();
                this.zzddg.onDestroy();
                this.zzddg = null;
            }
        }
        this.zzdet.reset();
        zzbv.zzff();
        zzaqg.zzb((zzapw) this);
        zzvn();
    }

    public final synchronized void zzb(zzd zzd) {
        this.zzddz = zzd;
    }

    public final synchronized void zzb(zzox zzox) {
        this.zzdds = zzox;
    }

    public final void zzb(String str, zzv<? super zzaqw> zzv) {
        zzasj zzasj = this.zzdet;
        if (zzasj != null) {
            zzasj.zzb(str, zzv);
        }
    }

    public final void zzb(String str, JSONObject jSONObject) {
        zzup.zza((zzuo) this, str, jSONObject);
    }

    public final synchronized void zzbe(String str) {
        if (!isDestroyed()) {
            super.zzbe(str);
        } else {
            zzakb.zzdk("The webview is destroyed. Ignoring action.");
        }
    }

    public final zzw zzbi() {
        return this.zzwc;
    }

    public final void zzbm(Context context) {
        zzvv().setBaseContext(context);
        this.zzaee.zzi(zzvv().zzto());
    }

    public final synchronized void zzc(String str, String str2, String str3) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzaya)).booleanValue()) {
            str2 = zzarx.zzb(str2, zzarx.zzvp());
        }
        super.loadDataWithBaseURL(str, str2, "text/html", "UTF-8", str3);
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

    public final void zzf(String str, String str2) {
        zzup.zza((zzuo) this, str, str2);
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
        zzup.zza((zzuo) this, "onshow", (Map) hashMap);
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
        return zzvv().zzto();
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
        zzup.zza((zzuo) this, "onhide", (Map) hashMap);
    }

    public final void zztz() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzbv.zzfj().zzdp()));
        hashMap.put("app_volume", String.valueOf(zzbv.zzfj().zzdo()));
        hashMap.put("device_volume", String.valueOf(zzalb.zzay(getContext())));
        zzup.zza((zzuo) this, AvidVideoPlaybackListenerImpl.VOLUME, (Map) hashMap);
    }

    public final synchronized void zzu(boolean z) {
        if (this.zzddg != null) {
            this.zzddg.zza(this.zzdet.zzfz(), z);
        } else {
            this.zzddi = z;
        }
    }

    public final Context zzua() {
        return zzvv().zzua();
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
        return this.zzdet;
    }

    public final WebViewClient zzug() {
        return this.zzdfb;
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
