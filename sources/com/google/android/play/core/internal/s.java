package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;

public abstract class s extends k implements t {
    public static t b(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
        return queryLocalInterface instanceof t ? (t) queryLocalInterface : new r(iBinder);
    }
}
