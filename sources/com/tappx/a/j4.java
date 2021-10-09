package com.tappx.a;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class j4 {

    /* renamed from: a  reason: collision with root package name */
    private static final Logger f482a = Logger.getLogger("com.tappx.mraid");
    private static final b b = new b((a) null);

    static class a implements Filter {
        a() {
        }

        public boolean isLoggable(LogRecord logRecord) {
            return true;
        }
    }

    private static final class b extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private static final Map<Level, Integer> f483a;

        static {
            HashMap hashMap = new HashMap(7);
            f483a = hashMap;
            hashMap.put(Level.FINEST, 2);
            f483a.put(Level.FINER, 2);
            f483a.put(Level.FINE, 2);
            f483a.put(Level.CONFIG, 3);
            f483a.put(Level.INFO, 4);
            f483a.put(Level.WARNING, 5);
            f483a.put(Level.SEVERE, 6);
        }

        private b() {
        }

        public void close() {
        }

        public void flush() {
        }

        public void publish(LogRecord logRecord) {
            if (isLoggable(logRecord)) {
                int intValue = f483a.containsKey(logRecord.getLevel()) ? f483a.get(logRecord.getLevel()).intValue() : 2;
                String str = logRecord.getMessage() + "\n";
                Throwable thrown = logRecord.getThrown();
                if (thrown != null) {
                    str = str + Log.getStackTraceString(thrown);
                }
                Log.println(intValue, "TappxMraid", str);
            }
        }

        /* synthetic */ b(a aVar) {
            this();
        }
    }

    static {
        f482a.setUseParentHandlers(false);
        f482a.setLevel(Level.ALL);
        b.setLevel(Level.FINE);
        b.setFilter(new a());
        LogManager.getLogManager().addLogger(f482a);
        a(f482a, (Handler) b);
    }

    public static void a(String str) {
        a(str, (Throwable) null);
    }

    public static void b(String str) {
        b(str, (Throwable) null);
    }

    public static void c(String str) {
        c(str, (Throwable) null);
    }

    public static void d(String str) {
        d(str, (Throwable) null);
    }

    public static void a(String str, Throwable th) {
        f482a.log(Level.CONFIG, str, th);
    }

    public static void b(String str, Throwable th) {
        f482a.log(Level.SEVERE, str, th);
    }

    public static void c(String str, Throwable th) {
        f482a.log(Level.FINE, str, th);
    }

    public static void d(String str, Throwable th) {
        f482a.log(Level.WARNING, str, th);
    }

    private static void a(Logger logger, Handler handler) {
        Handler[] handlers = logger.getHandlers();
        int length = handlers.length;
        int i = 0;
        while (i < length) {
            if (!handlers[i].equals(handler)) {
                i++;
            } else {
                return;
            }
        }
        logger.addHandler(handler);
    }
}
