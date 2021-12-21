package com.google.android.datatransport.cct.a;

import android.util.SparseArray;
import com.google.android.datatransport.cct.a.zzn;

/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
public abstract class zzy {

    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    public static abstract class zza {
        public abstract zza zza(zzb zzb);

        public abstract zza zza(zzc zzc);

        public abstract zzy zza();
    }

    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    public enum zzb {
        UNKNOWN_MOBILE_SUBTYPE(0),
        GPRS(1),
        EDGE(2),
        UMTS(3),
        CDMA(4),
        EVDO_0(5),
        EVDO_A(6),
        RTT(7),
        HSDPA(8),
        HSUPA(9),
        HSPA(10),
        IDEN(11),
        EVDO_B(12),
        LTE(13),
        EHRPD(14),
        HSPAP(15),
        GSM(16),
        TD_SCDMA(17),
        IWLAN(18),
        LTE_CA(19),
        COMBINED(100);
        
        private static final SparseArray<zzb> zzv = null;
        private final int zzw;

        static {
            UNKNOWN_MOBILE_SUBTYPE = new zzb("UNKNOWN_MOBILE_SUBTYPE", 0, 0);
            GPRS = new zzb("GPRS", 1, 1);
            EDGE = new zzb("EDGE", 2, 2);
            UMTS = new zzb("UMTS", 3, 3);
            CDMA = new zzb("CDMA", 4, 4);
            EVDO_0 = new zzb("EVDO_0", 5, 5);
            EVDO_A = new zzb("EVDO_A", 6, 6);
            RTT = new zzb("RTT", 7, 7);
            HSDPA = new zzb("HSDPA", 8, 8);
            HSUPA = new zzb("HSUPA", 9, 9);
            HSPA = new zzb("HSPA", 10, 10);
            IDEN = new zzb("IDEN", 11, 11);
            EVDO_B = new zzb("EVDO_B", 12, 12);
            LTE = new zzb("LTE", 13, 13);
            EHRPD = new zzb("EHRPD", 14, 14);
            HSPAP = new zzb("HSPAP", 15, 15);
            GSM = new zzb("GSM", 16, 16);
            TD_SCDMA = new zzb("TD_SCDMA", 17, 17);
            IWLAN = new zzb("IWLAN", 18, 18);
            LTE_CA = new zzb("LTE_CA", 19, 19);
            COMBINED = new zzb("COMBINED", 20, 100);
            SparseArray<zzb> sparseArray = new SparseArray<>();
            zzv = sparseArray;
            sparseArray.put(0, UNKNOWN_MOBILE_SUBTYPE);
            zzv.put(1, GPRS);
            zzv.put(2, EDGE);
            zzv.put(3, UMTS);
            zzv.put(4, CDMA);
            zzv.put(5, EVDO_0);
            zzv.put(6, EVDO_A);
            zzv.put(7, RTT);
            zzv.put(8, HSDPA);
            zzv.put(9, HSUPA);
            zzv.put(10, HSPA);
            zzv.put(11, IDEN);
            zzv.put(12, EVDO_B);
            zzv.put(13, LTE);
            zzv.put(14, EHRPD);
            zzv.put(15, HSPAP);
            zzv.put(16, GSM);
            zzv.put(17, TD_SCDMA);
            zzv.put(18, IWLAN);
            zzv.put(19, LTE_CA);
        }

        private zzb(int i) {
            this.zzw = i;
        }

        public int zza() {
            return this.zzw;
        }

        public static zzb zza(int i) {
            return zzv.get(i);
        }
    }

    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    public enum zzc {
        MOBILE(0),
        WIFI(1),
        MOBILE_MMS(2),
        MOBILE_SUPL(3),
        MOBILE_DUN(4),
        MOBILE_HIPRI(5),
        WIMAX(6),
        BLUETOOTH(7),
        DUMMY(8),
        ETHERNET(9),
        MOBILE_FOTA(10),
        MOBILE_IMS(11),
        MOBILE_CBS(12),
        WIFI_P2P(13),
        MOBILE_IA(14),
        MOBILE_EMERGENCY(15),
        PROXY(16),
        VPN(17),
        NONE(-1);
        
        private static final SparseArray<zzc> zzt = null;
        private final int zzu;

        static {
            MOBILE = new zzc("MOBILE", 0, 0);
            WIFI = new zzc("WIFI", 1, 1);
            MOBILE_MMS = new zzc("MOBILE_MMS", 2, 2);
            MOBILE_SUPL = new zzc("MOBILE_SUPL", 3, 3);
            MOBILE_DUN = new zzc("MOBILE_DUN", 4, 4);
            MOBILE_HIPRI = new zzc("MOBILE_HIPRI", 5, 5);
            WIMAX = new zzc("WIMAX", 6, 6);
            BLUETOOTH = new zzc("BLUETOOTH", 7, 7);
            DUMMY = new zzc("DUMMY", 8, 8);
            ETHERNET = new zzc("ETHERNET", 9, 9);
            MOBILE_FOTA = new zzc("MOBILE_FOTA", 10, 10);
            MOBILE_IMS = new zzc("MOBILE_IMS", 11, 11);
            MOBILE_CBS = new zzc("MOBILE_CBS", 12, 12);
            WIFI_P2P = new zzc("WIFI_P2P", 13, 13);
            MOBILE_IA = new zzc("MOBILE_IA", 14, 14);
            MOBILE_EMERGENCY = new zzc("MOBILE_EMERGENCY", 15, 15);
            PROXY = new zzc("PROXY", 16, 16);
            VPN = new zzc("VPN", 17, 17);
            NONE = new zzc("NONE", 18, -1);
            SparseArray<zzc> sparseArray = new SparseArray<>();
            zzt = sparseArray;
            sparseArray.put(0, MOBILE);
            zzt.put(1, WIFI);
            zzt.put(2, MOBILE_MMS);
            zzt.put(3, MOBILE_SUPL);
            zzt.put(4, MOBILE_DUN);
            zzt.put(5, MOBILE_HIPRI);
            zzt.put(6, WIMAX);
            zzt.put(7, BLUETOOTH);
            zzt.put(8, DUMMY);
            zzt.put(9, ETHERNET);
            zzt.put(10, MOBILE_FOTA);
            zzt.put(11, MOBILE_IMS);
            zzt.put(12, MOBILE_CBS);
            zzt.put(13, WIFI_P2P);
            zzt.put(14, MOBILE_IA);
            zzt.put(15, MOBILE_EMERGENCY);
            zzt.put(16, PROXY);
            zzt.put(17, VPN);
            zzt.put(-1, NONE);
        }

        private zzc(int i) {
            this.zzu = i;
        }

        public int zza() {
            return this.zzu;
        }

        public static zzc zza(int i) {
            return zzt.get(i);
        }
    }

    public static zza zza() {
        return new zzn.zza();
    }
}
