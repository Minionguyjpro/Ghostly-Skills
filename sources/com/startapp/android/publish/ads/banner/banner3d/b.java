package com.startapp.android.publish.ads.banner.banner3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Point;
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
import com.startapp.android.publish.ads.banner.banner3d.Banner3DSize;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.common.a.c;

/* compiled from: StartAppSDK */
public class b extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private TextView f50a;
    private TextView b;
    private ImageView c;
    private com.startapp.android.publish.a.b d;
    private TextView e;
    private Point f;

    /* compiled from: StartAppSDK */
    private enum a {
        XS,
        S,
        M,
        L,
        XL
    }

    public b(Context context, Point point) {
        super(context);
        this.f = point;
        a();
    }

    private void a() {
        Context context = getContext();
        a templateBySize = getTemplateBySize();
        setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{com.startapp.android.publish.adsCommon.b.a().n(), com.startapp.android.publish.adsCommon.b.a().o()}));
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        int a2 = h.a(context, 2);
        int a3 = h.a(context, 3);
        h.a(context, 4);
        int a4 = h.a(context, 5);
        int a5 = h.a(context, 6);
        int a6 = h.a(context, 8);
        h.a(context, 10);
        int a7 = h.a(context, 20);
        h.a(context, 84);
        int a8 = h.a(context, 90);
        setPadding(a4, 0, a4, 0);
        setTag(this);
        ImageView imageView = new ImageView(context);
        this.c = imageView;
        imageView.setId(1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a8, a8);
        layoutParams.addRule(15);
        this.c.setLayoutParams(layoutParams);
        TextView textView = new TextView(context);
        this.f50a = textView;
        textView.setId(2);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(c.a(17), 1);
        layoutParams2.addRule(14);
        this.f50a.setLayoutParams(layoutParams2);
        this.f50a.setTextColor(com.startapp.android.publish.adsCommon.b.a().q().intValue());
        this.f50a.setGravity(c.a(8388611));
        this.f50a.setBackgroundColor(0);
        int i = AnonymousClass2.f52a[templateBySize.ordinal()];
        if (i == 1 || i == 2) {
            this.f50a.setTextSize(17.0f);
            this.f50a.setPadding(a3, 0, 0, a2);
            Context context2 = getContext();
            double d2 = (double) this.f.x;
            Double.isNaN(d2);
            layoutParams2.width = h.a(context2, (int) (d2 * 0.55d));
        } else if (i == 3) {
            this.f50a.setTextSize(17.0f);
            this.f50a.setPadding(a3, 0, 0, a2);
            Context context3 = getContext();
            double d3 = (double) this.f.x;
            Double.isNaN(d3);
            layoutParams2.width = h.a(context3, (int) (d3 * 0.65d));
        } else if (i == 4 || i == 5) {
            this.f50a.setTextSize(22.0f);
            this.f50a.setPadding(a3, 0, 0, a4);
        }
        this.f50a.setSingleLine(true);
        this.f50a.setEllipsize(TextUtils.TruncateAt.END);
        h.a(this.f50a, com.startapp.android.publish.adsCommon.b.a().r());
        TextView textView2 = new TextView(context);
        this.b = textView2;
        textView2.setId(3);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(c.a(17), 1);
        layoutParams3.addRule(3, 2);
        layoutParams3.setMargins(0, 0, 0, a4);
        this.b.setLayoutParams(layoutParams3);
        this.b.setTextColor(com.startapp.android.publish.adsCommon.b.a().t().intValue());
        this.b.setTextSize(18.0f);
        this.b.setMaxLines(2);
        this.b.setLines(2);
        this.b.setSingleLine(false);
        this.b.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.b.setHorizontallyScrolling(true);
        this.b.setPadding(a3, 0, 0, 0);
        com.startapp.android.publish.a.b bVar = new com.startapp.android.publish.a.b(getContext());
        this.d = bVar;
        bVar.setId(5);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        int i2 = AnonymousClass2.f52a[templateBySize.ordinal()];
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            layoutParams4.addRule(c.a(17), 1);
            layoutParams4.addRule(8, 1);
        } else if (i2 == 4 || i2 == 5) {
            layoutParams4.addRule(c.a(17), 2);
            Context context4 = getContext();
            double d4 = (double) this.f.x;
            Double.isNaN(d4);
            layoutParams3.width = h.a(context4, (int) (d4 * 0.6d));
        }
        layoutParams4.setMargins(a3, a6, a3, 0);
        this.d.setLayoutParams(layoutParams4);
        this.e = new TextView(context);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        int i3 = AnonymousClass2.f52a[templateBySize.ordinal()];
        if (i3 == 1 || i3 == 2 || i3 == 3) {
            this.e.setTextSize(13.0f);
            layoutParams5.addRule(c.a(17), 2);
            layoutParams5.addRule(15);
        } else if (i3 == 4) {
            layoutParams5.addRule(c.a(17), 3);
            layoutParams5.addRule(15);
            layoutParams5.setMargins(a7, 0, 0, 0);
            this.e.setTextSize(26.0f);
        } else if (i3 == 5) {
            layoutParams5.addRule(c.a(17), 3);
            layoutParams5.addRule(15);
            layoutParams5.setMargins(a7 * 7, 0, 0, 0);
            this.e.setTextSize(26.0f);
        }
        this.e.setPadding(a5, a5, a5, a5);
        this.e.setLayoutParams(layoutParams5);
        setButtonText(false);
        this.e.setTextColor(-1);
        this.e.setTypeface((Typeface) null, 1);
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
        addView(this.c);
        addView(this.f50a);
        int i4 = AnonymousClass2.f52a[templateBySize.ordinal()];
        if (i4 == 1 || i4 == 2 || i4 == 3) {
            addView(this.e);
        } else if (i4 == 4 || i4 == 5) {
            addView(this.e);
            addView(this.b);
        }
        addView(this.d);
    }

    /* renamed from: com.startapp.android.publish.ads.banner.banner3d.b$2  reason: invalid class name */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f52a;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.startapp.android.publish.ads.banner.banner3d.b$a[] r0 = com.startapp.android.publish.ads.banner.banner3d.b.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f52a = r0
                com.startapp.android.publish.ads.banner.banner3d.b$a r1 = com.startapp.android.publish.ads.banner.banner3d.b.a.XS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f52a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.ads.banner.banner3d.b$a r1 = com.startapp.android.publish.ads.banner.banner3d.b.a.S     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f52a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.ads.banner.banner3d.b$a r1 = com.startapp.android.publish.ads.banner.banner3d.b.a.M     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f52a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.ads.banner.banner3d.b$a r1 = com.startapp.android.publish.ads.banner.banner3d.b.a.L     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f52a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.startapp.android.publish.ads.banner.banner3d.b$a r1 = com.startapp.android.publish.ads.banner.banner3d.b.a.XL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.banner.banner3d.b.AnonymousClass2.<clinit>():void");
        }
    }

    public void setText(String str) {
        this.f50a.setText(str);
    }

    public void setImage(Bitmap bitmap) {
        this.c.setImageBitmap(bitmap);
    }

    public void a(int i, int i2, int i3) {
        this.c.setImageResource(i);
        ViewGroup.LayoutParams layoutParams = this.c.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i3;
        this.c.setLayoutParams(layoutParams);
    }

    public void setRating(float f2) {
        try {
            this.d.setRating(f2);
        } catch (NullPointerException unused) {
        }
    }

    public void a(Bitmap bitmap, int i, int i2) {
        this.c.setImageBitmap(bitmap);
        ViewGroup.LayoutParams layoutParams = this.c.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        this.c.setLayoutParams(layoutParams);
    }

    public void setDescription(String str) {
        if (str != null) {
            String str2 = "";
            if (str.compareTo(str2) != 0) {
                String[] a2 = a(str);
                String str3 = a2[0];
                if (a2[1] != null) {
                    str2 = a(a2[1])[0];
                }
                if (str.length() >= 110) {
                    str2 = str2 + "...";
                }
                this.b.setText(str3 + "\n" + str2);
            }
        }
    }

    private String[] a(String str) {
        boolean z;
        String[] strArr = new String[2];
        int i = 55;
        if (str.length() > 55) {
            char[] charArray = str.substring(0, 55).toCharArray();
            int length = charArray.length - 1;
            int i2 = length - 1;
            while (true) {
                if (i2 <= 0) {
                    z = false;
                    break;
                } else if (charArray[i2] == ' ') {
                    length = i2;
                    z = true;
                    break;
                } else {
                    i2--;
                }
            }
            if (z) {
                i = length;
            }
            strArr[0] = str.substring(0, i);
            strArr[1] = str.substring(i + 1, str.length());
        } else {
            strArr[0] = str;
            strArr[1] = null;
        }
        return strArr;
    }

    private a getTemplateBySize() {
        a aVar = a.S;
        if (this.f.x > Banner3DSize.Size.SMALL.getSize().a() || this.f.y > Banner3DSize.Size.SMALL.getSize().b()) {
            aVar = a.M;
        }
        if (this.f.x > Banner3DSize.Size.MEDIUM.getSize().a() || this.f.y > Banner3DSize.Size.MEDIUM.getSize().b()) {
            aVar = a.L;
        }
        return (this.f.x > Banner3DSize.Size.LARGE.getSize().a() || this.f.y > Banner3DSize.Size.LARGE.getSize().b()) ? a.XL : aVar;
    }

    public void setButtonText(boolean z) {
        if (z) {
            this.e.setText("OPEN");
        } else {
            this.e.setText("DOWNLOAD");
        }
    }
}
