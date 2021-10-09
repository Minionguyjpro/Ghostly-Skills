package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi;
import java.security.GeneralSecurityException;
import java.util.logging.Logger;

class zzaus implements zzaug<zzatz> {
    private static final Logger logger = Logger.getLogger(zzaus.class.getName());

    zzaus() throws GeneralSecurityException {
        zzauo.zza("type.googleapis.com/google.crypto.tink.AesCtrKey", new zzaut());
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzatz zza(zzbah zzbah) throws GeneralSecurityException {
        try {
            zzavo zzi = zzavo.zzi(zzbah);
            if (zzi instanceof zzavo) {
                zzavo zzavo = zzi;
                zzazq.zzj(zzavo.getVersion(), 0);
                return new zzayx((zzazi) zzauo.zzb("type.googleapis.com/google.crypto.tink.AesCtrKey", zzavo.zzwn()), (zzauk) zzauo.zzb("type.googleapis.com/google.crypto.tink.HmacKey", zzavo.zzwo()), zzavo.zzwo().zzym().zzyt());
            }
            throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzavo) {
            zzavo zzavo = (zzavo) zzbcu;
            zzazq.zzj(zzavo.getVersion(), 0);
            return new zzayx((zzazi) zzauo.zzb("type.googleapis.com/google.crypto.tink.AesCtrKey", zzavo.zzwn()), (zzauk) zzauo.zzb("type.googleapis.com/google.crypto.tink.HmacKey", zzavo.zzwo()), zzavo.zzwo().zzym().zzyt());
        }
        throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
    }

    public final zzbcu zzb(zzbah zzbah) throws GeneralSecurityException {
        try {
            return zzb((zzbcu) zzavq.zzj(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKeyFormat proto", e);
        }
    }

    public final zzbcu zzb(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzavq) {
            zzavq zzavq = (zzavq) zzbcu;
            return zzavo.zzwp().zzb((zzavs) zzauo.zza("type.googleapis.com/google.crypto.tink.AesCtrKey", (zzbcu) zzavq.zzwr())).zzb((zzaxc) zzauo.zza("type.googleapis.com/google.crypto.tink.HmacKey", (zzbcu) zzavq.zzws())).zzal(0).zzadi();
        }
        throw new GeneralSecurityException("expected AesCtrHmacAeadKeyFormat proto");
    }

    public final zzaxi zzc(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey").zzai(((zzavo) zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.SYMMETRIC).zzadi();
    }
}
