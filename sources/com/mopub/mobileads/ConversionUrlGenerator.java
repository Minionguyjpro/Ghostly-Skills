package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.BaseUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;

class ConversionUrlGenerator extends BaseUrlGenerator {
    private static final String PACKAGE_NAME_KEY = "id";
    private static final String SESSION_TRACKER_KEY = "st";
    private String mConsentedPrivacyPolicyVersion;
    private String mConsentedVendorListVersion;
    private Context mContext;
    private String mCurrentConsentStatus;
    private boolean mForceGdprApplies;
    private Boolean mGdprApplies;
    private boolean mSt;

    ConversionUrlGenerator(Context context) {
        this.mContext = context;
    }

    public ConversionUrlGenerator withCurrentConsentStatus(String str) {
        this.mCurrentConsentStatus = str;
        return this;
    }

    public ConversionUrlGenerator withGdprApplies(Boolean bool) {
        this.mGdprApplies = bool;
        return this;
    }

    public ConversionUrlGenerator withForceGdprApplies(boolean z) {
        this.mForceGdprApplies = z;
        return this;
    }

    public ConversionUrlGenerator withConsentedVendorListVersion(String str) {
        this.mConsentedVendorListVersion = str;
        return this;
    }

    public ConversionUrlGenerator withConsentedPrivacyPolicyVersion(String str) {
        this.mConsentedPrivacyPolicyVersion = str;
        return this;
    }

    public ConversionUrlGenerator withSessionTracker(boolean z) {
        this.mSt = z;
        return this;
    }

    public String generateUrlString(String str) {
        ClientMetadata instance = ClientMetadata.getInstance(this.mContext);
        initUrlString(str, Constants.CONVERSION_TRACKING_HANDLER);
        setApiVersion("6");
        setAppVersion(instance.getAppVersion());
        appendAdvertisingInfoTemplates();
        addParam("id", this.mContext.getPackageName());
        if (this.mSt) {
            addParam(SESSION_TRACKER_KEY, (Boolean) true);
        }
        addParam("nv", "5.12.0");
        appendAppEngineInfo();
        appendWrapperVersion();
        addParam("current_consent_status", this.mCurrentConsentStatus);
        addParam("consented_vendor_list_version", this.mConsentedVendorListVersion);
        addParam("consented_privacy_policy_version", this.mConsentedPrivacyPolicyVersion);
        addParam("gdpr_applies", this.mGdprApplies);
        addParam("force_gdpr_applies", Boolean.valueOf(this.mForceGdprApplies));
        return getFinalUrlString();
    }
}
