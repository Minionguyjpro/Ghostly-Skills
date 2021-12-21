package com.integralads.avid.library.mopub.walking.async;

import com.integralads.avid.library.mopub.walking.async.AvidAsyncTask;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AvidAsyncTaskQueue implements AvidAsyncTask.AvidAsyncTaskListener {
    private AvidAsyncTask currentTask = null;
    private final ArrayDeque<AvidAsyncTask> pendingTasks = new ArrayDeque<>();
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, this.workQueue);
    private final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue();

    public void submitTask(AvidAsyncTask avidAsyncTask) {
        avidAsyncTask.setListener(this);
        this.pendingTasks.add(avidAsyncTask);
        if (this.currentTask == null) {
            executeNextTask();
        }
    }

    private void executeNextTask() {
        AvidAsyncTask poll = this.pendingTasks.poll();
        this.currentTask = poll;
        if (poll != null) {
            poll.start(this.threadPoolExecutor);
        }
    }

    public void onTaskCompleted(AvidAsyncTask avidAsyncTask) {
        this.currentTask = null;
        executeNextTask();
    }
}
