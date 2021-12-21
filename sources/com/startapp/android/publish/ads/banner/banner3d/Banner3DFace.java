package com.startapp.android.publish.ads.banner.banner3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.startapp.android.publish.ads.banner.BannerOptions;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.android.publish.adsCommon.c;
import com.startapp.android.publish.adsCommon.d.b;
import com.startapp.android.publish.adsCommon.i;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.a;
import com.startapp.common.a.g;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: StartAppSDK */
public class Banner3DFace implements Parcelable, a.C0009a {
    public static final Parcelable.Creator<Banner3DFace> CREATOR = new Parcelable.Creator<Banner3DFace>() {
        /* renamed from: a */
        public Banner3DFace createFromParcel(Parcel parcel) {
            return new Banner3DFace(parcel);
        }

        /* renamed from: a */
        public Banner3DFace[] newArray(int i) {
            return new Banner3DFace[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private AdDetails f49a;
    private Point b;
    private Bitmap c = null;
    private Bitmap d = null;
    private AtomicBoolean e = new AtomicBoolean(false);
    private b f;
    private i g = null;
    private b h = null;

    public int describeContents() {
        return 0;
    }

    public Banner3DFace(Context context, ViewGroup viewGroup, AdDetails adDetails, BannerOptions bannerOptions, b bVar) {
        this.f49a = adDetails;
        this.f = bVar;
        a(context, bannerOptions, viewGroup);
    }

    public AdDetails a() {
        return this.f49a;
    }

    public Bitmap b() {
        return this.d;
    }

    public void a(Context context, BannerOptions bannerOptions, ViewGroup viewGroup) {
        int a2 = h.a(context, bannerOptions.e() - 5);
        this.b = new Point((int) (((float) h.a(context, bannerOptions.d())) * bannerOptions.j()), (int) (((float) h.a(context, bannerOptions.e())) * bannerOptions.k()));
        b bVar = new b(context, new Point(bannerOptions.d(), bannerOptions.e()));
        this.h = bVar;
        bVar.setText(a().getTitle());
        this.h.setRating(a().getRating());
        this.h.setDescription(a().getDescription());
        this.h.setButtonText(this.f49a.isCPE());
        Bitmap bitmap = this.c;
        if (bitmap != null) {
            this.h.a(bitmap, a2, a2);
        } else {
            this.h.a(17301651, a2, a2);
            new a(a().getImageUrl(), this, 0).a();
            g.a("Banner3DFace", 3, " Banner Face Image Async Request: [" + a().getTitle() + "]");
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.b.x, this.b.y);
        layoutParams.addRule(13);
        viewGroup.addView(this.h, layoutParams);
        this.h.setVisibility(8);
        f();
    }

    private void f() {
        this.d = a((View) this.h);
        if (this.b.x > 0 && this.b.y > 0) {
            this.d = Bitmap.createScaledBitmap(this.d, this.b.x, this.b.y, false);
        }
    }

    private Bitmap a(View view) {
        view.measure(view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        return createBitmap;
    }

    public void a(Bitmap bitmap, int i) {
        b bVar;
        if (bitmap != null && (bVar = this.h) != null) {
            this.c = bitmap;
            bVar.setImage(bitmap);
            f();
        }
    }

    public i a(Context context) {
        if (a().getTrackingUrl() == null || !this.e.compareAndSet(false, true)) {
            return null;
        }
        Context context2 = context;
        i iVar = new i(context2, new String[]{a().getTrackingUrl()}, this.f, g());
        this.g = iVar;
        return iVar;
    }

    public void c() {
        i iVar = this.g;
        if (iVar != null) {
            iVar.a(false);
        }
    }

    public void b(Context context) {
        String intentPackageName = a().getIntentPackageName();
        boolean a2 = c.a(context, AdPreferences.Placement.INAPP_BANNER);
        i iVar = this.g;
        if (iVar != null) {
            iVar.a(true);
        }
        if (intentPackageName != null && !"null".equals(intentPackageName) && !TextUtils.isEmpty(intentPackageName)) {
            c.a(intentPackageName, a().getIntentDetails(), a().getClickUrl(), context, this.f);
        } else if (!a().isSmartRedirect() || a2) {
            c.a(context, a().getClickUrl(), a().getTrackingClickUrl(), this.f, a().isStartappBrowserEnabled() && !a2, false);
        } else {
            c.a(context, a().getClickUrl(), a().getTrackingClickUrl(), a().getPackageName(), this.f, com.startapp.android.publish.adsCommon.b.a().A(), com.startapp.android.publish.adsCommon.b.a().B(), a().isStartappBrowserEnabled(), a().shouldSendRedirectHops(), false);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(a(), i);
        parcel.writeInt(this.b.x);
        parcel.writeInt(this.b.y);
        parcel.writeParcelable(this.c, i);
        parcel.writeBooleanArray(new boolean[]{this.e.get()});
        parcel.writeSerializable(this.f);
    }

    public Banner3DFace(Parcel parcel) {
        this.f49a = (AdDetails) parcel.readParcelable(AdDetails.class.getClassLoader());
        Point point = new Point(1, 1);
        this.b = point;
        point.x = parcel.readInt();
        this.b.y = parcel.readInt();
        this.c = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.e.set(zArr[0]);
        this.f = (b) parcel.readSerializable();
    }

    public void d() {
        a(this.c);
        a(this.d);
        this.c = null;
        this.d = null;
    }

    public void e() {
        d();
        i iVar = this.g;
        if (iVar != null) {
            iVar.a(false);
        }
        b bVar = this.h;
        if (bVar != null) {
            bVar.removeAllViews();
            this.h = null;
        }
    }

    private void a(Bitmap bitmap) {
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    private long g() {
        if (a().getDelayImpressionInSeconds() != null) {
            return TimeUnit.SECONDS.toMillis(a().getDelayImpressionInSeconds().longValue());
        }
        return TimeUnit.SECONDS.toMillis(MetaData.getInstance().getIABDisplayImpressionDelayInSeconds());
    }
}
