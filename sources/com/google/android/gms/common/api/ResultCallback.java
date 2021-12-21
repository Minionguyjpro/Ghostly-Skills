package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public interface ResultCallback<R extends Result> {
    void onResult(R r);
}
