package com.google.android.gms.internal.ads;

public enum zzawk implements zzbbr {
    UNKNOWN_FORMAT(0),
    UNCOMPRESSED(1),
    COMPRESSED(2),
    UNRECOGNIZED(-1);
    
    private static final zzbbs<zzawk> zzall = null;
    private final int value;

    static {
        zzall = new zzawl();
    }

    private zzawk(int i) {
        this.value = i;
    }

    public static zzawk zzaq(int i) {
        if (i == 0) {
            return UNKNOWN_FORMAT;
        }
        if (i == 1) {
            return UNCOMPRESSED;
        }
        if (i != 2) {
            return null;
        }
        return COMPRESSED;
    }

    public final int zzhq() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
