package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzaqw;
import java.util.Map;

final class zzs implements zzv<zzaqw> {
    zzs() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaqw zzaqw = (zzaqw) obj;
        String str = (String) map.get("action");
        if ("pause".equals(str)) {
            zzaqw.zzcl();
        } else if ("resume".equals(str)) {
            zzaqw.zzcm();
        }
    }
}
