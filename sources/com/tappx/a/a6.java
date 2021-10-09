package com.tappx.a;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class a6 {

    /* renamed from: a  reason: collision with root package name */
    public static String f373a = "Volley";
    public static boolean b = Log.isLoggable("Volley", 2);
    private static final String c = a6.class.getName();

    public static void a(Throwable th, String str, Object... objArr) {
        Log.e(f373a, a(str, objArr), th);
    }

    public static void b(String str, Object... objArr) {
        Log.d(f373a, a(str, objArr));
    }

    public static void c(String str, Object... objArr) {
        Log.e(f373a, a(str, objArr));
    }

    public static void d(String str, Object... objArr) {
        if (b) {
            Log.v(f373a, a(str, objArr));
        }
    }

    private static String a(String str, Object... objArr) {
        String str2;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                str2 = "<unknown>";
                break;
            } else if (!stackTrace[i].getClassName().equals(c)) {
                String className = stackTrace[i].getClassName();
                String substring = className.substring(className.lastIndexOf(46) + 1);
                str2 = substring.substring(substring.lastIndexOf(36) + 1) + "." + stackTrace[i].getMethodName();
                break;
            } else {
                i++;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", new Object[]{Long.valueOf(Thread.currentThread().getId()), str2, str});
    }

    static class a {
        public static final boolean c = a6.b;

        /* renamed from: a  reason: collision with root package name */
        private final List<C0013a> f374a = new ArrayList();
        private boolean b = false;

        /* renamed from: com.tappx.a.a6$a$a  reason: collision with other inner class name */
        private static class C0013a {

            /* renamed from: a  reason: collision with root package name */
            public final String f375a;
            public final long b;
            public final long c;

            public C0013a(String str, long j, long j2) {
                this.f375a = str;
                this.b = j;
                this.c = j2;
            }
        }

        a() {
        }

        public synchronized void a(String str, long j) {
            if (!this.b) {
                this.f374a.add(new C0013a(str, j, SystemClock.elapsedRealtime()));
            } else {
                throw new IllegalStateException("Marker added to finished log");
            }
        }

        /* access modifiers changed from: protected */
        public void finalize() {
            if (!this.b) {
                a("Request on the loose");
                a6.c("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        public synchronized void a(String str) {
            this.b = true;
            long a2 = a();
            if (a2 > 0) {
                long j = this.f374a.get(0).c;
                a6.b("(%-4d ms) %s", Long.valueOf(a2), str);
                for (C0013a next : this.f374a) {
                    long j2 = next.c;
                    a6.b("(+%-4d) [%2d] %s", Long.valueOf(j2 - j), Long.valueOf(next.b), next.f375a);
                    j = j2;
                }
            }
        }

        private long a() {
            if (this.f374a.size() == 0) {
                return 0;
            }
            long j = this.f374a.get(0).c;
            List<C0013a> list = this.f374a;
            return list.get(list.size() - 1).c - j;
        }
    }
}
