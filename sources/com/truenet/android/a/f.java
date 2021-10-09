package com.truenet.android.a;

import a.a.a.g;
import a.a.b.b.h;
import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import java.util.List;

/* compiled from: StartAppSDK */
public final class f {
    public static final int a(TelephonyManager telephonyManager) {
        h.b(telephonyManager, "$receiver");
        if (Build.VERSION.SDK_INT < 26) {
            return c(telephonyManager);
        }
        List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
        CellInfo cellInfo = allCellInfo != null ? (CellInfo) g.d(allCellInfo) : null;
        if (cellInfo instanceof CellInfoGsm) {
            CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
            h.a((Object) cellIdentity, "info.cellIdentity");
            return cellIdentity.getCid();
        } else if (!(cellInfo instanceof CellInfoCdma)) {
            return c(telephonyManager);
        } else {
            CellIdentityCdma cellIdentity2 = ((CellInfoCdma) cellInfo).getCellIdentity();
            h.a((Object) cellIdentity2, "info.cellIdentity");
            return cellIdentity2.getBasestationId();
        }
    }

    public static final int b(TelephonyManager telephonyManager) {
        h.b(telephonyManager, "$receiver");
        if (Build.VERSION.SDK_INT < 26) {
            return d(telephonyManager);
        }
        List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
        CellInfo cellInfo = allCellInfo != null ? (CellInfo) g.d(allCellInfo) : null;
        if (!(cellInfo instanceof CellInfoGsm)) {
            return d(telephonyManager);
        }
        CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
        h.a((Object) cellIdentity, "info.cellIdentity");
        return cellIdentity.getLac();
    }

    private static final int c(TelephonyManager telephonyManager) {
        CellLocation cellLocation = telephonyManager.getCellLocation();
        if (cellLocation instanceof GsmCellLocation) {
            return ((GsmCellLocation) cellLocation).getCid();
        }
        if (cellLocation instanceof CdmaCellLocation) {
            return ((CdmaCellLocation) cellLocation).getBaseStationId();
        }
        return -1;
    }

    private static final int d(TelephonyManager telephonyManager) {
        CellLocation cellLocation = telephonyManager.getCellLocation();
        if (cellLocation instanceof GsmCellLocation) {
            return ((GsmCellLocation) cellLocation).getLac();
        }
        return -1;
    }
}
