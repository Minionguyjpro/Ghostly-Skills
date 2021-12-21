package com.tappx.a;

import android.util.Log;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class j0 {

    /* renamed from: a  reason: collision with root package name */
    private static final Logger f477a = Logger.getLogger("com.tappx");
    private static final c b = new c();
    private static boolean c = false;
    private static long d;
    private static Set<b> e;
    private static f f;

    public interface b {
        void a(String str);
    }

    private static final class c extends Handler {
        private c() {
        }

        public void close() {
        }

        public void flush() {
        }

        public void publish(LogRecord logRecord) {
            String str = logRecord.getMessage() + "\n";
            Throwable thrown = logRecord.getThrown();
            if (thrown != null) {
                str = str + Log.getStackTraceString(thrown);
            }
            Log.println(2, "tappx_v3.1.8", str);
        }
    }

    static {
        f477a.setLevel(Level.ALL);
        f477a.addHandler(b);
        LogManager.getLogManager().addLogger(f477a);
    }

    public static void a(String str, Object... objArr) {
        a(Level.FINE, str, objArr);
    }

    public static void b(String str, Object... objArr) {
        Level level = Level.SEVERE;
        a(level, "Tappx Error: " + str, objArr);
    }

    public static void c(String str, Object... objArr) {
        if (c) {
            long currentTimeMillis = System.currentTimeMillis() - d;
            d = System.currentTimeMillis();
            str = "(+" + currentTimeMillis + " ms) " + str;
            e(str, objArr);
        }
        try {
            a(Level.FINE, String.format(str, objArr), new Object[0]);
        } catch (Exception unused) {
        }
    }

    public static void d(String str, Object... objArr) {
        f fVar = f;
        if (fVar != null) {
            c(fVar.a(str), objArr);
        }
    }

    private static void e(String str, Object[] objArr) {
        Set<b> set = e;
        if (set != null) {
            for (b next : set) {
                if (next != null) {
                    try {
                        next.a(String.format(str, objArr));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    public static void f(String str, Object... objArr) {
        a(Level.WARNING, str, objArr);
    }

    private static void a(Level level, String str, Object... objArr) {
        try {
            f477a.log(level, String.format(str, objArr));
        } catch (Exception unused) {
        }
    }

    public static void a(String str) {
        if (str == null) {
            f = null;
        } else {
            f = new f(str);
        }
    }
}
