package com.yandex.metrica.impl;

import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.gms.ads.AdRequest;
import java.util.EnumSet;

public final class p {

    /* renamed from: a  reason: collision with root package name */
    public static final EnumSet<a> f946a = EnumSet.of(a.EVENT_TYPE_INIT, new a[]{a.EVENT_TYPE_INIT_BACKGROUND, a.EVENT_TYPE_CUSTOM_EVENT, a.EVENT_TYPE_FIRST_ACTIVATION, a.EVENT_TYPE_REFERRER_RECEIVED, a.EVENT_TYPE_REFERRER_DEPRECATED, a.EVENT_TYPE_APP_UPDATE});
    private static final EnumSet<a> b = EnumSet.of(a.EVENT_TYPE_UNDEFINED, new a[]{a.EVENT_TYPE_PURGE_BUFFER, a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS, a.EVENT_TYPE_REFERRER_RECEIVED, a.EVENT_TYPE_MIGRATE_EVENT_FORMAT_DEPRECATED, a.EVENT_TYPE_MIGRATE_TO_UUID_API_KEY_DEPRECATED, a.EVENT_TYPE_REFERRER_DEPRECATED, a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED, a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED, a.EVENT_TYPE_ACTIVATION});
    private static final EnumSet<a> c = EnumSet.of(a.EVENT_TYPE_SET_USER_INFO, new a[]{a.EVENT_TYPE_REPORT_USER_INFO, a.EVENT_TYPE_IDENTITY, a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS, a.EVENT_TYPE_UNDEFINED, a.EVENT_TYPE_INIT, a.EVENT_TYPE_APP_UPDATE, a.EVENT_TYPE_REFERRER_DEPRECATED, a.EVENT_TYPE_ALIVE, a.EVENT_TYPE_INIT_BACKGROUND, a.EVENT_TYPE_STARTUP, a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED, a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED, a.EVENT_TYPE_ACTIVATION});
    private static final EnumSet<a> d = EnumSet.of(a.EVENT_TYPE_ACTIVITY_END, a.EVENT_TYPE_SET_USER_INFO, a.EVENT_TYPE_REPORT_USER_INFO);
    private static final EnumSet<a> e = EnumSet.of(a.EVENT_TYPE_STARTUP, new a[]{a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS, a.EVENT_TYPE_REFERRER_RECEIVED, a.EVENT_TYPE_REFERRER_DEPRECATED, a.EVENT_TYPE_MIGRATE_EVENT_FORMAT_DEPRECATED, a.EVENT_TYPE_MIGRATE_TO_UUID_API_KEY_DEPRECATED, a.EVENT_TYPE_UNDEFINED, a.EVENT_TYPE_ALIVE, a.EVENT_TYPE_INIT_BACKGROUND, a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED, a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED});
    private static final EnumSet<a> f = EnumSet.of(a.EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED, a.EVENT_TYPE_EXCEPTION_UNHANDLED, a.EVENT_TYPE_EXCEPTION_USER, a.EVENT_TYPE_NATIVE_CRASH, a.EVENT_TYPE_REGULAR);
    private static final EnumSet<a> g = EnumSet.of(a.EVENT_TYPE_REGULAR);

