package com.mopub.common;

import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.DisplayCutout;
import android.view.WindowInsets;
import com.mopub.network.Networking;
import com.mopub.network.PlayServicesUrlRewriter;

public abstract class BaseUrlGenerator {
    protected static final String AD_UNIT_ID_KEY = "id";
    private static final String APP_ENGINE_NAME = "e_name";
    private static final String APP_ENGINE_VERSION = "e_ver";
    protected static final String BUNDLE_ID_KEY = "bundle";
    protected static final String CONSENTED_PRIVACY_POLICY_VERSION_KEY = "consented_privacy_policy_version";
    protected static final String CONSENTED_VENDOR_LIST_VERSION_KEY = "consented_vendor_list_version";
    protected static final String CURRENT_CONSENT_STATUS_KEY = "current_consent_status";
    protected static final String DNT_KEY = "dnt";
    protected static final String FORCE_GDPR_APPLIES = "force_gdpr_applies";
    protected static final String GDPR_APPLIES = "gdpr_applies";
    private static final String HEIGHT_KEY = "h";
    protected static final String MOPUB_ID_KEY = "mid";
    private static final String SAFE_HEIGHT_KEY = "ch";
    private static final String SAFE_WIDTH_KEY = "cw";
    protected static final String SDK_VERSION_KEY = "nv";
    protected static final String UDID_KEY = "udid";
    private static final String WIDTH_KEY = "w";
    private static final String WRAPPER_VERSION = "w_ver";
    private static AppEngineInfo mAppEngineInfo;
    private static String sWrapperVersion;
    private boolean mFirstParam;
    private StringBuilder mStringBuilder;

    public abstract String generateUrlString(String str);

    /* access modifiers changed from: protected */
    public void initUrlString(String str, String str2) {
        StringBuilder sb = new StringBuilder(Networking.getScheme());
        sb.append("://");
        sb.append(str);
        sb.append(str2);
        this.mStringBuilder = sb;
        this.mFirstParam = true;
    }

    /* access modifiers changed from: protected */
    public String getFinalUrlString() {
        return this.mStringBuilder.toString();
    }

    /* access modifiers changed from: protected */
    public void addParam(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            this.mStringBuilder.append(getParamDelimiter());
            this.mStringBuilder.append(str);
            this.mStringBuilder.append("=");
            this.mStringBuilder.append(Uri.encode(str2));
        }
    }

    /* access modifiers changed from: protected */
    public void addParam(String str, Boolean bool) {
        if (bool != null) {
            this.mStringBuilder.append(getParamDelimiter());
            this.mStringBuilder.append(str);
            this.mStringBuilder.append("=");
            this.mStringBuilder.append(bool.booleanValue() ? "1" : "0");
        }
    }

    private String getParamDelimiter() {
        if (!this.mFirstParam) {
            return "&";
        }
        this.mFirstParam = false;
        return "?";
    }

    /* access modifiers changed from: protected */
    public void setApiVersion(String str) {
        addParam("v", str);
    }

    /* access modifiers changed from: protected */
    public void setAppVersion(String str) {
        addParam("av", str);
    }

    /* access modifiers changed from: protected */
    public void setDeviceInfo(String... strArr) {
        StringBuilder sb = new StringBuilder();
        if (strArr != null && strArr.length >= 1) {
            for (int i = 0; i < strArr.length - 1; i++) {
                sb.append(strArr[i]);
                sb.append(",");
            }
            sb.append(strArr[strArr.length - 1]);
            addParam("dn", sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void appendAdvertisingInfoTemplates() {
        addParam(UDID_KEY, PlayServicesUrlRewriter.UDID_TEMPLATE);
        addParam(DNT_KEY, PlayServicesUrlRewriter.DO_NOT_TRACK_TEMPLATE);
        addParam(MOPUB_ID_KEY, PlayServicesUrlRewriter.MOPUB_ID_TEMPLATE);
    }

    public static void setAppEngineInfo(AppEngineInfo appEngineInfo) {
        mAppEngineInfo = appEngineInfo;
    }

    public static void setWrapperVersion(String str) {
        Preconditions.checkNotNull(str);
        sWrapperVersion = str;
    }

    /* access modifiers changed from: protected */
    public void appendAppEngineInfo() {
        AppEngineInfo appEngineInfo = mAppEngineInfo;
        if (appEngineInfo != null) {
            addParam(APP_ENGINE_NAME, appEngineInfo.mName);
            addParam(APP_ENGINE_VERSION, appEngineInfo.mVersion);
        }
    }

    /* access modifiers changed from: protected */
    public void appendWrapperVersion() {
        addParam(WRAPPER_VERSION, sWrapperVersion);
    }

    /* access modifiers changed from: protected */
    public void setDeviceDimensions(Point point, Point point2, WindowInsets windowInsets) {
        int i = 0;
        int i2 = point2 != null ? point2.x : 0;
        if (point2 != null) {
            i = point2.y;
        }
        if (Build.VERSION.SDK_INT < 28 || windowInsets == null || windowInsets.getDisplayCutout() == null) {
            addParam(SAFE_WIDTH_KEY, "" + i2);
            addParam(SAFE_HEIGHT_KEY, "" + i);
        } else {
            DisplayCutout displayCutout = windowInsets.getDisplayCutout();
            int safeInsetLeft = (point.x - displayCutout.getSafeInsetLeft()) - displayCutout.getSafeInsetRight();
            int safeInsetTop = (point.y - displayCutout.getSafeInsetTop()) - displayCutout.getSafeInsetBottom();
            addParam(SAFE_WIDTH_KEY, "" + Math.min(safeInsetLeft, i2));
            addParam(SAFE_HEIGHT_KEY, "" + Math.min(safeInsetTop, i));
        }
        addParam(WIDTH_KEY, "" + point.x);
        addParam(HEIGHT_KEY, "" + point.y);
    }
}
