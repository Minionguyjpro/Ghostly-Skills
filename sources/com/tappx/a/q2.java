package com.tappx.a;

import android.content.SharedPreferences;

final class q2 {

    /* renamed from: a  reason: collision with root package name */
    private final SharedPreferences f561a;

    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f562a;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.tappx.a.p2[] r0 = com.tappx.a.p2.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f562a = r0
                com.tappx.a.p2 r1 = com.tappx.a.p2.DENIED_DEVELOPER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f562a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.p2 r1 = com.tappx.a.p2.DENIED_USER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f562a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.p2 r1 = com.tappx.a.p2.GRANTED_DEVELOPER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f562a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.p2 r1 = com.tappx.a.p2.GRANTED_USER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f562a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.tappx.a.p2 r1 = com.tappx.a.p2.MISSING_ANSWER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.q2.a.<clinit>():void");
        }
    }

    q2(SharedPreferences sharedPreferences) {
        this.f561a = sharedPreferences;
    }

    private p2 c(int i) {
        if (i == -2) {
            return p2.DENIED_DEVELOPER;
        }
        if (i == -1) {
            return p2.DENIED_USER;
        }
        if (i == 1) {
            return p2.GRANTED_USER;
        }
        if (i != 2) {
            return p2.MISSING_ANSWER;
        }
        return p2.GRANTED_DEVELOPER;
    }

    public void a(Boolean bool, String str) {
        this.f561a.edit().putInt("tappx_privacy_applies", b(bool)).putString("tappx_privacy_consent_html", str).apply();
    }

    public Boolean d() {
        return b(this.f561a.getInt("tappx_privacy_applies", 0));
    }

    public long e() {
        return this.f561a.getLong("tappx_consent_timestamp", -1);
    }

    public String f() {
        return this.f561a.getString("tappx_privacy_gdpr_consent", (String) null);
    }

    public p2 g() {
        return c(this.f561a.getInt("tappx_privacy_accepted", 0));
    }

    public String h() {
        return this.f561a.getString("tappx_mark_choice", (String) null);
    }

    public String i() {
        return this.f561a.getString("tappx_privacy_consent_html", (String) null);
    }

    public int j() {
        return this.f561a.getInt("tappx_privacy_version", 0);
    }

    public String k() {
        return this.f561a.getString("tappx_usprivacy_string", (String) null);
    }

    public boolean l() {
        return this.f561a.getBoolean("tappx_privacy_autoDisclaimer", false);
    }

    public boolean m() {
        return this.f561a.getBoolean("tappx_privacy_renew", false);
    }

    private Boolean b(int i) {
        return (i == -1 || i == 1) ? true : null;
    }

    private int b(Boolean bool) {
        if (bool == null) {
            return 0;
        }
        return bool.booleanValue() ? 1 : -1;
    }

    public void a(Boolean bool) {
        this.f561a.edit().putInt("tappx_privacy_applies", b(bool)).apply();
    }

    public void b() {
        this.f561a.edit().remove("tappx_privacy_accepted").remove("tappx_sync_required").apply();
    }

    public void c(boolean z) {
        if (z) {
            this.f561a.edit().putBoolean("tappx_privacy_renew", true).apply();
        } else {
            this.f561a.edit().remove("tappx_privacy_renew").apply();
        }
    }

    private int b(p2 p2Var) {
        int i = a.f562a[p2Var.ordinal()];
        if (i == 1) {
            return -2;
        }
        if (i == 2) {
            return -1;
        }
        if (i != 3) {
            return i != 4 ? 0 : 1;
        }
        return 2;
    }

    public void a(p2 p2Var) {
        this.f561a.edit().putInt("tappx_privacy_accepted", b(p2Var)).apply();
    }

    public void b(String str) {
        this.f561a.edit().putString("tappx_privacy_gdpr_consent", str).apply();
    }

    public void a(boolean z) {
        this.f561a.edit().putBoolean("tappx_privacy_autoDisclaimer", z).apply();
    }

    public void b(boolean z) {
        this.f561a.edit().putBoolean("tappx_sync_required", z).apply();
    }

    public void c(String str) {
        this.f561a.edit().putString("tappx_usprivacy_string", str).apply();
    }

    public void a() {
        this.f561a.edit().remove("tappx_privacy_applies").remove("tappx_privacy_autoDisclaimer").remove("tappx_consent_timestamp").remove("tappx_privacy_renew").remove("tappx_privacy_consent_html").apply();
    }

    public boolean c() {
        return this.f561a.getBoolean("tappx_sync_required", false);
    }

    public void a(int i) {
        this.f561a.edit().putInt("tappx_privacy_version", i).apply();
    }

    public void a(long j) {
        this.f561a.edit().putLong("tappx_consent_timestamp", j).apply();
    }

    public void a(String str) {
        this.f561a.edit().putString("tappx_mark_choice", str).apply();
    }
}
