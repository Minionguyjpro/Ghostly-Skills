package com.appsgeyser.multiTabApp.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.URLUtil;
import android.webkit.WebView;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.ui.navigationwidget.INavigationWidget;
import com.wGhostlySkills_14510784.R;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class BrowserWebView extends WebView {
    private boolean _firstUrl = true;
    private INavigationWidget _navigationWidget = null;
    private Context context;
    private OnScrollChangedCallback mOnScrollChangedCallback;

    public interface OnScrollChangedCallback {
        void onScroll(int i, int i2, int i3, int i4);
    }

    public BrowserWebView(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        this.context = context2;
    }

    private void init() {
        this._navigationWidget = Factory.getInstance().getNavigationWidget();
        this._firstUrl = false;
    }

    public void loadUrl(String str) {
        if (this._firstUrl) {
            init();
        }
        INavigationWidget iNavigationWidget = this._navigationWidget;
        if (iNavigationWidget != null && iNavigationWidget.isVisible() && !URLUtil.isValidUrl(str)) {
            if (("http://" + str).matches("(news|(ht|f)tp(s?)\\://){1}[\\S\\.]+\\.[\\S\\.]+")) {
                str = "http://" + str;
            } else {
                str = getSearchUrl(str);
            }
        }
        if (!str.contains("javascript:(function(){  })()")) {
            super.loadUrl(str);
        }
    }

    private String getSearchUrl(String str) {
        try {
            String str2 = getResources().getString(R.string.searchUrl) + URLEncoder.encode(str, "UTF-8");
            Factory.getInstance().getMainNavigationActivity();
            return _getUrlWithAllArguments(str2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    private String _getUrlWithAllArguments(String str) {
        Factory.getInstance().getMainNavigationActivity();
        return str;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        OnScrollChangedCallback onScrollChangedCallback = this.mOnScrollChangedCallback;
        if (onScrollChangedCallback != null) {
            onScrollChangedCallback.onScroll(i, i2, i3, i4);
        }
    }

    public void setOnScrollChangedCallback(OnScrollChangedCallback onScrollChangedCallback) {
        this.mOnScrollChangedCallback = onScrollChangedCallback;
    }
}
