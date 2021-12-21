package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi;
import java.security.GeneralSecurityException;

final class zzaux implements zzaug<zzatz> {
    zzaux() {
    }

    private static zzatz zzc(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzaxv) {
            zzaxv zzaxv = (zzaxv) zzbcu;
            zzazq.zzj(zzaxv.getVersion(), 0);
            String zzaab = zzaxv.zzzy().zzaab();
            return zzauj.zzdx(zzaab).zzdw(zzaab);
        }
        throw new GeneralSecurityException("expected KmsAeadKey proto");
    }

    private static zzatz zzd(zzbah zzbah) throws GeneralSecurityException {
        try {
            return zzc((zzbcu) zzaxv.zzaj(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected KmsAeadKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah zzbah) throws GeneralSecurityException {
        return zzd(zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu zzbcu) throws GeneralSecurityException {
        return zzc(zzbcu);
    }

    public final zzbcu zzb(zzbah zzbah) throws GeneralSecurityException {
        try {
            return zzb((zzbcu) zzaxx.zzak(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized KmsAeadKeyFormat proto", e);
        }
    }

    public final zzbcu zzb(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzaxx) {
            return zzaxv.zzzz().zzb((zzaxx) zzbcu).zzbe(0).zzadi();
        }
        throw new GeneralSecurityException("expected KmsAeadKeyFormat proto");
    }

    public final zzaxi zzc(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.KmsAeadKey").zzai(((zzaxv) zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.REMOTE).zzadi();
    }
}
