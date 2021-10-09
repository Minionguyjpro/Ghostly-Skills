package com.github.barteksc.pdfviewer.util;

import java.util.ArrayList;

public class ArrayUtils {
    public static int[] deleteDuplicatedPages(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        int i = -1;
        for (int valueOf : iArr) {
            Integer valueOf2 = Integer.valueOf(valueOf);
            if (i != valueOf2.intValue()) {
                arrayList.add(valueOf2);
            }
            i = valueOf2.intValue();
        }
        int[] iArr2 = new int[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    public static int[] calculateIndexesInDuplicateArray(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        if (iArr.length == 0) {
            return iArr2;
        }
        int i = 0;
        iArr2[0] = 0;
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (iArr[i2] != iArr[i2 - 1]) {
                i++;
            }
            iArr2[i2] = i;
        }
        return iArr2;
    }
}
