package com.mopub.common.privacy;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Preconditions;
import com.mopub.common.SharedPreferencesHelper;
import java.util.Locale;

class PersonalInfoData implements ConsentData {
    private static final String AD_UNIT_ID_SP_KEY = "info/adunit";
    private static final String CACHED_LAST_AD_UNIT_ID_USED_FOR_INIT_SP_KEY = "info/cached_last_ad_unit_id_used_for_init";
    private static final String CONSENTED_PRIVACY_POLICY_VERSION_SP_KEY = "info/consented_privacy_policy_version";
    private static final String CONSENTED_VENDOR_LIST_IAB_FORMAT_SP_KEY = "info/consented_vendor_list_iab_format";
    private static final String CONSENTED_VENDOR_LIST_VERSION_SP_KEY = "info/consented_vendor_list_version";
    private static final String CONSENT_CHANGE_REASON_SP_KEY = "info/consent_change_reason";
    private static final String CONSENT_STATUS_BEFORE_DNT_SP_KEY = "info/consent_status_before_dnt";
    private static final String CONSENT_STATUS_SP_KEY = "info/consent_status";
    private static final String CURRENT_PRIVACY_POLICY_LINK_SP_KEY = "info/current_privacy_policy_link";
    private static final String CURRENT_PRIVACY_POLICY_VERSION_SP_KEY = "info/current_privacy_policy_version";
    private static final String CURRENT_VENDOR_LIST_IAB_FORMAT_SP_KEY = "info/current_vendor_list_iab_format";
    private static final String CURRENT_VENDOR_LIST_IAB_HASH_SP_KEY = "info/current_vendor_list_iab_hash";
    private static final String CURRENT_VENDOR_LIST_LINK_SP_KEY = "info/current_vendor_list_link";
    private static final String CURRENT_VENDOR_LIST_VERSION_SP_KEY = "info/current_vendor_list_version";
    private static final String EXTRAS_SP_KEY = "info/extras";
    private static final String FORCE_GDPR_APPLIES_SP_KEY = "info/force_gdpr_applies";
    private static final String GDPR_APPLIES_SP_KEY = "info/gdpr_applies";
    private static final String IS_WHITELISTED_SP_KEY = "info/is_whitelisted";
    private static final String LANGUAGE_MACRO_KEY = "%%LANGUAGE%%";
    private static final String LAST_CHANGED_MS_SP_KEY = "info/last_changed_ms";
    private static final String LAST_SUCCESSFULLY_SYNCED_CONSENT_STATUS_SP_KEY = "info/last_successfully_synced_consent_status";
    private static final String PERSONAL_INFO_DATA_SHARED_PREFS = "com.mopub.privacy";
    private static final String PERSONAL_INFO_PREFIX = "info/";
    private static final String REACQUIRE_CONSENT_SP_KEY = "info/reacquire_consent";
    private static final String UDID_SP_KEY = "info/udid";
    private String mAdUnitId = "";
    private final Context mAppContext;
    private String mCachedLastAdUnitIdUsedForInit;
    private String mConsentChangeReason;
    private ConsentStatus mConsentStatus = ConsentStatus.UNKNOWN;
    private ConsentStatus mConsentStatusBeforeDnt;
    private String mConsentedPrivacyPolicyVersion;
    private String mConsentedVendorListIabFormat;
    private String mConsentedVendorListVersion;
    private String mCurrentPrivacyPolicyLink;
    private String mCurrentPrivacyPolicyVersion;
    private String mCurrentVendorListIabFormat;
    private String mCurrentVendorListIabHash;
    private String mCurrentVendorListLink;
    private String mCurrentVendorListVersion;
    private String mExtras;
    private boolean mForceGdprApplies;
    private Boolean mGdprApplies;
    private boolean mIsWhitelisted;
    private String mLastChangedMs;
    private ConsentStatus mLastSuccessfullySyncedConsentStatus;
    private boolean mReacquireConsent;
    private String mUdid;

    PersonalInfoData(Context context) {
        Preconditions.checkNotNull(context);
        this.mAppContext = context.getApplicationContext();
        getStateFromDisk();
    }

