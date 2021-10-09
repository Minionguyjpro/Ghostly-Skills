package com.yandex.metrica.impl.ob;

import android.text.TextUtils;

public class cu {

    /* renamed from: a  reason: collision with root package name */
    private final String f838a;

    public cu(String str) {
        this.f838a = str;
    }

    public cq a(String str) {
        if (TextUtils.isEmpty(this.f838a) || !co.a().c()) {
            return new cr(str);
        }
        return new cs(str);
    }
}
