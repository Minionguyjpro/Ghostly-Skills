package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
class IidBroadcastProcessor implements BroadcastProcessor {
    private final ExecutorService executor;

    IidBroadcastProcessor(ExecutorService executorService) {
        this.executor = executorService;
    }

    public Task<Integer> process(Intent intent) {
        return Tasks.call(this.executor, new IidBroadcastProcessor$$Lambda$0(intent));
    }

    private static int processImpl(Intent intent) {
        String stringExtra = intent.getStringExtra("CMD");
        if (stringExtra == null) {
            return -1;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(intent.getExtras());
            StringBuilder sb = new StringBuilder(String.valueOf(stringExtra).length() + 21 + String.valueOf(valueOf).length());
            sb.append("Received command: ");
            sb.append(stringExtra);
            sb.append(" - ");
            sb.append(valueOf);
            Log.d("FirebaseInstanceId", sb.toString());
        }
        if ("RST".equals(stringExtra) || "RST_FULL".equals(stringExtra)) {
            FirebaseInstanceId.getInstance().resetStorageAndScheduleSync();
            return -1;
        } else if (!"SYNC".equals(stringExtra)) {
            return -1;
        } else {
            FirebaseInstanceId.getInstance().forceTokenRefresh();
            return -1;
        }
    }
}
