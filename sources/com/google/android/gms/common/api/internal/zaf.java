package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class zaf<A extends BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient>> extends zab {
    private final A zab;

    public zaf(int i, A a2) {
        super(i);
        this.zab = (BaseImplementation.ApiMethodImpl) Preconditions.checkNotNull(a2, "Null methods are not runnable.");
    }

    public final void zaa(GoogleApiManager.zaa<?> zaa) throws DeadObjectException {
        try {
            this.zab.run(zaa.zab());
        } catch (RuntimeException e) {
            zaa((Exception) e);
        }
    }

    public final void zaa(Status status) {
        this.zab.setFailedResult(status);
    }

    public final void zaa(Exception exc) {
        String simpleName = exc.getClass().getSimpleName();
        String localizedMessage = exc.getLocalizedMessage();
        StringBuilder sb = new StringBuilder(String.valueOf(simpleName).length() + 2 + String.valueOf(localizedMessage).length());
        sb.append(simpleName);
        sb.append(": ");
        sb.append(localizedMessage);
        this.zab.setFailedResult(new Status(10, sb.toString()));
    }

    public final void zaa(zav zav, boolean z) {
        zav.zaa((BasePendingResult<? extends Result>) this.zab, z);
    }
}
