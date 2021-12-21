package com.tappx.a;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public abstract class n3 {

    /* renamed from: a  reason: collision with root package name */
    private final Context f533a;
    private final RelativeLayout b = new RelativeLayout(this.f533a);
    private final a c;
    private final n2 d;

    public interface a {
        void onFinish();

        void onSetContentView(View view);
    }

    protected n3(Context context, a aVar) {
        this.f533a = context;
        this.d = o2.a(context).c();
        this.c = aVar;
    }

    private void k() {
        if (this.d.j()) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 83);
            this.b.addView(l(), layoutParams);
        }
    }

    private View l() {
        return i3.c(this.f533a);
    }

    public void a(int i, int i2, Intent intent) {
    }

    public abstract void a(Configuration configuration);

    public abstract void a(Bundle bundle);

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        if (z) {
            this.c.onFinish();
        }
    }

    public boolean a() {
        return true;
    }

    /* access modifiers changed from: protected */
    public a b() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public Context c() {
        return this.f533a;
    }

    public ViewGroup d() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public abstract VideoView e();

    public abstract void f();

    public void g() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13);
        this.b.addView(e(), 0, layoutParams);
        k();
        this.c.onSetContentView(this.b);
    }

    public abstract void h();

    public abstract void i();

    public abstract void j();

    /* access modifiers changed from: protected */
    public void b(boolean z) {
        j4.b("Video cannot be played.");
        if (z) {
            this.c.onFinish();
        }
    }
}
