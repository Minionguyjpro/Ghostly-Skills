package com.integralads.avid.library.mopub.session.internal;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.mopub.AvidBridge;
import com.integralads.avid.library.mopub.deferred.AvidDeferredAdSessionListener;
import com.integralads.avid.library.mopub.deferred.AvidDeferredAdSessionListenerImpl;
import com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.mopub.session.internal.jsbridge.AvidBridgeManager;
import com.integralads.avid.library.mopub.session.internal.jsbridge.AvidWebViewManager;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.integralads.avid.library.mopub.utils.AvidTimestamp;
import com.integralads.avid.library.mopub.weakreference.AvidView;

public abstract class InternalAvidAdSession<T extends View> implements AvidBridgeManager.AvidBridgeManagerListener {
    private AdState adState;
    private AvidBridgeManager avidBridgeManager;
    private AvidDeferredAdSessionListenerImpl avidDeferredAdSessionListener;
    private AvidView<T> avidView = new AvidView<>(null);
    private final InternalAvidAdSessionContext internalContext;
    private boolean isActive;
    private boolean isReady;
    private double lastUpdated;
    private InternalAvidAdSessionListener listener;
    private final ObstructionsWhiteList obstructionsWhiteList;
    private AvidWebViewManager webViewManager = new AvidWebViewManager(this.internalContext, this.avidBridgeManager);

    enum AdState {
        AD_STATE_IDLE,
        AD_STATE_VISIBLE,
        AD_STATE_HIDDEN
    }

    public abstract MediaType getMediaType();

    public abstract SessionType getSessionType();

    public abstract WebView getWebView();

    public void onStart() {
    }

    /* access modifiers changed from: protected */
    public void onViewRegistered() {
    }

    /* access modifiers changed from: protected */
    public void onViewUnregistered() {
    }

    public InternalAvidAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        InternalAvidAdSessionContext internalAvidAdSessionContext = new InternalAvidAdSessionContext(context, str, getSessionType().toString(), getMediaType().toString(), externalAvidAdSessionContext);
        this.internalContext = internalAvidAdSessionContext;
        AvidBridgeManager avidBridgeManager2 = new AvidBridgeManager(internalAvidAdSessionContext);
        this.avidBridgeManager = avidBridgeManager2;
        avidBridgeManager2.setListener(this);
        boolean z = !externalAvidAdSessionContext.isDeferred();
        this.isReady = z;
        if (!z) {
            this.avidDeferredAdSessionListener = new AvidDeferredAdSessionListenerImpl(this, this.avidBridgeManager);
        }
        this.obstructionsWhiteList = new ObstructionsWhiteList();
        onViewChanged();
    }

    public String getAvidAdSessionId() {
        return this.internalContext.getAvidAdSessionId();
    }

    public ExternalAvidAdSessionContext getAvidAdSessionContext() {
        return this.internalContext.getAvidAdSessionContext();
    }

    public T getView() {
        return (View) this.avidView.get();
    }

    public AvidDeferredAdSessionListener getAvidDeferredAdSessionListener() {
        return this.avidDeferredAdSessionListener;
    }

    public InternalAvidAdSessionListener getListener() {
        return this.listener;
    }

    public void setListener(InternalAvidAdSessionListener internalAvidAdSessionListener) {
        this.listener = internalAvidAdSessionListener;
    }

    public boolean isEmpty() {
        return this.avidView.isEmpty();
    }

    public boolean isActive() {
        return this.isActive;
    }

    public boolean isReady() {
        return this.isReady;
    }

    public AvidBridgeManager getAvidBridgeManager() {
        return this.avidBridgeManager;
    }

    public ObstructionsWhiteList getObstructionsWhiteList() {
        return this.obstructionsWhiteList;
    }

    public void registerAdView(T t) {
        if (!doesManageView(t)) {
            onViewChanged();
            this.avidView.set(t);
            onViewRegistered();
            sessionStateCanBeChanged();
        }
    }

    public void unregisterAdView(T t) {
        if (doesManageView(t)) {
            onViewChanged();
            cleanupViewState();
            this.avidView.set(null);
            onViewUnregistered();
            sessionStateCanBeChanged();
        }
    }

    public boolean doesManageView(View view) {
        return this.avidView.contains(view);
    }

    public void onEnd() {
        cleanupViewState();
        AvidDeferredAdSessionListenerImpl avidDeferredAdSessionListenerImpl = this.avidDeferredAdSessionListener;
        if (avidDeferredAdSessionListenerImpl != null) {
            avidDeferredAdSessionListenerImpl.destroy();
        }
        this.avidBridgeManager.destroy();
        this.webViewManager.destroy();
        this.isReady = false;
        sessionStateCanBeChanged();
        InternalAvidAdSessionListener internalAvidAdSessionListener = this.listener;
        if (internalAvidAdSessionListener != null) {
            internalAvidAdSessionListener.sessionDidEnd(this);
        }
    }

    public void onReady() {
        this.isReady = true;
        sessionStateCanBeChanged();
    }

    public void avidBridgeManagerDidInjectAvidJs() {
        sessionStateCanBeChanged();
    }

    public void setScreenMode(boolean z) {
        if (isActive()) {
            this.avidBridgeManager.publishAppState(z ? AvidBridge.APP_STATE_ACTIVE : AvidBridge.APP_STATE_INACTIVE);
        }
    }

    public void publishNativeViewStateCommand(String str, double d) {
        if (d > this.lastUpdated) {
            this.avidBridgeManager.callAvidbridge(str);
            this.adState = AdState.AD_STATE_VISIBLE;
        }
    }

    public void publishEmptyNativeViewStateCommand(String str, double d) {
        if (d > this.lastUpdated && this.adState != AdState.AD_STATE_HIDDEN) {
            this.avidBridgeManager.callAvidbridge(str);
            this.adState = AdState.AD_STATE_HIDDEN;
        }
    }

    /* access modifiers changed from: protected */
    public void cleanupViewState() {
        if (isActive()) {
            this.avidBridgeManager.publishNativeViewState(AvidJSONUtil.getEmptyTreeJSONObject().toString());
        }
    }

    /* access modifiers changed from: protected */
    public void updateWebViewManager() {
        this.webViewManager.setWebView(getWebView());
    }

    /* access modifiers changed from: protected */
    public void sessionStateCanBeChanged() {
        boolean z = this.avidBridgeManager.isActive() && this.isReady && !isEmpty();
        if (this.isActive != z) {
            setActive(z);
        }
    }

    /* access modifiers changed from: protected */
    public void setActive(boolean z) {
        this.isActive = z;
        InternalAvidAdSessionListener internalAvidAdSessionListener = this.listener;
        if (internalAvidAdSessionListener == null) {
            return;
        }
        if (z) {
            internalAvidAdSessionListener.sessionHasBecomeActive(this);
        } else {
            internalAvidAdSessionListener.sessionHasResignedActive(this);
        }
    }

    private void onViewChanged() {
        this.lastUpdated = AvidTimestamp.getCurrentTime();
        this.adState = AdState.AD_STATE_IDLE;
    }
}
