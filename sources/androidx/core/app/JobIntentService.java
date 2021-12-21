package androidx.core.app;

import android.app.Service;
import android.content.ComponentName;
import android.os.Build;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class JobIntentService extends Service {
    static final HashMap<ComponentName, Object> sClassWorkEnqueuer = new HashMap<>();
    static final Object sLock = new Object();
    final ArrayList<Object> mCompatQueue;
    boolean mDestroyed = false;
    boolean mInterruptIfStopped = false;
    boolean mStopped = false;

    public JobIntentService() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mCompatQueue = null;
        } else {
            this.mCompatQueue = new ArrayList<>();
        }
    }
}
