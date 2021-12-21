package com.startapp.android.publish.adsCommon.Utils;

import java.util.Set;

/* compiled from: StartAppSDK */
public class NameValueObject {
    private String name;
    private String value;
    private Set<String> valueSet;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public Set<String> getValueSet() {
        return this.valueSet;
    }

    public void setValueSet(Set<String> set) {
        this.valueSet = set;
    }

    public String toString() {
        return "NameValueObject [name=" + this.name + ", value=" + this.value + ", valueSet=" + this.valueSet + "]";
    }
}
