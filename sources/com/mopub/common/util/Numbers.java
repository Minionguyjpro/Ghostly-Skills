package com.mopub.common.util;

public class Numbers {
    private Numbers() {
    }

    public static Double parseDouble(Object obj) throws ClassCastException {
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException unused) {
                throw new ClassCastException("Unable to parse " + obj + " as double.");
            }
        } else {
            throw new ClassCastException("Unable to parse " + obj + " as double.");
        }
    }

    public static long convertMillisecondsToSecondsRoundedUp(long j) {
        return Math.round(Math.ceil((double) (((float) j) / 1000.0f)));
    }
}
