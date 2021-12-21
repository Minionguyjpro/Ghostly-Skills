package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
public class FcmBroadcastProcessor implements BroadcastProcessor {
    private static WithinAppServiceConnection fcmServiceConn;
    private static final Object lock = new Object();
    private final Context context;
    private final ExecutorService executor;

    public FcmBroadcastProcessor(Context context2, ExecutorService executorService) {
        this.context = context2;
        this.executor = executorService;
    }

    public Task<Integer> process(Intent intent) {
        String stringExtra = intent.getStringExtra("gcm.rawData64");
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        return startMessagingService(this.context, intent);
    }

    public Task<Integer> startMessagingService(Context context2, Intent intent) {
        boolean z = true;
        boolean z2 = PlatformVersion.isAtLeastO() && context2.getApplicationInfo().targetSdkVersion >= 26;
        if ((intent.getFlags() & 268435456) == 0) {
            z = false;
        }
        if (!z2 || z) {
            return Tasks.call(this.executor, new FcmBroadcastProcessor$$Lambda$0(context2, intent)).continueWithTask(this.executor, new FcmBroadcastProcessor$$Lambda$1(context2, intent));
        }
        return bindToMessagingService(context2, intent);
    }

    private static Task<Integer> bindToMessagingService(Context context2, Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Binding to service");
        }
        return getServiceConnection(context2, "com.google.firebase.MESSAGING_EVENT").sendIntent(intent).continueWith(FirebaseIidExecutors.directExecutor(), FcmBroadcastProcessor$$Lambda$2.$instance);
    }

    private static WithinAppServiceConnection getServiceConnection(Context context2, String str) {
        WithinAppServiceConnection withinAppServiceConnection;
        synchronized (lock) {
            if (fcmServiceConn == null) {
                fcmServiceConn = new WithinAppServiceConnection(context2, str);
            }
            withinAppServiceConnection = fcmServiceConn;
        }
        return withinAppServiceConnection;
    }

    static final /* synthetic */ Integer lambda$bindToMessagingService$3$FcmBroadcastProcessor(Task task) throws Exception {
        return -1;
    }

    static final /* synthetic */ Task lambda$startMessagingService$2$FcmBroadcastProcessor(Context context2, Intent intent, Task task) throws Exception {
        return (!PlatformVersion.isAtLeastO() || ((Integer) task.getResult()).intValue() != 402) ? task : bindToMessagingService(context2, intent).continueWith(FirebaseIidExecutors.directExecutor(), FcmBroadcastProcessor$$Lambda$3.$instance);
    }

    static final /* synthetic */ Integer lambda$startMessagingService$1$FcmBroadcastProcessor(Task task) throws Exception {
        return 403;
    }
}
