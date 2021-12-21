package com.google.android.play.core.assetpacks;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.play.core.internal.ag;
import com.google.android.play.core.internal.i;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

final class ca {

    /* renamed from: a  reason: collision with root package name */
    private static final ag f1064a = new ag("ExtractorSessionStoreView");
    private final au b;
    private final com.google.android.play.core.internal.ca<t> c;
    private final bo d;
    private final com.google.android.play.core.internal.ca<Executor> e;
    private final Map<Integer, bx> f = new HashMap();
    private final ReentrantLock g = new ReentrantLock();

    ca(au auVar, com.google.android.play.core.internal.ca<t> caVar, bo boVar, com.google.android.play.core.internal.ca<Executor> caVar2) {
        this.b = auVar;
        this.c = caVar;
        this.d = boVar;
        this.e = caVar2;
    }

    private final Map<String, bx> q(List<String> list) {
        return (Map) r(new bt(this, list, (byte[]) null));
    }

    private final <T> T r(bz<T> bzVar) {
        try {
            a();
            return bzVar.a();
        } finally {
            b();
        }
    }

    private final bx s(int i) {
        Map<Integer, bx> map = this.f;
        Integer valueOf = Integer.valueOf(i);
        bx bxVar = map.get(valueOf);
        if (bxVar != null) {
            return bxVar;
        }
        throw new bk(String.format("Could not find session %d while trying to get it", new Object[]{valueOf}), i);
    }

    private static String t(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
        if (stringArrayList != null && !stringArrayList.isEmpty()) {
            return stringArrayList.get(0);
        }
        throw new bk("Session without pack received.");
    }

    private static <T> List<T> u(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        this.g.lock();
    }

    /* access modifiers changed from: package-private */
    public final void b() {
        this.g.unlock();
    }

