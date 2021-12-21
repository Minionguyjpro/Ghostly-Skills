package com.yandex.metrica.impl.ob;

public enum bl {
    FOREGROUND(0),
    BACKGROUND(1);
    
    private final int c;

    private bl(int i) {
        this.c = i;
    }

    public int a() {
        return this.c;
    }

    public static bl a(Integer num) {
        bl blVar = FOREGROUND;
        if (num == null) {
            return blVar;
        }
        int intValue = num.intValue();
        if (intValue == 0) {
            return FOREGROUND;
        }
        if (intValue != 1) {
            return blVar;
        }
        return BACKGROUND;
    }
}
