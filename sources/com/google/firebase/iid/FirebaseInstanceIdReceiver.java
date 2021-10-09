package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
public final class FirebaseInstanceIdReceiver extends WakefulBroadcastReceiver {
    private final ExecutorService processorExecutor = FirebaseIidExecutors.newCachedSingleThreadExecutor();

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            onReceiveInternal(context, normalizeIntent(context, intent));
        }
    }

    private static Intent normalizeIntent(Context context, Intent intent) {
        Intent unwrapServiceIntent = ServiceStarter.unwrapServiceIntent(intent);
        if (unwrapServiceIntent != null) {
            intent = unwrapServiceIntent;
        }
        intent.setComponent((ComponentName) null);
        intent.setPackage(context.getPackageName());
        if (Build.VERSION.SDK_INT <= 18) {
            intent.removeCategory(context.getPackageName());
        }
        return intent;
    }

    private final void onReceiveInternal(Context context, Intent intent) {
        BroadcastProcessor broadcastProcessor;
        if ("google.com/iid".equals(intent.getStringExtra("from"))) {
            broadcastProcessor = new IidBroadcastProcessor(this.processorExecutor);
        } else {
            broadcastProcessor = new FcmBroadcastProcessor(context, this.processorExecutor);
        }
        broadcastProcessor.process(intent).addOnCompleteListener((Executor) this.processorExecutor, new FirebaseInstanceIdReceiver$$Lambda$0(isOrderedBroadcast(), goAsync()));
    }

    static final /* synthetic */ void lambda$onReceiveInternal$0$FirebaseInstanceIdReceiver(boolean z, BroadcastReceiver.PendingResult pendingResult, Task task) {
        if (z) {
            pendingResult.setResultCode(task.isSuccessful() ? ((Integer) task.getResult()).intValue() : 500);
        }
        pendingResult.finish();
    }
}
