package com.integralads.avid.library.mopub.session.internal;

import android.content.Context;
import com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext;

public class InternalAvidManagedDisplayAdSession extends InternalAvidManagedAdSession {
    public InternalAvidManagedDisplayAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
    }

    public SessionType getSessionType() {
        return SessionType.MANAGED_DISPLAY;
    }

    public MediaType getMediaType() {
        return MediaType.DISPLAY;
    }
}
