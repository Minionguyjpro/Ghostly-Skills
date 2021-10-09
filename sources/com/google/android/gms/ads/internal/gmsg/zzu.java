package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzaqw;
import java.util.Map;

final class zzu implements zzv<zzaqw> {
    zzu() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaqw zzaqw = (zzaqw) obj;
        if (map.keySet().contains("start")) {
            zzaqw.zzak(true);
        }
        if (map.keySet().contains("stop")) {
            zzaqw.zzak(false);
        }
    }
}
