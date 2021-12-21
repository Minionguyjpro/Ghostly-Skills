package com.yandex.metrica.impl.ob;

import java.util.Collections;
import java.util.List;

public class w<BaseHandler> extends x<BaseHandler> {

    /* renamed from: a  reason: collision with root package name */
    private final List<BaseHandler> f941a;

    public w(List<BaseHandler> list) {
        this.f941a = Collections.unmodifiableList(list);
    }

    public List<? extends BaseHandler> a() {
        return this.f941a;
    }
}
