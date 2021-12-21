package com.startapp.android.publish.ads.video.c.a;

import android.content.Context;
import android.util.DisplayMetrics;
import com.startapp.android.publish.ads.video.c.a.a.b;
import com.startapp.common.a.h;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* compiled from: StartAppSDK */
public class c {

    /* renamed from: a  reason: collision with root package name */
    protected int f126a;
    protected int b;
    /* access modifiers changed from: private */
    public int c;

    public c(Context context) {
        a(context);
    }

    public b a(List<b> list) {
        if (list == null || c(list) == 0) {
            return null;
        }
        Collections.sort(list, a());
        return b(list);
    }

    /* access modifiers changed from: protected */
    public Comparator<b> a() {
        return new a();
    }

    private void a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f126a = displayMetrics.widthPixels;
        int i = displayMetrics.heightPixels;
        this.b = i;
        this.c = this.f126a * i;
        if (!h.a(context).equals("WIFI")) {
            this.c = (int) (((float) this.c) * 0.75f);
        }
    }

    /* compiled from: StartAppSDK */
    private class a implements Comparator<b> {
        private a() {
        }

        /* renamed from: a */
        public int compare(b bVar, b bVar2) {
            int intValue = bVar.d().intValue() * bVar.e().intValue();
            int intValue2 = bVar2.d().intValue() * bVar2.e().intValue();
            int abs = Math.abs(intValue - c.this.c);
            int abs2 = Math.abs(intValue2 - c.this.c);
            if (abs < abs2) {
                return -1;
            }
            return abs > abs2 ? 1 : 0;
        }
    }

    /* access modifiers changed from: protected */
    public b b(List<b> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    private boolean a(b bVar) {
        return bVar.b().matches("video/.*(?i)(mp4|3gpp|mp2t|webm|matroska)");
    }

    /* access modifiers changed from: protected */
    public int c(List<b> list) {
        Iterator<b> it = list.iterator();
        while (it.hasNext()) {
            b next = it.next();
            if (!next.f() || !a(next)) {
                it.remove();
            }
        }
        return list.size();
    }
}
