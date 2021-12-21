package com.android.installreferrer.api;

import android.content.Context;
import android.os.RemoteException;

public abstract class InstallReferrerClient {

    public static final class Builder {
        private final Context mContext;

        private Builder(Context context) {
            this.mContext = context;
        }

        public InstallReferrerClient build() {
            Context context = this.mContext;
            if (context != null) {
                return new InstallReferrerClientImpl(context);
            }
            throw new IllegalArgumentException("Please provide a valid Context.");
        }
    }

    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }

    public abstract ReferrerDetails getInstallReferrer() throws RemoteException;

    public abstract void startConnection(InstallReferrerStateListener installReferrerStateListener);
}
