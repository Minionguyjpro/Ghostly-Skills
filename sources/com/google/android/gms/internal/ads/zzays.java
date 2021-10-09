package com.google.android.gms.internal.ads;

public final class zzays {
    private final zzazh zzdnk;
    private final zzazh zzdnl;

    public zzays(byte[] bArr, byte[] bArr2) {
        this.zzdnk = zzazh.zzm(bArr);
        this.zzdnl = zzazh.zzm(bArr2);
    }

    public final byte[] zzaap() {
        zzazh zzazh = this.zzdnk;
        if (zzazh == null) {
            return null;
        }
        return zzazh.getBytes();
    }

    public final byte[] zzaaq() {
        zzazh zzazh = this.zzdnl;
        if (zzazh == null) {
            return null;
        }
        return zzazh.getBytes();
    }
}
