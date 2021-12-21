package com.onesignal;

import com.mopub.common.AdType;
import org.json.JSONException;
import org.json.JSONObject;

class OSTrigger {
    public OSTriggerKind kind;
    public OSTriggerOperator operatorType;
    public String property;
    String triggerId;
    public Object value;

    public enum OSTriggerOperator {
        GREATER_THAN("greater"),
        LESS_THAN("less"),
        EQUAL_TO("equal"),
        NOT_EQUAL_TO("not_equal"),
        LESS_THAN_OR_EQUAL_TO("less_or_equal"),
        GREATER_THAN_OR_EQUAL_TO("greater_or_equal"),
        EXISTS("exists"),
        NOT_EXISTS("not_exists"),
        CONTAINS("in");
        
        private String text;

        private OSTriggerOperator(String str) {
            this.text = str;
        }

        public String toString() {
            return this.text;
        }

        public static OSTriggerOperator fromString(String str) {
            for (OSTriggerOperator oSTriggerOperator : values()) {
                if (oSTriggerOperator.text.equalsIgnoreCase(str)) {
                    return oSTriggerOperator;
                }
            }
            return EQUAL_TO;
        }

        public boolean checksEquality() {
            return this == EQUAL_TO || this == NOT_EQUAL_TO;
        }
    }

    public enum OSTriggerKind {
        TIME_SINCE_LAST_IN_APP("min_time_since"),
        SESSION_TIME("session_time"),
        CUSTOM(AdType.CUSTOM),
        UNKNOWN("unknown");
        
        private String value;

        private OSTriggerKind(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }

        public static OSTriggerKind fromString(String str) {
            for (OSTriggerKind oSTriggerKind : values()) {
                if (oSTriggerKind.value.equalsIgnoreCase(str)) {
                    return oSTriggerKind;
                }
            }
            return UNKNOWN;
        }
    }

    OSTrigger(JSONObject jSONObject) throws JSONException {
        this.triggerId = jSONObject.getString("id");
        this.kind = OSTriggerKind.fromString(jSONObject.getString("kind"));
        this.property = jSONObject.optString("property", (String) null);
        this.operatorType = OSTriggerOperator.fromString(jSONObject.getString("operator"));
        this.value = jSONObject.opt("value");
    }

    public String toString() {
        return "OSTrigger{triggerId='" + this.triggerId + '\'' + ", kind=" + this.kind + ", property='" + this.property + '\'' + ", operatorType=" + this.operatorType + ", value=" + this.value + '}';
    }
}
