package com.appsgeyser.sdk.datasdk;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.R;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.configuration.models.ConfigPhpSdkModel;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;

public class DataSdkController {
    public static void startDataSdkController(Context context, ConfigPhp configPhp) {
        PreferencesCoder preferencesCoder = new PreferencesCoder(context);
        int prefInt = preferencesCoder.getPrefInt("countOfTry", -1);
        int countOfTry = configPhp.getCountOfTry();
        if (-1 == prefInt) {
            preferencesCoder.savePrefInt("countOfTry", countOfTry);
        }
        ConfigPhpSdkModel appsgeyserSdk = configPhp.getAppsgeyserSdk();
        boolean z = false;
        if (appsgeyserSdk.isActive() && (appsgeyserSdk.isActiveByDefault() || preferencesCoder.getPrefBoolean("appsgeyserSdk_eulaAccepted", false))) {
            z = true;
        }
        preferencesCoder.savePrefBoolean("appsgeyserSdk_isActive", z);
        int prefInt2 = preferencesCoder.getPrefInt("countOfTry", -1);
        long prefLong = preferencesCoder.getPrefLong("elapsedTime", -1);
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - prefLong;
        if (prefInt2 <= 0 || (-1 != prefLong && j <= 7200000)) {
            initSdk(configPhp, context);
        } else if (!isSdkAccepted(context)) {
            String textOfPolicy = appsgeyserSdk.getTextOfPolicy();
            StringBuilder sb = new StringBuilder();
            if (appsgeyserSdk.isActive() && !appsgeyserSdk.isActiveByDefault() && !TextUtils.isEmpty(textOfPolicy)) {
                if (sb.toString().length() > 0) {
                    sb.append("\n\n");
                }
                sb.append(textOfPolicy);
            }
            checkPermissions(context, configPhp, sb.toString());
        } else {
            checkPermissions(context, configPhp, (String) null);
        }
        preferencesCoder.savePrefLong("elapsedTime", currentTimeMillis);
    }

    public static void onGetConfigErrorResponse(Context context) {
        new PreferencesCoder(context).getPrefBoolean("appsgeyserSdk_isActive", false);
    }

    static void acceptAllActiveSdk(Context context, ConfigPhp configPhp) {
        acceptSdk(context, configPhp);
        StatController.getInstance().sendRequestAsyncByKey("click_accept_sdk_dialog");
    }

    static void declineAllActiveSdk(final Context context, final ConfigPhp configPhp, AppCompatActivity appCompatActivity, final String str) {
        if (!configPhp.getStartupELUAConfirmationDialogAllow()) {
            final DataSdkActivity dataSdkActivity = (DataSdkActivity) appCompatActivity;
            AlertDialog.Builder builder = new AlertDialog.Builder(dataSdkActivity);
            builder.setMessage(R.string.appsgeysersdk_are_you_sure_decline_sdk);
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.appsgeysersdk_close_app, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    PreferencesCoder preferencesCoder = new PreferencesCoder(dataSdkActivity);
                    DataSdkController.declineActiveSdk(context, configPhp, preferencesCoder);
                    AppsgeyserServerClient.getInstance().setConfigPhpModel((ConfigPhp) null);
                    preferencesCoder.savePrefLong("elapsedTime", 0);
                    if (Build.VERSION.SDK_INT >= 16) {
                        dataSdkActivity.finishAffinity();
                    } else {
                        ActivityCompat.finishAffinity(dataSdkActivity);
                    }
                }
            });
            builder.setNegativeButton(R.string.appsgeysersdk_back, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dataSdkActivity.showEulaDialog(str, configPhp);
                }
            });
            builder.create().show();
            return;
        }
        declineActiveSdk(context, configPhp, new PreferencesCoder(context));
        if (Build.VERSION.SDK_INT < 23 || !PermissionsRequester.isPermissionsRequired(configPhp, context)) {
            initSdk(configPhp, context);
            appCompatActivity.finish();
            return;
        }
        PermissionsRequester.requestAllActiveByDefaultPermissions((Activity) context, configPhp, 78);
    }

    /* access modifiers changed from: private */
    public static void declineActiveSdk(Context context, ConfigPhp configPhp, PreferencesCoder preferencesCoder) {
        int prefInt = preferencesCoder.getPrefInt("countOfTry", -1) - 1;
        preferencesCoder.savePrefInt("countOfTry", prefInt);
        StatController.getInstance().sendRequestAsyncByKey("click_decline_sdk_dialog");
        if (prefInt == 0 && configPhp.getAppsgeyserSdk().isActive()) {
            preferencesCoder.savePrefBoolean("appsgeyserSdk_eulaAccepted", false);
        }
    }

    static void initSdk(ConfigPhp configPhp, Context context) {
        if (AppsgeyserSDK.getFastTrackAdsController() != null) {
            AppsgeyserSDK.getFastTrackAdsController().consentRequestProcessFinished();
        }
        InternalEntryPoint.getInstance().setConsentRequestProcessActive(false);
    }

    static boolean isSdkAccepted(Context context) {
        return new PreferencesCoder(context).getPrefBoolean("sdkIsAccepted", false);
    }

    private static void checkPermissions(Context context, ConfigPhp configPhp, String str) {
        if (PermissionsRequester.isPermissionsRequired(configPhp, context) || (!isSdkAccepted(context) && !TextUtils.isEmpty(str))) {
            InternalEntryPoint.getInstance().setConsentRequestProcessActive(true);
            DataSdkActivity.startRequestPermissions(context, configPhp, str);
            return;
        }
        initSdk(configPhp, context);
    }

    private static void acceptSdk(Context context, ConfigPhp configPhp) {
        PreferencesCoder preferencesCoder = new PreferencesCoder(context);
        preferencesCoder.savePrefBoolean("sdkIsAccepted", true);
        if (configPhp.getAppsgeyserSdk().isActive()) {
            preferencesCoder.savePrefBoolean("appsgeyserSdk_eulaAccepted", true);
        }
    }
}
