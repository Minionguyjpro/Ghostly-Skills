package com.startapp.android.publish.adsCommon.adinformation;

import android.content.Context;
import android.webkit.JavascriptInterface;
import com.startapp.android.publish.adsCommon.k;

/* compiled from: StartAppSDK */
public class AdInformationJsInterface {
    private Runnable acceptCallback = null;
    private Context context = null;
    private Runnable declineCallback = null;
    private Runnable privacyPolicyCallback = null;
    private boolean processed = false;

    public AdInformationJsInterface(Context context2, Runnable runnable, Runnable runnable2, Runnable runnable3) {
        this.context = context2;
        this.acceptCallback = runnable;
        this.declineCallback = runnable2;
        this.privacyPolicyCallback = runnable3;
    }

    @JavascriptInterface
    public void decline() {
        if (!this.processed) {
            this.processed = true;
            this.declineCallback.run();
        }
    }

    @JavascriptInterface
    public void accept() {
        if (!this.processed) {
            this.processed = true;
            this.acceptCallback.run();
        }
    }

    @JavascriptInterface
    public void fullPrivacyPolicy() {
        if (!this.processed) {
            this.processed = true;
            this.privacyPolicyCallback.run();
        }
    }

    @JavascriptInterface
    public String getAppId() {
        Context context2 = this.context;
        if (context2 != null) {
            try {
                return String.valueOf(121212121 ^ Long.valueOf(Long.parseLong(k.a(context2, "shared_prefs_appId", (String) null))).longValue());
            } catch (Exception unused) {
            }
        }
        return null;
    }
}
