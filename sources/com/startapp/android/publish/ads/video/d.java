package com.startapp.android.publish.ads.video;

import android.content.Context;
import android.util.Base64;
import com.startapp.android.publish.ads.video.c;
import com.startapp.android.publish.ads.video.g;
import com.startapp.android.publish.adsCommon.b;
import com.startapp.android.publish.cache.h;
import com.startapp.common.a.e;
import com.startapp.common.g;
import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: StartAppSDK */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static d f132a = new d();
    private LinkedList<h> b = new LinkedList<>();

    private d() {
    }

    public void a(Context context, String str, g.a aVar, c.a aVar2) {
        final Context context2 = context;
        final String str2 = str;
        final g.a aVar3 = aVar;
        final c.a aVar4 = aVar2;
        com.startapp.common.g.a(g.a.HIGH, (Runnable) new Runnable() {
            public void run() {
                d.this.b(context2, str2, aVar3, aVar4);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(final Context context, String str, final g.a aVar, final c.a aVar2) {
        String str2;
        com.startapp.common.a.g.a("VideoAdCacheManager", 3, "Full cache: " + str);
        a(context);
        try {
            URL url = new URL(str);
            String str3 = url.getHost() + url.getPath().replace("/", "_");
            try {
                String substring = str3.substring(0, str3.lastIndexOf(46));
                str2 = new String(Base64.encodeToString(MessageDigest.getInstance("MD5").digest(substring.getBytes()), 0)).replaceAll("[^a-zA-Z0-9]+", "_") + str3.substring(str3.lastIndexOf(46));
            } catch (NoSuchAlgorithmException e) {
                com.startapp.common.a.g.a("VideoAdCacheManager", 6, "Error applying MD5 digest to filename " + str3, e);
                str2 = str3;
            }
            final h hVar = new h(str2);
            new g(context, url, str2, new g.a() {
                public void a(String str) {
                    g.a aVar = aVar;
                    if (aVar != null) {
                        aVar.a(str);
                    }
                    if (str != null) {
                        hVar.a(System.currentTimeMillis());
                        hVar.a(str);
                        d.this.a(context, hVar);
                    }
                }
            }, new c.a() {
                public void a(String str) {
                    c.a aVar = aVar2;
                    if (aVar != null) {
                        aVar.a(str);
                    }
                }
            }).a();
        } catch (MalformedURLException e2) {
            com.startapp.common.a.g.a("VideoAdCacheManager", 6, "Malformed url " + str, e2);
            if (aVar != null) {
                aVar.a((String) null);
            }
        }
    }

    public boolean a(int i) {
        Iterator it = this.b.iterator();
        boolean z = false;
        while (it.hasNext() && this.b.size() > i) {
            h hVar = (h) it.next();
            if (!h.a(hVar.b())) {
                z = true;
                it.remove();
                if (hVar.b() != null) {
                    new File(hVar.b()).delete();
                    com.startapp.common.a.g.a("VideoAdCacheManager", 3, "cachedVideoAds reached the maximum of " + i + " videos - removed " + hVar.a() + " Size = " + this.b.size());
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void a(Context context, h hVar) {
        if (this.b.contains(hVar)) {
            this.b.remove(hVar);
            com.startapp.common.a.g.a("VideoAdCacheManager", 3, "cachedVideoAds already contained " + hVar.a() + " - removed. Size = " + this.b.size());
        }
        a(b.a().H().b() - 1);
        this.b.add(hVar);
        b(context);
        com.startapp.common.a.g.a("VideoAdCacheManager", 3, "Added " + hVar.a() + " to cachedVideoAds. Size = " + this.b.size());
    }

    private void a(Context context) {
        if (this.b == null) {
            LinkedList<h> linkedList = (LinkedList) e.a(context, "CachedAds", LinkedList.class);
            this.b = linkedList;
            if (linkedList == null) {
                this.b = new LinkedList<>();
            }
            if (a(b.a().H().b())) {
                b(context);
            }
        }
    }

    private void b(Context context) {
        e.b(context, "CachedAds", (Serializable) this.b);
    }

    public static d a() {
        return f132a;
    }
}
