package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;

final class zzcr implements zzcv {
    private final /* synthetic */ Activity val$activity;

    zzcr(zzcn zzcn, Activity activity) {
        this.val$activity = activity;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityPaused(this.val$activity);
    }
}
