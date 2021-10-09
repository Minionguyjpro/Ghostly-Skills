package com.truenet.android;

import a.a.b.b.h;
import java.util.List;

/* compiled from: StartAppSDK */
public final class RedirectsResult {
    private final List<String> cookies;
    private final long loadTime;
    private final int response;
    private final String url;

    public static /* synthetic */ RedirectsResult copy$default(RedirectsResult redirectsResult, String str, long j, int i, List<String> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = redirectsResult.url;
        }
        if ((i2 & 2) != 0) {
            j = redirectsResult.loadTime;
        }
        long j2 = j;
        if ((i2 & 4) != 0) {
            i = redirectsResult.response;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            list = redirectsResult.cookies;
        }
        return redirectsResult.copy(str, j2, i3, list);
    }

    public final String component1() {
        return this.url;
    }

    public final long component2() {
        return this.loadTime;
    }

    public final int component3() {
        return this.response;
    }

    public final List<String> component4() {
        return this.cookies;
    }

    public final RedirectsResult copy(String str, long j, int i, List<String> list) {
        h.b(str, "url");
        h.b(list, "cookies");
        return new RedirectsResult(str, j, i, list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof RedirectsResult) {
                RedirectsResult redirectsResult = (RedirectsResult) obj;
                if (h.a((Object) this.url, (Object) redirectsResult.url)) {
                    if (this.loadTime == redirectsResult.loadTime) {
                        if (!(this.response == redirectsResult.response) || !h.a((Object) this.cookies, (Object) redirectsResult.cookies)) {
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
        String str = this.url;
        int i = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        long j = this.loadTime;
        int i2 = ((((hashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.response) * 31;
        List<String> list = this.cookies;
        if (list != null) {
            i = list.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        return "RedirectsResult(url=" + this.url + ", loadTime=" + this.loadTime + ", response=" + this.response + ", cookies=" + this.cookies + ")";
    }

    public RedirectsResult(String str, long j, int i, List<String> list) {
        h.b(str, "url");
        h.b(list, "cookies");
        this.url = str;
        this.loadTime = j;
        this.response = i;
        this.cookies = list;
    }

    public final List<String> getCookies() {
        return this.cookies;
    }

    public final long getLoadTime() {
        return this.loadTime;
    }

    public final int getResponse() {
        return this.response;
    }

    public final String getUrl() {
        return this.url;
    }
}
