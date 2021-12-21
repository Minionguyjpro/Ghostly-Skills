package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public final class zzauy implements zzatz {
    private static final byte[] zzdhv = new byte[0];
    private final zzaxn zzdhw;
    private final zzatz zzdhx;

    public zzauy(zzaxn zzaxn, zzatz zzatz) {
        this.zzdhw = zzaxn;
        this.zzdhx = zzatz;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] byteArray = zzauo.zzb(this.zzdhw).toByteArray();
        byte[] zzc = this.zzdhx.zzc(byteArray, zzdhv);
        byte[] zzc2 = ((zzatz) zzauo.zza(this.zzdhw.zzyw(), byteArray)).zzc(bArr, bArr2);
        return ByteBuffer.allocate(zzc.length + 4 + zzc2.length).putInt(zzc.length).put(zzc).put(zzc2).array();
    }
}
