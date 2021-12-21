package com.mopub.common.util;

public enum JavaScriptWebViewCallbacks {
    WEB_VIEW_DID_APPEAR("webviewDidAppear();"),
    WEB_VIEW_DID_CLOSE("webviewDidClose();");
    
    private String mJavascript;

    private JavaScriptWebViewCallbacks(String str) {
        this.mJavascript = str;
    }

    public String getJavascript() {
        return this.mJavascript;
    }

    public String getUrl() {
        return "javascript:" + this.mJavascript;
    }
}
