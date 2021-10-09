package com.mopub.common;

import com.mopub.common.privacy.AdvertisingId;
import com.mopub.network.AdResponse;
import java.io.Serializable;
import java.util.Locale;

public class AdReport implements Serializable {
    private static final long serialVersionUID = 1;
    private final AdResponse mAdResponse;
    private final String mAdUnitId;
    private final AdvertisingId mAdvertisingId;
    private final Locale mDeviceLocale;
    private final String mDeviceModel;
    private final String mSdkVersion;

    public AdReport(String str, ClientMetadata clientMetadata, AdResponse adResponse) {
        this.mAdUnitId = str;
        this.mSdkVersion = clientMetadata.getSdkVersion();
        this.mDeviceModel = clientMetadata.getDeviceModel();
        this.mDeviceLocale = clientMetadata.getDeviceLocale();
        this.mAdvertisingId = clientMetadata.getMoPubIdentifier().getAdvertisingInfo();
        this.mAdResponse = adResponse;
    }

    public String getResponseString() {
        return this.mAdResponse.getStringBody();
    }

    public String getDspCreativeId() {
        return this.mAdResponse.getDspCreativeId();
    }

    public boolean shouldAllowCustomClose() {
        return this.mAdResponse.allowCustomClose();
    }
}
