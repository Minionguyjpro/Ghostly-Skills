package com.mopub.volley.toolbox;

import com.mopub.volley.Cache;

public class NoCache implements Cache {
    public void clear() {
    }

    public Cache.Entry get(String str) {
        return null;
    }

    public void initialize() {
    }

    public void invalidate(String str, boolean z) {
    }

    public void put(String str, Cache.Entry entry) {
    }

    public void remove(String str) {
    }
}
