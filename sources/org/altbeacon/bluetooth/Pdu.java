package org.altbeacon.bluetooth;

public class Pdu {
    private byte[] mBytes;
    private int mDeclaredLength;
    private int mEndIndex;
    private int mStartIndex;
    private byte mType;

    public static Pdu parse(byte[] bArr, int i) {
        byte b;
        if (bArr.length - i >= 2 && (b = bArr[i]) > 0) {
            byte b2 = bArr[i + 1];
            int i2 = i + 2;
            if (i2 < bArr.length) {
                Pdu pdu = new Pdu();
                int i3 = i + b;
                pdu.mEndIndex = i3;
                if (i3 >= bArr.length) {
                    pdu.mEndIndex = bArr.length - 1;
                }
                pdu.mType = b2;
                pdu.mDeclaredLength = b;
                pdu.mStartIndex = i2;
                pdu.mBytes = bArr;
                return pdu;
            }
        }
        return null;
    }

    public byte getType() {
        return this.mType;
    }

    public int getDeclaredLength() {
        return this.mDeclaredLength;
    }

    public int getStartIndex() {
        return this.mStartIndex;
    }

    public int getEndIndex() {
        return this.mEndIndex;
    }
}
