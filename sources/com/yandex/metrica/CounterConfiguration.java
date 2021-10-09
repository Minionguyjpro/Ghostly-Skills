package com.yandex.metrica;

import android.content.ContentValues;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.yandex.metrica.impl.bc;
import com.yandex.metrica.impl.bi;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.utils.g;
import com.yandex.metrica.impl.y;
import java.util.List;
import java.util.Map;

public class CounterConfiguration implements Parcelable {
    public static final Parcelable.Creator<CounterConfiguration> CREATOR = new Parcelable.Creator<CounterConfiguration>() {
        public /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new CounterConfiguration[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new CounterConfiguration(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private ContentValues f680a;
    private ResultReceiver b = null;

    public int describeContents() {
        return 0;
    }

    public enum a {
        UNDEFINED(-1),
        FALSE(0),
        TRUE(1);
        
        public final int d;

        private a(int i) {
            this.d = i;
        }

        public static a a(int i) {
            if (i == -1) {
                return UNDEFINED;
            }
            if (i == 0) {
                return FALSE;
            }
            if (i != 1) {
                return UNDEFINED;
            }
            return TRUE;
        }
    }

    public CounterConfiguration(CounterConfiguration counterConfiguration) {
        ContentValues contentValues = new ContentValues();
        this.f680a = contentValues;
        contentValues.putAll(counterConfiguration.f680a);
        this.b = counterConfiguration.b;
    }

    public CounterConfiguration() {
        ContentValues contentValues = new ContentValues();
        this.f680a = contentValues;
        contentValues.put("CFG_DISPATCH_PERIOD", 90);
        this.f680a.put("CFG_MAX_REPORTS_COUNT", 7);
        this.f680a.put("CFG_SESSION_TIMEOUT", 10);
        this.f680a.put("CFG_REPORTS", true);
        this.f680a.put("CFG_REPORTS_CRASHES", true);
        this.f680a.put("CFG_REPORTS_NATIVE_CRASHES", true);
        this.f680a.put("CFG_REPORT_LOCATION", true);
        this.f680a.put("CFG_COLLECT_INSTALLED_APPS", Integer.valueOf(a.FALSE.d));
        this.f680a.putNull("CFG_HOST_URL");
        this.f680a.putNull("CFG_CUSTOM_HOSTS");
        this.f680a.putNull("CFG_MANUAL_LOCATION");
        this.f680a.putNull("CFG_APP_VERSION");
        this.f680a.putNull("CFG_APP_VERSION_CODE");
        this.f680a.putNull("CFG_API_KEY");
        this.f680a.putNull("CFG_PACKAGE_NAME");
        this.f680a.putNull("CFG_UUID");
        this.f680a.putNull("CFG_DEVICE_ID");
        this.f680a.putNull("CFG_DEVICE_SIZE_TYPE");
        this.f680a.putNull("CFG_CLIDS");
        this.f680a.putNull("CFG_IS_FIRST_ACTIVATION_AS_UPDATE");
        this.f680a.put("CFG_MAIN_REPORTER", true);
        this.f680a.put("CFG_IS_LOG_ENABLED", false);
        this.f680a.put("CFG_APP_FRAMEWORK", bc.c());
    }

    public void a(e eVar) {
        boolean z = false;
        if (eVar.getSessionTimeout() != null) {
            c(eVar.getSessionTimeout().intValue());
        }
        if (eVar.getLocation() != null) {
            a(eVar.getLocation());
        }
        if (eVar.isTrackLocationEnabled() != null) {
            c(eVar.isTrackLocationEnabled().booleanValue());
        }
        if (eVar.isCollectInstalledApps() != null) {
            d(eVar.isCollectInstalledApps().booleanValue());
        }
        if (eVar.isReportCrashEnabled() != null) {
            a(eVar.isReportCrashEnabled().booleanValue());
        }
        if (eVar.isReportNativeCrashEnabled() != null) {
            b(eVar.isReportNativeCrashEnabled().booleanValue());
        }
        if (eVar.e() != null) {
            a(eVar.e());
        }
        if (eVar.i() != null) {
            a(eVar.i().intValue());
        }
        if (eVar.h() != null) {
            b(eVar.h().intValue());
        }
        if (!bi.a(eVar.getAppVersion())) {
            g(eVar.getAppVersion());
        }
        if (eVar.d() != null) {
            d(eVar.d().intValue());
        }
        if (eVar.c() != null) {
            a(eVar.c());
        }
        if (eVar.k() != null) {
            f(eVar.k().booleanValue());
        }
        if (eVar.l() != null) {
            g(eVar.l().booleanValue());
        }
        if (eVar.f() != null) {
            a(eVar.f());
        }
        if (eVar.g() != null) {
            z = true;
        }
        if (z) {
            h(eVar.g());
        }
        if (eVar.isFirstActivationAsUpdate()) {
            this.f680a.put("CFG_IS_FIRST_ACTIVATION_AS_UPDATE", true);
        }
    }

    public void a(ResultReceiver resultReceiver) {
        this.b = resultReceiver;
    }

    public ResultReceiver a() {
        return this.b;
    }

    public void a(int i) {
        this.f680a.put("CFG_DISPATCH_PERIOD", Integer.valueOf(i));
    }

    public int b() {
        return this.f680a.getAsInteger("CFG_DISPATCH_PERIOD").intValue();
    }

    public void b(int i) {
        ContentValues contentValues = this.f680a;
        if (i <= 0) {
            i = Integer.MAX_VALUE;
        }
        contentValues.put("CFG_MAX_REPORTS_COUNT", Integer.valueOf(i));
    }

    public int c() {
        return this.f680a.getAsInteger("CFG_MAX_REPORTS_COUNT").intValue();
    }

    public void c(int i) {
        this.f680a.put("CFG_SESSION_TIMEOUT", Integer.valueOf(Math.max(10, i)));
    }

    public int d() {
        return this.f680a.getAsInteger("CFG_SESSION_TIMEOUT").intValue();
    }

    public void a(a aVar) {
        this.f680a.put("CFG_DEVICE_SIZE_TYPE", aVar == null ? null : aVar.a());
    }

    public a e() {
        return a.a(this.f680a.getAsString("CFG_DEVICE_SIZE_TYPE"));
    }

    public void a(String str) {
        bk.a(str);
        this.f680a.put("CFG_API_KEY", str);
    }

    public void b(String str) {
        this.f680a.put("CFG_API_KEY", str);
    }

    public void c(String str) {
        this.f680a.put("CFG_PACKAGE_NAME", str);
    }

    public String f() {
        return this.f680a.getAsString("CFG_PACKAGE_NAME");
    }

    public void d(String str) {
        this.f680a.put("CFG_UUID", str);
    }

    public String g() {
        return this.f680a.getAsString("CFG_UUID");
    }

    public void e(String str) {
        this.f680a.put("CFG_DEVICE_ID", str);
    }

    public String h() {
        return this.f680a.getAsString("CFG_DEVICE_ID");
    }

    public void f(String str) {
        this.f680a.put("CFG_POSSIBLE_DEVICE_ID", str);
    }

    public String i() {
        return this.f680a.getAsString("CFG_POSSIBLE_DEVICE_ID");
    }

    public String j() {
        return this.f680a.getAsString("CFG_API_KEY");
    }

    public void a(boolean z) {
        this.f680a.put("CFG_REPORTS_CRASHES", Boolean.valueOf(z));
    }

    public boolean k() {
        return this.f680a.getAsBoolean("CFG_REPORTS_CRASHES").booleanValue();
    }

    public void b(boolean z) {
        this.f680a.put("CFG_REPORTS_NATIVE_CRASHES", Boolean.valueOf(z));
    }

    public boolean l() {
        return this.f680a.getAsBoolean("CFG_REPORTS_NATIVE_CRASHES").booleanValue();
    }

    public void c(boolean z) {
        this.f680a.put("CFG_REPORT_LOCATION", Boolean.valueOf(z));
    }

    public boolean m() {
        return this.f680a.getAsBoolean("CFG_REPORT_LOCATION").booleanValue();
    }

    public void a(List<String> list) {
        this.f680a.put("CFG_CUSTOM_HOSTS", g.a(list));
    }

    public List<String> n() {
        String asString = this.f680a.getAsString("CFG_CUSTOM_HOSTS");
        if (TextUtils.isEmpty(asString)) {
            return null;
        }
        return g.b(asString);
    }

    public void g(String str) {
        this.f680a.put("CFG_APP_VERSION", str);
    }

    public String o() {
        return this.f680a.getAsString("CFG_APP_VERSION");
    }

    public void d(int i) {
        this.f680a.put("CFG_APP_VERSION_CODE", String.valueOf(i));
    }

    public String p() {
        return this.f680a.getAsString("CFG_APP_VERSION_CODE");
    }

    public void d(boolean z) {
        this.f680a.put("CFG_COLLECT_INSTALLED_APPS", Integer.valueOf((z ? a.TRUE : a.FALSE).d));
    }

    public a r() {
        return a(this.f680a.get("CFG_COLLECT_INSTALLED_APPS"));
    }

    public void a(Location location) {
        this.f680a.put("CFG_MANUAL_LOCATION", y.b(location));
    }

    public void e(boolean z) {
        this.f680a.put("CFG_IS_LOG_ENABLED", Boolean.valueOf(z));
    }

    public boolean s() {
        if (this.f680a.containsKey("CFG_IS_LOG_ENABLED")) {
            return this.f680a.getAsBoolean("CFG_IS_LOG_ENABLED").booleanValue();
        }
        return false;
    }

    public Location t() {
        Location a2 = y.a(this.f680a.getAsByteArray("CFG_MANUAL_LOCATION"));
        if (a2 != null || !B()) {
            return a2;
        }
        Double z = z();
        Double A = A();
        Location location = new Location("NONE");
        location.setLatitude(z.doubleValue());
        location.setLongitude(A.doubleValue());
        location.setTime(System.currentTimeMillis());
        return location;
    }

    public void a(Map<String, String> map) {
        this.f680a.put("CFG_CLIDS", g.a((Map) map));
    }

    public Map<String, String> u() {
        return g.a(this.f680a.getAsString("CFG_CLIDS"));
    }

    public String v() {
        return this.f680a.getAsString("CFG_DISTRIBUTION_REFERRER");
    }

    public void h(String str) {
        this.f680a.put("CFG_DISTRIBUTION_REFERRER", str);
    }

    public boolean w() {
        Boolean asBoolean = this.f680a.getAsBoolean("CFG_AUTO_PRELOAD_INFO_DETECTION");
        if (asBoolean != null) {
            return asBoolean.booleanValue();
        }
        return false;
    }

    public boolean x() {
        Boolean asBoolean = this.f680a.getAsBoolean("CFG_PERMISSIONS_COLLECTING");
        if (asBoolean == null) {
            return true;
        }
        return asBoolean.booleanValue();
    }

    public void f(boolean z) {
        this.f680a.put("CFG_AUTO_PRELOAD_INFO_DETECTION", Boolean.valueOf(z));
    }

    public void g(boolean z) {
        this.f680a.put("CFG_PERMISSIONS_COLLECTING", Boolean.valueOf(z));
    }

    public boolean y() {
        if (this.f680a.get("CFG_IS_FIRST_ACTIVATION_AS_UPDATE") != null) {
            return this.f680a.getAsBoolean("CFG_IS_FIRST_ACTIVATION_AS_UPDATE").booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public Double z() {
        return this.f680a.getAsDouble("CFG_LOCATION_LATITUDE");
    }

    /* access modifiers changed from: package-private */
    public Double A() {
        return this.f680a.getAsDouble("CFG_LOCATION_LONGITUDE");
    }

    /* access modifiers changed from: package-private */
    public boolean B() {
        boolean z = true;
        boolean z2 = this.f680a.getAsDouble("CFG_LOCATION_LONGITUDE") != null;
        if (this.f680a.getAsDouble("CFG_LOCATION_LATITUDE") == null) {
            z = false;
        }
        return z2 & z;
    }

    public CounterConfiguration(Parcel parcel) {
        a(parcel);
    }

    public void a(Parcel parcel) {
        this.f680a = (ContentValues) parcel.readParcelable(ContentValues.class.getClass().getClassLoader());
        this.b = (ResultReceiver) parcel.readParcelable(ResultReceiver.class.getClass().getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f680a, 0);
        ResultReceiver resultReceiver = this.b;
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        obtain.recycle();
        parcel.writeParcelable((ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain), 0);
    }

    public void h(boolean z) {
        this.f680a.put("CFG_MAIN_REPORTER", Boolean.valueOf(z));
    }

    public boolean C() {
        Boolean asBoolean = this.f680a.getAsBoolean("CFG_MAIN_REPORTER");
        if (asBoolean != null) {
            return asBoolean.booleanValue();
        }
        return true;
    }

    public boolean D() {
        return bk.b(j());
    }

    public String E() {
        return this.f680a.getAsString("CFG_APP_FRAMEWORK");
    }

    public Bundle F() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("COUNTER_CFG_OBJ", this);
        return bundle;
    }

    public void a(CounterConfiguration counterConfiguration) {
        if (this.f680a.containsKey("CFG_DISPATCH_PERIOD")) {
            this.f680a.put("CFG_DISPATCH_PERIOD", counterConfiguration.f680a.getAsInteger("CFG_DISPATCH_PERIOD"));
        }
        if (this.f680a.containsKey("CFG_SESSION_TIMEOUT")) {
            this.f680a.put("CFG_SESSION_TIMEOUT", counterConfiguration.f680a.getAsInteger("CFG_SESSION_TIMEOUT"));
        }
        if (this.f680a.containsKey("CFG_MAX_REPORTS_COUNT")) {
            this.f680a.put("CFG_MAX_REPORTS_COUNT", counterConfiguration.f680a.getAsInteger("CFG_MAX_REPORTS_COUNT"));
        }
        if (this.f680a.containsKey("CFG_REPORTS_CRASHES")) {
            this.f680a.put("CFG_REPORTS_CRASHES", counterConfiguration.f680a.getAsBoolean("CFG_REPORTS_CRASHES"));
        }
        if (this.f680a.containsKey("CFG_REPORTS_NATIVE_CRASHES")) {
            this.f680a.put("CFG_REPORTS_NATIVE_CRASHES", counterConfiguration.f680a.getAsBoolean("CFG_REPORTS_NATIVE_CRASHES"));
        }
        if (this.f680a.containsKey("CFG_REPORT_LOCATION")) {
            this.f680a.put("CFG_REPORT_LOCATION", counterConfiguration.f680a.getAsBoolean("CFG_REPORT_LOCATION"));
        }
        if (this.f680a.containsKey("CFG_MANUAL_LOCATION")) {
            this.f680a.put("CFG_MANUAL_LOCATION", counterConfiguration.f680a.getAsByteArray("CFG_MANUAL_LOCATION"));
        }
        if (this.f680a.containsKey("CFG_COLLECT_INSTALLED_APPS")) {
            this.f680a.put("CFG_COLLECT_INSTALLED_APPS", Integer.valueOf(a(counterConfiguration.f680a.get("CFG_COLLECT_INSTALLED_APPS")).d));
        }
        if (this.f680a.containsKey("CFG_DEVICE_SIZE_TYPE")) {
            this.f680a.put("CFG_DEVICE_SIZE_TYPE", counterConfiguration.f680a.getAsString("CFG_DEVICE_SIZE_TYPE"));
        }
        if (this.f680a.containsKey("CFG_IS_LOG_ENABLED")) {
            this.f680a.put("CFG_IS_LOG_ENABLED", counterConfiguration.f680a.getAsBoolean("CFG_IS_LOG_ENABLED"));
        }
        if (this.f680a.containsKey("CFG_CLIDS")) {
            this.f680a.put("CFG_CLIDS", counterConfiguration.f680a.getAsString("CFG_CLIDS"));
        }
        if (this.f680a.containsKey("CFG_AUTO_PRELOAD_INFO_DETECTION")) {
            this.f680a.put("CFG_AUTO_PRELOAD_INFO_DETECTION", counterConfiguration.f680a.getAsBoolean("CFG_AUTO_PRELOAD_INFO_DETECTION"));
        }
        if (this.f680a.containsKey("CFG_PERMISSIONS_COLLECTING")) {
            this.f680a.put("CFG_PERMISSIONS_COLLECTING", counterConfiguration.f680a.getAsBoolean("CFG_PERMISSIONS_COLLECTING"));
        }
    }

    public void a(Bundle bundle) {
        if (bundle != null) {
            if (bundle.getInt("CFG_DISPATCH_PERIOD") != 0) {
                a(bundle.getInt("CFG_DISPATCH_PERIOD"));
            }
            if (bundle.getInt("CFG_SESSION_TIMEOUT") != 0) {
                c(bundle.getInt("CFG_SESSION_TIMEOUT"));
            }
            if (bundle.getInt("CFG_MAX_REPORTS_COUNT") != 0) {
                b(bundle.getInt("CFG_MAX_REPORTS_COUNT"));
            }
            if (bundle.getString("CFG_API_KEY") != null && !"-1".equals(bundle.getString("CFG_API_KEY"))) {
                a(bundle.getString("CFG_API_KEY"));
            }
        }
    }

    private static a a(Object obj) {
        if (obj != null) {
            if (obj instanceof Integer) {
                return a.a(((Integer) obj).intValue());
            }
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue() ? a.TRUE : a.FALSE;
            }
        }
        return a.UNDEFINED;
    }

    public static CounterConfiguration b(Bundle bundle) {
        CounterConfiguration counterConfiguration = null;
        if (bundle != null) {
            try {
                counterConfiguration = (CounterConfiguration) bundle.getParcelable("COUNTER_CFG_OBJ");
            } catch (Throwable unused) {
                return null;
            }
        }
        if (counterConfiguration == null) {
            counterConfiguration = new CounterConfiguration();
        }
        counterConfiguration.a(bundle);
        return counterConfiguration;
    }
}
