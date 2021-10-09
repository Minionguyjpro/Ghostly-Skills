package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.internal.ads.zzaab;
import com.google.android.gms.internal.ads.zzaad;
import com.google.android.gms.internal.ads.zzaae;
import com.google.android.gms.internal.ads.zzaam;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzzy;
import java.util.Map;

@zzadh
public final class zzac implements zzv<zzaqw> {
    private static final Map<String, Integer> zzbmz = CollectionUtils.mapOfKeyValueArrays(new String[]{"resize", "playVideo", "storePicture", "createCalendarEvent", "setOrientationProperties", "closeResizedAd", "unload"}, new Integer[]{1, 2, 3, 4, 5, 6, 7});
    private final zzx zzbmw;
    private final zzaab zzbmx;
    private final zzaam zzbmy;

    public zzac(zzx zzx, zzaab zzaab, zzaam zzaam) {
        this.zzbmw = zzx;
        this.zzbmx = zzaab;
        this.zzbmy = zzaam;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzx zzx;
        zzaqw zzaqw = (zzaqw) obj;
        int intValue = zzbmz.get((String) map.get("a")).intValue();
        if (intValue != 5 && intValue != 7 && (zzx = this.zzbmw) != null && !zzx.zzcy()) {
            this.zzbmw.zzs((String) null);
        } else if (intValue == 1) {
            this.zzbmx.zzk(map);
        } else if (intValue == 3) {
            new zzaae(zzaqw, map).execute();
        } else if (intValue == 4) {
            new zzzy(zzaqw, map).execute();
        } else if (intValue == 5) {
            new zzaad(zzaqw, map).execute();
        } else if (intValue == 6) {
            this.zzbmx.zzm(true);
        } else if (intValue != 7) {
            zzakb.zzdj("Unknown MRAID command called.");
        } else {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzawg)).booleanValue()) {
                this.zzbmy.zzcz();
            }
        }
    }
}
