package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfl {
    private static final int zzebu = 11;
    private static final int zzebv = 12;
    private static final int zzebw = 16;
    private static final int zzebx = 26;
    public static final int[] zzeby = new int[0];
    public static final long[] zzebz = new long[0];
    private static final float[] zzeca = new float[0];
    private static final double[] zzecb = new double[0];
    private static final boolean[] zzecc = new boolean[0];
    public static final String[] zzecd = new String[0];
    public static final byte[][] zzece = new byte[0][];
    public static final byte[] zzecf = new byte[0];

    public static final int zzb(zzbez zzbez, int i) throws IOException {
        int position = zzbez.getPosition();
        zzbez.zzbq(i);
        int i2 = 1;
        while (zzbez.zzabk() == i) {
            zzbez.zzbq(i);
            i2++;
        }
        zzbez.zzac(position, i);
        return i2;
    }
}
