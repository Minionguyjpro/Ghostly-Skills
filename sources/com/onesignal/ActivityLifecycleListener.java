package com.onesignal;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;

class ActivityLifecycleListener implements Application.ActivityLifecycleCallbacks {
    private static ComponentCallbacks configuration;
    private static ActivityLifecycleListener instance;

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    ActivityLifecycleListener() {
    }

    static void registerActivityLifecycleCallbacks(Application application) {
        if (instance == null) {
            ActivityLifecycleListener activityLifecycleListener = new ActivityLifecycleListener();
            instance = activityLifecycleListener;
            application.registerActivityLifecycleCallbacks(activityLifecycleListener);
        }
        if (configuration == null) {
            AnonymousClass1 r0 = new ComponentCallbacks() {
                public void onLowMemory() {
                }

                public void onConfigurationChanged(Configuration configuration) {
                    ActivityLifecycleHandler.onConfigurationChanged(configuration);
                }
            };
            configuration = r0;
            application.registerComponentCallbacks(r0);
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        ActivityLifecycleHandler.onActivityCreated(activity);
    }

    public void onActivityStarted(Activity activity) {
        ActivityLifecycleHandler.onActivityStarted(activity);
    }

    public void onActivityResumed(Activity activity) {
        ActivityLifecycleHandler.onActivityResumed(activity);
    }

    public void onActivityPaused(Activity activity) {
        ActivityLifecycleHandler.onActivityPaused(activity);
    }

    public void onActivityStopped(Activity activity) {
        ActivityLifecycleHandler.onActivityStopped(activity);
    }

    public void onActivityDestroyed(Activity activity) {
        ActivityLifecycleHandler.onActivityDestroyed(activity);
    }
}
