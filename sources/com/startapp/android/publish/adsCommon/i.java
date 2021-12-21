package com.startapp.android.publish.adsCommon;

import android.content.Context;
import android.os.Handler;
import com.appnext.base.services.OperationJobService;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.d.b;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.common.a.g;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: StartAppSDK */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f257a = MetaData.getInstance().isSupportIABViewability();
    private Handler b = new Handler();
    private long c;
    private Context d;
    private long e = -1;
    private long f;
    private boolean g;
    private boolean h;
    private String[] i;
    private b j;
    private AtomicBoolean k = new AtomicBoolean(false);

    public i(Context context, String[] strArr, b bVar, long j2) {
        this.d = context.getApplicationContext();
        this.i = strArr;
        this.j = bVar;
        this.c = j2;
    }

    public void a() {
        g.a("ScheduledImpression", 4, OperationJobService.SCHEDULE);
        if (c()) {
            g.a("ScheduledImpression", 3, "Already sent impression. Must call cancel(true/false) to reschedule");
        } else if (f257a) {
            a(this.c);
        } else {
            g.a("ScheduledImpression", 3, "Delay feature disabled, sending impression now!");
            b(true);
        }
    }

    public void b() {
        if (this.g && this.h) {
            g.a("ScheduledImpression", 4, "pause");
            this.b.removeCallbacksAndMessages((Object) null);
            long currentTimeMillis = System.currentTimeMillis();
            this.e = currentTimeMillis;
            this.c -= currentTimeMillis - this.f;
            this.h = false;
        }
    }

    public void a(boolean z) {
        if (this.g) {
            g.a("ScheduledImpression", 4, "cancel(" + z + ")");
            b(z);
            d();
        }
    }

    public boolean c() {
        return this.k.get();
    }

    private void a(long j2) {
        if (!this.h) {
            this.h = true;
            if (!this.g) {
                this.g = true;
            }
            g.a("ScheduledImpression", 3, "Scheduling timer to: " + j2 + " millis, Num urls = " + this.i.length);
            this.f = System.currentTimeMillis();
            this.b.postDelayed(new Runnable() {
                public void run() {
                    g.a("ScheduledImpression", 4, "Timer elapsed");
                    i.this.b(true);
                }
            }, j2);
            return;
        }
        g.a("ScheduledImpression", 3, "Already running");
    }

    /* access modifiers changed from: protected */
    public void b(boolean z) {
        if (!this.k.compareAndSet(false, true)) {
            g.a("ScheduledImpression", 4, "Already sent");
        } else if (z) {
            g.a("ScheduledImpression", 3, "Sending impression");
            c.a(this.d, this.i, this.j);
        } else {
            g.a("ScheduledImpression", 3, "Sending non-impression");
            c.a(this.d, this.i, this.j.getAdTag(), AdDisplayListener.NotDisplayedReason.AD_CLOSED_TOO_QUICKLY.toString());
        }
    }

    private void d() {
        g.a("ScheduledImpression", 4, "reset");
        this.g = false;
        this.b.removeCallbacksAndMessages((Object) null);
        this.h = false;
        this.e = -1;
        this.f = 0;
    }
}
