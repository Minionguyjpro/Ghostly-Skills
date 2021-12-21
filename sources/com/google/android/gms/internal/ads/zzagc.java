package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.WeakHashMap;
import java.util.concurrent.Future;

@zzadh
public final class zzagc {
    /* access modifiers changed from: private */
    public WeakHashMap<Context, zzage> zzckj = new WeakHashMap<>();

    public final Future<zzaga> zzq(Context context) {
        return zzaki.zza(new zzagd(this, context));
    }
}
