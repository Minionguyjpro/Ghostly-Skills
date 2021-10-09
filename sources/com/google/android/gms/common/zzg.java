package com.google.android.gms.common;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
final class zzg extends zzd {
    private final byte[] zza;

    zzg(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.zza = bArr;
    }

    /* access modifiers changed from: package-private */
    public final byte[] zza() {
        return this.zza;
    }
}
