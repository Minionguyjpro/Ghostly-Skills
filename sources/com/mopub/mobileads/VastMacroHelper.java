package com.mopub.mobileads;

import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VastMacroHelper {
    private final Map<VastMacro, String> mMacroDataMap;
    private final List<String> mOriginalUris;

    public VastMacroHelper(List<String> list) {
        Preconditions.checkNotNull(list, "uris cannot be null");
        this.mOriginalUris = list;
        HashMap hashMap = new HashMap();
        this.mMacroDataMap = hashMap;
        hashMap.put(VastMacro.CACHEBUSTING, getCachebustingString());
    }

    public List<String> getUris() {
        ArrayList arrayList = new ArrayList();
        for (String next : this.mOriginalUris) {
            if (!TextUtils.isEmpty(next)) {
                for (VastMacro vastMacro : VastMacro.values()) {
                    String str = this.mMacroDataMap.get(vastMacro);
                    if (str == null) {
                        str = "";
                    }
                    next = next.replaceAll("\\[" + vastMacro.name() + "\\]", str);
                }
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public VastMacroHelper withErrorCode(VastErrorCode vastErrorCode) {
        if (vastErrorCode != null) {
            this.mMacroDataMap.put(VastMacro.ERRORCODE, vastErrorCode.getErrorCode());
        }
        return this;
    }

    public VastMacroHelper withContentPlayHead(Integer num) {
        if (num != null) {
            String formatContentPlayHead = formatContentPlayHead(num.intValue());
            if (!TextUtils.isEmpty(formatContentPlayHead)) {
                this.mMacroDataMap.put(VastMacro.CONTENTPLAYHEAD, formatContentPlayHead);
            }
        }
        return this;
    }

    public VastMacroHelper withAssetUri(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                str = URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, "Failed to encode url", e);
            }
            this.mMacroDataMap.put(VastMacro.ASSETURI, str);
        }
        return this;
    }

    private String getCachebustingString() {
        return String.format(Locale.US, "%08d", new Object[]{Long.valueOf(Math.round(Math.random() * 1.0E8d))});
    }

    private String formatContentPlayHead(int i) {
        long j = (long) i;
        return String.format("%02d:%02d:%02d.%03d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toHours(j)), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(j) % TimeUnit.HOURS.toMinutes(1)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % TimeUnit.MINUTES.toSeconds(1)), Integer.valueOf(i % 1000)});
    }
}
