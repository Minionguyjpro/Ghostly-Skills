package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzazy;
import com.google.android.gms.internal.ads.zzazz;

public abstract class zzazz<MessageType extends zzazy<MessageType, BuilderType>, BuilderType extends zzazz<MessageType, BuilderType>> implements zzbcv {
    /* access modifiers changed from: protected */
    public abstract BuilderType zza(MessageType messagetype);

    /* renamed from: zzaax */
    public abstract BuilderType clone();

    public final /* synthetic */ zzbcv zzd(zzbcu zzbcu) {
        if (zzadg().getClass().isInstance(zzbcu)) {
            return zza((zzazy) zzbcu);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
