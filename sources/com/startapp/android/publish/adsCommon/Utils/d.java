package com.startapp.android.publish.adsCommon.Utils;

import com.startapp.common.e;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: StartAppSDK */
public class d extends e {

    /* renamed from: a  reason: collision with root package name */
    private List<NameValueObject> f177a;

    public d() {
        this.f177a = null;
        this.f177a = new ArrayList();
    }

    public void a(String str, Object obj, boolean z, boolean z2) {
        if (z && obj == null) {
            throw new e("Required key: [" + str + "] is missing", (Throwable) null);
        } else if (obj != null && !obj.toString().equals("")) {
            try {
                NameValueObject nameValueObject = new NameValueObject();
                nameValueObject.setName(str);
                String obj2 = obj.toString();
                if (z2) {
                    obj2 = URLEncoder.encode(obj2, "UTF-8");
                }
                nameValueObject.setValue(obj2);
                this.f177a.add(nameValueObject);
            } catch (UnsupportedEncodingException e) {
                if (z) {
                    throw new e("failed encoding value: [" + obj + "]", e);
                }
            }
        }
    }

    public void a(String str, Set<String> set, boolean z, boolean z2) {
        if (z && set == null) {
            throw new e("Required key: [" + str + "] is missing", (Throwable) null);
        } else if (set != null) {
            NameValueObject nameValueObject = new NameValueObject();
            nameValueObject.setName(str);
            HashSet hashSet = new HashSet();
            for (String next : set) {
                if (z2) {
                    try {
                        next = URLEncoder.encode(next, "UTF-8");
                    } catch (UnsupportedEncodingException unused) {
                    }
                }
                hashSet.add(next);
            }
            if (!z || hashSet.size() != 0) {
                nameValueObject.setValueSet(hashSet);
                this.f177a.add(nameValueObject);
                return;
            }
            throw new e("failed encoding value: [" + set + "]", (Throwable) null);
        }
    }

    public String toString() {
        Set<String> valueSet;
        StringBuilder sb = new StringBuilder();
        if (this.f177a == null) {
            return sb.toString();
        }
        sb.append('?');
        for (NameValueObject next : this.f177a) {
            if (next.getValue() != null) {
                sb.append(next.getName());
                sb.append('=');
                sb.append(next.getValue());
                sb.append('&');
            } else if (!(next.getValueSet() == null || (valueSet = next.getValueSet()) == null)) {
                for (String append : valueSet) {
                    sb.append(next.getName());
                    sb.append('=');
                    sb.append(append);
                    sb.append('&');
                }
            }
        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString().replace("+", "%20");
    }
}
