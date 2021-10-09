package com.appnext.base.operations;

import android.app.job.JobParameters;
import android.os.AsyncTask;

public class AsyncJobTask extends AsyncTask<Void, Void, Integer> {
    private final JobParameters jobParameters;
    private final AppnextOperationJobService jobService;

    /* access modifiers changed from: protected */
    public void onPostExecute(Integer num) {
    }

    public AsyncJobTask(AppnextOperationJobService appnextOperationJobService, JobParameters jobParameters2) {
        this.jobService = appnextOperationJobService;
        this.jobParameters = jobParameters2;
    }

    /* access modifiers changed from: protected */
    public Integer doInBackground(Void... voidArr) {
        return Integer.valueOf(this.jobService.onRunJob(this.jobParameters));
    }

    public void finishJob() {
        this.jobService.jobFinished(this.jobParameters, false);
    }
}
