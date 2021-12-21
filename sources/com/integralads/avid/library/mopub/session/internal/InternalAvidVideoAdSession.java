package com.integralads.avid.library.mopub.session.internal;

import android.content.Context;
import com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext;

public class InternalAvidVideoAdSession extends InternalAvidHtmlAdSession {
    public InternalAvidVideoAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
    }

    public SessionType getSessionType() {
        return SessionType.VIDEO;
    }

    public MediaType getMediaType() {
        return MediaType.VIDEO;
    }
}
