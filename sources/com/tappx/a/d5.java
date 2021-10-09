package com.tappx.a;

import android.content.Context;
import com.tappx.a.c5;

public class d5 implements c5 {

    /* renamed from: a  reason: collision with root package name */
    private final b5 f421a;
    private c5.a b;
    private Context c;
    private String d;
    private f5 e;
    private int f;

    public d5() {
        this(new b5());
    }

    public void a(c5.a aVar) {
        this.b = aVar;
    }

    public void b() {
        z4.a(this.c, this.d, this.e, this.f);
    }

    d5(b5 b5Var) {
        this.f = -1;
        this.f421a = b5Var;
    }

    public void a(Context context, String str, f5 f5Var) {
        this.c = context;
        this.d = str;
        this.e = f5Var;
        int a2 = a5.a(this.b);
        this.f = a2;
        this.f421a.a(a2, this, context, str, this.b, f5Var);
    }

    public void a() {
        a5.b(this.f);
        this.f421a.a();
    }
}
