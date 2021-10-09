package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzavs;
import com.google.android.gms.internal.ads.zzawe;
import com.google.android.gms.internal.ads.zzaxc;
import java.security.GeneralSecurityException;
import java.util.Arrays;

final class zzavj implements zzayn {
    private final String zzdic;
    private final int zzdid;
    private zzawe zzdie;
    private zzavo zzdif;
    private int zzdig;

    zzavj(zzaxn zzaxn) throws GeneralSecurityException {
        String zzyw = zzaxn.zzyw();
        this.zzdic = zzyw;
        if (zzyw.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            try {
                zzawg zzt = zzawg.zzt(zzaxn.zzyx());
                this.zzdie = (zzawe) zzauo.zzb(zzaxn);
                this.zzdid = zzt.getKeySize();
            } catch (zzbbu e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        } else if (this.zzdic.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            try {
                zzavq zzj = zzavq.zzj(zzaxn.zzyx());
                this.zzdif = (zzavo) zzauo.zzb(zzaxn);
                this.zzdig = zzj.zzwr().getKeySize();
                this.zzdid = this.zzdig + zzj.zzws().getKeySize();
            } catch (zzbbu e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e2);
            }
        } else {
            String valueOf = String.valueOf(this.zzdic);
            throw new GeneralSecurityException(valueOf.length() != 0 ? "unsupported AEAD DEM key type: ".concat(valueOf) : new String("unsupported AEAD DEM key type: "));
        }
    }

    public final zzatz zzi(byte[] bArr) throws GeneralSecurityException {
        zzbcu zzbcu;
        if (bArr.length == this.zzdid) {
            if (this.zzdic.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
                zzbcu = (zzawe) ((zzawe.zza) zzawe.zzxk().zza(this.zzdie)).zzs(zzbah.zzc(bArr, 0, this.zzdid)).zzadi();
            } else if (this.zzdic.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.zzdig);
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, this.zzdig, this.zzdid);
                zzbcu = (zzavo) zzavo.zzwp().zzal(this.zzdif.getVersion()).zzb((zzavs) ((zzavs.zza) zzavs.zzww().zza(this.zzdif.zzwn())).zzm(zzbah.zzo(copyOfRange)).zzadi()).zzb((zzaxc) ((zzaxc.zza) zzaxc.zzyn().zza(this.zzdif.zzwo())).zzaf(zzbah.zzo(copyOfRange2)).zzadi()).zzadi();
            } else {
                throw new GeneralSecurityException("unknown DEM key type");
            }
            return (zzatz) zzauo.zzb(this.zzdic, zzbcu);
        }
        throw new GeneralSecurityException("Symmetric key has incorrect length");
    }

    public final int zzwm() {
        return this.zzdid;
    }
}
