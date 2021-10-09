package com.appsgeyser.sdk.datasdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.R;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;

public class DataSdkActivity extends AppCompatActivity {
    private ConfigPhp configPhp;
    private AppCompatActivity dataActivity;

    public static void startRequestPermissions(Context context, ConfigPhp configPhp2, String str) {
        if (PermissionsRequester.isPermissionsRequired(configPhp2, context) || (!DataSdkController.isSdkAccepted(context) && !TextUtils.isEmpty(str))) {
            Intent intent = new Intent(context, DataSdkActivity.class);
            intent.setFlags(268435456);
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra("com.appsgeyser.sdk.permission.DataSdkActivity.textOfPolicy", str);
            }
            intent.putExtra("com.appsgeyser.sdk.permission.DataSdkActivity.configPhp", configPhp2);
            context.startActivity(intent);
            return;
        }
        DataSdkController.initSdk(configPhp2, context);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ConfigPhp configPhp2;
        super.onCreate(bundle);
        this.dataActivity = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.configPhp = (ConfigPhp) intent.getParcelableExtra("com.appsgeyser.sdk.permission.DataSdkActivity.configPhp");
            if (Build.VERSION.SDK_INT >= 23) {
                String stringExtra = intent.getStringExtra("com.appsgeyser.sdk.permission.DataSdkActivity.textOfPolicy");
                if (!TextUtils.isEmpty(stringExtra)) {
                    ConfigPhp configPhp3 = this.configPhp;
                    if (configPhp3 != null) {
                        showEulaDialog(stringExtra, configPhp3);
                    }
                } else if (PermissionsRequester.isPermissionsRequired(this.configPhp, this)) {
                    PermissionsRequester.requestAllActivePermissions(this, this.configPhp, 78);
                }
            } else {
                String stringExtra2 = intent.getStringExtra("com.appsgeyser.sdk.permission.DataSdkActivity.textOfPolicy");
                if (!TextUtils.isEmpty(stringExtra2) && (configPhp2 = this.configPhp) != null) {
                    showEulaDialog(stringExtra2, configPhp2);
                }
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onRequestPermissionsResult(int r13, java.lang.String[] r14, int[] r15) {
        /*
            r12 = this;
            r0 = 78
            if (r0 != r13) goto L_0x00e4
            int r13 = r14.length     // Catch:{ all -> 0x00d8 }
            r0 = 0
            r1 = 0
        L_0x0007:
            if (r1 >= r13) goto L_0x00cd
            r2 = r14[r1]     // Catch:{ all -> 0x00d8 }
            r3 = r15[r1]     // Catch:{ all -> 0x00d8 }
            com.appsgeyser.sdk.server.StatController r4 = com.appsgeyser.sdk.server.StatController.getInstance()     // Catch:{ all -> 0x00d8 }
            int r5 = r2.hashCode()     // Catch:{ all -> 0x00d8 }
            r6 = 5
            r7 = 4
            r8 = 3
            r9 = 2
            r10 = 1
            r11 = -1
            switch(r5) {
                case -1888586689: goto L_0x0051;
                case -406040016: goto L_0x0047;
                case -63024214: goto L_0x003d;
                case -5573545: goto L_0x0033;
                case 1271781903: goto L_0x0029;
                case 1365911975: goto L_0x001f;
                default: goto L_0x001e;
            }     // Catch:{ all -> 0x00d8 }
        L_0x001e:
            goto L_0x005b
        L_0x001f:
            java.lang.String r5 = "android.permission.WRITE_EXTERNAL_STORAGE"
            boolean r2 = r2.equals(r5)     // Catch:{ all -> 0x00d8 }
            if (r2 == 0) goto L_0x005b
            r2 = 4
            goto L_0x005c
        L_0x0029:
            java.lang.String r5 = "android.permission.GET_ACCOUNTS"
            boolean r2 = r2.equals(r5)     // Catch:{ all -> 0x00d8 }
            if (r2 == 0) goto L_0x005b
            r2 = 1
            goto L_0x005c
        L_0x0033:
            java.lang.String r5 = "android.permission.READ_PHONE_STATE"
            boolean r2 = r2.equals(r5)     // Catch:{ all -> 0x00d8 }
            if (r2 == 0) goto L_0x005b
            r2 = 2
            goto L_0x005c
        L_0x003d:
            java.lang.String r5 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r2 = r2.equals(r5)     // Catch:{ all -> 0x00d8 }
            if (r2 == 0) goto L_0x005b
            r2 = 3
            goto L_0x005c
        L_0x0047:
            java.lang.String r5 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r2 = r2.equals(r5)     // Catch:{ all -> 0x00d8 }
            if (r2 == 0) goto L_0x005b
            r2 = 5
            goto L_0x005c
        L_0x0051:
            java.lang.String r5 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r2 = r2.equals(r5)     // Catch:{ all -> 0x00d8 }
            if (r2 == 0) goto L_0x005b
            r2 = 0
            goto L_0x005c
        L_0x005b:
            r2 = -1
        L_0x005c:
            if (r2 == 0) goto L_0x00ba
            if (r2 == r10) goto L_0x00aa
            if (r2 == r9) goto L_0x009a
            if (r2 == r8) goto L_0x008a
            if (r2 == r7) goto L_0x007a
            if (r2 == r6) goto L_0x006a
            goto L_0x00c9
        L_0x006a:
            if (r3 != 0) goto L_0x0072
            java.lang.String r2 = "click_accept_permission_read_external_storage"
            r4.sendRequestAsyncByKey(r2)     // Catch:{ all -> 0x00d8 }
            goto L_0x00c9
        L_0x0072:
            if (r3 != r11) goto L_0x00c9
            java.lang.String r2 = "click_decline_permission_read_external_storage"
            r4.sendRequestAsyncByKey(r2)     // Catch:{ all -> 0x00d8 }
            goto L_0x00c9
        L_0x007a:
            if (r3 != 0) goto L_0x0082
            java.lang.String r2 = "click_accept_permission_write_external_storage"
            r4.sendRequestAsyncByKey(r2)     // Catch:{ all -> 0x00d8 }
            goto L_0x00c9
        L_0x0082:
            if (r3 != r11) goto L_0x00c9
            java.lang.String r2 = "click_decline_permission_write_external_storage"
            r4.sendRequestAsyncByKey(r2)     // Catch:{ all -> 0x00d8 }
            goto L_0x00c9
        L_0x008a:
            if (r3 != 0) goto L_0x0092
            java.lang.String r2 = "click_accept_permission_access_coarse_location"
            r4.sendRequestAsyncByKey(r2)     // Catch:{ all -> 0x00d8 }
            goto L_0x00c9
        L_0x0092:
            if (r3 != r11) goto L_0x00c9
            java.lang.String r2 = "click_decline_permission_access_coarse_location"
            r4.sendRequestAsyncByKey(r2)     // Catch:{ all -> 0x00d8 }
            goto L_0x00c9
        L_0x009a:
            if (r3 != 0) goto L_0x00a2
            java.lang.String r2 = "click_accept_permission_read_phone_state"
            r4.sendRequestAsyncByKey(r2)     // Catch:{ all -> 0x00d8 }
            goto L_0x00c9
        L_0x00a2:
            if (r3 != r11) goto L_0x00c9
            java.lang.String r2 = "click_decline_permission_read_phone_state"
            r4.sendRequestAsyncByKey(r2)     // Catch:{ all -> 0x00d8 }
            goto L_0x00c9
        L_0x00aa:
            if (r3 != 0) goto L_0x00b2
            java.lang.String r2 = "click_accept_permission_get_accounts"
            r4.sendRequestAsyncByKey(r2)     // Catch:{ all -> 0x00d8 }
            goto L_0x00c9
        L_0x00b2:
            if (r3 != r11) goto L_0x00c9
            java.lang.String r2 = "click_decline_permission_get_accounts"
            r4.sendRequestAsyncByKey(r2)     // Catch:{ all -> 0x00d8 }
            goto L_0x00c9
        L_0x00ba:
            if (r3 != 0) goto L_0x00c2
            java.lang.String r2 = "click_accept_permission_access_fine_location"
            r4.sendRequestAsyncByKey(r2)     // Catch:{ all -> 0x00d8 }
            goto L_0x00c9
        L_0x00c2:
            if (r3 != r11) goto L_0x00c9
            java.lang.String r2 = "click_decline_permission_access_fine_location"
            r4.sendRequestAsyncByKey(r2)     // Catch:{ all -> 0x00d8 }
        L_0x00c9:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x00cd:
            com.appsgeyser.sdk.configuration.models.ConfigPhp r13 = r12.configPhp
            if (r13 == 0) goto L_0x00d4
            com.appsgeyser.sdk.datasdk.DataSdkController.initSdk(r13, r12)
        L_0x00d4:
            r12.finish()
            goto L_0x00e4
        L_0x00d8:
            r13 = move-exception
            com.appsgeyser.sdk.configuration.models.ConfigPhp r14 = r12.configPhp
            if (r14 == 0) goto L_0x00e0
            com.appsgeyser.sdk.datasdk.DataSdkController.initSdk(r14, r12)
        L_0x00e0:
            r12.finish()
            throw r13
        L_0x00e4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsgeyser.sdk.datasdk.DataSdkActivity.onRequestPermissionsResult(int, java.lang.String[], int[]):void");
    }

    public void showEulaDialog(String str, ConfigPhp configPhp2) {
        View inflate = getLayoutInflater().inflate(R.layout.appsgeysersdk_datasdk_dialog, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.appsgeysersdk_datasdk_dialog_title)).setText(getString(R.string.appsgeysersdk_eula_title));
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.MaterialAlertDialog));
        builder.setCancelable(false);
        ((TextView) inflate.findViewById(R.id.appsgeysersdk_datasdk_dialog_accept)).setOnClickListener(new View.OnClickListener(configPhp2, this) {
            public final /* synthetic */ ConfigPhp f$1;
            public final /* synthetic */ Activity f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                DataSdkActivity.this.lambda$showEulaDialog$0$DataSdkActivity(this.f$1, this.f$2, view);
            }
        });
        ((TextView) inflate.findViewById(R.id.appsgeysersdk_datasdk_dialog_decline)).setOnClickListener(new View.OnClickListener(this, configPhp2, str) {
            public final /* synthetic */ Activity f$1;
            public final /* synthetic */ ConfigPhp f$2;
            public final /* synthetic */ String f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onClick(View view) {
                DataSdkActivity.this.lambda$showEulaDialog$1$DataSdkActivity(this.f$1, this.f$2, this.f$3, view);
            }
        });
        AlertDialog create = builder.create();
        WebView webView = (WebView) inflate.findViewById(R.id.appsgeysersdk_datasdk_dialog_web_view);
        webView.loadData(new StringBuilder(str).insert(0, configPhp2.getEulaBeginning() != null ? configPhp2.getEulaBeginning() : "").toString(), "text/html", "UTF-8");
        webView.setScrollbarFadingEnabled(false);
        new PreferencesCoder(this).savePrefLong("elapsedTime", 0);
        create.setView(inflate);
        create.show();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if (create.getWindow() != null) {
            layoutParams.copyFrom(create.getWindow().getAttributes());
            layoutParams.width = -1;
            layoutParams.height = -1;
            create.getWindow().setAttributes(layoutParams);
        }
    }

    public /* synthetic */ void lambda$showEulaDialog$0$DataSdkActivity(ConfigPhp configPhp2, Activity activity, View view) {
        if (Build.VERSION.SDK_INT < 23 || !PermissionsRequester.isPermissionsRequired(configPhp2, activity)) {
            DataSdkController.acceptAllActiveSdk(activity, configPhp2);
            DataSdkController.initSdk(configPhp2, activity);
            finish();
            return;
        }
        DataSdkController.acceptAllActiveSdk(activity, configPhp2);
        PermissionsRequester.requestAllActivePermissions(activity, configPhp2, 78);
    }

    public /* synthetic */ void lambda$showEulaDialog$1$DataSdkActivity(Activity activity, ConfigPhp configPhp2, String str, View view) {
        DataSdkController.declineAllActiveSdk(activity, configPhp2, this.dataActivity, str);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        AppsgeyserSDK.getFastTrackAdsController().showPendingFullscreen((Context) null);
    }
}
