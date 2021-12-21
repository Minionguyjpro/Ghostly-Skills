package com.startapp.android.publish.common.model;

import com.startapp.android.publish.adsCommon.BaseResponse;
import com.startapp.android.publish.adsCommon.adinformation.c;
import com.startapp.common.c.f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public class GetAdResponse extends BaseResponse {
    private static final long serialVersionUID = 1;
    @f(a = true)
    private c adInfoOverrides = c.a();
    @f(b = ArrayList.class, c = AdDetails.class)
    private List<AdDetails> adsDetails = new ArrayList();
    private boolean inAppBrowser;
    @f(b = inAppBrowserPreLoad.class)
    private inAppBrowserPreLoad inAppBrowserPreLoad;
    private String productId;
    private String publisherId;

    /* compiled from: StartAppSDK */
    public enum ResponseType {
        HTML,
        JSON
    }

    /* compiled from: StartAppSDK */
    private enum inAppBrowserPreLoad {
        DISABLED,
        CONTENT,
        FULL
    }

    public String getPublisherId() {
        return this.publisherId;
    }

    public void setPublisherId(String str) {
        this.publisherId = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public List<AdDetails> getAdsDetails() {
        return this.adsDetails;
    }

    public void setAdsDetails(List<AdDetails> list) {
        this.adsDetails = list;
    }

    public c getAdInfoOverride() {
        return this.adInfoOverrides;
    }

    public boolean isInAppBrowser() {
        return this.inAppBrowser;
    }

    public void setInAppBrowser(boolean z) {
        this.inAppBrowser = z;
    }

    public inAppBrowserPreLoad getInAppBrowserPreLoad() {
        return this.inAppBrowserPreLoad;
    }

    public void setInAppBrowserPreLoad(inAppBrowserPreLoad inappbrowserpreload) {
        this.inAppBrowserPreLoad = inappbrowserpreload;
    }
}
