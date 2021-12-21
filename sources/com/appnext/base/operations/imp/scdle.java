package com.appnext.base.operations.imp;

import android.os.Bundle;
import com.appnext.base.a.a;
import com.appnext.base.a.b.b;
import com.appnext.base.b.d;
import com.appnext.base.b.e;
import com.appnext.base.operations.c;
import java.util.List;

public class scdle extends c {
    public scdle(com.appnext.base.a.b.c cVar, Bundle bundle, Object obj) {
        super(cVar, bundle, obj);
    }

    /* access modifiers changed from: protected */
    public final String getKey() {
        return scdle.class.getSimpleName();
    }

    /* access modifiers changed from: protected */
    public List<b> getData() {
        try {
            List<com.appnext.base.a.b.c> as = a.X().ab().as();
            if (as == null) {
                return null;
            }
            for (com.appnext.base.a.b.c next : as) {
                if (!next.getKey().equals(scdle.class.getSimpleName()) && next.ao().equals(d.fm)) {
                    com.appnext.base.services.b.a.d(e.getContext()).c(next);
                    com.appnext.base.services.b.a.d(e.getContext()).a(next, true);
                }
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
