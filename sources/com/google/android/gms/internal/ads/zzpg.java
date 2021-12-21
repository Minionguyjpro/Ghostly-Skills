package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

final class zzpg implements zzv<Object> {
    private final /* synthetic */ zzacm zzbji;
    final /* synthetic */ zzpf zzbjj;

    zzpg(zzpf zzpf, zzacm zzacm) {
        this.zzbjj = zzpf;
        this.zzbji = zzacm;
    }

    public final void zza(Object obj, Map<String, String> map) {
        zzaqw zzaqw = (zzaqw) this.zzbjj.zzbjg.get();
        if (zzaqw == null) {
            this.zzbji.zzb("/loadHtml", this);
            return;
        }
        zzaqw.zzuf().zza((zzasd) new zzph(this, map, this.zzbji));
        String str = map.get("overlayHtml");
        String str2 = map.get("baseUrl");
        if (TextUtils.isEmpty(str2)) {
            zzaqw.loadData(str, "text/html", "UTF-8");
        } else {
            zzaqw.loadDataWithBaseURL(str2, str, "text/html", "UTF-8", (String) null);
        }
    }
}
