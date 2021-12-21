package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzuf extends zzus<zzwb> implements zzuo, zzuu {
    private final zzasv zzbpj;

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.android.gms.internal.ads.zzur, com.google.android.gms.internal.ads.zzuf] */
    public zzuf(Context context, zzang zzang) throws zzarg {
        try {
            zzasv zzasv = new zzasv(new zzash(context));
            this.zzbpj = zzasv;
            zzasv.setWillNotDraw(true);
            this.zzbpj.zza((zzasx) new zzug(this));
            this.zzbpj.zza((zzasz) new zzuh(this));
            this.zzbpj.addJavascriptInterface(new zzun(this), "GoogleJsInterface");
            zzbv.zzek().zza(context, zzang.zzcw, this.zzbpj.getSettings());
        } catch (Throwable th) {
            throw new zzarg("Init failed.", th);
        }
    }

    public final void destroy() {
        this.zzbpj.destroy();
    }

    public final /* bridge */ /* synthetic */ Object getReference() {
        return this;
    }

    public final void zza(zzuv zzuv) {
        this.zzbpj.zza((zzata) new zzuk(zzuv));
    }

    public final void zza(String str, Map map) {
        zzup.zza((zzuo) this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzup.zzb(this, str, jSONObject);
    }

    public final void zzb(String str, JSONObject jSONObject) {
        zzup.zza((zzuo) this, str, jSONObject);
    }

    public final void zzbb(String str) {
        zzbc(String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head></html>", new Object[]{str}));
    }

    public final void zzbc(String str) {
        zzaoe.zzcvy.execute(new zzui(this, str));
    }

    public final void zzbd(String str) {
        zzaoe.zzcvy.execute(new zzuj(this, str));
    }

    public final void zzbe(String str) {
        zzaoe.zzcvy.execute(new zzul(this, str));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzbf(String str) {
        this.zzbpj.zzbe(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzbg(String str) {
        this.zzbpj.loadUrl(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzbh(String str) {
        this.zzbpj.loadData(str, "text/html", "UTF-8");
    }

    public final void zzf(String str, String str2) {
        zzup.zza((zzuo) this, str, str2);
    }

    public final zzwc zzlw() {
        return new zzwd(this);
    }
}
