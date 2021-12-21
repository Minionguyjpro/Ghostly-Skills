package com.mopub.common;

import android.app.Activity;
import com.mopub.common.privacy.PersonalInfoManager;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public class MoPubLifecycleManager implements LifecycleListener {
    private static MoPubLifecycleManager sInstance;
    private final Set<LifecycleListener> mLifecycleListeners = new HashSet();
    private final WeakReference<Activity> mMainActivity;

    private MoPubLifecycleManager(Activity activity) {
        this.mMainActivity = new WeakReference<>(activity);
    }

    public static synchronized MoPubLifecycleManager getInstance(Activity activity) {
        MoPubLifecycleManager moPubLifecycleManager;
        synchronized (MoPubLifecycleManager.class) {
            if (sInstance == null) {
                sInstance = new MoPubLifecycleManager(activity);
            }
            moPubLifecycleManager = sInstance;
        }
        return moPubLifecycleManager;
    }

    public void addLifecycleListener(LifecycleListener lifecycleListener) {
        Activity activity;
        if (lifecycleListener != null && this.mLifecycleListeners.add(lifecycleListener) && (activity = (Activity) this.mMainActivity.get()) != null) {
            lifecycleListener.onCreate(activity);
            lifecycleListener.onStart(activity);
        }
    }

    public void onCreate(Activity activity) {
        for (LifecycleListener onCreate : this.mLifecycleListeners) {
            onCreate.onCreate(activity);
        }
    }

    public void onStart(Activity activity) {
        for (LifecycleListener onStart : this.mLifecycleListeners) {
            onStart.onStart(activity);
        }
    }

    public void onPause(Activity activity) {
        for (LifecycleListener onPause : this.mLifecycleListeners) {
            onPause.onPause(activity);
        }
    }

    public void onResume(Activity activity) {
        PersonalInfoManager personalInformationManager = MoPub.getPersonalInformationManager();
        if (personalInformationManager != null) {
            personalInformationManager.requestSync(false);
        }
        for (LifecycleListener onResume : this.mLifecycleListeners) {
            onResume.onResume(activity);
        }
    }

    public void onRestart(Activity activity) {
        for (LifecycleListener onRestart : this.mLifecycleListeners) {
            onRestart.onRestart(activity);
        }
    }

    public void onStop(Activity activity) {
        for (LifecycleListener onStop : this.mLifecycleListeners) {
            onStop.onStop(activity);
        }
    }

    public void onDestroy(Activity activity) {
        for (LifecycleListener onDestroy : this.mLifecycleListeners) {
            onDestroy.onDestroy(activity);
        }
    }

    public void onBackPressed(Activity activity) {
        for (LifecycleListener onBackPressed : this.mLifecycleListeners) {
            onBackPressed.onBackPressed(activity);
        }
    }
}
