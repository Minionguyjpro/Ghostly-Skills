package com.google.android.gms.internal.ads;

final class zzxc implements Runnable {
    private final /* synthetic */ zzxa zzbts;
    private final /* synthetic */ zzxb zzbtt;

    zzxc(zzxb zzxb, zzxa zzxa) {
        this.zzbtt = zzxb;
        this.zzbts = zzxa;
    }

    public final void run() {
        synchronized (this.zzbtt.mLock) {
            if (this.zzbtt.zzbtq == -2) {
                zzxq unused = this.zzbtt.zzbtp = this.zzbtt.zzmj();
                if (this.zzbtt.zzbtp == null) {
                    this.zzbtt.zzx(4);
                } else if (!this.zzbtt.zzmk() || this.zzbtt.zzy(1)) {
                    this.zzbts.zza((zzxf) this.zzbtt);
                    this.zzbtt.zza(this.zzbts);
                } else {
                    String zzf = this.zzbtt.zzbth;
                    StringBuilder sb = new StringBuilder(String.valueOf(zzf).length() + 56);
                    sb.append("Ignoring adapter ");
                    sb.append(zzf);
                    sb.append(" as delayed impression is not supported");
                    zzakb.zzdk(sb.toString());
                    this.zzbtt.zzx(2);
                }
            }
        }
    }
}
