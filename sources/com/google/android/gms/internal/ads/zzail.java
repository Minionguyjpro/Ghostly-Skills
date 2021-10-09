package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

final class zzail implements Runnable {
    private final /* synthetic */ Bitmap val$bitmap;
    private final /* synthetic */ zzaii zzcna;

    zzail(zzaii zzaii, Bitmap bitmap) {
        this.zzcna = zzaii;
        this.val$bitmap = bitmap;
    }

    public final void run() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.val$bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        synchronized (this.zzcna.mLock) {
            this.zzcna.zzcmn.zzecm = new zzbft();
            this.zzcna.zzcmn.zzecm.zzedl = byteArrayOutputStream.toByteArray();
            this.zzcna.zzcmn.zzecm.mimeType = "image/png";
            this.zzcna.zzcmn.zzecm.zzamf = 1;
        }
    }
}
