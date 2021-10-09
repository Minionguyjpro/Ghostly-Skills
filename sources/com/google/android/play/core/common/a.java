package com.google.android.play.core.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, Object> f1111a = new HashMap();
    private final AtomicBoolean b = new AtomicBoolean(false);

    private final synchronized void b() {
        this.f1111a.put("assetOnlyUpdates", false);
    }

    public final synchronized boolean a() {
        if (!this.b.get()) {
            b();
        }
        Object obj = this.f1111a.get("assetOnlyUpdates");
        if (!(obj instanceof Boolean)) {
            return false;
        }
        return ((Boolean) obj).booleanValue();
    }
}
