package com.startapp.android.mediation.admob;

import java.util.Calendar;

public class Utils {
    public static int getDiffInYears(long j, long j2) {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.setTimeInMillis(j);
        instance2.setTimeInMillis(j2);
        return getDiffInYears(instance, instance2);
    }

    public static int getDiffInYears(Calendar calendar, Calendar calendar2) {
        int i = calendar.get(1) - calendar2.get(1);
        int i2 = calendar.get(2) - calendar2.get(2);
        return (i2 < 0 || (i2 == 0 && calendar.get(5) - calendar2.get(5) < 0)) ? i - 1 : i;
    }
}
