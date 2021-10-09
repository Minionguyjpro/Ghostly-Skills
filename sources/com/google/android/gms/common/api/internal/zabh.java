package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zabh implements BaseGmsClient.SignOutCallbacks {
    final /* synthetic */ GoogleApiManager.zaa zaa;

    zabh(GoogleApiManager.zaa zaa2) {
        this.zaa = zaa2;
    }

    public final void onSignOutComplete() {
        GoogleApiManager.this.zaq.post(new zabg(this));
    }
}
