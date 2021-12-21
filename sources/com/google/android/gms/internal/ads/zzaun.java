package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzaun<P> {
    private final P zzdhm;
    private final byte[] zzdhn;
    private final zzaxl zzdho;
    private final zzayd zzdhp;

    public zzaun(P p, byte[] bArr, zzaxl zzaxl, zzayd zzayd) {
        this.zzdhm = p;
        this.zzdhn = Arrays.copyOf(bArr, bArr.length);
        this.zzdho = zzaxl;
        this.zzdhp = zzayd;
    }

    public final P zzwi() {
        return this.zzdhm;
    }

    public final byte[] zzwj() {
        byte[] bArr = this.zzdhn;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }
}
