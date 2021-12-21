package com.startapp.android.publish.ads.list3d;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appnext.base.b.d;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.a;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.adinformation.b;
import com.startapp.android.publish.adsCommon.c;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.adsCommon.m;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.a.g;
import java.util.List;

/* compiled from: StartAppSDK */
public class List3DActivity extends Activity implements g {

    /* renamed from: a  reason: collision with root package name */
    String f58a;
    String b;
    List<ListItem> c;
    private c d;
    private ProgressDialog e = null;
    private WebView f = null;
    private int g;
    private b h;
    private Long i;
    private Long j;
    private String k;
    private long l = 0;
    private long m = 0;
    private BroadcastReceiver n = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            List3DActivity.this.finish();
        }
    };

    public void onCreate(Bundle bundle) {
        String str;
        View view;
        Bundle bundle2 = bundle;
        try {
            overridePendingTransition(0, 0);
            super.onCreate(bundle);
            if (getIntent().getBooleanExtra("fullscreen", false)) {
                requestWindowFeature(1);
                getWindow().setFlags(d.fb, d.fb);
            }
            if (bundle2 == null) {
                com.startapp.common.b.a((Context) this).a(new Intent("com.startapp.android.ShowDisplayBroadcastListener"));
                this.i = (Long) getIntent().getSerializableExtra("lastLoadTime");
                this.j = (Long) getIntent().getSerializableExtra("adCacheTtl");
            } else {
                if (bundle2.containsKey("lastLoadTime")) {
                    this.i = (Long) bundle2.getSerializable("lastLoadTime");
                }
                if (bundle2.containsKey("adCacheTtl")) {
                    this.j = (Long) bundle2.getSerializable("adCacheTtl");
                }
            }
            this.k = getIntent().getStringExtra("position");
            this.f58a = getIntent().getStringExtra("listModelUuid");
            com.startapp.common.b.a((Context) this).a(this.n, new IntentFilter("com.startapp.android.CloseAdActivity"));
            this.g = getResources().getConfiguration().orientation;
            i.a((Activity) this, true);
            boolean booleanExtra = getIntent().getBooleanExtra("overlay", false);
            requestWindowFeature(1);
            this.b = getIntent().getStringExtra("adTag");
            int e2 = com.startapp.android.publish.adsCommon.b.a().e();
            int f2 = com.startapp.android.publish.adsCommon.b.a().f();
            this.d = new c(this, (AttributeSet) null, this.b, this.f58a);
            this.d.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{e2, f2}));
            List<ListItem> e3 = f.a().a(this.f58a).e();
            this.c = e3;
            if (e3 == null) {
                finish();
                return;
            }
            if (booleanExtra) {
                com.startapp.common.b.a((Context) this).a(this.d.p, new IntentFilter("com.startapp.android.Activity3DGetValues"));
                str = "";
            } else {
                this.d.a();
                this.d.setHint(true);
                this.d.setFade(true);
                str = "back";
            }
            b bVar = new b(this, this.c, str, this.b, this.f58a);
            f.a().a(this.f58a).a(this, !booleanExtra);
            this.d.setAdapter(bVar);
            this.d.setDynamics(new SimpleDynamics(0.9f, 0.6f));
            this.d.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    int i2 = i;
                    String b = List3DActivity.this.c.get(i2).b();
                    String e = List3DActivity.this.c.get(i2).e();
                    String f = List3DActivity.this.c.get(i2).f();
                    boolean l = List3DActivity.this.c.get(i2).l();
                    boolean m = List3DActivity.this.c.get(i2).m();
                    String p = List3DActivity.this.c.get(i2).p();
                    String o = List3DActivity.this.c.get(i2).o();
                    Boolean s = List3DActivity.this.c.get(i2).s();
                    f.a().a(List3DActivity.this.f58a).a(List3DActivity.this.c.get(i2).c());
                    if (p != null && !TextUtils.isEmpty(p)) {
                        a(p, o, b, e);
                    } else if (!TextUtils.isEmpty(b)) {
                        boolean a2 = c.a(List3DActivity.this.getApplicationContext(), AdPreferences.Placement.INAPP_OFFER_WALL);
                        if (!l || a2) {
                            List3DActivity list3DActivity = List3DActivity.this;
                            c.a(list3DActivity, b, e, list3DActivity.a(), m && !a2, false);
                            List3DActivity.this.finish();
                            return;
                        }
                        List3DActivity list3DActivity2 = List3DActivity.this;
                        c.a(list3DActivity2, b, e, f, list3DActivity2.a(), com.startapp.android.publish.adsCommon.b.a().A(), com.startapp.android.publish.adsCommon.b.a().B(), m, s, false, new Runnable() {
                            public void run() {
                                List3DActivity.this.finish();
                            }
                        });
                    }
                }

                private void a(String str, String str2, String str3, String str4) {
                    List3DActivity list3DActivity = List3DActivity.this;
                    c.a(str, str2, str3, (Context) list3DActivity, new com.startapp.android.publish.adsCommon.d.b(list3DActivity.b));
                    List3DActivity.this.finish();
                }
            });
            RelativeLayout relativeLayout = new RelativeLayout(this);
            relativeLayout.setContentDescription("StartApp Ad");
            relativeLayout.setId(AdsConstants.STARTAPP_AD_MAIN_LAYOUT_ID);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(1);
            relativeLayout.addView(linearLayout, layoutParams2);
            RelativeLayout relativeLayout2 = new RelativeLayout(this);
            relativeLayout2.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
            relativeLayout2.setBackgroundColor(com.startapp.android.publish.adsCommon.b.a().h().intValue());
            linearLayout.addView(relativeLayout2);
            TextView textView = new TextView(this);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams3.addRule(13);
            textView.setLayoutParams(layoutParams3);
            textView.setPadding(0, h.a((Context) this, 2), 0, h.a((Context) this, 5));
            textView.setTextColor(com.startapp.android.publish.adsCommon.b.a().k().intValue());
            textView.setTextSize((float) com.startapp.android.publish.adsCommon.b.a().j().intValue());
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setText(com.startapp.android.publish.adsCommon.b.a().i());
            textView.setShadowLayer(2.5f, -2.0f, 2.0f, -11513776);
            h.a(textView, com.startapp.android.publish.adsCommon.b.a().l());
            relativeLayout2.addView(textView);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams4.addRule(11);
            layoutParams4.addRule(15);
            Bitmap a2 = a.a(this, "close_button.png");
            if (a2 != null) {
                view = new ImageButton(this, (AttributeSet) null, 16973839);
                ((ImageButton) view).setImageBitmap(Bitmap.createScaledBitmap(a2, h.a((Context) this, 36), h.a((Context) this, 36), true));
            } else {
                view = new TextView(this);
                ((TextView) view).setText("   x   ");
                ((TextView) view).setTextSize(20.0f);
            }
            view.setLayoutParams(layoutParams4);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    List3DActivity list3DActivity = List3DActivity.this;
                    c.b((Context) list3DActivity, list3DActivity.b(), List3DActivity.this.a());
                    List3DActivity.this.finish();
                }
            });
            view.setContentDescription(AvidJSONUtil.KEY_X);
            view.setId(AdsConstants.LIST_3D_CLOSE_BUTTON_ID);
            relativeLayout2.addView(view);
            View view2 = new View(this);
            view2.setLayoutParams(new LinearLayout.LayoutParams(-1, h.a((Context) this, 2)));
            view2.setBackgroundColor(com.startapp.android.publish.adsCommon.b.a().m().intValue());
            linearLayout.addView(view2);
            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, 0);
            layoutParams5.weight = 1.0f;
            this.d.setLayoutParams(layoutParams5);
            linearLayout.addView(this.d);
            LinearLayout linearLayout2 = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams6.gravity = 80;
            linearLayout2.setLayoutParams(layoutParams6);
            linearLayout2.setBackgroundColor(com.startapp.android.publish.adsCommon.b.a().v().intValue());
            linearLayout2.setGravity(17);
            linearLayout.addView(linearLayout2);
            TextView textView2 = new TextView(this);
            textView2.setTextColor(com.startapp.android.publish.adsCommon.b.a().w().intValue());
            textView2.setPadding(0, h.a((Context) this, 2), 0, h.a((Context) this, 3));
            textView2.setText("Powered By ");
            textView2.setTextSize(16.0f);
            linearLayout2.addView(textView2);
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(Bitmap.createScaledBitmap(a.a(this, "logo.png"), h.a((Context) this, 56), h.a((Context) this, 12), true));
            linearLayout2.addView(imageView);
            b bVar2 = new b(this, b.C0003b.LARGE, AdPreferences.Placement.INAPP_OFFER_WALL, (com.startapp.android.publish.adsCommon.adinformation.c) getIntent().getSerializableExtra("adInfoOverride"));
            this.h = bVar2;
            bVar2.a(relativeLayout);
            setContentView(relativeLayout, layoutParams);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    List3DActivity.this.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
                }
            }, 500);
        } catch (Throwable th) {
            g.a("List3DActivity", 6, "List3DActivity.onCreate", th);
            f.a(this, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "List3DActivity.onCreate", th.getMessage(), "");
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public com.startapp.android.publish.adsCommon.d.b a() {
        long currentTimeMillis = System.currentTimeMillis();
        this.l = currentTimeMillis;
        double d2 = (double) (currentTimeMillis - this.m);
        Double.isNaN(d2);
        return new com.startapp.android.publish.adsCommon.d.a(String.valueOf(d2 / 1000.0d), this.b);
    }

    /* access modifiers changed from: protected */
    public String b() {
        List<ListItem> list = this.c;
        if (list == null || list.isEmpty() || this.c.get(0).d() == null) {
            return "";
        }
        return this.c.get(0).d();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (d()) {
            g.a("List3DActivity", 3, "Cache TTL passed, finishing");
            finish();
            return;
        }
        m.a().a(true);
        this.m = System.currentTimeMillis();
        f.a().a(this.f58a).c();
    }

    public void onBackPressed() {
        f.a().a(this.f58a).d();
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        ProgressDialog progressDialog = this.e;
        if (progressDialog != null) {
            synchronized (progressDialog) {
                if (this.e != null) {
                    this.e.dismiss();
                    this.e = null;
                }
            }
        }
        WebView webView = this.f;
        if (webView != null) {
            webView.stopLoading();
        }
        i.a((Activity) this, false);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        f.a().a(this.f58a).b();
        b bVar = this.h;
        if (bVar != null && bVar.b()) {
            this.h.d();
        }
        overridePendingTransition(0, 0);
        String str = this.k;
        if (str != null && str.equals("back")) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Long l2 = this.i;
        if (l2 != null) {
            bundle.putSerializable("lastLoadTime", l2);
        }
        Long l3 = this.j;
        if (l3 != null) {
            bundle.putSerializable("adCacheTtl", l3);
        }
    }

    public void a(int i2) {
        View childAt = this.d.getChildAt(i2 - this.d.getFirstItemPosition());
        if (childAt != null) {
            d dVar = (d) childAt.getTag();
            e a2 = f.a().a(this.f58a);
            if (a2 != null && a2.e() != null && i2 < a2.e().size()) {
                ListItem listItem = a2.e().get(i2);
                dVar.b().setImageBitmap(a2.a(i2, listItem.a(), listItem.i()));
                dVar.b().requestLayout();
                dVar.a(listItem.q());
            }
        }
    }

    public void finish() {
        g.a("List3DActivity", 2, "Finishing activity.");
        this.l = System.currentTimeMillis();
        c.b((Context) this, b(), a());
        m.a().a(false);
        c();
        synchronized (this) {
            if (this.n != null) {
                com.startapp.common.b.a((Context) this).a(this.n);
                this.n = null;
            }
        }
        f.a().a(this.f58a).d();
        if (!AdsConstants.OVERRIDE_NETWORK.booleanValue()) {
            f.a().b(this.f58a);
        }
        super.finish();
    }

    private void c() {
        if (this.g == getResources().getConfiguration().orientation) {
            com.startapp.common.b.a((Context) this).a(new Intent("com.startapp.android.HideDisplayBroadcastListener"));
        }
    }

    private boolean d() {
        if (this.i == null || this.j == null || System.currentTimeMillis() - this.i.longValue() <= this.j.longValue()) {
            return false;
        }
        return true;
    }
}
