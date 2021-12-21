package com.appnext.base.services;

import android.app.job.JobParameters;
import android.os.Bundle;
import android.os.PersistableBundle;
import com.appnext.base.a.b.c;
import com.appnext.base.b.e;
import com.appnext.base.b.i;
import com.appnext.base.operations.AppnextOperationJobService;
import com.appnext.base.operations.a;
import com.appnext.base.services.b.a;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class OperationJobService extends AppnextOperationJobService {
    public static final String SCHEDULE = "schedule";

    public int onRunJob(JobParameters jobParameters) {
        c cVar;
        PersistableBundle persistableBundle;
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            e.init(getApplicationContext());
            i.aR().init(getApplicationContext());
            PersistableBundle extras = jobParameters.getExtras();
            Bundle bundle = null;
            if (extras == null) {
                cVar = null;
            } else {
                String string = extras.getString("key", "");
                cVar = new c(extras.getString("status", ""), extras.getString(com.appnext.base.b.c.eQ, ""), extras.getString(com.appnext.base.b.c.eR, ""), extras.getString(com.appnext.base.b.c.eO, ""), extras.getString(com.appnext.base.b.c.eP, ""), string, extras.getString("service_key", ""), extras.getString(com.appnext.base.b.c.DATA, (String) null));
            }
            if (cVar == null) {
                b(jobParameters);
                return 0;
            } else if (a(extras)) {
                b(jobParameters);
                a.d(getApplicationContext()).a(cVar, true);
                return 0;
            } else {
                if (!(extras == null || (persistableBundle = extras.getPersistableBundle(com.appnext.base.services.a.c.eH)) == null)) {
                    bundle = new Bundle();
                    bundle.putAll(persistableBundle);
                }
                final JobParameters jobParameters2 = jobParameters;
                try {
                    new b().a(getApplicationContext(), cVar.getKey(), (String) null, bundle, (Object) null, new a.C0038a() {
                        public final void aH() {
                            OperationJobService.this.b(jobParameters2);
                            countDownLatch.countDown();
                        }

                        public final void b(com.appnext.base.a aVar) {
                            OperationJobService.this.b(jobParameters2);
                            countDownLatch.countDown();
                        }
                    });
                    countDownLatch.await(10, TimeUnit.SECONDS);
                } catch (Throwable unused) {
                }
                return 0;
            }
        } catch (Throwable unused2) {
        }
    }

    private static boolean a(PersistableBundle persistableBundle) {
        if (persistableBundle == null) {
            return false;
        }
        try {
            return Boolean.valueOf(persistableBundle.getString(SCHEDULE, "false")).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void b(JobParameters jobParameters) {
        try {
            a(jobParameters);
        } catch (Throwable unused) {
        }
    }
}
