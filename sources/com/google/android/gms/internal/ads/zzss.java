package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzal;
import com.google.android.gms.ads.internal.zzw;

@zzadh
public final class zzss {
    private final Context mContext;
    private final zzw zzwc;
    private final zzxn zzwh;
    private final zzang zzyf;

    zzss(Context context, zzxn zzxn, zzang zzang, zzw zzw) {
        this.mContext = context;
        this.zzwh = zzxn;
        this.zzyf = zzang;
        this.zzwc = zzw;
    }

    public final Context getApplicationContext() {
        return this.mContext.getApplicationContext();
    }

    public final zzal zzav(String str) {
        return new zzal(this.mContext, new zzjn(), str, this.zzwh, this.zzyf, this.zzwc);
    }

    public final zzal zzaw(String str) {
        return new zzal(this.mContext.getApplicationContext(), new zzjn(), str, this.zzwh, this.zzyf, this.zzwc);
    }

    public final zzss zzlc() {
        return new zzss(this.mContext.getApplicationContext(), this.zzwh, this.zzyf, this.zzwc);
    }
}
