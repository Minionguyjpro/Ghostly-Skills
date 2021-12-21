package com.mopub.network;

import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ResponseHeader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HeaderUtils {
    public static String extractHeader(JSONObject jSONObject, ResponseHeader responseHeader) {
        Preconditions.checkNotNull(responseHeader);
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.optString(responseHeader.getKey());
    }

    public static JSONObject extractJsonObjectHeader(JSONObject jSONObject, ResponseHeader responseHeader) {
        Preconditions.checkNotNull(responseHeader);
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.optJSONObject(responseHeader.getKey());
    }

    public static Integer extractIntegerHeader(JSONObject jSONObject, ResponseHeader responseHeader) {
        return formatIntHeader(extractHeader(jSONObject, responseHeader));
    }

    public static Integer extractIntegerHeader(JSONObject jSONObject, ResponseHeader responseHeader, int i) {
        return formatIntHeader(extractHeader(jSONObject, responseHeader), i);
    }

    public static boolean extractBooleanHeader(JSONObject jSONObject, ResponseHeader responseHeader, boolean z) {
        return formatBooleanHeader(extractHeader(jSONObject, responseHeader), z);
    }

    public static Integer extractPercentHeader(JSONObject jSONObject, ResponseHeader responseHeader) {
        return formatPercentHeader(extractHeader(jSONObject, responseHeader));
    }

    static List<String> extractStringArray(JSONObject jSONObject, ResponseHeader responseHeader) {
        Preconditions.checkNotNull(jSONObject);
        Preconditions.checkNotNull(responseHeader);
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray(responseHeader.getKey());
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                arrayList.add(optJSONArray.getString(i));
            } catch (JSONException unused) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "Unable to parse item " + i + " from " + responseHeader.getKey());
            }
        }
        return arrayList;
    }

    static String extractPercentHeaderString(JSONObject jSONObject, ResponseHeader responseHeader) {
        Integer extractPercentHeader = extractPercentHeader(jSONObject, responseHeader);
        if (extractPercentHeader != null) {
            return extractPercentHeader.toString();
        }
        return null;
    }

    private static boolean formatBooleanHeader(String str, boolean z) {
        return str == null ? z : str.equals("1");
    }

    private static Integer formatIntHeader(String str, int i) {
        Integer formatIntHeader = formatIntHeader(str);
        return formatIntHeader == null ? Integer.valueOf(i) : formatIntHeader;
    }

    private static Integer formatIntHeader(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (Exception unused) {
            NumberFormat instance = NumberFormat.getInstance(Locale.US);
            instance.setParseIntegerOnly(true);
            try {
                return Integer.valueOf(instance.parse(str.trim()).intValue());
            } catch (Exception unused2) {
                return null;
            }
        }
    }

    private static Integer formatPercentHeader(String str) {
        Integer formatIntHeader;
        if (str != null && (formatIntHeader = formatIntHeader(str.replace("%", ""))) != null && formatIntHeader.intValue() >= 0 && formatIntHeader.intValue() <= 100) {
            return formatIntHeader;
        }
        return null;
    }
}