    /* access modifiers changed from: package-private */
    public final Map<Integer, bx> c() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public final boolean d(Bundle bundle) {
        return ((Boolean) r(new bq(this, bundle, (byte[]) null))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean e(Bundle bundle) {
        return ((Boolean) r(new bq(this, bundle))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final void f(String str, int i, long j) {
        r(new br(this, str, i, j));
    }

    /* access modifiers changed from: package-private */
    public final void g(int i) {
        r(new bs(this, i));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Map i(List list) {
        int i;
        Map<String, bx> q = q(list);
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            bx bxVar = q.get(str);
            if (bxVar == null) {
                i = 8;
            } else {
                if (ck.f(bxVar.c.c)) {
                    try {
                        bxVar.c.c = 6;
                        this.e.a().execute(new bu(this, bxVar));
                        this.d.a(str);
                    } catch (bk unused) {
                        f1064a.d("Session %d with pack %s does not exist, no need to cancel.", Integer.valueOf(bxVar.f1062a), str);
                    }
                }
                i = bxVar.c.c;
            }
            hashMap.put(str, Integer.valueOf(i));
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Map j(List list) {
        HashMap hashMap = new HashMap();
        for (bx next : this.f.values()) {
            String str = next.c.f1061a;
            if (list.contains(str)) {
                bx bxVar = (bx) hashMap.get(str);
                if ((bxVar == null ? -1 : bxVar.f1062a) < next.f1062a) {
                    hashMap.put(str, next);
                }
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Boolean k(Bundle bundle) {
        boolean z;
        int i = bundle.getInt("session_id");
        if (i == 0) {
            return true;
        }
        Map<Integer, bx> map = this.f;
        Integer valueOf = Integer.valueOf(i);
        if (!map.containsKey(valueOf)) {
            return true;
        }
        bx bxVar = this.f.get(valueOf);
        if (bxVar.c.c == 6) {
            z = false;
        } else {
            z = !ck.i(bxVar.c.c, bundle.getInt(i.e("status", t(bundle))));
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Boolean l(Bundle bundle) {
        by byVar;
        Bundle bundle2 = bundle;
        int i = bundle2.getInt("session_id");
        if (i == 0) {
            return false;
        }
        Map<Integer, bx> map = this.f;
        Integer valueOf = Integer.valueOf(i);
        boolean z = true;
        if (map.containsKey(valueOf)) {
            bx s = s(i);
            int i2 = bundle2.getInt(i.e("status", s.c.f1061a));
            if (ck.i(s.c.c, i2)) {
                f1064a.a("Found stale update for session %s with status %d.", valueOf, Integer.valueOf(s.c.c));
                bw bwVar = s.c;
                String str = bwVar.f1061a;
                int i3 = bwVar.c;
                if (i3 == 4) {
                    this.c.a().f(i, str);
                } else if (i3 == 5) {
                    this.c.a().g(i);
                } else if (i3 == 6) {
                    this.c.a().b(Arrays.asList(new String[]{str}));
                }
            } else {
                s.c.c = i2;
                if (ck.g(i2)) {
                    g(i);
                    this.d.a(s.c.f1061a);
                } else {
                    for (by next : s.c.e) {
                        ArrayList parcelableArrayList = bundle2.getParcelableArrayList(i.f("chunk_intents", s.c.f1061a, next.f1063a));
                        if (parcelableArrayList != null) {
                            for (int i4 = 0; i4 < parcelableArrayList.size(); i4++) {
                                if (!(parcelableArrayList.get(i4) == null || ((Intent) parcelableArrayList.get(i4)).getData() == null)) {
                                    next.d.get(i4).f1060a = true;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            String t = t(bundle);
            long j = bundle2.getLong(i.e("pack_version", t));
            int i5 = bundle2.getInt(i.e("status", t));
            long j2 = bundle2.getLong(i.e("total_bytes_to_download", t));
            ArrayList<String> stringArrayList = bundle2.getStringArrayList(i.e("slice_ids", t));
            ArrayList arrayList = new ArrayList();
            for (T t2 : u(stringArrayList)) {
                ArrayList parcelableArrayList2 = bundle2.getParcelableArrayList(i.f("chunk_intents", t, t2));
                ArrayList arrayList2 = new ArrayList();
                for (Intent intent : u(parcelableArrayList2)) {
                    if (intent == null) {
                        z = false;
                    }
                    arrayList2.add(new bv(z));
                    z = true;
                }
                String string = bundle2.getString(i.f("uncompressed_hash_sha256", t, t2));
                long j3 = bundle2.getLong(i.f("uncompressed_size", t, t2));
                int i6 = bundle2.getInt(i.f("patch_format", t, t2), 0);
                if (i6 != 0) {
                    byVar = new by(t2, string, j3, arrayList2, 0, i6);
                } else {
                    byVar = new by(t2, string, j3, arrayList2, bundle2.getInt(i.f("compression_format", t, t2), 0), 0);
                }
                arrayList.add(byVar);
                z = true;
            }
            this.f.put(Integer.valueOf(i), new bx(i, bundle2.getInt("app_version_code"), new bw(t, j, i5, j2, arrayList)));
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void m(String str, int i, long j) {
        bx bxVar = q(Arrays.asList(new String[]{str})).get(str);
        if (bxVar == null || ck.g(bxVar.c.c)) {
            f1064a.b(String.format("Could not find pack %s while trying to complete it", new Object[]{str}), new Object[0]);
        }
        this.b.B(str, i, j);
        bxVar.c.c = 4;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void n(int i) {
        s(i).c.c = 5;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void o(int i) {
        bx s = s(i);
        if (ck.g(s.c.c)) {
            au auVar = this.b;
            bw bwVar = s.c;
            auVar.B(bwVar.f1061a, s.b, bwVar.b);
            bw bwVar2 = s.c;
            int i2 = bwVar2.c;
            if (i2 == 5 || i2 == 6) {
                this.b.C(bwVar2.f1061a, s.b, bwVar2.b);
                return;
            }
            return;
        }
        throw new bk(String.format("Could not safely delete session %d because it is not in a terminal state.", new Object[]{Integer.valueOf(i)}), i);
    }

    /* access modifiers changed from: package-private */
    public final void p(int i) {
        r(new bs(this, i, (byte[]) null));
    }
}
