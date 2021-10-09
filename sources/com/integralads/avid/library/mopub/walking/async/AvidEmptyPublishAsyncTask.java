package com.integralads.avid.library.mopub.walking.async;

import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.utils.AvidCommand;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.integralads.avid.library.mopub.walking.async.AvidAsyncTask;
import java.util.HashSet;
import org.json.JSONObject;

public class AvidEmptyPublishAsyncTask extends AbstractAvidPublishAsyncTask {
    public AvidEmptyPublishAsyncTask(AvidAsyncTask.StateProvider stateProvider, AvidAdSessionRegistry avidAdSessionRegistry, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(stateProvider, avidAdSessionRegistry, hashSet, jSONObject, d);
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Object... objArr) {
        return AvidCommand.setNativeViewState(AvidJSONUtil.getTreeJSONObject(this.state, this.timestamp).toString());
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        for (InternalAvidAdSession next : this.adSessionRegistry.getInternalAvidAdSessions()) {
            if (this.sessionIds.contains(next.getAvidAdSessionId())) {
                next.publishEmptyNativeViewStateCommand(str, this.timestamp);
            }
        }
        super.onPostExecute(str);
    }
}
