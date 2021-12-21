package com.truenet.android;

import a.a.b.b.h;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.common.c.f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public final class ValidationResult {
    private final String instanceId;
    private final String lastHtml;
    private final String lastImage;
    private final String lastUrl;
    private final String metaData;
    private final int numOfRedirect;
    private final String publisherId;
    @f(b = ArrayList.class, c = RedirectsResult.class)
    private final List<RedirectsResult> redirectUrls;
    private final long sessionTime;

    public static /* synthetic */ ValidationResult copy$default(ValidationResult validationResult, String str, int i, long j, List list, String str2, String str3, String str4, String str5, String str6, int i2, Object obj) {
        ValidationResult validationResult2 = validationResult;
        int i3 = i2;
        return validationResult.copy((i3 & 1) != 0 ? validationResult2.instanceId : str, (i3 & 2) != 0 ? validationResult2.numOfRedirect : i, (i3 & 4) != 0 ? validationResult2.sessionTime : j, (i3 & 8) != 0 ? validationResult2.redirectUrls : list, (i3 & 16) != 0 ? validationResult2.lastUrl : str2, (i3 & 32) != 0 ? validationResult2.lastHtml : str3, (i3 & 64) != 0 ? validationResult2.lastImage : str4, (i3 & 128) != 0 ? validationResult2.publisherId : str5, (i3 & 256) != 0 ? validationResult2.metaData : str6);
    }

    public final String component1() {
        return this.instanceId;
    }

    public final int component2() {
        return this.numOfRedirect;
    }

    public final long component3() {
        return this.sessionTime;
    }

    public final List<RedirectsResult> component4() {
        return this.redirectUrls;
    }

    public final String component5() {
        return this.lastUrl;
    }

    public final String component6() {
        return this.lastHtml;
    }

    public final String component7() {
        return this.lastImage;
    }

    public final String component8() {
        return this.publisherId;
    }

    public final String component9() {
        return this.metaData;
    }

    public final ValidationResult copy(String str, int i, long j, List<RedirectsResult> list, String str2, String str3, String str4, String str5, String str6) {
        h.b(str, "instanceId");
        List<RedirectsResult> list2 = list;
        h.b(list2, "redirectUrls");
        String str7 = str2;
        h.b(str7, "lastUrl");
        String str8 = str3;
        h.b(str8, "lastHtml");
        String str9 = str4;
        h.b(str9, "lastImage");
        String str10 = str5;
        h.b(str10, "publisherId");
        String str11 = str6;
        h.b(str11, MetaData.KEY_METADATA);
        return new ValidationResult(str, i, j, list2, str7, str8, str9, str10, str11);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ValidationResult) {
                ValidationResult validationResult = (ValidationResult) obj;
                if (h.a((Object) this.instanceId, (Object) validationResult.instanceId)) {
                    if (this.numOfRedirect == validationResult.numOfRedirect) {
                        if (!(this.sessionTime == validationResult.sessionTime) || !h.a((Object) this.redirectUrls, (Object) validationResult.redirectUrls) || !h.a((Object) this.lastUrl, (Object) validationResult.lastUrl) || !h.a((Object) this.lastHtml, (Object) validationResult.lastHtml) || !h.a((Object) this.lastImage, (Object) validationResult.lastImage) || !h.a((Object) this.publisherId, (Object) validationResult.publisherId) || !h.a((Object) this.metaData, (Object) validationResult.metaData)) {
                            return false;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.instanceId;
        int i = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        long j = this.sessionTime;
        int i2 = ((((hashCode * 31) + this.numOfRedirect) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        List<RedirectsResult> list = this.redirectUrls;
        int hashCode2 = (i2 + (list != null ? list.hashCode() : 0)) * 31;
        String str2 = this.lastUrl;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.lastHtml;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.lastImage;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.publisherId;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.metaData;
        if (str6 != null) {
            i = str6.hashCode();
        }
        return hashCode6 + i;
    }

    public String toString() {
        return "ValidationResult(instanceId=" + this.instanceId + ", numOfRedirect=" + this.numOfRedirect + ", sessionTime=" + this.sessionTime + ", redirectUrls=" + this.redirectUrls + ", lastUrl=" + this.lastUrl + ", lastHtml=" + this.lastHtml + ", lastImage=" + this.lastImage + ", publisherId=" + this.publisherId + ", metaData=" + this.metaData + ")";
    }

    public ValidationResult(String str, int i, long j, List<RedirectsResult> list, String str2, String str3, String str4, String str5, String str6) {
        h.b(str, "instanceId");
        h.b(list, "redirectUrls");
        h.b(str2, "lastUrl");
        h.b(str3, "lastHtml");
        h.b(str4, "lastImage");
        h.b(str5, "publisherId");
        h.b(str6, MetaData.KEY_METADATA);
        this.instanceId = str;
        this.numOfRedirect = i;
        this.sessionTime = j;
        this.redirectUrls = list;
        this.lastUrl = str2;
        this.lastHtml = str3;
        this.lastImage = str4;
        this.publisherId = str5;
        this.metaData = str6;
    }

    public final String getInstanceId() {
        return this.instanceId;
    }

    public final int getNumOfRedirect() {
        return this.numOfRedirect;
    }

    public final long getSessionTime() {
        return this.sessionTime;
    }

    public final List<RedirectsResult> getRedirectUrls() {
        return this.redirectUrls;
    }

    public final String getLastUrl() {
        return this.lastUrl;
    }

    public final String getLastHtml() {
        return this.lastHtml;
    }

    public final String getLastImage() {
        return this.lastImage;
    }

    public final String getPublisherId() {
        return this.publisherId;
    }

    public final String getMetaData() {
        return this.metaData;
    }
}
