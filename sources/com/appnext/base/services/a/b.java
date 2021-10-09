package com.appnext.base.services.a;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import com.appnext.base.a.b.c;
import com.appnext.base.b.a;
import com.appnext.base.services.OperationJobService;
import java.util.List;

public final class b extends c {
    private static final int eF = 900000;
    private JobScheduler eG;
    private Context mContext;

    public b(Context context) {
        try {
            Context applicationContext = context.getApplicationContext();
            this.mContext = applicationContext;
            this.eG = (JobScheduler) applicationContext.getSystemService("jobscheduler");
        } catch (Throwable unused) {
        }
    }

    public final void b(c cVar) {
        try {
            this.eG.cancel(cVar.ap().hashCode());
        } catch (Throwable unused) {
        }
    }

    public final void g(List<c> list) {
        try {
            this.eG.cancelAll();
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public final void a(c cVar, long j, long j2) {
        a(cVar, j, j2, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    public final void b(c cVar, long j, long j2) {
        a(cVar, j, 86400000, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    public final void a(c cVar, long j, Bundle bundle) {
        a(cVar, j, 0, bundle);
    }

    private void a(c cVar, long j, long j2, Bundle bundle) {
        PersistableBundle a2;
        try {
            JobInfo.Builder requiredNetworkType = new JobInfo.Builder(cVar.ap().hashCode(), new ComponentName(this.mContext, OperationJobService.class)).setPersisted(true).setRequiredNetworkType(1);
            PersistableBundle e = com.appnext.base.b.c.e(cVar);
            if (!(bundle == null || (a2 = a.a(bundle)) == null)) {
                e.putPersistableBundle(c.eH, a2);
            }
            if (j2 > 0 && j2 < 900000) {
                j2 = 900000;
            }
            if (j > System.currentTimeMillis()) {
                requiredNetworkType.setMinimumLatency(Math.max(j - System.currentTimeMillis(), 60000));
                e.putString(OperationJobService.SCHEDULE, "true");
            } else if (j2 > 0 && (Build.VERSION.SDK_INT < 24 || j2 >= 900000)) {
                requiredNetworkType.setPeriodic(j2);
            }
            requiredNetworkType.setExtras(e);
            this.eG.schedule(requiredNetworkType.build());
        } catch (Throwable unused) {
        }
    }
}
