package com.mopub.mraid;

import android.webkit.ConsoleMessage;
import android.webkit.JsResult;

public interface MraidWebViewDebugListener {
    boolean onConsoleMessage(ConsoleMessage consoleMessage);

    boolean onJsAlert(String str, JsResult jsResult);
}