    private void getStateFromDisk() {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.getSharedPreferences(this.mAppContext, PERSONAL_INFO_DATA_SHARED_PREFS);
        this.mAdUnitId = sharedPreferences.getString(AD_UNIT_ID_SP_KEY, "");
        this.mCachedLastAdUnitIdUsedForInit = sharedPreferences.getString(CACHED_LAST_AD_UNIT_ID_USED_FOR_INIT_SP_KEY, (String) null);
        this.mConsentStatus = ConsentStatus.fromString(sharedPreferences.getString(CONSENT_STATUS_SP_KEY, ConsentStatus.UNKNOWN.name()));
        String string = sharedPreferences.getString(LAST_SUCCESSFULLY_SYNCED_CONSENT_STATUS_SP_KEY, (String) null);
        if (TextUtils.isEmpty(string)) {
            this.mLastSuccessfullySyncedConsentStatus = null;
        } else {
            this.mLastSuccessfullySyncedConsentStatus = ConsentStatus.fromString(string);
        }
        this.mIsWhitelisted = sharedPreferences.getBoolean(IS_WHITELISTED_SP_KEY, false);
        this.mCurrentVendorListVersion = sharedPreferences.getString(CURRENT_VENDOR_LIST_VERSION_SP_KEY, (String) null);
        this.mCurrentVendorListLink = sharedPreferences.getString(CURRENT_VENDOR_LIST_LINK_SP_KEY, (String) null);
        this.mCurrentPrivacyPolicyVersion = sharedPreferences.getString(CURRENT_PRIVACY_POLICY_VERSION_SP_KEY, (String) null);
        this.mCurrentPrivacyPolicyLink = sharedPreferences.getString(CURRENT_PRIVACY_POLICY_LINK_SP_KEY, (String) null);
        this.mCurrentVendorListIabFormat = sharedPreferences.getString(CURRENT_VENDOR_LIST_IAB_FORMAT_SP_KEY, (String) null);
        this.mCurrentVendorListIabHash = sharedPreferences.getString(CURRENT_VENDOR_LIST_IAB_HASH_SP_KEY, (String) null);
        this.mConsentedVendorListVersion = sharedPreferences.getString(CONSENTED_VENDOR_LIST_VERSION_SP_KEY, (String) null);
        this.mConsentedPrivacyPolicyVersion = sharedPreferences.getString(CONSENTED_PRIVACY_POLICY_VERSION_SP_KEY, (String) null);
        this.mConsentedVendorListIabFormat = sharedPreferences.getString(CONSENTED_VENDOR_LIST_IAB_FORMAT_SP_KEY, (String) null);
        this.mExtras = sharedPreferences.getString(EXTRAS_SP_KEY, (String) null);
        this.mConsentChangeReason = sharedPreferences.getString(CONSENT_CHANGE_REASON_SP_KEY, (String) null);
        this.mReacquireConsent = sharedPreferences.getBoolean(REACQUIRE_CONSENT_SP_KEY, false);
        String string2 = sharedPreferences.getString(GDPR_APPLIES_SP_KEY, (String) null);
        if (TextUtils.isEmpty(string2)) {
            this.mGdprApplies = null;
        } else {
            this.mGdprApplies = Boolean.valueOf(Boolean.parseBoolean(string2));
        }
        this.mForceGdprApplies = sharedPreferences.getBoolean(FORCE_GDPR_APPLIES_SP_KEY, false);
        this.mUdid = sharedPreferences.getString(UDID_SP_KEY, (String) null);
        this.mLastChangedMs = sharedPreferences.getString(LAST_CHANGED_MS_SP_KEY, (String) null);
        String string3 = sharedPreferences.getString(CONSENT_STATUS_BEFORE_DNT_SP_KEY, (String) null);
        if (TextUtils.isEmpty(string3)) {
            this.mConsentStatusBeforeDnt = null;
        } else {
            this.mConsentStatusBeforeDnt = ConsentStatus.fromString(string3);
        }
    }

