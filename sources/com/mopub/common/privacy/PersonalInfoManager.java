package com.mopub.common.privacy;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.privacy.MoPubIdentifier;
import com.mopub.common.privacy.SyncRequest;
import com.mopub.common.util.ManifestUtils;
import com.mopub.mobileads.MoPubConversionTracker;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MultiAdResponse;
import com.mopub.network.Networking;
import com.mopub.volley.VolleyError;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PersonalInfoManager {
    private static final long MINIMUM_SYNC_DELAY = 300000;
    /* access modifiers changed from: private */
    public final Context mAppContext;
    private final ConsentDialogController mConsentDialogController;
    private final Set<ConsentStatusChangeListener> mConsentStatusChangeListeners;
    private final MoPubConversionTracker mConversionTracker;
    /* access modifiers changed from: private */
    public boolean mForceGdprAppliesChanged;
    /* access modifiers changed from: private */
    public boolean mForceGdprAppliesChangedSending;
    /* access modifiers changed from: private */
    public Long mLastSyncRequestTimeUptimeMs;
    private boolean mLegitimateInterestAllowed;
    /* access modifiers changed from: private */
    public final PersonalInfoData mPersonalInfoData;
    /* access modifiers changed from: private */
    public SdkInitializationListener mSdkInitializationListener;
    /* access modifiers changed from: private */
    public MultiAdResponse.ServerOverrideListener mServerOverrideListener;
    /* access modifiers changed from: private */
    public long mSyncDelayMs = MINIMUM_SYNC_DELAY;
    /* access modifiers changed from: private */
    public ConsentStatus mSyncRequestConsentStatus;
    /* access modifiers changed from: private */
    public boolean mSyncRequestInFlight;
    private final SyncRequest.Listener mSyncRequestListener;

    public PersonalInfoManager(Context context, String str, SdkInitializationListener sdkInitializationListener) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        this.mAppContext = context.getApplicationContext();
        this.mConsentStatusChangeListeners = Collections.synchronizedSet(new HashSet());
        this.mSyncRequestListener = new PersonalInfoSyncRequestListener();
        PersonalInfoServerOverrideListener personalInfoServerOverrideListener = new PersonalInfoServerOverrideListener();
        this.mServerOverrideListener = personalInfoServerOverrideListener;
        MultiAdResponse.setServerOverrideListener(personalInfoServerOverrideListener);
        this.mConsentDialogController = new ConsentDialogController(this.mAppContext);
        this.mPersonalInfoData = new PersonalInfoData(this.mAppContext);
        if (!TextUtils.isEmpty(str) && !str.equals(this.mPersonalInfoData.getCachedLastAdUnitIdUsedForInit())) {
            this.mPersonalInfoData.setAdUnit("");
            this.mPersonalInfoData.setCachedLastAdUnitIdUsedForInit(str);
            this.mPersonalInfoData.writeToDisk();
        }
        this.mConversionTracker = new MoPubConversionTracker(this.mAppContext);
        AnonymousClass1 r3 = new MoPubIdentifier.AdvertisingIdChangeListener() {
            public void onIdChanged(AdvertisingId advertisingId, AdvertisingId advertisingId2) {
                Preconditions.checkNotNull(advertisingId);
                Preconditions.checkNotNull(advertisingId2);
                if (advertisingId.isDoNotTrack() && advertisingId2.isDoNotTrack()) {
                    return;
                }
                if (!advertisingId.isDoNotTrack() && advertisingId2.isDoNotTrack()) {
                    PersonalInfoManager.this.attemptStateTransition(ConsentStatus.DNT, ConsentChangeReason.DENIED_BY_DNT_ON);
                    PersonalInfoManager.this.requestSync(true);
                } else if (!advertisingId.isDoNotTrack() || advertisingId2.isDoNotTrack()) {
                    if (!TextUtils.isEmpty(advertisingId2.mAdvertisingId) && !advertisingId2.getIfaWithPrefix().equals(PersonalInfoManager.this.mPersonalInfoData.getUdid()) && ConsentStatus.EXPLICIT_YES.equals(PersonalInfoManager.this.mPersonalInfoData.getConsentStatus())) {
                        PersonalInfoManager.this.mPersonalInfoData.setLastSuccessfullySyncedConsentStatus((ConsentStatus) null);
                        PersonalInfoManager.this.mPersonalInfoData.setLastChangedMs((String) null);
                        PersonalInfoManager.this.attemptStateTransition(ConsentStatus.UNKNOWN, ConsentChangeReason.IFA_CHANGED);
                    }
                } else if (ConsentStatus.EXPLICIT_NO.equals(PersonalInfoManager.this.mPersonalInfoData.getConsentStatusBeforeDnt())) {
                    PersonalInfoManager.this.attemptStateTransition(ConsentStatus.EXPLICIT_NO, ConsentChangeReason.DNT_OFF);
                } else {
                    PersonalInfoManager.this.attemptStateTransition(ConsentStatus.UNKNOWN, ConsentChangeReason.DNT_OFF);
                }
            }
        };
        this.mSdkInitializationListener = sdkInitializationListener;
        MoPubIdentifier moPubIdentifier = ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier();
        moPubIdentifier.setIdChangeListener(r3);
        moPubIdentifier.setInitializationListener(createInitializationListener());
    }

    public boolean shouldShowConsentDialog() {
        Boolean gdprApplies = gdprApplies();
        if (gdprApplies == null || !gdprApplies.booleanValue()) {
            return false;
        }
        if (this.mPersonalInfoData.shouldReacquireConsent()) {
            return true;
        }
        return this.mPersonalInfoData.getConsentStatus().equals(ConsentStatus.UNKNOWN);
    }

    public boolean isConsentDialogReady() {
        return this.mConsentDialogController.isReady();
    }

    public void loadConsentDialog(final ConsentDialogListener consentDialogListener) {
        MoPubLog.log(MoPubLog.ConsentLogEvent.LOAD_ATTEMPTED, new Object[0]);
        ManifestUtils.checkGdprActivitiesDeclared(this.mAppContext);
        if (!ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().isDoNotTrack()) {
            Boolean gdprApplies = gdprApplies();
            if (gdprApplies == null || gdprApplies.booleanValue()) {
                this.mConsentDialogController.loadConsentDialog(consentDialogListener, gdprApplies, this.mPersonalInfoData);
            } else if (consentDialogListener != null) {
                new Handler().post(new Runnable() {
                    public void run() {
                        MoPubLog.log(MoPubLog.ConsentLogEvent.LOAD_FAILED, Integer.valueOf(MoPubErrorCode.GDPR_DOES_NOT_APPLY.getIntCode()), MoPubErrorCode.GDPR_DOES_NOT_APPLY);
                        consentDialogListener.onConsentDialogLoadFailed(MoPubErrorCode.GDPR_DOES_NOT_APPLY);
                    }
                });
            }
        } else if (consentDialogListener != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    MoPubLog.log(MoPubLog.ConsentLogEvent.LOAD_FAILED, Integer.valueOf(MoPubErrorCode.DO_NOT_TRACK.getIntCode()), MoPubErrorCode.DO_NOT_TRACK);
                    consentDialogListener.onConsentDialogLoadFailed(MoPubErrorCode.DO_NOT_TRACK);
                }
            });
        }
    }

    public boolean showConsentDialog() {
        return this.mConsentDialogController.showConsentDialog();
    }

    public boolean canCollectPersonalInformation() {
        Boolean gdprApplies = gdprApplies();
        if (gdprApplies == null) {
            return false;
        }
        if (!gdprApplies.booleanValue()) {
            return true;
        }
        if (!getPersonalInfoConsentStatus().equals(ConsentStatus.EXPLICIT_YES) || ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().isDoNotTrack()) {
            return false;
        }
        return true;
    }

    public void setAllowLegitimateInterest(boolean z) {
        this.mLegitimateInterestAllowed = z;
    }

    public boolean shouldAllowLegitimateInterest() {
        return this.mLegitimateInterestAllowed;
    }

    public Boolean gdprApplies() {
        if (this.mPersonalInfoData.isForceGdprApplies()) {
            return true;
        }
        return this.mPersonalInfoData.getGdprApplies();
    }

    public void forceGdprApplies() {
        if (!this.mPersonalInfoData.isForceGdprApplies()) {
            boolean canCollectPersonalInformation = canCollectPersonalInformation();
            this.mPersonalInfoData.setForceGdprApplies(true);
            this.mForceGdprAppliesChanged = true;
            this.mPersonalInfoData.writeToDisk();
            boolean canCollectPersonalInformation2 = canCollectPersonalInformation();
            if (canCollectPersonalInformation != canCollectPersonalInformation2) {
                fireOnConsentStateChangeListeners(this.mPersonalInfoData.getConsentStatus(), this.mPersonalInfoData.getConsentStatus(), canCollectPersonalInformation2);
            }
            requestSync(true);
        }
    }

    public ConsentStatus getPersonalInfoConsentStatus() {
        return this.mPersonalInfoData.getConsentStatus();
    }

    public void grantConsent() {
        if (ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().isDoNotTrack()) {
            MoPubLog.log(MoPubLog.ConsentLogEvent.CUSTOM, "Cannot grant consent because Do Not Track is on.");
            return;
        }
        if (this.mPersonalInfoData.isWhitelisted()) {
            attemptStateTransition(ConsentStatus.EXPLICIT_YES, ConsentChangeReason.GRANTED_BY_WHITELISTED_PUB);
        } else {
            MoPubLog.log(MoPubLog.ConsentLogEvent.CUSTOM, "You do not have approval to use the grantConsent API. Please reach out to your account teams or support@mopub.com for more information.");
            attemptStateTransition(ConsentStatus.POTENTIAL_WHITELIST, ConsentChangeReason.GRANTED_BY_NOT_WHITELISTED_PUB);
        }
        requestSync(true);
    }

    public void revokeConsent() {
        if (ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().isDoNotTrack()) {
            MoPubLog.log(MoPubLog.ConsentLogEvent.CUSTOM, "Cannot revoke consent because Do Not Track is on.");
            return;
        }
        attemptStateTransition(ConsentStatus.EXPLICIT_NO, ConsentChangeReason.DENIED_BY_PUB);
        requestSync(true);
    }

    /* access modifiers changed from: package-private */
    public void changeConsentStateFromDialog(ConsentStatus consentStatus) {
        Preconditions.checkNotNull(consentStatus);
        int i = AnonymousClass6.$SwitchMap$com$mopub$common$privacy$ConsentStatus[consentStatus.ordinal()];
        if (i == 1) {
            attemptStateTransition(consentStatus, ConsentChangeReason.GRANTED_BY_USER);
            requestSync(true);
        } else if (i != 2) {
            MoPubLog.ConsentLogEvent consentLogEvent = MoPubLog.ConsentLogEvent.CUSTOM;
            MoPubLog.log(consentLogEvent, "Invalid consent status: " + consentStatus + ". This is a bug with the use of changeConsentStateFromDialog.");
        } else {
            attemptStateTransition(consentStatus, ConsentChangeReason.DENIED_BY_USER);
            requestSync(true);
        }
    }

    /* renamed from: com.mopub.common.privacy.PersonalInfoManager$6  reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$common$privacy$ConsentStatus;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.mopub.common.privacy.ConsentStatus[] r0 = com.mopub.common.privacy.ConsentStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$common$privacy$ConsentStatus = r0
                com.mopub.common.privacy.ConsentStatus r1 = com.mopub.common.privacy.ConsentStatus.EXPLICIT_YES     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$common$privacy$ConsentStatus     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.common.privacy.ConsentStatus r1 = com.mopub.common.privacy.ConsentStatus.EXPLICIT_NO     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.privacy.PersonalInfoManager.AnonymousClass6.<clinit>():void");
        }
    }

    public void subscribeConsentStatusChangeListener(ConsentStatusChangeListener consentStatusChangeListener) {
        if (consentStatusChangeListener != null) {
            this.mConsentStatusChangeListeners.add(consentStatusChangeListener);
        }
    }

    public void unsubscribeConsentStatusChangeListener(ConsentStatusChangeListener consentStatusChangeListener) {
        this.mConsentStatusChangeListeners.remove(consentStatusChangeListener);
    }

    static boolean shouldMakeSyncRequest(boolean z, Boolean bool, boolean z2, Long l, long j, String str, boolean z3) {
        if (z) {
            return false;
        }
        if (bool == null) {
            return true;
        }
        if (!bool.booleanValue()) {
            return false;
        }
        if (z2) {
            return true;
        }
        if (z3 && TextUtils.isEmpty(str)) {
            return false;
        }
        if (l != null && SystemClock.uptimeMillis() - l.longValue() <= j) {
            return false;
        }
        return true;
    }

    public void requestSync(boolean z) {
        if (MoPub.isSdkInitialized()) {
            if (shouldMakeSyncRequest(this.mSyncRequestInFlight, gdprApplies(), z, this.mLastSyncRequestTimeUptimeMs, this.mSyncDelayMs, this.mPersonalInfoData.getUdid(), ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().isDoNotTrack())) {
                requestSync();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void requestSync() {
        MoPubLog.log(MoPubLog.ConsentLogEvent.SYNC_ATTEMPTED, new Object[0]);
        this.mSyncRequestConsentStatus = this.mPersonalInfoData.getConsentStatus();
        this.mSyncRequestInFlight = true;
        this.mLastSyncRequestTimeUptimeMs = Long.valueOf(SystemClock.uptimeMillis());
        SyncUrlGenerator syncUrlGenerator = new SyncUrlGenerator(this.mAppContext, this.mSyncRequestConsentStatus.getValue());
        syncUrlGenerator.withAdUnitId(this.mPersonalInfoData.chooseAdUnit()).withUdid(this.mPersonalInfoData.getUdid()).withLastChangedMs(this.mPersonalInfoData.getLastChangedMs()).withLastConsentStatus(this.mPersonalInfoData.getLastSuccessfullySyncedConsentStatus()).withConsentChangeReason(this.mPersonalInfoData.getConsentChangeReason()).withConsentedVendorListVersion(this.mPersonalInfoData.getConsentedVendorListVersion()).withConsentedPrivacyPolicyVersion(this.mPersonalInfoData.getConsentedPrivacyPolicyVersion()).withCachedVendorListIabHash(this.mPersonalInfoData.getCurrentVendorListIabHash()).withExtras(this.mPersonalInfoData.getExtras()).withGdprApplies(gdprApplies()).withForceGdprApplies(this.mPersonalInfoData.isForceGdprApplies());
        if (this.mForceGdprAppliesChanged) {
            this.mForceGdprAppliesChangedSending = true;
            syncUrlGenerator.withForceGdprAppliesChanged(true);
        }
        Networking.getRequestQueue(this.mAppContext).add(new SyncRequest(this.mAppContext, syncUrlGenerator.generateUrlString(Constants.HOST), this.mSyncRequestListener));
    }

    public ConsentData getConsentData() {
        return new PersonalInfoData(this.mAppContext);
    }

    /* access modifiers changed from: private */
    public void attemptStateTransition(ConsentStatus consentStatus, ConsentChangeReason consentChangeReason) {
        attemptStateTransition(consentStatus, consentChangeReason.getReason());
    }

    /* access modifiers changed from: package-private */
    public void attemptStateTransition(ConsentStatus consentStatus, String str) {
        Preconditions.checkNotNull(consentStatus);
        Preconditions.checkNotNull(str);
        ConsentStatus consentStatus2 = this.mPersonalInfoData.getConsentStatus();
        if (this.mPersonalInfoData.shouldReacquireConsent() || !consentStatus2.equals(consentStatus)) {
            PersonalInfoData personalInfoData = this.mPersonalInfoData;
            personalInfoData.setLastChangedMs("" + Calendar.getInstance().getTimeInMillis());
            this.mPersonalInfoData.setConsentChangeReason(str);
            this.mPersonalInfoData.setConsentStatus(consentStatus);
            if (shouldSetConsentedVersions(consentStatus2, consentStatus)) {
                PersonalInfoData personalInfoData2 = this.mPersonalInfoData;
                personalInfoData2.setConsentedPrivacyPolicyVersion(personalInfoData2.getCurrentPrivacyPolicyVersion());
                PersonalInfoData personalInfoData3 = this.mPersonalInfoData;
                personalInfoData3.setConsentedVendorListVersion(personalInfoData3.getCurrentVendorListVersion());
                PersonalInfoData personalInfoData4 = this.mPersonalInfoData;
                personalInfoData4.setConsentedVendorListIabFormat(personalInfoData4.getCurrentVendorListIabFormat());
            }
            if (ConsentStatus.DNT.equals(consentStatus) || ConsentStatus.UNKNOWN.equals(consentStatus)) {
                this.mPersonalInfoData.setConsentedPrivacyPolicyVersion((String) null);
                this.mPersonalInfoData.setConsentedVendorListVersion((String) null);
                this.mPersonalInfoData.setConsentedVendorListIabFormat((String) null);
            }
            if (ConsentStatus.EXPLICIT_YES.equals(consentStatus)) {
                this.mPersonalInfoData.setUdid(ClientMetadata.getInstance(this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().getIfaWithPrefix());
            }
            if (ConsentStatus.DNT.equals(consentStatus)) {
                this.mPersonalInfoData.setConsentStatusBeforeDnt(consentStatus2);
            }
            this.mPersonalInfoData.setShouldReacquireConsent(false);
            this.mPersonalInfoData.writeToDisk();
            boolean canCollectPersonalInformation = canCollectPersonalInformation();
            if (canCollectPersonalInformation) {
                ClientMetadata.getInstance(this.mAppContext).repopulateCountryData();
                if (this.mConversionTracker.shouldTrack()) {
                    this.mConversionTracker.reportAppOpen(false);
                }
            }
            MoPubLog.log(MoPubLog.ConsentLogEvent.UPDATED, consentStatus2, consentStatus, Boolean.valueOf(canCollectPersonalInformation()), str);
            fireOnConsentStateChangeListeners(consentStatus2, consentStatus, canCollectPersonalInformation);
            return;
        }
        MoPubLog.ConsentLogEvent consentLogEvent = MoPubLog.ConsentLogEvent.CUSTOM;
        MoPubLog.log(consentLogEvent, "Consent status is already " + consentStatus2 + ". Not doing a state transition.");
    }

    private static boolean shouldSetConsentedVersions(ConsentStatus consentStatus, ConsentStatus consentStatus2) {
        if (ConsentStatus.EXPLICIT_NO.equals(consentStatus2) || ConsentStatus.POTENTIAL_WHITELIST.equals(consentStatus2)) {
            return true;
        }
        if (ConsentStatus.POTENTIAL_WHITELIST.equals(consentStatus) || !ConsentStatus.EXPLICIT_YES.equals(consentStatus2)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void fireOnConsentStateChangeListeners(ConsentStatus consentStatus, ConsentStatus consentStatus2, boolean z) {
        synchronized (this.mConsentStatusChangeListeners) {
            for (final ConsentStatusChangeListener next : this.mConsentStatusChangeListeners) {
                final ConsentStatus consentStatus3 = consentStatus;
                final ConsentStatus consentStatus4 = consentStatus2;
                final boolean z2 = z;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        next.onConsentStateChange(consentStatus3, consentStatus4, z2);
                    }
                });
            }
        }
    }

    private SdkInitializationListener createInitializationListener() {
        return new SdkInitializationListener() {
            public void onInitializationFinished() {
                MoPubLog.log(MoPubLog.ConsentLogEvent.CUSTOM, "MoPubIdentifier initialized.");
                if (PersonalInfoManager.shouldMakeSyncRequest(PersonalInfoManager.this.mSyncRequestInFlight, PersonalInfoManager.this.gdprApplies(), false, PersonalInfoManager.this.mLastSyncRequestTimeUptimeMs, PersonalInfoManager.this.mSyncDelayMs, PersonalInfoManager.this.mPersonalInfoData.getUdid(), ClientMetadata.getInstance(PersonalInfoManager.this.mAppContext).getMoPubIdentifier().getAdvertisingInfo().isDoNotTrack())) {
                    PersonalInfoManager.this.requestSync();
                } else if (PersonalInfoManager.this.mSdkInitializationListener != null) {
                    PersonalInfoManager.this.mSdkInitializationListener.onInitializationFinished();
                    SdkInitializationListener unused = PersonalInfoManager.this.mSdkInitializationListener = null;
                }
                new MoPubConversionTracker(PersonalInfoManager.this.mAppContext).reportAppOpen(true);
            }
        };
    }

    private class PersonalInfoSyncRequestListener implements SyncRequest.Listener {
        private PersonalInfoSyncRequestListener() {
        }

        public void onSuccess(SyncResponse syncResponse) {
            MoPubLog.log(MoPubLog.ConsentLogEvent.SYNC_COMPLETED, new Object[0]);
            boolean canCollectPersonalInformation = PersonalInfoManager.this.canCollectPersonalInformation();
            if (PersonalInfoManager.this.mPersonalInfoData.getGdprApplies() == null) {
                PersonalInfoManager.this.mPersonalInfoData.setGdprApplies(Boolean.valueOf(syncResponse.isGdprRegion()));
            }
            if (syncResponse.isForceGdprApplies()) {
                boolean unused = PersonalInfoManager.this.mForceGdprAppliesChanged = true;
                PersonalInfoManager.this.mPersonalInfoData.setForceGdprApplies(true);
                boolean canCollectPersonalInformation2 = PersonalInfoManager.this.canCollectPersonalInformation();
                if (canCollectPersonalInformation != canCollectPersonalInformation2) {
                    PersonalInfoManager personalInfoManager = PersonalInfoManager.this;
                    personalInfoManager.fireOnConsentStateChangeListeners(personalInfoManager.mPersonalInfoData.getConsentStatus(), PersonalInfoManager.this.mPersonalInfoData.getConsentStatus(), canCollectPersonalInformation2);
                }
            }
            String cachedLastAdUnitIdUsedForInit = PersonalInfoManager.this.mPersonalInfoData.getCachedLastAdUnitIdUsedForInit();
            if (!TextUtils.isEmpty(cachedLastAdUnitIdUsedForInit) && PersonalInfoManager.this.mPersonalInfoData.getAdUnitId().isEmpty()) {
                PersonalInfoManager.this.mPersonalInfoData.setAdUnit(cachedLastAdUnitIdUsedForInit);
            }
            PersonalInfoManager.this.mPersonalInfoData.setLastSuccessfullySyncedConsentStatus(PersonalInfoManager.this.mSyncRequestConsentStatus);
            PersonalInfoManager.this.mPersonalInfoData.setWhitelisted(syncResponse.isWhitelisted());
            PersonalInfoManager.this.mPersonalInfoData.setCurrentVendorListVersion(syncResponse.getCurrentVendorListVersion());
            PersonalInfoManager.this.mPersonalInfoData.setCurrentVendorListLink(syncResponse.getCurrentVendorListLink());
            PersonalInfoManager.this.mPersonalInfoData.setCurrentPrivacyPolicyVersion(syncResponse.getCurrentPrivacyPolicyVersion());
            PersonalInfoManager.this.mPersonalInfoData.setCurrentPrivacyPolicyLink(syncResponse.getCurrentPrivacyPolicyLink());
            String currentVendorListIabHash = syncResponse.getCurrentVendorListIabHash();
            String currentVendorListIabFormat = syncResponse.getCurrentVendorListIabFormat();
            if (!TextUtils.isEmpty(currentVendorListIabHash) && !currentVendorListIabHash.equals(PersonalInfoManager.this.mPersonalInfoData.getCurrentVendorListIabHash()) && !TextUtils.isEmpty(currentVendorListIabFormat)) {
                PersonalInfoManager.this.mPersonalInfoData.setCurrentVendorListIabFormat(currentVendorListIabFormat);
                PersonalInfoManager.this.mPersonalInfoData.setCurrentVendorListIabHash(currentVendorListIabHash);
            }
            String extras = syncResponse.getExtras();
            if (!TextUtils.isEmpty(extras)) {
                PersonalInfoManager.this.mPersonalInfoData.setExtras(extras);
            }
            String consentChangeReason = syncResponse.getConsentChangeReason();
            if (syncResponse.isForceExplicitNo()) {
                PersonalInfoManager.this.mServerOverrideListener.onForceExplicitNo(consentChangeReason);
            } else if (syncResponse.isInvalidateConsent()) {
                PersonalInfoManager.this.mServerOverrideListener.onInvalidateConsent(consentChangeReason);
            } else if (syncResponse.isReacquireConsent()) {
                PersonalInfoManager.this.mServerOverrideListener.onReacquireConsent(consentChangeReason);
            }
            String callAgainAfterSecs = syncResponse.getCallAgainAfterSecs();
            if (!TextUtils.isEmpty(callAgainAfterSecs)) {
                try {
                    long parseLong = Long.parseLong(callAgainAfterSecs);
                    if (parseLong > 0) {
                        long unused2 = PersonalInfoManager.this.mSyncDelayMs = parseLong * 1000;
                    } else {
                        MoPubLog.ConsentLogEvent consentLogEvent = MoPubLog.ConsentLogEvent.CUSTOM;
                        MoPubLog.log(consentLogEvent, "callAgainAfterSecs is not positive: " + callAgainAfterSecs);
                    }
                } catch (NumberFormatException unused3) {
                    MoPubLog.log(MoPubLog.ConsentLogEvent.CUSTOM, "Unable to parse callAgainAfterSecs. Ignoring value");
                }
            }
            if (!ConsentStatus.EXPLICIT_YES.equals(PersonalInfoManager.this.mSyncRequestConsentStatus)) {
                PersonalInfoManager.this.mPersonalInfoData.setUdid((String) null);
            }
            if (PersonalInfoManager.this.mForceGdprAppliesChangedSending) {
                boolean unused4 = PersonalInfoManager.this.mForceGdprAppliesChanged = false;
                boolean unused5 = PersonalInfoManager.this.mForceGdprAppliesChangedSending = false;
            }
            PersonalInfoManager.this.mPersonalInfoData.writeToDisk();
            boolean unused6 = PersonalInfoManager.this.mSyncRequestInFlight = false;
            if (ConsentStatus.POTENTIAL_WHITELIST.equals(PersonalInfoManager.this.mSyncRequestConsentStatus) && PersonalInfoManager.this.mPersonalInfoData.isWhitelisted()) {
                PersonalInfoManager.this.attemptStateTransition(ConsentStatus.EXPLICIT_YES, ConsentChangeReason.GRANTED_BY_WHITELISTED_PUB);
                PersonalInfoManager.this.requestSync(true);
            }
            if (PersonalInfoManager.this.mSdkInitializationListener != null) {
                PersonalInfoManager.this.mSdkInitializationListener.onInitializationFinished();
                SdkInitializationListener unused7 = PersonalInfoManager.this.mSdkInitializationListener = null;
            }
        }

        public void onErrorResponse(VolleyError volleyError) {
            int i;
            String str;
            boolean z = volleyError instanceof MoPubNetworkError;
            if (z) {
                i = ((MoPubNetworkError) volleyError).getReason().ordinal();
            } else {
                i = MoPubErrorCode.UNSPECIFIED.getIntCode();
            }
            if (z) {
                str = volleyError.getMessage();
            } else {
                str = MoPubErrorCode.UNSPECIFIED.toString();
            }
            MoPubLog.log(MoPubLog.ConsentLogEvent.SYNC_FAILED, Integer.valueOf(i), str);
            boolean unused = PersonalInfoManager.this.mSyncRequestInFlight = false;
            if (PersonalInfoManager.this.mSdkInitializationListener != null) {
                MoPubLog.log(MoPubLog.ConsentLogEvent.CUSTOM, "Personal Info Manager initialization finished but ran into errors.");
                PersonalInfoManager.this.mSdkInitializationListener.onInitializationFinished();
                SdkInitializationListener unused2 = PersonalInfoManager.this.mSdkInitializationListener = null;
            }
        }
    }

    private class PersonalInfoServerOverrideListener implements MultiAdResponse.ServerOverrideListener {
        private PersonalInfoServerOverrideListener() {
        }

        public void onForceExplicitNo(String str) {
            if (TextUtils.isEmpty(str)) {
                PersonalInfoManager.this.attemptStateTransition(ConsentStatus.EXPLICIT_NO, ConsentChangeReason.REVOKED_BY_SERVER);
            } else {
                PersonalInfoManager.this.attemptStateTransition(ConsentStatus.EXPLICIT_NO, str);
            }
        }

        public void onInvalidateConsent(String str) {
            if (TextUtils.isEmpty(str)) {
                PersonalInfoManager.this.attemptStateTransition(ConsentStatus.UNKNOWN, ConsentChangeReason.REACQUIRE_BY_SERVER);
            } else {
                PersonalInfoManager.this.attemptStateTransition(ConsentStatus.UNKNOWN, str);
            }
        }

        public void onReacquireConsent(String str) {
            if (!TextUtils.isEmpty(str)) {
                PersonalInfoManager.this.mPersonalInfoData.setConsentChangeReason(str);
            }
            PersonalInfoManager.this.mPersonalInfoData.setShouldReacquireConsent(true);
            PersonalInfoManager.this.mPersonalInfoData.writeToDisk();
        }

        public void onForceGdprApplies() {
            PersonalInfoManager.this.forceGdprApplies();
        }

        public void onRequestSuccess(String str) {
            if (TextUtils.isEmpty(PersonalInfoManager.this.mPersonalInfoData.getAdUnitId()) && !TextUtils.isEmpty(str)) {
                PersonalInfoManager.this.mPersonalInfoData.setAdUnit(str);
                PersonalInfoManager.this.mPersonalInfoData.writeToDisk();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public PersonalInfoData getPersonalInfoData() {
        return this.mPersonalInfoData;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public MultiAdResponse.ServerOverrideListener getServerOverrideListener() {
        return this.mServerOverrideListener;
    }
}
