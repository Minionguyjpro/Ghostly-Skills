package com.mopub.common;

import android.content.Context;
import android.content.SharedPreferences;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Json;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseAdapterConfiguration implements AdapterConfiguration {
    static final String CUSTOM_EVENT_PREF_NAME = "mopubCustomEventSettings";
    private Map<String, String> mMoPubRequestOptions;

    public void setCachedInitializationParameters(Context context, Map<String, String> map) {
        Preconditions.checkNotNull(context);
        if (map != null && !map.isEmpty()) {
            SharedPreferences sharedPreferences = SharedPreferencesHelper.getSharedPreferences(context, CUSTOM_EVENT_PREF_NAME);
            String jSONObject = new JSONObject(map).toString();
            String name = getClass().getName();
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "Updating init settings for custom event %s with params %s", new Object[]{name, jSONObject}));
            sharedPreferences.edit().putString(name, jSONObject).apply();
        }
    }

    public Map<String, String> getCachedInitializationParameters(Context context) {
        Map<String, ?> all = SharedPreferencesHelper.getSharedPreferences(context, CUSTOM_EVENT_PREF_NAME).getAll();
        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format(Locale.US, "fetched init settings for %s networks: %s", new Object[]{Integer.valueOf(all.size()), all.keySet()}));
        String name = getClass().getName();
        String str = (String) all.get(name);
        HashMap hashMap = new HashMap();
        try {
            return Json.jsonStringToMap(str);
        } catch (JSONException unused) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Error fetching init settings for adapter configuration " + name);
            return hashMap;
        }
    }

    public void setMoPubRequestOptions(Map<String, String> map) {
        this.mMoPubRequestOptions = map;
    }

    public Map<String, String> getMoPubRequestOptions() {
        return this.mMoPubRequestOptions;
    }
}
