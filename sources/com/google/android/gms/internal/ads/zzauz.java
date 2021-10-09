package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi;
import java.security.GeneralSecurityException;

final class zzauz implements zzaug<zzatz> {
    zzauz() {
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzatz zza(zzbah zzbah) throws GeneralSecurityException {
        try {
            zzaxz zzal = zzaxz.zzal(zzbah);
            if (zzal instanceof zzaxz) {
                zzaxz zzaxz = zzal;
                zzazq.zzj(zzaxz.getVersion(), 0);
                String zzaah = zzaxz.zzaae().zzaah();
                return new zzauy(zzaxz.zzaae().zzaai(), zzauj.zzdx(zzaah).zzdw(zzaah));
            }
            throw new GeneralSecurityException("expected KmsEnvelopeAeadKey proto");
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized KmSEnvelopeAeadKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzaxz) {
            zzaxz zzaxz = (zzaxz) zzbcu;
            zzazq.zzj(zzaxz.getVersion(), 0);
            String zzaah = zzaxz.zzaae().zzaah();
            return new zzauy(zzaxz.zzaae().zzaai(), zzauj.zzdx(zzaah).zzdw(zzaah));
        }
        throw new GeneralSecurityException("expected KmsEnvelopeAeadKey proto");
    }

    public final zzbcu zzb(zzbah zzbah) throws GeneralSecurityException {
        try {
            return zzb((zzbcu) zzayb.zzam(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized KmsEnvelopeAeadKeyFormat proto", e);
        }
    }

    public final zzbcu zzb(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzayb) {
            return zzaxz.zzaaf().zzb((zzayb) zzbcu).zzbf(0).zzadi();
        }
        throw new GeneralSecurityException("expected KmsEnvelopeAeadKeyFormat proto");
    }

    public final zzaxi zzc(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey").zzai(((zzaxz) zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.REMOTE).zzadi();
    }
}
