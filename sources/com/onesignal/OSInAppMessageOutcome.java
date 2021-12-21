package com.onesignal;

import org.json.JSONException;
import org.json.JSONObject;

public class OSInAppMessageOutcome {
    private String name;
    private boolean unique;
    private float weight;

    OSInAppMessageOutcome(JSONObject jSONObject) throws JSONException {
        this.name = jSONObject.getString("name");
        this.weight = jSONObject.has("weight") ? (float) jSONObject.getDouble("weight") : 0.0f;
        this.unique = jSONObject.has("unique") && jSONObject.getBoolean("unique");
    }

    public String getName() {
        return this.name;
    }

    public float getWeight() {
        return this.weight;
    }

    public boolean isUnique() {
        return this.unique;
    }

    public String toString() {
        return "OSInAppMessageOutcome{name='" + this.name + '\'' + ", weight=" + this.weight + ", unique=" + this.unique + '}';
    }
}
