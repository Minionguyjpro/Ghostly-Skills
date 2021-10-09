package com.google.android.play.core.tasks;

import com.google.android.play.core.internal.ax;
import java.util.concurrent.ExecutionException;

public final class Tasks {
    public static <ResultT> Task<ResultT> a(ResultT resultt) {
        m mVar = new m();
        mVar.a(resultt);
        return mVar;
    }

    public static <ResultT> ResultT await(Task<ResultT> task) throws ExecutionException, InterruptedException {
        ax.d(task, "Task must not be null");
        if (task.isComplete()) {
            return c(task);
        }
        n nVar = new n((byte[]) null);
        d(task, nVar);
        nVar.a();
        return c(task);
    }

    public static <ResultT> Task<ResultT> b(Exception exc) {
        m mVar = new m();
        mVar.c(exc);
        return mVar;
    }

    private static <ResultT> ResultT c(Task<ResultT> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }

    private static void d(Task<?> task, n nVar) {
        task.addOnSuccessListener(TaskExecutors.f1135a, nVar);
        task.addOnFailureListener(TaskExecutors.f1135a, nVar);
    }
}
