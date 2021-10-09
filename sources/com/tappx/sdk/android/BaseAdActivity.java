package com.tappx.sdk.android;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import com.tappx.a.y4;

public class BaseAdActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    private y4 f643a;

    private void a() {
        if (Build.VERSION.SDK_INT >= 14) {
            getWindow().setFlags(16777216, 16777216);
        }
    }

    public void onBackPressed() {
        if (this.f643a.d()) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        y4 y4Var = new y4(this);
        this.f643a = y4Var;
        y4Var.a(bundle);
        a();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.f643a.a();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.f643a.b();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.f643a.c();
    }
}
