package org.altbeacon.beacon.logging;

import android.util.Log;

final class VerboseAndroidLogger extends AbstractAndroidLogger {
    VerboseAndroidLogger() {
    }

    public void d(String str, String str2, Object... objArr) {
        Log.d(str, formatString(str2, objArr));
    }

    public void i(String str, String str2, Object... objArr) {
        Log.i(str, formatString(str2, objArr));
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
