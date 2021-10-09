package com.yandex.metrica.impl.utils;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.c;
import com.yandex.metrica.impl.bi;
import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.p;
import java.util.Locale;

public final class j extends c {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f956a = {3, 6, 4};
    private static final j b = new j();
    private static String c = "";

    public String d() {
        return "AppMetrica";
    }

    public j() {
        super(false);
    }

    public static j f() {
        return b;
    }

    public static void a(Context context) {
        c = String.format("[%s] : ", new Object[]{context.getPackageName()});
    }

    /* access modifiers changed from: package-private */
    public String c(String str, Object[] objArr) {
        return String.format(Locale.US, str, objArr);
    }

    /* access modifiers changed from: package-private */
    public String e() {
        return bi.b(c, "");
    }

    public void a(h hVar, String str) {
        if (p.b(hVar.c())) {
            StringBuilder sb = new StringBuilder(str);
            sb.append(": ");
            sb.append(hVar.a());
            if (p.c(hVar.c()) && !TextUtils.isEmpty(hVar.b())) {
                sb.append(" with value ");
                sb.append(hVar.b());
            }
            a(sb.toString());
        }
    }

    public void a(c.a.d dVar, String str) {
        for (c.a.d.C0025a a2 : dVar.d) {
            a(a2, str);
        }
    }

    public void a(c.a.d.C0025a aVar, String str) {
        String str2;
        int[] iArr = f956a;
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (aVar.d == iArr[i]) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(": ");
            if (aVar.d == 3 && TextUtils.isEmpty(aVar.e)) {
                str2 = p.a.EVENT_TYPE_NATIVE_CRASH.b();
            } else if (aVar.d == 4) {
                StringBuilder sb2 = new StringBuilder(aVar.e);
                if (aVar.f != null) {
                    String str3 = new String(aVar.f);
                    if (!TextUtils.isEmpty(str3)) {
                        sb2.append(" with value ");
                        sb2.append(str3);
                    }
                }
                str2 = sb2.toString();
            } else {
                str2 = aVar.e;
            }
            sb.append(str2);
            a(sb.toString());
        }
    }
}
