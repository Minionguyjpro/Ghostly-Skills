package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.mopub.common.AdType;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzzp extends zzzk {
    private final zzatg zzbvi;

    public zzzp(zzatg zzatg) {
        this.zzbvi = zzatg;
    }

    private static Bundle zzbt(String str) throws RemoteException {
        String valueOf = String.valueOf(str);
        zzane.zzdk(valueOf.length() != 0 ? "Server parameters: ".concat(valueOf) : new String("Server parameters: "));
        try {
            Bundle bundle = new Bundle();
            if (str == null) {
                return bundle;
            }
            JSONObject jSONObject = new JSONObject(str);
            Bundle bundle2 = new Bundle();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle2.putString(next, jSONObject.getString(next));
            }
            return bundle2;
        } catch (JSONException e) {
            zzane.zzb("", e);
            throw new RemoteException();
        }
    }

    public final zzlo getVideoController() {
        zzatg zzatg = this.zzbvi;
        if (!(zzatg instanceof zza)) {
            return null;
        }
        try {
            return ((zza) zzatg).getVideoController();
        } catch (Throwable th) {
            zzane.zzb("", th);
            return null;
        }
    }

    public final void showInterstitial() throws RemoteException {
        zzate zzate = null;
        try {
            zzate.zzoy();
        } catch (Throwable th) {
            zzane.zzb("", th);
            throw new RemoteException();
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.ads.zzzs, com.google.android.gms.internal.ads.zzatj] */
    public final void zza(IObjectWrapper iObjectWrapper, String str, Bundle bundle, zzzm zzzm) throws RemoteException {
        int i;
        try {
            ? zzzs = new zzzs(this, zzzm);
            zzatg zzatg = this.zzbvi;
            Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
            char c = 65535;
            switch (str.hashCode()) {
                case -1396342996:
                    if (str.equals("banner")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1052618729:
                    if (str.equals("native")) {
                        c = 3;
                        break;
                    }
                    break;
                case -239580146:
                    if (str.equals("rewarded")) {
                        c = 2;
                        break;
                    }
                    break;
                case 604727084:
                    if (str.equals(AdType.INTERSTITIAL)) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                i = zzath.zzdgn;
            } else if (c == 1) {
                i = zzath.zzdgo;
            } else if (c == 2) {
                i = zzath.zzdgp;
            } else if (c == 3) {
                i = zzath.zzdgq;
            } else {
                throw new IllegalArgumentException("Internal Error");
            }
            zzatg.zza(new zzati(context, i, bundle), zzzs);
        } catch (Throwable th) {
            zzane.zzb("Error generating signals for RTB", th);
            throw new RemoteException();
        }
    }

    public final void zza(byte[] bArr, String str, Bundle bundle, IObjectWrapper iObjectWrapper, zzzf zzzf, zzxt zzxt, zzjn zzjn) throws RemoteException {
        try {
            zzzq zzzq = new zzzq(this, zzzf, zzxt);
            zzatg zzatg = this.zzbvi;
            new zzatf((Context) ObjectWrapper.unwrap(iObjectWrapper), bArr, zzbt(str), bundle);
            zzb.zza(zzjn.width, zzjn.height, zzjn.zzarb);
            zzzq.zzau(String.valueOf(zzatg.getClass().getSimpleName()).concat(" does not support banner."));
        } catch (Throwable th) {
            zzane.zzb("Adapter failed to render banner ad.", th);
            throw new RemoteException();
        }
    }

    public final void zza(byte[] bArr, String str, Bundle bundle, IObjectWrapper iObjectWrapper, zzzh zzzh, zzxt zzxt) throws RemoteException {
        try {
            zzzr zzzr = new zzzr(this, zzzh, zzxt);
            zzatg zzatg = this.zzbvi;
            new zzatf((Context) ObjectWrapper.unwrap(iObjectWrapper), bArr, zzbt(str), bundle);
            zzzr.zzau(String.valueOf(zzatg.getClass().getSimpleName()).concat(" does not support interstitial."));
        } catch (Throwable th) {
            zzane.zzb("Adapter failed to render interstitial ad.", th);
            throw new RemoteException();
        }
    }

    public final zzzt zznc() throws RemoteException {
        return zzzt.zza(this.zzbvi.zzwa());
    }

    public final zzzt zznd() throws RemoteException {
        return zzzt.zza(this.zzbvi.zzvz());
    }
}
