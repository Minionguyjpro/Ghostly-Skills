package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;

final class zzcq implements zzcv {
    private final /* synthetic */ Activity val$activity;

    zzcq(zzcn zzcn, Activity activity) {
        this.val$activity = activity;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityResumed(this.val$activity);
    }
}
