package com.onesignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class OSInAppMessageDummyController extends OSInAppMessageController {
    /* access modifiers changed from: package-private */
    public void displayPreviewMessage(String str) {
    }

    public void initRedisplayData(OneSignalDbHelper oneSignalDbHelper) {
    }

    /* access modifiers changed from: package-private */
    public void initWithCachedInAppMessages() {
    }

    /* access modifiers changed from: package-private */
    public boolean isInAppMessageShowing() {
        return false;
    }

    public void messageTriggerConditionChanged() {
    }

    public void messageWasDismissed(OSInAppMessage oSInAppMessage) {
    }

    /* access modifiers changed from: package-private */
    public void onMessageActionOccurredOnMessage(OSInAppMessage oSInAppMessage, JSONObject jSONObject) {
    }

    /* access modifiers changed from: package-private */
    public void onMessageActionOccurredOnPreview(OSInAppMessage oSInAppMessage, JSONObject jSONObject) {
    }

    /* access modifiers changed from: package-private */
    public void receivedInAppMessageJson(JSONArray jSONArray) throws JSONException {
    }

    OSInAppMessageDummyController(OneSignalDbHelper oneSignalDbHelper) {
        super(oneSignalDbHelper);
    }
}
