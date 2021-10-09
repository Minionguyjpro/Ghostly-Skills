package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zax implements PendingResult.StatusListener {
    private final /* synthetic */ BasePendingResult zaa;
    private final /* synthetic */ zav zab;

    zax(zav zav, BasePendingResult basePendingResult) {
        this.zab = zav;
        this.zaa = basePendingResult;
    }

    public final void onComplete(Status status) {
        this.zab.zaa.remove(this.zaa);
    }
}
