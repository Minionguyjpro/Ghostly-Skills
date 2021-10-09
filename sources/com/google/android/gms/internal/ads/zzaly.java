package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzaly extends zzav {
    private final /* synthetic */ byte[] zzctk;
    private final /* synthetic */ Map zzctl;
    private final /* synthetic */ zzamy zzctm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaly(zzalt zzalt, int i, String str, zzz zzz, zzy zzy, byte[] bArr, Map map, zzamy zzamy) {
        super(i, str, zzz, zzy);
        this.zzctk = bArr;
        this.zzctl = map;
        this.zzctm = zzamy;
    }

    public final Map<String, String> getHeaders() throws zza {
        Map<String, String> map = this.zzctl;
        return map == null ? super.getHeaders() : map;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(Object obj) {
        zza((String) obj);
    }

    public final byte[] zzg() throws zza {
        byte[] bArr = this.zzctk;
        return bArr == null ? super.zzg() : bArr;
    }

    /* access modifiers changed from: protected */
    public final void zzh(String str) {
        this.zzctm.zzdg(str);
        super.zza(str);
    }
}
