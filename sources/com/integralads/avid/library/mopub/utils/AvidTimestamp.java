package com.integralads.avid.library.mopub.utils;

public class AvidTimestamp {
    private static double NANOSEC_PER_MILLISEC = 1000000.0d;

    public static double getCurrentTime() {
        double nanoTime = (double) System.nanoTime();
        double d = NANOSEC_PER_MILLISEC;
        Double.isNaN(nanoTime);
        return nanoTime / d;
    }
}
