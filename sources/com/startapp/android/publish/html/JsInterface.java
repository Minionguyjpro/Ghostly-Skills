package com.startapp.android.publish.html;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.c;
import com.startapp.android.publish.adsCommon.d.b;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.common.a.g;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
public class JsInterface {
    private Runnable clickCallback;
    private Runnable closeCallback;
    private Runnable enableScrollCallback;
    protected boolean inAppBrowserEnabled;
    protected Context mContext;
    private b params;
    private boolean processed;

    public JsInterface(Context context, Runnable runnable, b bVar, boolean z) {
        this(context, runnable, bVar);
        this.inAppBrowserEnabled = z;
    }

    public JsInterface(Context context, Runnable runnable, b bVar) {
        this.processed = false;
        this.inAppBrowserEnabled = true;
        this.closeCallback = null;
        this.clickCallback = null;
        this.enableScrollCallback = null;
        this.closeCallback = runnable;
        this.mContext = context;
        this.params = bVar;
    }

    public JsInterface(Context context, Runnable runnable, Runnable runnable2, Runnable runnable3, b bVar, boolean z) {
        this(context, runnable, bVar, z);
        this.clickCallback = runnable2;
        this.enableScrollCallback = runnable3;
    }

    public JsInterface(Context context, Runnable runnable, Runnable runnable2, b bVar) {
        this(context, runnable, bVar);
        this.clickCallback = runnable2;
    }

    @JavascriptInterface
    public void closeAd() {
        if (!this.processed) {
            this.processed = true;
            this.closeCallback.run();
        }
    }

    @JavascriptInterface
    public void openApp(String str, String str2, String str3) {
        if (str != null && !TextUtils.isEmpty(str)) {
            c.b(this.mContext, str, this.params);
        }
        Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(str2);
        if (str3 != null) {
            try {
                JSONObject jSONObject = new JSONObject(str3);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String valueOf = String.valueOf(keys.next());
                    launchIntentForPackage.putExtra(valueOf, String.valueOf(jSONObject.get(valueOf)));
                }
            } catch (JSONException e) {
                g.a(6, "Couldn't parse intent details json!", (Throwable) e);
            }
        }
        try {
            this.mContext.startActivity(launchIntentForPackage);
        } catch (Exception e2) {
            f.a(this.mContext, d.EXCEPTION, "JsInterface.openApp - Couldn't start activity", e2.getMessage(), c.a(str, (String) null));
            g.a(6, "Cannot find activity to handle url: [" + str + "]");
        }
        Runnable runnable = this.clickCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    @JavascriptInterface
    public void externalLinks(String str) {
        if (!this.inAppBrowserEnabled || !i.a(256)) {
            c.c(this.mContext, str);
        } else {
            c.b(this.mContext, str, (String) null);
        }
    }

    @JavascriptInterface
    public void enableScroll(String str) {
        Runnable runnable = this.enableScrollCallback;
        if (runnable != null) {
            runnable.run();
        }
    }
}
