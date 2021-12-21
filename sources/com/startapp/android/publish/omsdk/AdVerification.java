package com.startapp.android.publish.omsdk;

import com.startapp.common.c.f;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/* compiled from: StartAppSDK */
public class AdVerification implements Serializable {
    private static final long serialVersionUID = 1;
    @f(b = VerificationDetails.class, f = "adVerifications")
    private VerificationDetails[] adVerification;

    public AdVerification() {
    }

    public AdVerification(VerificationDetails[] verificationDetailsArr) {
        this.adVerification = verificationDetailsArr;
    }

    public List<VerificationDetails> getAdVerification() {
        VerificationDetails[] verificationDetailsArr = this.adVerification;
        if (verificationDetailsArr == null) {
            return null;
        }
        return Arrays.asList(verificationDetailsArr);
    }
}
