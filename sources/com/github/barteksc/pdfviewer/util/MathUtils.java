package com.github.barteksc.pdfviewer.util;

public class MathUtils {
    public static float limit(float f, float f2, float f3) {
        return f <= f2 ? f2 : f >= f3 ? f3 : f;
    }

    public static float max(float f, float f2) {
        return f > f2 ? f2 : f;
    }

    public static int max(int i, int i2) {
        return i > i2 ? i2 : i;
    }

    public static int min(int i, int i2) {
        return i < i2 ? i2 : i;
    }

    public static int floor(float f) {
        double d = (double) f;
        Double.isNaN(d);
        return ((int) (d + 16384.0d)) - 16384;
    }

    public static int ceil(float f) {
        double d = (double) f;
        Double.isNaN(d);
        return ((int) (d + 16384.999999999996d)) - 16384;
    }
}
