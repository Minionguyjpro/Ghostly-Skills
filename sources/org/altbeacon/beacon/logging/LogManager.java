package org.altbeacon.beacon.logging;

public final class LogManager {
    private static Logger sLogger = Loggers.infoLogger();
    private static boolean sVerboseLoggingEnabled = false;

    public static boolean isVerboseLoggingEnabled() {
        return sVerboseLoggingEnabled;
    }

    public static void d(String str, String str2, Object... objArr) {
        sLogger.d(str, str2, objArr);
    }

    public static void i(String str, String str2, Object... objArr) {
        sLogger.i(str, str2, objArr);
    }

    public static void w(String str, String str2, Object... objArr) {
        sLogger.w(str, str2, objArr);
    }

    public static void w(Throwable th, String str, String str2, Object... objArr) {
        sLogger.w(th, str, str2, objArr);
    }

    public static void e(String str, String str2, Object... objArr) {
        sLogger.e(str, str2, objArr);
    }

    public static void e(Throwable th, String str, String str2, Object... objArr) {
        sLogger.e(th, str, str2, objArr);
    }
}
