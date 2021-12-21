package com.appnext.core;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.appnext.core.e;
import com.google.android.gms.plus.PlusShare;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.mopub.common.Constants;
import java.io.IOException;
import java.util.HashMap;

public final class q {
    private static final String y = "error_no_market";
    /* access modifiers changed from: private */
    public String banner = "";
    /* access modifiers changed from: private */
    public e click;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public String gj = "";
    /* access modifiers changed from: private */
    public String guid = "";
    /* access modifiers changed from: private */
    public h hH;
    /* access modifiers changed from: private */
    public a hI;
    /* access modifiers changed from: private */
    public e.a hJ = new e.a() {
        public final void onMarket(String str) {
            AppnextAd f = q.this.hI.f();
            Ad e = q.this.hI.e();
            if (e != null && f != null && q.this.context != null) {
                if (!f.c(q.this.context, f.getAdPackage())) {
                    try {
                        if (!str.startsWith("market://details?id=" + f.getAdPackage()) && !str.startsWith(Constants.HTTP)) {
                            q.this.b(f.m("admin.appnext.com", "applink"), f.getBannerID(), e.getPlacementID(), e.getTID(), str, "SetROpenV1");
                        }
                    } catch (Throwable unused) {
                    }
                    if (q.this.hH == null) {
                        h unused2 = q.this.hH = new h();
                    }
                    h h = q.this.hH;
                    String adPackage = f.getAdPackage();
                    String m = f.m("admin.appnext.com", "applink");
                    String bannerID = f.getBannerID();
                    String placementID = e.getPlacementID();
                    String tid = e.getTID();
                    String vid = e.getVID();
                    String auid = e.getAUID();
                    h.am = adPackage;
                    h.an = str;
                    h.guid = m;
                    h.ao = bannerID;
                    h.ap = placementID;
                    h.at = null;
                    h.aq = tid;
                    h.ar = vid;
                    h.as = auid;
                    q.this.hH.t(q.this.context.getApplicationContext());
                } else if (str.startsWith("market://")) {
                    try {
                        Intent launchIntentForPackage = q.this.context.getPackageManager().getLaunchIntentForPackage(f.getAdPackage());
                        launchIntentForPackage.addFlags(268435456);
                        q.this.context.startActivity(launchIntentForPackage);
                    } catch (Throwable unused3) {
                        q.this.hI.report("error_no_market");
                    }
                } else {
                    try {
                        q.d(q.this, str);
                    } catch (Throwable unused4) {
                        q.this.hI.report("error_no_market");
                    }
                }
            }
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(5:6|7|8|9|20)(1:(1:11)(5:12|13|14|15|17))) */
        /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0087 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x003a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0074 */
        /* JADX WARNING: Removed duplicated region for block: B:10:0x007e  */
        /* JADX WARNING: Removed duplicated region for block: B:6:0x0052 A[SYNTHETIC, Splitter:B:6:0x0052] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void error(java.lang.String r8) {
            /*
                r7 = this;
                com.appnext.core.q r0 = com.appnext.core.q.this     // Catch:{ all -> 0x003a }
                java.lang.String r1 = "admin.appnext.com"
                java.lang.String r2 = "applink"
                java.lang.String r1 = com.appnext.core.f.m(r1, r2)     // Catch:{ all -> 0x003a }
                com.appnext.core.q r2 = com.appnext.core.q.this     // Catch:{ all -> 0x003a }
                com.appnext.core.q$a r2 = r2.hI     // Catch:{ all -> 0x003a }
                com.appnext.core.AppnextAd r2 = r2.f()     // Catch:{ all -> 0x003a }
                java.lang.String r2 = r2.getBannerID()     // Catch:{ all -> 0x003a }
                com.appnext.core.q r3 = com.appnext.core.q.this     // Catch:{ all -> 0x003a }
                com.appnext.core.q$a r3 = r3.hI     // Catch:{ all -> 0x003a }
                com.appnext.core.Ad r3 = r3.e()     // Catch:{ all -> 0x003a }
                java.lang.String r3 = r3.getPlacementID()     // Catch:{ all -> 0x003a }
                com.appnext.core.q r4 = com.appnext.core.q.this     // Catch:{ all -> 0x003a }
                com.appnext.core.q$a r4 = r4.hI     // Catch:{ all -> 0x003a }
                com.appnext.core.Ad r4 = r4.e()     // Catch:{ all -> 0x003a }
                java.lang.String r4 = r4.getTID()     // Catch:{ all -> 0x003a }
                java.lang.String r6 = "SetDOpenV1"
                r5 = r8
                r0.b(r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x003a }
            L_0x003a:
                com.appnext.core.q r0 = com.appnext.core.q.this     // Catch:{ all -> 0x0090 }
                com.appnext.core.q$a r0 = r0.hI     // Catch:{ all -> 0x0090 }
                com.appnext.core.p r0 = r0.g()     // Catch:{ all -> 0x0090 }
                java.lang.String r1 = "urlApp_protection"
                java.lang.String r0 = r0.get(r1)     // Catch:{ all -> 0x0090 }
                boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch:{ all -> 0x0090 }
                java.lang.String r1 = "error_no_market"
                if (r0 == 0) goto L_0x007e
                com.appnext.core.q r8 = com.appnext.core.q.this     // Catch:{ all -> 0x0074 }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0074 }
                java.lang.String r2 = "market://details?id="
                r0.<init>(r2)     // Catch:{ all -> 0x0074 }
                com.appnext.core.q r2 = com.appnext.core.q.this     // Catch:{ all -> 0x0074 }
                com.appnext.core.q$a r2 = r2.hI     // Catch:{ all -> 0x0074 }
                com.appnext.core.AppnextAd r2 = r2.f()     // Catch:{ all -> 0x0074 }
                java.lang.String r2 = r2.getAdPackage()     // Catch:{ all -> 0x0074 }
                r0.append(r2)     // Catch:{ all -> 0x0074 }
                java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0074 }
                com.appnext.core.q.d(r8, r0)     // Catch:{ all -> 0x0074 }
                goto L_0x0090
            L_0x0074:
                com.appnext.core.q r8 = com.appnext.core.q.this     // Catch:{ all -> 0x0090 }
                com.appnext.core.q$a r8 = r8.hI     // Catch:{ all -> 0x0090 }
                r8.report(r1)     // Catch:{ all -> 0x0090 }
                goto L_0x0090
            L_0x007e:
                if (r8 != 0) goto L_0x0081
                return
            L_0x0081:
                com.appnext.core.q r0 = com.appnext.core.q.this     // Catch:{ all -> 0x0087 }
                com.appnext.core.q.d(r0, r8)     // Catch:{ all -> 0x0087 }
                goto L_0x0090
            L_0x0087:
                com.appnext.core.q r8 = com.appnext.core.q.this     // Catch:{ all -> 0x0090 }
                com.appnext.core.q$a r8 = r8.hI     // Catch:{ all -> 0x0090 }
                r8.report(r1)     // Catch:{ all -> 0x0090 }
            L_0x0090:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.q.AnonymousClass3.error(java.lang.String):void");
        }
    };

    public interface a {
        Ad e();

        AppnextAd f();

        p g();

        void report(String str);
    }

    public q(Context context2, a aVar) {
        this.context = context2;
        this.click = e.k(context2);
        this.hI = aVar;
    }

    public final void b(String str, String str2, String str3, String str4, String str5, String str6) {
        e eVar = this.click;
        if (eVar != null) {
            new Thread(new Runnable(str, str2, str3, str4, str5, str6) {
                public final void run(
/*
Method generation error in method: com.appnext.core.e.7.run():void, dex: classes.dex
                jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.appnext.core.e.7.run():void, class status: UNLOADED
                	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:640)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:91)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:697)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                
*/
            }).start();
        }
    }

    public final void a(AppnextAd appnextAd, String str, e.a aVar) {
        e eVar = this.click;
        if (eVar != null) {
            new Thread(new Runnable(appnextAd) {
                public final void run(
/*
Method generation error in method: com.appnext.core.e.6.run():void, dex: classes.dex
                jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.appnext.core.e.6.run():void, class status: UNLOADED
                	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:640)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:91)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:697)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                
*/
            }).start();
        }
    }

    public final void b(final AppnextAd appnextAd, final String str, final e.a aVar) {
        if (this.click != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public final void run() {
                    try {
                        q.this.click.a(appnextAd.getAppURL(), appnextAd.getMarketUrl(), str + "&device=" + f.be() + "&ox=0", appnextAd.getBannerID(), new e.a() {
                            public final void onMarket(String str) {
                                StringBuilder sb = new StringBuilder("Vta - success - ");
                                sb.append(appnextAd.getAdTitle());
                                sb.append(" -- ");
                                sb.append(appnextAd.getBannerID());
                                String unused = q.this.gj = str;
                                String unused2 = q.this.guid = f.m("admin.appnext.com", "applink");
                                String unused3 = q.this.banner = appnextAd.getBannerID();
                                if (aVar != null) {
                                    aVar.onMarket(str);
                                }
                            }

                            public final void error(String str) {
                                StringBuilder sb = new StringBuilder("Vta - failed - ");
                                sb.append(appnextAd.getAdTitle());
                                sb.append(" -- ");
                                sb.append(appnextAd.getBannerID());
                                String unused = q.this.gj = "";
                                String unused2 = q.this.guid = "";
                                String unused3 = q.this.banner = "";
                                if (aVar != null) {
                                    aVar.error(str);
                                }
                            }
                        });
                    } catch (Throwable unused) {
                    }
                }
            });
        }
    }

    public final void a(AppnextAd appnextAd, String str, String str2, e.a aVar) {
        final String str3 = str;
        final e.a aVar2 = aVar;
        final AppnextAd appnextAd2 = appnextAd;
        final String str4 = str2;
        new Thread(new Runnable() {
            public final void run() {
                if (!q.this.bo()) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            e.a b = q.this.hJ;
                            b.error(str3 + "&device=" + f.be());
                            if (aVar2 != null) {
                                e.a aVar = aVar2;
                                aVar.error(str3 + "&device=" + f.be());
                            }
                        }
                    });
                } else if (appnextAd2 != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            if (q.this.gj.equals("") || !q.this.gj.contains(appnextAd2.getAdPackage())) {
                                new StringBuilder("click url ").append(str3);
                                String str = str3 + "&device=" + f.be();
                                String webview = appnextAd2.getWebview();
                                char c = 65535;
                                switch (webview.hashCode()) {
                                    case 48:
                                        if (webview.equals("0")) {
                                            c = 2;
                                            break;
                                        }
                                        break;
                                    case 49:
                                        if (webview.equals("1")) {
                                            c = 1;
                                            break;
                                        }
                                        break;
                                    case 50:
                                        if (webview.equals(InternalAvidAdSessionContext.AVID_API_LEVEL)) {
                                            c = 0;
                                            break;
                                        }
                                        break;
                                }
                                if (c == 0) {
                                    try {
                                        q.this.hJ.onMarket(str);
                                        if (aVar2 != null) {
                                            aVar2.onMarket(str);
                                        }
                                    } catch (Throwable unused) {
                                    }
                                } else if (c == 1) {
                                    Intent intent = new Intent(q.this.context, ResultActivity.class);
                                    intent.putExtra("url", str);
                                    intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, appnextAd2.getAdTitle());
                                    intent.addFlags(268435456);
                                    q.this.context.startActivity(intent);
                                    if (aVar2 != null) {
                                        aVar2.onMarket(str);
                                    }
                                } else if (q.this.click != null) {
                                    q.this.click.a(appnextAd2.getAppURL(), appnextAd2.getMarketUrl(), str, appnextAd2.getBannerID(), (e.a) new e.a() {
                                        public final void onMarket(String str) {
                                            q.this.hJ.onMarket(str);
                                            if (aVar2 != null) {
                                                aVar2.onMarket(str);
                                            }
                                        }

                                        public final void error(String str) {
                                            q.this.hJ.error(str);
                                            if (aVar2 != null) {
                                                aVar2.error(str);
                                            }
                                        }
                                    }, 1000 * Long.parseLong(q.this.hI.g().get("resolve_timeout")));
                                }
                            } else {
                                new Thread(new Runnable() {
                                    public final void run() {
                                        try {
                                            f.a("https://admin.appnext.com/AdminService.asmx/SetRL?guid=" + q.this.guid + "&bid=" + q.this.banner + "&pid=" + str4, (HashMap<String, String>) null);
                                        } catch (Throwable unused) {
                                        }
                                    }
                                }).start();
                                q.this.hJ.onMarket(q.this.gj);
                                if (aVar2 != null) {
                                    aVar2.onMarket(q.this.gj);
                                }
                                String unused2 = q.this.gj = "";
                            }
                        }
                    });
                }
            }
        }).start();
    }

    private void openLink(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(268435456);
        this.context.startActivity(intent);
    }

    public final void e(AppnextAd appnextAd) {
        try {
            if (this.click != null) {
                this.click.e(appnextAd);
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public final boolean bo() {
        try {
            if (this.context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
                f.a("http://www.appnext.com/myid.html", (HashMap<String, String>) null);
                return true;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true;
            }
            throw new IOException();
        } catch (Throwable unused) {
            return false;
        }
    }

    public final void destroy() {
        try {
            if (this.hH != null) {
                this.hH.a(this.context);
            }
            this.hH = null;
        } catch (Throwable unused) {
        }
        this.context = null;
        if (this.click != null) {
            this.click = null;
        }
    }

    static /* synthetic */ void d(q qVar, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(268435456);
        qVar.context.startActivity(intent);
    }
}
