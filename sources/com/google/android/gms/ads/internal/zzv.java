package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzqs;

final class zzv implements Runnable {
    private final /* synthetic */ zzq zzwt;
    private final /* synthetic */ zzqs zzwx;

    zzv(zzq zzq, zzqs zzqs) {
        this.zzwt = zzq;
        this.zzwx = zzqs;
    }

    public final void run() {
        try {
            this.zzwt.zzvw.zzadi.get(this.zzwx.getCustomTemplateId()).zzb(this.zzwx);
        } catch (RemoteException e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
