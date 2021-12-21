package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.Window;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.gmsg.zzah;
import com.google.android.gms.ads.internal.gmsg.zzai;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzaej;
import com.google.android.gms.internal.ads.zzafs;
import com.google.android.gms.internal.ads.zzago;
import com.google.android.gms.internal.ads.zzaig;
import com.google.android.gms.internal.ads.zzaiq;
import com.google.android.gms.internal.ads.zzait;
import com.google.android.gms.internal.ads.zzaix;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarc;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzasc;
import com.google.android.gms.internal.ads.zzasf;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzfp;
import com.google.android.gms.internal.ads.zzft;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzwx;
import com.google.android.gms.internal.ads.zzwy;
import com.google.android.gms.internal.ads.zzxn;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzal extends zzi implements zzai, zzz {
    private transient boolean zzyq;
    private int zzyr = -1;
    /* access modifiers changed from: private */
    public boolean zzys;
    /* access modifiers changed from: private */
    public float zzyt;
    /* access modifiers changed from: private */
    public boolean zzyu;
    private zzaix zzyv;
    private String zzyw;
    private final String zzyx;
    private final zzago zzyy;

    public zzal(Context context, zzjn zzjn, String str, zzxn zzxn, zzang zzang, zzw zzw) {
        super(context, zzjn, str, zzxn, zzang, zzw);
        boolean z = false;
        this.zzyq = false;
        if (zzjn != null && "reward_mb".equals(zzjn.zzarb)) {
            z = true;
        }
        this.zzyx = z ? "/Rewarded" : "/Interstitial";
        this.zzyy = z ? new zzago(this.zzvw, this.zzwh, new zzan(this), this, this) : null;
    }

    private static zzaji zzb(zzaji zzaji) {
        zzaji zzaji2 = zzaji;
        try {
            String jSONObject = zzafs.zzb(zzaji2.zzcos).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, zzaji2.zzcgs.zzacp);
            zzwx zzwx = new zzwx(jSONObject, (String) null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), (String) null, (String) null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), (String) null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), (String) null, (String) null, (String) null, (List<String>) null, (String) null, Collections.emptyList(), (String) null, -1);
            zzaej zzaej = zzaji2.zzcos;
            zzwy zzwy = new zzwy(Collections.singletonList(zzwx), ((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), zzaej.zzbsr, zzaej.zzbss, "", -1, 0, 1, (String) null, 0, -1, -1, false);
            return new zzaji(zzaji2.zzcgs, new zzaej(zzaji2.zzcgs, zzaej.zzbyq, zzaej.zzceo, Collections.emptyList(), Collections.emptyList(), zzaej.zzcep, true, zzaej.zzcer, Collections.emptyList(), zzaej.zzbsu, zzaej.orientation, zzaej.zzcet, zzaej.zzceu, zzaej.zzcev, zzaej.zzcew, zzaej.zzcex, (String) null, zzaej.zzcez, zzaej.zzare, zzaej.zzcdd, zzaej.zzcfa, zzaej.zzcfb, zzaej.zzamj, zzaej.zzarf, zzaej.zzarg, (zzaig) null, Collections.emptyList(), Collections.emptyList(), zzaej.zzcfh, zzaej.zzcfi, zzaej.zzcdr, zzaej.zzcds, zzaej.zzbsr, zzaej.zzbss, zzaej.zzcfj, (zzaiq) null, zzaej.zzcfl, zzaej.zzcfm, zzaej.zzced, zzaej.zzzl, 0, zzaej.zzcfp, Collections.emptyList(), zzaej.zzzm, zzaej.zzcfq), zzwy, zzaji2.zzacv, zzaji2.errorCode, zzaji2.zzcoh, zzaji2.zzcoi, (JSONObject) null, zzaji2.zzcoq, (Boolean) null);
        } catch (JSONException e) {
            zzakb.zzb("Unable to generate ad state for an interstitial ad with pooling.", e);
            return zzaji2;
        }
    }

    private final void zzb(Bundle bundle) {
        zzbv.zzek().zzb(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, "gmob-apps", bundle, false);
    }

    private final boolean zzc(boolean z) {
        return this.zzyy != null && z;
    }

    public final void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzyu = z;
    }

    public final void showInterstitial() {
        Bitmap bitmap;
        Preconditions.checkMainThread("showInterstitial must be called on the main UI thread.");
        if (zzc(this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq)) {
            this.zzyy.zzw(this.zzyu);
            return;
        }
        if (zzbv.zzfh().zzv(this.zzvw.zzrt)) {
            String zzy = zzbv.zzfh().zzy(this.zzvw.zzrt);
            this.zzyw = zzy;
            String valueOf = String.valueOf(zzy);
            String valueOf2 = String.valueOf(this.zzyx);
            this.zzyw = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        if (this.zzvw.zzacw == null) {
            zzakb.zzdk("The interstitial has not loaded.");
            return;
        }
        if (((Boolean) zzkb.zzik().zzd(zznk.zzazx)).booleanValue()) {
            String packageName = (this.zzvw.zzrt.getApplicationContext() != null ? this.zzvw.zzrt.getApplicationContext() : this.zzvw.zzrt).getPackageName();
            if (!this.zzyq) {
                zzakb.zzdk("It is not recommended to show an interstitial before onAdLoaded completes.");
                Bundle bundle = new Bundle();
                bundle.putString("appid", packageName);
                bundle.putString("action", "show_interstitial_before_load_finish");
                zzb(bundle);
            }
            zzbv.zzek();
            if (!zzakk.zzaq(this.zzvw.zzrt)) {
                zzakb.zzdk("It is not recommended to show an interstitial when app is not in foreground.");
                Bundle bundle2 = new Bundle();
                bundle2.putString("appid", packageName);
                bundle2.putString("action", "show_interstitial_app_not_in_foreground");
                zzb(bundle2);
            }
        }
        if (!this.zzvw.zzfp()) {
            if (this.zzvw.zzacw.zzceq && this.zzvw.zzacw.zzbtx != null) {
                try {
                    if (((Boolean) zzkb.zzik().zzd(zznk.zzayr)).booleanValue()) {
                        this.zzvw.zzacw.zzbtx.setImmersiveMode(this.zzyu);
                    }
                    this.zzvw.zzacw.zzbtx.showInterstitial();
                } catch (RemoteException e) {
                    zzakb.zzc("Could not show interstitial.", e);
                    zzdj();
                }
            } else if (this.zzvw.zzacw.zzbyo == null) {
                zzakb.zzdk("The interstitial failed to load.");
            } else if (this.zzvw.zzacw.zzbyo.zzuj()) {
                zzakb.zzdk("The interstitial is already showing.");
            } else {
                this.zzvw.zzacw.zzbyo.zzai(true);
                this.zzvw.zzj(this.zzvw.zzacw.zzbyo.getView());
                if (this.zzvw.zzacw.zzcob != null) {
                    this.zzvy.zza(this.zzvw.zzacv, this.zzvw.zzacw);
                }
                if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                    zzajh zzajh = this.zzvw.zzacw;
                    if (zzajh.zzfz()) {
                        new zzfp(this.zzvw.zzrt, zzajh.zzbyo.getView()).zza((zzft) zzajh.zzbyo);
                    } else {
                        zzajh.zzbyo.zzuf().zza((zzasf) new zzam(this, zzajh));
                    }
                }
                if (this.zzvw.zzze) {
                    zzbv.zzek();
                    bitmap = zzakk.zzar(this.zzvw.zzrt);
                } else {
                    bitmap = null;
                }
                this.zzyr = zzbv.zzfe().zzb(bitmap);
                if (!((Boolean) zzkb.zzik().zzd(zznk.zzbbg)).booleanValue() || bitmap == null) {
                    zzaq zzaq = new zzaq(this.zzvw.zzze, zzdi(), false, 0.0f, -1, this.zzyu, this.zzvw.zzacw.zzzl, this.zzvw.zzacw.zzzm);
                    int requestedOrientation = this.zzvw.zzacw.zzbyo.getRequestedOrientation();
                    if (requestedOrientation == -1) {
                        requestedOrientation = this.zzvw.zzacw.orientation;
                    }
                    AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(this, this, this, this.zzvw.zzacw.zzbyo, requestedOrientation, this.zzvw.zzacr, this.zzvw.zzacw.zzcev, zzaq);
                    zzbv.zzei();
                    zzl.zza(this.zzvw.zzrt, adOverlayInfoParcel, true);
                    return;
                }
                new zzao(this, this.zzyr).zzqo();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final zzaqw zza(zzaji zzaji, zzx zzx, zzait zzait) throws zzarg {
        zzbv.zzel();
        zzaqw zza = zzarc.zza(this.zzvw.zzrt, zzasi.zzb(this.zzvw.zzacv), this.zzvw.zzacv.zzarb, false, false, this.zzvw.zzacq, this.zzvw.zzacr, this.zzvr, this, this.zzwc, zzaji.zzcoq);
        zza.zzuf().zza(this, this, (zzn) null, this, this, ((Boolean) zzkb.zzik().zzd(zznk.zzaxe)).booleanValue(), this, zzx, this, zzait);
        zza(zza);
        zza.zzdr(zzaji.zzcgs.zzcdi);
        zza.zza("/reward", (zzv<? super zzaqw>) new zzah(this));
        return zza;
    }

    public final void zza(zzaji zzaji, zznx zznx) {
        if (zzaji.errorCode != -2) {
            super.zza(zzaji, zznx);
            return;
        }
        if (zzc(zzaji.zzcod != null)) {
            this.zzyy.zzou();
            return;
        }
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzayy)).booleanValue()) {
            super.zza(zzaji, zznx);
            return;
        }
        boolean z = !zzaji.zzcos.zzceq;
        if (zza(zzaji.zzcgs.zzccv) && z) {
            this.zzvw.zzacx = zzb(zzaji);
        }
        super.zza(this.zzvw.zzacx, zznx);
    }

    public final void zza(boolean z, float f) {
        this.zzys = z;
        this.zzyt = f;
    }

    public final boolean zza(zzajh zzajh, zzajh zzajh2) {
        if (zzc(zzajh2.zzceq)) {
            return zzago.zza(zzajh, zzajh2);
        }
        if (!super.zza(zzajh, zzajh2)) {
            return false;
        }
        if (!(this.zzvw.zzfo() || this.zzvw.zzadu == null || zzajh2.zzcob == null)) {
            this.zzvy.zza(this.zzvw.zzacv, zzajh2, this.zzvw.zzadu);
        }
        zzb(zzajh2, false);
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzjj zzjj, zzajh zzajh, boolean z) {
        if (this.zzvw.zzfo() && zzajh.zzbyo != null) {
            zzbv.zzem();
            zzakq.zzi(zzajh.zzbyo);
        }
        return this.zzvv.zzdz();
    }

    public final boolean zza(zzjj zzjj, zznx zznx) {
        if (this.zzvw.zzacw != null) {
            zzakb.zzdk("An interstitial is already loading. Aborting.");
            return false;
        }
        if (this.zzyv == null && zza(zzjj) && zzbv.zzfh().zzv(this.zzvw.zzrt) && !TextUtils.isEmpty(this.zzvw.zzacp)) {
            this.zzyv = new zzaix(this.zzvw.zzrt, this.zzvw.zzacp);
        }
        return super.zza(zzjj, zznx);
    }

    public final void zzb(zzaig zzaig) {
        if (zzc(this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq)) {
            zza(this.zzyy.zzd(zzaig));
            return;
        }
        if (this.zzvw.zzacw != null) {
            if (this.zzvw.zzacw.zzcfg != null) {
                zzbv.zzek();
                zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw.zzcfg);
            }
            if (this.zzvw.zzacw.zzcfe != null) {
                zzaig = this.zzvw.zzacw.zzcfe;
            }
        }
        zza(zzaig);
    }

    /* access modifiers changed from: protected */
    public final void zzbn() {
        zzdj();
        super.zzbn();
    }

    /* access modifiers changed from: protected */
    public final void zzbq() {
        zzaqw zzaqw = this.zzvw.zzacw != null ? this.zzvw.zzacw.zzbyo : null;
        zzaji zzaji = this.zzvw.zzacx;
        if (!(zzaji == null || zzaji.zzcos == null || !zzaji.zzcos.zzcfp || zzaqw == null || !zzbv.zzfa().zzi(this.zzvw.zzrt))) {
            int i = this.zzvw.zzacr.zzcve;
            int i2 = this.zzvw.zzacr.zzcvf;
            StringBuilder sb = new StringBuilder(23);
            sb.append(i);
            sb.append(".");
            sb.append(i2);
            this.zzwb = zzbv.zzfa().zza(sb.toString(), zzaqw.getWebView(), "", "javascript", zzbz());
            if (!(this.zzwb == null || zzaqw.getView() == null)) {
                zzbv.zzfa().zza(this.zzwb, zzaqw.getView());
                zzbv.zzfa().zzm(this.zzwb);
            }
        }
        super.zzbq();
        this.zzyq = true;
    }

    public final void zzcb() {
        super.zzcb();
        this.zzvy.zzh(this.zzvw.zzacw);
        zzaix zzaix = this.zzyv;
        if (zzaix != null) {
            zzaix.zzx(false);
        }
        zzby();
    }

    public final void zzcc() {
        zzasc zzuf;
        recordImpression();
        super.zzcc();
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null || (zzuf = this.zzvw.zzacw.zzbyo.zzuf()) == null)) {
            zzuf.zzuz();
        }
        if (!(!zzbv.zzfh().zzv(this.zzvw.zzrt) || this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null)) {
            zzbv.zzfh().zzd(this.zzvw.zzacw.zzbyo.getContext(), this.zzyw);
        }
        zzaix zzaix = this.zzyv;
        if (zzaix != null) {
            zzaix.zzx(true);
        }
        if (this.zzwb != null && this.zzvw.zzacw != null && this.zzvw.zzacw.zzbyo != null) {
            this.zzvw.zzacw.zzbyo.zza("onSdkImpression", (Map<String, ?>) new HashMap());
        }
    }

    public final void zzcz() {
        zzd zzub = this.zzvw.zzacw.zzbyo.zzub();
        if (zzub != null) {
            zzub.close();
        }
    }

    public final void zzd(boolean z) {
        this.zzvw.zzze = z;
    }

    /* access modifiers changed from: protected */
    public final boolean zzdi() {
        Window window;
        if (!(!(this.zzvw.zzrt instanceof Activity) || (window = ((Activity) this.zzvw.zzrt).getWindow()) == null || window.getDecorView() == null)) {
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            window.getDecorView().getGlobalVisibleRect(rect, (Point) null);
            window.getDecorView().getWindowVisibleDisplayFrame(rect2);
            return (rect.bottom == 0 || rect2.bottom == 0 || rect.top != rect2.top) ? false : true;
        }
    }

    public final void zzdj() {
        zzbv.zzfe().zzb(Integer.valueOf(this.zzyr));
        if (this.zzvw.zzfo()) {
            this.zzvw.zzfm();
            this.zzvw.zzacw = null;
            this.zzvw.zzze = false;
            this.zzyq = false;
        }
    }

    public final void zzdk() {
        if (zzc(this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq)) {
            this.zzyy.zzov();
            zzbt();
            return;
        }
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzcog == null)) {
            zzbv.zzek();
            zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw.zzcog);
        }
        zzbt();
    }

    public final void zzdl() {
        if (zzc(this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq)) {
            this.zzyy.zzow();
        }
        zzbu();
    }
}
