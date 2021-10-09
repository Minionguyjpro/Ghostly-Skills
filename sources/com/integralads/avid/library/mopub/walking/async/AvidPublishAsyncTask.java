package com.integralads.avid.library.mopub.walking.async;

import android.text.TextUtils;
import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.utils.AvidCommand;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.integralads.avid.library.mopub.walking.async.AvidAsyncTask;
import java.util.HashSet;
import org.json.JSONObject;

public class AvidPublishAsyncTask extends AbstractAvidPublishAsyncTask {
    public AvidPublishAsyncTask(AvidAsyncTask.StateProvider stateProvider, AvidAdSessionRegistry avidAdSessionRegistry, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(stateProvider, avidAdSessionRegistry, hashSet, jSONObject, d);
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Object... objArr) {
        if (AvidJSONUtil.equalStates(this.state, this.stateProvider.getPreviousState())) {
            return null;
        }
        this.stateProvider.setPreviousState(this.state);
        return AvidCommand.setNativeViewState(AvidJSONUtil.getTreeJSONObject(this.state, this.timestamp).toString());
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        if (!TextUtils.isEmpty(str)) {
            injectCommand(str);
        }
        super.onPostExecute(str);
    }

    private void injectCommand(String str) {
        for (InternalAvidAdSession next : this.adSessionRegistry.getInternalAvidAdSessions()) {
            if (this.sessionIds.contains(next.getAvidAdSessionId())) {
                next.publishNativeViewStateCommand(str, this.timestamp);
            }
        }
    }
}
