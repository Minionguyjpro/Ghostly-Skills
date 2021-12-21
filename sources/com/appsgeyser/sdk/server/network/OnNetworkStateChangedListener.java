package com.appsgeyser.sdk.server.network;

public abstract class OnNetworkStateChangedListener {
    private String id;

    public abstract void networkIsDown();

    public abstract void networkIsUp();

    protected OnNetworkStateChangedListener(String str) {
        this.id = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.id.equals(((OnNetworkStateChangedListener) obj).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }
}
