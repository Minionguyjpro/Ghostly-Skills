package com.yandex.metrica.impl;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.yandex.metrica.DeferredDeeplinkParametersListener;
import com.yandex.metrica.impl.ob.bz;
import java.util.HashMap;
import java.util.Map;

public class ar {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f727a;
    private final ay b;
    private final bz c;
    private String d;
    private Map<String, String> e;
    private DeferredDeeplinkParametersListener f;
    private Handler g;

    public ar(ay ayVar, bz bzVar) {
        this.b = ayVar;
        this.c = bzVar;
        this.d = bzVar.c();
        boolean d2 = bzVar.d();
        this.f727a = d2;
        if (d2) {
            this.c.p((String) null);
            this.d = null;
            return;
        }
        d(b(this.d));
    }

    public void a(String str) {
        this.b.b(str);
        if (!this.f727a) {
            synchronized (this) {
                this.d = str;
                this.c.p(str);
                d(b(str));
                if (this.g == null) {
                    this.g = new Handler(Looper.getMainLooper());
                }
                this.g.post(new Runnable() {
                    public void run() {
                        ar.this.a();
                    }
                });
            }
        }
    }

    private void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.e = c(str);
        }
    }

    /* access modifiers changed from: package-private */
    public String b(String str) {
        return e(str).get("appmetrica_deep_link");
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> c(String str) {
        Map<String, String> e2 = e(Uri.decode(str));
        HashMap hashMap = new HashMap(e2.size());
        for (Map.Entry next : e2.entrySet()) {
            hashMap.put(Uri.decode((String) next.getKey()), Uri.decode((String) next.getValue()));
        }
        return hashMap;
    }

    private static Map<String, String> e(String str) {
        HashMap hashMap = new HashMap();
        if (str != null) {
            int lastIndexOf = str.lastIndexOf(63);
            if (lastIndexOf >= 0) {
                str = str.substring(lastIndexOf + 1);
            }
            if (str.contains("=")) {
                for (String str2 : str.split("&")) {
                    int indexOf = str2.indexOf("=");
                    if (indexOf >= 0) {
                        hashMap.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
                    } else {
                        hashMap.put(str2, "");
                    }
                }
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public void a() {
        if (!bk.a((Map) this.e)) {
            DeferredDeeplinkParametersListener deferredDeeplinkParametersListener = this.f;
            if (deferredDeeplinkParametersListener != null) {
                deferredDeeplinkParametersListener.onParametersLoaded(this.e);
                this.f = null;
            }
        } else if (this.d != null) {
            a(DeferredDeeplinkParametersListener.Error.PARSE_ERROR);
        }
    }

    private void a(DeferredDeeplinkParametersListener.Error error) {
        DeferredDeeplinkParametersListener deferredDeeplinkParametersListener = this.f;
        if (deferredDeeplinkParametersListener != null) {
            deferredDeeplinkParametersListener.onError(error, this.d);
            this.f = null;
        }
    }
}
