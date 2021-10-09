package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi;
import com.google.android.gms.internal.ads.zzaxr;
import com.google.android.gms.internal.ads.zzaxt;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

final class zzaup {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static zzaxt zzb(zzaxr zzaxr) {
        zzaxt.zza zzbb = zzaxt.zzzu().zzbb(zzaxr.zzzk());
        for (zzaxr.zzb next : zzaxr.zzzl()) {
            zzbb.zzb((zzaxt.zzb) zzaxt.zzb.zzzw().zzeh(next.zzzp().zzyw()).zzb(next.zzzq()).zzb(next.zzzs()).zzbd(next.zzzr()).zzadi());
        }
        return (zzaxt) zzbb.zzadi();
    }

    public static void zzc(zzaxr zzaxr) throws GeneralSecurityException {
        if (zzaxr.zzzm() != 0) {
            int zzzk = zzaxr.zzzk();
            boolean z = false;
            boolean z2 = true;
            for (zzaxr.zzb next : zzaxr.zzzl()) {
                if (!next.zzzo()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(next.zzzr())}));
                } else if (next.zzzs() == zzayd.UNKNOWN_PREFIX) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(next.zzzr())}));
                } else if (next.zzzq() != zzaxl.UNKNOWN_STATUS) {
                    if (next.zzzq() == zzaxl.ENABLED && next.zzzr() == zzzk) {
                        if (!z) {
                            z = true;
                        } else {
                            throw new GeneralSecurityException("keyset contains multiple primary keys");
                        }
                    }
                    if (next.zzzp().zzyy() != zzaxi.zzb.ASYMMETRIC_PUBLIC) {
                        z2 = false;
                    }
                } else {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(next.zzzr())}));
                }
            }
            if (!z && !z2) {
                throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
            }
            return;
        }
        throw new GeneralSecurityException("empty keyset");
    }
}
