package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;

final class zzfy implements zzgc {
    private final /* synthetic */ Activity val$activity;

    zzfy(zzfu zzfu, Activity activity) {
        this.val$activity = activity;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityPaused(this.val$activity);
    }
}
