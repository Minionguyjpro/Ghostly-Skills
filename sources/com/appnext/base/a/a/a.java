package com.appnext.base.a.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.appnext.base.a.b;
import com.appnext.base.b.e;
import java.util.concurrent.atomic.AtomicInteger;

public class a {
    private static a dB;
    private static b dC;
    private AtomicInteger dA = new AtomicInteger(0);
    private SQLiteDatabase dD;

    /* renamed from: com.appnext.base.a.a.a$a  reason: collision with other inner class name */
    public enum C0037a {
        ;
        
        public static final int DatabaseOrDiskFull$53629b42 = 2;
        public static final int Global$53629b42 = 1;

        static {
            $VALUES$40a167d9 = new int[]{1, 2};
        }

        public static int[] af() {
            return (int[]) $VALUES$40a167d9.clone();
        }
    }

    private a(Context context) {
        dC = b.c(context);
    }

    public static a ac() {
        if (dB == null) {
            synchronized (a.class) {
                if (dB == null) {
                    dB = new a(e.getContext().getApplicationContext());
                }
            }
        }
        return dB;
    }

    public final SQLiteDatabase ad() {
        if (this.dA.incrementAndGet() == 1) {
            this.dD = dC.getWritableDatabase();
        }
        return this.dD;
    }

    public final void ae() {
        if (this.dA.decrementAndGet() == 0) {
            this.dD.close();
        }
    }

    /* renamed from: com.appnext.base.a.a.a$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] dE;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000f */
        static {
            /*
                int[] r0 = com.appnext.base.a.a.a.C0037a.af()
                int r0 = r0.length
                int[] r0 = new int[r0]
                dE = r0
                r1 = 1
                int r2 = com.appnext.base.a.a.a.C0037a.DatabaseOrDiskFull$53629b42     // Catch:{ NoSuchFieldError -> 0x000f }
                int r2 = r2 - r1
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x000f }
            L_0x000f:
                int[] r0 = dE     // Catch:{ NoSuchFieldError -> 0x0017 }
                int r2 = com.appnext.base.a.a.a.C0037a.Global$53629b42     // Catch:{ NoSuchFieldError -> 0x0017 }
                int r2 = r2 - r1
                r1 = 2
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0017 }
            L_0x0017:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appnext.base.a.a.a.AnonymousClass1.<clinit>():void");
        }
    }

    public static void a(int i, Throwable th) {
        int[] iArr = AnonymousClass1.dE;
    }
}
