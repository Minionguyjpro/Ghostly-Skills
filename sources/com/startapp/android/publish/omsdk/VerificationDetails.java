package com.startapp.android.publish.omsdk;

import java.io.Serializable;

/* compiled from: StartAppSDK */
public class VerificationDetails implements Serializable {
    private static final long serialVersionUID = 1;
    private String javascriptResourceUrl;
    private String vendorKey;
    private String verificationParameters;

    public VerificationDetails() {
    }

    public VerificationDetails(String str, String str2, String str3) {
        this.vendorKey = str;
        this.javascriptResourceUrl = str2;
        this.verificationParameters = str3;
    }

    public String getVendorKey() {
        return this.vendorKey;
    }

    public String getVerificationScriptUrl() {
        return this.javascriptResourceUrl;
    }

    public String getVerificationParameters() {
        return this.verificationParameters;
    }
}
