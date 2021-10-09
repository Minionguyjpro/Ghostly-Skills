package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzbfi {
    protected volatile int zzebt = -1;

    public static final <T extends zzbfi> T zza(T t, byte[] bArr) throws zzbfh {
        return zza(t, bArr, 0, bArr.length);
    }

    private static final <T extends zzbfi> T zza(T t, byte[] bArr, int i, int i2) throws zzbfh {
        try {
            zzbez zzi = zzbez.zzi(bArr, 0, i2);
            t.zza(zzi);
            zzi.zzbp(0);
            return t;
        } catch (zzbfh e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public static final byte[] zzb(zzbfi zzbfi) {
        int zzacw = zzbfi.zzacw();
        byte[] bArr = new byte[zzacw];
        try {
            zzbfa zzj = zzbfa.zzj(bArr, 0, zzacw);
            zzbfi.zza(zzj);
            zzj.zzacl();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public String toString() {
        return zzbfj.zzc(this);
    }

    public abstract zzbfi zza(zzbez zzbez) throws IOException;

    public void zza(zzbfa zzbfa) throws IOException {
    }

    public final int zzacw() {
        int zzr = zzr();
        this.zzebt = zzr;
        return zzr;
    }

    /* renamed from: zzago */
    public zzbfi clone() throws CloneNotSupportedException {
        return (zzbfi) super.clone();
    }

    /* access modifiers changed from: protected */
    public int zzr() {
        return 0;
    }
}
