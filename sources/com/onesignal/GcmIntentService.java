package com.onesignal;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import com.onesignal.NotificationExtenderService;

public class GcmIntentService extends IntentService {
    public GcmIntentService() {
        super("GcmIntentService");
        setIntentRedelivery(true);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            NotificationBundleProcessor.ProcessFromGCMIntentService(this, new BundleCompatBundle(extras), (NotificationExtenderService.OverrideSettings) null);
            GcmBroadcastReceiver.completeWakefulIntent(intent);
        }
    }
}
