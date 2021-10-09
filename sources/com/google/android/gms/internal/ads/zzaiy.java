package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@zzadh
public final class zzaiy {
    private final AtomicReference<ThreadPoolExecutor> zzcnp = new AtomicReference<>((Object) null);
    private final Object zzcnq = new Object();
    private String zzcnr = null;
    private final AtomicBoolean zzcns = new AtomicBoolean(false);
    private final AtomicInteger zzcnt = new AtomicInteger(-1);
    private final AtomicReference<Object> zzcnu = new AtomicReference<>((Object) null);
    private final AtomicReference<Object> zzcnv = new AtomicReference<>((Object) null);
    private ConcurrentMap<String, Method> zzcnw = new ConcurrentHashMap(9);

    private static Bundle zza(Context context, String str, boolean z) {
        Bundle bundle = new Bundle();
        try {
            bundle.putLong("_aeid", Long.parseLong(str));
        } catch (NullPointerException | NumberFormatException e) {
            String valueOf = String.valueOf(str);
            zzakb.zzb(valueOf.length() != 0 ? "Invalid event ID: ".concat(valueOf) : new String("Invalid event ID: "), e);
        }
        if (z) {
            bundle.putInt("_r", 1);
        }
        return bundle;
    }

    private final Object zza(String str, Context context) {
        if (!zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzcnu, true)) {
            return null;
        }
        try {
            return zzi(context, str).invoke(this.zzcnu.get(), new Object[0]);
        } catch (Exception e) {
            zza(e, str, true);
            return null;
        }
    }

    private final void zza(Context context, String str, Bundle bundle) {
        if (zzs(context) && zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzcnu, true)) {
            Method zzac = zzac(context);
            try {
                zzac.invoke(this.zzcnu.get(), new Object[]{"am", str, bundle});
            } catch (Exception e) {
                zza(e, "logEventInternal", true);
            }
        }
    }

    private final void zza(Exception exc, String str, boolean z) {
        if (!this.zzcns.get()) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 30);
            sb.append("Invoke Firebase method ");
            sb.append(str);
            sb.append(" error.");
            zzakb.zzdk(sb.toString());
            if (z) {
                zzakb.zzdk("The Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires the latest Firebase SDK jar, but Firebase SDK is either missing or out of date");
                this.zzcns.set(true);
            }
        }
    }

    private final boolean zza(Context context, String str, AtomicReference<Object> atomicReference, boolean z) {
        if (atomicReference.get() == null) {
            try {
                atomicReference.compareAndSet((Object) null, context.getClassLoader().loadClass(str).getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{context}));
            } catch (Exception e) {
                zza(e, "getInstance", z);
                return false;
            }
        }
        return true;
    }

    private final Method zzac(Context context) {
        Method method = (Method) this.zzcnw.get("logEventInternal");
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod("logEventInternal", new Class[]{String.class, String.class, Bundle.class});
            this.zzcnw.put("logEventInternal", declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, "logEventInternal", true);
            return null;
        }
    }

    private final void zzb(Context context, String str, String str2) {
        if (zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzcnu, true)) {
            Method zzh = zzh(context, str2);
            try {
                zzh.invoke(this.zzcnu.get(), new Object[]{str});
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 37 + String.valueOf(str).length());
                sb.append("Invoke Firebase method ");
                sb.append(str2);
                sb.append(", Ad Unit Id: ");
                sb.append(str);
                zzakb.v(sb.toString());
            } catch (Exception e) {
                zza(e, str2, false);
            }
        }
    }

    private final Method zzh(Context context, String str) {
        Method method = (Method) this.zzcnw.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[]{String.class});
            this.zzcnw.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final Method zzi(Context context, String str) {
        Method method = (Method) this.zzcnw.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[0]);
            this.zzcnw.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final Method zzj(Context context, String str) {
        Method method = (Method) this.zzcnw.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics").getDeclaredMethod(str, new Class[]{Activity.class, String.class, String.class});
            this.zzcnw.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    public final void zza(Context context, String str, String str2) {
        if (zzs(context)) {
            zza(context, str, zza(context, str2, "_ac".equals(str)));
        }
    }

    public final void zza(Context context, String str, String str2, String str3, int i) {
        if (zzs(context)) {
            Bundle zza = zza(context, str, false);
            zza.putString("_ai", str2);
            zza.putString("type", str3);
            zza.putInt("value", i);
            zza(context, "_ar", zza);
            StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 75);
            sb.append("Log a Firebase reward video event, reward type: ");
            sb.append(str3);
            sb.append(", reward value: ");
            sb.append(i);
            zzakb.v(sb.toString());
        }
    }

    public final String zzaa(Context context) {
        if (!zzs(context)) {
            return null;
        }
        long longValue = ((Long) zzkb.zzik().zzd(zznk.zzaxt)).longValue();
        if (longValue < 0) {
            return (String) zza("getAppInstanceId", context);
        }
        if (this.zzcnp.get() == null) {
            this.zzcnp.compareAndSet((Object) null, new ThreadPoolExecutor(((Integer) zzkb.zzik().zzd(zznk.zzaxu)).intValue(), ((Integer) zzkb.zzik().zzd(zznk.zzaxu)).intValue(), 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), new zzaja(this)));
        }
        Future submit = this.zzcnp.get().submit(new zzaiz(this, context));
        try {
            return (String) submit.get(longValue, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            submit.cancel(true);
            if (e instanceof TimeoutException) {
                return "TIME_OUT";
            }
            return null;
        }
    }

    public final String zzab(Context context) {
        Object zza;
        if (zzs(context) && (zza = zza("generateEventId", context)) != null) {
            return zza.toString();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzad(Context context) throws Exception {
        return (String) zza("getAppInstanceId", context);
    }

    public final void zzb(Context context, String str) {
        if (zzs(context)) {
            zzb(context, str, "beginAdUnitExposure");
        }
    }

    public final void zzc(Context context, String str) {
        if (zzs(context)) {
            zzb(context, str, "endAdUnitExposure");
        }
    }

    public final void zzd(Context context, String str) {
        if (zzs(context) && (context instanceof Activity) && zza(context, "com.google.firebase.analytics.FirebaseAnalytics", this.zzcnv, false)) {
            Method zzj = zzj(context, "setCurrentScreen");
            try {
                zzj.invoke(this.zzcnv.get(), new Object[]{(Activity) context, str, context.getPackageName()});
            } catch (Exception e) {
                zza(e, "setCurrentScreen", false);
            }
        }
    }

    public final void zze(Context context, String str) {
        zza(context, "_ac", str);
    }

    public final void zzf(Context context, String str) {
        zza(context, "_ai", str);
    }

    public final void zzg(Context context, String str) {
        zza(context, "_aq", str);
    }

    public final boolean zzs(Context context) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzaxj)).booleanValue() && !this.zzcns.get()) {
            if (this.zzcnt.get() == -1) {
                zzkb.zzif();
                if (!zzamu.zzbe(context)) {
                    zzkb.zzif();
                    if (zzamu.zzbh(context)) {
                        zzakb.zzdk("Google Play Service is out of date, the Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires updated Google Play Service.");
                        this.zzcnt.set(0);
                    }
                }
                this.zzcnt.set(1);
            }
            if (this.zzcnt.get() == 1) {
                return true;
            }
        }
        return false;
    }

    public final boolean zzt(Context context) {
        return ((Boolean) zzkb.zzik().zzd(zznk.zzaxk)).booleanValue() && zzs(context);
    }

    public final boolean zzu(Context context) {
        return ((Boolean) zzkb.zzik().zzd(zznk.zzaxl)).booleanValue() && zzs(context);
    }

    public final boolean zzv(Context context) {
        return ((Boolean) zzkb.zzik().zzd(zznk.zzaxm)).booleanValue() && zzs(context);
    }

    public final boolean zzw(Context context) {
        return ((Boolean) zzkb.zzik().zzd(zznk.zzaxn)).booleanValue() && zzs(context);
    }

    public final boolean zzx(Context context) {
        return ((Boolean) zzkb.zzik().zzd(zznk.zzaxq)).booleanValue() && zzs(context);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzy(android.content.Context r7) {
        /*
            r6 = this;
            java.lang.String r0 = "getCurrentScreenName"
            boolean r1 = r6.zzs(r7)
            java.lang.String r2 = ""
            if (r1 != 0) goto L_0x000b
            return r2
        L_0x000b:
            java.util.concurrent.atomic.AtomicReference<java.lang.Object> r1 = r6.zzcnu
            r3 = 1
            java.lang.String r4 = "com.google.android.gms.measurement.AppMeasurement"
            boolean r1 = r6.zza(r7, r4, r1, r3)
            if (r1 != 0) goto L_0x0017
            return r2
        L_0x0017:
            r1 = 0
            java.lang.reflect.Method r3 = r6.zzi(r7, r0)     // Catch:{ Exception -> 0x0045 }
            java.util.concurrent.atomic.AtomicReference<java.lang.Object> r4 = r6.zzcnu     // Catch:{ Exception -> 0x0045 }
            java.lang.Object r4 = r4.get()     // Catch:{ Exception -> 0x0045 }
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0045 }
            java.lang.Object r3 = r3.invoke(r4, r5)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0045 }
            if (r3 != 0) goto L_0x0041
            java.lang.String r3 = "getCurrentScreenClass"
            java.lang.reflect.Method r7 = r6.zzi(r7, r3)     // Catch:{ Exception -> 0x0045 }
            java.util.concurrent.atomic.AtomicReference<java.lang.Object> r3 = r6.zzcnu     // Catch:{ Exception -> 0x0045 }
            java.lang.Object r3 = r3.get()     // Catch:{ Exception -> 0x0045 }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0045 }
            java.lang.Object r7 = r7.invoke(r3, r4)     // Catch:{ Exception -> 0x0045 }
            r3 = r7
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0045 }
        L_0x0041:
            if (r3 == 0) goto L_0x0044
            return r3
        L_0x0044:
            return r2
        L_0x0045:
            r7 = move-exception
            r6.zza((java.lang.Exception) r7, (java.lang.String) r0, (boolean) r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaiy.zzy(android.content.Context):java.lang.String");
    }

    public final String zzz(Context context) {
        if (!zzs(context)) {
            return null;
        }
        synchronized (this.zzcnq) {
            if (this.zzcnr != null) {
                String str = this.zzcnr;
                return str;
            }
            String str2 = (String) zza("getGmpAppId", context);
            this.zzcnr = str2;
            return str2;
        }
    }
}
