package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class zah<ResultT> extends zad {
    private final TaskApiCall<Api.AnyClient, ResultT> zab;
    private final TaskCompletionSource<ResultT> zac;
    private final StatusExceptionMapper zad;

    public zah(int i, TaskApiCall<Api.AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        super(i);
        this.zac = taskCompletionSource;
        this.zab = taskApiCall;
        this.zad = statusExceptionMapper;
        if (i == 2 && taskApiCall.shouldAutoResolveMissingFeatures()) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }

    public final void zaa(GoogleApiManager.zaa<?> zaa) throws DeadObjectException {
        try {
            this.zab.doExecute(zaa.zab(), this.zac);
        } catch (DeadObjectException e) {
            throw e;
        } catch (RemoteException e2) {
            zaa(zab.zab(e2));
        } catch (RuntimeException e3) {
            zaa((Exception) e3);
        }
    }

    public final void zaa(Status status) {
        this.zac.trySetException(this.zad.getException(status));
    }

    public final void zaa(Exception exc) {
        this.zac.trySetException(exc);
    }

    public final void zaa(zav zav, boolean z) {
        zav.zaa(this.zac, z);
    }

    public final Feature[] zac(GoogleApiManager.zaa<?> zaa) {
        return this.zab.zaa();
    }

    public final boolean zad(GoogleApiManager.zaa<?> zaa) {
        return this.zab.shouldAutoResolveMissingFeatures();
    }
}
