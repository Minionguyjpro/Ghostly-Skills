package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi;
import java.security.GeneralSecurityException;

final class zzauw implements zzaug<zzatz> {
    zzauw() {
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzatz zza(zzbah zzbah) throws GeneralSecurityException {
        try {
            zzawi zzu = zzawi.zzu(zzbah);
            if (zzu instanceof zzawi) {
                zzawi zzawi = zzu;
                zzazq.zzj(zzawi.getVersion(), 0);
                if (zzawi.zzwv().size() == 32) {
                    return new zzaym(zzawi.zzwv().toByteArray());
                }
                throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
            }
            throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
        } catch (zzbbu e) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305 key", e);
        }
    }

    private static zzawi zzwl() throws GeneralSecurityException {
        return (zzawi) zzawi.zzxn().zzap(0).zzv(zzbah.zzo(zzazl.zzbh(32))).zzadi();
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzawi) {
            zzawi zzawi = (zzawi) zzbcu;
            zzazq.zzj(zzawi.getVersion(), 0);
            if (zzawi.zzwv().size() == 32) {
                return new zzaym(zzawi.zzwv().toByteArray());
            }
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        }
        throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
    }

    public final zzbcu zzb(zzbah zzbah) throws GeneralSecurityException {
        return zzwl();
    }

    public final zzbcu zzb(zzbcu zzbcu) throws GeneralSecurityException {
        return zzwl();
    }

    public final zzaxi zzc(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key").zzai(zzwl().zzaav()).zzb(zzaxi.zzb.SYMMETRIC).zzadi();
    }
}
