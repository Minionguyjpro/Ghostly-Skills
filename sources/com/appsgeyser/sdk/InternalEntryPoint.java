package com.appsgeyser.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.analytics.Analytics;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParameters;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParser;
import com.appsgeyser.sdk.deviceidparser.IDeviceIdParserListener;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;
import com.appsgeyser.sdk.server.network.NetworkAvailableReceiver;
import com.appsgeyser.sdk.server.network.NetworkManager;
import com.appsgeyser.sdk.server.network.OnNetworkStateChangedListener;
import com.appsgeyser.sdk.ui.AboutDialogActivity;
import com.appsgeyser.sdk.ui.AdvertisingTermsDialog;
import com.google.gson.JsonSyntaxException;
import com.yandex.metrica.YandexMetrica;

public final class InternalEntryPoint implements IDeviceIdParserListener {
    private static final InternalEntryPoint INSTANCE = new InternalEntryPoint();
    /* access modifiers changed from: private */
    public AppsgeyserSDK.OnAboutDialogEnableListener aboutDialogEnabledListener;
    private String additionalJsCode = "";
    /* access modifiers changed from: private */
    public AdvertisingTermsDialog advertisingTermsDialog;
    private AfterConsentRequestListener afterConsentRequestCompletedListener;
    private Application application;
    private Configuration configuration = null;
    /* access modifiers changed from: private */
    public boolean customHtmlAbout;
    private boolean doneDeviceParser;
    private boolean enablePull = false;
    /* access modifiers changed from: private */
    public DeviceIdParameters idParameters;
    private boolean isApplicationVisible = true;
    private boolean isConsentRequestProcessActive;
    private boolean isConsentRequestProcessCompleted;
    private OnNetworkStateChangedListener networkAvailableListener;
    private NetworkAvailableReceiver networkReceiver;
    /* access modifiers changed from: private */
    public AppsgeyserSDK.OnRateMyAppEnableListener rateMyAppEnableListener;
    /* access modifiers changed from: private */
    public boolean saveDialogEnableListener;
    /* access modifiers changed from: private */
    public boolean saveRateMyAppEnableListener;

    public interface AfterConsentRequestListener {
        void onConsentRequestCompleted();
    }

    public static InternalEntryPoint getInstance() {
        return INSTANCE;
    }

