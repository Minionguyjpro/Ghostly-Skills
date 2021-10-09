package com.onesignal;

import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OneSignalRemoteParams {
    /* access modifiers changed from: private */
    public static int androidParamsRetries;

    interface CallBack {
        void complete(Params params);
    }

    static /* synthetic */ int access$008() {
        int i = androidParamsRetries;
        androidParamsRetries = i + 1;
        return i;
    }

    static class FCMParams {
        String apiKey;
        String appId;
        String projectId;

        FCMParams() {
        }
    }

    public static class InfluenceParams {
        boolean directEnabled = false;
        int iamLimit = 10;
        boolean indirectEnabled = false;
        int indirectIAMAttributionWindow = 1440;
        int indirectNotificationAttributionWindow = 1440;
        int notificationLimit = 10;
        boolean outcomesV2ServiceEnabled = false;
        boolean unattributedEnabled = false;

        public int getIndirectNotificationAttributionWindow() {
            return this.indirectNotificationAttributionWindow;
        }

        public int getNotificationLimit() {
            return this.notificationLimit;
        }

        public int getIndirectIAMAttributionWindow() {
            return this.indirectIAMAttributionWindow;
        }

        public int getIamLimit() {
            return this.iamLimit;
        }

        public boolean isDirectEnabled() {
            return this.directEnabled;
        }

        public boolean isIndirectEnabled() {
            return this.indirectEnabled;
        }

        public boolean isUnattributedEnabled() {
            return this.unattributedEnabled;
        }

        public String toString() {
            return "InfluenceParams{indirectNotificationAttributionWindow=" + this.indirectNotificationAttributionWindow + ", notificationLimit=" + this.notificationLimit + ", indirectIAMAttributionWindow=" + this.indirectIAMAttributionWindow + ", iamLimit=" + this.iamLimit + ", directEnabled=" + this.directEnabled + ", indirectEnabled=" + this.indirectEnabled + ", unattributedEnabled=" + this.unattributedEnabled + '}';
        }
    }

    static class Params {
        boolean clearGroupOnSummaryClick;
        boolean enterprise;
        FCMParams fcmParams;
        boolean firebaseAnalytics;
        String googleProjectNumber;
        InfluenceParams influenceParams;
        JSONArray notificationChannels;
        boolean receiveReceiptEnabled;
        boolean restoreTTLFilter;
        boolean useEmailAuth;

        Params() {
        }
    }

    static void makeAndroidParamsRequest(final CallBack callBack) {
        AnonymousClass1 r0 = new OneSignalRestClient.ResponseHandler() {
            /* access modifiers changed from: package-private */
            public void onFailure(int i, String str, Throwable th) {
                if (i == 403) {
                    OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "403 error getting OneSignal params, omitting further retries!");
                } else {
                    new Thread(new Runnable() {
                        public void run() {
                            int access$000 = (OneSignalRemoteParams.androidParamsRetries * 10000) + 30000;
                            if (access$000 > 90000) {
                                access$000 = 90000;
                            }
                            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.INFO;
                            OneSignal.Log(log_level, "Failed to get Android parameters, trying again in " + (access$000 / 1000) + " seconds.");
                            OSUtils.sleep(access$000);
                            OneSignalRemoteParams.access$008();
                            OneSignalRemoteParams.makeAndroidParamsRequest(callBack);
                        }
                    }, "OS_PARAMS_REQUEST").start();
                }
            }

            /* access modifiers changed from: package-private */
            public void onSuccess(String str) {
                OneSignalRemoteParams.processJson(str, callBack);
            }
        };
        String str = "apps/" + OneSignal.appId + "/android_params.js";
        String userId = OneSignal.getUserId();
        if (userId != null) {
            str = str + "?player_id=" + userId;
        }
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "Starting request to get Android parameters.");
        OneSignalRestClient.get(str, r0, "CACHE_KEY_REMOTE_PARAMS");
    }

    /* access modifiers changed from: private */
    public static void processJson(String str, CallBack callBack) {
        try {
            final JSONObject jSONObject = new JSONObject(str);
            callBack.complete(new Params() {
                {
                    this.enterprise = jSONObject.optBoolean("enterp", false);
                    this.useEmailAuth = jSONObject.optBoolean("use_email_auth", false);
                    this.notificationChannels = jSONObject.optJSONArray("chnl_lst");
                    this.firebaseAnalytics = jSONObject.optBoolean("fba", false);
                    this.restoreTTLFilter = jSONObject.optBoolean("restore_ttl_filter", true);
                    this.googleProjectNumber = jSONObject.optString("android_sender_id", (String) null);
                    this.clearGroupOnSummaryClick = jSONObject.optBoolean("clear_group_on_summary_click", true);
                    this.receiveReceiptEnabled = jSONObject.optBoolean("receive_receipts_enable", false);
                    this.influenceParams = new InfluenceParams();
                    if (jSONObject.has("outcomes")) {
                        OneSignalRemoteParams.processOutcomeJson(jSONObject.optJSONObject("outcomes"), this.influenceParams);
                    }
                    this.fcmParams = new FCMParams();
                    if (jSONObject.has("fcm")) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("fcm");
                        this.fcmParams.apiKey = optJSONObject.optString("api_key", (String) null);
                        this.fcmParams.appId = optJSONObject.optString("app_id", (String) null);
                        this.fcmParams.projectId = optJSONObject.optString("project_id", (String) null);
                    }
                }
            });
        } catch (NullPointerException | JSONException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "Error parsing android_params!: ", e);
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.FATAL;
            OneSignal.Log(log_level, "Response that errored from android_params!: " + str);
        }
    }

    /* access modifiers changed from: private */
    public static void processOutcomeJson(JSONObject jSONObject, InfluenceParams influenceParams) {
        if (jSONObject.has("v2_enabled")) {
            influenceParams.outcomesV2ServiceEnabled = jSONObject.optBoolean("v2_enabled");
        }
        if (jSONObject.has("direct")) {
            influenceParams.directEnabled = jSONObject.optJSONObject("direct").optBoolean("enabled");
        }
        if (jSONObject.has("indirect")) {
            JSONObject optJSONObject = jSONObject.optJSONObject("indirect");
            influenceParams.indirectEnabled = optJSONObject.optBoolean("enabled");
            if (optJSONObject.has("notification_attribution")) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("notification_attribution");
                influenceParams.indirectNotificationAttributionWindow = optJSONObject2.optInt("minutes_since_displayed", 1440);
                influenceParams.notificationLimit = optJSONObject2.optInt("limit", 10);
            }
            if (optJSONObject.has("in_app_message_attribution")) {
                JSONObject optJSONObject3 = optJSONObject.optJSONObject("in_app_message_attribution");
                influenceParams.indirectIAMAttributionWindow = optJSONObject3.optInt("minutes_since_displayed", 1440);
                influenceParams.iamLimit = optJSONObject3.optInt("limit", 10);
            }
        }
        if (jSONObject.has("unattributed")) {
            influenceParams.unattributedEnabled = jSONObject.optJSONObject("unattributed").optBoolean("enabled");
        }
    }
}
