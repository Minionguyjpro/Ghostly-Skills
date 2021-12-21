package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import java.util.Map;

final class zzax implements zzv<zzaqw> {
    private final /* synthetic */ zzxz zzzr;
    private final /* synthetic */ zzac zzzs;
    private final /* synthetic */ zzyc zzzt;

    zzax(zzxz zzxz, zzac zzac, zzyc zzyc) {
        this.zzzr = zzxz;
        this.zzzs = zzac;
        this.zzzt = zzyc;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaqw zzaqw = (zzaqw) obj;
        View view = zzaqw.getView();
        if (view != null) {
            try {
                if (this.zzzr != null) {
                    if (!this.zzzr.getOverrideClickHandling()) {
                        this.zzzr.zzj(ObjectWrapper.wrap(view));
                        this.zzzs.zzxl.onAdClicked();
                        return;
                    }
                    zzas.zzd(zzaqw);
                } else if (this.zzzt == null) {
                } else {
                    if (!this.zzzt.getOverrideClickHandling()) {
                        this.zzzt.zzj(ObjectWrapper.wrap(view));
                        this.zzzs.zzxl.onAdClicked();
                        return;
                    }
                    zzas.zzd(zzaqw);
                }
            } catch (RemoteException e) {
                zzakb.zzc("Unable to call handleClick on mapper", e);
            }
        }
    }
}
