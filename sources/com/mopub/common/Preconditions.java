package com.mopub.common;

import android.os.Looper;
import com.mopub.common.logging.MoPubLog;
import java.util.IllegalFormatException;

public final class Preconditions {
    public static final String EMPTY_ARGUMENTS = "";

    private Preconditions() {
    }

    public static void checkArgument(boolean z) {
        checkArgumentInternal(z, true, "Illegal argument.", "");
    }

    public static void checkArgument(boolean z, String str) {
        checkArgumentInternal(z, true, str, "");
    }

    public static void checkArgument(boolean z, String str, Object... objArr) {
        checkArgumentInternal(z, true, str, objArr);
    }

    public static void checkState(boolean z) {
        checkStateInternal(z, true, "Illegal state.", "");
    }

    public static void checkState(boolean z, String str) {
        checkStateInternal(z, true, str, "");
    }

    public static void checkState(boolean z, String str, Object... objArr) {
        checkStateInternal(z, true, str, objArr);
    }

    public static void checkNotNull(Object obj) {
        checkNotNullInternal(obj, true, "Object can not be null.", "");
    }

    public static void checkNotNull(Object obj, String str) {
        checkNotNullInternal(obj, true, str, "");
    }

    public static void checkNotNull(Object obj, String str, Object... objArr) {
        checkNotNullInternal(obj, true, str, objArr);
    }

    public static void checkUiThread() {
        checkUiThreadInternal(true, "This method must be called from the UI thread.", "");
    }

    public static void checkUiThread(String str) {
        checkUiThreadInternal(true, str, "");
    }

    public static void checkUiThread(String str, Object... objArr) {
        checkUiThreadInternal(true, str, objArr);
    }

    public static final class NoThrow {
        private static volatile boolean sStrictMode = false;

        public static void setStrictMode(boolean z) {
            sStrictMode = z;
        }

        public static boolean checkArgument(boolean z) {
            return Preconditions.checkArgumentInternal(z, sStrictMode, "Illegal argument", "");
        }

        public static boolean checkArgument(boolean z, String str) {
            return Preconditions.checkArgumentInternal(z, sStrictMode, str, "");
        }

        public static boolean checkArgument(boolean z, String str, Object... objArr) {
            return Preconditions.checkArgumentInternal(z, sStrictMode, str, objArr);
        }

        public static boolean checkState(boolean z) {
            return Preconditions.checkStateInternal(z, sStrictMode, "Illegal state.", "");
        }

        public static boolean checkState(boolean z, String str) {
            return Preconditions.checkStateInternal(z, sStrictMode, str, "");
        }

        public static boolean checkState(boolean z, String str, Object... objArr) {
            return Preconditions.checkStateInternal(z, sStrictMode, str, objArr);
        }

        public static boolean checkNotNull(Object obj) {
            return Preconditions.checkNotNullInternal(obj, sStrictMode, "Object can not be null.", "");
        }

        public static boolean checkNotNull(Object obj, String str) {
            return Preconditions.checkNotNullInternal(obj, sStrictMode, str, "");
        }

        public static boolean checkNotNull(Object obj, String str, Object... objArr) {
            return Preconditions.checkNotNullInternal(obj, sStrictMode, str, objArr);
        }

        public static boolean checkUiThread() {
            return Preconditions.checkUiThreadInternal(sStrictMode, "This method must be called from the UI thread.", "");
        }

        public static boolean checkUiThread(String str) {
            return Preconditions.checkUiThreadInternal(sStrictMode, str, "");
        }

        public static boolean checkUiThread(String str, Object... objArr) {
            return Preconditions.checkUiThreadInternal(false, str, objArr);
        }
    }

    /* access modifiers changed from: private */
    public static boolean checkArgumentInternal(boolean z, boolean z2, String str, Object... objArr) {
        if (z) {
            return true;
        }
        String format = format(str, objArr);
        if (!z2) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, format);
            return false;
        }
        throw new IllegalArgumentException(format);
    }

    /* access modifiers changed from: private */
    public static boolean checkStateInternal(boolean z, boolean z2, String str, Object... objArr) {
        if (z) {
            return true;
        }
        String format = format(str, objArr);
        if (!z2) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, format);
            return false;
        }
        throw new IllegalStateException(format);
    }

    /* access modifiers changed from: private */
    public static boolean checkNotNullInternal(Object obj, boolean z, String str, Object... objArr) {
        if (obj != null) {
            return true;
        }
        String format = format(str, objArr);
        if (!z) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, format);
            return false;
        }
        throw new NullPointerException(format);
    }

    /* access modifiers changed from: private */
    public static boolean checkUiThreadInternal(boolean z, String str, Object... objArr) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            return true;
        }
        String format = format(str, objArr);
        if (!z) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, format);
            return false;
        }
        throw new IllegalStateException(format);
    }

    private static String format(String str, Object... objArr) {
        String valueOf = String.valueOf(str);
        try {
            return String.format(valueOf, objArr);
        } catch (IllegalFormatException e) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "MoPub preconditions had a format exception: " + e.getMessage());
            return valueOf;
        }
    }
}
