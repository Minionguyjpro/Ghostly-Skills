package com.mopub.common.privacy;

import android.content.Context;
import com.mopub.common.BaseUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;

public class ConsentDialogUrlGenerator extends BaseUrlGenerator {
    private static final String LANGUAGE_KEY = "language";
    private final String mAdUnitId;
    private String mConsentedPrivacyPolicyVersion;
    private String mConsentedVendorListVersion;
    private final Context mContext;
    private final String mCurrentConsentStatus;
    private boolean mForceGdprApplies;
    private Boolean mGdprApplies;

    ConsentDialogUrlGenerator(Context context, String str, String str2) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        this.mContext = context.getApplicationContext();
        this.mAdUnitId = str;
        this.mCurrentConsentStatus = str2;
    }

    /* access modifiers changed from: protected */
    public ConsentDialogUrlGenerator withGdprApplies(Boolean bool) {
        this.mGdprApplies = bool;
        return this;
    }

    /* access modifiers changed from: protected */
    public ConsentDialogUrlGenerator withForceGdprApplies(boolean z) {
        this.mForceGdprApplies = z;
        return this;
    }

    /* access modifiers changed from: protected */
    public ConsentDialogUrlGenerator withConsentedVendorListVersion(String str) {
        this.mConsentedVendorListVersion = str;
        return this;
    }

    /* access modifiers changed from: protected */
    public ConsentDialogUrlGenerator withConsentedPrivacyPolicyVersion(String str) {
        this.mConsentedPrivacyPolicyVersion = str;
        return this;
    }

    public String generateUrlString(String str) {
        initUrlString(str, Constants.GDPR_CONSENT_HANDLER);
        addParam("id", this.mAdUnitId);
        addParam("current_consent_status", this.mCurrentConsentStatus);
        addParam("nv", "5.12.0");
        appendAppEngineInfo();
        appendWrapperVersion();
        addParam(LANGUAGE_KEY, ClientMetadata.getCurrentLanguage(this.mContext));
        addParam("gdpr_applies", this.mGdprApplies);
        addParam("force_gdpr_applies", Boolean.valueOf(this.mForceGdprApplies));
        addParam("consented_vendor_list_version", this.mConsentedVendorListVersion);
        addParam("consented_privacy_policy_version", this.mConsentedPrivacyPolicyVersion);
        addParam("bundle", ClientMetadata.getInstance(this.mContext).getAppPackageName());
        return getFinalUrlString();
    }
}
