package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import androidx.collection.ArrayMap;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzait;
import com.google.android.gms.internal.ads.zzaix;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzasg;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzfp;
import com.google.android.gms.internal.ads.zzft;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzy extends zzi implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private boolean zzvm;
    private boolean zzxf;
    private WeakReference<Object> zzxg = new WeakReference<>((Object) null);

    public zzy(Context context, zzjn zzjn, String str, zzxn zzxn, zzang zzang, zzw zzw) {
        super(context, zzjn, str, zzxn, zzang, zzw);
    }

    private final void zzc(zzaqw zzaqw) {
        WebView webView;
        View view;
        if (zzcp() && (webView = zzaqw.getWebView()) != null && (view = zzaqw.getView()) != null && zzbv.zzfa().zzi(this.zzvw.zzrt)) {
            int i = this.zzvw.zzacr.zzcve;
            int i2 = this.zzvw.zzacr.zzcvf;
            StringBuilder sb = new StringBuilder(23);
            sb.append(i);
            sb.append(".");
            sb.append(i2);
            this.zzwb = zzbv.zzfa().zza(sb.toString(), webView, "", "javascript", zzbz());
            if (this.zzwb != null) {
                zzbv.zzfa().zza(this.zzwb, view);
                zzbv.zzfa().zzm(this.zzwb);
                this.zzxf = true;
            }
        }
    }

    private final boolean zzd(zzajh zzajh, zzajh zzajh2) {
        if (zzajh2.zzceq) {
            View zze = zzas.zze(zzajh2);
            if (zze == null) {
                zzakb.zzdk("Could not get mediation view");
                return false;
            }
            View nextView = this.zzvw.zzacs.getNextView();
            if (nextView != null) {
                if (nextView instanceof zzaqw) {
                    ((zzaqw) nextView).destroy();
                }
                this.zzvw.zzacs.removeView(nextView);
            }
            if (!zzas.zzf(zzajh2)) {
                try {
                    if (zzbv.zzfh().zzt(this.zzvw.zzrt)) {
                        new zzfp(this.zzvw.zzrt, zze).zza((zzft) new zzaix(this.zzvw.zzrt, this.zzvw.zzacp));
                    }
                    if (zzajh2.zzcof != null) {
                        this.zzvw.zzacs.setMinimumWidth(zzajh2.zzcof.widthPixels);
                        this.zzvw.zzacs.setMinimumHeight(zzajh2.zzcof.heightPixels);
                    }
                    zzg(zze);
                } catch (Exception e) {
                    zzbv.zzeo().zza(e, "BannerAdManager.swapViews");
                    zzakb.zzc("Could not add mediation view to view hierarchy.", e);
                    return false;
                }
            }
        } else if (!(zzajh2.zzcof == null || zzajh2.zzbyo == null)) {
            zzajh2.zzbyo.zza(zzasi.zzb(zzajh2.zzcof));
            this.zzvw.zzacs.removeAllViews();
            this.zzvw.zzacs.setMinimumWidth(zzajh2.zzcof.widthPixels);
            this.zzvw.zzacs.setMinimumHeight(zzajh2.zzcof.heightPixels);
            zzg(zzajh2.zzbyo.getView());
        }
        if (this.zzvw.zzacs.getChildCount() > 1) {
            this.zzvw.zzacs.showNext();
        }
        if (zzajh != null) {
            View nextView2 = this.zzvw.zzacs.getNextView();
            if (nextView2 instanceof zzaqw) {
                ((zzaqw) nextView2).destroy();
            } else if (nextView2 != null) {
                this.zzvw.zzacs.removeView(nextView2);
            }
            this.zzvw.zzfn();
        }
        this.zzvw.zzacs.setVisibility(0);
        return true;
    }

    public final zzlo getVideoController() {
        Preconditions.checkMainThread("getVideoController must be called from the main thread.");
        if (this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null) {
            return null;
        }
        return this.zzvw.zzacw.zzbyo.zztm();
    }

    public final void onGlobalLayout() {
        zzd(this.zzvw.zzacw);
    }

    public final void onScrollChanged() {
        zzd(this.zzvw.zzacw);
    }

    public final void setManualImpressionsEnabled(boolean z) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzvm = z;
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }

    /* access modifiers changed from: protected */
    public final zzaqw zza(zzaji zzaji, zzx zzx, zzait zzait) throws zzarg {
        zzjn zzjn;
        AdSize adSize;
        if (this.zzvw.zzacv.zzard == null && this.zzvw.zzacv.zzarf) {
            zzbw zzbw = this.zzvw;
            if (zzaji.zzcos.zzarf) {
                zzjn = this.zzvw.zzacv;
            } else {
                String str = zzaji.zzcos.zzcet;
                if (str != null) {
                    String[] split = str.split("[xX]");
                    split[0] = split[0].trim();
                    split[1] = split[1].trim();
                    adSize = new AdSize(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                } else {
                    adSize = this.zzvw.zzacv.zzhy();
                }
                zzjn = new zzjn(this.zzvw.zzrt, adSize);
            }
            zzbw.zzacv = zzjn;
        }
        return super.zza(zzaji, zzx, zzait);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzajh zzajh, boolean z) {
        if (zzcp()) {
            zzaqw zzaqw = zzajh != null ? zzajh.zzbyo : null;
            if (zzaqw != null) {
                if (!this.zzxf) {
                    zzc(zzaqw);
                }
                if (this.zzwb != null) {
                    zzaqw.zza("onSdkImpression", (Map<String, ?>) new ArrayMap());
                }
            }
        }
        super.zza(zzajh, z);
        if (zzas.zzf(zzajh)) {
            zzac zzac = new zzac(this);
            if (zzajh != null && zzas.zzf(zzajh)) {
                zzaqw zzaqw2 = zzajh.zzbyo;
                View view = zzaqw2 != null ? zzaqw2.getView() : null;
                if (view == null) {
                    zzakb.zzdk("AdWebView is null");
                    return;
                }
                try {
                    List<String> list = zzajh.zzbtw != null ? zzajh.zzbtw.zzbsi : null;
                    if (list != null) {
                        if (!list.isEmpty()) {
                            zzxz zzmo = zzajh.zzbtx != null ? zzajh.zzbtx.zzmo() : null;
                            zzyc zzmp = zzajh.zzbtx != null ? zzajh.zzbtx.zzmp() : null;
                            if (list.contains(InternalAvidAdSessionContext.AVID_API_LEVEL) && zzmo != null) {
                                zzmo.zzk(ObjectWrapper.wrap(view));
                                if (!zzmo.getOverrideImpressionRecording()) {
                                    zzmo.recordImpression();
                                }
                                zzaqw2.zza("/nativeExpressViewClicked", (zzv<? super zzaqw>) zzas.zza(zzmo, (zzyc) null, zzac));
                                return;
                            } else if (!list.contains("1") || zzmp == null) {
                                zzakb.zzdk("No matching template id and mapper");
                                return;
                            } else {
                                zzmp.zzk(ObjectWrapper.wrap(view));
                                if (!zzmp.getOverrideImpressionRecording()) {
                                    zzmp.recordImpression();
                                }
                                zzaqw2.zza("/nativeExpressViewClicked", (zzv<? super zzaqw>) zzas.zza((zzxz) null, zzmp, zzac));
                                return;
                            }
                        }
                    }
                    zzakb.zzdk("No template ids present in mediation response");
                } catch (RemoteException e) {
                    zzakb.zzc("Error occurred while recording impression and registering for clicks", e);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007c, code lost:
        if (((java.lang.Boolean) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzbbo)).booleanValue() != false) goto L_0x007e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(com.google.android.gms.internal.ads.zzajh r5, com.google.android.gms.internal.ads.zzajh r6) {
        /*
            r4 = this;
            boolean r0 = super.zza((com.google.android.gms.internal.ads.zzajh) r5, (com.google.android.gms.internal.ads.zzajh) r6)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzvw
            boolean r0 = r0.zzfo()
            if (r0 == 0) goto L_0x0025
            boolean r5 = r4.zzd(r5, r6)
            if (r5 != 0) goto L_0x0025
            com.google.android.gms.internal.ads.zzhs r5 = r6.zzcoq
            if (r5 == 0) goto L_0x0021
            com.google.android.gms.internal.ads.zzhs r5 = r6.zzcoq
            com.google.android.gms.internal.ads.zzhu$zza$zzb r6 = com.google.android.gms.internal.ads.zzhu.zza.zzb.AD_FAILED_TO_LOAD
            r5.zza((com.google.android.gms.internal.ads.zzhu.zza.zzb) r6)
        L_0x0021:
            r4.zzi(r1)
            return r1
        L_0x0025:
            r4.zzb((com.google.android.gms.internal.ads.zzajh) r6, (boolean) r1)
            boolean r5 = r6.zzcfh
            r0 = 0
            if (r5 == 0) goto L_0x0064
            r4.zzd(r6)
            com.google.android.gms.ads.internal.zzbv.zzfg()
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            com.google.android.gms.ads.internal.zzbx r5 = r5.zzacs
            com.google.android.gms.internal.ads.zzaor.zza((android.view.View) r5, (android.view.ViewTreeObserver.OnGlobalLayoutListener) r4)
            com.google.android.gms.ads.internal.zzbv.zzfg()
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            com.google.android.gms.ads.internal.zzbx r5 = r5.zzacs
            com.google.android.gms.internal.ads.zzaor.zza((android.view.View) r5, (android.view.ViewTreeObserver.OnScrollChangedListener) r4)
            boolean r5 = r6.zzcoc
            if (r5 != 0) goto L_0x0081
            com.google.android.gms.ads.internal.zzab r5 = new com.google.android.gms.ads.internal.zzab
            r5.<init>(r4)
            com.google.android.gms.internal.ads.zzaqw r1 = r6.zzbyo
            if (r1 == 0) goto L_0x0058
            com.google.android.gms.internal.ads.zzaqw r1 = r6.zzbyo
            com.google.android.gms.internal.ads.zzasc r1 = r1.zzuf()
            goto L_0x0059
        L_0x0058:
            r1 = r0
        L_0x0059:
            if (r1 == 0) goto L_0x0081
            com.google.android.gms.ads.internal.zzz r2 = new com.google.android.gms.ads.internal.zzz
            r2.<init>(r6, r5)
            r1.zza((com.google.android.gms.internal.ads.zzasg) r2)
            goto L_0x0081
        L_0x0064:
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            boolean r5 = r5.zzfp()
            if (r5 == 0) goto L_0x007e
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r5 = com.google.android.gms.internal.ads.zznk.zzbbo
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r5 = r2.zzd(r5)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x0081
        L_0x007e:
            r4.zza((com.google.android.gms.internal.ads.zzajh) r6, (boolean) r1)
        L_0x0081:
            com.google.android.gms.internal.ads.zzaqw r5 = r6.zzbyo
            if (r5 == 0) goto L_0x00a5
            com.google.android.gms.internal.ads.zzaqw r5 = r6.zzbyo
            com.google.android.gms.internal.ads.zzarl r5 = r5.zztm()
            com.google.android.gms.internal.ads.zzaqw r1 = r6.zzbyo
            com.google.android.gms.internal.ads.zzasc r1 = r1.zzuf()
            if (r1 == 0) goto L_0x0096
            r1.zzuz()
        L_0x0096:
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzvw
            com.google.android.gms.internal.ads.zzmu r1 = r1.zzadk
            if (r1 == 0) goto L_0x00a5
            if (r5 == 0) goto L_0x00a5
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzvw
            com.google.android.gms.internal.ads.zzmu r1 = r1.zzadk
            r5.zzb(r1)
        L_0x00a5:
            boolean r5 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich()
            if (r5 == 0) goto L_0x0142
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            boolean r5 = r5.zzfo()
            if (r5 == 0) goto L_0x011e
            com.google.android.gms.internal.ads.zzaqw r5 = r6.zzbyo
            if (r5 == 0) goto L_0x0139
            org.json.JSONObject r5 = r6.zzcob
            if (r5 == 0) goto L_0x00c4
            com.google.android.gms.internal.ads.zzes r5 = r4.zzvy
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzvw
            com.google.android.gms.internal.ads.zzjn r0 = r0.zzacv
            r5.zza(r0, r6)
        L_0x00c4:
            com.google.android.gms.internal.ads.zzaqw r5 = r6.zzbyo
            android.view.View r0 = r5.getView()
            com.google.android.gms.internal.ads.zzfp r5 = new com.google.android.gms.internal.ads.zzfp
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzvw
            android.content.Context r1 = r1.zzrt
            r5.<init>(r1, r0)
            com.google.android.gms.internal.ads.zzaiy r1 = com.google.android.gms.ads.internal.zzbv.zzfh()
            com.google.android.gms.ads.internal.zzbw r2 = r4.zzvw
            android.content.Context r2 = r2.zzrt
            boolean r1 = r1.zzt(r2)
            if (r1 == 0) goto L_0x0103
            com.google.android.gms.internal.ads.zzjj r1 = r6.zzccv
            boolean r1 = zza((com.google.android.gms.internal.ads.zzjj) r1)
            if (r1 == 0) goto L_0x0103
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzvw
            java.lang.String r1 = r1.zzacp
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0103
            com.google.android.gms.internal.ads.zzaix r1 = new com.google.android.gms.internal.ads.zzaix
            com.google.android.gms.ads.internal.zzbw r2 = r4.zzvw
            android.content.Context r2 = r2.zzrt
            com.google.android.gms.ads.internal.zzbw r3 = r4.zzvw
            java.lang.String r3 = r3.zzacp
            r1.<init>(r2, r3)
            r5.zza((com.google.android.gms.internal.ads.zzft) r1)
        L_0x0103:
            boolean r1 = r6.zzfz()
            if (r1 == 0) goto L_0x010f
            com.google.android.gms.internal.ads.zzaqw r1 = r6.zzbyo
            r5.zza((com.google.android.gms.internal.ads.zzft) r1)
            goto L_0x0139
        L_0x010f:
            com.google.android.gms.internal.ads.zzaqw r1 = r6.zzbyo
            com.google.android.gms.internal.ads.zzasc r1 = r1.zzuf()
            com.google.android.gms.ads.internal.zzaa r2 = new com.google.android.gms.ads.internal.zzaa
            r2.<init>(r5, r6)
            r1.zza((com.google.android.gms.internal.ads.zzasf) r2)
            goto L_0x0139
        L_0x011e:
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            android.view.View r5 = r5.zzadu
            if (r5 == 0) goto L_0x0139
            org.json.JSONObject r5 = r6.zzcob
            if (r5 == 0) goto L_0x0139
            com.google.android.gms.internal.ads.zzes r5 = r4.zzvy
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzvw
            com.google.android.gms.internal.ads.zzjn r0 = r0.zzacv
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzvw
            android.view.View r1 = r1.zzadu
            r5.zza(r0, r6, r1)
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            android.view.View r0 = r5.zzadu
        L_0x0139:
            boolean r5 = r6.zzceq
            if (r5 != 0) goto L_0x0142
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            r5.zzj(r0)
        L_0x0142:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzy.zza(com.google.android.gms.internal.ads.zzajh, com.google.android.gms.internal.ads.zzajh):boolean");
    }

    public final boolean zzb(zzjj zzjj) {
        zzjj zzjj2 = zzjj;
        if (zzjj2.zzaqb != this.zzvm) {
            zzjj2 = new zzjj(zzjj2.versionCode, zzjj2.zzapw, zzjj2.extras, zzjj2.zzapx, zzjj2.zzapy, zzjj2.zzapz, zzjj2.zzaqa, zzjj2.zzaqb || this.zzvm, zzjj2.zzaqc, zzjj2.zzaqd, zzjj2.zzaqe, zzjj2.zzaqf, zzjj2.zzaqg, zzjj2.zzaqh, zzjj2.zzaqi, zzjj2.zzaqj, zzjj2.zzaqk, zzjj2.zzaql);
        }
        return super.zzb(zzjj2);
    }

    /* access modifiers changed from: protected */
    public final void zzbq() {
        zzaqw zzaqw = this.zzvw.zzacw != null ? this.zzvw.zzacw.zzbyo : null;
        if (!this.zzxf && zzaqw != null) {
            zzc(zzaqw);
        }
        super.zzbq();
    }

    /* access modifiers changed from: protected */
    public final boolean zzca() {
        boolean z;
        zzbv.zzek();
        if (!zzakk.zzl(this.zzvw.zzrt, "android.permission.INTERNET")) {
            zzkb.zzif().zza(this.zzvw.zzacs, this.zzvw.zzacv, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            z = false;
        } else {
            z = true;
        }
        zzbv.zzek();
        if (!zzakk.zzaj(this.zzvw.zzrt)) {
            zzkb.zzif().zza(this.zzvw.zzacs, this.zzvw.zzacv, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            z = false;
        }
        if (!z && this.zzvw.zzacs != null) {
            this.zzvw.zzacs.setVisibility(0);
        }
        return z;
    }

    public final void zzcz() {
        this.zzvv.zzdy();
    }

    /* access modifiers changed from: package-private */
    public final void zzd(zzajh zzajh) {
        if (zzajh != null && !zzajh.zzcoc && this.zzvw.zzacs != null && zzbv.zzek().zza((View) this.zzvw.zzacs, this.zzvw.zzrt) && this.zzvw.zzacs.getGlobalVisibleRect(new Rect(), (Point) null)) {
            if (!(zzajh == null || zzajh.zzbyo == null || zzajh.zzbyo.zzuf() == null)) {
                zzajh.zzbyo.zzuf().zza((zzasg) null);
            }
            zza(zzajh, false);
            zzajh.zzcoc = true;
        }
    }
}
