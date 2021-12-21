package com.google.android.gms.internal.ads;

import android.graphics.Point;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzpp extends zzqg implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    static final String[] zzbjs = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private final Object mLock = new Object();
    private zzoz zzbij;
    private View zzbjx;
    private Point zzbjz = new Point();
    private Point zzbka = new Point();
    private WeakReference<zzfp> zzbkb = new WeakReference<>((Object) null);
    private final WeakReference<View> zzbke;
    private final Map<String, WeakReference<View>> zzbkf = new HashMap();
    private final Map<String, WeakReference<View>> zzbkg = new HashMap();
    private final Map<String, WeakReference<View>> zzbkh = new HashMap();

    public zzpp(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        zzbv.zzfg();
        zzaor.zza(view, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzbv.zzfg();
        zzaor.zza(view, (ViewTreeObserver.OnScrollChangedListener) this);
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
        this.zzbke = new WeakReference<>(view);
        for (Map.Entry next : hashMap.entrySet()) {
            String str = (String) next.getKey();
            View view2 = (View) next.getValue();
            if (view2 != null) {
                this.zzbkf.put(str, new WeakReference(view2));
                if (!NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str) && !UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str)) {
                    view2.setOnTouchListener(this);
                    view2.setClickable(true);
                    view2.setOnClickListener(this);
                }
            }
        }
        this.zzbkh.putAll(this.zzbkf);
        for (Map.Entry next2 : hashMap2.entrySet()) {
            View view3 = (View) next2.getValue();
            if (view3 != null) {
                this.zzbkg.put((String) next2.getKey(), new WeakReference(view3));
                view3.setOnTouchListener(this);
            }
        }
        this.zzbkh.putAll(this.zzbkg);
        zznk.initialize(view.getContext());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.zzpd r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            java.lang.String[] r1 = zzbjs     // Catch:{ all -> 0x003b }
            int r2 = r1.length     // Catch:{ all -> 0x003b }
            r3 = 0
        L_0x0007:
            if (r3 >= r2) goto L_0x001f
            r4 = r1[r3]     // Catch:{ all -> 0x003b }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r6.zzbkh     // Catch:{ all -> 0x003b }
            java.lang.Object r4 = r5.get(r4)     // Catch:{ all -> 0x003b }
            java.lang.ref.WeakReference r4 = (java.lang.ref.WeakReference) r4     // Catch:{ all -> 0x003b }
            if (r4 == 0) goto L_0x001c
            java.lang.Object r1 = r4.get()     // Catch:{ all -> 0x003b }
            android.view.View r1 = (android.view.View) r1     // Catch:{ all -> 0x003b }
            goto L_0x0020
        L_0x001c:
            int r3 = r3 + 1
            goto L_0x0007
        L_0x001f:
            r1 = 0
        L_0x0020:
            boolean r2 = r1 instanceof android.widget.FrameLayout     // Catch:{ all -> 0x003b }
            if (r2 != 0) goto L_0x0029
            r7.zzkq()     // Catch:{ all -> 0x003b }
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x0029:
            com.google.android.gms.internal.ads.zzpr r2 = new com.google.android.gms.internal.ads.zzpr     // Catch:{ all -> 0x003b }
            r2.<init>(r6, r1)     // Catch:{ all -> 0x003b }
            boolean r3 = r7 instanceof com.google.android.gms.internal.ads.zzoy     // Catch:{ all -> 0x003b }
            if (r3 == 0) goto L_0x0036
            r7.zzb((android.view.View) r1, (com.google.android.gms.internal.ads.zzox) r2)     // Catch:{ all -> 0x003b }
            goto L_0x0039
        L_0x0036:
            r7.zza((android.view.View) r1, (com.google.android.gms.internal.ads.zzox) r2)     // Catch:{ all -> 0x003b }
        L_0x0039:
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x003b:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            goto L_0x003f
        L_0x003e:
            throw r7
        L_0x003f:
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpp.zza(com.google.android.gms.internal.ads.zzpd):void");
    }

    /* access modifiers changed from: private */
    public final boolean zza(String[] strArr) {
        for (String str : strArr) {
            if (this.zzbkf.get(str) != null) {
                return true;
            }
        }
        for (String str2 : strArr) {
            if (this.zzbkg.get(str2) != null) {
                return false;
            }
        }
        return false;
    }

    private final void zzl(View view) {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                zzoz zzkn = this.zzbij instanceof zzoy ? ((zzoy) this.zzbij).zzkn() : this.zzbij;
                if (zzkn != null) {
                    zzkn.zzl(view);
                }
            }
        }
    }

    private final int zzv(int i) {
        int zzb;
        synchronized (this.mLock) {
            zzkb.zzif();
            zzb = zzamu.zzb(this.zzbij.getContext(), i);
        }
        return zzb;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzoz r1 = r8.zzbij     // Catch:{ all -> 0x0090 }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0090 }
            return
        L_0x0009:
            java.lang.ref.WeakReference<android.view.View> r1 = r8.zzbke     // Catch:{ all -> 0x0090 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0090 }
            r7 = r1
            android.view.View r7 = (android.view.View) r7     // Catch:{ all -> 0x0090 }
            if (r7 != 0) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x0090 }
            return
        L_0x0016:
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ all -> 0x0090 }
            r5.<init>()     // Catch:{ all -> 0x0090 }
            java.lang.String r1 = "x"
            android.graphics.Point r2 = r8.zzbjz     // Catch:{ all -> 0x0090 }
            int r2 = r2.x     // Catch:{ all -> 0x0090 }
            int r2 = r8.zzv(r2)     // Catch:{ all -> 0x0090 }
            float r2 = (float) r2     // Catch:{ all -> 0x0090 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r1 = "y"
            android.graphics.Point r2 = r8.zzbjz     // Catch:{ all -> 0x0090 }
            int r2 = r2.y     // Catch:{ all -> 0x0090 }
            int r2 = r8.zzv(r2)     // Catch:{ all -> 0x0090 }
            float r2 = (float) r2     // Catch:{ all -> 0x0090 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r1 = "start_x"
            android.graphics.Point r2 = r8.zzbka     // Catch:{ all -> 0x0090 }
            int r2 = r2.x     // Catch:{ all -> 0x0090 }
            int r2 = r8.zzv(r2)     // Catch:{ all -> 0x0090 }
            float r2 = (float) r2     // Catch:{ all -> 0x0090 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r1 = "start_y"
            android.graphics.Point r2 = r8.zzbka     // Catch:{ all -> 0x0090 }
            int r2 = r2.y     // Catch:{ all -> 0x0090 }
            int r2 = r8.zzv(r2)     // Catch:{ all -> 0x0090 }
            float r2 = (float) r2     // Catch:{ all -> 0x0090 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0090 }
            android.view.View r1 = r8.zzbjx     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x0087
            android.view.View r1 = r8.zzbjx     // Catch:{ all -> 0x0090 }
            boolean r1 = r1.equals(r9)     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x0087
            com.google.android.gms.internal.ads.zzoz r1 = r8.zzbij     // Catch:{ all -> 0x0090 }
            boolean r1 = r1 instanceof com.google.android.gms.internal.ads.zzoy     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x0080
            com.google.android.gms.internal.ads.zzoz r1 = r8.zzbij     // Catch:{ all -> 0x0090 }
            com.google.android.gms.internal.ads.zzoy r1 = (com.google.android.gms.internal.ads.zzoy) r1     // Catch:{ all -> 0x0090 }
            com.google.android.gms.internal.ads.zzoz r1 = r1.zzkn()     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x008e
            com.google.android.gms.internal.ads.zzoz r1 = r8.zzbij     // Catch:{ all -> 0x0090 }
            com.google.android.gms.internal.ads.zzoy r1 = (com.google.android.gms.internal.ads.zzoy) r1     // Catch:{ all -> 0x0090 }
            com.google.android.gms.internal.ads.zzoz r2 = r1.zzkn()     // Catch:{ all -> 0x0090 }
            java.lang.String r4 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r8.zzbkh     // Catch:{ all -> 0x0090 }
        L_0x007b:
            r3 = r9
            r2.zza(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0090 }
            goto L_0x008e
        L_0x0080:
            com.google.android.gms.internal.ads.zzoz r2 = r8.zzbij     // Catch:{ all -> 0x0090 }
            java.lang.String r4 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r8.zzbkh     // Catch:{ all -> 0x0090 }
            goto L_0x007b
        L_0x0087:
            com.google.android.gms.internal.ads.zzoz r1 = r8.zzbij     // Catch:{ all -> 0x0090 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r8.zzbkh     // Catch:{ all -> 0x0090 }
            r1.zza(r9, r2, r5, r7)     // Catch:{ all -> 0x0090 }
        L_0x008e:
            monitor-exit(r0)     // Catch:{ all -> 0x0090 }
            return
        L_0x0090:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0090 }
            goto L_0x0094
        L_0x0093:
            throw r9
        L_0x0094:
            goto L_0x0093
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpp.onClick(android.view.View):void");
    }

    public final void onGlobalLayout() {
        View view;
        synchronized (this.mLock) {
            if (!(this.zzbij == null || (view = (View) this.zzbke.get()) == null)) {
                this.zzbij.zzc(view, this.zzbkh);
            }
        }
    }

    public final void onScrollChanged() {
        View view;
        synchronized (this.mLock) {
            if (!(this.zzbij == null || (view = (View) this.zzbke.get()) == null)) {
                this.zzbij.zzc(view, this.zzbkh);
            }
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                return false;
            }
            View view2 = (View) this.zzbke.get();
            if (view2 == null) {
                return false;
            }
            int[] iArr = new int[2];
            view2.getLocationOnScreen(iArr);
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

    public final void unregisterNativeAd() {
        synchronized (this.mLock) {
            this.zzbjx = null;
            this.zzbij = null;
            this.zzbjz = null;
            this.zzbka = null;
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper) {
        int i;
        KeyEvent.Callback callback;
        synchronized (this.mLock) {
            ViewGroup viewGroup = null;
            zzl((View) null);
            Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
            if (!(unwrap instanceof zzpd)) {
                zzakb.zzdk("Not an instance of native engine. This is most likely a transient error");
                return;
            }
            zzpd zzpd = (zzpd) unwrap;
            if (!zzpd.zzkk()) {
                zzakb.e("Your account must be enabled to use this feature. Talk to your account manager to request this feature for your account.");
                return;
            }
            View view = (View) this.zzbke.get();
            if (!(this.zzbij == null || view == null)) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbbu)).booleanValue()) {
                    this.zzbij.zzb(view, this.zzbkh);
                }
            }
            synchronized (this.mLock) {
                i = 0;
                if (this.zzbij instanceof zzpd) {
                    zzpd zzpd2 = (zzpd) this.zzbij;
                    View view2 = (View) this.zzbke.get();
                    if (!(zzpd2 == null || zzpd2.getContext() == null || view2 == null || !zzbv.zzfh().zzu(view2.getContext()))) {
                        zzaix zzks = zzpd2.zzks();
                        if (zzks != null) {
                            zzks.zzx(false);
                        }
                        zzfp zzfp = (zzfp) this.zzbkb.get();
                        if (!(zzfp == null || zzks == null)) {
                            zzfp.zzb(zzks);
                        }
                    }
                }
            }
            if (!(this.zzbij instanceof zzoy) || !((zzoy) this.zzbij).zzkm()) {
                this.zzbij = zzpd;
                if (zzpd instanceof zzoy) {
                    ((zzoy) zzpd).zzc((zzoz) null);
                }
            } else {
                ((zzoy) this.zzbij).zzc(zzpd);
            }
            String[] strArr = {NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW};
            while (true) {
                if (i >= 2) {
                    callback = null;
                    break;
                }
                WeakReference weakReference = this.zzbkh.get(strArr[i]);
                if (weakReference != null) {
                    callback = (View) weakReference.get();
                    break;
                }
                i++;
            }
            if (callback == null) {
                zzakb.zzdk("Ad choices asset view is not provided.");
            } else {
                if (callback instanceof ViewGroup) {
                    viewGroup = (ViewGroup) callback;
                }
                if (viewGroup != null) {
                    View zza = zzpd.zza((View.OnClickListener) this, true);
                    this.zzbjx = zza;
                    if (zza != null) {
                        this.zzbkh.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference(this.zzbjx));
                        this.zzbkf.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference(this.zzbjx));
                        viewGroup.removeAllViews();
                        viewGroup.addView(this.zzbjx);
                    }
                }
            }
            zzpd.zza(view, this.zzbkf, this.zzbkg, (View.OnTouchListener) this, (View.OnClickListener) this);
            zzakk.zzcrm.post(new zzpq(this, zzpd));
            zzl(view);
            this.zzbij.zzj(view);
            synchronized (this.mLock) {
                if (this.zzbij instanceof zzpd) {
                    zzpd zzpd3 = (zzpd) this.zzbij;
                    View view3 = (View) this.zzbke.get();
                    if (!(zzpd3 == null || zzpd3.getContext() == null || view3 == null || !zzbv.zzfh().zzu(view3.getContext()))) {
                        zzfp zzfp2 = (zzfp) this.zzbkb.get();
                        if (zzfp2 == null) {
                            zzfp2 = new zzfp(view3.getContext(), view3);
                            this.zzbkb = new WeakReference<>(zzfp2);
                        }
                        zzfp2.zza((zzft) zzpd3.zzks());
                    }
                }
            }
        }
    }

    public final void zzc(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzbij.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }
}
