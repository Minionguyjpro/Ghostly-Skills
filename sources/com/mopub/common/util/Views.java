package com.mopub.common.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.mopub.common.logging.MoPubLog;

public class Views {
    public static void removeFromParent(View view) {
        if (view != null && view.getParent() != null && (view.getParent() instanceof ViewGroup)) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    public static View getTopmostView(Context context, View view) {
        View rootViewFromActivity = getRootViewFromActivity(context);
        return rootViewFromActivity != null ? rootViewFromActivity : getRootViewFromView(view);
    }

    private static View getRootViewFromActivity(Context context) {
        if (!(context instanceof Activity)) {
            return null;
        }
        return ((Activity) context).getWindow().getDecorView().findViewById(16908290);
    }

    private static View getRootViewFromView(View view) {
        if (view == null) {
            return null;
        }
        if (!ViewCompat.isAttachedToWindow(view)) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Attempting to call View#getRootView() on an unattached View.");
        }
        View rootView = view.getRootView();
        if (rootView == null) {
            return null;
        }
        View findViewById = rootView.findViewById(16908290);
        return findViewById != null ? findViewById : rootView;
    }
}
