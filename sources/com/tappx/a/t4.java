package com.tappx.a;

import android.net.Uri;
import com.tappx.a.x3;

public class t4 {
    public boolean a(String str, x3.d dVar) {
        Uri parse = Uri.parse(str);
        if (!"tappx".equalsIgnoreCase(parse.getScheme())) {
            return false;
        }
        String host = parse.getHost();
        if ("noFillAd".equalsIgnoreCase(host)) {
            dVar.a();
            return true;
        } else if (!"loadFinished".equalsIgnoreCase(host)) {
            return true;
        } else {
            dVar.c();
            return true;
        }
    }
}
