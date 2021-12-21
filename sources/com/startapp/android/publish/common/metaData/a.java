package com.startapp.android.publish.common.metaData;

import java.io.Serializable;

/* compiled from: StartAppSDK */
public class a implements Serializable {
    private static final long serialVersionUID = 1;
    private int delay = 3;
    private boolean enabled = true;
    private int minApiLevel = 18;

    public a() {
    }

    public a(int i) {
        this.minApiLevel = i;
    }

    public int a() {
        return this.delay;
    }

    public int b() {
        return this.minApiLevel;
    }

    public boolean c() {
        return this.enabled;
    }
}
