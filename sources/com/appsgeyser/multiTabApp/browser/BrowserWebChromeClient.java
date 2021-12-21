package com.appsgeyser.multiTabApp.browser;

import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.controllers.WebContentController;
import com.appsgeyser.multiTabApp.ui.dialog.SimpleDialogs;

public class BrowserWebChromeClient extends WebChromeClient {
    public static final String WEB_VIEW_LOG_PREFIX = "webConsoleMessage";
    private WebContentController _webController;

    public BrowserWebChromeClient(WebContentController webContentController) {
        this._webController = webContentController;
    }

    public void onProgressChanged(WebView webView, int i) {
        this._webController.setProgressBarState(i);
    }

    public View getVideoLoadingProgressView() {
        return Factory.getInstance().getMainNavigationActivity().getVideoLoadingProgressView();
    }

    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        super.onShowCustomView(view, customViewCallback);
        Factory.getInstance().getMainNavigationActivity().onShowCustomView(view, customViewCallback);
    }

    public void onHideCustomView() {
        super.onHideCustomView();
        Factory.getInstance().getMainNavigationActivity().onHideCustomView();
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        return Factory.getInstance().getMainNavigationActivity().openFileChooser(webView, valueCallback, fileChooserParams);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        openFileChooser(valueCallback, str);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
        Factory.getInstance().getMainNavigationActivity().openFileChooser(valueCallback, str);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        openFileChooser(valueCallback, "");
    }

    public void onPermissionRequest(final PermissionRequest permissionRequest) {
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new Runnable() {
            public void run() {
                for (String str : permissionRequest.getResources()) {
                    char c = 65535;
                    int hashCode = str.hashCode();
                    if (hashCode != -1660821873) {
                        if (hashCode == 968612586 && str.equals("android.webkit.resource.AUDIO_CAPTURE")) {
                            c = 0;
                        }
                    } else if (str.equals("android.webkit.resource.VIDEO_CAPTURE")) {
                        c = 1;
                    }
                    if (c == 0) {
                        BrowserWebChromeClient.this.askForPermission(permissionRequest.getOrigin().toString(), "android.permission.RECORD_AUDIO", 777);
                    } else if (c == 1) {
                        BrowserWebChromeClient.this.askForPermission(permissionRequest.getOrigin().toString(), "android.permission.CAMERA", 776);
                    }
                }
                PermissionRequest permissionRequest = permissionRequest;
                permissionRequest.grant(permissionRequest.getResources());
            }
        });
    }

    /* access modifiers changed from: private */
    public void askForPermission(String str, String str2, int i) {
        if (ContextCompat.checkSelfPermission(Factory.getInstance().getMainNavigationActivity(), str2) != 0) {
            ActivityCompat.requestPermissions(Factory.getInstance().getMainNavigationActivity(), new String[]{str2}, i);
        }
    }

    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        super.onGeolocationPermissionsShowPrompt(str, callback);
        callback.invoke(str, true, true);
    }

    public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
        SimpleDialogs.createConfirmDialog(Factory.getInstance().getMainNavigationActivity().getConfig().getWidgetName(), str2, webView.getContext(), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                jsResult.confirm();
            }
        }, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                jsResult.cancel();
            }
        }).show();
        return true;
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.d(WEB_VIEW_LOG_PREFIX, String.format("%s @ %d: %s", new Object[]{consoleMessage.message(), Integer.valueOf(consoleMessage.lineNumber()), consoleMessage.sourceId()}));
        return true;
    }
}
