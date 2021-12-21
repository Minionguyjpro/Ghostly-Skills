package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.Predicate;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzuw implements zzuo, zzuu {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final zzaqw zzbnd;

    public zzuw(Context context, zzang zzang, zzci zzci, zzw zzw) throws zzarg {
        this.mContext = context;
        zzbv.zzel();
        zzaqw zza = zzarc.zza(context, zzasi.zzvq(), "", false, false, zzci, zzang, (zznx) null, (zzbo) null, (zzw) null, zzhs.zzhm());
        this.zzbnd = zza;
        zza.getView().setWillNotDraw(true);
    }

    private static void runOnUiThread(Runnable runnable) {
        zzkb.zzif();
        if (zzamu.zzsh()) {
            runnable.run();
        } else {
            zzakk.zzcrm.post(runnable);
        }
    }

    public final void destroy() {
        this.zzbnd.destroy();
    }

    public final void zza(zzuv zzuv) {
        zzasc zzuf = this.zzbnd.zzuf();
        zzuv.getClass();
        zzuf.zza(zzuz.zzb(zzuv));
    }

    public final void zza(String str, zzv<? super zzwb> zzv) {
        this.zzbnd.zza(str, (zzv<? super zzaqw>) new zzvd(this, zzv));
    }

    public final void zza(String str, Map map) {
        zzup.zza((zzuo) this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzup.zzb(this, str, jSONObject);
    }

    public final void zzb(String str, zzv<? super zzwb> zzv) {
        this.zzbnd.zza(str, (Predicate<zzv<? super zzaqw>>) new zzuy(zzv));
    }

    public final void zzb(String str, JSONObject jSONObject) {
        zzup.zza((zzuo) this, str, jSONObject);
    }

    public final void zzbb(String str) {
        runOnUiThread(new zzva(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", new Object[]{str})));
    }

    public final void zzbc(String str) {
        runOnUiThread(new zzvb(this, str));
    }

    public final void zzbd(String str) {
        runOnUiThread(new zzvc(this, str));
    }

    public final void zzbe(String str) {
        runOnUiThread(new zzux(this, str));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzbi(String str) {
        this.zzbnd.zzbe(str);
    }

    public final void zzf(String str, String str2) {
        zzup.zza((zzuo) this, str, str2);
    }

    public final zzwc zzlw() {
        return new zzwd(this);
    }
}
