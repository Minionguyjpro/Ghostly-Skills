package com.integralads.avid.library.mopub.walking.async;

import com.integralads.avid.library.mopub.walking.async.AvidAsyncTask;
import org.json.JSONObject;

public class AvidCleanupAsyncTask extends AvidAsyncTask {
    public AvidCleanupAsyncTask(AvidAsyncTask.StateProvider stateProvider) {
        super(stateProvider);
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Object... objArr) {
        this.stateProvider.setPreviousState((JSONObject) null);
        return null;
    }
}
