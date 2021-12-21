package com.onesignal;

import android.os.Parcelable;
import android.os.PersistableBundle;

/* compiled from: BundleCompat */
class BundleCompatPersistableBundle implements BundleCompat<PersistableBundle> {
    private PersistableBundle mBundle = new PersistableBundle();

    BundleCompatPersistableBundle() {
    }

    public void putString(String str, String str2) {
        this.mBundle.putString(str, str2);
    }

    public void putLong(String str, Long l) {
        this.mBundle.putLong(str, l.longValue());
    }

    public String getString(String str) {
        return this.mBundle.getString(str);
    }

    public Integer getInt(String str) {
        return Integer.valueOf(this.mBundle.getInt(str));
    }

    public Long getLong(String str) {
        return Long.valueOf(this.mBundle.getLong(str));
    }

    public boolean getBoolean(String str, boolean z) {
        return this.mBundle.getBoolean(str, z);
    }

    public boolean containsKey(String str) {
        return this.mBundle.containsKey(str);
    }

    public PersistableBundle getBundle() {
        return this.mBundle;
    }

    public void setBundle(Parcelable parcelable) {
        this.mBundle = (PersistableBundle) parcelable;
    }
}
