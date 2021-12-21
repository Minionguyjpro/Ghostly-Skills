package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;
import com.google.android.gms.internal.ads.zzbbo.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzbbo<MessageType extends zzbbo<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzazy<MessageType, BuilderType> {
    private static Map<Object, zzbbo<?, ?>> zzdtv = new ConcurrentHashMap();
    protected zzbef zzdtt = zzbef.zzagc();
    private int zzdtu = -1;

    public static abstract class zza<MessageType extends zzbbo<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzazz<MessageType, BuilderType> {
        private final MessageType zzdtw;
        protected MessageType zzdtx;
        private boolean zzdty = false;

        protected zza(MessageType messagetype) {
            this.zzdtw = messagetype;
            this.zzdtx = (zzbbo) messagetype.zza(zze.zzdud, (Object) null, (Object) null);
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzbdg.zzaeo().zzab(messagetype).zzc(messagetype, messagetype2);
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zza = (zza) ((zzbbo) this.zzdtw).zza(zze.zzdue, (Object) null, (Object) null);
            if (!this.zzdty) {
                MessageType messagetype = this.zzdtx;
                zzbdg.zzaeo().zzab(messagetype).zzo(messagetype);
                this.zzdty = true;
            }
            zza.zza((zzbbo) this.zzdtx);
            return zza;
        }

        public final boolean isInitialized() {
            return zzbbo.zza(this.zzdtx, false);
        }

        public final BuilderType zza(MessageType messagetype) {
            zzadh();
            zza(this.zzdtx, messagetype);
            return this;
        }

        public final /* synthetic */ zzazz zzaax() {
            return (zza) clone();
        }

        public final /* synthetic */ zzbcu zzadg() {
            return this.zzdtw;
        }

        /* access modifiers changed from: protected */
        public final void zzadh() {
            if (this.zzdty) {
                MessageType messagetype = (zzbbo) this.zzdtx.zza(zze.zzdud, (Object) null, (Object) null);
                zza(messagetype, this.zzdtx);
                this.zzdtx = messagetype;
                this.zzdty = false;
            }
        }

        public final MessageType zzadi() {
            boolean z = true;
            if (!this.zzdty) {
                MessageType messagetype = this.zzdtx;
                zzbdg.zzaeo().zzab(messagetype).zzo(messagetype);
                this.zzdty = true;
            }
            MessageType messagetype2 = (zzbbo) this.zzdtx;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) messagetype2.zza(zze.zzdua, (Object) null, (Object) null)).byteValue();
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzbdg.zzaeo().zzab(messagetype2).zzaa(messagetype2);
                    if (booleanValue) {
                        messagetype2.zza(zze.zzdub, (Object) z ? messagetype2 : null, (Object) null);
                    }
                }
            }
            if (z) {
                return messagetype2;
            }
            throw new zzbed(messagetype2);
        }

        public final /* synthetic */ zzbcu zzadj() {
            if (this.zzdty) {
                return this.zzdtx;
            }
            MessageType messagetype = this.zzdtx;
            zzbdg.zzaeo().zzab(messagetype).zzo(messagetype);
            this.zzdty = true;
            return this.zzdtx;
        }

        public final /* synthetic */ zzbcu zzadk() {
            boolean z = true;
            if (!this.zzdty) {
                MessageType messagetype = this.zzdtx;
                zzbdg.zzaeo().zzab(messagetype).zzo(messagetype);
                this.zzdty = true;
            }
            zzbbo zzbbo = (zzbbo) this.zzdtx;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zzbbo.zza(zze.zzdua, (Object) null, (Object) null)).byteValue();
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzbdg.zzaeo().zzab(zzbbo).zzaa(zzbbo);
                    if (booleanValue) {
                        zzbbo.zza(zze.zzdub, (Object) z ? zzbbo : null, (Object) null);
                    }
                }
            }
            if (z) {
                return zzbbo;
            }
            throw new zzbed(zzbbo);
        }
    }

    public static class zzb<T extends zzbbo<T, ?>> extends zzbaa<T> {
        private T zzdtw;

        public zzb(T t) {
            this.zzdtw = t;
        }
    }

    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzbbo<MessageType, BuilderType> implements zzbcw {
        protected zzbbg<Object> zzdtz = zzbbg.zzacv();
    }

    public static class zzd<ContainingType extends zzbcu, Type> extends zzbaz<ContainingType, Type> {
    }

    /* 'enum' modifier removed */
    public static final class zze {
        public static final int zzdua = 1;
        public static final int zzdub = 2;
        public static final int zzduc = 3;
        public static final int zzdud = 4;
        public static final int zzdue = 5;
        public static final int zzduf = 6;
        public static final int zzdug = 7;
        private static final /* synthetic */ int[] zzduh = {1, 2, 3, 4, 5, 6, 7};
        public static final int zzdui = 1;
        public static final int zzduj = 2;
        private static final /* synthetic */ int[] zzduk = {1, 2};
        public static final int zzdul = 1;
        public static final int zzdum = 2;
        private static final /* synthetic */ int[] zzdun = {1, 2};

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            return (int[]) zzduh.clone();
        }
    }

    protected static <T extends zzbbo<T, ?>> T zza(T t, zzbah zzbah) throws zzbbu {
        boolean z;
        T zza2 = zza(t, zzbah, zzbbb.zzacr());
        boolean z2 = false;
        if (zza2 != null) {
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zza2.zza(zze.zzdua, (Object) null, (Object) null)).byteValue();
            if (byteValue == 1) {
                z = true;
            } else if (byteValue == 0) {
                z = false;
            } else {
                z = zzbdg.zzaeo().zzab(zza2).zzaa(zza2);
                if (booleanValue) {
                    zza2.zza(zze.zzdub, (Object) z ? zza2 : null, (Object) null);
                }
            }
            if (!z) {
                throw new zzbed(zza2).zzaga().zzj(zza2);
            }
        }
        if (zza2 != null) {
            boolean booleanValue2 = Boolean.TRUE.booleanValue();
            byte byteValue2 = ((Byte) zza2.zza(zze.zzdua, (Object) null, (Object) null)).byteValue();
            if (byteValue2 == 1) {
                z2 = true;
            } else if (byteValue2 != 0) {
                z2 = zzbdg.zzaeo().zzab(zza2).zzaa(zza2);
                if (booleanValue2) {
                    zza2.zza(zze.zzdub, (Object) z2 ? zza2 : null, (Object) null);
                }
            }
            if (!z2) {
                throw new zzbed(zza2).zzaga().zzj(zza2);
            }
        }
        return zza2;
    }

    private static <T extends zzbbo<T, ?>> T zza(T t, zzbah zzbah, zzbbb zzbbb) throws zzbbu {
        T zza2;
        try {
            zzbaq zzabf = zzbah.zzabf();
            zza2 = zza(t, zzabf, zzbbb);
            zzabf.zzbp(0);
            return zza2;
        } catch (zzbbu e) {
            throw e.zzj(zza2);
        } catch (zzbbu e2) {
            throw e2;
        }
    }

    private static <T extends zzbbo<T, ?>> T zza(T t, zzbaq zzbaq, zzbbb zzbbb) throws zzbbu {
        T t2 = (zzbbo) t.zza(zze.zzdud, (Object) null, (Object) null);
        try {
            zzbdg.zzaeo().zzab(t2).zza(t2, zzbat.zza(zzbaq), zzbbb);
            zzbdg.zzaeo().zzab(t2).zzo(t2);
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzbbu) {
                throw ((zzbbu) e.getCause());
            }
            throw new zzbbu(e.getMessage()).zzj(t2);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzbbu) {
                throw ((zzbbu) e2.getCause());
            }
            throw e2;
        }
    }

    private static <T extends zzbbo<T, ?>> T zza(T t, byte[] bArr) throws zzbbu {
        T t2 = (zzbbo) t.zza(zze.zzdud, (Object) null, (Object) null);
        try {
            zzbdg.zzaeo().zzab(t2).zza(t2, bArr, 0, bArr.length, new zzbae());
            zzbdg.zzaeo().zzab(t2).zzo(t2);
            if (t2.zzdpf == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzbbu) {
                throw ((zzbbu) e.getCause());
            }
            throw new zzbbu(e.getMessage()).zzj(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzbbu.zzadl().zzj(t2);
        }
    }

    protected static Object zza(zzbcu zzbcu, String str, Object[] objArr) {
        return new zzbdi(zzbcu, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static <T extends zzbbo<?, ?>> void zza(Class<T> cls, T t) {
        zzdtv.put(cls, t);
    }

    protected static final <T extends zzbbo<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zze.zzdua, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        return zzbdg.zzaeo().zzab(t).zzaa(t);
    }

    protected static <E> zzbbt<E> zzadd() {
        return zzbdh.zzaep();
    }

    protected static <T extends zzbbo<T, ?>> T zzb(T t, byte[] bArr) throws zzbbu {
        T zza2 = zza(t, bArr);
        if (zza2 != null) {
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zza2.zza(zze.zzdua, (Object) null, (Object) null)).byteValue();
            boolean z = true;
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzbdg.zzaeo().zzab(zza2).zzaa(zza2);
                    if (booleanValue) {
                        zza2.zza(zze.zzdub, (Object) z ? zza2 : null, (Object) null);
                    }
                }
            }
            if (!z) {
                throw new zzbed(zza2).zzaga().zzj(zza2);
            }
        }
        return zza2;
    }

    static <T extends zzbbo<?, ?>> T zzc(Class<T> cls) {
        T t = (zzbbo) zzdtv.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzbbo) zzdtv.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t != null) {
            return t;
        }
        String valueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(valueOf.length() != 0 ? "Unable to get default instance for: ".concat(valueOf) : new String("Unable to get default instance for: "));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzbbo) zza(zze.zzduf, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return zzbdg.zzaeo().zzab(this).equals(this, (zzbbo) obj);
    }

    public int hashCode() {
        if (this.zzdpf != 0) {
            return this.zzdpf;
        }
        this.zzdpf = zzbdg.zzaeo().zzab(this).hashCode(this);
        return this.zzdpf;
    }

    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zze.zzdua, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzaa = zzbdg.zzaeo().zzab(this).zzaa(this);
        if (booleanValue) {
            zza(zze.zzdub, (Object) zzaa ? this : null, (Object) null);
        }
        return zzaa;
    }

    public String toString() {
        return zzbcx.zza(this, super.toString());
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public final int zzaaw() {
        return this.zzdtu;
    }

    public final int zzacw() {
        if (this.zzdtu == -1) {
            this.zzdtu = zzbdg.zzaeo().zzab(this).zzy(this);
        }
        return this.zzdtu;
    }

    public final /* synthetic */ zzbcv zzade() {
        zza zza2 = (zza) zza(zze.zzdue, (Object) null, (Object) null);
        zza2.zza(this);
        return zza2;
    }

    public final /* synthetic */ zzbcv zzadf() {
        return (zza) zza(zze.zzdue, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzbcu zzadg() {
        return (zzbbo) zza(zze.zzduf, (Object) null, (Object) null);
    }

    public final void zzb(zzbav zzbav) throws IOException {
        zzbdg.zzaeo().zze(getClass()).zza(this, zzbax.zza(zzbav));
    }

    /* access modifiers changed from: package-private */
    public final void zzbj(int i) {
        this.zzdtu = i;
    }
}
