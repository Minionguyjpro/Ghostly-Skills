package com.onesignal;

import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import org.json.JSONException;
import org.json.JSONObject;

class OSReceiveReceiptRepository {
    OSReceiveReceiptRepository() {
    }

    /* access modifiers changed from: package-private */
    public void sendReceiveReceipt(String str, String str2, String str3, OneSignalRestClient.ResponseHandler responseHandler) {
        try {
            JSONObject put = new JSONObject().put("app_id", str).put("player_id", str2);
            OneSignalRestClient.put("notifications/" + str3 + "/report_received", put, responseHandler);
        } catch (JSONException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Generating direct receive receipt:JSON Failed.", e);
        }
    }
}
