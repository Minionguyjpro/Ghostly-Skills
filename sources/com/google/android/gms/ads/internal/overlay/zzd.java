package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.appnext.base.b.d;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaaq;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import com.mopub.mobileads.resource.DrawableConstants;
import java.util.Collections;
import java.util.Map;

@zzadh
public class zzd extends zzaaq implements zzw {
    private static final int zzbxm = Color.argb(0, 0, 0, 0);
    protected final Activity mActivity;
    zzaqw zzbnd;
    AdOverlayInfoParcel zzbxn;
    private zzi zzbxo;
    private zzo zzbxp;
    private boolean zzbxq = false;
    private FrameLayout zzbxr;
    private WebChromeClient.CustomViewCallback zzbxs;
    private boolean zzbxt = false;
    private boolean zzbxu = false;
    private zzh zzbxv;
    private boolean zzbxw = false;
    int zzbxx = 0;
    private final Object zzbxy = new Object();
    private Runnable zzbxz;
    private boolean zzbya;
    private boolean zzbyb;
    private boolean zzbyc = false;
    private boolean zzbyd = false;
    private boolean zzbye = true;

    public zzd(Activity activity) {
        this.mActivity = activity;
    }

    private final void zznl() {
        if (this.mActivity.isFinishing() && !this.zzbyc) {
            this.zzbyc = true;
            zzaqw zzaqw = this.zzbnd;
            if (zzaqw != null) {
                zzaqw.zzai(this.zzbxx);
                synchronized (this.zzbxy) {
                    if (!this.zzbya && this.zzbnd.zzun()) {
                        this.zzbxz = new zzf(this);
                        zzakk.zzcrm.postDelayed(this.zzbxz, ((Long) zzkb.zzik().zzd(zznk.zzayq)).longValue());
                        return;
                    }
                }
            }
            zznm();
        }
    }

    private final void zzno() {
        this.zzbnd.zzno();
    }

