package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzajf implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzaoj zzcoa;

    zzajf(zzaje zzaje, Context context, zzaoj zzaoj) {
        this.val$context = context;
        this.zzcoa = zzaoj;
    }

    public final void run() {
        try {
            this.zzcoa.set(AdvertisingIdClient.getAdvertisingIdInfo(this.val$context));
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e) {
            this.zzcoa.setException(e);
            zzane.zzb("Exception while getting advertising Id info", e);
        }
    }
}
