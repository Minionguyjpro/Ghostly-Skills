package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzamb extends zzr<zzp> {
    private final zzaoj<zzp> zzctn;
    private final Map<String, String> zzcto;
    private final zzamy zzctp;

    public zzamb(String str, zzaoj<zzp> zzaoj) {
        this(str, (Map<String, String>) null, zzaoj);
    }

    private zzamb(String str, Map<String, String> map, zzaoj<zzp> zzaoj) {
        super(0, str, new zzamc(zzaoj));
        this.zzcto = null;
        this.zzctn = zzaoj;
        zzamy zzamy = new zzamy();
        this.zzctp = zzamy;
        zzamy.zza(str, "GET", (Map<String, ?>) null, (byte[]) null);
    }

    /* access modifiers changed from: protected */
    public final zzx<zzp> zza(zzp zzp) {
        return zzx.zza(zzp, zzap.zzb(zzp));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(Object obj) {
        zzp zzp = (zzp) obj;
        this.zzctp.zza((Map<String, ?>) zzp.zzab, zzp.statusCode);
        zzamy zzamy = this.zzctp;
        byte[] bArr = zzp.data;
        if (zzamy.isEnabled() && bArr != null) {
            zzamy.zzf(bArr);
        }
        this.zzctn.set(zzp);
    }
}
