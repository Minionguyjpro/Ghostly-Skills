package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

final class zzfv implements zzgc {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ Bundle zzrn;

    zzfv(zzfu zzfu, Activity activity, Bundle bundle) {
        this.val$activity = activity;
        this.zzrn = bundle;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityCreated(this.val$activity, this.zzrn);
    }
}
