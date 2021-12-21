package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzadh
public final class zzahx extends zzajx implements zzahw {
    private final Context mContext;
    private final Object mLock;
    private final zzaji zzbze;
    private final long zzclp;
    private final ArrayList<zzahn> zzcmd;
    private final List<zzahq> zzcme;
    private final HashSet<String> zzcmf;
    private final zzago zzcmg;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzahx(android.content.Context r8, com.google.android.gms.internal.ads.zzaji r9, com.google.android.gms.internal.ads.zzago r10) {
        /*
            r7 = this;
            com.google.android.gms.internal.ads.zzna<java.lang.Long> r0 = com.google.android.gms.internal.ads.zznk.zzaye
            com.google.android.gms.internal.ads.zzni r1 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Long r0 = (java.lang.Long) r0
            long r5 = r0.longValue()
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r10
            r1.<init>(r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahx.<init>(android.content.Context, com.google.android.gms.internal.ads.zzaji, com.google.android.gms.internal.ads.zzago):void");
    }

    private zzahx(Context context, zzaji zzaji, zzago zzago, long j) {
        this.zzcmd = new ArrayList<>();
        this.zzcme = new ArrayList();
        this.zzcmf = new HashSet<>();
        this.mLock = new Object();
        this.mContext = context;
        this.zzbze = zzaji;
        this.zzcmg = zzago;
        this.zzclp = j;
    }

    private final zzajh zza(int i, String str, zzwx zzwx) {
        String str2;
        boolean z;
        long j;
        String str3;
        zzjn zzjn;
        boolean z2;
        int i2;
        zzjj zzjj = this.zzbze.zzcgs.zzccv;
        List<String> list = this.zzbze.zzcos.zzbsn;
        List<String> list2 = this.zzbze.zzcos.zzbso;
        List<String> list3 = this.zzbze.zzcos.zzces;
        int i3 = this.zzbze.zzcos.orientation;
        long j2 = this.zzbze.zzcos.zzbsu;
        String str4 = this.zzbze.zzcgs.zzccy;
        boolean z3 = this.zzbze.zzcos.zzceq;
        zzwy zzwy = this.zzbze.zzcod;
        long j3 = this.zzbze.zzcos.zzcer;
        zzjn zzjn2 = this.zzbze.zzacv;
        long j4 = j3;
        zzwy zzwy2 = zzwy;
        long j5 = this.zzbze.zzcos.zzcep;
        long j6 = this.zzbze.zzcoh;
        long j7 = this.zzbze.zzcos.zzceu;
        String str5 = this.zzbze.zzcos.zzcev;
        JSONObject jSONObject = this.zzbze.zzcob;
        zzaig zzaig = this.zzbze.zzcos.zzcfe;
        JSONObject jSONObject2 = jSONObject;
        List<String> list4 = this.zzbze.zzcos.zzcff;
        List<String> list5 = this.zzbze.zzcos.zzcfg;
        boolean z4 = this.zzbze.zzcos.zzcfh;
        zzael zzael = this.zzbze.zzcos.zzcfi;
        zzaig zzaig2 = zzaig;
        StringBuilder sb = new StringBuilder("");
        List<zzahq> list6 = this.zzcme;
        if (list6 == null) {
            str2 = sb.toString();
            zzjn = zzjn2;
            z = z3;
            str3 = str5;
            j = j7;
        } else {
            Iterator<zzahq> it = list6.iterator();
            while (true) {
                zzjn = zzjn2;
                if (!it.hasNext()) {
                    break;
                }
                zzahq next = it.next();
                if (next != null) {
                    Iterator<zzahq> it2 = it;
                    if (!TextUtils.isEmpty(next.zzbru)) {
                        String str6 = next.zzbru;
                        String str7 = str5;
                        int i4 = next.errorCode;
                        long j8 = j7;
                        if (i4 == 3) {
                            z2 = z3;
                            i2 = 1;
                        } else if (i4 == 4) {
                            z2 = z3;
                            i2 = 2;
                        } else if (i4 != 5) {
                            i2 = 6;
                            if (i4 == 6) {
                                z2 = z3;
                                i2 = 0;
                            } else if (i4 != 7) {
                                z2 = z3;
                            } else {
                                z2 = z3;
                                i2 = 3;
                            }
                        } else {
                            z2 = z3;
                            i2 = 4;
                        }
                        long j9 = next.zzbub;
                        boolean z5 = z2;
                        StringBuilder sb2 = new StringBuilder(String.valueOf(str6).length() + 33);
                        sb2.append(str6);
                        sb2.append(".");
                        sb2.append(i2);
                        sb2.append(".");
                        sb2.append(j9);
                        sb.append(String.valueOf(sb2.toString()).concat("_"));
                        it = it2;
                        zzjn2 = zzjn;
                        str5 = str7;
                        j7 = j8;
                        z3 = z5;
                    } else {
                        it = it2;
                    }
                }
                zzjn2 = zzjn;
            }
            z = z3;
            str3 = str5;
            j = j7;
            str2 = sb.substring(0, Math.max(0, sb.length() - 1));
        }
        zzaig zzaig3 = zzaig2;
        long j10 = j;
        long j11 = j6;
        JSONObject jSONObject3 = jSONObject2;
        List<String> list7 = list5;
        zzael zzael2 = zzael;
        return new zzajh(zzjj, (zzaqw) null, list, i, list2, list3, i3, j2, str4, z, zzwx, (zzxq) null, str, zzwy2, (zzxa) null, j4, zzjn, j5, j11, j10, str3, jSONObject3, (zzpb) null, zzaig3, list4, list7, z4, zzael2, str2, this.zzbze.zzcos.zzbsr, this.zzbze.zzcos.zzcfl, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, this.zzbze.zzcor, this.zzbze.zzcos.zzcfp, this.zzbze.zzcos.zzbsp, this.zzbze.zzcos.zzzm, this.zzbze.zzcos.zzcfq);
    }

    public final void onStop() {
    }

    public final void zza(String str, int i) {
    }

    public final void zzcb(String str) {
        synchronized (this.mLock) {
            this.zzcmf.add(str);
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARNING: Missing exception handler attribute for start block: B:87:0x0161 */
    public final void zzdn() {
        /*
            r19 = this;
            r11 = r19
            com.google.android.gms.internal.ads.zzaji r0 = r11.zzbze
            com.google.android.gms.internal.ads.zzwy r0 = r0.zzcod
            java.util.List<com.google.android.gms.internal.ads.zzwx> r0 = r0.zzbsm
            java.util.Iterator r12 = r0.iterator()
        L_0x000c:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x00bf
            java.lang.Object r0 = r12.next()
            r13 = r0
            com.google.android.gms.internal.ads.zzwx r13 = (com.google.android.gms.internal.ads.zzwx) r13
            java.lang.String r14 = r13.zzbsb
            java.util.List<java.lang.String> r0 = r13.zzbrt
            java.util.Iterator r15 = r0.iterator()
        L_0x0021:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L_0x000c
            java.lang.Object r0 = r15.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0040
            java.lang.String r1 = "com.google.ads.mediation.customevent.CustomEventAdapter"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x003e
            goto L_0x0040
        L_0x003e:
            r3 = r0
            goto L_0x004c
        L_0x0040:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00b7 }
            r0.<init>(r14)     // Catch:{ JSONException -> 0x00b7 }
            java.lang.String r1 = "class_name"
            java.lang.String r0 = r0.getString(r1)     // Catch:{ JSONException -> 0x00b7 }
            goto L_0x003e
        L_0x004c:
            java.lang.Object r9 = r11.mLock
            monitor-enter(r9)
            com.google.android.gms.internal.ads.zzago r0 = r11.zzcmg     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzaib r7 = r0.zzca(r3)     // Catch:{ all -> 0x00b0 }
            if (r7 == 0) goto L_0x008a
            com.google.android.gms.internal.ads.zzahv r0 = r7.zzpf()     // Catch:{ all -> 0x00b0 }
            if (r0 == 0) goto L_0x008a
            com.google.android.gms.internal.ads.zzxq r0 = r7.zzpe()     // Catch:{ all -> 0x00b0 }
            if (r0 != 0) goto L_0x0064
            goto L_0x008a
        L_0x0064:
            com.google.android.gms.internal.ads.zzahn r0 = new com.google.android.gms.internal.ads.zzahn     // Catch:{ all -> 0x00b0 }
            android.content.Context r2 = r11.mContext     // Catch:{ all -> 0x00b0 }
            com.google.android.gms.internal.ads.zzaji r6 = r11.zzbze     // Catch:{ all -> 0x00b0 }
            long r4 = r11.zzclp     // Catch:{ all -> 0x00b0 }
            r1 = r0
            r16 = r4
            r4 = r14
            r5 = r13
            r8 = r19
            r18 = r9
            r9 = r16
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00b5 }
            com.google.android.gms.internal.ads.zzago r1 = r11.zzcmg     // Catch:{ all -> 0x00b5 }
            com.google.android.gms.ads.internal.gmsg.zzb r1 = r1.zzos()     // Catch:{ all -> 0x00b5 }
            r0.zza((com.google.android.gms.ads.internal.gmsg.zzb) r1)     // Catch:{ all -> 0x00b5 }
            java.util.ArrayList<com.google.android.gms.internal.ads.zzahn> r1 = r11.zzcmd     // Catch:{ all -> 0x00b5 }
            r1.add(r0)     // Catch:{ all -> 0x00b5 }
        L_0x0088:
            monitor-exit(r18)     // Catch:{ all -> 0x00b5 }
            goto L_0x0021
        L_0x008a:
            r18 = r9
            java.util.List<com.google.android.gms.internal.ads.zzahq> r0 = r11.zzcme     // Catch:{ all -> 0x00b5 }
            com.google.android.gms.internal.ads.zzahs r1 = new com.google.android.gms.internal.ads.zzahs     // Catch:{ all -> 0x00b5 }
            r1.<init>()     // Catch:{ all -> 0x00b5 }
            java.lang.String r2 = r13.zzbru     // Catch:{ all -> 0x00b5 }
            com.google.android.gms.internal.ads.zzahs r1 = r1.zzcd(r2)     // Catch:{ all -> 0x00b5 }
            com.google.android.gms.internal.ads.zzahs r1 = r1.zzcc(r3)     // Catch:{ all -> 0x00b5 }
            r2 = 0
            com.google.android.gms.internal.ads.zzahs r1 = r1.zzg(r2)     // Catch:{ all -> 0x00b5 }
            r2 = 7
            com.google.android.gms.internal.ads.zzahs r1 = r1.zzad(r2)     // Catch:{ all -> 0x00b5 }
            com.google.android.gms.internal.ads.zzahq r1 = r1.zzpd()     // Catch:{ all -> 0x00b5 }
            r0.add(r1)     // Catch:{ all -> 0x00b5 }
            goto L_0x0088
        L_0x00b0:
            r0 = move-exception
            r18 = r9
        L_0x00b3:
            monitor-exit(r18)     // Catch:{ all -> 0x00b5 }
            throw r0
        L_0x00b5:
            r0 = move-exception
            goto L_0x00b3
        L_0x00b7:
            r0 = move-exception
            java.lang.String r1 = "Unable to determine custom event class name, skipping..."
            com.google.android.gms.internal.ads.zzakb.zzb(r1, r0)
            goto L_0x0021
        L_0x00bf:
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            java.util.ArrayList<com.google.android.gms.internal.ads.zzahn> r1 = r11.zzcmd
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r2 = r1.size()
            r3 = 0
            r4 = 0
        L_0x00ce:
            if (r4 >= r2) goto L_0x00e4
            java.lang.Object r5 = r1.get(r4)
            int r4 = r4 + 1
            com.google.android.gms.internal.ads.zzahn r5 = (com.google.android.gms.internal.ads.zzahn) r5
            java.lang.String r6 = r5.zzbth
            boolean r6 = r0.add(r6)
            if (r6 == 0) goto L_0x00ce
            r5.zzoz()
            goto L_0x00ce
        L_0x00e4:
            java.util.ArrayList<com.google.android.gms.internal.ads.zzahn> r0 = r11.zzcmd
            r1 = r0
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r2 = r1.size()
        L_0x00ed:
            if (r3 >= r2) goto L_0x019a
            java.lang.Object r0 = r1.get(r3)
            int r3 = r3 + 1
            r4 = r0
            com.google.android.gms.internal.ads.zzahn r4 = (com.google.android.gms.internal.ads.zzahn) r4
            java.util.concurrent.Future r0 = r4.zzoz()     // Catch:{ InterruptedException -> 0x0161, Exception -> 0x0142 }
            r0.get()     // Catch:{ InterruptedException -> 0x0161, Exception -> 0x0142 }
            java.lang.Object r5 = r11.mLock
            monitor-enter(r5)
            java.lang.String r0 = r4.zzbth     // Catch:{ all -> 0x013d }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x013d }
            if (r0 != 0) goto L_0x0113
            java.util.List<com.google.android.gms.internal.ads.zzahq> r0 = r11.zzcme     // Catch:{ all -> 0x013d }
            com.google.android.gms.internal.ads.zzahq r6 = r4.zzpa()     // Catch:{ all -> 0x013d }
            r0.add(r6)     // Catch:{ all -> 0x013d }
        L_0x0113:
            monitor-exit(r5)     // Catch:{ all -> 0x013d }
            java.lang.Object r6 = r11.mLock
            monitor-enter(r6)
            java.util.HashSet<java.lang.String> r0 = r11.zzcmf     // Catch:{ all -> 0x013a }
            java.lang.String r5 = r4.zzbth     // Catch:{ all -> 0x013a }
            boolean r0 = r0.contains(r5)     // Catch:{ all -> 0x013a }
            if (r0 == 0) goto L_0x0138
            java.lang.String r0 = r4.zzbth     // Catch:{ all -> 0x013a }
            com.google.android.gms.internal.ads.zzwx r1 = r4.zzpb()     // Catch:{ all -> 0x013a }
            r2 = -2
            com.google.android.gms.internal.ads.zzajh r0 = r11.zza(r2, r0, r1)     // Catch:{ all -> 0x013a }
            android.os.Handler r1 = com.google.android.gms.internal.ads.zzamu.zzsy     // Catch:{ all -> 0x013a }
            com.google.android.gms.internal.ads.zzahy r2 = new com.google.android.gms.internal.ads.zzahy     // Catch:{ all -> 0x013a }
            r2.<init>(r11, r0)     // Catch:{ all -> 0x013a }
            r1.post(r2)     // Catch:{ all -> 0x013a }
            monitor-exit(r6)     // Catch:{ all -> 0x013a }
            return
        L_0x0138:
            monitor-exit(r6)     // Catch:{ all -> 0x013a }
            goto L_0x00ed
        L_0x013a:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x013a }
            throw r0
        L_0x013d:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x013d }
            throw r0
        L_0x0140:
            r0 = move-exception
            goto L_0x0181
        L_0x0142:
            r0 = move-exception
            java.lang.String r5 = "Unable to resolve rewarded adapter."
            com.google.android.gms.internal.ads.zzakb.zzc(r5, r0)     // Catch:{ all -> 0x0140 }
            java.lang.Object r5 = r11.mLock
            monitor-enter(r5)
            java.lang.String r0 = r4.zzbth     // Catch:{ all -> 0x015e }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x015e }
            if (r0 != 0) goto L_0x015c
            java.util.List<com.google.android.gms.internal.ads.zzahq> r0 = r11.zzcme     // Catch:{ all -> 0x015e }
            com.google.android.gms.internal.ads.zzahq r4 = r4.zzpa()     // Catch:{ all -> 0x015e }
            r0.add(r4)     // Catch:{ all -> 0x015e }
        L_0x015c:
            monitor-exit(r5)     // Catch:{ all -> 0x015e }
            goto L_0x00ed
        L_0x015e:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x015e }
            throw r0
        L_0x0161:
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0140 }
            r0.interrupt()     // Catch:{ all -> 0x0140 }
            java.lang.Object r1 = r11.mLock
            monitor-enter(r1)
            java.lang.String r0 = r4.zzbth     // Catch:{ all -> 0x017e }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x017e }
            if (r0 != 0) goto L_0x017c
            java.util.List<com.google.android.gms.internal.ads.zzahq> r0 = r11.zzcme     // Catch:{ all -> 0x017e }
            com.google.android.gms.internal.ads.zzahq r2 = r4.zzpa()     // Catch:{ all -> 0x017e }
            r0.add(r2)     // Catch:{ all -> 0x017e }
        L_0x017c:
            monitor-exit(r1)     // Catch:{ all -> 0x017e }
            goto L_0x019a
        L_0x017e:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x017e }
            throw r0
        L_0x0181:
            java.lang.Object r1 = r11.mLock
            monitor-enter(r1)
            java.lang.String r2 = r4.zzbth     // Catch:{ all -> 0x0197 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0197 }
            if (r2 != 0) goto L_0x0195
            java.util.List<com.google.android.gms.internal.ads.zzahq> r2 = r11.zzcme     // Catch:{ all -> 0x0197 }
            com.google.android.gms.internal.ads.zzahq r3 = r4.zzpa()     // Catch:{ all -> 0x0197 }
            r2.add(r3)     // Catch:{ all -> 0x0197 }
        L_0x0195:
            monitor-exit(r1)     // Catch:{ all -> 0x0197 }
            throw r0
        L_0x0197:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0197 }
            throw r0
        L_0x019a:
            r0 = 3
            r1 = 0
            com.google.android.gms.internal.ads.zzajh r0 = r11.zza(r0, r1, r1)
            android.os.Handler r1 = com.google.android.gms.internal.ads.zzamu.zzsy
            com.google.android.gms.internal.ads.zzahz r2 = new com.google.android.gms.internal.ads.zzahz
            r2.<init>(r11, r0)
            r1.post(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahx.zzdn():void");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzl(zzajh zzajh) {
        this.zzcmg.zzot().zzb(zzajh);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzm(zzajh zzajh) {
        this.zzcmg.zzot().zzb(zzajh);
    }
}
