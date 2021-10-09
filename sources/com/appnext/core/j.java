package com.appnext.core;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;

public final class j {
    private static j hp;
    private int be = 24;
    /* access modifiers changed from: private */
    public HashMap<String, SharedPreferences> hq = new HashMap<>();

    private j() {
    }

    public final void d(final Context context, final String str) {
        if (!this.hq.containsKey(str.replace("/", ""))) {
            new Thread(new Runnable() {
                public final void run() {
                    HashMap a2 = j.this.hq;
                    String str = str;
                    Context context = context;
                    a2.put(str, context.getSharedPreferences("apnxt_cap" + str.replace("/", ""), 0));
                }
            }).start();
        }
    }

    public static synchronized j bj() {
        j jVar;
        synchronized (j.class) {
            if (hp == null) {
                hp = new j();
            }
            jVar = hp;
        }
        return jVar;
    }

    public final void n(String str, String str2) {
        this.hq.get(str2).edit().putLong(str, System.currentTimeMillis()).apply();
    }

    public final boolean o(String str, String str2) {
        long j = this.hq.get(str2).getLong(str, -1);
        return j != -1 && System.currentTimeMillis() - ((long) (this.be * 3600000)) <= j;
    }

    public final boolean p(String str, String str2) {
        long j = this.hq.get(str2).getLong(str, -1);
        return j != -1 && System.currentTimeMillis() - 120000 <= j;
    }

    public final void ab(String str) {
        this.hq.get(str).edit().clear().apply();
    }

    public final void b(int i) {
        this.be = i;
    }
}
