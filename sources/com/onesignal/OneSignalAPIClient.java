package com.onesignal;

import org.json.JSONObject;

public interface OneSignalAPIClient {
    void post(String str, JSONObject jSONObject, OneSignalApiResponseHandler oneSignalApiResponseHandler);
}
