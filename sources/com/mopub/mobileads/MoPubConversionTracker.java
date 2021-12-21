package com.mopub.mobileads;

import android.content.Context;
import android.content.SharedPreferences;
import com.mopub.common.Constants;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.SharedPreferencesHelper;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.privacy.ConsentData;
import com.mopub.common.privacy.PersonalInfoManager;
import com.mopub.network.TrackingRequest;
import com.mopub.volley.VolleyError;

public class MoPubConversionTracker {
    private static final String WANT_TO_TRACK = " wantToTrack";
    private final Context mContext;
    /* access modifiers changed from: private */
    public final String mIsTrackedKey;
    /* access modifiers changed from: private */
    public SharedPreferences mSharedPreferences = SharedPreferencesHelper.getSharedPreferences(this.mContext);
    /* access modifiers changed from: private */
    public final String mWantToTrack;

    public MoPubConversionTracker(Context context) {
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        String packageName = applicationContext.getPackageName();
        this.mWantToTrack = packageName + WANT_TO_TRACK;
        this.mIsTrackedKey = packageName + " tracked";
    }

    public void reportAppOpen() {
        reportAppOpen(false);
    }

    public void reportAppOpen(final boolean z) {
        PersonalInfoManager personalInformationManager = MoPub.getPersonalInformationManager();
        if (personalInformationManager == null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Cannot report app open until initialization is done");
        } else if (!z && isAlreadyTracked()) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Conversion already tracked");
        } else if (z || MoPub.canCollectPersonalInformation()) {
            ConsentData consentData = personalInformationManager.getConsentData();
            TrackingRequest.makeTrackingHttpRequest(new ConversionUrlGenerator(this.mContext).withGdprApplies(personalInformationManager.gdprApplies()).withForceGdprApplies(consentData.isForceGdprApplies()).withCurrentConsentStatus(personalInformationManager.getPersonalInfoConsentStatus().getValue()).withConsentedPrivacyPolicyVersion(consentData.getConsentedPrivacyPolicyVersion()).withConsentedVendorListVersion(consentData.getConsentedVendorListVersion()).withSessionTracker(z).generateUrlString(Constants.HOST), this.mContext, (TrackingRequest.Listener) new TrackingRequest.Listener() {
                public void onErrorResponse(VolleyError volleyError) {
                }

                public void onResponse(String str) {
                    if (!z) {
                        MoPubConversionTracker.this.mSharedPreferences.edit().putBoolean(MoPubConversionTracker.this.mIsTrackedKey, true).putBoolean(MoPubConversionTracker.this.mWantToTrack, false).apply();
                    }
                }
            });
        } else {
            this.mSharedPreferences.edit().putBoolean(this.mWantToTrack, true).apply();
        }
    }

    public boolean shouldTrack() {
        PersonalInfoManager personalInformationManager = MoPub.getPersonalInformationManager();
        if (personalInformationManager != null && personalInformationManager.canCollectPersonalInformation() && this.mSharedPreferences.getBoolean(this.mWantToTrack, false)) {
            return true;
        }
        return false;
    }

    private boolean isAlreadyTracked() {
        return this.mSharedPreferences.getBoolean(this.mIsTrackedKey, false);
    }
}
