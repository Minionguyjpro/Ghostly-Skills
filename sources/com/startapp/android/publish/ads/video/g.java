package com.startapp.android.publish.ads.video;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.startapp.android.publish.ads.video.c;
import com.startapp.android.publish.adsCommon.b;
import java.net.URL;

/* compiled from: StartAppSDK */
public class g {

    /* renamed from: a  reason: collision with root package name */
    protected Context f161a;
    protected URL b;
    protected String c;
    protected a d;
    protected c.a e;

    /* compiled from: StartAppSDK */
    public interface a {
        void a(String str);
    }

    public g(Context context, URL url, String str, a aVar, c.a aVar2) {
        this.f161a = context;
        this.b = url;
        this.c = str;
        this.d = aVar;
        this.e = aVar2;
    }

    public void a() {
        final String str;
        try {
            str = b.a().H().i() ? c.a().a(this.f161a, this.b, this.c, this.e) : h.a(this.f161a, this.b, this.c);
        } catch (Exception unused) {
            str = null;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (g.this.d != null) {
                    g.this.d.a(str);
                }
            }
        });
    }
}
