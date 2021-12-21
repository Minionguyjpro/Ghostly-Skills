package com.google.firebase.iid;

import android.content.Intent;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
interface BroadcastProcessor {
    Task<Integer> process(Intent intent);
}
