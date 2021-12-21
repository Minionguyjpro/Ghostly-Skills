package com.onesignal;

import android.app.Activity;
import android.content.Intent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class NotificationPayloadProcessorHMS {
    static void handleHMSNotificationOpenIntent(Activity activity, Intent intent) {
        JSONObject covertHMSOpenIntentToJson;
        OneSignal.setAppContext(activity);
        if (intent != null && (covertHMSOpenIntentToJson = covertHMSOpenIntentToJson(intent)) != null) {
            handleProcessJsonOpenData(activity, covertHMSOpenIntentToJson);
        }
    }

    private static JSONObject covertHMSOpenIntentToJson(Intent intent) {
        if (!OSNotificationFormatHelper.isOneSignalIntent(intent)) {
            return null;
        }
        JSONObject bundleAsJSONObject = NotificationBundleProcessor.bundleAsJSONObject(intent.getExtras());
        reformatButtonClickAction(bundleAsJSONObject);
        return bundleAsJSONObject;
    }

    private static void reformatButtonClickAction(JSONObject jSONObject) {
        try {
            String str = (String) NotificationBundleProcessor.getCustomJSONObject(jSONObject).remove("actionId");
            if (str != null) {
                jSONObject.put("actionId", str);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void handleProcessJsonOpenData(Activity activity, JSONObject jSONObject) {
        if (!NotificationOpenedProcessor.handleIAMPreviewOpen(activity, jSONObject)) {
            OneSignal.handleNotificationOpen(activity, new JSONArray().put(jSONObject), false, OSNotificationFormatHelper.getOSNotificationIdFromJson(jSONObject));
        }
    }
}
