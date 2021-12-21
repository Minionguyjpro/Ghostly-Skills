package com.mopub.common;

import android.text.TextUtils;
import com.mopub.common.logging.MoPubLog;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SdkConfiguration {
    private final String mAdUnitId;
    private final Set<String> mAdapterConfigurationClasses;
    private final boolean mLegitimateInterestAllowed;
    private final MoPubLog.LogLevel mLogLevel;
    private final Map<String, Map<String, String>> mMediatedNetworkConfigurations;
    private final MediationSettings[] mMediationSettings;
    private final Map<String, Map<String, String>> mMoPubRequestOptions;

    private SdkConfiguration(String str, Set<String> set, MediationSettings[] mediationSettingsArr, MoPubLog.LogLevel logLevel, Map<String, Map<String, String>> map, Map<String, Map<String, String>> map2, boolean z) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(set);
        Preconditions.checkNotNull(map);
        Preconditions.checkNotNull(map2);
        this.mAdUnitId = str;
        this.mAdapterConfigurationClasses = set;
        this.mMediationSettings = mediationSettingsArr;
        this.mLogLevel = logLevel;
        this.mMediatedNetworkConfigurations = map;
        this.mMoPubRequestOptions = map2;
        this.mLegitimateInterestAllowed = z;
    }

    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    public Set<String> getAdapterConfigurationClasses() {
        return Collections.unmodifiableSet(this.mAdapterConfigurationClasses);
    }

    public MediationSettings[] getMediationSettings() {
        MediationSettings[] mediationSettingsArr = this.mMediationSettings;
        return (MediationSettings[]) Arrays.copyOf(mediationSettingsArr, mediationSettingsArr.length);
    }

    /* access modifiers changed from: package-private */
    public MoPubLog.LogLevel getLogLevel() {
        return this.mLogLevel;
    }

    public Map<String, Map<String, String>> getMediatedNetworkConfigurations() {
        return Collections.unmodifiableMap(this.mMediatedNetworkConfigurations);
    }

    public Map<String, Map<String, String>> getMoPubRequestOptions() {
        return Collections.unmodifiableMap(this.mMoPubRequestOptions);
    }

    public boolean getLegitimateInterestAllowed() {
        return this.mLegitimateInterestAllowed;
    }

    public static class Builder {
        private String adUnitId;
        private final Set<String> adapterConfigurations;
        private boolean legitimateInterestAllowed;
        private MoPubLog.LogLevel logLevel = MoPubLog.LogLevel.NONE;
        private final Map<String, Map<String, String>> mediatedNetworkConfigurations;
        private MediationSettings[] mediationSettings;
        private final Map<String, Map<String, String>> moPubRequestOptions;

        public Builder(String str) {
            if (TextUtils.isEmpty(str)) {
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Ad unit cannot be empty at initialization");
                MoPubLog.setLogLevel(MoPubLog.getLogLevel());
                MoPubLog.log(MoPubLog.SdkLogEvent.INIT_FAILED, "Pass in an ad unit used by this app", illegalArgumentException);
            }
            this.adUnitId = str;
            this.adapterConfigurations = DefaultAdapterClasses.getClassNamesSet();
            this.mediationSettings = new MediationSettings[0];
            this.mediatedNetworkConfigurations = new HashMap();
            this.moPubRequestOptions = new HashMap();
            this.legitimateInterestAllowed = false;
        }

        public Builder withAdditionalNetwork(String str) {
            Preconditions.checkNotNull(str);
            this.adapterConfigurations.add(str);
            return this;
        }

        public Builder withMediationSettings(MediationSettings... mediationSettingsArr) {
            Preconditions.checkNotNull(mediationSettingsArr);
            this.mediationSettings = mediationSettingsArr;
            return this;
        }

        public Builder withLogLevel(MoPubLog.LogLevel logLevel2) {
            Preconditions.checkNotNull(logLevel2);
            this.logLevel = logLevel2;
            return this;
        }

        public Builder withMediatedNetworkConfiguration(String str, Map<String, String> map) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(map);
            this.mediatedNetworkConfigurations.put(str, map);
            return this;
        }

        public Builder withMoPubRequestOptions(String str, Map<String, String> map) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(map);
            this.moPubRequestOptions.put(str, map);
            return this;
        }

        public Builder withLegitimateInterestAllowed(boolean z) {
            this.legitimateInterestAllowed = z;
            return this;
        }

        public SdkConfiguration build() {
            return new SdkConfiguration(this.adUnitId, this.adapterConfigurations, this.mediationSettings, this.logLevel, this.mediatedNetworkConfigurations, this.moPubRequestOptions, this.legitimateInterestAllowed);
        }
    }
}
