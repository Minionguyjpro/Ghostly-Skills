package com.startapp.a.a.d;

import java.util.regex.Pattern;

/* compiled from: StartAppSDK */
public class d implements c {

    /* renamed from: a  reason: collision with root package name */
    private final Pattern f12a = Pattern.compile("\\+");
    private final Pattern b = Pattern.compile("/");
    private final Pattern c = Pattern.compile("=");
    private final Pattern d = Pattern.compile("_");
    private final Pattern e = Pattern.compile("\\*");
    private final Pattern f = Pattern.compile("#");

    public String a(String str) {
        return this.c.matcher(this.b.matcher(this.f12a.matcher(str).replaceAll("_")).replaceAll("*")).replaceAll("#");
    }
}
