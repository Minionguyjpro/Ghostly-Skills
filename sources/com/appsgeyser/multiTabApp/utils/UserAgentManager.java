package com.appsgeyser.multiTabApp.utils;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.wGhostlySkills_14510784.R;
import java.lang.reflect.Constructor;

public class UserAgentManager {
    public static String getDesktopUserAgent(Context context) {
        return context.getString(R.string.chromeUserAgent);
    }

    public static String getDefaultUserAgent(Context context) {
        Constructor<WebSettings> declaredConstructor;
        if (Build.VERSION.SDK_INT >= 17) {
            return NewApiWrapper.getDefaultUserAgent(context);
        }
        try {
            declaredConstructor = WebSettings.class.getDeclaredConstructor(new Class[]{Context.class, WebView.class});
            declaredConstructor.setAccessible(true);
            String userAgentString = declaredConstructor.newInstance(new Object[]{context, null}).getUserAgentString();
            declaredConstructor.setAccessible(false);
            return userAgentString;
        } catch (Exception unused) {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Throwable th) {
            declaredConstructor.setAccessible(false);
            throw th;
        }
    }

    static class NewApiWrapper {
        static String getDefaultUserAgent(Context context) {
            return WebSettings.getDefaultUserAgent(context);
        }
    }
}
