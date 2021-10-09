package com.integralads.avid.library.mopub.session.internal;

import android.content.Context;
import com.integralads.avid.library.mopub.AvidContext;
import com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext;
import org.json.JSONException;
import org.json.JSONObject;

public class InternalAvidAdSessionContext {
    public static final String AVID_API_LEVEL = "2";
    public static final String AVID_STUB_MODE = "stub";
    public static final String CONTEXT_AVID_AD_SESSION_ID = "avidAdSessionId";
    public static final String CONTEXT_AVID_AD_SESSION_TYPE = "avidAdSessionType";
    public static final String CONTEXT_AVID_API_LEVEL = "avidApiLevel";
    public static final String CONTEXT_AVID_LIBRARY_VERSION = "avidLibraryVersion";
    public static final String CONTEXT_BUNDLE_IDENTIFIER = "bundleIdentifier";
    public static final String CONTEXT_IS_DEFERRED = "isDeferred";
    public static final String CONTEXT_MEDIA_TYPE = "mediaType";
    public static final String CONTEXT_MODE = "mode";
    public static final String CONTEXT_PARTNER = "partner";
    public static final String CONTEXT_PARTNER_VERSION = "partnerVersion";
    private ExternalAvidAdSessionContext avidAdSessionContext;
    private String avidAdSessionId;
    private String avidAdSessionType;
    private String mediaType;

    public InternalAvidAdSessionContext(Context context, String str, String str2, String str3, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        AvidContext.getInstance().init(context);
        this.avidAdSessionId = str;
        this.avidAdSessionContext = externalAvidAdSessionContext;
        this.avidAdSessionType = str2;
        this.mediaType = str3;
    }

    public String getAvidAdSessionId() {
        return this.avidAdSessionId;
    }

    public ExternalAvidAdSessionContext getAvidAdSessionContext() {
        return this.avidAdSessionContext;
    }

    public void setAvidAdSessionContext(ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        this.avidAdSessionContext = externalAvidAdSessionContext;
    }

    public JSONObject getFullContext() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("avidAdSessionId", this.avidAdSessionId);
            jSONObject.put("bundleIdentifier", AvidContext.getInstance().getBundleId());
            jSONObject.put("partner", AvidContext.getInstance().getPartnerName());
            jSONObject.put("partnerVersion", this.avidAdSessionContext.getPartnerVersion());
            jSONObject.put("avidLibraryVersion", AvidContext.getInstance().getAvidVersion());
            jSONObject.put(CONTEXT_AVID_AD_SESSION_TYPE, this.avidAdSessionType);
            jSONObject.put(CONTEXT_MEDIA_TYPE, this.mediaType);
            jSONObject.put(CONTEXT_IS_DEFERRED, this.avidAdSessionContext.isDeferred());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public JSONObject getStubContext() {
        JSONObject fullContext = getFullContext();
        try {
            fullContext.put(CONTEXT_AVID_API_LEVEL, AVID_API_LEVEL);
            fullContext.put(CONTEXT_MODE, AVID_STUB_MODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fullContext;
    }
}
