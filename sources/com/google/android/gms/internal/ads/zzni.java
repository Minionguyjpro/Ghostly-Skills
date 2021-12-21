package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.ConditionVariable;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzni {
    private final Object mLock = new Object();
    private final ConditionVariable zzatv = new ConditionVariable();
    /* access modifiers changed from: private */
    public SharedPreferences zzatw = null;
    private Context zzatx;
    private volatile boolean zzzv = false;

    public final void initialize(Context context) {
        if (!this.zzzv) {
            synchronized (this.mLock) {
                if (!this.zzzv) {
                    this.zzatx = context.getApplicationContext() == null ? context : context.getApplicationContext();
                    try {
                        Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
                        if (remoteContext == null && context != null) {
                            Context applicationContext = context.getApplicationContext();
                            if (applicationContext != null) {
                                context = applicationContext;
                            }
                            remoteContext = context;
                        }
                        if (remoteContext != null) {
                            zzkb.zzii();
                            this.zzatw = remoteContext.getSharedPreferences("google_ads_flags", 0);
                            this.zzzv = true;
                            this.zzatv.open();
                        }
                    } finally {
                        this.zzatv.open();
                    }
                }
            }
        }
    }

    public final <T> T zzd(zzna<T> zzna) {
        if (this.zzatv.block(5000)) {
            if (!this.zzzv || this.zzatw == null) {
                synchronized (this.mLock) {
                    if (this.zzzv) {
                        if (this.zzatw == null) {
                        }
                    }
                    T zzja = zzna.zzja();
                    return zzja;
                }
            }
            return zzaml.zza(this.zzatx, new zznj(this, zzna));
        }
        throw new IllegalStateException("Flags.initialize() was not called!");
    }
}
