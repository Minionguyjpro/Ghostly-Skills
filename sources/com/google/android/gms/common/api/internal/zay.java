package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public class zay extends zal {
    private final ArraySet<ApiKey<?>> zad;
    private final GoogleApiManager zae;

    public static void zaa(Activity activity, GoogleApiManager googleApiManager, ApiKey<?> apiKey) {
        LifecycleFragment fragment = getFragment(activity);
        zay zay = (zay) fragment.getCallbackOrNull("ConnectionlessLifecycleHelper", zay.class);
        if (zay == null) {
            zay = new zay(fragment, googleApiManager);
        }
        Preconditions.checkNotNull(apiKey, "ApiKey cannot be null");
        zay.zad.add(apiKey);
        googleApiManager.zaa(zay);
    }

    private zay(LifecycleFragment lifecycleFragment, GoogleApiManager googleApiManager) {
        this(lifecycleFragment, googleApiManager, GoogleApiAvailability.getInstance());
    }

    private zay(LifecycleFragment lifecycleFragment, GoogleApiManager googleApiManager, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment, googleApiAvailability);
        this.zad = new ArraySet<>();
        this.zae = googleApiManager;
        this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", this);
    }

    public void onStart() {
        super.onStart();
        zad();
    }

    public void onResume() {
        super.onResume();
        zad();
    }

    public void onStop() {
        super.onStop();
        this.zae.zab(this);
    }

    /* access modifiers changed from: protected */
    public final void zaa(ConnectionResult connectionResult, int i) {
        this.zae.zab(connectionResult, i);
    }

    /* access modifiers changed from: protected */
    public final void zaa() {
        this.zae.zac();
    }

    /* access modifiers changed from: package-private */
    public final ArraySet<ApiKey<?>> zac() {
        return this.zad;
    }

    private final void zad() {
        if (!this.zad.isEmpty()) {
            this.zae.zaa(this);
        }
    }
}
