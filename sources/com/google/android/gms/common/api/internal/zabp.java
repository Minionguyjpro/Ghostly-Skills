package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final /* synthetic */ class zabp implements Runnable {
    private final NonGmsServiceBrokerClient zaa;

    zabp(NonGmsServiceBrokerClient nonGmsServiceBrokerClient) {
        this.zaa = nonGmsServiceBrokerClient;
    }

    public final void run() {
        this.zaa.zaa();
    }
}
