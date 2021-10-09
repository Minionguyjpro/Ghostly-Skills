package com.onesignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OSInAppMessageTag {
    private JSONObject tagsToAdd;
    private JSONArray tagsToRemove;

    OSInAppMessageTag(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = null;
        this.tagsToAdd = jSONObject.has("adds") ? jSONObject.getJSONObject("adds") : null;
        this.tagsToRemove = jSONObject.has("removes") ? jSONObject.getJSONArray("removes") : jSONArray;
    }

    public JSONObject getTagsToAdd() {
        return this.tagsToAdd;
    }

    public JSONArray getTagsToRemove() {
        return this.tagsToRemove;
    }

    public String toString() {
        return "OSInAppMessageTag{adds=" + this.tagsToAdd + ", removes=" + this.tagsToRemove + '}';
    }
}
