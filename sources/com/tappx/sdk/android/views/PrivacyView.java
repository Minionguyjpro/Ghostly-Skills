package com.tappx.sdk.android.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tappx.a.h3;
import com.tappx.a.n2;
import com.tappx.a.o2;

public class PrivacyView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private View f657a;
    private View b;
    private TextView c;
    /* access modifiers changed from: private */
    public boolean d;
    /* access modifiers changed from: private */
    public n2 e;
    private TextView f;
    private TextView g;

    public PrivacyView(Context context) {
        super(context);
        d();
    }

    private void c() {
        removeAllViews();
        Context context = getContext();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setGravity(83);
        linearLayout.setOrientation(0);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.width = -2;
        layoutParams.height = -2;
        linearLayout.setLayoutParams(layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setBackgroundColor(Color.parseColor("#EEe7e7e7"));
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(17);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(a(0), a(50));
        layoutParams2.weight = 1.0f;
        linearLayout2.setLayoutParams(layoutParams2);
        this.f657a = linearLayout2;
        TextView textView = new TextView(context);
        this.c = textView;
        textView.setGravity(17);
        this.c.setText("Do you want to change your ad experience?.");
        this.c.setTextColor(Color.parseColor("#000000"));
        this.c.setTextSize(14.0f);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(a(285), -2);
        layoutParams3.gravity = 17;
        layoutParams3.leftMargin = a(5);
        layoutParams3.rightMargin = a(5);
        this.c.setLayoutParams(layoutParams3);
        linearLayout2.addView(this.c);
        LinearLayout linearLayout3 = new LinearLayout(context);
        linearLayout3.setOrientation(0);
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams4.gravity = 17;
        layoutParams4.bottomMargin = a(3);
        layoutParams4.topMargin = a(3);
        linearLayout3.setLayoutParams(layoutParams4);
        int a2 = a(3);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        float f2 = (float) a2;
        gradientDrawable.setCornerRadii(new float[]{f2, f2, f2, f2, f2, f2, f2, f2});
        gradientDrawable.setColor(Color.parseColor("#116073"));
        TextView textView2 = new TextView(context);
        this.f = textView2;
        textView2.setBackgroundDrawable(gradientDrawable);
        int a3 = a(20);
        int a4 = a(2);
        this.f.setPadding(a3, a4, a3, a4);
        this.f.setText("Yes");
        this.f.setTextColor(Color.parseColor("#ffffff"));
        this.f.setTextSize(14.0f);
        this.f.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        linearLayout3.addView(this.f);
        View view = new View(context);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(a(1), -1);
        layoutParams5.leftMargin = a3;
        layoutParams5.rightMargin = a3;
        view.setLayoutParams(layoutParams5);
        view.setBackgroundColor(Color.parseColor("#cacaca"));
        linearLayout3.addView(view);
        TextView textView3 = new TextView(context);
        this.g = textView3;
        textView3.setBackgroundDrawable(gradientDrawable);
        this.g.setPadding(a3, a4, a3, a4);
        this.g.setText("No");
        this.g.setTextColor(Color.parseColor("#ffffff"));
        this.g.setTextSize(14.0f);
        FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams6.rightMargin = a(20);
        this.g.setLayoutParams(layoutParams6);
        linearLayout3.addView(this.g);
        linearLayout2.addView(linearLayout3);
        linearLayout.addView(linearLayout2);
        LinearLayout linearLayout4 = new LinearLayout(context);
        linearLayout4.setOrientation(1);
        FrameLayout.LayoutParams layoutParams7 = new FrameLayout.LayoutParams(-2, -1);
        layoutParams7.gravity = 83;
        linearLayout4.setLayoutParams(layoutParams7);
        linearLayout.addView(linearLayout4);
        this.b = linearLayout4;
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(h3.a(context));
        linearLayout4.addView(imageView, new LinearLayout.LayoutParams(-2, -2));
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setBackgroundColor(-1);
        linearLayout4.addView(frameLayout, new LinearLayout.LayoutParams(-1, -1));
        addView(linearLayout);
    }

    private void d() {
        b();
        this.e = o2.a(getContext()).c();
        a();
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PrivacyView.this.setCollapsed(true);
            }
        });
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PrivacyView.this.e.a(view.getContext());
                PrivacyView.this.setCollapsed(true);
            }
        });
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PrivacyView privacyView = PrivacyView.this;
                privacyView.setCollapsed(!privacyView.d);
            }
        });
        setCollapsed(true);
    }

    /* access modifiers changed from: private */
    public void setCollapsed(boolean z) {
        this.d = z;
        this.f657a.setVisibility(z ? 8 : 0);
    }

    private void b() {
        try {
            throw new RuntimeException("");
        } catch (Throwable unused) {
            c();
        }
    }

    public PrivacyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
    }

    private int a(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }

    private void a() {
        String b2 = this.e.b();
        if (b2 != null) {
            this.c.setText(b2);
        }
    }

    public PrivacyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d();
    }
}
