package com.onesignal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class OSInAppMessage {
    private boolean actionTaken;
    private Set<String> clickedClickIds;
    private double displayDuration;
    private boolean displayedInSession = false;
    boolean isPreview;
    public String messageId;
    private OSInAppMessageRedisplayStats redisplayStats = new OSInAppMessageRedisplayStats();
    private boolean triggerChanged = false;
    public ArrayList<ArrayList<OSTrigger>> triggers;
    public HashMap<String, HashMap<String, String>> variants;

    OSInAppMessage(boolean z) {
        this.isPreview = z;
    }

    OSInAppMessage(String str, Set<String> set, boolean z, OSInAppMessageRedisplayStats oSInAppMessageRedisplayStats) {
        this.messageId = str;
        this.clickedClickIds = set;
        this.displayedInSession = z;
        this.redisplayStats = oSInAppMessageRedisplayStats;
    }

    OSInAppMessage(JSONObject jSONObject) throws JSONException {
        this.messageId = jSONObject.getString("id");
        this.variants = parseVariants(jSONObject.getJSONObject("variants"));
        this.triggers = parseTriggerJson(jSONObject.getJSONArray("triggers"));
        this.clickedClickIds = new HashSet();
        if (jSONObject.has("redisplay")) {
            this.redisplayStats = new OSInAppMessageRedisplayStats(jSONObject.getJSONObject("redisplay"));
        }
    }

    private HashMap<String, HashMap<String, String>> parseVariants(JSONObject jSONObject) throws JSONException {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(next);
            HashMap hashMap2 = new HashMap();
            Iterator<String> keys2 = jSONObject2.keys();
            while (keys2.hasNext()) {
                String next2 = keys2.next();
                hashMap2.put(next2, jSONObject2.getString(next2));
            }
            hashMap.put(next, hashMap2);
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public ArrayList<ArrayList<OSTrigger>> parseTriggerJson(JSONArray jSONArray) throws JSONException {
        ArrayList<ArrayList<OSTrigger>> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONArray jSONArray2 = jSONArray.getJSONArray(i);
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                arrayList2.add(new OSTrigger(jSONArray2.getJSONObject(i2)));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public boolean takeActionAsUnique() {
        if (this.actionTaken) {
            return false;
        }
        this.actionTaken = true;
        return true;
    }

    /* access modifiers changed from: package-private */
    public double getDisplayDuration() {
        return this.displayDuration;
    }

    /* access modifiers changed from: package-private */
    public void setDisplayDuration(double d) {
        this.displayDuration = d;
    }

    /* access modifiers changed from: package-private */
    public boolean isTriggerChanged() {
        return this.triggerChanged;
    }

    /* access modifiers changed from: package-private */
    public void setTriggerChanged(boolean z) {
        this.triggerChanged = z;
    }

    public boolean isDisplayedInSession() {
        return this.displayedInSession;
    }

    public void setDisplayedInSession(boolean z) {
        this.displayedInSession = z;
    }

    /* access modifiers changed from: package-private */
    public Set<String> getClickedClickIds() {
        return this.clickedClickIds;
    }

    /* access modifiers changed from: package-private */
    public boolean isClickAvailable(String str) {
        return !this.clickedClickIds.contains(str);
    }

    /* access modifiers changed from: package-private */
    public void clearClickIds() {
        this.clickedClickIds.clear();
    }

    /* access modifiers changed from: package-private */
    public void addClickId(String str) {
        this.clickedClickIds.add(str);
    }

    /* access modifiers changed from: package-private */
    public OSInAppMessageRedisplayStats getRedisplayStats() {
        return this.redisplayStats;
    }

    public String toString() {
        return "OSInAppMessage{messageId='" + this.messageId + '\'' + ", triggers=" + this.triggers + ", clickedClickIds=" + this.clickedClickIds + ", displayStats=" + this.redisplayStats + ", actionTaken=" + this.actionTaken + ", isPreview=" + this.isPreview + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.messageId.equals(((OSInAppMessage) obj).messageId);
    }

    public int hashCode() {
        return this.messageId.hashCode();
    }
}
