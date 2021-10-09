package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi;
import java.security.GeneralSecurityException;

final class zzauu implements zzaug<zzatz> {
    zzauu() {
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzatz zza(zzbah zzbah) throws GeneralSecurityException {
        try {
            zzavy zzo = zzavy.zzo(zzbah);
            if (zzo instanceof zzavy) {
                zzavy zzavy = zzo;
                zzazq.zzj(zzavy.getVersion(), 0);
                zzazq.zzbi(zzavy.zzwv().size());
                if (zzavy.zzxe().zzxb() != 12) {
                    if (zzavy.zzxe().zzxb() != 16) {
                        throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
                    }
                }
                return new zzayi(zzavy.zzwv().toByteArray(), zzavy.zzxe().zzxb());
            }
            throw new GeneralSecurityException("expected AesEaxKey proto");
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized AesEaxKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzavy) {
            zzavy zzavy = (zzavy) zzbcu;
            zzazq.zzj(zzavy.getVersion(), 0);
            zzazq.zzbi(zzavy.zzwv().size());
            if (zzavy.zzxe().zzxb() == 12 || zzavy.zzxe().zzxb() == 16) {
                return new zzayi(zzavy.zzwv().toByteArray(), zzavy.zzxe().zzxb());
            }
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
        throw new GeneralSecurityException("expected AesEaxKey proto");
    }

    public final zzbcu zzb(zzbah zzbah) throws GeneralSecurityException {
        try {
            return zzb((zzbcu) zzawa.zzq(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized AesEaxKeyFormat proto", e);
        }
    }

    public final zzbcu zzb(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzawa) {
            zzawa zzawa = (zzawa) zzbcu;
            zzazq.zzbi(zzawa.getKeySize());
            if (zzawa.zzxe().zzxb() == 12 || zzawa.zzxe().zzxb() == 16) {
                return zzavy.zzxf().zzp(zzbah.zzo(zzazl.zzbh(zzawa.getKeySize()))).zzb(zzawa.zzxe()).zzan(0).zzadi();
            }
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
        throw new GeneralSecurityException("expected AesEaxKeyFormat proto");
    }

    public final zzaxi zzc(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.AesEaxKey").zzai(((zzavy) zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.SYMMETRIC).zzadi();
    }
}
