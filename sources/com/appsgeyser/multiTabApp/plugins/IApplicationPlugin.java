package com.appsgeyser.multiTabApp.plugins;

import android.content.Context;
import android.webkit.WebView;

public interface IApplicationPlugin {
    IApplicationPlugin load(Context context, WebView webView);
}
