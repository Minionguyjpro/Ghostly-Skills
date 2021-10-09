package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
final class zzi<TResult> implements zzr<TResult> {
    private final Executor zza;
    /* access modifiers changed from: private */
    public final Object zzb = new Object();
    /* access modifiers changed from: private */
    public OnCompleteListener<TResult> zzc;

    public zzi(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        this.zza = executor;
        this.zzc = onCompleteListener;
    }

    public final void zza(Task<TResult> task) {
        synchronized (this.zzb) {
            if (this.zzc != null) {
                this.zza.execute(new zzj(this, task));
            }
        }
    }

    public final void zza() {
        synchronized (this.zzb) {
            this.zzc = null;
        }
    }
}
