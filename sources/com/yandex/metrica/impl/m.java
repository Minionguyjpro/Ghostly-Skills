package com.yandex.metrica.impl;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class m implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a  reason: collision with root package name */
    private z f776a;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public m(z zVar) {
        this.f776a = zVar;
    }

    public void onActivityResumed(Activity activity) {
        this.f776a.b(activity);
    }

    public void onActivityPaused(Activity activity) {
        this.f776a.c(activity);
    }
}
