package com.yandex.metrica.impl.ob;

import android.telephony.SubscriptionInfo;

public final class ee {

    /* renamed from: a  reason: collision with root package name */
    private final Integer f876a;
    private final Integer b;
    private final boolean c;
    private final String d;
    private final String e;

    public ee(Integer num, Integer num2, boolean z, String str, String str2) {
        this.f876a = num;
        this.b = num2;
        this.c = z;
        this.d = str;
        this.e = str2;
    }

    public ee(SubscriptionInfo subscriptionInfo) {
        this.f876a = Integer.valueOf(subscriptionInfo.getMcc());
        this.b = Integer.valueOf(subscriptionInfo.getMnc());
        this.c = subscriptionInfo.getDataRoaming() != 1 ? false : true;
        this.d = subscriptionInfo.getCarrierName().toString();
        this.e = subscriptionInfo.getIccId();
    }

    public Integer a() {
        return this.f876a;
    }

    public Integer b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }
}
