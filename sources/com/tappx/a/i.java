package com.tappx.a;

import android.content.Context;
import com.tappx.a.b1;
import com.tappx.a.d1;
import com.tappx.a.l1;
import com.tappx.a.n;
import com.tappx.a.u0;
import java.util.ArrayList;
import java.util.List;

public class i {
    private static volatile i b;

    /* renamed from: a  reason: collision with root package name */
    private final c f463a;

    public i(Context context) {
        this.f463a = c.a(context);
    }

    public static i a(Context context) {
        i iVar = b;
        if (iVar == null) {
            synchronized (i.class) {
                iVar = b;
                if (iVar == null) {
                    iVar = new i(context);
                }
            }
        }
        return iVar;
    }

    private v c() {
        return this.f463a.l();
    }

    private t2 d() {
        return this.f463a.e();
    }

    private List<d1.a> e() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new b1.a(d()));
        arrayList.add(new l1.c(d(), c()));
        arrayList.add(new u0.b(d()));
        return arrayList;
    }

    public e1 b() {
        return new f1(e());
    }

    public f3 a() {
        return new f3(n.a.h, n.a.i, n.a.j);
    }
}
