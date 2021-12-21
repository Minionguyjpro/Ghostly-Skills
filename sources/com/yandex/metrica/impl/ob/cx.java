package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.os.Build;
import com.yandex.metrica.impl.utils.d;
import java.util.List;

public class cx {
    public List<cw> a(Context context, List<cw> list) {
        List<cw> a2 = a(context).a();
        if (d.a(a2, list)) {
            return null;
        }
        return a2;
    }

    /* access modifiers changed from: package-private */
    public cv a(Context context) {
        if (Build.VERSION.SDK_INT >= 16) {
            return new cy(context);
        }
        return new cz(context);
    }
}
