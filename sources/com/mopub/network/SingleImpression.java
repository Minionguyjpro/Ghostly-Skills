package com.mopub.network;

import com.mopub.common.logging.MoPubLog;

public class SingleImpression {
    private final String mAdUnitId;
    private final ImpressionData mImpressionData;

    public SingleImpression(String str, ImpressionData impressionData) {
        this.mAdUnitId = str;
        this.mImpressionData = impressionData;
    }

    public void sendImpression() {
        String str = this.mAdUnitId;
        if (str != null) {
            ImpressionsEmitter.send(str, this.mImpressionData);
            return;
        }
        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "SingleImpression ad unit id may not be null.");
    }
}
