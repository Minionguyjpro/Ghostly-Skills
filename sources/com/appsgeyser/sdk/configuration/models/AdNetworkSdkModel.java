package com.appsgeyser.sdk.configuration.models;

import com.google.gson.annotations.SerializedName;

public class AdNetworkSdkModel {
    @SerializedName("active")
    private boolean isActive;

    public boolean isActive() {
        return this.isActive;
    }
}
