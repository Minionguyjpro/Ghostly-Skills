package com.startapp.android.publish.ads.splash;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mopub.mobileads.resource.DrawableConstants;
import com.startapp.android.publish.ads.splash.SplashConfig;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.f.f;

/* compiled from: StartAppSDK */
public class i {

    /* renamed from: com.startapp.android.publish.ads.splash.i$1  reason: invalid class name */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f104a;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.startapp.android.publish.ads.splash.SplashConfig$Theme[] r0 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f104a = r0
                com.startapp.android.publish.ads.splash.SplashConfig$Theme r1 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.DEEP_BLUE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f104a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.ads.splash.SplashConfig$Theme r1 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.SKY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f104a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.ads.splash.SplashConfig$Theme r1 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.ASHEN_SKY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f104a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.ads.splash.SplashConfig$Theme r1 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.BLAZE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f104a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.startapp.android.publish.ads.splash.SplashConfig$Theme r1 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.GLOOMY     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f104a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.startapp.android.publish.ads.splash.SplashConfig$Theme r1 = com.startapp.android.publish.ads.splash.SplashConfig.Theme.OCEAN     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.splash.i.AnonymousClass1.<clinit>():void");
        }
    }

    public static View a(Context context, SplashConfig splashConfig) {
        switch (AnonymousClass1.f104a[splashConfig.getTheme().ordinal()]) {
            case 1:
                return g(context, splashConfig);
            case 2:
                return f(context, splashConfig);
            case 3:
                return e(context, splashConfig);
            case 4:
                return d(context, splashConfig);
            case 5:
                return c(context, splashConfig);
            case 6:
                return b(context, splashConfig);
            default:
                return null;
        }
    }

    private static View b(Context context, SplashConfig splashConfig) {
        View h = h(context, splashConfig);
        h.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-14451558, -7876130}));
        ((TextView) h.findViewById(100)).setTextColor(Color.rgb(6, 61, 82));
        ((TextView) h.findViewById(105)).setTextColor(Color.rgb(6, 61, 82));
        return h;
    }

    private static View c(Context context, SplashConfig splashConfig) {
        View h = h(context, splashConfig);
        h.setBackgroundColor(Color.rgb(47, 53, 63));
        ((TextView) h.findViewById(100)).setTextColor(Color.rgb(51, 181, 229));
        ((TextView) h.findViewById(105)).setTextColor(Color.rgb(122, 130, 139));
        return h;
    }

    private static View d(Context context, SplashConfig splashConfig) {
        View h = h(context, splashConfig);
        int i = context.getResources().getDisplayMetrics().widthPixels;
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{-92376, -40960});
        gradientDrawable.setGradientType(1);
        gradientDrawable.setGradientRadius((float) (i / 2));
        h.setBackgroundDrawable(gradientDrawable);
        ((TextView) h.findViewById(100)).setTextColor(Color.rgb(255, 255, 255));
        ((TextView) h.findViewById(105)).setTextColor(Color.rgb(255, 198, 151));
        return h;
    }

    private static View e(Context context, SplashConfig splashConfig) {
        View h = h(context, splashConfig);
        h.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-3881788, -1}));
        ((TextView) h.findViewById(100)).setTextColor(Color.rgb(51, 51, 51));
        ((TextView) h.findViewById(105)).setTextColor(Color.rgb(153, 153, 153));
        return h;
    }

    private static View f(Context context, SplashConfig splashConfig) {
        View h = h(context, splashConfig);
        int i = context.getResources().getDisplayMetrics().widthPixels;
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{-921103, -6040347});
        gradientDrawable.setGradientType(1);
        gradientDrawable.setGradientRadius((float) (i / 2));
        h.setBackgroundDrawable(gradientDrawable);
        ((TextView) h.findViewById(100)).setTextColor(Color.rgb(51, 51, 51));
        ((TextView) h.findViewById(105)).setTextColor(Color.rgb(162, 172, 175));
        return h;
    }

    private static View g(Context context, SplashConfig splashConfig) {
        View h = h(context, splashConfig);
        h.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-16356182, -15029533, -16356182}));
        ((TextView) h.findViewById(100)).setTextColor(Color.rgb(255, 255, 255));
        ((TextView) h.findViewById(105)).setTextColor(Color.rgb(208, 210, 210));
        return h;
    }

    private static View h(Context context, SplashConfig splashConfig) {
        int i;
        int i2;
        int i3;
        int i4;
        Context context2 = context;
        RelativeLayout relativeLayout = new RelativeLayout(context2);
        relativeLayout.setId(AdsConstants.SPLASH_NATIVE_MAIN_LAYOUT_ID);
        relativeLayout.setBackgroundColor(-1);
        RelativeLayout relativeLayout2 = new RelativeLayout(context2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        relativeLayout.addView(relativeLayout2, layoutParams);
        Point point = new Point(h.a(context2, (int) DrawableConstants.CtaButton.WIDTH_DIPS), h.a(context2, 28));
        if (splashConfig.getOrientation() == SplashConfig.Orientation.PORTRAIT) {
            i4 = h.a(context2, 5);
            i3 = h.a(context2, 8);
            i2 = h.a(context2, 75);
            i = h.a(context2, 130);
        } else {
            i4 = h.a(context2, 5);
            i3 = h.a(context2, 8);
            i2 = h.a(context2, 40);
            i = h.a(context2, 100);
        }
        ImageView imageView = new ImageView(context2);
        imageView.setImageDrawable(splashConfig.getLogo());
        imageView.setId(101);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i, i);
        layoutParams2.addRule(10);
        layoutParams2.addRule(14);
        layoutParams2.setMargins(0, 0, 0, i4);
        relativeLayout2.addView(imageView, layoutParams2);
        TextView textView = new TextView(context2);
        textView.setTypeface(Typeface.DEFAULT);
        textView.setText(splashConfig.getAppName());
        textView.setId(100);
        textView.setTextSize(1, 26.0f);
        textView.setTextColor(Color.rgb(255, 255, 255));
        textView.setGravity(17);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(14);
        layoutParams3.addRule(3, 101);
        layoutParams3.setMargins(0, 0, 0, i2);
        relativeLayout2.addView(textView, layoutParams3);
        try {
            WebView webView = new WebView(context2);
            webView.setId(102);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(point.x, point.y);
            layoutParams4.addRule(14);
            layoutParams4.addRule(3, 100);
            layoutParams4.setMargins(0, 0, 0, i3);
            webView.setBackgroundColor(Color.argb(0, 0, 0, 0));
            webView.setVerticalScrollBarEnabled(false);
            webView.setHorizontalScrollBarEnabled(false);
            webView.loadDataWithBaseURL((String) null, "<html>\n<style>\n#fountainG{\nposition:relative;\nwidth:141px;\nheight:17px}\n.fountainG{\nposition:absolute;\ntop:0;\nbackground-color:#000000;\nwidth:18px;\nheight:18px;\n-moz-animation-name:bounce_fountainG;\n-moz-animation-duration:2s;\n-moz-animation-iteration-count:infinite;\n-moz-animation-direction:linear;\n-moz-transform:scale(.3);\n-moz-border-radius:12px;\n-webkit-animation-name:bounce_fountainG;\n-webkit-animation-duration:2s;\n-webkit-animation-iteration-count:infinite;\n-webkit-animation-direction:linear;\n-webkit-transform:scale(.3);\n-webkit-border-radius:12px;\n-ms-animation-name:bounce_fountainG;\n-ms-animation-duration:2s;\n-ms-animation-iteration-count:infinite;\n-ms-animation-direction:linear;\n-ms-transform:scale(.3);\n-ms-border-radius:12px;\n-o-animation-name:bounce_fountainG;\n-o-animation-duration:2s;\n-o-animation-iteration-count:infinite;\n-o-animation-direction:linear;\n-o-transform:scale(.3);\n-o-border-radius:12px;\nanimation-name:bounce_fountainG;\nanimation-duration:2s;\nanimation-iteration-count:infinite;\nanimation-direction:linear;\ntransform:scale(.3);\nborder-radius:12px;\n}\n#fountainG_1{\nleft:0;\n-moz-animation-delay:0.8s;\n-webkit-animation-delay:0.8s;\n-ms-animation-delay:0.8s;\n-o-animation-delay:0.8s;\nanimation-delay:0.8s;\n}\n#fountainG_2{\nleft:18px;\n-moz-animation-delay:1s;\n-webkit-animation-delay:1s;\n-ms-animation-delay:1s;\n-o-animation-delay:1s;\nanimation-delay:1s;\n}\n#fountainG_3{\nleft:35px;\n-moz-animation-delay:1.2s;\n-webkit-animation-delay:1.2s;\n-ms-animation-delay:1.2s;\n-o-animation-delay:1.2s;\nanimation-delay:1.2s;\n}\n#fountainG_4{\nleft:53px;\n-moz-animation-delay:1.4s;\n-webkit-animation-delay:1.4s;\n-ms-animation-delay:1.4s;\n-o-animation-delay:1.4s;\nanimation-delay:1.4s;\n}\n#fountainG_5{\nleft:71px;\n-moz-animation-delay:1.6s;\n-webkit-animation-delay:1.6s;\n-ms-animation-delay:1.6s;\n-o-animation-delay:1.6s;\nanimation-delay:1.6s;\n}\n#fountainG_6{\nleft:88px;\n-moz-animation-delay:1.8s;\n-webkit-animation-delay:1.8s;\n-ms-animation-delay:1.8s;\n-o-animation-delay:1.8s;\nanimation-delay:1.8s;\n}\n#fountainG_7{\nleft:106px;\n-moz-animation-delay:2s;\n-webkit-animation-delay:2s;\n-ms-animation-delay:2s;\n-o-animation-delay:2s;\nanimation-delay:2s;\n}\n#fountainG_8{\nleft:123px;\n-moz-animation-delay:2.2s;\n-webkit-animation-delay:2.2s;\n-ms-animation-delay:2.2s;\n-o-animation-delay:2.2s;\nanimation-delay:2.2s;\n}\n@-moz-keyframes bounce_fountainG{\n0%{\n-moz-transform:scale(1);\nbackground-color:#000000;\n}\n100%{\n-moz-transform:scale(.3);\nbackground-color:rgba(255,255,255,0.2);\n}\n}\n@-webkit-keyframes bounce_fountainG{\n0%{\n-webkit-transform:scale(1);\nbackground-color:#000000;\n}\n100%{\n-webkit-transform:scale(.3);\nbackground-color:rgba(255,255,255,0.2);\n}\n}\n@-ms-keyframes bounce_fountainG{\n0%{\n-ms-transform:scale(1);\nbackground-color:#000000;\n}\n100%{\n-ms-transform:scale(.3);\nbackground-color:rgba(255,255,255,0.2);\n}\n}\n@-o-keyframes bounce_fountainG{\n0%{\n-o-transform:scale(1);\nbackground-color:#000000;\n}\n100%{\n-o-transform:scale(.3);\nbackground-color:rgba(255,255,255,0.2);\n}\n}\n@keyframes bounce_fountainG{\n0%{\ntransform:scale(1);\nbackground-color:#000000;\n}\n100%{\ntransform:scale(.3);\nbackground-color:rgba(255,255,255,0.2);\n}\n}\n</style>\n<body style=\"margin:0;padding:0\">\n<div id=\"fountainG\">\n<div id=\"fountainG_1\" class=\"fountainG\">\n</div>\n<div id=\"fountainG_2\" class=\"fountainG\">\n</div>\n<div id=\"fountainG_3\" class=\"fountainG\">\n</div>\n<div id=\"fountainG_4\" class=\"fountainG\">\n</div>\n<div id=\"fountainG_5\" class=\"fountainG\">\n</div>\n<div id=\"fountainG_6\" class=\"fountainG\">\n</div>\n<div id=\"fountainG_7\" class=\"fountainG\">\n</div>\n<div id=\"fountainG_8\" class=\"fountainG\">\n</div>\n</div>\n</body>\n</html>", "text/html", "utf-8", (String) null);
            relativeLayout2.addView(webView, layoutParams4);
        } catch (Exception e) {
            f.a(context2, d.EXCEPTION, "TemplatesLayout.getBaseTemplate - webview failed", e.getMessage(), "");
        }
        TextView textView2 = new TextView(context2);
        textView2.setText("Loading...");
        textView2.setId(105);
        textView2.setTextSize(1, 18.0f);
        textView2.setTextColor(Color.rgb(208, 210, 210));
        textView2.setGravity(17);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(14);
        layoutParams5.addRule(3, 102);
        layoutParams5.setMargins(0, 0, 0, 0);
        relativeLayout2.addView(textView2, layoutParams5);
        return relativeLayout;
    }
}
