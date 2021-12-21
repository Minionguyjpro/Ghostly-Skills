package com.appsgeyser.sdk.analytics;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.appsgeyser.sdk.Logger;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;

public final class Analytics {
    private static Analytics instance;
    private Configuration configuration;
    /* access modifiers changed from: private */
    public AppsgeyserServerClient serverClient;
    private boolean usageAlreadySent = false;

    public static synchronized Analytics getInstance(Context context) {
        Analytics analytics;
        synchronized (Analytics.class) {
            if (instance == null) {
                instance = new Analytics(context);
            }
            analytics = instance;
        }
        return analytics;
    }

    private Analytics(Context context) {
        this.configuration = Configuration.getInstance(context);
        this.serverClient = AppsgeyserServerClient.getInstance();
    }

    public void activityStarted(Context context) {
        sendActivityStartedInfo(context);
    }

    private void sendActivityStartedInfo(final Context context) {
        if (!this.configuration.isRegistered()) {
            this.configuration.registerNew();
            this.serverClient.sendAfterInstallInfo(context);
            Logger.DebugLog("App install was sent: id " + this.configuration.getApplicationId() + " , guid " + this.configuration.getAppGuid());
            final InstallReferrerClient build = InstallReferrerClient.newBuilder(context).build();
            build.startConnection(new InstallReferrerStateListener() {
                public void onInstallReferrerSetupFinished(int i) {
                    if (i == 0) {
                        try {
                            ReferrerDetails installReferrer = build.getInstallReferrer();
                            Log.d("referrerLogTag", "Sending referrer info");
                            Analytics.this.serverClient.sendReferrerStatsRequest(context, Constants.ReferrerInfoStatus.OK, installReferrer);
                        } catch (RemoteException e) {
                            Log.d("referrerLogTag", "RE while getting OK response: " + e.getMessage());
                            Analytics.this.serverClient.sendReferrerStatsRequest(context, Constants.ReferrerInfoStatus.REMOTE_EXCEPTION, (ReferrerDetails) null);
                        }
                    } else if (i == 1) {
                        Log.d("referrerLogTag", "Service unavailable");
                        Analytics.this.serverClient.sendReferrerStatsRequest(context, Constants.ReferrerInfoStatus.UNAVAILABLE, (ReferrerDetails) null);
                    } else if (i == 2) {
                        Log.d("referrerLogTag", "Feature not supported");
                        Analytics.this.serverClient.sendReferrerStatsRequest(context, Constants.ReferrerInfoStatus.FEATURE_NOT_SUPPORTED, (ReferrerDetails) null);
                    }
                }

                public void onInstallReferrerServiceDisconnected() {
                    Log.d("referrerLogTag", "Install referrer service disconnected");
                }
            });
        }
        if (!this.usageAlreadySent) {
            this.serverClient.sendUsageInfo(context);
            Logger.DebugLog("App usage was sent: id " + this.configuration.getApplicationId() + " , guid " + this.configuration.getAppGuid());
            this.usageAlreadySent = true;
        }
        this.serverClient.sendUpdateInfo(context);
        this.serverClient.sendApplicationMode(context);
    }

    public boolean isUsageAlreadySent() {
        return this.usageAlreadySent;
    }
}
