package com.mopub.nativeads;

public class IntInterval implements Comparable<IntInterval> {
    private int length;
    private int start;

    public IntInterval(int i, int i2) {
        this.start = i;
        this.length = i2;
    }

    public int getStart() {
        return this.start;
    }

    public int getLength() {
        return this.length;
    }

    public void setStart(int i) {
        this.start = i;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public boolean equals(int i, int i2) {
        return this.start == i && this.length == i2;
    }

    public String toString() {
        return "{start : " + this.start + ", length : " + this.length + "}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntInterval)) {
            return false;
        }
        IntInterval intInterval = (IntInterval) obj;
        if (this.start == intInterval.start && this.length == intInterval.length) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((899 + this.start) * 31) + this.length;
    }

    public int compareTo(IntInterval intInterval) {
        int i = this.start;
        int i2 = intInterval.start;
        return i == i2 ? this.length - intInterval.length : i - i2;
    }
}
