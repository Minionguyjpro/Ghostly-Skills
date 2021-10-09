package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

final class zzfu implements Application.ActivityLifecycleCallbacks {
    private final Application zzagd;
    private final WeakReference<Application.ActivityLifecycleCallbacks> zzagv;
    private boolean zzagw = false;

    public zzfu(Application application, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.zzagv = new WeakReference<>(activityLifecycleCallbacks);
        this.zzagd = application;
    }

    private final void zza(zzgc zzgc) {
        try {
            Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = (Application.ActivityLifecycleCallbacks) this.zzagv.get();
            if (activityLifecycleCallbacks != null) {
                zzgc.zza(activityLifecycleCallbacks);
            } else if (!this.zzagw) {
                this.zzagd.unregisterActivityLifecycleCallbacks(this);
                this.zzagw = true;
            }
        } catch (Exception e) {
            zzakb.zzb("Error while dispatching lifecycle callback.", e);
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(new zzfv(this, activity, bundle));
    }

    public final void onActivityDestroyed(Activity activity) {
        zza(new zzgb(this, activity));
    }

    public final void onActivityPaused(Activity activity) {
        zza(new zzfy(this, activity));
    }

    public final void onActivityResumed(Activity activity) {
        zza(new zzfx(this, activity));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zza(new zzga(this, activity, bundle));
    }

    public final void onActivityStarted(Activity activity) {
        zza(new zzfw(this, activity));
    }

    public final void onActivityStopped(Activity activity) {
        zza(new zzfz(this, activity));
    }
}
