package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import com.google.android.play.core.internal.ag;
import com.google.android.play.core.internal.aq;
import com.google.android.play.core.internal.bp;
import com.google.android.play.core.internal.t;
import com.google.android.play.core.splitcompat.p;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import com.google.android.play.core.tasks.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

final class an implements t {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final ag f1029a = new ag("AssetPackServiceImpl");
    private static final Intent b = new Intent("com.google.android.play.core.assetmoduleservice.BIND_ASSET_MODULE_SERVICE").setPackage("com.android.vending");
    /* access modifiers changed from: private */
    public final String c;
    private final bo d;
    /* access modifiers changed from: private */
    public aq<t> e;
    /* access modifiers changed from: private */
    public aq<t> f;
    /* access modifiers changed from: private */
    public final AtomicBoolean g = new AtomicBoolean();

    an(Context context, bo boVar) {
        this.c = context.getPackageName();
        this.d = boVar;
        if (bp.a(context)) {
            this.e = new aq(p.c(context), f1029a, "AssetPackService", b, u.b);
            this.f = new aq(p.c(context), f1029a, "AssetPackService-keepAlive", b, u.f1107a);
        }
        f1029a.a("AssetPackService initiated.", new Object[0]);
    }

    /* access modifiers changed from: private */
    public static Bundle A(int i, String str) {
        Bundle B = B(i);
        B.putString("module_name", str);
        return B;
    }

    /* access modifiers changed from: private */
    public static Bundle B(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", i);
        return bundle;
    }

    /* access modifiers changed from: private */
    public static Bundle C() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 11000);
        ArrayList arrayList = new ArrayList();
        arrayList.add(0);
        arrayList.add(1);
        bundle.putIntegerArrayList("supported_compression_formats", arrayList);
        bundle.putIntegerArrayList("supported_patch_formats", new ArrayList());
        return bundle;
    }

    static /* synthetic */ ArrayList k(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Bundle bundle = new Bundle();
            bundle.putString("module_name", (String) it.next());
            arrayList.add(bundle);
        }
        return arrayList;
    }

    static /* synthetic */ Bundle m(Map map) {
        Bundle C = C();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            Bundle bundle = new Bundle();
            bundle.putString("installed_asset_module_name", (String) entry.getKey());
            bundle.putLong("installed_asset_module_version", ((Long) entry.getValue()).longValue());
            arrayList.add(bundle);
        }
        C.putParcelableArrayList("installed_asset_module", arrayList);
        return C;
    }

    static /* synthetic */ Bundle r(int i, String str, String str2, int i2) {
        Bundle A = A(i, str);
        A.putString("slice_id", str2);
        A.putInt("chunk_number", i2);
        return A;
    }

    static /* synthetic */ List v(an anVar, List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AssetPackState next = AssetPackStates.b((Bundle) it.next(), anVar.d).packStates().values().iterator().next();
            if (next == null) {
                f1029a.b("onGetSessionStates: Bundle contained no pack.", new Object[0]);
            }
            if (ck.f(next.status())) {
                arrayList.add(next.name());
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public final void y(int i, String str, int i2) {
        if (this.e != null) {
            f1029a.d("notifyModuleCompleted", new Object[0]);
            i iVar = new i();
            this.e.a(new ac(this, iVar, i, str, iVar, i2));
            return;
        }
        throw new bk("The Play Store app is not installed or is an unofficial version.", i);
    }

    private static <T> Task<T> z() {
        f1029a.b("onError(%d)", -11);
        return Tasks.b(new AssetPackException(-11));
    }

    public final void b(List<String> list) {
        if (this.e != null) {
            f1029a.d("cancelDownloads(%s)", list);
            i iVar = new i();
            this.e.a(new y(this, iVar, list, iVar));
        }
    }

    public final Task<List<String>> c(Map<String, Long> map) {
        if (this.e == null) {
            return z();
        }
        f1029a.d("syncPacks", new Object[0]);
        i iVar = new i();
        this.e.a(new z(this, iVar, map, iVar));
        return iVar.c();
    }

    public final void e(int i, String str, String str2, int i2) {
        if (this.e != null) {
            f1029a.d("notifyChunkTransferred", new Object[0]);
            i iVar = new i();
            this.e.a(new ab(this, iVar, i, str, str2, i2, iVar));
            return;
        }
        throw new bk("The Play Store app is not installed or is an unofficial version.", i);
    }

    public final void f(int i, String str) {
        y(i, str, 10);
    }

    public final void g(int i) {
        if (this.e != null) {
            f1029a.d("notifySessionFailed", new Object[0]);
            i iVar = new i();
            this.e.a(new ad(this, iVar, i, iVar));
            return;
        }
        throw new bk("The Play Store app is not installed or is an unofficial version.", i);
    }

    public final Task<ParcelFileDescriptor> h(int i, String str, String str2, int i2) {
        if (this.e == null) {
            return z();
        }
        f1029a.d("getChunkFileDescriptor(%s, %s, %d, session=%d)", str, str2, Integer.valueOf(i2), Integer.valueOf(i));
        i iVar = new i();
        this.e.a(new ae(this, iVar, i, str, str2, i2, iVar));
        return iVar.c();
    }

    public final synchronized void j() {
        if (this.f == null) {
            f1029a.e("Keep alive connection manager is not initialized.", new Object[0]);
            return;
        }
        f1029a.d("keepAlive", new Object[0]);
        if (!this.g.compareAndSet(false, true)) {
            f1029a.d("Service is already kept alive.", new Object[0]);
            return;
        }
        i iVar = new i();
        this.f.a(new af(this, iVar, iVar));
    }
}
