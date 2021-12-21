package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public abstract class TaskApiCall<A extends Api.AnyClient, ResultT> {
    private final Feature[] zaa;
    private final boolean zab;

    @Deprecated
    public TaskApiCall() {
        this.zaa = null;
        this.zab = false;
    }

    /* access modifiers changed from: protected */
    public abstract void doExecute(A a2, TaskCompletionSource<ResultT> taskCompletionSource) throws RemoteException;

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static class Builder<A extends Api.AnyClient, ResultT> {
        /* access modifiers changed from: private */
        public RemoteCall<A, TaskCompletionSource<ResultT>> zaa;
        private boolean zab;
        private Feature[] zac;

        private Builder() {
            this.zab = true;
        }

        @Deprecated
        public Builder<A, ResultT> execute(BiConsumer<A, TaskCompletionSource<ResultT>> biConsumer) {
            this.zaa = new zacg(biConsumer);
            return this;
        }

        public Builder<A, ResultT> run(RemoteCall<A, TaskCompletionSource<ResultT>> remoteCall) {
            this.zaa = remoteCall;
            return this;
        }

        public Builder<A, ResultT> setFeatures(Feature... featureArr) {
            this.zac = featureArr;
            return this;
        }

        public Builder<A, ResultT> setAutoResolveMissingFeatures(boolean z) {
            this.zab = z;
            return this;
        }

        public TaskApiCall<A, ResultT> build() {
            Preconditions.checkArgument(this.zaa != null, "execute parameter required");
            return new zach(this, this.zac, this.zab);
        }
    }

    private TaskApiCall(Feature[] featureArr, boolean z) {
        this.zaa = featureArr;
        this.zab = z;
    }

    public final Feature[] zaa() {
        return this.zaa;
    }

    public boolean shouldAutoResolveMissingFeatures() {
        return this.zab;
    }

    public static <A extends Api.AnyClient, ResultT> Builder<A, ResultT> builder() {
        return new Builder<>();
    }
}
