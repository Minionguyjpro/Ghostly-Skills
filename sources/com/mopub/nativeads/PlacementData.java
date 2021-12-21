package com.mopub.nativeads;

import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import java.util.List;

class PlacementData {
    private static final int MAX_ADS = 200;
    public static final int NOT_FOUND = -1;
    private final int[] mAdjustedAdPositions = new int[MAX_ADS];
    private int mDesiredCount = 0;
    private final int[] mDesiredInsertionPositions = new int[MAX_ADS];
    private final int[] mDesiredOriginalPositions = new int[MAX_ADS];
    private final NativeAd[] mNativeAds = new NativeAd[MAX_ADS];
    private final int[] mOriginalAdPositions = new int[MAX_ADS];
    private int mPlacedCount = 0;

    private PlacementData(int[] iArr) {
        int min = Math.min(iArr.length, MAX_ADS);
        this.mDesiredCount = min;
        System.arraycopy(iArr, 0, this.mDesiredInsertionPositions, 0, min);
        System.arraycopy(iArr, 0, this.mDesiredOriginalPositions, 0, this.mDesiredCount);
    }

    static PlacementData fromAdPositioning(MoPubNativeAdPositioning.MoPubClientPositioning moPubClientPositioning) {
        List<Integer> fixedPositions = moPubClientPositioning.getFixedPositions();
        int repeatingInterval = moPubClientPositioning.getRepeatingInterval();
        int size = repeatingInterval == Integer.MAX_VALUE ? fixedPositions.size() : MAX_ADS;
        int[] iArr = new int[size];
        int i = 0;
        int i2 = 0;
        for (Integer intValue : fixedPositions) {
            i2 = intValue.intValue() - i;
            iArr[i] = i2;
            i++;
        }
        while (i < size) {
            i2 = (i2 + repeatingInterval) - 1;
            iArr[i] = i2;
            i++;
        }
        return new PlacementData(iArr);
    }

    static PlacementData empty() {
        return new PlacementData(new int[0]);
    }

    /* access modifiers changed from: package-private */
    public boolean shouldPlaceAd(int i) {
        return binarySearch(this.mDesiredInsertionPositions, 0, this.mDesiredCount, i) >= 0;
    }

    /* access modifiers changed from: package-private */
    public int nextInsertionPosition(int i) {
        int binarySearchGreaterThan = binarySearchGreaterThan(this.mDesiredInsertionPositions, this.mDesiredCount, i);
        if (binarySearchGreaterThan == this.mDesiredCount) {
            return -1;
        }
        return this.mDesiredInsertionPositions[binarySearchGreaterThan];
    }

    /* access modifiers changed from: package-private */
    public int previousInsertionPosition(int i) {
        int binarySearchFirstEquals = binarySearchFirstEquals(this.mDesiredInsertionPositions, this.mDesiredCount, i);
        if (binarySearchFirstEquals == 0) {
            return -1;
        }
        return this.mDesiredInsertionPositions[binarySearchFirstEquals - 1];
    }

