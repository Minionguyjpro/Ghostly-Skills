package com.yandex.metrica.impl.ob;

import android.os.Bundle;

public enum du {
    UNKNOWN(0),
    NETWORK(1),
    PARSE(2);
    
    private int d;

    private du(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }

    public Bundle a(Bundle bundle) {
        bundle.putInt("startup_error_key_code", a());
        return bundle;
    }

    public static du b(Bundle bundle) {
        int i = bundle.getInt("startup_error_key_code");
        du duVar = UNKNOWN;
        if (i == 1) {
            return NETWORK;
        }
        if (i != 2) {
            return duVar;
        }
        return PARSE;
    }
}
