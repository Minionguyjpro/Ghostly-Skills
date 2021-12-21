package com.mopub.common.privacy;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.Settings;
import android.text.TextUtils;
import com.mopub.common.GpsHelper;
import com.mopub.common.Preconditions;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.SharedPreferencesHelper;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import java.util.Calendar;

public class MoPubIdentifier {
    private static final int MISSING_VALUE = -1;
    private static final String PREF_AD_INFO_GROUP = "com.mopub.settings.identifier";
    private static final String PREF_IDENTIFIER_TIME = "privacy.identifier.time";
    private static final String PREF_IFA_IDENTIFIER = "privacy.identifier.ifa";
    private static final String PREF_LIMIT_AD_TRACKING = "privacy.limit.ad.tracking";
    private static final String PREF_MOPUB_IDENTIFIER = "privacy.identifier.mopub";
    private boolean initialized;
    private AdvertisingId mAdInfo;
    private final Context mAppContext;
    private AdvertisingIdChangeListener mIdChangeListener;
    private volatile SdkInitializationListener mInitializationListener;
    /* access modifiers changed from: private */
    public boolean mRefreshingAdvertisingInfo;

    public interface AdvertisingIdChangeListener {
        void onIdChanged(AdvertisingId advertisingId, AdvertisingId advertisingId2);
    }

    public MoPubIdentifier(Context context) {
        this(context, (AdvertisingIdChangeListener) null);
    }

    MoPubIdentifier(Context context, AdvertisingIdChangeListener advertisingIdChangeListener) {
        Preconditions.checkNotNull(context);
        this.mAppContext = context;
        this.mIdChangeListener = advertisingIdChangeListener;
        AdvertisingId readIdFromStorage = readIdFromStorage(context);
        this.mAdInfo = readIdFromStorage;
        if (readIdFromStorage == null) {
            this.mAdInfo = AdvertisingId.generateExpiredAdvertisingId();
        }
        refreshAdvertisingInfo();
    }

    public AdvertisingId getAdvertisingInfo() {
        if (this.initialized) {
            rotateMopubId();
        }
        AdvertisingId advertisingId = this.mAdInfo;
        refreshAdvertisingInfo();
        return advertisingId;
    }

