package com.appnext.base.b;

import android.os.BaseBundle;
import android.os.PersistableBundle;

public final class a {
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004b A[Catch:{ all -> 0x00f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0011 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.os.PersistableBundle a(android.os.Bundle r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.os.PersistableBundle r1 = new android.os.PersistableBundle     // Catch:{ all -> 0x00f2 }
            r1.<init>()     // Catch:{ all -> 0x00f2 }
            java.util.Set r2 = r6.keySet()     // Catch:{ all -> 0x00f2 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00f2 }
        L_0x0011:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00f2 }
            if (r3 == 0) goto L_0x00f1
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x00f2 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x00f2 }
            java.lang.Object r4 = r6.get(r3)     // Catch:{ all -> 0x00f2 }
            boolean r5 = r4 instanceof android.os.PersistableBundle     // Catch:{ all -> 0x00f2 }
            if (r5 != 0) goto L_0x0048
            boolean r5 = r4 instanceof java.lang.Integer     // Catch:{ all -> 0x00f2 }
            if (r5 != 0) goto L_0x0048
            boolean r5 = r4 instanceof int[]     // Catch:{ all -> 0x00f2 }
            if (r5 != 0) goto L_0x0048
            boolean r5 = r4 instanceof java.lang.Long     // Catch:{ all -> 0x00f2 }
            if (r5 != 0) goto L_0x0048
            boolean r5 = r4 instanceof long[]     // Catch:{ all -> 0x00f2 }
            if (r5 != 0) goto L_0x0048
            boolean r5 = r4 instanceof java.lang.Double     // Catch:{ all -> 0x00f2 }
            if (r5 != 0) goto L_0x0048
            boolean r5 = r4 instanceof double[]     // Catch:{ all -> 0x00f2 }
            if (r5 != 0) goto L_0x0048
            boolean r5 = r4 instanceof java.lang.String     // Catch:{ all -> 0x00f2 }
            if (r5 != 0) goto L_0x0048
            boolean r5 = r4 instanceof java.lang.String[]     // Catch:{ all -> 0x00f2 }
            if (r5 == 0) goto L_0x0046
            goto L_0x0048
        L_0x0046:
            r5 = 0
            goto L_0x0049
        L_0x0048:
            r5 = 1
        L_0x0049:
            if (r5 == 0) goto L_0x0011
            if (r4 == 0) goto L_0x00e9
            boolean r5 = r4 instanceof java.lang.Integer     // Catch:{ all -> 0x00f2 }
            if (r5 == 0) goto L_0x005b
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ all -> 0x00f2 }
            int r4 = r4.intValue()     // Catch:{ all -> 0x00f2 }
            r1.putInt(r3, r4)     // Catch:{ all -> 0x00f2 }
            goto L_0x0011
        L_0x005b:
            boolean r5 = r4 instanceof int[]     // Catch:{ all -> 0x00f2 }
            if (r5 == 0) goto L_0x0067
            int[] r4 = (int[]) r4     // Catch:{ all -> 0x00f2 }
            int[] r4 = (int[]) r4     // Catch:{ all -> 0x00f2 }
            r1.putIntArray(r3, r4)     // Catch:{ all -> 0x00f2 }
            goto L_0x0011
        L_0x0067:
            boolean r5 = r4 instanceof java.lang.Long     // Catch:{ all -> 0x00f2 }
            if (r5 == 0) goto L_0x0075
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ all -> 0x00f2 }
            long r4 = r4.longValue()     // Catch:{ all -> 0x00f2 }
            r1.putLong(r3, r4)     // Catch:{ all -> 0x00f2 }
            goto L_0x0011
        L_0x0075:
            boolean r5 = r4 instanceof long[]     // Catch:{ all -> 0x00f2 }
            if (r5 == 0) goto L_0x0081
            long[] r4 = (long[]) r4     // Catch:{ all -> 0x00f2 }
            long[] r4 = (long[]) r4     // Catch:{ all -> 0x00f2 }
            r1.putLongArray(r3, r4)     // Catch:{ all -> 0x00f2 }
            goto L_0x0011
        L_0x0081:
            boolean r5 = r4 instanceof java.lang.Double     // Catch:{ all -> 0x00f2 }
            if (r5 == 0) goto L_0x008f
            java.lang.Double r4 = (java.lang.Double) r4     // Catch:{ all -> 0x00f2 }
            double r4 = r4.doubleValue()     // Catch:{ all -> 0x00f2 }
            r1.putDouble(r3, r4)     // Catch:{ all -> 0x00f2 }
            goto L_0x0011
        L_0x008f:
            boolean r5 = r4 instanceof double[]     // Catch:{ all -> 0x00f2 }
            if (r5 == 0) goto L_0x009c
            double[] r4 = (double[]) r4     // Catch:{ all -> 0x00f2 }
            double[] r4 = (double[]) r4     // Catch:{ all -> 0x00f2 }
            r1.putDoubleArray(r3, r4)     // Catch:{ all -> 0x00f2 }
            goto L_0x0011
        L_0x009c:
            boolean r5 = r4 instanceof java.lang.String     // Catch:{ all -> 0x00f2 }
            if (r5 == 0) goto L_0x00a7
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x00f2 }
            r1.putString(r3, r4)     // Catch:{ all -> 0x00f2 }
            goto L_0x0011
        L_0x00a7:
            boolean r5 = r4 instanceof java.lang.String[]     // Catch:{ all -> 0x00f2 }
            if (r5 == 0) goto L_0x00b4
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ all -> 0x00f2 }
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ all -> 0x00f2 }
            r1.putStringArray(r3, r4)     // Catch:{ all -> 0x00f2 }
            goto L_0x0011
        L_0x00b4:
            boolean r3 = r4 instanceof android.os.PersistableBundle     // Catch:{ all -> 0x00f2 }
            if (r3 == 0) goto L_0x00bf
            android.os.PersistableBundle r4 = (android.os.PersistableBundle) r4     // Catch:{ all -> 0x00f2 }
            r1.putAll(r4)     // Catch:{ all -> 0x00f2 }
            goto L_0x0011
        L_0x00bf:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00f2 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f2 }
            java.lang.String r2 = "Objects of type "
            r1.<init>(r2)     // Catch:{ all -> 0x00f2 }
            java.lang.Class r2 = r4.getClass()     // Catch:{ all -> 0x00f2 }
            java.lang.String r2 = r2.getSimpleName()     // Catch:{ all -> 0x00f2 }
            r1.append(r2)     // Catch:{ all -> 0x00f2 }
            java.lang.String r2 = " can not be put into a "
            r1.append(r2)     // Catch:{ all -> 0x00f2 }
            java.lang.Class<android.os.BaseBundle> r2 = android.os.BaseBundle.class
            java.lang.String r2 = r2.getSimpleName()     // Catch:{ all -> 0x00f2 }
            r1.append(r2)     // Catch:{ all -> 0x00f2 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00f2 }
            r6.<init>(r1)     // Catch:{ all -> 0x00f2 }
            throw r6     // Catch:{ all -> 0x00f2 }
        L_0x00e9:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00f2 }
            java.lang.String r1 = "Unable to determine type of null values"
            r6.<init>(r1)     // Catch:{ all -> 0x00f2 }
            throw r6     // Catch:{ all -> 0x00f2 }
        L_0x00f1:
            return r1
        L_0x00f2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.base.b.a.a(android.os.Bundle):android.os.PersistableBundle");
    }

    private static boolean b(Object obj) {
        return (obj instanceof PersistableBundle) || (obj instanceof Integer) || (obj instanceof int[]) || (obj instanceof Long) || (obj instanceof long[]) || (obj instanceof Double) || (obj instanceof double[]) || (obj instanceof String) || (obj instanceof String[]);
    }

    private static void a(PersistableBundle persistableBundle, String str, Object obj) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("Unable to determine type of null values");
        } else if (obj instanceof Integer) {
            persistableBundle.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof int[]) {
            persistableBundle.putIntArray(str, (int[]) obj);
        } else if (obj instanceof Long) {
            persistableBundle.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof long[]) {
            persistableBundle.putLongArray(str, (long[]) obj);
        } else if (obj instanceof Double) {
            persistableBundle.putDouble(str, ((Double) obj).doubleValue());
        } else if (obj instanceof double[]) {
            persistableBundle.putDoubleArray(str, (double[]) obj);
        } else if (obj instanceof String) {
            persistableBundle.putString(str, (String) obj);
        } else if (obj instanceof String[]) {
            persistableBundle.putStringArray(str, (String[]) obj);
        } else if (obj instanceof PersistableBundle) {
            persistableBundle.putAll((PersistableBundle) obj);
        } else {
            throw new IllegalArgumentException("Objects of type " + obj.getClass().getSimpleName() + " can not be put into a " + BaseBundle.class.getSimpleName());
        }
    }
}
