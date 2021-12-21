package com.mopub.network;

import android.content.Context;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.AdType;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.FullAdType;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Json;
import com.mopub.common.util.ResponseHeader;
import com.mopub.mobileads.AdTypeTranslator;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.toolbox.HttpHeaderParser;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MultiAdResponse implements Iterator<AdResponse> {
    private static ServerOverrideListener sServerOverrideListener;
    private String mFailUrl;
    private final Iterator<AdResponse> mResponseIterator;

    public interface ServerOverrideListener {
        void onForceExplicitNo(String str);

        void onForceGdprApplies();

        void onInvalidateConsent(String str);

        void onReacquireConsent(String str);

        void onRequestSuccess(String str);
    }

    public String getFailURL() {
        return this.mFailUrl;
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0151 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MultiAdResponse(android.content.Context r19, com.mopub.volley.NetworkResponse r20, com.mopub.common.AdFormat r21, java.lang.String r22) throws org.json.JSONException, com.mopub.network.MoPubNetworkError {
        /*
            r18 = this;
            r1 = r18
            r9 = r22
            r18.<init>()
            java.lang.String r10 = parseStringBody(r20)
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>(r10)
            com.mopub.common.util.ResponseHeader r2 = com.mopub.common.util.ResponseHeader.FAIL_URL
            java.lang.String r2 = r2.getKey()
            java.lang.String r2 = r0.optString(r2)
            r1.mFailUrl = r2
            com.mopub.common.util.ResponseHeader r2 = com.mopub.common.util.ResponseHeader.ADUNIT_FORMAT
            java.lang.String r2 = r2.getKey()
            java.lang.String r11 = r0.optString(r2)
            com.mopub.common.util.ResponseHeader r2 = com.mopub.common.util.ResponseHeader.REQUEST_ID
            java.lang.String r2 = r2.getKey()
            java.lang.String r12 = r0.optString(r2)
            com.mopub.common.util.ResponseHeader r2 = com.mopub.common.util.ResponseHeader.BACKOFF_MS
            java.lang.Integer r2 = com.mopub.network.HeaderUtils.extractIntegerHeader(r0, r2)
            com.mopub.common.util.ResponseHeader r3 = com.mopub.common.util.ResponseHeader.BACKOFF_REASON
            java.lang.String r3 = com.mopub.network.HeaderUtils.extractHeader(r0, r3)
            com.mopub.network.RequestRateTracker r4 = com.mopub.network.RequestRateTracker.getInstance()
            r4.registerRateLimit(r9, r2, r3)
            com.mopub.common.util.ResponseHeader r2 = com.mopub.common.util.ResponseHeader.INVALIDATE_CONSENT
            r13 = 0
            boolean r2 = com.mopub.network.HeaderUtils.extractBooleanHeader(r0, r2, r13)
            com.mopub.common.util.ResponseHeader r3 = com.mopub.common.util.ResponseHeader.FORCE_EXPLICIT_NO
            boolean r3 = com.mopub.network.HeaderUtils.extractBooleanHeader(r0, r3, r13)
            com.mopub.common.util.ResponseHeader r4 = com.mopub.common.util.ResponseHeader.REACQUIRE_CONSENT
            boolean r4 = com.mopub.network.HeaderUtils.extractBooleanHeader(r0, r4, r13)
            com.mopub.common.util.ResponseHeader r5 = com.mopub.common.util.ResponseHeader.CONSENT_CHANGE_REASON
            java.lang.String r5 = com.mopub.network.HeaderUtils.extractHeader(r0, r5)
            com.mopub.common.util.ResponseHeader r6 = com.mopub.common.util.ResponseHeader.FORCE_GDPR_APPLIES
            boolean r6 = com.mopub.network.HeaderUtils.extractBooleanHeader(r0, r6, r13)
            com.mopub.network.MultiAdResponse$ServerOverrideListener r7 = sServerOverrideListener
            if (r7 == 0) goto L_0x0087
            if (r6 == 0) goto L_0x006b
            r7.onForceGdprApplies()
        L_0x006b:
            if (r3 == 0) goto L_0x0073
            com.mopub.network.MultiAdResponse$ServerOverrideListener r2 = sServerOverrideListener
            r2.onForceExplicitNo(r5)
            goto L_0x0082
        L_0x0073:
            if (r2 == 0) goto L_0x007b
            com.mopub.network.MultiAdResponse$ServerOverrideListener r2 = sServerOverrideListener
            r2.onInvalidateConsent(r5)
            goto L_0x0082
        L_0x007b:
            if (r4 == 0) goto L_0x0082
            com.mopub.network.MultiAdResponse$ServerOverrideListener r2 = sServerOverrideListener
            r2.onReacquireConsent(r5)
        L_0x0082:
            com.mopub.network.MultiAdResponse$ServerOverrideListener r2 = sServerOverrideListener
            r2.onRequestSuccess(r9)
        L_0x0087:
            com.mopub.common.util.ResponseHeader r2 = com.mopub.common.util.ResponseHeader.ENABLE_DEBUG_LOGGING
            boolean r2 = com.mopub.network.HeaderUtils.extractBooleanHeader(r0, r2, r13)
            if (r2 == 0) goto L_0x0094
            com.mopub.common.logging.MoPubLog$LogLevel r2 = com.mopub.common.logging.MoPubLog.LogLevel.DEBUG
            com.mopub.common.logging.MoPubLog.setLogLevel(r2)
        L_0x0094:
            com.mopub.common.util.ResponseHeader r2 = com.mopub.common.util.ResponseHeader.AD_RESPONSES
            java.lang.String r2 = r2.getKey()
            org.json.JSONArray r14 = r0.getJSONArray(r2)
            r0 = 3
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>(r0)
            r0 = 0
            r16 = r0
            r8 = 0
        L_0x00a8:
            int r0 = r14.length()
            if (r8 >= r0) goto L_0x0175
            r7 = 1
            org.json.JSONObject r0 = r14.getJSONObject(r8)     // Catch:{ JSONException -> 0x0152, MoPubNetworkError -> 0x0125, Exception -> 0x0100 }
            r2 = r19
            r3 = r20
            r4 = r0
            r5 = r22
            r6 = r21
            r13 = 1
            r7 = r11
            r17 = r8
            r8 = r12
            com.mopub.network.AdResponse r2 = parseSingleAdResponse(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ JSONException -> 0x0155, MoPubNetworkError -> 0x00fe, Exception -> 0x00fc }
            java.lang.String r3 = "clear"
            java.lang.String r4 = r2.getAdType()     // Catch:{ JSONException -> 0x0155, MoPubNetworkError -> 0x00fe, Exception -> 0x00fc }
            boolean r3 = r3.equals(r4)     // Catch:{ JSONException -> 0x0155, MoPubNetworkError -> 0x00fe, Exception -> 0x00fc }
            if (r3 != 0) goto L_0x00d5
            r15.add(r2)     // Catch:{ JSONException -> 0x0155, MoPubNetworkError -> 0x00fe, Exception -> 0x00fc }
            goto L_0x0123
        L_0x00d5:
            java.lang.String r3 = ""
            r1.mFailUrl = r3     // Catch:{ JSONException -> 0x0155, MoPubNetworkError -> 0x00fe, Exception -> 0x00fc }
            boolean r0 = extractWarmup(r0)     // Catch:{ JSONException -> 0x00f9, MoPubNetworkError -> 0x00f5, Exception -> 0x00f1 }
            if (r0 != 0) goto L_0x00e3
            r16 = r2
            goto L_0x0175
        L_0x00e3:
            com.mopub.network.MoPubNetworkError r0 = new com.mopub.network.MoPubNetworkError     // Catch:{ JSONException -> 0x00f9, MoPubNetworkError -> 0x00f5, Exception -> 0x00f1 }
            java.lang.String r3 = "Server is preparing this Ad Unit."
            com.mopub.network.MoPubNetworkError$Reason r4 = com.mopub.network.MoPubNetworkError.Reason.WARMING_UP     // Catch:{ JSONException -> 0x00f9, MoPubNetworkError -> 0x00f5, Exception -> 0x00f1 }
            java.lang.Integer r5 = r2.getRefreshTimeMillis()     // Catch:{ JSONException -> 0x00f9, MoPubNetworkError -> 0x00f5, Exception -> 0x00f1 }
            r0.<init>((java.lang.String) r3, (com.mopub.network.MoPubNetworkError.Reason) r4, (java.lang.Integer) r5)     // Catch:{ JSONException -> 0x00f9, MoPubNetworkError -> 0x00f5, Exception -> 0x00f1 }
            throw r0     // Catch:{ JSONException -> 0x00f9, MoPubNetworkError -> 0x00f5, Exception -> 0x00f1 }
        L_0x00f1:
            r0 = move-exception
            r16 = r2
            goto L_0x0104
        L_0x00f5:
            r0 = move-exception
            r16 = r2
            goto L_0x0129
        L_0x00f9:
            r16 = r2
            goto L_0x0155
        L_0x00fc:
            r0 = move-exception
            goto L_0x0104
        L_0x00fe:
            r0 = move-exception
            goto L_0x0129
        L_0x0100:
            r0 = move-exception
            r17 = r8
            r13 = 1
        L_0x0104:
            com.mopub.common.logging.MoPubLog$AdLogEvent r2 = com.mopub.common.logging.MoPubLog.AdLogEvent.CUSTOM
            java.lang.Object[] r3 = new java.lang.Object[r13]
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Unexpected error parsing response item. "
            r4.append(r5)
            java.lang.String r0 = r0.getMessage()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r4 = 0
            r3[r4] = r0
            com.mopub.common.logging.MoPubLog.log(r2, r3)
        L_0x0123:
            r4 = 0
            goto L_0x0170
        L_0x0125:
            r0 = move-exception
            r17 = r8
            r13 = 1
        L_0x0129:
            com.mopub.network.MoPubNetworkError$Reason r2 = r0.getReason()
            com.mopub.network.MoPubNetworkError$Reason r3 = com.mopub.network.MoPubNetworkError.Reason.WARMING_UP
            if (r2 == r3) goto L_0x0151
            com.mopub.common.logging.MoPubLog$AdLogEvent r2 = com.mopub.common.logging.MoPubLog.AdLogEvent.CUSTOM
            java.lang.Object[] r3 = new java.lang.Object[r13]
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Invalid response item. Error: "
            r4.append(r5)
            com.mopub.network.MoPubNetworkError$Reason r0 = r0.getReason()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r4 = 0
            r3[r4] = r0
            com.mopub.common.logging.MoPubLog.log(r2, r3)
            goto L_0x0123
        L_0x0151:
            throw r0
        L_0x0152:
            r17 = r8
            r13 = 1
        L_0x0155:
            com.mopub.common.logging.MoPubLog$AdLogEvent r0 = com.mopub.common.logging.MoPubLog.AdLogEvent.CUSTOM
            java.lang.Object[] r2 = new java.lang.Object[r13]
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Invalid response item. Body: "
            r3.append(r4)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            r4 = 0
            r2[r4] = r3
            com.mopub.common.logging.MoPubLog.log(r0, r2)
        L_0x0170:
            int r8 = r17 + 1
            r13 = 0
            goto L_0x00a8
        L_0x0175:
            java.util.Iterator r0 = r15.iterator()
            r1.mResponseIterator = r0
            boolean r0 = r0.hasNext()
            if (r0 != 0) goto L_0x0197
            r0 = 30000(0x7530, float:4.2039E-41)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            if (r16 == 0) goto L_0x018d
            java.lang.Integer r0 = r16.getRefreshTimeMillis()
        L_0x018d:
            com.mopub.network.MoPubNetworkError r2 = new com.mopub.network.MoPubNetworkError
            com.mopub.network.MoPubNetworkError$Reason r3 = com.mopub.network.MoPubNetworkError.Reason.NO_FILL
            java.lang.String r4 = "No ads found for ad unit."
            r2.<init>((java.lang.String) r4, (com.mopub.network.MoPubNetworkError.Reason) r3, (java.lang.Integer) r0)
            throw r2
        L_0x0197:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.network.MultiAdResponse.<init>(android.content.Context, com.mopub.volley.NetworkResponse, com.mopub.common.AdFormat, java.lang.String):void");
    }

    public boolean hasNext() {
        return this.mResponseIterator.hasNext();
    }

    public AdResponse next() {
        return this.mResponseIterator.next();
    }

    /* access modifiers changed from: package-private */
    public boolean isWaterfallFinished() {
        return TextUtils.isEmpty(this.mFailUrl);
    }

    public static void setServerOverrideListener(ServerOverrideListener serverOverrideListener) {
        sServerOverrideListener = serverOverrideListener;
    }

    protected static AdResponse parseSingleAdResponse(Context context, NetworkResponse networkResponse, JSONObject jSONObject, String str, AdFormat adFormat, String str2, String str3) throws JSONException, MoPubNetworkError {
        ExternalViewabilitySessionManager.ViewabilityVendor fromKey;
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(networkResponse);
        Preconditions.checkNotNull(jSONObject);
        Preconditions.checkNotNull(adFormat);
        Preconditions.checkNotNull(str2);
        boolean z = true;
        MoPubLog.log(MoPubLog.AdLogEvent.RESPONSE_RECEIVED, jSONObject.toString());
        AdResponse.Builder builder = new AdResponse.Builder();
        String optString = jSONObject.optString(ResponseHeader.CONTENT.getKey());
        JSONObject jSONObject2 = jSONObject.getJSONObject(ResponseHeader.METADATA.getKey());
        builder.setAdUnitId(str);
        builder.setResponseBody(optString);
        String extractHeader = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.AD_TYPE);
        String extractHeader2 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.AD_GROUP_ID);
        String extractHeader3 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.FULL_AD_TYPE);
        builder.setAdType(extractHeader);
        builder.setAdGroupId(extractHeader2);
        builder.setFullAdType(extractHeader3);
        builder.setRefreshTimeMilliseconds(extractRefreshTimeMS(jSONObject));
        if (AdType.CLEAR.equals(extractHeader)) {
            return builder.build();
        }
        builder.setDspCreativeId(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.DSP_CREATIVE_ID));
        builder.setNetworkType(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.NETWORK_TYPE));
        builder.setImpressionData(ImpressionData.create(HeaderUtils.extractJsonObjectHeader(jSONObject2, ResponseHeader.IMPRESSION_DATA)));
        String extractHeader4 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.CLICK_TRACKING_URL);
        builder.setClickTrackingUrl(extractHeader4);
        List<String> extractStringArray = HeaderUtils.extractStringArray(jSONObject2, ResponseHeader.IMPRESSION_URLS);
        if (extractStringArray.isEmpty()) {
            extractStringArray.add(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.IMPRESSION_URL));
        }
        builder.setImpressionTrackingUrls(extractStringArray);
        builder.setBeforeLoadUrl(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.BEFORE_LOAD_URL));
        List<String> extractStringArray2 = HeaderUtils.extractStringArray(jSONObject2, ResponseHeader.AFTER_LOAD_URL);
        if (extractStringArray2.isEmpty()) {
            extractStringArray2.add(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.AFTER_LOAD_URL));
        }
        builder.setAfterLoadUrls(extractStringArray2);
        List<String> extractStringArray3 = HeaderUtils.extractStringArray(jSONObject2, ResponseHeader.AFTER_LOAD_SUCCESS_URL);
        if (extractStringArray3.isEmpty()) {
            extractStringArray3.add(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.AFTER_LOAD_SUCCESS_URL));
        }
        builder.setAfterLoadSuccessUrls(extractStringArray3);
        List<String> extractStringArray4 = HeaderUtils.extractStringArray(jSONObject2, ResponseHeader.AFTER_LOAD_FAIL_URL);
        if (extractStringArray4.isEmpty()) {
            extractStringArray4.add(HeaderUtils.extractHeader(jSONObject2, ResponseHeader.AFTER_LOAD_FAIL_URL));
        }
        builder.setAfterLoadFailUrls(extractStringArray4);
        builder.setRequestId(str3);
        builder.setDimensions(HeaderUtils.extractIntegerHeader(jSONObject2, ResponseHeader.WIDTH), HeaderUtils.extractIntegerHeader(jSONObject2, ResponseHeader.HEIGHT));
        builder.setAdTimeoutDelayMilliseconds(HeaderUtils.extractIntegerHeader(jSONObject2, ResponseHeader.AD_TIMEOUT));
        if (AdType.STATIC_NATIVE.equals(extractHeader) || AdType.VIDEO_NATIVE.equals(extractHeader)) {
            try {
                builder.setJsonBody(new JSONObject(optString));
            } catch (JSONException e) {
                throw new MoPubNetworkError("Failed to decode body JSON for native ad format", (Throwable) e, MoPubNetworkError.Reason.BAD_BODY);
            }
        }
        builder.setCustomEventClassName(AdTypeTranslator.getCustomEventName(adFormat, extractHeader, extractHeader3, jSONObject2));
        MoPub.BrowserAgent fromHeader = MoPub.BrowserAgent.fromHeader(HeaderUtils.extractIntegerHeader(jSONObject2, ResponseHeader.BROWSER_AGENT));
        MoPub.setBrowserAgentFromAdServer(fromHeader);
        builder.setBrowserAgent(fromHeader);
        String extractHeader5 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.CUSTOM_EVENT_DATA);
        if (TextUtils.isEmpty(extractHeader5)) {
            extractHeader5 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.NATIVE_PARAMS);
        }
        try {
            Map<String, String> jsonStringToMap = Json.jsonStringToMap(extractHeader5);
            try {
                if (!jSONObject2.optString(DataKeys.ADM_KEY).isEmpty()) {
                    jsonStringToMap.put(DataKeys.ADM_KEY, jSONObject2.getString(DataKeys.ADM_KEY));
                }
                if (!TextUtils.isEmpty(extractHeader4)) {
                    jsonStringToMap.put(DataKeys.CLICKTHROUGH_URL_KEY, extractHeader4);
                }
                if (HeaderUtils.extractIntegerHeader(jSONObject2, ResponseHeader.VAST_CLICK_ENABLED, 0).intValue() != 1) {
                    z = false;
                }
                jsonStringToMap.put(DataKeys.VAST_CLICK_EXP_ENABLED_KEY, Boolean.toString(z));
                jsonStringToMap.put("adunit_format", str2);
                if (eventDataIsInResponseBody(extractHeader, extractHeader3)) {
                    jsonStringToMap.put(DataKeys.HTML_RESPONSE_BODY_KEY, optString);
                    jsonStringToMap.put(DataKeys.CREATIVE_ORIENTATION_KEY, HeaderUtils.extractHeader(jSONObject2, ResponseHeader.ORIENTATION));
                }
                builder.setAllowCustomClose(HeaderUtils.extractBooleanHeader(jSONObject2, ResponseHeader.ALLOW_CUSTOM_CLOSE, false));
                if (AdType.STATIC_NATIVE.equals(extractHeader) || AdType.VIDEO_NATIVE.equals(extractHeader)) {
                    String extractPercentHeaderString = HeaderUtils.extractPercentHeaderString(jSONObject2, ResponseHeader.IMPRESSION_MIN_VISIBLE_PERCENT);
                    String extractHeader6 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.IMPRESSION_VISIBLE_MS);
                    String extractHeader7 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.IMPRESSION_MIN_VISIBLE_PX);
                    if (!TextUtils.isEmpty(extractPercentHeaderString)) {
                        jsonStringToMap.put(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT, extractPercentHeaderString);
                    }
                    if (!TextUtils.isEmpty(extractHeader6)) {
                        jsonStringToMap.put(DataKeys.IMPRESSION_VISIBLE_MS, extractHeader6);
                    }
                    if (!TextUtils.isEmpty(extractHeader7)) {
                        jsonStringToMap.put(DataKeys.IMPRESSION_MIN_VISIBLE_PX, extractHeader7);
                    }
                }
                if (AdType.VIDEO_NATIVE.equals(extractHeader)) {
                    jsonStringToMap.put(DataKeys.PLAY_VISIBLE_PERCENT, HeaderUtils.extractPercentHeaderString(jSONObject2, ResponseHeader.PLAY_VISIBLE_PERCENT));
                    jsonStringToMap.put(DataKeys.PAUSE_VISIBLE_PERCENT, HeaderUtils.extractPercentHeaderString(jSONObject2, ResponseHeader.PAUSE_VISIBLE_PERCENT));
                    jsonStringToMap.put(DataKeys.MAX_BUFFER_MS, HeaderUtils.extractHeader(jSONObject2, ResponseHeader.MAX_BUFFER_MS));
                }
                String extractHeader8 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.VIDEO_TRACKERS);
                if (!TextUtils.isEmpty(extractHeader8)) {
                    jsonStringToMap.put(DataKeys.VIDEO_TRACKERS_KEY, extractHeader8);
                }
                if (AdType.REWARDED_VIDEO.equals(extractHeader) || (AdType.INTERSTITIAL.equals(extractHeader) && FullAdType.VAST.equals(extractHeader3))) {
                    jsonStringToMap.put(DataKeys.EXTERNAL_VIDEO_VIEWABILITY_TRACKERS_KEY, HeaderUtils.extractHeader(jSONObject2, ResponseHeader.VIDEO_VIEWABILITY_TRACKERS));
                }
                if (AdFormat.BANNER.equals(adFormat)) {
                    jsonStringToMap.put(DataKeys.BANNER_IMPRESSION_MIN_VISIBLE_MS, HeaderUtils.extractHeader(jSONObject2, ResponseHeader.BANNER_IMPRESSION_MIN_VISIBLE_MS));
                    jsonStringToMap.put(DataKeys.BANNER_IMPRESSION_MIN_VISIBLE_DIPS, HeaderUtils.extractHeader(jSONObject2, ResponseHeader.BANNER_IMPRESSION_MIN_VISIBLE_DIPS));
                }
                String extractHeader9 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.DISABLE_VIEWABILITY);
                if (!TextUtils.isEmpty(extractHeader9) && (fromKey = ExternalViewabilitySessionManager.ViewabilityVendor.fromKey(extractHeader9)) != null) {
                    fromKey.disable();
                }
                builder.setServerExtras(jsonStringToMap);
                if (AdType.REWARDED_VIDEO.equals(extractHeader) || AdType.CUSTOM.equals(extractHeader) || AdType.REWARDED_PLAYABLE.equals(extractHeader)) {
                    String extractHeader10 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.REWARDED_VIDEO_CURRENCY_NAME);
                    String extractHeader11 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.REWARDED_VIDEO_CURRENCY_AMOUNT);
                    String extractHeader12 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.REWARDED_CURRENCIES);
                    String extractHeader13 = HeaderUtils.extractHeader(jSONObject2, ResponseHeader.REWARDED_VIDEO_COMPLETION_URL);
                    Integer extractIntegerHeader = HeaderUtils.extractIntegerHeader(jSONObject2, ResponseHeader.REWARDED_DURATION);
                    boolean extractBooleanHeader = HeaderUtils.extractBooleanHeader(jSONObject2, ResponseHeader.SHOULD_REWARD_ON_CLICK, false);
                    builder.setRewardedVideoCurrencyName(extractHeader10);
                    builder.setRewardedVideoCurrencyAmount(extractHeader11);
                    builder.setRewardedCurrencies(extractHeader12);
                    builder.setRewardedVideoCompletionUrl(extractHeader13);
                    builder.setRewardedDuration(extractIntegerHeader);
                    builder.setShouldRewardOnClick(extractBooleanHeader);
                }
                return builder.build();
            } catch (JSONException e2) {
                throw new MoPubNetworkError("Failed to parse ADM for advanced bidding", (Throwable) e2, MoPubNetworkError.Reason.BAD_BODY);
            }
        } catch (JSONException e3) {
            throw new MoPubNetworkError("Failed to decode server extras for custom event data.", (Throwable) e3, MoPubNetworkError.Reason.BAD_HEADER_DATA);
        }
    }

    private static Integer extractRefreshTimeMS(JSONObject jSONObject) throws JSONException {
        Preconditions.checkNotNull(jSONObject);
        Integer extractIntegerHeader = HeaderUtils.extractIntegerHeader(jSONObject.getJSONObject(ResponseHeader.METADATA.getKey()), ResponseHeader.REFRESH_TIME);
        if (extractIntegerHeader == null) {
            return null;
        }
        return Integer.valueOf(extractIntegerHeader.intValue() * 1000);
    }

    private static boolean extractWarmup(JSONObject jSONObject) {
        Preconditions.checkNotNull(jSONObject);
        return HeaderUtils.extractBooleanHeader(jSONObject.optJSONObject(ResponseHeader.METADATA.getKey()), ResponseHeader.WARMUP, false);
    }

    private static String parseStringBody(NetworkResponse networkResponse) {
        Preconditions.checkNotNull(networkResponse);
        try {
            return new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
        } catch (UnsupportedEncodingException unused) {
            return new String(networkResponse.data);
        }
    }

    private static boolean eventDataIsInResponseBody(String str, String str2) {
        return AdType.MRAID.equals(str) || AdType.HTML.equals(str) || (AdType.INTERSTITIAL.equals(str) && FullAdType.VAST.equals(str2)) || ((AdType.REWARDED_VIDEO.equals(str) && FullAdType.VAST.equals(str2)) || AdType.REWARDED_PLAYABLE.equals(str));
    }
}
