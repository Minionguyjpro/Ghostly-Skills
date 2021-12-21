package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.appnext.base.b.c;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirelogAnalyticsEvent;

/* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
public class MessagingAnalytics {
    private static final DataEncoder dataEncoder = new JsonDataEncoderBuilder().registerEncoder(FirelogAnalyticsEvent.FirelogAnalyticsEventWrapper.class, new FirelogAnalyticsEvent.FirelogAnalyticsEventWrapperEncoder()).registerEncoder(FirelogAnalyticsEvent.class, new FirelogAnalyticsEvent.FirelogAnalyticsEventEncoder()).build();

    public static void logNotificationReceived(Intent intent, Transport<String> transport) {
        logToScion("_nr", intent);
        if (transport != null) {
            logToFirelog("MESSAGE_DELIVERED", intent, transport);
        }
    }

    public static void logNotificationOpen(Intent intent) {
        setUserPropertyIfRequired(intent);
        logToScion("_no", intent);
    }

    public static void logNotificationDismiss(Intent intent) {
        logToScion("_nd", intent);
    }

    public static void logNotificationForeground(Intent intent) {
        logToScion("_nf", intent);
    }

    public static boolean shouldUploadScionMetrics(Intent intent) {
        if (intent == null || isDirectBootMessage(intent)) {
            return false;
        }
        return "1".equals(intent.getStringExtra("google.c.a.e"));
    }

    public static boolean shouldUploadFirelogAnalytics(Intent intent) {
        if (intent == null || isDirectBootMessage(intent)) {
            return false;
        }
        return deliveryMetricsExportToBigQueryEnabled();
    }

    private static boolean isDirectBootMessage(Intent intent) {
        return "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT".equals(intent.getAction());
    }

    static boolean deliveryMetricsExportToBigQueryEnabled() {
        ApplicationInfo applicationInfo;
        try {
            FirebaseApp.getInstance();
            Context applicationContext = FirebaseApp.getInstance().getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("export_to_big_query")) {
                return sharedPreferences.getBoolean("export_to_big_query", false);
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (!(packageManager == null || (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("delivery_metrics_exported_to_big_query_enabled"))) {
                    return applicationInfo.metaData.getBoolean("delivery_metrics_exported_to_big_query_enabled", false);
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            return false;
        } catch (IllegalStateException unused2) {
            Log.i("FirebaseMessaging", "FirebaseApp has not being initialized. Device might be in direct boot mode. Skip exporting delivery metrics to Big Query");
            return false;
        }
    }

    private static void setUserPropertyIfRequired(Intent intent) {
        if (intent != null) {
            if ("1".equals(intent.getStringExtra("google.c.a.tc"))) {
                AnalyticsConnector analyticsConnector = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
                if (Log.isLoggable("FirebaseMessaging", 3)) {
                    Log.d("FirebaseMessaging", "Received event with track-conversion=true. Setting user property and reengagement event");
                }
                if (analyticsConnector != null) {
                    String stringExtra = intent.getStringExtra("google.c.a.c_id");
                    analyticsConnector.setUserProperty("fcm", "_ln", stringExtra);
                    Bundle bundle = new Bundle();
                    bundle.putString("source", "Firebase");
                    bundle.putString("medium", "notification");
                    bundle.putString("campaign", stringExtra);
                    analyticsConnector.logEvent("fcm", "_cmp", bundle);
                    return;
                }
                Log.w("FirebaseMessaging", "Unable to set user property for conversion tracking:  analytics library is missing");
            } else if (Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Received event with track-conversion=false. Do not set user property");
            }
        }
    }

    static void logToScion(String str, Intent intent) {
        Bundle bundle = new Bundle();
        String composerId = getComposerId(intent);
        if (composerId != null) {
            bundle.putString("_nmid", composerId);
        }
        String composerLabel = getComposerLabel(intent);
        if (composerLabel != null) {
            bundle.putString("_nmn", composerLabel);
        }
        String messageLabel = getMessageLabel(intent);
        if (!TextUtils.isEmpty(messageLabel)) {
            bundle.putString(PlusShare.KEY_CALL_TO_ACTION_LABEL, messageLabel);
        }
        String messageChannel = getMessageChannel(intent);
        if (!TextUtils.isEmpty(messageChannel)) {
            bundle.putString("message_channel", messageChannel);
        }
        String topic = getTopic(intent);
        if (topic != null) {
            bundle.putString("_nt", topic);
        }
        String messageTime = getMessageTime(intent);
        if (messageTime != null) {
            try {
                bundle.putInt("_nmt", Integer.parseInt(messageTime));
            } catch (NumberFormatException e) {
                Log.w("FirebaseMessaging", "Error while parsing timestamp in GCM event", e);
            }
        }
        String useDeviceTime = getUseDeviceTime(intent);
        if (useDeviceTime != null) {
            try {
                bundle.putInt("_ndt", Integer.parseInt(useDeviceTime));
            } catch (NumberFormatException e2) {
                Log.w("FirebaseMessaging", "Error while parsing use_device_time in GCM event", e2);
            }
        }
        String messageTypeForScion = getMessageTypeForScion(intent);
        if ("_nr".equals(str) || "_nf".equals(str)) {
            bundle.putString("_nmc", messageTypeForScion);
        }
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            String valueOf = String.valueOf(bundle);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 37 + String.valueOf(valueOf).length());
            sb.append("Logging to scion event=");
            sb.append(str);
            sb.append(" scionPayload=");
            sb.append(valueOf);
            Log.d("FirebaseMessaging", sb.toString());
        }
        AnalyticsConnector analyticsConnector = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
        if (analyticsConnector != null) {
            analyticsConnector.logEvent("fcm", str, bundle);
        } else {
            Log.w("FirebaseMessaging", "Unable to log event: analytics library is missing");
        }
    }

    private static void logToFirelog(String str, Intent intent, Transport<String> transport) {
        try {
            transport.send(Event.ofTelemetry(dataEncoder.encode(new FirelogAnalyticsEvent.FirelogAnalyticsEventWrapper(new FirelogAnalyticsEvent(str, intent)))));
        } catch (EncodingException unused) {
            Log.d("FirebaseMessaging", "Failed to encode big query analytics payload. Skip sending");
        }
    }

    static int getTtl(Intent intent) {
        Object obj = intent.getExtras().get("google.ttl");
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (!(obj instanceof String)) {
            return 0;
        }
        try {
            return Integer.parseInt((String) obj);
        } catch (NumberFormatException unused) {
            String valueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
            sb.append("Invalid TTL: ");
            sb.append(valueOf);
            Log.w("FirebaseMessaging", sb.toString());
            return 0;
        }
    }

    static String getCollapseKey(Intent intent) {
        return intent.getStringExtra("collapse_key");
    }

    static String getComposerId(Intent intent) {
        return intent.getStringExtra("google.c.a.c_id");
    }

    static String getComposerLabel(Intent intent) {
        return intent.getStringExtra("google.c.a.c_l");
    }

    static String getMessageLabel(Intent intent) {
        return intent.getStringExtra("google.c.a.m_l");
    }

    static String getMessageChannel(Intent intent) {
        return intent.getStringExtra("google.c.a.m_c");
    }

    static String getMessageTime(Intent intent) {
        return intent.getStringExtra("google.c.a.ts");
    }

    static String getMessageId(Intent intent) {
        String stringExtra = intent.getStringExtra("google.message_id");
        return stringExtra == null ? intent.getStringExtra("message_id") : stringExtra;
    }

    static String getPackageName() {
        return FirebaseApp.getInstance().getApplicationContext().getPackageName();
    }

    static String getInstanceId() {
        return FirebaseInstanceId.getInstance(FirebaseApp.getInstance()).getId();
    }

    static String getMessageTypeForScion(Intent intent) {
        return (intent.getExtras() == null || !NotificationParams.isNotification(intent.getExtras())) ? c.DATA : "display";
    }

    static String getMessageTypeForFirelog(Intent intent) {
        return (intent.getExtras() == null || !NotificationParams.isNotification(intent.getExtras())) ? "DATA_MESSAGE" : "DISPLAY_NOTIFICATION";
    }

    static String getTopic(Intent intent) {
        String stringExtra = intent.getStringExtra("from");
        if (stringExtra == null || !stringExtra.startsWith("/topics/")) {
            return null;
        }
        return stringExtra;
    }

    static String getUseDeviceTime(Intent intent) {
        if (intent.hasExtra("google.c.a.udt")) {
            return intent.getStringExtra("google.c.a.udt");
        }
        return null;
    }

    static int getPriority(Intent intent) {
        String stringExtra = intent.getStringExtra("google.delivered_priority");
        if (stringExtra == null) {
            if ("1".equals(intent.getStringExtra("google.priority_reduced"))) {
                return 2;
            }
            stringExtra = intent.getStringExtra("google.priority");
        }
        return getMessagePriority(stringExtra);
    }

    private static int getMessagePriority(String str) {
        if ("high".equals(str)) {
            return 1;
        }
        return "normal".equals(str) ? 2 : 0;
    }

    static String getProjectNumber() {
        FirebaseApp instance = FirebaseApp.getInstance();
        String gcmSenderId = instance.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = instance.getOptions().getApplicationId();
        if (!applicationId.startsWith("1:")) {
            return applicationId;
        }
        String[] split = applicationId.split(":");
        if (split.length < 2) {
            return null;
        }
        String str = split[1];
        if (str.isEmpty()) {
            return null;
        }
        return str;
    }
}
