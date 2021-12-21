package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

@zzadh
final class zzst {
    /* access modifiers changed from: private */
    public final List<zzts> zzxo = new ArrayList();

    zzst() {
    }

    /* access modifiers changed from: package-private */
    public final void zza(zztt zztt) {
        Handler handler = zzakk.zzcrm;
        for (zzts zztr : this.zzxo) {
            handler.post(new zztr(this, zztr, zztt));
        }
        this.zzxo.clear();
    }
}
