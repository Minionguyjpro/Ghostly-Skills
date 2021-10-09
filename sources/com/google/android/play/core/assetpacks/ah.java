package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.play.core.tasks.i;

final class ah extends ag<ParcelFileDescriptor> {
    ah(an anVar, i<ParcelFileDescriptor> iVar) {
        super(anVar, iVar);
    }

    public final void e(Bundle bundle, Bundle bundle2) throws RemoteException {
        super.e(bundle, bundle2);
        this.f1028a.e((ParcelFileDescriptor) bundle.getParcelable("chunk_file_descriptor"));
    }
}
