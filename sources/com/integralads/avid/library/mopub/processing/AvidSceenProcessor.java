package com.integralads.avid.library.mopub.processing;

import android.view.View;
import com.integralads.avid.library.mopub.activity.AvidActivityStack;
import com.integralads.avid.library.mopub.processing.IAvidNodeProcessor;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import org.json.JSONObject;

public class AvidSceenProcessor implements IAvidNodeProcessor {
    private final IAvidNodeProcessor childrenProcessor;

    public AvidSceenProcessor(IAvidNodeProcessor iAvidNodeProcessor) {
        this.childrenProcessor = iAvidNodeProcessor;
    }

    public JSONObject getState(View view) {
        return AvidJSONUtil.getViewState(0, 0, 0, 0);
    }

    public void iterateChildren(View view, JSONObject jSONObject, IAvidNodeProcessor.IAvidViewWalker iAvidViewWalker, boolean z) {
        for (View walkView : AvidActivityStack.getInstance().getRootViews()) {
            iAvidViewWalker.walkView(walkView, this.childrenProcessor, jSONObject);
        }
    }
}
