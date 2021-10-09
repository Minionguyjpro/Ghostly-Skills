package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi;
import java.security.GeneralSecurityException;

final class zzaut implements zzaug<zzazi> {
    zzaut() {
    }

    private static void zza(zzavw zzavw) throws GeneralSecurityException {
        if (zzavw.zzxb() < 12 || zzavw.zzxb() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zze */
    public final zzayh zza(zzbah zzbah) throws GeneralSecurityException {
        try {
            zzavs zzl = zzavs.zzl(zzbah);
            if (zzl instanceof zzavs) {
                zzavs zzavs = zzl;
                zzazq.zzj(zzavs.getVersion(), 0);
                zzazq.zzbi(zzavs.zzwv().size());
                zza(zzavs.zzwu());
                return new zzayh(zzavs.zzwv().toByteArray(), zzavs.zzwu().zzxb());
            }
            throw new GeneralSecurityException("expected AesCtrKey proto");
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized AesCtrKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzavs) {
            zzavs zzavs = (zzavs) zzbcu;
            zzazq.zzj(zzavs.getVersion(), 0);
            zzazq.zzbi(zzavs.zzwv().size());
            zza(zzavs.zzwu());
            return new zzayh(zzavs.zzwv().toByteArray(), zzavs.zzwu().zzxb());
        }
        throw new GeneralSecurityException("expected AesCtrKey proto");
    }

    public final zzbcu zzb(zzbah zzbah) throws GeneralSecurityException {
        try {
            return zzb((zzbcu) zzavu.zzn(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized AesCtrKeyFormat proto", e);
        }
    }

    public final zzbcu zzb(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzavu) {
            zzavu zzavu = (zzavu) zzbcu;
            zzazq.zzbi(zzavu.getKeySize());
            zza(zzavu.zzwu());
            return zzavs.zzww().zzc(zzavu.zzwu()).zzm(zzbah.zzo(zzazl.zzbh(zzavu.getKeySize()))).zzam(0).zzadi();
        }
        throw new GeneralSecurityException("expected AesCtrKeyFormat proto");
    }

    public final zzaxi zzc(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.AesCtrKey").zzai(((zzavs) zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.SYMMETRIC).zzadi();
    }
}
