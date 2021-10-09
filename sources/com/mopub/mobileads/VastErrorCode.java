package com.mopub.mobileads;

import com.mopub.common.Preconditions;

public enum VastErrorCode {
    XML_PARSING_ERROR("100"),
    WRAPPER_TIMEOUT("301"),
    NO_ADS_VAST_RESPONSE("303"),
    GENERAL_LINEAR_AD_ERROR("400"),
    GENERAL_COMPANION_AD_ERROR("600"),
    UNDEFINED_ERROR("900");
    
    private final String mErrorCode;

    private VastErrorCode(String str) {
        Preconditions.checkNotNull(str, "errorCode cannot be null");
        this.mErrorCode = str;
    }

    /* access modifiers changed from: package-private */
    public String getErrorCode() {
        return this.mErrorCode;
    }
}
