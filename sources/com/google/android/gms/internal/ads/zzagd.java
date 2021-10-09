package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.Callable;

final class zzagd implements Callable<zzaga> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzagc zzckk;

    zzagd(zzagc zzagc, Context context) {
        this.zzckk = zzagc;
        this.val$context = context;
    }

    public final /* synthetic */ Object call() throws Exception {
        zzaga zzaga;
        zzage zzage = (zzage) this.zzckk.zzckj.get(this.val$context);
        if (zzage != null) {
            if (!(zzage.zzckl + ((Long) zzkb.zzik().zzd(zznk.zzazw)).longValue() < zzbv.zzer().currentTimeMillis())) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzazv)).booleanValue()) {
                    zzaga = new zzagb(this.val$context, zzage.zzckm).zzoo();
                    this.zzckk.zzckj.put(this.val$context, new zzage(this.zzckk, zzaga));
                    return zzaga;
                }
            }
        }
        zzaga = new zzagb(this.val$context).zzoo();
        this.zzckk.zzckj.put(this.val$context, new zzage(this.zzckk, zzaga));
        return zzaga;
    }
}
