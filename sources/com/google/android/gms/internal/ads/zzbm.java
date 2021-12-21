package com.google.android.gms.internal.ads;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class zzbm implements Runnable {
    private zzbm() {
    }

    public final void run() {
        try {
            MessageDigest unused = zzbk.zzhz = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException unused2) {
        } catch (Throwable th) {
            zzbk.zzic.countDown();
            throw th;
        }
        zzbk.zzic.countDown();
    }
}
