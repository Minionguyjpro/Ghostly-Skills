package com.startapp.android.publish.ads.banner.banner3d;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import com.mopub.mobileads.MoPubView;
import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.ads.banner.BannerOptions;
import com.startapp.android.publish.ads.banner.d;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.common.a.g;

/* compiled from: StartAppSDK */
public class Banner3DSize {

    /* compiled from: StartAppSDK */
    public enum Size {
        XXSMALL(new d(MoPubView.MoPubAdSizeInt.HEIGHT_280_INT, 50)),
        XSMALL(new d(300, 50)),
        SMALL(new d(320, 50)),
        MEDIUM(new d(468, 60)),
        LARGE(new d(728, 90)),
        XLARGE(new d(com.appnext.base.b.d.fb, 90));
        
        private d size;

        private Size(d dVar) {
            this.size = dVar;
        }

        public d getSize() {
            return this.size;
        }
    }

    public static boolean a(Context context, ViewParent viewParent, BannerOptions bannerOptions, Banner3D banner3D, d dVar) {
        g.a("Banner3DSize", 3, "============== Optimize Size ==========");
        d a2 = a(context, viewParent, bannerOptions, banner3D);
        dVar.a(a2.a(), a2.b());
        boolean z = false;
        for (Size size : Size.values()) {
            if (size.getSize().a() <= a2.a() && size.getSize().b() <= a2.b()) {
                g.a("Banner3DSize", 3, "BannerSize [" + size.getSize().a() + "," + size.getSize().b() + "]");
                bannerOptions.a(size.getSize().a(), size.getSize().b());
                z = true;
            }
        }
        if (!z) {
            bannerOptions.a(0, 0);
        }
        g.a("Banner3DSize", 3, "============== Optimize Size [" + z + "] ==========");
        return z;
    }

    private static d a(Context context, ViewParent viewParent, BannerOptions bannerOptions, Banner3D banner3D) {
        Point point = new Point();
        point.x = bannerOptions.d();
        point.y = bannerOptions.e();
        g.a("Banner3DSize", 3, "=============== set Application Size ===========");
        if (banner3D.getLayoutParams() != null && banner3D.getLayoutParams().width > 0) {
            point.x = h.b(context, banner3D.getLayoutParams().width + 1);
        }
        if (banner3D.getLayoutParams() != null && banner3D.getLayoutParams().height > 0) {
            point.y = h.b(context, banner3D.getLayoutParams().height + 1);
        }
        if (banner3D.getLayoutParams() == null || banner3D.getLayoutParams().width <= 0 || banner3D.getLayoutParams().height <= 0) {
            if (context instanceof Activity) {
                g.a("Banner3DSize", 3, "Context is Activity");
                View decorView = ((Activity) context).getWindow().getDecorView();
                try {
                    View view = (View) viewParent;
                    if (view instanceof Banner) {
                        g.a("Banner3DSize", 3, "Parent is instance of Wrapper Banner");
                        view = (View) view.getParent();
                    }
                    boolean z = false;
                    boolean z2 = false;
                    while (view != null && (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0)) {
                        if (view.getMeasuredWidth() > 0 && !z) {
                            b(context, point, view);
                            z = true;
                        }
                        if (view.getMeasuredHeight() > 0 && !z2) {
                            a(context, point, view);
                            z2 = true;
                        }
                        view = (View) view.getParent();
                    }
                    if (view == null) {
                        c(context, point, decorView);
                    } else {
                        if (!z) {
                            b(context, point, view);
                        }
                        if (!z2) {
                            a(context, point, view);
                        }
                    }
                } catch (Exception unused) {
                    c(context, point, decorView);
                    g.a("Banner3DSize", 3, "Exception occoured");
                }
            } else {
                g.a("Banner3DSize", 3, "Context not Activity, get max win size");
                try {
                    WindowManager windowManager = (WindowManager) context.getSystemService("window");
                    if (windowManager != null) {
                        h.a(context, windowManager, point);
                    }
                } catch (Exception e) {
                    f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "Banner3DSize.getApplicationSize - system service failed", e.getMessage(), "");
                }
            }
        }
        g.a("Banner3DSize", 3, "============ exit Application Size [" + point.x + "," + point.y + "] =========");
        return new d(point.x, point.y);
    }

    private static void a(Context context, Point point, View view) {
        point.y = h.b(context, (view.getMeasuredHeight() - view.getPaddingBottom()) - view.getPaddingTop());
    }

    private static void b(Context context, Point point, View view) {
        point.x = h.b(context, (view.getMeasuredWidth() - view.getPaddingLeft()) - view.getPaddingRight());
    }

    private static void c(Context context, Point point, View view) {
        point.x = h.b(context, view.getMeasuredWidth());
        point.y = h.b(context, view.getMeasuredHeight());
    }
}
