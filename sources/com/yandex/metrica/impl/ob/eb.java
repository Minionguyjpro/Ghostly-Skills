package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.SparseArray;
import com.yandex.metrica.impl.al;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.d;
import com.yandex.metrica.impl.ob.dz;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

final class eb extends dy implements d {

    /* renamed from: a  reason: collision with root package name */
    private static final SparseArray<String> f871a = new SparseArray<String>() {
        {
            put(0, (Object) null);
            put(7, "1xRTT");
            put(4, "CDMA");
            put(2, "EDGE");
            put(14, "eHRPD");
            put(5, "EVDO rev.0");
            put(6, "EVDO rev.A");
            put(12, "EVDO rev.B");
            put(1, "GPRS");
            put(8, "HSDPA");
            put(10, "HSPA");
            put(15, "HSPA+");
            put(9, "HSUPA");
            put(11, "iDen");
            put(3, "UMTS");
            put(12, "EVDO rev.B");
            if (bk.a(11)) {
                put(14, "eHRPD");
                put(13, "LTE");
                if (bk.a(13)) {
                    put(15, "HSPA+");
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final TelephonyManager b;
    /* access modifiers changed from: private */
    public PhoneStateListener c;
    /* access modifiers changed from: private */
    public boolean d = false;
    private final d.a<eg> e = new d.a<>();
    private final d.a<dz[]> f = new d.a<>();
    private final Handler g;
    private final Context h;

    protected eb(Context context) {
        this.h = context;
        this.b = (TelephonyManager) context.getSystemService("phone");
        HandlerThread handlerThread = new HandlerThread("TelephonyProviderThread");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.g = handler;
        handler.post(new Runnable() {
            public void run() {
                PhoneStateListener unused = eb.this.c = new a(eb.this, (byte) 0);
            }
        });
    }

    public synchronized void a() {
        this.g.post(new Runnable() {
            public void run() {
                if (!eb.this.d) {
                    boolean unused = eb.this.d = true;
                    try {
                        if (eb.this.c != null) {
                            eb.this.b.listen(eb.this.c, 256);
                        }
                    } catch (Exception unused2) {
                    }
                }
            }
        });
    }

    public synchronized void b() {
        this.g.post(new Runnable() {
            public void run() {
                if (eb.this.d) {
                    boolean unused = eb.this.d = false;
                    try {
                        if (eb.this.c != null) {
                            eb.this.b.listen(eb.this.c, 0);
                        }
                    } catch (Exception unused2) {
                    }
                }
            }
        });
    }

    public synchronized void a(eh ehVar) {
        if (ehVar != null) {
            ehVar.a(c());
        }
    }

    public synchronized void a(ea eaVar) {
        if (eaVar != null) {
            eaVar.a(g());
        }
    }

    private class a extends PhoneStateListener {
        private a() {
        }

        /* synthetic */ a(eb ebVar, byte b) {
            this();
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            eb.this.a(signalStrength);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized eg c() {
        eg egVar;
        if (!this.e.b()) {
            if (!this.e.c()) {
                egVar = this.e.a();
            }
        }
        egVar = new eg(d(), e(), f());
        if (egVar.b().a() == null && !this.e.b()) {
            egVar.b().a(this.e.a().b().a());
        }
        this.e.a(egVar);
        return egVar;
    }

    private synchronized dz[] g() {
        dz[] dzVarArr;
        dz.b bVar;
        if (!this.f.b()) {
            if (!this.f.c()) {
                dzVarArr = this.f.a();
            }
        }
        ArrayList arrayList = new ArrayList();
        if (bk.a(17) && al.a(this.h, "android.permission.ACCESS_COARSE_LOCATION")) {
            List<CellInfo> allCellInfo = this.b.getAllCellInfo();
            if (!bk.a((Collection) allCellInfo)) {
                for (int i = 0; i < allCellInfo.size(); i++) {
                    CellInfo cellInfo = allCellInfo.get(i);
                    dz dzVar = null;
                    if (cellInfo instanceof CellInfoGsm) {
                        bVar = new dz.c();
                    } else if (cellInfo instanceof CellInfoCdma) {
                        bVar = new dz.a();
                    } else if (cellInfo instanceof CellInfoLte) {
                        bVar = new dz.d();
                    } else {
                        bVar = (!bk.a(18) || !(cellInfo instanceof CellInfoWcdma)) ? null : new dz.e();
                    }
                    if (bVar != null) {
                        dzVar = bVar.a(cellInfo);
                    }
                    if (dzVar != null) {
                        arrayList.add(dzVar);
                    }
                }
            }
        }
        dzVarArr = arrayList.size() <= 0 ? new dz[]{c().b()} : (dz[]) arrayList.toArray(new dz[arrayList.size()]);
        this.f.a(dzVarArr);
        return dzVarArr;
    }

    /* access modifiers changed from: private */
    public synchronized void a(SignalStrength signalStrength) {
        int i;
        if (!this.e.b() && !this.e.c()) {
            dz b2 = this.e.a().b();
            if (signalStrength.isGsm()) {
                int gsmSignalStrength = signalStrength.getGsmSignalStrength();
                i = 99 == gsmSignalStrength ? -1 : (gsmSignalStrength * 2) - 113;
            } else {
                int cdmaDbm = signalStrength.getCdmaDbm();
                i = signalStrength.getEvdoDbm();
                if (-120 == i) {
                    i = cdmaDbm;
                } else if (-120 != cdmaDbm) {
                    i = Math.min(cdmaDbm, i);
                }
            }
            b2.a(Integer.valueOf(i));
        }
    }

    private Integer h() {
        try {
            String substring = this.b.getNetworkOperator().substring(0, 3);
            if (!TextUtils.isEmpty(substring)) {
                return Integer.valueOf(Integer.parseInt(substring));
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private Integer i() {
        try {
            String substring = this.b.getNetworkOperator().substring(3);
            if (!TextUtils.isEmpty(substring)) {
                return Integer.valueOf(Integer.parseInt(substring));
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private Integer j() {
        try {
            String substring = this.b.getSimOperator().substring(0, 3);
            if (TextUtils.isEmpty(substring)) {
                return null;
            }
            return Integer.valueOf(Integer.parseInt(substring));
        } catch (Exception unused) {
            return null;
        }
    }

    private Integer k() {
        try {
            String substring = this.b.getSimOperator().substring(3);
            if (TextUtils.isEmpty(substring)) {
                return null;
            }
            return Integer.valueOf(Integer.parseInt(substring));
        } catch (Exception unused) {
            return null;
        }
    }

    private Integer l() {
        int cid;
        try {
            if (!al.a(this.h) || -1 == (cid = ((GsmCellLocation) this.b.getCellLocation()).getCid())) {
                return null;
            }
            return Integer.valueOf(cid);
        } catch (Exception unused) {
            return null;
        }
    }

    private Integer m() {
        int lac;
        try {
            if (!al.a(this.h) || -1 == (lac = ((GsmCellLocation) this.b.getCellLocation()).getLac())) {
                return null;
            }
            return Integer.valueOf(lac);
        } catch (Exception unused) {
            return null;
        }
    }

    private String n() {
        try {
            return f871a.get(this.b.getNetworkType(), "unknown");
        } catch (Exception unused) {
            return "unknown";
        }
    }

    private String o() {
        try {
            if (al.a(this.h, "android.permission.READ_PHONE_STATE")) {
                return this.b.getDeviceId();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private List<String> p() {
        HashSet hashSet = new HashSet();
        try {
            if (al.a(this.h, "android.permission.READ_PHONE_STATE")) {
                for (int i = 0; i < 10; i++) {
                    String deviceId = this.b.getDeviceId(i);
                    if (deviceId != null) {
                        hashSet.add(deviceId);
                    }
                }
            }
        } catch (Exception unused) {
        }
        return new ArrayList(hashSet);
    }

    private boolean q() {
        if (!al.a(this.h, "android.permission.READ_PHONE_STATE")) {
            return false;
        }
        try {
            return this.b.isNetworkRoaming();
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public dz d() {
        return new dz(h(), i(), m(), l(), this.b.getNetworkOperatorName(), n(), (Integer) null, true, 0, (Integer) null);
    }

    /* access modifiers changed from: package-private */
    public List<ee> e() {
        ArrayList arrayList = new ArrayList();
        if (bk.a(23)) {
            arrayList.addAll(s());
            if (arrayList.size() == 0) {
                arrayList.add(r());
            }
        } else {
            arrayList.add(r());
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<String> f() {
        ArrayList arrayList = new ArrayList();
        if (bk.a(23)) {
            arrayList.addAll(p());
        } else {
            arrayList.add(o());
        }
        return arrayList;
    }

    private ee r() {
        return new ee(j(), k(), q(), this.b.getSimOperatorName(), (String) null);
    }

    private List<ee> s() {
        ArrayList arrayList = new ArrayList();
        if (al.a(this.h, "android.permission.READ_PHONE_STATE")) {
            try {
                List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(this.h).getActiveSubscriptionInfoList();
                if (activeSubscriptionInfoList != null) {
                    for (SubscriptionInfo eeVar : activeSubscriptionInfoList) {
                        arrayList.add(new ee(eeVar));
                    }
                }
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }
}
