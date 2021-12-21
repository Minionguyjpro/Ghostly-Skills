package com.onesignal;

import com.onesignal.OneSignalRestClient;
import org.json.JSONObject;

class OneSignalRestClientWrapper implements OneSignalAPIClient {
    OneSignalRestClientWrapper() {
    }

    public void post(String str, JSONObject jSONObject, final OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        OneSignalRestClient.post(str, jSONObject, new OneSignalRestClient.ResponseHandler() {
            public void onSuccess(String str) {
                oneSignalApiResponseHandler.onSuccess(str);
            }

            public void onFailure(int i, String str, Throwable th) {
                oneSignalApiResponseHandler.onFailure(i, str, th);
            }
        });
    }
}
