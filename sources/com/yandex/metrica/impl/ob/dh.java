package com.yandex.metrica.impl.ob;

import android.content.Context;

public class dh extends dd {
    private static final dk c = new dk("PREF_KEY_OFFSET");
    private dk d;

    /* access modifiers changed from: protected */
    public String f() {
        return "_servertimeoffset";
    }

    public dh(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    public void h() {
        super.h();
        this.d = new dk(c.a(), (String) null);
    }

    public long a(int i) {
        return this.b.getLong(this.d.b(), (long) i);
    }

    public void a() {
        h(this.d.b()).k();
    }
}
