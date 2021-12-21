package com.mopub.network;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MoPubRequestUtils {
    public static String truncateQueryParamsIfPost(String str) {
        int indexOf;
        Preconditions.checkNotNull(str);
        if (isMoPubRequest(str) && (indexOf = str.indexOf(63)) != -1) {
            return str.substring(0, indexOf);
        }
        return str;
    }

    public static boolean isMoPubRequest(String str) {
        Preconditions.checkNotNull(str);
        String str2 = "http://" + Constants.HOST;
        StringBuilder sb = new StringBuilder();
        sb.append("https://");
        sb.append(Constants.HOST);
        return str.startsWith(str2) || str.startsWith(sb.toString());
    }

    public static int chooseMethod(String str) {
        return isMoPubRequest(str) ? 1 : 0;
    }

    public static Map<String, String> convertQueryToMap(Context context, String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        return getQueryParamMap(Uri.parse(Networking.getUrlRewriter(context).rewriteUrl(str)));
    }

    public static Map<String, String> getQueryParamMap(Uri uri) {
        Preconditions.checkNotNull(uri);
        HashMap hashMap = new HashMap();
        for (String next : uri.getQueryParameterNames()) {
            hashMap.put(next, TextUtils.join(",", uri.getQueryParameters(next)));
        }
        return hashMap;
    }

    public static String generateBodyFromParams(Map<String, String> map, String str) {
        Preconditions.checkNotNull(str);
        if (!isMoPubRequest(str) || map == null || map.isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String next : map.keySet()) {
            try {
                jSONObject.put(next, map.get(next));
            } catch (JSONException unused) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "Unable to add " + next + " to JSON body.");
            }
        }
        return jSONObject.toString();
    }

    private MoPubRequestUtils() {
    }
}
