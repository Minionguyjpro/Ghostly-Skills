package com.appsgeyser.sdk.push;

import android.content.Context;
import com.onesignal.OneSignal;

public class OneSignalCreator {
    public static void init(Context context) {
        OneSignal.startInit(context).inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification).unsubscribeWhenNotificationsAreDisabled(true).init();
    }
}
