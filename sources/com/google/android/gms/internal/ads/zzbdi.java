package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

final class zzbdi implements zzbcs {
    private final String info;
    private final zzbcu zzdwl;
    private final zzbdj zzdxe;

    zzbdi(zzbcu zzbcu, String str, Object[] objArr) {
        this.zzdwl = zzbcu;
        this.info = str;
        this.zzdxe = new zzbdj(zzbcu.getClass(), str, objArr);
    }

    public final int getFieldCount() {
        return this.zzdxe.zzdxh;
    }

    public final int zzaeh() {
        return (this.zzdxe.flags & 1) == 1 ? zzbbo.zze.zzdui : zzbbo.zze.zzduj;
    }

    public final boolean zzaei() {
        return (this.zzdxe.flags & 2) == 2;
    }

    public final zzbcu zzaej() {
        return this.zzdwl;
    }

    /* access modifiers changed from: package-private */
    public final zzbdj zzaeq() {
        return this.zzdxe;
    }

    public final int zzaer() {
        return this.zzdxe.zzdwi;
    }

    public final int zzaes() {
        return this.zzdxe.zzdwj;
    }

    public final int zzaet() {
        return this.zzdxe.zzdxk;
    }

    public final int zzaeu() {
        return this.zzdxe.zzdxm;
    }

    /* access modifiers changed from: package-private */
    public final int[] zzaev() {
        return this.zzdxe.zzdwq;
    }

    public final int zzaew() {
        return this.zzdxe.zzdxl;
    }

    public final int zzaex() {
        return this.zzdxe.zzdwk;
    }
}
