package com.startapp.android.publish.cache;

import com.startapp.android.publish.adsCommon.m;
import com.startapp.common.a.g;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class b extends e {
    private final FailuresHandler b = d.a().b().getFailuresHandler();
    private int c = 0;
    private boolean d = false;

    /* access modifiers changed from: protected */
    public String e() {
        return "CacheErrorReloadTimer";
    }

    public b(g gVar) {
        super(gVar);
    }

    public void a() {
        super.a();
        this.c = 0;
        this.d = false;
    }

    /* access modifiers changed from: protected */
    public void b() {
        j();
        super.b();
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        if (!m.a().l() || !k()) {
            return false;
        }
        if (this.d) {
            return this.b.isInfiniteLastRetry();
        }
        return true;
    }

    private void j() {
        if (this.c == this.b.getIntervals().size() - 1) {
            this.d = true;
            g.a("CacheErrorReloadTimer", 4, "Reached end index: " + this.c);
            return;
        }
        this.c++;
        g.a("CacheErrorReloadTimer", 4, "Advanced to index: " + this.c);
    }

    private boolean k() {
        FailuresHandler failuresHandler = this.b;
        return (failuresHandler == null || failuresHandler.getIntervals() == null) ? false : true;
    }

    /* access modifiers changed from: protected */
    public long d() {
        Long i;
        if (this.c >= this.b.getIntervals().size() || (i = i()) == null) {
            return -1;
        }
        long millis = TimeUnit.SECONDS.toMillis((long) this.b.getIntervals().get(this.c).intValue()) - (System.currentTimeMillis() - i.longValue());
        if (millis >= 0) {
            return millis;
        }
        return 0;
    }
}
