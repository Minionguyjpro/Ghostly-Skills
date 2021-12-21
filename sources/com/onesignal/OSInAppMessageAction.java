package com.onesignal;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OSInAppMessageAction {
    String clickId;
    public String clickName;
    public String clickUrl;
    public boolean closesMessage;
    public boolean firstClick;
    public List<OSInAppMessageOutcome> outcomes = new ArrayList();
    public List<OSInAppMessagePrompt> prompts = new ArrayList();
    public OSInAppMessageTag tags;
    public OSInAppMessageActionUrlType urlTarget;

    OSInAppMessageAction(JSONObject jSONObject) throws JSONException {
        this.clickId = jSONObject.optString("id", (String) null);
        this.clickName = jSONObject.optString("name", (String) null);
        this.clickUrl = jSONObject.optString("url", (String) null);
        OSInAppMessageActionUrlType fromString = OSInAppMessageActionUrlType.fromString(jSONObject.optString("url_target", (String) null));
        this.urlTarget = fromString;
        if (fromString == null) {
            this.urlTarget = OSInAppMessageActionUrlType.IN_APP_WEBVIEW;
        }
        this.closesMessage = jSONObject.optBoolean("close", true);
        if (jSONObject.has("outcomes")) {
            parseOutcomes(jSONObject);
        }
        if (jSONObject.has("tags")) {
            this.tags = new OSInAppMessageTag(jSONObject.getJSONObject("tags"));
        }
        if (jSONObject.has("prompts")) {
            parsePrompts(jSONObject);
        }
    }

    private void parseOutcomes(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("outcomes");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.outcomes.add(new OSInAppMessageOutcome((JSONObject) jSONArray.get(i)));
        }
    }

    private void parsePrompts(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("prompts");
        for (int i = 0; i < jSONArray.length(); i++) {
            if (jSONArray.get(i).equals("location")) {
                this.prompts.add(new OSInAppMessageLocationPrompt());
            }
        }
    }

    public enum OSInAppMessageActionUrlType {
        IN_APP_WEBVIEW("webview"),
        BROWSER("browser"),
        REPLACE_CONTENT("replacement");
        
        private String text;

        private OSInAppMessageActionUrlType(String str) {
            this.text = str;
        }

        public String toString() {
            return this.text;
        }

        public static OSInAppMessageActionUrlType fromString(String str) {
            for (OSInAppMessageActionUrlType oSInAppMessageActionUrlType : values()) {
                if (oSInAppMessageActionUrlType.text.equalsIgnoreCase(str)) {
                    return oSInAppMessageActionUrlType;
                }
            }
            return null;
        }
    }
}
