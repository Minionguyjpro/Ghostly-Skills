package com.google.android.gms.tasks;

import android.app.Activity;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
final class zzu<TResult> extends Task<TResult> {
    private final Object zza = new Object();
    private final zzq<TResult> zzb = new zzq<>();
    private boolean zzc;
    private volatile boolean zzd;
    private TResult zze;
    private Exception zzf;

    zzu() {
    }

    public final boolean isComplete() {
        boolean z;
        synchronized (this.zza) {
            z = this.zzc;
        }
        return z;
    }

    /* compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
    private static class zza extends LifecycleCallback {
        private final List<WeakReference<zzr<?>>> zza = new ArrayList();

        public static zza zza(Activity activity) {
            LifecycleFragment fragment = getFragment(activity);
            zza zza2 = (zza) fragment.getCallbackOrNull("TaskOnStopCallback", zza.class);
            return zza2 == null ? new zza(fragment) : zza2;
        }

        private zza(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.mLifecycleFragment.addCallback("TaskOnStopCallback", this);
        }

        public final <T> void zza(zzr<T> zzr) {
            synchronized (this.zza) {
                this.zza.add(new WeakReference(zzr));
            }
        }

        public void onStop() {
            synchronized (this.zza) {
                for (WeakReference<zzr<?>> weakReference : this.zza) {
                    zzr zzr = (zzr) weakReference.get();
                    if (zzr != null) {
                        zzr.zza();
                    }
                }
                this.zza.clear();
            }
        }
    }

    public final boolean isCanceled() {
        return this.zzd;
    }

    public final boolean isSuccessful() {
        boolean z;
        synchronized (this.zza) {
            z = this.zzc && !this.zzd && this.zzf == null;
        }
        return z;
    }

    public final TResult getResult() {
        TResult tresult;
        synchronized (this.zza) {
            zzb();
            zzd();
            if (this.zzf == null) {
                tresult = this.zze;
            } else {
                throw new RuntimeExecutionException(this.zzf);
            }
        }
        return tresult;
    }

    public final <X extends Throwable> TResult getResult(Class<X> cls) throws Throwable {
        TResult tresult;
        synchronized (this.zza) {
            zzb();
            zzd();
            if (cls.isInstance(this.zzf)) {
                throw ((Throwable) cls.cast(this.zzf));
            } else if (this.zzf == null) {
                tresult = this.zze;
            } else {
                throw new RuntimeExecutionException(this.zzf);
            }
        }
        return tresult;
    }

    public final Exception getException() {
        Exception exc;
        synchronized (this.zza) {
            exc = this.zzf;
        }
        return exc;
    }

    public final Task<TResult> addOnSuccessListener(OnSuccessListener<? super TResult> onSuccessListener) {
        return addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener);
    }

    public final Task<TResult> addOnSuccessListener(Executor executor, OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzb.zza(new zzm(zzv.zza(executor), onSuccessListener));
        zze();
        return this;
    }

    public final Task<TResult> addOnSuccessListener(Activity activity, OnSuccessListener<? super TResult> onSuccessListener) {
        zzm zzm = new zzm(zzv.zza(TaskExecutors.MAIN_THREAD), onSuccessListener);
        this.zzb.zza(zzm);
        zza.zza(activity).zza(zzm);
        zze();
        return this;
    }

    public final Task<TResult> addOnFailureListener(OnFailureListener onFailureListener) {
        return addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener);
    }

    public final Task<TResult> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        this.zzb.zza(new zzl(zzv.zza(executor), onFailureListener));
        zze();
        return this;
    }

    public final Task<TResult> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        zzl zzl = new zzl(zzv.zza(TaskExecutors.MAIN_THREAD), onFailureListener);
        this.zzb.zza(zzl);
        zza.zza(activity).zza(zzl);
        zze();
        return this;
    }

    public final Task<TResult> addOnCompleteListener(OnCompleteListener<TResult> onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    public final Task<TResult> addOnCompleteListener(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        this.zzb.zza(new zzi(zzv.zza(executor), onCompleteListener));
        zze();
        return this;
    }

    public final Task<TResult> addOnCompleteListener(Activity activity, OnCompleteListener<TResult> onCompleteListener) {
        zzi zzi = new zzi(zzv.zza(TaskExecutors.MAIN_THREAD), onCompleteListener);
        this.zzb.zza(zzi);
        zza.zza(activity).zza(zzi);
        zze();
        return this;
    }

    public final <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(TaskExecutors.MAIN_THREAD, continuation);
    }

    public final <TContinuationResult> Task<TContinuationResult> continueWith(Executor executor, Continuation<TResult, TContinuationResult> continuation) {
        zzu zzu = new zzu();
        this.zzb.zza(new zzc(zzv.zza(executor), continuation, zzu));
        zze();
        return zzu;
    }

    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(TaskExecutors.MAIN_THREAD, continuation);
    }

    public final Task<TResult> addOnCanceledListener(OnCanceledListener onCanceledListener) {
        return addOnCanceledListener(TaskExecutors.MAIN_THREAD, onCanceledListener);
    }

    public final Task<TResult> addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        this.zzb.zza(new zzh(zzv.zza(executor), onCanceledListener));
        zze();
        return this;
    }

    public final Task<TResult> addOnCanceledListener(Activity activity, OnCanceledListener onCanceledListener) {
        zzh zzh = new zzh(zzv.zza(TaskExecutors.MAIN_THREAD), onCanceledListener);
        this.zzb.zza(zzh);
        zza.zza(activity).zza(zzh);
        zze();
        return this;
    }

    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(Executor executor, Continuation<TResult, Task<TContinuationResult>> continuation) {
        zzu zzu = new zzu();
        this.zzb.zza(new zzd(zzv.zza(executor), continuation, zzu));
        zze();
        return zzu;
    }

    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(Executor executor, SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        zzu zzu = new zzu();
        this.zzb.zza(new zzp(zzv.zza(executor), successContinuation, zzu));
        zze();
        return zzu;
    }

    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        return onSuccessTask(TaskExecutors.MAIN_THREAD, successContinuation);
    }

    public final void zza(TResult tresult) {
        synchronized (this.zza) {
            zzc();
            this.zzc = true;
            this.zze = tresult;
        }
        this.zzb.zza(this);
    }

    public final boolean zzb(TResult tresult) {
        synchronized (this.zza) {
            if (this.zzc) {
                return false;
            }
            this.zzc = true;
            this.zze = tresult;
            this.zzb.zza(this);
            return true;
        }
    }

    public final void zza(Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.zza) {
            zzc();
            this.zzc = true;
            this.zzf = exc;
        }
        this.zzb.zza(this);
    }

    public final boolean zzb(Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.zza) {
            if (this.zzc) {
                return false;
            }
            this.zzc = true;
            this.zzf = exc;
            this.zzb.zza(this);
            return true;
        }
    }

    public final boolean zza() {
        synchronized (this.zza) {
            if (this.zzc) {
                return false;
            }
            this.zzc = true;
            this.zzd = true;
            this.zzb.zza(this);
            return true;
        }
    }

    private final void zzb() {
        Preconditions.checkState(this.zzc, "Task is not yet complete");
    }

    private final void zzc() {
        if (this.zzc) {
            throw DuplicateTaskCompletionException.of(this);
        }
    }

    private final void zzd() {
        if (this.zzd) {
            throw new CancellationException("Task is already canceled.");
        }
    }

    private final void zze() {
        synchronized (this.zza) {
            if (this.zzc) {
                this.zzb.zza(this);
            }
        }
    }
}
