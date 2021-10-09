package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import java.util.Map;

@zzadh
public final class zzc implements zzv<Object> {
    private final zzd zzblm;

    public zzc(zzd zzd) {
        this.zzblm = zzd;
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = map.get("name");
        if (str == null) {
            zzakb.zzdk("App event with no name parameter.");
        } else {
            this.zzblm.onAppEvent(str, map.get("info"));
        }
    }
}
