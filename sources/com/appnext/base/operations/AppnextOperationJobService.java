package com.appnext.base.operations;

import android.app.job.JobParameters;
import android.app.job.JobService;
import java.util.HashMap;
import java.util.Map;

public abstract class AppnextOperationJobService extends JobService {
    private final Map<JobParameters, AsyncJobTask> ej = new HashMap();
    private AsyncJobTask ek;

    public abstract int onRunJob(JobParameters jobParameters);

    public boolean onStartJob(JobParameters jobParameters) {
        this.ek = new AsyncJobTask(this, jobParameters);
        synchronized (this.ej) {
            this.ej.put(jobParameters, this.ek);
        }
        this.ek.execute(new Void[0]);
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        synchronized (this.ej) {
            AsyncJobTask remove = this.ej.remove(jobParameters);
            if (remove == null) {
                return false;
            }
            remove.cancel(true);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final void a(JobParameters jobParameters) {
        synchronized (this.ej) {
            this.ej.remove(jobParameters);
        }
        this.ek.finishJob();
    }
}
