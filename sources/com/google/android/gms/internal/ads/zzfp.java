package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbv;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;

@zzadh
public final class zzfp implements Application.ActivityLifecycleCallbacks, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final long zzagc = ((Long) zzkb.zzik().zzd(zznk.zzazt)).longValue();
    private zzamj zzadz = new zzamj(zzagc);
    private final Context zzaeo;
    private final WindowManager zzaeu;
    private final PowerManager zzaev;
    private final KeyguardManager zzaew;
    private boolean zzafd = false;
    private BroadcastReceiver zzafe;
    private final Rect zzafh;
    private Application zzagd;
    private WeakReference<ViewTreeObserver> zzage;
    private WeakReference<View> zzagf;
    private zzfu zzagg;
    private int zzagh = -1;
    private final HashSet<zzft> zzagi = new HashSet<>();
    private final DisplayMetrics zzagj;

    public zzfp(Context context, View view) {
        this.zzaeo = context.getApplicationContext();
        this.zzaeu = (WindowManager) context.getSystemService("window");
        this.zzaev = (PowerManager) this.zzaeo.getSystemService("power");
        this.zzaew = (KeyguardManager) context.getSystemService("keyguard");
        Context context2 = this.zzaeo;
        if (context2 instanceof Application) {
            this.zzagd = (Application) context2;
            this.zzagg = new zzfu((Application) context2, this);
        }
        this.zzagj = context.getResources().getDisplayMetrics();
        Rect rect = new Rect();
        this.zzafh = rect;
        rect.right = this.zzaeu.getDefaultDisplay().getWidth();
        this.zzafh.bottom = this.zzaeu.getDefaultDisplay().getHeight();
        WeakReference<View> weakReference = this.zzagf;
        View view2 = weakReference != null ? (View) weakReference.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzf(view2);
        }
        this.zzagf = new WeakReference<>(view);
        if (view != null) {
            if (zzbv.zzem().isAttachedToWindow(view)) {
                zze(view);
            }
            view.addOnAttachStateChangeListener(this);
        }
    }

    private final Rect zza(Rect rect) {
        return new Rect(zzn(rect.left), zzn(rect.top), zzn(rect.right), zzn(rect.bottom));
    }

    private final void zza(Activity activity, int i) {
        Window window;
        if (this.zzagf != null && (window = activity.getWindow()) != null) {
            View peekDecorView = window.peekDecorView();
            View view = (View) this.zzagf.get();
            if (view != null && peekDecorView != null && view.getRootView() == peekDecorView.getRootView()) {
                this.zzagh = i;
            }
        }
    }

    private final void zzao() {
        zzbv.zzek();
        zzakk.zzcrm.post(new zzfq(this));
    }

    private final void zze(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zzage = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zzafe == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzafe = new zzfr(this);
            zzbv.zzfk().zza(this.zzaeo, this.zzafe, intentFilter);
        }
        Application application = this.zzagd;
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(this.zzagg);
            } catch (Exception e) {
                zzakb.zzb("Error registering activity lifecycle callbacks.", e);
            }
        }
    }

    private final void zzf(View view) {
        try {
            if (this.zzage != null) {
                ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzage.get();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnScrollChangedListener(this);
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                this.zzage = null;
            }
        } catch (Exception e) {
            zzakb.zzb("Error while unregistering listeners from the last ViewTreeObserver.", e);
        }
        try {
            ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
            if (viewTreeObserver2.isAlive()) {
                viewTreeObserver2.removeOnScrollChangedListener(this);
                viewTreeObserver2.removeGlobalOnLayoutListener(this);
            }
        } catch (Exception e2) {
            zzakb.zzb("Error while unregistering listeners from the ViewTreeObserver.", e2);
        }
        if (this.zzafe != null) {
            try {
                zzbv.zzfk().zza(this.zzaeo, this.zzafe);
            } catch (IllegalStateException e3) {
                zzakb.zzb("Failed trying to unregister the receiver", e3);
            } catch (Exception e4) {
                zzbv.zzeo().zza(e4, "ActiveViewUnit.stopScreenStatusMonitoring");
            }
            this.zzafe = null;
        }
        Application application = this.zzagd;
        if (application != null) {
            try {
                application.unregisterActivityLifecycleCallbacks(this.zzagg);
            } catch (Exception e5) {
                zzakb.zzb("Error registering activity lifecycle callbacks.", e5);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zzm(int i) {
        WeakReference<View> weakReference;
        boolean z;
        boolean z2;
        int i2 = i;
        if (this.zzagi.size() != 0 && (weakReference = this.zzagf) != null) {
            View view = (View) weakReference.get();
            boolean z3 = i2 == 1;
            boolean z4 = view == null;
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            Rect rect3 = new Rect();
            Rect rect4 = new Rect();
            int[] iArr = new int[2];
            int[] iArr2 = new int[2];
            if (view != null) {
                boolean globalVisibleRect = view.getGlobalVisibleRect(rect2);
                boolean localVisibleRect = view.getLocalVisibleRect(rect3);
                view.getHitRect(rect4);
                try {
                    view.getLocationOnScreen(iArr);
                    view.getLocationInWindow(iArr2);
                } catch (Exception e) {
                    zzakb.zzb("Failure getting view location.", e);
                }
                rect.left = iArr[0];
                rect.top = iArr[1];
                rect.right = rect.left + view.getWidth();
                rect.bottom = rect.top + view.getHeight();
                z2 = globalVisibleRect;
                z = localVisibleRect;
            } else {
                z2 = false;
                z = false;
            }
            int windowVisibility = view != null ? view.getWindowVisibility() : 8;
            int i3 = this.zzagh;
            if (i3 != -1) {
                windowVisibility = i3;
            }
            boolean z5 = !z4 && zzbv.zzek().zza(view, this.zzaev, this.zzaew) && z2 && z && windowVisibility == 0;
            if (z3 && !this.zzadz.tryAcquire() && z5 == this.zzafd) {
                return;
            }
            if (z5 || this.zzafd || i2 != 1) {
                zzfs zzfs = new zzfs(zzbv.zzer().elapsedRealtime(), this.zzaev.isScreenOn(), view != null ? zzbv.zzem().isAttachedToWindow(view) : false, view != null ? view.getWindowVisibility() : 8, zza(this.zzafh), zza(rect), zza(rect2), z2, zza(rect3), z, zza(rect4), this.zzagj.density, z5);
                Iterator<zzft> it = this.zzagi.iterator();
                while (it.hasNext()) {
                    it.next().zza(zzfs);
                }
                this.zzafd = z5;
            }
        }
    }

    private final int zzn(int i) {
        return (int) (((float) i) / this.zzagj.density);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzm(3);
        zzao();
    }

    public final void onActivityDestroyed(Activity activity) {
        zzm(3);
        zzao();
    }

    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzm(3);
        zzao();
    }

    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzm(3);
        zzao();
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzm(3);
        zzao();
    }

    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzm(3);
        zzao();
    }

    public final void onActivityStopped(Activity activity) {
        zzm(3);
        zzao();
    }

    public final void onGlobalLayout() {
        zzm(2);
        zzao();
    }

    public final void onScrollChanged() {
        zzm(1);
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzagh = -1;
        zze(view);
        zzm(3);
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zzagh = -1;
        zzm(3);
        zzao();
        zzf(view);
    }

    public final void zza(zzft zzft) {
        this.zzagi.add(zzft);
        zzm(3);
    }

    public final void zzb(zzft zzft) {
        this.zzagi.remove(zzft);
    }

    public final void zzgm() {
        zzm(4);
    }
}
