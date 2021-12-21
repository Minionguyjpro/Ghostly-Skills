package com.appsgeyser.sdk.datasdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.network.NetworkManager;

public class DataSdksReceiver extends BroadcastReceiver {
    public void onReceive(final Context context, final Intent intent) {
        if (intent != null) {
            NetworkManager.getInstance().sendRequestAsync(new PreferencesCoder(context).getPrefString("ConfigPhpURLWithParams", ""), Integer.valueOf(NetworkManager.RequestType.CONFIG_PHP.ordinal()), context, (Response.Listener<String>) new Response.Listener<String>() {
                public void onResponse(String str) {
                    try {
                        ConfigPhp.parseFromJson(str);
                        DataSdksReceiver.this.passDataToSdk(context, intent);
                    } catch (Exception e) {
                        Log.e("DataSdksReceiver", "parsing response error...\n" + e.getMessage());
                        DataSdksReceiver.this.onServerError(context, intent);
                    }
                }
            }, (Response.ErrorListener) new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    if (volleyError != null) {
                        volleyError.printStackTrace();
                        if (volleyError.getMessage() != null) {
                            Log.e("DataSdksReceiver", "Volley request error: parsing response error...\n" + volleyError.getMessage());
                        } else {
                            Log.e("DataSdksReceiver", "Volley request error: parsing response error...\n");
                        }
                    }
                    DataSdksReceiver.this.onServerError(context, intent);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void onServerError(Context context, Intent intent) {
        new PreferencesCoder(context);
        passDataToSdk(context, intent);
    }

    /* access modifiers changed from: private */
    public void passDataToSdk(Context context, Intent intent) {
        intent.getAction();
    }
}
