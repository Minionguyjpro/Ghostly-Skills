package com.appsgeyser.multiTabApp.controllers;

import com.appsgeyser.multiTabApp.MainNavigationActivity;

public interface InjectMainNavigationController {
    MainNavigationActivity getMainNavigationActivity();

    void setMainNavigationActivity(MainNavigationActivity mainNavigationActivity);
}
