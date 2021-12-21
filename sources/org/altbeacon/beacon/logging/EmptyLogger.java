package org.altbeacon.beacon.logging;

final class EmptyLogger implements Logger {
    public void d(String str, String str2, Object... objArr) {
    }

    public void e(String str, String str2, Object... objArr) {
    }

    public void e(Throwable th, String str, String str2, Object... objArr) {
    }

    public void i(String str, String str2, Object... objArr) {
    }

    public void w(String str, String str2, Object... objArr) {
    }

    public void w(Throwable th, String str, String str2, Object... objArr) {
    }

    EmptyLogger() {
    }
}
