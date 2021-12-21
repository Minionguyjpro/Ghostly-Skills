package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

final class zzavh {
    public static zzayv zza(zzawy zzawy) throws GeneralSecurityException {
        int i = zzavi.zzdia[zzawy.ordinal()];
        if (i == 1) {
            return zzayv.NIST_P256;
        }
        if (i == 2) {
            return zzayv.NIST_P384;
        }
        if (i == 3) {
            return zzayv.NIST_P521;
        }
        String valueOf = String.valueOf(zzawy);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
        sb.append("unknown curve type: ");
        sb.append(valueOf);
        throw new GeneralSecurityException(sb.toString());
    }

    public static zzayw zza(zzawk zzawk) throws GeneralSecurityException {
        int i = zzavi.zzdib[zzawk.ordinal()];
        if (i == 1) {
            return zzayw.UNCOMPRESSED;
        }
        if (i == 2) {
            return zzayw.COMPRESSED;
        }
        String valueOf = String.valueOf(zzawk);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
        sb.append("unknown point format: ");
        sb.append(valueOf);
        throw new GeneralSecurityException(sb.toString());
    }

    public static String zza(zzaxa zzaxa) throws NoSuchAlgorithmException {
        int i = zzavi.zzdhz[zzaxa.ordinal()];
        if (i == 1) {
            return "HmacSha1";
        }
        if (i == 2) {
            return "HmacSha256";
        }
        if (i == 3) {
            return "HmacSha512";
        }
        String valueOf = String.valueOf(zzaxa);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
        sb.append("hash unsupported for HMAC: ");
        sb.append(valueOf);
        throw new NoSuchAlgorithmException(sb.toString());
    }

    public static void zza(zzawq zzawq) throws GeneralSecurityException {
        zzayt.zza(zza(zzawq.zzxu().zzyh()));
        zza(zzawq.zzxu().zzyi());
        if (zzawq.zzxw() != zzawk.UNKNOWN_FORMAT) {
            zzauo.zza(zzawq.zzxv().zzxp());
            return;
        }
        throw new GeneralSecurityException("unknown EC point format");
    }
}
