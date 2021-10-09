package com.mopub.common.privacy;

import com.mopub.common.Preconditions;

public class SyncResponse {
    private final String mCallAgainAfterSecs;
    private final String mConsentChangeReason;
    private final String mCurrentPrivacyPolicyLink;
    private final String mCurrentPrivacyPolicyVersion;
    private final String mCurrentVendorListIabFormat;
    private final String mCurrentVendorListIabHash;
    private final String mCurrentVendorListLink;
    private final String mCurrentVendorListVersion;
    private final String mExtras;
    private final boolean mForceExplicitNo;
    private final boolean mForceGdprApplies;
    private final boolean mInvalidateConsent;
    private final boolean mIsGdprRegion;
    private final boolean mIsWhitelisted;
    private final boolean mReacquireConsent;

    public boolean isGdprRegion() {
        return this.mIsGdprRegion;
    }

    public boolean isForceExplicitNo() {
        return this.mForceExplicitNo;
    }

    public boolean isInvalidateConsent() {
        return this.mInvalidateConsent;
    }

    public boolean isReacquireConsent() {
        return this.mReacquireConsent;
    }

    public boolean isWhitelisted() {
        return this.mIsWhitelisted;
    }

    public boolean isForceGdprApplies() {
        return this.mForceGdprApplies;
    }

    public String getCurrentVendorListVersion() {
        return this.mCurrentVendorListVersion;
    }

    public String getCurrentVendorListLink() {
        return this.mCurrentVendorListLink;
    }

    public String getCurrentPrivacyPolicyVersion() {
        return this.mCurrentPrivacyPolicyVersion;
    }

    public String getCurrentPrivacyPolicyLink() {
        return this.mCurrentPrivacyPolicyLink;
    }

    public String getCurrentVendorListIabFormat() {
        return this.mCurrentVendorListIabFormat;
    }

    public String getCurrentVendorListIabHash() {
        return this.mCurrentVendorListIabHash;
    }

    public String getCallAgainAfterSecs() {
        return this.mCallAgainAfterSecs;
    }

    /* access modifiers changed from: package-private */
    public String getExtras() {
        return this.mExtras;
    }

    public String getConsentChangeReason() {
        return this.mConsentChangeReason;
    }

    private SyncResponse(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str5);
        Preconditions.checkNotNull(str7);
        Preconditions.checkNotNull(str8);
        Preconditions.checkNotNull(str9);
        Preconditions.checkNotNull(str10);
        Preconditions.checkNotNull(str12);
        String str16 = str;
        this.mIsGdprRegion = !"0".equals(str);
        String str17 = str2;
        this.mForceExplicitNo = "1".equals(str2);
        String str18 = str3;
        this.mInvalidateConsent = "1".equals(str3);
        String str19 = str4;
        this.mReacquireConsent = "1".equals(str4);
        String str20 = str5;
        this.mIsWhitelisted = "1".equals(str5);
        String str21 = str6;
        this.mForceGdprApplies = "1".equals(str6);
        this.mCurrentVendorListVersion = str7;
        this.mCurrentVendorListLink = str8;
        this.mCurrentPrivacyPolicyVersion = str9;
        this.mCurrentPrivacyPolicyLink = str10;
        this.mCurrentVendorListIabFormat = str11;
        this.mCurrentVendorListIabHash = str12;
        this.mCallAgainAfterSecs = str13;
        this.mExtras = str14;
        this.mConsentChangeReason = str15;
    }

    public static class Builder {
        private String callAgainAfterSecs;
        private String consentChangeReason;
        private String currentPrivacyPolicyLink;
        private String currentPrivacyPolicyVersion;
        private String currentVendorListIabFormat;
        private String currentVendorListIabHash;
        private String currentVendorListLink;
        private String currentVendorListVersion;
        private String extras;
        private String forceExplicitNo;
        private String forceGdprApplies;
        private String invalidateConsent;
        private String isGdprRegion;
        private String isWhitelisted;
        private String reacquireConsent;

        public Builder setIsGdprRegion(String str) {
            this.isGdprRegion = str;
            return this;
        }

        public Builder setForceExplicitNo(String str) {
            this.forceExplicitNo = str;
            return this;
        }

        public Builder setInvalidateConsent(String str) {
            this.invalidateConsent = str;
            return this;
        }

        public Builder setReacquireConsent(String str) {
            this.reacquireConsent = str;
            return this;
        }

        public Builder setIsWhitelisted(String str) {
            this.isWhitelisted = str;
            return this;
        }

        public Builder setForceGdprApplies(String str) {
            this.forceGdprApplies = str;
            return this;
        }

        public Builder setCurrentVendorListVersion(String str) {
            this.currentVendorListVersion = str;
            return this;
        }

        public Builder setCurrentVendorListLink(String str) {
            this.currentVendorListLink = str;
            return this;
        }

        public Builder setCurrentPrivacyPolicyVersion(String str) {
            this.currentPrivacyPolicyVersion = str;
            return this;
        }

        public Builder setCurrentPrivacyPolicyLink(String str) {
            this.currentPrivacyPolicyLink = str;
            return this;
        }

        public Builder setCurrentVendorListIabFormat(String str) {
            this.currentVendorListIabFormat = str;
            return this;
        }

        public Builder setCurrentVendorListIabHash(String str) {
            this.currentVendorListIabHash = str;
            return this;
        }

        public Builder setCallAgainAfterSecs(String str) {
            this.callAgainAfterSecs = str;
            return this;
        }

        public Builder setExtras(String str) {
            this.extras = str;
            return this;
        }

        public Builder setConsentChangeReason(String str) {
            this.consentChangeReason = str;
            return this;
        }

        public SyncResponse build() {
            return new SyncResponse(this.isGdprRegion, this.forceExplicitNo, this.invalidateConsent, this.reacquireConsent, this.isWhitelisted, this.forceGdprApplies, this.currentVendorListVersion, this.currentVendorListLink, this.currentPrivacyPolicyVersion, this.currentPrivacyPolicyLink, this.currentVendorListIabFormat, this.currentVendorListIabHash, this.callAgainAfterSecs, this.extras, this.consentChangeReason);
        }
    }
}
