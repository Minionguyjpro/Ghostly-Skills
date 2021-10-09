package com.startapp.android.publish.ads.list3d;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: StartAppSDK */
class SimpleDynamics extends Dynamics implements Parcelable {
    public static final Parcelable.Creator<SimpleDynamics> CREATOR = new Parcelable.Creator<SimpleDynamics>() {
        /* renamed from: a */
        public SimpleDynamics createFromParcel(Parcel parcel) {
            return new SimpleDynamics(parcel);
        }

        /* renamed from: a */
        public SimpleDynamics[] newArray(int i) {
            return new SimpleDynamics[i];
        }
    };
    private float f;
    private float g;

    public int describeContents() {
        return 0;
    }

    public SimpleDynamics(float f2, float f3) {
        this.f = f2;
        this.g = f3;
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        this.b += c() * this.g;
        this.f57a += (this.b * ((float) i)) / 1000.0f;
        this.b *= this.f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.f);
        parcel.writeFloat(this.g);
    }

    public SimpleDynamics(Parcel parcel) {
        super(parcel);
        this.f = parcel.readFloat();
        this.g = parcel.readFloat();
    }

    public void a(double d) {
        super.a(d);
    }

    public String toString() {
        return super.toString() + ", Friction: [" + this.f + "], Snap:[" + this.g + "]";
    }
}
