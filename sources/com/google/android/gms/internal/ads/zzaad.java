package com.google.android.gms.internal.ads;

import com.appnext.core.Ad;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzadh
public final class zzaad {
    private final zzaqw zzbnd;
    private final boolean zzbwm;
    private final String zzbwn;

    public zzaad(zzaqw zzaqw, Map<String, String> map) {
        this.zzbnd = zzaqw;
        this.zzbwn = map.get("forceOrientation");
        this.zzbwm = map.containsKey("allowOrientationChange") ? Boolean.parseBoolean(map.get("allowOrientationChange")) : true;
    }

    public final void execute() {
        if (this.zzbnd == null) {
            zzakb.zzdk("AdWebView is null");
        } else {
            this.zzbnd.setRequestedOrientation(Ad.ORIENTATION_PORTRAIT.equalsIgnoreCase(this.zzbwn) ? zzbv.zzem().zzrm() : Ad.ORIENTATION_LANDSCAPE.equalsIgnoreCase(this.zzbwn) ? zzbv.zzem().zzrl() : this.zzbwm ? -1 : zzbv.zzem().zzrn());
        }
    }
}
