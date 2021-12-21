package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.TransformedResult;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class OptionalPendingResultImpl<R extends Result> extends OptionalPendingResult<R> {
    private final BasePendingResult<R> zaa;

    public OptionalPendingResultImpl(PendingResult<R> pendingResult) {
        this.zaa = (BasePendingResult) pendingResult;
    }

    public final boolean isDone() {
        return this.zaa.isReady();
    }

    public final R get() {
        if (isDone()) {
            return await(0, TimeUnit.MILLISECONDS);
        }
        throw new IllegalStateException("Result is not available. Check that isDone() returns true before calling get().");
    }

    public final R await() {
        return this.zaa.await();
    }

    public final R await(long j, TimeUnit timeUnit) {
        return this.zaa.await(j, timeUnit);
    }

    public final void cancel() {
        this.zaa.cancel();
    }

    public final boolean isCanceled() {
        return this.zaa.isCanceled();
    }

    public final void setResultCallback(ResultCallback<? super R> resultCallback) {
        this.zaa.setResultCallback(resultCallback);
    }

    public final void setResultCallback(ResultCallback<? super R> resultCallback, long j, TimeUnit timeUnit) {
        this.zaa.setResultCallback(resultCallback, j, timeUnit);
    }

    public final void addStatusListener(PendingResult.StatusListener statusListener) {
        this.zaa.addStatusListener(statusListener);
    }

    public final <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        return this.zaa.then(resultTransform);
    }
}
