package com.google.android.datatransport.cct.a;

import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;

/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
public class zzs {
    public static DataEncoder zza() {
        return new JsonDataEncoderBuilder().registerEncoder(zze.class, new zzp()).registerEncoder(zzk.class, new zzw()).registerEncoder(zzg.class, new zzr()).registerEncoder(zzi.class, new zzu()).registerEncoder(zzd.class, new zzb()).registerEncoder(zzn.class, new zzz()).build();
    }
}
