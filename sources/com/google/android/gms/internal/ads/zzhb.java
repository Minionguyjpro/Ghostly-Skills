package com.google.android.gms.internal.ads;

import java.util.Comparator;

public final class zzhb implements Comparator<zzgp> {
    public zzhb(zzha zzha) {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzgp zzgp = (zzgp) obj;
        zzgp zzgp2 = (zzgp) obj2;
        if (zzgp.zzhc() < zzgp2.zzhc()) {
            return -1;
        }
        if (zzgp.zzhc() > zzgp2.zzhc()) {
            return 1;
        }
        if (zzgp.zzhb() < zzgp2.zzhb()) {
            return -1;
        }
        if (zzgp.zzhb() > zzgp2.zzhb()) {
            return 1;
        }
        float zzhe = (zzgp.zzhe() - zzgp.zzhc()) * (zzgp.zzhd() - zzgp.zzhb());
        float zzhe2 = (zzgp2.zzhe() - zzgp2.zzhc()) * (zzgp2.zzhd() - zzgp2.zzhb());
        if (zzhe > zzhe2) {
            return -1;
        }
        return zzhe < zzhe2 ? 1 : 0;
    }
}
