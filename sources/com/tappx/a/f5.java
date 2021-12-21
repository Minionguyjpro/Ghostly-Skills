package com.tappx.a;

import android.os.Parcel;
import android.os.Parcelable;

public class f5 implements Parcelable {
    public static final Parcelable.Creator<f5> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    private int f442a;
    private boolean b;
    private k3 c;
    private int d;
    private int e;
    private boolean f;
    private boolean g;
    private j3 h;

    static class a implements Parcelable.Creator<f5> {
        a() {
        }

        public f5 createFromParcel(Parcel parcel) {
            return new f5(parcel, (a) null);
        }

        public f5[] newArray(int i) {
            return new f5[i];
        }
    }

    /* synthetic */ f5(Parcel parcel, a aVar) {
        this(parcel);
    }

    public f5 a(int i) {
        this.f442a = i;
        return this;
    }

    public k3 b() {
        return this.c;
    }

    public int c() {
        return this.f442a;
    }

    public int d() {
        return this.e;
    }

    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.d;
    }

    public boolean f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f442a);
        parcel.writeByte(this.b ? (byte) 1 : 0);
        parcel.writeString(k3.a(this.c));
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeByte(this.f ? (byte) 1 : 0);
        parcel.writeByte(this.g ? (byte) 1 : 0);
        parcel.writeString(j3.a(this.h));
    }

    private f5(Parcel parcel) {
        this.c = k3.NONE;
        this.f442a = parcel.readInt();
        boolean z = true;
        this.b = parcel.readByte() != 0;
        this.c = k3.a(parcel.readString());
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readByte() != 0;
        this.g = parcel.readByte() == 0 ? false : z;
        this.h = j3.a(parcel.readString());
    }

    public f5 a(k3 k3Var) {
        if (k3Var == null) {
            k3Var = k3.NONE;
        }
        this.c = k3Var;
        return this;
    }

    public f5 b(int i) {
        this.e = i;
        return this;
    }

    public f5 c(boolean z) {
        this.b = z;
        return this;
    }

    public f5 b(boolean z) {
        this.g = z;
        return this;
    }

    public f5 c(int i) {
        this.d = i;
        return this;
    }

    public f5 a(boolean z) {
        this.f = z;
        return this;
    }

    public j3 a() {
        return this.h;
    }

    public f5 a(j3 j3Var) {
        this.h = j3Var;
        return this;
    }

    public f5() {
        this.c = k3.NONE;
    }
}
