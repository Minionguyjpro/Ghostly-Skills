package com.startapp.android.publish.inappbrowser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.common.a.g;
import java.util.HashMap;
import java.util.Map;

/* compiled from: StartAppSDK */
public class b extends RelativeLayout {
    private static final int p = Color.rgb(78, 86, 101);
    private static final int q = Color.rgb(148, 155, 166);

    /* renamed from: a  reason: collision with root package name */
    private RelativeLayout f323a;
    private ImageView b;
    private ImageView c;
    private ImageView d;
    private ImageView e;
    private Bitmap f;
    private Bitmap g;
    private Bitmap h;
    private Bitmap i;
    private Bitmap j;
    private Bitmap k;
    private TextView l;
    private TextView m;
    private Boolean n = false;
    private Map<String, c> o;

    public b(Context context) {
        super(context);
    }

    public void a() {
        setDescendantFocusability(262144);
        setBackgroundColor(Color.parseColor("#e9e9e9"));
        setLayoutParams(new RelativeLayout.LayoutParams(-1, h.a(getContext(), 60)));
        setId(2101);
        this.o = d();
    }

    private Map<String, c> d() {
        HashMap hashMap = new HashMap();
        hashMap.put("BACK", new c(this.h, 14, 22, "back_.png"));
        hashMap.put("BACK_DARK", new c(this.j, 14, 22, "back_dark.png"));
        hashMap.put("FORWARD", new c(this.i, 14, 22, "forward_.png"));
        hashMap.put("FORWARD_DARK", new c(this.k, 14, 22, "forward_dark.png"));
        hashMap.put("X", new c(this.g, 23, 23, "x_dark.png"));
        hashMap.put("BROWSER", new c(this.f, 28, 28, "browser_icon_dark.png"));
        return hashMap;
    }

    public void b() {
        Typeface typeface = Typeface.DEFAULT;
        this.l = h.a(getContext(), this.l, typeface, 1, 16.46f, p, 2102);
        this.m = h.a(getContext(), this.l, typeface, 1, 12.12f, q, 2107);
        this.l.setText("Loading...");
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        this.f323a = relativeLayout;
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        this.f323a.addView(this.l, h.a(getContext(), new int[]{0, 0, 0, 0}, new int[0]));
        this.f323a.addView(this.m, h.a(getContext(), new int[]{0, 0, 0, 0}, new int[0], 3, 2102));
        e();
        this.b = h.a(getContext(), this.b, this.o.get("X").d(), 2103);
        this.d = h.a(getContext(), this.d, this.o.get("BROWSER").d(), 2104);
        this.e = h.a(getContext(), this.e, this.o.get("BACK").d(), 2105);
        this.c = h.a(getContext(), this.c, this.o.get("FORWARD").d(), 2106);
        int a2 = h.a(getContext(), 10);
        this.c.setPadding(a2, a2, a2, a2);
        this.e.setPadding(a2, a2, a2, a2);
        addView(this.b, h.a(getContext(), new int[]{0, 0, 16, 0}, new int[]{15, 11}));
        addView(this.d, h.a(getContext(), new int[]{0, 0, 17, 0}, new int[]{15}, 0, 2103));
        addView(this.f323a, h.a(getContext(), new int[]{16, 6, 16, 0}, new int[]{9}, 0, 2104));
    }

    private void e() {
        for (c next : this.o.values()) {
            Bitmap a2 = h.a(getContext(), next.c());
            if (a2 == null) {
                g.a("NavigationBarLayout", 6, "Error getting navigation bar bitmap - " + next.c() + " from resources ");
            } else {
                next.a(Bitmap.createScaledBitmap(a2, h.a(getContext(), next.a()), h.a(getContext(), next.b()), true));
            }
        }
    }

    public void a(WebView webView) {
        if (this.n.booleanValue()) {
            b(webView);
        } else if (webView.canGoBack()) {
            f();
            this.n = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void b(WebView webView) {
        if (webView.canGoBack()) {
            this.e.setImageBitmap(this.o.get("BACK_DARK").d());
        } else {
            this.e.setImageBitmap(this.o.get("BACK").d());
        }
        if (webView.canGoForward()) {
            this.c.setImageBitmap(this.o.get("FORWARD_DARK").d());
        } else {
            this.c.setImageBitmap(this.o.get("FORWARD").d());
        }
        if (webView.getTitle() != null) {
            this.l.setText(webView.getTitle());
        }
    }

    public TextView getUrlTxt() {
        return this.m;
    }

    public TextView getTitleTxt() {
        return this.l;
    }

    public void setButtonsListener(View.OnClickListener onClickListener) {
        this.b.setOnClickListener(onClickListener);
        this.e.setOnClickListener(onClickListener);
        this.c.setOnClickListener(onClickListener);
        this.d.setOnClickListener(onClickListener);
    }

    private void f() {
        this.e.setImageBitmap(this.o.get("BACK_DARK").d());
        addView(this.e, h.a(getContext(), new int[]{6, 0, 0, 0}, new int[]{15, 9}));
        addView(this.c, h.a(getContext(), new int[]{9, 0, 0, 0}, new int[]{15}, 1, 2105));
        removeView(this.f323a);
        this.f323a.removeView(this.m);
        this.f323a.removeView(this.l);
        this.f323a.addView(this.l, h.a(getContext(), new int[]{0, 0, 0, 0}, new int[]{14}));
        this.f323a.addView(this.m, h.a(getContext(), new int[]{0, 0, 0, 0}, new int[]{14}, 3, 2102));
        RelativeLayout.LayoutParams a2 = h.a(getContext(), new int[]{16, 0, 16, 0}, new int[]{15}, 1, 2106);
        a2.addRule(0, 2104);
        addView(this.f323a, a2);
    }

    public void c() {
        if (Build.VERSION.SDK_INT < 11) {
            ((BitmapDrawable) this.b.getDrawable()).getBitmap().recycle();
            ((BitmapDrawable) this.d.getDrawable()).getBitmap().recycle();
            ((BitmapDrawable) this.e.getDrawable()).getBitmap().recycle();
            ((BitmapDrawable) this.c.getDrawable()).getBitmap().recycle();
        }
        this.o = null;
        this.h = null;
        this.j = null;
        this.i = null;
        this.k = null;
    }
}
