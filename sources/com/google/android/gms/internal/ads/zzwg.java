package com.google.android.gms.internal.ads;

import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzwg {
    private final Object mLock = new Object();
    private zzwn zzbrb;

    public final zzwn zzb(Context context, zzang zzang) {
        zzwn zzwn;
        synchronized (this.mLock) {
            if (this.zzbrb == null) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
                this.zzbrb = new zzwn(context, zzang, (String) zzkb.zzik().zzd(zznk.zzaub));
            }
            zzwn = this.zzbrb;
        }
        return zzwn;
    }
}
