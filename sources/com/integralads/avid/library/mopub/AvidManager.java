package com.integralads.avid.library.mopub;

import android.app.Activity;
import android.content.Context;
import com.integralads.avid.library.mopub.AvidLoader;
import com.integralads.avid.library.mopub.AvidStateWatcher;
import com.integralads.avid.library.mopub.activity.AvidActivityStack;
import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistryListener;
import com.integralads.avid.library.mopub.session.AbstractAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;

public class AvidManager implements AvidLoader.AvidLoaderListener, AvidStateWatcher.AvidStateWatcherListener, AvidAdSessionRegistryListener {
    private static AvidManager avidManagerInstance = new AvidManager();
    private static Context context;

    public static AvidManager getInstance() {
        return avidManagerInstance;
    }

    public void init(Context context2) {
        if (context == null) {
            context = context2.getApplicationContext();
            AvidStateWatcher.getInstance().init(context);
            AvidAdSessionRegistry.getInstance().setListener(this);
            AvidJSONUtil.init(context);
        }
    }

    public void registerAvidAdSession(AbstractAvidAdSession abstractAvidAdSession, InternalAvidAdSession internalAvidAdSession) {
        AvidAdSessionRegistry.getInstance().registerAvidAdSession(abstractAvidAdSession, internalAvidAdSession);
    }

    public AbstractAvidAdSession findAvidAdSessionById(String str) {
        return AvidAdSessionRegistry.getInstance().findAvidAdSessionById(str);
    }

    public InternalAvidAdSession findInternalAvidAdSessionById(String str) {
        return AvidAdSessionRegistry.getInstance().findInternalAvidAdSessionById(str);
    }

    public void registerActivity(Activity activity) {
        AvidActivityStack.getInstance().addActivity(activity);
    }

    private void start() {
        AvidStateWatcher.getInstance().setStateWatcherListener(this);
        AvidStateWatcher.getInstance().start();
        if (AvidStateWatcher.getInstance().isActive()) {
            AvidTreeWalker.getInstance().start();
        }
    }

    private void stop() {
        AvidActivityStack.getInstance().cleanup();
        AvidTreeWalker.getInstance().stop();
        AvidStateWatcher.getInstance().stop();
        AvidLoader.getInstance().unregisterAvidLoader();
        context = null;
    }

    private boolean isActive() {
        return !AvidAdSessionRegistry.getInstance().isEmpty();
    }

    private void notifyAvidReady() {
        AvidAdSessionRegistry.getInstance().setListener((AvidAdSessionRegistryListener) null);
        for (InternalAvidAdSession avidBridgeManager : AvidAdSessionRegistry.getInstance().getInternalAvidAdSessions()) {
            avidBridgeManager.getAvidBridgeManager().onAvidJsReady();
        }
        AvidAdSessionRegistry.getInstance().setListener(this);
    }

    public void onAvidLoaded() {
        if (isActive()) {
            notifyAvidReady();
            if (AvidAdSessionRegistry.getInstance().hasActiveSessions()) {
                start();
            }
        }
    }

    public void onAppStateChanged(boolean z) {
        if (z) {
            AvidTreeWalker.getInstance().start();
        } else {
            AvidTreeWalker.getInstance().pause();
        }
    }

    public void registryHasSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry) {
        if (!avidAdSessionRegistry.isEmpty() && !AvidBridge.isAvidJsReady()) {
            AvidLoader.getInstance().setListener(this);
            AvidLoader.getInstance().registerAvidLoader(context);
        }
    }

    public void registryHasActiveSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry) {
        if (!avidAdSessionRegistry.hasActiveSessions() || !AvidBridge.isAvidJsReady()) {
            stop();
        } else {
            start();
        }
    }
}
