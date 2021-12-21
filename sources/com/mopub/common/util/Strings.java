package com.mopub.common.util;

import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Strings {
    public static String fromStream(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[4096];
        int i = 0;
        while (i != -1) {
            sb.append(new String(bArr, 0, i));
            i = inputStream.read(bArr);
        }
        inputStream.close();
        return sb.toString();
    }

    public static String getDelimitedString(Object obj, String str) {
        if (!(obj instanceof List)) {
            return "";
        }
        List list = (List) obj;
        if (list.isEmpty()) {
            return "";
        }
        if (str == null) {
            str = ", ";
        }
        return TextUtils.join(str, list);
    }
}
