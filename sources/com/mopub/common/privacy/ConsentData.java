package com.mopub.common.privacy;

public interface ConsentData {
    String getConsentedPrivacyPolicyVersion();

    String getConsentedVendorListIabFormat();

    String getConsentedVendorListVersion();

    String getCurrentPrivacyPolicyLink();

    String getCurrentPrivacyPolicyLink(String str);

    String getCurrentPrivacyPolicyVersion();

    String getCurrentVendorListIabFormat();

    String getCurrentVendorListLink();

    String getCurrentVendorListLink(String str);

    String getCurrentVendorListVersion();

    boolean isForceGdprApplies();
}
