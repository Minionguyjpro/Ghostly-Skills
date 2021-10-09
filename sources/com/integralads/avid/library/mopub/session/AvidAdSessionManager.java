package com.integralads.avid.library.mopub.session;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.integralads.avid.library.mopub.AvidContext;
import com.integralads.avid.library.mopub.AvidManager;
import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidDisplayAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidManagedDisplayAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidManagedVideoAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidVideoAdSession;

public class AvidAdSessionManager {
    public static String getVersion() {
        return AvidContext.getInstance().getAvidVersion();
    }

    public static String getReleaseDate() {
        return AvidContext.getInstance().getAvidReleaseDate();
    }

    public static AvidDisplayAdSession startAvidDisplayAdSession(Context context, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AvidDisplayAdSession avidDisplayAdSession = new AvidDisplayAdSession();
        InternalAvidDisplayAdSession internalAvidDisplayAdSession = new InternalAvidDisplayAdSession(context, avidDisplayAdSession.getAvidAdSessionId(), externalAvidAdSessionContext);
        internalAvidDisplayAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidDisplayAdSession, internalAvidDisplayAdSession);
        return avidDisplayAdSession;
    }

    public static AvidVideoAdSession startAvidVideoAdSession(Context context, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AvidVideoAdSession avidVideoAdSession = new AvidVideoAdSession();
        InternalAvidVideoAdSession internalAvidVideoAdSession = new InternalAvidVideoAdSession(context, avidVideoAdSession.getAvidAdSessionId(), externalAvidAdSessionContext);
        internalAvidVideoAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidVideoAdSession, internalAvidVideoAdSession);
        return avidVideoAdSession;
    }

    public static AvidManagedVideoAdSession startAvidManagedVideoAdSession(Context context, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AvidManagedVideoAdSession avidManagedVideoAdSession = new AvidManagedVideoAdSession();
        InternalAvidManagedVideoAdSession internalAvidManagedVideoAdSession = new InternalAvidManagedVideoAdSession(context, avidManagedVideoAdSession.getAvidAdSessionId(), externalAvidAdSessionContext);
        internalAvidManagedVideoAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidManagedVideoAdSession, internalAvidManagedVideoAdSession);
        return avidManagedVideoAdSession;
    }

    public static AvidManagedDisplayAdSession startAvidManagedDisplayAdSession(Context context, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AvidManagedDisplayAdSession avidManagedDisplayAdSession = new AvidManagedDisplayAdSession();
        InternalAvidManagedDisplayAdSession internalAvidManagedDisplayAdSession = new InternalAvidManagedDisplayAdSession(context, avidManagedDisplayAdSession.getAvidAdSessionId(), externalAvidAdSessionContext);
        internalAvidManagedDisplayAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidManagedDisplayAdSession, internalAvidManagedDisplayAdSession);
        return avidManagedDisplayAdSession;
    }

    public static <T extends AbstractAvidAdSession> T findAvidAdSessionById(String str) {
        return AvidManager.getInstance().findAvidAdSessionById(str);
    }

    public static WebView webViewForView(View view) {
        WebView findWebView = findWebView(view);
        if (findWebView != null) {
            return findWebView;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int i = 0;
        while (i < viewGroup.getChildCount() && (findWebView = webViewForView(viewGroup.getChildAt(i))) == null) {
            i++;
        }
        return findWebView;
    }

    public static WebView webViewForSessionId(String str) {
        InternalAvidAdSession findInternalAvidAdSessionById = AvidAdSessionRegistry.getInstance().findInternalAvidAdSessionById(str);
        if (findInternalAvidAdSessionById != null) {
            return findInternalAvidAdSessionById.getWebView();
        }
        return null;
    }

    private static WebView findWebView(View view) {
        InternalAvidAdSession findInternalAvidAdSessionByView = AvidAdSessionRegistry.getInstance().findInternalAvidAdSessionByView(view);
        if (findInternalAvidAdSessionByView != null) {
            return findInternalAvidAdSessionByView.getWebView();
        }
        return null;
    }
}
