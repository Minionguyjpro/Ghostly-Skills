package com.startapp.android.publish.adsCommon.adinformation;

import com.startapp.android.publish.adsCommon.adinformation.AdInformationPositions;
import com.startapp.common.c.f;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class c implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean enable = true;
    private boolean enableOverride = false;
    @f(b = AdInformationPositions.Position.class)
    private AdInformationPositions.Position position = AdInformationPositions.Position.getByName(AdInformationPositions.f202a);
    private boolean positionOverride = false;

    private c() {
    }

    public static c a() {
        return new c();
    }

    public boolean b() {
        return this.enable;
    }

    public void a(boolean z) {
        this.enable = z;
        this.enableOverride = true;
    }

    public AdInformationPositions.Position c() {
        return this.position;
    }

    public void a(AdInformationPositions.Position position2) {
        this.position = position2;
        if (position2 != null) {
            this.positionOverride = true;
        } else {
            this.positionOverride = false;
        }
    }

    public boolean d() {
        return this.positionOverride;
    }

    public boolean e() {
        return this.enableOverride;
    }
}
