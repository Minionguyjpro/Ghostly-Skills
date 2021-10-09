package com.google.android.gms.internal.ads;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzpn extends zzqb implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final String[] zzbjs = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private final Object mLock = new Object();
    private zzoz zzbij;
    private final FrameLayout zzbjt;
    private View zzbju;
    private final boolean zzbjv;
    private Map<String, WeakReference<View>> zzbjw = Collections.synchronizedMap(new HashMap());
    private View zzbjx;
    private boolean zzbjy = false;
    private Point zzbjz = new Point();
    private Point zzbka = new Point();
    private WeakReference<zzfp> zzbkb = new WeakReference<>((Object) null);
    private FrameLayout zzvh;

    public zzpn(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzbjt = frameLayout;
        this.zzvh = frameLayout2;
        zzbv.zzfg();
        zzaor.zza((View) this.zzbjt, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzbv.zzfg();
        zzaor.zza((View) this.zzbjt, (ViewTreeObserver.OnScrollChangedListener) this);
        this.zzbjt.setOnTouchListener(this);
        this.zzbjt.setOnClickListener(this);
        if (frameLayout2 != null && PlatformVersion.isAtLeastLollipop()) {
            frameLayout2.setElevation(Float.MAX_VALUE);
        }
        zznk.initialize(this.zzbjt.getContext());
        this.zzbjv = ((Boolean) zzkb.zzik().zzd(zznk.zzbcd)).booleanValue();
    }

    private final void zzkt() {
        synchronized (this.mLock) {
            if (!this.zzbjv && this.zzbjy) {
                int measuredWidth = this.zzbjt.getMeasuredWidth();
                int measuredHeight = this.zzbjt.getMeasuredHeight();
                if (!(measuredWidth == 0 || measuredHeight == 0 || this.zzvh == null)) {
                    this.zzvh.setLayoutParams(new FrameLayout.LayoutParams(measuredWidth, measuredHeight));
                    this.zzbjy = false;
                }
            }
        }
    }

    private final void zzl(View view) {
        zzoz zzoz = this.zzbij;
        if (zzoz != null) {
            if (zzoz instanceof zzoy) {
                zzoz = ((zzoy) zzoz).zzkn();
            }
            if (zzoz != null) {
                zzoz.zzl(view);
            }
        }
    }

    private final int zzv(int i) {
        zzkb.zzif();
        return zzamu.zzb(this.zzbij.getContext(), i);
    }

    public final void destroy() {
        synchronized (this.mLock) {
            if (this.zzvh != null) {
                this.zzvh.removeAllViews();
            }
            this.zzvh = null;
            this.zzbjw = null;
            this.zzbjx = null;
            this.zzbij = null;
            this.zzbjz = null;
            this.zzbka = null;
            this.zzbkb = null;
            this.zzbju = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzoz r1 = r8.zzbij     // Catch:{ all -> 0x008e }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x008e }
            return
        L_0x0009:
            com.google.android.gms.internal.ads.zzoz r1 = r8.zzbij     // Catch:{ all -> 0x008e }
            r1.cancelUnconfirmedClick()     // Catch:{ all -> 0x008e }
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ all -> 0x008e }
            r5.<init>()     // Catch:{ all -> 0x008e }
            java.lang.String r1 = "x"
            android.graphics.Point r2 = r8.zzbjz     // Catch:{ all -> 0x008e }
            int r2 = r2.x     // Catch:{ all -> 0x008e }
            int r2 = r8.zzv(r2)     // Catch:{ all -> 0x008e }
            float r2 = (float) r2     // Catch:{ all -> 0x008e }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x008e }
            java.lang.String r1 = "y"
            android.graphics.Point r2 = r8.zzbjz     // Catch:{ all -> 0x008e }
            int r2 = r2.y     // Catch:{ all -> 0x008e }
            int r2 = r8.zzv(r2)     // Catch:{ all -> 0x008e }
            float r2 = (float) r2     // Catch:{ all -> 0x008e }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x008e }
            java.lang.String r1 = "start_x"
            android.graphics.Point r2 = r8.zzbka     // Catch:{ all -> 0x008e }
            int r2 = r2.x     // Catch:{ all -> 0x008e }
            int r2 = r8.zzv(r2)     // Catch:{ all -> 0x008e }
            float r2 = (float) r2     // Catch:{ all -> 0x008e }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x008e }
            java.lang.String r1 = "start_y"
            android.graphics.Point r2 = r8.zzbka     // Catch:{ all -> 0x008e }
            int r2 = r2.y     // Catch:{ all -> 0x008e }
            int r2 = r8.zzv(r2)     // Catch:{ all -> 0x008e }
            float r2 = (float) r2     // Catch:{ all -> 0x008e }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x008e }
            android.view.View r1 = r8.zzbjx     // Catch:{ all -> 0x008e }
            if (r1 == 0) goto L_0x0083
            android.view.View r1 = r8.zzbjx     // Catch:{ all -> 0x008e }
            boolean r1 = r1.equals(r9)     // Catch:{ all -> 0x008e }
            if (r1 == 0) goto L_0x0083
            com.google.android.gms.internal.ads.zzoz r1 = r8.zzbij     // Catch:{ all -> 0x008e }
            boolean r1 = r1 instanceof com.google.android.gms.internal.ads.zzoy     // Catch:{ all -> 0x008e }
            if (r1 == 0) goto L_0x007a
            com.google.android.gms.internal.ads.zzoz r1 = r8.zzbij     // Catch:{ all -> 0x008e }
            com.google.android.gms.internal.ads.zzoy r1 = (com.google.android.gms.internal.ads.zzoy) r1     // Catch:{ all -> 0x008e }
            com.google.android.gms.internal.ads.zzoz r1 = r1.zzkn()     // Catch:{ all -> 0x008e }
            if (r1 == 0) goto L_0x008c
            com.google.android.gms.internal.ads.zzoz r1 = r8.zzbij     // Catch:{ all -> 0x008e }
            com.google.android.gms.internal.ads.zzoy r1 = (com.google.android.gms.internal.ads.zzoy) r1     // Catch:{ all -> 0x008e }
            com.google.android.gms.internal.ads.zzoz r2 = r1.zzkn()     // Catch:{ all -> 0x008e }
            java.lang.String r4 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r8.zzbjw     // Catch:{ all -> 0x008e }
            android.widget.FrameLayout r7 = r8.zzbjt     // Catch:{ all -> 0x008e }
        L_0x0075:
            r3 = r9
            r2.zza(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x008e }
            goto L_0x008c
        L_0x007a:
            com.google.android.gms.internal.ads.zzoz r2 = r8.zzbij     // Catch:{ all -> 0x008e }
            java.lang.String r4 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r8.zzbjw     // Catch:{ all -> 0x008e }
            android.widget.FrameLayout r7 = r8.zzbjt     // Catch:{ all -> 0x008e }
            goto L_0x0075
        L_0x0083:
            com.google.android.gms.internal.ads.zzoz r1 = r8.zzbij     // Catch:{ all -> 0x008e }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r8.zzbjw     // Catch:{ all -> 0x008e }
            android.widget.FrameLayout r3 = r8.zzbjt     // Catch:{ all -> 0x008e }
            r1.zza(r9, r2, r5, r3)     // Catch:{ all -> 0x008e }
        L_0x008c:
            monitor-exit(r0)     // Catch:{ all -> 0x008e }
            return
        L_0x008e:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008e }
            goto L_0x0092
        L_0x0091:
            throw r9
        L_0x0092:
            goto L_0x0091
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpn.onClick(android.view.View):void");
    }

    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            zzkt();
            if (this.zzbij != null) {
                this.zzbij.zzc(this.zzbjt, this.zzbjw);
            }
        }
    }

    public final void onScrollChanged() {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                this.zzbij.zzc(this.zzbjt, this.zzbjw);
            }
            zzkt();
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                return false;
            }
            int[] iArr = new int[2];
            this.zzbjt.getLocationOnScreen(iArr);
            Point point = new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
            this.zzbjz = point;
            if (motionEvent.getAction() == 0) {
                this.zzbka = point;
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setLocation((float) point.x, (float) point.y);
            this.zzbij.zzd(obtain);
            obtain.recycle();
            return false;
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
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0116 A[Catch:{ Exception -> 0x0190 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0161 A[Catch:{ Exception -> 0x0190 }] */
    public final void zza(com.google.android.gms.dynamic.IObjectWrapper r12) {
        /*
            r11 = this;
            java.lang.Object r0 = r11.mLock
            monitor-enter(r0)
            r1 = 0
            r11.zzl(r1)     // Catch:{ all -> 0x0253 }
            java.lang.Object r12 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r12)     // Catch:{ all -> 0x0253 }
            boolean r2 = r12 instanceof com.google.android.gms.internal.ads.zzpd     // Catch:{ all -> 0x0253 }
            if (r2 != 0) goto L_0x0016
            java.lang.String r12 = "Not an instance of native engine. This is most likely a transient error"
            com.google.android.gms.internal.ads.zzakb.zzdk(r12)     // Catch:{ all -> 0x0253 }
            monitor-exit(r0)     // Catch:{ all -> 0x0253 }
            return
        L_0x0016:
            boolean r2 = r11.zzbjv     // Catch:{ all -> 0x0253 }
            r3 = 0
            if (r2 != 0) goto L_0x002e
            android.widget.FrameLayout r2 = r11.zzvh     // Catch:{ all -> 0x0253 }
            if (r2 == 0) goto L_0x002e
            android.widget.FrameLayout r2 = r11.zzvh     // Catch:{ all -> 0x0253 }
            android.widget.FrameLayout$LayoutParams r4 = new android.widget.FrameLayout$LayoutParams     // Catch:{ all -> 0x0253 }
            r4.<init>(r3, r3)     // Catch:{ all -> 0x0253 }
            r2.setLayoutParams(r4)     // Catch:{ all -> 0x0253 }
            android.widget.FrameLayout r2 = r11.zzbjt     // Catch:{ all -> 0x0253 }
            r2.requestLayout()     // Catch:{ all -> 0x0253 }
        L_0x002e:
            r2 = 1
            r11.zzbjy = r2     // Catch:{ all -> 0x0253 }
            com.google.android.gms.internal.ads.zzpd r12 = (com.google.android.gms.internal.ads.zzpd) r12     // Catch:{ all -> 0x0253 }
            com.google.android.gms.internal.ads.zzoz r4 = r11.zzbij     // Catch:{ all -> 0x0253 }
            if (r4 == 0) goto L_0x0052
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r4 = com.google.android.gms.internal.ads.zznk.zzbbu     // Catch:{ all -> 0x0253 }
            com.google.android.gms.internal.ads.zzni r5 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0253 }
            java.lang.Object r4 = r5.zzd(r4)     // Catch:{ all -> 0x0253 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0253 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0253 }
            if (r4 == 0) goto L_0x0052
            com.google.android.gms.internal.ads.zzoz r4 = r11.zzbij     // Catch:{ all -> 0x0253 }
            android.widget.FrameLayout r5 = r11.zzbjt     // Catch:{ all -> 0x0253 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r11.zzbjw     // Catch:{ all -> 0x0253 }
            r4.zzb(r5, r6)     // Catch:{ all -> 0x0253 }
        L_0x0052:
            com.google.android.gms.internal.ads.zzoz r4 = r11.zzbij     // Catch:{ all -> 0x0253 }
            boolean r4 = r4 instanceof com.google.android.gms.internal.ads.zzpd     // Catch:{ all -> 0x0253 }
            if (r4 == 0) goto L_0x008c
            com.google.android.gms.internal.ads.zzoz r4 = r11.zzbij     // Catch:{ all -> 0x0253 }
            com.google.android.gms.internal.ads.zzpd r4 = (com.google.android.gms.internal.ads.zzpd) r4     // Catch:{ all -> 0x0253 }
            if (r4 == 0) goto L_0x008c
            android.content.Context r5 = r4.getContext()     // Catch:{ all -> 0x0253 }
            if (r5 == 0) goto L_0x008c
            com.google.android.gms.internal.ads.zzaiy r5 = com.google.android.gms.ads.internal.zzbv.zzfh()     // Catch:{ all -> 0x0253 }
            android.widget.FrameLayout r6 = r11.zzbjt     // Catch:{ all -> 0x0253 }
            android.content.Context r6 = r6.getContext()     // Catch:{ all -> 0x0253 }
            boolean r5 = r5.zzu(r6)     // Catch:{ all -> 0x0253 }
            if (r5 == 0) goto L_0x008c
            com.google.android.gms.internal.ads.zzaix r4 = r4.zzks()     // Catch:{ all -> 0x0253 }
            if (r4 == 0) goto L_0x007d
            r4.zzx(r3)     // Catch:{ all -> 0x0253 }
        L_0x007d:
            java.lang.ref.WeakReference<com.google.android.gms.internal.ads.zzfp> r5 = r11.zzbkb     // Catch:{ all -> 0x0253 }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x0253 }
            com.google.android.gms.internal.ads.zzfp r5 = (com.google.android.gms.internal.ads.zzfp) r5     // Catch:{ all -> 0x0253 }
            if (r5 == 0) goto L_0x008c
            if (r4 == 0) goto L_0x008c
            r5.zzb(r4)     // Catch:{ all -> 0x0253 }
        L_0x008c:
            com.google.android.gms.internal.ads.zzoz r4 = r11.zzbij     // Catch:{ all -> 0x0253 }
            boolean r4 = r4 instanceof com.google.android.gms.internal.ads.zzoy     // Catch:{ all -> 0x0253 }
            if (r4 == 0) goto L_0x00a4
            com.google.android.gms.internal.ads.zzoz r4 = r11.zzbij     // Catch:{ all -> 0x0253 }
            com.google.android.gms.internal.ads.zzoy r4 = (com.google.android.gms.internal.ads.zzoy) r4     // Catch:{ all -> 0x0253 }
            boolean r4 = r4.zzkm()     // Catch:{ all -> 0x0253 }
            if (r4 == 0) goto L_0x00a4
            com.google.android.gms.internal.ads.zzoz r4 = r11.zzbij     // Catch:{ all -> 0x0253 }
            com.google.android.gms.internal.ads.zzoy r4 = (com.google.android.gms.internal.ads.zzoy) r4     // Catch:{ all -> 0x0253 }
            r4.zzc(r12)     // Catch:{ all -> 0x0253 }
            goto L_0x00b0
        L_0x00a4:
            r11.zzbij = r12     // Catch:{ all -> 0x0253 }
            boolean r4 = r12 instanceof com.google.android.gms.internal.ads.zzoy     // Catch:{ all -> 0x0253 }
            if (r4 == 0) goto L_0x00b0
            r4 = r12
            com.google.android.gms.internal.ads.zzoy r4 = (com.google.android.gms.internal.ads.zzoy) r4     // Catch:{ all -> 0x0253 }
            r4.zzc(r1)     // Catch:{ all -> 0x0253 }
        L_0x00b0:
            android.widget.FrameLayout r4 = r11.zzvh     // Catch:{ all -> 0x0253 }
            if (r4 != 0) goto L_0x00b6
            monitor-exit(r0)     // Catch:{ all -> 0x0253 }
            return
        L_0x00b6:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r4 = com.google.android.gms.internal.ads.zznk.zzbbu     // Catch:{ all -> 0x0253 }
            com.google.android.gms.internal.ads.zzni r5 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0253 }
            java.lang.Object r4 = r5.zzd(r4)     // Catch:{ all -> 0x0253 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0253 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0253 }
            if (r4 == 0) goto L_0x00cd
            android.widget.FrameLayout r4 = r11.zzvh     // Catch:{ all -> 0x0253 }
            r4.setClickable(r3)     // Catch:{ all -> 0x0253 }
        L_0x00cd:
            android.widget.FrameLayout r4 = r11.zzvh     // Catch:{ all -> 0x0253 }
            r4.removeAllViews()     // Catch:{ all -> 0x0253 }
            boolean r4 = r12.zzkj()     // Catch:{ all -> 0x0253 }
            if (r4 == 0) goto L_0x0106
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r11.zzbjw     // Catch:{ all -> 0x0253 }
            if (r5 == 0) goto L_0x00fe
            java.lang.String r5 = "1098"
            java.lang.String r6 = "3011"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6}     // Catch:{ all -> 0x0253 }
            r6 = 0
        L_0x00e5:
            r7 = 2
            if (r6 >= r7) goto L_0x00fe
            r7 = r5[r6]     // Catch:{ all -> 0x0253 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r8 = r11.zzbjw     // Catch:{ all -> 0x0253 }
            java.lang.Object r7 = r8.get(r7)     // Catch:{ all -> 0x0253 }
            java.lang.ref.WeakReference r7 = (java.lang.ref.WeakReference) r7     // Catch:{ all -> 0x0253 }
            if (r7 == 0) goto L_0x00fb
            java.lang.Object r5 = r7.get()     // Catch:{ all -> 0x0253 }
            android.view.View r5 = (android.view.View) r5     // Catch:{ all -> 0x0253 }
            goto L_0x00ff
        L_0x00fb:
            int r6 = r6 + 1
            goto L_0x00e5
        L_0x00fe:
            r5 = r1
        L_0x00ff:
            boolean r6 = r5 instanceof android.view.ViewGroup     // Catch:{ all -> 0x0253 }
            if (r6 == 0) goto L_0x0106
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5     // Catch:{ all -> 0x0253 }
            goto L_0x0107
        L_0x0106:
            r5 = r1
        L_0x0107:
            if (r4 == 0) goto L_0x010c
            if (r5 == 0) goto L_0x010c
            goto L_0x010d
        L_0x010c:
            r2 = 0
        L_0x010d:
            android.view.View r4 = r12.zza((android.view.View.OnClickListener) r11, (boolean) r2)     // Catch:{ all -> 0x0253 }
            r11.zzbjx = r4     // Catch:{ all -> 0x0253 }
            r10 = -1
            if (r4 == 0) goto L_0x0152
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r11.zzbjw     // Catch:{ all -> 0x0253 }
            if (r4 == 0) goto L_0x0128
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r11.zzbjw     // Catch:{ all -> 0x0253 }
            java.lang.String r6 = "1007"
            java.lang.ref.WeakReference r7 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0253 }
            android.view.View r8 = r11.zzbjx     // Catch:{ all -> 0x0253 }
            r7.<init>(r8)     // Catch:{ all -> 0x0253 }
            r4.put(r6, r7)     // Catch:{ all -> 0x0253 }
        L_0x0128:
            if (r2 == 0) goto L_0x0133
            r5.removeAllViews()     // Catch:{ all -> 0x0253 }
            android.view.View r2 = r11.zzbjx     // Catch:{ all -> 0x0253 }
            r5.addView(r2)     // Catch:{ all -> 0x0253 }
            goto L_0x0152
        L_0x0133:
            android.content.Context r2 = r12.getContext()     // Catch:{ all -> 0x0253 }
            com.google.android.gms.ads.formats.AdChoicesView r4 = new com.google.android.gms.ads.formats.AdChoicesView     // Catch:{ all -> 0x0253 }
            r4.<init>(r2)     // Catch:{ all -> 0x0253 }
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams     // Catch:{ all -> 0x0253 }
            r2.<init>(r10, r10)     // Catch:{ all -> 0x0253 }
            r4.setLayoutParams(r2)     // Catch:{ all -> 0x0253 }
            android.view.View r2 = r11.zzbjx     // Catch:{ all -> 0x0253 }
            r4.addView(r2)     // Catch:{ all -> 0x0253 }
            android.widget.FrameLayout r2 = r11.zzvh     // Catch:{ all -> 0x0253 }
            if (r2 == 0) goto L_0x0152
            android.widget.FrameLayout r2 = r11.zzvh     // Catch:{ all -> 0x0253 }
            r2.addView(r4)     // Catch:{ all -> 0x0253 }
        L_0x0152:
            android.widget.FrameLayout r5 = r11.zzbjt     // Catch:{ all -> 0x0253 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r11.zzbjw     // Catch:{ all -> 0x0253 }
            r7 = 0
            r4 = r12
            r8 = r11
            r9 = r11
            r4.zza((android.view.View) r5, (java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>>) r6, (java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>>) r7, (android.view.View.OnTouchListener) r8, (android.view.View.OnClickListener) r9)     // Catch:{ all -> 0x0253 }
            boolean r2 = r11.zzbjv     // Catch:{ all -> 0x0253 }
            if (r2 == 0) goto L_0x018b
            android.view.View r2 = r11.zzbju     // Catch:{ all -> 0x0253 }
            if (r2 != 0) goto L_0x017a
            android.view.View r2 = new android.view.View     // Catch:{ all -> 0x0253 }
            android.widget.FrameLayout r4 = r11.zzbjt     // Catch:{ all -> 0x0253 }
            android.content.Context r4 = r4.getContext()     // Catch:{ all -> 0x0253 }
            r2.<init>(r4)     // Catch:{ all -> 0x0253 }
            r11.zzbju = r2     // Catch:{ all -> 0x0253 }
            android.widget.FrameLayout$LayoutParams r4 = new android.widget.FrameLayout$LayoutParams     // Catch:{ all -> 0x0253 }
            r4.<init>(r10, r3)     // Catch:{ all -> 0x0253 }
            r2.setLayoutParams(r4)     // Catch:{ all -> 0x0253 }
        L_0x017a:
            android.widget.FrameLayout r2 = r11.zzbjt     // Catch:{ all -> 0x0253 }
            android.view.View r4 = r11.zzbju     // Catch:{ all -> 0x0253 }
            android.view.ViewParent r4 = r4.getParent()     // Catch:{ all -> 0x0253 }
            if (r2 == r4) goto L_0x018b
            android.widget.FrameLayout r2 = r11.zzbjt     // Catch:{ all -> 0x0253 }
            android.view.View r4 = r11.zzbju     // Catch:{ all -> 0x0253 }
            r2.addView(r4)     // Catch:{ all -> 0x0253 }
        L_0x018b:
            com.google.android.gms.internal.ads.zzaqw r2 = r12.zzko()     // Catch:{ Exception -> 0x0190 }
            goto L_0x01a6
        L_0x0190:
            r2 = move-exception
            com.google.android.gms.ads.internal.zzbv.zzem()     // Catch:{ all -> 0x0253 }
            boolean r4 = com.google.android.gms.internal.ads.zzakq.zzrp()     // Catch:{ all -> 0x0253 }
            if (r4 == 0) goto L_0x01a0
            java.lang.String r2 = "Privileged processes cannot create HTML overlays."
            com.google.android.gms.internal.ads.zzakb.zzdk(r2)     // Catch:{ all -> 0x0253 }
            goto L_0x01a5
        L_0x01a0:
            java.lang.String r4 = "Error obtaining overlay."
            com.google.android.gms.internal.ads.zzakb.zzb(r4, r2)     // Catch:{ all -> 0x0253 }
        L_0x01a5:
            r2 = r1
        L_0x01a6:
            if (r2 == 0) goto L_0x01b5
            android.widget.FrameLayout r4 = r11.zzvh     // Catch:{ all -> 0x0253 }
            if (r4 == 0) goto L_0x01b5
            android.widget.FrameLayout r4 = r11.zzvh     // Catch:{ all -> 0x0253 }
            android.view.View r2 = r2.getView()     // Catch:{ all -> 0x0253 }
            r4.addView(r2)     // Catch:{ all -> 0x0253 }
        L_0x01b5:
            java.lang.Object r2 = r11.mLock     // Catch:{ all -> 0x0253 }
            monitor-enter(r2)     // Catch:{ all -> 0x0253 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r11.zzbjw     // Catch:{ all -> 0x0250 }
            r12.zzf(r4)     // Catch:{ all -> 0x0250 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4 = r11.zzbjw     // Catch:{ all -> 0x0250 }
            if (r4 == 0) goto L_0x01dc
            java.lang.String[] r4 = zzbjs     // Catch:{ all -> 0x0250 }
            int r5 = r4.length     // Catch:{ all -> 0x0250 }
        L_0x01c4:
            if (r3 >= r5) goto L_0x01dc
            r6 = r4[r3]     // Catch:{ all -> 0x0250 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r7 = r11.zzbjw     // Catch:{ all -> 0x0250 }
            java.lang.Object r6 = r7.get(r6)     // Catch:{ all -> 0x0250 }
            java.lang.ref.WeakReference r6 = (java.lang.ref.WeakReference) r6     // Catch:{ all -> 0x0250 }
            if (r6 == 0) goto L_0x01d9
            java.lang.Object r1 = r6.get()     // Catch:{ all -> 0x0250 }
            android.view.View r1 = (android.view.View) r1     // Catch:{ all -> 0x0250 }
            goto L_0x01dc
        L_0x01d9:
            int r3 = r3 + 1
            goto L_0x01c4
        L_0x01dc:
            boolean r3 = r1 instanceof android.widget.FrameLayout     // Catch:{ all -> 0x0250 }
            if (r3 != 0) goto L_0x01e5
            r12.zzkq()     // Catch:{ all -> 0x0250 }
        L_0x01e3:
            monitor-exit(r2)     // Catch:{ all -> 0x0250 }
            goto L_0x01f6
        L_0x01e5:
            com.google.android.gms.internal.ads.zzpo r3 = new com.google.android.gms.internal.ads.zzpo     // Catch:{ all -> 0x0250 }
            r3.<init>(r11, r1)     // Catch:{ all -> 0x0250 }
            boolean r4 = r12 instanceof com.google.android.gms.internal.ads.zzoy     // Catch:{ all -> 0x0250 }
            if (r4 == 0) goto L_0x01f2
            r12.zzb((android.view.View) r1, (com.google.android.gms.internal.ads.zzox) r3)     // Catch:{ all -> 0x0250 }
            goto L_0x01e3
        L_0x01f2:
            r12.zza((android.view.View) r1, (com.google.android.gms.internal.ads.zzox) r3)     // Catch:{ all -> 0x0250 }
            goto L_0x01e3
        L_0x01f6:
            android.widget.FrameLayout r1 = r11.zzbjt     // Catch:{ all -> 0x0253 }
            r12.zzi(r1)     // Catch:{ all -> 0x0253 }
            android.widget.FrameLayout r12 = r11.zzbjt     // Catch:{ all -> 0x0253 }
            r11.zzl(r12)     // Catch:{ all -> 0x0253 }
            com.google.android.gms.internal.ads.zzoz r12 = r11.zzbij     // Catch:{ all -> 0x0253 }
            android.widget.FrameLayout r1 = r11.zzbjt     // Catch:{ all -> 0x0253 }
            r12.zzj(r1)     // Catch:{ all -> 0x0253 }
            com.google.android.gms.internal.ads.zzoz r12 = r11.zzbij     // Catch:{ all -> 0x0253 }
            boolean r12 = r12 instanceof com.google.android.gms.internal.ads.zzpd     // Catch:{ all -> 0x0253 }
            if (r12 == 0) goto L_0x024e
            com.google.android.gms.internal.ads.zzoz r12 = r11.zzbij     // Catch:{ all -> 0x0253 }
            com.google.android.gms.internal.ads.zzpd r12 = (com.google.android.gms.internal.ads.zzpd) r12     // Catch:{ all -> 0x0253 }
            if (r12 == 0) goto L_0x024e
            android.content.Context r1 = r12.getContext()     // Catch:{ all -> 0x0253 }
            if (r1 == 0) goto L_0x024e
            com.google.android.gms.internal.ads.zzaiy r1 = com.google.android.gms.ads.internal.zzbv.zzfh()     // Catch:{ all -> 0x0253 }
            android.widget.FrameLayout r2 = r11.zzbjt     // Catch:{ all -> 0x0253 }
            android.content.Context r2 = r2.getContext()     // Catch:{ all -> 0x0253 }
            boolean r1 = r1.zzu(r2)     // Catch:{ all -> 0x0253 }
            if (r1 == 0) goto L_0x024e
            java.lang.ref.WeakReference<com.google.android.gms.internal.ads.zzfp> r1 = r11.zzbkb     // Catch:{ all -> 0x0253 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0253 }
            com.google.android.gms.internal.ads.zzfp r1 = (com.google.android.gms.internal.ads.zzfp) r1     // Catch:{ all -> 0x0253 }
            if (r1 != 0) goto L_0x0247
            com.google.android.gms.internal.ads.zzfp r1 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ all -> 0x0253 }
            android.widget.FrameLayout r2 = r11.zzbjt     // Catch:{ all -> 0x0253 }
            android.content.Context r2 = r2.getContext()     // Catch:{ all -> 0x0253 }
            android.widget.FrameLayout r3 = r11.zzbjt     // Catch:{ all -> 0x0253 }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x0253 }
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0253 }
            r2.<init>(r1)     // Catch:{ all -> 0x0253 }
            r11.zzbkb = r2     // Catch:{ all -> 0x0253 }
        L_0x0247:
            com.google.android.gms.internal.ads.zzaix r12 = r12.zzks()     // Catch:{ all -> 0x0253 }
            r1.zza((com.google.android.gms.internal.ads.zzft) r12)     // Catch:{ all -> 0x0253 }
        L_0x024e:
            monitor-exit(r0)     // Catch:{ all -> 0x0253 }
            return
        L_0x0250:
            r12 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0250 }
            throw r12     // Catch:{ all -> 0x0253 }
        L_0x0253:
            r12 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0253 }
            goto L_0x0257
        L_0x0256:
            throw r12
        L_0x0257:
            goto L_0x0256
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpn.zza(com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.view.View} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.dynamic.IObjectWrapper zzak(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r3.zzbjw     // Catch:{ all -> 0x0022 }
            r2 = 0
            if (r1 != 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            return r2
        L_0x000a:
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r3.zzbjw     // Catch:{ all -> 0x0022 }
            java.lang.Object r4 = r1.get(r4)     // Catch:{ all -> 0x0022 }
            java.lang.ref.WeakReference r4 = (java.lang.ref.WeakReference) r4     // Catch:{ all -> 0x0022 }
            if (r4 != 0) goto L_0x0015
            goto L_0x001c
        L_0x0015:
            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x0022 }
            r2 = r4
            android.view.View r2 = (android.view.View) r2     // Catch:{ all -> 0x0022 }
        L_0x001c:
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r2)     // Catch:{ all -> 0x0022 }
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            return r4
        L_0x0022:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpn.zzak(java.lang.String):com.google.android.gms.dynamic.IObjectWrapper");
    }

    public final void zzb(IObjectWrapper iObjectWrapper, int i) {
        WeakReference<zzfp> weakReference;
        zzfp zzfp;
        if (!(!zzbv.zzfh().zzu(this.zzbjt.getContext()) || (weakReference = this.zzbkb) == null || (zzfp = (zzfp) weakReference.get()) == null)) {
            zzfp.zzgm();
        }
        zzkt();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(java.lang.String r4, com.google.android.gms.dynamic.IObjectWrapper r5) {
        /*
            r3 = this;
            java.lang.Object r5 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r5)
            android.view.View r5 = (android.view.View) r5
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r3.zzbjw     // Catch:{ all -> 0x0040 }
            if (r1 != 0) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x000f:
            if (r5 != 0) goto L_0x0017
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r3.zzbjw     // Catch:{ all -> 0x0040 }
            r5.remove(r4)     // Catch:{ all -> 0x0040 }
            goto L_0x003c
        L_0x0017:
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r3.zzbjw     // Catch:{ all -> 0x0040 }
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0040 }
            r2.<init>(r5)     // Catch:{ all -> 0x0040 }
            r1.put(r4, r2)     // Catch:{ all -> 0x0040 }
            java.lang.String r1 = "1098"
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0040 }
            if (r1 != 0) goto L_0x003e
            java.lang.String r1 = "3011"
            boolean r4 = r1.equals(r4)     // Catch:{ all -> 0x0040 }
            if (r4 == 0) goto L_0x0032
            goto L_0x003e
        L_0x0032:
            r5.setOnTouchListener(r3)     // Catch:{ all -> 0x0040 }
            r4 = 1
            r5.setClickable(r4)     // Catch:{ all -> 0x0040 }
            r5.setOnClickListener(r3)     // Catch:{ all -> 0x0040 }
        L_0x003c:
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x003e:
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x0040:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpn.zzb(java.lang.String, com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    public final void zzc(IObjectWrapper iObjectWrapper) {
        this.zzbij.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
