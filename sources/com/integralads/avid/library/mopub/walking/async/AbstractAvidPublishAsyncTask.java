package com.integralads.avid.library.mopub.walking.async;

import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.walking.async.AvidAsyncTask;
import java.util.HashSet;
import org.json.JSONObject;

public abstract class AbstractAvidPublishAsyncTask extends AvidAsyncTask {
    protected final AvidAdSessionRegistry adSessionRegistry;
    protected final HashSet<String> sessionIds;
    protected final JSONObject state;
    protected final double timestamp;

    public AvidAdSessionRegistry getAdSessionRegistry() {
        return this.adSessionRegistry;
    }

    public HashSet<String> getSessionIds() {
        return this.sessionIds;
    }

    public JSONObject getState() {
        return this.state;
    }

    public double getTimestamp() {
        return this.timestamp;
    }

    public AbstractAvidPublishAsyncTask(AvidAsyncTask.StateProvider stateProvider, AvidAdSessionRegistry avidAdSessionRegistry, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(stateProvider);
        this.adSessionRegistry = avidAdSessionRegistry;
        this.sessionIds = new HashSet<>(hashSet);
        this.state = jSONObject;
        this.timestamp = d;
    }
}
