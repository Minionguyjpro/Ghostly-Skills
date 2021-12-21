package com.mopub.common;

import com.mopub.common.logging.MoPubLog;
import org.json.JSONException;
import org.json.JSONObject;

public class MoPubAdvancedBidderData {
    private static final String TOKEN_KEY = "token";
    final String mCreativeNetworkName;
    final String mToken;

    public MoPubAdvancedBidderData(String str, String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        this.mToken = str;
        this.mCreativeNetworkName = str2;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TOKEN_KEY, this.mToken);
        } catch (JSONException unused) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Invalid token format: " + this.mToken);
        }
        return jSONObject;
    }
}
