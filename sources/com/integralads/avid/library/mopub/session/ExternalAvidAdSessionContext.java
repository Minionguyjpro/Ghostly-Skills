package com.integralads.avid.library.mopub.session;

public class ExternalAvidAdSessionContext {
    private boolean isDeferred;
    private String partnerVersion;

    public ExternalAvidAdSessionContext(String str) {
        this(str, false);
    }

    public ExternalAvidAdSessionContext(String str, boolean z) {
        this.partnerVersion = str;
        this.isDeferred = z;
    }

    public String getPartnerVersion() {
        return this.partnerVersion;
    }

    public boolean isDeferred() {
        return this.isDeferred;
    }
}
