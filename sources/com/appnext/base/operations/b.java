package com.appnext.base.operations;

import android.os.Bundle;
import com.appnext.base.a.b.c;
import com.appnext.base.b.i;
import com.appnext.base.operations.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class b {
    private static final String eq = "com.appnext.base.operations.imp";
    private static volatile b er;
    private List<a> es = new ArrayList();

    public static b aI() {
        if (er == null) {
            synchronized (b.class) {
                if (er == null) {
                    er = new b();
                }
            }
        }
        return er;
    }

    private b() {
    }

    private static a a(String str, c cVar, Bundle bundle, Object obj) {
        if (cVar == null) {
            return null;
        }
        try {
            Object newInstance = Class.forName(B(str)).getConstructor(new Class[]{c.class, Bundle.class, Object.class}).newInstance(new Object[]{cVar, bundle, obj});
            if (newInstance instanceof a) {
                return (a) newInstance;
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public final void a(String str, c cVar, Bundle bundle, Object obj, a.C0038a aVar) {
        if (cVar != null) {
            try {
                a a2 = a(str, cVar, bundle, obj);
                if (a2 != null) {
                    synchronized (this) {
                        this.es.add(a2);
                    }
                    a2.a(aVar);
                    i aR = i.aR();
                    aR.putLong(cVar.getKey() + i.fy, System.currentTimeMillis());
                    a2.aC();
                }
            } catch (Throwable unused) {
            }
        }
    }

    public final void b(String str, c cVar, Bundle bundle, Object obj) {
        if (cVar != null) {
            try {
                a(str, cVar, (Bundle) null, (Object) null);
            } catch (Throwable unused) {
            }
        }
    }

    public final void a(a aVar) {
        synchronized (this) {
            this.es.remove(aVar);
        }
    }

    public final void aJ() {
        synchronized (this) {
            Iterator<a> it = this.es.iterator();
            while (it.hasNext()) {
                it.next();
            }
            this.es.clear();
        }
    }

    public static String B(String str) {
        return "com.appnext.base.operations.imp." + str;
    }
}
