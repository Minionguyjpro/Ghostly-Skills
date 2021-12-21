package com.integralads.avid.library.mopub.walking;

import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.walking.async.AvidAsyncTask;
import com.integralads.avid.library.mopub.walking.async.AvidAsyncTaskQueue;
import com.integralads.avid.library.mopub.walking.async.AvidCleanupAsyncTask;
import com.integralads.avid.library.mopub.walking.async.AvidEmptyPublishAsyncTask;
import com.integralads.avid.library.mopub.walking.async.AvidPublishAsyncTask;
import java.util.HashSet;
import org.json.JSONObject;

public class AvidStatePublisher implements AvidAsyncTask.StateProvider {
    private final AvidAdSessionRegistry adSessionRegistry;
    private JSONObject previousState;
    private final AvidAsyncTaskQueue taskQueue;

    public AvidStatePublisher(AvidAdSessionRegistry avidAdSessionRegistry, AvidAsyncTaskQueue avidAsyncTaskQueue) {
        this.adSessionRegistry = avidAdSessionRegistry;
        this.taskQueue = avidAsyncTaskQueue;
    }

    public void publishState(JSONObject jSONObject, HashSet<String> hashSet, double d) {
        this.taskQueue.submitTask(new AvidPublishAsyncTask(this, this.adSessionRegistry, hashSet, jSONObject, d));
    }

    public void publishEmptyState(JSONObject jSONObject, HashSet<String> hashSet, double d) {
        this.taskQueue.submitTask(new AvidEmptyPublishAsyncTask(this, this.adSessionRegistry, hashSet, jSONObject, d));
    }

    public void cleanupCache() {
        this.taskQueue.submitTask(new AvidCleanupAsyncTask(this));
    }

    public JSONObject getPreviousState() {
        return this.previousState;
    }

    public void setPreviousState(JSONObject jSONObject) {
        this.previousState = jSONObject;
    }
}
