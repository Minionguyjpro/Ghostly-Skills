package com.startapp.android.publish.ads.list3d;

import com.startapp.common.a.g;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: StartAppSDK */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static f f77a = new f();
    private Map<String, e> b = new ConcurrentHashMap();

    private f() {
    }

    public static f a() {
        return f77a;
    }

    public e a(String str) {
        if (this.b.containsKey(str)) {
            return this.b.get(str);
        }
        e eVar = new e();
        this.b.put(str, eVar);
        g.a("ListModelManager", 3, "Created new model for uuid " + str + ", Size = " + this.b.size());
        return eVar;
    }

    public void b(String str) {
        this.b.remove(str);
        g.a("ListModelManager", 3, "Model for " + str + " was removed, Size = " + this.b.size());
    }
}
