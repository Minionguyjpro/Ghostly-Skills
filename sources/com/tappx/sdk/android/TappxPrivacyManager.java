package com.tappx.sdk.android;

import android.app.Activity;

public interface TappxPrivacyManager {
    void checkAndShowPrivacyDisclaimer(Activity activity);

    void checkAndShowPrivacyDisclaimer(Activity activity, Runnable runnable);

    void denyPersonalInfoConsent();

    void grantPersonalInfoConsent();

    void renewPrivacyConsent(Activity activity);

    void setAutoPrivacyDisclaimerEnabled(boolean z);

    void setGDPRConsent(String str);

    void setUSPrivacy(String str);
}
