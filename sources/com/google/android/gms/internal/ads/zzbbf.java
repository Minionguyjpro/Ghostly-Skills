package com.google.android.gms.internal.ads;

final class zzbbf {
    private static final zzbbd<?> zzdqv = new zzbbe();
    private static final zzbbd<?> zzdqw = zzacs();

    private static zzbbd<?> zzacs() {
        try {
            return (zzbbd) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzbbd<?> zzact() {
        return zzdqv;
    }

    static zzbbd<?> zzacu() {
        zzbbd<?> zzbbd = zzdqw;
        if (zzbbd != null) {
            return zzbbd;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
