package com.google.android.datatransport.cct.a;

import android.util.SparseArray;

/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
public enum zzaa {
    DEFAULT(0),
    UNMETERED_ONLY(1),
    UNMETERED_OR_DAILY(2),
    FAST_IF_RADIO_AWAKE(3),
    NEVER(4),
    UNRECOGNIZED(-1);
    
    private static final SparseArray<zzaa> zzg = null;

    static {
        DEFAULT = new zzaa("DEFAULT", 0, 0);
        UNMETERED_ONLY = new zzaa("UNMETERED_ONLY", 1, 1);
        UNMETERED_OR_DAILY = new zzaa("UNMETERED_OR_DAILY", 2, 2);
        FAST_IF_RADIO_AWAKE = new zzaa("FAST_IF_RADIO_AWAKE", 3, 3);
        NEVER = new zzaa("NEVER", 4, 4);
        UNRECOGNIZED = new zzaa("UNRECOGNIZED", 5, -1);
        SparseArray<zzaa> sparseArray = new SparseArray<>();
        zzg = sparseArray;
        sparseArray.put(0, DEFAULT);
        zzg.put(1, UNMETERED_ONLY);
        zzg.put(2, UNMETERED_OR_DAILY);
        zzg.put(3, FAST_IF_RADIO_AWAKE);
        zzg.put(4, NEVER);
        zzg.put(-1, UNRECOGNIZED);
    }

    private zzaa(int i) {
    }
}
