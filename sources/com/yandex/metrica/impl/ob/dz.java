package com.yandex.metrica.impl.ob;

import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrength;

public final class dz {

    /* renamed from: a  reason: collision with root package name */
    private Integer f869a;
    private final Integer b;
    private final Integer c;
    private final Integer d;
    private final Integer e;
    private final String f;
    private final String g;
    private final boolean h;
    private final int i;
    private final Integer j;

    static abstract class b {

        /* renamed from: a  reason: collision with root package name */
        static final Integer f870a = Integer.MAX_VALUE;
        static final Integer b = Integer.MAX_VALUE;
        static final Integer c = Integer.MAX_VALUE;
        static final Integer d = Integer.MAX_VALUE;
        static final Integer e = Integer.MAX_VALUE;

        /* access modifiers changed from: package-private */
        public abstract dz a(CellInfo cellInfo);

        b() {
        }

        /* access modifiers changed from: protected */
        public dz a(Integer num, Integer num2, CellSignalStrength cellSignalStrength, Integer num3, Integer num4, boolean z, int i, Integer num5) {
            Integer num6;
            Integer num7;
            Integer num8;
            Integer num9;
            Integer num10 = num;
            Integer num11 = num2;
            Integer num12 = num3;
            Integer num13 = num4;
            Integer num14 = num5;
            if (num10 != null) {
                if (num10 == f870a) {
                    num10 = null;
                }
                num6 = num10;
            } else {
                num6 = null;
            }
            if (num11 != null) {
                if (num11 == b) {
                    num11 = null;
                }
                num7 = num11;
            } else {
                num7 = null;
            }
            Integer valueOf = cellSignalStrength != null ? Integer.valueOf(cellSignalStrength.getDbm()) : null;
            if (num13 != null) {
                if (num13 == c) {
                    num13 = null;
                }
                num8 = num13;
            } else {
                num8 = null;
            }
            if (num12 != null) {
                if (num12 == d) {
                    num12 = null;
                }
                num9 = num12;
            } else {
                num9 = null;
            }
            return new dz(num8, num9, num7, num6, (String) null, (String) null, valueOf, z, i, (num14 == null || num14 == e) ? null : num14);
        }
    }

    static class c extends b {
        c() {
        }

        /* access modifiers changed from: package-private */
        public dz a(CellInfo cellInfo) {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
            return a(Integer.valueOf(cellIdentity.getCid()), Integer.valueOf(cellIdentity.getLac()), cellInfoGsm.getCellSignalStrength(), Integer.valueOf(cellIdentity.getMnc()), Integer.valueOf(cellIdentity.getMcc()), cellInfoGsm.isRegistered(), 1, (Integer) null);
        }
    }

    static class a extends b {
        a() {
        }

        /* access modifiers changed from: package-private */
        public dz a(CellInfo cellInfo) {
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            return a((Integer) null, (Integer) null, cellInfoCdma.getCellSignalStrength(), (Integer) null, (Integer) null, cellInfoCdma.isRegistered(), 2, (Integer) null);
        }
    }

    static class d extends b {
        d() {
        }

        /* access modifiers changed from: package-private */
        public dz a(CellInfo cellInfo) {
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
            CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
            return a(Integer.valueOf(cellIdentity.getCi()), Integer.valueOf(cellIdentity.getTac()), cellInfoLte.getCellSignalStrength(), Integer.valueOf(cellIdentity.getMnc()), Integer.valueOf(cellIdentity.getMcc()), cellInfoLte.isRegistered(), 4, Integer.valueOf(cellIdentity.getPci()));
        }
    }

    static class e extends b {
        e() {
        }

        /* access modifiers changed from: package-private */
        public dz a(CellInfo cellInfo) {
            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
            CellIdentityWcdma cellIdentity = cellInfoWcdma.getCellIdentity();
            return a(Integer.valueOf(cellIdentity.getCid()), Integer.valueOf(cellIdentity.getLac()), cellInfoWcdma.getCellSignalStrength(), Integer.valueOf(cellIdentity.getMnc()), Integer.valueOf(cellIdentity.getMcc()), cellInfoWcdma.isRegistered(), 3, Integer.valueOf(cellIdentity.getPsc()));
        }
    }

    public dz(Integer num, Integer num2, Integer num3, Integer num4, String str, String str2, Integer num5, boolean z, int i2, Integer num6) {
        this.b = num;
        this.c = num2;
        this.d = num3;
        this.e = num4;
        this.f = str;
        this.g = str2;
        this.f869a = num5;
        this.h = z;
        this.i = i2;
        this.j = num6;
    }

    public Integer a() {
        return this.f869a;
    }

    public Integer b() {
        return this.b;
    }

    public Integer c() {
        return this.c;
    }

    public Integer d() {
        return this.d;
    }

    public Integer e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    public void a(Integer num) {
        this.f869a = num;
    }

    public int i() {
        return this.i;
    }

    public Integer j() {
        return this.j;
    }

    public String toString() {
        return "CellDescription{mSignalStrength=" + this.f869a + ", mMobileCountryCode=" + this.b + ", mMobileNetworkCode=" + this.c + ", mLocationAreaCode=" + this.d + ", mCellId=" + this.e + ", mOperatorName='" + this.f + '\'' + ", mNetworkType='" + this.g + '\'' + ", mConnected=" + this.h + ", mCellType=" + this.i + ", mPci=" + this.j + '}';
    }
}
