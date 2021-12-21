package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.MessengerIpcClient;
import com.google.firebase.iid.ServiceStarter;
import com.mopub.common.AdType;
import com.mopub.common.Constants;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
public class FirebaseMessagingService extends EnhancedIntentService {
    private static final Queue<String> recentlyReceivedMessageIds = new ArrayDeque(10);

    public void onDeletedMessages() {
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    public void onMessageSent(String str) {
    }

    public void onNewToken(String str) {
    }

    public void onSendError(String str, Exception exc) {
    }

    /* access modifiers changed from: protected */
    public Intent getStartCommandIntent(Intent intent) {
        return ServiceStarter.getInstance().getMessagingEvent();
    }

    public boolean handleIntentOnMainThread(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        handleNotificationOpen(intent);
        return true;
    }

    private void handleNotificationOpen(Intent intent) {
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException unused) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (MessagingAnalytics.shouldUploadScionMetrics(intent)) {
            MessagingAnalytics.logNotificationOpen(intent);
        }
    }

    public void handleIntent(Intent intent) {
        String action = intent.getAction();
        if ("com.google.android.c2dm.intent.RECEIVE".equals(action) || "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT".equals(action)) {
            handleMessageIntent(intent);
        } else if ("com.google.firebase.messaging.NOTIFICATION_DISMISS".equals(action)) {
            if (MessagingAnalytics.shouldUploadScionMetrics(intent)) {
                MessagingAnalytics.logNotificationDismiss(intent);
            }
        } else if ("com.google.firebase.messaging.NEW_TOKEN".equals(action)) {
            onNewToken(intent.getStringExtra("token"));
        } else {
            String valueOf = String.valueOf(intent.getAction());
            Log.d("FirebaseMessaging", valueOf.length() != 0 ? "Unknown intent action: ".concat(valueOf) : new String("Unknown intent action: "));
        }
    }

    private void handleMessageIntent(Intent intent) {
        String stringExtra = intent.getStringExtra("google.message_id");
        Task<Void> ackMessage = ackMessage(stringExtra);
        if (!alreadyReceivedMessage(stringExtra)) {
            passMessageIntentToSdk(intent);
        }
        try {
            Tasks.await(ackMessage, getAckTimeoutMillis(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
            sb.append("Message ack failed: ");
            sb.append(valueOf);
            Log.w("FirebaseMessaging", sb.toString());
        }
    }

    private void passMessageIntentToSdk(Intent intent) {
        String stringExtra = intent.getStringExtra(Constants.VAST_TRACKER_MESSAGE_TYPE);
        if (stringExtra == null) {
            stringExtra = "gcm";
        }
        char c = 65535;
        switch (stringExtra.hashCode()) {
            case -2062414158:
                if (stringExtra.equals("deleted_messages")) {
                    c = 1;
                    break;
                }
                break;
            case 102161:
                if (stringExtra.equals("gcm")) {
                    c = 0;
                    break;
                }
                break;
            case 814694033:
                if (stringExtra.equals("send_error")) {
                    c = 3;
                    break;
                }
                break;
            case 814800675:
                if (stringExtra.equals("send_event")) {
                    c = 2;
                    break;
                }
                break;
        }
        if (c == 0) {
            if (MessagingAnalytics.shouldUploadScionMetrics(intent)) {
                MessagingAnalytics.logNotificationReceived(intent, (Transport<String>) null);
            }
            if (MessagingAnalytics.shouldUploadFirelogAnalytics(intent)) {
                TransportFactory transportFactory = FirebaseMessaging.getTransportFactory();
                if (transportFactory != null) {
                    MessagingAnalytics.logNotificationReceived(intent, transportFactory.getTransport("FCM_CLIENT_EVENT_LOGGING", String.class, Encoding.of(AdType.STATIC_NATIVE), FirebaseMessagingService$$Lambda$0.$instance));
                } else {
                    Log.e("FirebaseMessaging", "TransportFactory is null. Skip exporting message delivery metrics to Big Query");
                }
            }
            dispatchMessage(intent);
        } else if (c == 1) {
            onDeletedMessages();
        } else if (c == 2) {
            onMessageSent(intent.getStringExtra("google.message_id"));
        } else if (c != 3) {
            String valueOf = String.valueOf(stringExtra);
            Log.w("FirebaseMessaging", valueOf.length() != 0 ? "Received message with unknown type: ".concat(valueOf) : new String("Received message with unknown type: "));
        } else {
            onSendError(getMessageId(intent), new SendException(intent.getStringExtra("error")));
        }
    }

    private void dispatchMessage(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.remove("androidx.content.wakelockid");
        if (NotificationParams.isNotification(extras)) {
            NotificationParams notificationParams = new NotificationParams(extras);
            ExecutorService newNetworkIOExecutor = FcmExecutors.newNetworkIOExecutor();
            try {
                if (!new DisplayNotification(this, notificationParams, newNetworkIOExecutor).handleNotification()) {
                    newNetworkIOExecutor.shutdown();
                    if (MessagingAnalytics.shouldUploadScionMetrics(intent)) {
                        MessagingAnalytics.logNotificationForeground(intent);
                    }
                } else {
                    return;
                }
            } finally {
                newNetworkIOExecutor.shutdown();
            }
        }
        onMessageReceived(new RemoteMessage(extras));
    }

    private boolean alreadyReceivedMessage(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!recentlyReceivedMessageIds.contains(str)) {
            if (recentlyReceivedMessageIds.size() >= 10) {
                recentlyReceivedMessageIds.remove();
            }
            recentlyReceivedMessageIds.add(str);
            return false;
        } else if (!Log.isLoggable("FirebaseMessaging", 3)) {
            return true;
        } else {
            String valueOf = String.valueOf(str);
            Log.d("FirebaseMessaging", valueOf.length() != 0 ? "Received duplicate message: ".concat(valueOf) : new String("Received duplicate message: "));
            return true;
        }
    }

    private Task<Void> ackMessage(String str) {
        if (TextUtils.isEmpty(str)) {
            return Tasks.forResult(null);
        }
        Bundle bundle = new Bundle();
        bundle.putString("google.message_id", str);
        return MessengerIpcClient.getInstance(this).sendOneWayRequest(2, bundle);
    }

    private String getMessageId(Intent intent) {
        String stringExtra = intent.getStringExtra("google.message_id");
        return stringExtra == null ? intent.getStringExtra("message_id") : stringExtra;
    }

    /* access modifiers changed from: package-private */
    public long getAckTimeoutMillis() {
        return TimeUnit.SECONDS.toMillis(1);
    }
}