    /* access modifiers changed from: package-private */
    public void placeAd(int i, NativeAd nativeAd) {
        int binarySearchFirstEquals = binarySearchFirstEquals(this.mDesiredInsertionPositions, this.mDesiredCount, i);
        if (binarySearchFirstEquals == this.mDesiredCount || this.mDesiredInsertionPositions[binarySearchFirstEquals] != i) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Attempted to insert an ad at an invalid position");
            return;
        }
        int i2 = this.mDesiredOriginalPositions[binarySearchFirstEquals];
        int binarySearchGreaterThan = binarySearchGreaterThan(this.mOriginalAdPositions, this.mPlacedCount, i2);
        int i3 = this.mPlacedCount;
        if (binarySearchGreaterThan < i3) {
            int i4 = i3 - binarySearchGreaterThan;
            int[] iArr = this.mOriginalAdPositions;
            int i5 = binarySearchGreaterThan + 1;
            System.arraycopy(iArr, binarySearchGreaterThan, iArr, i5, i4);
            int[] iArr2 = this.mAdjustedAdPositions;
            System.arraycopy(iArr2, binarySearchGreaterThan, iArr2, i5, i4);
            NativeAd[] nativeAdArr = this.mNativeAds;
            System.arraycopy(nativeAdArr, binarySearchGreaterThan, nativeAdArr, i5, i4);
        }
        this.mOriginalAdPositions[binarySearchGreaterThan] = i2;
        this.mAdjustedAdPositions[binarySearchGreaterThan] = i;
        this.mNativeAds[binarySearchGreaterThan] = nativeAd;
        this.mPlacedCount++;
        int i6 = (this.mDesiredCount - binarySearchFirstEquals) - 1;
        int[] iArr3 = this.mDesiredInsertionPositions;
        int i7 = binarySearchFirstEquals + 1;
        System.arraycopy(iArr3, i7, iArr3, binarySearchFirstEquals, i6);
        int[] iArr4 = this.mDesiredOriginalPositions;
        System.arraycopy(iArr4, i7, iArr4, binarySearchFirstEquals, i6);
        this.mDesiredCount--;
        while (binarySearchFirstEquals < this.mDesiredCount) {
            int[] iArr5 = this.mDesiredInsertionPositions;
            iArr5[binarySearchFirstEquals] = iArr5[binarySearchFirstEquals] + 1;
            binarySearchFirstEquals++;
        }
        for (int i8 = binarySearchGreaterThan + 1; i8 < this.mPlacedCount; i8++) {
            int[] iArr6 = this.mAdjustedAdPositions;
            iArr6[i8] = iArr6[i8] + 1;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isPlacedAd(int i) {
        return binarySearch(this.mAdjustedAdPositions, 0, this.mPlacedCount, i) >= 0;
    }

    /* access modifiers changed from: package-private */
    public NativeAd getPlacedAd(int i) {
        int binarySearch = binarySearch(this.mAdjustedAdPositions, 0, this.mPlacedCount, i);
        if (binarySearch < 0) {
            return null;
        }
        return this.mNativeAds[binarySearch];
    }

    /* access modifiers changed from: package-private */
    public int[] getPlacedAdPositions() {
        int i = this.mPlacedCount;
        int[] iArr = new int[i];
        System.arraycopy(this.mAdjustedAdPositions, 0, iArr, 0, i);
        return iArr;
    }

    /* access modifiers changed from: package-private */
    public int getOriginalPosition(int i) {
        int binarySearch = binarySearch(this.mAdjustedAdPositions, 0, this.mPlacedCount, i);
        if (binarySearch < 0) {
            return i - (binarySearch ^ -1);
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int getAdjustedPosition(int i) {
        return i + binarySearchGreaterThan(this.mOriginalAdPositions, this.mPlacedCount, i);
    }

    /* access modifiers changed from: package-private */
    public int getOriginalCount(int i) {
        if (i == 0) {
            return 0;
        }
        int originalPosition = getOriginalPosition(i - 1);
        if (originalPosition == -1) {
            return -1;
        }
        return originalPosition + 1;
    }

    /* access modifiers changed from: package-private */
    public int getAdjustedCount(int i) {
        if (i == 0) {
            return 0;
        }
        return getAdjustedPosition(i - 1) + 1;
    }

    /* access modifiers changed from: package-private */
    public int clearAdsInRange(int i, int i2) {
        int i3 = this.mPlacedCount;
        int[] iArr = new int[i3];
        int[] iArr2 = new int[i3];
        int i4 = 0;
        for (int i5 = 0; i5 < this.mPlacedCount; i5++) {
            int i6 = this.mOriginalAdPositions[i5];
            int i7 = this.mAdjustedAdPositions[i5];
            if (i <= i7 && i7 < i2) {
                iArr[i4] = i6;
                iArr2[i4] = i7 - i4;
                this.mNativeAds[i5].destroy();
                this.mNativeAds[i5] = null;
                i4++;
            } else if (i4 > 0) {
                int i8 = i5 - i4;
                this.mOriginalAdPositions[i8] = i6;
                this.mAdjustedAdPositions[i8] = i7 - i4;
                NativeAd[] nativeAdArr = this.mNativeAds;
                nativeAdArr[i8] = nativeAdArr[i5];
            }
        }
        if (i4 == 0) {
            return 0;
        }
        int binarySearchFirstEquals = binarySearchFirstEquals(this.mDesiredInsertionPositions, this.mDesiredCount, iArr2[0]);
        for (int i9 = this.mDesiredCount - 1; i9 >= binarySearchFirstEquals; i9--) {
            int[] iArr3 = this.mDesiredOriginalPositions;
            int i10 = i9 + i4;
            iArr3[i10] = iArr3[i9];
            int[] iArr4 = this.mDesiredInsertionPositions;
            iArr4[i10] = iArr4[i9] - i4;
        }
        for (int i11 = 0; i11 < i4; i11++) {
            int i12 = binarySearchFirstEquals + i11;
            this.mDesiredOriginalPositions[i12] = iArr[i11];
            this.mDesiredInsertionPositions[i12] = iArr2[i11];
        }
        this.mDesiredCount += i4;
        this.mPlacedCount -= i4;
        return i4;
    }

    /* access modifiers changed from: package-private */
    public void clearAds() {
        int i = this.mPlacedCount;
        if (i != 0) {
            clearAdsInRange(0, this.mAdjustedAdPositions[i - 1] + 1);
        }
    }

    /* access modifiers changed from: package-private */
    public void insertItem(int i) {
        for (int binarySearchFirstEquals = binarySearchFirstEquals(this.mDesiredOriginalPositions, this.mDesiredCount, i); binarySearchFirstEquals < this.mDesiredCount; binarySearchFirstEquals++) {
            int[] iArr = this.mDesiredOriginalPositions;
            iArr[binarySearchFirstEquals] = iArr[binarySearchFirstEquals] + 1;
            int[] iArr2 = this.mDesiredInsertionPositions;
            iArr2[binarySearchFirstEquals] = iArr2[binarySearchFirstEquals] + 1;
        }
        for (int binarySearchFirstEquals2 = binarySearchFirstEquals(this.mOriginalAdPositions, this.mPlacedCount, i); binarySearchFirstEquals2 < this.mPlacedCount; binarySearchFirstEquals2++) {
            int[] iArr3 = this.mOriginalAdPositions;
            iArr3[binarySearchFirstEquals2] = iArr3[binarySearchFirstEquals2] + 1;
            int[] iArr4 = this.mAdjustedAdPositions;
            iArr4[binarySearchFirstEquals2] = iArr4[binarySearchFirstEquals2] + 1;
        }
    }

    /* access modifiers changed from: package-private */
    public void removeItem(int i) {
        for (int binarySearchGreaterThan = binarySearchGreaterThan(this.mDesiredOriginalPositions, this.mDesiredCount, i); binarySearchGreaterThan < this.mDesiredCount; binarySearchGreaterThan++) {
            int[] iArr = this.mDesiredOriginalPositions;
            iArr[binarySearchGreaterThan] = iArr[binarySearchGreaterThan] - 1;
            int[] iArr2 = this.mDesiredInsertionPositions;
            iArr2[binarySearchGreaterThan] = iArr2[binarySearchGreaterThan] - 1;
        }
        for (int binarySearchGreaterThan2 = binarySearchGreaterThan(this.mOriginalAdPositions, this.mPlacedCount, i); binarySearchGreaterThan2 < this.mPlacedCount; binarySearchGreaterThan2++) {
            int[] iArr3 = this.mOriginalAdPositions;
            iArr3[binarySearchGreaterThan2] = iArr3[binarySearchGreaterThan2] - 1;
            int[] iArr4 = this.mAdjustedAdPositions;
            iArr4[binarySearchGreaterThan2] = iArr4[binarySearchGreaterThan2] - 1;
        }
    }

    /* access modifiers changed from: package-private */
    public void moveItem(int i, int i2) {
        removeItem(i);
        insertItem(i2);
    }

    private static int binarySearchFirstEquals(int[] iArr, int i, int i2) {
        int binarySearch = binarySearch(iArr, 0, i, i2);
        if (binarySearch < 0) {
            return binarySearch ^ -1;
        }
        int i3 = iArr[binarySearch];
        while (binarySearch >= 0 && iArr[binarySearch] == i3) {
            binarySearch--;
        }
        return binarySearch + 1;
    }

    private static int binarySearchGreaterThan(int[] iArr, int i, int i2) {
        int binarySearch = binarySearch(iArr, 0, i, i2);
        if (binarySearch < 0) {
            return binarySearch ^ -1;
        }
        int i3 = iArr[binarySearch];
        while (binarySearch < i && iArr[binarySearch] == i3) {
            binarySearch++;
        }
        return binarySearch;
    }

    private static int binarySearch(int[] iArr, int i, int i2, int i3) {
        int i4 = i2 - 1;
        while (i <= i4) {
            int i5 = (i + i4) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i3) {
                i = i5 + 1;
            } else if (i6 <= i3) {
                return i5;
            } else {
                i4 = i5 - 1;
            }
        }
        return i ^ -1;
    }
}
