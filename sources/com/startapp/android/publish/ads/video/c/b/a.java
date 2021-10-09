package com.startapp.android.publish.ads.video.c.b;

import android.text.TextUtils;
import com.startapp.android.publish.ads.video.c.a.a.b;
import com.startapp.android.publish.ads.video.c.a.c;
import com.startapp.android.publish.ads.video.c.a.e;
import com.startapp.common.a.g;
import java.util.List;

/* compiled from: StartAppSDK */
public class a {
    public static b a(e eVar, c cVar) {
        g.a("VASTModelPostValidator", 3, "validate");
        b bVar = null;
        if (!a(eVar)) {
            g.a("VASTModelPostValidator", 3, "Validator returns: not valid (invalid model)");
            return null;
        }
        if (cVar != null) {
            b a2 = cVar.a(eVar.b());
            if (a2 != null) {
                String a3 = a2.a();
                if (!TextUtils.isEmpty(a3)) {
                    g.a("VASTModelPostValidator", 3, "mediaPicker selected mediaFile with URL " + a3);
                    bVar = a2;
                }
            }
        } else {
            g.a("VASTModelPostValidator", 5, "mediaPicker: We don't have a compatible media file to play.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Validator returns: ");
        sb.append(bVar != null ? "valid" : "not valid (no media file)");
        g.a("VASTModelPostValidator", 3, sb.toString());
        return bVar;
    }

    public static boolean a(e eVar) {
        g.a("VASTModelPostValidator", 3, "validateModel");
        List<String> d = eVar.d();
        boolean z = (d == null || d.size() == 0) ? false : true;
        List<b> b = eVar.b();
        if (b != null && b.size() != 0) {
            return z;
        }
        g.a("VASTModelPostValidator", 3, "Validator error: mediaFile list invalid");
        return false;
    }
}
