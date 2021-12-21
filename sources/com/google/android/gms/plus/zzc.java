package com.google.android.gms.plus;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.ScopeUtil;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.plus.internal.zzh;
import com.google.android.gms.plus.internal.zzn;

final class zzc extends Api.AbstractClientBuilder<zzh, Plus.PlusOptions> {
    zzc() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Plus.PlusOptions plusOptions = (Plus.PlusOptions) obj;
        if (plusOptions == null) {
            plusOptions = new Plus.PlusOptions((zzc) null);
        }
        return new zzh(context, looper, clientSettings, new zzn(clientSettings.getAccountOrDefault().name, ScopeUtil.toScopeString(clientSettings.getAllRequestedScopes()), (String[]) plusOptions.zzh.toArray(new String[0]), new String[0], context.getPackageName(), context.getPackageName(), (String) null, new PlusCommonExtras()), connectionCallbacks, onConnectionFailedListener);
    }

    public final int getPriority() {
        return 2;
    }
}
