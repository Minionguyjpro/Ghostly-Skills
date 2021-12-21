package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class zzgh implements Application.ActivityLifecycleCallbacks {
    private Activity mActivity;
    private Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public boolean zzahr = true;
    /* access modifiers changed from: private */
    public boolean zzahs = false;
    /* access modifiers changed from: private */
    public final List<zzgj> zzaht = new ArrayList();
    private final List<zzgw> zzahu = new ArrayList();
    private Runnable zzahv;
    private long zzahw;
    private boolean zzzv = false;

    zzgh() {
    }

    private final void setActivity(Activity activity) {
        synchronized (this.mLock) {
            if (!activity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.mActivity = activity;
            }
        }
    }

    public final Activity getActivity() {
        return this.mActivity;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
        synchronized (this.mLock) {
            if (this.mActivity != null) {
                if (this.mActivity.equals(activity)) {
                    this.mActivity = null;
                }
                Iterator<zzgw> it = this.zzahu.iterator();
                while (it.hasNext()) {
                    try {
                        if (it.next().zza(activity)) {
                            it.remove();
                        }
                    } catch (Exception e) {
                        zzbv.zzeo().zza(e, "AppActivityTracker.ActivityListener.onActivityDestroyed");
                        zzane.zzb("", e);
                    }
                }
            }
        }
    }

    public final void onActivityPaused(Activity activity) {
        setActivity(activity);
        synchronized (this.mLock) {
            for (zzgw onActivityPaused : this.zzahu) {
                try {
                    onActivityPaused.onActivityPaused(activity);
                } catch (Exception e) {
                    zzbv.zzeo().zza(e, "AppActivityTracker.ActivityListener.onActivityPaused");
                    zzane.zzb("", e);
                }
            }
        }
        this.zzahs = true;
        if (this.zzahv != null) {
            zzakk.zzcrm.removeCallbacks(this.zzahv);
        }
        Handler handler = zzakk.zzcrm;
        zzgi zzgi = new zzgi(this);
        this.zzahv = zzgi;
        handler.postDelayed(zzgi, this.zzahw);
    }

    public final void onActivityResumed(Activity activity) {
        setActivity(activity);
        this.zzahs = false;
        boolean z = !this.zzahr;
        this.zzahr = true;
        if (this.zzahv != null) {
            zzakk.zzcrm.removeCallbacks(this.zzahv);
        }
        synchronized (this.mLock) {
            for (zzgw onActivityResumed : this.zzahu) {
                try {
                    onActivityResumed.onActivityResumed(activity);
                } catch (Exception e) {
                    zzbv.zzeo().zza(e, "AppActivityTracker.ActivityListener.onActivityResumed");
                    zzane.zzb("", e);
                }
            }
            if (z) {
                for (zzgj zzh : this.zzaht) {
                    try {
                        zzh.zzh(true);
                    } catch (Exception e2) {
                        zzane.zzb("", e2);
                    }
                }
            } else {
                zzakb.zzck("App is still foreground.");
            }
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
        setActivity(activity);
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void zza(Application application, Context context) {
        if (!this.zzzv) {
            application.registerActivityLifecycleCallbacks(this);
            if (context instanceof Activity) {
                setActivity((Activity) context);
            }
            this.mContext = application;
            this.zzahw = ((Long) zzkb.zzik().zzd(zznk.zzayh)).longValue();
            this.zzzv = true;
        }
    }

    public final void zza(zzgj zzgj) {
        synchronized (this.mLock) {
            this.zzaht.add(zzgj);
        }
    }
}
