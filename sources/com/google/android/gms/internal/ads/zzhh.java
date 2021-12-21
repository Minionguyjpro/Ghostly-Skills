package com.google.android.gms.internal.ads;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

final class zzhh implements BaseGmsClient.BaseOnConnectionFailedListener {
    private final /* synthetic */ zzhd zzajt;

    zzhh(zzhd zzhd) {
        this.zzajt = zzhd;
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        synchronized (this.zzajt.mLock) {
            zzho unused = this.zzajt.zzajs = null;
            if (this.zzajt.zzajr != null) {
                zzhk unused2 = this.zzajt.zzajr = null;
            }
            this.zzajt.mLock.notifyAll();
        }
    }
}
