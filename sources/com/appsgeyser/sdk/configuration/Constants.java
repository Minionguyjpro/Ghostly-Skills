package com.appsgeyser.sdk.configuration;

public class Constants {
    private static long fullScreenDelay = -1;

    public enum ReferrerInfoStatus {
        OK,
        FEATURE_NOT_SUPPORTED,
        UNAVAILABLE,
        REMOTE_EXCEPTION
    }

    public static void setFullScreenDelay(long j) {
        fullScreenDelay = j;
    }
}
