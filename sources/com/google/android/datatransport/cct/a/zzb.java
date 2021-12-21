package com.google.android.datatransport.cct.a;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
public final class zzb implements ObjectEncoder<zzd> {
    public void encode(Object obj, Object obj2) throws EncodingException, IOException {
        zzd zzd = (zzd) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        if (zzd.zzi() != Integer.MIN_VALUE) {
            objectEncoderContext.add("sdkVersion", zzd.zzi());
        }
        if (zzd.zzf() != null) {
            objectEncoderContext.add("model", (Object) zzd.zzf());
        }
        if (zzd.zzd() != null) {
            objectEncoderContext.add("hardware", (Object) zzd.zzd());
        }
        if (zzd.zzb() != null) {
            objectEncoderContext.add("device", (Object) zzd.zzb());
        }
        if (zzd.zzh() != null) {
            objectEncoderContext.add("product", (Object) zzd.zzh());
        }
        if (zzd.zzg() != null) {
            objectEncoderContext.add("osBuild", (Object) zzd.zzg());
        }
        if (zzd.zze() != null) {
            objectEncoderContext.add("manufacturer", (Object) zzd.zze());
        }
        if (zzd.zzc() != null) {
            objectEncoderContext.add("fingerprint", (Object) zzd.zzc());
        }
    }
}
