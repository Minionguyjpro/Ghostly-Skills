package com.startapp.android.publish.ads.list3d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.startapp.android.publish.a.b;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.android.publish.common.metaData.MetaDataStyle;
import com.startapp.common.a.c;

/* compiled from: StartAppSDK */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private RelativeLayout f74a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private b f;
    private MetaDataStyle g = null;

    public d(Context context) {
        Context context2 = context;
        context2.setTheme(16973829);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
        this.f74a = new RelativeLayout(context2);
        this.f74a.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{com.startapp.android.publish.adsCommon.b.a().n(), com.startapp.android.publish.adsCommon.b.a().o()}));
        this.f74a.setLayoutParams(layoutParams);
        int a2 = h.a(context2, 3);
        int a3 = h.a(context2, 4);
        int a4 = h.a(context2, 5);
        int a5 = h.a(context2, 6);
        int a6 = h.a(context2, 10);
        int a7 = h.a(context2, 84);
        this.f74a.setPadding(a6, a2, a6, a2);
        this.f74a.setTag(this);
        ImageView imageView = new ImageView(context2);
        this.b = imageView;
        imageView.setId(1);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a7, a7);
        layoutParams2.addRule(15);
        this.b.setLayoutParams(layoutParams2);
        this.b.setPadding(0, 0, a5, 0);
        TextView textView = new TextView(context2);
        this.c = textView;
        textView.setId(2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(c.a(17), 1);
        layoutParams3.addRule(6, 1);
        this.c.setLayoutParams(layoutParams3);
        this.c.setPadding(0, 0, 0, a4);
        this.c.setTextColor(com.startapp.android.publish.adsCommon.b.a().q().intValue());
        this.c.setTextSize((float) com.startapp.android.publish.adsCommon.b.a().p().intValue());
        this.c.setSingleLine(true);
        this.c.setEllipsize(TextUtils.TruncateAt.END);
        h.a(this.c, com.startapp.android.publish.adsCommon.b.a().r());
        TextView textView2 = new TextView(context2);
        this.d = textView2;
        textView2.setId(3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(c.a(17), 1);
        layoutParams4.addRule(3, 2);
        layoutParams4.setMargins(0, 0, 0, a4);
        this.d.setLayoutParams(layoutParams4);
        this.d.setTextColor(com.startapp.android.publish.adsCommon.b.a().t().intValue());
        this.d.setTextSize((float) com.startapp.android.publish.adsCommon.b.a().s().intValue());
        this.d.setSingleLine(true);
        this.d.setEllipsize(TextUtils.TruncateAt.END);
        h.a(this.d, com.startapp.android.publish.adsCommon.b.a().u());
        this.f = new b(context2);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(c.a(17), 1);
        layoutParams5.addRule(8, 1);
        layoutParams5.setMargins(0, 0, 0, -a4);
        this.f.setLayoutParams(layoutParams5);
        this.f.setPadding(0, 0, 0, a3);
        this.f.setId(5);
        this.e = new TextView(context2);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams6.addRule(c.a(21));
        layoutParams6.addRule(8, 1);
        this.e.setLayoutParams(layoutParams6);
        a(false);
        this.e.setTextColor(-1);
        this.e.setTextSize(12.0f);
        this.e.setTypeface((Typeface) null, 1);
        this.e.setPadding(a6, a5, a6, a5);
        this.e.setId(4);
        this.e.setShadowLayer(2.5f, -3.0f, 3.0f, -9013642);
        this.e.setBackgroundDrawable(new ShapeDrawable(new RoundRectShape(new float[]{10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f}, (RectF) null, (float[]) null)) {
            /* access modifiers changed from: protected */
            public void onDraw(Shape shape, Canvas canvas, Paint paint) {
                paint.setColor(-11363070);
                paint.setMaskFilter(new EmbossMaskFilter(new float[]{1.0f, 1.0f, 1.0f}, 0.4f, 5.0f, 3.0f));
                super.onDraw(shape, canvas, paint);
            }
        });
        this.f74a.addView(this.b);
        this.f74a.addView(this.c);
        this.f74a.addView(this.d);
        this.f74a.addView(this.f);
        this.f74a.addView(this.e);
    }

    public RelativeLayout a() {
        return this.f74a;
    }

    public ImageView b() {
        return this.b;
    }

    public TextView c() {
        return this.c;
    }

    public TextView d() {
        return this.d;
    }

    public b e() {
        return this.f;
    }

    public void a(boolean z) {
        if (z) {
            this.e.setText("Open");
        } else {
            this.e.setText("Download");
        }
    }

    public void a(MetaDataStyle metaDataStyle) {
        if (this.g != metaDataStyle) {
            this.g = metaDataStyle;
            this.f74a.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{metaDataStyle.getItemGradientTop().intValue(), metaDataStyle.getItemGradientBottom().intValue()}));
            this.c.setTextSize((float) metaDataStyle.getItemTitleTextSize().intValue());
            this.c.setTextColor(metaDataStyle.getItemTitleTextColor().intValue());
            h.a(this.c, metaDataStyle.getItemTitleTextDecoration());
            this.d.setTextSize((float) metaDataStyle.getItemDescriptionTextSize().intValue());
            this.d.setTextColor(metaDataStyle.getItemDescriptionTextColor().intValue());
            h.a(this.d, metaDataStyle.getItemDescriptionTextDecoration());
        }
    }
}
