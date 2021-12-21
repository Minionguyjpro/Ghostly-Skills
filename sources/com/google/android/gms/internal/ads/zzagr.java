package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzagr extends zzd implements zzahu {
    private static zzagr zzcle;
    private boolean zzclf;
    private final zzago zzclg = new zzago(this.zzvw, this.zzwh, this, this, this);
    private boolean zzyu;
    private final zzaix zzyv;

    public zzagr(Context context, zzw zzw, zzjn zzjn, zzxn zzxn, zzang zzang) {
        super(context, zzjn, (String) null, zzxn, zzang, zzw);
        zzcle = this;
        this.zzyv = new zzaix(context, (String) null);
    }

    private static zzaji zzc(zzaji zzaji) {
        zzaji zzaji2 = zzaji;
        zzakb.v("Creating mediation ad response for non-mediated rewarded ad.");
        try {
            JSONObject zzb = zzafs.zzb(zzaji2.zzcos);
            zzb.remove("impression_urls");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, zzaji2.zzcgs.zzacp);
            zzwy zzwy = new zzwy(Arrays.asList(new zzwx[]{new zzwx(zzb.toString(), (String) null, Arrays.asList(new String[]{"com.google.ads.mediation.admob.AdMobAdapter"}), (String) null, (String) null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jSONObject.toString(), (String) null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), (String) null, (String) null, (String) null, (List<String>) null, (String) null, Collections.emptyList(), (String) null, -1)}), ((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1, 0, 1, (String) null, 0, -1, -1, false);
            return new zzaji(zzaji2.zzcgs, zzaji2.zzcos, zzwy, zzaji2.zzacv, zzaji2.errorCode, zzaji2.zzcoh, zzaji2.zzcoi, zzaji2.zzcob, zzaji2.zzcoq, (Boolean) null);
        } catch (JSONException e) {
            zzakb.zzb("Unable to generate ad state for non-mediated rewarded video.", e);
            return new zzaji(zzaji2.zzcgs, zzaji2.zzcos, (zzwy) null, zzaji2.zzacv, 0, zzaji2.zzcoh, zzaji2.zzcoi, zzaji2.zzcob, zzaji2.zzcoq, (Boolean) null);
        }
    }

    public static zzagr zzox() {
        return zzcle;
    }

    public final void destroy() {
        this.zzclg.destroy();
        super.destroy();
    }

    public final boolean isLoaded() {
        Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
        return this.zzvw.zzact == null && this.zzvw.zzacu == null && this.zzvw.zzacw != null;
    }

    public final void onContextChanged(Context context) {
        this.zzclg.onContextChanged(context);
    }

    public final void onRewardedVideoAdClosed() {
        if (zzbv.zzfh().zzw(this.zzvw.zzrt)) {
            this.zzyv.zzx(false);
        }
        zzbn();
    }

    public final void onRewardedVideoAdLeftApplication() {
        zzbo();
    }

    public final void onRewardedVideoAdOpened() {
        if (zzbv.zzfh().zzw(this.zzvw.zzrt)) {
            this.zzyv.zzx(true);
        }
        zza(this.zzvw.zzacw, false);
        zzbp();
    }

    public final void onRewardedVideoCompleted() {
        this.zzclg.zzow();
        zzbu();
    }

    public final void onRewardedVideoStarted() {
        this.zzclg.zzov();
        zzbt();
    }

    public final void pause() {
        this.zzclg.pause();
    }

    public final void resume() {
        this.zzclg.resume();
    }

    public final void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzyu = z;
    }

    public final void zza(zzahk zzahk) {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        if (TextUtils.isEmpty(zzahk.zzacp)) {
            zzakb.zzdk("Invalid ad unit id. Aborting.");
            zzakk.zzcrm.post(new zzags(this));
            return;
        }
        this.zzclf = false;
        this.zzvw.zzacp = zzahk.zzacp;
        this.zzyv.setAdUnitId(zzahk.zzacp);
        super.zzb(zzahk.zzccv);
    }

    public final void zza(zzaji zzaji, zznx zznx) {
        if (zzaji.errorCode != -2) {
            zzakk.zzcrm.post(new zzagt(this, zzaji));
            return;
        }
        this.zzvw.zzacx = zzaji;
        if (zzaji.zzcod == null) {
            this.zzvw.zzacx = zzc(zzaji);
        }
        this.zzclg.zzou();
    }

    public final boolean zza(zzajh zzajh, zzajh zzajh2) {
        zzb(zzajh2, false);
        return zzago.zza(zzajh, zzajh2);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzjj zzjj, zzajh zzajh, boolean z) {
        return false;
    }

    /* access modifiers changed from: protected */
    public final void zzbn() {
        this.zzvw.zzacw = null;
        super.zzbn();
    }

    public final void zzc(zzaig zzaig) {
        zzaig zzd = this.zzclg.zzd(zzaig);
        if (zzbv.zzfh().zzw(this.zzvw.zzrt) && zzd != null) {
            zzbv.zzfh().zza(this.zzvw.zzrt, zzbv.zzfh().zzab(this.zzvw.zzrt), this.zzvw.zzacp, zzd.type, zzd.zzcmk);
        }
        zza(zzd);
    }

    public final zzaib zzca(String str) {
        return this.zzclg.zzca(str);
    }

    public final void zzdm() {
        onAdClicked();
    }

    public final void zzoy() {
        Preconditions.checkMainThread("showAd must be called on the main UI thread.");
        if (!isLoaded()) {
            zzakb.zzdk("The reward video has not loaded.");
        } else {
            this.zzclg.zzw(this.zzyu);
        }
    }
}
