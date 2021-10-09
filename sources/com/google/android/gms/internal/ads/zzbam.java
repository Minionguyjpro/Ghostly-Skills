package com.google.android.gms.internal.ads;

final class zzbam {
    private final byte[] buffer;
    private final zzbav zzdpv;

    private zzbam(int i) {
        byte[] bArr = new byte[i];
        this.buffer = bArr;
        this.zzdpv = zzbav.zzq(bArr);
    }

    /* synthetic */ zzbam(int i, zzbai zzbai) {
        this(i);
    }

    public final zzbah zzabi() {
        this.zzdpv.zzacl();
        return new zzbao(this.buffer);
    }

    public final zzbav zzabj() {
        return this.zzdpv;
    }
}
