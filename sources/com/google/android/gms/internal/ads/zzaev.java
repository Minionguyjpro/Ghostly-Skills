package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.IOUtils;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;

@zzadh
public final class zzaev extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaev> CREATOR = new zzaex();
    private ParcelFileDescriptor zzcft;
    private Parcelable zzcfu;
    private boolean zzcfv;

    public zzaev(ParcelFileDescriptor parcelFileDescriptor) {
        this.zzcft = parcelFileDescriptor;
        this.zzcfu = null;
        this.zzcfv = true;
    }

    public zzaev(SafeParcelable safeParcelable) {
        this.zzcft = null;
        this.zzcfu = safeParcelable;
        this.zzcfv = false;
    }

    private final <T> ParcelFileDescriptor zze(byte[] bArr) {
        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
        try {
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(createPipe[1]);
            try {
                new Thread(new zzaew(this, autoCloseOutputStream, bArr)).start();
                return createPipe[0];
            } catch (IOException e) {
                e = e;
                zzakb.zzb("Error transporting the ad response", e);
                zzbv.zzeo().zza(e, "LargeParcelTeleporter.pipeData.2");
                IOUtils.closeQuietly((Closeable) autoCloseOutputStream);
                return null;
            }
        } catch (IOException e2) {
            e = e2;
            autoCloseOutputStream = null;
            zzakb.zzb("Error transporting the ad response", e);
            zzbv.zzeo().zza(e, "LargeParcelTeleporter.pipeData.2");
            IOUtils.closeQuietly((Closeable) autoCloseOutputStream);
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    private final ParcelFileDescriptor zzoc() {
        if (this.zzcft == null) {
            Parcel obtain = Parcel.obtain();
            try {
                this.zzcfu.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                obtain.recycle();
                this.zzcft = zze(marshall);
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        }
        return this.zzcft;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        zzoc();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzcft, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX INFO: finally extract failed */
    public final <T extends SafeParcelable> T zza(Parcelable.Creator<T> creator) {
        if (this.zzcfv) {
            if (this.zzcft == null) {
                zzakb.e("File descriptor is empty, returning null.");
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(this.zzcft));
            try {
                int readInt = dataInputStream.readInt();
                byte[] bArr = new byte[readInt];
                dataInputStream.readFully(bArr, 0, readInt);
                IOUtils.closeQuietly((Closeable) dataInputStream);
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.unmarshall(bArr, 0, readInt);
                    obtain.setDataPosition(0);
                    this.zzcfu = (SafeParcelable) creator.createFromParcel(obtain);
                    obtain.recycle();
                    this.zzcfv = false;
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (IOException e) {
                zzakb.zzb("Could not read from parcel file descriptor", e);
                IOUtils.closeQuietly((Closeable) dataInputStream);
                return null;
            } catch (Throwable th2) {
                IOUtils.closeQuietly((Closeable) dataInputStream);
                throw th2;
            }
        }
        return (SafeParcelable) this.zzcfu;
    }
}
