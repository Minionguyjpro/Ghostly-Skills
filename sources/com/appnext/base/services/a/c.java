package com.appnext.base.services.a;

import android.os.Bundle;
import android.text.TextUtils;
import com.appnext.base.b.d;
import com.appnext.base.b.i;
import com.appnext.base.b.j;
import com.appnext.base.operations.b;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public abstract class c {
    public static final String eH = "more_data";
    private static final long eI = 1000;
    private static final long eJ = 60000;
    private static final long eK = 3600000;
    private static final long eL = 86400000;

    /* access modifiers changed from: protected */
    public abstract void a(com.appnext.base.a.b.c cVar, long j, long j2);

    /* access modifiers changed from: protected */
    public abstract void a(com.appnext.base.a.b.c cVar, long j, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void b(com.appnext.base.a.b.c cVar);

    /* access modifiers changed from: protected */
    public abstract void b(com.appnext.base.a.b.c cVar, long j, long j2);

    /* access modifiers changed from: protected */
    public abstract void g(List<com.appnext.base.a.b.c> list);

    public final void a(com.appnext.base.a.b.c cVar, boolean z, Bundle bundle) {
        if (cVar != null) {
            try {
                if (TextUtils.isEmpty(cVar.ak())) {
                    return;
                }
                if (!cVar.ak().equals(d.ff)) {
                    long j = 0;
                    if (cVar.al() != null && cVar.am().equals(d.fl)) {
                        if (!z) {
                            j = D(cVar.al());
                        }
                        if (j != -1) {
                            b(cVar, j + a(-1800000, 1800000), eL);
                        }
                    } else if (cVar.ao() != null && cVar.ao().equals(d.fn)) {
                        long g = (long) j.g(cVar.al(), cVar.am());
                        if (g != -1) {
                            i aR = i.aR();
                            long j2 = aR.getLong(cVar.getKey() + i.fy, 0);
                            if (j2 != 0) {
                                if (!z) {
                                    a(cVar, g + j2, g);
                                    return;
                                }
                            }
                            a(cVar, System.currentTimeMillis(), g);
                        }
                    } else if (cVar.ao() != null && cVar.ao().equals(d.fm)) {
                        a(cVar, System.currentTimeMillis(), (Bundle) null);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    private static long D(String str) {
        try {
            if (str.length() != 4) {
                return -1;
            }
            int parseInt = Integer.parseInt(str.substring(0, 2));
            int parseInt2 = Integer.parseInt(str.substring(2, 4));
            Calendar instance = Calendar.getInstance();
            instance.set(11, parseInt);
            instance.set(12, parseInt2);
            instance.set(13, 0);
            if (new Date().after(instance.getTime())) {
                instance.add(5, 1);
            }
            return instance.getTimeInMillis();
        } catch (Throwable unused) {
            return -1;
        }
    }

    private static long a(long j, long j2) {
        try {
            return 1800000 - ((long) new Random().nextInt((int) (Math.abs(-1800000) + 1800000)));
        } catch (Throwable unused) {
            return -1800000;
        }
    }

    public final void c(com.appnext.base.a.b.c cVar) {
        if (cVar != null) {
            try {
                b.aI().b(cVar.getKey(), cVar, (Bundle) null, (Object) null);
                b(cVar);
            } catch (Throwable unused) {
            }
        }
    }

    public final void h(List<com.appnext.base.a.b.c> list) {
        if (list != null) {
            try {
                for (com.appnext.base.a.b.c next : list) {
                    b.aI().b(next.getKey(), next, (Bundle) null, (Object) null);
                }
                g(list);
            } catch (Throwable unused) {
            }
        }
    }
}
