package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@zzadh
public final class zzjm {
    public static final zzjm zzara = new zzjm();

    protected zzjm() {
    }

    public static zzjj zza(Context context, zzlw zzlw) {
        List list;
        Context context2;
        String str;
        zzlw zzlw2 = zzlw;
        Date birthday = zzlw.getBirthday();
        long time = birthday != null ? birthday.getTime() : -1;
        String contentUrl = zzlw.getContentUrl();
        int gender = zzlw.getGender();
        Set<String> keywords = zzlw.getKeywords();
        if (!keywords.isEmpty()) {
            list = Collections.unmodifiableList(new ArrayList(keywords));
            context2 = context;
        } else {
            context2 = context;
            list = null;
        }
        boolean isTestDevice = zzlw2.isTestDevice(context2);
        int zzit = zzlw.zzit();
        Location location = zzlw.getLocation();
        Bundle networkExtrasBundle = zzlw2.getNetworkExtrasBundle(AdMobAdapter.class);
        boolean manualImpressionsEnabled = zzlw.getManualImpressionsEnabled();
        String publisherProvidedId = zzlw.getPublisherProvidedId();
        SearchAdRequest zziq = zzlw.zziq();
        zzmq zzmq = zziq != null ? new zzmq(zziq) : null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            String packageName = applicationContext.getPackageName();
            zzkb.zzif();
            str = zzamu.zza(Thread.currentThread().getStackTrace(), packageName);
        } else {
            str = null;
        }
        return new zzjj(7, time, networkExtrasBundle, gender, list, isTestDevice, zzit, manualImpressionsEnabled, publisherProvidedId, zzmq, location, contentUrl, zzlw.zzis(), zzlw.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(zzlw.zziu())), zzlw.zzip(), str, zzlw.isDesignedForFamilies());
    }
}
