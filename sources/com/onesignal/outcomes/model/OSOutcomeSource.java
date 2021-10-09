package com.onesignal.outcomes.model;

import org.json.JSONException;
import org.json.JSONObject;

public class OSOutcomeSource {
    private OSOutcomeSourceBody directBody;
    private OSOutcomeSourceBody indirectBody;

    public OSOutcomeSource(OSOutcomeSourceBody oSOutcomeSourceBody, OSOutcomeSourceBody oSOutcomeSourceBody2) {
        this.directBody = oSOutcomeSourceBody;
        this.indirectBody = oSOutcomeSourceBody2;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        OSOutcomeSourceBody oSOutcomeSourceBody = this.directBody;
        if (oSOutcomeSourceBody != null) {
            jSONObject.put("direct", oSOutcomeSourceBody.toJSONObject());
        }
        OSOutcomeSourceBody oSOutcomeSourceBody2 = this.indirectBody;
        if (oSOutcomeSourceBody2 != null) {
            jSONObject.put("indirect", oSOutcomeSourceBody2.toJSONObject());
        }
        return jSONObject;
    }

    public OSOutcomeSourceBody getDirectBody() {
        return this.directBody;
    }

    public OSOutcomeSource setDirectBody(OSOutcomeSourceBody oSOutcomeSourceBody) {
        this.directBody = oSOutcomeSourceBody;
        return this;
    }

    public OSOutcomeSourceBody getIndirectBody() {
        return this.indirectBody;
    }

    public OSOutcomeSource setIndirectBody(OSOutcomeSourceBody oSOutcomeSourceBody) {
        this.indirectBody = oSOutcomeSourceBody;
        return this;
    }

    public String toString() {
        return "OSOutcomeSource{directBody=" + this.directBody + ", indirectBody=" + this.indirectBody + '}';
    }
}
