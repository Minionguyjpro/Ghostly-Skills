package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

@zzadh
public final class zzaed extends zzadz implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    private Context mContext;
    private final Object mLock = new Object();
    private zzaol<zzaef> zzccp;
    private final zzadx zzccq;
    private zzaee zzcct;
    private zzang zzyf;

    public zzaed(Context context, zzang zzang, zzaol<zzaef> zzaol, zzadx zzadx) {
        super(zzaol, zzadx);
        this.mContext = context;
        this.zzyf = zzang;
        this.zzccp = zzaol;
        this.zzccq = zzadx;
        zzaee zzaee = new zzaee(context, ((Boolean) zzkb.zzik().zzd(zznk.zzavz)).booleanValue() ? zzbv.zzez().zzsa() : context.getMainLooper(), this, this);
        this.zzcct = zzaee;
        zzaee.checkAvailabilityAndConnect();
    }

    public final void onConnected(Bundle bundle) {
        zznt();
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        zzakb.zzck("Cannot connect to remote service, fallback to local instance.");
        new zzaec(this.mContext, this.zzccp, this.zzccq).zznt();
        Bundle bundle = new Bundle();
        bundle.putString("action", "gms_connection_failed_fallback_to_local");
        zzbv.zzek().zzb(this.mContext, this.zzyf.zzcw, "gmob-apps", bundle, true);
    }

    public final void onConnectionSuspended(int i) {
        zzakb.zzck("Disconnected from remote ad request service.");
    }

    public final void zznz() {
        synchronized (this.mLock) {
            if (this.zzcct.isConnected() || this.zzcct.isConnecting()) {
                this.zzcct.disconnect();
            }
            Binder.flushPendingCommands();
        }
    }

    public final zzaen zzoa() {
        zzaen zzob;
        synchronized (this.mLock) {
            try {
                zzob = this.zzcct.zzob();
            } catch (DeadObjectException | IllegalStateException unused) {
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzob;
    }
}
