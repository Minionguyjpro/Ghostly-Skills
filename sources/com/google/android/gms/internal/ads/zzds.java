package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public final class zzds extends zzei {
    public zzds(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 24);
    }

    private final void zzau() {
        AdvertisingIdClient zzan = this.zzps.zzan();
        if (zzan != null) {
            try {
                AdvertisingIdClient.Info info = zzan.getInfo();
                String zzn = zzdg.zzn(info.getId());
                if (zzn != null) {
                    synchronized (this.zztq) {
                        this.zztq.zzfi = zzn;
                        this.zztq.zzfk = Boolean.valueOf(info.isLimitAdTrackingEnabled());
                        this.zztq.zzfj = 5;
                    }
                }
            } catch (IOException unused) {
            }
        }
    }

    public final /* synthetic */ Object call() throws Exception {
        return call();
    }

    /* access modifiers changed from: protected */
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (this.zzps.zzaf()) {
            zzau();
            return;
        }
        synchronized (this.zztq) {
            this.zztq.zzfi = (String) this.zztz.invoke((Object) null, new Object[]{this.zzps.getContext()});
        }
    }

    public final Void zzat() throws Exception {
        if (this.zzps.isInitialized()) {
            return super.call();
        }
        if (!this.zzps.zzaf()) {
            return null;
        }
        zzau();
        return null;
    }
}
