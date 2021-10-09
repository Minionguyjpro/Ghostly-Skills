package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.zzbc;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzx;
import org.json.JSONObject;

@zzadh
public final class zzacq implements zzacm<zzaqw> {
    private final Context mContext;
    /* access modifiers changed from: private */
    public String zzaae;
    private final zzci zzbjc;
    private final zzbc zzcbc;
    private zzanz<zzaqw> zzcbm;
    private final zzab zzcbn = new zzab(this.mContext);
    private final zzpe zzcbo;
    private final zzang zzzw;

    public zzacq(Context context, zzbc zzbc, String str, zzci zzci, zzang zzang) {
        zzakb.zzdj("Webview loading for native ads.");
        this.mContext = context;
        this.zzcbc = zzbc;
        this.zzbjc = zzci;
        this.zzzw = zzang;
        this.zzaae = str;
        zzbv.zzel();
        zzanz<zzaqw> zza = zzarc.zza(this.mContext, this.zzzw, (String) zzkb.zzik().zzd(zznk.zzbbp), this.zzbjc, this.zzcbc.zzbi());
        this.zzcbo = new zzpe(zzbc, str);
        zzanz<zzaqw> zza2 = zzano.zza(zza, new zzacr(this), zzaoe.zzcvz);
        this.zzcbm = zza2;
        zzanm.zza(zza2, "WebViewNativeAdsUtil.constructor");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzanz zza(JSONObject jSONObject, zzaqw zzaqw) throws Exception {
        jSONObject.put("ads_id", this.zzaae);
        zzaqw.zzb("google.afma.nativeAds.handleDownloadedImpressionPing", jSONObject);
        return zzano.zzi(new JSONObject());
    }

    public final void zza(String str, zzv<? super zzaqw> zzv) {
        zzano.zza(this.zzcbm, new zzacx(this, str, zzv), zzaoe.zzcvy);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzano.zza(this.zzcbm, new zzacz(this, str, jSONObject), zzaoe.zzcvy);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzanz zzb(JSONObject jSONObject, zzaqw zzaqw) throws Exception {
        jSONObject.put("ads_id", this.zzaae);
        zzaqw.zzb("google.afma.nativeAds.handleImpressionPing", jSONObject);
        return zzano.zzi(new JSONObject());
    }

    public final void zzb(String str, zzv<? super zzaqw> zzv) {
        zzano.zza(this.zzcbm, new zzacy(this, str, zzv), zzaoe.zzcvy);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzanz zzc(JSONObject jSONObject, zzaqw zzaqw) throws Exception {
        jSONObject.put("ads_id", this.zzaae);
        zzaqw.zzb("google.afma.nativeAds.handleClickGmsg", jSONObject);
        return zzano.zzi(new JSONObject());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzanz zzd(JSONObject jSONObject, zzaqw zzaqw) throws Exception {
        jSONObject.put("ads_id", this.zzaae);
        zzaoj zzaoj = new zzaoj();
        zzaqw.zza("/nativeAdPreProcess", (zzv<? super zzaqw>) new zzacw(this, zzaqw, zzaoj));
        zzaqw.zzb("google.afma.nativeAds.preProcessJsonGmsg", jSONObject);
        return zzaoj;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzanz zzh(zzaqw zzaqw) throws Exception {
        zzakb.zzdj("Javascript has loaded for native ads.");
        zzasc zzuf = zzaqw.zzuf();
        zzbc zzbc = this.zzcbc;
        zzuf.zza(zzbc, zzbc, zzbc, zzbc, zzbc, false, (zzz) null, new zzx(this.mContext, (zzait) null, (zzael) null), (zzaam) null, (zzait) null);
        zzaqw.zza("/logScionEvent", (zzv<? super zzaqw>) this.zzcbn);
        zzaqw.zza("/logScionEvent", (zzv<? super zzaqw>) this.zzcbo);
        return zzano.zzi(zzaqw);
    }

    public final zzanz<JSONObject> zzh(JSONObject jSONObject) {
        return zzano.zza(this.zzcbm, new zzacs(this, jSONObject), zzaoe.zzcvy);
    }

    public final zzanz<JSONObject> zzi(JSONObject jSONObject) {
        return zzano.zza(this.zzcbm, new zzact(this, jSONObject), zzaoe.zzcvy);
    }

    public final zzanz<JSONObject> zzj(JSONObject jSONObject) {
        return zzano.zza(this.zzcbm, new zzacu(this, jSONObject), zzaoe.zzcvy);
    }

    public final zzanz<JSONObject> zzk(JSONObject jSONObject) {
        return zzano.zza(this.zzcbm, new zzacv(this, jSONObject), zzaoe.zzcvy);
    }

    public final void zzmc() {
        zzano.zza(this.zzcbm, new zzada(this), zzaoe.zzcvy);
    }
}