    public enum a {
        EVENT_TYPE_UNDEFINED(-1, "Unrecognized action"),
        EVENT_TYPE_INIT(0, "First initialization event"),
        EVENT_TYPE_REGULAR(1, "Regular event"),
        EVENT_TYPE_ACTIVITY_START_DEPRECATED(2, "Start of interaction with UI"),
        EVENT_TYPE_ACTIVITY_END(3, "End of interaction with UI"),
        EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED(4, "Deprecated crash of App"),
        EVENT_TYPE_EXCEPTION_USER(5, "Error from developer"),
        EVENT_TYPE_REFERRER_DEPRECATED(6, "Deprecated sending referrer"),
        EVENT_TYPE_ALIVE(7, "App is still alive"),
        EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS(8, "Update collect apps"),
        EVENT_TYPE_SET_USER_INFO(9, "User info"),
        EVENT_TYPE_REPORT_USER_INFO(10, "Report user info"),
        EVENT_TYPE_PURGE_BUFFER(256, "Forcible buffer clearing"),
        EVENT_TYPE_SESSION_START_MANUALLY(AdRequest.MAX_CONTENT_URL_LENGTH, "Manual start of session"),
        EVENT_TYPE_NATIVE_CRASH(768, "Native crash of App"),
        EVENT_TYPE_INIT_BACKGROUND(1280, "First initialization event in background mode"),
        EVENT_TYPE_STARTUP(1536, "Sending the startup due to lack of data"),
        EVENT_TYPE_IDENTITY(1792, "System identification"),
        EVENT_TYPE_STATBOX(2304, "Event with statistical data"),
        EVENT_TYPE_REFERRER_RECEIVED(4096, "Referrer received"),
        EVENT_TYPE_MIGRATE_EVENT_FORMAT_DEPRECATED(4352, "Migrate event format"),
        EVENT_TYPE_MIGRATE_TO_UUID_API_KEY_DEPRECATED(4608, "Migrate to uuid api key"),
        EVENT_TYPE_APP_ENVIRONMENT_UPDATED(5376, "App Environment Updated"),
        EVENT_TYPE_APP_ENVIRONMENT_CLEARED(5632, "App Environment Cleared"),
        EVENT_TYPE_EXCEPTION_UNHANDLED(5888, "Crash of App"),
        EVENT_TYPE_ACTIVATION(6144, "Activation of metrica"),
        EVENT_TYPE_FIRST_ACTIVATION(6145, "First activation of metrica"),
        EVENT_TYPE_START(6400, "Start of new session"),
        EVENT_TYPE_CUSTOM_EVENT(8192, "Custom event"),
        EVENT_TYPE_APP_OPEN(8208, "App open event"),
        EVENT_TYPE_APP_UPDATE(8224, "App update event"),
        EVENT_TYPE_PERMISSIONS(12288, "Permissions changed event"),
        EVENT_TYPE_APP_FEATURES(12289, "Features, required by application");
        
        static final SparseArray<a> H = null;
        private final int I;
        private final String J;

        static {
            int i;
            H = new SparseArray<>();
            for (a aVar : values()) {
                H.put(aVar.a(), aVar);
            }
        }

        private a(int i, String str) {
            this.I = i;
            this.J = str;
        }

        public int a() {
            return this.I;
        }

        public String b() {
            return this.J;
        }

        public static a a(int i) {
            a aVar = H.get(i);
            return aVar == null ? EVENT_TYPE_UNDEFINED : aVar;
        }
    }

    public static boolean a(a aVar) {
        return !b.contains(aVar);
    }

    public static boolean b(a aVar) {
        return !c.contains(aVar);
    }

    public static boolean a(int i) {
        return d.contains(a.a(i));
    }

    public static boolean a(h hVar) {
        return (hVar.c() == a.EVENT_TYPE_SET_USER_INFO.a() || hVar.c() == a.EVENT_TYPE_REPORT_USER_INFO.a()) && !TextUtils.isEmpty(hVar.l());
    }

    public static boolean c(a aVar) {
        return !e.contains(aVar);
    }

    public static boolean b(int i) {
        return f.contains(a.a(i));
    }

    public static boolean c(int i) {
        return g.contains(a.a(i));
    }

    static h a(a aVar, String str) {
        return new e(str, aVar.b(), aVar.a());
    }

    public static h d(a aVar) {
        return new e(aVar.b(), aVar.a());
    }

    public static h a(String str) {
        return new e(str, a.EVENT_TYPE_REGULAR.a());
    }

    static h a(String str, String str2) {
        return new e(str2, str, a.EVENT_TYPE_REGULAR.a());
    }

    static h b(String str, String str2) {
        return new e(str2, str, a.EVENT_TYPE_EXCEPTION_USER.a());
    }

    static h b(String str) {
        return new e(str, a.EVENT_TYPE_START.a());
    }

    static h c(String str) {
        return new e(str, a.EVENT_TYPE_ACTIVITY_END.a());
    }

    static h c(String str, String str2) {
        return new e(str2, str, a.EVENT_TYPE_EXCEPTION_UNHANDLED.a());
    }

    public static h d(String str) {
        return new e("", str, a.EVENT_TYPE_REFERRER_RECEIVED.a());
    }

    public static h a(an anVar) {
        return new e(anVar == null ? "" : anVar.a(), a.EVENT_TYPE_ACTIVATION.b(), a.EVENT_TYPE_ACTIVATION.a());
    }
}
