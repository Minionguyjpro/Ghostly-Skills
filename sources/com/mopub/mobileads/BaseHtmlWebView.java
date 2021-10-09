package com.mopub.mobileads;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import com.mopub.common.AdReport;
import com.mopub.common.Constants;
import com.mopub.common.logging.MoPubLog;
import com.mopub.network.Networking;

public class BaseHtmlWebView extends BaseWebView {
    /* access modifiers changed from: private */
    public final ViewGestureDetector mViewGestureDetector;

    public BaseHtmlWebView(Context context, AdReport adReport) {
        super(context);
        disableScrollingAndZoom();
        getSettings().setJavaScriptEnabled(true);
        this.mViewGestureDetector = new ViewGestureDetector(context);
        setBackgroundColor(0);
    }

    public void init() {
        initializeOnTouchListener();
    }

    public void loadUrl(String str) {
        if (str != null) {
            if (str.startsWith("javascript:")) {
                super.loadUrl(str);
                return;
            }
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Loading url: " + str);
        }
    }

    public void stopLoading() {
        Class<BaseHtmlWebView> cls = BaseHtmlWebView.class;
        if (this.mIsDestroyed) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, cls.getSimpleName() + "#stopLoading() called after destroy()");
            return;
        }
        WebSettings settings = getSettings();
        if (settings == null) {
            MoPubLog.SdkLogEvent sdkLogEvent2 = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent2, cls.getSimpleName() + "#getSettings() returned null");
            return;
        }
        settings.setJavaScriptEnabled(false);
        super.stopLoading();
        settings.setJavaScriptEnabled(true);
    }

    private void disableScrollingAndZoom() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        getSettings().setSupportZoom(false);
    }

    /* access modifiers changed from: package-private */
    public void loadHtmlResponse(String str) {
        loadDataWithBaseURL(Networking.getBaseUrlScheme() + "://" + Constants.HOST + "/", str, "text/html", "utf-8", (String) null);
    }

    /* access modifiers changed from: package-private */
    public void initializeOnTouchListener() {
        setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                BaseHtmlWebView.this.mViewGestureDetector.onTouchEvent(motionEvent);
                return motionEvent.getAction() == 2;
            }
        });
    }

    public void onResetUserClick() {
        ViewGestureDetector viewGestureDetector = this.mViewGestureDetector;
        if (viewGestureDetector != null) {
            viewGestureDetector.onResetUserClick();
        }
    }

    public boolean wasClicked() {
        ViewGestureDetector viewGestureDetector = this.mViewGestureDetector;
        return viewGestureDetector != null && viewGestureDetector.isClicked();
    }
}
