package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.core.app.NotificationCompat;
import java.util.List;

public abstract class NotificationExtenderService extends JobIntentService {

    public static class OverrideSettings {
        public Integer androidNotificationId;
        public NotificationCompat.Extender extender;
    }

    static Intent getIntent(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent().setAction("com.onesignal.NotificationExtender").setPackage(context.getPackageName());
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 128);
        if (queryIntentServices.size() < 1) {
            return null;
        }
        intent.setComponent(new ComponentName(context, queryIntentServices.get(0).serviceInfo.name));
        return intent;
    }
}
