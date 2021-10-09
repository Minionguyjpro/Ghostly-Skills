package com.truenet.android;

import a.a.b.b.h;
import com.startapp.android.publish.common.metaData.MetaData;

/* compiled from: StartAppSDK */
public final class Link {
    private final String htmlStorage;
    private final String imageStorage;
    private final String instanceId;
    private final String metaData;
    private final String validationUrl;

    public static /* synthetic */ Link copy$default(Link link, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = link.instanceId;
        }
        if ((i & 2) != 0) {
            str2 = link.validationUrl;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = link.imageStorage;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = link.htmlStorage;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = link.metaData;
        }
        return link.copy(str, str6, str7, str8, str5);
    }

    public final String component1() {
        return this.instanceId;
    }

    public final String component2() {
        return this.validationUrl;
    }

    public final String component3() {
        return this.imageStorage;
    }

    public final String component4() {
        return this.htmlStorage;
    }

    public final String component5() {
        return this.metaData;
    }

    public final Link copy(String str, String str2, String str3, String str4, String str5) {
        h.b(str, "instanceId");
        h.b(str2, "validationUrl");
        h.b(str3, "imageStorage");
        h.b(str4, "htmlStorage");
        h.b(str5, MetaData.KEY_METADATA);
        return new Link(str, str2, str3, str4, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Link)) {
            return false;
        }
        Link link = (Link) obj;
        return h.a((Object) this.instanceId, (Object) link.instanceId) && h.a((Object) this.validationUrl, (Object) link.validationUrl) && h.a((Object) this.imageStorage, (Object) link.imageStorage) && h.a((Object) this.htmlStorage, (Object) link.htmlStorage) && h.a((Object) this.metaData, (Object) link.metaData);
    }

    public int hashCode() {
        String str = this.instanceId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.validationUrl;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.imageStorage;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.htmlStorage;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.metaData;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "Link(instanceId=" + this.instanceId + ", validationUrl=" + this.validationUrl + ", imageStorage=" + this.imageStorage + ", htmlStorage=" + this.htmlStorage + ", metaData=" + this.metaData + ")";
    }

    public Link(String str, String str2, String str3, String str4, String str5) {
        h.b(str, "instanceId");
        h.b(str2, "validationUrl");
        h.b(str3, "imageStorage");
        h.b(str4, "htmlStorage");
        h.b(str5, MetaData.KEY_METADATA);
        this.instanceId = str;
        this.validationUrl = str2;
        this.imageStorage = str3;
        this.htmlStorage = str4;
        this.metaData = str5;
    }

    public final String getInstanceId() {
        return this.instanceId;
    }

    public final String getValidationUrl() {
        return this.validationUrl;
    }

    public final String getImageStorage() {
        return this.imageStorage;
    }

    public final String getHtmlStorage() {
        return this.htmlStorage;
    }

    public final String getMetaData() {
        return this.metaData;
    }

    public Link() {
        this("", "", "", "", "");
    }
}
