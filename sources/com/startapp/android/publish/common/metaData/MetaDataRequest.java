package com.startapp.android.publish.common.metaData;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Pair;
import com.startapp.android.publish.adsCommon.BaseRequest;
import com.startapp.android.publish.adsCommon.Utils.d;
import com.startapp.android.publish.adsCommon.Utils.e;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.k;
import com.startapp.android.publish.adsCommon.l;
import com.startapp.android.publish.adsCommon.m;
import com.startapp.common.a.c;

/* compiled from: StartAppSDK */
public class MetaDataRequest extends BaseRequest {
    private String apkHash;
    private int daysSinceFirstSession;
    private long firstInstalledAppTS;
    private Integer ian;
    private float paidAmount;
    private boolean payingUser;
    private String profileId = MetaData.getInstance().getProfileId();
    private a reason;
    private Pair<String, String> simpleToken;
    private int totalSessions;

    /* compiled from: StartAppSDK */
    public enum a {
        LAUNCH(1),
        APP_IDLE(2),
        IN_APP_PURCHASE(3),
        CUSTOM(4),
        PERIODIC(5),
        PAS(6);
        
        private int index;

        private a(int i) {
            this.index = i;
        }
    }

    public MetaDataRequest(Context context, a aVar) {
        this.totalSessions = k.a(context, "totalSessions", (Integer) 0).intValue();
        this.daysSinceFirstSession = calcDaysSinceFirstSession(context);
        this.paidAmount = k.a(context, "inAppPurchaseAmount", Float.valueOf(0.0f)).floatValue();
        this.payingUser = k.a(context, "payingUser", (Boolean) false).booleanValue();
        this.reason = aVar;
        this.apkHash = calcApkHash(context, k.a(context), m.a().e(), new i());
        setIan(context);
        this.simpleToken = l.c();
        this.firstInstalledAppTS = l.a();
    }

    private int calcDaysSinceFirstSession(Context context) {
        return millisToDays(System.currentTimeMillis() - k.a(context, "firstSessionTime", Long.valueOf(System.currentTimeMillis())).longValue());
    }

    private int millisToDays(long j) {
        return (int) (j / 86400000);
    }

    public int getTotalSessions() {
        return this.totalSessions;
    }

    public void setTotalSessions(int i) {
        this.totalSessions = i;
    }

    public String getApkHash() {
        return this.apkHash;
    }

    public void setApkHash(String str) {
        this.apkHash = str;
    }

    public int getDaysSinceFirstSession() {
        return this.daysSinceFirstSession;
    }

    public void setDaysSinceFirstSession(int i) {
        this.daysSinceFirstSession = i;
    }

    public boolean isPayingUser() {
        return this.payingUser;
    }

    public void setPayingUser(boolean z) {
        this.payingUser = z;
    }

    public float getPaidAmount() {
        return this.paidAmount;
    }

    public void setPaidAmount(float f) {
        this.paidAmount = f;
    }

    public a getReason() {
        return this.reason;
    }

    public void setReason(a aVar) {
        this.reason = aVar;
    }

    public int getIan() {
        return this.ian.intValue();
    }

    public void setIan(Context context) {
        int f = c.f(context);
        if (f > 0) {
            this.ian = Integer.valueOf(f);
        }
    }

    public String getProfileId() {
        return this.profileId;
    }

    public void setProfileId(String str) {
        this.profileId = str;
    }

    public String toString() {
        return "MetaDataRequest [totalSessions=" + this.totalSessions + ", daysSinceFirstSession=" + this.daysSinceFirstSession + ", payingUser=" + this.payingUser + ", paidAmount=" + this.paidAmount + ", reason=" + this.reason + ", profileId=" + this.profileId + "]";
    }

    public e getNameValueMap() {
        e nameValueMap = super.getNameValueMap();
        if (nameValueMap == null) {
            nameValueMap = new d();
        }
        addParams(nameValueMap);
        return nameValueMap;
    }

    private void addParams(e eVar) {
        eVar.a(com.startapp.common.a.a.a(), (Object) com.startapp.common.a.a.d(), true);
        eVar.a("totalSessions", (Object) Integer.valueOf(this.totalSessions), true);
        eVar.a("daysSinceFirstSession", (Object) Integer.valueOf(this.daysSinceFirstSession), true);
        eVar.a("payingUser", (Object) Boolean.valueOf(this.payingUser), true);
        eVar.a("profileId", (Object) this.profileId, false);
        eVar.a("paidAmount", (Object) Float.valueOf(this.paidAmount), true);
        eVar.a("reason", (Object) this.reason, true);
        String str = this.apkHash;
        if (str != null) {
            eVar.a("apkHash", (Object) str, false);
        }
        eVar.a("ian", (Object) this.ian, false);
        eVar.a((String) this.simpleToken.first, this.simpleToken.second, false);
        long j = this.firstInstalledAppTS;
        if (j > 0 && j < Long.MAX_VALUE) {
            eVar.a("firstInstalledAppTS", (Object) Long.valueOf(j), false);
        }
    }

    public static String calcApkHash(Context context, SharedPreferences sharedPreferences, boolean z, i iVar) {
        String string = sharedPreferences.getString("shared_prefs_app_apk_hash", (String) null);
        if (!TextUtils.isEmpty(string) && !z) {
            return string;
        }
        String a2 = iVar.a("SHA-256", context);
        sharedPreferences.edit().putString("shared_prefs_app_apk_hash", a2).commit();
        return a2;
    }
}
