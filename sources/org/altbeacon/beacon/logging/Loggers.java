package org.altbeacon.beacon.logging;

public final class Loggers {
    private static final Logger EMPTY_LOGGER = new EmptyLogger();
    private static final Logger INFO_ANDROID_LOGGER = new InfoAndroidLogger();
    private static final Logger VERBOSE_ANDROID_LOGGER = new VerboseAndroidLogger();
    private static final Logger WARNING_ANDROID_LOGGER = new WarningAndroidLogger();

    public static Logger infoLogger() {
        return INFO_ANDROID_LOGGER;
    }
}
