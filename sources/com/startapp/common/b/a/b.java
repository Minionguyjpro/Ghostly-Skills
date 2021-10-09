package com.startapp.common.b.a;

import android.content.Context;
import java.util.Map;

/* compiled from: StartAppSDK */
public interface b {

    /* compiled from: StartAppSDK */
    public enum a {
        SUCCESS,
        FAILED,
        RESCHEDULE
    }

    /* renamed from: com.startapp.common.b.a.b$b  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    public interface C0011b {
        void a(a aVar);
    }

    void a(Context context, int i, Map<String, String> map, C0011b bVar);
}
