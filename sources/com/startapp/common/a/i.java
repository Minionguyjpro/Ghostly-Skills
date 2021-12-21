package com.startapp.common.a;

import android.content.Context;
import android.widget.Toast;

/* compiled from: StartAppSDK */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static i f343a = new i();
    private Toast b;

    private i() {
    }

    public void a(Context context, String str) {
        Toast toast = this.b;
        if (toast == null) {
            this.b = Toast.makeText(context, str, 0);
        } else {
            toast.setText(str);
            this.b.setDuration(0);
        }
        this.b.show();
    }

    public static i a() {
        return f343a;
    }
}
