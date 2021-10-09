package com.onesignal;

import org.json.JSONObject;

public class OSEmailSubscriptionState implements Cloneable {
    private String emailAddress;
    private String emailUserId;
    OSObservable<Object, OSEmailSubscriptionState> observable = new OSObservable<>("changed", false);

    OSEmailSubscriptionState(boolean z) {
        if (z) {
            this.emailUserId = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_ONESIGNAL_EMAIL_ID_LAST", (String) null);
            this.emailAddress = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_ONESIGNAL_EMAIL_ADDRESS_LAST", (String) null);
            return;
        }
        this.emailUserId = OneSignal.getEmailId();
        this.emailAddress = OneSignalStateSynchronizer.getEmailStateSynchronizer().getRegistrationId();
    }

    /* access modifiers changed from: package-private */
    public void setEmailUserId(String str) {
        boolean z = true;
        if (str != null ? str.equals(this.emailUserId) : this.emailUserId == null) {
            z = false;
        }
        this.emailUserId = str;
        if (z) {
            this.observable.notifyChange(this);
        }
    }

    public boolean getSubscribed() {
        return (this.emailUserId == null || this.emailAddress == null) ? false : true;
    }

    /* access modifiers changed from: protected */
    public Object clone() {
        try {
            return super.clone();
        } catch (Throwable unused) {
            return null;
        }
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.emailUserId != null) {
                jSONObject.put("emailUserId", this.emailUserId);
            } else {
                jSONObject.put("emailUserId", JSONObject.NULL);
            }
            if (this.emailAddress != null) {
                jSONObject.put("emailAddress", this.emailAddress);
            } else {
                jSONObject.put("emailAddress", JSONObject.NULL);
            }
            jSONObject.put("subscribed", getSubscribed());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
