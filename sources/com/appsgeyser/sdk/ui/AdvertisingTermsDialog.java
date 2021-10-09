package com.appsgeyser.sdk.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Process;
import com.appsgeyser.sdk.R;
import com.appsgeyser.sdk.configuration.Configuration;

public class AdvertisingTermsDialog {
    private static final String PREFERENCES_PREFIX = (AdvertisingTermsDialog.class.getSimpleName() + "AlreadyShown");
    /* access modifiers changed from: private */
    public static volatile boolean onScreen = false;
    private final AlertDialog dialog;

    public AdvertisingTermsDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.appsgeysersdk_advertising_terms).setMessage(R.string.appsgeysersdk_advertising_terms_message).setCancelable(false).setPositiveButton(R.string.appsgeysersdk_accept, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean unused = AdvertisingTermsDialog.onScreen = false;
                AdvertisingTermsDialog.this.setAlreadyShown();
                dialogInterface.dismiss();
            }
        }).setNegativeButton(R.string.appsgeysersdk_refuse, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean unused = AdvertisingTermsDialog.onScreen = false;
                Process.killProcess(Process.myPid());
            }
        });
        this.dialog = builder.create();
    }

    public void show(boolean z) {
        if (z && !isAlreadyShown()) {
            onScreen = true;
            this.dialog.show();
        }
    }

    /* access modifiers changed from: private */
    public void setAlreadyShown() {
        Configuration.getInstance(this.dialog.getContext()).getSettingsCoder().savePrefBoolean(PREFERENCES_PREFIX, true);
    }

    private boolean isAlreadyShown() {
        return Configuration.getInstance(this.dialog.getContext()).getSettingsCoder().getPrefBoolean(PREFERENCES_PREFIX, false);
    }
}
