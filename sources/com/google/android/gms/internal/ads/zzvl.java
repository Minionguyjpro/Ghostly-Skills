package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

final class zzvl implements zzv<zzwb> {
    private final /* synthetic */ zzuu zzbqj;
    private final /* synthetic */ zzvf zzbqk;
    private final /* synthetic */ zzci zzbql;
    private final /* synthetic */ zzamk zzbqm;

    zzvl(zzvf zzvf, zzci zzci, zzuu zzuu, zzamk zzamk) {
        this.zzbqk = zzvf;
        this.zzbql = zzci;
        this.zzbqj = zzuu;
        this.zzbqm = zzamk;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        synchronized (this.zzbqk.mLock) {
            zzakb.zzdj("JS Engine is requesting an update");
            if (this.zzbqk.zzbqb == 0) {
                zzakb.zzdj("Starting reload.");
                int unused = this.zzbqk.zzbqb = 2;
                this.zzbqk.zza(this.zzbql);
            }
            this.zzbqj.zzb("/requestReload", (zzv) this.zzbqm.get());
        }
    }
}
