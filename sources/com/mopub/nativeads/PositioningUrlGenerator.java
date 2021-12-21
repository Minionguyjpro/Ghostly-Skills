package com.mopub.nativeads;

import android.content.Context;
import com.mopub.common.BaseUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;

class PositioningUrlGenerator extends BaseUrlGenerator {
    private static final String POSITIONING_API_VERSION = "1";
    private String mAdUnitId;
    private final Context mContext;

    public PositioningUrlGenerator(Context context) {
        this.mContext = context;
    }

    public PositioningUrlGenerator withAdUnitId(String str) {
        this.mAdUnitId = str;
        return this;
    }

    public String generateUrlString(String str) {
        initUrlString(str, Constants.POSITIONING_HANDLER);
        setAdUnitId(this.mAdUnitId);
        setApiVersion(POSITIONING_API_VERSION);
        ClientMetadata instance = ClientMetadata.getInstance(this.mContext);
        addParam("nv", instance.getSdkVersion());
        appendAppEngineInfo();
        appendWrapperVersion();
        setDeviceInfo(instance.getDeviceManufacturer(), instance.getDeviceModel(), instance.getDeviceProduct());
        setAppVersion(instance.getAppVersion());
        appendAdvertisingInfoTemplates();
        return getFinalUrlString();
    }

    private void setAdUnitId(String str) {
        addParam("id", str);
    }
}
