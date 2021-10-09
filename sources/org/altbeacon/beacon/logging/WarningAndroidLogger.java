package org.altbeacon.beacon.logging;

import android.util.Log;

final class WarningAndroidLogger extends AbstractAndroidLogger {
    public void d(String str, String str2, Object... objArr) {
    }

    public void i(String str, String str2, Object... objArr) {
    }

    WarningAndroidLogger() {
    }

    public void w(String str, String str2, Object... objArr) {
        Log.w(str, formatString(str2, objArr));
    }

    public void w(Throwable th, String str, String str2, Object... objArr) {
        Log.w(str, formatString(str2, objArr), th);
    }

    public void e(String str, String str2, Object... objArr) {
        Log.e(str, formatString(str2, objArr));
    }

    public void e(Throwable th, String str, String str2, Object... objArr) {
        Log.e(str, formatString(str2, objArr), th);
    }
}
