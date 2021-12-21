package com.yandex.metrica.impl;

import android.os.Bundle;
import com.yandex.metrica.CounterConfiguration;

public class bf extends aw {
    private final String e;

    public bf(String str) {
        this.e = str;
    }

    /* access modifiers changed from: package-private */
    public Bundle c() {
        Bundle c = super.c();
        CounterConfiguration counterConfiguration = new CounterConfiguration(b());
        counterConfiguration.b(this.e);
        c.putParcelable("COUNTER_MIGRATION_CFG_OBJ", counterConfiguration);
        return c;
    }
}
