package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.util.Base64;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

class PushRegistratorFCM extends PushRegistratorAbstractGoogle {
    private FirebaseApp firebaseApp;

    /* access modifiers changed from: package-private */
    public String getProviderName() {
        return "FCM";
    }

    PushRegistratorFCM() {
    }

    static void disableFirebaseInstanceIdService(Context context) {
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, FirebaseInstanceIdService.class), OSUtils.getResourceString(context, "gcm_defaultSenderId", (String) null) == null ? 2 : 1, 1);
        } catch (IllegalArgumentException | NoClassDefFoundError unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public String getToken(String str) throws Throwable {
        initFirebaseApp(str);
        return FirebaseInstanceId.getInstance(this.firebaseApp).getToken(str, "FCM");
    }

    private void initFirebaseApp(String str) {
        if (this.firebaseApp == null) {
            this.firebaseApp = FirebaseApp.initializeApp(OneSignal.appContext, new FirebaseOptions.Builder().setGcmSenderId(str).setApplicationId(getAppId()).setApiKey(getApiKey()).setProjectId(getProjectId()).build(), "ONESIGNAL_SDK_FCM_APP_NAME");
        }
    }

    private static String getAppId() {
        return OneSignal.remoteParams.fcmParams.appId != null ? OneSignal.remoteParams.fcmParams.appId : "1:754795614042:android:c682b8144a8dd52bc1ad63";
    }

    private static String getApiKey() {
        if (OneSignal.remoteParams.fcmParams.apiKey != null) {
            return OneSignal.remoteParams.fcmParams.apiKey;
        }
        return new String(Base64.decode("QUl6YVN5QW5UTG41LV80TWMyYTJQLWRLVWVFLWFCdGd5Q3JqbFlV", 0));
    }

    private static String getProjectId() {
        return OneSignal.remoteParams.fcmParams.projectId != null ? OneSignal.remoteParams.fcmParams.projectId : "onesignal-shared-public";
    }
}
