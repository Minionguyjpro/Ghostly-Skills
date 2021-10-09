package com.appnext.base.services.a;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.appnext.base.a.b.c;
import com.appnext.base.b.d;
import com.appnext.base.services.OperationService;
import java.util.List;

public final class a extends c {
    private AlarmManager eE;
    private Context mContext;

    public a(Context context) {
        try {
            this.mContext = context.getApplicationContext();
            this.eE = (AlarmManager) context.getSystemService("alarm");
        } catch (Throwable unused) {
        }
    }

    public final void b(c cVar) {
        try {
            this.eE.cancel(PendingIntent.getService(this.mContext, cVar.ap().hashCode(), new Intent(this.mContext, OperationService.class), 268435456));
            this.mContext.stopService(new Intent(this.mContext, OperationService.class));
        } catch (Throwable unused) {
        }
    }

    public final void g(List<c> list) {
        if (list != null) {
            try {
                for (c b : list) {
                    b(b);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void a(c cVar, long j, long j2) {
        a(cVar.ap(), cVar.getKey(), j, j2, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    public final void b(c cVar, long j, long j2) {
        a(cVar.ap(), cVar.getKey(), j, 86400000, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    public final void a(c cVar, long j, Bundle bundle) {
        a(cVar.ap(), cVar.getKey(), j, 0, bundle);
    }

    private void a(String str, String str2, long j, long j2, Bundle bundle) {
        try {
            Intent intent = new Intent(this.mContext, OperationService.class);
            intent.putExtra(d.fg, str2);
            if (bundle != null) {
                intent.putExtra(c.eH, bundle);
            }
            int hashCode = str.hashCode();
            String.valueOf(str);
            PendingIntent service = PendingIntent.getService(this.mContext, hashCode, intent, 134217728);
            if (j2 > 0) {
                this.eE.setInexactRepeating(1, j, j2, service);
            } else {
                this.eE.set(1, j, service);
            }
        } catch (Throwable unused) {
        }
    }
}