    private final void zzs(boolean z) {
        int intValue = ((Integer) zzkb.zzik().zzd(zznk.zzben)).intValue();
        zzp zzp = new zzp();
        zzp.size = 50;
        zzp.paddingLeft = z ? intValue : 0;
        zzp.paddingRight = z ? 0 : intValue;
        zzp.paddingTop = 0;
        zzp.paddingBottom = intValue;
        this.zzbxp = new zzo(this.mActivity, zzp, this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        zza(z, this.zzbxn.zzbyr);
        this.zzbxv.addView(this.zzbxp, layoutParams);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c4, code lost:
        if (r1.mActivity.getResources().getConfiguration().orientation == 1) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e5, code lost:
        if (r1.mActivity.getResources().getConfiguration().orientation == 2) goto L_0x00c6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0252  */
    /* JADX WARNING: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0131 A[SYNTHETIC, Splitter:B:58:0x0131] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0202  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x022d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzt(boolean r18) throws com.google.android.gms.ads.internal.overlay.zzg {
        /*
            r17 = this;
            r1 = r17
            boolean r0 = r1.zzbyb
            r2 = 1
            if (r0 != 0) goto L_0x000c
            android.app.Activity r0 = r1.mActivity
            r0.requestWindowFeature(r2)
        L_0x000c:
            android.app.Activity r0 = r1.mActivity
            android.view.Window r0 = r0.getWindow()
            if (r0 == 0) goto L_0x0256
            boolean r3 = com.google.android.gms.common.util.PlatformVersion.isAtLeastN()
            if (r3 == 0) goto L_0x003e
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.zznk.zzbel
            com.google.android.gms.internal.ads.zzni r4 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r3 = r4.zzd(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x003e
            com.google.android.gms.ads.internal.zzbv.zzek()
            android.app.Activity r3 = r1.mActivity
            android.content.res.Resources r4 = r3.getResources()
            android.content.res.Configuration r4 = r4.getConfiguration()
            boolean r3 = com.google.android.gms.internal.ads.zzakk.zza((android.app.Activity) r3, (android.content.res.Configuration) r4)
            goto L_0x003f
        L_0x003e:
            r3 = 1
        L_0x003f:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r4 = r1.zzbxn
            com.google.android.gms.ads.internal.zzaq r4 = r4.zzbyw
            r5 = 0
            if (r4 == 0) goto L_0x0050
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r4 = r1.zzbxn
            com.google.android.gms.ads.internal.zzaq r4 = r4.zzbyw
            boolean r4 = r4.zzzf
            if (r4 == 0) goto L_0x0050
            r4 = 1
            goto L_0x0051
        L_0x0050:
            r4 = 0
        L_0x0051:
            boolean r6 = r1.zzbxu
            if (r6 == 0) goto L_0x0057
            if (r4 == 0) goto L_0x008d
        L_0x0057:
            if (r3 == 0) goto L_0x008d
            r3 = 1024(0x400, float:1.435E-42)
            r0.setFlags(r3, r3)
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.zznk.zzayr
            com.google.android.gms.internal.ads.zzni r4 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r3 = r4.zzd(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x008d
            boolean r3 = com.google.android.gms.common.util.PlatformVersion.isAtLeastKitKat()
            if (r3 == 0) goto L_0x008d
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r1.zzbxn
            com.google.android.gms.ads.internal.zzaq r3 = r3.zzbyw
            if (r3 == 0) goto L_0x008d
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r1.zzbxn
            com.google.android.gms.ads.internal.zzaq r3 = r3.zzbyw
            boolean r3 = r3.zzzk
            if (r3 == 0) goto L_0x008d
            android.view.View r3 = r0.getDecorView()
            r4 = 4098(0x1002, float:5.743E-42)
            r3.setSystemUiVisibility(r4)
        L_0x008d:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r1.zzbxn
            com.google.android.gms.internal.ads.zzaqw r3 = r3.zzbyo
            r4 = 0
            if (r3 == 0) goto L_0x009d
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r1.zzbxn
            com.google.android.gms.internal.ads.zzaqw r3 = r3.zzbyo
            com.google.android.gms.internal.ads.zzasc r3 = r3.zzuf()
            goto L_0x009e
        L_0x009d:
            r3 = r4
        L_0x009e:
            if (r3 == 0) goto L_0x00a5
            boolean r3 = r3.zzfz()
            goto L_0x00a6
        L_0x00a5:
            r3 = 0
        L_0x00a6:
            r1.zzbxw = r5
            if (r3 == 0) goto L_0x00e8
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r6 = r1.zzbxn
            int r6 = r6.orientation
            com.google.android.gms.internal.ads.zzakq r7 = com.google.android.gms.ads.internal.zzbv.zzem()
            int r7 = r7.zzrl()
            if (r6 != r7) goto L_0x00ca
            android.app.Activity r6 = r1.mActivity
            android.content.res.Resources r6 = r6.getResources()
            android.content.res.Configuration r6 = r6.getConfiguration()
            int r6 = r6.orientation
            if (r6 != r2) goto L_0x00c7
        L_0x00c6:
            r5 = 1
        L_0x00c7:
            r1.zzbxw = r5
            goto L_0x00e8
        L_0x00ca:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r6 = r1.zzbxn
            int r6 = r6.orientation
            com.google.android.gms.internal.ads.zzakq r7 = com.google.android.gms.ads.internal.zzbv.zzem()
            int r7 = r7.zzrm()
            if (r6 != r7) goto L_0x00e8
            android.app.Activity r6 = r1.mActivity
            android.content.res.Resources r6 = r6.getResources()
            android.content.res.Configuration r6 = r6.getConfiguration()
            int r6 = r6.orientation
            r7 = 2
            if (r6 != r7) goto L_0x00c7
            goto L_0x00c6
        L_0x00e8:
            boolean r5 = r1.zzbxw
            r6 = 46
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            java.lang.String r6 = "Delay onShow to next orientation change: "
            r7.append(r6)
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            com.google.android.gms.internal.ads.zzakb.zzck(r5)
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r5 = r1.zzbxn
            int r5 = r5.orientation
            r1.setRequestedOrientation(r5)
            com.google.android.gms.internal.ads.zzakq r5 = com.google.android.gms.ads.internal.zzbv.zzem()
            boolean r0 = r5.zza((android.view.Window) r0)
            if (r0 == 0) goto L_0x0116
            java.lang.String r0 = "Hardware acceleration on the AdActivity window enabled."
            com.google.android.gms.internal.ads.zzakb.zzck(r0)
        L_0x0116:
            boolean r0 = r1.zzbxu
            if (r0 != 0) goto L_0x011f
            com.google.android.gms.ads.internal.overlay.zzh r0 = r1.zzbxv
            r5 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            goto L_0x0123
        L_0x011f:
            com.google.android.gms.ads.internal.overlay.zzh r0 = r1.zzbxv
            int r5 = zzbxm
        L_0x0123:
            r0.setBackgroundColor(r5)
            android.app.Activity r0 = r1.mActivity
            com.google.android.gms.ads.internal.overlay.zzh r5 = r1.zzbxv
            r0.setContentView(r5)
            r1.zzbyb = r2
            if (r18 == 0) goto L_0x0202
            com.google.android.gms.ads.internal.zzbv.zzel()     // Catch:{ Exception -> 0x01f4 }
            android.app.Activity r6 = r1.mActivity     // Catch:{ Exception -> 0x01f4 }
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn     // Catch:{ Exception -> 0x01f4 }
            com.google.android.gms.internal.ads.zzaqw r0 = r0.zzbyo     // Catch:{ Exception -> 0x01f4 }
            if (r0 == 0) goto L_0x0146
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn     // Catch:{ Exception -> 0x01f4 }
            com.google.android.gms.internal.ads.zzaqw r0 = r0.zzbyo     // Catch:{ Exception -> 0x01f4 }
            com.google.android.gms.internal.ads.zzasi r0 = r0.zzud()     // Catch:{ Exception -> 0x01f4 }
            r7 = r0
            goto L_0x0147
        L_0x0146:
            r7 = r4
        L_0x0147:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn     // Catch:{ Exception -> 0x01f4 }
            com.google.android.gms.internal.ads.zzaqw r0 = r0.zzbyo     // Catch:{ Exception -> 0x01f4 }
            if (r0 == 0) goto L_0x0157
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn     // Catch:{ Exception -> 0x01f4 }
            com.google.android.gms.internal.ads.zzaqw r0 = r0.zzbyo     // Catch:{ Exception -> 0x01f4 }
            java.lang.String r0 = r0.zzue()     // Catch:{ Exception -> 0x01f4 }
            r8 = r0
            goto L_0x0158
        L_0x0157:
            r8 = r4
        L_0x0158:
            r9 = 1
            r11 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn     // Catch:{ Exception -> 0x01f4 }
            com.google.android.gms.internal.ads.zzang r12 = r0.zzacr     // Catch:{ Exception -> 0x01f4 }
            r13 = 0
            r14 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn     // Catch:{ Exception -> 0x01f4 }
            com.google.android.gms.internal.ads.zzaqw r0 = r0.zzbyo     // Catch:{ Exception -> 0x01f4 }
            if (r0 == 0) goto L_0x0170
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn     // Catch:{ Exception -> 0x01f4 }
            com.google.android.gms.internal.ads.zzaqw r0 = r0.zzbyo     // Catch:{ Exception -> 0x01f4 }
            com.google.android.gms.ads.internal.zzw r0 = r0.zzbi()     // Catch:{ Exception -> 0x01f4 }
            r15 = r0
            goto L_0x0171
        L_0x0170:
            r15 = r4
        L_0x0171:
            com.google.android.gms.internal.ads.zzhs r16 = com.google.android.gms.internal.ads.zzhs.zzhm()     // Catch:{ Exception -> 0x01f4 }
            r10 = r3
            com.google.android.gms.internal.ads.zzaqw r0 = com.google.android.gms.internal.ads.zzarc.zza(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)     // Catch:{ Exception -> 0x01f4 }
            r1.zzbnd = r0     // Catch:{ Exception -> 0x01f4 }
            com.google.android.gms.internal.ads.zzasc r5 = r0.zzuf()
            r6 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn
            com.google.android.gms.ads.internal.gmsg.zzb r7 = r0.zzbyx
            r8 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn
            com.google.android.gms.ads.internal.gmsg.zzd r9 = r0.zzbyp
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn
            com.google.android.gms.ads.internal.overlay.zzt r10 = r0.zzbyt
            r11 = 1
            r12 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn
            com.google.android.gms.internal.ads.zzaqw r0 = r0.zzbyo
            if (r0 == 0) goto L_0x01a2
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn
            com.google.android.gms.internal.ads.zzaqw r0 = r0.zzbyo
            com.google.android.gms.internal.ads.zzasc r0 = r0.zzuf()
            com.google.android.gms.ads.internal.zzx r4 = r0.zzut()
        L_0x01a2:
            r13 = r4
            r14 = 0
            r15 = 0
            r5.zza(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            com.google.android.gms.internal.ads.zzaqw r0 = r1.zzbnd
            com.google.android.gms.internal.ads.zzasc r0 = r0.zzuf()
            com.google.android.gms.ads.internal.overlay.zze r4 = new com.google.android.gms.ads.internal.overlay.zze
            r4.<init>(r1)
            r0.zza((com.google.android.gms.internal.ads.zzasd) r4)
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn
            java.lang.String r0 = r0.url
            if (r0 == 0) goto L_0x01c6
            com.google.android.gms.internal.ads.zzaqw r0 = r1.zzbnd
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r4 = r1.zzbxn
            java.lang.String r4 = r4.url
            r0.loadUrl(r4)
            goto L_0x01de
        L_0x01c6:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn
            java.lang.String r0 = r0.zzbys
            if (r0 == 0) goto L_0x01ec
            com.google.android.gms.internal.ads.zzaqw r4 = r1.zzbnd
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn
            java.lang.String r5 = r0.zzbyq
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn
            java.lang.String r6 = r0.zzbys
            r9 = 0
            java.lang.String r7 = "text/html"
            java.lang.String r8 = "UTF-8"
            r4.loadDataWithBaseURL(r5, r6, r7, r8, r9)
        L_0x01de:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn
            com.google.android.gms.internal.ads.zzaqw r0 = r0.zzbyo
            if (r0 == 0) goto L_0x020d
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn
            com.google.android.gms.internal.ads.zzaqw r0 = r0.zzbyo
            r0.zzb((com.google.android.gms.ads.internal.overlay.zzd) r1)
            goto L_0x020d
        L_0x01ec:
            com.google.android.gms.ads.internal.overlay.zzg r0 = new com.google.android.gms.ads.internal.overlay.zzg
            java.lang.String r2 = "No URL or HTML to display in ad overlay."
            r0.<init>(r2)
            throw r0
        L_0x01f4:
            r0 = move-exception
            java.lang.String r2 = "Error obtaining webview."
            com.google.android.gms.internal.ads.zzakb.zzb(r2, r0)
            com.google.android.gms.ads.internal.overlay.zzg r0 = new com.google.android.gms.ads.internal.overlay.zzg
            java.lang.String r2 = "Could not obtain webview for the overlay."
            r0.<init>(r2)
            throw r0
        L_0x0202:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r1.zzbxn
            com.google.android.gms.internal.ads.zzaqw r0 = r0.zzbyo
            r1.zzbnd = r0
            android.app.Activity r4 = r1.mActivity
            r0.zzbm(r4)
        L_0x020d:
            com.google.android.gms.internal.ads.zzaqw r0 = r1.zzbnd
            r0.zza((com.google.android.gms.ads.internal.overlay.zzd) r1)
            com.google.android.gms.internal.ads.zzaqw r0 = r1.zzbnd
            android.view.ViewParent r0 = r0.getParent()
            if (r0 == 0) goto L_0x0229
            boolean r4 = r0 instanceof android.view.ViewGroup
            if (r4 == 0) goto L_0x0229
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            com.google.android.gms.internal.ads.zzaqw r4 = r1.zzbnd
            android.view.View r4 = r4.getView()
            r0.removeView(r4)
        L_0x0229:
            boolean r0 = r1.zzbxu
            if (r0 == 0) goto L_0x0232
            com.google.android.gms.internal.ads.zzaqw r0 = r1.zzbnd
            r0.zzur()
        L_0x0232:
            com.google.android.gms.ads.internal.overlay.zzh r0 = r1.zzbxv
            com.google.android.gms.internal.ads.zzaqw r4 = r1.zzbnd
            android.view.View r4 = r4.getView()
            r5 = -1
            r0.addView(r4, r5, r5)
            if (r18 != 0) goto L_0x0247
            boolean r0 = r1.zzbxw
            if (r0 != 0) goto L_0x0247
            r17.zzno()
        L_0x0247:
            r1.zzs(r3)
            com.google.android.gms.internal.ads.zzaqw r0 = r1.zzbnd
            boolean r0 = r0.zzuh()
            if (r0 == 0) goto L_0x0255
            r1.zza((boolean) r3, (boolean) r2)
        L_0x0255:
            return
        L_0x0256:
            com.google.android.gms.ads.internal.overlay.zzg r0 = new com.google.android.gms.ads.internal.overlay.zzg
            java.lang.String r2 = "Invalid activity, no window available."
            r0.<init>(r2)
            goto L_0x025f
        L_0x025e:
            throw r0
        L_0x025f:
            goto L_0x025e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zzd.zzt(boolean):void");
    }

    public final void close() {
        this.zzbxx = 2;
        this.mActivity.finish();
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
    }

    public final void onBackPressed() {
        this.zzbxx = 0;
    }

    public void onCreate(Bundle bundle) {
        this.mActivity.requestWindowFeature(1);
        this.zzbxt = bundle != null ? bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false) : false;
        try {
            AdOverlayInfoParcel zzc = AdOverlayInfoParcel.zzc(this.mActivity.getIntent());
            this.zzbxn = zzc;
            if (zzc != null) {
                if (zzc.zzacr.zzcvf > 7500000) {
                    this.zzbxx = 3;
                }
                if (this.mActivity.getIntent() != null) {
                    this.zzbye = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
                }
                if (this.zzbxn.zzbyw != null) {
                    this.zzbxu = this.zzbxn.zzbyw.zzze;
                } else {
                    this.zzbxu = false;
                }
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbbg)).booleanValue() && this.zzbxu && this.zzbxn.zzbyw.zzzj != -1) {
                    new zzj(this, (zzf) null).zzqo();
                }
                if (bundle == null) {
                    if (this.zzbxn.zzbyn != null && this.zzbye) {
                        this.zzbxn.zzbyn.zzcc();
                    }
                    if (!(this.zzbxn.zzbyu == 1 || this.zzbxn.zzbym == null)) {
                        this.zzbxn.zzbym.onAdClicked();
                    }
                }
                zzh zzh = new zzh(this.mActivity, this.zzbxn.zzbyv, this.zzbxn.zzacr.zzcw);
                this.zzbxv = zzh;
                zzh.setId(1000);
                int i = this.zzbxn.zzbyu;
                if (i == 1) {
                    zzt(false);
                } else if (i == 2) {
                    this.zzbxo = new zzi(this.zzbxn.zzbyo);
                    zzt(false);
                } else if (i == 3) {
                    zzt(true);
                } else {
                    throw new zzg("Could not determine ad overlay type.");
                }
            } else {
                throw new zzg("Could not get info for ad overlay.");
            }
        } catch (zzg e) {
            zzakb.zzdk(e.getMessage());
            this.zzbxx = 3;
            this.mActivity.finish();
        }
    }

    public final void onDestroy() {
        zzaqw zzaqw = this.zzbnd;
        if (zzaqw != null) {
            this.zzbxv.removeView(zzaqw.getView());
        }
        zznl();
    }

    public final void onPause() {
        zznh();
        if (this.zzbxn.zzbyn != null) {
            this.zzbxn.zzbyn.onPause();
        }
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbem)).booleanValue() && this.zzbnd != null && (!this.mActivity.isFinishing() || this.zzbxo == null)) {
            zzbv.zzem();
            zzakq.zzi(this.zzbnd);
        }
        zznl();
    }

    public final void onRestart() {
    }

    public final void onResume() {
        if (this.zzbxn.zzbyn != null) {
            this.zzbxn.zzbyn.onResume();
        }
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbem)).booleanValue()) {
            zzaqw zzaqw = this.zzbnd;
            if (zzaqw == null || zzaqw.isDestroyed()) {
                zzakb.zzdk("The webview does not exist. Ignoring action.");
                return;
            }
            zzbv.zzem();
            zzakq.zzj(this.zzbnd);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzbxt);
    }

    public final void onStart() {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbem)).booleanValue()) {
            zzaqw zzaqw = this.zzbnd;
            if (zzaqw == null || zzaqw.isDestroyed()) {
                zzakb.zzdk("The webview does not exist. Ignoring action.");
                return;
            }
            zzbv.zzem();
            zzakq.zzj(this.zzbnd);
        }
    }

    public final void onStop() {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbem)).booleanValue() && this.zzbnd != null && (!this.mActivity.isFinishing() || this.zzbxo == null)) {
            zzbv.zzem();
            zzakq.zzi(this.zzbnd);
        }
        zznl();
    }

    public final void setRequestedOrientation(int i) {
        if (this.mActivity.getApplicationInfo().targetSdkVersion >= ((Integer) zzkb.zzik().zzd(zznk.zzbfs)).intValue()) {
            if (this.mActivity.getApplicationInfo().targetSdkVersion <= ((Integer) zzkb.zzik().zzd(zznk.zzbft)).intValue()) {
                if (Build.VERSION.SDK_INT >= ((Integer) zzkb.zzik().zzd(zznk.zzbfu)).intValue()) {
                    if (Build.VERSION.SDK_INT <= ((Integer) zzkb.zzik().zzd(zznk.zzbfv)).intValue()) {
                        return;
                    }
                }
            }
        }
        this.mActivity.setRequestedOrientation(i);
    }

    public final void zza(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        FrameLayout frameLayout = new FrameLayout(this.mActivity);
        this.zzbxr = frameLayout;
        frameLayout.setBackgroundColor(DrawableConstants.CtaButton.BACKGROUND_COLOR);
        this.zzbxr.addView(view, -1, -1);
        this.mActivity.setContentView(this.zzbxr);
        this.zzbyb = true;
        this.zzbxs = customViewCallback;
        this.zzbxq = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0014, code lost:
        r0 = r6.zzbxn;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(boolean r7, boolean r8) {
        /*
            r6 = this;
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzays
            com.google.android.gms.internal.ads.zzni r1 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r6.zzbxn
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.ads.internal.zzaq r0 = r0.zzbyw
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r6.zzbxn
            com.google.android.gms.ads.internal.zzaq r0 = r0.zzbyw
            boolean r0 = r0.zzzl
            if (r0 == 0) goto L_0x0026
            r0 = 1
            goto L_0x0027
        L_0x0026:
            r0 = 0
        L_0x0027:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.zznk.zzayt
            com.google.android.gms.internal.ads.zzni r4 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r3 = r4.zzd(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x004b
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r6.zzbxn
            if (r3 == 0) goto L_0x004b
            com.google.android.gms.ads.internal.zzaq r3 = r3.zzbyw
            if (r3 == 0) goto L_0x004b
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r6.zzbxn
            com.google.android.gms.ads.internal.zzaq r3 = r3.zzbyw
            boolean r3 = r3.zzzm
            if (r3 == 0) goto L_0x004b
            r3 = 1
            goto L_0x004c
        L_0x004b:
            r3 = 0
        L_0x004c:
            if (r7 == 0) goto L_0x0062
            if (r8 == 0) goto L_0x0062
            if (r0 == 0) goto L_0x0062
            if (r3 != 0) goto L_0x0062
            com.google.android.gms.internal.ads.zzaal r7 = new com.google.android.gms.internal.ads.zzaal
            com.google.android.gms.internal.ads.zzaqw r4 = r6.zzbnd
            java.lang.String r5 = "useCustomClose"
            r7.<init>(r4, r5)
            java.lang.String r4 = "Custom close has been disabled for interstitial ads in this ad slot."
            r7.zzbw(r4)
        L_0x0062:
            com.google.android.gms.ads.internal.overlay.zzo r7 = r6.zzbxp
            if (r7 == 0) goto L_0x0071
            if (r3 != 0) goto L_0x006e
            if (r8 == 0) goto L_0x006d
            if (r0 != 0) goto L_0x006d
            goto L_0x006e
        L_0x006d:
            r1 = 0
        L_0x006e:
            r7.zzu(r1)
        L_0x0071:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zzd.zza(boolean, boolean):void");
    }

    public final void zzax() {
        this.zzbyb = true;
    }

    public final void zznh() {
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzbxn;
        if (adOverlayInfoParcel != null && this.zzbxq) {
            setRequestedOrientation(adOverlayInfoParcel.orientation);
        }
        if (this.zzbxr != null) {
            this.mActivity.setContentView(this.zzbxv);
            this.zzbyb = true;
            this.zzbxr.removeAllViews();
            this.zzbxr = null;
        }
        WebChromeClient.CustomViewCallback customViewCallback = this.zzbxs;
        if (customViewCallback != null) {
            customViewCallback.onCustomViewHidden();
            this.zzbxs = null;
        }
        this.zzbxq = false;
    }

    public final void zzni() {
        this.zzbxx = 1;
        this.mActivity.finish();
    }

    public final boolean zznj() {
        this.zzbxx = 0;
        zzaqw zzaqw = this.zzbnd;
        if (zzaqw == null) {
            return true;
        }
        boolean zzul = zzaqw.zzul();
        if (!zzul) {
            this.zzbnd.zza("onbackblocked", (Map<String, ?>) Collections.emptyMap());
        }
        return zzul;
    }

    public final void zznk() {
        this.zzbxv.removeView(this.zzbxp);
        zzs(true);
    }

    /* access modifiers changed from: package-private */
    public final void zznm() {
        if (!this.zzbyd) {
            this.zzbyd = true;
            zzaqw zzaqw = this.zzbnd;
            if (zzaqw != null) {
                this.zzbxv.removeView(zzaqw.getView());
                zzi zzi = this.zzbxo;
                if (zzi != null) {
                    this.zzbnd.zzbm(zzi.zzrt);
                    this.zzbnd.zzai(false);
                    this.zzbxo.parent.addView(this.zzbnd.getView(), this.zzbxo.index, this.zzbxo.zzbyi);
                    this.zzbxo = null;
                } else if (this.mActivity.getApplicationContext() != null) {
                    this.zzbnd.zzbm(this.mActivity.getApplicationContext());
                }
                this.zzbnd = null;
            }
            AdOverlayInfoParcel adOverlayInfoParcel = this.zzbxn;
            if (adOverlayInfoParcel != null && adOverlayInfoParcel.zzbyn != null) {
                this.zzbxn.zzbyn.zzcb();
            }
        }
    }

    public final void zznn() {
        if (this.zzbxw) {
            this.zzbxw = false;
            zzno();
        }
    }

    public final void zznp() {
        this.zzbxv.zzbyh = true;
    }

    public final void zznq() {
        synchronized (this.zzbxy) {
            this.zzbya = true;
            if (this.zzbxz != null) {
                zzakk.zzcrm.removeCallbacks(this.zzbxz);
                zzakk.zzcrm.post(this.zzbxz);
            }
        }
    }

    public final void zzo(IObjectWrapper iObjectWrapper) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbel)).booleanValue() && PlatformVersion.isAtLeastN()) {
            zzbv.zzek();
            if (zzakk.zza(this.mActivity, (Configuration) ObjectWrapper.unwrap(iObjectWrapper))) {
                this.mActivity.getWindow().addFlags(d.fb);
                this.mActivity.getWindow().clearFlags(2048);
                return;
            }
            this.mActivity.getWindow().addFlags(2048);
            this.mActivity.getWindow().clearFlags(d.fb);
        }
    }
}
