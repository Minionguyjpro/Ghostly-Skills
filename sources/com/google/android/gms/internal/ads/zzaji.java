package com.google.android.gms.internal.ads;

import org.json.JSONObject;

@zzadh
public final class zzaji {
    public final int errorCode;
    public final zzjn zzacv;
    public final zzaef zzcgs;
    public final JSONObject zzcob;
    public final zzwy zzcod;
    public final long zzcoh;
    public final long zzcoi;
    public final zzhs zzcoq;
    public final boolean zzcor;
    public final zzaej zzcos;

    public zzaji(zzaef zzaef, zzaej zzaej, zzwy zzwy, zzjn zzjn, int i, long j, long j2, JSONObject jSONObject, zzhs zzhs, Boolean bool) {
        this.zzcgs = zzaef;
        this.zzcos = zzaej;
        this.zzcod = zzwy;
        this.zzacv = zzjn;
        this.errorCode = i;
        this.zzcoh = j;
        this.zzcoi = j2;
        this.zzcob = jSONObject;
        this.zzcoq = zzhs;
        this.zzcor = bool != null ? bool.booleanValue() : zzamm.zzo(zzaef.zzccv);
    }

    public zzaji(zzaef zzaef, zzaej zzaej, zzwy zzwy, zzjn zzjn, int i, long j, long j2, JSONObject jSONObject, zzhx zzhx) {
        this.zzcgs = zzaef;
        this.zzcos = zzaej;
        this.zzcod = null;
        this.zzacv = null;
        this.errorCode = i;
        this.zzcoh = j;
        this.zzcoi = j2;
        this.zzcob = null;
        this.zzcoq = new zzhs(zzhx);
        this.zzcor = false;
    }
}
