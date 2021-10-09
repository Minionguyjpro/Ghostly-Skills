package com.google.android.gms.internal.ads;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbv;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzet implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private final Object mLock = new Object();
    private boolean zzaaq = false;
    private zzamj zzadz;
    private final Context zzaeo;
    private final WeakReference<zzajh> zzaeq;
    private WeakReference<ViewTreeObserver> zzaer;
    private final zzgd zzaes;
    protected final zzer zzaet;
    private final WindowManager zzaeu;
    private final PowerManager zzaev;
    private final KeyguardManager zzaew;
    private final DisplayMetrics zzaex;
    private zzfa zzaey;
    private boolean zzaez;
    private boolean zzafa = false;
    private boolean zzafb;
    private boolean zzafc;
    private boolean zzafd;
    private BroadcastReceiver zzafe;
    private final HashSet<zzeq> zzaff = new HashSet<>();
    private final HashSet<zzfo> zzafg = new HashSet<>();
    private final Rect zzafh = new Rect();
    private final zzew zzafi;
    private float zzafj;

    public zzet(Context context, zzjn zzjn, zzajh zzajh, zzang zzang, zzgd zzgd) {
        this.zzaeq = new WeakReference<>(zzajh);
        this.zzaes = zzgd;
        this.zzaer = new WeakReference<>((Object) null);
        this.zzafb = true;
        this.zzafd = false;
        this.zzadz = new zzamj(200);
        this.zzaet = new zzer(UUID.randomUUID().toString(), zzang, zzjn.zzarb, zzajh.zzcob, zzajh.zzfz(), zzjn.zzare);
        this.zzaeu = (WindowManager) context.getSystemService("window");
        this.zzaev = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zzaew = (KeyguardManager) context.getSystemService("keyguard");
        this.zzaeo = context;
        this.zzafi = new zzew(this, new Handler());
        this.zzaeo.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.zzafi);
        this.zzaex = context.getResources().getDisplayMetrics();
        Display defaultDisplay = this.zzaeu.getDefaultDisplay();
        this.zzafh.right = defaultDisplay.getWidth();
        this.zzafh.bottom = defaultDisplay.getHeight();
        zzgb();
    }

    private final boolean isScreenOn() {
        return Build.VERSION.SDK_INT >= 20 ? this.zzaev.isInteractive() : this.zzaev.isScreenOn();
    }

    private static int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    private final JSONObject zza(View view, Boolean bool) throws JSONException {
        View view2 = view;
        if (view2 == null) {
            return zzgg().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
        }
        boolean isAttachedToWindow = zzbv.zzem().isAttachedToWindow(view2);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view2.getLocationOnScreen(iArr);
            view2.getLocationInWindow(iArr2);
        } catch (Exception e) {
            zzakb.zzb("Failure getting view location.", e);
        }
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        boolean globalVisibleRect = view2.getGlobalVisibleRect(rect2, (Point) null);
        Rect rect3 = new Rect();
        boolean localVisibleRect = view2.getLocalVisibleRect(rect3);
        Rect rect4 = new Rect();
        view2.getHitRect(rect4);
        JSONObject zzgg = zzgg();
        String str = "isVisible";
        JSONObject jSONObject = zzgg;
        zzgg.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", isAttachedToWindow).put("viewBox", new JSONObject().put("top", zza(this.zzafh.top, this.zzaex)).put("bottom", zza(this.zzafh.bottom, this.zzaex)).put("left", zza(this.zzafh.left, this.zzaex)).put("right", zza(this.zzafh.right, this.zzaex))).put("adBox", new JSONObject().put("top", zza(rect.top, this.zzaex)).put("bottom", zza(rect.bottom, this.zzaex)).put("left", zza(rect.left, this.zzaex)).put("right", zza(rect.right, this.zzaex))).put("globalVisibleBox", new JSONObject().put("top", zza(rect2.top, this.zzaex)).put("bottom", zza(rect2.bottom, this.zzaex)).put("left", zza(rect2.left, this.zzaex)).put("right", zza(rect2.right, this.zzaex))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put("top", zza(rect3.top, this.zzaex)).put("bottom", zza(rect3.bottom, this.zzaex)).put("left", zza(rect3.left, this.zzaex)).put("right", zza(rect3.right, this.zzaex))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put("top", zza(rect4.top, this.zzaex)).put("bottom", zza(rect4.bottom, this.zzaex)).put("left", zza(rect4.left, this.zzaex)).put("right", zza(rect4.right, this.zzaex))).put("screenDensity", (double) this.zzaex.density);
        Boolean valueOf = bool == null ? Boolean.valueOf(zzbv.zzek().zza(view2, this.zzaev, this.zzaew)) : bool;
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put(str, valueOf.booleanValue());
        return jSONObject2;
    }

    private static JSONObject zza(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }

    private final void zza(JSONObject jSONObject, boolean z) {
        try {
            JSONObject zza = zza(jSONObject);
            ArrayList arrayList = new ArrayList(this.zzafg);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((zzfo) obj).zzb(zza, z);
            }
        } catch (Throwable th) {
            zzakb.zzb("Skipping active view message.", th);
        }
    }

    private final void zzgd() {
        zzfa zzfa = this.zzaey;
        if (zzfa != null) {
            zzfa.zza(this);
        }
    }

    private final void zzgf() {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzaer.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    private final JSONObject zzgg() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.zzaet.zzfw()).put("activeViewJSON", this.zzaet.zzfx()).put(AvidJSONUtil.KEY_TIMESTAMP, zzbv.zzer().elapsedRealtime()).put("adFormat", this.zzaet.zzfv()).put("hashCode", this.zzaet.zzfy()).put("isMraid", this.zzaet.zzfz()).put("isStopped", this.zzafa).put("isPaused", this.zzaaq).put("isNative", this.zzaet.zzga()).put("isScreenOn", isScreenOn()).put("appMuted", zzbv.zzfj().zzdp()).put("appVolume", (double) zzbv.zzfj().zzdo()).put("deviceVolume", (double) this.zzafj);
        return jSONObject;
    }

    public final void onGlobalLayout() {
        zzl(2);
    }

    public final void onScrollChanged() {
        zzl(1);
    }

    public final void pause() {
        synchronized (this.mLock) {
            this.zzaaq = true;
            zzl(3);
        }
    }

    public final void resume() {
        synchronized (this.mLock) {
            this.zzaaq = false;
            zzl(3);
        }
    }

    public final void stop() {
        synchronized (this.mLock) {
            this.zzafa = true;
            zzl(3);
        }
    }

    public final void zza(zzfa zzfa) {
        synchronized (this.mLock) {
            this.zzaey = zzfa;
        }
    }

    public final void zza(zzfo zzfo) {
        if (this.zzafg.isEmpty()) {
            synchronized (this.mLock) {
                if (this.zzafe == null) {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.SCREEN_ON");
                    intentFilter.addAction("android.intent.action.SCREEN_OFF");
                    this.zzafe = new zzeu(this);
                    zzbv.zzfk().zza(this.zzaeo, this.zzafe, intentFilter);
                }
            }
            zzl(3);
        }
        this.zzafg.add(zzfo);
        try {
            zzfo.zzb(zza(zza(this.zzaes.zzgh(), (Boolean) null)), false);
        } catch (JSONException e) {
            zzakb.zzb("Skipping measurement update for new client.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzfo zzfo, Map<String, String> map) {
        String valueOf = String.valueOf(this.zzaet.zzfy());
        zzakb.zzck(valueOf.length() != 0 ? "Received request to untrack: ".concat(valueOf) : new String("Received request to untrack: "));
        zzb(zzfo);
    }

    public final void zzb(zzfo zzfo) {
        this.zzafg.remove(zzfo);
        zzfo.zzgl();
        if (this.zzafg.isEmpty()) {
            synchronized (this.mLock) {
                zzgf();
                synchronized (this.mLock) {
                    if (this.zzafe != null) {
                        try {
                            zzbv.zzfk().zza(this.zzaeo, this.zzafe);
                        } catch (IllegalStateException e) {
                            zzakb.zzb("Failed trying to unregister the receiver", e);
                        } catch (Exception e2) {
                            zzbv.zzeo().zza(e2, "ActiveViewUnit.stopScreenStatusMonitoring");
                        }
                        this.zzafe = null;
                    }
                }
                this.zzaeo.getContentResolver().unregisterContentObserver(this.zzafi);
                int i = 0;
                this.zzafb = false;
                zzgd();
                ArrayList arrayList = new ArrayList(this.zzafg);
                int size = arrayList.size();
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    zzb((zzfo) obj);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = map.get("hashCode");
        return !TextUtils.isEmpty(str) && str.equals(this.zzaet.zzfy());
    }

    /* access modifiers changed from: package-private */
    public final void zzd(Map<String, String> map) {
        zzl(3);
    }

    /* access modifiers changed from: package-private */
    public final void zze(Map<String, String> map) {
        if (map.containsKey("isVisible")) {
            boolean z = "1".equals(map.get("isVisible")) || "true".equals(map.get("isVisible"));
            Iterator<zzeq> it = this.zzaff.iterator();
            while (it.hasNext()) {
                it.next().zza(this, z);
            }
        }
    }

    public final void zzgb() {
        this.zzafj = zzalb.zzay(this.zzaeo);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0036 A[Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003b A[Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzgc() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            boolean r1 = r5.zzafb     // Catch:{ all -> 0x0046 }
            if (r1 == 0) goto L_0x0044
            r1 = 1
            r5.zzafc = r1     // Catch:{ all -> 0x0046 }
            org.json.JSONObject r2 = r5.zzgg()     // Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }
            java.lang.String r3 = "doneReasonCode"
            java.lang.String r4 = "u"
            r2.put(r3, r4)     // Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }
            r5.zza((org.json.JSONObject) r2, (boolean) r1)     // Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }
            goto L_0x0024
        L_0x0019:
            r1 = move-exception
            java.lang.String r2 = "Failure while processing active view data."
        L_0x001c:
            com.google.android.gms.internal.ads.zzakb.zzb(r2, r1)     // Catch:{ all -> 0x0046 }
            goto L_0x0024
        L_0x0020:
            r1 = move-exception
            java.lang.String r2 = "JSON failure while processing active view data."
            goto L_0x001c
        L_0x0024:
            java.lang.String r1 = "Untracking ad unit: "
            com.google.android.gms.internal.ads.zzer r2 = r5.zzaet     // Catch:{ all -> 0x0046 }
            java.lang.String r2 = r2.zzfy()     // Catch:{ all -> 0x0046 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0046 }
            int r3 = r2.length()     // Catch:{ all -> 0x0046 }
            if (r3 == 0) goto L_0x003b
            java.lang.String r1 = r1.concat(r2)     // Catch:{ all -> 0x0046 }
            goto L_0x0041
        L_0x003b:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x0046 }
            r2.<init>(r1)     // Catch:{ all -> 0x0046 }
            r1 = r2
        L_0x0041:
            com.google.android.gms.internal.ads.zzakb.zzck(r1)     // Catch:{ all -> 0x0046 }
        L_0x0044:
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            return
        L_0x0046:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            goto L_0x004a
        L_0x0049:
            throw r1
        L_0x004a:
            goto L_0x0049
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzet.zzgc():void");
    }

    public final boolean zzge() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzafb;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00cf, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzl(int r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mLock
            monitor-enter(r0)
            java.util.HashSet<com.google.android.gms.internal.ads.zzfo> r1 = r7.zzafg     // Catch:{ all -> 0x00d0 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00d0 }
        L_0x0009:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x00d0 }
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x001f
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x00d0 }
            com.google.android.gms.internal.ads.zzfo r2 = (com.google.android.gms.internal.ads.zzfo) r2     // Catch:{ all -> 0x00d0 }
            boolean r2 = r2.zzgk()     // Catch:{ all -> 0x00d0 }
            if (r2 == 0) goto L_0x0009
            r1 = 1
            goto L_0x0020
        L_0x001f:
            r1 = 0
        L_0x0020:
            if (r1 == 0) goto L_0x00ce
            boolean r1 = r7.zzafb     // Catch:{ all -> 0x00d0 }
            if (r1 != 0) goto L_0x0028
            goto L_0x00ce
        L_0x0028:
            com.google.android.gms.internal.ads.zzgd r1 = r7.zzaes     // Catch:{ all -> 0x00d0 }
            android.view.View r1 = r1.zzgh()     // Catch:{ all -> 0x00d0 }
            if (r1 == 0) goto L_0x0040
            com.google.android.gms.internal.ads.zzakk r2 = com.google.android.gms.ads.internal.zzbv.zzek()     // Catch:{ all -> 0x00d0 }
            android.os.PowerManager r5 = r7.zzaev     // Catch:{ all -> 0x00d0 }
            android.app.KeyguardManager r6 = r7.zzaew     // Catch:{ all -> 0x00d0 }
            boolean r2 = r2.zza((android.view.View) r1, (android.os.PowerManager) r5, (android.app.KeyguardManager) r6)     // Catch:{ all -> 0x00d0 }
            if (r2 == 0) goto L_0x0040
            r2 = 1
            goto L_0x0041
        L_0x0040:
            r2 = 0
        L_0x0041:
            if (r1 == 0) goto L_0x0053
            if (r2 == 0) goto L_0x0053
            android.graphics.Rect r5 = new android.graphics.Rect     // Catch:{ all -> 0x00d0 }
            r5.<init>()     // Catch:{ all -> 0x00d0 }
            r6 = 0
            boolean r5 = r1.getGlobalVisibleRect(r5, r6)     // Catch:{ all -> 0x00d0 }
            if (r5 == 0) goto L_0x0053
            r5 = 1
            goto L_0x0054
        L_0x0053:
            r5 = 0
        L_0x0054:
            com.google.android.gms.internal.ads.zzgd r6 = r7.zzaes     // Catch:{ all -> 0x00d0 }
            boolean r6 = r6.zzgi()     // Catch:{ all -> 0x00d0 }
            if (r6 == 0) goto L_0x0061
            r7.zzgc()     // Catch:{ all -> 0x00d0 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x0061:
            if (r8 != r4) goto L_0x0071
            com.google.android.gms.internal.ads.zzamj r6 = r7.zzadz     // Catch:{ all -> 0x00d0 }
            boolean r6 = r6.tryAcquire()     // Catch:{ all -> 0x00d0 }
            if (r6 != 0) goto L_0x0071
            boolean r6 = r7.zzafd     // Catch:{ all -> 0x00d0 }
            if (r5 != r6) goto L_0x0071
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x0071:
            if (r5 != 0) goto L_0x007b
            boolean r6 = r7.zzafd     // Catch:{ all -> 0x00d0 }
            if (r6 != 0) goto L_0x007b
            if (r8 != r4) goto L_0x007b
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x007b:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r2)     // Catch:{ JSONException -> 0x008b, RuntimeException -> 0x0089 }
            org.json.JSONObject r8 = r7.zza((android.view.View) r1, (java.lang.Boolean) r8)     // Catch:{ JSONException -> 0x008b, RuntimeException -> 0x0089 }
            r7.zza((org.json.JSONObject) r8, (boolean) r3)     // Catch:{ JSONException -> 0x008b, RuntimeException -> 0x0089 }
            r7.zzafd = r5     // Catch:{ JSONException -> 0x008b, RuntimeException -> 0x0089 }
            goto L_0x0091
        L_0x0089:
            r8 = move-exception
            goto L_0x008c
        L_0x008b:
            r8 = move-exception
        L_0x008c:
            java.lang.String r1 = "Active view update failed."
            com.google.android.gms.internal.ads.zzakb.zza(r1, r8)     // Catch:{ all -> 0x00d0 }
        L_0x0091:
            com.google.android.gms.internal.ads.zzgd r8 = r7.zzaes     // Catch:{ all -> 0x00d0 }
            com.google.android.gms.internal.ads.zzgd r8 = r8.zzgj()     // Catch:{ all -> 0x00d0 }
            android.view.View r8 = r8.zzgh()     // Catch:{ all -> 0x00d0 }
            if (r8 == 0) goto L_0x00c9
            java.lang.ref.WeakReference<android.view.ViewTreeObserver> r1 = r7.zzaer     // Catch:{ all -> 0x00d0 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x00d0 }
            android.view.ViewTreeObserver r1 = (android.view.ViewTreeObserver) r1     // Catch:{ all -> 0x00d0 }
            android.view.ViewTreeObserver r8 = r8.getViewTreeObserver()     // Catch:{ all -> 0x00d0 }
            if (r8 == r1) goto L_0x00c9
            r7.zzgf()     // Catch:{ all -> 0x00d0 }
            boolean r2 = r7.zzaez     // Catch:{ all -> 0x00d0 }
            if (r2 == 0) goto L_0x00ba
            if (r1 == 0) goto L_0x00c2
            boolean r1 = r1.isAlive()     // Catch:{ all -> 0x00d0 }
            if (r1 == 0) goto L_0x00c2
        L_0x00ba:
            r7.zzaez = r4     // Catch:{ all -> 0x00d0 }
            r8.addOnScrollChangedListener(r7)     // Catch:{ all -> 0x00d0 }
            r8.addOnGlobalLayoutListener(r7)     // Catch:{ all -> 0x00d0 }
        L_0x00c2:
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x00d0 }
            r1.<init>(r8)     // Catch:{ all -> 0x00d0 }
            r7.zzaer = r1     // Catch:{ all -> 0x00d0 }
        L_0x00c9:
            r7.zzgd()     // Catch:{ all -> 0x00d0 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x00ce:
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            return
        L_0x00d0:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00d0 }
            goto L_0x00d4
        L_0x00d3:
            throw r8
        L_0x00d4:
            goto L_0x00d3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzet.zzl(int):void");
    }
}
