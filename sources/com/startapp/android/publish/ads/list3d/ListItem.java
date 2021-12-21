package com.startapp.android.publish.ads.list3d;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import com.startapp.android.publish.common.model.AdDetails;

/* compiled from: StartAppSDK */
public class ListItem implements Parcelable {
    public static final Parcelable.Creator<ListItem> CREATOR = new Parcelable.Creator<ListItem>() {
        /* renamed from: a */
        public ListItem createFromParcel(Parcel parcel) {
            return new ListItem(parcel);
        }

        /* renamed from: a */
        public ListItem[] newArray(int i) {
            return new ListItem[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f64a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private float j;
    private boolean k;
    private boolean l;
    private Drawable m;
    private String n;
    private String o;
    private Long p;
    private Boolean q;
    private String r;

    public int describeContents() {
        return 0;
    }

    public ListItem(AdDetails adDetails) {
        this.f64a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = 0.0f;
        this.k = false;
        this.l = true;
        this.m = null;
        this.q = null;
        this.r = "";
        this.f64a = adDetails.getAdId();
        this.b = adDetails.getClickUrl();
        this.c = adDetails.getTrackingUrl();
        this.d = adDetails.getTrackingClickUrl();
        this.e = adDetails.getTrackingCloseUrl();
        this.f = adDetails.getPackageName();
        this.g = adDetails.getTitle();
        this.h = adDetails.getDescription();
        this.i = adDetails.getImageUrl();
        this.j = adDetails.getRating();
        this.k = adDetails.isSmartRedirect();
        this.l = adDetails.isStartappBrowserEnabled();
        this.m = null;
        this.r = adDetails.getTemplate();
        this.n = adDetails.getIntentDetails();
        this.o = adDetails.getIntentPackageName();
        this.p = adDetails.getDelayImpressionInSeconds();
        this.q = adDetails.shouldSendRedirectHops();
    }

    public ListItem(Parcel parcel) {
        this.f64a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = 0.0f;
        boolean z = false;
        this.k = false;
        this.l = true;
        this.m = null;
        this.q = null;
        this.r = "";
        if (parcel.readInt() == 1) {
            this.m = new BitmapDrawable((Bitmap) Bitmap.CREATOR.createFromParcel(parcel));
        } else {
            this.m = null;
        }
        this.f64a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readFloat();
        if (parcel.readInt() == 1) {
            this.k = true;
        } else {
            this.k = false;
        }
        if (parcel.readInt() == 0) {
            this.l = false;
        } else {
            this.l = true;
        }
        this.r = parcel.readString();
        this.o = parcel.readString();
        this.n = parcel.readString();
        Long valueOf = Long.valueOf(parcel.readLong());
        this.p = valueOf;
        if (valueOf.longValue() == -1) {
            this.p = null;
        }
        int readInt = parcel.readInt();
        if (readInt == 0) {
            this.q = null;
        } else {
            this.q = Boolean.valueOf(readInt == 1 ? true : z);
        }
    }

    public String a() {
        return this.f64a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public String h() {
        return this.h;
    }

    public String i() {
        return this.i;
    }

    public Drawable j() {
        return this.m;
    }

    public float k() {
        return this.j;
    }

    public boolean l() {
        return this.k;
    }

    public boolean m() {
        return this.l;
    }

    public String n() {
        return this.r;
    }

    public String o() {
        return this.n;
    }

    public String p() {
        return this.o;
    }

    public boolean q() {
        return this.o != null;
    }

    public Long r() {
        return this.p;
    }

    public Boolean s() {
        return this.q;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        int i3 = 1;
        if (j() != null) {
            parcel.writeParcelable(((BitmapDrawable) j()).getBitmap(), i2);
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.f64a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeFloat(this.j);
        parcel.writeInt(this.k ? 1 : 0);
        parcel.writeInt(this.l ? 1 : 0);
        parcel.writeString(this.r);
        parcel.writeString(this.o);
        parcel.writeString(this.n);
        Long l2 = this.p;
        if (l2 == null) {
            parcel.writeLong(-1);
        } else {
            parcel.writeLong(l2.longValue());
        }
        Boolean bool = this.q;
        if (bool == null) {
            parcel.writeInt(0);
            return;
        }
        if (!bool.booleanValue()) {
            i3 = -1;
        }
        parcel.writeInt(i3);
    }
}
