package com.integralads.avid.library.mopub.session.internal.jsbridge;

import org.json.JSONObject;

public class AvidEvent {
    private JSONObject data;
    private int tag;
    private String type;

    public AvidEvent() {
    }

    public AvidEvent(int i, String str, JSONObject jSONObject) {
        this.tag = i;
        this.type = str;
        this.data = jSONObject;
    }

    public AvidEvent(int i, String str) {
        this(i, str, (JSONObject) null);
    }

    public int getTag() {
        return this.tag;
    }

    public void setTag(int i) {
        this.tag = i;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public JSONObject getData() {
        return this.data;
    }

    public void setData(JSONObject jSONObject) {
        this.data = jSONObject;
    }
}
