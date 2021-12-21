package com.google.android.gms.internal.ads;

import android.view.View;
import com.google.android.gms.ads.internal.zzaf;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzny extends zzob {
    private final zzaf zzbgs;
    private final String zzbgt;
    private final String zzbgu;

    public zzny(zzaf zzaf, String str, String str2) {
        this.zzbgs = zzaf;
        this.zzbgt = str;
        this.zzbgu = str2;
    }

    public final String getContent() {
        return this.zzbgu;
    }

    public final void recordClick() {
        this.zzbgs.zzcn();
    }

    public final void recordImpression() {
        this.zzbgs.zzco();
    }

    public final void zzg(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper != null) {
            this.zzbgs.zzh((View) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public final String zzjn() {
        return this.zzbgt;
    }
}
