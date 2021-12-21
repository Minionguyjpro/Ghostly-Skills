package com.google.android.gms.dynamic;

import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zab implements OnDelegateCreatedListener<T> {
    private final /* synthetic */ DeferredLifecycleHelper zaa;

    zab(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zaa = deferredLifecycleHelper;
    }

    public final void onDelegateCreated(T t) {
        LifecycleDelegate unused = this.zaa.zaa = t;
        Iterator it = this.zaa.zac.iterator();
        while (it.hasNext()) {
            ((DeferredLifecycleHelper.zaa) it.next()).zaa(this.zaa.zaa);
        }
        this.zaa.zac.clear();
        Bundle unused2 = this.zaa.zab = null;
    }
}
