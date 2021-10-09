package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzahp implements Runnable {
    private final /* synthetic */ zzxq zzclu;
    private final /* synthetic */ zzahn zzclv;
    private final /* synthetic */ zzahv zzclw;
    private final /* synthetic */ zzjj zzyh;

    zzahp(zzahn zzahn, zzxq zzxq, zzjj zzjj, zzahv zzahv) {
        this.zzclv = zzahn;
        this.zzclu = zzxq;
        this.zzyh = zzjj;
        this.zzclw = zzahv;
    }

    public final void run() {
        try {
            this.zzclu.zza(ObjectWrapper.wrap(this.zzclv.mContext), this.zzyh, (String) null, (zzaic) this.zzclw, this.zzclv.zzcln);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(this.zzclv.zzbth);
            zzakb.zzc(valueOf.length() != 0 ? "Fail to initialize adapter ".concat(valueOf) : new String("Fail to initialize adapter "), e);
            zzahn zzahn = this.zzclv;
            zzahn.zza(zzahn.zzbth, 0);
        }
    }
}
