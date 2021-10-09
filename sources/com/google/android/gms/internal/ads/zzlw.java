package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@zzadh
public final class zzlw {
    private final int zzaqn;
    private final int zzaqq;
    private final String zzaqr;
    private final String zzaqt;
    private final Bundle zzaqv;
    private final String zzaqx;
    private final boolean zzaqz;
    private final Bundle zzask;
    private final Map<Class<? extends NetworkExtras>, NetworkExtras> zzasl;
    private final SearchAdRequest zzasm;
    private final Set<String> zzasn;
    private final Set<String> zzaso;
    private final Date zzhl;
    private final Set<String> zzhn;
    private final Location zzhp;
    private final boolean zzvm;

    public zzlw(zzlx zzlx) {
        this(zzlx, (SearchAdRequest) null);
    }

    public zzlw(zzlx zzlx, SearchAdRequest searchAdRequest) {
        this.zzhl = zzlx.zzhl;
        this.zzaqt = zzlx.zzaqt;
        this.zzaqn = zzlx.zzaqn;
        this.zzhn = Collections.unmodifiableSet(zzlx.zzasp);
        this.zzhp = zzlx.zzhp;
        this.zzvm = zzlx.zzvm;
        this.zzask = zzlx.zzask;
        this.zzasl = Collections.unmodifiableMap(zzlx.zzasq);
        this.zzaqr = zzlx.zzaqr;
        this.zzaqx = zzlx.zzaqx;
        this.zzasm = searchAdRequest;
        this.zzaqq = zzlx.zzaqq;
        this.zzasn = Collections.unmodifiableSet(zzlx.zzasr);
        this.zzaqv = zzlx.zzaqv;
        this.zzaso = Collections.unmodifiableSet(zzlx.zzass);
        this.zzaqz = zzlx.zzaqz;
    }

    public final Date getBirthday() {
        return this.zzhl;
    }

    public final String getContentUrl() {
        return this.zzaqt;
    }

    public final Bundle getCustomEventExtrasBundle(Class<? extends CustomEvent> cls) {
        Bundle bundle = this.zzask.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        if (bundle != null) {
            return bundle.getBundle(cls.getName());
        }
        return null;
    }

    public final Bundle getCustomTargeting() {
        return this.zzaqv;
    }

    public final int getGender() {
        return this.zzaqn;
    }

    public final Set<String> getKeywords() {
        return this.zzhn;
    }

    public final Location getLocation() {
        return this.zzhp;
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zzvm;
    }

    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return (NetworkExtras) this.zzasl.get(cls);
    }

    public final Bundle getNetworkExtrasBundle(Class<? extends MediationAdapter> cls) {
        return this.zzask.getBundle(cls.getName());
    }

    public final String getPublisherProvidedId() {
        return this.zzaqr;
    }

    public final boolean isDesignedForFamilies() {
        return this.zzaqz;
    }

    public final boolean isTestDevice(Context context) {
        Set<String> set = this.zzasn;
        zzkb.zzif();
        return set.contains(zzamu.zzbc(context));
    }

    public final String zzip() {
        return this.zzaqx;
    }

    public final SearchAdRequest zziq() {
        return this.zzasm;
    }

    public final Map<Class<? extends NetworkExtras>, NetworkExtras> zzir() {
        return this.zzasl;
    }

    public final Bundle zzis() {
        return this.zzask;
    }

    public final int zzit() {
        return this.zzaqq;
    }

    public final Set<String> zziu() {
        return this.zzaso;
    }
}
