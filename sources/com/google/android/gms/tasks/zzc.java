package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
final class zzc<TResult, TContinuationResult> implements zzr<TResult> {
    private final Executor zza;
    /* access modifiers changed from: private */
    public final Continuation<TResult, TContinuationResult> zzb;
    /* access modifiers changed from: private */
    public final zzu<TContinuationResult> zzc;

    public zzc(Executor executor, Continuation<TResult, TContinuationResult> continuation, zzu<TContinuationResult> zzu) {
        this.zza = executor;
        this.zzb = continuation;
        this.zzc = zzu;
    }

    public final void zza(Task<TResult> task) {
        this.zza.execute(new zze(this, task));
    }

    public final void zza() {
        throw new UnsupportedOperationException();
    }
}
