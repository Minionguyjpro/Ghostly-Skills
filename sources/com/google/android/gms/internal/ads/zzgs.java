package com.google.android.gms.internal.ads;

import java.util.Comparator;

final class zzgs implements Comparator<zzgy> {
    zzgs(zzgr zzgr) {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzgy zzgy = (zzgy) obj;
        zzgy zzgy2 = (zzgy) obj2;
        int i = zzgy.zzajg - zzgy2.zzajg;
        return i != 0 ? i : (int) (zzgy.value - zzgy2.value);
    }
}