    private void refreshAdvertisingInfo() {
        if (!this.mRefreshingAdvertisingInfo) {
            this.mRefreshingAdvertisingInfo = true;
            AsyncTasks.safeExecuteOnExecutor(new RefreshAdvertisingInfoAsyncTask(), new Void[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshAdvertisingInfoBackgroundThread() {
        AdvertisingId advertisingId;
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        AdvertisingId advertisingId2 = this.mAdInfo;
        GpsHelper.AdvertisingInfo fetchAdvertisingInfoSync = GpsHelper.fetchAdvertisingInfoSync(this.mAppContext);
        if (fetchAdvertisingInfoSync == null || TextUtils.isEmpty(fetchAdvertisingInfoSync.advertisingId)) {
            advertisingId = getAmazonAdvertisingInfo(this.mAppContext);
        } else {
            advertisingId = new AdvertisingId(fetchAdvertisingInfoSync.advertisingId, advertisingId2.mMopubId, fetchAdvertisingInfoSync.limitAdTracking, advertisingId2.mLastRotation.getTimeInMillis());
        }
        if (advertisingId != null) {
            String generateIdString = advertisingId2.isRotationRequired() ? AdvertisingId.generateIdString() : advertisingId2.mMopubId;
            if (!advertisingId2.isRotationRequired()) {
                timeInMillis = advertisingId2.mLastRotation.getTimeInMillis();
            }
            setAdvertisingInfo(advertisingId.mAdvertisingId, generateIdString, advertisingId.mDoNotTrack, timeInMillis);
        }
        rotateMopubId();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        com.mopub.common.logging.MoPubLog.log(com.mopub.common.logging.MoPubLog.SdkLogEvent.CUSTOM, "Cannot read identifier from shared preferences");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0045 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized com.mopub.common.privacy.AdvertisingId readIdFromStorage(android.content.Context r11) {
        /*
            java.lang.Class<com.mopub.common.privacy.MoPubIdentifier> r0 = com.mopub.common.privacy.MoPubIdentifier.class
            monitor-enter(r0)
            com.mopub.common.Preconditions.checkNotNull(r11)     // Catch:{ all -> 0x0054 }
            java.util.Calendar r1 = java.util.Calendar.getInstance()     // Catch:{ all -> 0x0054 }
            r2 = 0
            java.lang.String r3 = "com.mopub.settings.identifier"
            android.content.SharedPreferences r11 = com.mopub.common.SharedPreferencesHelper.getSharedPreferences(r11, r3)     // Catch:{ ClassCastException -> 0x0045 }
            java.lang.String r3 = "privacy.identifier.ifa"
            java.lang.String r4 = ""
            java.lang.String r6 = r11.getString(r3, r4)     // Catch:{ ClassCastException -> 0x0045 }
            java.lang.String r3 = "privacy.identifier.mopub"
            java.lang.String r4 = ""
            java.lang.String r7 = r11.getString(r3, r4)     // Catch:{ ClassCastException -> 0x0045 }
            java.lang.String r3 = "privacy.identifier.time"
            long r4 = r1.getTimeInMillis()     // Catch:{ ClassCastException -> 0x0045 }
            long r9 = r11.getLong(r3, r4)     // Catch:{ ClassCastException -> 0x0045 }
            java.lang.String r1 = "privacy.limit.ad.tracking"
            boolean r8 = r11.getBoolean(r1, r2)     // Catch:{ ClassCastException -> 0x0045 }
            boolean r11 = android.text.TextUtils.isEmpty(r6)     // Catch:{ ClassCastException -> 0x0045 }
            if (r11 != 0) goto L_0x0051
            boolean r11 = android.text.TextUtils.isEmpty(r7)     // Catch:{ ClassCastException -> 0x0045 }
            if (r11 != 0) goto L_0x0051
            com.mopub.common.privacy.AdvertisingId r11 = new com.mopub.common.privacy.AdvertisingId     // Catch:{ ClassCastException -> 0x0045 }
            r5 = r11
            r5.<init>(r6, r7, r8, r9)     // Catch:{ ClassCastException -> 0x0045 }
            monitor-exit(r0)
            return r11
        L_0x0045:
            com.mopub.common.logging.MoPubLog$SdkLogEvent r11 = com.mopub.common.logging.MoPubLog.SdkLogEvent.CUSTOM     // Catch:{ all -> 0x0054 }
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0054 }
            java.lang.String r3 = "Cannot read identifier from shared preferences"
            r1[r2] = r3     // Catch:{ all -> 0x0054 }
            com.mopub.common.logging.MoPubLog.log(r11, r1)     // Catch:{ all -> 0x0054 }
        L_0x0051:
            r11 = 0
            monitor-exit(r0)
            return r11
        L_0x0054:
            r11 = move-exception
            monitor-exit(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.privacy.MoPubIdentifier.readIdFromStorage(android.content.Context):com.mopub.common.privacy.AdvertisingId");
    }

    private static synchronized void writeIdToStorage(Context context, AdvertisingId advertisingId) {
        synchronized (MoPubIdentifier.class) {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(advertisingId);
            SharedPreferences.Editor edit = SharedPreferencesHelper.getSharedPreferences(context, PREF_AD_INFO_GROUP).edit();
            edit.putBoolean(PREF_LIMIT_AD_TRACKING, advertisingId.mDoNotTrack);
            edit.putString(PREF_IFA_IDENTIFIER, advertisingId.mAdvertisingId);
            edit.putString(PREF_MOPUB_IDENTIFIER, advertisingId.mMopubId);
            edit.putLong(PREF_IDENTIFIER_TIME, advertisingId.mLastRotation.getTimeInMillis());
            edit.apply();
        }
    }

    static synchronized void clearStorage(Context context) {
        synchronized (MoPubIdentifier.class) {
            Preconditions.checkNotNull(context);
            SharedPreferences.Editor edit = SharedPreferencesHelper.getSharedPreferences(context, PREF_AD_INFO_GROUP).edit();
            edit.remove(PREF_LIMIT_AD_TRACKING);
            edit.remove(PREF_IFA_IDENTIFIER);
            edit.remove(PREF_MOPUB_IDENTIFIER);
            edit.remove(PREF_IDENTIFIER_TIME);
            edit.apply();
        }
    }

    /* access modifiers changed from: package-private */
    public void rotateMopubId() {
        if (this.mAdInfo.mAdvertisingId.endsWith("10ca1ad1abe1")) {
            MoPubLog.setLogLevel(MoPubLog.LogLevel.DEBUG);
        }
        if (!this.mAdInfo.isRotationRequired()) {
            setAdvertisingInfo(this.mAdInfo);
        } else {
            setAdvertisingInfo(AdvertisingId.generateFreshAdvertisingId());
        }
    }

    private void setAdvertisingInfo(String str, String str2, boolean z, long j) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        setAdvertisingInfo(new AdvertisingId(str, str2, z, j));
    }

    /* access modifiers changed from: package-private */
    public void setAdvertisingInfo(AdvertisingId advertisingId) {
        AdvertisingId advertisingId2 = this.mAdInfo;
        this.mAdInfo = advertisingId;
        writeIdToStorage(this.mAppContext, advertisingId);
        if (!this.mAdInfo.equals(advertisingId2) || !this.initialized) {
            notifyIdChangeListener(advertisingId2, this.mAdInfo);
        }
        this.initialized = true;
        reportInitializationComplete();
    }

    public void setIdChangeListener(AdvertisingIdChangeListener advertisingIdChangeListener) {
        this.mIdChangeListener = advertisingIdChangeListener;
    }

    /* access modifiers changed from: package-private */
    public void setInitializationListener(SdkInitializationListener sdkInitializationListener) {
        this.mInitializationListener = sdkInitializationListener;
        if (this.initialized) {
            reportInitializationComplete();
        }
    }

    private synchronized void reportInitializationComplete() {
        SdkInitializationListener sdkInitializationListener = this.mInitializationListener;
        if (sdkInitializationListener != null) {
            this.mInitializationListener = null;
            sdkInitializationListener.onInitializationFinished();
        }
    }

    private void notifyIdChangeListener(AdvertisingId advertisingId, AdvertisingId advertisingId2) {
        Preconditions.checkNotNull(advertisingId2);
        AdvertisingIdChangeListener advertisingIdChangeListener = this.mIdChangeListener;
        if (advertisingIdChangeListener != null) {
            advertisingIdChangeListener.onIdChanged(advertisingId, advertisingId2);
        }
    }

    private AdvertisingId getAmazonAdvertisingInfo(Context context) {
        Preconditions.NoThrow.checkNotNull(context);
        ContentResolver contentResolver = context.getContentResolver();
        int i = Settings.Secure.getInt(contentResolver, "limit_ad_tracking", -1);
        String string = Settings.Secure.getString(contentResolver, "advertising_id");
        if (i == -1 || TextUtils.isEmpty(string)) {
            return null;
        }
        boolean z = i != 0;
        AdvertisingId advertisingId = this.mAdInfo;
        return new AdvertisingId(string, advertisingId.mMopubId, z, advertisingId.mLastRotation.getTimeInMillis());
    }

    private class RefreshAdvertisingInfoAsyncTask extends AsyncTask<Void, Void, Void> {
        private RefreshAdvertisingInfoAsyncTask() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            MoPubIdentifier.this.refreshAdvertisingInfoBackgroundThread();
            boolean unused = MoPubIdentifier.this.mRefreshingAdvertisingInfo = false;
            return null;
        }
    }
}
