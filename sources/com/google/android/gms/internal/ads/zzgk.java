package com.google.android.gms.internal.ads;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgk extends Thread {
    private final Object mLock;
    private boolean mStarted = false;
    private final int zzagx;
    private final int zzagz;
    private boolean zzahy = false;
    private final zzgf zzahz;
    private final zzadf zzaia;
    private final int zzaib;
    private final int zzaic;
    private final int zzaid;
    private final int zzaie;
    private final int zzaif;
    private final int zzaig;
    private final String zzaih;
    private final boolean zzaii;
    private boolean zzbm = false;

    public zzgk(zzgf zzgf, zzadf zzadf) {
        this.zzahz = zzgf;
        this.zzaia = zzadf;
        this.mLock = new Object();
        this.zzagx = ((Integer) zzkb.zzik().zzd(zznk.zzawl)).intValue();
        this.zzaic = ((Integer) zzkb.zzik().zzd(zznk.zzawm)).intValue();
        this.zzagz = ((Integer) zzkb.zzik().zzd(zznk.zzawn)).intValue();
        this.zzaid = ((Integer) zzkb.zzik().zzd(zznk.zzawo)).intValue();
        this.zzaie = ((Integer) zzkb.zzik().zzd(zznk.zzawr)).intValue();
        this.zzaif = ((Integer) zzkb.zzik().zzd(zznk.zzawt)).intValue();
        this.zzaig = ((Integer) zzkb.zzik().zzd(zznk.zzawu)).intValue();
        this.zzaib = ((Integer) zzkb.zzik().zzd(zznk.zzawp)).intValue();
        this.zzaih = (String) zzkb.zzik().zzd(zznk.zzaww);
        this.zzaii = ((Boolean) zzkb.zzik().zzd(zznk.zzawy)).booleanValue();
        setName("ContentFetchTask");
    }

    private final zzgo zza(View view, zzge zzge) {
        boolean z;
        if (view == null) {
            return new zzgo(this, 0, 0);
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zzgo(this, 0, 0);
            }
            zzge.zzb(text.toString(), globalVisibleRect, view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
            return new zzgo(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof zzaqw)) {
            zzge.zzgs();
            WebView webView = (WebView) view;
            if (!PlatformVersion.isAtLeastKitKat()) {
                z = false;
            } else {
                zzge.zzgs();
                webView.post(new zzgm(this, zzge, webView, globalVisibleRect));
                z = true;
            }
            return z ? new zzgo(this, 0, 1) : new zzgo(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new zzgo(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                zzgo zza = zza(viewGroup.getChildAt(i3), zzge);
                i += zza.zzaiq;
                i2 += zza.zzair;
            }
            return new zzgo(this, i, i2);
        }
    }

    private static boolean zzgx() {
        try {
            Context context = zzbv.zzen().getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null) {
                return false;
            }
            if (keyguardManager == null) {
                return false;
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (Process.myPid() == next.pid) {
                    if (next.importance != 100 || keyguardManager.inKeyguardRestrictedInputMode()) {
                        return false;
                    }
                    PowerManager powerManager = (PowerManager) context.getSystemService("power");
                    return powerManager == null ? false : powerManager.isScreenOn();
                }
            }
            return false;
        } catch (Throwable th) {
            zzbv.zzeo().zza(th, "ContentFetchTask.isInForeground");
            return false;
        }
    }

    private final void zzgz() {
        synchronized (this.mLock) {
            this.zzahy = true;
            StringBuilder sb = new StringBuilder(42);
            sb.append("ContentFetchThread: paused, mPause = ");
            sb.append(true);
            zzakb.zzck(sb.toString());
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
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:368)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:172)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public final void run() {
        /*
            r4 = this;
        L_0x0000:
            boolean r0 = zzgx()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            if (r0 == 0) goto L_0x0059
            com.google.android.gms.internal.ads.zzgg r0 = com.google.android.gms.ads.internal.zzbv.zzen()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            android.app.Activity r0 = r0.getActivity()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            if (r0 != 0) goto L_0x0019
            java.lang.String r0 = "ContentFetchThread: no activity. Sleeping."
            com.google.android.gms.internal.ads.zzakb.zzck(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
        L_0x0015:
            r4.zzgz()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            goto L_0x005f
        L_0x0019:
            if (r0 == 0) goto L_0x005f
            r1 = 0
            android.view.Window r2 = r0.getWindow()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            if (r2 == 0) goto L_0x004c
            android.view.Window r2 = r0.getWindow()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            android.view.View r2 = r2.getDecorView()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            if (r2 == 0) goto L_0x004c
            android.view.Window r0 = r0.getWindow()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            android.view.View r0 = r0.getDecorView()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            r2 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r0 = r0.findViewById(r2)     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            r1 = r0
            goto L_0x004c
        L_0x003d:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzajm r2 = com.google.android.gms.ads.internal.zzbv.zzeo()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            java.lang.String r3 = "ContentFetchTask.extractContent"
            r2.zza(r0, r3)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            java.lang.String r0 = "Failed getting root view of activity. Content not extracted."
            com.google.android.gms.internal.ads.zzakb.zzck(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
        L_0x004c:
            if (r1 == 0) goto L_0x005f
            if (r1 == 0) goto L_0x005f
            com.google.android.gms.internal.ads.zzgl r0 = new com.google.android.gms.internal.ads.zzgl     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            r0.<init>(r4, r1)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            r1.post(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            goto L_0x005f
        L_0x0059:
            java.lang.String r0 = "ContentFetchTask: sleeping"
            com.google.android.gms.internal.ads.zzakb.zzck(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            goto L_0x0015
        L_0x005f:
            int r0 = r4.zzaib     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            int r0 = r0 * 1000
            long r0 = (long) r0     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            goto L_0x007c
        L_0x0068:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.ads.zzakb.zzb(r1, r0)
            com.google.android.gms.internal.ads.zzadf r1 = r4.zzaia
            java.lang.String r2 = "ContentFetchTask.run"
            r1.zza(r0, r2)
            goto L_0x007c
        L_0x0076:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.ads.zzakb.zzb(r1, r0)
        L_0x007c:
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
        L_0x007f:
            boolean r1 = r4.zzahy     // Catch:{ all -> 0x0091 }
            if (r1 == 0) goto L_0x008e
            java.lang.String r1 = "ContentFetchTask: waiting"
            com.google.android.gms.internal.ads.zzakb.zzck(r1)     // Catch:{ InterruptedException -> 0x007f }
            java.lang.Object r1 = r4.mLock     // Catch:{ InterruptedException -> 0x007f }
            r1.wait()     // Catch:{ InterruptedException -> 0x007f }
            goto L_0x007f
        L_0x008e:
            monitor-exit(r0)     // Catch:{ all -> 0x0091 }
            goto L_0x0000
        L_0x0091:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0091 }
            goto L_0x0095
        L_0x0094:
            throw r1
        L_0x0095:
            goto L_0x0094
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgk.run():void");
    }

    public final void wakeup() {
        synchronized (this.mLock) {
            this.zzahy = false;
            this.mLock.notifyAll();
            zzakb.zzck("ContentFetchThread: wakeup");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzge zzge, WebView webView, String str, boolean z) {
        zzge.zzgr();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString("text");
                if (this.zzaii || TextUtils.isEmpty(webView.getTitle())) {
                    zzge.zza(optString, z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                } else {
                    String title = webView.getTitle();
                    StringBuilder sb = new StringBuilder(String.valueOf(title).length() + 1 + String.valueOf(optString).length());
                    sb.append(title);
                    sb.append("\n");
                    sb.append(optString);
                    zzge.zza(sb.toString(), z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                }
            }
            if (zzge.zzgn()) {
                this.zzahz.zzb(zzge);
            }
        } catch (JSONException unused) {
            zzakb.zzck("Json string may be malformed.");
        } catch (Throwable th) {
            zzakb.zza("Failed to get webview content.", th);
            this.zzaia.zza(th, "ContentFetchTask.processWebViewContent");
        }
    }

    public final void zzgw() {
        synchronized (this.mLock) {
            if (this.mStarted) {
                zzakb.zzck("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            start();
        }
    }

    public final zzge zzgy() {
        return this.zzahz.zzgv();
    }

    public final boolean zzha() {
        return this.zzahy;
    }

    /* access modifiers changed from: package-private */
    public final void zzk(View view) {
        try {
            zzge zzge = new zzge(this.zzagx, this.zzaic, this.zzagz, this.zzaid, this.zzaie, this.zzaif, this.zzaig);
            Context context = zzbv.zzen().getContext();
            if (context != null && !TextUtils.isEmpty(this.zzaih)) {
                String str = (String) view.getTag(context.getResources().getIdentifier((String) zzkb.zzik().zzd(zznk.zzawv), "id", context.getPackageName()));
                if (str != null && str.equals(this.zzaih)) {
                    return;
                }
            }
            zzgo zza = zza(view, zzge);
            zzge.zzgt();
            if (zza.zzaiq != 0 || zza.zzair != 0) {
                if (zza.zzair != 0 || zzge.zzgu() != 0) {
                    if (zza.zzair != 0 || !this.zzahz.zza(zzge)) {
                        this.zzahz.zzc(zzge);
                    }
                }
            }
        } catch (Exception e) {
            zzakb.zzb("Exception in fetchContentOnUIThread", e);
            this.zzaia.zza(e, "ContentFetchTask.fetchContent");
        }
    }
}
