package com.startapp.android.publish.ads.splash;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import com.startapp.android.publish.ads.a.b;
import com.startapp.android.publish.common.model.AdPreferences;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class g extends b {
    private SplashConfig d = null;
    private h e;
    private boolean f = false;
    private boolean g = false;

    public void q() {
    }

    public void a(Bundle bundle) {
        com.startapp.common.a.g.a("SplashMode", 3, "onCreate");
        this.d = (SplashConfig) a().getSerializableExtra("SplashConfig");
    }

    public boolean a(int i, KeyEvent keyEvent) {
        com.startapp.common.a.g.a("SplashMode", 3, "onKeyDown");
        if (this.f) {
            if (i == 25) {
                if (!this.g) {
                    this.g = true;
                    this.e.g();
                    Toast.makeText(b(), "Test Mode", 0).show();
                    return true;
                }
            } else if (i == 24 && this.g) {
                b().finish();
                return true;
            }
        }
        return i == 4;
    }

    public void s() {
        com.startapp.common.a.g.a("SplashMode", 3, "onPause");
        h hVar = this.e;
        if (hVar != null) {
            hVar.a();
        }
    }

    public void t() {
        com.startapp.common.a.g.a("SplashMode", 3, "onStop");
        h hVar = this.e;
        if (hVar != null) {
            hVar.b();
        }
    }

    public void u() {
        AdPreferences adPreferences;
        com.startapp.common.a.g.a("SplashMode", 3, "onResume");
        if (this.d != null) {
            Serializable serializableExtra = a().getSerializableExtra("AdPreference");
            if (serializableExtra != null) {
                adPreferences = (AdPreferences) serializableExtra;
            } else {
                adPreferences = new AdPreferences();
            }
            this.f = a().getBooleanExtra("testMode", false);
            h hVar = new h(b(), this.d, adPreferences);
            this.e = hVar;
            hVar.a((Bundle) null);
        }
    }

    public void v() {
        com.startapp.common.a.g.a("SplashMode", 3, "onDestroy");
    }
}
