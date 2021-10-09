package com.tappx.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.tappx.a.m1;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.json.JSONObject;

public class z2 implements y2 {
    private static volatile y2 h;

    /* renamed from: a  reason: collision with root package name */
    private final Context f634a;
    private final m1.a b;
    private c c;
    private String d;
    private String e;
    private String f;
    private String g = "";

    class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ boolean f635a;

        a(boolean z) {
            this.f635a = z;
        }

        public void run() {
            z2.this.a(this.f635a);
        }
    }

    class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ String f636a;

        b(String str) {
            this.f636a = str;
        }

        public void run() {
            z2.this.f(this.f636a);
        }
    }

    private enum c {
        NORMAL,
        GETCLASS,
        TESTINSTALL_APP,
        TESTINSTALL_MANUAL
    }

    z2(Context context, m1.a aVar) {
        this.f634a = context.getApplicationContext();
        this.b = aVar;
        this.c = c.NORMAL;
    }

    private void b(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("token");
            if (stringExtra == null) {
                stringExtra = "";
            }
            char c2 = 65535;
            int hashCode = stringExtra.hashCode();
            if (hashCode != -1982207016) {
                if (hashCode != 210735) {
                    if (hashCode != 149971738) {
                        if (hashCode == 471550256) {
                            if (stringExtra.equals("TAPPX_AUX")) {
                                c2 = 3;
                            }
                        }
                    } else if (stringExtra.equals("TAPPX_INSTALL_GETCLASS")) {
                        c2 = 0;
                    }
                } else if (stringExtra.equals("TAPPX_INSTALL_TESTMODE_APP")) {
                    c2 = 1;
                }
            } else if (stringExtra.equals("TAPPX_INSTALL_TESTMODE_MANUAL")) {
                c2 = 2;
            }
            if (c2 == 0) {
                this.c = c.GETCLASS;
            } else if (c2 == 1) {
                this.c = c.TESTINSTALL_APP;
            } else if (c2 == 2) {
                this.c = c.TESTINSTALL_MANUAL;
            } else if (c2 != 3) {
                this.c = c.NORMAL;
            } else {
                this.c = c.GETCLASS;
            }
        } catch (Exception e2) {
            c("no string token");
            j0.b("ERROR01: " + e2.getMessage(), new Object[0]);
            e2.printStackTrace();
        }
    }

    private void c(String str) {
        c cVar = this.c;
        if (cVar != c.NORMAL && cVar != c.TESTINSTALL_APP) {
            j0.c(str, new Object[0]);
        }
    }

    private void d(String str) {
        c("SavedReferrer = " + w2.a(this.f634a, "sp_tappx_referrer", "NotFound"));
        if (str != null && str.length() > 0) {
            int indexOf = str.indexOf("referrer=");
            if (indexOf > 0) {
                str = str.substring(indexOf + 9);
            }
            String[] split = URLDecoder.decode(str, "UTF-8").split("&");
            if (split != null) {
                for (String split2 : split) {
                    String[] split3 = split2.split("=");
                    if (split3.length > 1) {
                        if ("tappxs".equalsIgnoreCase(split3[0])) {
                            this.d = split3[1];
                        } else if ("tappxp".equalsIgnoreCase(split3[0])) {
                            this.e = split3[1];
                        } else if ("ord".equalsIgnoreCase(split3[0])) {
                            this.f = split3[1];
                        }
                        c(split3[0] + "=" + split3[1]);
                    }
                }
                c("Referrer = " + str);
            }
        }
    }

    private void e(String str) {
        String str2;
        boolean z;
        if (this.c == c.NORMAL) {
            str2 = w2.a(this.f634a, "sp_tappx_referrer", "NotFound");
        } else {
            str2 = "NotFound";
        }
        if ("NotFound".equals(str2)) {
            a("sp_tappx_referrer", str);
            String str3 = "";
            if (str3.equals(this.d) || str3.equals(this.e)) {
                a("sp_tappx_referrer_send", "0");
                z = false;
            } else {
                str3 = b();
                a("sp_tappx_install_id", str3);
                z = true;
            }
            if (!z) {
                return;
            }
            if (this.c == c.NORMAL) {
                b(false);
            } else {
                g(str3);
            }
        } else {
            c("old_referrer = " + str2);
            b(false);
        }
    }

    /* access modifiers changed from: private */
    public void f(String str) {
        j0.a("Trying to Track Install", new Object[0]);
        String a2 = a("-" + str + ":" + (System.currentTimeMillis() / 1000), 2);
        try {
            a2 = URLEncoder.encode(a2, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
        }
        j0.a("ti->snd", new Object[0]);
        Bundle b2 = b(e() + a2);
        if (b2 != null) {
            String a3 = this.b.a();
            String a4 = a(b2.getCharSequence("HTML"), "");
            String a5 = a(b2.getCharSequence("ERROR"), "");
            if (this.c == c.NORMAL) {
                return;
            }
            if (a5 == null || a5.length() <= 0) {
                j0.a("TrackInstall result: " + a4, new Object[0]);
                if (a(b2.getCharSequence("HTML"), "").trim().equals("1")) {
                    j0.a("ti->ok", new Object[0]);
                    j0.a("Install Tracked", new Object[0]);
                } else {
                    j0.a("ti->ko", new Object[0]);
                    j0.f("Install NOT Tracked", new Object[0]);
                }
                if (c.TESTINSTALL_APP.equals(this.c)) {
                    try {
                        Intent intent = new Intent();
                        intent.addFlags(268435456);
                        intent.setPackage("com.tappx.TrackingTestApp");
                        intent.setAction("com.tappx.TrackingTestApp.TEST_INSTALL");
                        intent.setType("text/plain");
                        intent.putExtra("android.intent.extra.TEXT", a3 + "|" + c() + "|" + this.f634a.getApplicationContext().getPackageName());
                        this.f634a.startActivity(intent);
                    } catch (Exception e2) {
                        j0.a("Error sending to TestApp: " + e2.getMessage(), new Object[0]);
                    }
                }
            } else {
                j0.b("TrackInstall Error: " + a5, new Object[0]);
            }
        }
    }

    private void g(String str) {
        x2.a(new b(str));
    }

    public static final y2 a(Context context) {
        if (h == null) {
            synchronized (z2.class) {
                if (h == null) {
                    h = new z2(context.getApplicationContext(), m1.a.a(context));
                }
            }
        }
        return h;
    }

    private String c(Intent intent) {
        Uri data;
        String str = "";
        try {
            String stringExtra = intent.getStringExtra("referrer");
            if (stringExtra == null) {
                stringExtra = str;
            }
            try {
                if (stringExtra.length() == 0 && (data = intent.getData()) != null) {
                    try {
                        String query = data.getQuery();
                        if (query == null) {
                            return str;
                        }
                        return query;
                    } catch (Exception e2) {
                        c("02. no URI referrer");
                        j0.b("ERROR02: " + e2.getMessage(), new Object[0]);
                    }
                }
                return stringExtra;
            } catch (Exception e3) {
                e = e3;
                str = stringExtra;
                c("01. No string referrer");
                j0.b("ERROR01: " + e.getMessage(), new Object[0]);
                return str;
            }
        } catch (Exception e4) {
            e = e4;
            c("01. No string referrer");
            j0.b("ERROR01: " + e.getMessage(), new Object[0]);
            return str;
        }
    }

    public void a() {
        b(true);
    }

    public void a(Intent intent) {
        b(intent);
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        if (this.c != c.GETCLASS) {
            String c2 = c(intent);
            if (c2 == null || c2.length() <= 0) {
                if ("NotFound".equals(w2.a(this.f634a, "sp_tappx_referrer", "NotFound"))) {
                    a("sp_tappx_referrer", c2);
                    if ("NotFound".equals(w2.a(this.f634a, "sp_tappx_referrer_send", "NotFound"))) {
                        a("sp_tappx_referrer_send", "0");
                    }
                } else if (this.c == c.NORMAL) {
                    b(false);
                }
                c("Mode: " + this.c);
                c("Not referrer INFO received.");
                c("SavedReferrer = " + w2.a(this.f634a, "sp_tappx_referrer", "NotFound"));
                c("InstallSend   = " + w2.a(this.f634a, "sp_tappx_referrer_send", "NotFound"));
                return;
            }
            try {
                d(c2);
                e(c2);
            } catch (UnsupportedEncodingException e2) {
                c("No string referrer");
                j0.b("ERROR: " + e2.getMessage(), new Object[0]);
            }
        }
    }

    private String c() {
        if (!"".equals(this.g)) {
            return this.g;
        }
        try {
            String d2 = d();
            if (!d2.isEmpty() && d2.contains("tappx.com")) {
                this.g = a(d2 + "ts", 2, 6).getString("ts");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!"".equals(this.g)) {
            return this.g;
        }
        j0.f("Using device timestamp!", new Object[0]);
        return "" + (System.currentTimeMillis() / 1000);
    }

    private String d() {
        return f.b("L6AMiu9M3Gzzgb1DcC9zrNWKirwrdRZWS7ho5031f9E0pLEIRwh4cyjVdbI6wKX/");
    }

    private String e() {
        return f.b("wB98799JR2eOU8JQBj+AirJiMR1odQqWWeVt5DvdwLDbO/6GMnE3dISVriMmbsHg");
    }

    private void b(boolean z) {
        x2.a(new a(z));
    }

    public static String b(String str, boolean z) {
        try {
            return Base64.encodeToString(str.getBytes("UTF-8"), 0);
        } catch (UnsupportedEncodingException e2) {
            if (z) {
                e2.printStackTrace();
            }
            return "";
        }
    }

    private void a(String str, String str2) {
        if (this.c == c.NORMAL) {
            w2.b(this.f634a, str, str2);
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (this.c == c.NORMAL && !"1".equals(w2.a(this.f634a, "sp_tappx_referrer_send", "NotFound"))) {
            String a2 = w2.a(this.f634a, "sp_tappx_install_id", "NotFound");
            if ("NotFound".equals(a2) || "".equals(a2)) {
                w2.b(this.f634a, "sp_tappx_referrer_send", "0");
                return;
            }
            if (!z) {
                j0.a("Trying to Track Install", new Object[0]);
            } else {
                j0.a("Re-Trying to Track PENDING Install", new Object[0]);
            }
            String a3 = a(a2 + ":" + (System.currentTimeMillis() / 1000), 2);
            try {
                a3 = URLEncoder.encode(a3, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
            }
            j0.a("ti->snd", new Object[0]);
            Bundle b2 = b(e() + a3);
            if (b2 == null) {
                return;
            }
            if (a(b2.getCharSequence("HTML"), "").trim().equals("1")) {
                w2.b(this.f634a, "sp_tappx_referrer_send", "1");
                j0.a("ti->ok", new Object[0]);
                j0.a("Install Tracked", new Object[0]);
                return;
            }
            j0.a("ti->ko", new Object[0]);
            j0.f("Install NOT Tracked", new Object[0]);
        }
    }

    private Bundle b(String str) {
        String str2;
        String str3;
        String str4 = "";
        try {
            str2 = a(str);
            str3 = str4;
        } catch (Exception e2) {
            str3 = str4 + e2.getMessage() + "\n";
            str2 = str4;
        }
        Bundle bundle = new Bundle();
        if (str2 == null) {
            str2 = str4;
        }
        bundle.putString("HTML", str2);
        if (str3 != null) {
            str4 = str3;
        }
        bundle.putString("ERROR", str4);
        return bundle;
    }

    private String b() {
        String str;
        String str2;
        m1 b2 = this.b.b();
        try {
            str = Settings.Secure.getString(this.f634a.getContentResolver(), "android_id");
        } catch (Exception unused) {
            str = "unspecified_" + a(8);
        }
        try {
            if (b2.d != null) {
                str2 = b2.d;
                return ("2:" + c() + ":" + InternalAvidAdSessionContext.AVID_API_LEVEL + ":" + "Native" + ":" + b2.f516a.trim() + ":" + str.trim() + ":" + this.d.trim() + ":" + this.e.trim() + "::" + this.f.trim() + ":" + str2).trim();
            }
        } catch (Exception unused2) {
        }
        str2 = "";
        return ("2:" + c() + ":" + InternalAvidAdSessionContext.AVID_API_LEVEL + ":" + "Native" + ":" + b2.f516a.trim() + ":" + str.trim() + ":" + this.d.trim() + ":" + this.e.trim() + "::" + this.f.trim() + ":" + str2).trim();
    }

    private String a(CharSequence charSequence, String str) {
        return charSequence != null ? charSequence.toString() : str;
    }

    private String a(String str, int i) {
        String str2 = "";
        if (!str2.equals(str)) {
            String b2 = b(str, this.c != c.NORMAL);
            if (!str2.equals(b2)) {
                str2 = a(3) + b2;
            } else {
                str2 = b2;
            }
        }
        return i > 1 ? a(str2, i - 1) : str2;
    }

    public static String a(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            double random = Math.random();
            double d2 = (double) 62;
            Double.isNaN(d2);
            sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".charAt((int) (random * d2)));
        }
        return sb.toString();
    }

    private String b(String str, int i, int i2) {
        String a2 = a(str);
        for (int i3 = 0; i3 < i; i3++) {
            a2 = a(a2.substring(i2), false);
        }
        return a2;
    }

    public String a(String str) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(false);
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setUseCaches(true);
        httpURLConnection.connect();
        InputStreamReader inputStreamReader = new InputStreamReader((InputStream) httpURLConnection.getContent());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str2 = "";
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                try {
                    break;
                } catch (Exception unused) {
                }
            } else {
                str2 = str2 + readLine;
            }
        }
        bufferedReader.close();
        inputStreamReader.close();
        httpURLConnection.disconnect();
        return str2;
    }

    private JSONObject a(String str, int i, int i2) {
        return new JSONObject(b(str, i, i2));
    }

    private static String a(String str, boolean z) {
        try {
            return new String(Base64.decode(str, 0), "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            if (z) {
                e2.printStackTrace();
            }
            return "";
        }
    }
}
