package com.onesignal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import com.google.android.gms.plus.PlusShare;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.mopub.common.AdType;
import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationPayload;
import com.onesignal.OneSignal;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class NotificationBundleProcessor {
    static void ProcessFromGCMIntentService(Context context, BundleCompat bundleCompat, NotificationExtenderService.OverrideSettings overrideSettings) {
        OneSignal.setAppContext(context);
        try {
            String string = bundleCompat.getString("json_payload");
            if (string == null) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                OneSignal.Log(log_level, "json_payload key is nonexistent from mBundle passed to ProcessFromGCMIntentService: " + bundleCompat);
                return;
            }
            NotificationGenerationJob notificationGenerationJob = new NotificationGenerationJob(context);
            boolean z = false;
            notificationGenerationJob.restoring = bundleCompat.getBoolean("restoring", false);
            notificationGenerationJob.shownTimeStamp = bundleCompat.getLong(AvidJSONUtil.KEY_TIMESTAMP);
            notificationGenerationJob.jsonPayload = new JSONObject(string);
            if (inAppPreviewPushUUID(notificationGenerationJob.jsonPayload) != null) {
                z = true;
            }
            notificationGenerationJob.isInAppPreviewPush = z;
            if (notificationGenerationJob.restoring || notificationGenerationJob.isInAppPreviewPush || !OneSignal.notValidOrDuplicated(context, notificationGenerationJob.jsonPayload)) {
                if (bundleCompat.containsKey("android_notif_id")) {
                    if (overrideSettings == null) {
                        overrideSettings = new NotificationExtenderService.OverrideSettings();
                    }
                    overrideSettings.androidNotificationId = bundleCompat.getInt("android_notif_id");
                }
                notificationGenerationJob.overrideSettings = overrideSettings;
                ProcessJobForDisplay(notificationGenerationJob);
                if (notificationGenerationJob.restoring) {
                    OSUtils.sleep(100);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static int ProcessJobForDisplay(NotificationGenerationJob notificationGenerationJob) {
        notificationGenerationJob.showAsAlert = OneSignal.getInAppAlertNotificationEnabled() && OneSignal.isAppActive();
        processCollapseKey(notificationGenerationJob);
        if (shouldDisplayNotif(notificationGenerationJob)) {
            GenerateNotification.fromJsonPayload(notificationGenerationJob);
        }
        if (!notificationGenerationJob.restoring && !notificationGenerationJob.isInAppPreviewPush) {
            processNotification(notificationGenerationJob, false);
            try {
                JSONObject jSONObject = new JSONObject(notificationGenerationJob.jsonPayload.toString());
                jSONObject.put("androidNotificationId", notificationGenerationJob.getAndroidId());
                OneSignal.handleNotificationReceived(newJsonArray(jSONObject), true, notificationGenerationJob.showAsAlert);
            } catch (Throwable unused) {
            }
        }
        return notificationGenerationJob.getAndroidId().intValue();
    }

    private static boolean shouldDisplayNotif(NotificationGenerationJob notificationGenerationJob) {
        if (notificationGenerationJob.isInAppPreviewPush && Build.VERSION.SDK_INT <= 18) {
            return false;
        }
        if (notificationGenerationJob.hasExtender() || shouldDisplay(notificationGenerationJob.jsonPayload.optString("alert"))) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static JSONArray bundleAsJsonArray(Bundle bundle) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(bundleAsJSONObject(bundle));
        return jSONArray;
    }

    private static void saveAndProcessNotification(Context context, Bundle bundle, boolean z, int i) {
        NotificationGenerationJob notificationGenerationJob = new NotificationGenerationJob(context);
        notificationGenerationJob.jsonPayload = bundleAsJSONObject(bundle);
        notificationGenerationJob.overrideSettings = new NotificationExtenderService.OverrideSettings();
        notificationGenerationJob.overrideSettings.androidNotificationId = Integer.valueOf(i);
        processNotification(notificationGenerationJob, z);
    }

    static void processNotification(NotificationGenerationJob notificationGenerationJob, boolean z) {
        saveNotification(notificationGenerationJob, z);
        if (notificationGenerationJob.isNotificationToDisplay()) {
            String apiNotificationId = notificationGenerationJob.getApiNotificationId();
            OneSignal.getSessionManager().onNotificationReceived(apiNotificationId);
            OSReceiveReceiptController.getInstance().sendReceiveReceipt(apiNotificationId);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0120 A[SYNTHETIC, Splitter:B:53:0x0120] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x012a A[SYNTHETIC, Splitter:B:59:0x012a] */
    /* JADX WARNING: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void saveNotification(com.onesignal.NotificationGenerationJob r14, boolean r15) {
        /*
            java.lang.String r0 = "grp"
            java.lang.String r1 = "collapse_key"
            java.lang.String r2 = "Error closing transaction! "
            android.content.Context r3 = r14.context
            org.json.JSONObject r4 = r14.jsonPayload
            org.json.JSONObject r5 = r14.jsonPayload     // Catch:{ JSONException -> 0x0135 }
            org.json.JSONObject r5 = getCustomJSONObject(r5)     // Catch:{ JSONException -> 0x0135 }
            android.content.Context r6 = r14.context     // Catch:{ JSONException -> 0x0135 }
            com.onesignal.OneSignalDbHelper r6 = com.onesignal.OneSignalDbHelper.getInstance(r6)     // Catch:{ JSONException -> 0x0135 }
            r7 = 0
            android.database.sqlite.SQLiteDatabase r6 = r6.getSQLiteDatabaseWithRetries()     // Catch:{ Exception -> 0x0116 }
            r6.beginTransaction()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            boolean r8 = r14.isNotificationToDisplay()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            java.lang.String r9 = "notification"
            r10 = 1
            if (r8 == 0) goto L_0x0050
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r8.<init>()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            java.lang.String r11 = "android_notification_id = "
            r8.append(r11)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            int r11 = r14.getAndroidIdWithoutCreate()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r8.append(r11)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            android.content.ContentValues r11 = new android.content.ContentValues     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r11.<init>()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            java.lang.String r12 = "dismissed"
            java.lang.Integer r13 = java.lang.Integer.valueOf(r10)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r11.put(r12, r13)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r6.update(r9, r11, r8, r7)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            com.onesignal.BadgeCountUpdater.update(r6, r3)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
        L_0x0050:
            android.content.ContentValues r8 = new android.content.ContentValues     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r8.<init>()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            java.lang.String r11 = "notification_id"
            java.lang.String r12 = "i"
            java.lang.String r5 = r5.optString(r12)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r8.put(r11, r5)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            boolean r5 = r4.has(r0)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            if (r5 == 0) goto L_0x006f
            java.lang.String r5 = "group_id"
            java.lang.String r0 = r4.optString(r0)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r8.put(r5, r0)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
        L_0x006f:
            boolean r0 = r4.has(r1)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            if (r0 == 0) goto L_0x008a
            java.lang.String r0 = "do_not_collapse"
            java.lang.String r5 = r4.optString(r1)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            boolean r0 = r0.equals(r5)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            if (r0 != 0) goto L_0x008a
            java.lang.String r0 = "collapse_id"
            java.lang.String r1 = r4.optString(r1)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r8.put(r0, r1)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
        L_0x008a:
            java.lang.String r0 = "opened"
            if (r15 == 0) goto L_0x008f
            goto L_0x0090
        L_0x008f:
            r10 = 0
        L_0x0090:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r10)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r8.put(r0, r1)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            if (r15 != 0) goto L_0x00a6
            java.lang.String r0 = "android_notification_id"
            int r1 = r14.getAndroidIdWithoutCreate()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r8.put(r0, r1)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
        L_0x00a6:
            java.lang.CharSequence r0 = r14.getTitle()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            if (r0 == 0) goto L_0x00b9
            java.lang.String r0 = "title"
            java.lang.CharSequence r1 = r14.getTitle()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r8.put(r0, r1)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
        L_0x00b9:
            java.lang.CharSequence r0 = r14.getBody()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            if (r0 == 0) goto L_0x00cc
            java.lang.String r0 = "message"
            java.lang.CharSequence r14 = r14.getBody()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r8.put(r0, r14)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
        L_0x00cc:
            java.lang.String r14 = "google.sent_time"
            long r0 = android.os.SystemClock.currentThreadTimeMillis()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            long r0 = r4.optLong(r14, r0)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r10 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 / r10
            java.lang.String r14 = "google.ttl"
            r5 = 259200(0x3f480, float:3.63217E-40)
            int r14 = r4.optInt(r14, r5)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            long r10 = (long) r14     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            long r0 = r0 + r10
            java.lang.String r14 = "expire_time"
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r8.put(r14, r0)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            java.lang.String r14 = "full_data"
            java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r8.put(r14, r0)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            r6.insertOrThrow(r9, r7, r8)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            if (r15 != 0) goto L_0x00fe
            com.onesignal.BadgeCountUpdater.update(r6, r3)     // Catch:{ Exception -> 0x0111, all -> 0x010e }
        L_0x00fe:
            r6.setTransactionSuccessful()     // Catch:{ Exception -> 0x0111, all -> 0x010e }
            if (r6 == 0) goto L_0x0139
            r6.endTransaction()     // Catch:{ all -> 0x0107 }
            goto L_0x0139
        L_0x0107:
            r14 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r15 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ JSONException -> 0x0135 }
        L_0x010a:
            com.onesignal.OneSignal.Log(r15, r2, r14)     // Catch:{ JSONException -> 0x0135 }
            goto L_0x0139
        L_0x010e:
            r14 = move-exception
            r7 = r6
            goto L_0x0128
        L_0x0111:
            r14 = move-exception
            r7 = r6
            goto L_0x0117
        L_0x0114:
            r14 = move-exception
            goto L_0x0128
        L_0x0116:
            r14 = move-exception
        L_0x0117:
            com.onesignal.OneSignal$LOG_LEVEL r15 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x0114 }
            java.lang.String r0 = "Error saving notification record! "
            com.onesignal.OneSignal.Log(r15, r0, r14)     // Catch:{ all -> 0x0114 }
            if (r7 == 0) goto L_0x0139
            r7.endTransaction()     // Catch:{ all -> 0x0124 }
            goto L_0x0139
        L_0x0124:
            r14 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r15 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ JSONException -> 0x0135 }
            goto L_0x010a
        L_0x0128:
            if (r7 == 0) goto L_0x0134
            r7.endTransaction()     // Catch:{ all -> 0x012e }
            goto L_0x0134
        L_0x012e:
            r15 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r0 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ JSONException -> 0x0135 }
            com.onesignal.OneSignal.Log(r0, r2, r15)     // Catch:{ JSONException -> 0x0135 }
        L_0x0134:
            throw r14     // Catch:{ JSONException -> 0x0135 }
        L_0x0135:
            r14 = move-exception
            r14.printStackTrace()
        L_0x0139:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.NotificationBundleProcessor.saveNotification(com.onesignal.NotificationGenerationJob, boolean):void");
    }

    static JSONObject bundleAsJSONObject(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            try {
                jSONObject.put(str, bundle.get(str));
            } catch (JSONException e) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                OneSignal.Log(log_level, "bundleAsJSONObject error for key: " + str, e);
            }
        }
        return jSONObject;
    }

    private static void unMinifyButtonsFromBundle(Bundle bundle) {
        JSONObject jSONObject;
        String str;
        if (bundle.containsKey("o")) {
            try {
                JSONObject jSONObject2 = new JSONObject(bundle.getString(AdType.CUSTOM));
                if (jSONObject2.has("a")) {
                    jSONObject = jSONObject2.getJSONObject("a");
                } else {
                    jSONObject = new JSONObject();
                }
                JSONArray jSONArray = new JSONArray(bundle.getString("o"));
                bundle.remove("o");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                    String string = jSONObject3.getString("n");
                    jSONObject3.remove("n");
                    if (jSONObject3.has("i")) {
                        str = jSONObject3.getString("i");
                        jSONObject3.remove("i");
                    } else {
                        str = string;
                    }
                    jSONObject3.put("id", str);
                    jSONObject3.put("text", string);
                    if (jSONObject3.has("p")) {
                        jSONObject3.put("icon", jSONObject3.getString("p"));
                        jSONObject3.remove("p");
                    }
                }
                jSONObject.put("actionButtons", jSONArray);
                jSONObject.put("actionId", "__DEFAULT__");
                if (!jSONObject2.has("a")) {
                    jSONObject2.put("a", jSONObject);
                }
                bundle.putString(AdType.CUSTOM, jSONObject2.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    static OSNotificationPayload OSNotificationPayloadFrom(JSONObject jSONObject) {
        OSNotificationPayload oSNotificationPayload = new OSNotificationPayload();
        try {
            JSONObject customJSONObject = getCustomJSONObject(jSONObject);
            oSNotificationPayload.notificationID = customJSONObject.optString("i");
            oSNotificationPayload.templateId = customJSONObject.optString("ti");
            oSNotificationPayload.templateName = customJSONObject.optString("tn");
            oSNotificationPayload.rawPayload = jSONObject.toString();
            oSNotificationPayload.additionalData = customJSONObject.optJSONObject("a");
            oSNotificationPayload.launchURL = customJSONObject.optString("u", (String) null);
            oSNotificationPayload.body = jSONObject.optString("alert", (String) null);
            oSNotificationPayload.title = jSONObject.optString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, (String) null);
            oSNotificationPayload.smallIcon = jSONObject.optString("sicon", (String) null);
            oSNotificationPayload.bigPicture = jSONObject.optString("bicon", (String) null);
            oSNotificationPayload.largeIcon = jSONObject.optString("licon", (String) null);
            oSNotificationPayload.sound = jSONObject.optString("sound", (String) null);
            oSNotificationPayload.groupKey = jSONObject.optString("grp", (String) null);
            oSNotificationPayload.groupMessage = jSONObject.optString("grp_msg", (String) null);
            oSNotificationPayload.smallIconAccentColor = jSONObject.optString("bgac", (String) null);
            oSNotificationPayload.ledColor = jSONObject.optString("ledc", (String) null);
            String optString = jSONObject.optString("vis", (String) null);
            if (optString != null) {
                oSNotificationPayload.lockScreenVisibility = Integer.parseInt(optString);
            }
            oSNotificationPayload.fromProjectNumber = jSONObject.optString("from", (String) null);
            oSNotificationPayload.priority = jSONObject.optInt("pri", 0);
            String optString2 = jSONObject.optString("collapse_key", (String) null);
            if (!"do_not_collapse".equals(optString2)) {
                oSNotificationPayload.collapseId = optString2;
            }
            setActionButtons(oSNotificationPayload);
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error assigning OSNotificationPayload values!", th);
        }
        try {
            setBackgroundImageLayout(oSNotificationPayload, jSONObject);
        } catch (Throwable th2) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error assigning OSNotificationPayload.backgroundImageLayout values!", th2);
        }
        return oSNotificationPayload;
    }

    private static void setActionButtons(OSNotificationPayload oSNotificationPayload) throws Throwable {
        if (oSNotificationPayload.additionalData != null && oSNotificationPayload.additionalData.has("actionButtons")) {
            JSONArray jSONArray = oSNotificationPayload.additionalData.getJSONArray("actionButtons");
            oSNotificationPayload.actionButtons = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                OSNotificationPayload.ActionButton actionButton = new OSNotificationPayload.ActionButton();
                actionButton.id = jSONObject.optString("id", (String) null);
                actionButton.text = jSONObject.optString("text", (String) null);
                actionButton.icon = jSONObject.optString("icon", (String) null);
                oSNotificationPayload.actionButtons.add(actionButton);
            }
            oSNotificationPayload.additionalData.remove("actionId");
            oSNotificationPayload.additionalData.remove("actionButtons");
        }
    }

    private static void setBackgroundImageLayout(OSNotificationPayload oSNotificationPayload, JSONObject jSONObject) throws Throwable {
        String optString = jSONObject.optString("bg_img", (String) null);
        if (optString != null) {
            JSONObject jSONObject2 = new JSONObject(optString);
            oSNotificationPayload.backgroundImageLayout = new OSNotificationPayload.BackgroundImageLayout();
            oSNotificationPayload.backgroundImageLayout.image = jSONObject2.optString("img");
            oSNotificationPayload.backgroundImageLayout.titleTextColor = jSONObject2.optString("tc");
            oSNotificationPayload.backgroundImageLayout.bodyTextColor = jSONObject2.optString("bc");
        }
    }

    private static void processCollapseKey(NotificationGenerationJob notificationGenerationJob) {
        if (!notificationGenerationJob.restoring && notificationGenerationJob.jsonPayload.has("collapse_key") && !"do_not_collapse".equals(notificationGenerationJob.jsonPayload.optString("collapse_key"))) {
            Cursor cursor = null;
            try {
                cursor = OneSignalDbHelper.getInstance(notificationGenerationJob.context).getSQLiteDatabaseWithRetries().query("notification", new String[]{"android_notification_id"}, "collapse_id = ? AND dismissed = 0 AND opened = 0 ", new String[]{notificationGenerationJob.jsonPayload.optString("collapse_key")}, (String) null, (String) null, (String) null);
                if (cursor.moveToFirst()) {
                    notificationGenerationJob.setAndroidIdWithOutOverriding(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("android_notification_id"))));
                }
                if (cursor == null || cursor.isClosed()) {
                    return;
                }
            } catch (Throwable th) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                throw th;
            }
            cursor.close();
        }
    }

    static ProcessedBundleResult processBundleFromReceiver(Context context, final Bundle bundle) {
        ProcessedBundleResult processedBundleResult = new ProcessedBundleResult();
        if (!OSNotificationFormatHelper.isOneSignalBundle(bundle)) {
            return processedBundleResult;
        }
        processedBundleResult.isOneSignalPayload = true;
        unMinifyButtonsFromBundle(bundle);
        JSONObject bundleAsJSONObject = bundleAsJSONObject(bundle);
        String inAppPreviewPushUUID = inAppPreviewPushUUID(bundleAsJSONObject);
        if (inAppPreviewPushUUID != null) {
            if (OneSignal.isAppActive()) {
                processedBundleResult.inAppPreviewShown = true;
                OSInAppMessageController.getController().displayPreviewMessage(inAppPreviewPushUUID);
            }
            return processedBundleResult;
        } else if (startExtenderService(context, bundle, processedBundleResult)) {
            return processedBundleResult;
        } else {
            processedBundleResult.isDup = OneSignal.notValidOrDuplicated(context, bundleAsJSONObject);
            if (!processedBundleResult.isDup && !shouldDisplay(bundle.getString("alert"))) {
                saveAndProcessNotification(context, bundle, true, -1);
                new Thread(new Runnable() {
                    public void run() {
                        OneSignal.handleNotificationReceived(NotificationBundleProcessor.bundleAsJsonArray(bundle), false, false);
                    }
                }, "OS_PROC_BUNDLE").start();
            }
            return processedBundleResult;
        }
    }

    static String inAppPreviewPushUUID(JSONObject jSONObject) {
        try {
            JSONObject customJSONObject = getCustomJSONObject(jSONObject);
            if (!customJSONObject.has("a")) {
                return null;
            }
            JSONObject optJSONObject = customJSONObject.optJSONObject("a");
            if (optJSONObject.has("os_in_app_message_preview_id")) {
                return optJSONObject.optString("os_in_app_message_preview_id");
            }
            return null;
        } catch (JSONException unused) {
        }
    }

    private static boolean startExtenderService(Context context, Bundle bundle, ProcessedBundleResult processedBundleResult) {
        Intent intent = NotificationExtenderService.getIntent(context);
        boolean z = false;
        if (intent == null) {
            return false;
        }
        intent.putExtra("json_payload", bundleAsJSONObject(bundle).toString());
        intent.putExtra(AvidJSONUtil.KEY_TIMESTAMP, System.currentTimeMillis() / 1000);
        if (Integer.parseInt(bundle.getString("pri", "0")) > 9) {
            z = true;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            NotificationExtenderService.enqueueWork(context, intent.getComponent(), 2071862121, intent, z);
        } else {
            context.startService(intent);
        }
        processedBundleResult.hasExtenderService = true;
        return true;
    }

    static boolean shouldDisplay(String str) {
        boolean z = str != null && !"".equals(str);
        boolean inAppAlertNotificationEnabled = OneSignal.getInAppAlertNotificationEnabled();
        boolean isAppActive = OneSignal.isAppActive();
        if (!z || (!OneSignal.getNotificationsWhenActiveEnabled() && !inAppAlertNotificationEnabled && isAppActive)) {
            return false;
        }
        return true;
    }

    static JSONArray newJsonArray(JSONObject jSONObject) {
        return new JSONArray().put(jSONObject);
    }

    static JSONObject getCustomJSONObject(JSONObject jSONObject) throws JSONException {
        return new JSONObject(jSONObject.optString(AdType.CUSTOM));
    }

    static boolean hasRemoteResource(Bundle bundle) {
        return isBuildKeyRemote(bundle, "licon") || isBuildKeyRemote(bundle, "bicon") || bundle.getString("bg_img", (String) null) != null;
    }

    private static boolean isBuildKeyRemote(Bundle bundle, String str) {
        String trim = bundle.getString(str, "").trim();
        return trim.startsWith("http://") || trim.startsWith("https://");
    }

    static class ProcessedBundleResult {
        boolean hasExtenderService;
        boolean inAppPreviewShown;
        boolean isDup;
        boolean isOneSignalPayload;

        ProcessedBundleResult() {
        }

        /* access modifiers changed from: package-private */
        public boolean processed() {
            return !this.isOneSignalPayload || this.hasExtenderService || this.isDup || this.inAppPreviewShown;
        }
    }
}
