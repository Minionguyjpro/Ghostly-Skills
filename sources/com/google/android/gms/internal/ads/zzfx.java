package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;

final class zzfx implements zzgc {
    private final /* synthetic */ Activity val$activity;

    zzfx(zzfu zzfu, Activity activity) {
        this.val$activity = activity;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityResumed(this.val$activity);
    }
}
