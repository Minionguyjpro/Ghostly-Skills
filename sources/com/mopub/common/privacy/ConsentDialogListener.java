package com.mopub.common.privacy;

import com.mopub.mobileads.MoPubErrorCode;

public interface ConsentDialogListener {
    void onConsentDialogLoadFailed(MoPubErrorCode moPubErrorCode);

    void onConsentDialogLoaded();
}
