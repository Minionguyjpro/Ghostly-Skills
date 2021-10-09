package com.appnext.base.b;

import java.util.Calendar;
import java.util.Date;

public final class l {
    public static Date a(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.getTime();
    }
}
