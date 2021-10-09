package com.google.android.gms.internal.ads;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract;
import android.text.TextUtils;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.plus.PlusShare;
import java.util.Map;

@zzadh
public final class zzzy extends zzaal {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Map<String, String> zzbgp;
    private String zzbvs = zzbu(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
    private long zzbvt = zzbv("start_ticks");
    private long zzbvu = zzbv("end_ticks");
    private String zzbvv = zzbu("summary");
    private String zzbvw = zzbu("location");

    public zzzy(zzaqw zzaqw, Map<String, String> map) {
        super(zzaqw, "createCalendarEvent");
        this.zzbgp = map;
        this.mContext = zzaqw.zzto();
    }

    private final String zzbu(String str) {
        return TextUtils.isEmpty(this.zzbgp.get(str)) ? "" : this.zzbgp.get(str);
    }

    private final long zzbv(String str) {
        String str2 = this.zzbgp.get(str);
        if (str2 == null) {
            return -1;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    /* access modifiers changed from: package-private */
    public final Intent createIntent() {
        Intent data = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
        data.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, this.zzbvs);
        data.putExtra("eventLocation", this.zzbvw);
        data.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, this.zzbvv);
        long j = this.zzbvt;
        if (j > -1) {
            data.putExtra("beginTime", j);
        }
        long j2 = this.zzbvu;
        if (j2 > -1) {
            data.putExtra("endTime", j2);
        }
        data.setFlags(268435456);
        return data;
    }

    public final void execute() {
        if (this.mContext == null) {
            zzbw("Activity context is not available.");
            return;
        }
        zzbv.zzek();
        if (!zzakk.zzao(this.mContext).zziz()) {
            zzbw("This feature is not available on the device.");
            return;
        }
        zzbv.zzek();
        AlertDialog.Builder zzan = zzakk.zzan(this.mContext);
        Resources resources = zzbv.zzeo().getResources();
        zzan.setTitle(resources != null ? resources.getString(R.string.s5) : "Create calendar event");
        zzan.setMessage(resources != null ? resources.getString(R.string.s6) : "Allow Ad to create a calendar event?");
        zzan.setPositiveButton(resources != null ? resources.getString(R.string.s3) : "Accept", new zzzz(this));
        zzan.setNegativeButton(resources != null ? resources.getString(R.string.s4) : "Decline", new zzaaa(this));
        zzan.create().show();
    }
}
