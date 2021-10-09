package com.appsgeyser.multiTabApp;

import androidx.multidex.MultiDexApplication;
import com.appsgeyser.sdk.AppsgeyserSDK;

public class AppsgeyserApplication extends MultiDexApplication {
    public void onCreate() {
        super.onCreate();
        AppsgeyserSDK.setApplicationInstance(this);
    }
}
