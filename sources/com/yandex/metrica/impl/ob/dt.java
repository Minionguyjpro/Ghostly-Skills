package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.utils.g;
import java.util.HashMap;
import java.util.Map;

public class dt {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, Integer> f864a = new HashMap();

    public void a(String str, int i) {
        this.f864a.put(str, Integer.valueOf(i));
    }

    public String a() {
        return g.a((Map) this.f864a);
    }
}
