package com.mopub.network;

import android.content.Context;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.util.ResponseHeader;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.Response;
import com.mopub.volley.toolbox.HttpHeaderParser;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public abstract class MoPubRequest<T> extends Request<T> {
    private static final String JSON_CONTENT_TYPE = "application/json; charset=UTF-8";
    private final Context mContext;
    private final String mOriginalUrl;

    public MoPubRequest(Context context, String str, Response.ErrorListener errorListener) {
        super(MoPubRequestUtils.chooseMethod(str), MoPubRequestUtils.truncateQueryParamsIfPost(str), errorListener);
        this.mOriginalUrl = str;
        this.mContext = context.getApplicationContext();
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getParams() {
        if (!MoPubRequestUtils.isMoPubRequest(getUrl())) {
            return null;
        }
        return MoPubRequestUtils.convertQueryToMap(this.mContext, this.mOriginalUrl);
    }

    public String getBodyContentType() {
        if (MoPubRequestUtils.isMoPubRequest(getUrl())) {
            return JSON_CONTENT_TYPE;
        }
        return super.getBodyContentType();
    }

    public byte[] getBody() {
        String generateBodyFromParams = MoPubRequestUtils.generateBodyFromParams(getParams(), getUrl());
        if (generateBodyFromParams == null) {
            return null;
        }
        return generateBodyFromParams.getBytes();
    }

    /* access modifiers changed from: protected */
    public String parseStringBody(NetworkResponse networkResponse) {
        Preconditions.checkNotNull(networkResponse);
        try {
            return new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
        } catch (UnsupportedEncodingException unused) {
            return new String(networkResponse.data);
        }
    }

    public String getOriginalUrl() {
        return this.mOriginalUrl;
    }

    public Map<String, String> getHeaders() {
        Locale locale;
        String str;
        String str2;
        TreeMap treeMap = new TreeMap();
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList locales = this.mContext.getResources().getConfiguration().getLocales();
            locale = locales.size() > 0 ? locales.get(0) : null;
        } else {
            locale = this.mContext.getResources().getConfiguration().locale;
        }
        if (locale == null || TextUtils.isEmpty(locale.toString().trim())) {
            str = Locale.getDefault().getLanguage().trim();
            str2 = Locale.getDefault().getCountry().trim();
        } else {
            str = locale.getLanguage().trim();
            str2 = locale.getCountry().trim();
        }
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                str = str + "-" + str2.toLowerCase();
            }
            treeMap.put(ResponseHeader.ACCEPT_LANGUAGE.getKey(), str);
        }
        return treeMap;
    }
}
