package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zaz;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public class BaseImplementation {

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public interface ResultHolder<R> {
        void setFailedResult(Status status);

        void setResult(R r);
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static abstract class ApiMethodImpl<R extends Result, A extends Api.AnyClient> extends BasePendingResult<R> implements ResultHolder<R> {
        private final Api<?> mApi;
        private final Api.AnyClientKey<A> mClientKey;

        @Deprecated
        protected ApiMethodImpl(Api.AnyClientKey<A> anyClientKey, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) Preconditions.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
            this.mClientKey = (Api.AnyClientKey) Preconditions.checkNotNull(anyClientKey);
            this.mApi = null;
        }

        /* access modifiers changed from: protected */
        public abstract void doExecute(A a2) throws RemoteException;

        /* access modifiers changed from: protected */
        public void onSetFailedResult(R r) {
        }

        protected ApiMethodImpl(Api<?> api, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) Preconditions.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
            Preconditions.checkNotNull(api, "Api must not be null");
            this.mClientKey = api.zac();
            this.mApi = api;
        }

        protected ApiMethodImpl(BasePendingResult.CallbackHandler<R> callbackHandler) {
            super(callbackHandler);
            this.mClientKey = new Api.AnyClientKey<>();
            this.mApi = null;
        }

        public final Api.AnyClientKey<A> getClientKey() {
            return this.mClientKey;
        }

        public final Api<?> getApi() {
            return this.mApi;
        }

        public final void run(A a2) throws DeadObjectException {
            if (a2 instanceof zaz) {
                zaz zaz = (zaz) a2;
                a2 = zaz.zaa();
            }
            try {
                doExecute(a2);
            } catch (DeadObjectException e) {
                setFailedResult((RemoteException) e);
                throw e;
            } catch (RemoteException e2) {
                setFailedResult(e2);
            }
        }

        public final void setFailedResult(Status status) {
            Preconditions.checkArgument(!status.isSuccess(), "Failed result must not be success");
            Result createFailedResult = createFailedResult(status);
            setResult(createFailedResult);
            onSetFailedResult(createFailedResult);
        }

        private void setFailedResult(RemoteException remoteException) {
            setFailedResult(new Status(8, remoteException.getLocalizedMessage(), (PendingIntent) null));
        }

        public /* bridge */ /* synthetic */ void setResult(Object obj) {
            super.setResult((Result) obj);
        }
    }
}
