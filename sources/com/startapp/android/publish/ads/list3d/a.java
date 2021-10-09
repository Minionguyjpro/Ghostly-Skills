package com.startapp.android.publish.ads.list3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.startapp.android.publish.adsCommon.i;
import com.startapp.common.a.d;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: StartAppSDK */
public class a {

    /* renamed from: a  reason: collision with root package name */
    HashMap<String, i> f65a = new HashMap<>();
    Hashtable<String, Bitmap> b = new Hashtable<>();
    Set<String> c = new HashSet();
    g d;
    int e = 0;
    ConcurrentLinkedQueue<b> f = new ConcurrentLinkedQueue<>();

    public void a(g gVar, boolean z) {
        this.d = gVar;
        if (z) {
            a();
        }
    }

    public void a() {
        this.c.clear();
        this.e = 0;
        this.f.clear();
        HashMap<String, i> hashMap = this.f65a;
        if (hashMap != null) {
            for (String str : hashMap.keySet()) {
                this.f65a.get(str).a(false);
            }
            this.f65a.clear();
        }
    }

    public void a(Context context, String str, String str2, com.startapp.android.publish.adsCommon.d.b bVar, long j) {
        if (!this.f65a.containsKey(str2)) {
            i iVar = new i(context, new String[]{str2}, bVar, j);
            this.f65a.put(str2, iVar);
            iVar.a();
        }
    }

    public void a(String str) {
        HashMap<String, i> hashMap = this.f65a;
        if (hashMap != null && hashMap.containsKey(str) && this.f65a.get(str) != null) {
            this.f65a.get(str).a(true);
        }
    }

    public void b() {
        for (String next : this.f65a.keySet()) {
            if (this.f65a.get(next) != null) {
                this.f65a.get(next).b();
            }
        }
    }

    public void c() {
        for (String next : this.f65a.keySet()) {
            if (this.f65a.get(next) != null) {
                this.f65a.get(next).a();
            }
        }
    }

    public void d() {
        for (String next : this.f65a.keySet()) {
            if (this.f65a.get(next) != null) {
                this.f65a.get(next).a(false);
            }
        }
    }

    public Bitmap a(int i, String str, String str2) {
        Bitmap bitmap = this.b.get(str);
        if (bitmap != null) {
            return bitmap;
        }
        if (this.c.contains(str)) {
            return null;
        }
        this.c.add(str);
        int i2 = this.e;
        if (i2 >= 15) {
            this.f.add(new b(i, str, str2));
            return null;
        }
        this.e = i2 + 1;
        new C0000a(i, str, str2).execute(new Void[0]);
        return null;
    }

    public void e() {
        if (!this.f.isEmpty()) {
            b poll = this.f.poll();
            new C0000a(poll.f67a, poll.b, poll.c).execute(new Void[0]);
        }
    }

    /* renamed from: com.startapp.android.publish.ads.list3d.a$a  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    private class C0000a extends AsyncTask<Void, Void, Bitmap> {

        /* renamed from: a  reason: collision with root package name */
        int f66a = -1;
        String b;
        String c;

        public C0000a(int i, String str, String str2) {
            this.f66a = i;
            this.b = str;
            this.c = str2;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Bitmap doInBackground(Void... voidArr) {
            return d.b(this.c);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Bitmap bitmap) {
            a aVar = a.this;
            aVar.e--;
            if (bitmap != null) {
                a.this.b.put(this.b, bitmap);
                if (a.this.d != null) {
                    a.this.d.a(this.f66a);
                }
                a.this.e();
            }
        }
    }

    /* compiled from: StartAppSDK */
    class b {

        /* renamed from: a  reason: collision with root package name */
        int f67a;
        String b;
        String c;

        public b(int i, String str, String str2) {
            this.f67a = i;
            this.b = str;
            this.c = str2;
        }
    }
}
