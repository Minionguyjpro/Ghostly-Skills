package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.bk;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class bm {

    /* renamed from: a  reason: collision with root package name */
    public static final Boolean f794a = false;
    public static final int b = YandexMetrica.getLibraryApiLevel();
    static final SparseArray<l> c;
    static final SparseArray<l> d;
    static final HashMap<String, String[]> e;

    public static final class aa implements v {
    }

    interface v {

        /* renamed from: a  reason: collision with root package name */
        public static final String[] f796a = {"key", "value", "type"};
    }

    public static final class w {

        /* renamed from: a  reason: collision with root package name */
        public static final String[] f797a = {"name", "granted"};
    }

    public static final class x implements v {
    }

    public static final class y {

        /* renamed from: a  reason: collision with root package name */
        public static final String[] f798a = {"id", "number", "name", "value", "type", com.appnext.base.b.d.fl, "session_id", "wifi_network_info", "cell_info", "location_info", "error_environment", "user_info", "session_type", "app_environment", "app_environment_revision", "truncated", "connection_type", "cellular_connection_type", "custom_type", "wifi_access_point"};
        static final String b = ("CREATE TABLE IF NOT EXISTS reports (id INTEGER PRIMARY KEY,name TEXT,value TEXT,number INTEGER,type INTEGER,time INTEGER,session_id TEXT,wifi_network_info TEXT DEFAULT '',cell_info TEXT DEFAULT '',location_info TEXT DEFAULT '',error_environment TEXT,user_info TEXT,session_type INTEGER DEFAULT " + bl.FOREGROUND.a() + ",app_environment TEXT DEFAULT '{}',app_environment_revision INTEGER DEFAULT 0,truncated INTEGER DEFAULT 0,connection_type INTEGER DEFAULT 2,cellular_connection_type TEXT,custom_type INTEGER DEFAULT 0, wifi_access_point TEXT )");
    }

    public static final class z {

        /* renamed from: a  reason: collision with root package name */
        public static final String[] f799a = {"id", "start_time", "network_info", "report_request_parameters", "server_time_offset", "type"};
        static final String b = ("CREATE TABLE IF NOT EXISTS sessions (id INTEGER,start_time INTEGER,network_info TEXT,report_request_parameters TEXT,server_time_offset INTEGER,type INTEGER DEFAULT " + bl.FOREGROUND.a() + " )");
        public static final String c = String.format(Locale.US, "(select count(%s.%s) from %s where %s.%s = %s.%s) = 0 and %s NOT IN (%s)", new Object[]{"reports", "id", "reports", "reports", "session_id", "sessions", "id", "id", com.yandex.metrica.impl.utils.e.a(2)});
    }

    static {
        SparseArray<l> sparseArray = new SparseArray<>();
        c = sparseArray;
        sparseArray.put(6, new t((byte) 0));
        c.put(7, new u((byte) 0));
        c.put(14, new m((byte) 0));
        c.put(29, new n((byte) 0));
        c.put(37, new o((byte) 0));
        c.put(39, new p((byte) 0));
        c.put(45, new q((byte) 0));
        c.put(47, new r((byte) 0));
        c.put(50, new s((byte) 0));
        SparseArray<l> sparseArray2 = new SparseArray<>();
        d = sparseArray2;
        sparseArray2.put(12, new g((byte) 0));
        d.put(29, new h((byte) 0));
        d.put(47, new i((byte) 0));
        d.put(50, new j((byte) 0));
        d.put(55, new k((byte) 0));
        HashMap<String, String[]> hashMap = new HashMap<>();
        e = hashMap;
        hashMap.put("reports", y.f798a);
        e.put("sessions", z.f799a);
        e.put("preferences", x.f796a);
    }

    public static bs a() {
        return new bs(new c(), new d(), c, new bu(e));
    }

    public static bs b() {
        HashMap hashMap = new HashMap();
        hashMap.put("preferences", x.f796a);
        hashMap.put("startup", aa.f796a);
        hashMap.put("permissions", w.f797a);
        return new bs(new e(), new f(), d, new bu(hashMap));
    }

    public static bs c() {
        HashMap hashMap = new HashMap();
        hashMap.put("preferences", x.f796a);
        return new bs(new a((byte) 0), new b((byte) 0), new SparseArray(), new bu(hashMap));
    }

    static abstract class l {
        /* access modifiers changed from: protected */
        public abstract void a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException;

        l() {
        }
    }

    private static class t extends l {
        private t() {
        }

        /* synthetic */ t(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("ALTER TABLE sessions" + " ADD COLUMN wifi_network_info" + " TEXT DEFAULT ''");
        }
    }

    private static class u extends l {
        private u() {
        }

        /* synthetic */ u(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("ALTER TABLE sessions" + " ADD COLUMN report_request_parameters" + " TEXT DEFAULT ''");
        }
    }

    private static class m extends l {
        private m() {
        }

        /* synthetic */ m(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS sessions_BACKUP (" + "id INTEGER," + "start_time INTEGER," + "connection_type INTEGER," + "network_type TEXT," + "country_code INTEGER," + "operator_id INTEGER," + "lac INTEGER," + "report_request_parameters TEXT );");
            StringBuilder sb = new StringBuilder();
            sb.append("id,");
            sb.append("start_time,");
            sb.append("connection_type,");
            sb.append("network_type,");
            sb.append("country_code,");
            sb.append("operator_id,");
            sb.append("lac,");
            sb.append("report_request_parameters");
            sQLiteDatabase.execSQL("INSERT INTO sessions_BACKUP" + " SELECT " + sb + " FROM sessions;");
            sQLiteDatabase.execSQL("DROP TABLE sessions;");
            sQLiteDatabase.execSQL(z.b);
            Cursor cursor = null;
            try {
                Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM sessions_BACKUP", (String[]) null);
                while (rawQuery.moveToNext()) {
                    try {
                        ContentValues contentValues = new ContentValues();
                        DatabaseUtils.cursorRowToContentValues(rawQuery, contentValues);
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add("id");
                        arrayList.add("start_time");
                        arrayList.add("report_request_parameters");
                        ContentValues contentValues2 = new ContentValues(contentValues);
                        for (Map.Entry next : contentValues.valueSet()) {
                            if (!arrayList.contains(next.getKey())) {
                                contentValues2.remove((String) next.getKey());
                            }
                        }
                        for (String remove : arrayList) {
                            contentValues.remove(remove);
                        }
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("conn_type", contentValues.getAsInteger("connection_type"));
                        jSONObject.putOpt("net_type", contentValues.get("network_type"));
                        jSONObject.putOpt("operator_id", contentValues.get("operator_id"));
                        jSONObject.putOpt("lac", contentValues.get("lac"));
                        jSONObject.putOpt("country_code", contentValues.get("country_code"));
                        contentValues2.put("network_info", jSONObject.toString());
                        sQLiteDatabase.insertOrThrow("sessions", (String) null, contentValues2);
                    } catch (Throwable th) {
                        th = th;
                        cursor = rawQuery;
                        bk.a(cursor);
                        throw th;
                    }
                }
                bk.a(rawQuery);
                sQLiteDatabase.execSQL("DROP TABLE sessions_BACKUP;");
                sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN wifi_network_info" + " TEXT DEFAULT ''");
                sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN cell_info" + " TEXT DEFAULT ''");
                sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN location_info" + " TEXT DEFAULT ''");
            } catch (Throwable th2) {
                th = th2;
                bk.a(cursor);
                throw th;
            }
        }
    }

    private static class n extends l {
        private n() {
        }

        /* synthetic */ n(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN environment" + " TEXT ");
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN user_info" + " TEXT ");
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN session_type" + " INTEGER DEFAULT " + bl.FOREGROUND.a());
            sQLiteDatabase.execSQL("UPDATE reports" + " SET session_type = " + bl.BACKGROUND.a() + " WHERE session_id" + " = -2");
            StringBuilder sb = new StringBuilder();
            sb.append("ALTER TABLE sessions");
            sb.append(" ADD COLUMN server_time_offset");
            sb.append(" INTEGER ");
            sQLiteDatabase.execSQL(sb.toString());
            sQLiteDatabase.execSQL("ALTER TABLE sessions" + " ADD COLUMN type" + " INTEGER DEFAULT " + bl.FOREGROUND.a());
            sQLiteDatabase.execSQL("UPDATE sessions" + " SET type = " + bl.BACKGROUND.a() + " WHERE id" + " = -2");
        }
    }

    private static class g extends l {
        private g() {
        }

        /* synthetic */ g(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS api_level_info (API_LEVEL INT )");
            ContentValues contentValues = new ContentValues();
            contentValues.put("API_LEVEL", Integer.valueOf(YandexMetrica.getLibraryApiLevel()));
            sQLiteDatabase.insert("api_level_info", "API_LEVEL", contentValues);
        }
    }

    private static class h extends l {
        private h() {
        }

        /* synthetic */ h(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS api_level_info");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS device_id_info");
        }
    }

    private static class i extends l {
        private i() {
        }

        /* synthetic */ i(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS startup (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
        }
    }

    private static class j extends l {
        private j() {
        }

        /* synthetic */ j(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS GeoLocationInfo");
        }
    }

    private static final class k extends l {
        private k() {
        }

        /* synthetic */ k(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS permissions (name TEXT PRIMARY KEY,granted INTEGER)");
        }
    }

    private static class o extends l {

        /* renamed from: a  reason: collision with root package name */
        private static final String f795a = ("CREATE TABLE IF NOT EXISTS reports (id INTEGER PRIMARY KEY,name TEXT,value TEXT,number INTEGER,type INTEGER,time INTEGER,session_id TEXT,wifi_network_info TEXT DEFAULT '',cell_info TEXT DEFAULT '',location_info TEXT DEFAULT '',error_environment TEXT,user_info TEXT,session_type INTEGER DEFAULT " + bl.FOREGROUND.a() + ",app_environment TEXT DEFAULT '{}',app_environment_revision INTEGER DEFAULT 0 )");

        private o() {
        }

        /* synthetic */ o(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN app_environment" + " TEXT DEFAULT '{}'");
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN app_environment_revision" + " INTEGER DEFAULT 0");
            sQLiteDatabase.execSQL("ALTER TABLE reports RENAME TO reports_backup");
            sQLiteDatabase.execSQL(f795a);
            Cursor cursor = null;
            try {
                Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM reports_backup", (String[]) null);
                while (rawQuery.moveToNext()) {
                    try {
                        ContentValues contentValues = new ContentValues();
                        DatabaseUtils.cursorRowToContentValues(rawQuery, contentValues);
                        String asString = contentValues.getAsString("environment");
                        contentValues.remove("environment");
                        contentValues.put("error_environment", asString);
                        sQLiteDatabase.insert("reports", (String) null, contentValues);
                    } catch (Throwable th) {
                        th = th;
                        cursor = rawQuery;
                        bk.a(cursor);
                        throw th;
                    }
                }
                bk.a(rawQuery);
                sQLiteDatabase.execSQL("DROP TABLE reports_backup");
            } catch (Throwable th2) {
                th = th2;
                bk.a(cursor);
                throw th;
            }
        }
    }

    private static class p extends l {
        private p() {
        }

        /* synthetic */ p(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN truncated" + " INTEGER DEFAULT 0");
        }
    }

    private static class q extends l {
        private q() {
        }

        /* synthetic */ q(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException {
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN connection_type" + " INTEGER DEFAULT 2");
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN cellular_connection_type" + " TEXT ");
        }
    }

    private static class r extends l {
        private r() {
        }

        /* synthetic */ r(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN custom_type" + " INTEGER DEFAULT 0");
        }
    }

    private static class s extends l {
        private s() {
        }

        /* synthetic */ s(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN wifi_access_point" + " TEXT ");
        }
    }

    static class c extends l {
        c() {
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL(y.b);
            sQLiteDatabase.execSQL(z.b);
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
        }
    }

    static class d extends l {
        d() {
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS reports");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sessions");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS preferences");
        }
    }

    static class e extends l {
        e() {
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS startup (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS permissions (name TEXT PRIMARY KEY,granted INTEGER)");
        }
    }

    static class f extends l {
        f() {
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS device_id_info");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS api_level_info");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS preferences");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS startup");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS permissions");
        }
    }

    private static class a extends l {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
        }
    }

    private static class b extends l {
        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS preferences");
        }
    }
}
