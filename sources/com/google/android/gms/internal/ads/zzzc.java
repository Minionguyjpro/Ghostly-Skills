package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.HashSet;

@zzadh
public final class zzzc {
    public static int zza(AdRequest.ErrorCode errorCode) {
        int i = zzzd.zzbvg[errorCode.ordinal()];
        if (i == 2) {
            return 1;
        }
        if (i != 3) {
            return i != 4 ? 0 : 3;
        }
        return 2;
    }

    public static MediationAdRequest zza(zzjj zzjj, boolean z) {
        HashSet hashSet = zzjj.zzapy != null ? new HashSet(zzjj.zzapy) : null;
        Date date = new Date(zzjj.zzapw);
        int i = zzjj.zzapx;
        return new MediationAdRequest(date, i != 1 ? i != 2 ? AdRequest.Gender.UNKNOWN : AdRequest.Gender.FEMALE : AdRequest.Gender.MALE, hashSet, z, zzjj.zzaqe);
    }
}
