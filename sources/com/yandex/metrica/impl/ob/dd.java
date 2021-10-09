package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

public abstract class dd {
    private static final dk c = new dk("UNDEFINED_");

    /* renamed from: a  reason: collision with root package name */
    protected final String f845a;
    protected final SharedPreferences b;
    private final Map<String, Object> d = new HashMap();
    private boolean e = false;

    /* access modifiers changed from: protected */
    public abstract String f();

    public dd(Context context, String str) {
        this.f845a = str;
        this.b = a(context);
        h();
    }

    /* access modifiers changed from: protected */
    public void h() {
        new dk(c.a(), this.f845a);
    }

    /* access modifiers changed from: protected */
    public SharedPreferences a(Context context) {
        return dl.a(context, f());
    }

    /* access modifiers changed from: protected */
    public <T extends dd> T a(String str, Object obj) {
        synchronized (this) {
            if (obj != null) {
                this.d.put(str, obj);
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public <T extends dd> T h(String str) {
        synchronized (this) {
            this.d.put(str, this);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public <T extends dd> T i() {
        synchronized (this) {
            this.e = true;
            this.d.clear();
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public String j() {
        return this.f845a;
    }

    public void k() {
        synchronized (this) {
            SharedPreferences.Editor edit = this.b.edit();
            if (this.e) {
                edit.clear();
                edit.apply();
            } else {
                for (Map.Entry next : this.d.entrySet()) {
                    String str = (String) next.getKey();
                    Object value = next.getValue();
                    if (value == this) {
                        edit.remove(str);
                    } else if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    } else if (value != null) {
                        throw new UnsupportedOperationException();
                    }
                }
                edit.apply();
            }
            this.d.clear();
            this.e = false;
        }
    }
}
