package com.integralads.avid.library.mopub.session;

import android.app.Activity;
import android.view.View;
import com.integralads.avid.library.mopub.AvidManager;
import com.integralads.avid.library.mopub.deferred.AvidDeferredAdSessionListener;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import java.util.UUID;

public abstract class AbstractAvidAdSession<T extends View> {
    private String avidAdSessionId = UUID.randomUUID().toString();

    public String getAvidAdSessionId() {
        return this.avidAdSessionId;
    }

    public void registerAdView(T t, Activity activity) {
        InternalAvidAdSession findInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(this.avidAdSessionId);
        if (findInternalAvidAdSessionById != null) {
            findInternalAvidAdSessionById.registerAdView(t);
        }
        AvidManager.getInstance().registerActivity(activity);
    }

    public void unregisterAdView(T t) {
        InternalAvidAdSession findInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(this.avidAdSessionId);
        if (findInternalAvidAdSessionById != null) {
            findInternalAvidAdSessionById.unregisterAdView(t);
        }
    }

    public void endSession() {
        InternalAvidAdSession findInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        if (findInternalAvidAdSessionById != null) {
            findInternalAvidAdSessionById.onEnd();
        }
    }

    public AvidDeferredAdSessionListener getAvidDeferredAdSessionListener() {
        InternalAvidAdSession findInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        AvidDeferredAdSessionListener avidDeferredAdSessionListener = findInternalAvidAdSessionById != null ? findInternalAvidAdSessionById.getAvidDeferredAdSessionListener() : null;
        if (avidDeferredAdSessionListener != null) {
            return avidDeferredAdSessionListener;
        }
        throw new IllegalStateException("The AVID ad session is not deferred. Please ensure you are only using AvidDeferredAdSessionListener for deferred AVID ad session.");
    }

    public void registerFriendlyObstruction(View view) {
        InternalAvidAdSession findInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        if (findInternalAvidAdSessionById != null) {
            findInternalAvidAdSessionById.getObstructionsWhiteList().add(view);
        }
    }
}
