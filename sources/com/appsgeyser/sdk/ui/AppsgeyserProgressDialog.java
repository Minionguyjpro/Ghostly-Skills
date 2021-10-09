package com.appsgeyser.sdk.ui;

import android.app.Dialog;
import android.content.Context;
import com.appsgeyser.sdk.R;

public class AppsgeyserProgressDialog extends Dialog {
    public AppsgeyserProgressDialog(Context context) {
        super(context);
        setCancelable(false);
        setContentView(R.layout.appsgeysersdk_progress_dialog);
    }
}
