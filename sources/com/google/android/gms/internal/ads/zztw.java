package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzbv;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zztw {
    private final Map<zztx, zzty> zzbok = new HashMap();
    private final LinkedList<zztx> zzbol = new LinkedList<>();
    private zzss zzbom;

    private static void zza(String str, zztx zztx) {
        if (zzakb.isLoggable(2)) {
            zzakb.v(String.format(str, new Object[]{zztx}));
        }
    }

    private static String[] zzax(String str) {
        try {
            String[] split = str.split("\u0000");
            for (int i = 0; i < split.length; i++) {
                split[i] = new String(Base64.decode(split[i], 0), "UTF-8");
            }
            return split;
        } catch (UnsupportedEncodingException unused) {
            return new String[0];
        }
    }

    private static boolean zzay(String str) {
        try {
            return Pattern.matches((String) zzkb.zzik().zzd(zznk.zzazf), str);
        } catch (RuntimeException e) {
            zzbv.zzeo().zza(e, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }

    private static String zzaz(String str) {
        try {
            Matcher matcher = Pattern.compile("([^/]+/[0-9]+).*").matcher(str);
            return matcher.matches() ? matcher.group(1) : str;
        } catch (RuntimeException unused) {
            return str;
        }
    }

    private static void zzb(Bundle bundle, String str) {
        while (true) {
            String[] split = str.split("/", 2);
            if (split.length != 0) {
                String str2 = split[0];
                if (split.length == 1) {
                    bundle.remove(str2);
                    return;
                }
                bundle = bundle.getBundle(str2);
                if (bundle != null) {
                    str = split[1];
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    static Set<String> zzh(zzjj zzjj) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(zzjj.extras.keySet());
        Bundle bundle = zzjj.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            hashSet.addAll(bundle.keySet());
        }
        return hashSet;
    }

    static zzjj zzi(zzjj zzjj) {
        zzjj zzk = zzk(zzjj);
        Bundle bundle = zzk.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            bundle.putBoolean("_skipMediation", true);
        }
        zzk.extras.putBoolean("_skipMediation", true);
        return zzk;
    }

    private static zzjj zzj(zzjj zzjj) {
        zzjj zzk = zzk(zzjj);
        for (String str : ((String) zzkb.zzik().zzd(zznk.zzazb)).split(",")) {
            zzb(zzk.zzaqg, str);
            if (str.startsWith("com.google.ads.mediation.admob.AdMobAdapter/")) {
                zzb(zzk.extras, str.replaceFirst("com.google.ads.mediation.admob.AdMobAdapter/", ""));
            }
        }
        return zzk;
    }

    private static zzjj zzk(zzjj zzjj) {
        Parcel obtain = Parcel.obtain();
        zzjj.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        zzjj createFromParcel = zzjj.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return ((Boolean) zzkb.zzik().zzd(zznk.zzayo)).booleanValue() ? createFromParcel.zzhv() : createFromParcel;
    }

    private final String zzle() {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator it = this.zzbol.iterator();
            while (it.hasNext()) {
                sb.append(Base64.encodeToString(((zztx) it.next()).toString().getBytes("UTF-8"), 0));
                if (it.hasNext()) {
                    sb.append("\u0000");
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public final zztz zza(zzjj zzjj, String str) {
        if (zzay(str)) {
            return null;
        }
        int i = new zzagb(this.zzbom.getApplicationContext()).zzoo().zzcjx;
        zzjj zzj = zzj(zzjj);
        String zzaz = zzaz(str);
        zztx zztx = new zztx(zzj, zzaz, i);
        zzty zzty = this.zzbok.get(zztx);
        if (zzty == null) {
            zza("Interstitial pool created at %s.", zztx);
            zzty = new zzty(zzj, zzaz, i);
            this.zzbok.put(zztx, zzty);
        }
        this.zzbol.remove(zztx);
        this.zzbol.add(zztx);
        zzty.zzli();
        while (true) {
            if (this.zzbol.size() <= ((Integer) zzkb.zzik().zzd(zznk.zzazc)).intValue()) {
                break;
            }
            zztx remove = this.zzbol.remove();
            zzty zzty2 = this.zzbok.get(remove);
            zza("Evicting interstitial queue for %s.", remove);
            while (zzty2.size() > 0) {
                zztz zzl = zzty2.zzl((zzjj) null);
                if (zzl.zzwa) {
                    zzua.zzlk().zzlm();
                }
                zzl.zzbor.zzdj();
            }
            this.zzbok.remove(remove);
        }
        while (zzty.size() > 0) {
            zztz zzl2 = zzty.zzl(zzj);
            if (zzl2.zzwa) {
                if (zzbv.zzer().currentTimeMillis() - zzl2.zzbou > ((long) ((Integer) zzkb.zzik().zzd(zznk.zzaze)).intValue()) * 1000) {
                    zza("Expired interstitial at %s.", zztx);
                    zzua.zzlk().zzll();
                }
            }
            String str2 = zzl2.zzbos != null ? " (inline) " : " ";
            StringBuilder sb = new StringBuilder(str2.length() + 34);
            sb.append("Pooled interstitial");
            sb.append(str2);
            sb.append("returned at %s.");
            zza(sb.toString(), zztx);
            return zzl2;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzss zzss) {
        if (this.zzbom == null) {
            zzss zzlc = zzss.zzlc();
            this.zzbom = zzlc;
            if (zzlc != null) {
                SharedPreferences sharedPreferences = zzlc.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
                while (this.zzbol.size() > 0) {
                    zztx remove = this.zzbol.remove();
                    zzty zzty = this.zzbok.get(remove);
                    zza("Flushing interstitial queue for %s.", remove);
                    while (zzty.size() > 0) {
                        zzty.zzl((zzjj) null).zzbor.zzdj();
                    }
                    this.zzbok.remove(remove);
                }
                try {
                    HashMap hashMap = new HashMap();
                    for (Map.Entry next : sharedPreferences.getAll().entrySet()) {
                        if (!((String) next.getKey()).equals("PoolKeys")) {
                            zzuc zzba = zzuc.zzba((String) next.getValue());
                            zztx zztx = new zztx(zzba.zzaao, zzba.zzye, zzba.zzbop);
                            if (!this.zzbok.containsKey(zztx)) {
                                this.zzbok.put(zztx, new zzty(zzba.zzaao, zzba.zzye, zzba.zzbop));
                                hashMap.put(zztx.toString(), zztx);
                                zza("Restored interstitial queue for %s.", zztx);
                            }
                        }
                    }
                    for (String str : zzax(sharedPreferences.getString("PoolKeys", ""))) {
                        zztx zztx2 = (zztx) hashMap.get(str);
                        if (this.zzbok.containsKey(zztx2)) {
                            this.zzbol.add(zztx2);
                        }
                    }
                } catch (IOException | RuntimeException e) {
                    zzbv.zzeo().zza(e, "InterstitialAdPool.restore");
                    zzakb.zzc("Malformed preferences value for InterstitialAdPool.", e);
                    this.zzbok.clear();
                    this.zzbol.clear();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzjj zzjj, String str) {
        zzss zzss = this.zzbom;
        if (zzss != null) {
            int i = new zzagb(zzss.getApplicationContext()).zzoo().zzcjx;
            zzjj zzj = zzj(zzjj);
            String zzaz = zzaz(str);
            zztx zztx = new zztx(zzj, zzaz, i);
            zzty zzty = this.zzbok.get(zztx);
            if (zzty == null) {
                zza("Interstitial pool created at %s.", zztx);
                zzty = new zzty(zzj, zzaz, i);
                this.zzbok.put(zztx, zzty);
            }
            zzty.zza(this.zzbom, zzjj);
            zzty.zzli();
            zza("Inline entry added to the queue at %s.", zztx);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002f, code lost:
        r5 = r1.size();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzld() {
        /*
            r8 = this;
            com.google.android.gms.internal.ads.zzss r0 = r8.zzbom
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            java.util.Map<com.google.android.gms.internal.ads.zztx, com.google.android.gms.internal.ads.zzty> r0 = r8.zzbok
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x000f:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x0089
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r3 = r1.getKey()
            com.google.android.gms.internal.ads.zztx r3 = (com.google.android.gms.internal.ads.zztx) r3
            java.lang.Object r1 = r1.getValue()
            com.google.android.gms.internal.ads.zzty r1 = (com.google.android.gms.internal.ads.zzty) r1
            r4 = 2
            boolean r5 = com.google.android.gms.internal.ads.zzakb.isLoggable(r4)
            if (r5 == 0) goto L_0x0056
            int r5 = r1.size()
            int r6 = r1.zzlg()
            if (r6 >= r5) goto L_0x0056
            r7 = 3
            java.lang.Object[] r7 = new java.lang.Object[r7]
            int r6 = r5 - r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r7[r2] = r6
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r6 = 1
            r7[r6] = r5
            r7[r4] = r3
            java.lang.String r4 = "Loading %s/%s pooled interstitials for %s."
            java.lang.String r4 = java.lang.String.format(r4, r7)
            com.google.android.gms.internal.ads.zzakb.v(r4)
        L_0x0056:
            int r4 = r1.zzlh()
            int r4 = r4 + r2
        L_0x005b:
            int r2 = r1.size()
            com.google.android.gms.internal.ads.zzna<java.lang.Integer> r5 = com.google.android.gms.internal.ads.zznk.zzazd
            com.google.android.gms.internal.ads.zzni r6 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r5 = r6.zzd(r5)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            if (r2 >= r5) goto L_0x0081
            java.lang.String r2 = "Pooling and loading one new interstitial for %s."
            zza((java.lang.String) r2, (com.google.android.gms.internal.ads.zztx) r3)
            com.google.android.gms.internal.ads.zzss r2 = r8.zzbom
            boolean r2 = r1.zzb((com.google.android.gms.internal.ads.zzss) r2)
            if (r2 == 0) goto L_0x005b
            int r4 = r4 + 1
            goto L_0x005b
        L_0x0081:
            com.google.android.gms.internal.ads.zzua r1 = com.google.android.gms.internal.ads.zzua.zzlk()
            r1.zzw(r4)
            goto L_0x000f
        L_0x0089:
            com.google.android.gms.internal.ads.zzss r0 = r8.zzbom
            if (r0 == 0) goto L_0x00e8
            android.content.Context r0 = r0.getApplicationContext()
            java.lang.String r1 = "com.google.android.gms.ads.internal.interstitial.InterstitialAdPool"
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
            android.content.SharedPreferences$Editor r0 = r0.edit()
            r0.clear()
            java.util.Map<com.google.android.gms.internal.ads.zztx, com.google.android.gms.internal.ads.zzty> r1 = r8.zzbok
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x00a8:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00dc
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            com.google.android.gms.internal.ads.zztx r3 = (com.google.android.gms.internal.ads.zztx) r3
            java.lang.Object r2 = r2.getValue()
            com.google.android.gms.internal.ads.zzty r2 = (com.google.android.gms.internal.ads.zzty) r2
            boolean r4 = r2.zzlj()
            if (r4 == 0) goto L_0x00a8
            com.google.android.gms.internal.ads.zzuc r4 = new com.google.android.gms.internal.ads.zzuc
            r4.<init>(r2)
            java.lang.String r2 = r4.zzlu()
            java.lang.String r4 = r3.toString()
            r0.putString(r4, r2)
            java.lang.String r2 = "Saved interstitial queue for %s."
            zza((java.lang.String) r2, (com.google.android.gms.internal.ads.zztx) r3)
            goto L_0x00a8
        L_0x00dc:
            java.lang.String r1 = r8.zzle()
            java.lang.String r2 = "PoolKeys"
            r0.putString(r2, r1)
            r0.apply()
        L_0x00e8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztw.zzld():void");
    }
}
