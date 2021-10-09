package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaap;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzkb;

public class AdActivity extends Activity {
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private zzaap zzuj;

    private final void zzax() {
        zzaap zzaap = this.zzuj;
        if (zzaap != null) {
            try {
                zzaap.zzax();
            } catch (RemoteException e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            this.zzuj.onActivityResult(i, i2, intent);
        } catch (Exception e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        boolean z = true;
        try {
            if (this.zzuj != null) {
                z = this.zzuj.zznj();
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
        if (z) {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            this.zzuj.zzo(ObjectWrapper.wrap(configuration));
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        zzaap zzb = zzkb.zzig().zzb((Activity) this);
        this.zzuj = zzb;
        if (zzb == null) {
            e = null;
        } else {
            try {
                zzb.onCreate(bundle);
                return;
            } catch (RemoteException e) {
                e = e;
            }
        }
        zzane.zzd("#007 Could not call remote method.", e);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            if (this.zzuj != null) {
                this.zzuj.onDestroy();
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        try {
            if (this.zzuj != null) {
                this.zzuj.onPause();
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
            finish();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        try {
            if (this.zzuj != null) {
                this.zzuj.onRestart();
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            if (this.zzuj != null) {
                this.zzuj.onResume();
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        try {
            if (this.zzuj != null) {
                this.zzuj.onSaveInstanceState(bundle);
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        try {
            if (this.zzuj != null) {
                this.zzuj.onStart();
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        try {
            if (this.zzuj != null) {
                this.zzuj.onStop();
            }
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
            finish();
        }
        super.onStop();
    }

    public void setContentView(int i) {
        super.setContentView(i);
        zzax();
    }

    public void setContentView(View view) {
        super.setContentView(view);
        zzax();
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        zzax();
    }
}