    /* access modifiers changed from: package-private */
    public void writeToDisk() {
        String str;
        String str2;
        SharedPreferences.Editor edit = SharedPreferencesHelper.getSharedPreferences(this.mAppContext, PERSONAL_INFO_DATA_SHARED_PREFS).edit();
        edit.putString(AD_UNIT_ID_SP_KEY, this.mAdUnitId);
        edit.putString(CACHED_LAST_AD_UNIT_ID_USED_FOR_INIT_SP_KEY, this.mCachedLastAdUnitIdUsedForInit);
        edit.putString(CONSENT_STATUS_SP_KEY, this.mConsentStatus.name());
        ConsentStatus consentStatus = this.mLastSuccessfullySyncedConsentStatus;
        String str3 = null;
        if (consentStatus == null) {
            str = null;
        } else {
            str = consentStatus.name();
        }
        edit.putString(LAST_SUCCESSFULLY_SYNCED_CONSENT_STATUS_SP_KEY, str);
        edit.putBoolean(IS_WHITELISTED_SP_KEY, this.mIsWhitelisted);
        edit.putString(CURRENT_VENDOR_LIST_VERSION_SP_KEY, this.mCurrentVendorListVersion);
        edit.putString(CURRENT_VENDOR_LIST_LINK_SP_KEY, this.mCurrentVendorListLink);
        edit.putString(CURRENT_PRIVACY_POLICY_VERSION_SP_KEY, this.mCurrentPrivacyPolicyVersion);
        edit.putString(CURRENT_PRIVACY_POLICY_LINK_SP_KEY, this.mCurrentPrivacyPolicyLink);
        edit.putString(CURRENT_VENDOR_LIST_IAB_FORMAT_SP_KEY, this.mCurrentVendorListIabFormat);
        edit.putString(CURRENT_VENDOR_LIST_IAB_HASH_SP_KEY, this.mCurrentVendorListIabHash);
        edit.putString(CONSENTED_VENDOR_LIST_VERSION_SP_KEY, this.mConsentedVendorListVersion);
        edit.putString(CONSENTED_PRIVACY_POLICY_VERSION_SP_KEY, this.mConsentedPrivacyPolicyVersion);
        edit.putString(CONSENTED_VENDOR_LIST_IAB_FORMAT_SP_KEY, this.mConsentedVendorListIabFormat);
        edit.putString(EXTRAS_SP_KEY, this.mExtras);
        edit.putString(CONSENT_CHANGE_REASON_SP_KEY, this.mConsentChangeReason);
        edit.putBoolean(REACQUIRE_CONSENT_SP_KEY, this.mReacquireConsent);
        Boolean bool = this.mGdprApplies;
        if (bool == null) {
            str2 = null;
        } else {
            str2 = bool.toString();
        }
        edit.putString(GDPR_APPLIES_SP_KEY, str2);
        edit.putBoolean(FORCE_GDPR_APPLIES_SP_KEY, this.mForceGdprApplies);
        edit.putString(UDID_SP_KEY, this.mUdid);
        edit.putString(LAST_CHANGED_MS_SP_KEY, this.mLastChangedMs);
        ConsentStatus consentStatus2 = this.mConsentStatusBeforeDnt;
        if (consentStatus2 != null) {
            str3 = consentStatus2.name();
        }
        edit.putString(CONSENT_STATUS_BEFORE_DNT_SP_KEY, str3);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    /* access modifiers changed from: package-private */
    public void setAdUnit(String str) {
        this.mAdUnitId = str;
    }

    /* access modifiers changed from: package-private */
    public String getCachedLastAdUnitIdUsedForInit() {
        return this.mCachedLastAdUnitIdUsedForInit;
    }

    /* access modifiers changed from: package-private */
    public void setCachedLastAdUnitIdUsedForInit(String str) {
        this.mCachedLastAdUnitIdUsedForInit = str;
    }

    /* access modifiers changed from: package-private */
    public String chooseAdUnit() {
        String str = this.mAdUnitId;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return this.mCachedLastAdUnitIdUsedForInit;
    }

    /* access modifiers changed from: package-private */
    public ConsentStatus getConsentStatus() {
        return this.mConsentStatus;
    }

    /* access modifiers changed from: package-private */
    public void setConsentStatus(ConsentStatus consentStatus) {
        this.mConsentStatus = consentStatus;
    }

    /* access modifiers changed from: package-private */
    public ConsentStatus getLastSuccessfullySyncedConsentStatus() {
        return this.mLastSuccessfullySyncedConsentStatus;
    }

    /* access modifiers changed from: package-private */
    public void setLastSuccessfullySyncedConsentStatus(ConsentStatus consentStatus) {
        this.mLastSuccessfullySyncedConsentStatus = consentStatus;
    }

    /* access modifiers changed from: package-private */
    public boolean isWhitelisted() {
        return this.mIsWhitelisted;
    }

    /* access modifiers changed from: package-private */
    public void setWhitelisted(boolean z) {
        this.mIsWhitelisted = z;
    }

    public String getCurrentVendorListVersion() {
        return this.mCurrentVendorListVersion;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentVendorListVersion(String str) {
        this.mCurrentVendorListVersion = str;
    }

    public String getCurrentVendorListLink() {
        return getCurrentVendorListLink((String) null);
    }

    public String getCurrentVendorListLink(String str) {
        return replaceLanguageMacro(this.mCurrentVendorListLink, this.mAppContext, str);
    }

    /* access modifiers changed from: package-private */
    public void setCurrentVendorListLink(String str) {
        this.mCurrentVendorListLink = str;
    }

    public String getCurrentPrivacyPolicyVersion() {
        return this.mCurrentPrivacyPolicyVersion;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentPrivacyPolicyVersion(String str) {
        this.mCurrentPrivacyPolicyVersion = str;
    }

    public String getCurrentPrivacyPolicyLink() {
        return getCurrentPrivacyPolicyLink((String) null);
    }

    public String getCurrentPrivacyPolicyLink(String str) {
        return replaceLanguageMacro(this.mCurrentPrivacyPolicyLink, this.mAppContext, str);
    }

    /* access modifiers changed from: package-private */
    public void setCurrentPrivacyPolicyLink(String str) {
        this.mCurrentPrivacyPolicyLink = str;
    }

    public String getCurrentVendorListIabFormat() {
        return this.mCurrentVendorListIabFormat;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentVendorListIabFormat(String str) {
        this.mCurrentVendorListIabFormat = str;
    }

    /* access modifiers changed from: package-private */
    public String getCurrentVendorListIabHash() {
        return this.mCurrentVendorListIabHash;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentVendorListIabHash(String str) {
        this.mCurrentVendorListIabHash = str;
    }

    public String getConsentedVendorListVersion() {
        return this.mConsentedVendorListVersion;
    }

    /* access modifiers changed from: package-private */
    public void setConsentedVendorListVersion(String str) {
        this.mConsentedVendorListVersion = str;
    }

    public String getConsentedPrivacyPolicyVersion() {
        return this.mConsentedPrivacyPolicyVersion;
    }

    /* access modifiers changed from: package-private */
    public void setConsentedPrivacyPolicyVersion(String str) {
        this.mConsentedPrivacyPolicyVersion = str;
    }

    public String getConsentedVendorListIabFormat() {
        return this.mConsentedVendorListIabFormat;
    }

    /* access modifiers changed from: package-private */
    public void setConsentedVendorListIabFormat(String str) {
        this.mConsentedVendorListIabFormat = str;
    }

    public String getExtras() {
        return this.mExtras;
    }

    public void setExtras(String str) {
        this.mExtras = str;
    }

    /* access modifiers changed from: package-private */
    public String getConsentChangeReason() {
        return this.mConsentChangeReason;
    }

    /* access modifiers changed from: package-private */
    public void setConsentChangeReason(String str) {
        this.mConsentChangeReason = str;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldReacquireConsent() {
        return this.mReacquireConsent;
    }

    /* access modifiers changed from: package-private */
    public void setShouldReacquireConsent(boolean z) {
        this.mReacquireConsent = z;
    }

    /* access modifiers changed from: package-private */
    public Boolean getGdprApplies() {
        return this.mGdprApplies;
    }

    /* access modifiers changed from: package-private */
    public void setGdprApplies(Boolean bool) {
        this.mGdprApplies = bool;
    }

    public boolean isForceGdprApplies() {
        return this.mForceGdprApplies;
    }

    /* access modifiers changed from: package-private */
    public void setForceGdprApplies(boolean z) {
        this.mForceGdprApplies = z;
    }

    /* access modifiers changed from: package-private */
    public String getUdid() {
        return this.mUdid;
    }

    /* access modifiers changed from: package-private */
    public void setUdid(String str) {
        this.mUdid = str;
    }

    /* access modifiers changed from: package-private */
    public String getLastChangedMs() {
        return this.mLastChangedMs;
    }

    /* access modifiers changed from: package-private */
    public void setLastChangedMs(String str) {
        this.mLastChangedMs = str;
    }

    /* access modifiers changed from: package-private */
    public ConsentStatus getConsentStatusBeforeDnt() {
        return this.mConsentStatusBeforeDnt;
    }

    /* access modifiers changed from: package-private */
    public void setConsentStatusBeforeDnt(ConsentStatus consentStatus) {
        this.mConsentStatusBeforeDnt = consentStatus;
    }

    static String replaceLanguageMacro(String str, Context context, String str2) {
        Preconditions.checkNotNull(context);
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.replaceAll(LANGUAGE_MACRO_KEY, validateLanguage(context, str2));
    }

    private static String validateLanguage(Context context, String str) {
        Preconditions.checkNotNull(context);
        for (String str2 : Locale.getISOLanguages()) {
            if (str2 != null && str2.equals(str)) {
                return str;
            }
        }
        return ClientMetadata.getCurrentLanguage(context);
    }
}
