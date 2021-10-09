package com.mopub.nativeads;

import com.mopub.common.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

abstract class TaskManager<T> {
    protected final AtomicInteger mCompletedCount = new AtomicInteger(0);
    protected final AtomicBoolean mFailed = new AtomicBoolean(false);
    protected final TaskManagerListener<T> mImageTaskManagerListener;
    protected final Map<String, T> mResults = Collections.synchronizedMap(new HashMap(this.mSize));
    protected final int mSize;

    interface TaskManagerListener<T> {
        void onFail();

        void onSuccess(Map<String, T> map);
    }

    /* access modifiers changed from: package-private */
    public abstract void execute();

    TaskManager(List<String> list, TaskManagerListener<T> taskManagerListener) throws IllegalArgumentException {
        Preconditions.checkNotNull(list, "Urls list cannot be null");
        Preconditions.checkNotNull(taskManagerListener, "ImageTaskManagerListener cannot be null");
        Preconditions.checkState(!list.contains((Object) null), "Urls list cannot contain null");
        this.mSize = list.size();
        this.mImageTaskManagerListener = taskManagerListener;
    }
}
