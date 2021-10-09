package com.mopub.common.util;

import android.graphics.Bitmap;
import com.mopub.mobileads.resource.DrawableConstants;

public class ImageUtils {
    public static Bitmap applyFastGaussianBlurToBitmap(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i2 = i; i2 >= 1; i2 /= 2) {
            for (int i3 = i2; i3 < height - i2; i3++) {
                int i4 = i2;
                while (i4 < width - i2) {
                    int i5 = ((i3 - i2) * width) + i4;
                    int i6 = iArr[i5 - i2];
                    int i7 = iArr[i5 + i2];
                    int i8 = iArr[i5];
                    int i9 = ((i3 + i2) * width) + i4;
                    int i10 = iArr[i9 - i2];
                    int i11 = iArr[i9 + i2];
                    int i12 = iArr[i9];
                    int i13 = (i3 * width) + i4;
                    int i14 = iArr[i13 - i2];
                    int i15 = iArr[i13 + i2];
                    iArr[i13] = ((((((((((i6 & 16711680) + (i7 & 16711680)) + (i8 & 16711680)) + (i10 & 16711680)) + (i11 & 16711680)) + (i12 & 16711680)) + (i14 & 16711680)) + (i15 & 16711680)) >> 3) & 16711680) | ((((((((((i6 & 255) + (i7 & 255)) + (i8 & 255)) + (i10 & 255)) + (i11 & 255)) + (i12 & 255)) + (i14 & 255)) + (i15 & 255)) >> 3) & 255) | DrawableConstants.CtaButton.BACKGROUND_COLOR | ((((((((((i6 & 65280) + (i7 & 65280)) + (i8 & 65280)) + (i10 & 65280)) + (i11 & 65280)) + (i12 & 65280)) + (i14 & 65280)) + (i15 & 65280)) >> 3) & 65280);
                    i4++;
                    height = height;
                }
                int i16 = height;
            }
            int i17 = height;
        }
        bitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
