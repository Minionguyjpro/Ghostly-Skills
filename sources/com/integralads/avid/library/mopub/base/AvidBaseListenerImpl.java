package com.integralads.avid.library.mopub.base;

import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.jsbridge.AvidBridgeManager;

public abstract class AvidBaseListenerImpl {
    private InternalAvidAdSession avidAdSession;
    private AvidBridgeManager avidBridgeManager;

    public AvidBaseListenerImpl(InternalAvidAdSession internalAvidAdSession, AvidBridgeManager avidBridgeManager2) {
        this.avidAdSession = internalAvidAdSession;
        this.avidBridgeManager = avidBridgeManager2;
    }

    public void destroy() {
        this.avidAdSession = null;
        this.avidBridgeManager = null;
    }

    /* access modifiers changed from: protected */
    public InternalAvidAdSession getAvidAdSession() {
        return this.avidAdSession;
    }

    /* access modifiers changed from: protected */
    public AvidBridgeManager getAvidBridgeManager() {
        return this.avidBridgeManager;
    }

    /* access modifiers changed from: protected */
    public void assertSessionIsNotEnded() {
        if (this.avidAdSession == null) {
            throw new IllegalStateException("The AVID ad session is ended. Please ensure you are not recording events after the session has ended.");
        }
    }
}
