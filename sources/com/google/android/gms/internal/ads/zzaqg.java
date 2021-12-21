package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzadh
public final class zzaqg implements Iterable<zzaqe> {
    private final List<zzaqe> zzday = new ArrayList();

    public static boolean zzb(zzapw zzapw) {
        zzaqe zzc = zzc(zzapw);
        if (zzc == null) {
            return false;
        }
        zzc.zzdav.abort();
        return true;
    }

    static zzaqe zzc(zzapw zzapw) {
        Iterator<zzaqe> it = zzbv.zzff().iterator();
        while (it.hasNext()) {
            zzaqe next = it.next();
            if (next.zzcyg == zzapw) {
                return next;
            }
        }
        return null;
    }

    public final Iterator<zzaqe> iterator() {
        return this.zzday.iterator();
    }

    public final void zza(zzaqe zzaqe) {
        this.zzday.add(zzaqe);
    }

    public final void zzb(zzaqe zzaqe) {
        this.zzday.remove(zzaqe);
    }

    public final int zztx() {
        return this.zzday.size();
    }
}
