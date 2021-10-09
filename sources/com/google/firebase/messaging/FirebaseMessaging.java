package com.google.firebase.messaging;

import android.content.Context;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.Metadata;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.UserAgentPublisher;

/* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
public class FirebaseMessaging {
    static TransportFactory transportFactory;
    private final Context context;
    private final FirebaseInstanceId iid;
    private final Task<TopicsSubscriber> topicsSubscriberTask;

    static synchronized FirebaseMessaging getInstance(FirebaseApp firebaseApp) {
        FirebaseMessaging firebaseMessaging;
        Class cls = FirebaseMessaging.class;
        synchronized (cls) {
            firebaseMessaging = (FirebaseMessaging) firebaseApp.get(cls);
        }
        return firebaseMessaging;
    }

    FirebaseMessaging(FirebaseApp firebaseApp, FirebaseInstanceId firebaseInstanceId, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi, TransportFactory transportFactory2) {
        transportFactory = transportFactory2;
        this.iid = firebaseInstanceId;
        Context applicationContext = firebaseApp.getApplicationContext();
        this.context = applicationContext;
        Task<TopicsSubscriber> createInstance = TopicsSubscriber.createInstance(firebaseApp, firebaseInstanceId, new Metadata(applicationContext), userAgentPublisher, heartBeatInfo, firebaseInstallationsApi, this.context, FcmExecutors.newTopicsSyncExecutor());
        this.topicsSubscriberTask = createInstance;
        createInstance.addOnSuccessListener(FcmExecutors.newTopicsSyncTriggerExecutor(), (OnSuccessListener<? super TopicsSubscriber>) new FirebaseMessaging$$Lambda$0(this));
    }

    public boolean isAutoInitEnabled() {
        return this.iid.isFcmAutoInitEnabled();
    }

    public static TransportFactory getTransportFactory() {
        return transportFactory;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$new$0$FirebaseMessaging(TopicsSubscriber topicsSubscriber) {
        if (isAutoInitEnabled()) {
            topicsSubscriber.startTopicsSyncIfNecessary();
        }
    }
}
