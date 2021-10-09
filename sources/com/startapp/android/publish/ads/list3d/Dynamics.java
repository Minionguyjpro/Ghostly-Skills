package com.startapp.android.publish.ads.list3d;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.animation.AnimationUtils;

/* compiled from: StartAppSDK */
public abstract class Dynamics implements Parcelable {

    /* renamed from: a  reason: collision with root package name */
    protected float f57a;
    protected float b;
    protected float c = Float.MAX_VALUE;
    protected float d = -3.4028235E38f;
    protected long e = 0;

    /* access modifiers changed from: protected */
    public abstract void a(int i);

    public int describeContents() {
        return 0;
    }

    public Dynamics() {
    }

    public Dynamics(Parcel parcel) {
        this.f57a = parcel.readFloat();
        this.b = parcel.readFloat();
        this.c = parcel.readFloat();
        this.d = parcel.readFloat();
        this.e = AnimationUtils.currentAnimationTimeMillis();
    }

    public void a(float f, float f2, long j) {
        this.b = f2;
        this.f57a = f;
        this.e = j;
    }

    public float a() {
        return this.f57a;
    }

    public float b() {
        return this.b;
    }

    public boolean a(float f, float f2) {
        boolean z = Math.abs(this.b) < f;
        float f3 = this.f57a;
        boolean z2 = f3 - f2 < this.c && f3 + f2 > this.d;
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    public void a(float f) {
        this.c = f;
    }

    public void b(float f) {
        this.d = f;
    }

    public void a(long j) {
        long j2 = this.e;
        if (j2 != 0) {
            int i = (int) (j - j2);
            if (i > 50) {
                i = 50;
            }
            a(i);
        }
        this.e = j;
    }

    /* access modifiers changed from: protected */
    public float c() {
        float f = this.f57a;
        float f2 = this.c;
        if (f <= f2) {
            f2 = this.d;
            if (f >= f2) {
                return 0.0f;
            }
        }
        return f2 - f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f57a);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.c);
        parcel.writeFloat(this.d);
    }

    public void a(double d2) {
        double d3 = (double) this.f57a;
        Double.isNaN(d3);
        this.f57a = (float) (d3 * d2);
    }

    public String toString() {
        return "Position: [" + this.f57a + "], Velocity:[" + this.b + "], MaxPos: [" + this.c + "], mMinPos: [" + this.d + "] LastTime:[" + this.e + "]";
    }
}
