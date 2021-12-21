package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzoy extends zzpd {
    private Object mLock;
    private zzxz zzbit;
    private zzyc zzbiu;
    private zzyf zzbiv;
    private final zzpa zzbiw;
    private zzoz zzbix;
    private boolean zzbiy;

    private zzoy(Context context, zzpa zzpa, zzci zzci, zzpb zzpb) {
        super(context, zzpa, (zzacm) null, zzci, (JSONObject) null, zzpb, (zzang) null, (String) null);
        this.zzbiy = false;
        this.mLock = new Object();
        this.zzbiw = zzpa;
    }

    public zzoy(Context context, zzpa zzpa, zzci zzci, zzxz zzxz, zzpb zzpb) {
        this(context, zzpa, zzci, zzpb);
        this.zzbit = zzxz;
    }

    public zzoy(Context context, zzpa zzpa, zzci zzci, zzyc zzyc, zzpb zzpb) {
        this(context, zzpa, zzci, zzpb);
        this.zzbiu = zzyc;
    }

    public zzoy(Context context, zzpa zzpa, zzci zzci, zzyf zzyf, zzpb zzpb) {
        this(context, zzpa, zzci, zzpb);
        this.zzbiv = zzyf;
    }

    private static HashMap<String, View> zzb(Map<String, WeakReference<View>> map) {
        HashMap<String, View> hashMap = new HashMap<>();
        if (map == null) {
            return hashMap;
        }
        synchronized (map) {
            for (Map.Entry next : map.entrySet()) {
                View view = (View) ((WeakReference) next.getValue()).get();
                if (view != null) {
                    hashMap.put((String) next.getKey(), view);
                }
            }
        }
        return hashMap;
    }

    public final void cancelUnconfirmedClick() {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.cancelUnconfirmedClick();
            }
        }
    }

    public final void setClickConfirmingView(View view) {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.setClickConfirmingView(view);
            }
        }
    }

    public final View zza(View.OnClickListener onClickListener, boolean z) {
        IObjectWrapper iObjectWrapper;
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                View zza = this.zzbix.zza(onClickListener, z);
                return zza;
            }
            try {
                if (this.zzbiv != null) {
                    iObjectWrapper = this.zzbiv.zzmv();
                } else if (this.zzbit != null) {
                    iObjectWrapper = this.zzbit.zzmv();
                } else {
                    if (this.zzbiu != null) {
                        iObjectWrapper = this.zzbiu.zzmv();
                    }
                    iObjectWrapper = null;
                }
            } catch (RemoteException e) {
                zzakb.zzc("Failed to call getAdChoicesContent", e);
            }
            if (iObjectWrapper == null) {
                return null;
            }
            View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
            return view;
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map) {
        zzpa zzpa;
        Preconditions.checkMainThread("recordImpression must be called on the main UI thread.");
        synchronized (this.mLock) {
            this.zzbjd = true;
            if (this.zzbix != null) {
                this.zzbix.zza(view, map);
                this.zzbiw.recordImpression();
            } else {
                try {
                    if (this.zzbiv != null && !this.zzbiv.getOverrideImpressionRecording()) {
                        this.zzbiv.recordImpression();
                        zzpa = this.zzbiw;
                    } else if (this.zzbit != null && !this.zzbit.getOverrideImpressionRecording()) {
                        this.zzbit.recordImpression();
                        zzpa = this.zzbiw;
                    } else if (this.zzbiu != null && !this.zzbiu.getOverrideImpressionRecording()) {
                        this.zzbiu.recordImpression();
                        zzpa = this.zzbiw;
                    }
                    zzpa.recordImpression();
                } catch (RemoteException e) {
                    zzakb.zzc("Failed to call recordImpression", e);
                }
            }
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        zzpa zzpa;
        Preconditions.checkMainThread("performClick must be called on the main UI thread.");
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.zza(view, map, bundle, view2);
                this.zzbiw.onAdClicked();
            } else {
                try {
                    if (this.zzbiv != null && !this.zzbiv.getOverrideClickHandling()) {
                        this.zzbiv.zzj(ObjectWrapper.wrap(view));
                        zzpa = this.zzbiw;
                    } else if (this.zzbit != null && !this.zzbit.getOverrideClickHandling()) {
                        this.zzbit.zzj(ObjectWrapper.wrap(view));
                        zzpa = this.zzbiw;
                    } else if (this.zzbiu != null && !this.zzbiu.getOverrideClickHandling()) {
                        this.zzbiu.zzj(ObjectWrapper.wrap(view));
                        zzpa = this.zzbiw;
                    }
                    zzpa.onAdClicked();
                } catch (RemoteException e) {
                    zzakb.zzc("Failed to call performClick", e);
                }
            }
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        synchronized (this.mLock) {
            this.zzbiy = true;
            HashMap<String, View> zzb = zzb(map);
            HashMap<String, View> zzb2 = zzb(map2);
            try {
                if (this.zzbiv != null) {
                    this.zzbiv.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                } else if (this.zzbit != null) {
                    this.zzbit.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                    this.zzbit.zzk(ObjectWrapper.wrap(view));
                } else if (this.zzbiu != null) {
                    this.zzbiu.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                    this.zzbiu.zzk(ObjectWrapper.wrap(view));
                }
            } catch (RemoteException e) {
                zzakb.zzc("Failed to call prepareAd", e);
            }
            this.zzbiy = false;
        }
    }

    public final void zza(zzro zzro) {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.zza(zzro);
            }
        }
    }

    public final void zzb(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.mLock) {
            try {
                if (this.zzbiv != null) {
                    this.zzbiv.zzl(ObjectWrapper.wrap(view));
                } else if (this.zzbit != null) {
                    this.zzbit.zzl(ObjectWrapper.wrap(view));
                } else if (this.zzbiu != null) {
                    this.zzbiu.zzl(ObjectWrapper.wrap(view));
                }
            } catch (RemoteException e) {
                zzakb.zzc("Failed to call untrackView", e);
            }
        }
    }

    public final void zzc(zzoz zzoz) {
        synchronized (this.mLock) {
            this.zzbix = zzoz;
        }
    }

    public final void zzcr() {
        zzoz zzoz = this.zzbix;
        if (zzoz != null) {
            zzoz.zzcr();
        }
    }

    public final void zzcs() {
        zzoz zzoz = this.zzbix;
        if (zzoz != null) {
            zzoz.zzcs();
        }
    }

    public final boolean zzkj() {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                boolean zzkj = this.zzbix.zzkj();
                return zzkj;
            }
            boolean zzcu = this.zzbiw.zzcu();
            return zzcu;
        }
    }

    public final boolean zzkk() {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                boolean zzkk = this.zzbix.zzkk();
                return zzkk;
            }
            boolean zzcv = this.zzbiw.zzcv();
            return zzcv;
        }
    }

    public final void zzkl() {
        Preconditions.checkMainThread("recordDownloadedImpression must be called on main UI thread.");
        synchronized (this.mLock) {
            this.zzbje = true;
            if (this.zzbix != null) {
                this.zzbix.zzkl();
            }
        }
    }

    public final boolean zzkm() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzbiy;
        }
        return z;
    }

    public final zzoz zzkn() {
        zzoz zzoz;
        synchronized (this.mLock) {
            zzoz = this.zzbix;
        }
        return zzoz;
    }

    public final zzaqw zzko() {
        return null;
    }

    public final void zzkp() {
    }

    public final void zzkq() {
    }
}
