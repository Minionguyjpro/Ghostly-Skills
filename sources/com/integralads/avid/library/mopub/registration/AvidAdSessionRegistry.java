package com.integralads.avid.library.mopub.registration;

import android.view.View;
import com.integralads.avid.library.mopub.session.AbstractAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionListener;
import java.util.Collection;
import java.util.HashMap;

public class AvidAdSessionRegistry implements InternalAvidAdSessionListener {
    private static AvidAdSessionRegistry instance = new AvidAdSessionRegistry();
    private int activeSessionCount = 0;
    private final HashMap<String, AbstractAvidAdSession> avidAdSessions = new HashMap<>();
    private final HashMap<String, InternalAvidAdSession> internalAvidAdSessions = new HashMap<>();
    private AvidAdSessionRegistryListener listener;

    public static AvidAdSessionRegistry getInstance() {
        return instance;
    }

    public AvidAdSessionRegistryListener getListener() {
        return this.listener;
    }

    public void setListener(AvidAdSessionRegistryListener avidAdSessionRegistryListener) {
        this.listener = avidAdSessionRegistryListener;
    }

    public void registerAvidAdSession(AbstractAvidAdSession abstractAvidAdSession, InternalAvidAdSession internalAvidAdSession) {
        AvidAdSessionRegistryListener avidAdSessionRegistryListener;
        this.avidAdSessions.put(abstractAvidAdSession.getAvidAdSessionId(), abstractAvidAdSession);
        this.internalAvidAdSessions.put(abstractAvidAdSession.getAvidAdSessionId(), internalAvidAdSession);
        internalAvidAdSession.setListener(this);
        if (this.avidAdSessions.size() == 1 && (avidAdSessionRegistryListener = this.listener) != null) {
            avidAdSessionRegistryListener.registryHasSessionsChanged(this);
        }
    }

    public Collection<InternalAvidAdSession> getInternalAvidAdSessions() {
        return this.internalAvidAdSessions.values();
    }

    public Collection<AbstractAvidAdSession> getAvidAdSessions() {
        return this.avidAdSessions.values();
    }

    public boolean isEmpty() {
        return this.avidAdSessions.isEmpty();
    }

    public boolean hasActiveSessions() {
        return this.activeSessionCount > 0;
    }

    public AbstractAvidAdSession findAvidAdSessionById(String str) {
        return this.avidAdSessions.get(str);
    }

    public InternalAvidAdSession findInternalAvidAdSessionById(String str) {
        return this.internalAvidAdSessions.get(str);
    }

    public InternalAvidAdSession findInternalAvidAdSessionByView(View view) {
        for (InternalAvidAdSession next : this.internalAvidAdSessions.values()) {
            if (next.doesManageView(view)) {
                return next;
            }
        }
        return null;
    }

    public void sessionDidEnd(InternalAvidAdSession internalAvidAdSession) {
        AvidAdSessionRegistryListener avidAdSessionRegistryListener;
        this.avidAdSessions.remove(internalAvidAdSession.getAvidAdSessionId());
        this.internalAvidAdSessions.remove(internalAvidAdSession.getAvidAdSessionId());
        internalAvidAdSession.setListener((InternalAvidAdSessionListener) null);
        if (this.avidAdSessions.size() == 0 && (avidAdSessionRegistryListener = this.listener) != null) {
            avidAdSessionRegistryListener.registryHasSessionsChanged(this);
        }
    }

    public void sessionHasBecomeActive(InternalAvidAdSession internalAvidAdSession) {
        AvidAdSessionRegistryListener avidAdSessionRegistryListener;
        int i = this.activeSessionCount + 1;
        this.activeSessionCount = i;
        if (i == 1 && (avidAdSessionRegistryListener = this.listener) != null) {
            avidAdSessionRegistryListener.registryHasActiveSessionsChanged(this);
        }
    }

    public void sessionHasResignedActive(InternalAvidAdSession internalAvidAdSession) {
        AvidAdSessionRegistryListener avidAdSessionRegistryListener;
        int i = this.activeSessionCount - 1;
        this.activeSessionCount = i;
        if (i == 0 && (avidAdSessionRegistryListener = this.listener) != null) {
            avidAdSessionRegistryListener.registryHasActiveSessionsChanged(this);
        }
    }
}
