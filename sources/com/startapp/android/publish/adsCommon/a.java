package com.startapp.android.publish.adsCommon;

import android.app.Activity;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class a implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean isActivityFullScreen;

    public a(Activity activity) {
        a(c.a(activity));
    }

    public boolean a() {
        return this.isActivityFullScreen;
    }

    private void a(boolean z) {
        this.isActivityFullScreen = z;
    }
}
