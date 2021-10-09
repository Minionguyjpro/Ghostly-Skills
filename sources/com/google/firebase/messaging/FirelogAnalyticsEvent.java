package com.google.firebase.messaging;

import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
final class FirelogAnalyticsEvent {
    private final String eventType;
    private final Intent intent;

    /* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
    static class FirelogAnalyticsEventEncoder implements ObjectEncoder<FirelogAnalyticsEvent> {
        FirelogAnalyticsEventEncoder() {
        }

        public void encode(FirelogAnalyticsEvent firelogAnalyticsEvent, ObjectEncoderContext objectEncoderContext) throws EncodingException, IOException {
            Intent intent = firelogAnalyticsEvent.getIntent();
            objectEncoderContext.add("ttl", MessagingAnalytics.getTtl(intent));
            objectEncoderContext.add("event", (Object) firelogAnalyticsEvent.getEventType());
            objectEncoderContext.add("instanceId", (Object) MessagingAnalytics.getInstanceId());
            objectEncoderContext.add("priority", MessagingAnalytics.getPriority(intent));
            objectEncoderContext.add("packageName", (Object) MessagingAnalytics.getPackageName());
            objectEncoderContext.add("sdkPlatform", (Object) "ANDROID");
            objectEncoderContext.add("messageType", (Object) MessagingAnalytics.getMessageTypeForFirelog(intent));
            String messageId = MessagingAnalytics.getMessageId(intent);
            if (messageId != null) {
                objectEncoderContext.add("messageId", (Object) messageId);
            }
            String topic = MessagingAnalytics.getTopic(intent);
            if (topic != null) {
                objectEncoderContext.add("topic", (Object) topic);
            }
            String collapseKey = MessagingAnalytics.getCollapseKey(intent);
            if (collapseKey != null) {
                objectEncoderContext.add("collapseKey", (Object) collapseKey);
            }
            if (MessagingAnalytics.getMessageLabel(intent) != null) {
                objectEncoderContext.add("analyticsLabel", (Object) MessagingAnalytics.getMessageLabel(intent));
            }
            if (MessagingAnalytics.getComposerLabel(intent) != null) {
                objectEncoderContext.add("composerLabel", (Object) MessagingAnalytics.getComposerLabel(intent));
            }
            String projectNumber = MessagingAnalytics.getProjectNumber();
            if (projectNumber != null) {
                objectEncoderContext.add("projectNumber", (Object) projectNumber);
            }
        }
    }

    FirelogAnalyticsEvent(String str, Intent intent2) {
        this.eventType = Preconditions.checkNotEmpty(str, "evenType must be non-null");
        this.intent = (Intent) Preconditions.checkNotNull(intent2, "intent must be non-null");
    }

    /* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
    static final class FirelogAnalyticsEventWrapperEncoder implements ObjectEncoder<FirelogAnalyticsEventWrapper> {
        FirelogAnalyticsEventWrapperEncoder() {
        }

        public final void encode(FirelogAnalyticsEventWrapper firelogAnalyticsEventWrapper, ObjectEncoderContext objectEncoderContext) throws EncodingException, IOException {
            objectEncoderContext.add("messaging_client_event", (Object) firelogAnalyticsEventWrapper.getFirelogAnalyticsEvent());
        }
    }

    /* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
    static final class FirelogAnalyticsEventWrapper {
        private final FirelogAnalyticsEvent firelogAnalyticsEvent;

        FirelogAnalyticsEventWrapper(FirelogAnalyticsEvent firelogAnalyticsEvent2) {
            this.firelogAnalyticsEvent = (FirelogAnalyticsEvent) Preconditions.checkNotNull(firelogAnalyticsEvent2);
        }

        /* access modifiers changed from: package-private */
        public final FirelogAnalyticsEvent getFirelogAnalyticsEvent() {
            return this.firelogAnalyticsEvent;
        }
    }

    /* access modifiers changed from: package-private */
    public final Intent getIntent() {
        return this.intent;
    }

    /* access modifiers changed from: package-private */
    public final String getEventType() {
        return this.eventType;
    }
}
