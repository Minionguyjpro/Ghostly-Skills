package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.internal.tasks.zzb;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
public final class TaskExecutors {
    public static final Executor MAIN_THREAD = new zza();
    static final Executor zza = new zzt();

    private TaskExecutors() {
    }

    /* compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
    private static final class zza implements Executor {
        private final Handler zza = new zzb(Looper.getMainLooper());

        public final void execute(Runnable runnable) {
            this.zza.post(runnable);
        }
    }
}
