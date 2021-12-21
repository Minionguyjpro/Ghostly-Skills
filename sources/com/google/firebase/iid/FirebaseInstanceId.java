package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.Store;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
public class FirebaseInstanceId {
    private static final Pattern API_KEY_FORMAT = Pattern.compile("\\AA[\\w-]{38}\\z");
    private static final long MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8);
    private static Store store;
    static ScheduledExecutorService syncExecutor;
    /* access modifiers changed from: private */
    public final FirebaseApp app;
    private final AutoInit autoInit;
    final Executor fileIoExecutor;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final Metadata metadata;
    private final RequestDeduplicator requestDeduplicator;
    private final GmsRpc rpc;
    private boolean syncScheduledOrRunning;

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static FirebaseInstanceId getInstance(FirebaseApp firebaseApp) {
        checkRequiredFirebaseOptions(firebaseApp);
        return (FirebaseInstanceId) firebaseApp.get(FirebaseInstanceId.class);
    }

    /* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
    private class AutoInit {
        private EventHandler<DataCollectionDefaultChange> dataCollectionDefaultChangeEventHandler;
        private Boolean fcmAutoInitEnabled;
        private boolean initialized;
        private boolean isFcmLibraryPresent;
        private final Subscriber subscriber;

        AutoInit(Subscriber subscriber2) {
            this.subscriber = subscriber2;
        }

        /* access modifiers changed from: package-private */
        public synchronized void initialize() {
            if (!this.initialized) {
                this.isFcmLibraryPresent = isFcmPresent();
                Boolean readEnabled = readEnabled();
                this.fcmAutoInitEnabled = readEnabled;
                if (readEnabled == null && this.isFcmLibraryPresent) {
                    FirebaseInstanceId$AutoInit$$Lambda$0 firebaseInstanceId$AutoInit$$Lambda$0 = new FirebaseInstanceId$AutoInit$$Lambda$0(this);
                    this.dataCollectionDefaultChangeEventHandler = firebaseInstanceId$AutoInit$$Lambda$0;
                    this.subscriber.subscribe(DataCollectionDefaultChange.class, firebaseInstanceId$AutoInit$$Lambda$0);
                }
                this.initialized = true;
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean isEnabled() {
            initialize();
            if (this.fcmAutoInitEnabled == null) {
                return this.isFcmLibraryPresent && FirebaseInstanceId.this.app.isDataCollectionDefaultEnabled();
            }
            return this.fcmAutoInitEnabled.booleanValue();
        }

        private Boolean readEnabled() {
            ApplicationInfo applicationInfo;
            Context applicationContext = FirebaseInstanceId.this.app.getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("auto_init")) {
                return Boolean.valueOf(sharedPreferences.getBoolean("auto_init", false));
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_messaging_auto_init_enabled")) {
                    return null;
                }
                return Boolean.valueOf(applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled"));
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        private boolean isFcmPresent() {
            try {
                Class.forName("com.google.firebase.messaging.FirebaseMessaging");
                return true;
            } catch (ClassNotFoundException unused) {
                Context applicationContext = FirebaseInstanceId.this.app.getApplicationContext();
                Intent intent = new Intent("com.google.firebase.MESSAGING_EVENT");
                intent.setPackage(applicationContext.getPackageName());
                ResolveInfo resolveService = applicationContext.getPackageManager().resolveService(intent, 0);
                if (resolveService == null || resolveService.serviceInfo == null) {
                    return false;
                }
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$initialize$0$FirebaseInstanceId$AutoInit(Event event) {
            synchronized (this) {
                if (isEnabled()) {
                    FirebaseInstanceId.this.startSyncIfNecessary();
                }
            }
        }
    }

    FirebaseInstanceId(FirebaseApp firebaseApp, Subscriber subscriber, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi) {
        this(firebaseApp, new Metadata(firebaseApp.getApplicationContext()), FirebaseIidExecutors.newCachedSingleThreadExecutor(), FirebaseIidExecutors.newCachedSingleThreadExecutor(), subscriber, userAgentPublisher, heartBeatInfo, firebaseInstallationsApi);
    }

    FirebaseInstanceId(FirebaseApp firebaseApp, Metadata metadata2, Executor executor, Executor executor2, Subscriber subscriber, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi) {
        this.syncScheduledOrRunning = false;
        if (Metadata.getDefaultSenderId(firebaseApp) != null) {
            synchronized (FirebaseInstanceId.class) {
                if (store == null) {
                    store = new Store(firebaseApp.getApplicationContext());
                }
            }
            this.app = firebaseApp;
            this.metadata = metadata2;
            this.rpc = new GmsRpc(firebaseApp, metadata2, userAgentPublisher, heartBeatInfo, firebaseInstallationsApi);
            this.fileIoExecutor = executor2;
            this.autoInit = new AutoInit(subscriber);
            this.requestDeduplicator = new RequestDeduplicator(executor);
            this.firebaseInstallations = firebaseInstallationsApi;
            executor2.execute(new FirebaseInstanceId$$Lambda$0(this));
            return;
        }
        throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
    }

    /* access modifiers changed from: private */
    public void startSyncIfNecessary() {
        if (tokenNeedsRefresh(getTokenWithoutTriggeringSync())) {
            startSync();
        }
    }

    /* access modifiers changed from: package-private */
    public FirebaseApp getApp() {
        return this.app;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setSyncScheduledOrRunning(boolean z) {
        this.syncScheduledOrRunning = z;
    }

    /* access modifiers changed from: package-private */
    public synchronized void startSync() {
        if (!this.syncScheduledOrRunning) {
            syncWithDelaySecondsInternal(0);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void syncWithDelaySecondsInternal(long j) {
        enqueueTaskWithDelaySeconds(new SyncTask(this, Math.min(Math.max(30, j << 1), MAX_DELAY_SEC)), j);
        this.syncScheduledOrRunning = true;
    }

    /* access modifiers changed from: package-private */
    public void enqueueTaskWithDelaySeconds(Runnable runnable, long j) {
        synchronized (FirebaseInstanceId.class) {
            if (syncExecutor == null) {
                syncExecutor = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("FirebaseInstanceId"));
            }
            syncExecutor.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    public String getId() {
        checkRequiredFirebaseOptions(this.app);
        startSyncIfNecessary();
        return getIdWithoutTriggeringSync();
    }

    private static void checkRequiredFirebaseOptions(FirebaseApp firebaseApp) {
        Preconditions.checkNotEmpty(firebaseApp.getOptions().getProjectId(), "Please set your project ID. A valid Firebase project ID is required to communicate with Firebase server APIs: It identifies your project with Google.");
        Preconditions.checkNotEmpty(firebaseApp.getOptions().getApplicationId(), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.");
        Preconditions.checkNotEmpty(firebaseApp.getOptions().getApiKey(), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.");
        Preconditions.checkArgument(isValidAppIdFormat(firebaseApp.getOptions().getApplicationId()), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        Preconditions.checkArgument(isValidApiKeyFormat(firebaseApp.getOptions().getApiKey()), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.");
    }

    static boolean isValidAppIdFormat(@Nonnull String str) {
        return str.contains(":");
    }

    static boolean isValidApiKeyFormat(@Nonnull String str) {
        return API_KEY_FORMAT.matcher(str).matches();
    }

    /* access modifiers changed from: package-private */
    public String getIdWithoutTriggeringSync() {
        try {
            store.setCreationTime(this.app.getPersistenceKey());
            return (String) awaitTaskAllowOnMainThread(this.firebaseInstallations.getId());
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public Task<InstanceIdResult> getInstanceId() {
        checkRequiredFirebaseOptions(this.app);
        return getInstanceId(Metadata.getDefaultSenderId(this.app), "*");
    }

    private Task<InstanceIdResult> getInstanceId(String str, String str2) {
        return Tasks.forResult(null).continueWithTask(this.fileIoExecutor, new FirebaseInstanceId$$Lambda$1(this, str, rationaliseScope(str2)));
    }

    public String getToken(String str, String str2) throws IOException {
        checkRequiredFirebaseOptions(this.app);
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return ((InstanceIdResult) awaitTask(getInstanceId(str, str2))).getToken();
        }
        throw new IOException("MAIN_THREAD");
    }

    /* access modifiers changed from: package-private */
    public Store.Token getTokenWithoutTriggeringSync() {
        return getTokenWithoutTriggeringSync(Metadata.getDefaultSenderId(this.app), "*");
    }

    /* access modifiers changed from: package-private */
    public Store.Token getTokenWithoutTriggeringSync(String str, String str2) {
        return store.getToken(getSubtype(), str, str2);
    }

    /* access modifiers changed from: package-private */
    public String blockingGetMasterToken() throws IOException {
        return getToken(Metadata.getDefaultSenderId(this.app), "*");
    }

    private <T> T awaitTask(Task<T> task) throws IOException {
        try {
            return Tasks.await(task, 30000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(cause.getMessage())) {
                    resetStorageAndScheduleSync();
                }
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new IOException(e);
            }
        } catch (InterruptedException | TimeoutException unused) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    private static <T> T awaitTaskAllowOnMainThread(Task<T> task) throws InterruptedException {
        Preconditions.checkNotNull(task, "Task must not be null");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        task.addOnCompleteListener(FirebaseInstanceId$$Lambda$2.$instance, (OnCompleteListener<T>) new FirebaseInstanceId$$Lambda$3(countDownLatch));
        countDownLatch.await(30000, TimeUnit.MILLISECONDS);
        return getResultOrThrowException(task);
    }

    private static <T> T getResultOrThrowException(Task<T> task) {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        if (task.isCanceled()) {
            throw new CancellationException("Task is already canceled");
        } else if (task.isComplete()) {
            throw new IllegalStateException(task.getException());
        } else {
            throw new IllegalThreadStateException("Firebase Installations getId Task has timed out.");
        }
    }

    static boolean isDebugLogEnabled() {
        if (!Log.isLoggable("FirebaseInstanceId", 3)) {
            return Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public synchronized void resetStorageAndScheduleSync() {
        store.deleteAll();
        if (isFcmAutoInitEnabled()) {
            startSync();
        }
    }

    public boolean isGmsCorePresent() {
        return this.metadata.isGmscorePresent();
    }

    /* access modifiers changed from: package-private */
    public void forceTokenRefresh() {
        store.deleteTokens(getSubtype());
        startSync();
    }

    public boolean isFcmAutoInitEnabled() {
        return this.autoInit.isEnabled();
    }

    private static String rationaliseScope(String str) {
        return (str.isEmpty() || str.equalsIgnoreCase("fcm") || str.equalsIgnoreCase("gcm")) ? "*" : str;
    }

    private String getSubtype() {
        if ("[DEFAULT]".equals(this.app.getName())) {
            return "";
        }
        return this.app.getPersistenceKey();
    }

    /* access modifiers changed from: package-private */
    public boolean tokenNeedsRefresh(Store.Token token) {
        return token == null || token.needsRefresh(this.metadata.getAppVersionCode());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task lambda$getInstanceId$3$FirebaseInstanceId(String str, String str2, Task task) throws Exception {
        String idWithoutTriggeringSync = getIdWithoutTriggeringSync();
        Store.Token tokenWithoutTriggeringSync = getTokenWithoutTriggeringSync(str, str2);
        if (!tokenNeedsRefresh(tokenWithoutTriggeringSync)) {
            return Tasks.forResult(new InstanceIdResultImpl(idWithoutTriggeringSync, tokenWithoutTriggeringSync.token));
        }
        return this.requestDeduplicator.getOrStartGetTokenRequest(str, str2, new FirebaseInstanceId$$Lambda$4(this, idWithoutTriggeringSync, str, str2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task lambda$getInstanceId$2$FirebaseInstanceId(String str, String str2, String str3) {
        return this.rpc.getToken(str, str2, str3).onSuccessTask(this.fileIoExecutor, new FirebaseInstanceId$$Lambda$5(this, str2, str3, str));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task lambda$getInstanceId$1$FirebaseInstanceId(String str, String str2, String str3, String str4) throws Exception {
        store.saveToken(getSubtype(), str, str2, str4, this.metadata.getAppVersionCode());
        return Tasks.forResult(new InstanceIdResultImpl(str3, str4));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$new$0$FirebaseInstanceId() {
        if (isFcmAutoInitEnabled()) {
            startSyncIfNecessary();
        }
    }
}
