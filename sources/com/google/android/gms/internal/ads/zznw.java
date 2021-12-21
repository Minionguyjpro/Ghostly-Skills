package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zznw {
    private final Map<String, zznv> zzbgm = new HashMap();
    private final zznx zzvr;

    public zznw(zznx zznx) {
        this.zzvr = zznx;
    }

    public final void zza(String str, zznv zznv) {
        this.zzbgm.put(str, zznv);
    }

    public final void zza(String str, String str2, long j) {
        zznx zznx = this.zzvr;
        zznv zznv = this.zzbgm.get(str2);
        String[] strArr = {str};
        if (!(zznx == null || zznv == null)) {
            zznx.zza(zznv, j, strArr);
        }
        Map<String, zznv> map = this.zzbgm;
        zznx zznx2 = this.zzvr;
        map.put(str, zznx2 == null ? null : zznx2.zzd(j));
    }

    public final zznx zzji() {
        return this.zzvr;
    }
}
