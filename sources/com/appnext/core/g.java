package com.appnext.core;

import java.io.Serializable;

public class g implements Serializable {
    private static final long serialVersionUID = 8086013010302241826L;
    private int adID = -1;
    private String adJSON = "";
    private String placementID = "";
    private String sid = "";

    /* access modifiers changed from: protected */
    public int getAdID() {
        return this.adID;
    }

    /* access modifiers changed from: protected */
    public void setAdID(int i) {
        this.adID = i;
    }

    /* access modifiers changed from: protected */
    public String getAdJSON() {
        return this.adJSON;
    }

    /* access modifiers changed from: protected */
    public void setAdJSON(String str) {
        this.adJSON = str;
    }

    /* access modifiers changed from: protected */
    public String getPlacementID() {
        return this.placementID;
    }

    /* access modifiers changed from: protected */
    public void setPlacementID(String str) {
        this.placementID = str;
    }

    /* access modifiers changed from: protected */
    public String getSession() {
        return this.sid;
    }

    /* access modifiers changed from: protected */
    public void setSession(String str) {
        this.sid = str;
    }
}
