package com.onesignal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.appnext.core.a.b;
import com.mopub.common.AdType;
import com.onesignal.LocationController;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSSessionManager;
import com.onesignal.OneSignalRemoteParams;
import com.onesignal.OneSignalRestClient;
import com.onesignal.PushRegistrator;
import com.onesignal.UserStateSynchronizer;
import com.onesignal.influence.OSTrackerFactory;
import com.onesignal.influence.model.OSInfluence;
import com.onesignal.outcomes.OSOutcomeEventsFactory;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OneSignal {
    private static AdvertisingIdentifierProvider adIdProvider;
    private static OneSignalAPIClient apiClient = new OneSignalRestClientWrapper();
    static Context appContext;
    private static AppEntryAction appEntryState = AppEntryAction.APP_CLOSE;
    static String appId;
    private static OSEmailSubscriptionState currentEmailSubscriptionState;
    private static OSPermissionState currentPermissionState;
    private static OSSubscriptionState currentSubscriptionState;
    static DelayedConsentInitializationParameters delayedInitParams;
    private static String emailId = null;
    private static EmailUpdateHandler emailLogoutHandler;
    private static EmailUpdateHandler emailUpdateHandler;
    private static boolean foreground;
    /* access modifiers changed from: private */
    public static boolean getTagsCall;
    private static IAPUpdateJob iapUpdateJob;
    private static IdsAvailableHandler idsAvailableHandler;
    private static boolean initDone;
    /* access modifiers changed from: private */
    public static LocationController.LocationPoint lastLocationPoint;
    static OSPermissionState lastPermissionState;
    /* access modifiers changed from: private */
    public static String lastRegistrationId;
    static OSSubscriptionState lastSubscriptionState;
    static AtomicLong lastTaskId = new AtomicLong();
    /* access modifiers changed from: private */
    public static boolean locationFired;
    private static LOG_LEVEL logCatLevel = LOG_LEVEL.WARN;
    /* access modifiers changed from: private */
    public static OSLogger logger = new OSLogWrapper();
    /* access modifiers changed from: private */
    public static String mGoogleProjectNumber;
    static Builder mInitBuilder = new Builder();
    private static PushRegistrator mPushRegistrator;
    private static OSUtils osUtils = new OSUtils();
    /* access modifiers changed from: private */
    public static OSOutcomeEventsController outcomeEventsController;
    private static OSOutcomeEventsFactory outcomeEventsFactory;
    /* access modifiers changed from: private */
    public static ArrayList<GetTagsHandler> pendingGetTagsHandlers = new ArrayList<>();
    static ExecutorService pendingTaskExecutor;
    private static OSObservable<Object, OSPermissionStateChanges> permissionStateChangesObserver;
    private static HashSet<String> postedOpenedNotifIds = new HashSet<>();
    /* access modifiers changed from: private */
    public static OSSharedPreferences preferences;
    /* access modifiers changed from: private */
    public static boolean promptedLocation;
    /* access modifiers changed from: private */
    public static boolean registerForPushFired;
    static OneSignalRemoteParams.Params remoteParams;
    static boolean requiresUserPrivacyConsent = false;
    public static String sdkType = "native";
    private static OSSessionManager.SessionListener sessionListener = new OSSessionManager.SessionListener() {
        public void onSessionEnding(List<OSInfluence> list) {
            if (OneSignal.outcomeEventsController == null) {
                OneSignal.Log(LOG_LEVEL.WARN, "OneSignal onSessionEnding called before init!");
            }
            if (OneSignal.outcomeEventsController != null) {
                OneSignal.outcomeEventsController.cleanOutcomes();
            }
            FocusTimeController.getInstance().onSessionEnded(list);
        }
    };
    private static OSSessionManager sessionManager;
    static boolean shareLocation = true;
    /* access modifiers changed from: private */
    public static int subscribableStatus;
    private static OSObservable<Object, OSSubscriptionStateChanges> subscriptionStateChangesObserver;
    public static ConcurrentLinkedQueue<Runnable> taskQueueWaitingForInit = new ConcurrentLinkedQueue<>();
    private static TrackAmazonPurchase trackAmazonPurchase;
    private static TrackFirebaseAnalytics trackFirebaseAnalytics;
    private static TrackGooglePurchase trackGooglePurchase;
    /* access modifiers changed from: private */
    public static OSTrackerFactory trackerFactory;
    private static Collection<JSONArray> unprocessedOpenedNotifis = new ArrayList();
    /* access modifiers changed from: private */
    public static String userId = null;
    private static LOG_LEVEL visualLogLevel = LOG_LEVEL.NONE;
    private static boolean waitingToPostStateSync;

    public interface ChangeTagsUpdateHandler {
        void onFailure(SendTagsError sendTagsError);

        void onSuccess(JSONObject jSONObject);
    }

    public enum EmailErrorType {
        VALIDATION,
        REQUIRES_EMAIL_AUTH,
        INVALID_OPERATION,
        NETWORK
    }

    public interface EmailUpdateHandler {
        void onFailure(EmailUpdateError emailUpdateError);

        void onSuccess();
    }

    public interface GetTagsHandler {
        void tagsAvailable(JSONObject jSONObject);
    }

    public interface IdsAvailableHandler {
        void idsAvailable(String str, String str2);
    }

    public interface InAppMessageClickHandler {
        void inAppMessageClicked(OSInAppMessageAction oSInAppMessageAction);
    }

    public enum LOG_LEVEL {
        NONE,
        FATAL,
        ERROR,
        WARN,
        INFO,
        DEBUG,
        VERBOSE
    }

    public interface NotificationOpenedHandler {
        void notificationOpened(OSNotificationOpenResult oSNotificationOpenResult);
    }

    public interface NotificationReceivedHandler {
        void notificationReceived(OSNotification oSNotification);
    }

    public enum OSInFocusDisplayOption {
        None,
        InAppAlert,
        Notification
    }

    interface OSInternalExternalUserIdUpdateCompletionHandler {
        void onComplete(String str, boolean z);
    }

    interface OSPromptActionCompletionCallback {
        void onCompleted(PromptActionResult promptActionResult);
    }

    public interface OutcomeCallback {
        void onSuccess(OutcomeEvent outcomeEvent);
    }

    enum PromptActionResult {
        PERMISSION_GRANTED,
        PERMISSION_DENIED,
        LOCATION_PERMISSIONS_MISSING_MANIFEST,
        ERROR
    }

    /* access modifiers changed from: private */
    public static boolean pushStatusRuntimeError(int i) {
        return i < -6;
    }

    public enum AppEntryAction {
        NOTIFICATION_CLICK,
        APP_OPEN,
        APP_CLOSE;

        public boolean isNotificationClick() {
            return equals(NOTIFICATION_CLICK);
        }

        public boolean isAppOpen() {
            return equals(APP_OPEN);
        }

        public boolean isAppClose() {
            return equals(APP_CLOSE);
        }
    }

    public static class SendTagsError {
        private int code;
        private String message;

        SendTagsError(int i, String str) {
            this.message = str;
            this.code = i;
        }
    }

    public static class EmailUpdateError {
        private String message;
        private EmailErrorType type;

        EmailUpdateError(EmailErrorType emailErrorType, String str) {
            this.type = emailErrorType;
            this.message = str;
        }
    }

    public static class Builder {
        Context mContext;
        boolean mDisableGmsMissingPrompt;
        OSInFocusDisplayOption mDisplayOption;
        boolean mDisplayOptionCarryOver;
        boolean mFilterOtherGCMReceivers;
        InAppMessageClickHandler mInAppMessageClickHandler;
        NotificationOpenedHandler mNotificationOpenedHandler;
        NotificationReceivedHandler mNotificationReceivedHandler;
        boolean mPromptLocation;
        boolean mUnsubscribeWhenNotificationsAreDisabled;

        private Builder() {
            this.mDisplayOption = OSInFocusDisplayOption.InAppAlert;
        }

        private Builder(Context context) {
            this.mDisplayOption = OSInFocusDisplayOption.InAppAlert;
            this.mContext = context;
        }

        public Builder inFocusDisplaying(OSInFocusDisplayOption oSInFocusDisplayOption) {
            this.mDisplayOptionCarryOver = false;
            this.mDisplayOption = oSInFocusDisplayOption;
            return this;
        }

        public Builder unsubscribeWhenNotificationsAreDisabled(boolean z) {
            this.mUnsubscribeWhenNotificationsAreDisabled = z;
            return this;
        }

        public void init() {
            OneSignal.init(this);
        }
    }

    static {
        OSSharedPreferencesWrapper oSSharedPreferencesWrapper = new OSSharedPreferencesWrapper();
        preferences = oSSharedPreferencesWrapper;
        OSTrackerFactory oSTrackerFactory = new OSTrackerFactory(oSSharedPreferencesWrapper, logger);
        trackerFactory = oSTrackerFactory;
        sessionManager = new OSSessionManager(sessionListener, oSTrackerFactory, logger);
    }

    static boolean isInitDone() {
        return initDone;
    }

    static boolean isForeground() {
        return foreground;
    }

    static AppEntryAction getAppEntryState() {
        return appEntryState;
    }

    private static synchronized AdvertisingIdentifierProvider getAdIdProvider() {
        AdvertisingIdentifierProvider advertisingIdentifierProvider;
        synchronized (OneSignal.class) {
            if (adIdProvider == null && OSUtils.isAndroidDeviceType()) {
                adIdProvider = new AdvertisingIdProviderGPS();
            }
            advertisingIdentifierProvider = adIdProvider;
        }
        return advertisingIdentifierProvider;
    }

    private static OSPermissionState getCurrentPermissionState(Context context) {
        if (context == null) {
            return null;
        }
        if (currentPermissionState == null) {
            OSPermissionState oSPermissionState = new OSPermissionState(false);
            currentPermissionState = oSPermissionState;
            oSPermissionState.observable.addObserverStrong(new OSPermissionChangedInternalObserver());
        }
        return currentPermissionState;
    }

    static OSObservable<Object, OSPermissionStateChanges> getPermissionStateChangesObserver() {
        if (permissionStateChangesObserver == null) {
            permissionStateChangesObserver = new OSObservable<>("onOSPermissionChanged", true);
        }
        return permissionStateChangesObserver;
    }

    /* access modifiers changed from: private */
    public static OSSubscriptionState getCurrentSubscriptionState(Context context) {
        if (context == null) {
            return null;
        }
        if (currentSubscriptionState == null) {
            currentSubscriptionState = new OSSubscriptionState(false, getCurrentPermissionState(context).getEnabled());
            getCurrentPermissionState(context).observable.addObserver(currentSubscriptionState);
            currentSubscriptionState.observable.addObserverStrong(new OSSubscriptionChangedInternalObserver());
        }
        return currentSubscriptionState;
    }

    static OSObservable<Object, OSSubscriptionStateChanges> getSubscriptionStateChangesObserver() {
        if (subscriptionStateChangesObserver == null) {
            subscriptionStateChangesObserver = new OSObservable<>("onOSSubscriptionChanged", true);
        }
        return subscriptionStateChangesObserver;
    }

    private static OSEmailSubscriptionState getCurrentEmailSubscriptionState(Context context) {
        if (context == null) {
            return null;
        }
        if (currentEmailSubscriptionState == null) {
            OSEmailSubscriptionState oSEmailSubscriptionState = new OSEmailSubscriptionState(false);
            currentEmailSubscriptionState = oSEmailSubscriptionState;
            oSEmailSubscriptionState.observable.addObserverStrong(new OSEmailSubscriptionChangedInternalObserver());
        }
        return currentEmailSubscriptionState;
    }

    private static class IAPUpdateJob {
        boolean newAsExisting;
        OneSignalRestClient.ResponseHandler restResponseHandler;
        JSONArray toReport;

        IAPUpdateJob(JSONArray jSONArray) {
            this.toReport = jSONArray;
        }
    }

    public static void setAppContext(Context context) {
        if (context == null) {
            Log(LOG_LEVEL.WARN, "setAppContext(null) is not valid, ignoring!");
            return;
        }
        boolean z = appContext == null;
        Context applicationContext = context.getApplicationContext();
        appContext = applicationContext;
        ActivityLifecycleListener.registerActivityLifecycleCallbacks((Application) applicationContext);
        if (z) {
            if (outcomeEventsFactory == null) {
                outcomeEventsFactory = new OSOutcomeEventsFactory(logger, apiClient, getDBHelperInstance(), preferences);
            }
            sessionManager.initSessionFromCache();
            outcomeEventsController = new OSOutcomeEventsController(sessionManager, outcomeEventsFactory);
            OneSignalPrefs.startDelayedWrite();
            OneSignalCacheCleaner.cleanOldCachedData(context);
        }
    }

    static OneSignalDbHelper getDBHelperInstance() {
        return OneSignalDbHelper.getInstance(appContext);
    }

    public static Builder startInit(Context context) {
        return new Builder(context);
    }

    /* access modifiers changed from: private */
    public static void init(Builder builder) {
        if (mInitBuilder.mDisplayOptionCarryOver) {
            builder.mDisplayOption = mInitBuilder.mDisplayOption;
        }
        mInitBuilder = builder;
        Context context = builder.mContext;
        mInitBuilder.mContext = null;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            String string = bundle.getString("onesignal_google_project_number");
            if (string != null && string.length() > 4) {
                string = string.substring(4);
            }
            init(context, string, bundle.getString("onesignal_app_id"), mInitBuilder.mNotificationOpenedHandler, mInitBuilder.mNotificationReceivedHandler);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void init(Context context, String str, String str2, NotificationOpenedHandler notificationOpenedHandler, NotificationReceivedHandler notificationReceivedHandler) {
        mInitBuilder = createInitBuilder(notificationOpenedHandler, notificationReceivedHandler);
        setAppContext(context);
        setupPrivacyConsent(context);
        if (requiresUserPrivacyConsent()) {
            Log(LOG_LEVEL.VERBOSE, "OneSignal SDK initialization delayed, user privacy consent is set to required for this application.");
            delayedInitParams = new DelayedConsentInitializationParameters(context, str, str2, notificationOpenedHandler, notificationReceivedHandler);
            return;
        }
        mInitBuilder = createInitBuilder(notificationOpenedHandler, notificationReceivedHandler);
        if (!isGoogleProjectNumberRemote()) {
            mGoogleProjectNumber = str;
        }
        subscribableStatus = osUtils.initializationChecker(context, str2);
        if (!isSubscriptionStatusUninitializable()) {
            String str3 = appId;
            if (str3 != null && !str3.equals(str2)) {
                initDone = false;
            }
            if (!initDone) {
                appId = str2;
                saveFilterOtherGCMReceivers(mInitBuilder.mFilterOtherGCMReceivers);
                handleActivityLifecycleHandler(context);
                OneSignalStateSynchronizer.initUserState();
                handleAmazonPurchase();
                handleAppIdChange();
                OSPermissionChangedInternalObserver.handleInternalChanges(getCurrentPermissionState(appContext));
                doSessionInit();
                if (mInitBuilder.mNotificationOpenedHandler != null) {
                    fireCallbackForOpenedNotifications();
                }
                if (TrackGooglePurchase.CanTrack(appContext)) {
                    trackGooglePurchase = new TrackGooglePurchase(appContext);
                }
                if (TrackFirebaseAnalytics.CanTrack()) {
                    trackFirebaseAnalytics = new TrackFirebaseAnalytics(appContext);
                }
                PushRegistratorFCM.disableFirebaseInstanceIdService(appContext);
                initDone = true;
                outcomeEventsController.sendSavedOutcomes();
                startPendingTasks();
            } else if (mInitBuilder.mNotificationOpenedHandler != null) {
                fireCallbackForOpenedNotifications();
            }
        }
    }

    private static void setupPrivacyConsent(Context context) {
        try {
            setRequiresUserPrivacyConsent("ENABLE".equalsIgnoreCase(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("com.onesignal.PrivacyConsent")));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static Builder createInitBuilder(NotificationOpenedHandler notificationOpenedHandler, NotificationReceivedHandler notificationReceivedHandler) {
        mInitBuilder.mDisplayOptionCarryOver = false;
        mInitBuilder.mNotificationOpenedHandler = notificationOpenedHandler;
        mInitBuilder.mNotificationReceivedHandler = notificationReceivedHandler;
        return mInitBuilder;
    }

    private static void handleAppIdChange() {
        String savedAppId = getSavedAppId();
        if (savedAppId == null) {
            BadgeCountUpdater.updateCount(0, appContext);
            saveAppId(appId);
        } else if (!savedAppId.equals(appId)) {
            Log(LOG_LEVEL.DEBUG, "APP ID changed, clearing user id as it is no longer valid.");
            saveAppId(appId);
            OneSignalStateSynchronizer.resetCurrentState();
            remoteParams = null;
        }
    }

    public static boolean userProvidedPrivacyConsent() {
        return getSavedUserConsentStatus();
    }

    private static boolean isGoogleProjectNumberRemote() {
        OneSignalRemoteParams.Params params = remoteParams;
        return (params == null || params.googleProjectNumber == null) ? false : true;
    }

    private static boolean isSubscriptionStatusUninitializable() {
        return subscribableStatus == -999;
    }

    private static void handleActivityLifecycleHandler(Context context) {
        boolean isContextActivity = isContextActivity(context);
        foreground = isContextActivity;
        if (isContextActivity) {
            ActivityLifecycleHandler.curActivity = (Activity) context;
            NotificationRestorer.asyncRestore(appContext);
            FocusTimeController.getInstance().appForegrounded();
            return;
        }
        ActivityLifecycleHandler.nextResumeIsFirstActivity = true;
    }

    private static void handleAmazonPurchase() {
        try {
            Class.forName("com.amazon.device.iap.PurchasingListener");
            trackAmazonPurchase = new TrackAmazonPurchase(appContext);
        } catch (ClassNotFoundException unused) {
        }
    }

    private static void doSessionInit() {
        if (isPastOnSessionTime()) {
            OneSignalStateSynchronizer.setNewSession();
            if (foreground) {
                outcomeEventsController.cleanOutcomes();
                sessionManager.restartSessionIfNeeded(getAppEntryState());
            }
        } else if (foreground) {
            OSInAppMessageController.getController().initWithCachedInAppMessages();
            sessionManager.attemptSessionUpgrade(getAppEntryState());
        }
        if (foreground || !hasUserId()) {
            setLastSessionTime(System.currentTimeMillis());
            startRegistrationOrOnSession();
        }
    }

    private static boolean isContextActivity(Context context) {
        return context instanceof Activity;
    }

    /* access modifiers changed from: private */
    public static void onTaskRan(long j) {
        if (lastTaskId.get() == j) {
            Log(LOG_LEVEL.INFO, "Last Pending Task has ran, shutting down");
            pendingTaskExecutor.shutdown();
        }
    }

    private static class PendingTaskRunnable implements Runnable {
        private Runnable innerTask;
        /* access modifiers changed from: private */
        public long taskId;

        PendingTaskRunnable(Runnable runnable) {
            this.innerTask = runnable;
        }

        public void run() {
            this.innerTask.run();
            OneSignal.onTaskRan(this.taskId);
        }
    }

    private static void startPendingTasks() {
        if (!taskQueueWaitingForInit.isEmpty()) {
            pendingTaskExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread(runnable);
                    thread.setName("OS_PENDING_EXECUTOR_" + thread.getId());
                    return thread;
                }
            });
            while (!taskQueueWaitingForInit.isEmpty()) {
                pendingTaskExecutor.submit(taskQueueWaitingForInit.poll());
            }
        }
    }

    private static void addTaskToQueue(PendingTaskRunnable pendingTaskRunnable) {
        long unused = pendingTaskRunnable.taskId = lastTaskId.incrementAndGet();
        ExecutorService executorService = pendingTaskExecutor;
        if (executorService == null) {
            LOG_LEVEL log_level = LOG_LEVEL.INFO;
            Log(log_level, "Adding a task to the pending queue with ID: " + pendingTaskRunnable.taskId);
            taskQueueWaitingForInit.add(pendingTaskRunnable);
        } else if (!executorService.isShutdown()) {
            LOG_LEVEL log_level2 = LOG_LEVEL.INFO;
            Log(log_level2, "Executor is still running, add to the executor with ID: " + pendingTaskRunnable.taskId);
            try {
                pendingTaskExecutor.submit(pendingTaskRunnable);
            } catch (RejectedExecutionException e) {
                LOG_LEVEL log_level3 = LOG_LEVEL.INFO;
                Log(log_level3, "Executor is shutdown, running task manually with ID: " + pendingTaskRunnable.taskId);
                pendingTaskRunnable.run();
                e.printStackTrace();
            }
        }
    }

    private static boolean shouldRunTaskThroughQueue() {
        if (initDone && pendingTaskExecutor == null) {
            return false;
        }
        if (!initDone && pendingTaskExecutor == null) {
            return true;
        }
        ExecutorService executorService = pendingTaskExecutor;
        if (executorService == null || executorService.isShutdown()) {
            return false;
        }
        return true;
    }

    private static void startRegistrationOrOnSession() {
        if (!waitingToPostStateSync) {
            waitingToPostStateSync = true;
            if (OneSignalStateSynchronizer.getSyncAsNewSession()) {
                locationFired = false;
            }
            startLocationUpdate();
            registerForPushFired = false;
            makeAndroidParamsRequest();
        }
    }

    private static void startLocationUpdate() {
        AnonymousClass3 r0 = new LocationController.LocationHandler() {
            public LocationController.PermissionType getType() {
                return LocationController.PermissionType.STARTUP;
            }

            public void onComplete(LocationController.LocationPoint locationPoint) {
                LocationController.LocationPoint unused = OneSignal.lastLocationPoint = locationPoint;
                boolean unused2 = OneSignal.locationFired = true;
                OneSignal.registerUser();
            }
        };
        boolean z = true;
        boolean z2 = mInitBuilder.mPromptLocation && !promptedLocation;
        if (!promptedLocation && !mInitBuilder.mPromptLocation) {
            z = false;
        }
        promptedLocation = z;
        LocationController.getLocation(appContext, z2, false, r0);
    }

    private static PushRegistrator getPushRegistrator() {
        PushRegistrator pushRegistrator = mPushRegistrator;
        if (pushRegistrator != null) {
            return pushRegistrator;
        }
        if (OSUtils.isFireOSDeviceType()) {
            mPushRegistrator = new PushRegistratorADM();
        } else if (!OSUtils.isAndroidDeviceType()) {
            mPushRegistrator = new PushRegistratorHMS();
        } else if (OSUtils.hasFCMLibrary()) {
            mPushRegistrator = new PushRegistratorFCM();
        } else {
            mPushRegistrator = new PushRegistratorGCM();
        }
        return mPushRegistrator;
    }

    /* access modifiers changed from: private */
    public static void registerForPushToken() {
        getPushRegistrator().registerForPush(appContext, mGoogleProjectNumber, new PushRegistrator.RegisteredHandler() {
            public void complete(String str, int i) {
                if (i < 1) {
                    if (OneSignalStateSynchronizer.getRegistrationId() == null && (OneSignal.subscribableStatus == 1 || OneSignal.pushStatusRuntimeError(OneSignal.subscribableStatus))) {
                        int unused = OneSignal.subscribableStatus = i;
                    }
                } else if (OneSignal.pushStatusRuntimeError(OneSignal.subscribableStatus)) {
                    int unused2 = OneSignal.subscribableStatus = i;
                }
                String unused3 = OneSignal.lastRegistrationId = str;
                boolean unused4 = OneSignal.registerForPushFired = true;
                OneSignal.getCurrentSubscriptionState(OneSignal.appContext).setPushToken(str);
                OneSignal.registerUser();
            }
        });
    }

    private static void makeAndroidParamsRequest() {
        if (remoteParams != null) {
            registerForPushToken();
        } else {
            OneSignalRemoteParams.makeAndroidParamsRequest(new OneSignalRemoteParams.CallBack() {
                public void complete(OneSignalRemoteParams.Params params) {
                    OneSignal.remoteParams = params;
                    if (OneSignal.remoteParams.googleProjectNumber != null) {
                        String unused = OneSignal.mGoogleProjectNumber = OneSignal.remoteParams.googleProjectNumber;
                    }
                    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_FIREBASE_TRACKING_ENABLED", OneSignal.remoteParams.firebaseAnalytics);
                    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "OS_RESTORE_TTL_FILTER", OneSignal.remoteParams.restoreTTLFilter);
                    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "OS_CLEAR_GROUP_SUMMARY_CLICK", OneSignal.remoteParams.clearGroupOnSummaryClick);
                    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_OS_RECEIVE_RECEIPTS_ENABLED", OneSignal.remoteParams.receiveReceiptEnabled);
                    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, OneSignal.preferences.getOutcomesV2KeyName(), params.influenceParams.outcomesV2ServiceEnabled);
                    OSLogger access$1600 = OneSignal.logger;
                    access$1600.debug("OneSignal saveInfluenceParams: " + params.influenceParams.toString());
                    OneSignal.trackerFactory.saveInfluenceParams(params.influenceParams);
                    NotificationChannelManager.processChannelList(OneSignal.appContext, params.notificationChannels);
                    OneSignal.registerForPushToken();
                }
            });
        }
    }

    private static void fireCallbackForOpenedNotifications() {
        for (JSONArray runNotificationOpenedCallback : unprocessedOpenedNotifis) {
            runNotificationOpenedCallback(runNotificationOpenedCallback, true, false);
        }
        unprocessedOpenedNotifis.clear();
    }

    public static void onesignalLog(LOG_LEVEL log_level, String str) {
        Log(log_level, str);
    }

    public static void setRequiresUserPrivacyConsent(boolean z) {
        if (!requiresUserPrivacyConsent || z) {
            requiresUserPrivacyConsent = z;
        } else {
            Log(LOG_LEVEL.ERROR, "Cannot change requiresUserPrivacyConsent() from TRUE to FALSE");
        }
    }

    public static boolean requiresUserPrivacyConsent() {
        return requiresUserPrivacyConsent && !userProvidedPrivacyConsent();
    }

    static boolean shouldLogUserPrivacyConsentErrorMessageForMethodName(String str) {
        if (!requiresUserPrivacyConsent()) {
            return false;
        }
        if (str == null) {
            return true;
        }
        LOG_LEVEL log_level = LOG_LEVEL.WARN;
        Log(log_level, "Method " + str + " was called before the user provided privacy consent. Your application is set to require the user's privacy consent before the OneSignal SDK can be initialized. Please ensure the user has provided consent before calling this method. You can check the latest OneSignal consent status by calling OneSignal.userProvidedPrivacyConsent()");
        return true;
    }

    static boolean atLogLevel(LOG_LEVEL log_level) {
        return log_level.compareTo(visualLogLevel) < 1 || log_level.compareTo(logCatLevel) < 1;
    }

    static void Log(LOG_LEVEL log_level, String str) {
        Log(log_level, str, (Throwable) null);
    }

    static void Log(final LOG_LEVEL log_level, String str, Throwable th) {
        if (log_level.compareTo(logCatLevel) < 1) {
            if (log_level == LOG_LEVEL.VERBOSE) {
                Log.v("OneSignal", str, th);
            } else if (log_level == LOG_LEVEL.DEBUG) {
                Log.d("OneSignal", str, th);
            } else if (log_level == LOG_LEVEL.INFO) {
                Log.i("OneSignal", str, th);
            } else if (log_level == LOG_LEVEL.WARN) {
                Log.w("OneSignal", str, th);
            } else if (log_level == LOG_LEVEL.ERROR || log_level == LOG_LEVEL.FATAL) {
                Log.e("OneSignal", str, th);
            }
        }
        if (log_level.compareTo(visualLogLevel) < 1 && ActivityLifecycleHandler.curActivity != null) {
            try {
                final String str2 = str + "\n";
                if (th != null) {
                    StringWriter stringWriter = new StringWriter();
                    th.printStackTrace(new PrintWriter(stringWriter));
                    str2 = (str2 + th.getMessage()) + stringWriter.toString();
                }
                OSUtils.runOnMainUIThread(new Runnable() {
                    public void run() {
                        if (ActivityLifecycleHandler.curActivity != null) {
                            new AlertDialog.Builder(ActivityLifecycleHandler.curActivity).setTitle(log_level.toString()).setMessage(str2).show();
                        }
                    }
                });
            } catch (Throwable th2) {
                Log.e("OneSignal", "Error showing logging message.", th2);
            }
        }
    }

    static void logHttpError(String str, int i, Throwable th, String str2) {
        String str3;
        if (str2 == null || !atLogLevel(LOG_LEVEL.INFO)) {
            str3 = "";
        } else {
            str3 = "\n" + str2 + "\n";
        }
        Log(LOG_LEVEL.WARN, "HTTP code: " + i + " " + str + str3, th);
    }

    static void onAppLostFocus() {
        foreground = false;
        appEntryState = AppEntryAction.APP_CLOSE;
        setLastSessionTime(System.currentTimeMillis());
        LocationController.onFocusChange();
        if (initDone) {
            TrackAmazonPurchase trackAmazonPurchase2 = trackAmazonPurchase;
            if (trackAmazonPurchase2 != null) {
                trackAmazonPurchase2.checkListener();
            }
            if (appContext == null) {
                Log(LOG_LEVEL.ERROR, "Android Context not found, please call OneSignal.init when your app starts.");
                return;
            }
            FocusTimeController.getInstance().appBackgrounded();
            scheduleSyncService();
        }
    }

    private static boolean scheduleSyncService() {
        boolean persist = OneSignalStateSynchronizer.persist();
        if (persist) {
            OneSignalSyncServiceUtils.scheduleSyncTask(appContext);
        }
        return LocationController.scheduleUpdate(appContext) || persist;
    }

    static void onAppFocus() {
        foreground = true;
        if (!appEntryState.equals(AppEntryAction.NOTIFICATION_CLICK)) {
            appEntryState = AppEntryAction.APP_OPEN;
        }
        LocationController.onFocusChange();
        if (!shouldLogUserPrivacyConsentErrorMessageForMethodName("onAppFocus") && !OSUtils.shouldLogMissingAppIdError(appId)) {
            FocusTimeController.getInstance().appForegrounded();
            doSessionInit();
            TrackGooglePurchase trackGooglePurchase2 = trackGooglePurchase;
            if (trackGooglePurchase2 != null) {
                trackGooglePurchase2.trackIAP();
            }
            NotificationRestorer.asyncRestore(appContext);
            getCurrentPermissionState(appContext).refreshAsTo();
            if (trackFirebaseAnalytics != null && getFirebaseAnalyticsEnabled()) {
                trackFirebaseAnalytics.trackInfluenceOpenEvent();
            }
            OneSignalSyncServiceUtils.cancelSyncTask(appContext);
        }
    }

    static void addNetType(JSONObject jSONObject) {
        try {
            jSONObject.put("net_type", osUtils.getNetType());
        } catch (Throwable unused) {
        }
    }

    private static int getTimeZoneOffset() {
        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        int rawOffset = timeZone.getRawOffset();
        if (timeZone.inDaylightTime(new Date())) {
            rawOffset += timeZone.getDSTSavings();
        }
        return rawOffset / 1000;
    }

    /* access modifiers changed from: private */
    public static void registerUser() {
        LOG_LEVEL log_level = LOG_LEVEL.DEBUG;
        Log(log_level, "registerUser:registerForPushFired:" + registerForPushFired + ", locationFired: " + locationFired + ", remoteParams: " + remoteParams + ", appId: " + appId);
        if (registerForPushFired && locationFired && remoteParams != null && appId != null) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        OneSignal.registerUserTask();
                        OneSignalChromeTabAndroidFrame.setup(OneSignal.appId, OneSignal.userId, AdvertisingIdProviderGPS.getLastValue());
                    } catch (JSONException e) {
                        OneSignal.Log(LOG_LEVEL.FATAL, "FATAL Error registering device!", e);
                    }
                }
            }, "OS_REG_USER").start();
        }
    }

    /* access modifiers changed from: private */
    public static void registerUserTask() throws JSONException {
        LocationController.LocationPoint locationPoint;
        String identifier;
        String packageName = appContext.getPackageName();
        PackageManager packageManager = appContext.getPackageManager();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("app_id", getSavedAppId());
        if (!(getAdIdProvider() == null || (identifier = getAdIdProvider().getIdentifier(appContext)) == null)) {
            jSONObject.put("ad_id", identifier);
        }
        jSONObject.put("device_os", Build.VERSION.RELEASE);
        jSONObject.put("timezone", getTimeZoneOffset());
        jSONObject.put("language", OSUtils.getCorrectedLanguage());
        jSONObject.put("sdk", "031502");
        jSONObject.put("sdk_type", sdkType);
        jSONObject.put("android_package", packageName);
        jSONObject.put("device_model", Build.MODEL);
        try {
            jSONObject.put("game_version", packageManager.getPackageInfo(packageName, 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        jSONObject.put("net_type", osUtils.getNetType());
        jSONObject.put("carrier", osUtils.getCarrierName());
        jSONObject.put("rooted", RootToolsInternalMethods.isRooted());
        OneSignalStateSynchronizer.updateDeviceInfo(jSONObject);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("identifier", lastRegistrationId);
        jSONObject2.put("subscribableStatus", subscribableStatus);
        jSONObject2.put("androidPermission", areNotificationsEnabledForSubscribedState());
        jSONObject2.put("device_type", osUtils.getDeviceType());
        OneSignalStateSynchronizer.updatePushState(jSONObject2);
        if (shareLocation && (locationPoint = lastLocationPoint) != null) {
            OneSignalStateSynchronizer.updateLocation(locationPoint);
        }
        OneSignalStateSynchronizer.readyToUpdate(true);
        waitingToPostStateSync = false;
    }

    public static void sendTags(JSONObject jSONObject) {
        sendTags(jSONObject, (ChangeTagsUpdateHandler) null);
    }

    public static void sendTags(final JSONObject jSONObject, final ChangeTagsUpdateHandler changeTagsUpdateHandler) {
        if (!shouldLogUserPrivacyConsentErrorMessageForMethodName("sendTags()")) {
            AnonymousClass12 r0 = new Runnable() {
                public void run() {
                    if (jSONObject == null) {
                        ChangeTagsUpdateHandler changeTagsUpdateHandler = changeTagsUpdateHandler;
                        if (changeTagsUpdateHandler != null) {
                            changeTagsUpdateHandler.onFailure(new SendTagsError(-1, "Attempted to send null tags"));
                            return;
                        }
                        return;
                    }
                    JSONObject jSONObject = OneSignalStateSynchronizer.getTags(false).result;
                    JSONObject jSONObject2 = new JSONObject();
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        try {
                            Object opt = jSONObject.opt(next);
                            if (!(opt instanceof JSONArray)) {
                                if (!(opt instanceof JSONObject)) {
                                    if (!jSONObject.isNull(next)) {
                                        if (!"".equals(opt)) {
                                            jSONObject2.put(next, opt.toString());
                                        }
                                    }
                                    if (jSONObject != null && jSONObject.has(next)) {
                                        jSONObject2.put(next, "");
                                    }
                                }
                            }
                            LOG_LEVEL log_level = LOG_LEVEL.ERROR;
                            OneSignal.Log(log_level, "Omitting key '" + next + "'! sendTags DO NOT supported nested values!");
                        } catch (Throwable unused) {
                        }
                    }
                    if (!jSONObject2.toString().equals("{}")) {
                        OneSignalStateSynchronizer.sendTags(jSONObject2, changeTagsUpdateHandler);
                        return;
                    }
                    ChangeTagsUpdateHandler changeTagsUpdateHandler2 = changeTagsUpdateHandler;
                    if (changeTagsUpdateHandler2 != null) {
                        changeTagsUpdateHandler2.onSuccess(jSONObject);
                    }
                }
            };
            if (appContext == null || shouldRunTaskThroughQueue()) {
                Log(LOG_LEVEL.ERROR, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue.");
                if (changeTagsUpdateHandler != null) {
                    changeTagsUpdateHandler.onFailure(new SendTagsError(-1, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue."));
                }
                addTaskToQueue(new PendingTaskRunnable(r0));
                return;
            }
            r0.run();
        }
    }

    private static void internalFireGetTagsCallbacks() {
        synchronized (pendingGetTagsHandlers) {
            if (pendingGetTagsHandlers.size() != 0) {
                new Thread(new Runnable() {
                    public void run() {
                        JSONObject jSONObject;
                        UserStateSynchronizer.GetTagsResult tags = OneSignalStateSynchronizer.getTags(!OneSignal.getTagsCall);
                        if (tags.serverSuccess) {
                            boolean unused = OneSignal.getTagsCall = true;
                        }
                        synchronized (OneSignal.pendingGetTagsHandlers) {
                            Iterator it = OneSignal.pendingGetTagsHandlers.iterator();
                            while (it.hasNext()) {
                                GetTagsHandler getTagsHandler = (GetTagsHandler) it.next();
                                if (tags.result != null) {
                                    if (!tags.toString().equals("{}")) {
                                        jSONObject = tags.result;
                                        getTagsHandler.tagsAvailable(jSONObject);
                                    }
                                }
                                jSONObject = null;
                                getTagsHandler.tagsAvailable(jSONObject);
                            }
                            OneSignal.pendingGetTagsHandlers.clear();
                        }
                    }
                }, "OS_GETTAGS_CALLBACK").start();
            }
        }
    }

    public static void deleteTags(JSONArray jSONArray, ChangeTagsUpdateHandler changeTagsUpdateHandler) {
        if (!shouldLogUserPrivacyConsentErrorMessageForMethodName("deleteTags()")) {
            try {
                JSONObject jSONObject = new JSONObject();
                for (int i = 0; i < jSONArray.length(); i++) {
                    jSONObject.put(jSONArray.getString(i), "");
                }
                sendTags(jSONObject, changeTagsUpdateHandler);
            } catch (Throwable th) {
                Log(LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", th);
            }
        }
    }

    static void fireIdsAvailableCallback() {
        if (idsAvailableHandler != null) {
            OSUtils.runOnMainUIThread(new Runnable() {
                public void run() {
                    OneSignal.internalFireIdsAvailableCallback();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0027, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void internalFireIdsAvailableCallback() {
        /*
            java.lang.Class<com.onesignal.OneSignal> r0 = com.onesignal.OneSignal.class
            monitor-enter(r0)
            com.onesignal.OneSignal$IdsAvailableHandler r1 = idsAvailableHandler     // Catch:{ all -> 0x0028 }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)
            return
        L_0x0009:
            java.lang.String r1 = com.onesignal.OneSignalStateSynchronizer.getRegistrationId()     // Catch:{ all -> 0x0028 }
            boolean r2 = com.onesignal.OneSignalStateSynchronizer.getSubscribed()     // Catch:{ all -> 0x0028 }
            r3 = 0
            if (r2 != 0) goto L_0x0015
            r1 = r3
        L_0x0015:
            java.lang.String r2 = getUserId()     // Catch:{ all -> 0x0028 }
            if (r2 != 0) goto L_0x001d
            monitor-exit(r0)
            return
        L_0x001d:
            com.onesignal.OneSignal$IdsAvailableHandler r4 = idsAvailableHandler     // Catch:{ all -> 0x0028 }
            r4.idsAvailable(r2, r1)     // Catch:{ all -> 0x0028 }
            if (r1 == 0) goto L_0x0026
            idsAvailableHandler = r3     // Catch:{ all -> 0x0028 }
        L_0x0026:
            monitor-exit(r0)
            return
        L_0x0028:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OneSignal.internalFireIdsAvailableCallback():void");
    }

    static void sendPurchases(JSONArray jSONArray, boolean z, OneSignalRestClient.ResponseHandler responseHandler) {
        if (!shouldLogUserPrivacyConsentErrorMessageForMethodName("sendPurchases()")) {
            if (getUserId() == null) {
                IAPUpdateJob iAPUpdateJob = new IAPUpdateJob(jSONArray);
                iapUpdateJob = iAPUpdateJob;
                iAPUpdateJob.newAsExisting = z;
                iapUpdateJob.restResponseHandler = responseHandler;
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("app_id", getSavedAppId());
                if (z) {
                    jSONObject.put(b.hY, true);
                }
                jSONObject.put("purchases", jSONArray);
                OneSignalRestClient.post("players/" + getUserId() + "/on_purchase", jSONObject, responseHandler);
                if (getEmailId() != null) {
                    OneSignalRestClient.post("players/" + getEmailId() + "/on_purchase", jSONObject, (OneSignalRestClient.ResponseHandler) null);
                }
            } catch (Throwable th) {
                Log(LOG_LEVEL.ERROR, "Failed to generate JSON for sendPurchases.", th);
            }
        }
    }

    private static boolean openURLFromNotification(Context context, JSONArray jSONArray) {
        String optString;
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName((String) null)) {
            return false;
        }
        int length = jSONArray.length();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has(AdType.CUSTOM)) {
                    JSONObject jSONObject2 = new JSONObject(jSONObject.optString(AdType.CUSTOM));
                    if (jSONObject2.has("u") && (optString = jSONObject2.optString("u", (String) null)) != null) {
                        OSUtils.openURLInBrowser(optString);
                        z = true;
                    }
                }
            } catch (Throwable th) {
                LOG_LEVEL log_level = LOG_LEVEL.ERROR;
                Log(log_level, "Error parsing JSON item " + i + "/" + length + " for launching a web URL.", th);
            }
        }
        return z;
    }

    private static void runNotificationOpenedCallback(JSONArray jSONArray, boolean z, boolean z2) {
        Builder builder = mInitBuilder;
        if (builder == null || builder.mNotificationOpenedHandler == null) {
            unprocessedOpenedNotifis.add(jSONArray);
        } else {
            fireNotificationOpenedHandler(generateOsNotificationOpenResult(jSONArray, z, z2));
        }
    }

    private static OSNotificationOpenResult generateOsNotificationOpenResult(JSONArray jSONArray, boolean z, boolean z2) {
        int length = jSONArray.length();
        OSNotificationOpenResult oSNotificationOpenResult = new OSNotificationOpenResult();
        OSNotification oSNotification = new OSNotification();
        oSNotification.isAppInFocus = isAppActive();
        oSNotification.shown = z;
        oSNotification.androidNotificationId = jSONArray.optJSONObject(0).optInt("androidNotificationId");
        boolean z3 = true;
        String str = null;
        for (int i = 0; i < length; i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                oSNotification.payload = NotificationBundleProcessor.OSNotificationPayloadFrom(jSONObject);
                if (str == null && jSONObject.has("actionId")) {
                    str = jSONObject.optString("actionId", (String) null);
                }
                if (z3) {
                    z3 = false;
                } else {
                    if (oSNotification.groupedNotifications == null) {
                        oSNotification.groupedNotifications = new ArrayList();
                    }
                    oSNotification.groupedNotifications.add(oSNotification.payload);
                }
            } catch (Throwable th) {
                Log(LOG_LEVEL.ERROR, "Error parsing JSON item " + i + "/" + length + " for callback.", th);
            }
        }
        oSNotificationOpenResult.notification = oSNotification;
        oSNotificationOpenResult.action = new OSNotificationAction();
        oSNotificationOpenResult.action.actionID = str;
        oSNotificationOpenResult.action.type = str != null ? OSNotificationAction.ActionType.ActionTaken : OSNotificationAction.ActionType.Opened;
        if (z2) {
            oSNotificationOpenResult.notification.displayType = OSNotification.DisplayType.InAppAlert;
        } else {
            oSNotificationOpenResult.notification.displayType = OSNotification.DisplayType.Notification;
        }
        return oSNotificationOpenResult;
    }

    private static void fireNotificationOpenedHandler(final OSNotificationOpenResult oSNotificationOpenResult) {
        OSUtils.runOnMainUIThread(new Runnable() {
            public void run() {
                OneSignal.mInitBuilder.mNotificationOpenedHandler.notificationOpened(oSNotificationOpenResult);
            }
        });
    }

    static void handleNotificationReceived(JSONArray jSONArray, boolean z, boolean z2) {
        OSNotificationOpenResult generateOsNotificationOpenResult = generateOsNotificationOpenResult(jSONArray, z, z2);
        if (trackFirebaseAnalytics != null && getFirebaseAnalyticsEnabled()) {
            trackFirebaseAnalytics.trackReceivedEvent(generateOsNotificationOpenResult);
        }
        Builder builder = mInitBuilder;
        if (builder != null && builder.mNotificationReceivedHandler != null) {
            mInitBuilder.mNotificationReceivedHandler.notificationReceived(generateOsNotificationOpenResult.notification);
        }
    }

    public static void handleNotificationOpen(Context context, JSONArray jSONArray, boolean z, String str) {
        if (!shouldLogUserPrivacyConsentErrorMessageForMethodName((String) null)) {
            notificationOpenedRESTCall(context, jSONArray);
            if (trackFirebaseAnalytics != null && getFirebaseAnalyticsEnabled()) {
                trackFirebaseAnalytics.trackOpenedEvent(generateOsNotificationOpenResult(jSONArray, true, z));
            }
            boolean z2 = false;
            boolean equals = "DISABLE".equals(OSUtils.getManifestMeta(context, "com.onesignal.NotificationOpened.DEFAULT"));
            if (!equals) {
                z2 = openURLFromNotification(context, jSONArray);
            }
            if (shouldInitDirectSessionFromNotificationOpen(context, z, z2, equals)) {
                AppEntryAction appEntryAction = AppEntryAction.NOTIFICATION_CLICK;
                appEntryState = appEntryAction;
                sessionManager.onDirectInfluenceFromNotificationOpen(appEntryAction, str);
            }
            runNotificationOpenedCallback(jSONArray, true, z);
        }
    }

    static boolean startOrResumeApp(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            return false;
        }
        launchIntentForPackage.setFlags(268566528);
        context.startActivity(launchIntentForPackage);
        return true;
    }

    private static boolean shouldInitDirectSessionFromNotificationOpen(Context context, boolean z, boolean z2, boolean z3) {
        return !z && !z2 && !z3 && !foreground && startOrResumeApp(context);
    }

    private static void notificationOpenedRESTCall(Context context, JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                String optString = new JSONObject(jSONArray.getJSONObject(i).optString(AdType.CUSTOM, (String) null)).optString("i", (String) null);
                if (!postedOpenedNotifIds.contains(optString)) {
                    postedOpenedNotifIds.add(optString);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("app_id", getSavedAppId(context));
                    jSONObject.put("player_id", getSavedUserId(context));
                    jSONObject.put("opened", true);
                    jSONObject.put("device_type", osUtils.getDeviceType());
                    OneSignalRestClient.put("notifications/" + optString, jSONObject, new OneSignalRestClient.ResponseHandler() {
                        /* access modifiers changed from: package-private */
                        public void onFailure(int i, String str, Throwable th) {
                            OneSignal.logHttpError("sending Notification Opened Failed", i, th, str);
                        }
                    });
                }
            } catch (Throwable th) {
                Log(LOG_LEVEL.ERROR, "Failed to generate JSON to send notification opened.", th);
            }
        }
    }

    private static void saveAppId(String str) {
        if (appContext != null) {
            OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_APP_ID", str);
        }
    }

    static String getSavedAppId() {
        return getSavedAppId(appContext);
    }

    private static String getSavedAppId(Context context) {
        if (context == null) {
            return null;
        }
        return OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_APP_ID", (String) null);
    }

    static boolean getSavedUserConsentStatus() {
        return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
    }

    private static String getSavedUserId(Context context) {
        if (context == null) {
            return null;
        }
        return OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_PLAYER_ID", (String) null);
    }

    static boolean hasUserId() {
        return getUserId() != null;
    }

    static String getUserId() {
        Context context;
        if (userId == null && (context = appContext) != null) {
            userId = getSavedUserId(context);
        }
        return userId;
    }

    static void saveUserId(String str) {
        userId = str;
        if (appContext != null) {
            OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_PLAYER_ID", userId);
        }
    }

    static boolean hasEmailId() {
        return !TextUtils.isEmpty(emailId);
    }

    static String getEmailId() {
        if (TextUtils.isEmpty(emailId) && appContext != null) {
            emailId = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "OS_EMAIL_ID", (String) null);
        }
        return emailId;
    }

    static void saveEmailId(String str) {
        emailId = str;
        if (appContext != null) {
            OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "OS_EMAIL_ID", "".equals(emailId) ? null : emailId);
        }
    }

    static boolean getFilterOtherGCMReceivers(Context context) {
        return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
    }

    static void saveFilterOtherGCMReceivers(boolean z) {
        if (appContext != null) {
            OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "OS_FILTER_OTHER_GCM_RECEIVERS", z);
        }
    }

    static void updateUserIdDependents(String str) {
        saveUserId(str);
        fireIdsAvailableCallback();
        internalFireGetTagsCallbacks();
        getCurrentSubscriptionState(appContext).setUserId(str);
        IAPUpdateJob iAPUpdateJob = iapUpdateJob;
        if (iAPUpdateJob != null) {
            sendPurchases(iAPUpdateJob.toReport, iapUpdateJob.newAsExisting, iapUpdateJob.restResponseHandler);
            iapUpdateJob = null;
        }
        OneSignalStateSynchronizer.refreshEmailState();
        OneSignalChromeTabAndroidFrame.setup(appId, str, AdvertisingIdProviderGPS.getLastValue());
    }

    static void updateEmailIdDependents(String str) {
        saveEmailId(str);
        getCurrentEmailSubscriptionState(appContext).setEmailUserId(str);
        try {
            OneSignalStateSynchronizer.updatePushState(new JSONObject().put("parent_player_id", str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static boolean getFirebaseAnalyticsEnabled() {
        return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_FIREBASE_TRACKING_ENABLED", false);
    }

    static boolean getClearGroupSummaryClick() {
        return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "OS_CLEAR_GROUP_SUMMARY_CLICK", true);
    }

    static boolean getVibrate() {
        return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_VIBRATE_ENABLED", true);
    }

    static boolean getSoundEnabled() {
        return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_SOUND_ENABLED", true);
    }

    static void setLastSessionTime(long j) {
        OneSignalPrefs.saveLong(OneSignalPrefs.PREFS_ONESIGNAL, "OS_LAST_SESSION_TIME", j);
    }

    private static long getLastSessionTime() {
        return OneSignalPrefs.getLong(OneSignalPrefs.PREFS_ONESIGNAL, "OS_LAST_SESSION_TIME", -31000);
    }

    static boolean getNotificationsWhenActiveEnabled() {
        Builder builder = mInitBuilder;
        if (builder == null || builder.mDisplayOption == OSInFocusDisplayOption.Notification) {
            return true;
        }
        return false;
    }

    static boolean getInAppAlertNotificationEnabled() {
        Builder builder = mInitBuilder;
        if (builder != null && builder.mDisplayOption == OSInFocusDisplayOption.InAppAlert) {
            return true;
        }
        return false;
    }

    static void promptLocation(final OSPromptActionCompletionCallback oSPromptActionCompletionCallback, final boolean z) {
        if (!shouldLogUserPrivacyConsentErrorMessageForMethodName("promptLocation()")) {
            AnonymousClass21 r0 = new Runnable() {
                public void run() {
                    LocationController.getLocation(OneSignal.appContext, true, z, new LocationController.LocationPromptCompletionHandler() {
                        public LocationController.PermissionType getType() {
                            return LocationController.PermissionType.PROMPT_LOCATION;
                        }

                        public void onComplete(LocationController.LocationPoint locationPoint) {
                            if (!OneSignal.shouldLogUserPrivacyConsentErrorMessageForMethodName("promptLocation()") && locationPoint != null) {
                                OneSignalStateSynchronizer.updateLocation(locationPoint);
                            }
                        }

                        /* access modifiers changed from: package-private */
                        public void onAnswered(PromptActionResult promptActionResult) {
                            super.onAnswered(promptActionResult);
                            if (oSPromptActionCompletionCallback != null) {
                                oSPromptActionCompletionCallback.onCompleted(promptActionResult);
                            }
                        }
                    });
                    boolean unused = OneSignal.promptedLocation = true;
                }
            };
            if (appContext == null || shouldRunTaskThroughQueue()) {
                Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not prompt for location at this time - moving this operation to awaiting queue.");
                addTaskToQueue(new PendingTaskRunnable(r0));
                return;
            }
            r0.run();
        }
    }

    public static void cancelNotification(final int i) {
        AnonymousClass23 r0 = new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:17:0x0089 A[SYNTHETIC, Splitter:B:17:0x0089] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    java.lang.String r0 = "dismissed"
                    java.lang.String r1 = "Error closing transaction! "
                    android.content.Context r2 = com.onesignal.OneSignal.appContext
                    com.onesignal.OneSignalDbHelper r2 = com.onesignal.OneSignalDbHelper.getInstance(r2)
                    r3 = 0
                    android.database.sqlite.SQLiteDatabase r2 = r2.getSQLiteDatabaseWithRetries()     // Catch:{ all -> 0x0069 }
                    r2.beginTransaction()     // Catch:{ all -> 0x0066 }
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0066 }
                    r4.<init>()     // Catch:{ all -> 0x0066 }
                    java.lang.String r5 = "android_notification_id = "
                    r4.append(r5)     // Catch:{ all -> 0x0066 }
                    int r5 = r4     // Catch:{ all -> 0x0066 }
                    r4.append(r5)     // Catch:{ all -> 0x0066 }
                    java.lang.String r5 = " AND "
                    r4.append(r5)     // Catch:{ all -> 0x0066 }
                    java.lang.String r5 = "opened"
                    r4.append(r5)     // Catch:{ all -> 0x0066 }
                    java.lang.String r5 = " = 0 AND "
                    r4.append(r5)     // Catch:{ all -> 0x0066 }
                    r4.append(r0)     // Catch:{ all -> 0x0066 }
                    java.lang.String r5 = " = 0"
                    r4.append(r5)     // Catch:{ all -> 0x0066 }
                    java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0066 }
                    android.content.ContentValues r5 = new android.content.ContentValues     // Catch:{ all -> 0x0066 }
                    r5.<init>()     // Catch:{ all -> 0x0066 }
                    r6 = 1
                    java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0066 }
                    r5.put(r0, r6)     // Catch:{ all -> 0x0066 }
                    java.lang.String r0 = "notification"
                    int r0 = r2.update(r0, r5, r4, r3)     // Catch:{ all -> 0x0066 }
                    if (r0 <= 0) goto L_0x0058
                    android.content.Context r0 = com.onesignal.OneSignal.appContext     // Catch:{ all -> 0x0066 }
                    int r3 = r4     // Catch:{ all -> 0x0066 }
                    com.onesignal.NotificationSummaryManager.updatePossibleDependentSummaryOnDismiss(r0, r2, r3)     // Catch:{ all -> 0x0066 }
                L_0x0058:
                    android.content.Context r0 = com.onesignal.OneSignal.appContext     // Catch:{ all -> 0x0066 }
                    com.onesignal.BadgeCountUpdater.update(r2, r0)     // Catch:{ all -> 0x0066 }
                    r2.setTransactionSuccessful()     // Catch:{ all -> 0x0066 }
                    if (r2 == 0) goto L_0x0093
                    r2.endTransaction()     // Catch:{ all -> 0x008d }
                    goto L_0x0093
                L_0x0066:
                    r0 = move-exception
                    r3 = r2
                    goto L_0x006a
                L_0x0069:
                    r0 = move-exception
                L_0x006a:
                    com.onesignal.OneSignal$LOG_LEVEL r2 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x009f }
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x009f }
                    r4.<init>()     // Catch:{ all -> 0x009f }
                    java.lang.String r5 = "Error marking a notification id "
                    r4.append(r5)     // Catch:{ all -> 0x009f }
                    int r5 = r4     // Catch:{ all -> 0x009f }
                    r4.append(r5)     // Catch:{ all -> 0x009f }
                    java.lang.String r5 = " as dismissed! "
                    r4.append(r5)     // Catch:{ all -> 0x009f }
                    java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x009f }
                    com.onesignal.OneSignal.Log(r2, r4, r0)     // Catch:{ all -> 0x009f }
                    if (r3 == 0) goto L_0x0093
                    r3.endTransaction()     // Catch:{ all -> 0x008d }
                    goto L_0x0093
                L_0x008d:
                    r0 = move-exception
                    com.onesignal.OneSignal$LOG_LEVEL r2 = com.onesignal.OneSignal.LOG_LEVEL.ERROR
                    com.onesignal.OneSignal.Log(r2, r1, r0)
                L_0x0093:
                    android.content.Context r0 = com.onesignal.OneSignal.appContext
                    android.app.NotificationManager r0 = com.onesignal.OneSignalNotificationManager.getNotificationManager(r0)
                    int r1 = r4
                    r0.cancel(r1)
                    return
                L_0x009f:
                    r0 = move-exception
                    if (r3 == 0) goto L_0x00ac
                    r3.endTransaction()     // Catch:{ all -> 0x00a6 }
                    goto L_0x00ac
                L_0x00a6:
                    r2 = move-exception
                    com.onesignal.OneSignal$LOG_LEVEL r3 = com.onesignal.OneSignal.LOG_LEVEL.ERROR
                    com.onesignal.OneSignal.Log(r3, r1, r2)
                L_0x00ac:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OneSignal.AnonymousClass23.run():void");
            }
        };
        if (appContext == null || shouldRunTaskThroughQueue()) {
            LOG_LEVEL log_level = LOG_LEVEL.ERROR;
            Log(log_level, "OneSignal.init has not been called. Could not clear notification id: " + i + " at this time - movingthis operation to a waiting task queue. The notification will still be canceledfrom NotificationManager at this time.");
            taskQueueWaitingForInit.add(r0);
            return;
        }
        r0.run();
    }

    private static boolean isDuplicateNotification(String str, Context context) {
        boolean z;
        if (str == null || "".equals(str)) {
            return false;
        }
        Cursor cursor = null;
        try {
            Cursor query = OneSignalDbHelper.getInstance(context).getSQLiteDatabaseWithRetries().query("notification", new String[]{"notification_id"}, "notification_id = ?", new String[]{str}, (String) null, (String) null, (String) null);
            z = query.moveToFirst();
            if (query != null) {
                query.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        if (!z) {
            return false;
        }
        LOG_LEVEL log_level = LOG_LEVEL.DEBUG;
        Log(log_level, "Duplicate GCM message received, skip processing of " + str);
        return true;
    }

    static boolean notValidOrDuplicated(Context context, JSONObject jSONObject) {
        String oSNotificationIdFromJson = OSNotificationFormatHelper.getOSNotificationIdFromJson(jSONObject);
        return oSNotificationIdFromJson == null || isDuplicateNotification(oSNotificationIdFromJson, context);
    }

    static boolean isAppActive() {
        return initDone && isForeground();
    }

    private static boolean isPastOnSessionTime() {
        return System.currentTimeMillis() - getLastSessionTime() >= 30000;
    }

    static boolean areNotificationsEnabledForSubscribedState() {
        if (mInitBuilder.mUnsubscribeWhenNotificationsAreDisabled) {
            return OSUtils.areNotificationsEnabled(appContext);
        }
        return true;
    }

    static void handleSuccessfulEmailLogout() {
        EmailUpdateHandler emailUpdateHandler2 = emailLogoutHandler;
        if (emailUpdateHandler2 != null) {
            emailUpdateHandler2.onSuccess();
            emailLogoutHandler = null;
        }
    }

    static void handleFailedEmailLogout() {
        EmailUpdateHandler emailUpdateHandler2 = emailLogoutHandler;
        if (emailUpdateHandler2 != null) {
            emailUpdateHandler2.onFailure(new EmailUpdateError(EmailErrorType.NETWORK, "Failed due to network failure. Will retry on next sync."));
            emailLogoutHandler = null;
        }
    }

    static void fireEmailUpdateSuccess() {
        EmailUpdateHandler emailUpdateHandler2 = emailUpdateHandler;
        if (emailUpdateHandler2 != null) {
            emailUpdateHandler2.onSuccess();
            emailUpdateHandler = null;
        }
    }

    static void fireEmailUpdateFailure() {
        EmailUpdateHandler emailUpdateHandler2 = emailUpdateHandler;
        if (emailUpdateHandler2 != null) {
            emailUpdateHandler2.onFailure(new EmailUpdateError(EmailErrorType.NETWORK, "Failed due to network failure. Will retry on next sync."));
            emailUpdateHandler = null;
        }
    }

    static OSSessionManager getSessionManager() {
        return sessionManager;
    }

    static void sendClickActionOutcomes(List<OSInAppMessageOutcome> list) {
        OSOutcomeEventsController oSOutcomeEventsController = outcomeEventsController;
        if (oSOutcomeEventsController == null) {
            Log(LOG_LEVEL.ERROR, "Make sure OneSignal.init is called first");
        } else {
            oSOutcomeEventsController.sendClickActionOutcomes(list);
        }
    }
}
