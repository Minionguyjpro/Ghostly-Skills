package com.startapp.android.publish.adsCommon.e;

import android.content.Context;
import android.text.TextUtils;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.adsCommon.f.g;
import com.startapp.common.b.a.b;
import com.startapp.common.b.b;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.Map;

/* compiled from: StartAppSDK */
public class a implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private final Thread.UncaughtExceptionHandler f234a = Thread.getDefaultUncaughtExceptionHandler();

    /* renamed from: com.startapp.android.publish.adsCommon.e.a$a  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    public static final class C0004a implements com.startapp.common.b.a.a {
        public b create(int i) {
            if (i != 868418937) {
                return null;
            }
            return new b() {
                public void a(Context context, int i, Map<String, String> map, final b.C0011b bVar) {
                    String str = map.get("KEY_STACK_TRACE");
                    if (!TextUtils.isEmpty(str)) {
                        if (str.length() > 1000) {
                            str = str.substring(0, 1000);
                        }
                        Context context2 = context;
                        f.a(context2, d.EXCEPTION, "uncaughtException", str, "", new g.a() {
                            public void a(boolean z) {
                                bVar.a(b.a.SUCCESS);
                            }
                        });
                    }
                }
            };
        }
    }

    public static synchronized void a(Context context) {
        synchronized (a.class) {
            new a(context);
        }
    }

    private a(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        com.startapp.common.b.a.a(context);
        com.startapp.common.b.a.a((com.startapp.common.b.a.a) new C0004a());
    }

    public void uncaughtException(Thread thread, Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        if (!TextUtils.isEmpty(stringWriter2) && (stringWriter2.contains("startapp") || stringWriter2.contains("truenet"))) {
            com.startapp.common.b.a.a(new b.a(868418937).a(1000).a("KEY_STACK_TRACE", stringWriter2).a());
        }
        this.f234a.uncaughtException(thread, th);
    }
}
