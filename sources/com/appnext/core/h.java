package com.appnext.core;

import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;

public final class h extends o {
    String am;
    String an;
    String ao;
    String ap;
    String aq;
    String ar;
    String as;
    ResultReceiver at;
    String guid;

    public h() {
    }

    public h(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ResultReceiver resultReceiver) {
        this.am = str;
        this.an = str2;
        this.guid = str3;
        this.ao = str4;
        this.ap = str5;
        this.at = resultReceiver;
        this.aq = str6;
        this.ar = str7;
        this.as = str8;
    }

    public final void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ResultReceiver resultReceiver) {
        this.am = str;
        this.an = str2;
        this.guid = str3;
        this.ao = str4;
        this.ap = str5;
        this.at = null;
        this.aq = str6;
        this.ar = str7;
        this.as = str8;
    }

    /* access modifiers changed from: protected */
    public final void a(Intent intent) {
        intent.putExtra("added_info", AdsService.ADD_PACK);
        intent.putExtra("package", this.am);
        intent.putExtra("link", this.an);
        intent.putExtra("guid", this.guid);
        intent.putExtra("bannerid", this.ao);
        intent.putExtra("placementid", this.ap);
        intent.putExtra("receiver", this.at);
        intent.putExtra("vid", this.aq);
        intent.putExtra("tid", this.ar);
        intent.putExtra("auid", this.as);
    }

    public final void a(Context context) {
        super.a(context);
        this.at = null;
    }
}
