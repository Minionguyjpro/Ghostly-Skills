package com.onesignal;

import com.appnext.base.b.i;
import com.onesignal.LocationController;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

abstract class UserState {
    private static final String[] LOCATION_FIELDS = {i.fC, "long", "loc_acc", "loc_type", "loc_bg", "loc_time_stamp", "ad_id"};
    private static final Set<String> LOCATION_FIELDS_SET = new HashSet(Arrays.asList(LOCATION_FIELDS));
    private static final Object syncLock = new Object() {
    };
    JSONObject dependValues;
    private String persistKey;
    JSONObject syncValues;

    /* access modifiers changed from: protected */
    public abstract void addDependFields();

    /* access modifiers changed from: package-private */
    public abstract boolean isSubscribed();

    /* access modifiers changed from: package-private */
    public abstract UserState newInstance(String str);

    UserState(String str, boolean z) {
        this.persistKey = str;
        if (z) {
            loadState();
            return;
        }
        this.dependValues = new JSONObject();
        this.syncValues = new JSONObject();
    }

    /* access modifiers changed from: package-private */
    public UserState deepClone(String str) {
        UserState newInstance = newInstance(str);
        try {
            newInstance.dependValues = new JSONObject(this.dependValues.toString());
            newInstance.syncValues = new JSONObject(this.syncValues.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newInstance;
    }

    private Set<String> getGroupChangeFields(UserState userState) {
        try {
            if (this.dependValues.optLong("loc_time_stamp") == userState.dependValues.getLong("loc_time_stamp")) {
                return null;
            }
            userState.syncValues.put("loc_bg", userState.dependValues.opt("loc_bg"));
            userState.syncValues.put("loc_time_stamp", userState.dependValues.opt("loc_time_stamp"));
            return LOCATION_FIELDS_SET;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void setLocation(LocationController.LocationPoint locationPoint) {
        try {
            this.syncValues.put(i.fC, locationPoint.lat);
            this.syncValues.put("long", locationPoint.log);
            this.syncValues.put("loc_acc", locationPoint.accuracy);
            this.syncValues.put("loc_type", locationPoint.type);
            this.dependValues.put("loc_bg", locationPoint.bg);
            this.dependValues.put("loc_time_stamp", locationPoint.timeStamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public JSONObject generateJsonDiff(UserState userState, boolean z) {
        addDependFields();
        userState.addDependFields();
        JSONObject generateJsonDiff = generateJsonDiff(this.syncValues, userState.syncValues, (JSONObject) null, getGroupChangeFields(userState));
        if (!z && generateJsonDiff.toString().equals("{}")) {
            return null;
        }
        try {
            if (!generateJsonDiff.has("app_id")) {
                generateJsonDiff.put("app_id", this.syncValues.optString("app_id"));
            }
            if (this.syncValues.has("email_auth_hash")) {
                generateJsonDiff.put("email_auth_hash", this.syncValues.optString("email_auth_hash"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return generateJsonDiff;
    }

    private void loadState() {
        int i;
        boolean z;
        String str = OneSignalPrefs.PREFS_ONESIGNAL;
        String string = OneSignalPrefs.getString(str, "ONESIGNAL_USERSTATE_DEPENDVALYES_" + this.persistKey, (String) null);
        if (string == null) {
            this.dependValues = new JSONObject();
            try {
                int i2 = 1;
                if (this.persistKey.equals("CURRENT_STATE")) {
                    i = OneSignalPrefs.getInt(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_SUBSCRIPTION", 1);
                } else {
                    i = OneSignalPrefs.getInt(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_SYNCED_SUBSCRIPTION", 1);
                }
                if (i == -2) {
                    z = false;
                } else {
                    i2 = i;
                    z = true;
                }
                this.dependValues.put("subscribableStatus", i2);
                this.dependValues.put("userSubscribePref", z);
            } catch (JSONException unused) {
            }
        } else {
            try {
                this.dependValues = new JSONObject(string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String str2 = OneSignalPrefs.PREFS_ONESIGNAL;
        String string2 = OneSignalPrefs.getString(str2, "ONESIGNAL_USERSTATE_SYNCVALYES_" + this.persistKey, (String) null);
        if (string2 == null) {
            try {
                this.syncValues = new JSONObject();
                this.syncValues.put("identifier", OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_REGISTRATION_ID", (String) null));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else {
            this.syncValues = new JSONObject(string2);
        }
    }

    /* access modifiers changed from: package-private */
    public void persistState() {
        synchronized (syncLock) {
            String str = OneSignalPrefs.PREFS_ONESIGNAL;
            OneSignalPrefs.saveString(str, "ONESIGNAL_USERSTATE_SYNCVALYES_" + this.persistKey, this.syncValues.toString());
            String str2 = OneSignalPrefs.PREFS_ONESIGNAL;
            OneSignalPrefs.saveString(str2, "ONESIGNAL_USERSTATE_DEPENDVALYES_" + this.persistKey, this.dependValues.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void persistStateAfterSync(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null) {
            JSONObject jSONObject3 = this.dependValues;
            generateJsonDiff(jSONObject3, jSONObject, jSONObject3, (Set<String>) null);
        }
        if (jSONObject2 != null) {
            JSONObject jSONObject4 = this.syncValues;
            generateJsonDiff(jSONObject4, jSONObject2, jSONObject4, (Set<String>) null);
            mergeTags(jSONObject2, (JSONObject) null);
        }
        if (jSONObject != null || jSONObject2 != null) {
            persistState();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0023 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x007f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mergeTags(org.json.JSONObject r7, org.json.JSONObject r8) {
        /*
            r6 = this;
            java.lang.Object r0 = syncLock
            monitor-enter(r0)
            java.lang.String r1 = "tags"
            boolean r1 = r7.has(r1)     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x007f
            org.json.JSONObject r1 = r6.syncValues     // Catch:{ all -> 0x0081 }
            java.lang.String r2 = "tags"
            boolean r1 = r1.has(r2)     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x0029
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0023 }
            org.json.JSONObject r2 = r6.syncValues     // Catch:{ JSONException -> 0x0023 }
            java.lang.String r3 = "tags"
            java.lang.String r2 = r2.optString(r3)     // Catch:{ JSONException -> 0x0023 }
            r1.<init>(r2)     // Catch:{ JSONException -> 0x0023 }
            goto L_0x002e
        L_0x0023:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ all -> 0x0081 }
            r1.<init>()     // Catch:{ all -> 0x0081 }
            goto L_0x002e
        L_0x0029:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ all -> 0x0081 }
            r1.<init>()     // Catch:{ all -> 0x0081 }
        L_0x002e:
            java.lang.String r2 = "tags"
            org.json.JSONObject r7 = r7.optJSONObject(r2)     // Catch:{ all -> 0x0081 }
            java.util.Iterator r2 = r7.keys()     // Catch:{ all -> 0x0081 }
        L_0x0038:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x007f }
            if (r3 == 0) goto L_0x0064
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x007f }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x007f }
            java.lang.String r4 = ""
            java.lang.String r5 = r7.optString(r3)     // Catch:{ all -> 0x007f }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x007f }
            if (r4 == 0) goto L_0x0054
            r1.remove(r3)     // Catch:{ all -> 0x007f }
            goto L_0x0038
        L_0x0054:
            if (r8 == 0) goto L_0x005c
            boolean r4 = r8.has(r3)     // Catch:{ all -> 0x007f }
            if (r4 != 0) goto L_0x0038
        L_0x005c:
            java.lang.String r4 = r7.optString(r3)     // Catch:{ all -> 0x007f }
            r1.put(r3, r4)     // Catch:{ all -> 0x007f }
            goto L_0x0038
        L_0x0064:
            java.lang.String r7 = r1.toString()     // Catch:{ all -> 0x007f }
            java.lang.String r8 = "{}"
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x007f }
            if (r7 == 0) goto L_0x0078
            org.json.JSONObject r7 = r6.syncValues     // Catch:{ all -> 0x007f }
            java.lang.String r8 = "tags"
            r7.remove(r8)     // Catch:{ all -> 0x007f }
            goto L_0x007f
        L_0x0078:
            org.json.JSONObject r7 = r6.syncValues     // Catch:{ all -> 0x007f }
            java.lang.String r8 = "tags"
            r7.put(r8, r1)     // Catch:{ all -> 0x007f }
        L_0x007f:
            monitor-exit(r0)     // Catch:{ all -> 0x0081 }
            return
        L_0x0081:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0081 }
            goto L_0x0085
        L_0x0084:
            throw r7
        L_0x0085:
            goto L_0x0084
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.UserState.mergeTags(org.json.JSONObject, org.json.JSONObject):void");
    }

    private static JSONObject generateJsonDiff(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, Set<String> set) {
        JSONObject generateJsonDiff;
        synchronized (syncLock) {
            generateJsonDiff = JSONUtils.generateJsonDiff(jSONObject, jSONObject2, jSONObject3, set);
        }
        return generateJsonDiff;
    }
}
