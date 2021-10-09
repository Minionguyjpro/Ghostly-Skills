package com.startapp.android.publish.cache;

import java.io.Serializable;

/* compiled from: StartAppSDK */
public class h implements Serializable {
    private static final long serialVersionUID = 1;
    private String filename;
    private long lastUseTimestamp;
    private String videoLocation;

    public h(String str) {
        this.filename = str;
    }

    public String a() {
        return this.filename;
    }

    public String b() {
        return this.videoLocation;
    }

    public void a(String str) {
        this.videoLocation = str;
    }

    public void a(long j) {
        this.lastUseTimestamp = j;
    }

    public int hashCode() {
        int i;
        String str = this.filename;
        if (str == null) {
            i = 0;
        } else {
            i = str.hashCode();
        }
        return 31 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        h hVar = (h) obj;
        String str = this.filename;
        if (str == null) {
            if (hVar.filename != null) {
                return false;
            }
        } else if (!str.equals(hVar.filename)) {
            return false;
        }
        return true;
    }
}
