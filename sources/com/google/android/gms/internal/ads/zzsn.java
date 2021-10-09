package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;

final class zzsn extends zzaoj<ParcelFileDescriptor> {
    private final /* synthetic */ zzsm zzbnn;

    zzsn(zzsm zzsm) {
        this.zzbnn = zzsm;
    }

    public final boolean cancel(boolean z) {
        this.zzbnn.disconnect();
        return super.cancel(z);
    }
}
