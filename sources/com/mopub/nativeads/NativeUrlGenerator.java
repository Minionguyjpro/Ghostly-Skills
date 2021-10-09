package com.mopub.nativeads;

import android.content.Context;
import android.text.TextUtils;
import com.mopub.common.AdUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.MoPub;

class NativeUrlGenerator extends AdUrlGenerator {
    private String mDesiredAssets;
    private String mSequenceNumber;

    NativeUrlGenerator(Context context) {
        super(context);
    }

    public NativeUrlGenerator withAdUnitId(String str) {
        this.mAdUnitId = str;
        return this;
    }

    /* access modifiers changed from: package-private */
    public NativeUrlGenerator withRequest(RequestParameters requestParameters) {
        if (requestParameters != null) {
            this.mUserDataKeywords = MoPub.canCollectPersonalInformation() ? requestParameters.getUserDataKeywords() : null;
            this.mKeywords = requestParameters.getKeywords();
            this.mDesiredAssets = requestParameters.getDesiredAssets();
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public NativeUrlGenerator withSequenceNumber(int i) {
        this.mSequenceNumber = String.valueOf(i);
        return this;
    }

    public String generateUrlString(String str) {
        initUrlString(str, Constants.AD_HANDLER);
        addBaseParams(ClientMetadata.getInstance(this.mContext));
        setDesiredAssets();
        setSequenceNumber();
        return getFinalUrlString();
    }

    private void setSequenceNumber() {
        if (!TextUtils.isEmpty(this.mSequenceNumber)) {
            addParam("MAGIC_NO", this.mSequenceNumber);
        }
    }

    private void setDesiredAssets() {
        if (!TextUtils.isEmpty(this.mDesiredAssets)) {
            addParam("assets", this.mDesiredAssets);
        }
    }
}
