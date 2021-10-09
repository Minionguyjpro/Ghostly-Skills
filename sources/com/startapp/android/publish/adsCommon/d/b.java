package com.startapp.android.publish.adsCommon.d;

import android.content.Context;
import com.startapp.android.publish.adsCommon.Utils.g;
import com.startapp.android.publish.adsCommon.m;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.common.a.a;
import com.startapp.common.a.f;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* compiled from: StartAppSDK */
public class b implements Serializable {
    private static final long serialVersionUID = 1;
    private String adTag;
    private String clientSessionId;
    private String location;
    private String nonImpressionReason;
    private int offset;
    private String profileId;

    public b() {
        this((String) null);
    }

    public b(String str) {
        this.adTag = str;
        this.clientSessionId = g.d().a();
        this.profileId = MetaData.getInstance().getProfileId();
        this.offset = 0;
    }

    public String getAdTag() {
        return this.adTag;
    }

    public String getClientSessionId() {
        return this.clientSessionId;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public int getOffset() {
        return this.offset;
    }

    public b setOffset(int i) {
        this.offset = i;
        return this;
    }

    public String getNonImpressionReason() {
        return this.nonImpressionReason;
    }

    public b setNonImpressionReason(String str) {
        this.nonImpressionReason = str;
        return this;
    }

    public void setLocation(Context context) {
        try {
            m.a().h(context);
            this.location = f.a(f.a(context, MetaData.getInstance().getLocationConfig().isFiEnabled(), MetaData.getInstance().getLocationConfig().isCoEnabled()));
        } catch (Exception unused) {
            this.location = null;
        }
    }

    private String getLocationQuery() {
        String str = this.location;
        if (str == null || str.equals("")) {
            return "";
        }
        return "&locations=" + encode(a.c(this.location));
    }

    private String getNonImpressionReasonQuery() {
        String str = this.nonImpressionReason;
        if (str == null || str.equals("")) {
            return "";
        }
        return "&isShown=false&reason=" + encode(this.nonImpressionReason);
    }

    /* access modifiers changed from: protected */
    public String getOffsetQuery() {
        if (this.offset <= 0) {
            return "";
        }
        return "&offset=" + this.offset;
    }

    private String getAdTagQuery() {
        String str = this.adTag;
        if (str == null || str.equals("")) {
            return "";
        }
        int i = 200;
        if (this.adTag.length() < 200) {
            i = this.adTag.length();
        }
        return "&adTag=" + encode(this.adTag.substring(0, i));
    }

    private String getClientSessionIdQuery() {
        if (this.clientSessionId == null) {
            return "";
        }
        return "&clientSessionId=" + encode(this.clientSessionId);
    }

    private String getProfileIdQuery() {
        if (this.profileId == null) {
            return "";
        }
        return "&profileId=" + encode(this.profileId);
    }

    /* access modifiers changed from: protected */
    public String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public String getQueryString() {
        return getAdTagQuery() + getClientSessionIdQuery() + getProfileIdQuery() + getOffsetQuery() + getNonImpressionReasonQuery() + getLocationQuery();
    }
}
