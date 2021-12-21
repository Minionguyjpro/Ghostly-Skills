package com.startapp.android.publish.common.metaData;

import java.io.Serializable;

/* compiled from: StartAppSDK */
public class LocationConfig implements Serializable {
    private static final boolean DEFAULT_CO_ENABLED = false;
    private static final boolean DEFAULT_FI_ENABLED = false;
    private static final long serialVersionUID = 1;
    private boolean coEnabled = false;
    private boolean fiEnabled = false;

    public boolean isFiEnabled() {
        return this.fiEnabled;
    }

    public boolean isCoEnabled() {
        return this.coEnabled;
    }
}
