package com.appnext.base.operations.imp;

import android.os.Bundle;
import com.appnext.base.a.a;
import com.appnext.base.a.b.b;
import com.appnext.base.b.d;
import com.appnext.base.operations.c;
import com.appnext.core.f;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;

public class sals extends c {
    /* access modifiers changed from: protected */
    public final boolean aA() {
        return false;
    }

    public final boolean aF() {
        return true;
    }

    public sals(com.appnext.base.a.b.c cVar, Bundle bundle, Object obj) {
        super(cVar, bundle, obj);
    }

    /* access modifiers changed from: protected */
    public final String getKey() {
        return sals.class.getSimpleName();
    }

    /* access modifiers changed from: protected */
    public List<b> getData() {
        try {
            String a2 = f.a(d.fa, (HashMap<String, String>) null, true, 60000);
            a.X().Z().delete();
            a.X().Z().a(new JSONArray(a2));
        } catch (Throwable th) {
            th.getMessage();
        }
        return null;
    }
}
