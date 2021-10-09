package com.mopub.mobileads;

import android.content.Context;
import android.os.Build;
import android.view.WindowManager;
import android.webkit.WebView;
import com.mopub.common.util.Views;
import com.mopub.mobileads.util.WebViews;

public class BaseWebView extends WebView {
    private static boolean sDeadlockCleared = false;
    protected boolean mIsDestroyed;

    public BaseWebView(Context context) {
        super(context.getApplicationContext());
        restrictDeviceContentAccess();
        WebViews.setDisableJSChromeClient(this);
        if (!sDeadlockCleared) {
            clearWebViewDeadlock(getContext());
            sDeadlockCleared = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        WebViews.manageThirdPartyCookies(this);
    }

    public void destroy() {
        if (!this.mIsDestroyed) {
            this.mIsDestroyed = true;
            Views.removeFromParent(this);
            removeAllViews();
            super.destroy();
        }
    }

    /* access modifiers changed from: protected */
    public void enableJavascriptCaching() {
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setAppCacheEnabled(true);
        getSettings().setAppCachePath(getContext().getCacheDir().getAbsolutePath());
    }

    private void restrictDeviceContentAccess() {
        getSettings().setAllowFileAccess(false);
        getSettings().setAllowContentAccess(false);
        getSettings().setAllowFileAccessFromFileURLs(false);
        getSettings().setAllowUniversalAccessFromFileURLs(false);
    }

    private void clearWebViewDeadlock(Context context) {
        if (Build.VERSION.SDK_INT == 19) {
            WebView webView = new WebView(context.getApplicationContext());
            webView.setBackgroundColor(0);
            webView.loadDataWithBaseURL((String) null, "", "text/html", "UTF-8", (String) null);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.width = 1;
            layoutParams.height = 1;
            layoutParams.type = 2005;
            layoutParams.flags = 16777240;
            layoutParams.format = -2;
            layoutParams.gravity = 8388659;
            ((WindowManager) context.getSystemService("window")).addView(webView, layoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setIsDestroyed(boolean z) {
        this.mIsDestroyed = z;
    }
}