    /* access modifiers changed from: package-private */
    public void takeOff(Activity activity, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            if (checkPermissions(activity)) {
                init(activity, str3, str, str2);
                YandexMetrica.activate(activity, "34e75064-5ba5-4fac-b092-dc10aa167be0");
                YandexMetrica.enableActivityAutoTracking(activity.getApplication());
                try {
                    String metricaOnStartEvent = this.configuration.getMetricaOnStartEvent();
                    if (metricaOnStartEvent != null) {
                        YandexMetrica.reportEvent("on_start_event", metricaOnStartEvent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                return;
            }
        }
        if (NetworkManager.isOnline(activity)) {
            DeviceIdParser.getInstance().rescan(activity, this);
            this.doneDeviceParser = true;
        }
    }

    private void init(Activity activity, String str, String str2, String str3) {
        Configuration instance = Configuration.getInstance(activity);
        this.configuration = instance;
        instance.setTemplateVersion(str);
        this.configuration.loadConfiguration();
        if (!(this.configuration.getApplicationId() != null ? this.configuration.getApplicationId() : "").equals(str2)) {
            this.configuration.clearApplicationSettings();
            this.configuration.setApplicationId(str2);
            this.configuration.setMetricaOnStartEvent(str3, str);
        }
        Analytics.getInstance(activity).activityStarted(activity);
        this.advertisingTermsDialog = new AdvertisingTermsDialog(activity);
        if (this.networkReceiver == null) {
            this.networkReceiver = NetworkAvailableReceiver.createAndRegisterNetworkReceiver(activity);
        }
        OnNetworkStateChangedListener createNetworkAvailableListener = this.networkReceiver.createNetworkAvailableListener(activity);
        this.networkAvailableListener = createNetworkAvailableListener;
        this.networkReceiver.addListener(createNetworkAvailableListener);
    }

    public void retryParsers(Context context) {
        if (!this.doneDeviceParser) {
            DeviceIdParser.getInstance().rescan(context, this);
            this.doneDeviceParser = true;
        }
    }

    private boolean checkPermissions(Activity activity) {
        if (activity.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0 && activity.checkCallingOrSelfPermission("android.permission.INTERNET") == 0) {
            return true;
        }
        ExceptionHandler.handleException(new Exception("Invalid permission. You have to grant ACCESS_NETWORK_STATE and INTERNET permissions to work properly"));
        return false;
    }

    /* access modifiers changed from: package-private */
    public void onPause(Context context) {
        if (context instanceof Activity) {
            ((Activity) context).getLocalClassName();
        }
        NetworkAvailableReceiver networkAvailableReceiver = this.networkReceiver;
        if (networkAvailableReceiver != null) {
            try {
                context.unregisterReceiver(networkAvailableReceiver);
            } catch (IllegalArgumentException unused) {
            }
        }
        FastTrackAdsController.getInstance().onPause();
        INSTANCE.setApplicationVisible(false);
    }

    /* access modifiers changed from: package-private */
    public void onResume(Context context) {
        if (context instanceof Activity) {
            ((Activity) context).getLocalClassName();
        }
        this.configuration = Configuration.getInstance(context);
        if (this.networkReceiver == null) {
            NetworkAvailableReceiver createAndRegisterNetworkReceiver = NetworkAvailableReceiver.createAndRegisterNetworkReceiver(context);
            this.networkReceiver = createAndRegisterNetworkReceiver;
            OnNetworkStateChangedListener createNetworkAvailableListener = createAndRegisterNetworkReceiver.createNetworkAvailableListener(context);
            this.networkAvailableListener = createNetworkAvailableListener;
            this.networkReceiver.addListener(createNetworkAvailableListener);
        } else {
            context.registerReceiver(this.networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        FastTrackAdsController.getInstance().onResume(context);
        INSTANCE.setApplicationVisible(true);
    }

    public void onDeviceIdParametersObtained(Context context, DeviceIdParameters deviceIdParameters) {
        this.idParameters = deviceIdParameters;
        AppsgeyserServerClient.getInstance().getConfigPhp(context, this.idParameters, new AppsgeyserServerClient.ConfigPhpRequestListener() {
            public void receivedConfigPhp(ConfigPhp configPhp) {
                if (configPhp != null) {
                    if (InternalEntryPoint.this.advertisingTermsDialog != null) {
                        InternalEntryPoint.this.advertisingTermsDialog.show(configPhp.isAdvertisingTermsDialog());
                    }
                    if (InternalEntryPoint.this.saveDialogEnableListener) {
                        InternalEntryPoint.this.aboutDialogEnabledListener.onDialogEnableReceived(configPhp.isAboutScreenEnabled());
                    }
                    if (InternalEntryPoint.this.saveRateMyAppEnableListener) {
                        InternalEntryPoint.this.rateMyAppEnableListener.onRateMyAppEnableReceived(configPhp.isRateMyAppActive());
                    }
                    Constants.setFullScreenDelay(configPhp.getFullScreenDelay());
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void showAboutDialog(Activity activity) {
        final Intent intent = new Intent(activity, AboutDialogActivity.class);
        intent.setFlags(268435456);
        if (this.idParameters != null) {
            AppsgeyserServerClient.getInstance().getConfigPhp(activity, this.idParameters, new AppsgeyserServerClient.ConfigPhpRequestListener() {
                public void receivedConfigPhp(ConfigPhp configPhp) {
                    intent.putExtra("config_php_key", configPhp);
                    boolean unused = InternalEntryPoint.this.customHtmlAbout = configPhp.isCustomAboutActive();
                }
            });
        } else {
            String prefString = Configuration.getInstance(activity).getSettingsCoder().getPrefString("ServerResponse", "");
            if (!TextUtils.isEmpty(prefString)) {
                try {
                    ConfigPhp parseFromJson = ConfigPhp.parseFromJson(prefString);
                    intent.putExtra("config_php_key", parseFromJson);
                    this.customHtmlAbout = parseFromJson.isCustomAboutActive();
                } catch (JsonSyntaxException unused) {
                }
            } else {
                intent.putExtra("config_php_key", "");
            }
        }
        if (!this.customHtmlAbout) {
            activity.startActivity(intent);
        } else {
            PausedContentInfoActivity.startPausedContentInfoActivity(activity, true);
        }
    }

    /* access modifiers changed from: package-private */
    public void getNewConfigPhp(Context context, final AppsgeyserSDK.OnAboutDialogEnableListener onAboutDialogEnableListener) {
        this.aboutDialogEnabledListener = onAboutDialogEnableListener;
        if (this.idParameters != null) {
            AppsgeyserServerClient.getInstance().getConfigPhp(context, this.idParameters, new AppsgeyserServerClient.ConfigPhpRequestListener() {
                public void receivedConfigPhp(ConfigPhp configPhp) {
                    onAboutDialogEnableListener.onDialogEnableReceived(configPhp.isAboutScreenEnabled());
                    boolean unused = InternalEntryPoint.this.saveDialogEnableListener = false;
                }
            });
            return;
        }
        String prefString = Configuration.getInstance(context).getSettingsCoder().getPrefString("ServerResponse", "");
        if (!TextUtils.isEmpty(prefString)) {
            try {
                onAboutDialogEnableListener.onDialogEnableReceived(ConfigPhp.parseFromJson(prefString).isAboutScreenEnabled());
                this.saveDialogEnableListener = false;
            } catch (JsonSyntaxException unused) {
                this.saveDialogEnableListener = true;
            }
        } else {
            this.saveDialogEnableListener = true;
        }
    }

    public void setAdditionalJsCode(String str) {
        this.additionalJsCode = str;
    }

    public String getAdditionalJsCode() {
        return this.additionalJsCode;
    }

    /* access modifiers changed from: package-private */
    public void checkIsOfferWallEnabled(Context context, final AppsgeyserSDK.OfferWallEnabledListener offerWallEnabledListener) {
        if (this.idParameters != null) {
            AppsgeyserServerClient.getInstance().getConfigPhp(context, this.idParameters, new AppsgeyserServerClient.ConfigPhpRequestListener() {
                public void receivedConfigPhp(ConfigPhp configPhp) {
                    offerWallEnabledListener.isOfferWallEnabled(configPhp.isOfferWallEnabled());
                }
            });
        } else {
            DeviceIdParser.getInstance().rescan(context, new IDeviceIdParserListener() {
                public void onDeviceIdParametersObtained(Context context, DeviceIdParameters deviceIdParameters) {
                    DeviceIdParameters unused = InternalEntryPoint.this.idParameters = deviceIdParameters;
                    AppsgeyserServerClient.getInstance().getConfigPhp(context, deviceIdParameters, new AppsgeyserServerClient.ConfigPhpRequestListener() {
                        public void receivedConfigPhp(ConfigPhp configPhp) {
                            offerWallEnabledListener.isOfferWallEnabled(configPhp.isOfferWallEnabled());
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public FastTrackAdsController getFastTrackAdsController() {
        return FastTrackAdsController.getInstance();
    }

    public void setApplication(Application application2) {
        this.application = application2;
    }

    public void setApplicationVisible(boolean z) {
        this.isApplicationVisible = z;
    }

    public boolean isConsentRequestProcessActive() {
        return this.isConsentRequestProcessActive;
    }

    public void setConsentRequestProcessActive(boolean z) {
        if (!this.isConsentRequestProcessCompleted) {
            this.isConsentRequestProcessActive = z;
            if (!z) {
                this.isConsentRequestProcessCompleted = true;
                AfterConsentRequestListener afterConsentRequestListener = this.afterConsentRequestCompletedListener;
                if (afterConsentRequestListener != null) {
                    afterConsentRequestListener.onConsentRequestCompleted();
                    this.afterConsentRequestCompletedListener = null;
                    return;
                }
                return;
            }
            return;
        }
        this.isConsentRequestProcessActive = false;
    }
}
