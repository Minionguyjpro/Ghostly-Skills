package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;

final class zzcp implements zzcv {
    private final /* synthetic */ Activity val$activity;

    zzcp(zzcn zzcn, Activity activity) {
        this.val$activity = activity;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityStarted(this.val$activity);
    }
}
