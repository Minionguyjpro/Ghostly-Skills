package com.google.android.play.core.assetpacks;

import com.google.android.play.core.common.a;
import com.google.android.play.core.internal.ag;

final class cd {

    /* renamed from: a  reason: collision with root package name */
    private static final ag f1066a = new ag("ExtractorTaskFinder");
    private final ca b;
    private final au c;
    private final bc d;
    private final a e;

    cd(ca caVar, au auVar, bc bcVar, a aVar) {
        this.b = caVar;
        this.c = auVar;
        this.d = bcVar;
        this.e = aVar;
    }

    private final boolean b(bx bxVar, by byVar) {
        au auVar = this.c;
        bw bwVar = bxVar.c;
        return new cz(auVar, bwVar.f1061a, bxVar.b, bwVar.b, byVar.f1063a).l();
    }

    private static boolean c(by byVar) {
        int i = byVar.f;
        return i == 1 || i == 2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: com.google.android.play.core.assetpacks.cq} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.android.play.core.assetpacks.cq} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: com.google.android.play.core.assetpacks.cn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: com.google.android.play.core.assetpacks.cn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: com.google.android.play.core.assetpacks.dc} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: com.google.android.play.core.assetpacks.dc} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: com.google.android.play.core.assetpacks.cw} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: com.google.android.play.core.assetpacks.cw} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v51, resolved type: com.google.android.play.core.assetpacks.cc} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v52, resolved type: com.google.android.play.core.assetpacks.cc} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: com.google.android.play.core.assetpacks.cw} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v5, resolved type: com.google.android.play.core.assetpacks.dc} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v6, resolved type: com.google.android.play.core.assetpacks.cn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v16, resolved type: com.google.android.play.core.assetpacks.cq} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v56, resolved type: com.google.android.play.core.assetpacks.cc} */
    /* JADX WARNING: type inference failed for: r0v48, types: [com.google.android.play.core.assetpacks.cc] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a7, code lost:
        if (r0 == null) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        f1066a.a("Found final move task for session %s with pack %s.", java.lang.Integer.valueOf(r7.f1062a), r7.c.f1061a);
        r11 = r7.f1062a;
        r8 = r7.c;
        r10 = new com.google.android.play.core.assetpacks.cq(r11, r8.f1061a, r7.b, r8.b);
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.play.core.assetpacks.cc a() {
        /*
            r32 = this;
            r1 = r32
            com.google.android.play.core.assetpacks.ca r0 = r1.b     // Catch:{ all -> 0x03e6 }
            r0.a()     // Catch:{ all -> 0x03e6 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x03e6 }
            r2.<init>()     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.ca r0 = r1.b     // Catch:{ all -> 0x03e6 }
            java.util.Map r0 = r0.c()     // Catch:{ all -> 0x03e6 }
            java.util.Collection r0 = r0.values()     // Catch:{ all -> 0x03e6 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x03e6 }
        L_0x001a:
            boolean r3 = r0.hasNext()     // Catch:{ all -> 0x03e6 }
            if (r3 == 0) goto L_0x0034
            java.lang.Object r3 = r0.next()     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bx r3 = (com.google.android.play.core.assetpacks.bx) r3     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r4 = r3.c     // Catch:{ all -> 0x03e6 }
            int r4 = r4.c     // Catch:{ all -> 0x03e6 }
            boolean r4 = com.google.android.play.core.assetpacks.ck.h(r4)     // Catch:{ all -> 0x03e6 }
            if (r4 == 0) goto L_0x001a
            r2.add(r3)     // Catch:{ all -> 0x03e6 }
            goto L_0x001a
        L_0x0034:
            boolean r0 = r2.isEmpty()     // Catch:{ all -> 0x03e6 }
            r3 = 0
            if (r0 == 0) goto L_0x0041
        L_0x003b:
            com.google.android.play.core.assetpacks.ca r0 = r1.b
            r0.b()
            return r3
        L_0x0041:
            com.google.android.play.core.common.a r0 = r1.e     // Catch:{ all -> 0x03e6 }
            boolean r0 = r0.a()     // Catch:{ all -> 0x03e6 }
            r4 = 2
            r5 = 1
            r6 = 0
            if (r0 == 0) goto L_0x00b0
            com.google.android.play.core.assetpacks.au r0 = r1.c     // Catch:{ all -> 0x03e6 }
            java.util.Map r0 = r0.c()     // Catch:{ all -> 0x03e6 }
            java.util.Iterator r7 = r2.iterator()     // Catch:{ all -> 0x03e6 }
        L_0x0056:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x03e6 }
            if (r8 == 0) goto L_0x00a6
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bx r8 = (com.google.android.play.core.assetpacks.bx) r8     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r9 = r8.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = r9.f1061a     // Catch:{ all -> 0x03e6 }
            java.lang.Object r9 = r0.get(r9)     // Catch:{ all -> 0x03e6 }
            java.lang.Long r9 = (java.lang.Long) r9     // Catch:{ all -> 0x03e6 }
            if (r9 == 0) goto L_0x0056
            com.google.android.play.core.assetpacks.bw r10 = r8.c     // Catch:{ all -> 0x03e6 }
            long r10 = r10.b     // Catch:{ all -> 0x03e6 }
            long r12 = r9.longValue()     // Catch:{ all -> 0x03e6 }
            int r9 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r9 != 0) goto L_0x0056
            com.google.android.play.core.internal.ag r0 = f1066a     // Catch:{ all -> 0x03e6 }
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ all -> 0x03e6 }
            int r9 = r8.f1062a     // Catch:{ all -> 0x03e6 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x03e6 }
            r7[r6] = r9     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r9 = r8.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = r9.f1061a     // Catch:{ all -> 0x03e6 }
            r7[r5] = r9     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = "Found promote pack task for session %s with pack %s."
            r0.a(r9, r7)     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.cc r0 = new com.google.android.play.core.assetpacks.cc     // Catch:{ all -> 0x03e6 }
            int r7 = r8.f1062a     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r9 = r8.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = r9.f1061a     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.au r10 = r1.c     // Catch:{ all -> 0x03e6 }
            r10.t(r9)     // Catch:{ all -> 0x03e6 }
            int r10 = r8.b     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r8 = r8.c     // Catch:{ all -> 0x03e6 }
            r0.<init>(r7, r9, r3)     // Catch:{ all -> 0x03e6 }
            goto L_0x00a7
        L_0x00a6:
            r0 = r3
        L_0x00a7:
            if (r0 != 0) goto L_0x00aa
            goto L_0x00b0
        L_0x00aa:
            com.google.android.play.core.assetpacks.ca r2 = r1.b
            r2.b()
            return r0
        L_0x00b0:
            java.util.Iterator r0 = r2.iterator()     // Catch:{ all -> 0x03e6 }
        L_0x00b4:
            boolean r7 = r0.hasNext()     // Catch:{ all -> 0x03e6 }
            if (r7 == 0) goto L_0x011f
            java.lang.Object r7 = r0.next()     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bx r7 = (com.google.android.play.core.assetpacks.bx) r7     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.au r8 = r1.c     // Catch:{ IOException -> 0x0100 }
            com.google.android.play.core.assetpacks.bw r9 = r7.c     // Catch:{ IOException -> 0x0100 }
            java.lang.String r10 = r9.f1061a     // Catch:{ IOException -> 0x0100 }
            int r11 = r7.b     // Catch:{ IOException -> 0x0100 }
            long r12 = r9.b     // Catch:{ IOException -> 0x0100 }
            int r8 = r8.k(r10, r11, r12)     // Catch:{ IOException -> 0x0100 }
            com.google.android.play.core.assetpacks.bw r9 = r7.c     // Catch:{ IOException -> 0x0100 }
            java.util.List<com.google.android.play.core.assetpacks.by> r9 = r9.e     // Catch:{ IOException -> 0x0100 }
            int r9 = r9.size()     // Catch:{ IOException -> 0x0100 }
            if (r8 != r9) goto L_0x00b4
            com.google.android.play.core.internal.ag r0 = f1066a     // Catch:{ all -> 0x03e6 }
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ all -> 0x03e6 }
            int r9 = r7.f1062a     // Catch:{ all -> 0x03e6 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x03e6 }
            r8[r6] = r9     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r9 = r7.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = r9.f1061a     // Catch:{ all -> 0x03e6 }
            r8[r5] = r9     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = "Found final move task for session %s with pack %s."
            r0.a(r9, r8)     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.cq r0 = new com.google.android.play.core.assetpacks.cq     // Catch:{ all -> 0x03e6 }
            int r11 = r7.f1062a     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r8 = r7.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r12 = r8.f1061a     // Catch:{ all -> 0x03e6 }
            int r13 = r7.b     // Catch:{ all -> 0x03e6 }
            long r14 = r8.b     // Catch:{ all -> 0x03e6 }
            r10 = r0
            r10.<init>(r11, r12, r13, r14)     // Catch:{ all -> 0x03e6 }
            goto L_0x0120
        L_0x0100:
            r0 = move-exception
            com.google.android.play.core.assetpacks.bk r2 = new com.google.android.play.core.assetpacks.bk     // Catch:{ all -> 0x03e6 }
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ all -> 0x03e6 }
            int r4 = r7.f1062a     // Catch:{ all -> 0x03e6 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x03e6 }
            r3[r6] = r4     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r4 = r7.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r4 = r4.f1061a     // Catch:{ all -> 0x03e6 }
            r3[r5] = r4     // Catch:{ all -> 0x03e6 }
            java.lang.String r4 = "Failed to check number of completed merges for session %s, pack %s"
            java.lang.String r3 = java.lang.String.format(r4, r3)     // Catch:{ all -> 0x03e6 }
            int r4 = r7.f1062a     // Catch:{ all -> 0x03e6 }
            r2.<init>(r3, r0, r4)     // Catch:{ all -> 0x03e6 }
            throw r2     // Catch:{ all -> 0x03e6 }
        L_0x011f:
            r0 = r3
        L_0x0120:
            if (r0 != 0) goto L_0x00aa
            java.util.Iterator r0 = r2.iterator()     // Catch:{ all -> 0x03e6 }
        L_0x0126:
            boolean r7 = r0.hasNext()     // Catch:{ all -> 0x03e6 }
            r8 = 3
            if (r7 == 0) goto L_0x01a9
            java.lang.Object r7 = r0.next()     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bx r7 = (com.google.android.play.core.assetpacks.bx) r7     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r9 = r7.c     // Catch:{ all -> 0x03e6 }
            int r9 = r9.c     // Catch:{ all -> 0x03e6 }
            boolean r9 = com.google.android.play.core.assetpacks.ck.h(r9)     // Catch:{ all -> 0x03e6 }
            if (r9 == 0) goto L_0x0126
            com.google.android.play.core.assetpacks.bw r9 = r7.c     // Catch:{ all -> 0x03e6 }
            java.util.List<com.google.android.play.core.assetpacks.by> r9 = r9.e     // Catch:{ all -> 0x03e6 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x03e6 }
        L_0x0145:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x03e6 }
            if (r10 == 0) goto L_0x0126
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.by r10 = (com.google.android.play.core.assetpacks.by) r10     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.au r11 = r1.c     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r12 = r7.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r13 = r12.f1061a     // Catch:{ all -> 0x03e6 }
            int r14 = r7.b     // Catch:{ all -> 0x03e6 }
            long r3 = r12.b     // Catch:{ all -> 0x03e6 }
            java.lang.String r15 = r10.f1063a     // Catch:{ all -> 0x03e6 }
            r12 = r13
            r13 = r14
            r16 = r15
            r14 = r3
            java.io.File r3 = r11.i(r12, r13, r14, r16)     // Catch:{ all -> 0x03e6 }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x03e6 }
            if (r3 == 0) goto L_0x01a6
            com.google.android.play.core.internal.ag r0 = f1066a     // Catch:{ all -> 0x03e6 }
            java.lang.Object[] r3 = new java.lang.Object[r8]     // Catch:{ all -> 0x03e6 }
            int r4 = r7.f1062a     // Catch:{ all -> 0x03e6 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x03e6 }
            r3[r6] = r4     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r4 = r7.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r4 = r4.f1061a     // Catch:{ all -> 0x03e6 }
            r3[r5] = r4     // Catch:{ all -> 0x03e6 }
            java.lang.String r4 = r10.f1063a     // Catch:{ all -> 0x03e6 }
            r9 = 2
            r3[r9] = r4     // Catch:{ all -> 0x03e6 }
            java.lang.String r4 = "Found merge task for session %s with pack %s and slice %s."
            r0.a(r4, r3)     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.cn r0 = new com.google.android.play.core.assetpacks.cn     // Catch:{ all -> 0x03e6 }
            int r3 = r7.f1062a     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r4 = r7.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = r4.f1061a     // Catch:{ all -> 0x03e6 }
            int r7 = r7.b     // Catch:{ all -> 0x03e6 }
            long r11 = r4.b     // Catch:{ all -> 0x03e6 }
            java.lang.String r4 = r10.f1063a     // Catch:{ all -> 0x03e6 }
            r18 = r0
            r19 = r3
            r20 = r9
            r21 = r7
            r22 = r11
            r24 = r4
            r18.<init>(r19, r20, r21, r22, r24)     // Catch:{ all -> 0x03e6 }
            goto L_0x01aa
        L_0x01a6:
            r3 = 0
            r4 = 2
            goto L_0x0145
        L_0x01a9:
            r0 = 0
        L_0x01aa:
            if (r0 != 0) goto L_0x00aa
            java.util.Iterator r0 = r2.iterator()     // Catch:{ all -> 0x03e6 }
        L_0x01b0:
            boolean r3 = r0.hasNext()     // Catch:{ all -> 0x03e6 }
            if (r3 == 0) goto L_0x0236
            java.lang.Object r3 = r0.next()     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bx r3 = (com.google.android.play.core.assetpacks.bx) r3     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r4 = r3.c     // Catch:{ all -> 0x03e6 }
            int r4 = r4.c     // Catch:{ all -> 0x03e6 }
            boolean r4 = com.google.android.play.core.assetpacks.ck.h(r4)     // Catch:{ all -> 0x03e6 }
            if (r4 == 0) goto L_0x01b0
            com.google.android.play.core.assetpacks.bw r4 = r3.c     // Catch:{ all -> 0x03e6 }
            java.util.List<com.google.android.play.core.assetpacks.by> r4 = r4.e     // Catch:{ all -> 0x03e6 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x03e6 }
        L_0x01ce:
            boolean r7 = r4.hasNext()     // Catch:{ all -> 0x03e6 }
            if (r7 == 0) goto L_0x01b0
            java.lang.Object r7 = r4.next()     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.by r7 = (com.google.android.play.core.assetpacks.by) r7     // Catch:{ all -> 0x03e6 }
            boolean r9 = r1.b(r3, r7)     // Catch:{ all -> 0x03e6 }
            if (r9 == 0) goto L_0x01ce
            com.google.android.play.core.assetpacks.au r10 = r1.c     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r9 = r3.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r11 = r9.f1061a     // Catch:{ all -> 0x03e6 }
            int r12 = r3.b     // Catch:{ all -> 0x03e6 }
            long r13 = r9.b     // Catch:{ all -> 0x03e6 }
            java.lang.String r15 = r7.f1063a     // Catch:{ all -> 0x03e6 }
            java.io.File r9 = r10.h(r11, r12, r13, r15)     // Catch:{ all -> 0x03e6 }
            boolean r9 = r9.exists()     // Catch:{ all -> 0x03e6 }
            if (r9 == 0) goto L_0x01ce
            com.google.android.play.core.internal.ag r0 = f1066a     // Catch:{ all -> 0x03e6 }
            java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ all -> 0x03e6 }
            int r9 = r3.f1062a     // Catch:{ all -> 0x03e6 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x03e6 }
            r4[r6] = r9     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r9 = r3.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = r9.f1061a     // Catch:{ all -> 0x03e6 }
            r4[r5] = r9     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = r7.f1063a     // Catch:{ all -> 0x03e6 }
            r10 = 2
            r4[r10] = r9     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = "Found verify task for session %s with pack %s and slice %s."
            r0.a(r9, r4)     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.dc r0 = new com.google.android.play.core.assetpacks.dc     // Catch:{ all -> 0x03e6 }
            int r4 = r3.f1062a     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r9 = r3.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r10 = r9.f1061a     // Catch:{ all -> 0x03e6 }
            int r3 = r3.b     // Catch:{ all -> 0x03e6 }
            long r11 = r9.b     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = r7.f1063a     // Catch:{ all -> 0x03e6 }
            java.lang.String r13 = r7.b     // Catch:{ all -> 0x03e6 }
            long r14 = r7.c     // Catch:{ all -> 0x03e6 }
            r18 = r0
            r19 = r4
            r20 = r10
            r21 = r3
            r22 = r11
            r24 = r9
            r25 = r13
            r18.<init>(r19, r20, r21, r22, r24, r25)     // Catch:{ all -> 0x03e6 }
            goto L_0x0237
        L_0x0236:
            r0 = 0
        L_0x0237:
            if (r0 != 0) goto L_0x00aa
            java.util.Iterator r3 = r2.iterator()     // Catch:{ all -> 0x03e6 }
        L_0x023d:
            boolean r0 = r3.hasNext()     // Catch:{ all -> 0x03e6 }
            r4 = 4
            if (r0 == 0) goto L_0x0324
            java.lang.Object r0 = r3.next()     // Catch:{ all -> 0x03e6 }
            r7 = r0
            com.google.android.play.core.assetpacks.bx r7 = (com.google.android.play.core.assetpacks.bx) r7     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r0 = r7.c     // Catch:{ all -> 0x03e6 }
            int r0 = r0.c     // Catch:{ all -> 0x03e6 }
            boolean r0 = com.google.android.play.core.assetpacks.ck.h(r0)     // Catch:{ all -> 0x03e6 }
            if (r0 == 0) goto L_0x023d
            com.google.android.play.core.assetpacks.bw r0 = r7.c     // Catch:{ all -> 0x03e6 }
            java.util.List<com.google.android.play.core.assetpacks.by> r0 = r0.e     // Catch:{ all -> 0x03e6 }
            java.util.Iterator r9 = r0.iterator()     // Catch:{ all -> 0x03e6 }
        L_0x025d:
            boolean r0 = r9.hasNext()     // Catch:{ all -> 0x03e6 }
            if (r0 == 0) goto L_0x023d
            java.lang.Object r0 = r9.next()     // Catch:{ all -> 0x03e6 }
            r10 = r0
            com.google.android.play.core.assetpacks.by r10 = (com.google.android.play.core.assetpacks.by) r10     // Catch:{ all -> 0x03e6 }
            boolean r0 = c(r10)     // Catch:{ all -> 0x03e6 }
            if (r0 != 0) goto L_0x025d
            com.google.android.play.core.assetpacks.cz r0 = new com.google.android.play.core.assetpacks.cz     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.au r11 = r1.c     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r12 = r7.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r13 = r12.f1061a     // Catch:{ all -> 0x03e6 }
            int r14 = r7.b     // Catch:{ all -> 0x03e6 }
            r16 = r9
            long r8 = r12.b     // Catch:{ all -> 0x03e6 }
            java.lang.String r12 = r10.f1063a     // Catch:{ all -> 0x03e6 }
            r18 = r0
            r19 = r11
            r20 = r13
            r21 = r14
            r22 = r8
            r24 = r12
            r18.<init>(r19, r20, r21, r22, r24)     // Catch:{ all -> 0x03e6 }
            int r0 = r0.k()     // Catch:{ IOException -> 0x0294 }
            goto L_0x02a2
        L_0x0294:
            r0 = move-exception
            r8 = r0
            com.google.android.play.core.internal.ag r0 = f1066a     // Catch:{ all -> 0x03e6 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ all -> 0x03e6 }
            r9[r6] = r8     // Catch:{ all -> 0x03e6 }
            java.lang.String r8 = "Slice checkpoint corrupt, restarting extraction. %s"
            r0.b(r8, r9)     // Catch:{ all -> 0x03e6 }
            r0 = 0
        L_0x02a2:
            r8 = -1
            if (r0 == r8) goto L_0x031f
            java.util.List<com.google.android.play.core.assetpacks.bv> r8 = r10.d     // Catch:{ all -> 0x03e6 }
            java.lang.Object r8 = r8.get(r0)     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bv r8 = (com.google.android.play.core.assetpacks.bv) r8     // Catch:{ all -> 0x03e6 }
            boolean r8 = r8.f1060a     // Catch:{ all -> 0x03e6 }
            if (r8 == 0) goto L_0x031f
            com.google.android.play.core.internal.ag r3 = f1066a     // Catch:{ all -> 0x03e6 }
            r8 = 5
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x03e6 }
            int r9 = r10.e     // Catch:{ all -> 0x03e6 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x03e6 }
            r8[r6] = r9     // Catch:{ all -> 0x03e6 }
            int r9 = r7.f1062a     // Catch:{ all -> 0x03e6 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x03e6 }
            r8[r5] = r9     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r9 = r7.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = r9.f1061a     // Catch:{ all -> 0x03e6 }
            r11 = 2
            r8[r11] = r9     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = r10.f1063a     // Catch:{ all -> 0x03e6 }
            r11 = 3
            r8[r11] = r9     // Catch:{ all -> 0x03e6 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x03e6 }
            r8[r4] = r9     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = "Found extraction task using compression format %s for session %s, pack %s, slice %s, chunk %s."
            r3.a(r9, r8)     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bc r3 = r1.d     // Catch:{ all -> 0x03e6 }
            int r8 = r7.f1062a     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r9 = r7.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = r9.f1061a     // Catch:{ all -> 0x03e6 }
            java.lang.String r11 = r10.f1063a     // Catch:{ all -> 0x03e6 }
            java.io.InputStream r31 = r3.a(r8, r9, r11, r0)     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bi r3 = new com.google.android.play.core.assetpacks.bi     // Catch:{ all -> 0x03e6 }
            int r8 = r7.f1062a     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r9 = r7.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r11 = r9.f1061a     // Catch:{ all -> 0x03e6 }
            int r12 = r7.b     // Catch:{ all -> 0x03e6 }
            long r13 = r9.b     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = r10.f1063a     // Catch:{ all -> 0x03e6 }
            int r15 = r10.e     // Catch:{ all -> 0x03e6 }
            java.util.List<com.google.android.play.core.assetpacks.bv> r10 = r10.d     // Catch:{ all -> 0x03e6 }
            int r27 = r10.size()     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r7 = r7.c     // Catch:{ all -> 0x03e6 }
            long r4 = r7.d     // Catch:{ all -> 0x03e6 }
            int r7 = r7.c     // Catch:{ all -> 0x03e6 }
            r18 = r3
            r19 = r8
            r20 = r11
            r21 = r12
            r22 = r13
            r24 = r9
            r25 = r15
            r26 = r0
            r28 = r4
            r30 = r7
            r18.<init>(r19, r20, r21, r22, r24, r25, r26, r27, r28, r30, r31)     // Catch:{ all -> 0x03e6 }
            goto L_0x0325
        L_0x031f:
            r9 = r16
            r8 = 3
            goto L_0x025d
        L_0x0324:
            r3 = 0
        L_0x0325:
            if (r3 != 0) goto L_0x003b
            java.util.Iterator r0 = r2.iterator()     // Catch:{ all -> 0x03e6 }
        L_0x032b:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x03e6 }
            if (r2 == 0) goto L_0x03da
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bx r2 = (com.google.android.play.core.assetpacks.bx) r2     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r3 = r2.c     // Catch:{ all -> 0x03e6 }
            int r3 = r3.c     // Catch:{ all -> 0x03e6 }
            boolean r3 = com.google.android.play.core.assetpacks.ck.h(r3)     // Catch:{ all -> 0x03e6 }
            if (r3 == 0) goto L_0x032b
            com.google.android.play.core.assetpacks.bw r3 = r2.c     // Catch:{ all -> 0x03e6 }
            java.util.List<com.google.android.play.core.assetpacks.by> r3 = r3.e     // Catch:{ all -> 0x03e6 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x03e6 }
        L_0x0349:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x03e6 }
            if (r4 == 0) goto L_0x032b
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.by r4 = (com.google.android.play.core.assetpacks.by) r4     // Catch:{ all -> 0x03e6 }
            boolean r5 = c(r4)     // Catch:{ all -> 0x03e6 }
            if (r5 == 0) goto L_0x0349
            java.util.List<com.google.android.play.core.assetpacks.bv> r5 = r4.d     // Catch:{ all -> 0x03e6 }
            java.lang.Object r5 = r5.get(r6)     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bv r5 = (com.google.android.play.core.assetpacks.bv) r5     // Catch:{ all -> 0x03e6 }
            boolean r5 = r5.f1060a     // Catch:{ all -> 0x03e6 }
            if (r5 == 0) goto L_0x0349
            boolean r5 = r1.b(r2, r4)     // Catch:{ all -> 0x03e6 }
            if (r5 != 0) goto L_0x0349
            com.google.android.play.core.internal.ag r0 = f1066a     // Catch:{ all -> 0x03e6 }
            r5 = 4
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch:{ all -> 0x03e6 }
            int r5 = r4.f     // Catch:{ all -> 0x03e6 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x03e6 }
            r3[r6] = r5     // Catch:{ all -> 0x03e6 }
            int r5 = r2.f1062a     // Catch:{ all -> 0x03e6 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x03e6 }
            r7 = 1
            r3[r7] = r5     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r5 = r2.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r5 = r5.f1061a     // Catch:{ all -> 0x03e6 }
            r8 = 2
            r3[r8] = r5     // Catch:{ all -> 0x03e6 }
            java.lang.String r5 = r4.f1063a     // Catch:{ all -> 0x03e6 }
            r9 = 3
            r3[r9] = r5     // Catch:{ all -> 0x03e6 }
            java.lang.String r5 = "Found patch slice task using patch format %s for session %s, pack %s, slice %s."
            r0.a(r5, r3)     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bc r0 = r1.d     // Catch:{ all -> 0x03e6 }
            int r3 = r2.f1062a     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r5 = r2.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r5 = r5.f1061a     // Catch:{ all -> 0x03e6 }
            java.lang.String r7 = r4.f1063a     // Catch:{ all -> 0x03e6 }
            java.io.InputStream r30 = r0.a(r3, r5, r7, r6)     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.cw r0 = new com.google.android.play.core.assetpacks.cw     // Catch:{ all -> 0x03e6 }
            int r3 = r2.f1062a     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r5 = r2.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r5 = r5.f1061a     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.au r6 = r1.c     // Catch:{ all -> 0x03e6 }
            int r20 = r6.t(r5)     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.au r6 = r1.c     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r7 = r2.c     // Catch:{ all -> 0x03e6 }
            java.lang.String r7 = r7.f1061a     // Catch:{ all -> 0x03e6 }
            long r21 = r6.u(r7)     // Catch:{ all -> 0x03e6 }
            int r6 = r2.b     // Catch:{ all -> 0x03e6 }
            com.google.android.play.core.assetpacks.bw r2 = r2.c     // Catch:{ all -> 0x03e6 }
            long r7 = r2.b     // Catch:{ all -> 0x03e6 }
            int r2 = r4.f     // Catch:{ all -> 0x03e6 }
            java.lang.String r9 = r4.f1063a     // Catch:{ all -> 0x03e6 }
            long r10 = r4.c     // Catch:{ all -> 0x03e6 }
            r17 = r0
            r18 = r3
            r19 = r5
            r23 = r6
            r24 = r7
            r26 = r2
            r27 = r9
            r28 = r10
            r17.<init>(r18, r19, r20, r21, r23, r24, r26, r27, r28, r30)     // Catch:{ all -> 0x03e6 }
            goto L_0x03db
        L_0x03da:
            r0 = 0
        L_0x03db:
            if (r0 == 0) goto L_0x03df
            goto L_0x00aa
        L_0x03df:
            com.google.android.play.core.assetpacks.ca r0 = r1.b
            r0.b()
            r2 = 0
            return r2
        L_0x03e6:
            r0 = move-exception
            com.google.android.play.core.assetpacks.ca r2 = r1.b
            r2.b()
            goto L_0x03ee
        L_0x03ed:
            throw r0
        L_0x03ee:
            goto L_0x03ed
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.cd.a():com.google.android.play.core.assetpacks.cc");
    }
}
