package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public final class zzayx implements zzatz {
    private final zzazi zzdnv;
    private final zzauk zzdnw;
    private final int zzdnx;

    public zzayx(zzazi zzazi, zzauk zzauk, int i) {
        this.zzdnv = zzazi;
        this.zzdnw = zzauk;
        this.zzdnx = i;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] zzk = this.zzdnv.zzk(bArr);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        byte[] copyOf = Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long) bArr2.length) * 8).array(), 8);
        return zzayk.zza(zzk, this.zzdnw.zzg(zzayk.zza(bArr2, zzk, copyOf)));
    }
}
