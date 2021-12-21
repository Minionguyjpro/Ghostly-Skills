package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zab;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.LinkedList;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public abstract class DeferredLifecycleHelper<T extends LifecycleDelegate> {
    /* access modifiers changed from: private */
    public T zaa;
    /* access modifiers changed from: private */
    public Bundle zab;
    /* access modifiers changed from: private */
    public LinkedList<zaa> zac;
    private final OnDelegateCreatedListener<T> zad = new zab(this);

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    private interface zaa {
        int zaa();

        void zaa(LifecycleDelegate lifecycleDelegate);
    }

    /* access modifiers changed from: protected */
    public abstract void createDelegate(OnDelegateCreatedListener<T> onDelegateCreatedListener);

    public T getDelegate() {
        return this.zaa;
    }

    private final void zaa(int i) {
        while (!this.zac.isEmpty() && this.zac.getLast().zaa() >= i) {
            this.zac.removeLast();
        }
    }

    private final void zaa(Bundle bundle, zaa zaa2) {
        T t = this.zaa;
        if (t != null) {
            zaa2.zaa(t);
            return;
        }
        if (this.zac == null) {
            this.zac = new LinkedList<>();
        }
        this.zac.add(zaa2);
        if (bundle != null) {
            Bundle bundle2 = this.zab;
            if (bundle2 == null) {
                this.zab = (Bundle) bundle.clone();
            } else {
                bundle2.putAll(bundle);
            }
        }
        createDelegate(this.zad);
    }

    public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
        zaa(bundle2, (zaa) new zaa(this, activity, bundle, bundle2));
    }

    public void onCreate(Bundle bundle) {
        zaa(bundle, (zaa) new zad(this, bundle));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        zaa(bundle, (zaa) new zac(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.zaa == null) {
            handleGooglePlayUnavailable(frameLayout);
        }
        return frameLayout;
    }

    /* access modifiers changed from: protected */
    public void handleGooglePlayUnavailable(FrameLayout frameLayout) {
        showGooglePlayUnavailableMessage(frameLayout);
    }

    public static void showGooglePlayUnavailableMessage(FrameLayout frameLayout) {
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        String zac2 = zab.zac(context, isGooglePlayServicesAvailable);
        String zae = zab.zae(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(zac2);
        linearLayout.addView(textView);
        Intent errorResolutionIntent = instance.getErrorResolutionIntent(context, isGooglePlayServicesAvailable, (String) null);
        if (errorResolutionIntent != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(zae);
            linearLayout.addView(button);
            button.setOnClickListener(new zaf(context, errorResolutionIntent));
        }
    }

    public void onStart() {
        zaa((Bundle) null, (zaa) new zae(this));
    }

    public void onResume() {
        zaa((Bundle) null, (zaa) new zag(this));
    }

    public void onPause() {
        T t = this.zaa;
        if (t != null) {
            t.onPause();
        } else {
            zaa(5);
        }
    }

    public void onStop() {
        T t = this.zaa;
        if (t != null) {
            t.onStop();
        } else {
            zaa(4);
        }
    }

    public void onDestroyView() {
        T t = this.zaa;
        if (t != null) {
            t.onDestroyView();
        } else {
            zaa(2);
        }
    }

    public void onDestroy() {
        T t = this.zaa;
        if (t != null) {
            t.onDestroy();
        } else {
            zaa(1);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        T t = this.zaa;
        if (t != null) {
            t.onSaveInstanceState(bundle);
            return;
        }
        Bundle bundle2 = this.zab;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
    }

    public void onLowMemory() {
        T t = this.zaa;
        if (t != null) {
            t.onLowMemory();
        }
    }
}
