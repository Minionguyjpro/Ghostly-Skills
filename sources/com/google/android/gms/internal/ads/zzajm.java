package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzajm implements zzakh {
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    @Nullable
    private zzgf zzahz = null;
    private final zzajt zzcpl = new zzajt();
    private final zzakd zzcpm = new zzakd();
    /* access modifiers changed from: private */
    @Nullable
    public zznn zzcpn = null;
    @Nullable
    private zzgk zzcpo = null;
    @Nullable
    private Boolean zzcpp = null;
    private String zzcpq;
    private final AtomicInteger zzcpr = new AtomicInteger(0);
    private final zzajp zzcps = new zzajp((zzajo) null);
    private final Object zzcpt = new Object();
    private zzanz<ArrayList<String>> zzcpu;
    private zzes zzvy;
    /* access modifiers changed from: private */
    public zzang zzyf;
    private boolean zzzv = false;

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007e, code lost:
        return null;
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzgk zza(@javax.annotation.Nullable android.content.Context r4, boolean r5, boolean r6) {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzawk
            com.google.android.gms.internal.ads.zzni r1 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r1 = 0
            if (r0 != 0) goto L_0x0014
            return r1
        L_0x0014:
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich()
            if (r0 != 0) goto L_0x001b
            return r1
        L_0x001b:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzaws
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r2.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0040
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zznk.zzawq
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()
            java.lang.Object r0 = r2.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0040
            return r1
        L_0x0040:
            if (r5 == 0) goto L_0x0045
            if (r6 == 0) goto L_0x0045
            return r1
        L_0x0045:
            java.lang.Object r5 = r3.mLock
            monitor-enter(r5)
            android.os.Looper r6 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x007f }
            if (r6 == 0) goto L_0x007d
            if (r4 != 0) goto L_0x0051
            goto L_0x007d
        L_0x0051:
            com.google.android.gms.internal.ads.zzgf r6 = r3.zzahz     // Catch:{ all -> 0x007f }
            if (r6 != 0) goto L_0x005c
            com.google.android.gms.internal.ads.zzgf r6 = new com.google.android.gms.internal.ads.zzgf     // Catch:{ all -> 0x007f }
            r6.<init>()     // Catch:{ all -> 0x007f }
            r3.zzahz = r6     // Catch:{ all -> 0x007f }
        L_0x005c:
            com.google.android.gms.internal.ads.zzgk r6 = r3.zzcpo     // Catch:{ all -> 0x007f }
            if (r6 != 0) goto L_0x006f
            com.google.android.gms.internal.ads.zzgk r6 = new com.google.android.gms.internal.ads.zzgk     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.ads.zzgf r0 = r3.zzahz     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.ads.zzang r1 = r3.zzyf     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.ads.zzadf r4 = com.google.android.gms.internal.ads.zzadb.zzc(r4, r1)     // Catch:{ all -> 0x007f }
            r6.<init>(r0, r4)     // Catch:{ all -> 0x007f }
            r3.zzcpo = r6     // Catch:{ all -> 0x007f }
        L_0x006f:
            com.google.android.gms.internal.ads.zzgk r4 = r3.zzcpo     // Catch:{ all -> 0x007f }
            r4.zzgw()     // Catch:{ all -> 0x007f }
            java.lang.String r4 = "start fetching content..."
            com.google.android.gms.internal.ads.zzakb.zzdj(r4)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.ads.zzgk r4 = r3.zzcpo     // Catch:{ all -> 0x007f }
            monitor-exit(r5)     // Catch:{ all -> 0x007f }
            return r4
        L_0x007d:
            monitor-exit(r5)     // Catch:{ all -> 0x007f }
            return r1
        L_0x007f:
            r4 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x007f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzajm.zza(android.content.Context, boolean, boolean):com.google.android.gms.internal.ads.zzgk");
    }

    private static ArrayList<String> zzag(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(context.getApplicationInfo().packageName, 4096);
            if (!(packageInfo.requestedPermissions == null || packageInfo.requestedPermissionsFlags == null)) {
                for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                    if ((packageInfo.requestedPermissionsFlags[i] & 2) != 0) {
                        arrayList.add(packageInfo.requestedPermissions[i]);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return arrayList;
    }

    @Nullable
    public final Context getApplicationContext() {
        return this.mContext;
    }

    @Nullable
    public final Resources getResources() {
        if (this.zzyf.zzcvg) {
            return this.mContext.getResources();
        }
        try {
            DynamiteModule load = DynamiteModule.load(this.mContext, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID);
            if (load != null) {
                return load.getModuleContext().getResources();
            }
            return null;
        } catch (DynamiteModule.LoadingException e) {
            zzakb.zzc("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public final void zza(Boolean bool) {
        synchronized (this.mLock) {
            this.zzcpp = bool;
        }
    }

    public final void zza(Throwable th, String str) {
        zzadb.zzc(this.mContext, this.zzyf).zza(th, str);
    }

    public final void zzaa(boolean z) {
        this.zzcps.zzaa(z);
    }

    @Nullable
    public final zzgk zzaf(@Nullable Context context) {
        return zza(context, this.zzcpm.zzqu(), this.zzcpm.zzqw());
    }

    public final void zzb(Throwable th, String str) {
        zzadb.zzc(this.mContext, this.zzyf).zza(th, str, ((Float) zzkb.zzik().zzd(zznk.zzaul)).floatValue());
    }

    public final void zzd(Context context, zzang zzang) {
        zznn zznn;
        synchronized (this.mLock) {
            if (!this.zzzv) {
                this.mContext = context.getApplicationContext();
                this.zzyf = zzang;
                zzbv.zzen().zza(zzbv.zzep());
                this.zzcpm.initialize(this.mContext);
                this.zzcpm.zza((zzakh) this);
                zzadb.zzc(this.mContext, this.zzyf);
                this.zzcpq = zzbv.zzek().zzm(context, zzang.zzcw);
                this.zzvy = new zzes(context.getApplicationContext(), this.zzyf);
                zzbv.zzet();
                if (!((Boolean) zzkb.zzik().zzd(zznk.zzawh)).booleanValue()) {
                    zzakb.v("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
                    zznn = null;
                } else {
                    zznn = new zznn();
                }
                this.zzcpn = zznn;
                zzanm.zza((zzanz) new zzajo(this).zznt(), "AppState.registerCsiReporter");
                this.zzzv = true;
                zzqi();
            }
        }
    }

    public final void zzd(Bundle bundle) {
        if (bundle.containsKey("content_url_opted_out") && bundle.containsKey("content_vertical_opted_out")) {
            zza(this.mContext, bundle.getBoolean("content_url_opted_out"), bundle.getBoolean("content_vertical_opted_out"));
        }
    }

    public final zzajt zzpx() {
        return this.zzcpl;
    }

    @Nullable
    public final zznn zzpy() {
        zznn zznn;
        synchronized (this.mLock) {
            zznn = this.zzcpn;
        }
        return zznn;
    }

    public final Boolean zzpz() {
        Boolean bool;
        synchronized (this.mLock) {
            bool = this.zzcpp;
        }
        return bool;
    }

    public final boolean zzqa() {
        return this.zzcps.zzqa();
    }

    public final boolean zzqb() {
        return this.zzcps.zzqb();
    }

    public final void zzqc() {
        this.zzcps.zzqc();
    }

    public final zzes zzqd() {
        return this.zzvy;
    }

    public final void zzqe() {
        this.zzcpr.incrementAndGet();
    }

    public final void zzqf() {
        this.zzcpr.decrementAndGet();
    }

    public final int zzqg() {
        return this.zzcpr.get();
    }

    public final zzakd zzqh() {
        zzakd zzakd;
        synchronized (this.mLock) {
            zzakd = this.zzcpm;
        }
        return zzakd;
    }

    public final zzanz<ArrayList<String>> zzqi() {
        if (this.mContext != null && PlatformVersion.isAtLeastJellyBean()) {
            if (!((Boolean) zzkb.zzik().zzd(zznk.zzbau)).booleanValue()) {
                synchronized (this.zzcpt) {
                    if (this.zzcpu != null) {
                        zzanz<ArrayList<String>> zzanz = this.zzcpu;
                        return zzanz;
                    }
                    zzanz<ArrayList<String>> zza = zzaki.zza(new zzajn(this));
                    this.zzcpu = zza;
                    return zza;
                }
            }
        }
        return zzano.zzi(new ArrayList());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ ArrayList zzqj() throws Exception {
        return zzag(this.mContext);
    }
}
