package com.mopub.common.logging;

import android.text.TextUtils;
import androidx.core.util.Pair;
import com.mopub.common.Preconditions;
import com.mopub.common.util.Strings;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MoPubLog {
    public static final String LOGTAG = "MoPub";
    private static final int STACK_TRACE_LEVEL = 4;
    private static final MoPubLog sInstance = new MoPubLog();
    private MoPubLogger mDefaultLogger = new MoPubDefaultLogger();
    private Map<MoPubLogger, LogLevel> mLoggers = new HashMap();
    private LogLevel sLogLevel = LogLevel.INFO;

    public interface LogLevelInt {
        public static final int DEBUG_INT = 20;
        public static final int INFO_INT = 30;
        public static final int NONE_INT = 70;
    }

    protected interface MPLogEventType {
        LogLevel getLogLevel();

        String getMessage(Object... objArr);
    }

    public enum LogLevel implements LogLevelInt {
        DEBUG(20, "DEBUG"),
        INFO(30, "INFO"),
        NONE(70, "NONE");
        
        private int mLevel;
        private String mLevelString;

        private LogLevel(int i, String str) {
            this.mLevel = i;
            this.mLevelString = str;
        }

        public String toString() {
            return this.mLevelString;
        }

        public int intValue() {
            return this.mLevel;
        }

        public static LogLevel valueOf(int i) {
            if (i == 20) {
                return DEBUG;
            }
            if (i != 30) {
                return NONE;
            }
            return INFO;
        }
    }

    private MoPubLog() {
    }

    private static void logDeprecated(String str, Throwable th) {
        SdkLogEvent sdkLogEvent = SdkLogEvent.CUSTOM_WITH_THROWABLE;
        Object[] objArr = new Object[2];
        objArr[0] = str;
        objArr[1] = th != null ? th.getMessage() : "";
        log(sdkLogEvent, objArr);
    }

    private static void removeLogger(MoPubLogger moPubLogger) {
        sInstance.mLoggers.remove(moPubLogger);
    }

    public static void addLogger(MoPubLogger moPubLogger) {
        addLogger(moPubLogger, sInstance.sLogLevel);
    }

    public static void addLogger(MoPubLogger moPubLogger, LogLevel logLevel) {
        sInstance.mLoggers.put(moPubLogger, logLevel);
    }

    public static void setLogLevel(LogLevel logLevel) {
        Preconditions.checkNotNull(logLevel);
        MoPubLog moPubLog = sInstance;
        moPubLog.sLogLevel = logLevel;
        addLogger(moPubLog.mDefaultLogger, logLevel);
    }

    public static LogLevel getLogLevel() {
        return sInstance.sLogLevel;
    }

    public static void log(MPLogEventType mPLogEventType, Object... objArr) {
        log(getClassAndMethod(), (String) null, mPLogEventType, objArr);
    }

    public static void log(String str, MPLogEventType mPLogEventType, Object... objArr) {
        log(getClassAndMethod(), str, mPLogEventType, objArr);
    }

    private static void log(Pair<String, String> pair, String str, MPLogEventType mPLogEventType, Object... objArr) {
        Preconditions.checkNotNull(pair);
        if (mPLogEventType != null) {
            for (MoPubLogger next : sInstance.mLoggers.keySet()) {
                if (sInstance.mLoggers.get(next) != null && sInstance.mLoggers.get(next).intValue() <= mPLogEventType.getLogLevel().intValue()) {
                    next.log((String) pair.first, (String) pair.second, str, mPLogEventType.getMessage(objArr));
                }
            }
        }
    }

    private static Pair<String, String> getClassAndMethod() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return new Pair<>(stackTrace[4].getClassName(), stackTrace[4].getMethodName());
    }

    @Deprecated
    public static void c(String str) {
        c(str, (Throwable) null);
    }

    @Deprecated
    public static void v(String str) {
        v(str, (Throwable) null);
    }

    @Deprecated
    public static void d(String str) {
        d(str, (Throwable) null);
    }

    @Deprecated
    public static void i(String str) {
        i(str, (Throwable) null);
    }

    @Deprecated
    public static void w(String str) {
        w(str, (Throwable) null);
    }

    @Deprecated
    public static void e(String str) {
        e(str, (Throwable) null);
    }

    @Deprecated
    public static void c(String str, Throwable th) {
        logDeprecated(str, th);
    }

    @Deprecated
    public static void v(String str, Throwable th) {
        logDeprecated(str, th);
    }

    @Deprecated
    public static void d(String str, Throwable th) {
        logDeprecated(str, th);
    }

    @Deprecated
    public static void i(String str, Throwable th) {
        logDeprecated(str, th);
    }

    @Deprecated
    public static void w(String str, Throwable th) {
        logDeprecated(str, th);
    }

    @Deprecated
    public static void e(String str, Throwable th) {
        logDeprecated(str, th);
    }

    public enum AdLogEvent implements MPLogEventType {
        REQUESTED(LogLevel.DEBUG, "Ad requesting from AdServer: {0}\n{1}"),
        RESPONSE_RECEIVED(LogLevel.DEBUG, "Ad server responded with:\n{0}"),
        LOAD_ATTEMPTED(LogLevel.INFO, "Ad attempting to load"),
        LOAD_SUCCESS(LogLevel.INFO, "Ad loaded"),
        LOAD_FAILED(LogLevel.INFO, "Ad failed to load: ({0}) {1}"),
        SHOW_ATTEMPTED(LogLevel.INFO, "Attempting to show ad"),
        SHOW_SUCCESS(LogLevel.INFO, "Ad shown"),
        SHOW_FAILED(LogLevel.INFO, "Ad failed to show: ({0}) {1}"),
        EXPIRED(LogLevel.DEBUG, "Ad expired since it was not shown within {0} seconds of it being loaded"),
        CLICKED(LogLevel.DEBUG, "Ad clicked"),
        WILL_APPEAR(LogLevel.DEBUG, "Ad will appear"),
        DID_APPEAR(LogLevel.DEBUG, "Ad did appear"),
        WILL_DISAPPEAR(LogLevel.DEBUG, "Ad will disappear"),
        DID_DISAPPEAR(LogLevel.DEBUG, "Ad did disappear"),
        SHOULD_REWARD(LogLevel.DEBUG, "Ad should reward user with {0} {1}"),
        WILL_LEAVE_APPLICATION(LogLevel.DEBUG, "Ad will leave application"),
        CUSTOM(LogLevel.DEBUG, "Ad Log - {0}"),
        CUSTOM_WITH_THROWABLE(LogLevel.DEBUG, "Ad Log With Throwable - {0}, {1}");
        
        private LogLevel mLogLevel;
        private String mMessageFormat;

        private AdLogEvent(LogLevel logLevel, String str) {
            Preconditions.checkNotNull(logLevel);
            Preconditions.checkNotNull(str);
            this.mLogLevel = logLevel;
            this.mMessageFormat = str;
        }

        public String getMessage(Object... objArr) {
            return MessageFormat.format(this.mMessageFormat, objArr);
        }

        public LogLevel getLogLevel() {
            return this.mLogLevel;
        }
    }

    public enum AdapterLogEvent implements MPLogEventType {
        LOAD_ATTEMPTED(LogLevel.DEBUG, "Adapter {0} attempting to load ad{1}{2}"),
        LOAD_SUCCESS(LogLevel.DEBUG, "Adapter {0} successfully loaded ad"),
        LOAD_FAILED(LogLevel.DEBUG, "Adapter {0} failed to load ad: ({1}) {2}"),
        SHOW_ATTEMPTED(LogLevel.DEBUG, "Adapter {0} attempting to show ad"),
        SHOW_SUCCESS(LogLevel.DEBUG, "Adapter {0} successfully showed ad"),
        SHOW_FAILED(LogLevel.DEBUG, "Adapter {0} failed to show ad: ({1}) {2}"),
        EXPIRED(LogLevel.DEBUG, "Adapter {0} expired since it was not shown within {1} seconds of it being loaded"),
        CLICKED(LogLevel.DEBUG, "Adapter {0} clicked"),
        WILL_APPEAR(LogLevel.DEBUG, "Adapter {0} will appear"),
        DID_APPEAR(LogLevel.DEBUG, "Adapter {0} did appear"),
        WILL_DISAPPEAR(LogLevel.DEBUG, "Adapter {0} will disappear"),
        DID_DISAPPEAR(LogLevel.DEBUG, "Adapter {0} did disappear"),
        SHOULD_REWARD(LogLevel.DEBUG, "Adapter {0} should reward user with {1} {2}"),
        WILL_LEAVE_APPLICATION(LogLevel.DEBUG, "Adapter {0} will leave application"),
        CUSTOM(LogLevel.DEBUG, "Adapter {0} Log - {1}"),
        CUSTOM_WITH_THROWABLE(LogLevel.DEBUG, "Adapter Log With Throwable - {0}, {1}");
        
        private LogLevel mLogLevel;
        private String mMessageFormat;

        private AdapterLogEvent(LogLevel logLevel, String str) {
            Preconditions.checkNotNull(logLevel);
            Preconditions.checkNotNull(str);
            this.mLogLevel = logLevel;
            this.mMessageFormat = str;
        }

        public String getMessage(Object... objArr) {
            MessageFormat messageFormat = new MessageFormat(this.mMessageFormat);
            Object[] copyOf = Arrays.copyOf(objArr, messageFormat.getFormats().length);
            if (this == LOAD_ATTEMPTED) {
                if (objArr.length <= 1 || objArr[1] == null || TextUtils.isEmpty(objArr[1].toString())) {
                    copyOf[1] = "";
                } else {
                    copyOf[1] = MessageFormat.format(" with DSP creative ID {0}", new Object[]{objArr[1].toString()});
                }
                if (objArr.length <= 2 || objArr[2] == null || TextUtils.isEmpty(objArr[2].toString())) {
                    copyOf[2] = "";
                } else {
                    copyOf[2] = MessageFormat.format(" with DSP name {0}", new Object[]{objArr[2].toString()});
                }
            }
            return messageFormat.format(copyOf);
        }

        public LogLevel getLogLevel() {
            return this.mLogLevel;
        }
    }

    public enum ConsentLogEvent implements MPLogEventType {
        SYNC_ATTEMPTED(LogLevel.DEBUG, "Consent attempting to synchronize state"),
        SYNC_COMPLETED(LogLevel.DEBUG, "Consent synchronization completed: {0}"),
        SYNC_FAILED(LogLevel.DEBUG, "Consent synchronization failed: ({0}) {1}"),
        UPDATED(LogLevel.DEBUG, "Consent changed from {0} to {1}: PII {2} be collected. Reason: {3}"),
        SHOULD_SHOW_DIALOG(LogLevel.DEBUG, "Consent dialog should be shown"),
        LOAD_ATTEMPTED(LogLevel.DEBUG, "Consent attempting to load dialog"),
        LOAD_SUCCESS(LogLevel.DEBUG, "Consent dialog loaded"),
        LOAD_FAILED(LogLevel.DEBUG, "Consent dialog failed: ({0}) {1}"),
        SHOW_ATTEMPTED(LogLevel.DEBUG, "Consent dialog attempting to show"),
        SHOW_SUCCESS(LogLevel.DEBUG, "Consent successfully showed dialog"),
        SHOW_FAILED(LogLevel.DEBUG, "Consent dialog failed to show: ({0}) {1}"),
        CUSTOM(LogLevel.DEBUG, "Consent Log - {0}"),
        CUSTOM_WITH_THROWABLE(LogLevel.DEBUG, "Consent Log With Throwable - {0}, {1}");
        
        private LogLevel mLogLevel;
        private String mMessageFormat;

        private ConsentLogEvent(LogLevel logLevel, String str) {
            Preconditions.checkNotNull(logLevel);
            Preconditions.checkNotNull(str);
            this.mLogLevel = logLevel;
            this.mMessageFormat = str;
        }

        public String getMessage(Object... objArr) {
            if (this == UPDATED && objArr != null && objArr.length > 2) {
                objArr[2] = (!(objArr[2] instanceof Boolean) || !objArr[2].booleanValue()) ? "cannot" : "can";
            }
            return MessageFormat.format(this.mMessageFormat, objArr);
        }

        public LogLevel getLogLevel() {
            return this.mLogLevel;
        }
    }

    public enum SdkLogEvent implements MPLogEventType {
        INIT_STARTED(LogLevel.DEBUG, "SDK initialization started"),
        INIT_FINISHED(LogLevel.INFO, "SDK initialized and ready to display ads.\nInitialized adapters:\n{0}"),
        INIT_FAILED(LogLevel.INFO, "SDK initialization failed - {0}\n{1}"),
        CUSTOM(LogLevel.DEBUG, "SDK Log - {0}"),
        CUSTOM_WITH_THROWABLE(LogLevel.DEBUG, "SDK Log With Throwable - {0}, {1}"),
        ERROR(LogLevel.DEBUG, "SDK Error Log - {0}"),
        ERROR_WITH_THROWABLE(LogLevel.DEBUG, "SDK Error Log - {0}, {1}");
        
        private LogLevel mLogLevel;
        private String mMessageFormat;

        private SdkLogEvent(LogLevel logLevel, String str) {
            Preconditions.checkNotNull(logLevel);
            Preconditions.checkNotNull(str);
            this.mLogLevel = logLevel;
            this.mMessageFormat = str;
        }

        public String getMessage(Object... objArr) {
            if (this == INIT_FINISHED && objArr.length > 0) {
                String delimitedString = Strings.getDelimitedString(objArr[0], "\n");
                if (TextUtils.isEmpty(delimitedString)) {
                    objArr[0] = "No adapters initialized.";
                } else {
                    objArr[0] = delimitedString;
                }
            }
            return MessageFormat.format(this.mMessageFormat, objArr);
        }

        public LogLevel getLogLevel() {
            return this.mLogLevel;
        }
    }
}
